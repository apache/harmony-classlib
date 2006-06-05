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

package ar.org.fitc.test.integration.crypto.agreement;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.security.PublicKey;


/**
 * Multi actores DHKeyAgreement test. 
 *
 */
public class StreamDHKeyAgreement extends DHKeyAgreement {

   /**
    * actors numbre.
    */
    public static int N = 8;
    
    
    public static void main(String[] argv) {
        try {
            main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Instance N StreamDhKeyAgreement and N thread and N pipedStream. 
     * All together make a good party.
     * 
     * At the end, check that all secret key are equals.
     */
    public static void main() throws Exception {
        
            DHKeyAgreement[] dhka = new DHKeyAgreement[N];
            Thread[] t = new Thread[N];
            
            InputStream in1, in2;
            OutputStream out1, out2;
            ObjectInputStream oin1, oin2;
            ObjectOutputStream oout1, oout2;

            in1 = new PipedInputStream();
            out1 = new PipedOutputStream((PipedInputStream) in1);
            oout1 = new ObjectOutputStream(out1);
            oin1 = new ObjectInputStream(in1);
            
            
            
            for (int i=0; i < N-1; i++) {
                in2 = new PipedInputStream();
                out2 = new PipedOutputStream((PipedInputStream) in2);
                oout2 = new ObjectOutputStream(out2);
                oin2 = new ObjectInputStream(in2);
                
                dhka[i] = new StreamDHKeyAgreement(N, oin1, oout2);
                oin1 = oin2;
                t[i] = new Thread(dhka[i]);
                t[i].start();
            }
            dhka[N-1] = new StreamDHKeyAgreement(N, oin1, oout1);
            t[N-1] = new Thread(dhka[N-1]);
            t[N-1].start();
            for (int i=0; i < N; i++) {
                t[i].join();
            }
            for (int i=0; i < N-1; i++) {
                byte[] code12 = dhka[i].getResult();
                byte[] code21 = dhka[i+1].getResult();
                for (int j = 0; j < code12.length; j++) {
                    if (code12[j] != code21[j]) {
                        throw new Exception("no same Secret generate "+i);
                    }
                }
            } 
       
       
    }

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public StreamDHKeyAgreement(int N, ObjectOutputStream out, ObjectInputStream in) {
        this(N, in, out);
    }

    /**
     *  
     * @param N numbre of actors where in the party 
     * @param in stream where read keys.
     * @param out stream where write keys.
     */

    public StreamDHKeyAgreement(int N, ObjectInputStream in, ObjectOutputStream out) {
        super(N);
        this.in = in;
        this.out =  out;

    }

    
  

    /**
     * Send Key by out. 
     */
    @Override
    public void sendKey(PublicKey key) throws Exception {
        out.writeObject(key);
    }

    /**
     * read key by in 
     */
    @Override
    public PublicKey recieveKey() throws Exception {
        return (PublicKey) in.readObject();
    }

    
}
