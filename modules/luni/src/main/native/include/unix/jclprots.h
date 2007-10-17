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
extern "C" {
#endif

#include "vmi.h"

/************************************************************
 ** COMPONENT: NativesCommon
 ************************************************************/

/* NativesCommonDeflater*/
void JNICALL Java_java_util_zip_Deflater_setDictionaryImpl PROTOTYPE(( JNIEnv * env, jobject recv,  jbyteArray dict, int off, int len, jlong handle));
void JNICALL Java_java_util_zip_Deflater_resetImpl PROTOTYPE(( JNIEnv * env, jobject recv,  jlong handle));
jlong JNICALL Java_java_util_zip_Deflater_getTotalOutImpl PROTOTYPE(( JNIEnv * env, jobject recv, jlong handle));
void JNICALL Java_java_util_zip_Deflater_endImpl PROTOTYPE(( JNIEnv * env, jobject recv, jlong handle));
jint JNICALL Java_java_util_zip_Deflater_deflateImpl PROTOTYPE((JNIEnv * env, jobject recv, jbyteArray buf, int off, int len,
													 jlong handle, int flushParm));
void JNICALL Java_java_util_zip_Deflater_setLevelsImpl PROTOTYPE(( JNIEnv * env, jobject recv, int level, int strategy, jlong handle));
void JNICALL Java_java_util_zip_Deflater_oneTimeInitialization PROTOTYPE((JNIEnv * env, jclass clazz));
void JNICALL Java_java_util_zip_Deflater_setInputImpl PROTOTYPE(( JNIEnv * env, jobject recv, jbyteArray buf, jint off, jint len, jlong handle));
jlong JNICALL Java_java_util_zip_Deflater_createStream PROTOTYPE(( JNIEnv * env, jobject recv, jint level, jint strategy, jboolean noHeader));
jlong JNICALL Java_java_util_zip_Deflater_getTotalInImpl PROTOTYPE(( JNIEnv * env, jobject recv, jlong handle));
jint JNICALL Java_java_util_zip_Deflater_getAdlerImpl PROTOTYPE(( JNIEnv * env, jobject recv, jlong handle));

/* NativesCommonDoubleParsing*/
JNIEXPORT jdouble JNICALL Java_org_apache_harmony_luni_util_FloatingPointParser_parseDblImpl
   PROTOTYPE((JNIEnv *env, jclass clazz, jstring s, jint e));
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_util_NumberConverter_bigIntDigitGeneratorInstImpl
    PROTOTYPE((JNIEnv *env, jobject inst, jlong f, jint e, jboolean isDenormalized, jboolean mantissaIsZero, jint p));

/* NativesCommonAdler32*/
jlong JNICALL Java_java_util_zip_Adler32_updateImpl PROTOTYPE(( JNIEnv * env, jobject recv, jbyteArray buf, int off, int len, jlong crc));
jlong JNICALL Java_java_util_zip_Adler32_updateByteImpl PROTOTYPE(( JNIEnv * env, jobject recv, jint val, jlong crc));

/* NativesCommonCRC32*/
jlong JNICALL Java_java_util_zip_CRC32_updateByteImpl PROTOTYPE(( JNIEnv * env, jobject recv, jbyte val, jlong crc));
jlong JNICALL Java_java_util_zip_CRC32_updateImpl PROTOTYPE(( JNIEnv * env, jobject recv, jbyteArray buf, int off, int len, jlong crc));

/* NativesCommonFile*/
jboolean JNICALL Java_java_io_File_mkdirImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jboolean JNICALL Java_java_io_File_setLastModifiedImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path, jlong time));
jboolean JNICALL Java_java_io_File_isDirectoryImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jboolean JNICALL Java_java_io_File_isReadOnlyImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jlong JNICALL Java_java_io_File_lastModifiedImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jboolean JNICALL Java_java_io_File_renameToImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray pathExist, jbyteArray pathNew));
jobject JNICALL Java_java_io_File_rootsImpl PROTOTYPE((JNIEnv *env, jclass clazz));
jboolean JNICALL Java_java_io_File_deleteDirImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jboolean JNICALL Java_java_io_File_isCaseSensitiveImpl PROTOTYPE((JNIEnv *env, jclass clazz));
jlong JNICALL Java_java_io_File_lengthImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jboolean JNICALL Java_java_io_File_isAbsoluteImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jboolean JNICALL Java_java_io_File_isWriteOnlyImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jboolean JNICALL Java_java_io_File_isFileImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jint JNICALL Java_java_io_File_newFileImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jboolean JNICALL Java_java_io_File_deleteFileImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jobject JNICALL Java_java_io_File_getCanonImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jobject JNICALL Java_java_io_File_listImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jboolean JNICALL Java_java_io_File_isHiddenImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jobject JNICALL Java_java_io_File_getLinkImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jbyteArray JNICALL Java_java_io_File_properPathImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
void JNICALL Java_java_io_File_oneTimeInitialization PROTOTYPE((JNIEnv * env, jclass clazz));
jboolean JNICALL Java_java_io_File_existsImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));
jboolean JNICALL Java_java_io_File_setReadOnlyImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray path));

