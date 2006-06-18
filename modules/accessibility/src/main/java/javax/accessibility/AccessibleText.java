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
 * @author Dennis Ushakov
 * @version $Revision$
 */

package javax.accessibility;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.text.AttributeSet;

public interface AccessibleText {
    static final int CHARACTER = 1;
    static final int WORD = 2;
    static final int SENTENCE = 3;
    int getIndexAtPoint(Point p);
    Rectangle getCharacterBounds(int i);
    int getCharCount();
    int getCaretPosition();
    String getAtIndex(int part, int index);
    String getAfterIndex(int part, int index);
    String getBeforeIndex(int part, int index);
    AttributeSet getCharacterAttribute(int i);
    int getSelectionStart();
    int getSelectionEnd();
    String getSelectedText();
}
