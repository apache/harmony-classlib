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

include $(HY_HDK)/build/make/makefile.include

CFLAGS += -fpic -I$(SHARED)common -I../zlib/dist \
	-I../../../modules/archive/src/main/native/include/shared

BUILDFILES = $(SHAREDSUB)archive_copyright.o $(SHAREDSUB)jclcrc32.o \
	$(SHAREDSUB)zip.o $(SHAREDSUB)adler32.o $(SHAREDSUB)inflater.o \
	$(SHAREDSUB)jarfile.o $(SHAREDSUB)deflater.o $(SHAREDSUB)archiveglob.o

MDLLIBFILES = \
	../libhysig.so $(LIBPATH)libhyzip.a ../libhyzlib.so $(LIBPATH)libhycommon.a \
	$(LIBPATH)libhypool.a $(LIBPATH)libhyfdlibm.a ../libhythr.so ../libvmi.so

DLLNAME=../libhyarchive.so

include $(HY_HDK)/build/make/rules.mk
