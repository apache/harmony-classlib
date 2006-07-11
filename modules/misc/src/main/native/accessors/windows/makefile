# Copyright 2006 The Apache Software Foundation or its licensors, as
# applicable
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

!include <$(HY_HDK)\build\make\defines.mak>

LIBBASE=accessors
DLLNAME=..\$(LIBBASE).dll
LIBNAME=$(LIBPATH)$(LIBBASE).lib
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

# TOFIX: This is only copied to remove -WX due to errors which should
# be fixed - line 112 ..Malloc.cpp gives warning about cast from jlong
# to size_t.
HYCFLAGS = -Ogityb1 -GF -Gs -MD -Zi -Zm400 \
  -D_DLL -D_MT -DWIN32 -D_WIN32_WINNT=0x0400 -D_WINSOCKAPI_ -DWINVER=0x0400 \
  $(VMDEBUG) /I$(HY_HDK)\include /I$(HY_HDK)\jdk\include /I. /I$(SHAREDSUB)

BUILDFILES = \
  $(SHAREDSUB)org_apache_harmony_misc_accessors_ArrayAccessorImpl.obj \
  $(SHAREDSUB)org_apache_harmony_misc_accessors_Malloc.obj \
  $(SHAREDSUB)org_apache_harmony_misc_accessors_MemoryAccessorNativeImpl.obj \
  $(SHAREDSUB)org_apache_harmony_misc_accessors_ObjectAccessorImpl.obj \
  $(SHAREDSUB)org_apache_harmony_misc_accessors_StringAccessorImpl.obj
VIRTFILES = accessors.res
SYSLIBFILES = ws2_32.lib Iphlpapi.lib

MDLLIBFILES = \
  $(LIBPATH)hysig.lib $(LIBPATH)hycommon.lib $(LIBPATH)hypool.lib $(LIBPATH)hyfdlibm.lib \
  $(LIBPATH)hythr.lib $(LIBPATH)vmi.lib

DLLBASE=0x13300000
COMMENT=/comment:"Accessors native code. (c) Copyright 2006 The Apache Software Foundation or its licensors, as applicable."

!include <$(HY_HDK)\build\make\rules.mak>
