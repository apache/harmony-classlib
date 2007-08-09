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
/**
 * @author Alexey A. Petrenko
 * @version $Revision$
 */
#include <jni.h>

#include <windows.h>
#include "org_apache_harmony_awt_gl_windows_WinGraphicsEnvironment.h"

#define MAX_MONITOR_NUMBER 256

typedef struct _Monitors {
    JNIEnv *env;
    jobject monitors[MAX_MONITOR_NUMBER];
    int index;
    jclass deviceClass;
    jmethodID deviceInit;
} Monitors;

/*
 * This is a callback function for EnumDisplayMonitors.
 * It creates a new instance of WinGraphicsDevice class
 * and stores it in the array.
 */
BOOL CALLBACK MonitorDevices(HMONITOR hMonitor, HDC hdc, LPRECT rect, LPARAM data)
{
    MONITORINFOEX mi;
    mi.cbSize = sizeof(MONITORINFOEX);
    GetMonitorInfo(hMonitor, &mi);

    Monitors *m = (Monitors *)data;

    jstring id = m->env->NewStringUTF(mi.szDevice);

    m->monitors[m->index++] = m->env->NewObject(m->deviceClass, m->deviceInit,
        rect->left, rect->top, rect->right, rect->bottom, id, mi.dwFlags & MONITORINFOF_PRIMARY);

    return TRUE;
}

/*
 * Class:     org_apache_harmony_awt_gl_windows_WinGraphicsEnvironment
 * Method:    enumerateDisplayDevices
 * Signature: ()[Lorg/apache/harmony/awt/gl/windows/WinGraphicsDevice;
 */
JNIEXPORT jobjectArray JNICALL Java_org_apache_harmony_awt_gl_windows_WinGraphicsEnvironment_enumerateDisplayDevices
  (JNIEnv *env, jobject obj) 
{
    Monitors monitors;
    monitors.env = env;
    monitors.index = 0;
    monitors.deviceClass = env->FindClass("org/apache/harmony/awt/gl/windows/WinGraphicsDevice");
    monitors.deviceInit = env->GetMethodID(monitors.deviceClass, "<init>", "(IIIILjava/lang/String;Z)V"); 
    
    if (EnumDisplayMonitors(NULL, NULL, &MonitorDevices, (LPARAM)&monitors) == 0)
        return 0;

    jobjectArray array = env->NewObjectArray(monitors.index, monitors.deviceClass, 0);
    
    for (int i = 0; i < monitors.index; i++)
        env->SetObjectArrayElement(array, i, monitors.monitors[i]); 
    
    return array;
}
