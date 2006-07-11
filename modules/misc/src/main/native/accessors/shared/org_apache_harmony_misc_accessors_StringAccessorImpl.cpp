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
 * @author Sergey I. Salishev
 * @version $Revision$
 */
#include <stdlib.h>
#include <string.h>

#define min(a, b) ((a < b) ? a : b)

#include "MemMacros.h"
#include "org_apache_harmony_misc_accessors_StringAccessor.h"

size_t jstrlen(const jchar* str) {
    size_t i;
    for (i = 0; str[i] != 0; i++);
    return i;
}

/*
 * Class:     org_apache_harmony_misc_accessors_StringAccessor
 * Method:    getUTFChars
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_misc_accessors_StringAccessor_getUTFChars
  (JNIEnv *env, jobject self, jstring str)
{
    const char* strPtr = (const char*)env->GetStringUTFChars(str, 0);
    int len = env->GetStringUTFLength(str);
    char* resPtr = (char*)malloc(len * sizeof(char) + 2);
    memcpy(resPtr, strPtr, len);
    env->ReleaseStringUTFChars(str, (const char*)strPtr);
    resPtr[len + 1] = resPtr[len] = 0;
    return addr2jlong(resPtr);

}

/*
 * Class:     org_apache_harmony_misc_accessors_StringAccessor
 * Method:    getChars
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_misc_accessors_StringAccessor_getChars
  (JNIEnv *env, jobject self, jstring str)
{
    const jchar* strPtr = (const jchar*)env->GetStringCritical(str, 0);
    int len = env->GetStringLength(str);
    jchar* resPtr = (jchar*)malloc((len + 1) * sizeof(jchar));
    memcpy(resPtr, strPtr, len * sizeof(jchar));
    env->ReleaseStringCritical(str, (const jchar*)strPtr);
    resPtr[len] = 0;
    return addr2jlong(resPtr);
}

/*
 * Class:     org_apache_harmony_misc_accessors_StringAccessor
 * Method:    createStringUTF
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_apache_harmony_misc_accessors_StringAccessor_createStringUTF__J
  (JNIEnv *env, jobject self, jlong ptr) 
{
    return env->NewStringUTF(jlong2addr(const char, ptr));
}

/*
 * Class:     org_apache_harmony_misc_accessors_StringAccessor
 * Method:    createString
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_apache_harmony_misc_accessors_StringAccessor_createString__J
  (JNIEnv *env, jobject self, jlong ptr)
{
    return env->NewString(jlong2addr(const jchar, ptr), jstrlen(jlong2addr(const jchar, ptr)));
}

/*
 * Class:     org_apache_harmony_misc_accessors_StringAccessor
 * Method:    createStringUTF
 * Signature: (JJ)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_apache_harmony_misc_accessors_StringAccessor_createStringUTF__JJ
  (JNIEnv *env, jobject self, jlong ptr, jlong len)
{
    const jlong tmplen = len + 2;
    char* cstr = (char*)malloc(tmplen);
    memset(cstr, 0, tmplen);
    strncpy(cstr, jlong2addr(const char, ptr), len);
    jstring res = env->NewStringUTF(cstr);
    free(cstr);
    return res;
}

/*
 * Class:     org_apache_harmony_misc_accessors_StringAccessor
 * Method:    createString
 * Signature: (JJ)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_apache_harmony_misc_accessors_StringAccessor_createString__JJ
  (JNIEnv *env, jobject self, jlong ptr, jlong len)
{
    return env->NewString(jlong2addr(const jchar, ptr), min(len >> 1, jstrlen(jlong2addr(const jchar, ptr))));
}
