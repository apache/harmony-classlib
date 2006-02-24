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
# Makefile for module 'zlib'
#

include ../makefile.include

DLLFILENAME=libhyzlib.so# declaration

DLLNAME=../libhyzlib.so# declaration

LIBNAME=hyzlib# declaration

LIBPATH=../lib/# declaration

CFLAGS=-fpic -O1 -march=pentium3 -DLINUX -D_REENTRANT -DIPv6_FUNCTION_SUPPORT -DHYX86  -I../include -I. $(VMDEBUG)


BUILDFILES = \
	zlib_copyright.o dist/adler32.o dist/compress.o dist/crc32.o \
	dist/deflate.o dist/infback.o dist/inffast.o dist/inflate.o \
	dist/inftrees.o dist/trees.o dist/uncompr.o dist/zutil.o

all: $(DLLNAME)

$(DLLNAME): $(BUILDFILES)
	$(DLL_LD) -shared -Wl,--version-script,$(LIBNAME).exp \
	-Wl,-soname=$(DLLFILENAME) $(VMLINK) -L.  -o $(DLLNAME) \
	$(BUILDFILES) -Xlinker --start-group -Xlinker --end-group  -lc -lm -ldl

clean:
	-rm -f *.o
	-rm -f $(DLLNAME)
