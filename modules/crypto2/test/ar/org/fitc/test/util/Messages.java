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
/**
 * This interface contains Strings for use when a test failed.
 * 
 *
 */
public interface Messages {
    static String msgNoException = "Should not raise an Exception...";
    static String msgNoSuchAlgorithm = "Should raise NoSuchAlgorithmException";
    static String msgNullPointer = "Should raise NullPointerException";
    static String msgNoSuchPadding = "Should raise NoSuchPaddingException";
    static String msgNoSuchProvider = "Should raise NoSuchProviderException";
    static String msgInvalidKey = "Should raise InvalidKeyException...";
    static String msgIllegalArgument = "Should raise IllegalArgumentException";
    static String msgNotInstance = "Not returning an instance of ";
    static String msgRaise = "Should raise "; 
    static String msgInvalidAlgorithmParamter = "Should raise InvalidAlgorithmParameterException...";
    static String msgNoImp = "Method not implemented yet";
    static String msgThrowTest = "Throwned for testing purposes...";
    static String msgNullRet = "Should return null";
    static String msgNotSame = "Not the same...";
    static String msgNotNull = "Should not return null";
    static String msgExceptionNoExpected = "Some exception don't expected: ";
}
