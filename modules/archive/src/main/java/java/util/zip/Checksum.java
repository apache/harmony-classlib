/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.util.zip;

/**
 * Interface to types that can compute a checksum value of given data.
 */
public interface Checksum {

    /**
     * Answers the computed checksum value so far.
     * 
     * @return the checksum value
     */
    public long getValue();

    /**
     * Reinitialize the checksum computation to its starting value.
     */
    public void reset();

    /**
     * Update the checksum value based on the given byte array
     * 
     * @param buf
     *            the data used to update the checksum
     * @param off
     *            the starting point for data values
     * @param nbytes
     *            the number of bytes to consider
     */
    public void update(byte[] buf, int off, int nbytes);

    /**
     * Update the checksum value based on the given data value.
     * 
     * @param val
     *            a single byte value
     */
    public void update(int val);
}
