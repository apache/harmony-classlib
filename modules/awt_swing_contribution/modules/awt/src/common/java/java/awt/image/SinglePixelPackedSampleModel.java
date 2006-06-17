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

import java.util.Arrays;

public class SinglePixelPackedSampleModel extends SampleModel {

    private int bitMasks[];

    private int bitOffsets[];

    private int bitSizes[];

    private int scanlineStride;

    private int maxBitSize;

    public SinglePixelPackedSampleModel(int dataType, int w, int h,
            int bitMasks[]) {
        this(dataType, w, h, w, bitMasks);
    }

    public SinglePixelPackedSampleModel(int dataType, int w, int h,
            int scanlineStride, int bitMasks[]) {

        super(dataType, w, h, bitMasks.length);

        if (dataType != DataBuffer.TYPE_BYTE &&
                dataType != DataBuffer.TYPE_USHORT &&
                dataType != DataBuffer.TYPE_INT)
            throw new IllegalArgumentException("Unsupported data type:"
                    + dataType);

        this.scanlineStride = scanlineStride;
        this.bitMasks = (int[]) bitMasks.clone();
        this.bitOffsets = new int[this.numBands];
        this.bitSizes = new int[this.numBands];

        this.maxBitSize = 0;

        for (int i = 0; i < this.numBands; i++) {
            int offset = 0;
            int size = 0;
            int mask = bitMasks[i];

            if (mask != 0) {
                while ((mask & 1) == 0) {
                    mask >>>= 1;
                    offset++;
                }

                while ((mask & 1) == 1) {
                    mask >>>= 1;
                    size++;
                }

                if (mask != 0)
                    throw new IllegalArgumentException("Wrong mask :"
                            + bitMasks[i]);
            }

            this.bitOffsets[i] = offset;
            this.bitSizes[i] = size;

            if (this.maxBitSize < size)
                this.maxBitSize = size;

        }

    }

