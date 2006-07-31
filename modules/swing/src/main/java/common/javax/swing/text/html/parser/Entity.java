/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
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
 * @author Evgeniya G. Maenkova
 * @version $Revision$
 */
package javax.swing.text.html.parser;


public final class Entity implements DTDConstants {
    public String name;

    public int type;

    public char[] data;

    boolean isGeneral;

    boolean isParameter;


    public Entity(final String name,
                  final int type,
                  final char[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    Entity(final String name,
           final int type,
           final String data,
           final boolean isGeneral,
           final boolean isParameter) {
        this.name = name;
        this.type = type;
        this.data = data.toCharArray();
        this.isGeneral = isGeneral;
        this.isParameter = isParameter;
    }

    Entity(final String name,
           final char ch) {
        this.name = name;
        this.type = DTDConstants.CDATA;
        this.data = new char[] {ch};
        this.isGeneral = true;
    }


    public String getString() {
        return String.valueOf(data);
    }

    public char[] getData() {
        return data;
    }

    public boolean isGeneral() {
        // TODO: implement
        return isGeneral;
    }

    public boolean isParameter() {
        // TODO: implement
        return isParameter;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static int name2type(final String name) {
        if (name.equals("PUBLIC")) {
            return DTDConstants.PUBLIC;
        } else if (name.equals("SDATA")) {
            return DTDConstants.SDATA;
        } else if (name.equals("PI")) {
            return DTDConstants.PI;
        } else if (name.equals("STARTTAG")) {
            return DTDConstants.STARTTAG;
        } else if (name.equals("ENDTAG")) {
            return DTDConstants.ENDTAG;
        } else if (name.equals("MS")) {
            return DTDConstants.MS;
        } else if (name.equals("MD")) {
            return DTDConstants.MD;
        } else if (name.equals("SYSTEM")) {
            return DTDConstants.SYSTEM;
        } else {
            return DTDConstants.CDATA;
        }
    }

}

