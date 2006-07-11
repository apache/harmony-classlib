#!/bin/sh

for d in depends make modules ;do
  if [ \! -d $d ]; then
    echo no $d directory
    echo this script should be run from classlib top-level directory
    echo
    echo sh depends/libs/build/fetch-awt-swing.sh
    exit 1
  fi
done

LCMS_HOME=/usr
mkdir -p depends/libs/build/lcms
while read s t 
do
  src=$LCMS_HOME/$s
  tgt=depends/libs/build/lcms/$t
  if [ \! -e $src ]; then
    echo $src not found
    echo liblcms development package not install
    echo for debian/ubuntu try: apt-get install liblcms1-dev
    exit 1
  fi
  if [ -e $tgt ]; then
    echo $tgt exists
  else
    echo Linking $src to $tgt
    ln -s $src $tgt
  fi
done <<EOF
  lib/liblcms.a liblcms.ia32
  include/icc34.h icc34.h
  include/lcms.h lcms.h
EOF

PNG_HOME=/usr
mkdir -p depends/libs/build/png
while read s t 
do
  src=$PNG_HOME/$s
  tgt=depends/libs/build/png/$t
  if [ \! -e $src ]; then
    echo $src not found
    echo libpng development package not install
    echo for debian/ubuntu try: apt-get install libpng12-dev
    exit 1
  fi
  if [ -e $tgt ]; then
    echo $tgt exists
  else
    echo Linking $src to $tgt
    ln -s $src $tgt
  fi
done <<EOF
  lib/libpng.a libpng.ia32
  include/pngconf.h pngconf.h
  include/png.h png.h
EOF

JPEG_HOME=/usr
mkdir -p depends/libs/build/jpeg
while read s t 
do
  src=$JPEG_HOME/$s
  tgt=depends/libs/build/jpeg/$t
  if [ \! -e $src ]; then
    echo $src not found
    echo libjpeg development package not install
    echo for debian/ubuntu try: apt-get install libjpeg62-dev
    exit 1
  fi
  if [ -e $tgt ]; then
    echo $tgt exists
  else
    echo Linking $src to $tgt
    ln -s $src $tgt
  fi
done <<EOF
  lib/libjpeg.a libjpeg.ia32
  include/jconfig.h jconfig.lnx
  include/jpeglib.h jpeglib.h
  include/jmorecfg.h jmorecfg.h
  include/jerror.h jerror.h
  include/jpegint.h jpegint.h
EOF

exit
