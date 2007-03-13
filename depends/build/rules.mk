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
EXPFILE = $(notdir $(basename $(DLLNAME))).exp

all: $(DLLNAME) $(EXENAME) $(LIBNAME)

$(LIBNAME): $(BUILDFILES)
	$(AR) $(ARFLAGS) $@ $(BUILDFILES)

$(EXPFILE): exports.txt
ifeq ($(HY_OS),aix)
	cp $< $@
else
	echo "$(EXPNAME) {" >$@
	echo "  global :" >>$@
	sed -e's/^/    /;s/$$/;/' <$< >>$@
	echo "  local : *;" >>$@
	echo "};" >>$@
endif

$(DLLNAME): $(BUILDFILES) $(MDLLIBFILES) $(EXPFILE)
	$(DLL_LD) -shared -Wl,-soname=$(@F) -Wl,--version-script,$(EXPFILE) \
	$(LDFLAGS) $(VMLINK) -o $@ \
	$(BUILDFILES) \
	$(MDLLIBPREFIX) $(MDLLIBFILES) $(MDLLIBSUFFIX) \
	$(OSLIBS)

$(EXENAME): $(BUILDFILES) $(MDLLIBFILES)
	$(CC) $(VMLINK) $(EXELDFLAGS) \
	$(BUILDFILES) \
	$(MDLLIBPREFIX) $(MDLLIBFILES) $(MDLLIBSUFFIX) \
	-o $@ $(OSLIBS) \
	$(EXERPATHPREFIX) -L$(HY_HDK)/jdk/jre/bin

clean:
	-rm -f $(BUILDFILES) $(DLLNAME) $(EXENAME) $(LIBNAME) $(EXPFILE) \
	       $(CLEANFILES)
