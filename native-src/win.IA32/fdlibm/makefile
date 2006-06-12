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
# Makefile for module 'fdlibm'
#

!include <..\defines.mak>

SHAREDSUB=$(SHARED)fdlibm\# comment to avoid \ being treated as continuation
LIBNAME=$(LIBPATH)hyfdlibm.lib

BUILDFILES = \
  dist/e_acos.obj dist/e_acosh.obj dist/e_asin.obj dist/e_atan2.obj \
  dist/e_atanh.obj dist/e_cosh.obj dist/e_exp.obj dist/e_fmod.obj \
  dist/e_gamma.obj dist/e_gamma_r.obj dist/e_hypot.obj dist/e_j0.obj \
  dist/e_j1.obj dist/e_jn.obj dist/e_lgamma.obj dist/e_lgamma_r.obj \
  dist/e_log.obj dist/e_log10.obj dist/e_pow.obj dist/e_rem_pio2.obj \
  dist/e_remainder.obj dist/e_scalb.obj dist/e_sinh.obj dist/e_sqrt.obj \
  dist/k_cos.obj dist/k_rem_pio2.obj dist/k_sin.obj dist/k_standard.obj \
  dist/k_tan.obj dist/s_asinh.obj dist/s_atan.obj dist/s_cbrt.obj \
  dist/s_ceil.obj dist/s_copysign.obj dist/s_cos.obj dist/s_erf.obj \
  dist/s_expm1.obj dist/s_fabs.obj dist/s_finite.obj dist/s_floor.obj \
  dist/s_frexp.obj dist/s_ilogb.obj dist/s_isnan.obj dist/s_ldexp.obj \
  $(SHAREDSUB)s_lib_version.obj \
  dist/s_log1p.obj dist/s_logb.obj dist/s_matherr.obj \
  dist/s_modf.obj dist/s_nextafter.obj dist/s_rint.obj dist/s_scalbn.obj \
  dist/s_signgam.obj dist/s_significand.obj dist/s_sin.obj dist/s_tan.obj \
  dist/s_tanh.obj dist/w_acos.obj dist/w_acosh.obj dist/w_asin.obj \
  dist/w_atan2.obj dist/w_atanh.obj dist/w_cosh.obj dist/w_exp.obj \
  dist/w_fmod.obj dist/w_gamma.obj dist/w_gamma_r.obj dist/w_hypot.obj \
  dist/w_j0.obj dist/w_j1.obj dist/w_jn.obj dist/w_lgamma.obj \
  dist/w_lgamma_r.obj dist/w_log.obj dist/w_log10.obj dist/w_pow.obj \
  dist/w_remainder.obj dist/w_scalb.obj dist/w_sinh.obj dist/w_sqrt.obj

HYCFLAGS = -Oityb1 -W3 -Fd$(LIBPATH)hyfdlibm.pdb -D_IEEE_LIBM \
	 -DHY_PLATFORM_DOUBLE_ORDER /I$(SHAREDSUB) /I..\..\..\modules\luni\src\main\native\include\shared /I..\..\..\modules\luni\src\main\native\include\windows

.c.obj:
        $(cc) $(cflags) $(HYCFLAGS) -Fo$*.obj $*.c

$(LIBPATH)$(LIBNAME): $(BUILDFILES)
	@echo /NOLOGO -out:$(LIBNAME) >templrf
	@echo $(BUILDFILES) >>templrf
	$(implib) @templrf
	@del templrf

clean:
	-del *.obj dist\*.obj
	-del $(LIBNAME)
	-del $(LIBPATH)hyfdlibm.pdb
