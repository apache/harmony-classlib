/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.beans.editors;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyEditorSupport;

public class FontEditor extends PropertyEditorSupport {

    public FontEditor(Object source) {
        super(source);
    }

    public FontEditor() {
        super();
    }

    @Override
    public Component getCustomEditor() {
        return null;
    }

    @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public String getJavaInitializationString() {
        String result = null;
        Font font = (Font) getValue();
        if (font != null) {
            String name = font.getName();
            int style = font.getStyle();
            int size = font.getSize();
            result = "new Font(" + name + "," + style + "," + size + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        }
        return result;
    }

    @Override
    public String[] getTags() {
        return null;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Font) {
            super.setValue(value);
        }
    }

    @Override
    public boolean isPaintable() {
        return true;
    }

    @Override
    public void paintValue(Graphics gfx, Rectangle box) {
        Font font = (Font) getValue();
        if (font != null) {
            gfx.setFont(font);
            gfx.drawBytes("Hello".getBytes(), box.x, box.y, box.x + box.width, //$NON-NLS-1$
                    box.y + box.height);
        }
    }
}
