# Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
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
# Makefile for module 'text'
#

!include <..\defines.mak>

SHAREDSUB=$(SHARED)text\# comment to avoid \ being treated as continuation
LIBBASE=hytext
DLLNAME=$(DLLPATH)$(LIBBASE).dll
LIBNAME=$(LIBPATH)$(LIBBASE).lib
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def 
HYCFLAGS = $(HYCFLAGS) /I$(SHAREDSUB)
BUILDFILES = text_copyright.obj $(SHAREDSUB)BidiWrapper.obj
VIRTFILES = hytext.res
SYSLIBFILES = ws2_32.lib Iphlpapi.lib

MDLLIBFILES = \
  $(LIBPATH)hysig.lib $(LIBPATH)hycommon.lib $(LIBPATH)hypool.lib $(LIBPATH)hyfdlibm.lib \
  $(LIBPATH)hythr.lib $(LIBPATH)vmi.lib unicode\icuuc.lib

DLLBASE=0x13300000
COMMENT=/comment:"Text component native code. (c) Copyright 1993, 2005 The Apache Software Foundation or its licensors, as applicable."

!include <..\rules.mak>
