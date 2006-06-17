INTEL CONTRIBUTION TO APACHE HARMONY
          March 22, 2006
======================================


This archive contains the contribution to the Apache
Harmony project from Intel. The contribution consists
of the following components:

    - AWT
    - SWING
    - MISC

See http://wiki.apache.org/harmony/ClassLibrary for a
definition of components.

1. ARCHIVE CONTENTS
-------------------

The archive contains the source files, the building environment,
and the unit tests' source for testing the provided implementation.

After extracting the archive, the following directories appear under
<EXTRACT_DIR>/Harmony/modules/, where EXTRACT_DIR is the location into which
you extracted this archive:

<EXTRACT_DIR>/Harmony/modules
       |
       +---awt
       |     |
       |     +---make - The AWT module and tests build scripts
       |     +---src  - The AWT module source files
       |     \---test - Unit tests for AWT module
       +---misc
       |     |
       |     +---make - The MISC module build scripts
       |     \---src  - The MISC module source files
       \---swing
             |
             +---make - The SWING module and tests build scripts
             +---src  - The SWING module source files
             \---test - Unit tests for SWING module


2. TOOLS AND LIBRARIES REQUIRED FOR THE BUILD
-----------------------------------------------------------

To build the Java* and C++ sources contained in the src/ directories,
install and configure the following tools and support libraries:

+ Apache Ant 		  - Apache Ant version 1.6.4 or higher
                	    http://ant.apache.org

+ Java* SDK and compiler  - You can use either of the following:
				+ A J2SE* 1.5.0 compatible SDK
				+ Apache Harmony Execution Environment
                       		  http://incubator.apache.org/harmony/
				  with the Eclipse* Java* compiler version 3.1.1
				  http://download.eclipse.org/eclipse/downloads/

+ C/C++ compiler 	 - on Windows*, you can use either of the following:
                    		+ Microsoft* 32-bit C/C++ compiler version 7 or higher
                          	  http://www.microsoft.com/downloads/
             			  and Windows* platform SDK
                          	  http://www.microsoft.com/downloads/
            	 		+ Microsoft* Visual Studio .NET* 2003 or higher.

		  	 - on Linux*, use the GNU project C/C++ compiler version
                 	    3.3.3 or higher
			    http://gcc.gnu.org
                            and the Xlib library version X11R6
                            http://www.x.org/

+ External libraries  	 - The IJG JPEG library version 6b
			   http://www.ijg.org/files/
			 - The Libpng library version 1.2.8
			   http://www.libpng.org/pub/png/libpng.html
				NOTE: For Linux*, download a configurable version,
				for example, libpng-1.2.8-config.tar.gz.
			 - The Little CMS library version 1.14
			   http://www.littlecms.com
				NOTE: Later versions are incompatible.

+ cpptasks               - The cpptasks bundle version 1.0 beta 3 or higher
                           http://ant-contrib.sourceforge.net/
                           http://sourceforge.net/project/showfiles.php?group_id=3617

To build the unit test sources contained in the test/ directories and
run unit tests, additionally install:

+ JUnit      		 - Testing framework version 3.8.1 or higher
                           http://junit.org

To use the Libpng external library, additionally install:

+ ZLib 			 - Zlib version 1.2.3
			   http://www.zlib.net/

3. BUILDING CLASS LIBRARIES AND NATIVE LIBRARIES
------------------------------------------------

+ Build the IJG JPEG, Libpng, and Little CMS libraries by following
  the instructions in section 5 below.

+ Download the cpptasks bundle and place the file cpptasks.jar to the directory
  <EXTRACT_DIR>/Harmony/depends/jars/build.

+ On Windows*, start the Microsoft* Windows* SDK build environment
  or the Visual Studio .NET* 2003 Command Prompt.

+ On both platforms, verify the values for the following environment
  variables:

	- PATH must contain the path to Ant and the C++ compiler.
	- JAVA_HOME must point to J2SE 1.5.0 compatible SDK or the Harmony build.

  NOTE: All paths must be absolute.

+ Build the modules in the following order:
  1. MISC
  2. AWT
  3. SWING

  + Change the working directory to make/ of a specific module.

  + Start the build with Apache Ant by typing 'ant'. Ant runs against
    the default target and compiles all Java* and C++ sources from the source directory.

