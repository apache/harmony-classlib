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
 * Created on 19.12.2005
 *
 */
package org.apache.harmony.awt.gl.windows;

import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import org.apache.harmony.awt.gl.Surface;


public class BitmapSurface extends Surface {

    public BitmapSurface(long gi, int width, int height){
        surfaceDataPtr = createSurfData(gi, width, height);
        this.width = width;
        this.height = height;
    }

    public void dispose() {
        dispose(surfaceDataPtr);
        surfaceDataPtr = 0L;
    }

    private native long createSurfData(long gi, int width, int height);

    private native void dispose(long structPtr);

    public long lock() {
        return 0;
    }

    public void unlock() {
    }

    public ColorModel getColorModel() {
        return null;
    }

    public WritableRaster getRaster() {
        return null;
    }

    public int getSurfaceType() {
        return 0;
    }

    public Surface getImageSurface() {
        return this;
    }

}
