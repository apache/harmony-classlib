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

#include "nethelp.h"
#include "hysock.h"
#include "hyport.h"
#include "jclglob.h"
#include "exceptions.h"

/**
 * Set up JNI ID Caches.
 *
 * @param env           pointer to the JNI library
 *
 */

void
netInitializeIDCaches (JNIEnv * env, jboolean ipv6_support)
{
  jclass lookupClass;
  jmethodID mid;
  jfieldID fid;
  jobject globalRef;

  /* Set the JCL cache to use IPv6 address support */
  JCL_CACHE_SET (env, jcl_supports_ipv6, ipv6_support);

  /* java/lang/Boolean class, constructors, and fids */
  lookupClass = (*env)->FindClass (env, "java/lang/Boolean");
  if (!lookupClass)
    return;
  globalRef = (*env)->NewWeakGlobalRef (env, lookupClass);
  if (!globalRef)
    return;
  mid = (*env)->GetMethodID (env, lookupClass, "<init>", "(Z)V");
  if (!mid)
    return;
  fid = (*env)->GetFieldID (env, lookupClass, "value", "Z");
  if (!fid)
    return;
  JCL_CACHE_SET (env, CLS_java_lang_Boolean, globalRef);
  JCL_CACHE_SET (env, MID_java_lang_Boolean_init, mid);
  JCL_CACHE_SET (env, FID_java_lang_Boolean_value, fid);

  /* java/lang/Byte class, constructors, and fids */
  lookupClass = (*env)->FindClass (env, "java/lang/Byte");
  if (!lookupClass)
    return;
  globalRef = (*env)->NewWeakGlobalRef (env, lookupClass);
  if (!globalRef)
    return;
  mid = (*env)->GetMethodID (env, lookupClass, "<init>", "(B)V");
  if (!mid)
    return;
  fid = (*env)->GetFieldID (env, lookupClass, "value", "B");
  if (!fid)
    return;
  JCL_CACHE_SET (env, CLS_java_lang_Byte, globalRef);
  JCL_CACHE_SET (env, MID_java_lang_Byte_init, mid);
  JCL_CACHE_SET (env, FID_java_lang_Byte_value, fid);

  /* java/lang/Integer class, constructors, and fids */
  lookupClass = (*env)->FindClass (env, "java/lang/Integer");
  if (!lookupClass)
    return;
  globalRef = (*env)->NewWeakGlobalRef (env, lookupClass);
  if (!globalRef)
    return;
  mid = (*env)->GetMethodID (env, lookupClass, "<init>", "(I)V");
  if (!mid)
    return;
  fid = (*env)->GetFieldID (env, lookupClass, "value", "I");
  if (!fid)
    return;
  JCL_CACHE_SET (env, CLS_java_lang_Integer, globalRef);
  JCL_CACHE_SET (env, MID_java_lang_Integer_init, mid);
  JCL_CACHE_SET (env, FID_java_lang_Integer_value, fid);

  /* InetAddress cache setup */
  lookupClass = (*env)->FindClass (env, "java/net/InetAddress");
  if (!lookupClass)
    return;
  globalRef = (*env)->NewWeakGlobalRef (env, lookupClass);
  if (!globalRef)
    return;
  fid = (*env)->GetFieldID (env, lookupClass, "ipaddress", "[B");

  if (!fid)
    return;
  JCL_CACHE_SET (env, CLS_java_net_InetAddress, globalRef);
  JCL_CACHE_SET (env, FID_java_net_InetAddress_address, fid);

  mid = NULL;
  mid =
    (*env)->GetStaticMethodID (env, lookupClass, "preferIPv6Addresses",
             "()Z");
  if (!mid)
    return;
  JCL_CACHE_SET (env, MID_java_net_InetAddress_preferIPv6Addresses, mid);

  if (ipv6_support)
    {
      /* static InetAddress getByAddress( String name, byte[] address ) */
      mid =
        (*env)->GetStaticMethodID (env, lookupClass, "getByAddress",
        "(Ljava/lang/String;[B)Ljava/net/InetAddress;");
      if (!mid)
        return;
      JCL_CACHE_SET (env,
        MID_java_net_InetAddress_getByAddress_Ljava_lang_String_byteArray,
        mid);

      /* static InetAddress getByAddress( byte[] address ) */
      mid =
        (*env)->GetStaticMethodID (env, lookupClass, "getByAddress",
        "([B)Ljava/net/InetAddress;");
      if (!mid)
        return;
      JCL_CACHE_SET (env, MID_java_net_InetAddress_getByAddress_byteArray,
        mid);
    }
  else
    {
      /* InetAddress( byte[] addr ) */
      mid = (*env)->GetMethodID (env, lookupClass, "<init>", "([B)V");
      if (!mid)
        return;
      JCL_CACHE_SET (env, MID_java_net_InetAddress_init_byteArray, mid);

      /* InetAddress( byte[] addr, String address ) */
      mid =
        (*env)->GetMethodID (env, lookupClass, "<init>",
        "([BLjava/lang/String;)V");
      if (!mid)
        return;
      JCL_CACHE_SET (env,
        MID_java_net_InetAddress_init_byteArrayLjava_lang_String,
        mid);
    }

  /* cache Socket class CLS and preferIPv4Socket method */
  lookupClass = (*env)->FindClass (env, "java/net/Socket");
  if (!lookupClass)
    return;
  globalRef = (*env)->NewWeakGlobalRef (env, lookupClass);
  if (!globalRef)
    return;
  mid =
    (*env)->GetStaticMethodID (env, lookupClass, "preferIPv4Stack", "()Z");
  if (!mid)
    return;
  JCL_CACHE_SET (env, CLS_java_net_Socket, globalRef);
  JCL_CACHE_SET (env, MID_java_net_Socket_preferIPv4Stack, mid);

  lookupClass = (*env)->FindClass (env, "java/lang/Thread");
  if (!lookupClass)
    return;
  globalRef = (*env)->NewWeakGlobalRef (env, lookupClass);
  if (!globalRef)
    return;
  mid = (*env)->GetStaticMethodID (env, lookupClass, "yield", "()V");
  if (!mid)
    return;
  JCL_CACHE_SET (env, CLS_java_lang_Thread, globalRef);
  JCL_CACHE_SET (env, MID_java_lang_Thread_yield, mid);

}


/*--------------------------------------
//reflect function
----------------------------------------*/
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
getJavaNetInetAddressGetByAddressByteArray(JNIEnv * env);
jmethodID
getJavaNetInetAddressInitByteArray(JNIEnv * env);

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



/**
 * Set the exception state of the VM with a new java.lang.IllegalArgumentException.
 *
 * @param env         pointer to the JNI library
 * @param errorNumber the error code which lead to the exception
 *
 */

void
throwJavaLangIllegalArgumentException (JNIEnv * env, I_32 errorNumber)
{
  /* the error message lookup should be done before the FindClass
   * call, because the FindClass call may reset the error number that
   * is used in hysock_error_message */
  char *errorMessage = netLookupErrorString (env, errorNumber);
  throwNewExceptionByName(env, "java/lang/IllegalArgumentException",
                          errorMessage);
}

/**
 * Set the exception state of the VM with a new java.net.BindException.
 *
 * @param env         pointer to the JNI library
 * @param errorNumber the error code which lead to the exception
 *
 */

void
throwJavaNetBindException (JNIEnv * env, I_32 errorNumber)
{
  jclass aClass;
  /* the error message lookup should be done before the FindClass
   * call, because the FindClass call may reset the error number that
   * is used in hysock_error_message */
  char *errorMessage = netLookupErrorString (env, errorNumber);
  aClass = (*env)->FindClass (env, "java/net/BindException");
  if (0 == aClass)
    {
      (*env)->ExceptionClear (env);
      aClass = (*env)->FindClass (env, "java/net/SocketException");
      if (0 == aClass)
        {
          return;
        }
    }
  (*env)->ThrowNew (env, aClass, errorMessage);
}

