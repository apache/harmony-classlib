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
# Makefile for module 'port'
#

include $(HY_HDK)/build/makefile.include

CFLAGS += -fpic -DHYPORT_LIBRARY_DEFINE \
	-I../../../modules/luni/src/main/native/include/shared -I../../../modules/luni/src/main/native/include/linux

BUILDFILES = \
	$(SHAREDSUB)port_copyright.o hycpu.o $(SHAREDSUB)hyerror.o \
	hyerrorhelpers.o $(SHAREDSUB)hyexit.o hyfile.o hyfiletext.o \
	$(SHAREDSUB)hygp.o hyipcmutex.o hymem.o \
	hymmap.o $(SHAREDSUB)hynls.o hynlshelpers.o hyosdump.o \
	$(SHAREDSUB)hyport.o $(SHAREDSUB)hyportcontrol.o hyportptb.o \
	hysharedhelper.o hyshmem.o hyshsem.o hysignal.o hysl.o hysock.o \
	$(SHAREDSUB)hystr.o $(SHAREDSUB)hystrftime.o $(SHAREDSUB)hystsl.o \
	hysysinfo.o hytime.o $(SHAREDSUB)hytlshelpers.o hytty.o hyvmem.o

MDLLIBFILES = ../libhythr.so $(LIBPATH)libhycommon.a ../libhysig.so

DLLNAME = ../libhyprt.so

# temp by geir - please ignore
#
LIBNAME = $(LIBPATH)libhyprt.a


include $(HY_HDK)/build/rules.mk
