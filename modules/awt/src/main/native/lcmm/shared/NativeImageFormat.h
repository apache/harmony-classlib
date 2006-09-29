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
 * @author Oleg V. Khaschansky
 * @version $Revision$
 * 
 */

#ifndef _Included_NativeImageFormat
#define _Included_NativeImageFormat

#include "NativeCMM.h"

jfieldID clr_NIF_cmmFormatID;
jfieldID clr_NIF_colsID;
jfieldID clr_NIF_rowsID;
jfieldID clr_NIF_scanlineStrideID;
jfieldID clr_NIF_imageDataID;
jfieldID clr_NIF_dataOffsetID;
jfieldID clr_NIF_alphaOffsetID;

// Represents NativeImageFormat java class
typedef struct {
    int cmmFormat;
    int cols;       // number of columns
    int rows;       // number of rows
    int scanlineStride; // bytes to next row
  int dataOffset; // offset from the beginning of data array if any
  int alphaOffset; // offset from the beginning of the data to the first alpha sample
  
  // Java array for image data
    jarray jImageData;
  // C array corresponding to jImageData
    BYTE *imageData;

} ImageFormat;
////////////////////

ImageFormat* getImageFormat(JNIEnv* env, jobject jimft);
void releaseImageFormat(JNIEnv* env, ImageFormat* imft);

#endif // _Included_NativeImageFormat
