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
 
#include "SurfaceDataStructure.h"
#include "org_apache_harmony_awt_gl_windows_GDISurface.h"
#include "org_apache_harmony_awt_gl_windows_GDIBlitter.h"
#include "blitter.h"
#include "GDIBlitter.h"
#include "gl_GDIPlus.h"

JNIEXPORT jlong JNICALL Java_org_apache_harmony_awt_gl_windows_GDISurface_createSurfData
(JNIEnv *env, jobject obj, jlong gi){

    SURFACE_STRUCTURE *surf = (SURFACE_STRUCTURE *)calloc(sizeof(SURFACE_STRUCTURE), 1);
    surf->gi = (GraphicsInfo *)gi;
    return (jlong)surf;
}

JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_windows_GDISurface_dispose
(JNIEnv *env, jobject obj, jlong surfDataPtr){

    free((void *)surfDataPtr);
}

JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_windows_GDIBlitter_bltBGImage
  (JNIEnv *env, jobject obj, jint srcX, jint srcY, jlong srcSurfStruct, jobject srcData, 
  jint dstX, jint dstY, jlong dstSurfStruct, jint width, jint height, 
  jint bgcolor, jint compType, jfloat alpha, jdoubleArray matrix, 
  jintArray clip, jint numVertex, jboolean invalidated){

      SURFACE_STRUCTURE *srcSurf = (SURFACE_STRUCTURE *)srcSurfStruct;
      SURFACE_STRUCTURE *dstSurf = (SURFACE_STRUCTURE *)dstSurfStruct;

      srcSurf->invalidated = invalidated != 0;
      HDC tmpDC = CreateCompatibleDC(dstSurf->gi->hdc);
      int w = srcSurf->width;
      int h = srcSurf->height;
      HBITMAP tmpBmp = CreateCompatibleBitmap(dstSurf->gi->hdc, w, h);
      SelectObject(tmpDC, tmpBmp);

      BYTE a = (BYTE)((bgcolor >> 24) & 0xff);
      BYTE r = (BYTE)((bgcolor >> 16) & 0xff);
      BYTE g = (BYTE)((bgcolor >> 8) & 0xff);
      BYTE b = (BYTE)(bgcolor & 0xff);
      r = MUL(a, r);
      g = MUL(a, g);
      b = MUL(a, b);

      HBRUSH brush = CreateSolidBrush(RGB(r, g, b));
      SelectObject(tmpDC, brush);
      PatBlt(tmpDC, 0, 0, w, h, PATCOPY);

      if(initBitmap(srcSurf, env, srcData, true)){
          BLENDFUNCTION bf;
          bf.AlphaFormat = AC_SRC_ALPHA;
          bf.BlendOp = AC_SRC_OVER;
          bf.BlendFlags = 0;
          bf.SourceConstantAlpha = 255;
          AlphaBlend(tmpDC, 0, 0, w, h, srcSurf->srcDC, 0, 0, w, h, bf);
      }

      UCHAR srca = (UCHAR)(alpha * 255 + 0.5);

      XFORM currentTransform, transform;
      if(matrix != NULL){

          jdouble * mtrx = (jdouble *)env->GetPrimitiveArrayCritical(matrix, 0);
          jdouble * old_mtrx = mtrx;

          transform.eM11 = (FLOAT)(*mtrx++);
          transform.eM12 = (FLOAT)(*mtrx++);
          transform.eM21 = (FLOAT)(*mtrx++);
          transform.eM22 = (FLOAT)(*mtrx++);
          transform.eDx = (FLOAT)(*mtrx++);
          transform.eDy = (FLOAT)(*mtrx);

          env->ReleasePrimitiveArrayCritical(matrix, old_mtrx, 0);

          SetGraphicsMode(dstSurf->gi->hdc, GM_ADVANCED);
          GetWorldTransform(dstSurf->gi->hdc, &currentTransform);
          SetWorldTransform(dstSurf->gi->hdc, &transform);
      }

      HRGN oldClip = setGdiClip(env, dstSurf->gi->hdc, clip, numVertex);

      BLITSTRUCT blitStruct;
      memset(&blitStruct, 0, sizeof(BLITSTRUCT));

      switch(compType){
          case COMPOSITE_CLEAR:
          case COMPOSITE_SRC_OUT:
              blitStruct.blitFunctintType = BIT_BLT;
              blitStruct.rastOp = BLACKNESS;
              break;

          case COMPOSITE_SRC:
          case COMPOSITE_SRC_IN:
              blitStruct.blitFunctintType = BIT_BLT;
              if(srca == 0) blitStruct.rastOp = BLACKNESS;
              else blitStruct.rastOp = SRCCOPY;
              break;

          case COMPOSITE_DST:
          case COMPOSITE_DST_OVER:
              return;

          case COMPOSITE_SRC_ATOP:
          case COMPOSITE_SRC_OVER:
              if(srca == 255){
                  blitStruct.blitFunctintType = BIT_BLT;
                  blitStruct.rastOp = SRCCOPY;
              }else{

                  blitStruct.blitFunctintType = ALPHA_BLEND;
                  blitStruct.blendFunc.AlphaFormat = 0;
                  blitStruct.blendFunc.BlendOp = AC_SRC_OVER;
                  blitStruct.blendFunc.BlendFlags = 0;
                  blitStruct.blendFunc.SourceConstantAlpha = srca;
              }
              break;

          case COMPOSITE_DST_IN:
          case COMPOSITE_DST_ATOP:
              if(srca != 0) return;
              blitStruct.blitFunctintType = BIT_BLT;
              blitStruct.rastOp = BLACKNESS;
              break;

          case COMPOSITE_DST_OUT:
          case COMPOSITE_XOR:
              if(srca != 255) return;
              blitStruct.blitFunctintType = BIT_BLT;
              blitStruct.rastOp = BLACKNESS;
              break;
      }

      switch(blitStruct.blitFunctintType){
          case ALPHA_BLEND:
              AlphaBlend(dstSurf->gi->hdc, dstX, dstY, width, height, tmpDC,
                      srcX, srcY, width, height, blitStruct.blendFunc);
              break;

          case TRANSPARENT_BLT:
              TransparentBlt(dstSurf->gi->hdc, dstX, dstY, width, height, tmpDC,
                  srcX, srcY, width, height, srcSurf->rtc);
              break;

          default:
              BitBlt(dstSurf->gi->hdc, dstX, dstY, width, height, tmpDC,
                  srcX, srcY, blitStruct.rastOp);
              break;
      }
      if(matrix){
          SetWorldTransform(dstSurf->gi->hdc, &currentTransform);
          SetGraphicsMode(dstSurf->gi->hdc, GM_COMPATIBLE);
      }
      restoreGdiClip(dstSurf->gi->hdc, oldClip);

      DeleteObject(brush);
      DeleteObject(tmpBmp);
      DeleteDC(tmpDC);

  }

JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_windows_GDIBlitter_bltImage
  (JNIEnv *env, jobject obj, jint srcX, jint srcY, jlong srcSurfStruct, jobject srcData, 
  jint dstX, jint dstY, jlong dstSurfStruct, jint width, jint height, 
  jint compType, jfloat alpha, jdoubleArray matrix, 
  jintArray clip, jint numVertex, jboolean invalidated){

      SURFACE_STRUCTURE *srcSurf = (SURFACE_STRUCTURE *)srcSurfStruct;
      SURFACE_STRUCTURE *dstSurf = (SURFACE_STRUCTURE *)dstSurfStruct;

      UCHAR srca = (UCHAR)(alpha * 255 + 0.5);

      BLITSTRUCT blitStruct;
      memset(&blitStruct, 0, sizeof(BLITSTRUCT));

      srcSurf->invalidated = invalidated != 0;
      if(!initBlitData(srcSurf, env, srcData, compType, srca, &blitStruct)) return;

      XFORM currentTransform, transform;
      if(matrix != NULL){

          jdouble * mtrx = (jdouble *)env->GetPrimitiveArrayCritical(matrix, 0);
          jdouble * old_mtrx = mtrx;

          transform.eM11 = (FLOAT)(*mtrx++);
          transform.eM12 = (FLOAT)(*mtrx++);
          transform.eM21 = (FLOAT)(*mtrx++);
          transform.eM22 = (FLOAT)(*mtrx++);
          transform.eDx = (FLOAT)(*mtrx++);
          transform.eDy = (FLOAT)(*mtrx);

          env->ReleasePrimitiveArrayCritical(matrix, old_mtrx, 0);

          SetGraphicsMode(dstSurf->gi->hdc, GM_ADVANCED);
          GetWorldTransform(dstSurf->gi->hdc, &currentTransform);
          SetWorldTransform(dstSurf->gi->hdc, &transform);
      }

      HRGN oldClip = setGdiClip(env, dstSurf->gi->hdc, clip, numVertex);

      switch(blitStruct.blitFunctintType){
          case ALPHA_BLEND:
              AlphaBlend(dstSurf->gi->hdc, dstX, dstY, width, height, srcSurf->srcDC,
                      srcX, srcY, width, height, blitStruct.blendFunc);
              break;

          case TRANSPARENT_BLT:
              TransparentBlt(dstSurf->gi->hdc, dstX, dstY, width, height, srcSurf->srcDC,
                  srcX, srcY, width, height, srcSurf->rtc);
              break;

          default:
              BitBlt(dstSurf->gi->hdc, dstX, dstY, width, height, srcSurf->srcDC,
                  srcX, srcY, blitStruct.rastOp);
              break;
      }

      if(matrix){
          SetWorldTransform(dstSurf->gi->hdc, &currentTransform);
          SetGraphicsMode(dstSurf->gi->hdc, GM_COMPATIBLE);
      }
      restoreGdiClip(dstSurf->gi->hdc, oldClip);

  }

JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_windows_GDIBlitter_bltBitmap
  (JNIEnv *env, jobject obj, jint srcX, jint srcY, jlong srcSurfStruct, 
  jint dstX, jint dstY, jlong dstSurfStruct, jint width, jint height, 
  jint compType, jfloat alpha, jdoubleArray matrix, jintArray clip, 
  jint numVertex){

      SURFACE_STRUCTURE *srcSurf = (SURFACE_STRUCTURE *)srcSurfStruct;
      SURFACE_STRUCTURE *dstSurf = (SURFACE_STRUCTURE *)dstSurfStruct;

      UCHAR srca = (UCHAR)(alpha * 255 + 0.5);

      XFORM currentTransform, transform;
      if(matrix != NULL){

          jdouble * mtrx = (jdouble *)env->GetPrimitiveArrayCritical(matrix, 0);
          jdouble * old_mtrx = mtrx;

          transform.eM11 = (FLOAT)(*mtrx++);
          transform.eM12 = (FLOAT)(*mtrx++);
          transform.eM21 = (FLOAT)(*mtrx++);
          transform.eM22 = (FLOAT)(*mtrx++);
          transform.eDx = (FLOAT)(*mtrx++);
          transform.eDy = (FLOAT)(*mtrx);

          env->ReleasePrimitiveArrayCritical(matrix, old_mtrx, 0);

          SetGraphicsMode(dstSurf->gi->hdc, GM_ADVANCED);
          GetWorldTransform(dstSurf->gi->hdc, &currentTransform);
          SetWorldTransform(dstSurf->gi->hdc, &transform);
      }

      HRGN oldClip = setGdiClip(env, dstSurf->gi->hdc, clip, numVertex);

      BLITSTRUCT blitStruct;
      memset(&blitStruct, 0, sizeof(BLITSTRUCT));

      switch(compType){
          case COMPOSITE_CLEAR:
          case COMPOSITE_SRC_OUT:
              blitStruct.blitFunctintType = BIT_BLT;
              blitStruct.rastOp = BLACKNESS;
              break;

          case COMPOSITE_SRC:
          case COMPOSITE_SRC_IN:
              blitStruct.blitFunctintType = BIT_BLT;
              if(srca == 0) blitStruct.rastOp = BLACKNESS;
              else blitStruct.rastOp = SRCCOPY;
              break;

          case COMPOSITE_DST:
          case COMPOSITE_DST_OVER:
              return;

          case COMPOSITE_SRC_ATOP:
          case COMPOSITE_SRC_OVER:
              if(srca == 255){
                  blitStruct.blitFunctintType = BIT_BLT;
                  blitStruct.rastOp = SRCCOPY;
              }else{

                  blitStruct.blitFunctintType = ALPHA_BLEND;
                  blitStruct.blendFunc.AlphaFormat = 0;
                  blitStruct.blendFunc.BlendOp = AC_SRC_OVER;
                  blitStruct.blendFunc.BlendFlags = 0;
                  blitStruct.blendFunc.SourceConstantAlpha = srca;
              }
              break;

          case COMPOSITE_DST_IN:
          case COMPOSITE_DST_ATOP:
              if(srca != 0) return;
              blitStruct.blitFunctintType = BIT_BLT;
              blitStruct.rastOp = BLACKNESS;
              break;

          case COMPOSITE_DST_OUT:
          case COMPOSITE_XOR:
              if(srca != 255) return;
              blitStruct.blitFunctintType = BIT_BLT;
              blitStruct.rastOp = BLACKNESS;
              break;
      }

      switch(blitStruct.blitFunctintType){
          case ALPHA_BLEND:
              AlphaBlend(dstSurf->gi->hdc, dstX, dstY, width, height, srcSurf->gi->hdc,
                      srcX, srcY, width, height, blitStruct.blendFunc);
              break;

          case TRANSPARENT_BLT:
              TransparentBlt(dstSurf->gi->hdc, dstX, dstY, width, height, srcSurf->gi->hdc,
                  srcX, srcY, width, height, srcSurf->rtc);
              break;

          default:
              BitBlt(dstSurf->gi->hdc, dstX, dstY, width, height, srcSurf->gi->hdc,
                  srcX, srcY, blitStruct.rastOp);
              break;
      }
      if(matrix){
          SetWorldTransform(dstSurf->gi->hdc, &currentTransform);
          SetGraphicsMode(dstSurf->gi->hdc, GM_COMPATIBLE);
      }
      restoreGdiClip(dstSurf->gi->hdc, oldClip);
  }

JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_windows_GDIBlitter_xorImage
  (JNIEnv *env, jobject obj, jint srcX, jint srcY, jlong srcSurfStruct, jobject srcData, 
  jint dstX, jint dstY, jlong dstSurfStruct, jobject dstData, jint width, jint heigth, 
  jint xorcolor, jdoubleArray matrix, jintArray clip, jint numVertex, jboolean invalidated){

  }

JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_windows_GDIBlitter_xorBitmap
  (JNIEnv *env, jobject obj, jint srcX, jint srcY, jlong srcSurfStruct, jobject srcData, 
  jint dstX, jint dstY, jlong dstSurfStruct, jobject dstData, jint width, jint heigth, 
  jint xorcolor, jdoubleArray matrix, jintArray clip, jint numVertex){

  }

void findNonExistColor(DWORD &tcolor, DWORD *colormap, UINT size){
     UINT *tmp = (UINT *)malloc(sizeof(UINT) * 257);
     memset(tmp, 0, sizeof(UINT) * size);
     UINT idx;
     for(UINT i = 0; i < size; i++){
         idx = 0x1ff & *(colormap + i);
         *(tmp + idx) = 1;
     }
     for(UINT i = 0; i < 257; i++){
         if(*(tmp + i) == 0){
             tcolor = i;
             break;
         }
     }
     delete(tmp);
 }

