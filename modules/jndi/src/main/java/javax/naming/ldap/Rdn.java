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

package javax.naming.ldap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.naming.InvalidNameException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;

import org.apache.harmony.jndi.internal.nls.Messages;
import org.apache.harmony.jndi.internal.parser.LdapRdnParser;

/**
 * 
 * @ar.org.fitc.spec_ref
 * 
 * @version 1.0
 * @author Osvaldo C. Demo
 * 
 */
public class Rdn implements Serializable, Comparable {

    private static final long serialVersionUID = -5994465067210009656L;

    /**
     * @ar.org.fitc.spec_ref
     */
    public static String escapeValue(Object val) {
        if (val == null)
            throw new NullPointerException("val "+Messages.getString("ldap.00"));
        return LdapRdnParser.escapeValue(val);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public static Object unescapeValue(String val) {
        if (val == null)
            throw new NullPointerException("val "+Messages.getString("ldap.00"));
        return LdapRdnParser.unescapeValue(val);
    }

    private List list = new ArrayList();

    private transient LdapRdnParser parser;

    /**
     * @ar.org.fitc.spec_ref
     */
    public Rdn(Attributes attrSet) throws InvalidNameException {
        if (attrSet == null)
            throw new NullPointerException("attrSet "+Messages.getString("ldap.00"));
        if (attrSet.size() == 0)
            throw new InvalidNameException("atrrSet "+Messages.getString("ldap.03"));

        NamingEnumeration ne = attrSet.getAll();
        while (ne.hasMoreElements()) {
            Attribute at = (Attribute) ne.nextElement();
            try {
                at.get();
            } catch (NamingException e) {
            }
        }
        list = convertToAttributeArrayList((Attributes) attrSet.clone());
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Rdn(Rdn rdn) {
        if (rdn == null)
            throw new NullPointerException("rdn "+Messages.getString("ldap.00"));
        list = convertToAttributeArrayList((Attributes) rdn.toAttributes()
                .clone());
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Rdn(String rdnString) throws InvalidNameException {
        if (rdnString == null)
            throw new NullPointerException("rdnString "+Messages.getString("ldap.00"));
        if (rdnString.length() != 0) {
            parser = new LdapRdnParser(rdnString);
            list = parser.getList();
        }
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Rdn(String type, Object value) throws InvalidNameException {
        if (type == null)
            throw new NullPointerException("type "+Messages.getString("ldap.00"));
        if (value == null)
            throw new NullPointerException("value "+Messages.getString("ldap.00"));
        if (type.length() == 0)
            throw new InvalidNameException("type "+Messages.getString("ldap.04"));
        if (value instanceof String) {
            if (((String) value).length() == 0)
                throw new InvalidNameException("value "+Messages.getString("ldap.04"));
        }
        list = convertToAttributeArrayList(new BasicAttributes(type, value,
                true));
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public int compareTo(Object obj) {

        if (!(obj instanceof Rdn)) {
            throw new ClassCastException(
                    Messages.getString("ldap.06"));
        }
        Rdn rdn = (Rdn) obj;
        String s1 = new String(), s2 = new String();

        for (Enumeration iter = toAttributes().getAll(); iter.hasMoreElements();) {
            s1 = s1 + escapeValue(iter.nextElement().toString());
            if (iter.hasMoreElements())
                s1 = s1 + ",";
        }
        for (Enumeration iter = rdn.toAttributes().getAll(); iter
                .hasMoreElements();) {
            s2 = s2 + escapeValue(iter.nextElement().toString());
            if (iter.hasMoreElements())
                s2 = s2 + ",";
        }
        return s1.toLowerCase().compareTo(s2.toLowerCase());
    }

    private List convertToAttributeArrayList(Attributes attrList) {
        List myList = new ArrayList();
        NamingEnumeration ne = attrList.getAll();
        while (ne.hasMoreElements()) {
            myList.add((Attribute) ne.nextElement());
        }
        return myList;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public boolean equals(Object obj) {

        if (!(obj instanceof Rdn) || this.size() != ((Rdn) obj).size()) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        NamingEnumeration iter1 = toAttributes().getAll();
        NamingEnumeration iter2 = ((Rdn) obj).toAttributes().getAll();
        while (iter1.hasMoreElements()) {
            Attribute a1 = (Attribute) iter1.nextElement();
            Attribute a2 = (Attribute) iter2.nextElement();
            if (!(a1.getID().toLowerCase().equals(a2.getID().toLowerCase())))
                return false;
            Enumeration en1 = null;
            Enumeration en2 = null;
            try {
                en1 = a1.getAll();
                en2 = a2.getAll();
            } catch (NamingException e) {
            }

            while (en1.hasMoreElements()) {
                Object obj1 = en1.nextElement();
                Object obj2 = en2.nextElement();
                byte[] array1 = null;
                byte[] array2 = null;

                if (obj1 instanceof String && obj2 instanceof String) {
                    String s1 = (String) obj1;
                    String s2 = (String) obj2;
                    if (!(escapeValue(s1.toLowerCase()).equals(escapeValue(s2
                            .toLowerCase()))))
                        return false;
                    continue;
                } else if (obj1 instanceof byte[] && obj2 instanceof byte[]) {
                    array1 = (byte[]) obj1;
                    array2 = (byte[]) obj2;
                } else if (obj1 instanceof String && obj2 instanceof byte[]) {
                    try {
                        array1 = (byte[]) Rdn.unescapeValue((String) obj1);
                    } catch (ClassCastException e) {
                        return false;
                    }
                    array2 = (byte[]) obj2;
                } else if (obj1 instanceof byte[] && obj2 instanceof String) {
                    array1 = (byte[]) obj1;
                    try {
                        array2 = (byte[]) Rdn.unescapeValue((String) obj2);
                    } catch (ClassCastException e) {
                        return false;
                    }
                } else {
                    throw new ClassCastException();
                }
                if (array1.length == array2.length)
                    for (int i = 0; i < array1.length; i++) {
                        if (array1[i] != array2[i])
                            return false;
                    }
                else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public String getType() {
        Attribute b = (Attribute) list.get(0);
        return b.getID();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Object getValue() {
        Attribute b = (Attribute) list.get(0);
        Object a = null;
        try {
            a = b.get();
        } catch (NamingException e) {
        } catch (NullPointerException e) {
        }
        return a;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public int hashCode() {
        int sum = 0;

        for (Iterator attr = list.iterator(); attr.hasNext();) {
            Attribute a = (Attribute) attr.next();
            NamingEnumeration en = null;
            sum += a.getID().toLowerCase().hashCode();
            try {
                en = a.getAll();
            } catch (NamingException e) {
            }
            while (en.hasMoreElements()) {
                Object obj = en.nextElement();
                try {
                    String s = (String) obj;
                    sum += escapeValue(s.toLowerCase()).hashCode();
                } catch (ClassCastException e) {
                    sum += obj.hashCode();
                }
            }
        }
        return sum;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public int size() {
        int result = 0;
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Attribute element = (Attribute) iter.next();
            result += element.size();
        }
        return result;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Attributes toAttributes() {
        BasicAttributes ba = new BasicAttributes(true);
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Attribute element = (Attribute) iter.next();
            ba.put((Attribute) element.clone());
        }
        return ba;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Attribute element = (Attribute) iter.next();
            NamingEnumeration ne = null;
            try {
                ne = element.getAll();
            } catch (NamingException e) {
            }
            while (ne.hasMoreElements()) {
                sb
                        .append(element.getID() + "="
                                + escapeValue(ne.nextElement()));
                if (ne.hasMoreElements())
                    sb.append("+");
            }
            if (iter.hasNext())
                sb.append("+");
        }
        return sb.toString();
    }

}