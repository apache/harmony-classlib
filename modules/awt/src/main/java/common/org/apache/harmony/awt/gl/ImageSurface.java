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
 * Created on 10.11.2005
 *
 */
package org.apache.harmony.awt.gl;

import java.awt.color.ColorSpace;
import java.awt.image.BandedSampleModel;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.PixelInterleavedSampleModel;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;

import org.apache.harmony.awt.gl.color.LUTColorConverter;


/**
 * This class represent Surface for differnt types of Images (BufferedImage, 
 * OffscreenImage and so on) 
 */
public class ImageSurface extends Surface {

    boolean nativeDrawable = true;
    int surfaceType;
    int csType;
    ColorModel cm;
    WritableRaster raster;
    Object data;

    public ImageSurface(ColorModel cm, WritableRaster raster){
        this(cm, raster, Surface.getType(cm, raster));
    }

    public ImageSurface(ColorModel cm, WritableRaster raster, int type){
        if (!cm.isCompatibleRaster(raster))
            throw new IllegalArgumentException("The raster is" +
                    " incompatible with this ColorModel");
        this.cm = cm;
        this.raster = raster;
        surfaceType = type;

        ColorSpace cs = cm.getColorSpace();
        transparency = cm.getTransparency();
        width = raster.getWidth();
        height = raster.getHeight();

        // For the moment we can blit natively only images which have 
        // sRGB, Linear_RGB, Linear_Gray Color Space and type different
        // from BufferedImage.TYPE_CUSTOM
        if(cs == LUTColorConverter.sRGB_CS){
            csType = sRGB_CS;
        }else if(cs == LUTColorConverter.LINEAR_RGB_CS){
            csType = Linear_RGB_CS;
        }else if(cs == LUTColorConverter.LINEAR_GRAY_CS){
            csType = Linear_Gray_CS;
        }else{
            csType = Custom_CS;
            nativeDrawable = false;
        }

        if(type == BufferedImage.TYPE_CUSTOM){
            nativeDrawable = false;
        }
        if(nativeDrawable) createSufaceStructure();
    }

    public ColorModel getColorModel() {
        return cm;
    }

    public WritableRaster getRaster() {
        return raster;
    }

    public long getSurfaceDataPtr() {
        return surfaceDataPtr;
    }

    public Object getData(){
        return data;
    }

    public boolean isNativeDrawable(){
        return nativeDrawable;
    }

    public int getSurfaceType() {
        return surfaceType;
    }

    /**
     * Creates native Surface structure which used for native blitting
     */
    private void createSufaceStructure(){
        SampleModel sm = raster.getSampleModel();
        int dataType = sm.getDataType();
        data = AwtImageBackdoorAccessor.getInstance().
        getData(raster.getDataBuffer());

        if(cm instanceof DirectColorModel){
            DirectColorModel dcm = (DirectColorModel) cm;
            int redMask = dcm.getRedMask();
            int greenMask = dcm.getGreenMask();
            int blueMask = dcm.getBlueMask();
            int alphaMask = dcm.getAlphaMask();
            SinglePixelPackedSampleModel sppsm =
                (SinglePixelPackedSampleModel) sm;
            int scanlineStride = sppsm.getScanlineStride();
            boolean isAlphaPre = dcm.isAlphaPremultiplied();

            surfaceDataPtr = createStructDCM(surfaceType, dataType,
                    csType, redMask, greenMask, blueMask, alphaMask,
                    cm.getPixelSize(), scanlineStride, raster.getWidth(),
                    raster.getHeight(), dcm.getTransparency(), isAlphaPre);

        }else if(cm instanceof IndexColorModel){
            IndexColorModel icm = (IndexColorModel) cm;
            int mapSize = icm.getMapSize();
            int colorMap[] = new int[mapSize];
            icm.getRGBs(colorMap);
            int pixelStride = icm.getPixelSize();
            int trans = icm.getTransparentPixel();
            int transparency = icm.getTransparency();
            boolean isGrayPallete = Surface.isGrayPallete(icm);

            int smType;
            int scanlineStride;
            if(sm instanceof MultiPixelPackedSampleModel){
                smType = MPPSM;
                MultiPixelPackedSampleModel mppsm =
                    (MultiPixelPackedSampleModel) sm;
                scanlineStride = mppsm.getScanlineStride();
            }else if(sm instanceof ComponentSampleModel){
                smType = CSM;
                ComponentSampleModel csm =
                    (ComponentSampleModel) sm;
                scanlineStride = csm.getScanlineStride();
            }else{
                throw new IllegalArgumentException("The raster is" +
                " incompatible with this ColorModel");
            }
            surfaceDataPtr = createStructICM(surfaceType, dataType,
                    pixelStride, scanlineStride, width, height,
                    mapSize, colorMap, isGrayPallete, transparency,
                    trans, smType);

        }else if(cm instanceof ComponentColorModel){
            ComponentColorModel ccm = (ComponentColorModel) cm;
            int transparency = ccm.getTransparency();
            boolean isAlphaPre = ccm.isAlphaPremultiplied();
            int numComponents = ccm.getNumComponents();
            int bits[] = ccm.getComponentSize();

            int smType;
            int scanlineStride;
            int bankIndeces[];
            int bandOffsets[];

            if(sm instanceof ComponentSampleModel){
                ComponentSampleModel csm = (ComponentSampleModel) sm;
                scanlineStride = csm.getScanlineStride();
                bankIndeces = csm.getBankIndices();
                bandOffsets = csm.getBandOffsets();
                if(sm instanceof PixelInterleavedSampleModel){
                    smType = PISM;
                }else if(sm instanceof BandedSampleModel){
                    smType = BSM;
                }else{
                    smType = CSM;
                }
            }else{
                throw new IllegalArgumentException("The raster is" +
                " incompatible with this ColorModel");
            }
            surfaceDataPtr = createStructCCM(surfaceType, dataType,
                    csType, numComponents, cm.getPixelSize(), scanlineStride,
                    width, height, bits, bankIndeces, bandOffsets,
                    transparency, isAlphaPre);
        }else{
            surfaceDataPtr = 0L;
        }
    }

    public void dispose() {
        dispose(surfaceDataPtr);
        surfaceDataPtr = 0L;
    }

    private native long createStructDCM(int surfaceType, int dataType,
            int csType, int redMask, int greenMask, int blueMask,
            int alphaMask, int pixelStride, int scanlineStride, int width, int height,
            int transparancy, boolean isAlphaPre);

    private native long createStructICM(int surfaceType, int dataType,
            int pixelStride, int scanlineStride, int width, int height,
            int mapSize, int colorMap[], boolean isGrayPallete, int transparency,
            int trans, int smType);

    private native long createStructCCM(int surfaceType, int dataType,
            int csType, int numComponents, int pixelStride, int scanlineStride,
            int width, int height, int bits[], int bankIndeces[], int bandOffsets[],
            int transparency, boolean isAlphaPre);

    private native void dispose(long structPtr);

    private native void setImageSize(long structPtr, int width, int height);

    /**
     * Supposes that new raster is compatible with an old one
     * @param r
     */
    public void setRaster(WritableRaster r) {
        raster = r;
        data = AwtImageBackdoorAccessor.getInstance().getData(r.getDataBuffer());
        setImageSize(surfaceDataPtr, r.getWidth(), r.getHeight());
        this.width = r.getWidth();
        this.height = r.getHeight();
    }

    public long lock() {
        // TODO
        return 0;
    }

    public void unlock() {
        //TODO
    }

    public Surface getImageSurface() {
        return this;
    }
}
