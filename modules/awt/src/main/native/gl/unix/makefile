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

PNG_DIR=$(HY_HDK)/../depends/libs/build/png

CFLAGS += -fpic
INCLUDES += -I$(SHARED)common -I$(SHAREDSUB)/include -I$(PNG_DIR)

BUILDFILES = \
  $(SHAREDSUB)/blitter.o \
  $(SHAREDSUB)/gifdecoder.o \
  $(SHAREDSUB)/LUTTables.o \
  $(SHAREDSUB)/pngdecoder.o \
  $(SHAREDSUB)/SurfaceDataStructure.o \
  libpng.a

MDLLIBFILES += \
	$(LIBPATH)libhyzip.a $(DLLPATH)libhyzlib$(HY_SHLIB_SUFFIX) \
	$(LIBPATH)libhypool.a $(LIBPATH)libhyfdlibm.a $(LIBPATH)libvmi$(HY_SHLIB_SUFFIX)

OSLIBS += $(STDCLIBS)

DLLNAME=../libgl$(HY_SHLIB_SUFFIX)
EXPNAME=HYGL_0.1

include $(HY_HDK)/build/make/rules.mk

libpng.a: $(PNG_DIR)/libpng.$(HY_PLATFORM)
	cp $< $@
