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
#if defined( _WINDOWS)
#include <windows.h>
BOOL APIENTRY DllMain( HANDLE hModule, 
                       DWORD  ul_reason_for_call, 
                       LPVOID lpReserved
                     )
{
    return TRUE;
}


    #include "nixemu.h"
#else
    #include <unistd.h>
    #include <pwd.h>
    #include <grp.h>
#endif // ifdef _WINDOWS

#include <stdlib.h>
#include <assert.h>

#include <jni.h>

jfieldID jf_uid = NULL;
jfieldID jf_username = NULL;
jfieldID jf_gid = NULL;
jfieldID jf_groupname = NULL;

jfieldID jf_groups = NULL;
jfieldID jf_groupsNames = NULL;

jclass jclassString = NULL;

JNIEXPORT void JNICALL Java_org_apache_harmony_security_x_security_auth_module_UnixSystem_load(JNIEnv * jenv, jobject thiz) {

    if( NULL == jf_uid ) {
        jclass klass = jenv->GetObjectClass(thiz);
        if( NULL == klass ) {
            jclass klassErr = jenv->FindClass("java/lang/Error");
            assert(klassErr);
            jenv->ThrowNew(klassErr, "Could not obtain object's Class");
            return;
        }

        if( NULL == (jf_uid = jenv->GetFieldID(klass, "uid", "J"))) {
            jclass klassErr = jenv->FindClass("java/lang/Error");
            assert(klassErr);
            jenv->ThrowNew(klassErr, "Could not find field \"uid\" of type long");
            return;
        }
        if( NULL == (jf_username = jenv->GetFieldID(klass, "username", "Ljava/lang/String;"))) {
            jclass klassErr = jenv->FindClass("java/lang/Error");
            assert(klassErr);
            jenv->ThrowNew(klassErr, "Could not find field \"username\" of type String");
            return;
        }
        if( NULL == (jf_gid = jenv->GetFieldID(klass, "gid", "J"))) {
            jclass klassErr = jenv->FindClass("java/lang/Error");
            assert(klassErr);
            jenv->ThrowNew(klassErr, "Could not find field \"gid\" of type long");
            return;
        }
        if( NULL == (jf_groupname = jenv->GetFieldID(klass, "groupname", "Ljava/lang/String;"))) {
            jclass klassErr = jenv->FindClass("java/lang/Error");
            assert(klassErr);
            jenv->ThrowNew(klassErr, "Could not find field \"groupname\" of type String");
            return;
        }

        if( NULL == (jf_groups = jenv->GetFieldID(klass, "groups", "[J"))) {
            jclass klassErr = jenv->FindClass("java/lang/Error");
            assert(klassErr);
            jenv->ThrowNew(klassErr, "Could not find field \"groups\" of type long[]");
            return;
        }
        if( NULL == (jf_groupsNames = jenv->GetFieldID(klass, "groupsNames", "[Ljava/lang/String;"))) {
            jclass klassErr = jenv->FindClass("java/lang/Error");
            assert(klassErr);
            jenv->ThrowNew(klassErr, "Could not find field \"groupsNames\" of type String[]");
            return;
        }
        if( NULL == (jclassString = jenv->FindClass("java/lang/String")) ) {
            jclass klassErr = jenv->FindClass("java/lang/Error");
            assert(klassErr);
            jenv->ThrowNew(klassErr, "Could not find class java/lang/String");
            return;
        }
        jclassString = (jclass)jenv->NewGlobalRef(jclassString);
    }


    //
    uid_t uid = getuid();
    jenv->SetLongField(thiz, jf_uid, (jlong)uid);
    gid_t gid = getgid();
    jenv->SetLongField(thiz, jf_gid, (jlong)gid);
    //
    passwd * pp = getpwuid(uid);
    jenv->SetObjectField(thiz, jf_username, jenv->NewStringUTF(pp->pw_name));
    //
    group * pg = getgrgid(gid);
    jenv->SetObjectField(thiz, jf_groupname, jenv->NewStringUTF(pg->gr_name));
    //
    int gcount = getgroups(0, NULL);
    if( 0 != gcount ) {
        //
        gid_t * gids = (gid_t*)malloc(gcount*sizeof(gid_t));
        //
        getgroups(gcount, gids);
        jlongArray jgs = jenv->NewLongArray(gcount);
        jlong * jgs_raw = jenv->GetLongArrayElements(jgs, NULL);
        jobjectArray jgsnames = jenv->NewObjectArray(gcount, jclassString, NULL);
    
        for( int i=0; i<gcount; i++ ) {
            group * g = getgrgid(gids[i]);
            jgs_raw[i] = g->gr_gid;
            jenv->SetObjectArrayElement(jgsnames, i, jenv->NewStringUTF(g->gr_name));
        }
        jenv->ReleaseLongArrayElements(jgs, jgs_raw, 0); // here: 0='update java array with the passed values'
        jenv->SetObjectField(thiz, jf_groups, jgs);
        jenv->SetObjectField(thiz, jf_groupsNames, jgsnames);
        //
        free(gids);
        //
    };
};


