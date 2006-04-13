INTEL CONTRIBUTION TO APACHE HARMONY
          January 19, 2006
======================================


This archive contains the contribution to the Apache 
Harmony project from Intel. The contribution consists 
of the following components: 

    - BEANS
    - REGEX
    - MATH

See http://wiki.apache.org/harmony/ClassLibrary for a
definition of components. 

NOTE: This contribution includes the stubs for the certain classes from the
java.awt and java.applet packages to enable compilation of java.beans.
The stub classes do not yet include the complete method signatures
or their fully-functional implementaions.

1. ARCHIVE CONTENTS
-------------------

The archive contains the source files, the building environment, 
and the unit tests' source for testing the provided implementation.

After extracting the archive, the following directories appear under 
<EXTRACT_DIR>/Harmony, where EXTRACT_DIR is the location of the 
archive content:

  doc      - Regex framework description
  make     - Files required to build the class libraries and tests
  src      - Source files that can be compiled into the class libraries            
  test     - Unit tests' source files


2. TOOLS AND ENVIRONMENT VARIABLES REQUIRED FOR THE BUILD
-----------------------------------------------------------

To build the Java* sources contained in the src/ directory, 
install and configure the following tools in the user environment:

+ Apache Ant     - Build tool: Ant 1.6.4 or higher, from 
                   http://ant.apache.org

+ Eclipse* compiler - Eclipse* SDK 3.0.1 or higher, from 
                   http://download.eclipse.org/eclipse/downloads/index.php

+ Virtual machine - Apache Harmony Execution Environment, from
                   http://www-128.ibm.com/developerworks/java/jdk/harmony

+ Class libraries - Harmony Class Libraries, from
                   https://svn.apache.org/repos/asf/incubator/harmony/enhanced/classlib/trunk

+ Xerces J        - Xerces2 Java Parser 2.7.1, from 
                   http://xerces.apache.org/xerces2-j
    

To build the unit test sources contained in the test directory and 
run unit tests, additionally install and configure the following tools:

+ JUnit          - Testing framework 3.8.1 or higher, from http://junit.org 


3. BUILDING CLASS LIBRARIES
---------------------------

Build the Harmony class libraries. For building instructions, see the README.txt
file deployed with the class libraries bundle.

Copy the contents of the deploy\jre directory of the class libraries into deploy\jre
 directory of the Harmony execution environment.

Set up the environment variables in the following way: 

+ PATH must point to the location of Ant. 
+ JAVA_HOME must point to the location of the Harmony execution environment
+ CLASSPATH must point to the Eclipse* compiler .jar files, to the Harmony class libraries, and to kernel.jar
  from the Harmony execution environment. An example of the CLASSPATH variable on Windows*:

      CLASSPATH=%ECLIPSE_HOME%\plugins\org.eclipse.jdt.core_3.0.1\jdtCompilerAdapter.jar; \
          %ECLIPSE_HOME%\plugins\org.eclipse.jdt.core_3.0.1\jdtcore.jar; \
          %JAVA_HOME%\bin\default\kernel.jar;C:\Harmony\bin;C:\junit3.8.1\junit.jar

NOTE: All paths must be absolute. 

To start the build, change the directory into <EXTRACT_DIR>/Harmony/make 
and run Apache Ant by typing: 
    
    ant -Dbuild.compiler=org.eclipse.jdt.core.JDTCompilerAdapter 

Ant runs against the default target and compiles all Java* sources from the 
<EXTRACT_DIR>/Harmony/src folder.

You can also build sources of individual components by using the following 
commands:  
    ant -Dbuild.compiler=org.eclipse.jdt.core.JDTCompilerAdapter jar.beans
        for BEANS component
    ant -Dbuild.compiler=org.eclipse.jdt.core.JDTCompilerAdapter jar.regex
        for REGEX component
    ant -Dbuild.compiler=org.eclipse.jdt.core.JDTCompilerAdapter jar.math
        for MATH component

The build produces a set of .class and .jar files.
These files are placed in the following directory 
tree structure:

