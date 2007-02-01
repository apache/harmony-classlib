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

#include <jni.h>
/* Header for class org_apache_harmony_luni_platform_OSNetworkSystem */
#define SOCKET_CONNECT_STEP_START 0
#define SOCKET_CONNECT_STEP_CHECK 1
#define SOCKET_OP_NONE 0
#define SOCKET_OP_READ 1
#define SOCKET_OP_WRITE 2
#define SOCKET_READ_WRITE 3

// const of ICMP value
#define ICMP_ECHO_REPLY 0  
#define ICMP_DEST_UNREACH 3
#define ICMP_TTL_EXPIRE 11
#define ICMP_ECHO_REQUEST 8
#define ICMP_SIZE 8
#define PACKET_SIZE 1024

// IP header
struct IPHeader {
    unsigned char h_len:4;           // default 4
    unsigned char version:4;         // that is IP v4
    unsigned char tos;               // Type of service
    unsigned short total_len;       // Length of the packet in dwords
    unsigned short ident;           // unique identifier
    unsigned short flags;           
    unsigned char ttl;               // Time to live
    unsigned char proto;             // Protocol number (TCP, UDP etc)
    unsigned short checksum;        // IP checksum
    unsigned long source_ip;
    unsigned long dest_ip;
};

// ICMP header
struct ICMPHeader {
    unsigned char type;          // ICMP packet type
    unsigned char code;          // Type sub code
    unsigned short checksum;
    unsigned short id;
    unsigned short seq;
};

