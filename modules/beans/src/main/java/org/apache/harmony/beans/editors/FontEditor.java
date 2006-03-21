/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.1.2.1 $
 */
package org.apache.harmony.beans.editors;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyEditorSupport;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.1.2.1 $
 */

public class FontEditor extends PropertyEditorSupport {
    
    /**
     * 
     * @param source
     */
    public FontEditor(Object source) {
        super(source);
    }

    /**
     */
    public FontEditor() {
        super();
    }
    
    public Component getCustomEditor() {
        return null;
    }
    
    public boolean supportsCustomEditor() {
        return true;
    }
    
    public String getJavaInitializationString() {
        String result = null;
        Font font = (Font) getValue();
        if(font != null) {
            String name = font.getName();
            int style = font.getStyle();
            int size = font.getSize();
            result = "new Font(" + name + "," + style + "," + size + ")";
        }
        return result;
    }
    
    public String[] getTags() {
        return null;
    }
    
    public void setValue(Object value) {
        if(value instanceof Font) {
            super.setValue(value);
        }
    }
    
    public boolean isPaintable() {
        return true;
    }
    
    public void paintValue(Graphics gfx, Rectangle box) {
        Font font = (Font) getValue();
        if(font != null) {
            gfx.setFont(font);
            gfx.drawBytes("Hello".getBytes(), box.x, box.y, box.x + box.width,
                    box.y + box.height);
        }
    }
}
