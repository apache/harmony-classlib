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
# Makefile for module 'instrument'
#

!include <$(HY_HDK)\build\make\defines.mak>

LIBBASE=hyinstrument
DLLNAME=..\$(LIBBASE).dll
LIBNAME=$(LIBPATH)$(LIBBASE).lib
HYCFLAGS = $(HYCFLAGS) /I$(SHAREDSUB) /I$(SHARED)common /I$(SHARED)fdlibm
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

BUILDFILES = \
  $(SHAREDSUB)instrument.obj $(SHAREDSUB)inst_agt.obj 
  
VIRTFILES = hyinstrument.res

SYSLIBFILES = ws2_32.lib Iphlpapi.lib

MDLLIBFILES = $(MDLLIBFILES) \
  $(LIBPATH)hyzip.lib $(LIBPATH)hyzlib.lib \
  $(LIBPATH)hypool.lib $(LIBPATH)hyfdlibm.lib $(LIBPATH)vmi.lib
  
DLLBASE=0x13200000
COMMENT=/comment:"instrument component native code. (c) Copyright 2006 The Apache Software Foundation or its licensors, as applicable."

!include <$(HY_HDK)\build\make\rules.mak>
