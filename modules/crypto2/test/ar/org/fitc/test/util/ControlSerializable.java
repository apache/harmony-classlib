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

import java.io.IOException;
import java.io.Serializable;

public class ControlSerializable implements Serializable {

    private static final long serialVersionUID = 1L;
    public final static int 
        readObjectIOException =0,
        readObjectClassNotFoundException = 1,
        writeObjectIOException = 3;
    public transient int whatTo = 0;
    public int a;
    public int b;
    public byte c = 0;
    
    public ControlSerializable(int arg0, int arg1, int what) {
        this(arg0, arg1);
        whatTo = what;
        // TODO Auto-generated constructor stub
    }
    
    public ControlSerializable(int arg0, int arg1) {
        a = arg0;
        b = arg1;
        // TODO Auto-generated constructor stub
    }

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        if (whatTo == writeObjectIOException)
            throw new IOException("For testing purpose");
        stream.defaultWriteObject( );
        stream.writeInt(whatTo);
    }
    
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject( );
        whatTo = stream.readInt();
        if (whatTo == readObjectIOException)
            throw new IOException("For testing purpose");
        if (whatTo == readObjectClassNotFoundException) 
            throw new ClassNotFoundException("For testing purpose");
        
    }

}
