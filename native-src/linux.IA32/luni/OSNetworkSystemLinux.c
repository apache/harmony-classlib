/* Copyright 1998, 2006 The Apache Software Foundation or its licensors, as applicable
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

#include<netinet/ip.h>
#include<netinet/ip_icmp.h>
#include<netinet/in_systm.h>
#include "nethelp.h"
#include "jclglob.h"
#include "portsock.h"
#include "hyport.h"
#include "OSNetworkSystem.h"
#define NOPRIVILEGE -1
#define UNREACHABLE -2
#define REACHABLE 0
#define INVALID_SOCKET -1
#define SOCKET_ERROR -1

unsigned short ip_checksum(unsigned short * buffer, int size);
void set_icmp_packet(struct icmp * icmp_hdr, int packet_size);

//Alternative Select function
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
  FD_SET (hysocketP->sock, &fdset_read->handle);
  size =hysocketP->sock + 1;

  if (0 <= uSecTime)
    result = hysock_select (size, fdset_read, NULL, NULL,&timeP);
  else
    result = hysock_select (size, fdset_read, NULL, NULL,NULL);
  hymem_free_memory(fdset_read);
  return result;
}

JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_isReachableByICMPImpl
  (JNIEnv * env, jobject clz, jobject address, jobject localaddr,  jint ttl, jint timeout){
  struct sockaddr_in dest,source,local;
  struct icmp * send_buf = 0;
  struct ip * recv_buf = 0;
  int size = sizeof(struct icmp);
  int result,ret=UNREACHABLE;
  struct timeval timeP;
  fd_set * fdset_read;
  int sockadd_size = sizeof (source);
  jbyte host[HYSOCK_INADDR6_LEN];
  U_32 length =  (*env)->GetArrayLength (env,address);

  int sock = socket(AF_INET, SOCK_RAW, IPPROTO_ICMP);
  if (INVALID_SOCKET == sock){
	return NOPRIVILEGE;
  }
  setuid(getuid());
  if (0 < ttl){
	  if (0 > setsockopt(sock, IPPROTO_ICMP, IP_TTL, (char*)&ttl,
	          sizeof(ttl))) {
	        return NOPRIVILEGE;
	  }
  }
  
  memset(&dest, 0, sizeof(dest));

  // set address
  netGetJavaNetInetAddressValue (env, address,(U_8 *)host, &length);
  memset(&dest, 0, sizeof(dest));	  
  memcpy (&dest.sin_addr.s_addr,(U_8 *)host, length);
  dest.sin_family = AF_INET;

  if(NULL != localaddr){
    memset(&local, 0, sizeof(local));
    netGetJavaNetInetAddressValue (env, localaddr,(U_8 *)host, &length);
    memcpy (&local.sin_addr.s_addr,(U_8 *)host, length);
    bind(sock, (struct sockaddr *)& local, sizeof(local));
  }

  send_buf = (struct icmp*)malloc(sizeof(char)*ICMP_SIZE);
  recv_buf = (struct ip*)malloc(sizeof(char)*PACKET_SIZE);
  if (NULL == send_buf || NULL == recv_buf){
	  return NOPRIVILEGE;
  }
  set_icmp_packet(send_buf, ICMP_SIZE);

  if(SOCKET_ERROR == sendto(sock, (char*)send_buf, ICMP_SIZE, 0,
            (struct sockaddr*)&dest, sizeof(dest))){
            goto cleanup;
  }
  //set select timeout, change millisecond to usec
  memset (&timeP, 0, sizeof (struct timeval));    
  timeP.tv_sec = timeout/1000;
  timeP.tv_usec = timeout%1000*1000;
  result = select (sock+1, fdset_read, NULL, NULL,&timeP);
  fdset_read = (fd_set *)malloc(sizeof (fd_set));
  FD_ZERO (fdset_read);
  FD_SET (sock, fdset_read);
  result = select (sock+1, fdset_read, NULL, NULL,&timeP);
  if (SOCKET_ERROR == result || 0 == result){
  	goto cleanup;
  }  
  result = recvfrom(sock, (char*)recv_buf,
            PACKET_SIZE, 0,
            (struct sockaddr*)&source, &sockadd_size);

  if (SOCKET_ERROR == result){
  	goto cleanup;
  }  
			    
  unsigned short header_len = recv_buf->ip_hl << 2;
  struct icmp* icmphdr = (struct icmp*)((char*)recv_buf + header_len);
  if ((result < header_len + ICMP_SIZE)||
	(icmphdr->icmp_type != ICMP_ECHO_REPLY)||
	(icmphdr->icmp_id != getpid())) {	
	if (!(icmphdr->icmp_type == ICMP_ECHO_REQUEST && icmphdr->icmp_seq == 0))
		goto cleanup;
  }
  ret = REACHABLE;
cleanup:
  free(fdset_read);
  free(send_buf);
  free(recv_buf);
  return ret;
}

// typical ip checksum
unsigned short ip_checksum(unsigned short* buffer, int size)
{
	register unsigned short * buf = buffer;
    register int bufleft = size;
    register unsigned long sum = 0;
    
    while (bufleft > 1) {
        sum = sum + (*buf++);
        bufleft = bufleft - sizeof(unsigned short );
    }
    if (bufleft) {
        sum = sum + (*(unsigned char*)buf);
    }
    sum = (sum >> 16) + (sum & 0xffff);
    sum += (sum >> 16);
   
    return (unsigned short )(~sum);
}

void set_icmp_packet(struct icmp* icmp_hdr, int packet_size)
{
    icmp_hdr->icmp_type = ICMP_ECHO_REQUEST;
    icmp_hdr->icmp_code = 0;
    icmp_hdr->icmp_cksum = 0;
    icmp_hdr->icmp_id = getpid();
    icmp_hdr->icmp_seq = 0;

    // Calculate a checksum on the result
    icmp_hdr->icmp_cksum = ip_checksum((unsigned short*)icmp_hdr, packet_size);
}

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    selectImpl
 * Signature: ([Ljava/io/FileDescriptor;[Ljava/io/FileDescriptor;II[IJ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_selectImpl	
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
	  hysocketP = getJavaIoFileDescriptorContentsAsAPointer	(env, gotFD);
	  /*No difference between ipv4 and ipv6 as in windows*/
		FD_SET (hysocketP->sock, &fdset_read->handle);
		if (0 >	(size -	hysocketP->sock))
			size = hysocketP->sock;		
	}
  for (val = 0; val<countWriteC; val++){
	  gotFD	= (*env)->GetObjectArrayElement(env,writeFDArray,val);
	  hysocketP = getJavaIoFileDescriptorContentsAsAPointer	(env, gotFD);
	  /*No difference between ipv4 and ipv6 as in windows*/
	  FD_SET (hysocketP->sock, &fdset_write->handle);	
		if (0 >	(size -	hysocketP->sock))
			size = hysocketP->sock;	
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
		hysocketP = getJavaIoFileDescriptorContentsAsAPointer (env, gotFD);
		if (FD_ISSET(hysocketP->sock,&fdset_read->handle))
			flagArray[val] = SOCKET_OP_READ;
		else
			flagArray[val] = SOCKET_OP_NONE;

	 }
		
	  for (val=0;val<countWriteC;val++){
		gotFD =	(*env)->GetObjectArrayElement(env,writeFDArray,val);
		hysocketP = getJavaIoFileDescriptorContentsAsAPointer (env, gotFD);
		if (FD_ISSET(hysocketP->sock,&fdset_write->handle))
			flagArray[val+countReadC] = SOCKET_OP_WRITE;
		else
			flagArray[val+countReadC] = SOCKET_OP_NONE;

		}
	(*env)->ReleaseIntArrayElements(env,outFlags, flagArray, 0); 
  }
  hymem_free_memory(fdset_write);
  hymem_free_memory(fdset_read);
  /* return both correct and error result, let java code handle	the exception*/
  return result;
};