BOOL isRepeatColor(UINT idx, DWORD *colormap, UINT size){
     DWORD tcolor = *(colormap + idx);
     BOOL repeat = false;
     for(UINT i = 0; i < size; i++){
         if(i == idx) continue;
         if((tcolor ^ *(colormap + i)) == 0){
             repeat = true;
             break;
         }
     }
     return repeat;
 }

BOOL initBlitData
(SURFACE_STRUCTURE *srcSurf, JNIEnv *env, jobject srcData, UINT compType, UCHAR srcConstAlpha, BLITSTRUCT *blitStruct){

    switch(compType){
        case COMPOSITE_CLEAR:
        case COMPOSITE_SRC_OUT:
            blitStruct->blitFunctintType = BIT_BLT;
            blitStruct->rastOp = BLACKNESS;
            return true;

        case COMPOSITE_SRC:
        case COMPOSITE_SRC_IN:
            if(srcConstAlpha == 0){
                blitStruct->blitFunctintType = BIT_BLT;
                blitStruct->rastOp = BLACKNESS;
                return true;
            }
                       if(srcSurf->invalidated || srcSurf->isAlphaPre != false){
                               if(!initBitmap(srcSurf, env, srcData, false)) return false;
                       }
            blitStruct->blitFunctintType = BIT_BLT;
            blitStruct->rastOp = SRCCOPY;
            return true;

        case COMPOSITE_SRC_OVER:
        case COMPOSITE_SRC_ATOP:
                       if(srcSurf->invalidated || srcSurf->isAlphaPre != true){
                               if(!initBitmap(srcSurf, env, srcData, true)) return false;
                       }
            if(srcSurf->transparency != GL_OPAQUE || srcConstAlpha != 255){
                blitStruct->blitFunctintType = ALPHA_BLEND;
                blitStruct->blendFunc.AlphaFormat = srcSurf->transparency != GL_OPAQUE ? AC_SRC_ALPHA : 0;
                blitStruct->blendFunc.BlendOp = AC_SRC_OVER;
                blitStruct->blendFunc.BlendFlags = 0;
                blitStruct->blendFunc.SourceConstantAlpha = srcConstAlpha;
            }else{
                blitStruct->blitFunctintType = BIT_BLT;
                blitStruct->rastOp = SRCCOPY;
            }
            return true;

        case COMPOSITE_DST:
        case COMPOSITE_DST_OVER:
            return false;

        case COMPOSITE_DST_IN:
        case COMPOSITE_DST_ATOP:
            if(srcConstAlpha == 0){
                blitStruct->blitFunctintType = BIT_BLT;
                blitStruct->rastOp = BLACKNESS;
                return true;
            }
            // TODO Need to check src alpha
            return false;

        case COMPOSITE_DST_OUT:
        case COMPOSITE_XOR:
            if(srcConstAlpha == 255 && srcSurf->transparency == GL_OPAQUE){
                blitStruct->blitFunctintType = BIT_BLT;
                blitStruct->rastOp = BLACKNESS;
                return true;
            }
            // TODO Need to check src alpha
            return false;

        default:
            return false;
    }
}

BOOL initBitmap
(SURFACE_STRUCTURE *srcSurf, JNIEnv *env, jobject srcData, BOOL alphaPre){

       HBITMAP srcBmp = srcSurf->bitmap;
    if(!srcBmp){
        return false;
    }
       updateCache(srcSurf, env, srcData, alphaPre != 0);
       SetDIBits(srcSurf->srcDC, srcSurf->bitmap, 0, srcSurf->height, srcSurf->bmpData, (BITMAPINFO *)&srcSurf->bmpInfo, DIB_RGB_COLORS);
    return true;
}



