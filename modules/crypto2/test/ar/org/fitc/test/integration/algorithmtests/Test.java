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

package ar.org.fitc.test.integration.algorithmtests;

import java.security.Security;

public abstract class Test {
    
    private static final String CLASS_PREFIX = "ar.org.fitc.test.integration.algorithmtests.Test",
            VERSION = "1.0";

    private static boolean verbose = true;

    protected long start;

    protected long finish;

    protected static String provider;

    private static String[] p = { "BC", "SunJCE", "CryptixCrypto" };

    private static String[] TESTS = { "DES", "3DES", "RSA", "RSA_ECB_PKCS1", "Blowfish",
            "ModeAndPadding", "AlgorithmParameters",
            "CipherStream", "SealedObject" };

    private static String currentTest;

    private static boolean error;

    public static void main(String[] argv) {
        long TotalStart = System.currentTimeMillis();
        print("Cryptographic Service Provider Tester - " + VERSION);
        print("");
        print("Platform: JDK " + System.getProperty("java.version")
                + " (" + System.getProperty("java.vendor") + ") on "
                + System.getProperty("os.name") + " ("
                + System.getProperty("os.version") + ", "
                + System.getProperty("os.arch") + ")");
        print("Classpath: " + System.getProperty("java.class.path"));
        print("");

        for (int i = 0; i < p.length; i++) {
            if (Security.getProvider(p[i]) == null) {
                print("Provider " + p[i] + " not found ");
                error = true;
            }
        }

        if (error) {
            print("Tests cannot continue. Exiting...");
            System.exit(2);
        }

        try {
            // java.security.Security.addProvider(new
            // cryptix.jce.provider.CryptixCrypto());
            for (int i = 0; i < p.length; i++) {
                print("\n--- Testing provider: " + p[i] + " --- \n");
                for (int j = 0; j < TESTS.length; j++) {
                    Test t = getTest(TESTS[j], p[i]);
                    t.doIt();
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Test not found.");
            e.printStackTrace();
            System.exit(1);
        } catch (Throwable t) {
            System.out.println("Failed");
            t.printStackTrace();
            System.exit(1);
        }
        long TotalFinish = System.currentTimeMillis();

        print("Total runtime: " + (TotalFinish - TotalStart) / 1000
                + " Secs to run");
        System.exit(10);
    }

    protected Test(String name) {
        currentTest = name;
    }

    private static void print(String s) {
        if (verbose)
            System.out.println(s);
    }

    private static Test getTest(String name, String prov) throws Exception {
        Class c = Class.forName(CLASS_PREFIX + name);
        Object o = c.newInstance();
        ((Test) o).setProvider(prov);
        return (Test) o;
    }

    protected void beginTest(String s) {
        System.out.print("Testing " + currentTest + " (" + s + ")... ");
    }

    protected void passIf(boolean success) {
        if (!success) {
            System.out.println("Test failed");
        } else {
            System.out.println("Ok");
        }
        System.out.print(" Time consumed: " + (finish - start) / 1000 + " mSecs \n");
    }

    protected abstract void doIt() throws Exception;

    @SuppressWarnings("static-access")
    public void setProvider(String provider) {
        this.provider = provider;
    }
}
