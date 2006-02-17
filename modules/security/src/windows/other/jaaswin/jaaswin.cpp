/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @author Alexander V. Astapchuk
 * @version $Revision$
 */

// jaaswin.cpp : Defines the entry point for the DLL application.
//

#define _WIN32_WINNT 0x0500
#define WIN32_LEAN_AND_MEAN		// Exclude rarely-used stuff from Windows headers
#include <windows.h>
#include <sddl.h>

//#include <winbase.h>
#include "jaaswin.h"

#include <assert.h>

#include <jni.h>


BOOL APIENTRY DllMain( HANDLE hModule, 
                       DWORD  ul_reason_for_call, 
                       LPVOID lpReserved
					 )
{
	switch (ul_reason_for_call)
	{
	case DLL_PROCESS_ATTACH:
	case DLL_THREAD_ATTACH:
	case DLL_THREAD_DETACH:
	case DLL_PROCESS_DETACH:
		break;
	}
    return TRUE;
}


#pragma warning(disable:4311)

void error(LPVOID lpJEnv, LPCSTR msg, DWORD dwErr);

jfieldID jf_user = NULL;
jfieldID jf_domainSid = NULL;
    
jfieldID jf_mainGroup = NULL;
jfieldID jf_groups = NULL;
jfieldID jf_token = NULL;

jfieldID jf_debugNative = NULL;

bool getDebugNative( JNIEnv * jenv, jobject thiz ) {
	return jenv->GetBooleanField(thiz, jf_debugNative) ? true : false;
};



JNIEXPORT void JNICALL Java_org_apache_harmony_security_x_security_auth_module_NTSystem_initNatives(JNIEnv * jenv, jclass klass) {

	if( NULL == (jf_user = jenv->GetFieldID(klass, "user", "Lorg/apache/harmony/security/x/security/auth/NTSidUserPrincipal;"))) {
		jclass klassErr = jenv->FindClass("java/lang/Error");
		assert(klassErr);
		jenv->ThrowNew(klassErr, "Could not find field \"user\" of type NTSidUserPrincipal");
		return;
	}

	if( NULL == (jf_domainSid = jenv->GetFieldID(klass, "domainSid", "Ljava/lang/String;")) ) {
		jclass klassErr = jenv->FindClass("java/lang/Error");
		assert(klassErr);
		jenv->ThrowNew(klassErr, "Could not find field \"domainSid\" of type String");
		return;
	}

	if( NULL == (jf_mainGroup = jenv->GetFieldID(klass, "mainGroup", "Lorg/apache/harmony/security/x/security/auth/NTSidPrimaryGroupPrincipal;")) ) {
		jclass klassErr = jenv->FindClass("java/lang/Error");
		assert(klassErr);
		jenv->ThrowNew(klassErr, "Could not find field \"mainGroup\" of type NTSidPrimaryGroupPrincipal");
		return;
	}

	if( NULL == (jf_groups = jenv->GetFieldID(klass, "groups", "[Lorg/apache/harmony/security/x/security/auth/NTSidGroupPrincipal;")) ) {
		jclass klassErr = jenv->FindClass("java/lang/Error");
		assert(klassErr);
		jenv->ThrowNew(klassErr, "Could not find field \"groups\" of type NTSidPrimaryGroupPrincipal");
		return;
	}

	if( NULL == (jf_token = jenv->GetFieldID(klass, "token", "J")) ) {
		jclass klassErr = jenv->FindClass("java/lang/Error");
		assert(klassErr);
		jenv->ThrowNew(klassErr, "Could not find field \"token\" of type NTSidPrimaryGroupPrincipal");
		return;
	}

	if( NULL == (jf_debugNative = jenv->GetFieldID(klass, "debugNative", "Z")) ) {
		if( jenv->ExceptionCheck() ) {
			jenv->ExceptionDescribe();
			return;
		}
		jclass klassErr = jenv->FindClass("java/lang/Error");
		assert(klassErr);
		jenv->ThrowNew(klassErr, "Could not find field \"debugNative\" of type boolean.");
		return;
	}
}


LPVOID QueryInfo(HANDLE hToken, TOKEN_INFORMATION_CLASS klass ) {
	DWORD dwSize;
	if( !GetTokenInformation(hToken, klass, NULL, 0, &dwSize) ) {
		DWORD dwErr = GetLastError();
		if( ERROR_INSUFFICIENT_BUFFER != dwErr ) {
			return NULL;
		}
	}

	LPVOID pData = LocalAlloc(LMEM_FIXED, dwSize);
	if( !GetTokenInformation(hToken, klass, pData, dwSize, &dwSize) ) {
		DWORD dwErr = GetLastError();
		LocalFree(pData);
		SetLastError(dwErr);
		return NULL;
	}
	return pData;
}

