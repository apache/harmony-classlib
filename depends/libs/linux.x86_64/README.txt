64-bit linux builds of ICU libraries.

Note these are available as source under the X license
from 

  http://icu.sourceforge.net/

These binaries came from HARMONY-1676 and build via : 

Build from:
  ftp://ftp.software.ibm.com/software/globalization/icu/icu4j/icu4jni/3.4/icu4jni_3_4_patch-01.zip
  ftp://ftp.software.ibm.com/software/globalization/icu/icu4j/icu4jni/3.4/icu4jni_3_4.zip
  
Build instructions:

unzip ~/work/icu4jni_3_4.zip
unzip ~/work/icu4jni_3_4_patch-01.zip
cd icu4jni
dos2unix ../patch/icu4jni-3401.patch
dos2unix src/native/converter/ConverterInterface.c
dos2unix src/native/normalizer/NormalizationInterface.c
patch -p0 <../patch/icu4jni-3401.patch
dos2unix configure
export PATH=/usr/local/bin:$PATH
dos2unix config.sub
dos2unix config.guess
sh configure
make
cd build/lib
tar -c libICUInterface34d.so | bzip2 -9 >libICUInterface34d.tar.bz2 