#ifndef _Included_org_apache_harmony_luni_platform_OSNetworkSystem
#define _Included_org_apache_harmony_luni_platform_OSNetworkSystem
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    oneTimeInitializationDatagram
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_oneTimeInitializationDatagram
  (JNIEnv *, jclass, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    oneTimeInitializationSocket
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_oneTimeInitializationSocket
  (JNIEnv *, jclass, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    createSocketImpl
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_createSocketImpl
  (JNIEnv *, jclass, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    createDatagramSocketImpl
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_createDatagramSocketImpl
  (JNIEnv *, jclass, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    readSocketImpl
 * Signature: (Ljava/io/FileDescriptor;[BIII)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_readSocketImpl
  (JNIEnv *, jclass, jobject, jbyteArray, jint, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    readSocketDirectImpl
 * Signature: (Ljava/io/FileDescriptor;JIII)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_readSocketDirectImpl
  (JNIEnv *, jclass, jobject, jlong, jint, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    writeSocketImpl
 * Signature: (Ljava/io/FileDescriptor;[BII)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_writeSocketImpl
  (JNIEnv *, jclass, jobject, jbyteArray, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    writeSocketDirectImpl
 * Signature: (Ljava/io/FileDescriptor;JII)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_writeSocketDirectImpl
  (JNIEnv *, jclass, jobject, jlong, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    setNonBlockingImpl
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_setNonBlockingImpl
  (JNIEnv *, jclass, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    connectSocketImpl
 * Signature: (Ljava/io/FileDescriptor;ILjava/net/InetAddress;I)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_connectSocketImpl
  (JNIEnv *, jclass, jobject, jint, jobject, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    connectWithTimeoutSocketImpl
 * Signature: (Ljava/io/FileDescriptor;IILjava/net/InetAddress;IILjava/lang/Long;)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_connectWithTimeoutSocketImpl
  (JNIEnv *, jclass, jobject, jint, jint, jobject, jint, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    connectStreamWithTimeoutSocketImpl
 * Signature: (Ljava/io/FileDescriptor;IIILjava/net/InetAddress;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_connectStreamWithTimeoutSocketImpl
  (JNIEnv *, jclass, jobject, jint, jint, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    socketBindImpl
 * Signature: (Ljava/io/FileDescriptor;ILjava/net/InetAddress;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_socketBindImpl
  (JNIEnv *, jclass, jobject, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    listenStreamSocketImpl
 * Signature: (Ljava/io/FileDescriptor;I)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_listenStreamSocketImpl
  (JNIEnv *, jclass, jobject, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    availableStreamImpl
 * Signature: (Ljava/io/FileDescriptor;)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_availableStreamImpl
  (JNIEnv *, jclass, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    acceptSocketImpl
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/SocketImpl;Ljava/io/FileDescriptor;I)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_acceptSocketImpl
  (JNIEnv *, jclass, jobject, jobject, jobject, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    supportsUrgentDataImpl
 * Signature: (Ljava/io/FileDescriptor;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_supportsUrgentDataImpl
  (JNIEnv *, jclass, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendUrgentDataImpl
 * Signature: (Ljava/io/FileDescriptor;B)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendUrgentDataImpl
  (JNIEnv *, jclass, jobject, jbyte);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    connectDatagramImpl2
 * Signature: (Ljava/io/FileDescriptor;IILjava/net/InetAddress;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_connectDatagramImpl2
  (JNIEnv *, jclass, jobject, jint, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    disconnectDatagramImpl
 * Signature: (Ljava/io/FileDescriptor;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_disconnectDatagramImpl
  (JNIEnv *, jclass, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    socketBindImpl2
 * Signature: (Ljava/io/FileDescriptor;IZLjava/net/InetAddress;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_socketBindImpl2
  (JNIEnv *, jclass, jobject, jint, jboolean, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    peekDatagramImpl
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/InetAddress;I)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_peekDatagramImpl
  (JNIEnv *, jclass, jobject, jobject, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    receiveDatagramImpl
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;[BIIIZ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_receiveDatagramImpl
  (JNIEnv *, jclass, jobject, jobject, jbyteArray, jint, jint, jint, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    receiveDatagramDirectImpl
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;JIIIZ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_receiveDatagramDirectImpl
  (JNIEnv *, jclass, jobject, jobject, jlong, jint, jint, jint, jboolean);
  
/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    recvConnectedDatagramImpl
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;[BIIIZ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_recvConnectedDatagramImpl
  (JNIEnv *, jclass, jobject, jobject, jbyteArray, jint, jint, jint, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    recvConnectedDatagramDirectImpl
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;JIIIZ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_recvConnectedDatagramDirectImpl
  (JNIEnv *, jclass, jobject, jobject, jlong, jint, jint, jint, jboolean);
  

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendDatagramImpl
 * Signature: (Ljava/io/FileDescriptor;[BIIIZILjava/net/InetAddress;)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendDatagramImpl
  (JNIEnv *, jclass, jobject, jbyteArray, jint, jint, jint, jboolean, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendDatagramDirectImpl
 * Signature: (Ljava/io/FileDescriptor;JIIIZILjava/net/InetAddress;)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendDatagramDirectImpl
  (JNIEnv *, jclass, jobject, jlong, jint, jint, jint, jboolean, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendConnectedDatagramImpl
 * Signature: (Ljava/io/FileDescriptor;[BIIZ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendConnectedDatagramImpl
  (JNIEnv *, jclass, jobject, jbyteArray, jint, jint, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendConnectedDatagramDirectImpl
 * Signature: (Ljava/io/FileDescriptor;JIIZ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendConnectedDatagramDirectImpl
  (JNIEnv *, jclass, jobject, jlong, jint, jint, jboolean);  

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    createServerStreamSocketImpl
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_createServerStreamSocketImpl
  (JNIEnv *, jclass, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    createMulticastSocketImpl
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_createMulticastSocketImpl
  (JNIEnv *, jclass, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    receiveStreamImpl
 * Signature: (Ljava/io/FileDescriptor;[BIII)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_receiveStreamImpl
  (JNIEnv *, jclass, jobject, jbyteArray, jint, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendStreamImpl
 * Signature: (Ljava/io/FileDescriptor;[BII)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendStreamImpl
  (JNIEnv *, jclass, jobject, jbyteArray, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    shutdownInputImpl
 * Signature: (Ljava/io/FileDescriptor;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_shutdownInputImpl
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    shutdownOutputImpl
 * Signature: (Ljava/io/FileDescriptor;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_shutdownOutputImpl
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    acceptStreamSocketImpl
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/SocketImpl;Ljava/io/FileDescriptor;I)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_acceptStreamSocketImpl
  (JNIEnv *, jclass, jobject, jobject, jobject, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    createStreamSocketImpl
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_createStreamSocketImpl
  (JNIEnv *, jclass, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendDatagramImpl2
 * Signature: (Ljava/io/FileDescriptor;[BIIILjava/net/InetAddress;)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendDatagramImpl2
  (JNIEnv *, jclass, jobject, jbyteArray, jint, jint, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    selectImpl
 * Signature: ([Ljava/io/FileDescriptor;[Ljava/io/FileDescriptor;II[IJ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_selectImpl
  (JNIEnv *, jclass, jobjectArray, jobjectArray, jint, jint, jintArray, jlong);

  /*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getSocketLocalAddressImpl
 * Signature: (Ljava/io/FileDescriptor;Z)Ljava/net/InetAddress;
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketLocalAddressImpl
  (JNIEnv *, jclass, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getSocketLocalPortImpl
 * Signature: (Ljava/io/FileDescriptor;Z)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketLocalPortImpl
  (JNIEnv *, jclass, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getSocketOptionImpl
 * Signature: (Ljava/io/FileDescriptor;I)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketOptionImpl
  (JNIEnv *, jclass, jobject, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    setSocketOptionImpl
 * Signature: (Ljava/io/FileDescriptor;ILjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_setSocketOptionImpl
  (JNIEnv *, jclass, jobject, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getSocketFlags
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketFlagsImpl
  (JNIEnv *, jclass);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    socketCloseImpl
 * Signature: (Ljava/io/FileDescriptor;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_socketCloseImpl
  (JNIEnv *, jclass, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getHostByAddrImpl
 * Signature: ([B)Ljava/net/InetAddress;
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getHostByAddrImpl
  (JNIEnv *, jclass, jbyteArray);

JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getHostByNameImpl
  (JNIEnv *, jclass,jstring,jboolean);
  
/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    isReachableByICMPImpl
 * Signature: ([BII)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_isReachableByICMPImpl
  (JNIEnv *, jobject, jobject, jobject, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    inheritedChannelImpl
 * Signature: ([BII)I
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_inheritedChannelImpl
  (JNIEnv *, jobject);


#ifdef __cplusplus
}
#endif
#endif
