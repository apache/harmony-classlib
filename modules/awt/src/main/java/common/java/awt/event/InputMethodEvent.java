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
 * @author Michael Danilov
 * @version $Revision$
 */
package java.awt.event;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.font.TextHitInfo;
import java.text.AttributedCharacterIterator;

public class InputMethodEvent extends AWTEvent {

    private static final long serialVersionUID = 4727190874778922661L;

    public static final int INPUT_METHOD_FIRST = 1100;

    public static final int INPUT_METHOD_TEXT_CHANGED = 1100;

    public static final int CARET_POSITION_CHANGED = 1101;

    public static final int INPUT_METHOD_LAST = 1101;

    private AttributedCharacterIterator text;
    private TextHitInfo visiblePosition;
    private TextHitInfo caret;
    private int committedCharacterCount;
    private long when;

    public InputMethodEvent(Component src, int id,
                            TextHitInfo caret, 
                            TextHitInfo visiblePos) {
        this(src, id, null, 0, caret, visiblePos);
    }

    public InputMethodEvent(Component src, int id, 
                            AttributedCharacterIterator text,
                            int commitedCharCount,
                            TextHitInfo caret, 
                            TextHitInfo visiblePos) {
        this(src, id, 0l, text, commitedCharCount, caret, visiblePos);
    }

    public InputMethodEvent(Component src, int id, long when,
                            AttributedCharacterIterator text, 
                            int committedCharacterCount,
                            TextHitInfo caret,
                            TextHitInfo visiblePos) {
        super(src, id);

        if ((id < INPUT_METHOD_FIRST) || (id > INPUT_METHOD_LAST)) {
            throw new IllegalArgumentException("Wrong event id");
        }
        if ((id == CARET_POSITION_CHANGED) && (text != null)) {
            throw new IllegalArgumentException(
                    "Text must be null for CARET_POSITION_CHANGED");
        }
        if ((text != null) &&
                ((committedCharacterCount < 0) ||
                 (committedCharacterCount > 
                        (text.getEndIndex() - text.getBeginIndex())))) {
            throw new IllegalArgumentException("Wrong committedCharacterCount");
        }

        this.when = when;
        this.text = text;
        this.caret = caret;
        this.visiblePosition = visiblePos;
        this.committedCharacterCount = committedCharacterCount;
    }

    public TextHitInfo getCaret() {
        return caret;
    }

    public int getCommittedCharacterCount() {
        return committedCharacterCount;
    }

    public AttributedCharacterIterator getText() {
        return text;
    }

    public TextHitInfo getVisiblePosition() {
        return visiblePosition;
    }

    public long getWhen() {
        return when;
    }

    public void consume() {
        super.consume();
    }

    public boolean isConsumed() {
        return super.isConsumed();
    }

    public String paramString() {
        /* The format is based on 1.5 release behavior 
         * which can be revealed by the following code:
         * 
         * InputMethodEvent e = new InputMethodEvent(new Component(){},
         *          InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
         *          TextHitInfo.leading(1), TextHitInfo.trailing(2));
         * System.out.println(e);
         */
        String typeString = null;

        switch (id) {
        case INPUT_METHOD_TEXT_CHANGED:
            typeString = "INPUT_METHOD_TEXT_CHANGED";
            break;
        case CARET_POSITION_CHANGED:
            typeString = "CARET_POSITION_CHANGED";
            break;
        default:
            typeString = "unknown type";
        }

        return typeString + ",text=" + text + 
                ",commitedCharCount=" + committedCharacterCount +
                ",caret=" + caret + ",visiblePosition=" + visiblePosition;
    }

}
