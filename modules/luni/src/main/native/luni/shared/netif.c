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
#include "jclprots.h"
#include "helpers.h"
#include "harmonyglob.h"
#include "hysock.h"

#if (defined(WIN32))
#include <Winsock2.h>
#include <ws2tcpip.h>
#include <Iphlpapi.h>
#include <windows.h>
#else
#include <sys/socket.h>
#include <netdb.h>
#include <sys/ioctl.h>
#include <net/if.h>
#include <stdio.h>
#include <arpa/inet.h>
#endif
/**
* Answer an array of NetworkInterface objects.  One for each network interface within the system
*
* @param	env	pointer to the JNI library
* @param	clazz	the class of the object invoking the JNI function
*
* @return			an array of NetworkInterface objects of length 0 or more
*/

#if defined(WIN32)
typedef CRITICAL_SECTION MUTEX;
typedef struct HyPortControlData
{
	UDATA sig_flags;
	UDATA shmem_group_perm;
} HyPortControlData;
typedef struct HyNLSDataCache
{
	char *baseCatalogPaths[4];
	UDATA nPaths;
	char *baseCatalogName;
	char *baseCatalogExtension;
	char *catalog;
	char language[4];
	char region[4];
	char variant[32];
	struct HyThreadMonitor *monitor;
	struct HyNLSHashEntry *hash_buckets[256];
	struct HyNLSHashEntry *old_hashEntries;
} HyNLSDataCache;
typedef struct HyPortLibraryGlobalData
{
	struct HyPortControlData control;
	struct HyNLSDataCache nls_data;
	hythread_tls_key_t tls_key;
	MUTEX tls_mutex;
	void *buffer_list;
	struct HyPortPlatformGlobals platformGlobals;
} HyPortLibraryGlobalData;


#define LOOP_BACK_NUM_ADDRESSES   1
typedef DWORD (WINAPI * GetAdaptersAddressesFunctionAddress) (ULONG, DWORD,
															  PVOID,
															  PIP_ADAPTER_ADDRESSES,
															  PULONG);

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_mem_hymem_allocate_memory_Entry(P1) do { \
	if ((unsigned char) hyprt_UtActive[96] != 0){ \
	hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((96 << 8) | hyprt_UtActive[96]), "\4", P1);} \
} while(0)
#else
#define Trc_PRT_mem_hymem_allocate_memory_Entry(P1)     /* tracepoint name: hyprt.96 */
#endif

#if (UT_TRACE_OVERHEAD>=1)
#define Trc_PRT_mem_hymem_allocate_memory_Exit(P1) do { \
	if ((unsigned char) hyprt_UtActive[97] != 0){ \
	hyprt_UtModuleInfo.intf->Trace((void *)NULL, &hyprt_UtModuleInfo, ((97 << 8) | hyprt_UtActive[97]), "\6", P1);} \
} while(0)
#else
#define Trc_PRT_mem_hymem_allocate_memory_Exit(P1)      /* tracepoint name: hyprt.97 */
#endif
#endif

#define LOOP_BACK_NAME            "loopback"
#define LOOP_BACK_DISPLAY_NAME    "loopback interface"
#define LOOP_BACK_IPV4_ADDRESS    "127.0.0.1"
#define LOOP_BACK_NUM_ADDRESSES   1

