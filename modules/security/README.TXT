INTEL CONTRIBUTION TO APACHE HARMONY
          November 18, 2005
======================================


This archive contains the contribution to the Apache 
Harmony project from Intel.  The contribution consists 
of the following components: 

    - SECURITY  
    - CRYPTO
    - X-NET

See http://wiki.apache.org/harmony/ClassLibrary for a
definition of components. 

1. ARCHIVE CONTENTS
-------------------

The archive contains the source files, the building environment, 
and the unit tests' source for testing the provided implementation.

After extracting the archive, the following directories appear under 
<EXTRACT_DIR>/Harmony, where EXTRACT_DIR is the location into which
you extracted this archive :

  make     - Files required to build the class libraries, native 
              libraries, and tests
  src      - Source files that can be compiled into the class and natives 
              libraries            
  test     - Unit tests' source files
  doc      - ASN.1 framework description


2. TOOLS AND ENVIRONMENT VARIABLES REQUIRED FOR THE BUILD
-----------------------------------------------------------

To build the Java* and C++ sources contained in the src/ directory, 
it is necessary to install and configure the following tools in the 
user environment:

+ Apache Ant 	 - Build tool: Ant 1.6.4 or higher, from 
                   http://ant.apache.org

+ J2SDK 1.4.2    - J2SE* 1.4.2 compatible SDK

+ C/C++ compiler - on Windows*, you can use either of these: 
			- Microsoft* 32-bit C/C++ Compiler v.7 or 
                          higher, from http://www.microsoft.com/downloads/ 
        	   	  and Windows* platform SDK, from 
                          http://www.microsoft.com/downloads/
            		- Microsoft* Visual Studio .NET* 2003 
                          or higher.
                 - on Linux*, the GNU project C/C++ Compiler version 
                   3.3.3 or higher                


You can build class and native libraries without the J2SDK by using 
a Java* 1.4.2 compatible compiler and the jni.h header file instead. 
Comments in the file make/build.xml contain an example of settings 
required for building the libraries with the Eclipse* compiler.


To build the unit test sources contained in the test/ directory and 
run unit tests,  it is necessary to install and configure the following
tools in the user environment:

+ Apache Ant 	 - Build tool: Ant 1.6.4 or higher, from http://ant.apache.org

+ J2SDK 1.4.2    - J2SE* 1.4.2 compatible SDK

+ Junit      	 - Testing framework 3.8.1 or higher, from http://junit.org. 

+ Bouncy Castle  - Bouncy Castle Cryptography provider 1.29 or higher, 
                   from http://www.bouncycastle.org

Note - the Bouncy Castle project includes implementations of the IDEA and 
       other algorithms, which are patented.  Our testsuite doesn't use those 
       algorithms but you should be aware they are present.

3. BUILDING CLASS LIBRARIES AND NATIVE LIBRARIES
------------------------------------------------

On windows*, start Microsoft* Windows* SDK build environment 
or Visual Studio .NET* 2003 Command Prompt.

On both platforms make sure that the following environment 
variables are verified (all paths must be absolute):

+ PATH must contain path to Ant and C++ compiler. 
+ JAVA_HOME must point to Java 2 SDK 1.4.2.

To start the build, change directory into <EXTRACT_DIR>/Harmony/make 
and then run Apache Ant by typing "ant". Ant will run against the 
default target and compile all Java* and C++ sources from the 
<EXTRACT_DIR>/Harmony/src folder.


The build produces a set of .jar files, native libraries, and 
support files. These files are placed in the following directory 
tree structure:

<EXTRACT_DIR>/Harmony/build
       |
       \---deploy
             |
             \---jdk
                \---jre
                     |
                     +---bin
                     |    |
                     |    \--- jaaswin.dll or libjaasnix.so
                     |
                     |
                     \---lib
                         |
                         +---boot
                         |      |
                         |      +--- security.jar
                         |      +--- crypto.jar
                         |      \--- x-net.jar
                         | 
                         +---ext
                         |
                         \---security
                                |
                                +--- drl.policy
                                \--- java.security 


4. RUNNING CLASS LIBRARIES WITH AN EXTERNAL VM
----------------------------------------------

To run an application on a third-party JRE using the classes provided with this 
contribution, do the following:

+ add dynamic library (jaaswin.dll or libjaasnix.so) to the system path or copy 
  the file into the JRE/bin directory
+ setup the JRE to use the configuration files drl.policy and java.security 
  included into this contribution
+ prepend the bootclasspath with security.jar, crypto.jar and x_net.jar

For example:
java -Xbootclasspath/p:c:\jre\lib\boot\security.jar;c:\jre\lib\boot\crypto.jar;c:\jre\lib\boot\x_net.jar -Djava.security.properties==c:\jre\lib\security\java.security -Djava.security.policy==c:\jre\lib\security\drl.policy MyApp

NOTE: certain third-party JREs have internal dependencies on their 
security classes. For example, errors related to 
javax.security.auth.x500.X500Principal may occur. As a workaround for these 
errors, remove X500Principal from the security.jar (see the KNOWN 
ISSUES section of this README).


5. BUILDING AND RUNNING TESTS
-----------------------------

The following environment variables must be verified (all paths must be 
absolute):

+ PATH must contain path to Ant
+ JAVA_HOME must point to Java 2 SDK 1.4.2.
+ junit.jar and bcprov-jdk14-xxx.jar must be available in CLASSPATH


To build and run tests, run 'ant tests.run' in the 
<EXTRACT_DIR>/Harmony/make directory. Running this target compiles 
all api and tests sources (if necessary) and runs tests. 

The build produces a set of unit tests' class files and test results. 
These files are placed in the following directory tree structure:

<EXTRACT_DIR>/Harmony/build
       |
       +--- classes
       |         |
       |         \--- security_alt.jar      security.jar w/o X500Principal class
       |
       +--- tests                           compiled unit tests
       |
       +--- tests_report                    test results

             

6. KNOWN ISSUES
---------------

Jar file verification is out of the scope of the given 
contribution. Instead JRE's signature verification is used 
when run unit tests. Third party JREs might have internal 
dependencies on third-party implementations of security classes. 

When run the unit tests with some third-party JREs errors 
connected to javax.security.auth.x500.X500Principal occur. To
avoid those errors this class is excluded from bootclasspath for the test run 
and corresponding original JRE class is used instead. Thus security_alt.jar 
without X500Principal.class created in the <EXTRACT_DIR>/Harmony/build/classes 
directory for the tests run.


7. DISCLAIMER AND LEGAL INFORMATION
------------------------------------

*) Other brands and names are the property of their respective owners.
