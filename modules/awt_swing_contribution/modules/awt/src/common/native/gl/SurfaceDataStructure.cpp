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
 */
 
#include <stdlib.h>
#include <malloc.h>
#include <memory.h>

#include "SurfaceDataStructure.h"
#include "org_apache_harmony_awt_gl_ImageSurface.h"

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


JNIEXPORT jlong JNICALL Java_org_apache_harmony_awt_gl_ImageSurface_createStructDCM
  (JNIEnv * env, jobject obj, jint surfDataType, jint dataType, jint csType, 
  jint redMask, jint greenMask, jint blueMask, jint alphaMask, jint pixelStride, 
  jint scanlineStride, jint width, jint height, jint transparancy, jboolean isAlphaPre){

      SURFACE_STRUCTURE *surf = (SURFACE_STRUCTURE *)calloc(sizeof(SURFACE_STRUCTURE), 1);
      if(surf != NULL){
          surf->ss_type = surfDataType;
          surf->cm_type = DIRECT_CM;
          surf->cs_type = csType;
          surf->data_type = dataType;
          surf->red_mask = redMask;
          surf->green_mask = greenMask;
          surf->blue_mask = blueMask;
          surf->alpha_mask = alphaMask;
          surf->scanline_stride = scanlineStride;
          switch(surf->ss_type){
              case INT_RGB:
              case INT_ARGB:
              case INT_ARGB_PRE:
              case INT_BGR:
                  surf->scanline_stride_byte = scanlineStride << 2;
                  break;
              case USHORT_555:
              case USHORT_565:
                  surf->scanline_stride_byte = scanlineStride << 1;
                  break;
              //default:
                  // TODO
          }
          surf->pixel_stride = pixelStride;
          surf->width = width;
          surf->height = height;
          surf->transparency = transparancy;
          surf->alpha_pre = isAlphaPre;
          surf->num_components = (alphaMask == 0 ? 3 : 4);

          surf->bits = (int *)malloc(surf->num_components * sizeof(int));
          surf->bits[0] = parseMask(redMask, &surf->red_sht, &surf->max_red);
          surf->bits[1] = parseMask(greenMask, &surf->green_sht, &surf->max_green);
          surf->bits[2] = parseMask(blueMask, &surf->blue_sht, &surf->max_blue);
          if(alphaMask != 0){
            surf->bits[3] = parseMask(alphaMask, &(surf->alpha_sht), &(surf->max_alpha));
            surf->has_alpha = 1;
          }

#ifdef _WIN32
          GLBITMAPINFO glbmpInfo;
          memset(&glbmpInfo, 0, sizeof(GLBITMAPINFO));
          UINT stride;
          glbmpInfo.bmiHeader.biSize = sizeof(BITMAPINFOHEADER);
          glbmpInfo.bmiHeader.biWidth = surf->width;
          glbmpInfo.bmiHeader.biHeight = -surf->height;
          glbmpInfo.bmiHeader.biPlanes = 1;
          glbmpInfo.bmiHeader.biBitCount = 32;
          stride = surf->width << 2;
          glbmpInfo.bmiHeader.biSizeImage = stride * surf->height;
          glbmpInfo.bmiHeader.biCompression = BI_BITFIELDS;
          DWORD *colors = (DWORD *)glbmpInfo.bmiColors;
          colors[0] = 0xff0000;
          colors[1] = 0xff00;
          colors[2] = 0xff;
          surf->srcDC = CreateCompatibleDC(NULL);
          surf->bitmap = CreateDIBSection(NULL, (BITMAPINFO *)&glbmpInfo, DIB_RGB_COLORS, &surf->bmpData, NULL, 0);
          if(surf->srcDC != NULL && surf->bitmap != NULL){
              SelectObject(surf->srcDC, surf->bitmap);
          }
          surf->invalidated = true;
          surf->isAlphaPre = true;
#endif
      }
      return (jlong)surf;
  }

JNIEXPORT jlong JNICALL Java_org_apache_harmony_awt_gl_ImageSurface_createStructICM
  (JNIEnv *env, jobject obj, jint surfDataType, jint dataType, jint pixelStride, 
  jint scanlineStride, jint width, jint height, jint mapSize, jintArray colorMap, 
  jboolean isGrayPallete, jint transparency, jint trans, jint smType){

      SURFACE_STRUCTURE *surf = (SURFACE_STRUCTURE *)calloc(sizeof(SURFACE_STRUCTURE), 1);
      if(surf != NULL){
          surf->ss_type = surfDataType;
          surf->cm_type = INDEX_CM;
          surf->cs_type = sRGB_CS;
          surf->pixel_stride = pixelStride;
          surf->scanline_stride = scanlineStride;
          surf->scanline_stride_byte = scanlineStride;
          surf->width = width;
          surf->height = height;
          surf->colormap_size = mapSize;
          surf->transparency = transparency;
          surf->transparent_pixel = trans;
          surf->sm_type = smType;
          surf->has_alpha = (transparency == GL_OPAQUE ? 0 : 1);
          surf->isGrayPallete = isGrayPallete;
          surf->colormap = (int *)malloc(mapSize * sizeof(int));
          void *p = env->GetPrimitiveArrayCritical(colorMap, 0);
          memcpy((void *)surf->colormap, p, mapSize << 2);
          env->ReleasePrimitiveArrayCritical(colorMap, p, 0);
#ifdef _WIN32
          GLBITMAPINFO glbmpInfo;
          memset(&glbmpInfo, 0, sizeof(GLBITMAPINFO));
          UINT stride;
          glbmpInfo.bmiHeader.biSize = sizeof(BITMAPINFOHEADER);
          glbmpInfo.bmiHeader.biWidth = surf->width;
          glbmpInfo.bmiHeader.biHeight = -surf->height;
          glbmpInfo.bmiHeader.biPlanes = 1;
          glbmpInfo.bmiHeader.biBitCount = 32;
          stride = surf->width << 2;
          glbmpInfo.bmiHeader.biSizeImage = stride * surf->height;
          glbmpInfo.bmiHeader.biCompression = BI_BITFIELDS;
          DWORD *colors = (DWORD *)glbmpInfo.bmiColors;
          colors[0] = 0xff0000;
          colors[1] = 0xff00;
          colors[2] = 0xff;

          surf->srcDC = CreateCompatibleDC(NULL);
          surf->bitmap = CreateDIBSection(NULL, (BITMAPINFO *)&glbmpInfo, DIB_RGB_COLORS, &surf->bmpData, NULL, 0);
          if(surf->srcDC != NULL && surf->bitmap != NULL){
              SelectObject(surf->srcDC, surf->bitmap);
          }
          surf->invalidated = true;
          surf->isAlphaPre = true;
#endif
      }

      return (jlong)surf;
  }