/* NativesCommonObjectInputStream*/
void JNICALL Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2I PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName, jint newValue));
void JNICALL Java_java_io_ObjectInputStream_objSetField PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName, jobject fieldTypeName, jobject newValue));
void JNICALL Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2C PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName, jchar newValue));
void JNICALL Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2D PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName, jdouble newValue));
void JNICALL Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2F PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName, jfloat newValue));
void JNICALL Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2B PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName, jbyte newValue));
jobject JNICALL Java_java_io_ObjectInputStream_newInstance PROTOTYPE((JNIEnv *env, jclass clazz, jobject instantiationClass, jobject constructorClass));
void JNICALL Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2S PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName, jshort newValue));
void JNICALL Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2J PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName, jlong newValue));
void JNICALL Java_java_io_ObjectInputStream_setField__Ljava_lang_Object_2Ljava_lang_Class_2Ljava_lang_String_2Z PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName, jboolean newValue));

/* NativesCommonAccessController*/
jboolean JNICALL Java_java_security_AccessController_initializeInternal PROTOTYPE((JNIEnv *env, jclass thisClz));

/* NativesCommonNetworkInterface*/
jobjectArray JNICALL Java_java_net_NetworkInterface_getNetworkInterfacesImpl PROTOTYPE((JNIEnv* env, jclass clazz));

/* NativesCommonObjectStreamClass*/
jboolean JNICALL Java_java_io_ObjectStreamClass_hasClinit PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetClass));
jobject JNICALL Java_java_io_ObjectStreamClass_getFieldSignature PROTOTYPE((JNIEnv * env, jclass clazz, jobject reflectField));
jobject JNICALL Java_java_io_ObjectStreamClass_getConstructorSignature PROTOTYPE((JNIEnv * env, jclass clazz,
																	   jobject reflectConstructor));
jobject JNICALL Java_java_io_ObjectStreamClass_getMethodSignature PROTOTYPE((JNIEnv * env, jclass clazz, jobject reflectMethod));
void JNICALL Java_java_io_ObjectStreamClass_oneTimeInitialization PROTOTYPE((JNIEnv * env, jclass clazz));

/* NativesCommonInflater*/
void JNICALL Java_java_util_zip_Inflater_endImpl PROTOTYPE(( JNIEnv * env, jobject recv, jlong handle));
void JNICALL Java_java_util_zip_Inflater_setInputImpl PROTOTYPE(( JNIEnv * env, jobject recv, jbyteArray buf, jint off, jint len, jlong handle));
jint JNICALL Java_java_util_zip_Inflater_inflateImpl PROTOTYPE((JNIEnv * env, jobject recv, jbyteArray buf, int off, int len,
													 jlong handle));
