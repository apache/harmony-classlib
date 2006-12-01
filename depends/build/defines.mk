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

EXEPATH=../
LIBPATH=$(HY_HDK)/lib/
DLLPATH=$(HY_HDK)/jdk/jre/bin/
SHAREDSUB=../shared/

CFLAGS = -O1 $(HY_CFLAGS) -DLINUX -D_REENTRANT -DIPv6_FUNCTION_SUPPORT \
         -D$(HY_ARCH_DEFINE) $(VMDEBUG) -I$(HY_HDK)/include -I$(HY_HDK)/jdk/include -I. -I$(SHAREDSUB)
