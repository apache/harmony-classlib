/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.nio;

import org.apache.harmony.luni.util.Msg;

/*
 * Static methods. Used by io and nio packages.
 * 
 */
public final class Util {

    // -------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------

    /*
     * No instance.
     */
    private Util() {
        super();
    }

    // -------------------------------------------------------------------
    // Routine methods.
    // -------------------------------------------------------------------

    /*
     * Check array bounds method for methods like doSomething(Object[], offset,
     * length). Exception throws order is IndexOutOfBoundsException for negative
     * index, NullPointerException for null array, IndexOutOfBoundsException for
     * offset+length > array.length
     */
    public static void assertArrayIndex(Object[] array, int offset, int length) {
        if (offset < 0 || length < 0) {
            throw new IndexOutOfBoundsException(Msg.getString("K0006"));
        }
        if ((long) offset + (long) length > array.length) {
            throw new IndexOutOfBoundsException(Msg.getString("K00ae"));
        }
    }

    public static void assertArrayIndex(boolean[] array, int offset, int length) {
        if (offset < 0 || length < 0) {
            throw new IndexOutOfBoundsException(Msg.getString("K0006"));
        }
        if ((long) offset + (long) length > array.length) {
            throw new IndexOutOfBoundsException(Msg.getString("K00ae"));
        }
    }

    public static void assertArrayIndex(byte[] array, int offset, int length) {
        if (offset < 0 || length < 0) {
            throw new IndexOutOfBoundsException(Msg.getString("K0006"));
        }
        if ((long) offset + (long) length > array.length) {
            throw new IndexOutOfBoundsException(Msg.getString("K00ae"));
        }
    }

    public static void assertArrayIndex(short[] array, int offset, int length) {
        if (offset < 0 || length < 0) {
            throw new IndexOutOfBoundsException(Msg.getString("K0006"));
        }
        if ((long) offset + (long) length > array.length) {
            throw new IndexOutOfBoundsException(Msg.getString("K00ae"));
        }
    }

    public static void assertArrayIndex(int[] array, int offset, int length) {
        if (offset < 0 || length < 0) {
            throw new IndexOutOfBoundsException(Msg.getString("K0006"));
        }
        if ((long) offset + (long) length > array.length) {
            throw new IndexOutOfBoundsException(Msg.getString("K00ae"));
        }
    }

    public static void assertArrayIndex(long[] array, int offset, int length) {
        if (offset < 0 || length < 0) {
            throw new IndexOutOfBoundsException(Msg.getString("K0006"));
        }
        if ((long) offset + (long) length > array.length) {
            throw new IndexOutOfBoundsException(Msg.getString("K00ae"));
        }
    }

    public static void assertArrayIndex(float[] array, int offset, int length) {
        if (offset < 0 || length < 0) {
            throw new IndexOutOfBoundsException(Msg.getString("K0006"));
        }
        if ((long) offset + (long) length > array.length) {
            throw new IndexOutOfBoundsException(Msg.getString("K00ae"));
        }
    }

    public static void assertArrayIndex(double[] array, int offset, int length) {
        if (offset < 0 || length < 0) {
            throw new IndexOutOfBoundsException(Msg.getString("K0006"));
        }
        if ((long) offset + (long) length > array.length) {
            throw new IndexOutOfBoundsException(Msg.getString("K00ae"));
        }
    }

    public static void assertArrayIndex(char[] array, int offset, int length) {
        if (offset < 0 || length < 0) {
            throw new IndexOutOfBoundsException(Msg.getString("K0006"));
        }
        if ((long) offset + (long) length > array.length) {
            throw new IndexOutOfBoundsException(Msg.getString("K00ae"));
        }
    }

    /*
     * Check array bounds method for methods like doSomething(Object[], offset,
     * length). Exception throws order is IndexOutOfBoundsException for negative
     * index, IndexOutOfBoundsException for offset+length > array.length
     */
    public static void assertArrayIndex(int arrayLength, int offset, int length) {
        if (offset < 0 || length < 0) {
            throw new IndexOutOfBoundsException(Msg.getString("K0006"));
        }
        if ((long) offset + (long) length > arrayLength) {
            throw new IndexOutOfBoundsException(Msg.getString("K00ae"));
        }
    }
}
