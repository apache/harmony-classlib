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

include ../makefile.include

BUILDFILES = \
	dist/e_acos.o dist/e_acosh.o dist/e_asin.o dist/e_atan2.o \
	dist/e_atanh.o dist/e_cosh.o dist/e_exp.o dist/e_fmod.o \
	dist/e_gamma.o dist/e_gamma_r.o dist/e_hypot.o dist/e_j0.o \
	dist/e_j1.o dist/e_jn.o dist/e_lgamma.o dist/e_lgamma_r.o \
	dist/e_log.o dist/e_log10.o dist/e_pow.o dist/e_rem_pio2.o \
	dist/e_remainder.o dist/e_scalb.o dist/e_sinh.o dist/e_sqrt.o \
	dist/k_cos.o dist/k_rem_pio2.o dist/k_sin.o dist/k_standard.o \
	dist/k_tan.o dist/s_asinh.o dist/s_atan.o dist/s_cbrt.o dist/s_ceil.o \
	dist/s_copysign.o dist/s_cos.o dist/s_erf.o dist/s_expm1.o \
	dist/s_fabs.o dist/s_finite.o dist/s_floor.o dist/s_frexp.o \
	dist/s_ilogb.o dist/s_isnan.o dist/s_ldexp.o \
	$(SHAREDSUB)s_lib_version.o \
	dist/s_log1p.o dist/s_logb.o dist/s_matherr.o dist/s_modf.o \
	dist/s_nextafter.o dist/s_rint.o dist/s_scalbn.o dist/s_signgam.o \
	dist/s_significand.o dist/s_sin.o dist/s_tan.o dist/s_tanh.o \
	dist/w_acos.o dist/w_acosh.o dist/w_asin.o dist/w_atan2.o \
	dist/w_atanh.o dist/w_cosh.o dist/w_exp.o dist/w_fmod.o \
	dist/w_gamma.o dist/w_gamma_r.o dist/w_hypot.o dist/w_j0.o \
	dist/w_j1.o dist/w_jn.o dist/w_lgamma.o dist/w_lgamma_r.o \
	dist/w_log.o dist/w_log10.o dist/w_pow.o dist/w_remainder.o \
	dist/w_scalb.o dist/w_sinh.o dist/w_sqrt.o

# CFLAGS are overriden not just appended to
CFLAGS= -O0 -DLINUX -D_REENTRANT -DIPv6_FUNCTION_SUPPORT -DHYX86 -D_IEEE_LIBM \
        -DHY_PLATFORM_DOUBLE_ORDER \
	-I../include -I. -I$(SHARED)include -I$(SHAREDSUB) \
	-ffloat-store $(VMDEBUG)

LIBNAME = $(LIBPATH)libhyfdlibm.a

include ../rules.mk
