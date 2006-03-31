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

all: \
	_sig _common _pool _thread _port _fdlibm _zip \
	_zlib _vmi _luni _nio _archive _auth \
	_math _text _vmls _launcher

_auth:
	(cd auth && $(MAKE) )

_pool: 
	(cd pool && $(MAKE) )

_common:
	(cd common && $(MAKE) )

_thread: _pool _common
	(cd thread && $(MAKE) )

_port: _thread _sig
	(cd port && $(MAKE) )

_sig:
	(cd sig && $(MAKE) )

_fdlibm: 
	(cd fdlibm && $(MAKE) )

_zip: 
	(cd zip && $(MAKE) )

_zlib: 
	(cd zlib && $(MAKE) )

_vmi: _zip _pool
	(cd vmi && $(MAKE) )

_luni: _common _sig _zip _zlib _pool _fdlibm _thread _vmi
	(cd luni && $(MAKE) )
	
_nio: _sig _zip _zlib _pool _fdlibm _thread _vmi
	(cd nio && $(MAKE) )
	
_archive: _common _sig _zip _zlib _pool _fdlibm _thread _vmi
	(cd archive && $(MAKE) )
	
_math: _common _sig _zip _zlib _pool _fdlibm _thread _vmi
	(cd math && $(MAKE) )
	
_text: _sig _zip _zlib _pool _fdlibm _thread _vmi
	(cd text && $(MAKE) )

_vmls: _thread _port
	(cd vmls && $(MAKE) )	

_launcher: _thread _port
	(cd launcher && $(MAKE) )	

clean:
	(cd auth && $(MAKE) clean )
	(cd sig && $(MAKE) clean )
	(cd pool  && $(MAKE) clean )
	(cd thread && $(MAKE) clean )
	(cd port && $(MAKE) clean )
	(cd fdlibm && $(MAKE) clean )
	(cd zip && $(MAKE) clean )
	(cd zlib && $(MAKE) clean )
	(cd vmi && $(MAKE) clean )
	(cd common && $(MAKE) clean )
	(cd luni && $(MAKE) clean )
	(cd nio && $(MAKE) clean )
	(cd archive && $(MAKE) clean )
	(cd math && $(MAKE) clean )
	(cd text && $(MAKE) clean )
	(cd vmls && $(MAKE) clean )
	(cd launcher && $(MAKE) clean )
