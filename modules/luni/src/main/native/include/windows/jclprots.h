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

#if !defined(JCLPROTS_H)
#define JCLPROTS_H

#if defined(__cplusplus)
extern "C"
{
#endif
#include "vmi.h"


  /* NativesCommonPlainServerSocketImpl*/
  JNIEXPORT void JNICALL
    Java_java_net_PlainServerSocketImpl_createServerStreamSocketImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject thisObjFD,
                jboolean preferIPv4Stack));
                
  /* NativesCommonDeflater*/
  JNIEXPORT void JNICALL Java_java_util_zip_Deflater_setDictionaryImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray dict, int off, int len,
                jlong handle));
  JNIEXPORT void JNICALL Java_java_util_zip_Deflater_resetImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong handle));
  JNIEXPORT jlong JNICALL Java_java_util_zip_Deflater_getTotalOutImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong handle));
  JNIEXPORT void JNICALL Java_java_util_zip_Deflater_endImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong handle));
  JNIEXPORT jint JNICALL Java_java_util_zip_Deflater_deflateImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buf, int off, int len,
                jlong handle, int flushParm));
  JNIEXPORT void JNICALL Java_java_util_zip_Deflater_setLevelsImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, int level, int strategy,
                jlong handle));
  JNIEXPORT void JNICALL Java_java_util_zip_Deflater_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  JNIEXPORT void JNICALL Java_java_util_zip_Deflater_setInputImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buf, jint off,
                jint len, jlong handle));
  JNIEXPORT jlong JNICALL Java_java_util_zip_Deflater_createStream
    PROTOTYPE ((JNIEnv * env, jobject recv, jint level, jint strategy,
                jboolean noHeader));
  JNIEXPORT jlong JNICALL Java_java_util_zip_Deflater_getTotalInImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong handle));
  JNIEXPORT jint JNICALL Java_java_util_zip_Deflater_getAdlerImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong handle));

  /* NativesCommonPlainSocketImpl2*/
  JNIEXPORT void JNICALL
    Java_java_net_PlainSocketImpl2_connectStreamWithTimeoutSocketImpl2
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jint remotePort, jint timeout, jint trafficClass,
                jobject inetAddress));
  JNIEXPORT void JNICALL Java_java_net_PlainSocketImpl2_socketBindImpl2
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jint localPort, jobject inetAddress));
  JNIEXPORT void JNICALL Java_java_net_PlainSocketImpl2_createStreamSocketImpl2
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject thisObjFD,
                jboolean preferIPv4Stack));
  JNIEXPORT void JNICALL Java_java_net_PlainSocketImpl2_connectStreamSocketImpl2
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jint remotePort, jint trafficClass, jobject inetAddress));
  JNIEXPORT jint JNICALL Java_java_net_PlainSocketImpl2_sendDatagramImpl2
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jbyteArray data, jint offset, jint msgLength, jint targetPort,
                jobject inetAddress));
                
  /* NativesCommonFileOutputStream*/
  JNIEXPORT jint JNICALL Java_java_io_FileOutputStream_openImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path,
                jboolean append));
  JNIEXPORT void JNICALL Java_java_io_FileOutputStream_closeImpl
    PROTOTYPE ((JNIEnv * env, jobject recv));
  JNIEXPORT void JNICALL Java_java_io_FileOutputStream_writeByteImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jint c, jlong descriptor));
  JNIEXPORT void JNICALL Java_java_io_FileOutputStream_writeImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buffer, jint offset,
                jint count, jlong descriptor));
  JNIEXPORT void JNICALL Java_java_io_FileOutputStream_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass clazz));
    
  /* NativesCommonDoubleParsing*/
  JNIEXPORT jdouble JNICALL
    Java_org_apache_harmony_luni_util_FloatingPointParser_parseDblImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz, jstring s, jint e));
  JNIEXPORT void JNICALL
    Java_org_apache_harmony_luni_util_NumberConverter_bigIntDigitGeneratorInstImpl
    PROTOTYPE ((JNIEnv * env, jobject inst, jlong f, jint e,
                jboolean isDenormalized, jboolean mantissaIsZero, jint p));
                
  /* NativesCommonAdler32 */
  JNIEXPORT jlong JNICALL Java_java_util_zip_Adler32_updateImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buf, int off, int len,
                jlong crc));
  JNIEXPORT jlong JNICALL Java_java_util_zip_Adler32_updateByteImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jint val, jlong crc));
    
  /* NativesCommonCRC32*/
  JNIEXPORT jlong JNICALL Java_java_util_zip_CRC32_updateByteImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyte val, jlong crc));
  JNIEXPORT jlong JNICALL Java_java_util_zip_CRC32_updateImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buf, int off, int len,
                jlong crc));
                
  /* NativesCommonSocketImpl*/
  JNIEXPORT void JNICALL Java_java_net_SocketImpl_listenStreamSocketImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jint backlog));
  JNIEXPORT void JNICALL Java_java_net_SocketImpl_acceptStreamSocketImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptorServer,
                jobject socketImpl, jobject fileDescriptorSocketImpl,
                jint timeout));
  JNIEXPORT void JNICALL Java_java_net_SocketImpl_sendUrgentDataImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jbyte data));
  JNIEXPORT jint JNICALL Java_java_net_SocketImpl_receiveStreamImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jbyteArray data, jint offset, jint count, jint timeout));
  JNIEXPORT void JNICALL Java_java_net_SocketImpl_createStreamSocketImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject thisObjFD,
                jboolean preferIPv4Stack));
  JNIEXPORT jint JNICALL Java_java_net_SocketImpl_sendStreamImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jbyteArray data, jint offset, jint count));
  JNIEXPORT void JNICALL Java_java_net_SocketImpl_shutdownOutputImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor));
  JNIEXPORT void JNICALL Java_java_net_SocketImpl_createDatagramSocketImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject thisObjFD,
                jboolean preferIPv4Stack));
  JNIEXPORT jint JNICALL Java_java_net_SocketImpl_availableStreamImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor));
  JNIEXPORT jboolean JNICALL Java_java_net_SocketImpl_supportsUrgentDataImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor));
  JNIEXPORT void JNICALL Java_java_net_SocketImpl_shutdownInputImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor));
  JNIEXPORT void JNICALL Java_java_net_SocketImpl_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass clazz, jboolean jcl_supports_ipv6));
    
  /* NativesCommonFile*/
  JNIEXPORT jboolean JNICALL Java_java_io_File_mkdirImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jboolean JNICALL Java_java_io_File_setLastModifiedImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path, jlong time));
  JNIEXPORT jboolean JNICALL Java_java_io_File_isDirectoryImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jboolean JNICALL Java_java_io_File_isReadOnlyImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jlong JNICALL Java_java_io_File_lastModifiedImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jboolean JNICALL Java_java_io_File_renameToImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray pathExist,
                jbyteArray pathNew));
  JNIEXPORT jobject JNICALL Java_java_io_File_rootsImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  JNIEXPORT jboolean JNICALL Java_java_io_File_deleteDirImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jboolean JNICALL Java_java_io_File_isCaseSensitiveImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  JNIEXPORT jlong JNICALL Java_java_io_File_lengthImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jboolean JNICALL Java_java_io_File_isAbsoluteImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jboolean JNICALL Java_java_io_File_isWriteOnlyImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jboolean JNICALL Java_java_io_File_isFileImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jint JNICALL Java_java_io_File_newFileImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jboolean JNICALL Java_java_io_File_deleteFileImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jobject JNICALL Java_java_io_File_getCanonImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jobject JNICALL Java_java_io_File_listImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jboolean JNICALL Java_java_io_File_isHiddenImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jobject JNICALL Java_java_io_File_getLinkImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jbyteArray JNICALL Java_java_io_File_properPathImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT void JNICALL Java_java_io_File_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  JNIEXPORT jboolean JNICALL Java_java_io_File_existsImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
  JNIEXPORT jboolean JNICALL Java_java_io_File_setReadOnlyImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
    
  /* NativesCommonFileInputStream*/
  JNIEXPORT jint JNICALL Java_java_io_FileInputStream_readByteImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong descriptor));
  JNIEXPORT jint JNICALL Java_java_io_FileInputStream_readImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buffer, jint offset,
                jint count, jlong descriptor));
  JNIEXPORT void JNICALL Java_java_io_FileInputStream_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  JNIEXPORT void JNICALL Java_java_io_FileInputStream_closeImpl
    PROTOTYPE ((JNIEnv * env, jobject recv));
  JNIEXPORT jlong JNICALL Java_java_io_FileInputStream_skip
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong count));
  JNIEXPORT jint JNICALL Java_java_io_FileInputStream_available
    PROTOTYPE ((JNIEnv * env, jobject recv));
  JNIEXPORT jint JNICALL Java_java_io_FileInputStream_openImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path));
    
  /* NativesCommonObjectInputStream*/
  JNIEXPORT void JNICALL
    Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2I
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName, jint newValue));
  JNIEXPORT void JNICALL Java_java_io_ObjectInputStream_objSetField
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName,
                jobject fieldTypeName, jobject newValue));
  JNIEXPORT void JNICALL
    Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2C
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName, jchar newValue));
  JNIEXPORT void JNICALL
    Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2D
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName, jdouble newValue));
  JNIEXPORT void JNICALL
    Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2F
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName, jfloat newValue));
  JNIEXPORT void JNICALL
    Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2B
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName, jbyte newValue));
  JNIEXPORT jobject JNICALL Java_java_io_ObjectInputStream_newInstance
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject instantiationClass,
                jobject constructorClass));
  JNIEXPORT void JNICALL
    Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2S
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName, jshort newValue));
  JNIEXPORT void JNICALL
    Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2J
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName, jlong newValue));
  JNIEXPORT void JNICALL
    Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2Z
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName,
                jboolean newValue));
    
  /* NativesCommonAccessController*/
  JNIEXPORT jboolean JNICALL Java_java_security_AccessController_initializeInternal
    PROTOTYPE ((JNIEnv * env, jclass thisClz));
    
  /* NativesCommonNetworkInterface*/
  JNIEXPORT jobjectArray JNICALL Java_java_net_NetworkInterface_getNetworkInterfacesImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz));
    
  /* NativesCommonObjectStreamClass*/
  JNIEXPORT jboolean JNICALL Java_java_io_ObjectStreamClass_hasClinit
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetClass));
  JNIEXPORT jobject JNICALL Java_java_io_ObjectStreamClass_getFieldSignature
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject reflectField));
  JNIEXPORT jobject JNICALL Java_java_io_ObjectStreamClass_getConstructorSignature
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject reflectConstructor));
  JNIEXPORT jobject JNICALL Java_java_io_ObjectStreamClass_getMethodSignature
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject reflectMethod));
  JNIEXPORT void JNICALL Java_java_io_ObjectStreamClass_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass clazz));
    
  /* NativesCommonInflater*/
  JNIEXPORT void JNICALL Java_java_util_zip_Inflater_endImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong handle));
  JNIEXPORT void JNICALL Java_java_util_zip_Inflater_setInputImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buf, jint off,
                jint len, jlong handle));
  JNIEXPORT jint JNICALL Java_java_util_zip_Inflater_inflateImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buf, int off, int len,
                jlong handle));
  JNIEXPORT void JNICALL Java_java_util_zip_Inflater_setDictionaryImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray dict, int off, int len,
                jlong handle));
  JNIEXPORT void JNICALL Java_java_util_zip_Inflater_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  JNIEXPORT void JNICALL Java_java_util_zip_Inflater_resetImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong handle));

  JNIEXPORT jlong JNICALL Java_java_util_zip_Inflater_getTotalOutImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong handle));
  JNIEXPORT jlong JNICALL Java_java_util_zip_Inflater_createStream
    PROTOTYPE ((JNIEnv * env, jobject recv, jboolean noHeader));
  JNIEXPORT jlong JNICALL Java_java_util_zip_Inflater_getTotalInImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong handle));
  JNIEXPORT jint JNICALL Java_java_util_zip_Inflater_getAdlerImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong handle));
    
  /* NativesCommonSystem*/
  JNIEXPORT void JNICALL Java_java_lang_System_setFieldImpl
    PROTOTYPE ((JNIEnv * env, jclass cls, jstring name, jobject stream));
  jobject createSystemPropertyList
    PROTOTYPE ((JNIEnv * env, const char *defaultValues[], int defaultCount));
  JNIEXPORT jstring JNICALL Java_java_lang_System_getCommPortList
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  JNIEXPORT jstring JNICALL Java_java_lang_System_getEncoding
    PROTOTYPE ((JNIEnv * env, jclass clazz, jint encodingType));
  JNIEXPORT jobject JNICALL Java_java_lang_System_getPropertyList
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  JNIEXPORT jstring JNICALL Java_java_lang_SystemProperties_getEncoding
    PROTOTYPE ((JNIEnv * env, jclass clazz, jint encodingType));
  JNIEXPORT jstring JNICALL Java_java_lang_System_mapLibraryName
    PROTOTYPE ((JNIEnv * env, jclass unusedClass, jstring inName));
  JNIEXPORT void JNICALL Java_java_lang_System_initLocale
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  JNIEXPORT jobject JNICALL Java_java_lang_SystemProperties_getPropertyList
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  char *readCodepageMappings
    PROTOTYPE ((JNIEnv * env, char *codepage, char *codepageResult,
                int resultSize));
                
  /* NativesCommonProxy*/
  JNIEXPORT jclass JNICALL Java_java_lang_reflect_Proxy_defineClassImpl
    PROTOTYPE ((JNIEnv * env, jclass recvClass, jobject classLoader,
                jstring className, jbyteArray classBytes));
  JNIEXPORT jclass JNICALL
    Java_java_lang_reflect_Proxy_defineClass0__Ljava_lang_ClassLoader_2Ljava_lang_String_2_3BIILjava_lang_Object_2_3Ljava_lang_Object_2Ljava_lang_Object_2
    PROTOTYPE ((JNIEnv * env, jclass recvClass, jobject classLoader,
                jstring className, jbyteArray classBytes, jint offset,
                jint length, jobject pd, jobject signers, jobject source));
  JNIEXPORT jclass JNICALL
    Java_java_lang_reflect_Proxy_defineClass0__Ljava_lang_ClassLoader_2Ljava_lang_String_2_3BII
    PROTOTYPE ((JNIEnv * env, jclass recvClass, jobject classLoader,
                jstring className, jbyteArray classBytes, jint offset,
                jint length));
                
  /* NativesCommonGlobals*/
  JNIEXPORT void JNICALL JNI_OnUnload PROTOTYPE ((JavaVM * vm, void *reserved));
  JNIEXPORT jint JNICALL JCL_OnLoad PROTOTYPE ((JavaVM * vm, void *reserved));
  
  /* NativesCommonRuntime*/
  JNIEXPORT jlong JNICALL Java_java_lang_Runtime_maxMemoryImpl
    PROTOTYPE ((JNIEnv * env, jclass cls));
  JNIEXPORT jint JNICALL Java_java_lang_Runtime_availableProcessorsImpl
    PROTOTYPE ((JNIEnv * env, jclass cls));
    
  /* NativesCommonJarFile*/
  JNIEXPORT jarray JNICALL Java_java_util_jar_JarFile_getMetaEntriesImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray zipName));
    
  /* NativesCommonRandomAccessFile*/
  JNIEXPORT jint JNICALL Java_java_io_RandomAccessFile_readImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buffer, jint offset,
                jint count, jlong descriptor));
  JNIEXPORT void JNICALL Java_java_io_RandomAccessFile_seek
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong pos));
  JNIEXPORT void JNICALL Java_java_io_RandomAccessFile_closeImpl
    PROTOTYPE ((JNIEnv * env, jobject recv));
  JNIEXPORT jlong JNICALL Java_java_io_RandomAccessFile_length
    PROTOTYPE ((JNIEnv * env, jobject recv));
  JNIEXPORT jlong JNICALL Java_java_io_RandomAccessFile_getFilePointer
    PROTOTYPE ((JNIEnv * env, jobject recv));
  JNIEXPORT void JNICALL Java_java_io_RandomAccessFile_setLengthImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong newLength));
  JNIEXPORT void JNICALL Java_java_io_RandomAccessFile_writeImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buffer, jint offset,
                jint count, jlong descriptor));
  JNIEXPORT jint JNICALL Java_java_io_RandomAccessFile_readByteImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong descriptor));
  JNIEXPORT void JNICALL Java_java_io_RandomAccessFile_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass rafClazz));
  JNIEXPORT jint JNICALL Java_java_io_RandomAccessFile_openImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray path,
                jboolean writable));
  JNIEXPORT void JNICALL Java_java_io_RandomAccessFile_writeByteImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jint c, jlong descriptor));
    
  /* NativesCommonObjectOutputStream*/
  JNIEXPORT jfloat JNICALL Java_java_io_ObjectOutputStream_getFieldFloat
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName));
  JNIEXPORT jobject JNICALL Java_java_io_ObjectOutputStream_getFieldObj
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName,
                jobject fieldTypeName));
  JNIEXPORT jshort JNICALL Java_java_io_ObjectOutputStream_getFieldShort
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName));
  JNIEXPORT jbyte JNICALL Java_java_io_ObjectOutputStream_getFieldByte
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName));
  JNIEXPORT jlong JNICALL Java_java_io_ObjectOutputStream_getFieldLong
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName));
  JNIEXPORT jdouble JNICALL Java_java_io_ObjectOutputStream_getFieldDouble
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName));
  JNIEXPORT jboolean JNICALL Java_java_io_ObjectOutputStream_getFieldBool
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName));
  JNIEXPORT jint JNICALL Java_java_io_ObjectOutputStream_getFieldInt
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName));
  JNIEXPORT jchar JNICALL Java_java_io_ObjectOutputStream_getFieldChar
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject targetObject,
                jobject declaringClass, jobject fieldName));
                
  /* NativesCommonSocket*/
  void createSocket
    PROTOTYPE ((JNIEnv * env, jobject thisObjFD, int sockType,
                jboolean preferIPv4Stack));
  JNIEXPORT void JNICALL Java_java_net_Socket_socketCloseImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor));
  JNIEXPORT void JNICALL Java_java_net_Socket_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass clazz, jboolean jcl_supports_ipv6));
  JNIEXPORT jobject JNICALL Java_java_net_Socket_getSocketLocalAddressImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jboolean preferIPv6Addresses));
  JNIEXPORT jobject JNICALL Java_java_net_Socket_getSocketOptionImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject aFileDescriptor,
                jint anOption));
  JNIEXPORT void JNICALL Java_java_net_Socket_setSocketOptionImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject aFileDescriptor,
                jint anOption, jobject aValue));
  JNIEXPORT jint JNICALL Java_java_net_Socket_getSocketFlags
    PROTOTYPE ((JNIEnv * env, jclass thisClz));
  JNIEXPORT jint JNICALL Java_java_net_Socket_getSocketLocalPortImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jboolean preferIPv6Addresses));
  I_32 pollSelectRead
    PROTOTYPE ((JNIEnv * env, jobject fileDescriptor, jint timeout,
                BOOLEAN poll));
                
  /* NativesCommonPlainMulticastSocketImpl*/
  JNIEXPORT void JNICALL
    Java_java_net_PlainMulticastSocketImpl_createMulticastSocketImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject thisObjFD,
                jboolean preferIPv4Stack));
                
  /* NativesCommonZipFile*/
  void throwJavaZIOException PROTOTYPE ((JNIEnv * env, const char *message));
  void throwNewInternalError PROTOTYPE ((JNIEnv * env, const char *message));
  JNIEXPORT void JNICALL Java_java_util_zip_ZipFile_closeZipImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong descriptor));
  JNIEXPORT jobject JNICALL Java_java_util_zip_ZipFile_00024ZFEnum_getNextEntry
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong descriptor,
                jlong nextEntry));
  JNIEXPORT void JNICALL Java_java_util_zip_ZipFile_ntvinit
    PROTOTYPE ((JNIEnv * env, jclass cls));
  void throwNewIllegalStateException
    PROTOTYPE ((JNIEnv * env, const char *message));
  JNIEXPORT jlong JNICALL Java_java_util_zip_ZipFile_00024ZFEnum_resetZip
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong descriptor));
  JNIEXPORT jint JNICALL Java_java_util_zip_ZipFile_openZipImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray zipName));
  JNIEXPORT jobject JNICALL Java_java_util_zip_ZipFile_getEntryImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong zipPointer,
                jstring entryName));
  JNIEXPORT jbyteArray JNICALL Java_java_util_zip_ZipFile_inflateEntryImpl2
    PROTOTYPE ((JNIEnv * env, jobject recv, jlong zipPointer,
                jstring entryName));
  void throwNewIllegalArgumentException
    PROTOTYPE ((JNIEnv * env, const char *message));
    
  /* NativesCommonInetAddress*/
  JNIEXPORT void JNICALL Java_java_net_InetAddress_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass clazz, jboolean ipv6_support));
  JNIEXPORT jint JNICALL Java_java_net_InetAddress_inetAddrImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz, jstring host));
  JNIEXPORT jstring JNICALL Java_java_net_InetAddress_getHostNameImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  JNIEXPORT jobjectArray JNICALL Java_java_net_InetAddress_getAliasesByNameImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz, jstring aName));
  JNIEXPORT jstring JNICALL Java_java_net_InetAddress_inetNtoaImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz, jint hipAddr));
  JNIEXPORT jobject JNICALL Java_java_net_InetAddress_getHostByAddrImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz, jbyteArray addr));
  JNIEXPORT jobject JNICALL Java_java_net_InetAddress_getHostByNameImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz, jstring aName,
                jboolean preferIPv6Addresses));
  JNIEXPORT jobjectArray JNICALL Java_java_net_InetAddress_getAliasesByAddrImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz, jstring addr));
    
  /* NativesCommonTimeZone*/
  JNIEXPORT jstring JNICALL Java_java_util_TimeZone_getCustomTimeZone
    PROTOTYPE ((JNIEnv * env, jclass clazz, jintArray tzinfo,
                jbooleanArray isCustomTimeZone));
                
  /* NativesCommonFloatParsing*/
  JNIEXPORT jfloat JNICALL
    Java_org_apache_harmony_luni_util_FloatingPointParser_parseFltImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz, jstring s, jint e));
    
  /* NativesCommonNetHelpers*/
  void throwJavaNetBindException PROTOTYPE ((JNIEnv * env, I_32 errorNumber));
  jobject newJavaNetInetAddressGenericBS
    PROTOTYPE ((JNIEnv * env, jbyte * address, U_32 length, char *hostName,
                U_32 scope_id));
  void throwJavaNetUnknownHostException
    PROTOTYPE ((JNIEnv * env, I_32 errorNumber));
  jobject newJavaNetInetAddressGenericB
    PROTOTYPE ((JNIEnv * env, jbyte * address, U_32 length, U_32 scope_id));
  jobject newJavaLangByte PROTOTYPE ((JNIEnv * env, U_8 aByte));
  U_8 byteValue PROTOTYPE ((JNIEnv * env, jobject aByte));
  I_32 intValue PROTOTYPE ((JNIEnv * env, jobject anInteger));
  void throwJavaNetPortUnreachableException
    PROTOTYPE ((JNIEnv * env, I_32 errorNumber));
  jobject newJavaByteArray
    PROTOTYPE ((JNIEnv * env, jbyte * bytes, jint length));
  jobjectArray createAliasArrayFromAddrinfo
    PROTOTYPE ((JNIEnv * env, hyaddrinfo_t addresses, char *hName));
  BOOLEAN booleanValue PROTOTYPE ((JNIEnv * env, jobject aBoolean));
  BOOLEAN jcl_supports_ipv6 PROTOTYPE ((JNIEnv * env));
  jobject newJavaLangInteger PROTOTYPE ((JNIEnv * env, I_32 anInt));
  BOOLEAN preferIPv4Stack PROTOTYPE ((JNIEnv * env));
  char *netLookupErrorString PROTOTYPE ((JNIEnv * env, I_32 anErrorNum));
  void netInitializeIDCaches
    PROTOTYPE ((JNIEnv * env, jboolean ipv6_support));
  jobject newJavaLangBoolean PROTOTYPE ((JNIEnv * env, BOOLEAN aBool));
  void throwJavaLangIllegalArgumentException
    PROTOTYPE ((JNIEnv * env, I_32 errorNumber));
  void netGetJavaNetInetAddressValue
    PROTOTYPE ((JNIEnv * env, jobject anInetAddress, U_8 * buffer,
                U_32 * length));
  void throwJavaIoInterruptedIOException
    PROTOTYPE ((JNIEnv * env, I_32 errorNumber));
  void throwJavaNetSocketTimeoutException
    PROTOTYPE ((JNIEnv * env, I_32 errorNumber));
  void callThreadYield PROTOTYPE ((JNIEnv * env));
  void throwJavaNetConnectException
    PROTOTYPE ((JNIEnv * env, I_32 errorNumber));
  void netGetJavaNetInetAddressScopeId
    PROTOTYPE ((JNIEnv * env, jobject anInetAddress, U_32 * scope_id));
  BOOLEAN preferIPv6Addresses PROTOTYPE ((JNIEnv * env));
  jobjectArray createAliasArray
    PROTOTYPE ((JNIEnv * env, jbyte ** addresses, I_32 * family, U_32 count,
                char *hName, U_32 * scope_id_array));
  void throwJavaNetSocketException
    PROTOTYPE ((JNIEnv * env, I_32 errorNumber));
  I_32 netGetSockAddr
    PROTOTYPE ((JNIEnv * env, jobject fileDescriptor, hysockaddr_t sockaddrP,
                jboolean preferIPv6Addresses));
                
  /* NativesCommonIoHelpers*/
  void throwNewExceptionByName PROTOTYPE((JNIEnv* env,
                                          const char* name,
                                          const char* message));
  void throwNewOutOfMemoryError PROTOTYPE ((JNIEnv* env, const char* message));
  void throwJavaIoIOException PROTOTYPE ((JNIEnv* env, const char* message));
  void throwJavaIoIOExceptionClosed PROTOTYPE ((JNIEnv* env));
  void ioh_convertToPlatform PROTOTYPE ((char *path));
  void throwNPException PROTOTYPE ((JNIEnv* env, const char* message));
  void ioh_writebytesImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buffer, jint offset,
                jint count, IDATA descriptor));
  char *ioLookupErrorString PROTOTYPE ((JNIEnv * env, I_32 anErrorNum));
  jint ioh_readbytesImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buffer, jint offset,
                jint count, IDATA descriptor));
  void new_ioh_close PROTOTYPE ((JNIEnv * env, jobject recv, jfieldID fdFID));
  void throwIndexOutOfBoundsException PROTOTYPE ((JNIEnv* env));

  /* NativesCommonSocket*/
  void throwSocketException PROTOTYPE ((JNIEnv * env, I_32 errorNumber));
  I_32 conPollSelectRead
    PROTOTYPE ((JNIEnv * env, jobject socket, jint timeout, BOOLEAN poll,
                BOOLEAN accept));
  void *getSocketDescriptor PROTOTYPE ((JNIEnv * env, jobject fd));
  void setSocketDescriptor
    PROTOTYPE ((JNIEnv * env, jobject fd, void *value));
  JNIEXPORT jstring JNICALL Java_java_lang_System_getHostnameImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  void conUpdateSocket
    PROTOTYPE ((JNIEnv * env, hysockaddr_t sockaddrP, jobject socket,
                int remote));
  hysocket_t createAndBindSocket
    PROTOTYPE ((JNIEnv * env, jobject socket, int sockType, int localPort));
                
  /* NativesCommonFileDescriptor*/
  JNIEXPORT void JNICALL Java_java_io_FileDescriptor_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass fdClazz));
  JNIEXPORT void JNICALL Java_java_io_FileDescriptor_sync
    PROTOTYPE ((JNIEnv * env, jobject recv));
  JNIEXPORT jboolean JNICALL Java_java_io_FileDescriptor_valid
    PROTOTYPE ((JNIEnv * env, jobject recv));
    
  /* NativesCommonProcess*/
  JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_internal_process_ProcessInputStream_availableImpl
    PROTOTYPE ((JNIEnv * env, jobject recv));
  JNIEXPORT void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessInputStream_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  JNIEXPORT void JNICALL Java_org_apache_harmony_luni_internal_process_SystemProcess_destroyImpl
    PROTOTYPE ((JNIEnv * env, jobject recv));
  JNIEXPORT void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessOutputStream_writeImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buffer, jint offset,
                jint nbytes, jlong handle));
  JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_internal_process_SystemProcess_waitForCompletionImpl
    PROTOTYPE ((JNIEnv * env, jobject recv));
  JNIEXPORT void JNICALL Java_org_apache_harmony_luni_internal_process_SystemProcess_closeImpl
    PROTOTYPE ((JNIEnv * env, jobject recv));
  JNIEXPORT void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessOutputStream_closeImpl
    PROTOTYPE ((JNIEnv * env, jobject recv));
  JNIEXPORT void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessOutputStream_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass clazz));
  JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_internal_process_ProcessInputStream_readImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jbyteArray buffer, jint offset,
                jint nbytes, jlong handle));
  JNIEXPORT void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessOutputStream_setFDImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jobject arg1, jlong arg2));
  JNIEXPORT void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessInputStream_closeImpl
    PROTOTYPE ((JNIEnv * env, jobject recv));
  JNIEXPORT jlongArray JNICALL Java_org_apache_harmony_luni_internal_process_SystemProcess_createImpl
    PROTOTYPE ((JNIEnv * env, jclass clazz, jobject recv, jobjectArray arg1,
                jobjectArray arg2, jbyteArray dir));
  JNIEXPORT void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessInputStream_setFDImpl
    PROTOTYPE ((JNIEnv * env, jobject recv, jobject arg1, jlong arg2));
  JNIEXPORT void JNICALL Java_org_apache_harmony_luni_internal_process_SystemProcess_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass clazz));
    
  /* NativesCommonPlainDatagramSocketImpl*/
  JNIEXPORT jint JNICALL Java_java_net_PlainDatagramSocketImpl_sendDatagramImpl2
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jbyteArray data, jint offset, jint msgLength, jint targetPort,
                jboolean bindToDevice, jint trafficClass,
                jobject inetAddress));
  JNIEXPORT void JNICALL Java_java_net_PlainDatagramSocketImpl_createDatagramSocketImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject thisObjFD,
                jboolean preferIPv4Stack));
  JNIEXPORT jint JNICALL Java_java_net_PlainDatagramSocketImpl_peekDatagramImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jobject senderAddress, jint timeout));
  JNIEXPORT jint JNICALL Java_java_net_PlainDatagramSocketImpl_sendConnectedDatagramImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jbyteArray data, jint offset, jint msgLength,
                jboolean bindToDevice));
  JNIEXPORT void JNICALL Java_java_net_PlainDatagramSocketImpl_oneTimeInitialization
    PROTOTYPE ((JNIEnv * env, jclass clazz, jboolean ipv6support));
  JNIEXPORT void JNICALL Java_java_net_PlainDatagramSocketImpl_connectDatagramImpl2
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jint remotePort, jint trafficClass, jobject inetAddress));
  JNIEXPORT jint JNICALL Java_java_net_PlainDatagramSocketImpl_receiveDatagramImpl2
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jobject datagramPacket, jbyteArray data, jint offset,
                jint msgLength, jint timeout, jboolean peek));
  JNIEXPORT jint JNICALL Java_java_net_PlainDatagramSocketImpl_receiveDatagramImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jobject datagramPacket, jbyteArray data, jint offset,
                jint msgLength, jint timeout));
  JNIEXPORT jboolean JNICALL Java_java_net_PlainDatagramSocketImpl_socketBindImpl2
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jint localPort, jboolean doDevice, jobject inetAddress));
  JNIEXPORT void JNICALL Java_java_net_PlainDatagramSocketImpl_disconnectDatagramImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor));
  JNIEXPORT jint JNICALL Java_java_net_PlainDatagramSocketImpl_recvConnectedDatagramImpl
    PROTOTYPE ((JNIEnv * env, jclass thisClz, jobject fileDescriptor,
                jobject datagramPacket, jbyteArray data, jint offset,
                jint msgLength, jint timeout, jboolean peek));
    
  /************************************************************
  ** COMPONENT: NativesWin32
  ************************************************************/

  /* NativesWin32Helpers*/
  int platformReadLink PROTOTYPE ((char *link));
  jbyteArray getPlatformPath PROTOTYPE ((JNIEnv * env, jbyteArray path));
  void setDefaultServerSocketOptions
    PROTOTYPE ((JNIEnv * env, hysocket_t socketP));
  jint getPlatformDatagramNominalSize
    PROTOTYPE ((JNIEnv * env, hysocket_t socketP));
  I_32 getPlatformRoots PROTOTYPE ((char *rootStrings));
  jstring getCustomTimeZoneInfo
    PROTOTYPE ((JNIEnv * env, jintArray tzinfo,
                jbooleanArray isCustomTimeZone));
  I_32 getPlatformIsHidden PROTOTYPE ((JNIEnv * env, char *path));
  jint getPlatformDatagramMaxSize
    PROTOTYPE ((JNIEnv * env, hysocket_t socketP));
  char *getCommports PROTOTYPE ((JNIEnv * env));
  I_32 getPlatformIsWriteOnly PROTOTYPE ((JNIEnv * env, char *path));
  I_32 setPlatformFileLength
    PROTOTYPE ((JNIEnv * env, IDATA descriptor, jlong newLength));
  void platformCanonicalPath PROTOTYPE ((char *pathCopy));
  I_32 getPlatformIsReadOnly PROTOTYPE ((JNIEnv * env, char *path));
  void setPlatformBindOptions PROTOTYPE ((JNIEnv * env, hysocket_t socketP));
  I_32 setPlatformLastModified
    PROTOTYPE ((JNIEnv * env, char *path, I_64 time));
  I_32 setPlatformReadOnly PROTOTYPE ((JNIEnv * env, char *path));

  /* NativesWin32Procimpl*/
  int execProgram PROTOTYPE ((JNIEnv * vmthread, jobject recv,
                              char *command[], int commandLength,
                              char *env[], int envSize, char *dir,
                              IDATA * procHandle, IDATA * inHandle,
                              IDATA * outHandle, IDATA * errHandle));
  int closeProc PROTOTYPE ((IDATA procHandle));
  int waitForProc PROTOTYPE ((IDATA procHandle));
  int getAvailable PROTOTYPE ((IDATA sHandle));
  int termProc PROTOTYPE ((IDATA procHandle));

  /* NativesWin32Comm*/
  jint readSerialPort
    PROTOTYPE ((JNIEnv * env, char *message, jint messageLength,
                jint osHandle_, char *buffer, jint offset, jint length));
  jint openSerialPort PROTOTYPE ((JNIEnv * env, char *portNumber, int len));
  void closeSerialPort PROTOTYPE ((JNIEnv * env, jint osHandle));
  jint setBaud PROTOTYPE ((JNIEnv * env, jint osHandle_, jint baudRate));
  jint getBaud PROTOTYPE ((JNIEnv * env, jint osHandle_));
  jint writeSerialPort
    PROTOTYPE ((JNIEnv * env, char *message, jint messageLength,
                jint osHandle_, char *buffer, jint offset, jint length));
  jint openSerialPortByName
    PROTOTYPE ((JNIEnv * env, jstring portNameString));
  jint availableSerialPort PROTOTYPE ((JNIEnv * env, jint osHandle));
  void configureSerialPort
    PROTOTYPE ((JNIEnv * env, jint osHandle_, jint baudRate, jint bitsPerChar,
                jint stopBits, jint parity, jboolean autoRTS,
                jboolean autoCTS, jint timeout));

  /* NativesWin32SystemHelpers*/
  char *getPlatformFileEncoding
    PROTOTYPE ((JNIEnv * env, char *codepage, int size));
  char *getTmpDir PROTOTYPE ((JNIEnv * env, char **tempdir));
  jobject getPlatformPropertyList
    PROTOTYPE ((JNIEnv * env, const char *strings[], int propIndex));
  void mapLibraryToPlatformName
    PROTOTYPE ((const char *inPath, char *outPath));

  /************************************************************
  ** COMPONENT: harmonyNativesCommon
  ************************************************************/
  /* NativesCommonFloatAndDouble*/
  JNIEXPORT jlong JNICALL Java_java_lang_Double_doubleToLongBits
    PROTOTYPE ((JNIEnv * env, jclass cls, jdouble doubleValue));
  JNIEXPORT jint JNICALL Java_java_lang_Float_floatToIntBits
    PROTOTYPE ((JNIEnv * env, jclass cls, jfloat floatValue));
  JNIEXPORT jint JNICALL Java_java_lang_Float_floatToRawIntBits
    PROTOTYPE ((JNIEnv * env, jclass cls, jfloat floatValue));
  JNIEXPORT jdouble JNICALL Java_java_lang_Double_longBitsToDouble
    PROTOTYPE ((JNIEnv * env, jclass cls, jlong longValue));
  JNIEXPORT jlong JNICALL Java_java_lang_Double_doubleToRawLongBits
    PROTOTYPE ((JNIEnv * env, jclass cls, jdouble doubleValue));
  JNIEXPORT jfloat JNICALL Java_java_lang_Float_intBitsToFloat
    PROTOTYPE ((JNIEnv * env, jclass cls, jint intValue));

  /* harmonyNativesCommonMath*/
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_asin
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_rint
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_atan
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_exp
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_tan
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_rint
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_ceil
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_IEEEremainder
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1, jdouble arg2));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_floor
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_sin
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_log
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_acos
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_ceil
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_sqrt
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_pow
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1, jdouble arg2));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_exp
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_acos
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_floor
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_cos
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_cos
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_sqrt
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_tan
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_sin
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_pow
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1, jdouble arg2));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_asin
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_atan2
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1, jdouble arg2));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_IEEEremainder
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1, jdouble arg2));
  JNIEXPORT jdouble JNICALL Java_java_lang_StrictMath_log
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_atan
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1));
  JNIEXPORT jdouble JNICALL Java_java_lang_Math_atan2
    PROTOTYPE ((JNIEnv * env, jclass jclazz, jdouble arg1, jdouble arg2));

  /* harmonyNativesLUNIGlobals*/
  JNIEXPORT jint JNICALL JNI_OnLoad PROTOTYPE ((JavaVM * vm, void *reserved));
  JNIEXPORT void JNICALL JNI_OnUnload PROTOTYPE ((JavaVM * vm, void *reserved));

  /* harmonyNativesLibGlobals*/
  jint JNICALL ClearLibAttach PROTOTYPE ((JNIEnv * env));
  void JNICALL ClearLibDetach PROTOTYPE ((JNIEnv * env));

  /* harmonyNativesMathGlobals*/
  JNIEXPORT jint JNICALL JNI_OnLoad PROTOTYPE ((JavaVM * vm, void *reserved));
  JNIEXPORT void JNICALL JNI_OnUnload PROTOTYPE ((JavaVM * vm, void *reserved));

  /* harmonyNativesArchiveGlobals*/
  JNIEXPORT jint JNICALL JNI_OnLoad PROTOTYPE ((JavaVM * vm, void *reserved));
  JNIEXPORT void JNICALL JNI_OnUnload PROTOTYPE ((JavaVM * vm, void *reserved));

#if defined(__cplusplus)
}
#endif

#endif /* JCLPROTS_H */
