# Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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
# Makefile for module 'vmi'
#

!include <..\defines.mak>

SHAREDSUB=$(SHARED)vmi\# comment to avoid \ being treated as continuation
LIBBASE=vmi
LIBNAME=$(LIBPATH)$(LIBBASE).lib
HYCFLAGS = $(HYCFLAGS) -DHYPORT_LIBRARY_DEFINE /I..\..\..\modules\luni\src\main\native\include\shared /I..\..\..\modules\luni\src\main\native\include\windows
HYLDFLAGS = $(HYLDFLAGS) -def:$(LIBBASE).def

BUILDFILES = $(SHAREDSUB)vmi_copyright.obj $(SHAREDSUB)vmi.obj

VIRTFILES = vmi.res

!include <..\rules.mak>
