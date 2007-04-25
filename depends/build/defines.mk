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
XLIBS = -L/usr/X11R6/lib -lX11 -lXft
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
OSLIBS += -ldl
endif

EXEPATH=../
LIBPATH=$(HY_HDK)/lib/
DLLPATH=$(HY_HDK)/jdk/jre/bin/
SHAREDSUB=../shared/

DEFINES += -D_REENTRANT -DIPv6_FUNCTION_SUPPORT
INCLUDES += -I$(HY_HDK)/include -I$(HY_HDK)/jdk/include -I. -I$(SHAREDSUB)

ifndef HYDEBUGCFLAGS
HYDEBUGCFLAGS = -g
endif

ifndef HYRELEASECFLAGS  
HYRELEASECFLAGS = -O1
endif

ifeq ($(HY_CFG),release)
OPT += $(HYRELEASECFLAGS)
else
OPT += $(HYDEBUGCFLAGS)
endif

MDLLIBFILES = $(LIBPATH)libhycommon.a

ifeq ($(HY_NO_THR),false)
MDLLIBFILES += $(DLLPATH)libhythr$(HY_SHLIB_SUFFIX)
else
DEFINES += -DHY_NO_THR
endif

ifeq ($(HY_NO_SIG),false)
MDLLIBFILES += $(DLLPATH)libhysig$(HY_SHLIB_SUFFIX)
else
DEFINES += -DHY_NO_SIG
endif