void JNICALL Java_java_util_zip_Inflater_setDictionaryImpl PROTOTYPE(( JNIEnv * env, jobject recv,  jbyteArray dict, int off, int len, jlong handle));
void JNICALL Java_java_util_zip_Inflater_oneTimeInitialization PROTOTYPE((JNIEnv * env, jclass clazz));
void JNICALL Java_java_util_zip_Inflater_resetImpl PROTOTYPE(( JNIEnv * env, jobject recv, jlong handle));
jlong JNICALL Java_java_util_zip_Inflater_getTotalOutImpl PROTOTYPE(( JNIEnv * env, jobject recv, jlong handle));
jlong JNICALL Java_java_util_zip_Inflater_createStream PROTOTYPE(( JNIEnv * env, jobject recv, jboolean noHeader));
jlong JNICALL Java_java_util_zip_Inflater_getTotalInImpl PROTOTYPE(( JNIEnv * env, jobject recv, jlong handle));
jint JNICALL Java_java_util_zip_Inflater_getAdlerImpl PROTOTYPE(( JNIEnv * env, jobject recv, jlong handle));

/* NativesCommonSystem*/
void JNICALL Java_java_lang_System_setFieldImpl PROTOTYPE((JNIEnv * env, jclass cls, jstring name, jobject stream));
jobject createSystemPropertyList PROTOTYPE((JNIEnv *env, const char *defaultValues[], int defaultCount));
jstring JNICALL Java_java_lang_System_getCommPortList PROTOTYPE((JNIEnv *env, jclass clazz));
jstring JNICALL Java_java_lang_System_getEncoding PROTOTYPE((JNIEnv *env, jclass clazz, jint encodingType));
jobject JNICALL Java_java_lang_System_getPropertyList PROTOTYPE((JNIEnv *env, jclass clazz));
jstring JNICALL Java_java_lang_SystemProperties_getEncoding PROTOTYPE((JNIEnv *env, jclass clazz, jint encodingType));
jstring JNICALL Java_java_lang_System_mapLibraryName PROTOTYPE((JNIEnv * env, jclass unusedClass, jstring inName));
void JNICALL Java_java_lang_System_initLocale PROTOTYPE((JNIEnv *env, jclass clazz));
jobject JNICALL Java_java_lang_SystemProperties_getPropertyList PROTOTYPE((JNIEnv *env, jclass clazz));
char *readCodepageMappings PROTOTYPE((JNIEnv *env, char* codepage, char *codepageResult, int resultSize));

/* NativesCommonProxy*/
jclass JNICALL Java_java_lang_reflect_Proxy_defineClassImpl PROTOTYPE((JNIEnv * env, jclass recvClass, jobject classLoader, jstring className, jbyteArray classBytes));

/* NativesCommonGlobals*/
void JNICALL JNI_OnUnload PROTOTYPE((JavaVM * vm, void *reserved));
jint JNICALL JCL_OnLoad PROTOTYPE((JavaVM * vm, void *reserved));

/* NativesCommonJarFile*/
jarray JNICALL Java_java_util_jar_JarFile_getMetaEntriesImpl PROTOTYPE((JNIEnv * env, jobject recv, jbyteArray zipName));

/* NativesCommonObjectOutputStream*/
jfloat JNICALL Java_java_io_ObjectOutputStream_getFieldFloat PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName));
jobject JNICALL Java_java_io_ObjectOutputStream_getFieldObj PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName, jobject fieldTypeName));
jshort JNICALL Java_java_io_ObjectOutputStream_getFieldShort PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName));
jbyte JNICALL Java_java_io_ObjectOutputStream_getFieldByte PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName));
jlong JNICALL Java_java_io_ObjectOutputStream_getFieldLong PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName));
jdouble JNICALL Java_java_io_ObjectOutputStream_getFieldDouble PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName));
jboolean JNICALL Java_java_io_ObjectOutputStream_getFieldBool PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName));
jint JNICALL Java_java_io_ObjectOutputStream_getFieldInt PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName));
jchar JNICALL Java_java_io_ObjectOutputStream_getFieldChar PROTOTYPE((JNIEnv *env, jclass clazz, jobject targetObject, jobject declaringClass, jobject fieldName));

/* NativesCommonSocket*/
void createSocket PROTOTYPE((JNIEnv* env, jobject thisObjFD, int sockType, jboolean preferIPv4Stack));
I_32 pollSelectRead PROTOTYPE((JNIEnv * env, jobject fileDescriptor, jint timeout, BOOLEAN poll));