/**
 * Set the exception state of the VM with a new java.net.ConnectException.
 *
 * @param env         pointer to the JNI library
 * @param errorNumber the error code which lead to the exception
 *
 */

void
throwJavaNetConnectException (JNIEnv * env, I_32 errorNumber)
{
  jclass aClass;
  /* the error message lookup should be done before the FindClass call, because the FindClass call
   * may reset the error number that is used in hysock_error_message */
  char *errorMessage = netLookupErrorString (env, errorNumber);
  aClass = (*env)->FindClass (env, "java/net/ConnectException");
  if (0 == aClass)
    {
      (*env)->ExceptionClear (env);
      aClass = (*env)->FindClass (env, "java/net/SocketException");
      if (0 == aClass)
        {
          return;
        }
    }
  (*env)->ThrowNew (env, aClass, errorMessage);
}

/**
 * Set the exception state of the VM with a new java.net.SocketException.
 *
 * @param env         pointer to the JNI library
 * @param errorNumber the error code which lead to the exception
 *
 */

void
throwJavaNetSocketException (JNIEnv * env, I_32 errorNumber)
{
  /* the error message lookup should be done before the FindClass
   * call, because the FindClass call may reset the error number that
   * is used in hysock_error_message */
  jclass socketExClass,errorCodeExClass;
  jmethodID errorCodeExConstructor, socketExConstructor,socketExCauseMethod;
  jobject errorCodeEx,socketEx;
  char *errorMessage = netLookupErrorString (env, errorNumber);
  if (HYPORT_ERROR_SOCKET_WOULDBLOCK == errorNumber){
  	   errorCodeExClass = (*env)->FindClass (env, "org/apache/harmony/luni/util/ErrorCodeException");
  	   if (!errorCodeExClass){
  	           return;
  	   }
  	   errorCodeExConstructor = (*env)->GetMethodID(env,errorCodeExClass,"init","(I)V");
       if (!errorCodeExConstructor){
               return;
       }
       errorCodeEx = (*env)->NewObject(env, errorCodeExClass,errorCodeExConstructor,errorNumber);
       socketExClass = (*env)->FindClass (env, "java/net/SocketException");
  	   if (!socketExClass) {
  	           return;
  	   }
  	   socketExConstructor = (*env)->GetMethodID(env,socketExClass,"init","(Ljava/lang/String;)V");
       if (!socketExConstructor) {
               return;
       }
       socketEx = (*env)->NewObject(env, socketExClass,errorCodeExConstructor,errorMessage); 
       socketExCauseMethod = (*env)->GetMethodID(env,socketExClass,"initCause","(Ljava/lang/Throwable;)Ljava/lang/Throwable;");
       (*env)->CallObjectMethod(env,socketEx,socketExCauseMethod,errorCodeEx);
       (*env)->Throw(env,socketEx);
  }
  throwNewExceptionByName(env, "java/net/SocketException", errorMessage);
}

/**
 * Set the exception state of the VM with a new java.net.UnknownHostException.
 *
 * @param env         pointer to the JNI library
 * @param errorNumber the error code which lead to the exception
 *
 */

void
throwJavaNetUnknownHostException (JNIEnv * env, I_32 errorNumber)
{
  char *errorMessage;
  if (errorNumber == HYPORT_ERROR_SOCKET_SYSTEMFULL)
    {
      throwNewOutOfMemoryError(env, "");
      return;
    }
  /* the error message lookup should be done before the FindClass
   * call, because the FindClass call may reset the error number that
   * is used in hysock_error_message */
  errorMessage = netLookupErrorString (env, errorNumber);
  throwNewExceptionByName(env, "java/net/UnknownHostException", errorMessage);
}

/**
 * Set the exception state of the VM with a new java.io.InterruptedIOException.
 *
 * @param env         pointer to the JNI library
 * @param errorNumber the error code which lead to the exception
 *
 */

void
throwJavaIoInterruptedIOException (JNIEnv * env, I_32 errorNumber)
{
  /* the error message lookup should be done before the FindClass
   * call, because the FindClass call may reset the error number that
   * is used in hysock_error_message */
  char *errorMessage = netLookupErrorString (env, errorNumber);
  throwNewExceptionByName(env, "java/io/InterruptedIOException", errorMessage);
}

/**
 * Answer a new java.lang.Boolean object.
 *
 * @param env   pointer to the JNI library
 * @param aBool the Boolean constructor argument
 *
 * @return  the new Boolean
 */

jobject
newJavaLangBoolean (JNIEnv * env, BOOLEAN aBool)
{
  jclass tempClass;
  jmethodID tempMethod;

  tempClass = JCL_CACHE_GET (env, CLS_java_lang_Boolean);
  //tempClass = getJavaLangBooleanClass(env);
  tempMethod = JCL_CACHE_GET (env, MID_java_lang_Boolean_init);
  //tempMethod = getJavaLangBooleanInit(env);
  return (*env)->NewObject (env, tempClass, tempMethod, (jboolean) aBool);
}

/**
 * Answer a new java.lang.Byte object.
 *
 * @param env   pointer to the JNI library
 * @param aByte the Byte constructor argument
 *
 * @return  the new Byte
 */

jobject
newJavaLangByte (JNIEnv * env, U_8 aByte)
{
  jclass tempClass;
  jmethodID tempMethod;

  tempClass = JCL_CACHE_GET (env, CLS_java_lang_Byte);
  tempMethod = JCL_CACHE_GET (env, MID_java_lang_Byte_init);
  //tempClass = getJavaLangByteClass(env);
  //tempMethod = getJavaLangByteInit(env);
  return (*env)->NewObject (env, tempClass, tempMethod, (jbyte) aByte);
}

/**
 * Answer a new java.lang.Integer object.
 *
 * @param env   pointer to the JNI library
 * @param anInt the Integer constructor argument
 *
 * @return  the new Integer
 */

jobject
newJavaLangInteger (JNIEnv * env, I_32 anInt)
{
  jclass tempClass;
  jmethodID tempMethod;

  tempClass = JCL_CACHE_GET (env, CLS_java_lang_Integer);
  tempMethod = JCL_CACHE_GET (env, MID_java_lang_Integer_init);
  //tempClass = getJavaLangIntegerClass(env);
  //tempMethod = getJavaLangIntegerInit(env);
  return (*env)->NewObject (env, tempClass, tempMethod, (jint) anInt);
}

/**
 * Answer the network address structure for this socket.  A helper method, used
 * to later query a socket bound to the default address/port.  See the 
 * Java_java_net_Socket_netGetSocketLocalAddress/Port functions.
 *
 * @param env         pointer to the JNI library
 * @param fileDescriptor    pointer to the socket file descriptor
 * @param sockAddrResult  pointer pointer to the sockaddr struct to return the address in
 * @param preferIPv6Addresses on V4/V6 nodes, a preference as to which address to return for the node
 *
 * @return  0, if no errors occurred, otherwise the (negative) error code
 */

