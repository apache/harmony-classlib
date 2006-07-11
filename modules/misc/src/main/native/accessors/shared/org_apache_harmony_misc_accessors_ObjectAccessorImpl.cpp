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
 * @author Andrey Y. Chernyshev
 * @version $Revision$
 */
#include <stdlib.h>
#include <assert.h>
#include "MemMacros.h"
#include "org_apache_harmony_misc_accessors_ObjectAccessor.h"

/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    get<Type>
 * Signature: (Ljava/lang/Object;J)Z
 * Method:    getStatic<Type>
 * Signature: (Ljava/lang/Class;J)Z
 * Method:    set<Type>
 * Signature: (Ljava/lang/Object;JZ)V
 * Method:    setStatic<Type>
 * Signature: (Ljava/lang/Class;JZ)V
 */
#define fieldAccessFunctions(t,T,F) \
JNIEXPORT t JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_get##T \
(JNIEnv *env, jobject accessorObj, jobject obj, jlong fieldID) { \
    return env->Get##F(obj, (jfieldID)(intptr_t)fieldID); \
} \
JNIEXPORT t JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_getStatic##T \
  (JNIEnv *env, jobject accessorObj, jclass clss, jlong fieldID) { \
  return env->GetStatic##F(clss, (jfieldID)(intptr_t)fieldID); \
} \
JNIEXPORT void JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_set##T \
(JNIEnv *env, jobject accessorObj, jobject obj, jlong fieldID, t value) { \
    env->Set##F(obj, (jfieldID)(intptr_t)fieldID, value); \
} \
JNIEXPORT void JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_setStatic##T \
(JNIEnv *env, jobject accessorObj, jclass clss, jlong fieldID, t value) { \
    env->SetStatic##F(clss, (jfieldID)(intptr_t)fieldID, value); \
}


fieldAccessFunctions(jboolean, Boolean, BooleanField)
fieldAccessFunctions(jbyte, Byte, ByteField)
fieldAccessFunctions(jchar, Char, CharField)
fieldAccessFunctions(jint, Int, IntField)
fieldAccessFunctions(jlong, Long, LongField)
fieldAccessFunctions(jshort, Short, ShortField)
fieldAccessFunctions(jfloat, Float, FloatField)
fieldAccessFunctions(jdouble, Double, DoubleField)
fieldAccessFunctions(jobject, Object, ObjectField)


// Cached references to primitive type wrapping classes
jclass findIntClass(JNIEnv* env) {
    static jclass intClass;
    return intClass == NULL ? (jclass)env->NewGlobalRef(env->FindClass("java/lang/Integer")) : intClass;
}
jclass findBooleanClass(JNIEnv* env) {
    static jclass booleanClass;
    return booleanClass == NULL ? (jclass)env->NewGlobalRef(env->FindClass("java/lang/Boolean")) : booleanClass;
}
jclass findByteClass(JNIEnv* env) {
    static jclass byteClass;
    return byteClass == NULL ? (jclass)env->NewGlobalRef(env->FindClass("java/lang/Byte")) : byteClass;
}
jclass findShortClass(JNIEnv* env) {
    static jclass shortClass;
    return shortClass == NULL ? (jclass)env->NewGlobalRef(env->FindClass("java/lang/Short")) : shortClass;
}
jclass findCharClass(JNIEnv* env) {
    static jclass charClass;
    return charClass == NULL ? (jclass)env->NewGlobalRef(env->FindClass("java/lang/Character")) : charClass;
}
jclass findLongClass(JNIEnv* env) {
    static jclass longClass;
    return longClass == NULL ? (jclass)env->NewGlobalRef(env->FindClass("java/lang/Long")) : longClass;
}
jclass findFloatClass(JNIEnv* env) {
    static jclass floatClass;
    return floatClass == NULL ? (jclass)env->NewGlobalRef(env->FindClass("java/lang/Float")) : floatClass;
}
jclass findDoubleClass(JNIEnv* env) {
    static jclass doubleClass;
    return doubleClass == NULL ? (jclass)env->NewGlobalRef(env->FindClass("java/lang/Double")) : doubleClass;
}



