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
INCLUDES += -I$(SHARED)common -I/usr/X11R6/include -I/usr/include/freetype2 \
            -Iinclude # TOFIX move includes
ifeq ($(HY_OS),freebsd)
INCLUDES += -I/usr/local/include -I/usr/local/include/freetype2
endif

BUILDFILES = LinuxNativeFont.o

MDLLIBFILES = \
	$(DLLPATH)libhysig.so $(LIBPATH)libhyzip.a $(DLLPATH)libhyzlib.so $(LIBPATH)libhycommon.a \
	$(LIBPATH)libhypool.a $(LIBPATH)libhyfdlibm.a $(DLLPATH)libhythr.so $(LIBPATH)libvmi.so

OSLIBS += $(XLIBS)

DLLNAME=../liblinuxfont.so

include $(HY_HDK)/build/make/rules.mk
