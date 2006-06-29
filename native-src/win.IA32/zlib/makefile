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

ARCHIVE_NATIVE=..\..\..\modules\archive\src\main\native\# temporary addition until zlib moves into archive module

# override HYCFLAGS to get different warning behaviour (W3 not WX)
HYCFLAGS = \
  -Ogityb1 -W3 -GF -Gs -MD -Zi -Zm400 \
  -D_DLL -D_MT -DWIN32 -D_WIN32_WINNT=0x0400 -D_WINSOCKAPI_ -DWINVER=0x0400 \
  $(VMDEBUG) /I..\include /I$(SHARED)include /I. /I$(ARCHIVE_NATIVE)include\shared
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

BUILDFILES = \
  $(SHAREDSUB)zlib_copyright.obj $(ARCHIVE_NATIVE)zlib_dist\adler32.obj $(ARCHIVE_NATIVE)zlib_dist\compress.obj \
  $(ARCHIVE_NATIVE)zlib_dist\crc32.obj $(ARCHIVE_NATIVE)zlib_dist\deflate.obj $(ARCHIVE_NATIVE)zlib_dist\gzio.obj $(ARCHIVE_NATIVE)zlib_dist\infback.obj \
  $(ARCHIVE_NATIVE)zlib_dist\inffast.obj $(ARCHIVE_NATIVE)zlib_dist\inflate.obj $(ARCHIVE_NATIVE)zlib_dist\inftrees.obj $(ARCHIVE_NATIVE)zlib_dist\trees.obj \
  $(ARCHIVE_NATIVE)zlib_dist\uncompr.obj $(ARCHIVE_NATIVE)zlib_dist\zutil.obj

VIRTFILES = $(LIBBASE).res
DLLBASE=0x11700000
COMMENT=/comment:"zlib data compression library Copyright (C) 1995-2003 Jean-loup Gailly and Mark Adler"

!include <$(HY_HDK)\build\make\rules.mak>
