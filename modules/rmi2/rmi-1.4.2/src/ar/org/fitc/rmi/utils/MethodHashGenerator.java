/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package ar.org.fitc.rmi.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * This class provides the methods that compute the hash for a specific method
 * 
 * @author Horacio de Oro
 */
public final class MethodHashGenerator {
    private static MessageDigest md;
    
    static {
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException nsae) {
            throw new InternalError("SHA1 not implemented: " + nsae);
        }
    }
    
    /**
     * This method computes the hash of a specific method
     * 
     * @param method
     *            to compute his hash
     * @return the hash of the method
     */
    public final static long getMethodHash(final Method method) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutput dataOutput = new DataOutputStream(baos);

        try {
            dataOutput.writeUTF(getMethodDescriptor(method));
        } catch (IOException ioe) {
            throw new InternalError("IOException while using DataOutput: "
                    + ioe);
        }

        byte[] bytes = baos.toByteArray();

        byte[] digest = md.digest(bytes);

        //
        // 64-bit (long) integer computed from the
        // first two 32-bit values of the message digest
        //
        // The 64-bit hash value is the little-endian composition of an eight
        // byte sequence
        // where the first four bytes are the first 32-bit value of the message
        // digest in
        // big-endian byte order and the last four bytes are the second 32-bit
        // value of the
        // message digest in big-endian byte order. For example, if the first
        // two 32-bit
        // values of the message digest are 0xB0B1B2B3 and 0xB4B5B6B7, then the
        // hash value
        // would be 0xB7B6B5B4B3B2B1B0.
        //

        byte[] hash = new byte[8];

        hash[0] = digest[7];
        hash[1] = digest[6];
        hash[2] = digest[5];
        hash[3] = digest[4];
        hash[4] = digest[3];
        hash[5] = digest[2];
        hash[6] = digest[1];
        hash[7] = digest[0];

        return new BigInteger(hash).longValue();
    }

    /** Create the description of a method */
    /**
     * This method creates the description of a method
     * 
     * @param method
     *            to create the descriptor
     * @return a descriptor for the method
     */
    private final static String getMethodDescriptor(final Method method) {

        StringBuffer sb = new StringBuffer();

        sb.append(method.getName());
        sb.append("(");

        for (Class clazz : method.getParameterTypes())
            sb.append(MethodHashGenerator.getFieldDescriptor(clazz));

        sb.append(")");
        sb.append(MethodHashGenerator
                .getFieldDescriptor(method.getReturnType()));

        return sb.toString();
    }

    /**
     * This method returns the descriptor for the type represented by the class
     * clazz
     * 
     * @param clazz
     *            type to compute the descriptor
     * @return the descriptor for the type represented by the class clazz
     */
    private final static String getFieldDescriptor(Class clazz) {

        if (clazz == null) {
            // TODO: what kind of
            // exception should be
            // thrown here?
            throw new InternalError("clazz == null!"); 
        }
        
        if (clazz.isArray()) {
            return clazz.getName().replace('.', '/');
        }

        if (clazz.isPrimitive()) {

            if (clazz.equals(Boolean.TYPE))
                return "Z";

            else if (clazz.equals(Character.TYPE))
                return "C";

            else if (clazz.equals(Byte.TYPE))
                return "B";

            else if (clazz.equals(Short.TYPE))
                return "S";

            else if (clazz.equals(Integer.TYPE))
                return "I";

            else if (clazz.equals(Long.TYPE))
                return "J";

            else if (clazz.equals(Float.TYPE))
                return "F";

            else if (clazz.equals(Double.TYPE))
                return "D";

            else if (clazz.equals(Void.TYPE))
                return "V";

            else
                throw new InternalError("Unknown primitive type: " + clazz);

        }

        //
        // clazz.isPrimitive() == false
        //

        return "L" + clazz.getName().replace('.', '/') + ";";

    }
}
