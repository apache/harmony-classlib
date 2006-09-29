/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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
 * @author Alexey A. Ivanov
 * @version $Revision$
 */
package javax.swing.text.html;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * Loads an image asynchronously, that is in another thread.
 */
class BackgroundImageLoader implements ImageObserver {
    final int desiredWidth;
    final int desiredHeight;

    final Image image;

    private int imageWidth = -1;
    private int imageHeight = -1;

    private volatile boolean ready;
    private volatile boolean error;

    BackgroundImageLoader(final URL url,
                          final int desiredWidth, final int desiredHeight) {
        this.desiredWidth = desiredWidth;
        this.desiredHeight = desiredHeight;

        error = url == null;
        if (!error) {
            final Toolkit tk = Toolkit.getDefaultToolkit();
            image = tk.createImage(url);
            tk.prepareImage(image, desiredWidth, desiredHeight, this);
        } else {
            image = null;
        }
    }

    public boolean imageUpdate(final Image image, final int flags,
                               final int x, final int y,
                               final int width, final int height) {
        if ((flags & WIDTH) != 0) {
            imageWidth = desiredWidth == -1 ? width : desiredWidth;
        }
        if ((flags & HEIGHT) != 0) {
            imageHeight = desiredHeight == -1 ? height : desiredHeight;
        }
        if ((flags & ALLBITS) != 0) {
            ready = true;
            onReady();
        }
        if ((flags & (ERROR | ABORT)) != 0) {
            error = true;
            onError();
            return false;
        }
        return width == -1 || height == -1 || !ready;
    }

    public final Image getImage() {
        return image;
    }

    public final int getWidth() {
        return imageWidth;
    }

    public final int getHeight() {
        return imageHeight;
    }

    public final boolean isReady() {
        return ready;
    }

    public final boolean isError() {
        return error;
    }

    public final void waitForImage() {
        while (!ready && !error) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void onReady() {
    }

    protected void onError() {
    }
}