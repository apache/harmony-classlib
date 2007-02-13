#  Licensed to the Apache Software Foundation (ASF) under one or more
#  contributor license agreements.  See the NOTICE file distributed with
#  this work for additional information regarding copyright ownership.
#  The ASF licenses this file to You under the Apache License, Version 2.0
#  (the "License"); you may not use this file except in compliance with
#  the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

#
# Makefile for module 'luni'
#

!include <$(HY_HDK)\build\make\defines.mak>

LIBBASE=hyluni
DLLNAME=..\$(LIBBASE).dll
LIBNAME=$(LIBPATH)$(LIBBASE).lib
HYCFLAGS = $(HYCFLAGS) /I$(SHAREDSUB)
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

BUILDFILES = \
  $(SHAREDSUB)luni_copyright.obj $(SHAREDSUB)file.obj procimpl.obj \
  $(SHAREDSUB)oos.obj \
  $(SHAREDSUB)nethelp.obj $(SHAREDSUB)floatbits.obj \
  $(SHAREDSUB)ois.obj helpers.obj $(SHAREDSUB)math.obj \
  $(SHAREDSUB)luniglob.obj $(SHAREDSUB)proxy.obj $(SHAREDSUB)netif.obj \
  $(SHAREDSUB)cbigint.obj $(SHAREDSUB)dblparse.obj $(SHAREDSUB)fltparse.obj \
  $(SHAREDSUB)process.obj $(SHAREDSUB)bigint.obj $(SHAREDSUB)osc.obj \
  $(SHAREDSUB)socket.obj $(SHAREDSUB)inetadds.obj \
  $(SHAREDSUB)filedesc.obj $(SHAREDSUB)timezone.obj \
  OSFileSystemWin32.obj hyenv.obj\
  $(SHAREDSUB)OSFileSystem.obj OSMemoryWin32.obj OSMemory.obj \
  $(SHAREDSUB)OSNetworkSystem.obj OSNetworkSystemWin32.obj

VIRTFILES = hyluni.res

SYSLIBFILES = ws2_32.lib Iphlpapi.lib Mswsock.lib

MDLLIBFILES = $(MDLLIBFILES) \
  $(LIBPATH)hyzip.lib $(LIBPATH)hyzlib.lib \
  $(LIBPATH)hypool.lib $(LIBPATH)hyfdlibm.lib $(LIBPATH)vmi.lib

DLLBASE=0x13200000
COMMENT=/comment:"LUNI component native code. (c) Copyright 1991, 2005 The Apache Software Foundation or its licensors, as applicable."

!include <$(HY_HDK)\build\make\rules.mak>
