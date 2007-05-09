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

JPEG_DIR=$(HY_HDK)/../depends/libs/build/jpeg/

CFLAGS += -fpic
INCLUDES += -I$(SHARED)common -I$(SHAREDSUB)include -I$(JPEG_DIR)

BUILDFILES = \
  $(SHAREDSUB)JpegDecoder.o \
  libjpeg.a

MDLLIBFILES += \
	$(LIBPATH)libhyzip.a $(DLLPATH)libhyzlib$(HY_LINKLIB_SUFFIX) \
	$(LIBPATH)libhypool.a $(LIBPATH)libhyfdlibm.a $(LIBPATH)libvmi$(HY_LINKLIB_SUFFIX)

DLLNAME=../libjpegdecoder$(HY_SHLIB_SUFFIX)
EXPNAME=HYJPEGDECODER_0.1

CLEANFILES=jconfig.h

include $(HY_HDK)/build/make/rules.mk

$(SHAREDSUB)JpegDecoder.o: jconfig.h

jconfig.h: $(JPEG_DIR)jconfig.lnx
	cp $< $@

libjpeg.a: $(JPEG_DIR)libjpeg.$(HY_PLATFORM)
	cp $< $@
