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

#include "OSNetworkSystem.h"
#include "nethelp.h"
#include "portsock.h"

/*
 * Class:     com_ibm_platform_OSNetworkSystem
 * Method:    selectImpl
 * Signature: ([Ljava/io/FileDescriptor;[Ljava/io/FileDescriptor;II[IJ)I
 */
JNIEXPORT jint JNICALL Java_com_ibm_platform_OSNetworkSystem_selectImpl	
  (JNIEnv * env, jclass	thisClz, jobjectArray readFDArray, jobjectArray	writeFDArray,
   jint	countReadC, jint countWriteC, jintArray	outFlags, jlong	timeout){
  PORT_ACCESS_FROM_ENV (env);
  hytimeval_struct timeP;	
  I_32 result =	0;		
  I_32 size = 0;		
  jobject gotFD;		
  hyfdset_t fdset_read,fdset_write;
  hysocket_t hysocketP;		
  jboolean isCopy ;
  jint *flagArray;
  int val;

  fdset_read = hymem_allocate_memory(sizeof (struct hyfdset_struct));
  fdset_write =	hymem_allocate_memory(sizeof (struct hyfdset_struct));

  FD_ZERO (&fdset_read->handle);
  FD_ZERO (&fdset_write->handle);
  for (val = 0; val<countReadC; val++){
	  gotFD	= (*env)->GetObjectArrayElement(env,readFDArray,val);
	  hysocketP = getJavaIoFileDescriptorContentsAsPointer (env, gotFD);
	  if (!hysock_socketIsValid (hysocketP)){
      		continue;
    	  }
	 if (hysocketP->flags &	SOCKET_IPV4_OPEN_MASK)
	 {
		FD_SET (hysocketP->ipv4, &fdset_read->handle);
		if (0 >	(size -	hysocketP->ipv4))
			size = hysocketP->ipv4;	
	 }
	 if (hysocketP->flags &	SOCKET_IPV6_OPEN_MASK)
	 {
		FD_SET (hysocketP->ipv6, &fdset_read->handle);
		if (0 >	(size -	hysocketP->ipv6))
			size = hysocketP->ipv6;	
	 }
	}
  for (val = 0; val<countWriteC; val++){
	  gotFD	= (*env)->GetObjectArrayElement(env,writeFDArray,val);
	  hysocketP = getJavaIoFileDescriptorContentsAsPointer (env, gotFD);
	  if (!hysock_socketIsValid (hysocketP)){
      		continue;
    	  }
	 if (hysocketP->flags &	SOCKET_IPV4_OPEN_MASK)
	 {
		FD_SET (hysocketP->ipv4, &fdset_write->handle);	
		if (0 >	(size -	hysocketP->ipv4))
			size = hysocketP->ipv4;	
	 }
	 if (hysocketP->flags &	SOCKET_IPV6_OPEN_MASK)
	 {
		FD_SET (hysocketP->ipv6, &fdset_write->handle);	
		if (0 >	(size -	hysocketP->ipv6))
			size = hysocketP->ipv6;	
	 }
	}
  /* the size is the max_fd + 1	*/
  size =size + 1;

  if (0	> size)	
    {
      result = HYPORT_ERROR_SOCKET_FDSET_SIZEBAD;
    }
  else
    {
      /* only set when timeout >= 0 (non-block)*/
      if (0 <= timeout){      	
	hysock_timeval_init ( 0, (I_32)timeout,	&timeP);
	result = hysock_select (size, fdset_read, fdset_write, NULL,&timeP);
      }	
      else{        
	result = hysock_select (size, fdset_read, fdset_write, NULL,NULL);
      }	
    }
    
  if (0	< result){
	  /*output the reslut to a int array*/
	  flagArray = (*env)->GetIntArrayElements(env,outFlags,	&isCopy);
	  for (val=0;val<countReadC;val++){
		gotFD =	(*env)->GetObjectArrayElement(env,readFDArray,val);
		hysocketP = getJavaIoFileDescriptorContentsAsPointer (env, gotFD);
		if (!hysock_socketIsValid (hysocketP)){
      			continue;
    	  	}
		if (hysocketP->flags & SOCKET_IPV4_OPEN_MASK)
		{
			if (FD_ISSET(hysocketP->ipv4,fdset_read))
				flagArray[val] = SOCKET_OP_READ;
			else
				flagArray[val] = SOCKET_OP_NONE;
		}
		else if (hysocketP->flags &	SOCKET_IPV6_OPEN_MASK)
		{
			if (FD_ISSET(hysocketP->ipv6,fdset_read))
				flagArray[val] = SOCKET_OP_READ;
			else
				flagArray[val] = SOCKET_OP_NONE;
		}
	 }
		
	  for (val=0;val<countWriteC;val++){
		gotFD =	(*env)->GetObjectArrayElement(env,writeFDArray,val);
		hysocketP = getJavaIoFileDescriptorContentsAsPointer (env, gotFD);
		if (!hysock_socketIsValid (hysocketP)){
      			continue;
    	  	}
		if (hysocketP->flags & SOCKET_IPV4_OPEN_MASK)
		{
			if (FD_ISSET(hysocketP->ipv4,fdset_write))
				flagArray[val+countReadC] = SOCKET_OP_WRITE;
			else
				flagArray[val+countReadC] = SOCKET_OP_NONE;
		}
		else if (hysocketP->flags & SOCKET_IPV6_OPEN_MASK)
		{
			if (FD_ISSET(hysocketP->ipv6,fdset_write))
				flagArray[val+countReadC] = SOCKET_OP_WRITE;
			else
				flagArray[val+countReadC] = SOCKET_OP_NONE;
		}

		}
	(*env)->ReleaseIntArrayElements(env,outFlags, flagArray, 0); 
  }
  hymem_free_memory(fdset_read);
  hymem_free_memory(fdset_write);
  /* return both correct and error result, let java code handle	the exception*/
  return result;
}

/*Alternative Select function*/
int 
selectRead (JNIEnv * env,hysocket_t hysocketP, I_32 uSecTime, BOOLEAN accept){
  PORT_ACCESS_FROM_ENV (env);
  hytimeval_struct timeP;
  hyfdset_t fdset_read;
  I_32 result = 0;
  I_32 size = 0;
  if (0 <= uSecTime)
    hysock_timeval_init (0, uSecTime, &timeP);

  fdset_read = hymem_allocate_memory(sizeof (struct hyfdset_struct));
  FD_ZERO (&fdset_read->handle);
  if (hysocketP->flags & SOCKET_IPV4_OPEN_MASK)
    {
      FD_SET (hysocketP->ipv4, &fdset_read->handle);
      size =hysocketP->ipv4 + 1;
    }
  if (hysocketP->flags & SOCKET_IPV6_OPEN_MASK)
    {
      FD_SET (hysocketP->ipv6, &fdset_read->handle);
      size =hysocketP->ipv6 + 1;
    }
  if (0 <= uSecTime)
    result = hysock_select (size, fdset_read, NULL, NULL,&timeP);  
  else
    result = hysock_select (size, fdset_read, NULL, NULL,NULL);  
  hymem_free_memory(fdset_read);
  return result;
}