/**
 * Method for debugging purposes - can be used to print java object name.
 */
void printObjectName(JNIEnv* env, jobject obj) {
    jmethodID id = env->GetMethodID(env->GetObjectClass(obj), "toString", "()Ljava/lang/String;");
    assert(id != NULL);
    jstring jstr = (jstring)env->CallObjectMethodA(obj, id, NULL);
    assert(jstr != NULL);
    jboolean isCopy;
    const char* str = env->GetStringUTFChars(jstr, &isCopy);
    printf("Object: %s\n", str);
    env->ReleaseStringUTFChars(jstr, str);
}


/**
 * Convenience method - converts an object array to array of jvalues suitable for CallXXX functions.
 */
jvalue* jarrayToValues(JNIEnv* env, jobjectArray array) {
    int len = env->GetArrayLength(array);
    jvalue* pargs = (jvalue*)malloc(len * sizeof(jvalue));
    jobject obj;
    jclass clss;
    jmethodID methodID;
    for (int i = 0; i < len; i++) {
        obj = env->GetObjectArrayElement(array, i);
        clss = env->GetObjectClass(obj);
        // Do unboxing for primitive type wrappers
        if (env->IsSameObject(clss, findIntClass(env))) {
            methodID = env->GetMethodID(clss, "intValue", "()I");
            pargs[i].i = env->CallIntMethodA(obj, methodID, NULL);
        } else if(env->IsSameObject(clss, findLongClass(env))) {
            methodID = env->GetMethodID(clss, "longValue", "()J");
            pargs[i].j = env->CallLongMethodA(obj, methodID, NULL);
        } else if(env->IsSameObject(clss, findShortClass(env))) {
            methodID = env->GetMethodID(clss, "shortValue", "()S");
            pargs[i].s = env->CallShortMethodA(obj, methodID, NULL);
        } else if(env->IsSameObject(clss, findByteClass(env))) {
            methodID = env->GetMethodID(clss, "byteValue", "()B");
            pargs[i].b = env->CallByteMethodA(obj, methodID, NULL);
        } else if(env->IsSameObject(clss, findCharClass(env))) {
            methodID = env->GetMethodID(clss, "charValue", "()C");
            pargs[i].c = env->CallCharMethodA(obj, methodID, NULL);
        } else if(env->IsSameObject(clss, findFloatClass(env))) {
            methodID = env->GetMethodID(clss, "floatValue", "()F");
            pargs[i].f = env->CallFloatMethodA(obj, methodID, NULL);
        } else if(env->IsSameObject(clss, findDoubleClass(env))) {
            methodID = env->GetMethodID(clss, "doubleValue", "()D");
            pargs[i].d = env->CallDoubleMethodA(obj, methodID, NULL);
        } else if(env->IsSameObject(clss, findBooleanClass(env))) {
            methodID = env->GetMethodID(clss, "booleanValue", "()Z");
            pargs[i].z = env->CallBooleanMethodA(obj, methodID, NULL);
        } else {
            pargs[i].l = obj;
        }
    }
    return pargs;
}


// invokeMethodFunctions(void, Void, VoidMethodA)
JNIEXPORT void JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_invokeNonVirtualVoid
(JNIEnv *env, jobject accessorObj, jclass clss, jobject obj, jlong methodID, jobjectArray args){
    jvalue* pargs = jarrayToValues(env, args);
    env->CallNonvirtualVoidMethodA(obj, clss, (jmethodID)(intptr_t)methodID, pargs);
    free(pargs);
}
JNIEXPORT void JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_invokeStaticVoid
(JNIEnv *env, jobject accessorObj, jclass clss, jlong methodID, jobjectArray args) {
    jvalue* pargs = jarrayToValues(env, args);
    env->CallStaticVoidMethodA(clss, (jmethodID)(intptr_t)methodID, pargs);
    free(pargs);
}
JNIEXPORT void JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_invokeVirtualVoid
(JNIEnv *env, jobject accessorObj, jobject obj, jlong methodID, jobjectArray args) {
    jvalue* pargs = jarrayToValues(env, args);
    env->CallVoidMethodA(obj, (jmethodID)(intptr_t)methodID, pargs);
    free(pargs);
}



