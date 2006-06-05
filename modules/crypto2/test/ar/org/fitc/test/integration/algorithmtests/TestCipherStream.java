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

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

final class TestCipherStream extends Test {
    private static final String name = "CipherStreams";

    private Cipher c, d;

    private final String s = "ThisIsASecretSecretMessageOfNull";

    private String algo;

    private int KEY_SIZE;

    protected TestCipherStream(String algo, int KEY_SIZE) {
        super(name);
        this.algo = algo;
        this.KEY_SIZE = KEY_SIZE;
    }

    protected TestCipherStream() {
        super(name);
        this.algo = "DES";
        this.KEY_SIZE = 56;
    }

    protected void doIt() throws Exception {
        init();
        beginTest("COS(file, ENCRYPT) -> CIS(file, DECRYPT)");
        passIf(test1(c, d, s));
        beginTest("CIS(CIS(file, ENCRYPT), DECRYPT)");
        passIf(test2(c, d, s));
        beginTest("COS(COS(file, DECRYPT), ENCRYPT)");
        passIf(test3(c, d, s));

        return;
    }

    private void init() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(algo, provider);
        kg.init(KEY_SIZE);
        SecretKey sk = kg.generateKey();

        algo = algo + "/ECB/PKCS5Padding";
        c = Cipher.getInstance(algo, provider);
        d = Cipher.getInstance(algo, provider);

        c.init(Cipher.ENCRYPT_MODE, sk);
        d.init(Cipher.DECRYPT_MODE, sk);
        return;
    }

    private boolean test1(Cipher c, Cipher d, String s) throws Exception {
        start = System.nanoTime();
        File file = new File("slask.tmp");
        if (file.exists())
            file.delete();
        byte[] b = s.getBytes();

        FileOutputStream fos = new FileOutputStream(file);
        CipherOutputStream cos = new CipherOutputStream(fos, c);

        cos.write(b);
        cos.flush();
        cos.close();
        fos.close();

        FileInputStream fis = new FileInputStream(file);
        CipherInputStream cis = new CipherInputStream(fis, d);

        byte[] rb = new byte[1024];
        StringBuffer sb = new StringBuffer();
        int i, a = 0;
        while ((i = cis.read(rb)) != -1) {
            sb.append(new String(rb, 0, i));
            a += i;
        }
        cis.close();
        fis.close();
        file.delete();
        finish = System.nanoTime();
        return sb.toString().equals(s);
    }

    private boolean test2(Cipher c, Cipher d, String s) throws Exception {
        start = System.nanoTime();
        FileWriter fw;
        FileReader fr;
        FileInputStream fis;
        FileOutputStream fos;
        CipherInputStream cis1, cis2;
        File file1 = new File("slask1.tmp");
        if (file1.exists())
            file1.delete();
        File file2 = new File("slask2.tmp");
        if (file2.exists())
            file2.delete();
        int i;

        fw = new FileWriter(file1);
        fw.write(s);
        fw.close();

        fis = new FileInputStream(file1);
        cis1 = new CipherInputStream(fis, c);
        cis2 = new CipherInputStream(cis1, d);
        fos = new FileOutputStream(file2);
        byte[] b = new byte[8];
        while ((i = cis2.read(b)) != -1)
            fos.write(b, 0, i);

        cis1.close();
        cis2.close();
        fis.close();
        fos.close();

        fr = new FileReader(file2);
        char[] rb = new char[1024];
        StringBuffer sb = new StringBuffer();
        while ((i = fr.read(rb)) != -1)
            sb.append(new String(rb, 0, i));

        fr.close();
        file1.delete();
        file2.delete();
        finish = System.nanoTime();
        return sb.toString().equals(s);
    }

    private boolean test3(Cipher c, Cipher d, String s) throws Exception {
        start = System.nanoTime();
        FileWriter fw;
        FileReader fr;
        FileInputStream fis;
        FileOutputStream fos;
        CipherOutputStream cos1, cos2;
        File file1 = new File("slask1.tmp");
        if (file1.exists())
            file1.delete();
        File file2 = new File("slask2.tmp");
        if (file2.exists())
            file2.delete();
        int i;

        fw = new FileWriter(file1);
        fw.write(s);
        fw.close();

        fis = new FileInputStream(file1);
        fos = new FileOutputStream(file2);
        cos1 = new CipherOutputStream(fos, d);
        cos2 = new CipherOutputStream(cos1, c);
        byte[] b = new byte[8];
        while ((i = fis.read(b)) != -1)
            cos2.write(b, 0, i);

        cos2.flush();
        cos2.close();
        cos1.close();
        fos.close();
        fis.close();

        fr = new FileReader(file2);
        char[] rb = new char[8];
        StringBuffer sb = new StringBuffer();
        while ((i = fr.read(rb)) != -1)
            sb.append(new String(rb, 0, i));

        fr.close();
        file1.delete();
        file2.delete();
        finish = System.nanoTime();
        return sb.toString().equals(s);
    }
}
