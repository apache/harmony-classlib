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

include ../makefile.include

DLLFILENAME=libhyauth.so# declaration

DLLNAME=../libhyauth.so# declaration

LIBNAME=hyauth# declaration

LIBPATH=../lib/# declaration

CFLAGS= -fpic -DLINUX -D_REENTRANT -O1 -march=pentium3 -DIPv6_FUNCTION_SUPPORT  -DHYX86 -I../include $(VMDEBUG)

BUILDFILES = \
	auth_copyright.o authnix.o

all: $(DLLNAME)

$(DLLNAME): $(BUILDFILES) $(MDLLIBFILES)
	$(DLL_LD) -shared -Wl,--version-script,$(LIBNAME).exp \
	-Wl,-soname=$(DLLFILENAME) $(VMLINK) -L.  -L../lib -L.. -o $(DLLNAME) \
	$(BUILDFILES) -Xlinker --start-group \
	-Xlinker --end-group  -lc -lm -ldl

clean:
	-rm -f *.o
	-rm -f $(DLLNAME)