JNIEXPORT jobjectArray JNICALL
Java_java_net_NetworkInterface_getNetworkInterfacesImpl (JNIEnv * env,
														 jclass clazz)
{

	/* variables to store network interface data returned by call to port library */
	struct hyNetworkInterfaceArray_struct networkInterfaceArray;
	struct hyNetworkInterface_struct *interfaces = NULL;

	U_32 currentAdapterIndex = 0;
  	U_32 counter  = 0;
  	U_32 counter2 = 0;
  	U_32 numAddresses = 0;
  	U_32 currentIPAddressIndex = 0;
  	I_32 numAdapters = 0;
	int len = 0;
	int socketP = 0;
	U_32 totalInterfaces = 0;
	I_32 err = 0;

	/* variables for class and method objects needed to create bridge to java */
	jclass networkInterfaceClass = NULL;
	jclass inetAddressClass = NULL;
	jclass utilClass = NULL;
	jmethodID methodID = NULL;
	jmethodID utilMid = NULL;

	/* JNI objects used to return values from native call */
	jstring name = NULL;
	jstring displayName = NULL;
	jobjectArray addresses = NULL;
	jobjectArray networkInterfaces = NULL;
	jbyteArray bytearray = NULL;

	/* jobjects used to build the object arrays returned */
	jobject currentInterface = NULL;
	jobject element = NULL;

	/* misc variables needed for looping and determining inetAddress info */
	U_32 i = 0;
	U_32 j = 0;
	U_32 nameLen = 0;

#if defined(WIN32)
	DWORD returnVal = 0;
	ULONG bufferLength = 0;
	U_32 pseudoLoopbackFound = 0;
	U_32 numLoopbackAddresses = LOOP_BACK_NUM_ADDRESSES;
	int ethIndex = 0;
	int tunIndex = 0;
	GetAdaptersAddressesFunctionAddress getAdaptersAddresses_functionAddress =
		NULL;
	HINSTANCE libInstance = NULL;
	I_32 result = 0;
#else
	struct ifreq reqCopy;
	struct ifconf ifc;
	int ifconfCommand = SIOCGIFCONF;
	char * lastName;
#endif
	/* required call if we are going to call port library methods */
	PORT_ACCESS_FROM_ENV (env);
	HyPortLibrary * portLibrary = privatePortLibrary; 

	networkInterfaceArray.length = 0;
	networkInterfaceArray.elements = NULL;

	/* get the classes and methods that we need for later calls */
	networkInterfaceClass =
		(*env)->FindClass (env, "java/net/NetworkInterface");
	if (networkInterfaceClass == NULL)
	{
		throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
		return NULL;
	}

	inetAddressClass = (*env)->FindClass (env, "java/net/InetAddress");
	if (inetAddressClass == NULL)
	{
		throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
		return NULL;
	}

	methodID =
		(*env)->GetMethodID (env, networkInterfaceClass, "<init>",
		"(Ljava/lang/String;Ljava/lang/String;[Ljava/net/InetAddress;I)V");
	if (methodID == NULL)
	{
		throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
		return NULL;
	}

	utilClass = (*env)->FindClass (env, "org/apache/harmony/luni/util/Util");
	if (!utilClass)
	{
		return NULL;
	}

	utilMid =
		((*env)->
		GetStaticMethodID (env, utilClass, "toString",
		"([BII)Ljava/lang/String;"));
	if (!utilMid)
		return NULL;

#if defined(WIN32)

	/* validate that we were passed the required structure to return network interfaces */
	if (NULL == &networkInterfaceArray)
	{
		result = HYPORT_ERROR_SOCKET_NORECOVERY;
	}
	else
	{
		/* initialize the structure so that freeing it would not a cause an issue */
		networkInterfaceArray.length = 0;
		networkInterfaceArray.elements = NULL;
	}
	/* try to load the function that gives us the IPV6 info.  If it is not available on this platform then 
	* we will default to using the other function which does not give us the IPV6 info but is available on earlier platforms 
	*/
	libInstance = LoadLibrary (TEXT ("Iphlpapi"));
	if (NULL != libInstance)
	{
		getAdaptersAddresses_functionAddress =
			(GetAdaptersAddressesFunctionAddress) GetProcAddress (libInstance,
			TEXT
			("GetAdaptersAddresses"));
	}

	/* only use the function that returns the IPV6 info if we could load the function and we
	* have not been told to prefer the IPv4 stack */
	if ((NULL != getAdaptersAddresses_functionAddress) && (!preferIPv4Stack(env)))
	{
		/* For the functions to get network interface information that are supported on the
		* IPV6 platforms, the loopback interface is included in the list */

		IP_ADAPTER_ADDRESSES *adaptersList = NULL;
		IP_ADAPTER_ADDRESSES *currentAdapter = NULL;
		IP_ADAPTER_ADDRESSES *tempAdapter = NULL;
		IP_ADAPTER_UNICAST_ADDRESS *currentIPAddress = NULL;

		/* get the required buffer size and allocate the memory required */
		returnVal =
			(getAdaptersAddresses_functionAddress) (AF_UNSPEC,
			(GAA_FLAG_SKIP_ANYCAST |
			GAA_FLAG_SKIP_MULTICAST |
			GAA_FLAG_SKIP_DNS_SERVER),
			NULL, adaptersList,
			&bufferLength);

		/* GetAdaptorsInfo fails if there are no adaptors configured,  
		so just return 0, the structure is allready initialized to 
		indicate that there are no interfaces */
		if (returnVal == ERROR_BUFFER_OVERFLOW)
		{
			/* this is ok since we did not pass in a buffer */
		}
		else
		{
			if (libInstance != NULL)
			{
				FreeLibrary (libInstance);
			}
			result = 0;
		}

		Trc_PRT_mem_hymem_allocate_memory_Entry (bufferLength);
		if (bufferLength == 0)
		{				/* prevent GlobalLock from failing causing allocate to return null */
			bufferLength = 1;
		}
		adaptersList = (IP_ADAPTER_ADDRESSES *)hymem_allocate_memory (bufferLength);
		Trc_PRT_mem_hymem_allocate_memory_Exit (pointer);

		/* now get the actual adaptor information  and the fill in the hyNetworkInterface_struct */
		if ((returnVal =
			(getAdaptersAddresses_functionAddress) (AF_UNSPEC,
			(GAA_FLAG_SKIP_ANYCAST |
			GAA_FLAG_SKIP_MULTICAST |
			GAA_FLAG_SKIP_DNS_SERVER),
			NULL, adaptersList,
			&bufferLength) ==
			ERROR_SUCCESS))
		{

			/* first get the number of adaptors */
			currentAdapter = adaptersList;
			while (currentAdapter)
			{
				/* on windows to be compatible we don't return the Loopback Pseudo Interface we just merge its addresses into
				the loopback interface with the IPv4 loopback address */
				if (strcmp (currentAdapter->AdapterName, pseudoLoopbackGUID) !=
					0)
				{
					numAdapters = numAdapters + 1;
				}
				currentAdapter = currentAdapter->Next;
			}

			/* now allocate the space for the hyNetworkInterface structs and fill it in */
			interfaces =
				portLibrary->mem_allocate_memory (portLibrary,
				numAdapters *
				sizeof
				(hyNetworkInterface_struct));

			/* set up the return stucture */
			networkInterfaceArray.elements = interfaces;
			networkInterfaceArray.length = numAdapters;

			currentAdapter = adaptersList;
			while (currentAdapter)
			{
				/* on windows to be compatible we don't return the Loopback Pseudo Interface we just merge
				* its addresses into the loopback interface with the IPv4 loopback address.  Note the name
				* should never be null but we do the check to be safe  */
				if ((currentAdapter->AdapterName != NULL) &&
					(strcmp (currentAdapter->AdapterName, pseudoLoopbackGUID) !=
					0))
				{

					/* set the index for the interface */
					interfaces[currentAdapterIndex].index =
						currentAdapter->Ipv6IfIndex == 0? currentAdapter->IfIndex : currentAdapter->Ipv6IfIndex;
					nameLen = strlen (currentAdapter->AdapterName);
					interfaces[currentAdapterIndex].name =
						portLibrary->mem_allocate_memory (portLibrary,
						nameLen + 1);

					/* get the name and display name for the adapter */
					switch(currentAdapter->IfType) {
	case IF_TYPE_SOFTWARE_LOOPBACK:
		sprintf(interfaces[currentAdapterIndex].name, "lo");
		break;
	case IF_TYPE_ETHERNET_CSMACD:
		sprintf(interfaces[currentAdapterIndex].name, "eth%d", ethIndex++);
		break;
	case IF_TYPE_TUNNEL:
		sprintf(interfaces[currentAdapterIndex].name, "tun%d", tunIndex++);
		break;
	default:

		strncpy (interfaces[currentAdapterIndex].name,
			currentAdapter->AdapterName, nameLen);

		interfaces[currentAdapterIndex].name[nameLen] = 0;
					}

					nameLen =
						WideCharToMultiByte (CP_ACP, WC_COMPOSITECHECK,
						currentAdapter->FriendlyName, -1,
						NULL, 0, NULL, NULL);

					if (nameLen != 0)
					{
						interfaces[currentAdapterIndex].displayName =
							portLibrary->mem_allocate_memory (portLibrary,
							nameLen + 1);
					}
					else
					{
						interfaces[currentAdapterIndex].displayName =
							portLibrary->mem_allocate_memory (portLibrary, 1);
						interfaces[currentAdapterIndex].displayName[0] = 0;
					}

					if (nameLen != 0)
					{
						WideCharToMultiByte (CP_ACP, WC_COMPOSITECHECK,
							currentAdapter->FriendlyName, -1,
							interfaces[currentAdapterIndex].
							displayName, nameLen, NULL,
							NULL);
						interfaces[currentAdapterIndex].
							displayName[nameLen] = 0;
					}

					/* now get the interface information */

					/* first count the number of IP addreses and allocate the memory required
					* for the ip address info that will be returned */
					numAddresses = 0;
					currentIPAddress = currentAdapter->FirstUnicastAddress;
					while (currentIPAddress)
					{
						numAddresses = numAddresses + 1;
						currentIPAddress = currentIPAddress->Next;
					}

					/* if this is the loopback address then we need to add the addresses from
					* the Loopback Pseudo-Interface */
					pseudoLoopbackFound = 0;
					if ((NULL != currentAdapter->FirstUnicastAddress) &&
						(AF_INET ==
						((struct sockaddr_in *) (currentAdapter->
						FirstUnicastAddress->Address.
						lpSockaddr))->sin_family)
						&& (127 ==
						((struct sockaddr_in *) (currentAdapter->
						FirstUnicastAddress->
						Address.lpSockaddr))->
						sin_addr.S_un.S_un_b.s_b1))
					{
						/* find the pseudo interface and get the first unicast address */
						tempAdapter = adaptersList;
						pseudoLoopbackFound = 0;
						while ((tempAdapter) && (0 == pseudoLoopbackFound))
						{
							if (strcmp
								(tempAdapter->AdapterName,
								pseudoLoopbackGUID) == 0)
							{
								pseudoLoopbackFound = 1;
							}
							else
							{
								tempAdapter = tempAdapter->Next;
							}
						}

						if (1 == pseudoLoopbackFound)
						{				
							/* now if we found the adapter add the count for the addresses on it */
							currentIPAddress = tempAdapter->FirstUnicastAddress;
							while (currentIPAddress)
							{
								numAddresses = numAddresses + 1;
								currentIPAddress = currentIPAddress->Next;
							}

							/* also if we found the pseudo interface we must have to use the interface
							* id associated with this interface */
							interfaces[currentAdapterIndex].index =
								tempAdapter->Ipv6IfIndex;
							//strncpy (interfaces[currentAdapterIndex].name,  "aa", 3);
						}
					}

					interfaces[currentAdapterIndex].addresses =
						portLibrary->mem_allocate_memory (portLibrary,
						numAddresses *
						sizeof
						(hyipAddress_struct));

					interfaces[currentAdapterIndex].numberAddresses =
						numAddresses;

					/* now get the actual ip address info */
					currentIPAddressIndex = 0;
					currentIPAddress = currentAdapter->FirstUnicastAddress;
					while (currentIPAddress)
					{
						if (currentIPAddress->Address.iSockaddrLength ==
							sizeof (struct sockaddr_in6))
						{
							memcpy (interfaces[currentAdapterIndex].
								addresses[currentIPAddressIndex].addr.bytes,
								&(((struct sockaddr_in6 *)
								currentIPAddress->Address.lpSockaddr)->
								sin6_addr.u.Byte),
								sizeof (struct in6_addr));
							interfaces[currentAdapterIndex].
								addresses[currentIPAddressIndex].length =
								sizeof (struct in6_addr);
							interfaces[currentAdapterIndex].
								addresses[currentIPAddressIndex].scope =
								((struct sockaddr_in6 *) currentIPAddress->
								Address.lpSockaddr)->sin6_scope_id;
						}
						else if (currentIPAddress->Address.iSockaddrLength ==
							sizeof (struct sockaddr_in6_old))
						{
							memcpy (interfaces[currentAdapterIndex].
								addresses[currentIPAddressIndex].addr.bytes,
								&(((struct sockaddr_in6_old *)
								currentIPAddress->Address.lpSockaddr)->
								sin6_addr.u.Byte),
								sizeof (struct in6_addr));
							interfaces[currentAdapterIndex].
								addresses[currentIPAddressIndex].length =
								sizeof (struct in6_addr);
							interfaces[currentAdapterIndex].
								addresses[currentIPAddressIndex].scope = 0;
						}
						else
						{
							interfaces[currentAdapterIndex].
								addresses[currentIPAddressIndex].addr.inAddr.S_un.
								S_addr =
								((struct sockaddr_in *) currentIPAddress->Address.
								lpSockaddr)->sin_addr.S_un.S_addr;
							interfaces[currentAdapterIndex].
								addresses[currentIPAddressIndex].length =
								sizeof (struct in_addr);
							interfaces[currentAdapterIndex].
								addresses[currentIPAddressIndex].scope = 0;
						}

						currentIPAddress = currentIPAddress->Next;
						currentIPAddressIndex = currentIPAddressIndex + 1;
					}

					/* now add in the addresses from the loopback pseudo-interface if appropriate */
					if (1 == pseudoLoopbackFound)
					{
						currentIPAddress = tempAdapter->FirstUnicastAddress;
						while (currentIPAddress)
						{
							if (currentIPAddress->Address.iSockaddrLength ==
								sizeof (struct sockaddr_in6))
							{
								memcpy (interfaces[currentAdapterIndex].
									addresses[currentIPAddressIndex].addr.
									bytes,
									&(((struct sockaddr_in6 *)
									currentIPAddress->Address.
									lpSockaddr)->sin6_addr.u.Byte),
									sizeof (struct in6_addr));
								interfaces[currentAdapterIndex].
									addresses[currentIPAddressIndex].length =
									sizeof (struct in6_addr);
								interfaces[currentAdapterIndex].
									addresses[currentIPAddressIndex].scope =
									((struct sockaddr_in6 *) currentIPAddress->
									Address.lpSockaddr)->sin6_scope_id;
							}
							else if (currentIPAddress->Address.
								iSockaddrLength ==
								sizeof (struct sockaddr_in6_old))
							{
								memcpy (interfaces[currentAdapterIndex].
									addresses[currentIPAddressIndex].addr.
									bytes,
									&(((struct sockaddr_in6_old *)
									currentIPAddress->Address.
									lpSockaddr)->sin6_addr.u.Byte),
									sizeof (struct in6_addr));
								interfaces[currentAdapterIndex].
									addresses[currentIPAddressIndex].length =
									sizeof (struct in6_addr);
								interfaces[currentAdapterIndex].
									addresses[currentIPAddressIndex].scope = 0;
							}
							else
							{
								interfaces[currentAdapterIndex].
									addresses[currentIPAddressIndex].addr.inAddr.
									S_un.S_addr =
									((struct sockaddr_in *) currentIPAddress->
									Address.lpSockaddr)->sin_addr.S_un.S_addr;
								interfaces[currentAdapterIndex].
									addresses[currentIPAddressIndex].length =
									sizeof (struct in_addr);
								interfaces[currentAdapterIndex].
									addresses[currentIPAddressIndex].scope = 0;
							}

							currentIPAddress = currentIPAddress->Next;
							currentIPAddressIndex = currentIPAddressIndex + 1;
						}
					}
					currentAdapterIndex = currentAdapterIndex + 1;
				}		/* not adaptor to exlude */
				currentAdapter = currentAdapter->Next;
			}

			/* free the memory used for the call to the getAdaptorAddresses info call */
			portLibrary->mem_free_memory (portLibrary, adaptersList);

			/* free the dll we don't need anymore */
			if (libInstance != NULL)
			{
				FreeLibrary (libInstance);
			}

			/* return OK */
			result = 0;

		}
		else
		{
			/* GetAdaptorsInfo fails if there are no adaptors configured, so just return 0, the structure is
			* already initialized to indicate that there are no interfaces */

			/* free the dll we don't need anymore */
			if (libInstance != NULL)
			{
				FreeLibrary (libInstance);
			}

			if (returnVal == ERROR_BUFFER_OVERFLOW)
			{
				result = HYPORT_ERROR_SOCKET_NOBUFFERS;
			}
			else
			{
				result = 0;
			}
		}
	}
	else

	{
		/* For the functions to get network interface information that are supported on the pre- IPV6
		platforms, the loopback interface is NOT included in the list.  Therefore, we have to add it ourselves */

		IP_ADAPTER_INFO *adaptersList = NULL;
		IP_ADAPTER_INFO *currentAdapter = NULL;
		IP_ADDR_STRING *currentIPAddress = NULL;

		/* get the required buffer size and allocate the memory required */
		returnVal = GetAdaptersInfo (adaptersList, &bufferLength);

		/* GetAdaptorsInfo fails if there are no adaptors configured, so just return 0, the structure is
		* already initialized to indicate that there are no interfaces */
		if (returnVal != ERROR_SUCCESS)
		{
			if (returnVal == ERROR_NO_DATA)
			{
				numAdapters = 0;
				bufferLength = 0;
				adaptersList = NULL;
			}
			else if (returnVal == ERROR_BUFFER_OVERFLOW)
			{
				/*  this is ok since we did not pass in a buffer */
			}
			else
			{
				result = HYPORT_ERROR_SOCKET_NORECOVERY;
			}
		}

		if (bufferLength != 0)
		{
			adaptersList =
				(IP_ADAPTER_INFO *) portLibrary->mem_allocate_memory (portLibrary,
				bufferLength);

			/* now get the actual adaptor information  and the fill in the hyNetworkInterface_struct */
			if ((returnVal =
				GetAdaptersInfo (adaptersList,
				&bufferLength)) == ERROR_SUCCESS)
			{

				/* first get the number of adaptors */
				currentAdapter = adaptersList;
				while (currentAdapter)
				{
					currentAdapter = currentAdapter->Next;
					numAdapters = numAdapters + 1;
				}
			}
			else
			{
				numAdapters = 0;
				if (returnVal == ERROR_NO_DATA)
				{
					bufferLength = 0;
					adaptersList = NULL;
				}
				else if (returnVal == ERROR_BUFFER_OVERFLOW)
				{
					result = HYPORT_ERROR_SOCKET_NOBUFFERS;
				}
				else
				{
					result = HYPORT_ERROR_SOCKET_NORECOVERY;
				}
			}
		}

		/* now allocate the space for the hyNetworkInterface structs and fill it in */
		/* allow space for one more than was returned as the system call does not include the loopback interface
		which we must add */
		interfaces =
			portLibrary->mem_allocate_memory (portLibrary,
			(numAdapters +
			1) *
			sizeof (hyNetworkInterface_struct));

		// set up the return stucture
		networkInterfaceArray.elements = interfaces;
		networkInterfaceArray.length = numAdapters + 1;
		currentAdapter = adaptersList;
		while (currentAdapter)
		{
			/* set the index to 0 as for non-IPV6 we don't fill in this value */
			interfaces[currentAdapterIndex].index = 0;

			/* get the name and display name for the adapter */
			nameLen = strlen (currentAdapter->AdapterName);
			interfaces[currentAdapterIndex].name =
				portLibrary->mem_allocate_memory (portLibrary, nameLen+1);

			switch(currentAdapter->Type) {
	case MIB_IF_TYPE_LOOPBACK:
		sprintf(interfaces[currentAdapterIndex].name, "lo");
		break;
	case MIB_IF_TYPE_ETHERNET:
		sprintf(interfaces[currentAdapterIndex].name, "eth%d", ethIndex++);
		break;
		break;
	default:

		strncpy (interfaces[currentAdapterIndex].name,
			currentAdapter->AdapterName, nameLen);
		interfaces[currentAdapterIndex].name[nameLen] = 0;
			}

			nameLen = strlen (currentAdapter->Description);
			interfaces[currentAdapterIndex].displayName =
				portLibrary->mem_allocate_memory (portLibrary, nameLen + 1);

			strncpy (interfaces[currentAdapterIndex].displayName,
				currentAdapter->Description, nameLen);
			interfaces[currentAdapterIndex].displayName[nameLen] = 0;

			/* now get the interface information */

			/* first count the number of IP addreses and allocate the memory required for
			* the ip address info that will be returned */
			numAddresses = 0;
			currentIPAddress = &(currentAdapter->IpAddressList);
			while (currentIPAddress)
			{
				/* don't count the any address which seems to be returned as the first address
				* for interfaces with no addresses */
				if (inet_addr (currentIPAddress->IpAddress.String) != 0)
				{
					numAddresses = numAddresses + 1;
				}
				currentIPAddress = currentIPAddress->Next;
			}
			interfaces[currentAdapterIndex].addresses =
				portLibrary->mem_allocate_memory (portLibrary,
				numAddresses *
				sizeof (hyipAddress_struct));

			interfaces[currentAdapterIndex].numberAddresses = numAddresses;

			/* now get the actual ip address info */
			currentIPAddressIndex = 0;
			currentIPAddress = &(currentAdapter->IpAddressList);
			while (currentIPAddress)
			{
				if (inet_addr (currentIPAddress->IpAddress.String) != 0)
				{
					interfaces[currentAdapterIndex].
						addresses[currentIPAddressIndex].addr.inAddr.S_un.S_addr =
						inet_addr (currentIPAddress->IpAddress.String);
					interfaces[currentAdapterIndex].
						addresses[currentIPAddressIndex].length =
						sizeof (struct in_addr);
					interfaces[currentAdapterIndex].
						addresses[currentIPAddressIndex].scope = 0;
					currentIPAddressIndex = currentIPAddressIndex + 1;
				}
				currentIPAddress = currentIPAddress->Next;
			}

			currentAdapter = currentAdapter->Next;
			currentAdapterIndex = currentAdapterIndex + 1;
		}

		/* now fill in the loopback adaptor */
		interfaces[currentAdapterIndex].index = 0;
		nameLen = strlen (LOOP_BACK_NAME);

		interfaces[currentAdapterIndex].name =
			portLibrary->mem_allocate_memory (portLibrary, nameLen + 1);

		strncpy (interfaces[currentAdapterIndex].name, LOOP_BACK_NAME,
			nameLen);
		interfaces[currentAdapterIndex].name[nameLen] = 0;

		nameLen = strlen (LOOP_BACK_DISPLAY_NAME);
		interfaces[currentAdapterIndex].displayName =
			portLibrary->mem_allocate_memory (portLibrary, nameLen + 1);

		strncpy (interfaces[currentAdapterIndex].displayName,
			LOOP_BACK_DISPLAY_NAME, nameLen);
		interfaces[currentAdapterIndex].displayName[nameLen] = 0;

		/* now get interface information */

		interfaces[currentAdapterIndex].addresses =
			portLibrary->mem_allocate_memory (portLibrary,
			numLoopbackAddresses *
			sizeof (hyipAddress_struct));

		/* now the actual ip address info */
		interfaces[currentAdapterIndex].numberAddresses = numLoopbackAddresses;
		interfaces[currentAdapterIndex].addresses[0].addr.inAddr.S_un.S_addr =
			inet_addr (LOOP_BACK_IPV4_ADDRESS);
		interfaces[currentAdapterIndex].addresses[0].length =
			sizeof (struct in_addr);
		interfaces[currentAdapterIndex].addresses[0].scope = 0;

		/* free the memory used for the call to the getAdaptors info call */
		if (bufferLength != 0)
		{
			portLibrary->mem_free_memory (portLibrary, adaptersList);
		}

		/* return ok */
		result = 0;
	}    
#else
  /* first get the list of interfaces.  We do not know how long the buffer needs to be so we try with one that allows for
     32 interfaces.  If this turns out not to be big enough then we expand the buffer to be able to support another
     32 interfaces and try again.  We do this until the result indicates that the result fit into the buffer provided */
  /* we need  socket to do the ioctl so create one */
  socketP =
    socket (AF_INET6, SOCK_DGRAM, 0);
  if (socketP < 0)
    {
      throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
	   return NULL;
    }

  for (;;)
    {
      char *data =
        (char *) portLibrary->mem_allocate_memory (portLibrary, len);
#if (defined(VALIDATE_ALLOCATIONS))
      if (data == NULL)
        {
          close (socketP);
          throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
			return NULL;
        }
#endif
      ifc.ifc_len = len;
      ifc.ifc_buf = data;
      errno = 0;
      if (ioctl (socketP, ifconfCommand, &ifc) != 0)
        {
          err = errno;
          portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
          close (socketP);
          throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
			return NULL;
        }
      if (ifc.ifc_len < len)
        break;
      /* the returned data was likely truncated, expand the buffer and try again */
      portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
      len += 32 * sizeof (struct ifreq);
    }
  /* get the number of distinct interfaces */
  if (ifc.ifc_len != 0)
    {
      totalInterfaces = ifc.ifc_len / sizeof (struct ifreq);
    }

  lastName = NULL;
  for (counter = 0; counter < totalInterfaces; counter++)
    {
      if ((NULL == lastName)
          || (strncmp (lastName, ifc.ifc_req[counter].ifr_name, IFNAMSIZ) !=
              0))
        {
          /* make sure the interface is up */
          reqCopy = ifc.ifc_req[counter];
          ioctl (socketP, SIOCGIFFLAGS, &reqCopy);
          if ((reqCopy.ifr_flags & IFF_UP) == IFF_UP)
            {
              numAdapters++;
            }
        }
      lastName = ifc.ifc_req[counter].ifr_name;
    }
  /* now allocate the space for the hyNetworkInterface structs and fill it in */
  interfaces =
    portLibrary->mem_allocate_memory (portLibrary,
                                      numAdapters *
                                      sizeof (hyNetworkInterface_struct));
#if (defined(VALIDATE_ALLOCATIONS))
  if (NULL == interfaces)
    {
      portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
      close (socketP);
	   throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
		return NULL;
    }
  /* initialize the structure so that we can free allocated if a failure occurs */
  for (counter = 0; counter < numAdapters; counter++)
    {
      interfaces[counter].name = NULL;
      interfaces[counter].displayName = NULL;
      interfaces[counter].addresses = NULL;
    }
#endif

/* set up the return stucture */
  networkInterfaceArray.elements = interfaces;
  networkInterfaceArray.length = numAdapters;
  lastName = NULL;
  FILE * ipv6 = NULL;
  for (counter = 0; counter < totalInterfaces; counter++)
    {
      /* make sure the interface is still up */
      reqCopy = ifc.ifc_req[counter];
      ioctl (socketP, SIOCGIFFLAGS, &reqCopy);
      if ((reqCopy.ifr_flags & IFF_UP) == IFF_UP)
        {
          /* since this function can return multiple entries for the same name, only do it for the first one with any given name */
          if ((NULL == lastName)
              || (strncmp (lastName, ifc.ifc_req[counter].ifr_name, IFNAMSIZ)
                  != 0))
            {
	           char ifname6[IFNAMSIZ];
	           int oct6[8];
	           char addr6[40];
              int index6 = 0;
              int scope = 0;
              int other = 0;
              /* get the index for the interface.  This is only truely necessary on platforms that support IPV6 */
#if defined(IPv6_FUNCTION_SUPPORT)
              interfaces[currentAdapterIndex].index =
                if_nametoindex (ifc.ifc_req[counter].ifr_name);
#else
              interfaces[currentAdapterIndex].index = 0;
#endif
/* get the name and display name for the adapter */
              /* there only seems to be one name so use it for both the name and the display name */
              nameLen = strlen (ifc.ifc_req[counter].ifr_name);
              interfaces[currentAdapterIndex].name =
                portLibrary->mem_allocate_memory (portLibrary,
                                                  nameLen + 1);
#if (defined(VALIDATE_ALLOCATIONS))
              if (NULL == interfaces[currentAdapterIndex].name)
                {
                  portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
                  hysock_free_network_interface_struct (portLibrary, networkInterfaceArray);
                  close (socketP);
					throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
					return NULL;
                }
#endif
              strncpy (interfaces[currentAdapterIndex].name,
                       ifc.ifc_req[counter].ifr_name, nameLen);
              interfaces[currentAdapterIndex].name[nameLen] = 0;
              nameLen = strlen (ifc.ifc_req[counter].ifr_name);
              interfaces[currentAdapterIndex].displayName =
                portLibrary->mem_allocate_memory (portLibrary,
                                                  nameLen + 1);
#if (defined(VALIDATE_ALLOCATIONS))
              if (NULL == interfaces[currentAdapterIndex].displayName)
                {
                  portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
                  hysock_free_network_interface_struct (portLibrary, networkInterfaceArray);
                  close (socketP);
					throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
					return NULL;
                }
#endif
              strncpy (interfaces[currentAdapterIndex].displayName,
                       ifc.ifc_req[counter].ifr_name, nameLen);
              interfaces[currentAdapterIndex].displayName[nameLen] = 0;

              /* check how many addresses/aliases this adapter has.  aliases show up as adaptors with the same name */
              numAddresses = 0;
              for (counter2 = counter; counter2 < totalInterfaces; counter2++)
                {
                  if (strncmp
                      (ifc.ifc_req[counter].ifr_name,
                       ifc.ifc_req[counter2].ifr_name, IFNAMSIZ) == 0)
                    {
                      if (
#if defined(IPv6_FUNCTION_SUPPORT)
                           (ifc.ifc_req[counter2].ifr_addr.sa_family ==
                            AF_INET6) ||
#endif
                           (ifc.ifc_req[counter2].ifr_addr.sa_family ==
                            AF_INET))
                        {
                          numAddresses++;
                        }
                    }
                  else
                    {
                      break;
                    }
                }
				ipv6 = fopen("/proc/net/if_inet6", "r");
              while(fscanf(ipv6, "%4x%4x%4x%4x%4x%4x%4x%4x %x %x %x %x %s\n", &oct6[0], &oct6[1], &oct6[2], &oct6[3], &oct6[4], &oct6[5], &oct6[6], &oct6[7],
              		&index6, &other, &scope, &other, ifname6) != EOF) {
              		if(strncmp(ifc.ifc_req[counter].ifr_name, ifname6, IFNAMSIZ) == 0) {
              				numAddresses++;
              			}
               }
				fclose(ipv6);

              /* allocate space for the addresses */
              interfaces[currentAdapterIndex].numberAddresses = numAddresses;
              interfaces[currentAdapterIndex].addresses =
                portLibrary->mem_allocate_memory (portLibrary,
                                                  numAddresses *
                                                  sizeof
                                                  (hyipAddress_struct));
#if (defined(VALIDATE_ALLOCATIONS))
              if (NULL == interfaces[currentAdapterIndex].addresses)
                {
                  portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
                  hysock_free_network_interface_struct (portLibrary, networkInterfaceArray);
                  close (socketP);
					throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
					return NULL;
                }
#endif
/* now get the addresses */
              currentIPAddressIndex = 0;
              lastName = ifc.ifc_req[counter].ifr_name;

              for (;;)
                {
                  if (ifc.ifc_req[counter].ifr_addr.sa_family == AF_INET)
                    {
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].addr.inAddr.s_addr =
                        ((struct sockaddr_in *) (&ifc.ifc_req[counter].
                                                 ifr_addr))->sin_addr.s_addr;
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].length =
                        sizeof (struct in_addr);
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].scope = 0;
                      currentIPAddressIndex++;
                    }
#if defined(IPv6_FUNCTION_SUPPORT)
                  else if (ifc.ifc_req[counter].ifr_addr.sa_family ==
                           AF_INET6)
                    {
                      memcpy (interfaces[currentAdapterIndex].
                              addresses[currentIPAddressIndex].addr.bytes,
                              &(((struct sockaddr_in6 *) (&ifc.
                                                          ifc_req[counter].
                                                          ifr_addr))->
                                sin6_addr), sizeof (struct in6_addr));
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].length =
                        sizeof (struct in6_addr);
                      interfaces[currentAdapterIndex].
                        addresses[currentIPAddressIndex].scope =
                        ((struct sockaddr_in6 *) (&ifc.ifc_req[counter].
                                                    ifr_addr))->
                          sin6_scope_id;
                      currentIPAddressIndex++;
                    }

              ipv6 = fopen("/proc/net/if_inet6", "r");
              while(fscanf(ipv6, "%4x%4x%4x%4x%4x%4x%4x%4x %x %x %x %x %s\n", &oct6[0], &oct6[1], &oct6[2], &oct6[3], &oct6[4], &oct6[5], &oct6[6], &oct6[7],
              	&index6, &other, &scope, &other, ifname6) != EOF) {
              	if(strncmp(ifc.ifc_req[counter].ifr_name, ifname6, IFNAMSIZ) == 0) {
              		sprintf(addr6, "%04x:%04x:%04x:%04x:%04x:%04x:%04x:%04x", oct6[0], oct6[1], oct6[2], oct6[3], oct6[4], oct6[5], oct6[6], oct6[7]);
              		inet_pton(AF_INET6, addr6, 
              			(void *)(&interfaces[currentAdapterIndex].addresses[currentIPAddressIndex].addr.in6Addr));

    	              interfaces[currentAdapterIndex].
       	             addresses[currentIPAddressIndex].length = sizeof (struct in6_addr);
                       interfaces[currentAdapterIndex].addresses[currentIPAddressIndex].scope = scope;
   				         currentIPAddressIndex++;
              			}
               }
			fclose(ipv6);

#endif
/* we mean to increment the outside counter here as we want to skip the next entry as it is for the same interface
					   as we are currently working on */
                  if ((counter + 1 < totalInterfaces)
                      &&
                      (strncmp
                       (ifc.ifc_req[counter + 1].ifr_name, lastName,
                        IFNAMSIZ) == 0))
                    {
                      counter++;
                    }
                  else
                    {
                      break;
                    }

                }
              currentAdapterIndex++;
            }
        }
    }                           /* for over all interfaces */
  /* now an interface might have been taken down since we first counted them */
  networkInterfaceArray.length = currentAdapterIndex;
  /* free the memory now that we are done with it */
  portLibrary->mem_free_memory (portLibrary, ifc.ifc_buf);
  close (socketP);