I_32
netGetSockAddr (JNIEnv * env, jobject fileDescriptor, hysockaddr_t sockaddrP,
    jboolean preferIPv6Addresses)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result = 0;
  hysocket_t socketP;
  U_8 ipAddr[HYSOCK_INADDR6_LEN];
  memset (ipAddr, 0, HYSOCK_INADDR6_LEN);

  socketP = getJavaIoFileDescriptorContentsAsAPointer (env, fileDescriptor);
  if (!hysock_socketIsValid (socketP))
    {
      return HYPORT_ERROR_SOCKET_UNKNOWNSOCKET;
    }
  else
    {
      if (preferIPv6Addresses)
        {
          hysock_sockaddr_init6 (sockaddrP, ipAddr, HYSOCK_INADDR6_LEN,
            HYADDR_FAMILY_UNSPEC, 0, 0, 0, socketP);
          result = hysock_getsockname (socketP, sockaddrP);
        }
      else
        {
          hysock_sockaddr_init6 (sockaddrP, ipAddr, HYSOCK_INADDR_LEN,
            HYADDR_FAMILY_AFINET4, 0, 0, 0, socketP);
          result = hysock_getsockname (socketP, sockaddrP);
        }
      return result;
    }
}

/**
 * Answer the 'value' field value from a java.lang.Boolean
 *
 * @param env     pointer to the JNI library
 * @param aBoolean    the object to access the 'value' field of
 *
 * @return  the 'value' field boolean value (completion status is not returned)
 */

BOOLEAN
booleanValue (JNIEnv * env, jobject aBoolean)
{
  return (BOOLEAN) ((*env)->
        GetBooleanField (env, aBoolean,
             JCL_CACHE_GET (env,
                FID_java_lang_Boolean_value)));
  /*return (BOOLEAN) ((*env)->
        GetBooleanField (env, aBoolean,
             getJavaLangBooleanValue(env)));*/
}

/**
 * Answer the 'value' field value from a java.lang.Byte
 *
 * @param env     pointer to the JNI library
 * @param aByte   the object to access the 'value' field of
 *
 * @return  the 'value' field byte value (completion status is not returned)
 */

U_8
byteValue (JNIEnv * env, jobject aByte)
{
  return (U_8) ((*env)->
    GetByteField (env, aByte,
            JCL_CACHE_GET (env, FID_java_lang_Byte_value)));
  /*return (U_8) ((*env)->
    GetByteField (env, aByte,
            getJavaLangByteValue(env)));*/
}

/**
 * Answer the 'value' field value from a java.lang.Integer
 *
 * @param env       pointer to the JNI library
 * @param anInteger   the object to access the 'value' field of
 *
 * @return  the 'value' field integer value (completion status is not returned)
 */

I_32
intValue (JNIEnv * env, jobject anInteger)
{
  return (I_32) ((*env)->
     GetIntField (env, anInteger,
            JCL_CACHE_GET (env,
               FID_java_lang_Integer_value)));
  /*return (I_32) ((*env)->
     GetIntField (env, anInteger,
            getJavaLangIntegerValue(env)));*/
}

/**
 * Answer the errorString corresponding to the errorNumber, if available.
 * This function will answer a default error string, if the errorNumber is not
 * recognized.
 *
 * This function will have to be reworked to handle internationalization properly, removing
 * the explicit strings.
 *
 * @param anErrorNum    the error code to resolve to a human readable string
 *
 * @return  a human readable error string
 */

char *
netLookupErrorString (JNIEnv * env, I_32 anErrorNum)
{
  PORT_ACCESS_FROM_ENV (env);
  switch (anErrorNum)
    {
    case HYPORT_ERROR_SOCKET_BADSOCKET:
      return "Bad socket";
    case HYPORT_ERROR_SOCKET_NOTINITIALIZED:
      return "Socket library uninitialized";
    case HYPORT_ERROR_SOCKET_BADAF:
      return "Bad address family";
    case HYPORT_ERROR_SOCKET_BADPROTO:
      return "Bad protocol";
    case HYPORT_ERROR_SOCKET_BADTYPE:
      return "Bad type";
    case HYPORT_ERROR_SOCKET_SYSTEMBUSY:
      return "System busy handling requests";
    case HYPORT_ERROR_SOCKET_SYSTEMFULL:
      return "Too many sockets allocated";
    case HYPORT_ERROR_SOCKET_NOTCONNECTED:
      return "Socket is not connected";
    case HYPORT_ERROR_SOCKET_INTERRUPTED:
      return "The call was cancelled";
    case HYPORT_ERROR_SOCKET_TIMEOUT:
      return "The operation timed out";
    case HYPORT_ERROR_SOCKET_CONNRESET:
      return "The connection was reset";
    case HYPORT_ERROR_SOCKET_WOULDBLOCK:
      return "The socket is marked as nonblocking operation would block";
    case HYPORT_ERROR_SOCKET_ADDRNOTAVAIL:
      return "The address is not available";
    case HYPORT_ERROR_SOCKET_ADDRINUSE:
      return "The address is already in use";
    case HYPORT_ERROR_SOCKET_NOTBOUND:
      return "The socket is not bound";
    case HYPORT_ERROR_SOCKET_UNKNOWNSOCKET:
      return "Resolution of the FileDescriptor to socket failed";
    case HYPORT_ERROR_SOCKET_INVALIDTIMEOUT:
      return "The specified timeout is invalid";
    case HYPORT_ERROR_SOCKET_FDSETFULL:
      return "Unable to create an FDSET";
    case HYPORT_ERROR_SOCKET_TIMEVALFULL:
      return "Unable to create a TIMEVAL";
    case HYPORT_ERROR_SOCKET_REMSOCKSHUTDOWN:
      return "The remote socket has shutdown gracefully";
    case HYPORT_ERROR_SOCKET_NOTLISTENING:
      return "Listen() was not invoked prior to accept()";
    case HYPORT_ERROR_SOCKET_NOTSTREAMSOCK:
      return "The socket does not support connection-oriented service";
    case HYPORT_ERROR_SOCKET_ALREADYBOUND:
      return "The socket is already bound to an address";
    case HYPORT_ERROR_SOCKET_NBWITHLINGER:
      return "The socket is marked non-blocking & SO_LINGER is non-zero";
    case HYPORT_ERROR_SOCKET_ISCONNECTED:
      return "The socket is already connected";
    case HYPORT_ERROR_SOCKET_NOBUFFERS:
      return "No buffer space is available";
    case HYPORT_ERROR_SOCKET_HOSTNOTFOUND:
      return "Authoritative Answer Host not found";
    case HYPORT_ERROR_SOCKET_NODATA:
      return "Valid name, no data record of requested type";
    case HYPORT_ERROR_SOCKET_BOUNDORCONN:
      return "The socket has not been bound or is already connected";
    case HYPORT_ERROR_SOCKET_OPNOTSUPP:
      return "The socket does not support the operation";
    case HYPORT_ERROR_SOCKET_OPTUNSUPP:
      return "The socket option is not supported";
    case HYPORT_ERROR_SOCKET_OPTARGSINVALID:
      return "The socket option arguments are invalid";
    case HYPORT_ERROR_SOCKET_SOCKLEVELINVALID:
      return "The socket level is invalid";
    case HYPORT_ERROR_SOCKET_TIMEOUTFAILURE:
      return "The timeout operation failed";
    case HYPORT_ERROR_SOCKET_SOCKADDRALLOCFAIL:
      return "Failed to allocate address structure";
    case HYPORT_ERROR_SOCKET_FDSET_SIZEBAD:
      return "The calculated maximum size of the file descriptor set is bad";
    case HYPORT_ERROR_SOCKET_UNKNOWNFLAG:
      return "The flag is unknown";
    case HYPORT_ERROR_SOCKET_MSGSIZE:
      return
  "The datagram was too big to fit the specified buffer, so truncated";
    case HYPORT_ERROR_SOCKET_NORECOVERY:
      return "The operation failed with no recovery possible";
    case HYPORT_ERROR_SOCKET_ARGSINVALID:
      return "The arguments are invalid";
    case HYPORT_ERROR_SOCKET_BADDESC:
      return "The socket argument is not a valid file descriptor";
    case HYPORT_ERROR_SOCKET_NOTSOCK:
      return "The socket argument is not a socket";
    case HYPORT_ERROR_SOCKET_HOSTENTALLOCFAIL:
      return "Unable to allocate the hostent structure";
    case HYPORT_ERROR_SOCKET_TIMEVALALLOCFAIL:
      return "Unable to allocate the timeval structure";
    case HYPORT_ERROR_SOCKET_LINGERALLOCFAIL:
      return "Unable to allocate the linger structure";
    case HYPORT_ERROR_SOCKET_IPMREQALLOCFAIL:
      return "Unable to allocate the ipmreq structure";
    case HYPORT_ERROR_SOCKET_FDSETALLOCFAIL:
      return "Unable to allocate the fdset structure";
    case HYPORT_ERROR_SOCKET_CONNECTION_REFUSED:
      return "Connection refused";

    default:
      return (char *) hysock_error_message ();
    }
}


