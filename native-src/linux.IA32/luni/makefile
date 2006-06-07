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

include ../makefile.include

CFLAGS += -fpic -I$(SHARED)common -I$(SHARED)fdlibm \
	-I../../../modules/luni/src/main/native/include/shared -I../../../modules/luni/src/main/native/include/linux

BUILDFILES = \
	$(SHAREDSUB)luni_copyright.o $(SHAREDSUB)file.o procimpl.o \
	$(SHAREDSUB)oos.o $(SHAREDSUB)nethelp.o \
	$(SHAREDSUB)floatbits.o $(SHAREDSUB)ois.o \
	helpers.o $(SHAREDSUB)math.o $(SHAREDSUB)luniglob.o \
	$(SHAREDSUB)proxy.o \
	$(SHAREDSUB)cbigint.o $(SHAREDSUB)dblparse.o $(SHAREDSUB)fltparse.o \
	$(SHAREDSUB)netif.o $(SHAREDSUB)process.o $(SHAREDSUB)bigint.o \
	$(SHAREDSUB)osc.o $(SHAREDSUB)socket.o $(SHAREDSUB)inetadds.o \
	$(SHAREDSUB)filedesc.o $(SHAREDSUB)timezone.o \
	$(SHAREDSUB)OSFileSystem.o OSFileSystemLinux32.o \
	OSMemory.o OSMemoryLinux32.o $(SHAREDSUB)OSNetworkSystem.o \
	OSNetworkSystemLinux.o

MDLLIBFILES = \
	../lib/libhycommon.a ../libhysig.so ../lib/libhyzip.a ../libhyzlib.so \
	../lib/libhypool.a ../lib/libhyfdlibm.a ../libhythr.so ../libvmi.so

DLLNAME = ../libhyluni.so

include ../rules.mk
