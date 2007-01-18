# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#  
#      http://www.apache.org/licenses/LICENSE-2.0
#  
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

#
# Configuration Makefile
#

CFLAGS := $(DEFINES) $(INCLUDES) $(OPT) $(CFLAGS)
CXXFLAGS := $(DEFINES) $(INCLUDES) $(OPT) $(CXXFLAGS)

all: $(DLLNAME) $(EXENAME) $(LIBNAME)

$(LIBNAME): $(BUILDFILES)
	$(AR) rcv $@ $(BUILDFILES)

$(DLLNAME): $(BUILDFILES) $(MDLLIBFILES)
	$(DLL_LD) -shared -Wl,--version-script,$(@F:.so=.exp) $(LDFLAGS) \
	-Wl,-soname=$(@F) $(VMLINK) -o $@ \
	$(BUILDFILES) $(SYSLIBFILES) \
	-Xlinker --start-group $(MDLLIBFILES) -Xlinker --end-group \
	$(OSLIBS)

$(EXENAME): $(BUILDFILES) $(MDLLIBFILES)
	$(CC) $(VMLINK) $(LDFLAGS) \
	$(BUILDFILES) \
	-Xlinker --start-group $(MDLLIBFILES) -Xlinker --end-group \
	-o $@ $(OSLIBS) \
	-Xlinker -z -Xlinker origin \
	-Xlinker -rpath -Xlinker \$$ORIGIN/ \
	-Xlinker -rpath-link -Xlinker $(HY_HDK)/jdk/jre/bin

clean:
	-rm -f $(BUILDFILES) $(DLLNAME) $(EXENAME) $(LIBNAME) $(CLEANFILES)
