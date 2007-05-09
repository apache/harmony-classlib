# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
# 
#     http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

TARGETOS = WINNT # Need _WIN32_WINNT=0x0500 to get GLYPHSET defined.
APPVER = 5.0 
!include <$(HY_HDK)\build\make\defines.mak>

LIBBASE=fontlib
DLLNAME=..\$(LIBBASE).dll
LIBNAME=$(LIBPATH)$(LIBBASE).lib
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

HYCFLAGS = $(HYCFLAGS) /Iinclude /I..\..\gl\windows\include \
  -DUNICODE -D_UNICODE

BUILDFILES = winFont.obj

VIRTFILES = $(LIBBASE).res
SYSLIBFILES = ws2_32.lib Iphlpapi.lib gdi32.lib GdiPlus.lib advapi32.lib

MDLLIBFILES = $(MDLLIBFILES) $(LIBPATH)hypool$(HY_LINKLIB_SUFFIX) $(LIBPATH)vmi$(HY_LINKLIB_SUFFIX)

DLLBASE=0x13300000
COMMENT=/comment:"Win32 Font native code. (c) Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable."

!include <$(HY_HDK)\build\make\rules.mak>