#endif
	
	/* now loop through the interfaces and extract the information to be returned */
	for (j = 0; j < networkInterfaceArray.length; j++)
	{
		/* set the name and display name and reset the addresses object array */
		addresses = NULL;
		name = NULL;
		displayName = NULL;

		if (networkInterfaceArray.elements[j].name != NULL)
		{
			nameLen = strlen (networkInterfaceArray.elements[j].name);
			bytearray = (*env)->NewByteArray (env, nameLen);
			if (bytearray == NULL)
			{
				/* NewByteArray should have thrown an exception */
				return NULL;
			}
			(*env)->SetByteArrayRegion (env, bytearray, (jint) 0, nameLen,
				(jbyte *)networkInterfaceArray.elements[j].name);
			name =
				(*env)->CallStaticObjectMethod (env, utilClass, utilMid,
				bytearray, (jint) 0, nameLen);
			if ((*env)->ExceptionCheck (env))
			{
				return NULL;
			}
		}

		if (networkInterfaceArray.elements[j].displayName != NULL)
		{
			nameLen = strlen (networkInterfaceArray.elements[j].displayName);
			bytearray = (*env)->NewByteArray (env, nameLen);
			if (bytearray == NULL)
			{
				/* NewByteArray should have thrown an exception */
				return NULL;
			}
			(*env)->SetByteArrayRegion (env, bytearray, (jint) 0, nameLen,
				(jbyte *)networkInterfaceArray.elements[j].
				displayName);
			displayName =
				(*env)->CallStaticObjectMethod (env, utilClass, utilMid,
				bytearray, (jint) 0, nameLen);
			if ((*env)->ExceptionCheck (env))
			{
				return NULL;
			}
		}

		/* generate the object with the inet addresses for the itnerface       */
		for (i = 0; i < networkInterfaceArray.elements[j].numberAddresses; i++)
		{
			element = newJavaNetInetAddressGenericB (env,
				(jbyte *)networkInterfaceArray.
				elements[j].addresses[i].
				addr.bytes,
				networkInterfaceArray.
				elements[j].addresses[i].
				length,
				networkInterfaceArray.
				elements[j].addresses[i].
				scope);
			if (i == 0)
			{
				addresses =
					(*env)->NewObjectArray (env,
					networkInterfaceArray.elements[j].
					numberAddresses, inetAddressClass,
					element);
			}
			else
			{
				(*env)->SetObjectArrayElement (env, addresses, i, element);
			}
		}

		/* now  create the NetworkInterface object for this interface and then add it it ot the arrary that will be returned */
		if( NULL == addresses ) {
			addresses =
					(*env)->NewObjectArray (env,
					0, inetAddressClass,
					NULL);
		}
		currentInterface =
			(*env)->NewObject (env, networkInterfaceClass, methodID, name,
			displayName, addresses,
			networkInterfaceArray.elements[j].index);

		if (j == 0)
		{
			networkInterfaces =
				(*env)->NewObjectArray (env, networkInterfaceArray.length,
				networkInterfaceClass, currentInterface);
		}
		else
		{
			(*env)->SetObjectArrayElement (env, networkInterfaces, j,
				currentInterface);
		}
	}
	/* free the memory for the interfaces struct and return the new NetworkInterface List */
	hysock_free_network_interface_struct (&networkInterfaceArray);
	return networkInterfaces;
}

