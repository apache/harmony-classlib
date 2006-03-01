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

CFLAGS += -fpic

BUILDFILES = \
	$(SHAREDSUB)thread_copyright.o thrhelp.o thrspinlock.o hythread.o \
	$(SHAREDSUB)hythreadinspect.o linuxonexit.o priority.o rasthrsup.o \
	$(SHAREDSUB)rwmutex.o thrcreate.o thrdsup.o thrprof.o

SYSLIBFILES = -lpthread
MDLLIBFILES = ../lib/libhypool.a ../lib/libhycommon.a
DLLNAME = ../libhythr.so

include ../rules.mk
