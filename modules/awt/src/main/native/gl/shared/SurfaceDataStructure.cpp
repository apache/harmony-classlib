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
 * @author Igor V. Stolyarov
 * @version $Revision$
 */
 
#include <stdlib.h>
#include <stdlib.h>
#include <memory.h>

#include "SurfaceDataStructure.h"
#include "org_apache_harmony_awt_gl_ImageSurface.h"
#include "LUTTables.h"

#ifdef _WIN32

#include "GDIBlitter.h"

#endif


int parseMask(unsigned int mask, int *shift, int *maxVal){
    int bits = 0;
    *shift = 0;
    *maxVal = 0;
    if (mask != 0) {
        // Deleting final zeros
        while ((mask & 1) == 0) {
            mask >>= 1;
            (*shift)++;
        }
        *maxVal = mask;
        // Counting component bits
        while ((mask & 1) == 1) {
            mask >>= 1;
            bits++;
        }
    }
    return bits;
}


int getShift(unsigned int mask){
    int shift = 0;
    if (mask != 0) {
        while ((mask & 1) == 0) {
            mask >>= 1;
            shift++;
        }
    }
    return shift;
}

inline void updateCache
(SURFACE_STRUCTURE *srcSurf, JNIEnv *env, jobject srcData, bool alphaPre){

       unsigned int srcstride, dststride, offset;
       unsigned int h = srcSurf->height;
       unsigned int w = srcSurf->width;

    void *bmpDataPtr = srcSurf->bmpData;
    void *srcDataPtr = env->GetPrimitiveArrayCritical((jarray)srcData, 0);

    switch(srcSurf->ss_type){

        case INT_RGB:
                       {
                               //if(!srcSurf->invalidated) return false;

                               srcstride = srcSurf->scanline_stride;
                               dststride = w;
                               /*
                               if(srcstride == dststride){
                                       memcpy(bmpDataPtr, srcDataPtr, (w << 2) * h);
                               }else{
                */
                                       unsigned int *src, *s, *dst, *d;

                                       offset = w - 1;
                                       src = (unsigned int *)srcDataPtr + offset;
                                       dst = (unsigned int *)bmpDataPtr + offset;

                                       for(int y = h; y > 0; y--, src += srcstride, dst += dststride){
                                               s = src;
                                               d = dst;

                                               for(int x = w; x > 0 ; x--){
                                                       *d-- = 0xff000000 | *s--;
                                               }
                                       }
                               //}
                       }
            break;

        case INT_ARGB:
                       {
                               //if(!srcSurf->invalidated && srcSurf->isAlphaPre && alphaPre) return false;
                               //if(!srcSurf->invalidated && !srcSurf->isAlphaPre && !alphaPre) return false;

                               unsigned char *src, *s, *dst, *d, sa;

                               offset = (w << 2) - 1;
                               src = (unsigned char *)srcDataPtr + offset;
                               dst = (unsigned char *)bmpDataPtr + offset;

                               srcstride = srcSurf->scanline_stride_byte;
                               dststride = w << 2;

                               if(alphaPre){
                                       for(int y = h; y > 0 ; y--, src += srcstride, dst += dststride){
                                               s = src;
                                               d = dst;

                                               for(int x = w; x > 0 ; x--){
                                                       sa = *s--;
                                                       *d-- = sa;
                                                       if(sa != 255){
                                                               *d-- = MUL(sa, *s--);
                                                               *d-- = MUL(sa, *s--);
                                                               *d-- = MUL(sa, *s--);
                                                               srcSurf->hasRealAlpha = true;
                                                       }else{
                                                               *d-- = *s--;
                                                               *d-- = *s--;
                                                               *d-- = *s--;
                                                       }
                                               }
                                       }
                                       srcSurf->isAlphaPre = true;
                               }else{
                                       for(int y = h; y > 0 ; y--, src += srcstride, dst += dststride){
                                               s = src;
                                               d = dst;

                                               for(int x = w; x > 0 ; x--){
                                                       sa = *s--;
                                                       if(sa == 0){
                                                               *d-- = 0;
                                                               *d-- = 0;
                                                               *d-- = 0;
                                                               *d-- = 0;
                                                               s -= 3;
                                                       }else{
                                                               *d-- = sa;
                                                               *d-- = MUL(sa, *s--);
                                                               *d-- = MUL(sa, *s--);
                                                               *d-- = MUL(sa, *s--);
                                                       }
                                               }
                                       }
                                       srcSurf->isAlphaPre = false;
                               }
                       }
            break;

        case INT_ARGB_PRE:
                       {
                               //if(!srcSurf->invalidated && srcSurf->isAlphaPre && alphaPre) return false;
                               //if(!srcSurf->invalidated && !srcSurf->isAlphaPre && !alphaPre) return false;

                               unsigned char *src, *s, *dst, *d, sa;

                               offset = (w << 2) - 1;
                               src = (unsigned char *)srcDataPtr + offset;
                               dst = (unsigned char *)bmpDataPtr + offset;

                               srcstride = srcSurf->scanline_stride_byte;
                               dststride = w << 2;

                               if(alphaPre){
                                       for(int y = h; y > 0; y--, src += srcstride, dst += dststride){
                                               s = src;
                                               d = dst;

                                               for(int x = w; x > 0 ; x--){
                                                       sa = *s--;
                                                       *d-- = sa;
                                                       *d-- = *s--;
                                                       *d-- = *s--;
                                                       *d-- = *s--;
                                                       if(sa != 255){
                                                               srcSurf->hasRealAlpha = true;
                                                       }
                                               }
                                       }
                                       srcSurf->isAlphaPre = true;
                               }else{
                                       for(int y = h; y > 0 ; y--, src += srcstride, dst += dststride){
                                               s = src;
                                               d = dst;

                                               for(int x = w; x > 0 ; x--){
                                                       sa = *s--;
                                                       *d-- = sa;
                                                       *d-- = DIV(sa, *s--);
                                                       *d-- = DIV(sa, *s--);
                                                       *d-- = DIV(sa, *s--);
                                               }
                                       }
                                       srcSurf->isAlphaPre = false;
                               }
                       }
            break;

        case INT_BGR:
                       {
                               //if(!srcSurf->invalidated) return false;

                               unsigned char *src, *s, *dst, *d;

                               offset = (w << 2) - 1;
                               src = (unsigned char *)srcDataPtr + offset;
                               dst = (unsigned char *)bmpDataPtr + offset;

                               srcstride = srcSurf->scanline_stride_byte;
                               dststride = w << 2;

                               for(int y = h; y > 0; y--, src += srcstride, dst += dststride){
                                       s = src;
                                       d = dst;

                                       for(int x = w; x > 0 ; x--){
                                               *d = 255;
                                               *s--;
                                               *(d - 3) = *s--;
                                               *(d - 2) = *s--;
                                               *(d - 1) = *s--;
                                               d -= 4;
                                       }
                               }
                       }
            break;

        case BYTE_BGR:
                       {
                               //if(!srcSurf->invalidated) return false;

                               unsigned char *src, *s, *dst, *d;

                               offset = (w << 2) - 1;
                               unsigned int srcOffset = w * 3 - 1;
                               src = (unsigned char *)srcDataPtr + srcOffset;
                               dst = (unsigned char *)bmpDataPtr + offset;

                               srcstride = srcSurf->scanline_stride_byte;
                               dststride = w << 2;

                               for(int y = srcSurf->height; y > 0; y--, src += srcstride, dst += dststride){
                                       s = src;
                                       d = dst;

                                       for(int x = w; x > 0 ; x--){
                                               *d-- = 255;
                                               *d-- = *s--;
                                               *d-- = *s--;
                                               *d-- = *s--;
                                       }
                               }
                       }
            break;

        case BYTE_ABGR:
                       {
                               //if(!srcSurf->invalidated && srcSurf->isAlphaPre && alphaPre) return false;
                               //if(!srcSurf->invalidated && !srcSurf->isAlphaPre && !alphaPre) return false;

                               unsigned char *src, *s, *dst, *d, a, r, g, b;

                               offset = (w << 2) - 1;
                               src = (unsigned char *)srcDataPtr + offset;
                               dst = (unsigned char *)bmpDataPtr + offset;

                               srcstride = srcSurf->scanline_stride_byte;
                               dststride = w << 2;

                               if(alphaPre){
                                       for(int y = h; y > 0 ; y--, src += srcstride, dst += dststride){
                                               s = src;
                                               d = dst;

                                               for(int x = w; x > 0 ; x--){
                                                       r = *s--;
                                                       g = *s--;
                                                       b = *s--;
                                                       a = *s--;
                                                       *d-- = a;
                                                       if(a != 255){
                                                               *d-- = MUL(a, r);
                                                               *d-- = MUL(a, g);
                                                               *d-- = MUL(a, b);
                                                               srcSurf->hasRealAlpha = true;
                                                       }else{
                                                               *d-- = r;
                                                               *d-- = g;
                                                               *d-- = b;
                                                       }
                                               }
                                       }
                                       srcSurf->isAlphaPre = true;
                               }else{
                                       for(int y = h; y > 0 ; y--, src += srcstride, dst += dststride){
                                               s = src;
                                               d = dst;

                                               for(int x = w; x > 0 ; x--){
                                                       r = *s--;
                                                       g = *s--;
                                                       b = *s--;
                                                       a = *s--;
                                                       if(a == 0){
                                                               *d-- = 0;
                                                               *d-- = 0;
                                                               *d-- = 0;
                                                               *d-- = 0;
                                                       }else{
                                                               *d-- = a;
                                                               *d-- = r;
                                                               *d-- = g;
                                                               *d-- = b;
                                                       }
                                               }
                                       }
                                       srcSurf->isAlphaPre = false;
                               }
                       }
            break;

        case BYTE_ABGR_PRE:
                       {
                               //if(!srcSurf->invalidated && srcSurf->isAlphaPre && alphaPre) return false;
                               //if(!srcSurf->invalidated && !srcSurf->isAlphaPre && !alphaPre) return false;

                               unsigned char *src, *s, *dst, *d, a, r, g, b;

                               offset = (w << 2) - 1;
                               src = (unsigned char *)srcDataPtr + offset;
                               dst = (unsigned char *)bmpDataPtr + offset;

                               srcstride = srcSurf->scanline_stride_byte;
                               dststride = w << 2;

                               if(alphaPre){
                                       for(int y = h; y > 0 ; y--, src += srcstride, dst += dststride){
                                               s = src;
                                               d = dst;

                                               for(int x = w; x > 0 ; x--){
                                                       r = *s--;
                                                       g = *s--;
                                                       b = *s--;
                                                       a = *s--;
                                                       if(a != 255){
                                                               srcSurf->hasRealAlpha = true;
                                                       }
                                                       *d-- = a;
                                                       *d-- = r;
                                                       *d-- = g;
                                                       *d-- = b;
                                               }
                                       }
                                       srcSurf->isAlphaPre = true;
                               }else{
                                       for(int y = h; y > 0 ; y--, src += srcstride, dst += dststride){
                                               s = src;
                                               d = dst;

                                               for(int x = w; x > 0 ; x--){
                                                       r = *s--;
                                                       g = *s--;
                                                       b = *s--;
                                                       a = *s--;
                                                       *d-- = a;
                                                       if(a != 255){
                                                               *d-- = DIV(a, r);
                                                               *d-- = DIV(a, g);
                                                               *d-- = DIV(a, b);
                                                       }else{
                                                               *d-- = r;
                                                               *d-- = g;
                                                               *d-- = b;
                                                       }
                                               }
                                       }
                                       srcSurf->isAlphaPre = false;
                               }
                       }
            break;

        case USHORT_555:
        case USHORT_565:
                       {
                               //if(!srcSurf->invalidated) return false;

                               unsigned char *dst, *d;
                               unsigned short *src, *s, pixel;

                               offset = (w << 2) - 1;
                               unsigned int srcOffset = w - 1;
                               src = (unsigned short *)srcDataPtr + srcOffset;
                               dst = (unsigned char *)bmpDataPtr + offset;

                               srcstride = srcSurf->scanline_stride;
                               dststride = w << 2;

                               unsigned int mr = srcSurf->max_red;
                               unsigned int mg = srcSurf->max_green;
                               unsigned int mb = srcSurf->max_red;
                               unsigned int rm = srcSurf->red_mask;
                               unsigned int gm = srcSurf->green_mask;
                               unsigned int bm = srcSurf->blue_mask;
                               unsigned int rs = srcSurf->red_sht;
                               unsigned int gs = srcSurf->green_sht;
                               unsigned int bs = srcSurf->blue_sht;

                               for(int y = h; y > 0; y--, src += srcstride, dst += dststride){
                                       d = dst;
                                       s = src;
                                       for(int x = w; x > 0; x--){
                                               pixel = *s--;
                                               *d-- = 255;
                                               *d-- = DIV(mb, ((pixel & rm) >> rs));
                                               *d-- = DIV(mg, ((pixel & gm) >> gs));
                                               *d-- = DIV(mr, ((pixel & bm) >> bs));
                                       }
                               }
                       }
            break;

        case USHORT_GRAY:
                       {
                               //if(!srcSurf->invalidated) return false;

                               unsigned char *dst, *d, pixel;
                               unsigned short *src, *s;
                               src = (unsigned short *)srcDataPtr;
                               dst = (unsigned char *)bmpDataPtr;

                               srcstride = srcSurf->scanline_stride;
                               dststride = w << 2;

                               for(int y = h; y > 0; y--, src += srcstride, dst += dststride){
                                       s = src;
                                       d = dst;
                                       for(int x = w; x > 0; x--){
                                               pixel = (unsigned char)(*s++ / 257);
                                               *d++ = pixel;
                                               *d++ = pixel;
                                               *d++ = pixel;
                                               *d++ = 255;
                                       }
                               }
                       }
            break;

        case BYTE_BINARY:
                       {
                               //if(!srcSurf->invalidated) return false;

                               unsigned char *src, *s;
                               unsigned int *dst, *d, pixel, bitnum, elem, shift, bitMask;
                               src = (unsigned char *)srcDataPtr;
                               dst = (unsigned int *)bmpDataPtr;

                               srcstride = srcSurf->scanline_stride;
                               dststride = w;
                               unsigned int pixelBits = srcSurf->pixel_stride;
                               int *cm = srcSurf->colormap;

                               for(int y = h; y > 0; y--, src += srcstride, dst += dststride){
                                       d = dst;

                                       for(unsigned int x = 0; x < w; x++){
                                               bitnum = x * pixelBits;
                                               s = src + bitnum / 8;
                                               elem = *s;
                                               shift = 8 - (bitnum & 7) - pixelBits;
                                               bitMask = (1 << pixelBits) - 1;
                                               pixel = (elem >> shift) & bitMask;
                                               *d++ = 0xff000000 | *(cm + pixel);
                                       }
                               }
                       }
            break;

        case BYTE_INDEXED:
                       {
                               int transparency = srcSurf->transparency;

                               //if(!srcSurf->invalidated && transparency != GL_TRANSLUCENT) return false;
                               //if(!srcSurf->invalidated && transparency == GL_TRANSLUCENT &&
                               //      srcSurf->isAlphaPre && alphaPre) return false;
                               //if(!srcSurf->invalidated && transparency == GL_TRANSLUCENT &&
                               //      !srcSurf->isAlphaPre && !alphaPre) return false;

                               unsigned char *src, *s;
                               unsigned int *dst, *d, pixel, r, g, b, a;

                               unsigned int offset = w - 1;
                               src = (unsigned char *)srcDataPtr + offset;
                               dst = (unsigned int *)bmpDataPtr + offset;

                               srcstride = srcSurf->scanline_stride;
                               dststride = w;
                               int *cm = srcSurf->colormap;
                               int tp = srcSurf->transparent_pixel;
                               if(transparency == GL_OPAQUE){
                                       for(int y = h; y > 0; y--, src += srcstride, dst += dststride){
                                               s = src;
                                               d = dst;

                                               for(int x = w; x > 0; x--){
                                                       *d-- = 0xff000000 | *(cm + *s--);
                                               }
                                       }
                               }else if(transparency == GL_BITMASK){
                                       for(int y = h; y > 0; y--, src += srcstride, dst += dststride){
                                               s = src;
                                               d = dst;

                                               for(int x = w; x > 0; x--){
                                                       pixel = *s--;
                                                       if(pixel != tp){
                                                               *d-- = 0xff000000 | *(cm + pixel);
                                                       }else{
                                                               srcSurf->hasRealAlpha = true;
                                                               *d-- = 0;
                                                       }
                                               }
                                       }
                               }else{
                                       for(int y = h; y > 0; y--, src += srcstride, dst += dststride){
                                               s = src;
                                               d = dst;

                                               for(int x = w; x > 0; x--){
                                                       pixel = *(cm + *s--);
                                                       a = (pixel >> 24) & 0xff;
                                                       if(alphaPre){
                                                               if(a == 255){
                                                                       *d-- = pixel;
                                                               }else{
                                                                       r = (pixel >> 16) & 0xff;
                                                                       g = (pixel >> 8) & 0xff;
                                                                       b = pixel & 0xff;
                                                                       r = MUL(a, r);
                                                                       g = MUL(a, g);
                                                                       b = MUL(a, b);
                                                                       *d-- = (a << 24) | (r << 16) | (g << 8) | b;
                                                                       srcSurf->hasRealAlpha = true;
                                                               }
                                                               srcSurf->isAlphaPre = true;
                                                       }else{
                                                               if(a == 0) *d-- = 0;
                                                               else *d-- = pixel;
                                                               srcSurf->isAlphaPre = false;
                                                       }
                                               }
                                       }
                               }
                       }
            break;

        case BYTE_GRAY:
                       {
                               //if(!srcSurf->invalidated) return false;
                               unsigned char *src, *s, *dst, *d, pixel;
                               src = (unsigned char *)srcDataPtr;
                               dst = (unsigned char *)bmpDataPtr;

                               srcstride = srcSurf->scanline_stride;
                               dststride = w << 2;

                               for(int y = h; y > 0; y--, src += srcstride, dst += dststride){
                                       s = src;
                                       d = dst;

                                       for(int x = srcSurf->width; x > 0; x--){
                                               pixel = *s++;
                                               *d++ = pixel;
                                               *d++ = pixel;
                                               *d++ = pixel;
                                               *d++ = 255;
                                       }
                               }
                       }
            break;
    }
    env->ReleasePrimitiveArrayCritical((jarray)srcData, srcDataPtr, 0);
}

