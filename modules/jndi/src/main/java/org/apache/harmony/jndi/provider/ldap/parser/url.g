options {

STATIC = false;

}


PARSER_BEGIN(LdapUrlParser)
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.StringReader;
import javax.naming.directory.SearchControls;
import org.apache.harmony.jndi.provider.ldap.Filter;
import org.apache.harmony.jndi.provider.ldap.asn1.Utils;

public class LdapUrlParser {
    private SearchControls controls;
    
    private Filter filter;
 
    private String baseObject;

    private String host;

    private int port = -1;

    private boolean isEndEOF = false;

    public LdapUrlParser(String url) {
        this(new StringReader(url));
        isEndEOF = true;
    }

    public SearchControls getControls() {
        return controls;
    }

    public Filter getFilter() {
        return filter;
    }

    private String convertToUtf8Char(String s) {
        byte[] bs = new byte[] { (byte)Integer.parseInt(s, 16) };
        return Utils.getString(bs);
    }
    
    /**
     * get the host part of the url, if host part is omitted, <code>null</code>
     * will be return
     * 
     * @return host part of the url or <code>null</code> if it's omitted
     */
    public String getHost() {
        return host;
    }
    
    /**
     * get the port number of the url, if this part is omitted, -1 will be
     * return
     * 
     * @return port number of the url or -1 if it's omitted
     */
    public int getPort() {
        return port;
    }

    public String getBaseObject() {
        return baseObject;
    }

    public static void main(String args[]) throws ParseException, FileNotFoundException {
        LdapUrlParser parser = new LdapUrlParser(new FileInputStream("parser.url.test"));
//        URLParser parser = new URLParser(System.in);
        //  FilterParser parser = new FilterParser(System.in);
        //System.out.println(parser.value());
        parser.test();
        //parser.value();
    }
}

PARSER_END(LdapUrlParser)

TOKEN :
{

<SCHEME : "ldap://" | "ldaps://" >
|
<PRE_XTOKEN : "X-" | "x-" >
|
<SCOPE : "base" | "one" | "sub">
|
<COMMA : "," >
|
<QUESTION_MARK : "?" >
|
<SLASH : "/">
| 
<PERCENT : "%">
|
<COLON : ":">
|
<PERIOD : ".">
|
<EXCLAM_MARK : "!">
|
<EQUAL : "=">
|
<HEX_CHAR : ["a"-"f", "A"-"F"] >
|
<NOHEX_CHAR : ["g"-"z", "G" - "Z"] >
|
<DIGIT : ["1"-"9"]>
|
<ZERO : "0">
|
<CHAR : ~["/", "?", "\n"] >

}


void parseURL():
        {}
        {
            <SCHEME> 
            [hostport()] 
            [<SLASH> [dn() 
                  [<QUESTION_MARK> attributes()]]]
            {
                if (isEndEOF) {
                    eof();
                }
            }
        }

void eof():
        {}
        {
            <EOF>
        }

String number():
        {
            StringBuilder value = new StringBuilder();
            Token t;
        }
        {
            (t = <ZERO> {value.append(t.image);}
            | (t = <DIGIT> {value.append(t.image);}
               (t = <ZERO> {value.append(t.image);}
               |t = <DIGIT> {value.append(t.image);})*))

            {return value.toString();}
        }

String hexDigit():
        {
            String value;
            Token t;
        }
        {
            (t = <HEX_CHAR> | t = <DIGIT> | t = <ZERO> ) {value = t.image;}
            (t = <HEX_CHAR> | t = <DIGIT> | t = <ZERO> ) {value += t.image;}

            {return value;}
        }

String value():
        {
            StringBuilder value = new StringBuilder();
            String ch;
        }
        {
            (ch = ch() {value.append(ch);})+

            {return value.toString();}
        }

String ch():
        {
            Token t;
            String value;
            String temp;
        }
        {
            (t = <CHAR> {value = t.image;} 
             | t = <PERCENT> value = hexDigit() {value = convertToUtf8Char(value);}
             | t = <ZERO> {value = t.image;} 
             | t = <DIGIT> {value = t.image;} 
             | t = <HEX_CHAR> {value = t.image;} 
             | t = <NOHEX_CHAR> {value = t.image;} 
             | t = <SCHEME> {value = t.image;} 
             | t = <EQUAL> {value = t.image;} 
             | t = <PERIOD> {value = t.image;} 
             | t = <EXCLAM_MARK> {value = t.image;} 
             | t = <SCOPE> {value = t.image;} 
             | t = <PRE_XTOKEN> {value = t.image;})

            {return value;}
        }

void hostport():
        {
            String ch;
            StringBuilder h = new StringBuilder();

        }
        {
            (ch = ch() {h.append(ch);})+ {host = h.toString();} 
            [<COLON> ch = number() {port = Integer.valueOf(ch);}]
        }

void dn():
        {
            Token t;
            String value;
            StringBuilder dn = new StringBuilder();
        }
        {
            (value = value() {dn.append(value);} 
            [(t = <COMMA> {dn.append(t.image);} value = value() {dn.append(value);})+])
            {
                baseObject = dn.toString();
            }
        }

void attributes():
        {
            String value;
            List attrs = new ArrayList();
        }
        {
            [value = value() {attrs.add(value);} 
             [(<COMMA> value = value() {attrs.add(value);})+]]
            {
                if (attrs.size() != 0) {
                    if (controls == null) {
                        // FIXME: test what default search parameter value is
                        controls = new SearchControls(SearchControls.OBJECT_SCOPE, 0, 0, null, false, false);
                    }
                    controls.setReturningAttributes((String[]) attrs.toArray(new String[0]));
                }
            }
            [<QUESTION_MARK> scope()]
        }

void attrDescr():
        {}
        {
            value()
        }

void scope():
        {
            Token t;
            String scope;
        }
        {
            [t = <SCOPE> 
                {
                    scope = t.image;
                    if (controls == null) {
                        // FIXME: test what default search parameter value is
                        controls = new SearchControls(SearchControls.OBJECT_SCOPE, 0, 0, null, false, false);
                    }
                    if (scope.equals("base")) {
                        controls.setSearchScope(SearchControls.OBJECT_SCOPE);
                    } else if (scope.equals("one")) {
                        controls.setSearchScope(SearchControls.ONELEVEL_SCOPE);
                    } else {
                        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
                    }
                }] 

            [<QUESTION_MARK> filter()]

        }

void filter():
        {
            String value;
        }
        {
            [value = value() 
                {
                    FilterParser parser = new FilterParser(new StringReader(value));
                    filter = parser.parse();
                }] 
            [<QUESTION_MARK> extensions()]
        }
void extensions():
        {}
        {
            extension() [(<COMMA> extension())+]
        }

void extension():
        {}
        {
            [<EXCLAM_MARK>] (oid() | <PRE_XTOKEN> oid()) ["=" value()]
        }

String oid():
        {
            StringBuilder value = new StringBuilder();
            String number = null;
            Token t = null;
        }
        {
            number = number() {value.append(number);}
                (t = <PERIOD> {value.append(t.image);}
                 number = number() {value.append(number);})+

            {return value.toString();}
        }

void test():
        {}
        {
            parseURL() "\n" test() | LOOKAHEAD(2) "\n" | "\n" <EOF> | <EOF>
        }