    public Object getDataElements(int x, int y, Object obj, DataBuffer data) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height)
            throw new ArrayIndexOutOfBoundsException("Coordinates are " +
                    "not in bounds");
        switch (getTransferType()) {
        case DataBuffer.TYPE_BYTE:
            byte bdata[];
            if (obj == null)
                bdata = new byte[1];
            else
                bdata = (byte[]) obj;

            bdata[0] = (byte) data.getElem(y * scanlineStride + x);
            obj = (Object) bdata;
            break;
        case DataBuffer.TYPE_USHORT:
            short sdata[];
            if (obj == null)
                sdata = new short[1];
            else
                sdata = (short[]) obj;

            sdata[0] = (short) data.getElem(y * scanlineStride + x);
            obj = (Object) sdata;
            break;
        case DataBuffer.TYPE_INT:
            int idata[];
            if (obj == null)
                idata = new int[1];
            else
                idata = (int[]) obj;

            idata[0] = data.getElem(y * scanlineStride + x);
            obj = (Object) idata;
            break;
        }
        return obj;
    }

    public void setDataElements(int x, int y, Object obj, DataBuffer data) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height)
            throw new ArrayIndexOutOfBoundsException("Coordinates are " +
                    "not in bounds");
        switch (getTransferType()) {
        case DataBuffer.TYPE_BYTE:
            data.setElem(y * scanlineStride + x, ((byte[]) obj)[0] & 0xff);
            break;
        case DataBuffer.TYPE_USHORT:
            data.setElem(y * scanlineStride + x, ((short[]) obj)[0] & 0xffff);
            break;
        case DataBuffer.TYPE_INT:
            data.setElem(y * scanlineStride + x, ((int[]) obj)[0]);
            break;
        }
    }

    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof SinglePixelPackedSampleModel))
            return false;

        SinglePixelPackedSampleModel model = (SinglePixelPackedSampleModel) o;
        return this.width == model.width &&
                this.height == model.height &&
                this.numBands == model.numBands &&
                this.dataType == model.dataType &&
                Arrays.equals(this.bitMasks, model.bitMasks) &&
                Arrays.equals(this.bitOffsets, model.bitOffsets) &&
                Arrays.equals(this.bitSizes, model.bitSizes) &&
                this.scanlineStride == model.scanlineStride;
    }

    public SampleModel createSubsetSampleModel(int bands[]) {
        if (bands.length > this.numBands)
            throw new RasterFormatException("The number of the bands " +
                    "in the subset is greater than the number of bands " +
                    "in the sample model");

        int masks[] = new int[bands.length];
        for (int i = 0; i < bands.length; i++)
            masks[i] = this.bitMasks[bands[i]];
        return new SinglePixelPackedSampleModel(this.dataType, this.width,
                this.height, this.scanlineStride, masks);
    }

    public SampleModel createCompatibleSampleModel(int w, int h) {
        return new SinglePixelPackedSampleModel(this.dataType, w, h,
                this.bitMasks);
    }

    public int[] getPixel(int x, int y, int iArray[], DataBuffer data) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height)
            throw new ArrayIndexOutOfBoundsException("Coordinates are " +
                    "not in bounds");
        int pixel[];
        if (iArray == null)
            pixel = new int[this.numBands];
        else
            pixel = iArray;

        for (int i = 0; i < this.numBands; i++)
            pixel[i] = getSample(x, y, i, data);

        return pixel;
    }

    public void setPixel(int x, int y, int iArray[], DataBuffer data) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height)
            throw new ArrayIndexOutOfBoundsException("Coordinates are " +
                    "not in bounds");
        for (int i = 0; i < this.numBands; i++)
            setSample(x, y, i, iArray[i], data);
    }

    public int getSample(int x, int y, int b, DataBuffer data) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height)
            throw new ArrayIndexOutOfBoundsException("Coordinates are " +
                    "not in bounds");
        int sample = data.getElem(y * scanlineStride + x);
        return ((sample & this.bitMasks[b]) >>> this.bitOffsets[b]);
    }

    public int[] getPixels(int x, int y, int w, int h, int iArray[],
            DataBuffer data) {
        if (x < 0 || y < 0 || x + w > this.width || y + h > this.height)
            throw new ArrayIndexOutOfBoundsException("Coordinates are " +
                    "not in bounds");

        int pixels[];

        if (iArray == null)
            pixels = new int[w * h * this.numBands];
        else
            pixels = iArray;

        int idx = 0;

        for (int i = y; i < y + h; i++) {
            for (int j = x; j < x + w; j++)
                for (int n = 0; n < this.numBands; n++)
                    pixels[idx++] = getSample(j, i, n, data);
        }
        return pixels;
    }

    public void setPixels(int x, int y, int w, int h, int iArray[],
            DataBuffer data) {
        if (x < 0 || y < 0 || x + w > this.width || y + h > this.height)
            throw new ArrayIndexOutOfBoundsException("Coordinates are " +
                    "not in bounds");

        int idx = 0;

        for (int i = y; i < y + h; i++) {
            for (int j = x; j < x + w; j++)
                for (int n = 0; n < this.numBands; n++)
                    setSample(j, i, n, iArray[idx++], data);
        }
    }

    public void setSample(int x, int y, int b, int s, DataBuffer data) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height)
            throw new ArrayIndexOutOfBoundsException("Coordinates are " +
                    "not in bounds");
        int tmp = data.getElem(y * scanlineStride + x);
        tmp &= ~this.bitMasks[b];
        tmp |= (s << this.bitOffsets[b]) & this.bitMasks[b];
        data.setElem(y * scanlineStride + x, tmp);
    }

    public int[] getSamples(int x, int y, int w, int h, int b, int iArray[],
            DataBuffer data) {
        if (x < 0 || y < 0 || x + w > this.width || y + h > this.height)
            throw new ArrayIndexOutOfBoundsException("Coordinates are " +
                    "not in bounds");

        int samples[];
        int idx = 0;

        if (iArray == null)
            samples = new int[w * h];
        else
            samples = iArray;

        for (int i = y; i < y + h; i++)
            for (int j = x; j < x + w; j++)
                samples[idx++] = getSample(j, i, b, data);

        return samples;
    }

    public void setSamples(int x, int y, int w, int h, int b, int iArray[],
            DataBuffer data) {
        if (x < 0 || y < 0 || x + w > this.width || y + h > this.height)
            throw new ArrayIndexOutOfBoundsException("Coordinates are " +
                    "not in bounds");

        int idx = 0;
        for (int i = y; i < y + h; i++)
            for (int j = x; j < x + w; j++)
                setSample(x + j, y + i, b, iArray[idx++], data);
    }

    public DataBuffer createDataBuffer() {
        DataBuffer data = null;
        int size = (this.height - 1) * scanlineStride + width;

        switch (this.dataType) {
        case DataBuffer.TYPE_BYTE:
            data = new DataBufferByte(size);
            break;
        case DataBuffer.TYPE_USHORT:
            data = new DataBufferUShort(size);
            break;
        case DataBuffer.TYPE_INT:
            data = new DataBufferInt(size);
            break;
        }
        return data;
    }

    public int getOffset(int x, int y) {
        return (y * scanlineStride + x);
    }

    public int getSampleSize(int band) {
        return bitSizes[band];
    }

    public int[] getSampleSize() {
        return (int[]) bitSizes.clone();
    }

    public int[] getBitOffsets() {
        return (int[]) bitOffsets.clone();
    }

    public int[] getBitMasks() {
        return (int[]) bitMasks.clone();
    }

    public int hashCode() {
        int hash = 0;
        int tmp = 0;

        hash = width;
        tmp = hash >>> 24;
        hash <<= 8;
        hash |= tmp;
        hash ^= height;
        tmp = hash >>> 24;
        hash <<= 8;
        hash |= tmp;
        hash ^= numBands;
        tmp = hash >>> 24;
        hash <<= 8;
        hash |= tmp;
        hash ^= dataType;
        tmp = hash >>> 24;
        hash <<= 8;
        hash |= tmp;
        for (int i = 0; i < bitMasks.length; i++) {
            hash ^= bitMasks[i];
            tmp = hash >>> 24;
            hash <<= 8;
            hash |= tmp;
        }
        for (int i = 0; i < bitOffsets.length; i++) {
            hash ^= bitOffsets[i];
            tmp = hash >>> 24;
            hash <<= 8;
            hash |= tmp;
        }
        for (int i = 0; i < bitSizes.length; i++) {
            hash ^= bitSizes[i];
            tmp = hash >>> 24;
            hash <<= 8;
            hash |= tmp;
        }
        hash ^= scanlineStride;
        return hash;
    }

    public int getScanlineStride() {
        return this.scanlineStride;
    }

    public int getNumDataElements() {
        return 1;
    }

}

