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
DLL_LD = $(CC)
CXX_DLL_LD = $(CXX)
OSLIBS = -lc -lm

include $(HY_HDK)/build/make/platform/$(HY_PLATFORM).mk

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
