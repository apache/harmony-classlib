/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Elena V. Sayapina 
 * @version $Revision: 1.4 $ 
 */ 

package javax.print.attribute;

import java.io.Serializable;
import java.util.Locale;


public abstract class TextSyntax implements Cloneable, Serializable {


    private String text;

    private Locale locale;


    protected TextSyntax(String textValue, Locale textLocale) {

        if (textValue == null) {
            throw new NullPointerException("Text is null");
        } else {
            text = textValue;
        }

        if (textLocale == null) {
            locale = Locale.getDefault();
        } else {
            locale = textLocale;
        }
    }
    
    
    public boolean equals(Object object) {

        if ((object instanceof TextSyntax) &&
                text.equals( ((TextSyntax) object).text ) &&
                    locale.equals( ((TextSyntax) object).locale )) {
            return true;
        }
        return false;
    }
    
    public Locale getLocale() {
        return locale;
    } 

    public String getValue() {
        return text;
    }

    public int hashCode() {
        return text.hashCode() + locale.hashCode();
    }   
    
    public String toString(){
        return text;
    }
           
}
