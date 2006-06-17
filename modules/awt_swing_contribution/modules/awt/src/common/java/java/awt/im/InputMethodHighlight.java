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
 * @author Pavel Dolgov
 * @version $Revision$
 */
package java.awt.im;

import java.util.Map;

public class InputMethodHighlight {

    public static final int RAW_TEXT = 0;

    public static final int CONVERTED_TEXT = 1;

    public static final InputMethodHighlight
        UNSELECTED_RAW_TEXT_HIGHLIGHT = null;

    public static final InputMethodHighlight
        SELECTED_RAW_TEXT_HIGHLIGHT = null;

    public static final InputMethodHighlight
        UNSELECTED_CONVERTED_TEXT_HIGHLIGHT = null;

    public static final InputMethodHighlight
        SELECTED_CONVERTED_TEXT_HIGHLIGHT = null;

    private InputMethodHighlight() {
    }

    public InputMethodHighlight(boolean selected, int state, int variation) {
    }

    public InputMethodHighlight(boolean selected, int state, int variation, Map style) {
    }

    public InputMethodHighlight(boolean selected, int state) {
    }

    public int getState() {
        return 0;
    }

    public Map getStyle() {
        return null;
    }

    public int getVariation() {
        return 0;
    }

    public boolean isSelected() {
        return false;
    }
}

