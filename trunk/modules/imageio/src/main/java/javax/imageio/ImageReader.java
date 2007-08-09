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

import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.event.IIOReadWarningListener;
import javax.imageio.event.IIOReadProgressListener;
import javax.imageio.event.IIOReadUpdateListener;
import java.util.Locale;
import java.util.List;
import java.util.Iterator;
import java.util.Set;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.*;

public abstract class ImageReader {

    protected ImageReaderSpi originatingProvider;

    protected Object input;

    protected boolean seekForwardOnly;

    protected boolean ignoreMetadata;

    protected int minIndex;

    protected Locale[] availableLocales;

    protected Locale locale;

    protected List<IIOReadWarningListener> warningListeners;

    protected List<Locale> warningLocales;

    protected List<IIOReadProgressListener> progressListeners;

    protected List<IIOReadUpdateListener> updateListeners;

    protected ImageReader(ImageReaderSpi originatingProvider) {
        this.originatingProvider = originatingProvider;
    }

    public String getFormatName() throws IOException {
        return originatingProvider.getFormatNames()[0];
    }

    public ImageReaderSpi getOriginatingProvider() {
        return originatingProvider;
    }

    public void setInput(Object input, boolean seekForwardOnly, boolean ignoreMetadata) {
        if (input != null) {
            if (!isSupported(input) && !(input instanceof ImageInputStream)) {
                throw new IllegalArgumentException("input " + input + " is not supported");
            }
        }
        this.minIndex = 0;
        this.seekForwardOnly = seekForwardOnly;
        this.ignoreMetadata = ignoreMetadata;
        this.input = input;
    }

