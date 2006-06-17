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
package org.apache.harmony.awt.text;

/**
 *
 * Containts names of text actions.
 */
public interface ActionNames {
    String backwardAction = "caret-backward";

    String beepAction = "beep";

    String beginAction = "caret-begin";

    String beginLineAction = "caret-begin-line";

    String beginParagraphAction = "caret-begin-paragraph";

    String beginWordAction = "caret-begin-word";

    String copyAction = "copy-to-clipboard";

    String cutAction = "cut-to-clipboard";

    String defaultKeyTypedAction = "default-typed";

    String deleteNextCharAction = "delete-next";

    String deletePrevCharAction = "delete-previous";

    String downAction = "caret-down";

    String dumpModelAction = "dump-model";

    String endAction = "caret-end";

    String endLineAction = "caret-end-line";

    String endParagraphAction = "caret-end-paragraph";

    String endWordAction = "caret-end-word";

    String forwardAction = "caret-forward";

    String insertBreakAction = "insert-break";

    String insertContentAction = "insert-content";

    String insertTabAction = "insert-tab";

    String nextWordAction = "caret-next-word";

    String pageDownAction = "page-down";

    String pageUpAction = "page-up";

    String pasteAction = "paste-from-clipboard";

    String previousWordAction = "caret-previous-word";

    String readOnlyAction = "set-read-only";

    String selectAllAction = "select-all";

    String selectionBackwardAction = "selection-backward";

    String selectionBeginAction = "selection-begin";

    String selectionBeginLineAction = "selection-begin-line";

    String selectionBeginParagraphAction = "selection-begin-paragraph";

    String selectionBeginWordAction = "selection-begin-word";

    String selectionDownAction = "selection-down";

    String selectionEndAction = "selection-end";

    String selectionEndLineAction = "selection-end-line";

    String selectionEndParagraphAction = "selection-end-paragraph";

    String selectionEndWordAction = "selection-end-word";

    String selectionForwardAction = "selection-forward";

    String selectionNextWordAction = "selection-next-word";

    String selectionPageDownAction = "selection-page-down";

    String selectionPageLeftAction = "selection-page-left";

    String selectionPageRightAction = "selection-page-right";

    String selectionPageUpAction = "selection-page-up";

    String selectionPreviousWordAction = "selection-previous-word";

    String selectionUpAction = "selection-up";

    String selectLineAction = "select-line";

    String selectParagraphAction = "select-paragraph";

    String selectWordAction = "select-word";

    String toggleComponentOrientationAction = "toggle-componentOrientation";

    String unselectAction = "unselect";

    String upAction = "caret-up";

    String writableAction = "set-writable";

    int NONE = 0;

    int COPY = 1;

    int MOVE = 2;

    int COPY_OR_MOVE = 3;
}

