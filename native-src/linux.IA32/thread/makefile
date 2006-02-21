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
# Makefile for module 'thread'
#

include ../makefile.include

DLLFILENAME=libhythr.so# declaration

DLLNAME=../libhythr.so# declaration

LIBNAME=hythr# declaration

LIBPATH=../lib/# declaration

CFLAGS= -fpic  -DLINUX -D_REENTRANT -O1 -march=pentium3 -DIPv6_FUNCTION_SUPPORT  -DHYX86 -I../include  $(VMDEBUG)

BUILDFILES = \
	thread_copyright.o thrhelp.o thrspinlock.o hythread.o \
	hythreadinspect.o linuxonexit.o priority.o rasthrsup.o rwmutex.o \
	thrcreate.o thrdsup.o thrprof.o

SYSLIBFILES = -lpthread

MDLLIBFILES = ../lib/libhypool.a ../lib/libhycommon.a

all: $(DLLNAME)

$(DLLNAME): $(BUILDFILES) $(MDLLIBFILES) 
	$(DLL_LD) -shared -Wl,--version-script,$(LIBNAME).exp \
	-Wl,-soname=$(DLLFILENAME) $(VMLINK) -L.  -L../lib -o $(DLLNAME) \
	$(BUILDFILES) $(SYSLIBFILES) -Xlinker --start-group \
	-lhypool \
	-lhycommon -Xlinker --end-group  -lc -lm -ldl

clean:
	-rm -f *.o
	-rm -f ../libhythr.so
