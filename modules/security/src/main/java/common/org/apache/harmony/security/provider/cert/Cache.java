/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
* @author Alexander Y. Kleymenov
* @version $Revision$
*/

package org.apache.harmony.security.provider.cert;

import java.util.Arrays;

/**
 * Makes correspondences between Objects and arrays of bytes.
 * Arrays of bytes should not be less than prefix_size.
 */
public class Cache {
    
    // size of the cache
    private final int cache_size;
    // array containing the hash codes of encodings
    // hash has the following structure:
    // it consist of 6 bytes: 
    //      2 bytes for prefix hash
    //      2 bytes for tail hash
    //      2 reserved bytes (equals 0 in this array)
    private final long[] hashes;
    // array containing hash<->index correspondings:
    // reserved 2 bytes contains the value of the index in cache table
    private final long[] hashes_idx;
    // array containing the encodings of the cached objects
    private final byte[][] encodings;
    // array containing the cached certificates
    private final Object[] cache;
    // how many times cached value had been demanded
    //private long[] demanded = new long[cache_size];

    // the number of bytes which will be used for array hash generation.
    private final int prefix_size = 28;
    
    private int last_cached = 0;
    private boolean cache_is_full = false;

    private static final int  INDEX_MASK = 0x00FFFF;
    private static final long HASH_MASK = 0xFFFFFFFFFFFF0000L;
    private static final long PREFIX_HASH_MASK = 0xFFFFFFFF00000000L;

    /**
     * Creates the Cache object. Capasity of the cache is size, 
     * 
     * @param   size:   capacity of the cache. 
     * @param   pref_size:  the number of bytes which will be used
     * for array hash generation.
     */
    public Cache(int size) {
        cache_size = size;
        hashes = new long[cache_size];
        hashes_idx = new long[cache_size];
        encodings = new byte[cache_size][];
        cache = new Object[cache_size];
    }

    public Cache() {
        this(900);
    }

    // Returns the hash value of the array (which length should be
    // greater of equal to prefix_size).
    public long getHash(byte[] arr) {
        long hash = 0;
        for (int i=1; i<prefix_size; i++) {
            hash += (arr[i] & 0xFF);
        } // it takes about 2 bytes for prefix_size == 28

        // 2 bytes for array prefix hash, 2 for suffix hash, 2 bytes for index
        hash = hash << 32;
        //System.out.println("getHash: "+Long.toHexString(hash));
        return hash;
    }
    
    public long getSuffHash(byte[] arr) {
        long hash_addon = 0;
        for (int i=arr.length-1; i>arr.length - prefix_size; i--) {
            hash_addon += (arr[i] & 0xFF);
        }
        return hash_addon << 16;
    }
    
    public void put(long hash, byte[] encoding, Object cert) {
        // index pointing to the item of the table to be overwritten
        int index;
        // TODO: make throw out line:
        if (last_cached == cache_size) {
            last_cached = 0;
            cache_is_full = true;
        }
        index = last_cached++;

        // improove the hash value (now we know the tail of encoding):
        hash |= getSuffHash(encoding);

        if (cache_is_full) {
            // indexing hash value to be overwritten:
            long idx_hash = (hashes[index] | (index+1));
            int idx = Arrays.binarySearch(hashes_idx, idx_hash);
            if (idx < 0) {
                // it will never happen because we use saved hash value
                // (hashes[index])
                System.out.println("WARNING! "+idx);
                idx = -(idx + 1);
            }
            long new_hash_idx = (hash | (index + 1));
            int new_idx = Arrays.binarySearch(hashes_idx, new_hash_idx);
            if (new_idx >= 0) {
                // it's possible when we write the same hash in the same cell
                if (idx != new_idx) {
                    // it will never happen because we use the same
                    // hash and the same index in hash table
                    System.out.println("WARNING: ");
                    System.out.println(">> idx: "+idx+" new_idx: "+new_idx);
                } 
            } else {
                new_idx = -(new_idx + 1);
                // replace in sorted array
                if (new_idx > idx) {
                    System.arraycopy(hashes_idx, idx+1, hashes_idx, idx, 
                            new_idx - idx - 1);
                    hashes_idx[new_idx-1] = new_hash_idx;
                } else if (idx > new_idx) {
                    System.arraycopy(hashes_idx, new_idx, hashes_idx, new_idx+1, 
                            idx - new_idx);
                    hashes_idx[new_idx] = new_hash_idx;
                } else { // idx == new_idx
                    hashes_idx[new_idx] = new_hash_idx;
                }
            }
        } else {
            long idx_hash = (hash | (index + 1));
            int idx = Arrays.binarySearch(hashes_idx, idx_hash);
            if (idx < 0) {
                // it will always be true because idx_hash depends on index
                idx = -(idx + 1);
            }
            idx = idx - 1;
            if (idx != cache_size - index - 1) { 
                // if not in cell containing 0, do copy:
                System.arraycopy(hashes_idx, cache_size - index, 
                        hashes_idx, cache_size - index - 1, 
                        idx - (cache_size - index) + 1);
            }
            hashes_idx[idx] = idx_hash;
        }
        // overwrite the values in the tables:
        hashes[index] = hash;
        encodings[index] = encoding;
        cache[index] = cert;
    }
   
    private boolean arrEquals(byte[] arr1, byte[] arr2) {
        return Arrays.equals(arr1, arr2);
        /*
        // comparison from the tail:
        int length = arr1.length;
        if (length != arr2.length) {
            return false;
        }
        while (length > 0) {
            if (arr1[--length] != arr2[length]) {
                return false;
            }
        }
        return true;
        //*/
    }

    public Object get(long hash, byte[] encoding) {
        hash |= getSuffHash(encoding);
        int idx = -1*Arrays.binarySearch(hashes_idx, hash)-1;
        if (idx == cache_size) {
            return null;
        }
        while ((hashes_idx[idx] & HASH_MASK) == hash) {
            int i = (int) (hashes_idx[idx] & INDEX_MASK) - 1;
            if (arrEquals(encoding, encodings[i])) {
                // Uncomment to see the number of objects 
                // retrieved from the cache:
                //
                // if (XXX%2500 == 0)
                //     System.out.println(">> "+XXX);
                // XXX++;
                return cache[i];
            }
            idx++;
            if (idx == cache_size) {
                return null;
            }
        }
        return null;
    }
    
    public boolean contains(long prefix_hash) {
        int idx = -1*Arrays.binarySearch(hashes_idx, prefix_hash)-1;
        if (idx == cache_size) {
            return false;
        } else {
            return (hashes_idx[idx] & PREFIX_HASH_MASK) == prefix_hash;
        }
    }
}

