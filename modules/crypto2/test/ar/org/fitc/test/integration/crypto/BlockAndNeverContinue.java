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

package ar.org.fitc.test.integration.crypto;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

import ar.org.fitc.test.integration.crypto.util.K;

/**
 * A command line test that never end.
 *
 * BlockAndNeverContinue is a simple case where a block cipher is used to encrypt stream.
 * If OutputStream write less byte than a Cipher's block size, the InputStream can't read.
 *
 * It is recomend to use a stream cipher to instance a CipherStream.
 *
 */

public class BlockAndNeverContinue {
    
    public static void main() throws Exception {
        String[] args = new String[] { "a", "e", "hola como estas?", "mama" };
        InputStream in = new PipedInputStream();
        OutputStream out = new PipedOutputStream((PipedInputStream) in);
        
        
        
        Cipher ci = Cipher.getInstance("DES");
        ci.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3,
                4, 5, 6, 7, 8 }));
        out = new CipherOutputStream(out, ci);
        
        ci = Cipher.getInstance("DES");
        ci.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3,
                4, 5, 6, 7, 8 }));
        in = new CipherInputStream(in, ci);
        
        
        PrintStream outP = new PrintStream(out);
        BufferedReader inP = new BufferedReader(new InputStreamReader(in));
        
        for (int i = 0; i < args.length; i++) {
            outP.println(args[i]);
            System.out.println(inP.readLine());
            
        }
        out.close();
        String i = inP.readLine();
        while (i != null) {
            System.out.println(i);
            i = inP.readLine();
        }
        
    }
    
    public static void main(String[] args) {
        System.out.println("Start proces");
        try {
            main();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Stop proces");
    }
    
}