BOOL GetInfo( PSID sid, LPSTR* ppName, LPSTR* ppDomain ) {
	DWORD dwNameSize = 0;
	DWORD dwDomainNameSize = 0;
	SID_NAME_USE snu;
	if( !LookupAccountSid(NULL, sid, NULL, &dwNameSize, NULL, &dwDomainNameSize, &snu)) {
		if( ERROR_INSUFFICIENT_BUFFER != GetLastError() ) {
			return FALSE;
		}
	}
	*ppName = (LPSTR)LocalAlloc(LMEM_FIXED, dwNameSize);
	if( NULL == ppName ) {
		return FALSE;
	}
	*ppDomain = (LPSTR)LocalAlloc(LMEM_FIXED, dwDomainNameSize);
	if( NULL == ppName ) {
		DWORD err = GetLastError();
		LocalFree(*ppName);
		SetLastError(err);
		return FALSE;
	}
	if( !LookupAccountSid(NULL, sid, *ppName, &dwNameSize, *ppDomain, &dwDomainNameSize, &snu)) {
		DWORD err = GetLastError();
		LocalFree(*ppName);
		LocalFree(*ppDomain);
		SetLastError(err);
		return FALSE;
	}

	return TRUE;
}

JNIEXPORT void JNICALL Java_org_apache_harmony_security_x_security_auth_module_NTSystem_load(JNIEnv * jenv, jobject thiz) {

	DWORD dwError = -1; // presume unknown error
	LPCSTR errMsg = NULL;

	HANDLE hUser = INVALID_HANDLE_VALUE;
	HANDLE iToken= INVALID_HANDLE_VALUE;

	LPVOID lpUserData = NULL, lpGroupData = NULL, lpAllGroupsData = NULL;
	LPSTR lpStr0 = NULL, lpStr1 = NULL, lpStr2 = NULL;
	LPSTR lpUserSid = NULL, lpDomainName = NULL;
	PSID domainSid = NULL;

	SID_IDENTIFIER_AUTHORITY sia = SECURITY_NT_AUTHORITY;

	//
	// Get the token for the user currently running this Thread
	//
	if( !OpenThreadToken(GetCurrentThread(), TOKEN_QUERY|TOKEN_DUPLICATE, TRUE, &hUser) ) {
		// failed to open thread token. well, let's try process' one
		if( !OpenProcessToken(GetCurrentProcess(), TOKEN_QUERY|TOKEN_DUPLICATE, &hUser) ) {
			errMsg = "Unable to obtain user token";
			goto exit;
		}
	}

	//
	// Obtain the User's info
	//
	if( NULL == (lpUserData = (TOKEN_USER*)QueryInfo(hUser, TokenUser)) ) {
		errMsg = "Unable to obtain user's token info";
		goto exit;
	}

	TOKEN_USER * ptu = (TOKEN_USER*)lpUserData;

	if( !IsValidSid(ptu->User.Sid) ) {
		errMsg = "Got invalid user's SID";
		goto exit;
	}

	PSID userSid = ptu->User.Sid;

	ConvertSidToStringSid(userSid, &lpStr0);
	lpUserSid = lpStr0;
	lpStr0 = NULL;

	//
    // step +n:  Retrieve user name and domain name basing on user's SID.
	//
	if( !GetInfo(userSid, &lpStr0, &lpStr1) ) {
		errMsg = "Unable to retrieve user's name and domain";
		goto exit;
	};

	jclass jkl = jenv->FindClass("org/apache/harmony/security/x/security/auth/NTSidUserPrincipal");
	if( NULL == jkl || jenv->ExceptionCheck() ) {
		errMsg = "Could not find class NTSidUserPrincipal";
		goto exit;
	}
	jmethodID ctor = jenv->GetMethodID(jkl, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
	if( NULL == ctor || jenv->ExceptionCheck() ) {
		errMsg = "Could not find ctor at NTSidUserPrincipal class";
		goto exit;
	}

	jstring jstrSid = jenv->NewStringUTF(lpUserSid);
	jstring jstrUser = jenv->NewStringUTF(lpStr0);
	jstring jstrDomain = jenv->NewStringUTF(lpStr1);
	jobject obj = jenv->NewObject(jkl, ctor, jstrSid, jstrUser, jstrDomain);
	if( jenv->ExceptionCheck() ) {
		goto exit;
	}
	jenv->SetObjectField(thiz, jf_user, obj);
	if( jenv->ExceptionCheck() ) {
		goto exit;
	}
	
	LocalFree(lpStr0); lpStr0 = NULL;
	lpDomainName = lpStr1; 
	lpStr1 = NULL;

	//
	// Step +1: Obtain domain SID
	//
	if( !AllocateAndInitializeSid(
		&sia, 4, 
		*GetSidSubAuthority(userSid, 0), 
		*GetSidSubAuthority(userSid, 1), 
		*GetSidSubAuthority(userSid, 2),
		*GetSidSubAuthority(userSid, 3), 
		0, 0, 0, 0, 
		&domainSid)) {

		errMsg = "Unable to allocate domain SID";
		goto exit;
	}

	if( !IsValidSid(domainSid) ) {
		errMsg = "Got invalid domain SID";
		goto exit;
	}

	ConvertSidToStringSid(domainSid, &lpStr0);

	jstring jstrDomainSid = jenv->NewStringUTF(lpStr0);
	jenv->SetObjectField(thiz, jf_domainSid, jstrDomainSid);
	if( jenv->ExceptionCheck() ) {
		goto exit;
	}
	LocalFree(lpStr0); lpStr0 = NULL;

	//
	// step +1: get primary group sid
	//
	if( NULL == (lpGroupData = QueryInfo(hUser, TokenPrimaryGroup)) ) {
		errMsg = "Unable to get primaty group";
		goto exit;
	};

	PTOKEN_PRIMARY_GROUP ptpg = (PTOKEN_PRIMARY_GROUP)lpGroupData;
	PSID groupSid = ptpg->PrimaryGroup;

	if( !IsValidSid(groupSid) ) {
		errMsg = "Got invalid primary groups' SID";
		goto exit;
	}

	if( !GetInfo(groupSid, &lpStr0, &lpStr1) ) {
		errMsg = "Unable to get primary group's info";
		goto exit;
	}
	ConvertSidToStringSid(groupSid, &lpStr2);

	jclass jklassPrimaryGroup = jenv->FindClass("org/apache/harmony/security/x/security/auth/NTSidPrimaryGroupPrincipal");
	if( NULL == jklassPrimaryGroup || jenv->ExceptionCheck() ) {
		errMsg = "Could not find class NTSidPrimaryGroupPrincipal";
		goto exit;
	}

	ctor = jenv->GetMethodID(jklassPrimaryGroup, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
	if( NULL == ctor ) {
		errMsg = "Could not find appropriate ctor at NTSidPrimaryGroupPrincipal";
		goto exit;
	}

	jobject jobj = jenv->NewObject(jklassPrimaryGroup, ctor, 
		jenv->NewStringUTF(lpStr2), jenv->NewStringUTF(lpStr0), jenv->NewStringUTF(lpStr1));

	LocalFree(lpStr0); lpStr0 = NULL;
	LocalFree(lpStr1); lpStr1 = NULL;
	LocalFree(lpStr2); lpStr2 = NULL;


	if( jenv->ExceptionCheck() ) {
		goto exit;
	}
	jenv->SetObjectField(thiz, jf_mainGroup, jobj);

	//
	// step +1: get groups
	//
	if( NULL== (lpAllGroupsData = QueryInfo(hUser, TokenGroups)) ) {
		errMsg = "Unable to query user's groups";
		goto exit;
	}

	PTOKEN_GROUPS ptgs = (PTOKEN_GROUPS)lpAllGroupsData;

	jclass klassGroup = jenv->FindClass("org/apache/harmony/security/x/security/auth/NTSidGroupPrincipal");
	if( NULL == klassGroup || jenv->ExceptionCheck() ) {
		errMsg = "Could not find NTSidGroupPrincipal";
		goto exit;
	};

	jmethodID groupCtor3 = jenv->GetMethodID(klassGroup, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
	if( NULL == groupCtor3 || jenv->ExceptionCheck() ) {
		errMsg = "Could not find appropriate ctor with 3 Strings at NTSidGroupPrincipal";
		goto exit;
	};
	jmethodID groupCtor1 = jenv->GetMethodID(klassGroup, "<init>", "(Ljava/lang/String;)V");
	if( NULL == groupCtor1 || jenv->ExceptionCheck() ) {
		errMsg = "Could not find appropriate ctor at NTSidGroupPrincipal";
		goto exit;
	};

	// allocate an array 
	jobjectArray jgroups = jenv->NewObjectArray(ptgs->GroupCount, klassGroup, NULL);

	if( NULL == jgroups || jenv->ExceptionCheck() ) {
		errMsg = "Could not create array of NTSidGroupPrincipal";
		goto exit;
	};

	for( DWORD i=0; i<ptgs->GroupCount; i++ ) {

		ConvertSidToStringSid(ptgs->Groups[i].Sid, &lpStr2);

		jobject jobj;
		if( !GetInfo(ptgs->Groups[i].Sid, &lpStr0, &lpStr1) ) {
			jobj = jenv->NewObject(klassGroup, groupCtor1, jenv->NewStringUTF(lpStr2));
			//printf("SET_FIELD: %d] Simple Group: %s\n", i, lpStr2 );
		}
		else {
			jobj = jenv->NewObject(klassGroup, groupCtor3, 
				jenv->NewStringUTF(lpStr2), jenv->NewStringUTF(lpStr0), jenv->NewStringUTF(lpStr1));
//			printf("SET_FIELD: %d] Group: %s@%s \n\t %s\n", i, lpStr0, lpStr1, lpStr2 );
		}
		if( NULL != lpStr0 ) { LocalFree(lpStr0); lpStr0 = NULL; }
		if( NULL != lpStr1 ) { LocalFree(lpStr1); lpStr1 = NULL; }
		if( NULL != lpStr2 ) { LocalFree(lpStr2); lpStr2 = NULL; }
		if( NULL == jobj || jenv->ExceptionCheck() ) {
			goto exit;
		}
		jenv->SetObjectArrayElement(jgroups, i, jobj);
		if( jenv->ExceptionCheck() ) {
			goto exit;
		}
	};
	jenv->SetObjectField(thiz, jf_groups, jgroups);
	if( jenv->ExceptionCheck() ) {
		goto exit;
	}

	//
	// step +1: get itoken
	//

	//FIXME: on NT 'SecurityImpersonation'  is not supported. 
	// Check whether we support NT - just to be sure.
	if (!DuplicateToken (hUser, SecurityImpersonation, &iToken)) {
		errMsg = "Unable to duplicate impersonation token";
		goto exit;
	};

	// printf("_SET_FIELD: iToken: %d \n", ((long)iToken) );
	jenv->SetLongField(thiz, jf_token, ((jlong)iToken));
	if( jenv->ExceptionCheck() ) {
		goto exit;
	}

	dwError = 0;
exit:
	DWORD dwSaveError = GetLastError();

	if( NULL != lpUserData )		LocalFree(lpUserData);
	if( NULL != lpGroupData )		LocalFree(lpGroupData);
	if( NULL != lpAllGroupsData )	LocalFree(lpAllGroupsData);
	if( NULL != lpStr0 )			LocalFree(lpStr0);
	if( NULL != lpStr1 )			LocalFree(lpStr1);
	if( NULL != lpStr2 )			LocalFree(lpStr2);
	if( NULL != lpUserSid )			LocalFree(lpUserSid);
	if( NULL != lpDomainName)		LocalFree(lpDomainName);
	//
	if( NULL != domainSid )			FreeSid(domainSid);

	if( INVALID_HANDLE_VALUE != hUser ) CloseHandle(hUser);

	if( jenv->ExceptionCheck() ) {
		jenv->ExceptionDescribe();
	}
	else {
		if( (0 != dwError) || (NULL!=errMsg) ) {
			if( dwError == -1 ) {
				dwError = dwSaveError;
			}
			error(jenv, errMsg, dwError);
		}
	}
	return;
}

/*
 * Class:     org_apache_harmony_security_x_security_auth_module_NTSystem
 * Method:    free
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_security_x_security_auth_module_NTSystem_free(JNIEnv * jenv, jobject thiz) {
	HANDLE hTok = (HANDLE)jenv->GetLongField(thiz, jf_token);
	if( !(0 == hTok || INVALID_HANDLE_VALUE == hTok) ) {
		if( !CloseHandle(hTok) ) {
			error(jenv, "Unable to close handle", GetLastError());
		}
	}
}

void error(LPVOID lpJEnv, LPCSTR msg, DWORD dwErr ) {

	JNIEnv * jenv = (JNIEnv*)lpJEnv;

	LPVOID lpMsg = NULL;
	if (!FormatMessage(
		FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
        NULL, dwErr, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), // Default language
		(LPTSTR)&lpMsg, 0, NULL )) {
			// error in error(). 
		//assert(false);
	}

	LPVOID lpFullMsg = NULL;

	DWORD args[3] = {(DWORD)msg, dwErr, (DWORD)lpMsg};
	
	if (!FormatMessage(FORMAT_MESSAGE_ALLOCATE_BUFFER|FORMAT_MESSAGE_FROM_STRING|FORMAT_MESSAGE_ARGUMENT_ARRAY,
		"%1!s! (#%2!d!): \"%3!s!\"", 0, MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), // Default language
		(LPTSTR)&lpFullMsg, 0, (va_list*)args)) {
			// error in error(). 
		//
	}

	jclass excl = jenv->FindClass("java/lang/Error");
	if( NULL == excl ) {
		return;
	}
	jenv->ThrowNew(excl, (LPCSTR)lpFullMsg);

	// Free the buffer.
	LocalFree( lpMsg );
	LocalFree( lpFullMsg );

};