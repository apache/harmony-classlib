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
#include <string.h>
#include <unistd.h>
#include "hyenv.h"

JNIEXPORT jbyteArray JNICALL Java_org_apache_harmony_luni_platform_Environment_getEnvBytes
  (JNIEnv *env, jclass obj){
  jstring returnvar;
  char* var;
  extern char** environ;
  jbyteArray byteArray;
  int bufsize=0,i=0,start=0,len=0;
  for(i=0;*(environ+i);i++){
    bufsize+=strlen(environ[i])+1;
  }
  byteArray = (*env)->NewByteArray(env,bufsize);
  for(i=0;*(environ+i);i++){
      len=strlen(environ[i])+1;
      (*env)->SetByteArrayRegion(env,byteArray, start, len, environ[i]);
      start+=len;
  }
  return byteArray;
}

