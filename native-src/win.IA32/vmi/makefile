# Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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
# Makefile for module 'vmi'
#

APPVER=4.0
TARGETOS=WIN95
_WIN32_IE=0x0500
SEHMAP = TRUE
!include <win32.mak>

DLLFILENAME=vmi.dll# declaration

DLLNAME=..\vmi.dll# declaration

LIBNAME=vmi# declaration

LIBPATH=..\lib\# declaration

.c.obj:
	$(cc) -DWINVER=0x0400 -D_WIN32_WINNT=0x0400 $(cflags) -D_MT -D_DLL -MD -D_WINSOCKAPI_ -DWIN32 -Ogityb1 -Gs -GF -Zm400 -WX -Zi  /I..\include  $(VMDEBUG) $*.c

.rc.res:
	rc -I..\include $<

BUILDFILES = vmi_copyright.obj vmi.obj

VIRTFILES = vmi.res

all: ..\lib\$(LIBNAME).lib

$(LIBPATH)$(LIBNAME).lib: $(BUILDFILES) $(VIRTFILES)
	$(implib) /NOLOGO -subsystem:windows -out:$(LIBPATH)$(LIBNAME).lib \
	-def:$(LIBNAME).def -machine:$(CPU) \
	$(BUILDFILES) $(VIRTFILES)

clean:
	-del *.obj
	-del *.pdb
	-del *.res
	-del $(LIBPATH)$(LIBNAME).lib
	-del $(LIBPATH)$(LIBNAME).exp
