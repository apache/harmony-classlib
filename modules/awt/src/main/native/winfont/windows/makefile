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

!include <$(HY_HDK)\build\make\defines.mak>

LIBBASE=fontlib
DLLNAME=..\$(LIBBASE).dll
LIBNAME=$(LIBPATH)$(LIBBASE).lib
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

# TOFIX: fix warning so we can use default HYCFLAGS -WX
HYCFLAGS = \
  -Ogityb1 -W3 -GF -Gs -MD -Zi -Zm400 \
  -D_DLL -D_MT -DWIN32 -D_WIN32_WINNT=0x0501 -D_WINSOCKAPI_ -DWINVER=0x0400 \
  $(VMDEBUG) /I$(HY_HDK)\include /I$(HY_HDK)\jdk\include /I. \
  /Iinclude /I..\..\gl\windows\include \
  -DUNICODE -D_UNICODE

BUILDFILES = \
  Exceptions.obj \
  winFont.obj

VIRTFILES = $(LIBBASE).res
SYSLIBFILES = ws2_32.lib Iphlpapi.lib gdi32.lib GdiPlus.lib advapi32.lib

MDLLIBFILES = \
  $(LIBPATH)hysig.lib $(LIBPATH)hycommon.lib $(LIBPATH)hypool.lib \
  $(LIBPATH)hythr.lib $(LIBPATH)vmi.lib 

DLLBASE=0x13300000
COMMENT=/comment:"Win32 Font native code. (c) Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable."

!include <$(HY_HDK)\build\make\rules.mak>
