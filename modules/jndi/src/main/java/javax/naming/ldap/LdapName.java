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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.naming.InvalidNameException;
import javax.naming.Name;

import org.apache.harmony.jndi.internal.nls.Messages;
import org.apache.harmony.jndi.internal.parser.LdapNameParser;

/**
 * @ar.org.fitc.spec_ref
 * 
 * @version 0.0.1
 * 
 */
public class LdapName implements Name {

    private static final long serialVersionUID = -1595520034788997356L;

    private List rdns;

    /**
     * @ar.org.fitc.spec_ref
     */
    public LdapName(List rdns) {
        if (rdns == null)
            throw new NullPointerException("rdns "+Messages.getString("ldap.00"));
        init(rdns);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public LdapName(String name) throws InvalidNameException {
        LdapNameParser parser = new LdapNameParser(name);
        init(parser.getList());
    }

    private void init(List rdns) {
        this.rdns = rdns;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Name add(int posn, Rdn comp) {
        if (comp == null)
            throw new NullPointerException("comp "+Messages.getString("ldap.00"));
        if (posn < 0 || posn > rdns.size())
            throw new IndexOutOfBoundsException(
                    Messages.getString("ldap.05"));
        getRdns().add(posn, comp);
        return this;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Name add(int posn, String comp) throws InvalidNameException {
        return add(posn, new Rdn(comp));
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Name add(Rdn comp) {
        return add(getRdns().size(), comp);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Name add(String comp) throws InvalidNameException {
        return add(getRdns().size(), comp);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Name addAll(int posn, List suffixRdns) {
        if (suffixRdns == null)
            throw new NullPointerException("suffixRdns "+Messages.getString("ldap.00"));
        if (posn < 0 || posn > rdns.size())
            throw new IndexOutOfBoundsException(Messages.getString("ldap.00"));
        getRdns().addAll(posn, suffixRdns);
        return this;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Name addAll(int posn, Name suffix) throws InvalidNameException {
        if (suffix instanceof LdapName) {
            return addAll(posn, ((LdapName) suffix).getRdns());
        } else {
            List rdns = new ArrayList();
            for (Enumeration iter = ((Name) suffix).getAll(); iter
                    .hasMoreElements();) {
                rdns.add(new Rdn((String)iter.nextElement()));
            }
            return addAll(posn, rdns);
        }
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Name addAll(List suffixRdns) {
        return addAll(getRdns().size(), suffixRdns);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Name addAll(Name suffix) throws InvalidNameException {
        return addAll(getRdns().size(), suffix);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Object clone() {
        List lista = new ArrayList();
        for (int i = 0; i < getRdns().size(); i++) {
            lista.add(getRdns().get(i));
        }
        return new LdapName(lista);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public int compareTo(Object obj) {
        LdapName ln = null;
        if (obj == null)
            throw new ClassCastException("obj "+Messages.getString("ldap.01"));
        try {
            ln = (LdapName) obj;
        } catch (ClassCastException e) {
            throw new ClassCastException("obj "+Messages.getString("ldap.01"));
        }
        String s1 = new String(), s2 = new String();

        for (Iterator iter = getRdns().iterator(); iter.hasNext();) {
            s1 = s1 + iter.next().toString() + ",";
        }
        for (Iterator iter = ln.getRdns().iterator(); iter.hasNext();) {
            s2 = s2 + iter.next().toString() + ",";
        }
        if (s1.lastIndexOf(',') > 0)
            s1 = s1.substring(0, s1.lastIndexOf(','));
        if (s2.lastIndexOf(',') > 0)
            s2 = s2.substring(0, s2.lastIndexOf(','));
        return s1.toLowerCase().compareTo(s2.toLowerCase());
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public boolean endsWith(List rdns) {
        try {
            Iterator iter = rdns.iterator();
            Iterator iter2 = ((LdapName) getSuffix(getRdns().size()
                    - rdns.size())).getRdns().iterator();
            while (iter.hasNext()) {
                if (!((Rdn) iter.next()).equals((Rdn) iter2.next()))
                    return false;
            }
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public boolean endsWith(Name n) {
        try {
            if (n.equals(getSuffix(getRdns().size() - n.size())))
                return true;
            else
                return false;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public boolean equals(Object obj) {
        LdapName ln = null;
        if (obj == null)
            return false;
        try {
            ln = (LdapName) obj;
        } catch (ClassCastException e) {
            return false;
        }
        if (ln.getRdns().size() != getRdns().size())
            return false;

        Iterator iter = ln.getRdns().iterator();
        Iterator iter2 = getRdns().iterator();
        while (iter.hasNext()) {
            Rdn rdn1 = (Rdn) iter.next();
            Rdn rdn2 = (Rdn) iter2.next();
            if (!rdn1.equals(rdn2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public String get(int posn) {
        return getRdn(posn).toString();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Enumeration getAll() {
        final Iterator rdns = getRdns().iterator();
        return new Enumeration() {
            public boolean hasMoreElements() {
                return rdns.hasNext();
            }

            public String nextElement() {
                return rdns.next().toString();
            }
        };
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Name getPrefix(int posn) {
        if (posn < 0)
            throw new IndexOutOfBoundsException(
                    Messages.getString("ldap.02"));
        return new LdapName(getRdns().subList(0, posn));
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Rdn getRdn(int posn) {
        return (Rdn) getRdns().get(posn);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public List getRdns() {
        return rdns;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Name getSuffix(int posn) {
        if (posn > getRdns().size())
            throw new IndexOutOfBoundsException(
                    Messages.getString("ldap.02"));
        return new LdapName(getRdns().subList(posn, getRdns().size()));
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public int hashCode() {
        int sum = 0;
        for (Iterator iter = getRdns().iterator(); iter.hasNext();) {
            sum += iter.next().hashCode();
        }
        return sum;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public boolean isEmpty() {
        if (getRdns().size() == 0)
            return true;
        else
            return false;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public Object remove(int posn) throws InvalidNameException {
        return getRdns().remove(posn);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public int size() {
        return rdns.size();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public boolean startsWith(List rdns) {
        try {
            Iterator iter = rdns.iterator();
            Iterator iter2 = ((LdapName) getPrefix(rdns.size())).getRdns()
                    .iterator();
            while (iter.hasNext()) {
                if (!((Rdn) iter.next()).equals((Rdn) iter2.next()))
                    return false;
            }
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public boolean startsWith(Name n) {
        try {
            if (n.equals(getPrefix(n.size())))
                return true;
            else
                return false;
        } catch (RuntimeException e) {
            return false;
        }

    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public String toString() {
        if (rdns.size() == 0)
            return "";
        StringBuffer sb = new StringBuffer();
        sb.append(rdns.get(getRdns().size() - 1).toString());
        for (int i = getRdns().size() - 2; i >= 0; i--) {
            sb.append("," + rdns.get(i).toString());
        }
        return sb.toString();
    }
}