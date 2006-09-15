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

    public void preferLocaleFonts() {
        // Note: API specification says following:
        // "The actual change in font rendering behavior resulting
        // from a call to this method is implementation dependent;
        // it may have no effect at all." So, doing nothing is an
        // acceptable behavior for this method.

        // For now FontManager uses 1.4 font.properties scheme for font mapping, so
        // this method doesn't make any sense. The implementation of this method
        // which will influence font mapping is postponed until
        // 1.5 mapping scheme not implemented.

        // todo - Implement non-default behavior with 1.5 font mapping scheme
    }

    public void preferProportionalFonts() {
        // Note: API specification says following:
        // "The actual change in font rendering behavior resulting
        // from a call to this method is implementation dependent;
        // it may have no effect at all." So, doing nothing is an
        // acceptable behavior for this method.

        // For now FontManager uses 1.4 font.properties scheme for font mapping, so
        // this method doesn't make any sense. The implementation of this method
        // which will influence font mapping is postponed until
        // 1.5 mapping scheme not implemented.

        // todo - Implement non-default behavior with 1.5 font mapping scheme
    }

    public abstract Graphics2D createGraphics(BufferedImage bufferedImage);

    public abstract Font[] getAllFonts();

    public abstract String[] getAvailableFontFamilyNames();

    public abstract String[] getAvailableFontFamilyNames(Locale locale);

    public abstract GraphicsDevice getDefaultScreenDevice() throws HeadlessException;

    public abstract GraphicsDevice[] getScreenDevices() throws HeadlessException;
}
