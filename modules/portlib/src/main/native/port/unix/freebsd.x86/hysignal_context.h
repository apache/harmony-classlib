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

#include "hyport.h"
#include <sys/ucontext.h>

#define MAX_UNIX_SIGNAL_TYPES  NSIG

#define __USE_GNU
#include <dlfcn.h>
#undef __USE_GNU

typedef struct HyPlatformSignalInfo
{
  mcontext_t *mContext;
  Dl_info dl_info;
} HyPlatformSignalInfo;

typedef struct HyUnixSignalInfo
{
  U_32 portLibrarySignalType;
  void *handlerAddress;
  void *handlerAddress2;
  siginfo_t *sigInfo;
  struct HyPlatformSignalInfo platformSignalInfo;
} HyUnixSignalInfo;

U_32 infoForFPR (struct HyPortLibrary *portLibrary,
                 struct HyUnixSignalInfo *info, I_32 index,
                 const char **name, void **value);

U_32 infoForGPR (struct HyPortLibrary *portLibrary,
                 struct HyUnixSignalInfo *info, I_32 index,
                 const char **name, void **value);

U_32 infoForModule (struct HyPortLibrary *portLibrary,
                    struct HyUnixSignalInfo *info, I_32 index,
                    const char **name, void **value);

U_32 infoForControl (struct HyPortLibrary *portLibrary,
                     struct HyUnixSignalInfo *info, I_32 index,
                     const char **name, void **value);

U_32 infoForSignal (struct HyPortLibrary *portLibrary,
                    struct HyUnixSignalInfo *info, I_32 index,
                    const char **name, void **value);

void fillInUnixSignalInfo (struct HyPortLibrary *portLibrary,
                           void *contextInfo,
                           struct HyUnixSignalInfo *hyInfo);