JNIEXPORT jlong JNICALL Java_org_apache_harmony_awt_gl_ImageSurface_createStructCCM
  (JNIEnv *env, jobject obj, jint surfDataType, jint dataType, jint csType, 
  jint numComponents, jint pixelStride, jint scanlineStride, jint width, jint height, jintArray bits,
  jintArray bankIndeces, jintArray bandOffsets, jint transparency, jboolean isAlphaPre){

      SURFACE_STRUCTURE *surf = (SURFACE_STRUCTURE *)calloc(sizeof(SURFACE_STRUCTURE), 1);
      if(surf != NULL){
          surf->ss_type = surfDataType;
          surf->cm_type = COMPONENT_CM;
          surf->cs_type = csType;
          surf->num_components = numComponents;
          surf->pixel_stride = pixelStride;
          surf->scanline_stride = scanlineStride;
          switch(surf->ss_type){
              case BYTE_BGR:
              case BYTE_ABGR:
              case BYTE_ABGR_PRE:
              case BYTE_GRAY:
                  surf->scanline_stride_byte = scanlineStride;
                  break;
              case USHORT_GRAY:
                  surf->scanline_stride_byte = scanlineStride << 1;
                  break;
              //default:
                  // TODO
          }
          surf->width = width;
          surf->height = height;
          surf->transparency = transparency;
          surf->has_alpha = (transparency == GL_OPAQUE ? 0 : 1);
          surf->alpha_pre = isAlphaPre;

          surf->bits = (int *)malloc(numComponents * sizeof(int));
          surf->bank_indexes = (int *)malloc(numComponents * sizeof(int));
          surf->band_offsets = (int *)malloc(numComponents * sizeof(int));

          void *p = env->GetPrimitiveArrayCritical(bits, 0);
          memcpy((void *)surf->bits, p, numComponents << 2);
          env->ReleasePrimitiveArrayCritical(bits, p, 0);

          p = env->GetPrimitiveArrayCritical(bankIndeces, 0);
          memcpy((void *)surf->bank_indexes, p, numComponents << 2);
          env->ReleasePrimitiveArrayCritical(bankIndeces, p, 0);

          p = env->GetPrimitiveArrayCritical(bandOffsets, 0);
          memcpy((void *)surf->band_offsets, p, numComponents << 2);
          env->ReleasePrimitiveArrayCritical(bandOffsets, p, 0);
      }
#ifdef _WIN32
          GLBITMAPINFO glbmpInfo;
          memset(&glbmpInfo, 0, sizeof(GLBITMAPINFO));
          UINT stride;
          glbmpInfo.bmiHeader.biSize = sizeof(BITMAPINFOHEADER);
          glbmpInfo.bmiHeader.biWidth = surf->width;
          glbmpInfo.bmiHeader.biHeight = -surf->height;
          glbmpInfo.bmiHeader.biPlanes = 1;
          glbmpInfo.bmiHeader.biBitCount = 32;
          stride = surf->width << 2;
          glbmpInfo.bmiHeader.biSizeImage = stride * surf->height;
          glbmpInfo.bmiHeader.biCompression = BI_BITFIELDS;
          DWORD *colors = (DWORD *)glbmpInfo.bmiColors;
          colors[0] = 0xff0000;
          colors[1] = 0xff00;
          colors[2] = 0xff;
          surf->srcDC = CreateCompatibleDC(NULL);
          surf->bitmap = CreateDIBSection(NULL, (BITMAPINFO *)&glbmpInfo, DIB_RGB_COLORS, &surf->bmpData, NULL, 0);
          if(surf->srcDC != NULL && surf->bitmap != NULL){
              SelectObject(surf->srcDC, surf->bitmap);
          }
          surf->invalidated = true;
          surf->isAlphaPre = true;
#endif

      return (jlong)surf;
  }

JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_ImageSurface_dispose
  (JNIEnv *env, jobject obj, jlong ptr){

    SURFACE_STRUCTURE *surf = (SURFACE_STRUCTURE *)ptr;

    if(surf != NULL){
        if(surf->bits) free(surf->bits);
        if(surf->colormap) free(surf->colormap);
        if(surf->bank_indexes) free(surf->bank_indexes);
        if(surf->band_offsets) free(surf->band_offsets);
#ifdef _WIN32
        //if(surf->bitmap != NULL) delete surf->bitmap;
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
