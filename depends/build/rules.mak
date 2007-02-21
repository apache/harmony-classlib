# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#  
#      http://www.apache.org/licenses/LICENSE-2.0
#  
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

!IF "$(HY_OS)-$(HY_ARCH)" == "windows-x86_64" 
ml=ml64
# don't quite know what to specify as an entry point on win/em64t
ENTRY_OPTION=
!ELSE
ml=ml
ENTRY_OPTION=-entry:_DllMainCRTStartup@12
!ENDIF

.c.obj:
	$(cc) $(cflags) $(HYCFLAGS) -Fo$*.obj $*.c

.cpp.obj:
	$(cc) $(cflags) $(HYCFLAGS) -Fo$*.obj $*.cpp

.asm.obj:
	$(ml) /Fo$*.obj /c /Cp /W3 /nologo /coff /Zm /Zd /Zi /Gd $(VMASMDEBUG) -DWIN32 $<

.rc.res:
	rc -I..\include $<

all: $(DLLNAME) $(EXENAME) $(LIBNAME)

!ifdef LIBNAME
$(LIBNAME): $(BUILDFILES) $(VIRTFILES) $(MDLLIBFILES)
	$(implib) /NOLOGO -subsystem:windows -out:$(LIBNAME) \
	$(HYLDFLAGS) -machine:$(CPU) \
	$(BUILDFILES) $(VIRTFILES) $(MDLLIBFILES)
!endif

!ifdef DLLNAME
$(DLLNAME): $(LIBNAME)
	link $(VMLINK) /debug /opt:icf /opt:ref /INCREMENTAL:NO /NOLOGO \
	$(ENTRY_OPTION) -dll /BASE:$(DLLBASE) -machine:$(CPU) \
        $(COMMENT) \
	-subsystem:windows -out:$@ -map:$*.map \
	$(BUILDFILES) $(VIRTFILES) $(MDLLIBFILES) $(SYSLIBFILES) \
	kernel32.lib  ws2_32.lib advapi32.lib user32.lib gdi32.lib \
        comdlg32.lib winspool.lib  $(LIBPATH)$(*F).exp
!endif

!ifdef EXENAME
$(EXENAME): $(BUILDFILES) $(VIRTFILES) $(MDLLIBFILES)
	link /NOLOGO $(EXEFLAGS) /debug /opt:icf /opt:ref $(VMLINK) \
	-out:$(EXENAME) -machine:$(CPU) setargv.obj  \
	$(BUILDFILES) $(VIRTFILES) $(MDLLIBFILES) $(EXEDLLFILES) 
!endif

clean:
    -del $(BUILDFILES) *.res *.pdb \
             $(LIBNAME) $(LIBNAME:.lib=.exp) \
             $(DLLNAME) $(DLLNAME:.dll=.pdb) $(DLLNAME:.dll=.map) \
             $(EXENAME) $(EXENAME:.exe=.pdb) \
             $(CLEANFILES)
#             $(CLEANFILES) >nul 2>&1
