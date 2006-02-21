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
# Makefile for module 'vmi stubs'
#

include ../makefile.include

DLLFILENAME=libvmi.so

DLLNAME=../libvmi.so

LIBNAME=vmi

LIBPATH=../lib/

CFLAGS= -fpic  -DLINUX -D_REENTRANT -O1 -march=pentium3 -DIPv6_FUNCTION_SUPPORT  -DHYX86 -I../include $(VMDEBUG)



BUILDFILES1 = vmi_copyright.o vmi.o

MDLLIBFILES1 = ../lib/libhyzip.a ../lib/libhypool.a

all: \
	 $(DLLNAME)

$(DLLNAME):\
	$(BUILDFILES1) $(MDLLIBFILES1) 
	 $(DLL_LD)  -shared  -Wl,-Map=$(LIBNAME).map  -Wl,--version-script,$(LIBNAME).exp -Wl,-soname=$(DLLFILENAME) $(VMLINK) -L.  -L../lib -L.. -o $(DLLNAME) \
	$(BUILDFILES1) -Xlinker --start-group \
	-lhyzip \
	-lhypool -Xlinker --end-group  -lc -lm -ldl

clean:
	-rm -f *.o
	-rm -f $(DLLNAME)
