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

PNG_DIR=$(HY_HDK)\..\depends\libs\build\png\# avoid continuation

LIBBASE=gl
DLLNAME=..\$(LIBBASE).dll
LIBNAME=$(LIBPATH)$(LIBBASE).lib
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

# TOFIX: fix warning so we can use default HYCFLAGS
# That is:
# HYCFLAGS = $(HYCFLAGS) /I$(SHAREDSUB)include /I$(SHAREDSUB) \
#            /I$(PNG_DIR) /I..\..\lcmm\shared /Iinclude
# Need WINVER=0x0500 to get HMONITOR defined.
HYCFLAGS = \
  -Ogityb1 -W3 -GF -Gs -MD -Zi -Zm400 \
  -D_DLL -D_MT -DWIN32 -D_WIN32_WINNT=0x0400 -D_WINSOCKAPI_ -DWINVER=0x0500 \
  $(VMDEBUG) /I$(HY_HDK)\include /I$(HY_HDK)\jdk\include /I. \
  /I$(SHAREDSUB)include /I$(SHAREDSUB) \
  /I$(PNG_DIR) /I..\..\lcmm\shared /Iinclude

BUILDFILES = \
  BitmapSurface.obj \
  GDIBlitter.obj \
  WinGDIPGraphics2D.obj \
  WinGraphicsEnvironment.obj \
  WinThemeGraphics.obj \
  $(SHAREDSUB)blitter.obj \
  $(SHAREDSUB)gifdecoder.obj \
  $(SHAREDSUB)LUTTables.obj \
  $(SHAREDSUB)pngdecoder.obj \
  $(SHAREDSUB)SurfaceDataStructure.obj \
  ..\..\lcmm\shared\Exceptions.obj

VIRTFILES = $(LIBBASE).res
SYSLIBFILES = ws2_32.lib Iphlpapi.lib \
              Msimg32.lib gdiplus.lib User32.lib uxTheme.lib 

MDLLIBFILES = \
  $(LIBPATH)hysig.lib $(LIBPATH)hycommon.lib $(LIBPATH)hypool.lib \
  $(LIBPATH)hythr.lib $(LIBPATH)vmi.lib \
  $(LIBPATH)hyzlib.lib $(PNG_DIR)libpng.lib

DLLBASE=0x13300000
COMMENT=/comment:"GL native code. (c) Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable."

!include <$(HY_HDK)\build\make\rules.mak>