The build produces a set of .jar file and native libraries. These files are
placed in the following directory tree structure:

  <EXTRACT_DIR>/Harmony/
       |
       \---deploy
             |
             \---jre
                  |
                  +---bin
                  |    |
                  |    +--- vmaccess.dll or libvmaccess.so
                  |    +--- gl.dll or libgl.so
                  |    +--- fontlib.dll or liblinuxfont.so
                  |    +--- lcmm.dll or liblcmm.so
                  |    +--- jpegdecoder.dll or libjpegdecoder.so
                  |    \--- Win32Wrapper.dll or libX11Wrapper.so
                  |
                  |
                  \---lib
                      |
                      \---boot
                             |
                             +--- awt.jar
                             +--- misc.jar
                             \--- swing.jar


4. RUNNING CLASS LIBRARIES WITH AN EXTERNAL VM
----------------------------------------------

To run an application on a third-party JRE using the classes provided with this
contribution, do the following:

+ Add dynamic libraries to the system path or copy the file into the <JRE>/bin directory
+ Prepend the boot class path with awt.jar, misc.jar and swing.jar

5. BUILDING THE EXTERNAL LIBRARIES
----------------------------------
To enable image decoding (JPEG and PNG images) and color management
with the provided donation, build the IJG JPEG, Libpng and the Little CMS libraries.

After performing the instructions below,
you create the following directory tree structure:

<EXTRACT_DIR>/Harmony/depends/build
       |
       \---jpeg
       |    |
       |    +--- cderror.h
       |    +--- jinclude.h
       |    +--- jpeglib.h
       |    +--- cdjpeg.h
       |    +--- jdct.h
       |    +--- jmemsys.h
       |    +--- jversion.h
       |    +--- jchuff.h
       |    +--- jdhuff.h
       |    +--- jmorecfg.h
       |    +--- jerror.h
       |    +--- jpegint.h
       |    +--- jconfig.lnx and/or jconfig.vc
       |    +--- libjpeg.lib and/or libjpeg.ia32 and/or libjpeg.ipf
       \---png
       |    |
       |    +--- png.h
       |    +--- pngconf.h
       |    +--- libpng.lib and/or libpng.ia32 and/or libpng.ipf
       \---lcms
       |    |
       |    +--- icc34.h
       |    +--- lcms.h
       |    +--- lcms114.lib and/or liblcms.ia32 and/or liblcms.ipf
       ...

NOTE: The tree above indicates the files required for this contribution,
      not all the files distributed with each library.

Further in the document, <EXTERNAL_LIBS_DIR> denotes the directory
<EXTRACT_DIR>/Harmony/depends/build.

5.1 Building the IJG IPEG library

    System: Windows* IA-32

    1. Change the directory to the IJG JPEG library source directory; normally, jpeg-6b.
    2. Copy the file jconfig.vc to jconfig.h.
    3. Copy the jconfig.vc file to the <EXTERNAL_LIBS_DIR>/jpeg/ directory.
    4. Start the Microsoft* Windows* SDK build environment or
       the Visual Studio .NET* 2003 Command Prompt.
    5. Build the library by running:

	    For the release version: nmake nodebug=1 /f makefile.vc
	    For the debug version: nmake /f makefile.vc

    6. Copy the file libjpeg.lib to the <EXTERNAL_LIBS_DIR>/jpeg directory.
    7. Copy the required header files to the <EXTERNAL_LIBS_DIR>/jpeg directory.
       For a list of required files, see the tree view above.

    System: Linux* IA-32

    1. Change the directory to the IJG JPEG library source directory; normally, jpeg-6b.
    2. Configure the build by running:

	    For the release version: ./configure CFLAGS=’-O2 -fpic’
	    For the debug version: ./configure CFLAGS=’-g -fpic’

    3. Copy the file jconfig.h created during the previous step to
       the <EXTERNAL_LIBS_DIR>/jpeg/jconfig.lnx directory.
    4. To build the library, run:

	    make

    5. Copy the resulting libjpeg.a file to the file <EXTERNAL_LIBS_DIR>/jpeg/libjpeg.ia32.

    6. Copy the required header files to the <EXTERNAL_LIBS_DIR>/jpeg directory.

