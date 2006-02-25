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

#
# Makefile for module 'auth'
#

APPVER=4.0
TARGETOS=WIN95
SEHMAP = TRUE
!include <win32.mak>

DLLFILENAME=hyauth.dll# declaration

DLLNAME=..\hyauth.dll# declaration

LIBNAME=hyauth# declaration

LIBPATH=..\lib\# declaration

.c.obj:
	$(cc) -DWINVER=0x0400 -D_WIN32_WINNT=0x0400 $(cflags) -D_MT -D_DLL -MD -D_WINSOCKAPI_ -DWIN32 -Ogityb1 -Gs -GF -Zm400 -Zi -WX  /I..\include /I..\auth $(VMDEBUG) $*.c

.rc.res:
	rc -I..\include $<

BUILDFILES = auth_copyright.obj authwin32.obj

VIRTFILES = hyauth.res

SYSLIBFILES = ws2_32.lib Iphlpapi.lib

MDLLIBFILES = ..\lib\hycommon.lib ..\lib\hysig.lib ..\lib\hyzip.lib \
	..\lib\hyzlib.lib ..\lib\hypool.lib ..\lib\hyfdlibm.lib \
	..\lib\hythr.lib ..\lib\vmi.lib

all: $(DLLNAME)
	
$(LIBPATH)$(LIBNAME).lib: $(BUILDFILES) $(VIRTFILES) $(MDLLIBFILES) 
	$(implib) /NOLOGO -subsystem:windows -out:$(LIBPATH)$(LIBNAME).lib \
	-def:$(LIBNAME).def -machine:$(CPU) \
	$(BUILDFILES) $(VIRTFILES) $(MDLLIBFILES)


$(DLLNAME): $(LIBPATH)$(LIBNAME).lib
	link $(VMLINK) /debug /opt:icf /opt:ref /INCREMENTAL:NO /NOLOGO \
	-entry:_DllMainCRTStartup@12 -dll /BASE:0x13300000 -machine:$(CPU) \
	/comment:"Auth component native code. (c) Copyright 2005, 2006 The Apache Software Foundation or its licensors, as applicable." \
	-subsystem:windows -out:$(DLLNAME) -map:$(LIBNAME).map  \
	$(BUILDFILES) $(VIRTFILES) $(MDLLIBFILES) $(SYSLIBFILES)  \
	kernel32.lib  ws2_32.lib advapi32.lib user32.lib gdi32.lib comdlg32.lib winspool.lib  $(LIBPATH)$(LIBNAME).exp


clean:
	-del *.map
	-del *.obj
	-del *.res
	-del *.pdb
	-del ..\lib\$(LIBNAME).lib
	-del ..\lib\$(LIBNAME).exp
	-del ..\$(LIBNAME).pdb
	-del $(DLLNAME)
