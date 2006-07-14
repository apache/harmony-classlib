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
  jintArray clip, jint numVertex){

      SURFACE_STRUCTURE *srcSurf = (SURFACE_STRUCTURE *)srcSurfStruct;
      SURFACE_STRUCTURE *dstSurf = (SURFACE_STRUCTURE *)dstSurfStruct;

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

          transform.eM11 = (FLOAT)(*mtrx++);
          transform.eM12 = (FLOAT)(*mtrx++);
          transform.eM21 = (FLOAT)(*mtrx++);
          transform.eM22 = (FLOAT)(*mtrx++);
          transform.eDx = (FLOAT)(*mtrx++);
          transform.eDy = (FLOAT)(*mtrx);

          env->ReleasePrimitiveArrayCritical(matrix, mtrx, 0);

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
  jintArray clip, jint numVertex){

      SURFACE_STRUCTURE *srcSurf = (SURFACE_STRUCTURE *)srcSurfStruct;
      SURFACE_STRUCTURE *dstSurf = (SURFACE_STRUCTURE *)dstSurfStruct;

      UCHAR srca = (UCHAR)(alpha * 255 + 0.5);

      BLITSTRUCT blitStruct;
      memset(&blitStruct, 0, sizeof(BLITSTRUCT));

      if(!initBlitData(srcSurf, env, srcData, compType, srca, &blitStruct)) return;

      XFORM currentTransform, transform;
      if(matrix != NULL){

          jdouble * mtrx = (jdouble *)env->GetPrimitiveArrayCritical(matrix, 0);

          transform.eM11 = (FLOAT)(*mtrx++);
          transform.eM12 = (FLOAT)(*mtrx++);
          transform.eM21 = (FLOAT)(*mtrx++);
          transform.eM22 = (FLOAT)(*mtrx++);
          transform.eDx = (FLOAT)(*mtrx++);
          transform.eDy = (FLOAT)(*mtrx);

          env->ReleasePrimitiveArrayCritical(matrix, mtrx, 0);

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

          transform.eM11 = (FLOAT)(*mtrx++);
          transform.eM12 = (FLOAT)(*mtrx++);
          transform.eM21 = (FLOAT)(*mtrx++);
          transform.eM22 = (FLOAT)(*mtrx++);
          transform.eDx = (FLOAT)(*mtrx++);
          transform.eDy = (FLOAT)(*mtrx);

          env->ReleasePrimitiveArrayCritical(matrix, mtrx, 0);

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
  jint xorcolor, jdoubleArray matrix, jintArray clip, jint numVertex){

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
            if(!initBitmap(srcSurf, env, srcData, false)) return false;
            blitStruct->blitFunctintType = BIT_BLT;
            blitStruct->rastOp = SRCCOPY;
            return true;

        case COMPOSITE_SRC_OVER:
        case COMPOSITE_SRC_ATOP:
            if(!initBitmap(srcSurf, env, srcData, true)) return false;
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

    void *bmpDataPtr = srcSurf->bmpData;
    void *srcDataPtr = env->GetPrimitiveArrayCritical((jarray)srcData, 0);

    UINT srcstride, dststride;
    DIBSECTION dibs;
    GetObject(srcBmp, sizeof(DIBSECTION), &dibs);
    srcstride = srcSurf->scanline_stride_byte;
    dststride = dibs.dsBm.bmWidthBytes;
    int transparency = srcSurf->transparency;

    UCHAR *src, *dst, *s, *d;
    src = (UCHAR *)srcDataPtr;
    dst = (UCHAR *)bmpDataPtr;
    UINT *dstIntPtr, *dstInt, r, g, b, a, pixel, pixelBits, bitnum, elem, shift, bitMask;
    UCHAR sa;
    USHORT *usSrc, *tus, uspixel;

    switch(srcSurf->ss_type){

        case INT_RGB:
            if(!srcSurf->invalidated) return true;
            //for(int y = srcSurf->height; y > 0; y--, src += srcstride, dst += dststride){
            //    memcpy(dst, src, dststride);
            //}
            for(int y = srcSurf->height; y > 0; y--, src += srcstride, dst += dststride){
                int x = srcSurf->width;
                s = src + (x << 2) - 1;
                d = dst + (x << 2) - 1;

                for(; x > 0 ; x--){
                    *d-- = 255;
                    *s--;
                    *d-- = *s--;
                    *d-- = *s--;
                    *d-- = *s--;
                }
            }
            break;

        case INT_ARGB:
            if(alphaPre){
                if(!srcSurf->invalidated && srcSurf->isAlphaPre) return true;
                for(int y = srcSurf->height; y > 0 ; y--, src += srcstride, dst += dststride){
                    int x = srcSurf->width;
                    s = src + (x << 2) - 1;
                    d = dst + (x << 2) - 1;

                    for(; x > 0 ; x--){
                        sa = *s--;
                        *d-- = sa;
                        *d-- = MUL(sa, *s--);
                        *d-- = MUL(sa, *s--);
                        *d-- = MUL(sa, *s--);
                    }
                }
                srcSurf->isAlphaPre = true;
            }else{
                if(!srcSurf->invalidated && !srcSurf->isAlphaPre) return true;
                for(int y = srcSurf->height; y > 0 ; y--, src += srcstride, dst += dststride){
                    int x = srcSurf->width;
                    s = src + (x << 2) - 1;
                    d = dst + (x << 2) - 1;

                    for(; x > 0 ; x--){
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
            break;

        case BYTE_ABGR:
            if(alphaPre){
                if(!srcSurf->invalidated && srcSurf->isAlphaPre) return true;
                for(int y = srcSurf->height; y > 0 ; y--, src += srcstride, dst += dststride){
                    int x = srcSurf->width;
                    s = src + (x << 2) - 1;
                    d = dst + (x << 2) - 1;

                    for(; x > 0 ; x--){
                        r = *s--;
                        g = *s--;
                        b = *s--;
                        sa = *s--;
                        *d-- = sa;
                        *d-- = MUL(sa, r);
                        *d-- = MUL(sa, g);
                        *d-- = MUL(sa, b);
                    }
                }
                srcSurf->isAlphaPre = true;
            }else{
                if(!srcSurf->invalidated && !srcSurf->isAlphaPre) return true;
                for(int y = srcSurf->height; y > 0 ; y--, src += srcstride, dst += dststride){
                    int x = srcSurf->width;
                    s = src + (x << 2) - 1;
                    d = dst + (x << 2) - 1;

                    for(; x > 0 ; x--){
                        r = *s--;
                        g = *s--;
                        b = *s--;
                        sa = *s--;
                        if(sa == 0){
                            *d-- = 0;
                            *d-- = 0;
                            *d-- = 0;
                            *d-- = 0;
                        }else{
                            *d-- = sa;
                            *d-- = r;
                            *d-- = g;
                            *d-- = b;
                        }
                    }
                }
                srcSurf->isAlphaPre = false;
            }
            break;

        case INT_ARGB_PRE:
            if(alphaPre){
                if(!srcSurf->invalidated && srcSurf->isAlphaPre) return true;
                for(int y = srcSurf->height; y > 0; y--, src += srcstride, dst += dststride){
                    memcpy(dst, src, dststride);
                }
                srcSurf->isAlphaPre = true;
            }else{
                if(!srcSurf->invalidated && !srcSurf->isAlphaPre) return true;
                for(int y = srcSurf->height; y > 0 ; y--, src += srcstride, dst += dststride){
                    int x = srcSurf->width;
                    s = src + (x << 2) - 1;
                    d = dst + (x << 2) - 1;

                    for(; x > 0 ; x--){
                        sa = *s--;
                        *d-- = sa;
                        *d-- = DIV(sa, *s--);
                        *d-- = DIV(sa, *s--);
                        *d-- = DIV(sa, *s--);
                    }
                }
                srcSurf->isAlphaPre = false;
            }
            break;

        case BYTE_ABGR_PRE:
            if(alphaPre){
                if(!srcSurf->invalidated && srcSurf->isAlphaPre) return true;
                for(int y = srcSurf->height; y > 0 ; y--, src += srcstride, dst += dststride){
                    int x = srcSurf->width;
                    s = src + (x << 2) - 1;
                    d = dst + (x << 2) - 1;

                    for(; x > 0 ; x--){
                        r = *s--;
                        g = *s--;
                        b = *s--;
                        sa = *s--;
                        *d-- = sa;
                        *d-- = r;
                        *d-- = g;
                        *d-- = b;
                    }
                }
                srcSurf->isAlphaPre = true;
            }else{
                if(!srcSurf->invalidated && !srcSurf->isAlphaPre) return true;
                    for(int y = srcSurf->height; y > 0 ; y--, src += srcstride, dst += dststride){
                        int x = srcSurf->width;
                        s = src + (x << 2) - 1;
                        d = dst + (x << 2) - 1;

                        for(; x > 0 ; x--){
                            r = *s--;
                            g = *s--;
                            b = *s--;
                            sa = *s--;
                            *d-- = sa;
                            *d-- = DIV(sa, r);
                            *d-- = DIV(sa, g);
                            *d-- = DIV(sa, b);
                        }
                    }
                srcSurf->isAlphaPre = false;
            }
            break;

        case INT_BGR:
            if(!srcSurf->invalidated) return true;
            for(int y = srcSurf->height; y > 0; y--, src += srcstride, dst += dststride){
                int x = srcSurf->width;
                s = src + (x << 2) - 1;
                d = dst + (x << 2) - 1;

                for(; x > 0 ; x--){
                    *d = 255;
                    *s--;
                    *(d - 3) = *s--;
                    *(d - 2) = *s--;
                    *(d - 1) = *s--;
                    d -= 4;
                }
            }
            break;

        case USHORT_555:
        case USHORT_565:
            if(!srcSurf->invalidated) return true;
            usSrc = (USHORT *)srcDataPtr;
            srcstride >>= 1;
            dstIntPtr = (UINT *)bmpDataPtr;
            dststride >>= 2;
            a = 0xff000000;
            for(int y = srcSurf->height; y > 0; y--, usSrc += srcstride, dstIntPtr += dststride){
                dstInt = dstIntPtr;
                tus = usSrc;
                for(int x = srcSurf->width; x > 0; x--){
                    uspixel = *tus++;
                    r = (uspixel & srcSurf->red_mask) >> srcSurf->red_sht;
                    g = (uspixel & srcSurf->green_mask) >> srcSurf->green_sht;
                    b = (uspixel & srcSurf->blue_mask) >> srcSurf->blue_sht;
                    r = DIV(srcSurf->max_red, r);
                    g = DIV(srcSurf->max_green, g);
                    b = DIV(srcSurf->max_blue, b);
                    *dstInt++ = a | (r << 16) | (g << 8) | b;
                }
            }
            break;

        case USHORT_GRAY:
            if(!srcSurf->invalidated) return true;
            dstIntPtr = (UINT *)bmpDataPtr;
            dststride >>= 2;
            usSrc = (USHORT *)srcDataPtr;
            srcstride = srcSurf->scanline_stride;
            a = 0xff000000;
            for(int y = srcSurf->height; y > 0; y--, usSrc += srcstride, dstIntPtr += dststride){
                tus = usSrc;
                dstInt = dstIntPtr;
                for(int x = srcSurf->width; x > 0; x--){
                    pixel = (UINT)(*tus++ / 257);
                    *dstInt++ = a | (pixel << 16) | (pixel << 8) | pixel;
                }
            }
            break;

        case BYTE_BINARY:
            if(!srcSurf->invalidated) return true;
            dstIntPtr = (UINT *)bmpDataPtr;
            dststride >>= 2;
            a = 0xff000000;
            for(int y = srcSurf->height; y > 0; y--, src += srcstride, dstIntPtr += dststride){
                int x = srcSurf->width;
                dstInt = dstIntPtr + x - 1;

                for(; x > 0; x--){
                    pixelBits = srcSurf->pixel_stride;
                    bitnum = x * pixelBits;
                    s = src + bitnum / 8;
                    elem = *s;
                    shift = 8 - (bitnum & 7) - pixelBits;
                    bitMask = (1 << pixelBits) - 1;
                    pixel = (elem >> shift) & bitMask;
                    *dstInt-- = a | *(srcSurf->colormap + pixel);
                }
            }
            break;

        case BYTE_INDEXED:
            if(!srcSurf->invalidated && srcSurf->transparency != GL_TRANSLUCENT) return true;
            dstIntPtr = (UINT *)bmpDataPtr;
            dststride >>= 2;
            for(int y = srcSurf->height; y > 0; y--, src += srcstride, dstIntPtr += dststride){
                s = src;
                dstInt = dstIntPtr;

                for(int x = srcSurf->width; x > 0; x--){
                    pixel = *s++;
                    if(transparency == GL_OPAQUE){
                        *dstInt++ = *(srcSurf->colormap + pixel);
                    }else if(transparency == GL_BITMASK){
                        if(pixel != srcSurf->transparent_pixel){
                            *dstInt++ = *(srcSurf->colormap + pixel);
                        }else{
                            *dstInt++ = 0;
                        }
                    }else{
                        pixel = *(srcSurf->colormap + pixel);
                        a = (pixel >> 24) & 0xff;
                        if(alphaPre){
                            if(a == 255) *dstInt = pixel;
                            else{
                                r = (pixel >> 16) & 0xff;
                                g = (pixel >> 8) & 0xff;
                                b = pixel & 0xff;
                                r = MUL(a, r);
                                g = MUL(a, g);
                                b = MUL(a, b);
                                *dstInt = (a << 24) | (r << 16) | (g << 8) | b;
                            }
                        }else{
                            if(a == 0) *dstInt = 0;
                            else *dstInt = pixel;
                        }
                    }
                }
            }
            if(srcSurf->transparency == GL_TRANSLUCENT && alphaPre){
                srcSurf->isAlphaPre = true;
            }else if(srcSurf->transparency == GL_TRANSLUCENT && !alphaPre){
                srcSurf->isAlphaPre = false;
            }else{
                srcSurf->isAlphaPre = true;
            }
            break;

        case BYTE_GRAY:
            if(!srcSurf->invalidated) return true;
            dstIntPtr = (UINT *)bmpDataPtr;
            dststride >>= 2;
            a = 0xff000000;
            for(int y = srcSurf->height; y > 0; y--, src += srcstride, dstIntPtr += dststride){
                s = src;
                dstInt = dstIntPtr;

                for(int x = srcSurf->width; x > 0; x--){
                    pixel = *s++;
                    *dstInt++ = a | (pixel << 16) | (pixel << 8) | pixel;
                }
            }
            break;

        case BYTE_BGR:
            if(!srcSurf->invalidated) return true;
            dstIntPtr = (UINT *)bmpDataPtr;
            dststride >>= 2;
            a = 0xff000000;
            for(int y = srcSurf->height; y > 0; y--, src += srcstride, dstIntPtr += dststride){
                s = src;
                dstInt = dstIntPtr;

                for(int x = srcSurf->width; x > 0; x--){
                    b = *s++;
                    g = *s++;
                    r = *s++;
                    *dstInt++ = a | (r << 16) | (g << 8) | b;
                }
            }
            break;
    }
    env->ReleasePrimitiveArrayCritical((jarray)srcData, srcDataPtr, 0);
    return true;
}



