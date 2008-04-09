# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#  
#      http://www.apache.org/licenses/LICENSE-2.0
#  
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

#
# Configuration Makefile
#

CXX = $(CC)
CPP = $(CC) -E
AS = as
AR = ar
ARFLAGS = rcv
DLL_LD = $(CC)
DLL_LDFLAGS = -shared -Wl,-soname=$(@F) -Wl,--version-script,$(EXPFILE)
CXX_DLL_LD = $(CXX)
STDCLIBS = -lstdc++
OSLIBS = -lc -lm
XLIBS = -L/usr/X11R6/lib -lX11 -lXft -lXext -lXtst
MDLLIBPREFIX = -Xlinker --start-group
MDLLIBSUFFIX = -Xlinker --end-group
EXELDFLAGS = $(LDFLAGS)
EXERPATHPREFIX = -Xlinker -z -Xlinker origin -Xlinker -rpath \
	-Xlinker \$$ORIGIN/ -Xlinker -rpath-link \
	-Xlinker

include $(HY_HDK)/build/make/platform/$(HY_PLATFORM).mk

ifeq ($(RANLIB),)
RANLIB=echo
endif

ifneq ($(HY_OS),freebsd)
ifneq ($(HY_OS),zos)
OSLIBS += -ldl
endif
endif

EXEPATH=../
LIBPATH=$(HY_HDK)/lib/

ifneq ($(HY_OS),zos)
DLLPATH=$(HY_HDK)/jdk/jre/bin/
else
# On z/OS set DLLPATH to LIBPATH so we link against .x export files in
# $(HY_HDK)/lib instead of directly against the .so libraries.
DLLPATH=$(LIBPATH)
endif
SHAREDSUB=../shared/

DEFINES += -D_REENTRANT
INCLUDES += -I$(HY_HDK)/include -I$(HY_HDK)/jdk/include -I. -I$(SHAREDSUB)

ifndef HYDEBUGCFLAGS
ifneq ($(HY_OS),zos)
HYDEBUGCFLAGS = -ggdb -O0
else
# z/OS has different debug flags
HYDEBUGCFLAGS = -g -O0
endif
endif

ifndef HYRELEASECFLAGS  
HYRELEASECFLAGS = -O1 -DNDEBUG
endif

ifeq ($(HY_CFG),release)
OPT += $(HYRELEASECFLAGS)
else
OPT += $(HYDEBUGCFLAGS)
endif

MDLLIBFILES = $(LIBPATH)libhycommon.a

ifeq ($(HY_NO_THR),false)
ifeq ($(HY_THR_NO_DEPLOY), true)
MDLLIBFILES += $(HY_HDK)/../modules/portlib/src/main/native/thread/libhythr$(HY_LINKLIB_SUFFIX)
else
MDLLIBFILES += $(DLLPATH)libhythr$(HY_LINKLIB_SUFFIX)
endif
else
DEFINES += -DHY_NO_THR
endif

ifeq ($(HY_NO_SIG),false)
MDLLIBFILES += $(DLLPATH)libhysig$(HY_LINKLIB_SUFFIX)
else
DEFINES += -DHY_NO_SIG
endif

ifeq ($(HY_ZIP_API),true)
DEFINES += -DHY_ZIP_API
endif

ifeq ($(HY_LOCAL_ZLIB),true)
DEFINES += -DHY_LOCAL_ZLIB
OSLIBS += -lz
MDLLIBZLIB =
else
MDLLIBZLIB += $(DLLPATH)libhyzlib$(HY_LINKLIB_SUFFIX)
endif
