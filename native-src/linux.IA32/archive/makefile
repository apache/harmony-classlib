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
# Makefile for module 'archive'
#

include ../makefile.include

DLLFILENAME=libhyarchive.so# declaration

DLLNAME=../libhyarchive.so# declaration

LIBNAME=hyarchive# declaration

LIBPATH=../lib/# declaration

CFLAGS= -fpic  -DLINUX -D_REENTRANT -O1 -march=pentium3 -DIPv6_FUNCTION_SUPPORT  -DHYX86 -I../include -I../archive -I../common -I../zlib -I../zip -I../fdlibm  $(VMDEBUG)

BUILDFILES = \
	archive_copyright.o jclcrc32.o zip.o adler32.o inflater.o \
	jarfile.o deflater.o archiveglob.o

MDLLIBFILES = \
	../libhysig.so ../lib/libhyzip.a ../libhyzlib.so ../lib/libhycommon.a \
	../lib/libhypool.a ../lib/libhyfdlibm.a ../libhythr.so ../libvmi.so

all: $(DLLNAME)

$(DLLNAME): $(BUILDFILES) $(MDLLIBFILES) 
	$(DLL_LD) -shared -Wl,--version-script,$(LIBNAME).exp \
	-Wl,-soname=$(DLLFILENAME) $(VMLINK) -L.  -L../lib -L.. -o $(DLLNAME) \
	$(BUILDFILES) -Xlinker --start-group \
	-lhysig \
	-lhyzip \
	-lhyzlib \
	-lhycommon \
	-lhypool \
	-lhyfdlibm \
	-lhythr \
	-lvmi -Xlinker --end-group  -lc -lm -ldl

clean:
	-rm -f *.o
	-rm -f $(DLLNAME)
