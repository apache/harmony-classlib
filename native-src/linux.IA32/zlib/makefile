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

include $(HY_HDK)/build/make/makefile.include

ARCHIVE_NATIVE=../../../modules/archive/src/main/native/# temporary addition until zlib moves into archive module

CFLAGS += -fpic -I$(ARCHIVE_NATIVE)include/shared

BUILDFILES = \
	$(SHAREDSUB)zlib_copyright.o $(ARCHIVE_NATIVE)zlib_dist/adler32.o $(ARCHIVE_NATIVE)zlib_dist/compress.o \
	$(ARCHIVE_NATIVE)zlib_dist/crc32.o $(ARCHIVE_NATIVE)zlib_dist/deflate.o $(ARCHIVE_NATIVE)zlib_dist/infback.o $(ARCHIVE_NATIVE)zlib_dist/inffast.o \
	$(ARCHIVE_NATIVE)zlib_dist/inflate.o $(ARCHIVE_NATIVE)zlib_dist/inftrees.o $(ARCHIVE_NATIVE)zlib_dist/trees.o $(ARCHIVE_NATIVE)zlib_dist/uncompr.o $(ARCHIVE_NATIVE)zlib_dist/zutil.o

DLLNAME = ../libhyzlib.so

include $(HY_HDK)/build/make/rules.mk
