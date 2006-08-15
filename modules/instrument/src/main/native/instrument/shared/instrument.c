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

#include <stdlib.h>
#include "instrument.h"
#include "vmi.h"


/*
 * This file contains native methods implementation for org/apache/harmony/instrument/internal/InstrumentationImpl
 */

jobjectArray extract_elements(JNIEnv *env, jvmtiEnv *jvmti, jint count, const jclass* classes_ptr){
	  jclass klass;
	  jobjectArray classes;
	  int index;
	  jvmtiError err;
		
	  //get the class of "java.lang.Class" in java language
	  klass= (*env)->FindClass(env, "java/lang/Class");
	  if(NULL == klass){
		return NULL;
	  }
	
	  //initiate the object array to return, fill in all elements with the same value
	  classes = (*env)->NewObjectArray(env, count, klass, NULL);
	  if(NULL == classes){
		  return NULL;
	  }
	
	  //fill in the object array with right values
	  for(index=0; index<count; index++){
		  (*env)->SetObjectArrayElement(env, classes, index, classes_ptr[index]);
	  }

	  err = (*jvmti)->Deallocate(jvmti,(unsigned char *)classes_ptr);
	  check_jvmti_error(env, err, "Cannot deallocate memory.");

	  return classes;
}

/*
 * Class:     Java_org_apache_harmony_instrument_internal_InstrumentationImpl
 * Method:    getAllLoadedClasses
 * Signature: ()[Ljava/lang/Class;
 */
JNIEXPORT jobjectArray JNICALL Java_org_apache_harmony_instrument_internal_InstrumentationImpl_getAllLoadedClasses
  (JNIEnv* env, jobject objThis){
	  jvmtiEnv* jvmti=gdata->jvmti;
	  jint count=0;
	  jclass* classes_ptr=NULL;
      jobjectArray classes; //the object array to return
	  
	  jvmtiError err = (*jvmti)->GetLoadedClasses(jvmti, &count, &classes_ptr);
	  check_jvmti_error(env, err, "Cannot get loaded classes.");

	  classes = extract_elements(env, jvmti, count, classes_ptr);

	  return classes;
}


/*
 * Class:     Java_org_apache_harmony_instrument_internal_InstrumentationImpl
 * Method:    getInitiatedClasses
 * Signature: (Ljava/lang/ClassLoader;)[Ljava/lang/Class;
 */
JNIEXPORT jobjectArray JNICALL Java_org_apache_harmony_instrument_internal_InstrumentationImpl_getInitiatedClasses
  (JNIEnv * env, jobject objThis, jobject loader){
	  jvmtiEnv* jvmti=gdata->jvmti;
	  jint count=0;
	  jclass* classes_ptr=NULL;
      jobjectArray classes; 

	  jvmtiError err = (*jvmti)->GetClassLoaderClasses(jvmti, loader, &count, &classes_ptr);
	  check_jvmti_error(env, err, "Cannot get loaded classes for this classloader.");

	  classes = extract_elements(env, jvmti, count, classes_ptr);

	  return classes;
}

/*
 * Class:     Java_org_apache_harmony_instrument_internal_InstrumentationImpl
 * Method:    getObjectSize_native
 * Signature: (Ljava/lang/Object;)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_instrument_internal_InstrumentationImpl_getObjectSize_1native
  (JNIEnv * env, jobject objThis, jobject objToSize){
	  jvmtiEnv *jvmti=gdata->jvmti;
	  jlong size=0l; 
	  jvmtiError err=(*jvmti)->GetObjectSize(jvmti, objToSize, &size);
	  check_jvmti_error(env, err, "Cannot get object size.");
	  return size;
}

/*
 * Class:     Java_org_apache_harmony_instrument_internal_InstrumentationImpl
 * Method:    redefineClasses_native
 * Signature: ([Ljava/lang/instrument/ClassDefinition;)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_instrument_internal_InstrumentationImpl_redefineClasses_1native
  (JNIEnv * env, jobject objThis, jobjectArray javaClassDefArr){
      PORT_ACCESS_FROM_ENV (env);
	  jvmtiEnv* jvmti=gdata->jvmti;
	  int err; 
	  int index;
	  jmethodID method_get_class;
	  jmethodID method_get_data;
	  jsize length;
	  jvmtiClassDefinition *class_definitions;	  
	  int i=0;

	  //locate the java methods needed by class definition data extraction
	  jclass class_ClassDefinition=(*env)->FindClass(env, "java/lang/instrument/ClassDefinition");
	  if(NULL == class_ClassDefinition){
		return JNI_ERR;
	  }

	  method_get_data=(*env)->GetMethodID(env, class_ClassDefinition, "getDefinitionClassFile", "()[B");
	  if(NULL == method_get_data){
		return JNI_ERR;
	  }
	  
	  method_get_class=(*env)->GetMethodID(env, class_ClassDefinition, "getDefinitionClass", "()Ljava/lang/Class;");
	  if(NULL == method_get_class){
		return JNI_ERR;
	  }

	  //allocate memory for native jvmtiClassDefinition structs to hold class redefinition data
	  length=(*env)->GetArrayLength(env, javaClassDefArr);
	  class_definitions=(jvmtiClassDefinition*) hymem_allocate_memory(sizeof(jvmtiClassDefinition)*length);
	  if(NULL == class_definitions){
	  	return JNI_ERR;
	  }
	  
	  //extract class definition data from java array into native array
	  for(index=0; index<length; index++){
		  int class_byte_count; 
		  jobject obj_ClassDefinition=(*env)->GetObjectArrayElement(env, javaClassDefArr, index); 		  
		  jbyteArray jclass_bytes;
		  jboolean copy;
		  jbyte* class_bytes;
		  jclass klass=(jclass)(*env)->CallObjectMethod(env, obj_ClassDefinition, method_get_class);
		  if (NULL == klass){
		    hymem_free_memory(class_definitions);
		  	return JNI_ERR;
		  }
		  jclass_bytes =(jbyteArray)(*env)->CallObjectMethod(env, obj_ClassDefinition, method_get_data);
		  copy = JNI_TRUE;
		  class_bytes = (*env)->GetByteArrayElements(env, jclass_bytes, &copy);
		  if(NULL == class_bytes){
			hymem_free_memory(class_definitions);
			return JNI_ERR;
		  }
		  class_byte_count = (*env)->GetArrayLength(env, jclass_bytes);
          if(copy == JNI_TRUE){
		    (*env)->ReleaseByteArrayElements(env,jclass_bytes, class_bytes, JNI_ABORT);
          }
	  
		  //construct a jvmtiClassDefinition element		  
		  class_definitions[index].klass=klass;
		  class_definitions[index].class_bytes=class_bytes;
		  class_definitions[index].class_byte_count=class_byte_count;
	  }

	  //perform redefinition
	  err=(*jvmti)->RedefineClasses(jvmti, length, class_definitions);
	  //free memory
	  hymem_free_memory(class_definitions);
	  return err;
}

void check_jvmti_error(JNIEnv *env, jvmtiError error, const char *msg){
	if(error != JVMTI_ERROR_NONE){
		(*env)->FatalError(env,msg);
	}
	return;
}
