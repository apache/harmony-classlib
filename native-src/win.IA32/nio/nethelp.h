/* Copyright 2000, 2006 The Apache Software Foundation or its licensors, as applicable
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

#if !defined(nethelp_h)
#define nethelp_h

#include "hysocket.h"
#include "iohelp.h"
#define JAVASOCKOPT_TCP_NODELAY 1
#define JAVASOCKOPT_SO_REUSEADDR 4
#define JAVASOCKOPT_MCAST_ADD_MEMBERSHIP 19
#define JAVASOCKOPT_MCAST_DROP_MEMBERSHIP 20
#define JAVASOCKOPT_MCAST_TTL 17
#define JAVASOCKOPT_SO_KEEPALIVE 8
#define JAVASOCKOPT_MCAST_TIME_TO_LIVE 10	/* Currently unused */
#define JAVASOCKOPT_SO_BROADCAST 32
#define JAVASOCKOPT_SO_BINDADDR 15
#define JAVASOCKOPT_MCAST_INTERFACE 16
#define JAVASOCKOPT_SO_LINGER 128
#define JAVASOCKOPT_SO_REUSEPORT 512
#define JAVASOCKOPT_SO_SNDBUF 4097
#define JAVASOCKOPT_SO_RCVBUF 4098
#define JAVASOCKOPT_SO_RCVTIMEOUT  4102
#define JAVASOCKOPT_IP_TOS 3
#define JAVASOCKOPT_IP_MULTICAST_LOOP 18
#define JAVASOCKOPT_IP_MULTICAST_IF2 31
#define JAVASOCKOPT_SO_OOBINLINE  4099
#define JAVASOCKOPT_REUSEADDR_AND_REUSEPORT  10001

//--------------------------------------
//reflect function
//--------------------------------------
void
setJavaIoFileDescriptorContents (JNIEnv * env, jobject fd,
                                          void *value);
                                          
void *
getJavaIoFileDescriptorContentsAsAPointer (JNIEnv * env, jobject fd);

jclass 
getJavaLangBooleanClass(JNIEnv * env);

jmethodID
getJavaLangBooleanInit(JNIEnv * env);

jfieldID
getJavaLangBooleanValue(JNIEnv * env);

jclass
getJavaLangByteClass(JNIEnv * env);

jmethodID
getJavaLangByteInit(JNIEnv * env);

jfieldID
getJavaLangByteValue(JNIEnv * env);

jclass
getJavaLangIntegerClass(JNIEnv * env);

jmethodID
getJavaLangIntegerInit(JNIEnv * env);

jfieldID
getJavaLangIntegerValue(JNIEnv * env);

jclass
getJavaNetInetAddressClass(JNIEnv * env);

jfieldID
getJavaNetInetAddressIpaddress(JNIEnv * env);

jmethodID
getJavaNetInetAddressPreferIPv6Addresses(JNIEnv * env);

jmethodID
getJavaNetInetAddressGetByAddressStringByte(JNIEnv * env);

jmethodID
getJavaNetInetAddressGetByAddressByte(JNIEnv * env);
jmethodID
getJavaNetInetAddressInitByte(JNIEnv * env);

jmethodID
getJavaNetInetAddressInitByteString(JNIEnv * env);

jclass
getJavaNetSocketClass(JNIEnv * env);

jmethodID
getJavaNetSocketPreferIPv4Stack(JNIEnv * env);

jclass
getJavaLangThreadClass(JNIEnv * env);

jmethodID
getJavaLangThreadYield(JNIEnv * env);

jclass
getJavaNetDatagramPacketClass(JNIEnv * env);

jfieldID
getJavaNetDatagramPacketAddress(JNIEnv * env);

jfieldID
getJavaNetDatagramPacketLength(JNIEnv * env);

jfieldID
getJavaNetDatagramPacketPort(JNIEnv * env);

jfieldID
getJavaNetSocketImplAddress(JNIEnv * env);

jfieldID
getJavaNetSocketImplPort(JNIEnv * env);

#endif /* nethelp_h */
