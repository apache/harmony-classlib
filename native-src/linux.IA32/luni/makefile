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

DLLFILENAME=libhyluni.so# declaration

DLLNAME=../libhyluni.so# declaration

LIBNAME=hyluni# declaration

LIBPATH=../lib/# declaration

CFLAGS= -fpic  -DLINUX -D_REENTRANT -O1 -march=pentium3 -DIPv6_FUNCTION_SUPPORT  -DHYX86 -I../include -I../luni -I../common -I../zlib -I../zip -I../fdlibm  $(VMDEBUG)

BUILDFILES = \
	luni_copyright.o file.o procimpl.o oos.o fileis.o \
	simpl.o psimpl2.o nethelp.o floatbits.o rafile.o \
	ois.o helpers.o math.o luniglob.o proxy.o \
	netif.o process.o bigint.o osc.o socket.o \
	inetadds.o pssimpl.o filedesc.o timezone.o pmsimpl.o \
	pdsimpl.o fileos.o

MDLLIBFILES = \
	../lib/libhycommon.a ../libhysig.so ../lib/libhyzip.a ../libhyzlib.so \
	../lib/libhypool.a ../lib/libhyfdlibm.a ../libhythr.so ../libvmi.so

all: $(DLLNAME)

$(DLLNAME): $(BUILDFILES) $(MDLLIBFILES)
	$(DLL_LD) -shared -Wl,--version-script,$(LIBNAME).exp \
	-Wl,-soname=$(DLLFILENAME) $(VMLINK) -L.  -L../lib -L.. -o $(DLLNAME) \
	$(BUILDFILES) -Xlinker --start-group \
	-lhycommon \
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