/**
 * Answer the 'address' field value from a java.net.InetAddress
 *
 * @param env           pointer to the JNI library
 * @param anInetAddress   the object to access the 'value' field of
 *
 * @return  the 'address' field integer value (completion status is not returned)
 */

void
netGetJavaNetInetAddressValue (JNIEnv * env, jobject anInetAddress,
             U_8 * buffer, U_32 * length)
{
  jbyteArray byte_array =
    (jbyteArray) ((*env)->GetObjectField (env, anInetAddress,
            JCL_CACHE_GET (env,
               FID_java_net_InetAddress_address)));
  /*jbyteArray byte_array =
    (jbyteArray) ((*env)->GetObjectField (env, anInetAddress,
            getJavaNetInetAddressIpaddress(env)));*/
  *length = (*env)->GetArrayLength (env, byte_array);
  (*env)->GetByteArrayRegion (env, byte_array, 0, *length, (jbyte *)buffer);
}

/**
 * Call the java.lang.Thread.yield() method.
 *
 * @param env   pointer to the JNI library
 *
 */

void
callThreadYield (JNIEnv * env)
{
  jmethodID tempMethod;
  jclass tempClass;
  jobject globalRef;

  tempClass = JCL_CACHE_GET (env, CLS_java_lang_Thread);
  tempMethod = JCL_CACHE_GET (env, MID_java_lang_Thread_yield);
  
  //tempClass = getJavaLangThreadClass(env);
  //tempMethod = getJavaLangThreadYield(env);
  if (tempClass == 0)
    {
      tempClass = (*env)->FindClass (env, "java/lang/Thread");
      if (!tempClass)
        return;
      globalRef = (*env)->NewWeakGlobalRef (env, tempClass);
      if (!globalRef)
        return;
      tempMethod = (*env)->GetStaticMethodID (env, tempClass, "yield", "()V");
      if (!tempMethod)
        return;
      JCL_CACHE_SET (env, CLS_java_lang_Thread, globalRef);
      JCL_CACHE_SET (env, MID_java_lang_Thread_yield, tempMethod);      
    }
  (*env)->CallStaticVoidMethod (env, tempClass, tempMethod);
}

/**
 * A helper method, to construct an array of InetAddress's, based upon the nominated host name
 * and array of alternate addresses.  Used by the netGetAliasesByAddr/Name functions.  If the host
 * does not have aliases (only multi-homed hosts do), answer an array with a single InetAddress
 * constructed from the host name & address.
 *
 * @param env       pointer to the JNI library
 * @param addresses     an array of host addresses
 * @param family      an array of families matching host addresses
 * @param count     the number of addresses
 * @param hName     the host name
 * @param h_aliases pointer to the array of alternate addresses
 * @param  scope_id_array scope ids for the addresses
 *
 * @return  an array of InetAddress's
 */

jobjectArray
createAliasArray (JNIEnv * env, jbyte ** addresses, I_32 * family, U_32 count,
      char *hName, U_32 * scope_id_array)
{
  U_32 i, length;
  jobjectArray aliases = NULL;
  jobject element;
  jclass tempClass = (*env)->FindClass (env, "java/net/InetAddress");

  /* Number of structures */
  for (i = 0; i < count; i++)
    {

      /* Create the byte array for the appropriate family and fill it in */
      if (family[i] == HYADDR_FAMILY_AFINET4)
        {
          length = HYSOCK_INADDR_LEN;
        }
      else
        {
          length = HYSOCK_INADDR6_LEN;
        }

      element =
        newJavaNetInetAddressGenericBS (env, addresses[i], length, hName,
        scope_id_array[i]);

      if (i == 0)
        {
          aliases = (*env)->NewObjectArray (env, count, tempClass, element);
        }
      else
        {
          (*env)->SetObjectArrayElement (env, aliases, i, element);
        }
    }
  return aliases;
}

/**
 * A helper method, to construct an array of InetAddress's, based upon the nominated host name
 * and array of alternate addresses.  Used by the netGetAliasesByAddr/Name functions.  If the host
 * does not have aliases (only multi-homed hosts do), answer an array with a single InetAddress
 * constructed from the host name & address.
 *
 * @param env           pointer to the JNI library
 * @param addresses     addrinfos to take the ip addresses from
 * @param hName         the host name
 * @param h_aliases pointer to the array of alternate addresses
 *
 * @return  an array of InetAddress's
 */

jobjectArray
createAliasArrayFromAddrinfo (JNIEnv * env, hyaddrinfo_t addresses,
            char *hName)
{
  PORT_ACCESS_FROM_ENV (env);
  U_32 count = 0;
  U_32 i, j;
  I_32 length = 0;
  jbyte **aliasList;
  I_32 *family;
  U_32 *scope_id_array;
  U_32 mem_size;
  BOOLEAN contains;
  jbyte temp_address[HYSOCK_INADDR6_LEN];
  jobjectArray inetAddressArray;
  U_32 scope_id = 0;

  hysock_getaddrinfo_length (addresses, &length);

  /* The array needs to be big enough to hold an aliases and an address for each entry */
  mem_size = length * sizeof (jbyte *);
  aliasList = hymem_allocate_memory (mem_size);
  family = hymem_allocate_memory (length * sizeof (I_32));
  scope_id_array = hymem_allocate_memory (length * sizeof (U_32));
  memset (aliasList, 0, mem_size);

  for (i = 0; i < (U_32) length; i++)
    {
      memset (temp_address, 0, HYSOCK_INADDR6_LEN);
      hysock_getaddrinfo_address (addresses, (U_8 *)temp_address, i, &scope_id);

      /* On some platforms we get duplicate addresses back for each protocol type, 
      we only want 1 per list, so we're filtering duplicates */
      contains = FALSE;
      for (j = 0; j < count; j++)
        {
          if (!memcmp (temp_address, aliasList[j], HYSOCK_INADDR6_LEN))
            {
              contains = TRUE;
            }
        }
      if (!contains)
        {
          aliasList[count] =
            (jbyte *) hymem_allocate_memory (HYSOCK_INADDR6_LEN);
          hysock_getaddrinfo_family (addresses, &family[count], i);
          scope_id_array[count] = scope_id;
          memcpy (aliasList[count++], temp_address, HYSOCK_INADDR6_LEN);
        }
    }
  inetAddressArray =
    createAliasArray (env, aliasList, family, count, hName, scope_id_array);

  for (i = 0; i < count; i++)
    {
      hymem_free_memory ( aliasList[i]);
    }
  hymem_free_memory (family);
  hymem_free_memory (scope_id_array);
  hymem_free_memory (aliasList);

  return inetAddressArray;
}

/**
 * Answers whether or not the jcl supports IPv6.
 *
 * @param env     pointer to the JNI library
 * @return                  a boolean
 */

BOOLEAN
jcl_supports_ipv6 (JNIEnv * env)
{
  return (BOOLEAN) JCL_CACHE_GET (env, jcl_supports_ipv6);
  //return (BOOLEAN) support;
}

