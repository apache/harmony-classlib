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

package ar.org.fitc.test.util.crypto;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Genera una objeto conteniendo todas las combinaciones de ciphers válidos para todos los proveedores
 * que existen declarados
 * @author odemo
 */

public class AllTransformation {

    protected Cipher c = null;
    protected Mac m = null;
    protected SecretKey key;
    protected ArrayList<Key> keys = new ArrayList<Key>();
    protected KeyGenerator kg;
    protected int n = -1;
    protected ArrayList<String> provider = new ArrayList<String>();
    protected Provider[] providers = null;
    protected int totalTransformations = -1;
    protected ArrayList<String> transformation = new ArrayList<String>();
    protected boolean log = false;

    public AllTransformation(String msg) {
        Provider[] p = new Provider[Security.getProviders().length];
        p = Security.getProviders();
        loadArrayLists(msg,p);
        init();
    }

    public AllTransformation(String msg,String prov) {
        Provider[] p = new Provider[1];
        p[0] = Security.getProvider(prov);
        if (p[0] == null) {
            System.err.println("Provider "+prov+" not found");
            System.exit(1);
        }
        loadArrayLists(msg,p);
        init();
    }

    protected void init() {
        totalTransformations = transformation.size();

        if ((provider.size()+transformation.size()+keys.size())/3 != keys.size()) {
            throw new RuntimeException("Internal ArrayList integrity check FAILED!");
        }

        if (log) {
            System.out.println("Provider ArrayList: "+provider.size());
            System.out.println("Transformation ArrayList: "+transformation.size());
            System.out.println("Key ArrayList: "+keys.size());
        }

    }

    protected void loadArrayLists(String type,Provider[] prov) {
//        if (type != "Cipher" && type != "Mac" && type != "KeyGenerator") {
//            throw new NoSuchTypeException("Service type not supported");
//        }
        @SuppressWarnings("unused")
        String[] mode = {"/NONE", "/CBC", "/CFB", "/ECB", "/OFB", "/PCBC"};
        String[] padding = {"/ISO10126Padding", "/ISO10126Padding", "/PKCS5Padding", ""};

        for (int i = 0; i < prov.length; i++) {
            Provider p = prov[i];
            for (Object o : p.keySet()) {
                if (o instanceof String) {
                    String s = (String) o;
                    if (s.startsWith(type+".")) {
                        s = s.replaceFirst(type+".", "");
                        try {
                            if (type == "Cipher") {
                                c = Cipher.getInstance(s,p);
                                kg = KeyGenerator.getInstance(s);
                                key = kg.generateKey();
                                c.init(Cipher.ENCRYPT_MODE,key);
                            } else if (type == "Mac") {
                                m = Mac.getInstance(s);

                            }
                            check();
                            //add it ...
                            provider.add(p.getName());
                            transformation.add(s);
                            keys.add(key);
                            if(log) { System.out.println(p.getName()+" -> "+s); }
                        } catch (Throwable e) { }

                        if (type == "Cipher") {
                            for(int k=0; k < mode.length; k++) {
                                for(int j=0; j < padding.length; j++) {
                                    try {
                                        if (type == "Cipher") {
                                            c = Cipher.getInstance(s + mode[k] + padding[j], p);
                                            kg = KeyGenerator.getInstance(s);
                                            key = kg.generateKey();
                                            c.init(Cipher.ENCRYPT_MODE,key);
                                        }
                                        check();
                                        //add cipher ...
                                        transformation.add(s + mode[k] + padding[j]);
                                        provider.add(p.getName());
                                        keys.add(key);
                                        if(log) { System.out.println(p.getName()+" -> "+s+mode[k]+padding[j]); }
                                    } catch (Throwable e) { }
                                }
                            }
                        }
                    }
                }

            }
        }

    }

    protected void check() throws Exception { }

    public String getAlgorithm() {
        if (c.getAlgorithm().indexOf('/') != -1) {
            return c.getAlgorithm().substring(0,c.getAlgorithm().indexOf('/'));
        } else {
            return c.getAlgorithm();
        }
    }

    public String getMode() {
        if (transformation.get(n).indexOf('/') != -1) {
            return transformation.get(n).substring(transformation.get(n).indexOf('/')+1,transformation.get(n).lastIndexOf('/'));
        } else {
            return "";
        }
    }

    public String getPadding() {
        if (c.getAlgorithm().indexOf('/') != -1) {
            return transformation.get(n).substring(transformation.get(n).lastIndexOf('/')+1,transformation.get(n).length());
        } else {
            return "";
        }

    }

    public Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        return Cipher.getInstance(getTransformation());
    }

    public Key getKeyObject() {
        return keys.get(n);
    }

    public String getProvider() {
        return c.getProvider().getName();
    }

    public int getSize() {
        return totalTransformations;
    }

    public String getTransformation() {
        return c.getAlgorithm();
    }

    public boolean hasNext() {
        if (n == transformation.size()-1) {
            return false;
        } else {

            try {
                c = Cipher.getInstance(transformation.get(++n));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    public void reset() {
        this.n = -1;
    }
}