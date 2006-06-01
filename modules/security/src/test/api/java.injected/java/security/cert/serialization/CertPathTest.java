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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package java.security.cert.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import org.apache.harmony.security.tests.support.cert.MyCertPath;

import junit.framework.TestCase;


/**
 * Tests for <code>CertPath</code> serialization
 * 
 */
public class CertPathTest extends TestCase {
    /**
     * <code>Certificate/CertPath</code> type to be created during testing
     */
    private static final String certType = "X.509";
    /**
     * <code>CertPath</code> encoding name, defines input file names
     * used during testing
     */
    private static final String certPathEncoding = "PkiPath";
    /**
     * Input file name used for <code>CertPath</code> instance generation
     */
    private static final String certPathFileName =
        org.apache.harmony.security.tests.support.TestUtils.TEST_ROOT +
        "java/security/cert/serialization/CertPath." +
        certPathEncoding;
    /**
     * Golden file containing serial <code>CertPath</code> stream
     */
    private static final String serializedCertPathFileName =
        certPathFileName + ".dat";

    /**
     * Constructor for CertPathTest.
     * @param name
     */
    public CertPathTest(String name) {
        super(name);
    }

    //
    // Tests
    //

    /**
     * Test #1 for <code>CertPath</code> serialization/deserialization.<br>
     * ByteArray streams used.
     *
     * Assertion: original and deserialized objects must be equal
     * 
     * @throws CertificateException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public final void testSerialization01()
        throws CertificateException,
               IOException,
               ClassNotFoundException {
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance(certType);
        } catch (CertificateException e) {
            fail(getName() +
                    ": PASSED (could not create CertificateFactory): " + e);
            return;
        }
        // Create object to be serialized
        CertPath cp1 = cf.generateCertPath(new FileInputStream(certPathFileName));
        // This testcase uses ByteArray streams
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // Serialize cert
        serialize(cp1, bos);
        // Deserialize it
        CertPath cp2 = deserialize(new ByteArrayInputStream(bos.toByteArray()));
        // compare both Certificates
        assertTrue(cp1.equals(cp2) && cp2.equals(cp1));
    }

    /**
     * Test #2 for <code>CertPath</code> serialization/deserialization.<br>
     * File input stream with golden content used.
     *
     * Assertion: original and deserialized objects must be equal
     * 
     * @throws CertificateException
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public final void testSerialization02()
        throws CertificateException,
               FileNotFoundException,
               IOException,
               ClassNotFoundException {
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance(certType);
        } catch (CertificateException e) {
            fail(getName() +
                    ": PASSED (could not create CertificateFactory): " + e);
            return;
        }
        // Create object to be compared to deserialized one
        CertPath cp1 = cf.generateCertPath(new FileInputStream(certPathFileName));
        // Deserialize CertPath from golden file
        CertPath cp2 = deserialize(new FileInputStream(serializedCertPathFileName));
        // compare both Certificates
        assertTrue(cp1.equals(cp2) && cp2.equals(cp1));
    }

    /**
     * Test for <code>CertPath.CertPathRep.readResolve()</code> method<br>
     *
     * Assertion: ObjectStreamException if a <code>CertPath</code>
     * could not be constructed
     * 
     * @throws IOException
     */
    public final void testSerialization03()
        throws IOException {
        boolean passed = false;
        // Create object to be serialized
        CertPath cp1 = new MyCertPath(new byte[] {(byte)0, (byte)1});
        // This testcase uses ByteArray streams
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // Serialize cert
        serialize(cp1, bos);
        // try to deserialize it
        try {
            deserialize(new ByteArrayInputStream(bos.toByteArray()));
        } catch (Exception e) {
        	System.out.println(getName() + ": " + e);
            if (e instanceof ObjectStreamException) {
                passed = true;
            }
        }
        // check that exception has been thrown
        assertTrue(passed);
    }

    /**
     * Test for <code>writeReplace()</code> method<br>
     * ByteArray streams used.
     * 
     * @throws CertificateException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public final void testSerialization04()
        throws CertificateException,
               IOException,
               ClassNotFoundException {
        boolean passed = false;
        // Create object to be serialized
        // set encoded form to null
        CertPath cp1 = new MyCertPath(null);
        // This testcase uses ByteArray streams
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // Try to serialize cert
        // writeReplace() must fail with exception
        // (both OSE and NPE are possible)
        try {
            serialize(cp1, bos);
        } catch (Exception e) {
        	System.out.println(getName() + ": " + e);
            // both OSE and NPE are possible
            passed = true;
        }
        assertTrue(passed);
    }

    //
    // private stuff
    //

    /**
     * Test case start template - serialization
     *
     * @param cp
     * <code>CertPath</code> object to be serialized
     * @param os
     * Serialization <code>OutputStream</code> for <code>cp</code>
     */
    private void serialize(CertPath cp, OutputStream os)
            throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        try {
            // Serialize it to the os
            oos.writeObject(cp);
            oos.flush();
        } finally {
            oos.close();
        }
    }
    /**
     * Test case end template - deserialization and checks
     *
     * @param os
     * <code>CertPath</code> deserialization <code>InputStream</code>
     */
    private CertPath deserialize(InputStream is)
            throws IOException,
                   ClassNotFoundException {
        // deserialize our object
        ObjectInputStream ois = new ObjectInputStream(is);
        CertPath cp = null;
        try {
            cp = (CertPath)ois.readObject();
            return cp;
        } finally {
            ois.close();
        }
    }

}