/**
 * Answer a new byte[] object.
 *
 * @param env       pointer to the JNI library
 * @param bytes     bytes to write to the new ByteArray
 *
 * @return  the new InetAddress
 */

jobject
newJavaByteArray (JNIEnv * env, jbyte * bytes, jint length)
{
  jbyteArray byte_array = (*env)->NewByteArray (env, length);
  if (byte_array == NULL)
    {
      return NULL;
    }
  (*env)->SetByteArrayRegion (env, byte_array, 0, length, bytes);

  return byte_array;
}

/**
 * Answer a new java.net.InetAddress object.
 *
 * @param env       pointer to the JNI library
 * @param address   the ip address as a byte array, in network order
 * @param length      the number of bytes in the address
 * @param scope_id    the scope id for the address if applicable.  Otherwise should be 0
 *
 * Determines whether to return a InetAddress in the case of IPv4, or Inet4Address/Inet6Address in the case of IPv6
 * @return  the new InetAddress
 */

jobject
newJavaNetInetAddressGenericB (JNIEnv * env, jbyte * address, U_32 length,
             U_32 scope_id)
{
  jclass tempClass;
  jmethodID tempMethod;
  jmethodID tempMethodWithScope = NULL;
  jbyteArray byte_array;
  BOOLEAN isAnyAddress = 1;
  static jbyte IPv4ANY[4] = { 0, 0, 0, 0 };
  static jbyte IPv6ANY[16] =
    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
  U_32 i = 0;

  /* check if the address being returned is the any address.  If so we need to check the prefer flags to see how it should be returned
  (either as IPv4 Any or IPv6 ANY) */

  if (jcl_supports_ipv6 (env))
    {
      /* Figure out if it is the any address */
      for (i = 0; i < length; i++)
        {
          if (address[i] != 0)
            {
              isAnyAddress = 0;
              break;
            }
        }
    }
  else
    {
      /* just do what we used to do without checking */
      isAnyAddress = 0;
    }

  /* If it is the any address then set up to return appropriately based on the flags */
  if (isAnyAddress)
    {
      if ((!preferIPv4Stack (env)) && (preferIPv6Addresses (env)))
        {
          if ((byte_array =
            newJavaByteArray (env, IPv6ANY, sizeof (IPv6ANY))) == NULL)
            {
              return NULL;
            }
        }
      else
        {
          if ((byte_array =
            newJavaByteArray (env, IPv4ANY, sizeof (IPv4ANY))) == NULL)
            {
              return NULL;
            }
        }
    }
  else
    {
      /* not any so just set up to return the address normally */
      if ((byte_array = newJavaByteArray (env, address, length)) == NULL)
        {
          return NULL;
        }
    }

  if (jcl_supports_ipv6 (env))
    {
      tempMethodWithScope = NULL;
      if (scope_id != 0)
        {
          tempMethodWithScope =
            (*env)->GetStaticMethodID (env,
            JCL_CACHE_GET (env,
            CLS_java_net_InetAddress),
            "getByAddress",
            "([BI)Ljava/net/InetAddress;");
          
          /*tempMethodWithScope =
            (*env)->GetStaticMethodID (env,
            getJavaNetInetAddressClass(env),
            "getByAddress",
            "([BI)Ljava/net/InetAddress;");*/
          if ((*env)->ExceptionCheck (env))
            {
              (*env)->ExceptionClear (env);
              tempMethodWithScope = NULL;
            }
        }

      if (tempMethodWithScope != NULL)
        {
          /* create using the scope id */
          tempClass = JCL_CACHE_GET (env, CLS_java_net_InetAddress);
          //tempClass = getJavaNetInetAddressClass(env);
          return (*env)->CallStaticObjectMethod (env, tempClass,
            tempMethodWithScope,
            byte_array, scope_id);
        }
      else
        {
          tempClass = JCL_CACHE_GET (env, CLS_java_net_InetAddress);
          //tempClass = getJavaNetInetAddressClass(env);
          tempMethod =
            JCL_CACHE_GET (env,
            MID_java_net_InetAddress_getByAddress_byteArray);
          //tempMethod = getJavaNetInetAddressGetByAddressByteArray(env);
          return (*env)->CallStaticObjectMethod (env, tempClass, tempMethod,
            byte_array);
        }
    }
  else
    {
      tempClass = JCL_CACHE_GET (env, CLS_java_net_InetAddress);
      tempMethod =
        JCL_CACHE_GET (env, MID_java_net_InetAddress_init_byteArray);
      //tempClass = getJavaNetInetAddressClass(env);
      //tempMethod = getJavaNetInetAddressInitByteArray(env);
      return (*env)->NewObject (env, tempClass, tempMethod, byte_array);
    }
}

/**
 * Answer a new java.net.InetAddress object.
 *
 * @param env       pointer to the JNI library
 * @param address     the ip address as a byte array, in network order
 * @param length      the number of bytes in the address
 * @param hostName    the host name 
 * @param scope_id    the scope id for the address if applicable.  Otherwise should be 0
 *
 * Determines whether to return a InetAddress in the case of IPv4, or Inet4Address/Inet6Address in the case of IPv6
 * @return  the new InetAddress
 */

jobject
newJavaNetInetAddressGenericBS (JNIEnv * env, jbyte * address, U_32 length,
        char *hostName, U_32 scope_id)
{
  jclass tempClass;
  jmethodID tempMethod;
  jmethodID tempMethodWithScope = NULL;
  jbyteArray byte_array;
  jstring aString;
  BOOLEAN isAnyAddress = 1;
  static jbyte IPv4ANY[4] = { 0, 0, 0, 0 };
  static jbyte IPv6ANY[16] =
    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
  U_32 i = 0;

  aString = (*env)->NewStringUTF (env, hostName);

  /* check if the address being returned is the any address.  If so we need to check the prefer flags to see how it should be returned
     (either as IPv4 Any or IPv6 ANY) */

  if (jcl_supports_ipv6 (env))
    {
      /* Figure out if it is the any address */
      for (i = 0; i < length; i++)
        {
          if (address[i] != 0)
            {
              isAnyAddress = 0;
              break;
            }
        }
    }
  else
    {
      /* just do what we used to do without checking */
      isAnyAddress = 0;
    }

  /* If it is the any address then set up to return appropriately based on the flags */
  if (isAnyAddress)
    {
      if ((!preferIPv4Stack (env)) && (preferIPv6Addresses (env)))
        {
          if ((byte_array =
            newJavaByteArray (env, IPv6ANY, sizeof (IPv6ANY))) == NULL)
            {
              return NULL;
            }
        }
      else
        {
          if ((byte_array =
            newJavaByteArray (env, IPv4ANY, sizeof (IPv4ANY))) == NULL)
            {
              return NULL;
            }
        }
    }
  else
    {
      /* not any so just set up to return the address normally */
      if ((byte_array = newJavaByteArray (env, address, length)) == NULL)
        {
          return NULL;
        }
    }

  if (jcl_supports_ipv6 (env))
    {
      tempMethodWithScope = NULL;
      if (scope_id != 0)
        {
          tempMethodWithScope =
            (*env)->GetStaticMethodID (env,
            JCL_CACHE_GET (env,
            CLS_java_net_InetAddress),
            "getByAddress",
            "(Ljava/lang/String;[BI)Ljava/net/InetAddress;");
          
          /*tempMethodWithScope =
            (*env)->GetStaticMethodID (env,
            getJavaNetInetAddressClass(env),
            "getByAddress",
            "(Ljava/lang/String;[BI)Ljava/net/InetAddress;");*/
          if ((*env)->ExceptionCheck (env))
            {
              (*env)->ExceptionClear (env);
              tempMethodWithScope = NULL;
            }
        }

      if (tempMethodWithScope != NULL)
        {
          /* create using the scope id */
          tempClass = JCL_CACHE_GET (env, CLS_java_net_InetAddress);
          //tempClass = getJavaNetInetAddressClass(env);
          return (*env)->CallStaticObjectMethod (env, tempClass,
            tempMethodWithScope, aString,
            byte_array, scope_id);
        }
      else
        {
          tempClass = JCL_CACHE_GET (env, CLS_java_net_InetAddress);
          tempMethod =
            JCL_CACHE_GET (env,
            MID_java_net_InetAddress_getByAddress_Ljava_lang_String_byteArray);
          
          //tempClass = getJavaNetInetAddressClass(env);
          //tempMethod = getJavaNetInetAddressGetByAddressStringByte(env);
          return (*env)->CallStaticObjectMethod (env, tempClass, tempMethod,
            aString, byte_array);
        }
      }
    else
      {
        tempClass = JCL_CACHE_GET (env, CLS_java_net_InetAddress);
        tempMethod =
          JCL_CACHE_GET (env,
          MID_java_net_InetAddress_init_byteArrayLjava_lang_String);
        
        //tempClass = getJavaNetInetAddressClass(env);
        //tempMethod = getJavaNetInetAddressInitByteString(env);        
        return (*env)->NewObject (env, tempClass, tempMethod, byte_array,
          aString);
    }
}

