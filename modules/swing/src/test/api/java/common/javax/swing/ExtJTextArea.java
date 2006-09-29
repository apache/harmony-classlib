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
package javax.swing;
import java.awt.Rectangle;

import javax.swing.text.Document;

public class ExtJTextArea extends JTextArea {
    public int flag = 0;
    public boolean wasCallInvalidate = false;
    public boolean wasCallRevalidate = false;

    public ExtJTextArea(){
        super();
    }

    public ExtJTextArea(final Document doc) {
        super(doc);
    }

    public ExtJTextArea(final Document doc, final String text, final int rows, final int columns){
        super(doc, text, rows, columns);
    }

    public ExtJTextArea(final int rows, final int columns) {
        super(rows,columns);
    }

    public ExtJTextArea(final String s){
        super(s);
    }

    public ExtJTextArea(final String text, final int rows, final int columns){
        super(text, rows, columns);
    }

    public void invalidate() {
        wasCallInvalidate = true;
        super.invalidate();
    }

    public void revalidate() {
        wasCallRevalidate = true;
        super.revalidate();
    }

    public void scrollRectToVisible(final Rectangle aRect) {
        flag = 1;
        super.scrollRectToVisible(aRect);
    }

}

