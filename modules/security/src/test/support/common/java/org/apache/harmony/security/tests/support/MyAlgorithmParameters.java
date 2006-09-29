/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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

package org.apache.harmony.security.tests.support;

import java.io.IOException;
import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;

/**
 * Tests implementation of AlgorithmParameters
 * 
 */
public class MyAlgorithmParameters extends AlgorithmParametersSpi {

	public static boolean runEngineInit1 = false;
	public static boolean runEngineInit2 = false;
	public static boolean runEngineInit3 = false;
	public static boolean runEngineGetParameterSpec = false;
	public static boolean runEngineGetEncoded1 = false;
	public static boolean runEngineGetEncoded2 = false;	
	public static boolean runEngineToString = false;
	
	protected void engineInit(AlgorithmParameterSpec paramSpec)
			throws InvalidParameterSpecException {
		runEngineInit1 = true;
	}

	protected void engineInit(byte[] params) throws IOException {
		runEngineInit2 = true;
	}

	protected void engineInit(byte[] params, String format) throws IOException {
		runEngineInit3 = true;
	}

	protected AlgorithmParameterSpec engineGetParameterSpec(Class paramSpec)
			throws InvalidParameterSpecException {
		runEngineGetParameterSpec = true;
		return null;
	}

	protected byte[] engineGetEncoded() throws IOException {
		runEngineGetEncoded1 = true;
		return null;
	}

	protected byte[] engineGetEncoded(String format) throws IOException {
		runEngineGetEncoded2 = true;
		return null;
	}

	protected String engineToString() {
		runEngineToString = true;
		return "AlgorithmParameters";
	}

}
