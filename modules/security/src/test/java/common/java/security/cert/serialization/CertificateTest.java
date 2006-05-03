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
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import org.apache.harmony.security.cert.MyCertificate;

import junit.framework.TestCase;


/**
 * Tests for <code>Certificate</code> serialization
 * 
 */
public class CertificateTest extends TestCase {
    /**
     * <code>Certificate</code> type to be created during testing
     */
    private static final String certType = "X.509";
    /**
     * Input file name used for <code>Certificate</code> instance generation
     */
    private static final String certFileName =
        org.apache.harmony.security.support.TestUtils.TEST_ROOT +
        "java/security/cert/serialization/Certificate." +
        certType;
    /**
     * Golden file containing serial <code>Certificate</code> stream
     */
    private static final String serializedCertFileName = certFileName + ".dat";

    /**
     * Constructor for CertificateTest.
     * @param name
     */
    public CertificateTest(String name) {
        super(name);
    }

    //
    // Tests
    //

    /**
     * Test #1 for <code>Certificate</code> serialization/deserialization.<br>
     * ByteArray streams used.
     *
     * Assertion: deserialized object must match to original one
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
        Certificate c1 = cf.generateCertificate(new FileInputStream(certFileName));
        // This testcase uses ByteArray streams
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // Serialize cert
        serialize(c1, bos);
        // Deserialize it
        Certificate c2 = deserialize(new ByteArrayInputStream(bos.toByteArray()));
        // compare both Certificates
        assertTrue(c1.equals(c2) && c2.equals(c1));
    }

    /**
     * Test #2 for <code>Certificate</code> serialization/deserialization.<br>
     * File input stream with golden content used.
     *
     * Assertion: deserialized object must match to original one
     * 
     * @throws CertificateException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws IOException
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
        Certificate c1 = cf.generateCertificate(new FileInputStream(certFileName));
        // Deserialize certificate from golden file
        Certificate c2 = deserialize(new FileInputStream(serializedCertFileName));
        // compare both Certificates
        assertTrue(c1.equals(c2) && c2.equals(c1));
    }

    /**
     * Test for <code>Certificate.CertificateRep.readResolve()</code> method<br>
     *
     * Assertion: ObjectStreamException if a <code>CertPath</code>
     * could not be constructed
     * 
     * @throws CertificateException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public final void testSerialization03()
        throws CertificateException,
               IOException,
               ClassNotFoundException {
        boolean passed = false;
        // Create object to be serialized
        Certificate c1 = new MyCertificate("DUMMY", new byte[] {(byte)0, (byte)1});
        // This testcase uses ByteArray streams
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // Serialize cert
        serialize(c1, bos);
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
        Certificate c1 = new MyCertificate("DUMMY", null);
        // This testcase uses ByteArray streams
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // Try to serialize cert
        try {
            serialize(c1, bos);
        } catch (Exception e) {
        	System.out.println(getName() + ": " + e);
            // OSE and NPE are possible
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
     * @param c
     * <code>Certificate</code> object to be serialized
     * @param os
     * Serialization <code>OutputStream</code> for <code>c</code>
     */
    private void serialize(Certificate c, OutputStream os)
            throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        try {
            // Serialize it to the os
            oos.writeObject(c);
            oos.flush();
        } finally {
            oos.close();
        }
    }
    /**
     * Test case end template - deserialization and checks
     *
     * @param is
     * <code>Certificate</code> object deserialization <code>InputStream</code>
     */
    private Certificate deserialize(InputStream is)
            throws IOException,
                   ClassNotFoundException {
        // deserialize our object
        ObjectInputStream ois = new ObjectInputStream(is);
        Certificate cert = null;
        try {
            cert = (Certificate)ois.readObject();
            return cert;
        } finally {
            ois.close();
        }
    }

}
