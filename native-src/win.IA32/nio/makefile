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
# Makefile for module 'nio'
#

!include <..\defines.mak>

SHAREDSUB=$(SHARED)nio\# comment to avoid \ being treated as continuation
LIBBASE=hynio
DLLNAME=$(DLLPATH)$(LIBBASE).dll
LIBNAME=$(LIBPATH)$(LIBBASE).lib

HYCFLAGS = $(HYCFLAGS) /I$(SHAREDSUB) /I$(SHARED)common /I..\zip /I..\fdlibm
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

BUILDFILES = \
  $(SHAREDSUB)nio_copyright.obj OSFileSystemWin32.obj \
  $(SHAREDSUB)OSFileSystem.obj OSMemoryWin32.obj \
  OSMemory.obj nethelp.obj helpers.obj socket.obj OSNetworkSystem.obj \
  OSNetworkSystemWin32.obj

VIRTFILES = $(LIBBASE).res

SYSLIBFILES = ws2_32.lib Iphlpapi.lib Mswsock.lib

MDLLIBFILES = \
  ..\lib\hycommon.lib ..\lib\hysig.lib ..\lib\hyzip.lib ..\lib\hyzlib.lib \
  ..\lib\hypool.lib ..\lib\hyfdlibm.lib ..\lib\hythr.lib ..\lib\vmi.lib

DLLBASE=0x13300000
COMMENT=/comment:"NIO component native code. (c) Copyright 1991, 2005 The Apache Software Foundation or its licensors, as applicable."

!include <..\rules.mak>
