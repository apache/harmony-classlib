/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.beans.tests.java.beans;

import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.ExceptionListener;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.Statement;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import junit.framework.TestCase;

import org.apache.harmony.beans.tests.java.beans.EncoderTest.SampleBean;
import org.apache.harmony.beans.tests.support.AType;
import org.apache.harmony.beans.tests.support.StandardBean;
import org.apache.harmony.beans.tests.support.TestEventHandler;
import org.apache.harmony.beans.tests.support.mock.MockBean4Codec;
import org.apache.harmony.beans.tests.support.mock.MockBean4Owner_Owner;
import org.apache.harmony.beans.tests.support.mock.MockBean4Owner_Target;
import org.apache.harmony.beans.tests.support.mock.MockBean4StaticField;
import org.apache.harmony.beans.tests.support.mock.MockBean4StaticField_PD;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Tests for XMLEncoder
 */
public class XMLEncoderTest extends TestCase {

    public static void main(String[] args) {

        // VerboseEncoder enc = new VerboseEncoder();
        //
        // MockBean4Codec b = new MockBean4Codec();
        // b.getBornFriend().getZarr()[0] = 888;
        // b.setNill(b.getBornFriend());
        //
        // enc.writeObject(b);
        // enc.flush();

        junit.textui.TestRunner.run(XMLEncoderTest.class);
    }

    public static class DependencyBean {
        private int ints[] = new int[] { 1 };

        private Object ref;

        public int[] getInts() {
            return ints;
        }

        public void setInts(int[] ints) {
            this.ints = ints;
        }

        public Object getRef() {
            return ref;
        }

        public void setRef(Object ref) {
            this.ref = ref;
        }
    }

    public static class VerboseEncoder extends XMLEncoder {

        private PrintWriter out;

        private boolean ident;

        public VerboseEncoder() {
            this(new PrintWriter(System.out, true), true);
        }

        public VerboseEncoder(PrintWriter out, boolean ident) {
            super(System.out);
            this.out = out;
            this.ident = ident;
        }

        @Override
        public Object get(Object arg0) {
            String identStr = ident ? ident() : "";
            out.println(identStr + "get()> " + arg0);
            Object result = super.get(arg0);
            out.println(identStr + "get()< " + result);
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        public PersistenceDelegate getPersistenceDelegate(Class type) {
            PersistenceDelegate result = super.getPersistenceDelegate(type);
            return result;
        }

        @Override
        public Object remove(Object arg0) {
            String identStr = ident ? ident() : "";
            out.println(identStr + "remove()> " + arg0);
            Object result = super.remove(arg0);
            out.println(identStr + "remove()< " + result);
            return result;
        }

        @Override
        public void writeExpression(Expression arg0) {
            String identStr = ident ? ident() : "";
            out.println(identStr + "writeExpression()> " + string(arg0));
            super.writeExpression(arg0);
            out.println(identStr + "writeExpression()< ");
        }

        @Override
        public void writeStatement(Statement arg0) {
            String identStr = ident ? ident() : "";
            out.println(identStr + "writeStatement()> " + string(arg0));
            super.writeStatement(arg0);
            out.println(identStr + "writeStatement()< ");
        }

        @Override
        public void writeObject(Object arg0) {
            String identStr = ident ? ident() : "";
            out.println(identStr + "writeObject()> " + arg0);
            super.writeObject(arg0);
            out.println(identStr + "writeObject()< ");
        }
    }

    public XMLEncoderTest() {
        super();
    }

    public XMLEncoderTest(String s) {
        super(s);
    }

    public static String ident() {
        Exception ex = new Exception();
        int level = ex.getStackTrace().length;
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < level; i++) {
            buf.append("  ");
        }
        return buf.toString();
    }

