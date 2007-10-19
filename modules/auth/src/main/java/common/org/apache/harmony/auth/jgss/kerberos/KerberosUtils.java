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

package org.apache.harmony.auth.jgss.kerberos;

import java.lang.reflect.Constructor;

import org.apache.harmony.auth.jgss.kerberos.toolbox.KerberosToolboxSpi;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

public class KerberosUtils {
	public static final String DEFAULT_CHARSET_NAME = "UTF-8";

	public static final int DEFAULT_GSSEXCEPTION_MAJOR_CODE = 3;

	public static final int DEFAULT_GSSEXCEPTION_MINOR_CODE = 0;
	
	public static final Oid KRB5_MECH;
	
	public static final Oid KRB5_PRINCIPAL_NAMETYPE;

	public static final Oid[] SUPPORTED_NAME_MECHS;
    
    public static final String KERBEROS_TOOLBOX_PROVIDER = "org.apache.harmony.auth.jgss.kerberos.toolbox.KerberosToolboxImpl";

	static {
		try {
			KRB5_MECH = new Oid("1.2.840.113554.1.2.2");
			KRB5_PRINCIPAL_NAMETYPE = new Oid("1.2.840.113554.1.2.2.1");

		} catch (GSSException e) {
			throw new Error();
		}
		SUPPORTED_NAME_MECHS = new Oid[] { GSSName.NT_USER_NAME,
				GSSName.NT_HOSTBASED_SERVICE, GSSName.NT_EXPORT_NAME,
				KRB5_PRINCIPAL_NAMETYPE };
	}
    
    public static KerberosToolboxSpi getKerberosToolbox(String kdcName) throws Exception{
        Class cls = Class.forName(KERBEROS_TOOLBOX_PROVIDER);
        Constructor constructor = cls.getConstructor(String.class);
        return (KerberosToolboxSpi) constructor.newInstance(kdcName);        
    }

}