5.2 Building the Libpng library

    System: Windows* IA-32

    1. Place the libpng source directory and zlib source directory
       in a convenient location and rename the zlib source directory to zlib.
    2. Change the working directory to the libpng source directory.
    3. Edit the file ./scripts/makefile.vcwin32 by changing the following line:

	    CFLAGS  = -nologo -MD -O2 -W3 -I..\zlib

	    For the release version, type: CFLAGS = -nologo -MT -O2 -W3 -I..\zlib .
	    For the debug version, type: CFLAGS = -nologo -MTd -W3 -I..\zlib .

    4. Start the Microsoft* Windows* SDK build environment
       or the Visual Studio .NET* 2003 Command Prompt.
    5. Subsequently run the following commands:

	    nmake /f scripts\makefile.vcwin32 clean
	    nmake /f scripts\makefile.vcwin32

    6. Copy the files libpng.lib,  png.h and pngconf.h to
       the directory <EXTERNAL_LIBS_DIR>/png.

   System: Linux* IA-32

   1. Change the working directory to the libpng source directory.
   2. Configure the build by running:

	    For the release version: ./configure CFLAGS=’-O2 -fpic’
	    For the debug version: ./configure CFLAGS=’-g -fpic’

   3. To build the library, run:

   	   make

   4. Copy the resulting ./.libs/libpng.a file the following file <EXTERNAL_LIBS_DIR>/png/libpng.ia32

   5. Copy the files png.h and pngconf.h to the <EXTERNAL_LIBS_DIR>/png directory.

5.3 Building the Little CMS library

   System: Windows* IA-32

   1. Change the working directory to the LCMS source directory.
   2. Open the Visual Studio* solution from the ./Projects/VC7/ directory.
   3. Set the LCMS static library as a startup project.
   4. Specify the configuration: debug or release.
   5. Open the project properties. Go to C/C++/Code generation
      and change the run-time library to multi-threaded: /MT or /MTd.
   6. Go to C/C++/Preprocessor and add __CONSOLE__ to the preprocessor definitions.
   7. Build the LCMS static library.
   8. Copy the file lcms114.lib to the <EXTERNAL_LIBS_DIR>/lcms directory.
	    NOTE: Use the file lcms114d.lib for the debug configuration.
   9. Copy ./include/icc34.h and ./include/lcms.h to the <EXTERNAL_LIBS_DIR>/lcms directory.

   System: Linux* IA-32

   1. Change the working directory to the LCMS source directory.
   2. Configure the build by running:

   	For the release version: ./configure CFLAGS=’-O2 -fpic’
   	For the debug version: ./configure CFLAGS=’-g -fpic’

   3. To build the library, run:

   	make

   4. Copy the file ./src/.libs/liblcms.a to the following file <EXTERNAL_LIBS_DIR>/lcms/liblcms.ia32

   5. Copy the files ./include/icc34.h and ./include/lcms.h
      to the <EXTERNAL_LIBS_DIR>/lcms directory.

6. BUILDING AND RUNNING TESTS
-----------------------------

Verify the following environment variables:

+ PATH must point to the location of Apache Ant.
+ JAVA_HOME must point to the Java* 2 SDK 1.5.0 or the Harmony build.
+ CLASSPATH must include the location of the junit.jar file.

NOTE: All paths must be absolute.

To build and run tests, change the working directory to make/ of a specific
module, and run:

	ant test.classlib

Running this target compiles all api and tests source files (if necessary) and runs tests.

The build produces a set of unit tests' class files and test results.

7. KNOWN ISSUES
---------------

The contributed AWT module has the following known problems:

- Java* 1.5.0 language extensions are not supported.
- The Windows* implementation of the java.awt.Graphics2D object
  does not always use the specified AlphaComposite.
- Graphics2D implementation for BufferedImage cannot draw VolatileImage
  and transformed text.
- Certain methods in the java.awt.geom.Area package are not implemented.
- The java.awt.PrintJob class and image data flavors have been removed
  or commented out because they depend on the packages javax.print and
  javax.imageio respectively, which are not available in Harmony yet.

The contributed SWING module has the following major problems:

- Java* 1.5.0 language extensions are not supported.
- Drag-and-drop operation is partially supported: this is supported
  on the component level, but TransferHanlder is incomplete. Components support
  only plain text flavor.
- Only the Basic and Metal look-and-feels are supported.
  These are implemented in the style of Java* 1.4.2, so that the Ocean theme is not created.
- Bidirectional text is partially supported.
- A number of unit tests fail due to incomplete functionality or known bugs.


8. TODO
---------------

The following functionality is missing in the AWT module:

- Bidirectional text support
- Complete implementation of clipboard and drag-and-drop functionality
- Use FontRenderContext in string drawing functions
- Input methods support

The following functionality is missing in the SWING module:

- Support for the HTML and RTF formats, including parsers and UI representation
- JColorChooser class and related stuff
- JApplet class
- Multi and Synth look-and-feels
- Component accessibility
- Serialization
- Internationalization (exception error messages, and similar)



9. DISCLAIMER
---------------
*) Other brands and names are the property of their respective owners.


