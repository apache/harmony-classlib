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
 * @author Alexey A. Petrenko
 * @version $Revision$
 */
package java.awt.image;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.ImageCapabilities;
import java.awt.Transparency;
import java.awt.image.AwtImageBackdoorAccessorImpl;

/**
 * Volatile image implementation
 */
public abstract class VolatileImage extends Image
    // Volatile image implements Transparency since 1.5
    implements Transparency {
    /***************************************************************************
    *
    *  Constants
    *
    ***************************************************************************/

    public static final int IMAGE_INCOMPATIBLE = 2;

    public static final int IMAGE_OK = 0;

    public static final int IMAGE_RESTORED = 1;


    static {
        AwtImageBackdoorAccessorImpl.init();
    }

    protected int transparency = OPAQUE;

    /***************************************************************************
    *
    *  Constructors
    *
    ***************************************************************************/

    public VolatileImage() {
        super();
    }



    /***************************************************************************
    *
    *  Abstract methods
    *
    ***************************************************************************/

    public abstract boolean contentsLost();

    public abstract Graphics2D createGraphics();

    public abstract ImageCapabilities getCapabilities();

    public abstract int getHeight();

    public abstract BufferedImage getSnapshot();

    public abstract int getWidth();

    public abstract int validate(GraphicsConfiguration gc);


    /***************************************************************************
    *
    *  Public methods
    *
    ***************************************************************************/

    public void flush() {
    }

    public Graphics getGraphics() {
        return createGraphics();
    }

    public ImageProducer getSource() {
        return getSnapshot().getSource();
    }

    public int getTransparency() {
        return transparency;
    }
}
