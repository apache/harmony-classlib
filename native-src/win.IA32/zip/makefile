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
# Makefile for module 'zip'
#

!include <$(HY_HDK)\build\make\defines.mak>

SHAREDSUB=$(SHARED)zip\# comment to avoid \ being treated as continuation
LIBBASE=hyzip
LIBNAME=$(LIBPATH)$(LIBBASE).lib
BUILDFILES = $(SHAREDSUB)zcpool.obj $(SHAREDSUB)zipalloc.obj \
	$(SHAREDSUB)zipcache.obj $(SHAREDSUB)zipsup.obj
HYCFLAGS = $(HYCFLAGS) /I..\zlib\dist /I..\..\..\modules\archive\src\main\native\include\shared

!include <$(HY_HDK)\build\make\rules.mak>
