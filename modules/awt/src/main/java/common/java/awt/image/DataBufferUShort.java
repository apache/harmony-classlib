/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
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
 * @author Igor V. Stolyarov
 * @version $Revision$
 */
package java.awt.image;


public final class DataBufferUShort extends DataBuffer {

    short data[][];

    public DataBufferUShort(short dataArrays[][], int size, int offsets[]) {
        super(TYPE_USHORT, size, dataArrays.length, offsets);
        data = (short[][])dataArrays.clone();
    }

    public DataBufferUShort(short dataArrays[][], int size) {
        super(TYPE_USHORT, size, dataArrays.length);
        data = (short[][])dataArrays.clone();
    }

    public DataBufferUShort(short dataArray[], int size, int offset) {
        super(TYPE_USHORT, size, 1, offset);
        data = new short[1][];
        data[0] = dataArray;
    }

    public DataBufferUShort(short dataArray[], int size) {
        super(TYPE_USHORT, size);
        data = new short[1][];
        data[0] = dataArray;
    }

    public DataBufferUShort(int size, int numBanks) {
        super(TYPE_USHORT, size, numBanks);
        data = new short[numBanks][];
        int i= 0;
        while( i < numBanks) {
            data[i++] = new short[size];
        }
    }

    public DataBufferUShort(int size) {
        super(TYPE_USHORT, size);
        data = new short[1][];
        data[0] = new short[size];
    }

    public void setElem(int bank, int i, int val) {
        data[bank][offsets[bank] + i] = (short)val;
    }

    public void setElem(int i, int val) {
        data[0][offset + i] = (short)val;
    }

    public int getElem(int bank, int i) {
        return (int)(data[bank][offsets[bank] + i]) & 0xffff;
    }

    public short[] getData(int bank) {
        return data[bank];
    }

    public int getElem(int i) {
        return (int)(data[0][offset + i]) & 0xffff;
    }

    public short[][] getBankData() {
        return (short[][])data.clone();
    }

    public short[] getData() {
        return data[0];
    }
}

