# Copyright 2006 The Apache Software Foundation or its licensors, as applicable
# 
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
# 
#     http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

#
# Makefile for module 'nio'
#

!include <$(HY_HDK)\build\make\defines.mak>

SHAREDSUB=..\shared\# comment to avoid \ being treated as continuation
LIBBASE=hynio
DLLNAME=..\$(LIBBASE).dll
LIBNAME=$(LIBPATH)$(LIBBASE).lib
HYCFLAGS = $(HYCFLAGS) /I$(SHAREDSUB) /I$(SHARED)common /I$(SHARED)fdlibm
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

BUILDFILES = \
  $(SHAREDSUB)DirectBufferUtil.obj $(SHAREDSUB)AddressUtil.obj
VIRTFILES = hynio.res

SYSLIBFILES = ws2_32.lib Iphlpapi.lib

MDLLIBFILES = \
  $(LIBPATH)hycommon.lib $(LIBPATH)hysig.lib $(LIBPATH)hyzip.lib $(LIBPATH)hyzlib.lib \
  $(LIBPATH)hypool.lib $(LIBPATH)hyfdlibm.lib $(LIBPATH)hythr.lib $(LIBPATH)vmi.lib
  
DLLBASE=0x13200000
COMMENT=/comment:"nio component native code. (c) Copyright 1991, 2005 The Apache Software Foundation or its licensors, as applicable."

!include <$(HY_HDK)\build\make\rules.mak>