    private boolean isSupported(Object input) {
        ImageReaderSpi spi = getOriginatingProvider();
        if (null != spi) {
            Class[] outTypes = spi.getInputTypes();
            for (Class<?> element : outTypes) {
                if (element.isInstance(input)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setInput(Object input, boolean seekForwardOnly) {
        setInput(input, seekForwardOnly, false);
    }

    public void setInput(Object input) {
        setInput(input, false, false);
    }

    public Object getInput() {
        return input;
    }

    public boolean isSeekForwardOnly() {
        return seekForwardOnly;
    }

    public boolean isIgnoringMetadata() {
        return ignoreMetadata;
    }

    public int getMinIndex() {
        return minIndex;
    }

    public Locale[] getAvailableLocales() {
        return availableLocales;
    }

    public void setLocale(Locale locale) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Locale getLocale() {
        return locale;
    }

    public abstract int getNumImages(boolean allowSearch) throws IOException;

    public abstract int getWidth(int imageIndex) throws IOException;

    public abstract int getHeight(int imageIndex) throws IOException;

    public boolean isRandomAccessEasy(int imageIndex) throws IOException {
        return false; //def
    }

    public float getAspectRatio(int imageIndex) throws IOException {
        return (float) getWidth(imageIndex) / getHeight(imageIndex);
    }

    public ImageTypeSpecifier getRawImageType(int imageIndex) throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public abstract Iterator<ImageTypeSpecifier> getImageTypes(int imageIndex) throws IOException;

    public ImageReadParam getDefaultReadParam() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public abstract IIOMetadata getStreamMetadata() throws IOException;

    public IIOMetadata getStreamMetadata(String formatName, Set<String> nodeNames)
            throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public abstract IIOMetadata getImageMetadata(int imageIndex) throws IOException;

    public IIOMetadata getImageMetadata(int imageIndex, String formatName,
                                        Set<String> nodeNames) throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public BufferedImage read(int imageIndex) throws IOException {
        return read(imageIndex, null);
    }

    public abstract BufferedImage read(int imageIndex, ImageReadParam param) throws IOException;

    public IIOImage readAll(int imageIndex, ImageReadParam param) throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Iterator<IIOImage> readAll(Iterator<? extends ImageReadParam> params) throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public boolean canReadRaster() {
        return false; //def
    }

    public Raster readRaster(int imageIndex, ImageReadParam param) throws IOException {
        throw new UnsupportedOperationException("Unsupported");
    }

    public boolean isImageTiled(int imageIndex) throws IOException {
        return false; //def
    }

    public int getTileWidth(int imageIndex) throws IOException {
        return getWidth(imageIndex); //def
    }

    public int getTileHeight(int imageIndex) throws IOException {
        return getHeight(imageIndex); //def
    }

    public int getTileGridXOffset(int imageIndex) throws IOException {
        return 0; //def
    }

    public int getTileGridYOffset(int imageIndex) throws IOException {
        return 0; //def
    }

    public BufferedImage readTile(int imageIndex, int tileX, int tileY) throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Raster readTileRaster(int imageIndex, int tileX, int tileY) throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public RenderedImage readAsRenderedImage(int imageIndex, ImageReadParam param) throws IOException {
        return read(imageIndex, param);
    }

    public boolean readerSupportsThumbnails() {
        return false; //def
    }

    public boolean hasThumbnails(int imageIndex) throws IOException {
        return getNumThumbnails(imageIndex) > 0; //def
    }

    public int getNumThumbnails(int imageIndex) throws IOException {
        return 0; //def
    }

    public int getThumbnailWidth(int imageIndex, int thumbnailIndex) throws IOException {
        return readThumbnail(imageIndex, thumbnailIndex).getWidth();  //def
    }

    public int getThumbnailHeight(int imageIndex, int thumbnailIndex) throws IOException {
        return readThumbnail(imageIndex, thumbnailIndex).getHeight();  //def
    }

    public BufferedImage readThumbnail(int imageIndex, int thumbnailIndex) throws IOException {
        throw new UnsupportedOperationException("Unsupported"); //def
    }

    public void abort() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected boolean abortRequested() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void clearAbortRequest() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void addIIOReadWarningListener(IIOReadWarningListener listener) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void removeIIOReadWarningListener(IIOReadWarningListener listener) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void removeAllIIOReadWarningListeners() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void addIIOReadProgressListener(IIOReadProgressListener listener) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void removeIIOReadProgressListener(IIOReadProgressListener listener) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void removeAllIIOReadProgressListeners() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void addIIOReadUpdateListener(IIOReadUpdateListener listener) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void removeIIOReadUpdateListener(IIOReadUpdateListener listener) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void removeAllIIOReadUpdateListeners() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processSequenceStarted(int minIndex) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processSequenceComplete() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processImageStarted(int imageIndex) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processImageProgress(float percentageDone) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processImageComplete() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processThumbnailStarted(int imageIndex, int thumbnailIndex) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processThumbnailProgress(float percentageDone) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processThumbnailComplete() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processReadAborted() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processPassStarted(BufferedImage theImage,
                                  int pass,
                                  int minPass,
                                  int maxPass,
                                  int minX,
                                  int minY,
                                  int periodX,
                                  int periodY,
                                  int[] bands) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processImageUpdate(BufferedImage theImage,
                                  int minX,
                                  int minY,
                                  int width,
                                  int height,
                                  int periodX,
                                  int periodY,
                                  int[] bands) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processPassComplete(BufferedImage theImage) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processThumbnailPassStarted(BufferedImage theThumbnail,
                                           int pass,
                                           int minPass,
                                           int maxPass,
                                           int minX,
                                           int minY,
                                           int periodX,
                                           int periodY,
                                           int[] bands) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processThumbnailUpdate(BufferedImage theThumbnail,
                                      int minX,
                                      int minY,
                                      int width,
                                      int height,
                                      int periodX,
                                      int periodY,
                                       int[] bands) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processThumbnailPassComplete(BufferedImage theThumbnail) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processWarningOccurred(String warning) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected void processWarningOccurred(String baseName, String keyword) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void reset() {
        // def
        setInput(null, false);
        setLocale(null);
        removeAllIIOReadUpdateListeners();
        removeAllIIOReadWarningListeners();
        removeAllIIOReadProgressListeners();
        clearAbortRequest();
    }

    public void dispose() {
        // do nothing by def
    }

    protected static Rectangle getSourceRegion(ImageReadParam param, int srcWidth, int srcHeight) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected static void computeRegions(ImageReadParam param,
                                     int srcWidth,
                                     int srcHeight,
                                     BufferedImage image,
                                     Rectangle srcRegion,
                                     Rectangle destRegion) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected static void checkReadParamBandSettings(ImageReadParam param,
                                                 int numSrcBands,
                                                 int numDstBands) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected static BufferedImage getDestination(ImageReadParam param, Iterator<ImageTypeSpecifier> imageTypes,
                                              int width,
                                              int height)
                                       throws IIOException {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
