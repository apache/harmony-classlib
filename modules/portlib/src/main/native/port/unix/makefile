#  Licensed to the Apache Software Foundation (ASF) under one or more
#  contributor license agreements.  See the NOTICE file distributed with
#  this work for additional information regarding copyright ownership.
#  The ASF licenses this file to You under the Apache License, Version 2.0
#  (the "License"); you may not use this file except in compliance with
#  the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

#
# Makefile for module 'port'
#

include $(HY_HDK)/build/make/defines.mk

INCLUDES += -DHYPORT_LIBRARY_DEFINE -I$(HY_PLATFORM)
CFLAGS += -fpic

BUILDFILES = \
	$(SHAREDSUB)port_copyright.o hycpu.o $(SHAREDSUB)hyerror.o \
	hyerrorhelpers.o $(SHAREDSUB)hyexit.o hyfile.o hyfiletext.o \
	$(SHAREDSUB)hygp.o hyipcmutex.o hymem.o \
	hymmap.o $(SHAREDSUB)hynls.o hynlshelpers.o hyosdump.o \
	$(SHAREDSUB)hyport.o $(SHAREDSUB)hyportcontrol.o hyportptb.o \
	hysharedhelper.o hyshmem.o hyshsem.o hysl.o hysock.o \
	$(SHAREDSUB)hystr.o $(SHAREDSUB)hystrftime.o $(SHAREDSUB)hystsl.o \
	hysysinfo.o hytime.o $(SHAREDSUB)hytlshelpers.o hytty.o hyvmem.o

ifeq ($(HY_NO_SIG),false)
BUILDFILES += hysignal.o $(HY_PLATFORM)/hysignal_context.o
endif

DLLNAME = ../libhyprt.so
EXPNAME = HYPRT_0.1

# temp by geir - please ignore
#
LIBNAME = $(LIBPATH)libhyprt.a


include $(HY_HDK)/build/make/rules.mk
