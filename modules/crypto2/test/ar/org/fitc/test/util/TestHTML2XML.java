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

package ar.org.fitc.test.util;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import org.w3c.tidy.Tidy;

public class TestHTML2XML {
    private String url;

    private String outFileName;

    private String errOutFileName;

    public TestHTML2XML(String url, String outFileName, String errOutFileName) {
        this.url = url;
        this.outFileName = outFileName;
        this.errOutFileName = errOutFileName;
    }

    public void convert() {
        URL u;
        BufferedInputStream in;
        FileOutputStream out;

        Tidy tidy = new Tidy();

        tidy.setXmlOut(true);

        try {
            tidy.setErrout(new PrintWriter(new FileWriter(errOutFileName),true));
            u = new URL(url);
            in = new BufferedInputStream(u.openStream());
            out = new FileOutputStream(outFileName);
            tidy.parse(in, out);
            in.close();
            out.close();

        } catch (IOException e) {
            System.out.println(this.toString() + e.toString());
        }
    }

    public static void main(String[] args) {
//        TestHTML2XML t = new TestHTML2XML(args[0], args[1], args[2]);
        TestHTML2XML t = new TestHTML2XML("file:///mnt/intel/Testing/Ejecucion%20de%20Pruebas/Crypto/Linux/profile.class.Crypto.ITC.html","out.xml","err");
        t.convert();
    }
}