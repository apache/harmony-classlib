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

package javax.crypto;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Cipher;

/**
 * 
 * 
 * @author Diego Raul Mercado 
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class CipherOutputStream extends FilterOutputStream { 
	private Cipher cipher;
	
    /** Indicates if this input stream has been closed */
    private boolean isClosed = false;
    
    /** @ar.org.fitc.spec_ref */
    protected CipherOutputStream(OutputStream os) {
    	this(os, new NullCipher());
    }

    /** @ar.org.fitc.spec_ref */
	public CipherOutputStream(OutputStream os, Cipher c) {
		super(os);
        cipher = c;
    }

    /** @ar.org.fitc.spec_ref */
    public void close() throws IOException {
        if (!isClosed) {
            try {
                byte[] b = cipher.doFinal();

                if (b != null && b.length > 0) {
                    out.write(b);
                }
                out.flush();
            } catch (IllegalBlockSizeException e) {
            } catch (BadPaddingException e) {
            } catch (IOException e) { // could be raised in flush() call
            } finally {
                out.close();
                isClosed = true;
            }
        }
    }

    /** @ar.org.fitc.spec_ref */
    public void flush() throws IOException {
        out.flush();
    }

    /** 
     * @ar.org.fitc.spec_ref    
     * <code>NullPointerException</code> is thrown if <code>b</code> is null
     */
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    /**
     * @ar.org.fitc.spec_ref
     * <code>IllegalArgumentException</code> is thrown if <code>b</code> is 
     * null, <code>off</code> > <code>b.length</code>, <code>off</code> is 
     * negative, <code>off</code> + <code>len</code> > <code>b.length</code>
     */
    public void write(byte[] b, int off, int len) 
    		throws IOException {
    		byte[] buff = cipher.update(b, off, len);

        if (buff != null && buff.length > 0) {
            out.write(buff);
        }
    }
 
    /** @ar.org.fitc.spec_ref */
    public void write(int b) throws IOException {
        byte[] buff = new byte[1];
        buff[0] = (byte) b;
        write(buff, 0, 1);
    }
}