/**
* Answer whether a specific NetworkInterface is up.
*
* @param	env		pointer to the JNI library 
* @param	ifname	name of NetworkInterface
*
* @return	a boolean value indicates whether an network interface is up
*/

JNIEXPORT jboolean JNICALL
Java_java_net_NetworkInterface_isUpImpl
(JNIEnv * env, jobject obj, jstring ifname, jint jindex)
{
	return getPlatformIsUp(env, ifname, jindex);
}

/**
* Answer whether a specific NetworkInterface is Loopback.
*
* @param	env		pointer to the JNI library 
* @param	ifname	name of NetworkInterface
*
* @return	a boolean value indicates whether an network interface is a loopback one
*/

JNIEXPORT jboolean JNICALL
Java_java_net_NetworkInterface_isLoopbackImpl (JNIEnv * env, jobject obj, jstring ifname, jint jindex)
{
	return getPlatformIsLoopback(env, ifname, jindex);
}

/**
* Answer whether a specific NetworkInterface is up.
*
* @param	env		pointer to the JNI library 
* @param	ifname	name of NetworkInterface
*
* @return	a boolean value indicates whether an network interface is a point to point one
*/

JNIEXPORT jboolean JNICALL
Java_java_net_NetworkInterface_isPoint2PointImpl
(JNIEnv * env, jobject obj, jstring ifname, jint jindex)

