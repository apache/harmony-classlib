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
# Makefile for module 'luni'
#

!include <..\defines.mak>

SHAREDSUB=$(SHARED)luni\# comment to avoid \ being treated as continuation
LIBBASE=hyluni
DLLNAME=$(DLLPATH)$(LIBBASE).dll
LIBNAME=$(LIBPATH)$(LIBBASE).lib
HYCFLAGS = $(HYCFLAGS) /I$(SHAREDSUB) /I$(SHARED)common /I..\zip \
	   /I$(SHARED)fdlibm
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

BUILDFILES = \
  $(SHAREDSUB)luni_copyright.obj $(SHAREDSUB)file.obj procimpl.obj \
  $(SHAREDSUB)oos.obj $(SHAREDSUB)fileis.obj $(SHAREDSUB)simpl.obj \
  $(SHAREDSUB)psimpl2.obj nethelp.obj $(SHAREDSUB)floatbits.obj \
  $(SHAREDSUB)rafile.obj $(SHAREDSUB)ois.obj helpers.obj $(SHAREDSUB)math.obj \
  $(SHAREDSUB)luniglob.obj $(SHAREDSUB)proxy.obj $(SHAREDSUB)netif.obj \
  $(SHAREDSUB)process.obj $(SHAREDSUB)bigint.obj $(SHAREDSUB)osc.obj \
  $(SHAREDSUB)socket.obj $(SHAREDSUB)inetadds.obj $(SHAREDSUB)pssimpl.obj \
  $(SHAREDSUB)filedesc.obj $(SHAREDSUB)timezone.obj $(SHAREDSUB)pmsimpl.obj \
  pdsimpl.obj $(SHAREDSUB)fileos.obj OSFileSystemWin32.obj \
  $(SHAREDSUB)OSFileSystem.obj OSMemoryWin32.obj OSMemory.obj \
  OSNetworkSystem.obj OSNetworkSystemWin32.obj

VIRTFILES = hyluni.res

SYSLIBFILES = ws2_32.lib Iphlpapi.lib Mswsock.lib

MDLLIBFILES = \
  ..\lib\hycommon.lib ..\lib\hysig.lib ..\lib\hyzip.lib ..\lib\hyzlib.lib \
  ..\lib\hypool.lib ..\lib\hyfdlibm.lib ..\lib\hythr.lib ..\lib\vmi.lib

DLLBASE=0x13200000
COMMENT=/comment:"LUNI component native code. (c) Copyright 1991, 2005 The Apache Software Foundation or its licensors, as applicable."

!include <..\rules.mak>
