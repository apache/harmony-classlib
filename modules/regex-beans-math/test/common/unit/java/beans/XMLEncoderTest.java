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
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.5.6.3 $
 */
package java.beans;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import java.beans.XMLEncoder;

import java.beans.auxiliary.AType;
import java.beans.auxiliary.StandardBean;

/**
 * The test checks the class java.beans.XMLEncoder
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.5.6.3 $
 */

public class XMLEncoderTest extends TestCase {
    
    /**
     * 
     */
    public XMLEncoderTest() {
        super();
    }
    
    /**
     *
     */
    public XMLEncoderTest(String name) {
        super(name);
    }
    
    /**
     * The test checks that java.lang.Boolean exemplar store is correct
     */
    public void testEncodeBoolean() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });
        
        try {
            e.writeObject(new Boolean(true));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     * The test checks that java.lang.Byte exemplar store is correct
     */
    public void testEncodeByte() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });
        
        try {
            e.writeObject(new Byte((byte) 123));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     * The test checks that java.lang.Character exemplar store is correct
     */
    public void testEncodeCharacter() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });
        
        try {
            e.writeObject(new Character('a'));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     * The test checks that java.lang.Class exemplar store is correct
     */
    public void testEncodeClass() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });
        
        try {
            e.writeObject(Object.class);
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     * The test checks that java.lang.Double exemplar store is correct
     */
    public void testEncodeDouble() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });
        
        try {
            e.writeObject(new Double((double) 0.01));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     * The test checks that java.lang.Float exemplar store is correct
     */
    public void testEncodeFloat() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });
        
        try {
            e.writeObject(new Float((float) 0.01));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     * The test checks that java.lang.Integer exemplar store is correct
     */
    public void testEncodeInteger() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });
        
        try {
            e.writeObject(new Integer(1));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     * The test checks that java.lang.Long exemplar store is correct
     */
    public void testEncodeLong() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });
        
        try {
            e.writeObject(new Long(1));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     * The test checks that java.lang.Short exemplar store is correct
     */
    public void testEncodeShort() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });
        
        try {
            e.writeObject(new Short((short) 1));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     * The test checks that java.lang.String exemplar store is correct
     */
    public void testEncodeString() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });
        
        try {
            e.writeObject(new String("hello"));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     * The test checks that array exemplar store is correct
     */
    public void testEncodeArray() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });
        
        try {
            e.writeObject(new int[] {1, 2, 3});
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     * The test checks that null exemplar store is correct
     */
    public void testEncodeNull() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });
        
        try {
            e.writeObject(null);
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     * The test checks that complex scenario store is correct
     */
    public void testEncodingScenario() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });

        StandardBean bean1 = new StandardBean("bean1");
        
        StandardBean bean2 = new StandardBean();
        bean2.setText(null);
        
        bean1.setPeer(bean2);
        bean2.setPeer(bean1);
        
        try {
            e.writeObject(bean1);
            e.writeObject(bean2);
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     * The test checks that encoder can handle writeExpression in initialize
     */
    public void testEncodeExpressionAsStatement() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() +" is thrown: "
                        + e.getMessage());
            }
        });
        
        try {
            final Object object = new Object();
            e.setPersistenceDelegate(AType.class,
                    new DefaultPersistenceDelegate() {
                        protected void initialize(
                                Class type,
                                Object oldInstance,
                                Object newInstance,
                                Encoder out) {
                            out.writeExpression(new Expression(
                                    object, oldInstance, "go", new Object[] {}
                                    ));
                        }
                    });
            AType a = new AType();

            //e.writeObject(object);
            e.writeObject(a);
            e.writeObject(object);
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() +" is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
    
    /**
     *
     */
    public static Test suite() {
        TestSuite suite = new TestSuite();
        //suite.addTest(new XMLEncoderTest("testEncodeArray"));
        //suite.addTest(new XMLEncoderTest("testEncodeExpressionAsStatement"));
        //suite.addTest(new XMLEncoderTest("testEncodingScenario"));
        //return suite;
        return new TestSuite(XMLEncoderTest.class);
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
}
