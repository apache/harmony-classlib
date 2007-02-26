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

package org.apache.harmony.jndi.internal.parser;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.naming.InvalidNameException;

import org.apache.harmony.jndi.internal.nls.Messages;

/**
 * Class used to parse RDN components of a Distinguished name
 * 
 * @author Osvaldo C. Demo
 * 
 */
public class LdapRdnParser implements LdapParser {

    private static String name = null;

    LdapTypeAndValueList list = new LdapTypeAndValueList();

    private List attrList;

    private List listAll;

    private RelaxedDnParser parser = null;

    /**
     * Constructor
     */
    public LdapRdnParser(String name) {
        if (name.endsWith("+")) {
            LdapRdnParser.name = name.substring(0, name.lastIndexOf('+'));
        } else {
            LdapRdnParser.name = name;
        }
    }

    /**
     * Used to get the list of Type/Values
     */
    public List getList() throws InvalidNameException {
        try {
            checkTypeRestrictions(name);
            parser = new RelaxedDnParser(name);
            listAll = parser.parse();
        } catch (IOException e) {
            throw (InvalidNameException) (new InvalidNameException(Messages
                    .getString("ldap.17")
                    + name).initCause(e));
        }
        attrList = (List) listAll.get(0);
        for (Iterator iter = attrList.iterator(); iter.hasNext();) {
            AttributeTypeAndValuePair element = (AttributeTypeAndValuePair) iter
                    .next();
            list.put(element.getType(), element.getValue());
        }
        return list.toAttributeList();
    }

    private void checkTypeRestrictions(String rdn) throws InvalidNameException {
        int in = rdn.indexOf("=");
        try {
            if (rdn.substring(0, in).length() == 0) {
                throw new InvalidNameException(Messages.getString("ldap.18")
                        + rdn);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidNameException(Messages.getString("ldap.17") + rdn);
        }
    }

    /**
     * returns a string escaped according to the rules specified in RFC 2253.
     * 
     * @param obj
     *            the String or byteArray to escape
     * @return The escaped value
     * @throws ClassCastException
     *             when obj is not an instance of String or ByteArray
     */
    public static String escapeValue(Object obj) {
        if (obj instanceof String) {
            String val = String.valueOf((String) obj);
            return getEscaped(val.toCharArray().clone());
        } else if (obj instanceof byte[]) {
            return getHexValues((byte[]) obj);
        } else {
            throw new ClassCastException(Messages.getString("ldap.19"));
        }
    }

    private static String getEscaped(char[] chars) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if (isSpecialChar(chars, i))
                sb.append("\\");
            sb.append(new Character(chars[i]));
        }
        return sb.toString();
    }

    private static String getHexValues(byte[] byteArray) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            sb.append(Integer.toHexString(byteArray[i] >> 4 & 0x0F));
            sb.append(Integer.toHexString(byteArray[i] & 0x0F)); 
        }
        return "#" + sb.toString();
    }

    /**
     * Given an attribute value string formated according to the rules specified
     * in RFC 2253, returns the unescaped value.
     * 
     * @param val
     *            String to unescape
     * @return the unescaped value (String or ByteArray)
     */
    public static Object unescapeValue(String val) {
        if (val.startsWith("#") && numeralCounter(val)) {
            return getByteFromHexString(val);
        } else if (val.startsWith("#")) {
            return getByteFromHexString(val);
        } else {
            char[] chars = new String(val.trim()).toCharArray();
            return getUnEscapedValues(chars);
        }

    }

    private static boolean numeralCounter(String val) {
        int counter = 0;
        for (int i = 0; i < val.length(); i++) {
            if (val.charAt(i) == '#') {
                counter++;
            }
        }
        if (counter == 1) {
            return true;
        } else {
            return false;
        }
    }

    private static String getUnEscapedValues(char[] chars) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '\\') {
                sb.append(chars[i]);
            } else {
                try {
                    if (!isSpecialChar(chars, i + 1)
                            && !isSpecialChar(chars, i + 2)) {
                        try {
                            sb.append(RelaxedDnParser.hexToUTF8(new String(
                                    chars, i + 1, 2)));
                            i = i + 2;
                        } catch (IOException e) {
                            throw new IllegalArgumentException(Messages.getString("ldap.1A"));
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }

            }
        }
        return sb.toString();
    }

    private static byte[] getByteFromHexString(String val) {
        String str = val.substring(val.indexOf("#") + 1);
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException(Messages.getString("ldap.1A"));
        }
        byte[] ret = null;
        try {
            ret = new byte[str.length() / 2];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = (byte) Integer.parseInt(str
                        .substring(2 * i, 2 * i + 2), 16);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Messages.getString("ldap.17") + val);
        }
        return ret;
    }

    private static boolean isSpecialChar(char[] chars, int index) {
        switch (chars[index]) {
        case '"':
        case '\\':
        case ',':
        case '=':
        case '+':
        case '<':
        case '>':
        case '#':
        case ';':
            return true;
        default:
            return false;
        }
    }
}
