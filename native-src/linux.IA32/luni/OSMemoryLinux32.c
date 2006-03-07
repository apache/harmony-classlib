/* Copyright 2004, 2006 The Apache Software Foundation or its licensors, as applicable
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

/*
 * Common natives supporting the memory system interface.
 */

#include <string.h>
#include <sys/mman.h>
#include <errno.h>
#include <unistd.h>
#include <harmony.h>
#include "OSMemory.h"

#define	OS_JNI(func) JNICALL Java_com_ibm_platform_OSMemory_##func

JNIEXPORT jboolean JNICALL Java_com_ibm_platform_OSMemory_isLittleEndianImpl(JNIEnv * env, jclass clazz)
{
  return JNI_TRUE;
}

JNIEXPORT jint JNICALL Java_com_ibm_platform_OSMemory_getPointerSizeImpl (JNIEnv * env, jclass clazz)
{
  return 4;
}

JNIEXPORT jlong JNICALL Java_com_ibm_platform_OSMemory_getAddress
  (JNIEnv * env, jobject thiz, jlong address)
{
  return (jlong) * (long *) ((IDATA) address);
}

JNIEXPORT void JNICALL Java_com_ibm_platform_OSMemory_setAddress
  (JNIEnv * env, jobject thiz, jlong address, jlong value)
{
  *(long *) ((IDATA) address) = (long) value;
}


int getPageSize()
{
	static int PAGE_SIZE = 0;
	if(PAGE_SIZE==0)
		PAGE_SIZE=getpagesize();
	return PAGE_SIZE;
}

JNIEXPORT jint JNICALL Java_com_ibm_platform_OSMemory_loadImpl
  (JNIEnv * env, jobject thiz, jlong addr, jlong size){
   if(mlock((void *)addr, size)!=-1)
   {
      if(munlock((void *)addr,size)!=-1)
	      return 0;  /*normally*/
   }
   else{
      	if(errno == EPERM) /*according to linux sys call, only root can mlock memory.*/
      	   return 0;
   }
   return -1;
  }

JNIEXPORT jboolean JNICALL Java_com_ibm_platform_OSMemory_isLoadedImpl
  (JNIEnv * env, jobject thiz, jlong addr, jlong size){
	  PORT_ACCESS_FROM_ENV (env);
  	  jboolean result = 0;
  	  int m_addr = (int)addr;
	  int PAGE_SIZE = getPageSize();
	  char* vec = NULL;
	  int page_count = 0;
	  int align_offset = m_addr%PAGE_SIZE;//addr should align with the boundary of a page.
	  m_addr -= align_offset;
	  size   += align_offset;
	  page_count = (size+PAGE_SIZE-1)/PAGE_SIZE;
	  vec = (char *) hymem_allocate_memory(page_count*sizeof(char));
	  if(mincore((void *)m_addr, size , vec)==0) //or else there is error about the mincore and return false;
	  {
	  	  int i;
		  for(i=0 ;i<page_count;i++)
			  if(vec[i]!=1)
			     break;
		  if(i==page_count)
			  result = 1;
	  }
	  hymem_free_memory(vec);
      return result;
  }

JNIEXPORT jint JNICALL Java_com_ibm_platform_OSMemory_flushImpl
  (JNIEnv * env, jobject thiz, jlong addr, jlong size){
  return msync((void *)addr, size, MS_SYNC);
};
