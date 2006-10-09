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
 * @author Rustem V. Rafikov
 * @version $Revision: 1.3 $
 */
package javax.imageio;

import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.event.IIOWriteProgressListener;
import javax.imageio.event.IIOWriteWarningListener;
import java.io.IOException;
import java.awt.image.RenderedImage;
import java.util.Locale;
import java.util.List;
import java.util.Iterator;

/**
 * TODO: Implement the rest of methods
 */
public abstract class ImageWriter implements ImageTranscoder {

    protected Locale[] availableLocales;
    protected Locale locale;
    protected ImageWriterSpi originatingProvider;
    protected Object output;
    protected List progressListeners;
    protected List warningListeners;
    protected List warningLocales;


    protected ImageWriter(ImageWriterSpi originatingProvider) {
        this.originatingProvider = originatingProvider;
    }

    public abstract IIOMetadata convertStreamMetadata(IIOMetadata iioMetadata,
                                             ImageWriteParam imageWriteParam);

    public abstract IIOMetadata convertImageMetadata(IIOMetadata iioMetadata,
                                            ImageTypeSpecifier imageTypeSpecifier,
                                            ImageWriteParam imageWriteParam);

    public ImageWriterSpi getOriginatingProvider() {
        return originatingProvider;
    }

    protected void processImageStarted(int imageIndex) {
        if (null != progressListeners) {
            for (Iterator iterator = progressListeners.iterator(); iterator.hasNext();) {
                IIOWriteProgressListener listener = (IIOWriteProgressListener) iterator.next();
                listener.imageStarted(this, imageIndex);
            }
        }
    }

    protected void processImageProgress(float percentageDone) {
        if (null != progressListeners) {
            for (Iterator iterator = progressListeners.iterator(); iterator.hasNext();) {
                IIOWriteProgressListener listener = (IIOWriteProgressListener) iterator.next();
                listener.imageProgress(this, percentageDone);
            }
        }
    }

    protected void processImageComplete() {
        if (null != progressListeners) {
            for (Iterator iterator = progressListeners.iterator(); iterator.hasNext();) {
                IIOWriteProgressListener listener = (IIOWriteProgressListener) iterator.next();
                listener.imageComplete(this);
            }
        }
    }

    protected void processWarningOccurred(int imageIndex, String warning) {
        if (null == warning) {
            throw new NullPointerException("warning message should not be NULL");
        }
        if (null != warningListeners) {
            for (Iterator iterator = warningListeners.iterator(); iterator.hasNext();) {
                IIOWriteWarningListener listener = (IIOWriteWarningListener) iterator.next();
                listener.warningOccurred(this, imageIndex, warning);
            }
        }
    }

    protected void processWarningOccurred(int imageIndex, String bundle, String key) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public void setOutput(Object output) {
        if (output != null) {
            ImageWriterSpi spi = getOriginatingProvider();
            if (null != spi) {
                Class[] outTypes = spi.getOutputTypes();
                boolean supported = false;
                for(int i = 0; i < outTypes.length; i++) {
                    if (outTypes[i].isInstance(output)) {
                        supported = true;
                        break;
                    }
                }
                if (!supported) {
                    throw new IllegalArgumentException("output " + output + " is not supported");
                }
            }
        }
        this.output = output;
    }

    public void write(IIOImage image) throws IOException {
        write(null, image, null);
    }

    public void write(RenderedImage image) throws IOException {
        write(null, new IIOImage(image, null, null), null);
    }

    /**
     * @throws  IllegalStateException - if the output has not been set.
     * @throws UnsupportedOperationException - if image contains a Raster and canWriteRasters returns false.
     * @throws IllegalArgumentException - if image is null.
     * @throws IOException - if an error occurs during writing.
     *
     * if !canWriteRaster() then Image must contain only RenderedImage

     * @param streamMetadata <code>null</code> for default stream metadata
     * @param image
     * @param param <code>null</code> for default params
     */
    public abstract void write(IIOMetadata streamMetadata,
                               IIOImage image, ImageWriteParam param) throws IOException;

    public void dispose() {
        // def impl. does nothing according to the spec.
    }
}