/* NativesCommonZipFile*/
void throwJavaZIOException PROTOTYPE((JNIEnv * env, const char *message));
void throwNewInternalError PROTOTYPE((JNIEnv * env, const char * message));
void JNICALL Java_java_util_zip_ZipFile_closeZipImpl PROTOTYPE((JNIEnv * env, jobject recv, jlong descriptor));
jobject JNICALL Java_java_util_zip_ZipFile_00024ZFEnum_getNextEntry PROTOTYPE((JNIEnv * env, jobject recv, jlong descriptor, jlong nextEntry));
void JNICALL Java_java_util_zip_ZipFile_ntvinit PROTOTYPE((JNIEnv * env, jclass cls));
void throwNewIllegalStateException PROTOTYPE((JNIEnv * env, const char * message));
jlong JNICALL Java_java_util_zip_ZipFile_00024ZFEnum_resetZip PROTOTYPE((JNIEnv * env, jobject recv, jlong descriptor));
jint JNICALL Java_java_util_zip_ZipFile_openZipImpl PROTOTYPE((JNIEnv * env, jobject recv, jbyteArray zipName));
jobject JNICALL Java_java_util_zip_ZipFile_getEntryImpl PROTOTYPE((JNIEnv * env, jobject recv, jlong zipPointer, jstring entryName));
jbyteArray JNICALL Java_java_util_zip_ZipFile_inflateEntryImpl2 PROTOTYPE((JNIEnv * env, jobject recv, jlong zipPointer, jstring entryName));
void throwNewIllegalArgumentException PROTOTYPE((JNIEnv * env, const char * message));

/* NativesCommonInetAddress*/
void JNICALL Java_java_net_InetAddress_oneTimeInitialization PROTOTYPE((JNIEnv * env, jclass clazz, jboolean ipv6_support ));
jint JNICALL Java_java_net_InetAddress_inetAddrImpl PROTOTYPE((JNIEnv* env, jclass clazz, jstring host));
jstring JNICALL Java_java_net_InetAddress_getHostNameImpl PROTOTYPE((JNIEnv* env, jclass clazz));
jobjectArray JNICALL Java_java_net_InetAddress_getAliasesByNameImpl PROTOTYPE((JNIEnv* env, jclass clazz, jstring aName));
jstring JNICALL Java_java_net_InetAddress_inetNtoaImpl PROTOTYPE((JNIEnv* env, jclass clazz, jint hipAddr));
jobject JNICALL Java_java_net_InetAddress_getHostByAddrImpl PROTOTYPE((JNIEnv* env, jclass clazz, jbyteArray addr));
jobject JNICALL Java_java_net_InetAddress_getHostByNameImpl PROTOTYPE((JNIEnv* env, jclass clazz, jstring aName, jboolean preferIPv6Addresses));

/* NativesCommonTimeZone*/
jstring JNICALL Java_java_util_TimeZone_getCustomTimeZone PROTOTYPE((JNIEnv *env, jclass clazz, jintArray tzinfo, jbooleanArray isCustomTimeZone));

/* NativesCommonFloatParsing*/
JNIEXPORT jfloat JNICALL Java_org_apache_harmony_luni_util_FloatingPointParser_parseFltImpl
   PROTOTYPE((JNIEnv *env, jclass clazz, jstring s, jint e));

