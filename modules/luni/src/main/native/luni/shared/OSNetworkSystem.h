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
 * Method:    createSocket
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_createSocket
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    createDatagramSocket
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_createDatagramSocket
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    read
 * Signature: (Ljava/io/FileDescriptor;[BIII)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_read
  (JNIEnv *, jobject, jobject, jbyteArray, jint, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    readDirect
 * Signature: (Ljava/io/FileDescriptor;JII)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_readDirect
  (JNIEnv *, jobject, jobject, jlong, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    write
 * Signature: (Ljava/io/FileDescriptor;[BII)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_write
  (JNIEnv *, jobject, jobject, jbyteArray, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    writeDirect
 * Signature: (Ljava/io/FileDescriptor;JI)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_writeDirect
  (JNIEnv *, jobject, jobject, jlong, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    setNonBlockingImpl
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_setNonBlocking
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    connectSocketImpl
 * Signature: (Ljava/io/FileDescriptor;ILjava/net/InetAddress;I)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_connect
  (JNIEnv *, jobject, jobject, jint, jobject, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    connectWithTimeout
 * Signature: (Ljava/io/FileDescriptor;IILjava/net/InetAddress;IILjava/lang/Long;)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_connectWithTimeout
  (JNIEnv *, jobject, jobject, jint, jint, jobject, jint, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    connectStreamWithTimeoutSocket
 * Signature: (Ljava/io/FileDescriptor;IIILjava/net/InetAddress;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_connectStreamWithTimeoutSocket
  (JNIEnv *, jobject, jobject, jint, jint, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    bind
 * Signature: (Ljava/io/FileDescriptor;ILjava/net/InetAddress;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_bind
  (JNIEnv *, jobject, jobject, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    listenStreamSocket
 * Signature: (Ljava/io/FileDescriptor;I)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_listenStreamSocket
  (JNIEnv *, jobject, jobject, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    availableStream
 * Signature: (Ljava/io/FileDescriptor;)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_availableStream
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    accept
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/SocketImpl;Ljava/io/FileDescriptor;I)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_accept
  (JNIEnv *, jobject, jobject, jobject, jobject, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    supportsUrgentData
 * Signature: (Ljava/io/FileDescriptor;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_supportsUrgentData
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendUrgentData
 * Signature: (Ljava/io/FileDescriptor;B)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendUrgentData
  (JNIEnv *, jobject, jobject, jbyte);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    connectDatagram
 * Signature: (Ljava/io/FileDescriptor;IILjava/net/InetAddress;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_connectDatagram
  (JNIEnv *, jobject, jobject, jint, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    disconnectDatagram
 * Signature: (Ljava/io/FileDescriptor;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_disconnectDatagram
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    bind2
 * Signature: (Ljava/io/FileDescriptor;IZLjava/net/InetAddress;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_bind2
  (JNIEnv *, jobject, jobject, jint, jboolean, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    peekDatagram
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/InetAddress;I)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_peekDatagram
  (JNIEnv *, jobject, jobject, jobject, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    receiveDatagram
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;[BIIIZ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_receiveDatagram
  (JNIEnv *, jobject, jobject, jobject, jbyteArray, jint, jint, jint, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    receiveDatagramDirect
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;JIIIZ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_receiveDatagramDirect
  (JNIEnv *, jobject, jobject, jobject, jlong, jint, jint, jint, jboolean);
  
/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    recvConnectedDatagram
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;[BIIIZ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_recvConnectedDatagram
  (JNIEnv *, jobject, jobject, jobject, jbyteArray, jint, jint, jint, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    recvConnectedDatagramDirect
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/DatagramPacket;JIIIZ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_recvConnectedDatagramDirect
  (JNIEnv *, jobject, jobject, jobject, jlong, jint, jint, jint, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendDatagram
 * Signature: (Ljava/io/FileDescriptor;[BIIIZILjava/net/InetAddress;)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendDatagram
  (JNIEnv *, jobject, jobject, jbyteArray, jint, jint, jint, jboolean, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendDatagramDirect
 * Signature: (Ljava/io/FileDescriptor;JIIIZILjava/net/InetAddress;)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendDatagramDirect
  (JNIEnv *, jobject, jobject, jlong, jint, jint, jint, jboolean, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendConnectedDatagram
 * Signature: (Ljava/io/FileDescriptor;[BIIZ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendConnectedDatagram
  (JNIEnv *, jobject, jobject, jbyteArray, jint, jint, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendConnectedDatagramDirect
 * Signature: (Ljava/io/FileDescriptor;JIIZ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendConnectedDatagramDirect
  (JNIEnv *, jobject, jobject, jlong, jint, jint, jboolean);  

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    createServerStreamSocket
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_createServerStreamSocket
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    createMulticastSocket
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_createMulticastSocket
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    receiveStream
 * Signature: (Ljava/io/FileDescriptor;[BIII)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_receiveStream
  (JNIEnv *, jobject, jobject, jbyteArray, jint, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendStream
 * Signature: (Ljava/io/FileDescriptor;[BII)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendStream
  (JNIEnv *, jobject, jobject, jbyteArray, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    shutdownInput
 * Signature: (Ljava/io/FileDescriptor;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_shutdownInput
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    shutdownOutput
 * Signature: (Ljava/io/FileDescriptor;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_shutdownOutput
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    acceptStreamSocket
 * Signature: (Ljava/io/FileDescriptor;Ljava/net/SocketImpl;Ljava/io/FileDescriptor;I)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_acceptStreamSocket
  (JNIEnv *, jobject, jobject, jobject, jobject, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    createStreamSocket
 * Signature: (Ljava/io/FileDescriptor;Z)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_createStreamSocket
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    sendDatagram2
 * Signature: (Ljava/io/FileDescriptor;[BIIILjava/net/InetAddress;)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_sendDatagram2
  (JNIEnv *, jobject, jobject, jbyteArray, jint, jint, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    selectImpl
 * Signature: ([Ljava/io/FileDescriptor;[Ljava/io/FileDescriptor;II[IJ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_selectImpl
  (JNIEnv *, jobject, jobjectArray, jobjectArray, jint, jint, jintArray, jlong);

  /*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getSocketLocalAddress
 * Signature: (Ljava/io/FileDescriptor;Z)Ljava/net/InetAddress;
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketLocalAddress
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getSocketLocalPort
 * Signature: (Ljava/io/FileDescriptor;Z)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketLocalPort
  (JNIEnv *, jobject, jobject, jboolean);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getSocketOption
 * Signature: (Ljava/io/FileDescriptor;I)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketOption
  (JNIEnv *, jobject, jobject, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    setSocketOption
 * Signature: (Ljava/io/FileDescriptor;ILjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_setSocketOption
  (JNIEnv *, jobject, jobject, jint, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getSocketFlags
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getSocketFlags
  (JNIEnv *, jclass);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    socketClose
 * Signature: (Ljava/io/FileDescriptor;)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_socketClose
  (JNIEnv *, jobject, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    getHostByAddr
 * Signature: ([B)Ljava/net/InetAddress;
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getHostByAddr
  (JNIEnv *, jobject, jbyteArray);

JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_getHostByName
  (JNIEnv *, jobject, jstring,jboolean);
  
/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    isReachableByICMPImpl
 * Signature: ([BII)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_isReachableByICMPImpl
  (JNIEnv *, jobject, jobject, jobject, jint, jint);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    inheritedChannel
 * Signature: ([BII)I
 */
JNIEXPORT jobject JNICALL Java_org_apache_harmony_luni_platform_OSNetworkSystem_inheritedChannel
  (JNIEnv *, jobject);

/*
 * Class:     org_apache_harmony_luni_platform_OSNetworkSystem
 * Method:    setInetAddress
 * Signature: (Ljava/net/InetAddress;[B)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSNetworkSystem_setInetAddress
(JNIEnv *, jobject, jobject, jbyteArray);

#ifdef __cplusplus
}
#endif
#endif
