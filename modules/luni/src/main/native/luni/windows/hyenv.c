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
#include <windows.h>
#include "hyenv.h"


JNIEXPORT jbyteArray JNICALL Java_org_apache_harmony_luni_platform_Environment_getEnvBytes
  (JNIEnv *env, jclass obj){ 
  jsize len =0;
  jbyte *buffer;
  LPTSTR lpszVars;
  LPVOID lpvEnv;
  jbyteArray byteArray;
  lpvEnv = GetEnvironmentStrings();
  if (lpvEnv == NULL){
    return NULL;
  }
  lpszVars = (LPTSTR)lpvEnv;
  buffer = lpszVars;
  while(*lpszVars){
    len += strlen(lpszVars)+1;
	lpszVars += strlen(lpszVars)+1;
  }
  byteArray = (*env)->NewByteArray(env,len);
  (*env)->SetByteArrayRegion(env,byteArray, 0, len, buffer);
  FreeEnvironmentStrings((LPTCH)lpvEnv);
  return byteArray;
}