/**
 * Set the exception state of the VM with a new java.net.PortUnreachableException.
 *
 * @param env         pointer to the JNI library
 * @param errorNumber the error code which lead to the exception
 *
 */

void
throwJavaNetPortUnreachableException (JNIEnv * env, I_32 errorNumber)
{
  /* the error message lookup should be done before the FindClass
   * call, because the FindClass call may reset the error number that
   * is used in hysock_error_message */
  char *errorMessage = netLookupErrorString (env, errorNumber);
  throwNewExceptionByName(env, "java/net/PortUnreachableException",
                          errorMessage);
}

/**
 * Set the exception state of the VM with a new java.net.SocketTimeoutException.
 *
 * @param env         pointer to the JNI library
 * @param errorNumber the error code which lead to the exception
 *
 */

void
throwJavaNetSocketTimeoutException (JNIEnv * env, I_32 errorNumber)
{
  /* the error message lookup should be done before the FindClass
   * call, because the FindClass call may reset the error number that
   * is used in hysock_error_message */
  char *errorMessage = netLookupErrorString (env, errorNumber);
  throwNewExceptionByName(env, "java/net/SocketTimeoutException", errorMessage);
}

/**
 * Answers whether or not we should prefer the IPv4 stack
 *
 * @param env     pointer to the JNI library
 * @return                  a boolean
 */

BOOLEAN
preferIPv4Stack (JNIEnv * env)
{
  BOOLEAN result = FALSE;

  /* if the jcl does not support IPV6 then just use the IPV4 stack without checking
     the values of the flags */
  if (!jcl_supports_ipv6 (env))
    {
      return TRUE;
    }

  result =
    (*env)->CallStaticBooleanMethod (env,
             JCL_CACHE_GET (env, CLS_java_net_Socket),
             JCL_CACHE_GET (env,
                MID_java_net_Socket_preferIPv4Stack));
  
  /*result =
    (*env)->CallStaticBooleanMethod (env,
             getJavaNetSocketClass(env),
             getJavaNetSocketPreferIPv4Stack(env));*/
  if ((*env)->ExceptionCheck (env))
    {
      /* older JCLs do not have the right code for security checks so this may fail with an exception
         just clear the exception and set the value to false which is the default */
      (*env)->ExceptionClear (env);
      return FALSE;
    }
  else
    {
      return result;
    }
}

/**
 * Answers whether or not we should prefer the IPv4 stack
 *
 * @param env     pointer to the JNI library
 * @return                  a boolean
 */

BOOLEAN
preferIPv6Addresses (JNIEnv * env)
{
  BOOLEAN result = FALSE;

  /* if the jcl does not support IPV6 then we don't prefer IPv6 addresses for any case so just return false 
     without checking the values of the flags */
  if (!jcl_supports_ipv6 (env))
    {
      return FALSE;
    }

  result =
    (*env)->CallStaticBooleanMethod (env,
             JCL_CACHE_GET (env,
                CLS_java_net_InetAddress),
             JCL_CACHE_GET (env,
                MID_java_net_InetAddress_preferIPv6Addresses));
  /*
  result =
    (*env)->CallStaticBooleanMethod (env,
             getJavaNetInetAddressClass(env),
             getJavaNetInetAddressPreferIPv6Addresses(env));*/ 
  if ((*env)->ExceptionCheck (env))
    {
      /* older JCLs do not have the right code for security checks so this may fail with an exception
         just clear the exception and set the value to false which is the default */
      (*env)->ExceptionClear (env);
      return FALSE;
    }
  else
    {
      return result;
    }
}

/**
 * Answer the 'scope_id' field value from a java.net.InetAddress
 *
 * @param env           pointer to the JNI library
 * @param anInetAddress   the object to access the 'value' field 
 * @param   scope_id             pointer to the integer in which the scope_id value should be returned
 *
 */
void
netGetJavaNetInetAddressScopeId (JNIEnv * env, jobject anInetAddress,
         U_32 * scope_id)
{
  jfieldID scopeFid;
  jclass inet6AddressClass = (*env)->FindClass (env, "java/net/Inet6Address");

  /* only inet6 addresses have the scope id so only get the value for this type of InetAddress */
  if ((!((*env)->ExceptionCheck (env)))
    && (*env)->IsInstanceOf (env, anInetAddress, inet6AddressClass))
    {
      scopeFid = (*env)->GetFieldID (env, inet6AddressClass, "scope_id", "I");
      /* this is to support older jcls that did not have the scope id */
      if (!((*env)->ExceptionCheck (env)))
        {
          *scope_id = (*env)->GetIntField (env, anInetAddress, scopeFid);
        }
      else
        {
          *scope_id = 0;
        }
    }
  else
    {
      *scope_id = 0;
    }

  /* clear any exception that might have occured */
  (*env)->ExceptionClear (env);

}

jclass 
getJavaLangBooleanClass(JNIEnv * env){
  jclass lookupClass = (*env)->FindClass (env, "java/lang/Boolean");
  if (!lookupClass)
    return NULL;
  return lookupClass;
}

jmethodID
getJavaLangBooleanInit(JNIEnv * env){
  jmethodID mid;
  jclass lookupClass = (*env)->FindClass (env, "java/lang/Boolean");
  if (!lookupClass)
    return NULL;
  mid = (*env)->GetMethodID (env, lookupClass, "<init>", "(Z)V");
  if (!mid)
    return NULL;
  return mid;
}

jfieldID
getJavaLangBooleanValue(JNIEnv * env){
  jmethodID mid;
  jfieldID fid;
  jclass lookupClass = (*env)->FindClass (env, "java/lang/Boolean");
  if (!lookupClass)
    return NULL;
  mid = (*env)->GetMethodID (env, lookupClass, "<init>", "(Z)V");
  if (!mid)
    return NULL;
  fid = (*env)->GetFieldID (env, lookupClass, "value", "Z");
  if (!fid)
    return NULL;
  return fid;
}

jclass
getJavaLangByteClass(JNIEnv * env){
  jclass lookupClass = (*env)->FindClass (env, "java/lang/Byte");
  if (!lookupClass)
    return NULL;
  return lookupClass;
}

