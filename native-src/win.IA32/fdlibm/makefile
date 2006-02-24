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

APPVER=4.0
TARGETOS=WIN95
SEHMAP=TRUE
!include <win32.mak>

LIBNAME=hyfdlibm.lib# declaration

LIBPATH=..\lib\# declaration

BUILDFILES1 = dist/e_acos.obj dist/e_acosh.obj dist/e_asin.obj dist/e_atan2.obj dist/e_atanh.obj
BUILDFILES2 = dist/e_cosh.obj dist/e_exp.obj dist/e_fmod.obj dist/e_gamma.obj dist/e_gamma_r.obj
BUILDFILES3 = dist/e_hypot.obj dist/e_j0.obj dist/e_j1.obj dist/e_jn.obj dist/e_lgamma.obj
BUILDFILES4 = dist/e_lgamma_r.obj dist/e_log.obj dist/e_log10.obj dist/e_pow.obj dist/e_rem_pio2.obj
BUILDFILES5 = dist/e_remainder.obj dist/e_scalb.obj dist/e_sinh.obj dist/e_sqrt.obj dist/k_cos.obj
BUILDFILES6 = dist/k_rem_pio2.obj dist/k_sin.obj dist/k_standard.obj dist/k_tan.obj dist/s_asinh.obj
BUILDFILES7 = dist/s_atan.obj dist/s_cbrt.obj dist/s_ceil.obj dist/s_copysign.obj dist/s_cos.obj
BUILDFILES8 = dist/s_erf.obj dist/s_expm1.obj dist/s_fabs.obj dist/s_finite.obj dist/s_floor.obj
BUILDFILES9 = dist/s_frexp.obj dist/s_ilogb.obj dist/s_isnan.obj dist/s_ldexp.obj s_lib_version.obj
BUILDFILES10 = dist/s_log1p.obj dist/s_logb.obj dist/s_matherr.obj dist/s_modf.obj dist/s_nextafter.obj
BUILDFILES11 = dist/s_rint.obj dist/s_scalbn.obj dist/s_signgam.obj dist/s_significand.obj dist/s_sin.obj
BUILDFILES12 = dist/s_tan.obj dist/s_tanh.obj dist/w_acos.obj dist/w_acosh.obj dist/w_asin.obj
BUILDFILES13 = dist/w_atan2.obj dist/w_atanh.obj dist/w_cosh.obj dist/w_exp.obj dist/w_fmod.obj
BUILDFILES14 = dist/w_gamma.obj dist/w_gamma_r.obj dist/w_hypot.obj dist/w_j0.obj dist/w_j1.obj
BUILDFILES15 = dist/w_jn.obj dist/w_lgamma.obj dist/w_lgamma_r.obj dist/w_log.obj dist/w_log10.obj
BUILDFILES16 = dist/w_pow.obj dist/w_remainder.obj dist/w_scalb.obj dist/w_sinh.obj dist/w_sqrt.obj

.c.obj:
	$(cc) -DWINVER=0x0400 -D_WIN32_WINNT=0x0400 $(cflags) -D_MT -D_DLL -MD -D_WINSOCKAPI_ -DWIN32 -Oityb1 -Gs -GF -Zm400 -W3 -Zi -Fd$(LIBPATH)hyfdlibm.pdb  -D_IEEE_LIBM -DHY_PLATFORM_DOUBLE_ORDER /I..\include /I. $(VMDEBUG) -Fo$*.obj $*.c

.cpp.obj:
	$(cc) -DWINVER=0x0400 -D_WIN32_WINNT=0x0400 $(cflags) -D_MT -D_DLL -MD -D_WINSOCKAPI_ -DWIN32 -Oityb1 -Gs -GF -Zm400 -W3 -Zi -Fd$(LIBPATH)hyfdlibm.pdb  -D_IEEE_LIBM -DHY_PLATFORM_DOUBLE_ORDER /I..\include $(VMDEBUG) $*.cpp

.asm.obj:
	ml /c /Cp /W3 /nologo /coff /Zm /Zd /Zi /Gd $(VMASMDEBUG) -DWIN32   $<

all: $(LIBPATH)$(LIBNAME)

$(LIBPATH)$(LIBNAME):\
	$(BUILDFILES1) $(BUILDFILES2) $(BUILDFILES3) $(BUILDFILES4) $(BUILDFILES5) \
	$(BUILDFILES6) $(BUILDFILES7) $(BUILDFILES8) $(BUILDFILES9) $(BUILDFILES10) \
	$(BUILDFILES11) $(BUILDFILES12) $(BUILDFILES13) $(BUILDFILES14) $(BUILDFILES15) \
	$(BUILDFILES16) 
	@echo /NOLOGO -out:$(LIBPATH)$(LIBNAME) >templrf
	@echo $(BUILDFILES1) >>templrf
	@echo $(BUILDFILES2) >>templrf
	@echo $(BUILDFILES3) >>templrf
	@echo $(BUILDFILES4) >>templrf
	@echo $(BUILDFILES5) >>templrf
	@echo $(BUILDFILES6) >>templrf
	@echo $(BUILDFILES7) >>templrf
	@echo $(BUILDFILES8) >>templrf
	@echo $(BUILDFILES9) >>templrf
	@echo $(BUILDFILES10) >>templrf
	@echo $(BUILDFILES11) >>templrf
	@echo $(BUILDFILES12) >>templrf
	@echo $(BUILDFILES13) >>templrf
	@echo $(BUILDFILES14) >>templrf
	@echo $(BUILDFILES15) >>templrf
	@echo $(BUILDFILES16) >>templrf
	$(implib) @templrf
	@del templrf

clean:
	-del *.obj
	-del $(LIBPATH)$(LIBNAME)
	-del $(LIBPATH)hyfdlibm.pdb