/* NativesCommonNetHelpers*/
void throwJavaNetBindException PROTOTYPE((JNIEnv* env, I_32 errorNumber));
jobject newJavaNetInetAddressGenericBS PROTOTYPE((JNIEnv * env, jbyte *address, U_32 length, char *hostName, U_32 scope_id ));
void throwJavaNetUnknownHostException PROTOTYPE((JNIEnv* env, I_32 errorNumber));
jobject newJavaNetInetAddressGenericB PROTOTYPE((JNIEnv * env, jbyte *address, U_32 length, U_32 scope_id ));
jobject newJavaLangByte PROTOTYPE((JNIEnv * env, U_8 aByte));
U_8 byteValue PROTOTYPE((JNIEnv * env, jobject aByte));
I_32 intValue PROTOTYPE((JNIEnv * env, jobject anInteger));
void throwJavaNetPortUnreachableException PROTOTYPE((JNIEnv* env, I_32 errorNumber));
jobject newJavaByteArray PROTOTYPE((JNIEnv * env, jbyte *bytes, jint length));
jobjectArray createAliasArrayFromAddrinfo PROTOTYPE((JNIEnv* env, hyaddrinfo_t addresses, char* hName ));
BOOLEAN booleanValue PROTOTYPE((JNIEnv * env, jobject aBoolean));
jobject newJavaLangInteger PROTOTYPE((JNIEnv * env, I_32 anInt));
BOOLEAN preferIPv4Stack PROTOTYPE((JNIEnv * env));
char* netLookupErrorString PROTOTYPE((JNIEnv* env, I_32 anErrorNum));
void netInitializeIDCaches PROTOTYPE((JNIEnv * env, jboolean ipv6_support));
jobject newJavaLangBoolean PROTOTYPE((JNIEnv * env, BOOLEAN aBool));
void throwJavaLangIllegalArgumentException PROTOTYPE((JNIEnv* env, I_32 errorNumber));
void netGetJavaNetInetAddressValue PROTOTYPE((JNIEnv * env, jobject anInetAddress, U_8 *buffer, U_32 *length));
void throwJavaIoInterruptedIOException PROTOTYPE((JNIEnv* env, I_32 errorNumber));
void throwJavaNetSocketTimeoutException PROTOTYPE((JNIEnv* env, I_32 errorNumber));
void callThreadYield PROTOTYPE((JNIEnv * env));
void throwJavaNetConnectException PROTOTYPE((JNIEnv* env, I_32 errorNumber));
void netGetJavaNetInetAddressScopeId PROTOTYPE((JNIEnv * env, jobject anInetAddress, U_32 *scope_id));
BOOLEAN preferIPv6Addresses PROTOTYPE((JNIEnv * env));
jobjectArray createAliasArray PROTOTYPE((JNIEnv* env, jbyte **addresses, I_32 *family, U_32 count, char* hName, U_32* scope_id_array ));
void throwJavaNetSocketException PROTOTYPE((JNIEnv* env, I_32 errorNumber));
I_32 netGetSockAddr PROTOTYPE((JNIEnv *env, jobject fileDescriptor, hysockaddr_t sockaddrP, jboolean preferIPv6Addresses));

/* NativesCommonIoHelpers*/
void throwNewExceptionByName PROTOTYPE((JNIEnv* env,
                                        const char* name, const char* message));
void throwNewOutOfMemoryError PROTOTYPE((JNIEnv* env, const char* message));
void throwJavaIoIOException PROTOTYPE((JNIEnv* env, const char* message));
void throwJavaIoIOExceptionClosed PROTOTYPE((JNIEnv* env));
void ioh_convertToPlatform PROTOTYPE((char *path));
void throwNPException PROTOTYPE((JNIEnv* env, const char* message));
void throwIndexOutOfBoundsException PROTOTYPE((JNIEnv* env));

/* NativesCommonFileDescriptor*/
void JNICALL Java_java_io_FileDescriptor_oneTimeInitialization PROTOTYPE((JNIEnv * env, jclass fdClazz));
void JNICALL Java_java_io_FileDescriptor_sync PROTOTYPE((JNIEnv * env, jobject recv));
jboolean JNICALL Java_java_io_FileDescriptor_valid PROTOTYPE((JNIEnv * env, jobject recv));

