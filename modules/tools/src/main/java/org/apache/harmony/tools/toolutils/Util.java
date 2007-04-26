package org.apache.harmony.tools.toolutils;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Util {

    /* A default encoding for console messages */
    static String CONSOLE_ENCODING = System.getProperty("console.encoding", //$NON-NLS-1$
            "ISO8859_1"); //$NON-NLS-1$

    public static PrintWriter getDefaultWriter(OutputStream out){
        OutputStreamWriter osw;
        try {
            osw = new OutputStreamWriter(out, CONSOLE_ENCODING);
        } catch (UnsupportedEncodingException e) {
            osw = new OutputStreamWriter(out);
        }
        return new PrintWriter(osw);
    }

}