<EXTRACT_DIR>/Harmony/build
       |
       +---classes                  compiled class files
       |
       +---deploy
             |
             \---jre
                  |
                  |
                  \---lib
                      |
                      +---boot
                             |
                             +--- beans.jar
                             +--- math.jar
                             \--- regex.jar
       

NOTE: To build regex and math packages in a self-hosting environment on Linux*, you also need 
to set up the LD_LIBRARY_PATH variable to point to the bin directory of the Harmony JRE. Make sure
that all environment variables are exported


4. RUNNING CLASS LIBRARIES WITH AN EXTERNAL VM
----------------------------------------------

To run an application on a third-party JRE using the classes provided with this 
contribution, prepend the bootclasspath with beans.jar, regex.jar and math.jar. 

Example:
java -Xbootclasspath/p:c:\jre\lib\boot\beans.jar;c:\jre\lib\boot\regex.jar;c:\jre\lib\boot\math.jar MyApp

NOTE: Certain third-party environments have internal dependencies on their 
beans, regex or math classes. As a workaround for these errors, 
try removing classes with such dependencies.


5. BUILDING AND RUNNING TESTS
-----------------------------

Verify the values for the following environment variables: 

+ PATH must point to the location of Ant. 
+ CLASSPATH must point to the location of JUnit classes (normally, junit.jar).
+ JAVA_HOME must point to the Harmony execution environment.
+ <EXTRACT_DIR>/Harmony/make/external_tools.properties must contain valid
paths to JUnit and Xerces implementation classes (normally "junit.jar", "xercesImpl.jar" and "xml-apis.jar").

NOTE: All paths must be absolute.

To build and run all tests in the <EXTRACT_DIR>/Harmony/make directory, run 

    ant -Dbuild.compiler=org.eclipse.jdt.core.JDTCompilerAdapter tests.run 

Running this target compiles all api and tests sources (if necessary) and runs tests. 

You can also run all unit test suites for individual components by using
the following commands:
    ant -Dbuild.compiler=org.eclipse.jdt.core.JDTCompilerAdapter test.beans
        for BEANS component
    ant -Dbuild.compiler=org.eclipse.jdt.core.JDTCompilerAdapter test.regex
        for REGEX component
    ant -Dbuild.compiler=org.eclipse.jdt.core.JDTCompilerAdapter test.math
        for MATH component

The build produces a set of unit tests' class files and test results. 
These files are in the following directory tree structure:

<EXTRACT_DIR>/Harmony/build
       |
       |
       +--- tests                          compiled unit tests
       |
       +--- test-reports                   test results



6. KNOWN ISSUES
---------------

- Unit tests for the beans package may fail in the Harmony self-hosting 
  environment due to the incomplete functionality of stub classes in awt
  and applet packages. The failing tests are:
      java.beans.EventHandlerTest
      java.beans.SimpleBeanInfoTest   

- The find() and findBack() methods in regex nodes may work incorrectly with back references. 
The issue may show up for such patterns as below: 
    .*(.)\\1
    (.)\\1

- The Node.match() method is not completely optimized in regex. It may call the CharSequence.toString() method 
even if CharacterSequence is not a string, which leads to a String creation during each call to 
this node’s match() method.

- Methods implementing bitwise operations (and, or, and xor) in the BigInteger class might seem complicated 
although they work rather efficiently. These methods might be rewritten in a more comprehensive form.  


7. TODO
--------

- Implement canonical equivalence support in regex.

- Simplify multistep operations in the BigInteger class, which create 
multiple BigInteger objects on each step. The methods gcd (), isProbablePrime(), 
modInverse(), and modPow()can be speeded up with implementing safe in-place computations. 

- Enhance the multistep BigInteger.pow() method. To reduce the number of created 
objects, the method can be implemented by using the square-and-multiply algorithm. 
However, the proposed implementation works longer due to longer multiplication of large numbers.
The enhancement may consist of implementing safe in-place multiplication 
and possible combination of the two algorithms.

- Implement special cases for more efficient exponentiation of BigInteger.TEN and 
multiplication by using BigInteger.TEN. These mechanisms are widely used in the BigDecimal implementation.

- Optimize the BigInteger.toString() method.


8. DISCLAIMER AND LEGAL INFORMATION
------------------------------------

*) Other brands and names are the property of their respective owners.
