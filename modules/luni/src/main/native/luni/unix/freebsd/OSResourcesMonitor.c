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

/*
 * Utilities to monitor system resources.
 */
#include <sys/types.h>
#include <sys/sysctl.h>
#include <sys/vmmeter.h>

#include <hyport.h>
#include "OSResourcesMonitor.h"

static unsigned int MEMORYLOADTHRESHOLD = 98; // 98% load

/*
 * Class:     org_apache_harmony_luni_platform_OSResourcesMonitor
 * Method:    isSystemPhysicalMemoryLow
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_apache_harmony_luni_platform_OSResourcesMonitor_isSystemPhysicalMemoryLow
  (JNIEnv *env, jclass thizClass)
{
  unsigned long free;
  unsigned long total;
  int len = sizeof(free);
  
  if (sysctlbyname("vm.stats.vm.v_free_count", &free, &len, NULL, 0) == -1) {
    return FALSE;	
  }
  if (sysctlbyname("vm.stats.vm.v_page_count", &total, &len, NULL, 0) == -1) {
    return FALSE;	
  }
  int memoryLoad = 100 - ((float)(free/total)) * 100;
  if(memoryLoad >= MEMORYLOADTHRESHOLD) {
    return TRUE;
  }
  return FALSE;	
}