/* NativesCommonProcess*/
jint JNICALL Java_org_apache_harmony_luni_internal_process_ProcessInputStream_availableImpl PROTOTYPE((JNIEnv * env, jobject recv));
void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessInputStream_oneTimeInitialization PROTOTYPE((JNIEnv * env, jclass clazz));
void JNICALL Java_org_apache_harmony_luni_internal_process_SystemProcess_destroyImpl PROTOTYPE((JNIEnv * env, jobject recv));
void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessOutputStream_writeImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray  buffer, jint offset, jint nbytes,jlong handle));
jint JNICALL Java_org_apache_harmony_luni_internal_process_SystemProcess_waitForCompletionImpl PROTOTYPE((JNIEnv * env, jobject recv));
void JNICALL Java_org_apache_harmony_luni_internal_process_SystemProcess_closeImpl PROTOTYPE((JNIEnv * env, jobject recv));
void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessOutputStream_closeImpl PROTOTYPE((JNIEnv *env, jobject recv));
void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessOutputStream_oneTimeInitialization PROTOTYPE((JNIEnv * env, jclass clazz));
jint JNICALL Java_org_apache_harmony_luni_internal_process_ProcessInputStream_readImpl PROTOTYPE((JNIEnv *env, jobject recv, jbyteArray  buffer, jint offset, jint nbytes, jlong handle));
void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessOutputStream_setFDImpl PROTOTYPE((JNIEnv *env, jobject recv, jobject arg1, jlong arg2));
void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessInputStream_closeImpl PROTOTYPE((JNIEnv * env, jobject recv));
jlongArray JNICALL Java_org_apache_harmony_luni_internal_process_SystemProcess_createImpl PROTOTYPE((JNIEnv * env, jclass clazz, jobject recv,
	jobjectArray arg1, jobjectArray arg2, jbyteArray dir));
void JNICALL Java_org_apache_harmony_luni_internal_process_ProcessInputStream_setFDImpl PROTOTYPE((JNIEnv *env, jobject recv, jobject arg1, jlong arg2));
void JNICALL Java_org_apache_harmony_luni_internal_process_SystemProcess_oneTimeInitialization PROTOTYPE((JNIEnv * env, jclass clazz));

/************************************************************
 ** COMPONENT: NativesCommonFileSystem
 ************************************************************/
/* NativesCommonVMFileSystem*/
jbyteArray JNICALL Java_org_apache_harmony_kernel_vm_VM_getPathFromClassPath PROTOTYPE((JNIEnv * env, jclass clazz, jint cpIndex));
jbyteArray JNICALL Java_org_apache_harmony_kernel_vm_VM_readManifestFromClassPath PROTOTYPE((JNIEnv * env, jclass clazz, jint cpIndex));
jbyteArray JNICALL Java_org_apache_harmony_kernel_vm_VM_readZip PROTOTYPE((JNIEnv * env, jclass clazz, jbyteArray zipName, jstring fileNameString));
jbyteArray JNICALL Java_org_apache_harmony_kernel_vm_VM_readZipFromClassPath PROTOTYPE((JNIEnv * env, jclass clazz, jint cpIndex, jstring fileNameString));
jbyteArray JNICALL Java_org_apache_harmony_kernel_vm_VM_readManifest PROTOTYPE((JNIEnv * env, jclass clazz, jbyteArray zipName));

/************************************************************
 ** COMPONENT: NativesUNIX
 ************************************************************/
/* NativesUNIXHelpers*/
int platformReadLink PROTOTYPE((char *link));
jbyteArray getPlatformPath PROTOTYPE((JNIEnv *env, jbyteArray path));
void setDefaultServerSocketOptions PROTOTYPE((JNIEnv *env, hysocket_t socketP));
I_32 getPlatformRoots PROTOTYPE((char *rootStrings));
char * getCommports  PROTOTYPE((JNIEnv * env));
jint getPlatformDatagramNominalSize PROTOTYPE((JNIEnv *env, hysocket_t socketP));
I_32 getPlatformIsHidden PROTOTYPE((JNIEnv *env, char * path));
jstring getCustomTimeZoneInfo PROTOTYPE((JNIEnv *env, jintArray tzinfo, jbooleanArray isCustomTimeZone));
jint getPlatformDatagramMaxSize PROTOTYPE((JNIEnv *env, hysocket_t socketP));
I_32 getPlatformIsWriteOnly PROTOTYPE((JNIEnv *env, char * path));
I_32 setPlatformFileLength PROTOTYPE((JNIEnv *env, IDATA descriptor,jlong newLength));
I_32 getPlatformIsReadOnly PROTOTYPE((JNIEnv *env, char * path));
void setPlatformBindOptions PROTOTYPE((JNIEnv *env, hysocket_t socketP));
I_32 setPlatformLastModified PROTOTYPE((JNIEnv *env, char * path, I_64 time));
I_32 setPlatformReadOnly PROTOTYPE((JNIEnv *env, char * path));

