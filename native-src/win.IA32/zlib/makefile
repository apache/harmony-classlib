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
# Makefile for module 'zlib'
#

!include <$(HY_HDK)\build\make\defines.mak>

SHAREDSUB=$(SHARED)zlib\# comment to avoid \ being treated as continuation
LIBBASE=hyzlib
DLLNAME=..\$(LIBBASE).dll
LIBNAME=$(LIBPATH)$(LIBBASE).lib

# override HYCFLAGS to get different warning behaviour (W3 not WX)
HYCFLAGS = \
  -Ogityb1 -W3 -GF -Gs -MD -Zi -Zm400 \
  -D_DLL -D_MT -DWIN32 -D_WIN32_WINNT=0x0400 -D_WINSOCKAPI_ -DWINVER=0x0400 \
  $(VMDEBUG) /I..\include /I$(SHARED)include /I. /I..\..\..\modules\archive\src\main\native\include\shared
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

BUILDFILES = \
  $(SHAREDSUB)zlib_copyright.obj dist\adler32.obj dist\compress.obj \
  dist\crc32.obj dist\deflate.obj dist\gzio.obj dist\infback.obj \
  dist\inffast.obj dist\inflate.obj dist\inftrees.obj dist\trees.obj \
  dist\uncompr.obj dist\zutil.obj

VIRTFILES = $(LIBBASE).res
DLLBASE=0x11700000
COMMENT=/comment:"zlib data compression library Copyright (C) 1995-2003 Jean-loup Gailly and Mark Adler"

!include <$(HY_HDK)\build\make\rules.mak>
