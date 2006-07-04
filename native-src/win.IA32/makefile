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
# Main Makefile
#

!include <ntwin32.mak>

all: \
	_sig _common _pool _thread _port _fdlibm

_pool: 
	cd pool
	$(MAKE) /NOLOGO
	cd ..

_thread: _pool _common
	cd thread
	$(MAKE) /NOLOGO
	cd ..

_port: _thread _sig _common
	cd port
	$(MAKE) /NOLOGO
	cd ..

_sig:
	cd sig
	$(MAKE) /NOLOGO
	cd ..

_fdlibm: 
	cd fdlibm
	$(MAKE) /NOLOGO
	cd ..

_common: 
	cd common
	$(MAKE) /NOLOGO
	cd ..

clean:
	cd fdlibm
	$(MAKE) /NOLOGO clean
	cd ..
	cd sig
	$(MAKE) /NOLOGO clean
	cd ..
	cd pool
	$(MAKE) /NOLOGO clean
	cd ..
	cd port
	$(MAKE) /NOLOGO clean
	cd ..
	cd thread
	$(MAKE) /NOLOGO clean
	cd ..
	cd common
	$(MAKE) /NOLOGO clean
	cd ..