    public static String string(Statement stat) {
        String str = "(" + stat.getTarget() + ")." + stat.getMethodName() + "(";
        Object args[] = stat.getArguments();
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                str += ", ";
            }
            str += args[i];
        }
        str = str + ")";
        return str;
    }

    public static String string(Expression exp) {
        String str = "";
        try {
            str += str + exp.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        str += "=" + string((Statement) exp);
        return str;
    }

    public void testWriteExpression() {
        // covered by testWriteObject
    }

    public void testWriteStatement() {
        // covered by testWriteStatement

         //Regression for HARMONY-1521
         //no exception expected
         new XMLEncoder(new ByteArrayOutputStream()).writeStatement(null);
    }

    public void testWriteObject_Null() throws Exception {
        assertCodedXML(null, "/xml/null.xml");
    }

    public void testWriteObject_Integer() throws Exception {
        assertCodedXML(new Integer(3), "/xml/int.xml");
    }

    public void testWriteObject_StringCodec() throws Exception {
        SampleBean b = new SampleBean();
        b.setMyid("<Li Yang> & \"liyang'");
        SampleBean c = new SampleBean();
        c.setMyid("a child");
        b.setRef(c);
        assertCodedXML(b, "/xml/SampleBean_StringCodec.xml");
    }

    public void testWriteObject_IntArray() throws Exception {
        assertCodedXML(new int[] { 1, 2, 3 }, "/xml/IntArray.xml");
    }

    public void testWriteObject_PropertyDependency() throws Exception {
        DependencyBean b = new DependencyBean();
        b.getInts()[0] = 888;
        b.setRef(b.getInts());
        assertCodedXML(b, "/xml/DependencyBean.xml");
    }

    public void testWriteObject_NoChange() throws Exception {
        assertCodedXML(new MockBean4Codec(), "/xml/MockBean4Codec_NoChange.xml");
    }

    public void testWriteObject_BornFriendChange() throws Exception {
        MockBean4Codec b = new MockBean4Codec();
        b.getBornFriend().getZarr()[0] = 888;
        b.setNill(b.getBornFriend());

        assertCodedXML(b, "/xml/MockBean4Codec_BornFriendChange.xml");
    }

    public void testWriteObject_ManyChanges() throws Exception {
        assertCodedXML(MockBean4Codec.getInstanceOfManyChanges(),
                "/xml/MockBean4Codec_ManyChanges.xml");
    }

    public void testWriteObject_ManyChanges_2() throws Exception {
        assertCodedXML(MockBean4Codec.getInstanceOfManyChanges2(),
                "/xml/MockBean4Codec_ManyChanges_2.xml");
    }

    public void testWriteObject_SetOwner() throws Exception {
        ByteArrayOutputStream temp = new ByteArrayOutputStream();
        XMLEncoder enc = new XMLEncoder(temp);

        MockBean4Owner_Target t = new MockBean4Owner_Target();
        MockBean4Owner_Owner o = new MockBean4Owner_Owner();
        t.setV(o);
        enc.setOwner(o);

        assertCodedXML(t, "/xml/MockBean4Owner_SetOwner.xml", temp, enc);

    }

    public void testWriteObject_SetOwnerWithWriteStatement() throws Exception {
        ByteArrayOutputStream temp = new ByteArrayOutputStream();
        XMLEncoder enc = new XMLEncoder(temp);

        MockBean4Owner_Target t = new MockBean4Owner_Target();
        MockBean4Owner_Owner o = new MockBean4Owner_Owner();
        t.setV(o);
        enc.setOwner(o);

        enc.writeStatement(new Statement(o, "loading", new Object[] {}));

        assertCodedXML(t, "/xml/MockBean4Owner_SetOwnerWithWriteStatement.xml",
                temp, enc);

    }

    public void testWriteObject_StaticField() throws Exception {
        ByteArrayOutputStream temp = new ByteArrayOutputStream();
        XMLEncoder enc = new XMLEncoder(temp);

        enc.setPersistenceDelegate(MockBean4StaticField.class,
                new MockBean4StaticField_PD());

        assertCodedXML(MockBean4StaticField.inst,
                "/xml/MockBean4StaticField.xml", temp, enc);

    }

    public void testClose() {
        ByteArrayOutputStream out = new ByteArrayOutputStream() {
            boolean closeCalled = false;

            @Override
            public void close() throws IOException {
                if (closeCalled) {
                    throw new IOException("close already called!");
                }
                closeCalled = true;
                super.close();
            }
        };
        XMLEncoder enc = new XMLEncoder(out);
        enc.writeObject(new Integer(3));
        assertEquals(0, out.size());

        enc.close();

        assertTrue(out.size() > 0);
        try {
            out.close();
            fail();
        } catch (IOException e) {
            assertEquals("close already called!", e.getMessage());
        }
    }

    public void testFlush() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLEncoder enc = new XMLEncoder(out);
        Integer i = new Integer(3);
        enc.writeObject(i);
        assertEquals(0, out.size());
        assertNotNull(enc.get(i));

        enc.flush();

        assertTrue(out.size() > 0);
        assertNull(enc.get(i));
    }

    public void testXMLEncoder_Null() throws NullPointerException {
        new XMLEncoder(null);
    }

    public void testGetOwner() {
        XMLEncoder enc = new XMLEncoder(System.out);
        assertNull(enc.getOwner());
    }

    public void testSetOwner() {
        XMLEncoder enc = new XMLEncoder(System.out);
        Object owner = Boolean.FALSE;

        enc.setOwner(owner);
        assertSame(owner, enc.getOwner());

        enc.setOwner(null);
        assertNull(enc.getOwner());
    }

    private void assertCodedXML(Object obj, String xmlFile) throws Exception {
        ByteArrayOutputStream temp = new ByteArrayOutputStream();
        XMLEncoder enc = new XMLEncoder(temp);

        assertCodedXML(obj, xmlFile, temp, enc);
    }

    private void assertCodedXML(Object obj, String xmlFile,
            ByteArrayOutputStream temp, XMLEncoder enc) throws Exception {

        InputStream refIn;
        InputStreamReader xml;
        InputStreamReader refXml;

        XMLReader xmlReader;
        XMLReader refXmlReader;
        TestEventHandler handler = new TestEventHandler();
        TestEventHandler refHandler = new TestEventHandler();
        String saxParserClassName = System.getProperty("org.xml.sax.driver");

        if (enc == null || temp == null) {
            temp = new ByteArrayOutputStream();
            enc = new XMLEncoder(temp);
        }
        enc.writeObject(obj);
        enc.close();
        byte bytes[] = temp.toByteArray();

        refIn = XMLEncoderTest.class.getResourceAsStream(xmlFile);
        if (refIn == null) {
            throw new Error("resource " + xmlFile + " not exist in "
                    + XMLEncoderTest.class.getPackage());
        }
        xml = new InputStreamReader(new ByteArrayInputStream(bytes), "UTF-8");
        refXml = new InputStreamReader(refIn, "UTF-8");

        if (saxParserClassName == null) {
            saxParserClassName = "org.apache.xerces.parsers.SAXParser";
        }

        xmlReader = XMLReaderFactory.createXMLReader(saxParserClassName);
        xmlReader.setContentHandler(handler);
        xmlReader.setErrorHandler(handler);
        xmlReader.parse(new InputSource(xml));

        refXmlReader = XMLReaderFactory.createXMLReader(saxParserClassName);
        refXmlReader.setContentHandler(refHandler);
        refXmlReader.setErrorHandler(refHandler);
        refXmlReader.parse(new InputSource(refXml));

        assertEquals("Generated XML differs from the sample,", refHandler.root,
                handler.root);
    }

    /**
     * The test checks that java.lang.Boolean exemplar store is correct
     */
    public void testEncodeBoolean() {
        XMLEncoder e = new XMLEncoder(System.out);
        e.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                fail("Exception " + e.getClass() + " is thrown: "
                        + e.getMessage());
            }
        });

        try {
            e.writeObject(new Boolean(true));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() + " is thrown: "
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
                fail("Exception " + e.getClass() + " is thrown: "
                        + e.getMessage());
            }
        });

        try {
            e.writeObject(new Byte((byte) 123));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() + " is thrown: "
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
                fail("Exception " + e.getClass() + " is thrown: "
                        + e.getMessage());
            }
        });

        try {
            e.writeObject(new Character('a'));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() + " is thrown: "
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
                fail("Exception " + e.getClass() + " is thrown: "
                        + e.getMessage());
            }
        });

        try {
            e.writeObject(Object.class);
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() + " is thrown: "
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
                fail("Exception " + e.getClass() + " is thrown: "
                        + e.getMessage());
            }
        });

        try {
            e.writeObject(new Double(0.01));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() + " is thrown: "
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
                fail("Exception " + e.getClass() + " is thrown: "
                        + e.getMessage());
            }
        });

        try {
            e.writeObject(new Float((float) 0.01));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() + " is thrown: "
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
                fail("Exception " + e.getClass() + " is thrown: "
                        + e.getMessage());
            }
        });

        try {
            e.writeObject(new Integer(1));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() + " is thrown: "
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
                fail("Exception " + e.getClass() + " is thrown: "
                        + e.getMessage());
            }
        });

        try {
            e.writeObject(new Long(1));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() + " is thrown: "
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
                fail("Exception " + e.getClass() + " is thrown: "
                        + e.getMessage());
            }
        });

        try {
            e.writeObject(new Short((short) 1));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() + " is thrown: "
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
                fail("Exception " + e.getClass() + " is thrown: "
                        + e.getMessage());
            }
        });

        try {
            e.writeObject(new String("hello"));
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() + " is thrown: "
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
                fail("Exception " + e.getClass() + " is thrown: "
                        + e.getMessage());
            }
        });

        try {
            e.writeObject(new int[] { 1, 2, 3 });
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() + " is thrown: "
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
                fail("Exception " + e.getClass() + " is thrown: "
                        + e.getMessage());
            }
        });

        try {
            e.writeObject(null);
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() + " is thrown: "
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
                fail("Exception " + e.getClass() + " is thrown: "
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
            fail("Exception " + excp.getClass() + " is thrown: "
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
                fail("Exception " + e.getClass() + " is thrown: "
                        + e.getMessage());
            }
        });

        try {
            final Object object = new Object();
            e.setPersistenceDelegate(AType.class,
                    new DefaultPersistenceDelegate() {
                        @SuppressWarnings("unchecked")
                        @Override
                        protected void initialize(Class type,
                                Object oldInstance, Object newInstance,
                                Encoder out) {
                            out.writeExpression(new Expression(object,
                                    oldInstance, "go", new Object[] {}));
                        }
                    });
            AType a = new AType();

            // e.writeObject(object);
            e.writeObject(a);
            e.writeObject(object);
        } catch (Exception excp) {
            fail("Exception " + excp.getClass() + " is thrown: "
                    + excp.getMessage());
        } finally {
            e.close();
        }
    }
}
