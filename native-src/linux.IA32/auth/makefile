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

CFLAGS += -fpic 
BUILDFILES = $(SHAREDSUB)auth_copyright.o authnix.o
MDLLIBFILES = \
        ../libhysig.so $(LIBPATH)libhyzip.a ../libhyzlib.so $(LIBPATH)libhycommon.a \
        $(LIBPATH)libhypool.a $(LIBPATH)libhyfdlibm.a ../libhythr.so ../libvmi.so

DLLNAME = ../libhyauth.so

include ../rules.mk
