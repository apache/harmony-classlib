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
 * Created on 10.01.2006
 *
 */
package org.apache.harmony.awt.gl.windows;

import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import org.apache.harmony.awt.gl.Surface;

/**
 * This class represent Window Surface and Surface for WinVolatileImages.
 * Used conjointly with GDIBlitter.
 */

public class GDISurface extends Surface {

    public GDISurface(long gi){
        surfaceDataPtr = createSurfData(gi);
    }
    public void dispose() {
        if (surfaceDataPtr == 0)
            return;
        
        dispose(surfaceDataPtr);
        surfaceDataPtr = 0;
    }

    public long lock() {
        return 0;
    }

    public void unlock() {

    }

    private native long createSurfData(long gi);

    private native void dispose(long structPtr);

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
