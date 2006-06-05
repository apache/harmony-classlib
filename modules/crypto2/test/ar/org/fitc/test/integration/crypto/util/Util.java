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

package ar.org.fitc.test.integration.crypto.util;

public class Util {

    public static byte[] intToByteArray(int source){
        //A four byte array
        byte res[] = new byte[4];
        //using directly source

        //Unpackagging the int in the array
        res[3] = (byte) source;
        source >>= 8;
        res[2] = (byte) source;
        source >>= 8;
        res[1] = (byte) source;
        source >>= 8;
        res[0] = (byte) source;
        return res;
    }

    public static int byteArrayToInt(byte[] source){
        assert source.length == 4 : "Trying to packagage a non 32 bits number?";
        int digit =  digit = (int) source[0] & 0xFF;;
        digit <<= 8;
        digit |= source[1] & 0xFF;
        digit <<= 8;
        digit |= source[2] & 0xFF;
        digit <<= 8;
        digit |= source[3] & 0xFF;
        return digit;
    }

}