{
	return getPlatformIsPoint2Point(env, ifname, jindex);
}

/**
* Answer whether a specific NetworkInterface supports multicast.
*
* @param	env		pointer to the JNI library 
* @param	ifname	name of NetworkInterface
*
* @return	a boolean value indicates whether an network interface supports multicast
*/

JNIEXPORT jboolean JNICALL
Java_java_net_NetworkInterface_supportMulticastImpl (JNIEnv * env, jobject obj, jstring ifname, jint jindex)
{
	return getPlatformSupportMulticast(env, ifname, jindex);
}

JNIEXPORT jint JNICALL
Java_java_net_NetworkInterface_getMTUImpl (JNIEnv * env, jobject obj, jstring ifname, jint index)
{
	return getPlatformGetMTU(env, ifname, index);
}

char* 
convertInterfaceName(JNIEnv * env, jstring ifname)
{
	char * interfaceName = 0;
	jsize nameLen = 0;
	const jchar * p;
	int l = 0;

	PORT_ACCESS_FROM_ENV (env);

	nameLen = (*env)->GetStringLength (env, ifname);
	interfaceName= hymem_allocate_memory (sizeof(char) * (nameLen + 1));
#if defined(VALIDATE_ALLOCATIONS)
	if (NULL == interfaceName){
		return NULL;
	}
#endif
	p = (*env)->GetStringChars(env, ifname, NULL);
	for(; l < nameLen; l++) {
		interfaceName[l] = (char)p[l]; 
	}
	interfaceName[nameLen] = '\0';
	return interfaceName;
}

