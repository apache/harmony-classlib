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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyEditorSupport;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.1.2.1 $
 */

public class ColorEditor extends PropertyEditorSupport {
    
    /**
     * 
     * @param source
     */
    public ColorEditor(Object source) {
        super(source);
    }

    /**
     */
    public ColorEditor() {
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
        Color color = (Color) getValue();
        if(color != null) {
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            result = "new Color(" + red + "," + green + "," + blue + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        }
        return result;
    }
    
    public String[] getTags() {
        return null;
    }
    
    public void setValue(Object value) {
        if(value instanceof Color) {
            super.setValue(value);
        }
    }
    
    public boolean isPaintable() {
        return true;
    }
    
    public void paintValue(Graphics gfx, Rectangle box) {
        Color color = (Color) getValue();
        if(color != null) {
            gfx.setColor(color);
            gfx.drawRect(box.x, box.y, box.x + box.width, box.y + box.height);
        }
    }
}
