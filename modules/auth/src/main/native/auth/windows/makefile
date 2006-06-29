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
# Makefile for module 'auth'
#

!include <$(HY_HDK)\build\make\defines.mak>

SHAREDSUB=..\shared\# comment to avoid \ being treated as continuation
LIBBASE=hyauth
DLLNAME=..\$(LIBBASE).dll
LIBNAME=$(LIBPATH)$(LIBBASE).lib
BUILDFILES = $(SHAREDSUB)auth_copyright.obj authwin32.obj
VIRTFILES = $(LIBBASE).res
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

SYSLIBFILES = ws2_32.lib Iphlpapi.lib

MDLLIBFILES = $(LIBPATH)hycommon.lib $(LIBPATH)hysig.lib $(LIBPATH)hyzip.lib \
	$(LIBPATH)hyzlib.lib $(LIBPATH)hypool.lib $(LIBPATH)hyfdlibm.lib \
	$(LIBPATH)hythr.lib $(LIBPATH)vmi.lib
DLLBASE=0x1300000
COMMENT=/comment:"Auth component native code. (c) Copyright 2005, 2006 The Apache Software Foundation or its licensors, as applicable."

!include <$(HY_HDK)\build\make\rules.mak>
