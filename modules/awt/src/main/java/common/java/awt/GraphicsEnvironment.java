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
 * @author Oleg V. Khaschansky
 * @version $Revision$
 */

package java.awt;

import java.awt.image.BufferedImage;
import java.util.Locale;

import org.apache.harmony.awt.*;
import org.apache.harmony.awt.gl.*;


public abstract class GraphicsEnvironment {
    protected GraphicsEnvironment() {}

    public static GraphicsEnvironment getLocalGraphicsEnvironment() {
        synchronized(ContextStorage.getContextLock()) {
            if (ContextStorage.getGraphicsEnvironment() == null) {
                CommonGraphics2DFactory g2df =
                        (CommonGraphics2DFactory) Toolkit.getDefaultToolkit().getGraphicsFactory();
                ContextStorage.setGraphicsEnvironment(
                        g2df.createGraphicsEnvironment(ContextStorage.getWindowFactory())
                );
            }

            return ContextStorage.getGraphicsEnvironment();
        }
    }

    public boolean isHeadlessInstance() {
        // At present headless mode not supported, HeadlessException will never be thrown
        try {
            getDefaultScreenDevice();
        } catch (HeadlessException e) {
            return true;
        }

        return false;
    }

    public static boolean isHeadless() {
        return getLocalGraphicsEnvironment().isHeadlessInstance();
    }

    public Rectangle getMaximumWindowBounds() throws HeadlessException {
        return getDefaultScreenDevice().getDefaultConfiguration().getBounds();
    }

    public Point getCenterPoint() throws HeadlessException {
        Rectangle mwb = getMaximumWindowBounds();
        return new Point(mwb.width >> 1, mwb.height >> 1);
    }

    public abstract Graphics2D createGraphics(BufferedImage bufferedImage);

    public abstract Font[] getAllFonts();

    public abstract String[] getAvailableFontFamilyNames();

    public abstract String[] getAvailableFontFamilyNames(Locale locale);

    public abstract GraphicsDevice getDefaultScreenDevice() throws HeadlessException;

    public abstract GraphicsDevice[] getScreenDevices() throws HeadlessException;
}
