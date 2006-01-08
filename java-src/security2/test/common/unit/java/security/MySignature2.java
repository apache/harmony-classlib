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
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package java.security;

/**
 * Tests implementation of Signature
 * 
 */
public class MySignature2 extends SignatureSpi {

	public static boolean runEngineInitVerify = false;
	public static boolean runEngineInitSign = false;
	public static boolean runEngineUpdate1 = false;
	public static boolean runEngineUpdate2 = false;	
	public static boolean runEngineSign = false;
	public static boolean runEngineVerify = false;
	public static boolean runEngineSetParameter = false;	
	public static boolean runEngineGetParameter = false;
	
	protected void engineInitVerify(PublicKey publicKey)
			throws InvalidKeyException {
		runEngineInitVerify = true;
	}

	protected void engineInitSign(PrivateKey privateKey)
			throws InvalidKeyException {
		runEngineInitSign = true;
	}

	protected void engineUpdate(byte b) throws SignatureException {
		runEngineUpdate1 = true;
	}

	protected void engineUpdate(byte[] b, int off, int len)
			throws SignatureException {
		runEngineUpdate2 = true;
	}

	protected byte[] engineSign() throws SignatureException {
		runEngineSign = true;
		return null;
	}

	protected boolean engineVerify(byte[] sigBytes) throws SignatureException {
		runEngineVerify = true;
		return false;
	}

	protected void engineSetParameter(String param, Object value)
			throws InvalidParameterException {
		runEngineSetParameter = true;
	}

	protected Object engineGetParameter(String param)
			throws InvalidParameterException {
		runEngineGetParameter = true;
		return null;
	}
}