/* NativesUNIXSystemHelpers*/
char *getPlatformFileEncoding PROTOTYPE((JNIEnv * env, char *codepageProp, int propSize));
char * getTmpDir PROTOTYPE((JNIEnv *env, char**envSpace));
jobject getPlatformPropertyList PROTOTYPE((JNIEnv * env, const char *strings[], int propIndex));
void mapLibraryToPlatformName PROTOTYPE((const char *inPath, char *outPath));

/************************************************************
 ** COMPONENT: harmonyNativesCommon
 ************************************************************/
/* NativesCommonFloatAndDouble*/
JNIEXPORT jlong JNICALL Java_java_lang_Double_doubleToLongBits PROTOTYPE((JNIEnv *env, jclass cls, jdouble doubleValue));
JNIEXPORT jint JNICALL Java_java_lang_Float_floatToIntBits PROTOTYPE((JNIEnv *env, jclass cls, jfloat floatValue));
JNIEXPORT jint JNICALL Java_java_lang_Float_floatToRawIntBits PROTOTYPE((JNIEnv *env, jclass cls, jfloat floatValue));
JNIEXPORT jdouble JNICALL Java_java_lang_Double_longBitsToDouble PROTOTYPE((JNIEnv *env, jclass cls, jlong longValue));
JNIEXPORT jlong JNICALL Java_java_lang_Double_doubleToRawLongBits PROTOTYPE((JNIEnv *env, jclass cls, jdouble doubleValue));
JNIEXPORT jfloat JNICALL Java_java_lang_Float_intBitsToFloat PROTOTYPE((JNIEnv *env, jclass cls, jint intValue));

/* harmonyNativesCommonMath*/
jdouble JNICALL Java_java_lang_Math_asin PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_StrictMath_rint PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_StrictMath_atan PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_Math_exp PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_StrictMath_tan PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_Math_rint PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_StrictMath_ceil PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_StrictMath_IEEEremainder PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1, jdouble arg2));
jdouble JNICALL Java_java_lang_Math_floor PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_StrictMath_sin PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_Math_log PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_Math_acos PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_Math_ceil PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_StrictMath_sqrt PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_Math_pow PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1, jdouble arg2));
jdouble JNICALL Java_java_lang_StrictMath_exp PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_StrictMath_acos PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_StrictMath_floor PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_StrictMath_cos PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_Math_cos PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_Math_sqrt PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_Math_tan PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_Math_sin PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_StrictMath_pow PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1, jdouble arg2));
jdouble JNICALL Java_java_lang_StrictMath_asin PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_StrictMath_atan2 PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1, jdouble arg2));
jdouble JNICALL Java_java_lang_Math_IEEEremainder PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1, jdouble arg2));
jdouble JNICALL Java_java_lang_StrictMath_log PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_Math_atan PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1));
jdouble JNICALL Java_java_lang_Math_atan2 PROTOTYPE((JNIEnv *env, jclass jclazz, jdouble arg1, jdouble arg2));

/* harmonyNativesLUNIGlobals*/
jint JNICALL JNI_OnLoad PROTOTYPE((JavaVM * vm, void *reserved));
void JNICALL JNI_OnUnload PROTOTYPE((JavaVM * vm, void *reserved));

/* harmonyNativesLibGlobals*/
jint JNICALL ClearLibAttach PROTOTYPE((JNIEnv *env));
void JNICALL ClearLibDetach PROTOTYPE((JNIEnv *env));

/* BBharmonyNativesMathGlobals*/
jint JNICALL JNI_OnLoad PROTOTYPE((JavaVM * vm, void *reserved));
void JNICALL JNI_OnUnload PROTOTYPE((JavaVM * vm, void *reserved));

/* BBharmonyNativesArchiveGlobals*/
jint JNICALL JNI_OnLoad PROTOTYPE((JavaVM * vm, void *reserved));
void JNICALL JNI_OnUnload PROTOTYPE((JavaVM * vm, void *reserved));

#if defined(__cplusplus)
}
#endif

#endif /* JCLPROTS_H */
