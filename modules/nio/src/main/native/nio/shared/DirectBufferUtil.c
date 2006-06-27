/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include <jni.h>

/*
 * For JNI
 * Method:    NewDirectByteBuffer
 * Signature: (JJ)Ljava/nio/ByteBuffer;
 */
jobject NewDirectByteBuffer
  (JNIEnv * env, jlong address, jlong capacity){
	  jmethodID newBufferMethod;
	  jclass directBufferClass;
	  jclass platformaddressClass;
	  jobject platformaddress;
	  jmethodID onMethod;
          directBufferClass = (*env)->FindClass (env, "java/nio/ReadWriteDirectByteBuffer");
	  if (!directBufferClass){
	      	  return NULL;
	  }
	  newBufferMethod = (*env)->GetMethodID (env, directBufferClass, "<init>",
             "(Lorg/apache/harmony/luni/platform/PlatformAddress;II)V");
	  if (!newBufferMethod){
	      	  return NULL;
	  }
	  platformaddressClass = (*env)->FindClass (env, "org/apache/harmony/luni/platform/PlatformAddress");
	  if (!platformaddressClass){
	      	  return NULL;
	  }
	  onMethod = (*env)->GetStaticMethodID (env, platformaddressClass, "on",
             "(J)Lorg/apache/harmony/luni/platform/PlatformAddress;");
	  if (!onMethod){
	      	  return NULL;
	  }
	  platformaddress = (*env)->CallStaticObjectMethod(env, platformaddressClass, onMethod, address);
	  return (*env)->NewObject(env, directBufferClass, newBufferMethod, platformaddress, capacity, 0);
  }

/*
 * For JNI
 * Method:    GetDirectBufferAddress
 * Signature: (Ljava/nio/Buffer;)J
 */
jlong GetDirectBufferAddress
  (JNIEnv * env, jobject buf){
	  jmethodID tempMethod;
	  jclass tempClass;
	  jobject platformAddr;
	  jclass platformAddrClass;
	  jmethodID toLongMethod;
	  
          tempClass = (*env)->FindClass (env, "org/apache/harmony/nio/internal/DirectBuffer");
	  if (!tempClass){
	      	  return 0;
	  }
	  if (JNI_FALSE == (*env)->IsInstanceOf(env, buf, tempClass)){
		  return 0;
	  }	  
	  tempMethod = (*env)->GetMethodID (env, tempClass, "getBaseAddress",
             "()Lorg/apache/harmony/luni/platform/PlatformAddress;");	  	  
	  if (!tempMethod){
	      	  return 0;
	  }	  
	  platformAddr = (*env)->CallObjectMethod(env, buf, tempMethod);
	  platformAddrClass = (*env)->FindClass (env, "org/apache/harmony/luni/platform/PlatformAddress");
	  if (!platformAddrClass){
	      	  return 0;
	  }
	  toLongMethod = (*env)->GetMethodID (env, platformAddrClass, "toLong",
             "()J");
	  if (!toLongMethod){
	      	  return 0;
	  }
	  return  (*env)->CallLongMethod(env, platformAddr, toLongMethod);	  
  }

/*
 * For JNI
 * Method:    GetDirectBufferCapacity
 * Signature: (Ljava/nio/Buffer;)J
 */
jlong GetDirectBufferCapacity
  (JNIEnv * env, jobject buf){
	  jfieldID fieldCapacity;
	  jclass directBufferClass;
	  jclass bufferClass;
          directBufferClass = (*env)->FindClass (env, "org/apache/harmony/nio/internal/DirectBuffer");
	  if (!directBufferClass){
	      	  return -1;
	  }
	  if (JNI_FALSE == (*env)->IsInstanceOf(env, buf, directBufferClass)){
		  return -1;
	  }
	  bufferClass = (*env)->FindClass (env, "java/nio/Buffer");
	  if (!bufferClass){
	      	  return -1;
	  }
	  fieldCapacity = (*env)->GetFieldID (env, bufferClass, "capacity",
             "I");
	  if (!fieldCapacity){
	      	  return -1;
	  }
	  return (*env)->GetIntField(env, buf, fieldCapacity);
  }