jmethodID
getJavaLangByteInit(JNIEnv * env){
  jmethodID mid;
  jclass lookupClass = (*env)->FindClass (env, "java/lang/Byte");
  if (!lookupClass)
    return NULL;
  mid = (*env)->GetMethodID (env, lookupClass, "<init>", "(B)V");
  if (!mid)
    return NULL;
  return mid;
}

jfieldID
getJavaLangByteValue(JNIEnv * env){
  jmethodID mid;
  jfieldID fid;
  jclass lookupClass = (*env)->FindClass (env, "java/lang/Byte");
  if (!lookupClass)
    return NULL;
  mid = (*env)->GetMethodID (env, lookupClass, "<init>", "(B)V");
  if (!mid)
    return NULL;
  fid = (*env)->GetFieldID (env, lookupClass, "value", "B");
  if (!fid)
    return NULL;
  return fid;
}

jclass
getJavaLangIntegerClass(JNIEnv * env){
  jclass lookupClass = (*env)->FindClass (env, "java/lang/Integer");
  if (!lookupClass)
    return NULL;
  return lookupClass;
}

jmethodID
getJavaLangIntegerInit(JNIEnv * env){
  jmethodID mid;
  jclass lookupClass = (*env)->FindClass (env, "java/lang/Integer");
  if (!lookupClass)
    return NULL;
  mid =  (*env)->GetMethodID (env, lookupClass, "<init>", "(I)V");
  return mid;
}

jfieldID
getJavaLangIntegerValue(JNIEnv * env){
  jfieldID fid;
  jclass lookupClass = (*env)->FindClass (env, "java/lang/Integer");
  if (!lookupClass)
    return NULL;
  fid =  (*env)->GetFieldID (env, lookupClass, "value", "I");
  if (!fid)
    return NULL;
  return fid;
}

/* already impl
jmethodID
getJavaLangByteInit(JNIEnv * env){
  jmethodID mid;
  jclass lookupClass = (*env)->FindClass (env, "java/lang/Integer");
  if (!lookupClass)
    return NULL;
  mid = (*env)->GetMethodID (env, lookupClass, "<init>", "(I)V");
  if (!mid)
    return NULL;
  return mid;
}

jfieldID
getJavaLangByteValue(JNIEnv * env){
  jmethodID mid;
  jfieldID fid;
  jclass lookupClass = (*env)->FindClass (env, "java/lang/Integer");
  if (!lookupClass)
    return NULL;
  mid = (*env)->GetMethodID (env, lookupClass, "<init>", "(I)V");
  if (!mid)
    return NULL;
  fid = (*env)->GetFieldID (env, lookupClass, "value", "I");
  if (!fid)
    return NULL;
  return fid;
}
*/

jclass
getJavaNetInetAddressClass(JNIEnv * env){
  jclass lookupClass = (*env)->FindClass (env, "java/net/InetAddress");
  if (!lookupClass)
    return NULL;
  return lookupClass;
}

jfieldID
getJavaNetInetAddressIpaddress(JNIEnv * env){
  jfieldID fid;
  jclass lookupClass = (*env)->FindClass (env, "java/net/InetAddress");
  if (!lookupClass)
    return NULL;
  fid = (*env)->GetFieldID (env, lookupClass, "ipaddress", "[B");
  if (!fid)
    return NULL;
  return fid;
}

jmethodID
getJavaNetInetAddressPreferIPv6Addresses(JNIEnv * env){
  jmethodID mid;
  jclass lookupClass = (*env)->FindClass (env, "java/net/InetAddress");
  if (!lookupClass)
    return NULL;
  mid = (*env)->GetStaticMethodID (env, lookupClass, "preferIPv6Addresses",
             "()Z");
  if (!mid)
    return NULL;
  return mid;
}

jmethodID
getJavaNetInetAddressGetByAddressStringByte(JNIEnv * env){
  jmethodID mid;
  jclass lookupClass = (*env)->FindClass (env, "java/net/InetAddress");
  if (!lookupClass)
    return NULL;
  mid =
        (*env)->GetStaticMethodID (env, lookupClass, "getByAddress",
        "(Ljava/lang/String;[B)Ljava/net/InetAddress;");
  if (!mid)
        return NULL;
  return mid;
}

jmethodID
getJavaNetInetAddressGetByAddressByteArray(JNIEnv * env){
  jmethodID mid;
  jclass lookupClass = (*env)->FindClass (env, "java/net/InetAddress");
  if (!lookupClass)
    return NULL;
  mid =
        (*env)->GetStaticMethodID (env, lookupClass, "getByAddress",
        "([B)Ljava/net/InetAddress;");
  if (!mid)
        return NULL;
  return mid;
}
jmethodID
getJavaNetInetAddressInitByteArray(JNIEnv * env){
  jmethodID mid;
  jclass lookupClass = (*env)->FindClass (env, "java/net/InetAddress");
  if (!lookupClass)
    return NULL;
  mid = (*env)->GetMethodID (env, lookupClass, "<init>", "([B)V");
  if (!mid)
        return NULL;
  return mid;
}

jmethodID
getJavaNetInetAddressInitByteString(JNIEnv * env){
  jmethodID mid;
  jclass lookupClass = (*env)->FindClass (env, "java/net/InetAddress");
  if (!lookupClass)
    return NULL;
  mid = (*env)->GetMethodID (env, lookupClass, "<init>",
        "([BLjava/lang/String;)V");
  if (!mid)
        return NULL;
  return mid;
}

jclass
getJavaNetSocketClass(JNIEnv * env){
  jclass lookupClass = (*env)->FindClass (env, "java/net/Socket");
  if (!lookupClass)
    return NULL;
  return lookupClass;
}

jmethodID
getJavaNetSocketPreferIPv4Stack(JNIEnv * env){
  jmethodID mid;
  jclass lookupClass = (*env)->FindClass (env, "java/net/Socket");
  if (!lookupClass)
    return NULL;
  mid = (*env)->GetStaticMethodID (env, lookupClass, "preferIPv4Stack", "()Z");
  if (!mid)
    return NULL;
  return mid;
}

jclass
getJavaLangThreadClass(JNIEnv * env){
  jclass lookupClass = (*env)->FindClass (env, "java/lang/Thread");
  if (!lookupClass)
    return NULL;
  return lookupClass;
}

jmethodID
getJavaLangThreadYield(JNIEnv * env){
  jmethodID mid;
  jclass lookupClass = (*env)->FindClass (env, "java/lang/Thread");
  if (!lookupClass)
    return NULL;
  mid = (*env)->GetStaticMethodID (env, lookupClass, "yield", "()V");
  if (!mid)
    return NULL;
  return mid;
}

jclass
getJavaNetDatagramPacketClass(JNIEnv * env){
  jclass lookupClass = (*env)->FindClass (env, "java/net/DatagramPacket");
  if (!lookupClass)
    return NULL;
  return lookupClass;
}

jfieldID
getJavaNetDatagramPacketAddress(JNIEnv * env){
  jfieldID fid;
  jclass lookupClass = (*env)->FindClass (env, "java/net/DatagramPacket");
  if (!lookupClass)
    return NULL;
  fid =
    (*env)->GetFieldID (env, lookupClass, "address",
      "Ljava/net/InetAddress;");
  if (!fid)
    return NULL;
  return fid;
}

jfieldID
getJavaNetDatagramPacketLength(JNIEnv * env){
  jfieldID fid;
  jclass lookupClass = (*env)->FindClass (env, "java/net/DatagramPacket");
  if (!lookupClass)
    return NULL;
  fid = (*env)->GetFieldID (env, lookupClass, "length", "I");
  if (!fid)
    return NULL;
  return fid;
}

jfieldID
getJavaNetDatagramPacketPort(JNIEnv * env){
  jfieldID fid;
  jclass lookupClass = (*env)->FindClass (env, "java/net/DatagramPacket");
  if (!lookupClass)
    return NULL;
  fid = (*env)->GetFieldID (env, lookupClass, "port", "I");
  if (!fid)
    return NULL;
  return fid;
}

