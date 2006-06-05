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

package ar.org.fitc.test.integration.crypto.util;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestBlockFiller extends TestCase {

    InputStream vin, in2;

    OutputStream vout, out2;

    ObjectInputStream in, oin2;

    ObjectOutputStream out, oout2;

    public TestBlockFiller(String name) {
        super(name);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestBlockFiller.class);
    }

    public static Test suite() {
        return new TestSuite(TestBlockFiller.class);
    }

    protected void setUp() throws Exception {

    }

    protected void tearDown() {

    }

    public void testRead() {
        byte[] data = {12,45};
        int result = 0;
        byte[] newdata = new byte[2];
        try {
            vin = new PipedInputStream();
            vout = new PipedOutputStream((PipedInputStream) vin);
            out = new ObjectOutputStream(new BlockFillerOutputStream(vout, 8));
            in = new ObjectInputStream(new BlockFillerInputStream(vin, 8));
            out.write(data);
            result = in.read(newdata);
            System.out.println(result);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(e);
            fail("Failed with:" + e);
        }
        assertEquals(newdata,data);
    }

}