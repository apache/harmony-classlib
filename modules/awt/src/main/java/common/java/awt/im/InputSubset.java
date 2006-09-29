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
 * @author Dmitry A. Durnev
 * @version $Revision$
 */
package java.awt.im;

public final class InputSubset extends Character.Subset {

    public static final InputSubset LATIN = new InputSubset("LATIN");

    public static final InputSubset 
        LATIN_DIGITS = new InputSubset("LATIN_DIGITS");

    public static final InputSubset 
        TRADITIONAL_HANZI = new InputSubset("TRADITIONAL_HANZI");

    public static final InputSubset 
        SIMPLIFIED_HANZI = new InputSubset("SIMPLIFIED_HANZI");

    public static final InputSubset KANJI = new InputSubset("KANJI");

    public static final InputSubset HANJA = new InputSubset("HANJA");

    public static final InputSubset 
        HALFWIDTH_KATAKANA = new InputSubset("HALFWIDTH_KATAKANA");

    public static final InputSubset 
        FULLWIDTH_LATIN = new InputSubset("FULLWIDTH_LATIN");

    public static final InputSubset 
        FULLWIDTH_DIGITS = new InputSubset("FULLWIDTH_DIGITS");

    private InputSubset(String name) {
        super(name);
    }
}

