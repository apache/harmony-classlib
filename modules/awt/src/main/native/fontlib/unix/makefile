# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
# 
#     http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

include $(HY_HDK)/build/make/defines.mk

CFLAGS += -fpic
INCLUDES += -I$(SHARED)common -I$(SHAREDSUB)include

BUILDFILES = \
  $(SHAREDSUB)fljni.o \
  $(SHAREDSUB)EncodedValue.o \
  $(SHAREDSUB)Environment.o \
  $(SHAREDSUB)Font.o \
  $(SHAREDSUB)Glyph.o \
  $(SHAREDSUB)Outline.o \
  $(SHAREDSUB)ParsingTables.o \
  $(SHAREDSUB)T1Font.o \
  $(SHAREDSUB)T1Glyph.o \
  $(SHAREDSUB)TTCurve.o \
  $(SHAREDSUB)TTFont.o \
  $(SHAREDSUB)TypeDefinition.o

ifneq ($(HY_ZIP_API),true)
MDLLIBFILES += $(LIBPATH)libhyzip.a $(DLLPATH)libhyzlib$(HY_LINKLIB_SUFFIX)
endif

MDLLIBFILES += \
	$(LIBPATH)libhypool.a $(LIBPATH)libhyfdlibm.a $(LIBPATH)libvmi.so

OSLIBS += $(STDCLIBS)

DLLNAME=../libFL.so
EXPNAME=HYFONTLIB_0.1

include $(HY_HDK)/build/make/rules.mk
