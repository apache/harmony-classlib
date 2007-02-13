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

#
# Makefile for module 'text'
#

include $(HY_HDK)/build/make/defines.mk

CFLAGS += -fpic
INCLUDES += -I$(HY_HDK)/include/icu4c

BUILDFILES = $(SHAREDSUB)text_copyright.o $(SHAREDSUB)BidiWrapper.o

MDLLIBFILES += \
	$(DLLPATH)libicuuc.so.34 \
	$(LIBPATH)libhyzip.a $(DLLPATH)libhyzlib.so \
	$(LIBPATH)libhypool.a $(LIBPATH)libhyfdlibm.a $(LIBPATH)libvmi.so

DLLNAME = ../libhytext.so
EXPNAME = HYTEXT_0.1

include $(HY_HDK)/build/make/rules.mk
