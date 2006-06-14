/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, 
 *  as applicable.
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
package org.apache.harmony.archive.tests.internal.pack200;

/**
 * This is intended to be used as a test class for unpacking a packed Jar file.
 * @author Alex Blewitt
 * @version $Revision: $
 */
public class HelloWorld {
	int i=97,j=42,k=12345;
	float f=3.142f,g=2.718f;
	long l=299792458;
	double d=4.0d;
	public static void main(String[] args) {
		System.out.println("Hello world");
	}
	public HelloWorld[][] method(int a,int b,int c) {
		return null;
	}
}