jfieldID
getJavaNetSocketImplAddress(JNIEnv * env){
  jfieldID fid;
  jclass lookupClass = (*env)->FindClass (env, "java/net/SocketImpl");
  if (!lookupClass)
    return NULL;
  fid = (*env)->GetFieldID (env, lookupClass, "address", "Ljava/net/InetAddress;");
  if (!fid)
    return NULL;
  return fid;
}
jfieldID
getJavaNetSocketImplPort(JNIEnv * env){
  jfieldID fid;
  jclass lookupClass = (*env)->FindClass (env, "java/net/SocketImpl");
  if (!lookupClass)
    return NULL;
  fid = (*env)->GetFieldID (env, lookupClass, "port", "I");
  if (!fid)
    return NULL;
  return fid;
}

jfieldID
getJavaIoFileDescriptorDescriptor (JNIEnv * env)
{
  jclass descriptorCLS;
  jfieldID descriptorFID;

  descriptorCLS = (*env)->FindClass (env, "java/io/FileDescriptor");
  if (NULL == descriptorCLS)
    {
      return NULL;
    }

  descriptorFID = (*env)->GetFieldID (env, descriptorCLS, "descriptor", "J");
  if (NULL == descriptorFID)
    {
      return NULL;
    }

  return descriptorFID;
}

void
setJavaIoFileDescriptorContents (JNIEnv * env, jobject fd,
                                          void *value)
{
  jfieldID fid = getJavaIoFileDescriptorDescriptor (env);
  if (NULL != fid)
    {
      (*env)->SetLongField (env, fd, fid, (jlong) ((IDATA)value));
    }
}

void *
getJavaIoFileDescriptorContentsAsAPointer (JNIEnv * env, jobject fd)
{
  jfieldID descriptorFID = getJavaIoFileDescriptorDescriptor (env);
  if (NULL == descriptorFID)
    {
      return (void *) -1;
    }
  return (void *) ((IDATA)((*env)->GetLongField (env, fd, descriptorFID)));
}

jobject getJavaNioChannelsSocketChannelImplObj(JNIEnv * env, jclass channel_class){
          jmethodID channel_new;
	  jobject channel_object;
          
          if(NULL == channel_class) {
              return NULL;
          }
	  channel_new = (*env)->GetMethodID(env, channel_class,"<init>","()V");
          if(NULL == channel_new) {
              return NULL;
          }
          channel_object = (*env)->NewObject(env,channel_class,channel_new);
          return channel_object;
}

void setJavaNioChannelsLocalPort(JNIEnv * env,jclass channel_class,jobject channel_object,int port){
	// set port
	jfieldID port_field = (*env)->GetFieldID(env,channel_class,"localPort","I");
	(*env)->SetIntField(env,channel_object,port_field,port);
}

void setFDContent(JNIEnv * env, jclass channel_class, jobject channel_object, void * sock){
	jobject fd_object;
	jfieldID fd_field = (*env)->GetFieldID(env,channel_class,"fd","Ljava/io/FileDescriptor;");
	jclass fd_class = (*env)->FindClass(env,"java/io/FileDescriptor");
	if(NULL == fd_class) {
		return;
        }
	fd_object = (*env)->GetObjectField(env,channel_object,fd_field);
	if(NULL == fd_object) {
        	return;
	}
	setJavaIoFileDescriptorContents (env, fd_object,sock);
}

void setSocketAddressContent(JNIEnv * env, jclass channel_class, jobject channel_object,jbyte * address){
	jmethodID addr_new;
	jfieldID socketaddr_field,addrarray_field,addr_field;
	jobject addr_object,socketaddr_object;
	jclass addr_class,socketaddr_class ;
	jbyteArray addr_array;
	addr_class = (*env)->FindClass(env,"Ljava/net/InetAddress;");
	if(NULL == addr_class) {
	        return;
        }
	addr_new = (*env)->GetMethodID(env,addr_class,"<init>","()V");
        addr_object = (*env)->NewObject(env,addr_class,addr_new);
	if(NULL == addr_object) {
        	return;
        }
	socketaddr_class = (*env)->FindClass(env,"java/net/InetSocketAddress");
	socketaddr_field = (*env)->GetFieldID(env,channel_class,"connectAddress","Ljava/net/InetSocketAddress;");
	socketaddr_object = (*env)->GetObjectField(env,channel_object,socketaddr_field);
	if(NULL == socketaddr_object){
		return;
	}
	addr_field = (*env)->GetFieldID(env,socketaddr_class,"addr","Ljava/net/InetAddress;");
	(*env)->SetObjectField(env, socketaddr_object, addr_field, addr_object);
	addrarray_field = (*env)->GetFieldID(env,addr_class,"ipaddress","[B");
	addr_array = (*env)->NewByteArray(env,(jsize)4);		
        (*env)->SetByteArrayRegion(env,addr_array,(jsize)0,(jsize)4,address);
	(*env)->SetObjectField(env, addr_object, addrarray_field, addr_array);
}

void setSocketLocalAddressContent(JNIEnv * env, jclass channel_class, jobject channel_object,jbyte * address){
	jfieldID addrarray_field;
	jfieldID localAddr_field = (*env)->GetFieldID(env,channel_class,"localAddress","Ljava/net/InetAddress;");
	jclass addr_class = (*env)->FindClass(env,"Ljava/net/InetAddress;");
	jmethodID addr_new = (*env)->GetMethodID(env,addr_class,"<init>","()V");
	jobject localAddr_object = (*env)->NewObject(env,addr_class,addr_new);
	jfieldID socketaddr_field = (*env)->GetFieldID(env,channel_class,"connectAddress","Ljava/net/InetSocketAddress;");
	jobject socketaddr_object = (*env)->GetObjectField(env,channel_object,socketaddr_field);
	jbyteArray addr_array;
	if(NULL == addr_class) {
              return;
       	}
	(*env)->SetObjectField(env, socketaddr_object, localAddr_field, localAddr_object);
	if(NULL == localAddr_object) {
              return;
        }
	addr_array = (*env)->NewByteArray(env,(jsize)4);	
	(*env)->SetByteArrayRegion(env,addr_array,(jsize)0,(jsize)4,address);
	addrarray_field = (*env)->GetFieldID(env,addr_class,"ipaddress","[B");
	(*env)->SetObjectField(env, localAddr_object, addrarray_field, addr_array);
}

void setServerSocketLocalAddressContent(JNIEnv * env, jclass socketImpl_class, jobject socketImpl_object, jbyte * localAddr){
	jmethodID addr_new;
	jfieldID localAddr_field,addrarray_field;
	jobject localAddr_object;
	jbyteArray addr_array;
	jclass addr_class = (*env)->FindClass(env,"Ljava/net/InetAddress;");
	if(NULL == addr_class) {
	        return;
        }
	addr_new = (*env)->GetMethodID(env,addr_class,"<init>","()V");
	localAddr_field = (*env)->GetFieldID(env,socketImpl_class,"address","Ljava/net/InetAddress;");
	localAddr_object = (*env)->NewObject(env,addr_class,addr_new);
	if(NULL == localAddr_object) {
        	return;
        }
	(*env)->SetObjectField(env, socketImpl_object, localAddr_field, localAddr_object);
	addr_array = (*env)->NewByteArray(env,(jsize)4);	
	(*env)->SetByteArrayRegion(env,addr_array,(jsize)0,(jsize)4,localAddr);
	addrarray_field = (*env)->GetFieldID(env,addr_class,"ipaddress","[B");
	(*env)->SetObjectField(env, localAddr_object, addrarray_field, addr_array);
}
