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

package org.apache.harmony.security.tests.java.security.cert.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.security.cert.CertPath;
import java.security.cert.CertificateFactory;

import junit.framework.TestCase;

import org.apache.harmony.security.tests.support.cert.MyCertPath;
import org.apache.harmony.testframework.serialization.SerializationTest;

/**
 * Tests for <code>CertPath</code> serialization
 */
public class CertPathTest extends TestCase {

    //Certificate/CertPath type to be created during testing
    private static final String certType = "X.509";

    // CertPath encoding name, defines input file names used during testing
    private static final String certPathEncoding = "PkiPath";

    /**
     * Input file name used for <code>CertPath</code> instance generation
     */
    private static final String certPathFileName =
        org.apache.harmony.security.tests.support.TestUtils.TEST_ROOT +
        "java/security/cert/serialization/CertPath." +
        certPathEncoding; //TODO move me to other folder

    //
    // Tests
    //

    /**
     * @tests serialization/deserialization.
     */
    public void testSerializationSelf() throws Exception {

        CertificateFactory cf = CertificateFactory.getInstance(certType);

        CertPath certPath = cf.generateCertPath(new FileInputStream(
                certPathFileName));

        SerializationTest.verifySelf(certPath);
    }

    /**
     * @tests serialization/deserialization compatibility with RI.
     */
    public void testSerializationCompatibility() throws Exception {

        CertificateFactory cf = CertificateFactory.getInstance(certType);

        CertPath certPath = cf.generateCertPath(new FileInputStream(
                certPathFileName));

        SerializationTest.verifyGolden(this, certPath);
    }

    /**
     * Test for <code>CertPath.CertPathRep.readResolve()</code> method<br>
     *
     * Assertion: ObjectStreamException if a <code>CertPath</code>
     * could not be constructed
     */
    public final void testCertPathRep_readResolve() throws Exception {

        // Create object to be serialized
        CertPath cp1 = new MyCertPath(new byte[] {(byte)0, (byte)1});
        // This testcase uses ByteArray streams
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // Serialize cert
        serialize(cp1, bos);
        // try to deserialize it
        try {
            deserialize(new ByteArrayInputStream(bos.toByteArray()));
            fail("No expected ObjectStreamException");
        } catch (ObjectStreamException e) {
        }
    }

    /**
     * Test for <code>writeReplace()</code> method<br>
     * ByteArray streams used.
     */
    public final void testWriteReplace() throws Exception {
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
            fail("No exception");
        } catch (ObjectStreamException e) {
        } catch (NullPointerException e) {
        }
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
        try {
            return (CertPath)ois.readObject();
        } finally {
            ois.close();
        }
    }
}
