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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyEditorSupport;

public class ColorEditor extends PropertyEditorSupport {

    public ColorEditor(Object source) {
        super(source);
    }

    public ColorEditor() {
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
        Color color = (Color) getValue();
        if (color != null) {
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            result = "new Color(" + red + "," + green + "," + blue + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        }
        return result;
    }

    @Override
    public String[] getTags() {
        return null;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Color) {
            super.setValue(value);
        }
    }

    @Override
    public boolean isPaintable() {
        return true;
    }

    @Override
    public void paintValue(Graphics gfx, Rectangle box) {
        Color color = (Color) getValue();
        if (color != null) {
            gfx.setColor(color);
            gfx.drawRect(box.x, box.y, box.x + box.width, box.y + box.height);
        }
    }
}