I_32
freeInterfaceAddressArray(JNIEnv * env, struct interfaceAddressArray_struct * array)
{
	U_32 i = 0;

	PORT_ACCESS_FROM_ENV (env);

	if ((array != NULL) && (array->elements != NULL))
	{
		/* free the allocated memory in each of the structures */
		for (i = 0; i < array->length; i++)
		{
			if (array->elements[i].address != NULL)
			{
				hymem_free_memory(array->elements[i].address);
			}
		}

		/* now free the array itself */
		hymem_free_memory(array->elements);
	}
	return 0;
}

JNIEXPORT jobjectArray JNICALL
Java_java_net_NetworkInterface_getInterfaceAddressesImpl (JNIEnv * env, jobject obj, jstring ifname, jint index)
{
	U_32 i = 0;
	I_32 result = 0;

	interfaceAddressArray_struct interfaceAddressArray;

	jobjectArray interfaceAddresses = NULL;
	jobject currentInterfaceAddress = NULL;
	jclass interfaceAddressClass = NULL;
	jmethodID methodID = NULL;

	PORT_ACCESS_FROM_ENV (env);
	
	interfaceAddressClass =
		(*env)->FindClass (env, "java/net/InterfaceAddress");
	if (interfaceAddressClass == NULL)
	{
		throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
		return NULL;
	}

	methodID =
		(*env)->GetMethodID (env, interfaceAddressClass, "<init>",
		"(Ljava/net/InetAddress;S)V");
	if (methodID == NULL)
	{
		throwJavaNetSocketException (env, HYPORT_ERROR_SOCKET_NORECOVERY);
		return NULL;
	}

	// initialize interfaceAddressArray struct
	interfaceAddressArray.elements = NULL;
	interfaceAddressArray.length = 0;
	
	// get the interface address info according to different system calls.
    
	result = getPlatformGetInterfaceAddresses(env, ifname, index, &interfaceAddressArray);

	if (result != 0) {
		/* this means an error occured.  The value returned is the socket error that should be returned */
		throwJavaNetSocketException (env, result);
		return NULL;
	}

    // use JNI to encapsulate interfaceAddress_struct to jobject InterfaceAddress
	for (i = 0; i < interfaceAddressArray.length; i++)
	{
		jobject inetAddress = NULL;
		jshort prefix = 0;

		inetAddress = newJavaNetInetAddressGenericB (env,
			(jbyte *)interfaceAddressArray.elements[i].address->addr.bytes,
            interfaceAddressArray.elements[i].address->length,
			interfaceAddressArray.elements[i].address->scope);

		prefix = (jshort) interfaceAddressArray.elements[i].prefixLength;

		currentInterfaceAddress = (*env)->NewObject (env,
			interfaceAddressClass, methodID, 
			inetAddress, prefix);

        if (i == 0)
		{
			interfaceAddresses =
				(*env)->NewObjectArray (env, interfaceAddressArray.length,
				interfaceAddressClass, currentInterfaceAddress);
		}
		else
		{
			(*env)->SetObjectArrayElement (env, interfaceAddresses, i,
				currentInterfaceAddress);
		}
	}
      
	freeInterfaceAddressArray(env, &interfaceAddressArray);
	
	return interfaceAddresses;
}

JNIEXPORT jbyteArray JNICALL
Java_java_net_NetworkInterface_getHardwareAddressImpl(JNIEnv * env, jobject obj, jstring ifname, jint index)
{
	return getPlatformGetHardwareAddress(env, ifname, index);
}
