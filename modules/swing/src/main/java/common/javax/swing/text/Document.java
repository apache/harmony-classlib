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
 * @author Alexey A. Ivanov
 * @version $Revision$
 */
package javax.swing.text;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;

public interface Document {
    String StreamDescriptionProperty = "stream";

    String TitleProperty             = "title";

    void addDocumentListener(final DocumentListener listener);

    void addUndoableEditListener(UndoableEditListener listener);

    Position createPosition(int offset) throws BadLocationException;

    Element getDefaultRootElement();

    Position getEndPosition();

    int getLength();

    Object getProperty(Object key);

    Element[] getRootElements();

    Position getStartPosition();

    String getText(int offset, int length) throws BadLocationException;

    void getText(int offset, int length, Segment text)
            throws BadLocationException;

    void insertString(int offset, String text, AttributeSet attrs)
            throws BadLocationException;

    void putProperty(Object key, Object value);

    void remove(int offset, int length) throws BadLocationException;

    void removeDocumentListener(DocumentListener listener);

    void removeUndoableEditListener(UndoableEditListener listener);

    void render(Runnable r);
}