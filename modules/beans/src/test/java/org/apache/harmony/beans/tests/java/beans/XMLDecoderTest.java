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

import java.beans.ExceptionListener;
import java.beans.Introspector;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Vector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.harmony.beans.tests.java.beans.EncoderTest.SampleBean;
import org.apache.harmony.beans.tests.java.beans.XMLEncoderTest.DependencyBean;
import org.apache.harmony.beans.tests.support.mock.MockBean4Codec;
import org.apache.harmony.beans.tests.support.mock.MockBean4Owner_Owner;
import org.apache.harmony.beans.tests.support.mock.MockBean4Owner_Target;
import org.apache.harmony.beans.tests.support.mock.MockBean4StaticField;
import org.apache.harmony.beans.tests.support.mock.MockExceptionListener;

/**
 * Tests XMLDecoder
 */
public class XMLDecoderTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(XMLDecoderTest.class);
    }

    static byte xml123bytes[] = null;

    static {
        ByteArrayOutputStream byteout = new ByteArrayOutputStream();
        XMLEncoder enc = new XMLEncoder(byteout);
        enc.writeObject(Integer.valueOf("1"));
        enc.writeObject(Integer.valueOf("2"));
        enc.writeObject(Integer.valueOf("3"));
        enc.close();
        xml123bytes = byteout.toByteArray();
    }

    static class MockClassLoader extends ClassLoader {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            throw new ClassNotFoundException();
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            throw new ClassNotFoundException();
        }

    }

    public void testConstructor_ClassLoader() {
        XMLDecoder dec;
        final Vector<Exception> exceptions = new Vector<Exception>();

        ExceptionListener el = new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                exceptions.addElement(e);
            }
        };

        dec = new XMLDecoder(new ByteArrayInputStream(xml123bytes), this, el,
                Thread.currentThread().getContextClassLoader());
        assertEquals(Integer.valueOf("1"), dec.readObject());
        assertEquals(0, exceptions.size());
        dec.close();

        dec = new XMLDecoder(new ByteArrayInputStream(xml123bytes), this, el,
                new MockClassLoader());
        try {
            dec.readObject();
            assertTrue(exceptions.size() > 0);
        } catch (ArrayIndexOutOfBoundsException e) {
            // also valid
        }
        dec.close();
    }

    public void testClose() {
        XMLDecoder dec = new XMLDecoder(new ByteArrayInputStream(xml123bytes));
        assertEquals(Integer.valueOf("1"), dec.readObject());

        dec.close();

        assertEquals(Integer.valueOf("2"), dec.readObject());
        assertEquals(Integer.valueOf("3"), dec.readObject());
    }

    public void testGetExceptionListener() {
        XMLDecoder dec = new XMLDecoder(new ByteArrayInputStream(xml123bytes));
        assertNotNull(dec.getExceptionListener());
    }

    public void testGetOwner() {
        XMLDecoder dec = new XMLDecoder(new ByteArrayInputStream(xml123bytes));
        assertNull(dec.getOwner());
    }

    public void testReadObject_ArrayOutOfBounds() {
        XMLDecoder dec = new XMLDecoder(new ByteArrayInputStream(xml123bytes));
        assertEquals(Integer.valueOf("1"), dec.readObject());
        assertEquals(Integer.valueOf("2"), dec.readObject());
        assertEquals(Integer.valueOf("3"), dec.readObject());

        try {
            dec.readObject();
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            // expected
        }
    }

    public void testReadObject_Null() {
        XMLDecoder dec = new XMLDecoder(this.getClass().getResourceAsStream(
                "/xml/null.xml"));
        Object obj = dec.readObject();
        assertNull(obj);
    }

    public void testReadObject_Integer() {
        XMLDecoder dec = new XMLDecoder(this.getClass().getResourceAsStream(
                "/xml/int.xml"));
        Object obj = dec.readObject();
        assertEquals(Integer.valueOf("3"), obj);
    }

    public void testReadObject_StringCodec() {
        XMLDecoder dec = new XMLDecoder(this.getClass().getResourceAsStream(
                "/xml/SampleBean_StringCodec.xml"));
        SampleBean obj = (SampleBean) dec.readObject();
        assertEquals("<Li Yang> & \"liyang'", obj.getMyid());
        assertEquals("a child", obj.getRef().getMyid());
    }

    public void testReadObject_IntArray() {
        XMLDecoder dec = new XMLDecoder(this.getClass().getResourceAsStream(
                "/xml/IntArray.xml"));
        int ints[] = (int[]) dec.readObject();
        assertEquals(1, ints[0]);
        assertEquals(2, ints[1]);
        assertEquals(3, ints[2]);
    }

    public void testReadObject_PropertyDependency() {
        XMLDecoder dec = new XMLDecoder(this.getClass().getResourceAsStream(
                "/xml/DependencyBean.xml"));
        DependencyBean b = (DependencyBean) dec.readObject();
        assertEquals(888, b.getInts()[0]);
        assertSame(b.getInts(), b.getRef());
    }

    public void testReadObject_NoChange() {
        XMLDecoder dec = new XMLDecoder(this.getClass().getResourceAsStream(
                "/xml/MockBean4Codec_NoChange.xml"));
        dec.readObject();
    }

    public void testReadObject_BornFriendChange() {
        XMLDecoder dec = new XMLDecoder(this.getClass().getResourceAsStream(
                "/xml/MockBean4Codec_BornFriendChange.xml"));
        MockBean4Codec b = (MockBean4Codec) dec.readObject();
        assertEquals(888, b.getBornFriend().getZarr()[0]);
        assertEquals(b.getBornFriend(), b.getNill());
    }

    public void testReadObject_ManyChanges() {
        XMLDecoder dec = new XMLDecoder(this.getClass().getResourceAsStream(
                "/xml/MockBean4Codec_ManyChanges.xml"));
        MockBean4Codec b = (MockBean4Codec) dec.readObject();
        assertEquals(127, b.getB());
        assertSame(b, b.getBackRef());
        assertEquals(new Byte((byte) 127), b.getBobj());
        assertFalse(b.isBool());
        assertEquals(Boolean.TRUE, b.getBoolobj());
        assertEquals(Exception.class, b.getBornFriend().getClazz());
        assertEquals(888, b.getBornFriend().getZarr()[0]);
        assertEquals('Z', b.getC());
        assertEquals(String.class, b.getClazz());
        assertEquals(new Character('z'), b.getCobj());
        assertEquals(123.456, b.getD(), 0);
        assertEquals(new Double(123.456), b.getDobj());
        assertEquals(12.34F, b.getF(), 0);
        assertEquals(new Float(12.34F), b.getFobj());
        assertEquals(MockBean4Codec.class, b.getFriend().getClazz());
        assertEquals(999, b.getI());
        assertEquals(new Integer(999), b.getIobj());
        assertEquals(8888888, b.getL());
        assertEquals(new Long(8888888), b.getLobj());
        assertEquals("Li Yang", b.getName());
        assertNull(b.getNill());
        assertEquals(55, b.getS());
        assertEquals(new Short((short) 55), b.getSobj());
        assertEquals(3, b.getZarr().length);
        assertEquals(3, b.getZarr()[0]);
        assertEquals(2, b.getZarr()[1]);
        assertEquals(1, b.getZarr()[2]);
        assertEquals(1, b.getZarrarr().length);
        assertEquals(3, b.getZarrarr()[0].length);
        assertEquals("6", b.getZarrarr()[0][0]);
        assertEquals("6", b.getZarrarr()[0][1]);
        assertEquals("6", b.getZarrarr()[0][2]);
    }

    public void testReadObject_StaticField() {
        XMLDecoder dec1 = new XMLDecoder(this.getClass().getResourceAsStream(
                "/xml/MockBean4StaticField_Original.xml"));
        MockBean4StaticField o1 = (MockBean4StaticField) dec1.readObject();

        XMLDecoder dec2 = new XMLDecoder(this.getClass().getResourceAsStream(
                "/xml/MockBean4StaticField.xml"));
        MockBean4StaticField o2 = (MockBean4StaticField) dec2.readObject();

        if (!o1.equals(o2)) {
            System.out
                    .println("Loading object with static field, original xml: "
                            + o1.getV());
            System.out.println("Loading object with static field, field xml: "
                    + o2.getV());
        }

        assertEquals(o1, o2);
    }

    public void testReadObject_Owner() {
        MockBean4Owner_Owner o1 = new MockBean4Owner_Owner();
        XMLDecoder dec1 = new XMLDecoder(this.getClass().getResourceAsStream(
                "/xml/MockBean4Owner_SetOwner.xml"), o1);
        MockBean4Owner_Target t1 = (MockBean4Owner_Target) dec1.readObject();

        assertEquals(1, o1.getV());
        assertEquals(o1, t1.getV());
    }

    public void testReadObject_Owner_WithWriteStatement() {
        MockBean4Owner_Owner o2 = new MockBean4Owner_Owner();
        XMLDecoder dec2 = new XMLDecoder(this.getClass().getResourceAsStream(
                "/xml/MockBean4Owner_SetOwnerWithWriteStatement.xml"), o2);
        MockBean4Owner_Target t2 = (MockBean4Owner_Target) dec2.readObject();

        assertEquals(999, o2.getV());
        assertEquals(o2, t2.getV());
    }

    public void testSetExceptionListener() {
        XMLDecoder dec = new XMLDecoder(new ByteArrayInputStream(xml123bytes));
        Object defaultL = dec.getExceptionListener();

        dec.setExceptionListener(null);
        assertSame(defaultL, dec.getExceptionListener());

        ExceptionListener newL = new MockExceptionListener();
        dec.setExceptionListener(newL);
        assertSame(newL, dec.getExceptionListener());
    }

    public void testSetExceptionListener_CatchException() {
        MockExceptionListener l = new MockExceptionListener();
        new XMLDecoder(XMLDecoderTest.class
                .getResourceAsStream("/xml/bad_int.xml"), null, l);
        assertTrue(l.size() > 0);
    }

    public void testSetOwner() {
        XMLDecoder dec = new XMLDecoder(new ByteArrayInputStream(xml123bytes));
        assertNull(dec.getOwner());

        String owner = "owner";
        dec.setOwner(owner);
        assertSame(owner, dec.getOwner());

        dec.setOwner(null);
        assertNull(dec.getOwner());
    }

    /*
     * Class under test for void XMLDecoder(java.io.InputStream)
     */
    public void testXMLDecoderInputStream() {
        XMLDecoder dec = new XMLDecoder(new ByteArrayInputStream(xml123bytes));
        assertNull(dec.getOwner());
        assertNotNull(dec.getExceptionListener());
    }

    /*
     * Class under test for void XMLDecoder(java.io.InputStream,
     * java.lang.Object)
     */
    public void testXMLDecoderInputStreamObject() {
        String owner = "owner";
        XMLDecoder dec = new XMLDecoder(new ByteArrayInputStream(xml123bytes),
                owner);
        assertSame(owner, dec.getOwner());
        assertNotNull(dec.getExceptionListener());
    }

    /*
     * Class under test for void XMLDecoder(java.io.InputStream,
     * java.lang.Object, java.beans.ExceptionListener)
     */
    public void testXMLDecoderInputStreamObjectExceptionListener() {
        String owner = "owner";
        MockExceptionListener l = new MockExceptionListener();
        XMLDecoder dec = new XMLDecoder(new ByteArrayInputStream(xml123bytes),
                owner, l);
        assertSame(owner, dec.getOwner());
        assertSame(l, dec.getExceptionListener());
    }

    /**
     * The test checks the code generation for XML from Test1.xml
     */
    public void testDecodeLinkedList() throws Exception {
        decode("xml/Test1.xml");
    }

    /**
     * The test checks the code generation for XML from Test2.xml
     */
    public void testDecodePrimitiveArrayByLength() throws Exception {
        decode("xml/Test2.xml");
    }

    /**
     * The test checks the code generation for XML from Test3.xml
     */
    public void testDecodePrimitiveArrayByElements() throws Exception {
        decode("xml/Test3.xml");
    }

    /**
     * The test checks the code generation for XML from Test4.xml
     */
    public void testDecodeObjectArrayByLength() throws Exception {
        decode("xml/Test4.xml");
    }

    /**
     * The test checks the code generation for XML from Test5.xml
     */
    public void testDecodeObjectArrayByElements() throws Exception {
        decode("xml/Test5.xml");
    }

    /**
     * The test checks the code generation for XML from Test6.xml
     */
    public void testDecodeReference() throws Exception {
        decode("xml/Test6.xml");
    }

    /**
     * The test checks the code generation for XML from Test7.xml
     */
    public void testDecodeStringArray() throws Exception {
        decode("xml/Test7.xml");
    }

    /*
     * The test checks the code generation for XML from MainTest.xml
     * 
     * public void testMain() { decode("xml/MainTest.xml"); }
     */

    /**
     * 
     */
    public static Test suite() {
        return new TestSuite(XMLDecoderTest.class);
    }

    /**
     * 
     */
    private void decode(String resourceName) throws Exception {
        XMLDecoder d = null;
        try {
            Introspector.setBeanInfoSearchPath(new String[] {});
            d = new XMLDecoder(new BufferedInputStream(ClassLoader
                    .getSystemClassLoader().getResourceAsStream(resourceName)));
            while (true) {
                d.readObject();
            }
        } catch (ArrayIndexOutOfBoundsException aibe) {
            assertTrue(true);
        } finally {
            if (d != null) {
                d.close();
            }
        }
    }
}