/*
 * Class:     org_apache_harmony_awt_gl_ImageSurface
 * Method:    createSurfStruct
 * Signature: (IIIIIIIIII[I[II[IIZ[I[IIZZI)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_awt_gl_ImageSurface_createSurfStruct
  (JNIEnv * env, jobject obj, jint surfType, jint width, jint height, jint cmType, 
  jint csType, jint smType, jint dataType, jint numComponents, jint pixelStride, 
  jint scanlineStride, jintArray bits, jintArray masks, jint colorMapSize, 
  jintArray colorMap, jint transpPixel, jboolean isGrayPalette, jintArray bankIndeces, 
  jintArray bandOffsets, jint offset, jboolean hasAlpha, jboolean isAlphaPre, 
  jint transparency){

      SURFACE_STRUCTURE *surf = (SURFACE_STRUCTURE *)calloc(sizeof(SURFACE_STRUCTURE), 1);
      if(surf != NULL){
          surf->ss_type = surfType;
          surf->width = width;
          surf->height = height;
          surf->cm_type = cmType;
          surf->cs_type = csType;
          surf->data_type = dataType;
          surf->num_components = numComponents;
          surf->pixel_stride = pixelStride;
          surf->scanline_stride = scanlineStride;
                 surf->offset = offset;
                 surf->has_alpha = hasAlpha;
                 surf->isAlphaPre = isAlphaPre != 0;
                 surf->transparency = transparency;
                 if(dataType == TYPE_BYTE){
                         surf->scanline_stride_byte = scanlineStride;
                 }else if(dataType == TYPE_USHORT){
                         surf->scanline_stride_byte = scanlineStride << 1;
                 }else if(dataType == TYPE_INT){
                         surf->scanline_stride_byte = scanlineStride << 2;
                 }

                 void *p;
                 int *s, *d;
                 switch(cmType){
                         case DIRECT_CM:
                                 surf->bits = (int *)malloc(surf->num_components * sizeof(int));
                                 p = env->GetPrimitiveArrayCritical(bits, 0);
                                 d = surf->bits;
                                 s = (int *)p;
                                 for(int i = 0; i < numComponents; i++){
                                         *d++ = *s++;
                                 }
                                 env->ReleasePrimitiveArrayCritical(bits, p, 0);
                                 p = env->GetPrimitiveArrayCritical(masks, 0);
                                 s = (int *)p;
                                 
                                 surf->red_mask = *s++;
                                 surf->green_mask = *s++;
                                 surf->blue_mask = *s++;
                                 if(hasAlpha){
                                         surf->alpha_mask = *s;
                                 }
                                 env->ReleasePrimitiveArrayCritical(masks, p, 0);

                                 surf->red_sht = getShift(surf->red_mask);
                                 surf->max_red = (1 << surf->bits[0]) - 1;
                                 surf->green_sht = getShift(surf->green_mask);
                                 surf->max_green = (1 << surf->bits[1]) - 1;
                                 surf->blue_sht = getShift(surf->blue_mask);
                                 surf->max_blue = (1 << surf->bits[2]) - 1;
                                 if(hasAlpha){
                                         surf->alpha_sht = getShift(surf->alpha_mask);
                                         surf->max_alpha = ( 1 << surf->bits[3]) - 1;
                                 }
                                 break;

                         case INDEX_CM:
                                 surf->colormap_size = colorMapSize;
                                 surf->transparent_pixel = transpPixel;
                                 surf->isGrayPallete = isGrayPalette;
                                 surf->colormap = (int *)malloc(colorMapSize * sizeof(int));
                                 p = env->GetPrimitiveArrayCritical(colorMap, 0);
                                 memcpy(surf->colormap, p, colorMapSize * sizeof(int));
                                 env->ReleasePrimitiveArrayCritical(colorMap, p, 0);
                                 break;

                         case COMPONENT_CM:
                                 surf->bank_indexes = (int *)malloc(numComponents * sizeof(int));
                                 surf->band_offsets = (int *)malloc(numComponents * sizeof(int));

                                 p = env->GetPrimitiveArrayCritical(bankIndeces, 0);
                                 memcpy((void *)surf->bank_indexes, p, numComponents * sizeof(int));
                                 env->ReleasePrimitiveArrayCritical(bankIndeces, p, 0);

                                 p = env->GetPrimitiveArrayCritical(bandOffsets, 0);
                                 memcpy((void *)surf->band_offsets, p, numComponents * sizeof(int));
                                 env->ReleasePrimitiveArrayCritical(bandOffsets, p, 0);
                                 break;
                 }
                 surf->bmp_byte_stride = surf->width << 2;
                 surf->bmpData = malloc(surf->bmp_byte_stride  * surf->height);
          surf->invalidated = true;

#ifdef _WIN32
                 surf->bmpInfo.bmiHeader.biSize = sizeof(BITMAPINFOHEADER);
          surf->bmpInfo.bmiHeader.biWidth = surf->width;
          surf->bmpInfo.bmiHeader.biHeight = -surf->height;
          surf->bmpInfo.bmiHeader.biPlanes = 1;
          surf->bmpInfo.bmiHeader.biBitCount = 32;
          surf->bmpInfo.bmiHeader.biSizeImage = surf->bmp_byte_stride * surf->height;
          surf->bmpInfo.bmiHeader.biCompression = BI_BITFIELDS;
          DWORD *colors = (DWORD *)surf->bmpInfo.bmiColors;
          colors[0] = 0xff0000;
          colors[1] = 0xff00;
          colors[2] = 0xff;

          HDC dc = GetDC(NULL);
          surf->srcDC = CreateCompatibleDC(dc);
                 surf->bitmap = CreateCompatibleBitmap(dc, surf->width, surf->height);
                 ReleaseDC(NULL, dc);
          if(surf->srcDC != NULL && surf->bitmap != NULL){
              SelectObject(surf->srcDC, surf->bitmap);
          }
          surf->isAlphaPre = true;
                 //surf->bmpInfo.bmiHeader.biSize = sizeof(BITMAPINFOHEADER);
    //      surf->bmpInfo.bmiHeader.biWidth = surf->width;
    //      surf->bmpInfo.bmiHeader.biHeight = -surf->height;
    //      surf->bmpInfo.bmiHeader.biPlanes = 1;
    //      surf->bmpInfo.bmiHeader.biBitCount = 16;
                 //surf->bmp_byte_stride = surf->width << 1;
    //      surf->bmpInfo.bmiHeader.biCompression = BI_BITFIELDS;
    //      DWORD *colors = (DWORD *)surf->bmpInfo.bmiColors;
                 //colors[0] = redMask;
                 //colors[1] = greenMask;
    //      colors[2] = blueMask;
    //      surf->bitmap = CreateDIBSection(NULL, (BITMAPINFO *)&surf->bmpInfo, DIB_RGB_COLORS, &surf->bmpData, NULL, 0);

    //      surf->invalidated = true;
    //      surf->isAlphaPre = true;
#endif
      }
      return (jlong)surf;
  
  }

//JNIEXPORT jlong JNICALL Java_org_apache_harmony_awt_gl_ImageSurface_createStructDCM
//  (JNIEnv * env, jobject obj, jint surfDataType, jint dataType, jint csType, 
//  jint redMask, jint greenMask, jint blueMask, jint alphaMask, jint pixelStride, 
//  jint scanlineStride, jint width, jint height, jint transparancy, jboolean isAlphaPre){
//
//      SURFACE_STRUCTURE *surf = (SURFACE_STRUCTURE *)calloc(sizeof(SURFACE_STRUCTURE), 1);
//      if(surf != NULL){
//          surf->ss_type = surfDataType;
//          surf->cm_type = DIRECT_CM;
//          surf->cs_type = csType;
//          surf->data_type = dataType;
//          surf->red_mask = redMask;
//          surf->green_mask = greenMask;
//          surf->blue_mask = blueMask;
//          surf->alpha_mask = alphaMask;
//          surf->scanline_stride = scanlineStride;
//          switch(surf->ss_type){
//              case INT_RGB:
//              case INT_ARGB:
//              case INT_ARGB_PRE:
//              case INT_BGR:
//                  surf->scanline_stride_byte = scanlineStride << 2;
//                  break;
//              case USHORT_555:
//              case USHORT_565:
//                  surf->scanline_stride_byte = scanlineStride << 1;
//                  break;
//              //default:
//                  // TODO
//          }
//          surf->pixel_stride = pixelStride;
//          surf->width = width;
//          surf->height = height;
//          surf->transparency = transparancy;
//          surf->alpha_pre = isAlphaPre;
//          surf->num_components = (alphaMask == 0 ? 3 : 4);
//
//          surf->bits = (int *)malloc(surf->num_components * sizeof(int));
//          surf->bits[0] = parseMask(redMask, &surf->red_sht, &surf->max_red);
//          surf->bits[1] = parseMask(greenMask, &surf->green_sht, &surf->max_green);
//          surf->bits[2] = parseMask(blueMask, &surf->blue_sht, &surf->max_blue);
//          if(alphaMask != 0){
//            surf->bits[3] = parseMask(alphaMask, &(surf->alpha_sht), &(surf->max_alpha));
//            surf->has_alpha = 1;
//          }
//
//#ifdef _WIN32
//          GLBITMAPINFO glbmpInfo;
//          memset(&glbmpInfo, 0, sizeof(GLBITMAPINFO));
//          UINT stride;
//          glbmpInfo.bmiHeader.biSize = sizeof(BITMAPINFOHEADER);
//          glbmpInfo.bmiHeader.biWidth = surf->width;
//          glbmpInfo.bmiHeader.biHeight = -surf->height;
//          glbmpInfo.bmiHeader.biPlanes = 1;
//          glbmpInfo.bmiHeader.biBitCount = 32;
//          stride = surf->width << 2;
//          glbmpInfo.bmiHeader.biSizeImage = stride * surf->height;
//          glbmpInfo.bmiHeader.biCompression = BI_BITFIELDS;
//          DWORD *colors = (DWORD *)glbmpInfo.bmiColors;
//          colors[0] = 0xff0000;
//          colors[1] = 0xff00;
//          colors[2] = 0xff;
//          surf->srcDC = CreateCompatibleDC(NULL);
//          surf->bitmap = CreateDIBSection(NULL, (BITMAPINFO *)&glbmpInfo, DIB_RGB_COLORS, &surf->bmpData, NULL, 0);
//          if(surf->srcDC != NULL && surf->bitmap != NULL){
//              SelectObject(surf->srcDC, surf->bitmap);
//          }
//          surf->invalidated = true;
//          surf->isAlphaPre = true;
//#endif
//      }
//      return (jlong)surf;
//  }
//
//JNIEXPORT jlong JNICALL Java_org_apache_harmony_awt_gl_ImageSurface_createStructICM
//  (JNIEnv *env, jobject obj, jint surfDataType, jint dataType, jint pixelStride, 
//  jint scanlineStride, jint width, jint height, jint mapSize, jintArray colorMap, 
//  jboolean isGrayPallete, jint transparency, jint trans, jint smType){
//
//      SURFACE_STRUCTURE *surf = (SURFACE_STRUCTURE *)calloc(sizeof(SURFACE_STRUCTURE), 1);
//      if(surf != NULL){
//          surf->ss_type = surfDataType;
//          surf->cm_type = INDEX_CM;
//          surf->cs_type = sRGB_CS;
//          surf->pixel_stride = pixelStride;
//          surf->scanline_stride = scanlineStride;
//          surf->scanline_stride_byte = scanlineStride;
//          surf->width = width;
//          surf->height = height;
//          surf->colormap_size = mapSize;
//          surf->transparency = transparency;
//          surf->transparent_pixel = trans;
//          surf->sm_type = smType;
//          surf->has_alpha = (transparency == GL_OPAQUE ? 0 : 1);
//          surf->isGrayPallete = isGrayPallete;
//          surf->colormap = (int *)malloc(mapSize * sizeof(int));
//          void *p = env->GetPrimitiveArrayCritical(colorMap, 0);
//          memcpy((void *)surf->colormap, p, mapSize << 2);
//          env->ReleasePrimitiveArrayCritical(colorMap, p, 0);
//#ifdef _WIN32
//          GLBITMAPINFO glbmpInfo;
//          memset(&glbmpInfo, 0, sizeof(GLBITMAPINFO));
//          UINT stride;
//          glbmpInfo.bmiHeader.biSize = sizeof(BITMAPINFOHEADER);
//          glbmpInfo.bmiHeader.biWidth = surf->width;
//          glbmpInfo.bmiHeader.biHeight = -surf->height;
//          glbmpInfo.bmiHeader.biPlanes = 1;
//          glbmpInfo.bmiHeader.biBitCount = 32;
//          stride = surf->width << 2;
//          glbmpInfo.bmiHeader.biSizeImage = stride * surf->height;
//          glbmpInfo.bmiHeader.biCompression = BI_BITFIELDS;
//          DWORD *colors = (DWORD *)glbmpInfo.bmiColors;
//          colors[0] = 0xff0000;
//          colors[1] = 0xff00;
//          colors[2] = 0xff;
//
//          surf->srcDC = CreateCompatibleDC(NULL);
//          surf->bitmap = CreateDIBSection(NULL, (BITMAPINFO *)&glbmpInfo, DIB_RGB_COLORS, &surf->bmpData, NULL, 0);
//          if(surf->srcDC != NULL && surf->bitmap != NULL){
//              SelectObject(surf->srcDC, surf->bitmap);
//          }
//          surf->invalidated = true;
//          surf->isAlphaPre = true;
//#endif
//      }
//
//      return (jlong)surf;
//  }
//
//JNIEXPORT jlong JNICALL Java_org_apache_harmony_awt_gl_ImageSurface_createStructCCM
//  (JNIEnv *env, jobject obj, jint surfDataType, jint dataType, jint csType, 
//  jint numComponents, jint pixelStride, jint scanlineStride, jint width, jint height, jintArray bits,
//  jintArray bankIndeces, jintArray bandOffsets, jint transparency, jboolean isAlphaPre){
//
//      SURFACE_STRUCTURE *surf = (SURFACE_STRUCTURE *)calloc(sizeof(SURFACE_STRUCTURE), 1);
//      if(surf != NULL){
//          surf->ss_type = surfDataType;
//          surf->cm_type = COMPONENT_CM;
//          surf->cs_type = csType;
//          surf->num_components = numComponents;
//          surf->pixel_stride = pixelStride;
//          surf->scanline_stride = scanlineStride;
//          switch(surf->ss_type){
//              case BYTE_BGR:
//              case BYTE_ABGR:
//              case BYTE_ABGR_PRE:
//              case BYTE_GRAY:
//                  surf->scanline_stride_byte = scanlineStride;
//                  break;
//              case USHORT_GRAY:
//                  surf->scanline_stride_byte = scanlineStride << 1;
//                  break;
//              //default:
//                  // TODO
//          }
//          surf->width = width;
//          surf->height = height;
//          surf->transparency = transparency;
//          surf->has_alpha = (transparency == GL_OPAQUE ? 0 : 1);
//          surf->alpha_pre = isAlphaPre;
//
//          surf->bits = (int *)malloc(numComponents * sizeof(int));
//          surf->bank_indexes = (int *)malloc(numComponents * sizeof(int));
//          surf->band_offsets = (int *)malloc(numComponents * sizeof(int));
//
//          void *p = env->GetPrimitiveArrayCritical(bits, 0);
//          memcpy((void *)surf->bits, p, numComponents << 2);
//          env->ReleasePrimitiveArrayCritical(bits, p, 0);
//
//          p = env->GetPrimitiveArrayCritical(bankIndeces, 0);
//          memcpy((void *)surf->bank_indexes, p, numComponents << 2);
//          env->ReleasePrimitiveArrayCritical(bankIndeces, p, 0);
//
//          p = env->GetPrimitiveArrayCritical(bandOffsets, 0);
//          memcpy((void *)surf->band_offsets, p, numComponents << 2);
//          env->ReleasePrimitiveArrayCritical(bandOffsets, p, 0);
//      }
//#ifdef _WIN32
//          GLBITMAPINFO glbmpInfo;
//          memset(&glbmpInfo, 0, sizeof(GLBITMAPINFO));
//          UINT stride;
//          glbmpInfo.bmiHeader.biSize = sizeof(BITMAPINFOHEADER);
//          glbmpInfo.bmiHeader.biWidth = surf->width;
//          glbmpInfo.bmiHeader.biHeight = -surf->height;
//          glbmpInfo.bmiHeader.biPlanes = 1;
//          glbmpInfo.bmiHeader.biBitCount = 32;
//          stride = surf->width << 2;
//          glbmpInfo.bmiHeader.biSizeImage = stride * surf->height;
//          glbmpInfo.bmiHeader.biCompression = BI_BITFIELDS;
//          DWORD *colors = (DWORD *)glbmpInfo.bmiColors;
//          colors[0] = 0xff0000;
//          colors[1] = 0xff00;
//          colors[2] = 0xff;
//          surf->srcDC = CreateCompatibleDC(NULL);
//          surf->bitmap = CreateDIBSection(NULL, (BITMAPINFO *)&glbmpInfo, DIB_RGB_COLORS, &surf->bmpData, NULL, 0);
//          if(surf->srcDC != NULL && surf->bitmap != NULL){
//              SelectObject(surf->srcDC, surf->bitmap);
//          }
//          surf->invalidated = true;
//          surf->isAlphaPre = true;
//#endif
//
//      return (jlong)surf;
//  }

JNIEXPORT jlong JNICALL Java_org_apache_harmony_awt_gl_ImageSurface_updateCache
(JNIEnv *env, jobject obj, jlong ptr, jobject data, jboolean alphaPre){

    SURFACE_STRUCTURE *surf = (SURFACE_STRUCTURE *)ptr;
       jlong cachePtr = 0;
       if(surf != NULL){
               updateCache(surf, env, data, alphaPre != 0);
               cachePtr = (jlong)surf->bmpData;
       }
       return cachePtr;
}

JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_ImageSurface_dispose
  (JNIEnv *env, jobject obj, jlong ptr){

    SURFACE_STRUCTURE *surf = (SURFACE_STRUCTURE *)ptr;

    if(surf != NULL){
        if(surf->bits) free(surf->bits);
        if(surf->colormap) free(surf->colormap);
        if(surf->bank_indexes) free(surf->bank_indexes);
        if(surf->band_offsets) free(surf->band_offsets);
               if(surf->bmpData) free(surf->bmpData);
#ifdef _WIN32
        if(surf->bitmap) DeleteObject(surf->bitmap);
        if(surf->srcDC) DeleteDC(surf->srcDC);
#endif
        free(surf);
    }
  }

JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_ImageSurface_setImageSize
  (JNIEnv *env, jobject obj, jlong ptr, jint width, jint height) {
    SURFACE_STRUCTURE *surf = (SURFACE_STRUCTURE *)ptr;
    surf->scanline_stride = surf->scanline_stride / surf->width * width;
    surf->scanline_stride_byte = surf->scanline_stride_byte / surf->width * width;
    surf->width = width;
    surf->height = height;
  }
