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

LCMS_DIR=$(HY_HDK)\..\depends\libs\build\lcms\# comment to avoid continuation

LIBBASE=lcmm
DLLNAME=..\$(LIBBASE).dll
LIBNAME=$(LIBPATH)$(LIBBASE).lib
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

# TOFIX: fix warns and use default flags.  That is:
# HYCFLAGS = $(HYCFLAGS) /I$(SHAREDSUB) /I$(LCMS_DIR) /I$(SHAREDSUB)include

HYCFLAGS = \
  -Ogityb1 -W3 -GF -Gs -MD -Zi -Zm400 \
  -D_DLL -D_MT -DWIN32 -D_WIN32_WINNT=0x0400 -D_WINSOCKAPI_ -DWINVER=0x0400 \
  $(VMDEBUG) /I$(HY_HDK)\include /I$(HY_HDK)\jdk\include /I. \
  /I$(SHAREDSUB) /I$(LCMS_DIR) /I$(SHAREDSUB)include

BUILDFILES = \
  $(SHAREDSUB)cmmerror.obj \
  $(SHAREDSUB)cmmio.obj \
  $(SHAREDSUB)cmmxforms.obj \
  $(SHAREDSUB)Exceptions.obj \
  $(SHAREDSUB)NativeCMM.obj \
  $(SHAREDSUB)NativeImageFormat.obj

VIRTFILES = $(LIBBASE).res
SYSLIBFILES = ws2_32.lib Iphlpapi.lib

MDLLIBFILES = \
  $(LIBPATH)hysig.lib $(LIBPATH)hycommon.lib $(LIBPATH)hypool.lib \
  $(LIBPATH)hythr.lib $(LIBPATH)vmi.lib $(LCMS_DIR)lcms114.lib

DLLBASE=0x13300000
COMMENT=/comment:"LCMM Wrapper native code. (c) Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable."

!include <$(HY_HDK)\build\make\rules.mak>
