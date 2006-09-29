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
 * @author Evgeniya G. Maenkova
 * @version $Revision$
 */
package javax.swing.text;

import java.awt.Color;
import java.awt.Font;

public interface StyledDocument extends Document {
    Color getForeground(AttributeSet attributes);
    Color getBackground(AttributeSet attributes);
    Font getFont(AttributeSet attributes);

    Style addStyle(String styleName, Style parent);
    Style getStyle(String styleName);
    void removeStyle(String styleName);

    void setLogicalStyle(int offset, Style style);
    Style getLogicalStyle(int offset);

    Element getCharacterElement(int offset);
    Element getParagraphElement(int offset);
    void setCharacterAttributes(int offset, int length,
                                AttributeSet attributes,
                                boolean replace);
    void setParagraphAttributes(int offset, int length,
                                AttributeSet attributes,
                                boolean replace);
}

