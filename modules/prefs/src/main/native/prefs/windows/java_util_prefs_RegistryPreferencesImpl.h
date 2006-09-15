/* Copyright 1991, 2005 The Apache Software Foundation or its licensors, as applicable
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

/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class java_util_prefs_RegistryPreferencesImpl */

#if !defined(_Included_java_util_prefs_RegistryPreferencesImpl)
#define _Included_java_util_prefs_RegistryPreferencesImpl
#if defined(__cplusplus)
extern "C"
{
#endif
/* Inaccessible static: factory */
#undef java_util_prefs_RegistryPreferencesImpl_MAX_KEY_LENGTH
#define java_util_prefs_RegistryPreferencesImpl_MAX_KEY_LENGTH 80L
#undef java_util_prefs_RegistryPreferencesImpl_MAX_VALUE_LENGTH
#define java_util_prefs_RegistryPreferencesImpl_MAX_VALUE_LENGTH 8192L
#undef java_util_prefs_RegistryPreferencesImpl_MAX_NAME_LENGTH
#define java_util_prefs_RegistryPreferencesImpl_MAX_NAME_LENGTH 80L
/* Inaccessible static: prefsPerm */
/* Inaccessible static: EMPTY_STRING_ARRAY */
/* Inaccessible static: EMPTY_ABSTRACT_PREFS_ARRAY */
/* Inaccessible static: eventQueue */
/* Inaccessible static: eventDispatchThread */
#undef java_util_prefs_RegistryPreferencesImpl_ERROR_CODE
#define java_util_prefs_RegistryPreferencesImpl_ERROR_CODE 0L
#undef java_util_prefs_RegistryPreferencesImpl_RETURN_SUCCESS
#define java_util_prefs_RegistryPreferencesImpl_RETURN_SUCCESS 0L
#undef java_util_prefs_RegistryPreferencesImpl_RETURN_FILE_NOT_FOUND
#define java_util_prefs_RegistryPreferencesImpl_RETURN_FILE_NOT_FOUND 1L
#undef java_util_prefs_RegistryPreferencesImpl_RETURN_ACCESS_DENIED
#define java_util_prefs_RegistryPreferencesImpl_RETURN_ACCESS_DENIED 2L
#undef java_util_prefs_RegistryPreferencesImpl_RETURN_UNKNOWN_ERROR
#define java_util_prefs_RegistryPreferencesImpl_RETURN_UNKNOWN_ERROR 3L
/* Inaccessible static: USER_ROOT */
/* Inaccessible static: SYSTEM_ROOT */
/*
 * Class:     java_util_prefs_RegistryPreferencesImpl
 * Method:    getValue
 * Signature: ([B[BZ[I)[B
 */
  JNIEXPORT jbyteArray JNICALL
    Java_java_util_prefs_RegistryPreferencesImpl_getValue (JNIEnv *, jobject,
                                                           jbyteArray,
                                                           jbyteArray,
                                                           jboolean,
                                                           jintArray);
/*
 * Class:     java_util_prefs_RegistryPreferencesImpl
 * Method:    putValue
 * Signature: ([B[B[BZ[I)V
 */
  JNIEXPORT void JNICALL Java_java_util_prefs_RegistryPreferencesImpl_putValue
    (JNIEnv *, jobject, jbyteArray, jbyteArray, jbyteArray, jboolean,
     jintArray);
/*
 * Class:     java_util_prefs_RegistryPreferencesImpl
 * Method:    removeKey
 * Signature: ([B[BZ[I)V
 */
  JNIEXPORT void JNICALL
    Java_java_util_prefs_RegistryPreferencesImpl_removeKey (JNIEnv *, jobject,
                                                            jbyteArray,
                                                            jbyteArray,
                                                            jboolean,
                                                            jintArray);
/*
 * Class:     java_util_prefs_RegistryPreferencesImpl
 * Method:    keys
 * Signature: ([BZ[I)[[B
 */
  JNIEXPORT jobjectArray JNICALL
    Java_java_util_prefs_RegistryPreferencesImpl_keys (JNIEnv *, jobject,
                                                       jbyteArray, jboolean,
                                                       jintArray);
/*
 * Class:     java_util_prefs_RegistryPreferencesImpl
 * Method:    removeNode
 * Signature: ([B[BZ[I)V
 */
  JNIEXPORT void JNICALL
    Java_java_util_prefs_RegistryPreferencesImpl_removeNode (JNIEnv *,
                                                             jobject,
                                                             jbyteArray,
                                                             jbyteArray,
                                                             jboolean,
                                                             jintArray);
/*
 * Class:     java_util_prefs_RegistryPreferencesImpl
 * Method:    getNode
 * Signature: ([B[BZ[I)Z
 */
  JNIEXPORT jboolean JNICALL
    Java_java_util_prefs_RegistryPreferencesImpl_getNode (JNIEnv *, jobject,
                                                          jbyteArray,
                                                          jbyteArray,
                                                          jboolean,
                                                          jintArray);
/*
 * Class:     java_util_prefs_RegistryPreferencesImpl
 * Method:    getChildNames
 * Signature: ([BZ[I)[[B
 */
  JNIEXPORT jobjectArray JNICALL
    Java_java_util_prefs_RegistryPreferencesImpl_getChildNames (JNIEnv *,
                                                                jobject,
                                                                jbyteArray,
                                                                jboolean,
                                                                jintArray);
/*
 * Class:     java_util_prefs_RegistryPreferencesImpl
 * Method:    flushPrefs
 * Signature: ([BZ[I)V
 */
  JNIEXPORT void JNICALL
    Java_java_util_prefs_RegistryPreferencesImpl_flushPrefs (JNIEnv *,
                                                             jobject,
                                                             jbyteArray,
                                                             jboolean,
                                                             jintArray);
#if defined(__cplusplus)
}
#endif
#endif
