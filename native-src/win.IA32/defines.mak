APPVER=4.0
TARGETOS=WIN95
_WIN32_IE=0x0500
SEHMAP = TRUE
!include <win32.mak>

LIBPATH=$(HY_HDK)\lib\# comment to avoid \ being treated as continuation
EXEPATH=..\# ditto
DLLPATH=..\# ditto
SHARED=..\..\shared\# ditto

HYCFLAGS = \
  -Ogityb1 -WX -GF -Gs -MD -Zi -Zm400 \
  -D_DLL -D_MT -DWIN32 -D_WIN32_WINNT=0x0400 -D_WINSOCKAPI_ -DWINVER=0x0400 \
  $(VMDEBUG) /I$(HY_HDK)\include /I$(HY_HDK)\jdk\include /I.
