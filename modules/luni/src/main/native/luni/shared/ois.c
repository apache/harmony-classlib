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

#include "jni.h"

JNIEXPORT jobject JNICALL
Java_java_io_ObjectInputStream_newInstance (JNIEnv * env, jclass clazz,
                                            jobject instantiationClass,
                                            jobject constructorClass)
{
  jmethodID mid =
    (*env)->GetMethodID (env, constructorClass, "<init>", "()V");

  if (mid == 0)
    {
      /* Cant newInstance,No empty constructor... */
      return (jobject) 0;
    }
  else
    {
      /* Instantiate an object of a given class and construct it using
         the constructor of the other class. */
      jobject obj;
      obj = (*env)->AllocObject(env, instantiationClass);
      if (obj != NULL) {
        (*env)->CallNonvirtualVoidMethod(env, obj, constructorClass, mid);
      }
      return obj;
    }

}

