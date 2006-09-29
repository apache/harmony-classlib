/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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
/*
 * Created on 30.09.2004
 *
 */
package org.apache.harmony.awt.gl.image;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;

public class OrdinaryWritableRaster extends WritableRaster {

    public OrdinaryWritableRaster(SampleModel sampleModel,
            DataBuffer dataBuffer, Rectangle aRegion,
            Point sampleModelTranslate, WritableRaster parent) {
        super(sampleModel, dataBuffer, aRegion, sampleModelTranslate, parent);
    }

    public OrdinaryWritableRaster(SampleModel sampleModel,
            DataBuffer dataBuffer, Point origin) {
        super(sampleModel, dataBuffer, origin);
    }

    public OrdinaryWritableRaster(SampleModel sampleModel, Point origin) {
        super(sampleModel, origin);
    }

    public void setDataElements(int x, int y, Object inData) {
        super.setDataElements(x, y, inData);
    }

    public void setDataElements(int x, int y, int w, int h, Object inData) {
        super.setDataElements(x, y, w, h, inData);
    }

    public WritableRaster createWritableChild(int parentX, int parentY, int w,
            int h, int childMinX, int childMinY, int[] bandList) {
        return super.createWritableChild(parentX, parentY, w, h, childMinX,
                childMinY, bandList);
    }

    public WritableRaster createWritableTranslatedChild(int childMinX,
            int childMinY) {
        return super.createWritableTranslatedChild(childMinX, childMinY);
    }

    public WritableRaster getWritableParent() {
        return super.getWritableParent();
    }

    public void setRect(Raster srcRaster) {
        super.setRect(srcRaster);
    }

    public void setRect(int dx, int dy, Raster srcRaster) {
        super.setRect(dx, dy, srcRaster);
    }

    public void setDataElements(int x, int y, Raster inRaster) {
        super.setDataElements(x, y, inRaster);
    }

    public void setPixel(int x, int y, int[] iArray) {
        super.setPixel(x, y, iArray);
    }

    public void setPixel(int x, int y, float[] fArray) {
        super.setPixel(x, y, fArray);
    }

    public void setPixel(int x, int y, double[] dArray) {
        super.setPixel(x, y, dArray);
    }

    public void setPixels(int x, int y, int w, int h, int[] iArray) {
        super.setPixels(x, y, w, h, iArray);
    }

    public void setPixels(int x, int y, int w, int h, float[] fArray) {
        super.setPixels(x, y, w, h, fArray);
    }

    public void setPixels(int x, int y, int w, int h, double[] dArray) {
        super.setPixels(x, y, w, h, dArray);
    }

    public void setSamples(int x, int y, int w, int h, int b, int[] iArray) {
        super.setSamples(x, y, w, h, b, iArray);
    }

    public void setSamples(int x, int y, int w, int h, int b, float[] fArray) {
        super.setSamples(x, y, w, h, b, fArray);
    }

    public void setSamples(int x, int y, int w, int h, int b, double[] dArray) {
        super.setSamples(x, y, w, h, b, dArray);
    }

    public void setSample(int x, int y, int b, int s) {
        super.setSample(x, y, b, s);
    }

    public void setSample(int x, int y, int b, float s) {
        super.setSample(x, y, b, s);
    }

    public void setSample(int x, int y, int b, double s) {
        super.setSample(x, y, b, s);
    }
}