/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    invokeStaticVoid
 * Signature: (Ljava/lang/Class;J[Ljava/lang/Object;)V
 * Method:    invokeVirtualVoid
 * Signature: (Ljava/lang/Object;J[Ljava/lang/Object;)V
 * Method:    invokeNonVirtualVoid
 * Signature: (Ljava/lang/Class;Ljava/lang/Object;J[Ljava/lang/Object;)V
 */
#define invokeMethodFunctions(t, T, M) \
 JNIEXPORT t JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_invokeNonVirtual##T \
(JNIEnv *env, jobject accessorObj, jclass clss, jobject obj, jlong methodID, jobjectArray args){ \
    jvalue* pargs = jarrayToValues(env, args); \
    t res = env->CallNonvirtual##M(obj, clss, (jmethodID)(intptr_t)methodID, pargs); \
    free(pargs); \
    return res; \
} \
JNIEXPORT t JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_invokeStatic##T \
(JNIEnv *env, jobject accessorObj, jclass clss, jlong methodID, jobjectArray args) { \
    jvalue* pargs = jarrayToValues(env, args); \
    t res = env->CallStatic##M(clss, (jmethodID)(intptr_t)methodID, pargs); \
    free(pargs); \
    return res; \
} \
JNIEXPORT t JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_invokeVirtual##T \
(JNIEnv *env, jobject accessorObj, jobject obj, jlong methodID, jobjectArray args) { \
    jvalue* pargs = jarrayToValues(env, args); \
    t res = env->Call##M(obj, (jmethodID)(intptr_t)methodID, pargs); \
    free(pargs); \
    return res; \
}



invokeMethodFunctions(jobject, Object, ObjectMethodA)
invokeMethodFunctions(jboolean, Boolean, BooleanMethodA)
invokeMethodFunctions(jbyte, Byte, ByteMethodA)
invokeMethodFunctions(jchar, Char, CharMethodA)
invokeMethodFunctions(jshort, Short, ShortMethodA)
invokeMethodFunctions(jint, Int, IntMethodA)
invokeMethodFunctions(jlong, Long, LongMethodA)
invokeMethodFunctions(jfloat, Float, FloatMethodA)
invokeMethodFunctions(jdouble, Double, DoubleMethodA)





/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    getFieldID
 * Signature: (Ljava/lang/reflect/Field;)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_getFieldID__Ljava_lang_reflect_Field_2
(JNIEnv *env, jobject accessorObj, jobject field) {
        return (jlong)(intptr_t)env->FromReflectedField(field);
}

/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    getMethodID0
 * Signature: (Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_getMethodID0__Ljava_lang_Class_2Ljava_lang_String_2Ljava_lang_String_2
(JNIEnv *env, jclass, jclass clss, jstring methodName, jstring methodSignature) {
    jboolean isCopy;
    char* method = (char *)env->GetStringUTFChars(methodName, &isCopy);
    char* sig = (char *)env->GetStringUTFChars(methodSignature, &isCopy);
    jlong res = (jlong)(intptr_t)env->GetMethodID(clss, method, sig);
    env->ReleaseStringUTFChars(methodName, method);
    env->ReleaseStringUTFChars(methodSignature, sig);
    return res;
}

/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    getStaticMethodID0
 * Signature: (Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_getStaticMethodID0
(JNIEnv *env, jclass, jclass clss, jstring methodName, jstring methodSignature) {
    jboolean isCopy;
    char* method = (char *)env->GetStringUTFChars(methodName, &isCopy);
    char* sig = (char *)env->GetStringUTFChars(methodSignature, &isCopy);
    jlong res = (jlong)(intptr_t)env->GetStaticMethodID(clss, method, sig);
    env->ReleaseStringUTFChars(methodName, method);
    env->ReleaseStringUTFChars(methodSignature, sig);
    return res;
}


/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    getMethodID0
 * Signature: (Ljava/lang/reflect/Member;)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_getMethodID0__Ljava_lang_reflect_Member_2
(JNIEnv *env, jclass, jobject method) {
    return (jlong)(intptr_t)env->FromReflectedField(method);
}



/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    allocateObject
 * Signature: (Ljava/lang/Class;)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_allocateObject
(JNIEnv *env, jobject accessorObj, jclass clazz) {
     return env->AllocObject(clazz);
}

/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    newInstance
 * Signature: (Ljava/lang/Class;J[Ljava/lang/Object;)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_newInstance__Ljava_lang_Class_2J_3Ljava_lang_Object_2
(JNIEnv *env, jobject accessorObj, jclass clss, jlong ctorID, jobjectArray args) {
    jvalue* pargs = jarrayToValues(env, args);
    jobject res = env->NewObjectA(clss, (jmethodID)(intptr_t)ctorID, pargs);
    free(pargs);
    return res;
}




/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    newInstance
 * Signature: (Ljava/lang/Class;)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_newInstance__Ljava_lang_Class_2
(JNIEnv *env, jobject accessorObj, jclass c) {
    jmethodID ctorID = env->GetMethodID(c, "<init>", "()V");
    return env->NewObject(c, ctorID);
}

/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    hasStaticInitializer
 * Signature: (Ljava/lang/Class;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_hasStaticInitializer
(JNIEnv *env, jobject accessorObj, jclass c) {
    jboolean res = env->GetStaticMethodID(c, "<clinit>", "()V") != 0 ? JNI_TRUE : JNI_FALSE;
    env->ExceptionClear();
    return res;
}

/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    monitorEnter
 * Signature: (Ljava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_monitorEnter
(JNIEnv *env, jobject accessorObj, jobject obj) {
    env->MonitorEnter(obj);
}

/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    monitorExit
 * Signature: (Ljava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_monitorExit
(JNIEnv *env, jobject accessorObj, jobject obj) {
    env->MonitorExit(obj);
}

/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    getFieldID
 * Signature: (Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_getFieldID__Ljava_lang_Class_2Ljava_lang_String_2Ljava_lang_String_2
(JNIEnv *env, jobject accessorObj, jclass clss, jstring fieldName, jstring fieldSig) {
    jboolean isCopy;
    char* name = (char *)env->GetStringUTFChars(fieldName, &isCopy);
    char* sig = (char *)env->GetStringUTFChars(fieldSig, &isCopy);
    jlong res = (jlong)(intptr_t)env->GetFieldID(clss, name, sig);
    env->ReleaseStringUTFChars(fieldName, name);
    env->ReleaseStringUTFChars(fieldSig, sig);
    return res;
}

/*
 * Class:     org_apache_harmony_misc_accessors_ObjectAccessor
 * Method:    getStaticFieldID
 * Signature: (Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_misc_accessors_ObjectAccessor_getStaticFieldID
(JNIEnv *env, jobject accessorObj, jclass clss, jstring fieldName, jstring fieldSig) {
    jboolean isCopy;
    char* name = (char *)env->GetStringUTFChars(fieldName, &isCopy);
    char* sig = (char *)env->GetStringUTFChars(fieldSig, &isCopy);
    jlong res = (jlong)(intptr_t)env->GetStaticFieldID(clss, name, sig);
    env->ReleaseStringUTFChars(fieldName, name);
    env->ReleaseStringUTFChars(fieldSig, sig);
    return res;
}





