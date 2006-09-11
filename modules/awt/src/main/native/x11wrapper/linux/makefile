# Copyright 2006 The Apache Software Foundation or its licensors, as
# applicable
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

include $(HY_HDK)/build/make/makefile.include

CFLAGS += -fpic -I$(SHARED)common -I/usr/X11R6/include

BUILDFILES = \
  org_apache_harmony_awt_nativebridge_linux_ErrorHandler.o \
  org_apache_harmony_awt_nativebridge_linux_GLX.o \
  org_apache_harmony_awt_nativebridge_linux_X11.o \
  org_apache_harmony_awt_nativebridge_linux_Xft.o

MDLLIBFILES = \
	$(DLLPATH)libhysig.so $(LIBPATH)libhyzip.a $(DLLPATH)libhyzlib.so $(LIBPATH)libhycommon.a \
	$(LIBPATH)libhypool.a $(LIBPATH)libhyfdlibm.a $(DLLPATH)libhythr.so $(LIBPATH)libvmi.so

ifeq ($(HY_ARCH),x86_64)
 SYSLIBFILES = -lstdc++ -L/usr/X11R6/lib64 -lX11
endif
ifeq ($(HY_ARCH),x86)
 SYSLIBFILES = -lstdc++ -L/usr/X11R6/lib -lX11
endif

DLLNAME=../libX11Wrapper.so

include $(HY_HDK)/build/make/rules.mk
