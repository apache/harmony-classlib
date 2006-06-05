/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.Vector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class TestSuiteAcumulable implements Test {
    
        final private Class theClass;
        private Vector names;
        private String fName;
       
        /**
         * Constructs a TestSuite from the given class with the given name.
         * @see TestSuite#TestSuite(Class)
         */
        public TestSuiteAcumulable(Class theClass, String name) {
            this(theClass);
            setName(name);
        }
        
        /**
         * Constructs a TestSuite from the given class. Adds all the methods
         * starting with "test" as test cases to the suite.
         * Parts of this method was written at 2337 meters in the Hüffihütte,
         * Kanton Uri
         */
         public TestSuiteAcumulable(final Class theClass) {
            fName= theClass.getName();
            names= new Vector();
            Class superClass= theClass;
            while (Test.class.isAssignableFrom(superClass)) {
                Method[] methods= superClass.getDeclaredMethods();
                for (int i= 0; i < methods.length; i++) {
                    addTestMethod(methods[i]);
                }
                superClass= superClass.getSuperclass();
            }
            this.theClass = theClass;
         }

        @SuppressWarnings("unchecked")
        private void addTestMethod(Method m) {
             String name= m.getName();
             if (names.contains(name)) {
                 return;
             }
            if (isPublicTestMethod(m)) {
                names.addElement(name);
            } 
        }
        
        /**
         * ...as the moon sets over the early morning Merlin, Oregon
         * mountains, our intrepid adventurers type...
         */
        static public Test createTest(Class theClass, String name) {
            Constructor constructor;
            try {
                constructor= getTestConstructor(theClass);
            } catch (NoSuchMethodException e) {
                return warning("Class "+theClass.getName()+" has no public constructor TestCase(String name) or TestCase()");
            }
            Object test;
            try {
                if (constructor.getParameterTypes().length == 0) {
                    test= constructor.newInstance(new Object[0]);
                    if (test instanceof TestCase)
                        ((TestCase) test).setName(name);
                } else {
                    test= constructor.newInstance(new Object[]{name});
                }
            } catch (InstantiationException e) {
                return(warning("Cannot instantiate test case: "+name+" ("+exceptionToString(e)+")"));
            } catch (InvocationTargetException e) {
                return(warning("Exception in constructor: "+name+" ("+exceptionToString(e.getTargetException())+")"));
            } catch (IllegalAccessException e) {
                return(warning("Cannot access test case: "+name+" ("+exceptionToString(e)+")"));
            }
            return (Test) test;
        }

        /**
         * Converts the stack trace into a string
         */
        private static String exceptionToString(Throwable t) {
            StringWriter stringWriter= new StringWriter();
            PrintWriter writer= new PrintWriter(stringWriter);
            t.printStackTrace(writer);
            return stringWriter.toString();

        }
        
        /**
         * Adds a test to the suite.
         */
        public void addTest(Test test) {
            throw new AssertionError("TestSuiteAcumulable mustn't add Test");
        }
        
        /**
         * Returns the test at the given index
         */
        public Test testAt(int index) {
            return createTest(theClass, (String) names.elementAt(index));
        }
        
        /**
         * Returns the number of tests in this suite
         */
        public int testCount() {
            return countTestCases();
        }
        
        /**
         * Returns the tests as an enumeration
         */
        public Enumeration tests() {
            return names.elements();
        }
        
        
        /**
         * Counts the number of test cases that will be run by this test.
         */
        public int countTestCases() { 
            return names.size();
            
        }
        
        /**
         * Gets a constructor which takes a single String as
         * its argument or a no arg constructor.
         */
        public static Constructor getTestConstructor(Class theClass) throws NoSuchMethodException {
            Class[] args= { String.class };
            try {
                return theClass.getConstructor(args);   
            } catch (NoSuchMethodException e) {
                // fall through
            }
            return theClass.getConstructor(new Class[0]);
        }

        private boolean isPublicTestMethod(Method m) {
            return isTestMethod(m) && Modifier.isPublic(m.getModifiers());
         }
         
        private boolean isTestMethod(Method m) {
            String name= m.getName();
            Class[] parameters= m.getParameterTypes();
            Class returnType= m.getReturnType();
            return parameters.length == 0 && name.startsWith("test") && returnType.equals(Void.TYPE);
         }
         
        /**
         * Runs the tests and collects their result in a TestResult.
         */
        public void run(TestResult result) {
            for (Enumeration e = names.elements(); e.hasMoreElements(); ) {
                if (result.shouldStop() )
                    break;
                createTest(theClass, (String) e.nextElement()).run(result);
            }
            
            
        }

        public void runTest(Test test, TestResult result) {
            test.run(result);
        }

        public String toString() {
            if (getName() != null)
                return getName();
            return super.toString();
         }
         
        /**
         * Sets the name of the suite.
         * @param name The name to set
         */
        public void setName(String name) {
            fName= name;
        }

        /**
         * Returns the name of the suite. Not all
         * test suites have a name and this method
         * can return null.
         */
        public String getName() {
            return fName;
        }

        /**
         * Returns a test which will fail and log a warning message.
         */
        private static Test warning(final String message) {
            return new TestCase("warning") {
                protected void runTest() {
                    fail(message);
                }
            };
        }
    }