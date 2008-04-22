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
package org.apache.harmony.jndi.provider.ldap.parser;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class SchemaParser {

    public final static String SPACE = " "; //$NON-NLS-1$

    public final static String LEFT_PARENTHESIS = "("; //$NON-NLS-1$

    public final static String RIGHT_PARENTHESIS = ")"; //$NON-NLS-1$

    public final static String SINGLE_QUOTE = "'"; //$NON-NLS-1$

    public final static String NAME = "name"; //$NON-NLS-1$

    public final static String ORIG = "orig"; //$NON-NLS-1$

    public final static String NUMERICOID = "numericoid"; //$NON-NLS-1$

    public final static String TRUE = "true"; //$NON-NLS-1$

    public static String getName(String schemaLine) {
        StringTokenizer st = new StringTokenizer(schemaLine);
        st.nextToken();

        String name = st.nextToken();
        if (st.hasMoreTokens()) {
            if (st.nextToken().equalsIgnoreCase(NAME)) {
                name = st.nextToken();

                if (name.equals(LEFT_PARENTHESIS)) {
                    name = st.nextToken();
                }
                name = name.substring(1, name.length() - 1);
            }
        }

        return name;
    }

    public static Hashtable<String, Object> parseValue(String schemaLine) {
        StringTokenizer st = new StringTokenizer(schemaLine.toLowerCase());
        // Skip (
        st.nextToken();

        String oid = st.nextToken();

        Hashtable<String, Object> schemaDef = new Hashtable<String, Object>();
        schemaDef.put(ORIG, schemaLine);
        schemaDef.put(NUMERICOID, oid);
        String token = null;
        ArrayList<String> values = null;
        StringBuilder desc = new StringBuilder();
        while (st.hasMoreTokens()) {
            String attrName = st.nextToken().toLowerCase();
            if (attrName.startsWith("x-")) { //$NON-NLS-1$
                token = st.nextToken();
                // remove the ending ' symbol
                token = token.substring(1, token.length() - 1);
                schemaDef.put(attrName, token);
            } else if (attrName.equals("usage") || attrName.equals("equality") //$NON-NLS-1$//$NON-NLS-2$
                    || attrName.equals("syntax") || attrName.equals("ordering") //$NON-NLS-1$ //$NON-NLS-2$
                    || attrName.equals("substr")) { //$NON-NLS-1$
                token = st.nextToken();
                schemaDef.put(attrName, token);
            } else if (attrName.equals("desc")) { //$NON-NLS-1$
                token = st.nextToken();

                // remove the leading ' symbol
                if (token.startsWith(SINGLE_QUOTE)) {
                    token = token.substring(1);
                }
                while (token != null && !token.endsWith(SINGLE_QUOTE)) {
                    desc.append(token).append(SPACE);
                    token = st.nextToken();
                }

                if (token != null) {
                    // remove the ending ' symbol
                    desc.append(token.substring(0, token.length() - 1));
                    schemaDef.put(attrName, desc.toString());
                    desc.delete(0, desc.length());
                }
            } else if (attrName.equals(NAME)) {
                token = st.nextToken();
                values = new ArrayList<String>();
                // Name has multiple values
                if (token.startsWith(LEFT_PARENTHESIS)) {
                    token = st.nextToken();
                    while (!token.equals(RIGHT_PARENTHESIS)) {
                        // remove the leading ' symbol
                        if (token.startsWith(SINGLE_QUOTE)) {
                            token = token.substring(1);
                        }
                        while (!token.endsWith(SINGLE_QUOTE)) {
                            desc.append(token).append(SPACE);
                            token = st.nextToken();
                        }

                        // remove the ending ' symbol
                        desc.append(token.substring(0, token.length() - 1));
                        values.add(desc.toString());
                        desc.delete(0, desc.length());

                        token = st.nextToken();
                    }
                } else {
                    // remove the leading ' symbol
                    if (token.startsWith(SINGLE_QUOTE)) {
                        token = token.substring(1);
                    }
                    while (token != null && !token.endsWith(SINGLE_QUOTE)) {
                        desc.append(token).append(SPACE);
                        token = st.nextToken();
                    }

                    if (token != null) {// remove the ending ' symbol
                        desc.append(token.substring(0, token.length() - 1));
                        values.add(desc.toString());
                        desc.delete(0, desc.length());
                    }
                }
                schemaDef.put(attrName, values);
            } else if (attrName.equals("must") || attrName.equals("sup") //$NON-NLS-1$ //$NON-NLS-2$
                    || attrName.equals("may")) { //$NON-NLS-1$
                token = st.nextToken();
                values = new ArrayList<String>();
                // has multiple values
                if (token.startsWith(LEFT_PARENTHESIS)) {
                    token = st.nextToken();
                    while (!token.equals(RIGHT_PARENTHESIS)) {
                        if (!token.equals("$")) //$NON-NLS-1$
                            values.add(token);
                        token = st.nextToken();
                    }
                } else {
                    values.add(token);
                }
                schemaDef.put(attrName, values);
            } else if (attrName.equals("abstract") || attrName.equals("structural") //$NON-NLS-1$ //$NON-NLS-2$
                    || attrName.equals("auxiliary") //$NON-NLS-1$
                    || attrName.equals("single-value") //$NON-NLS-1$
                    || attrName.equals("no-user-modification")) { //$NON-NLS-1$
                schemaDef.put(attrName, TRUE);
            }
        }
        return schemaDef;
    }
}
