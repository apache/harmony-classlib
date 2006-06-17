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
 * @author Michael Danilov
 * @version $Revision$
 */
package java.awt.event;

import java.awt.Component;
import java.awt.Rectangle;

public class PaintEvent extends ComponentEvent {

    private static final long serialVersionUID = 1267492026433337593L;

    public static final int PAINT_FIRST = 800;

    public static final int PAINT_LAST = 801;

    public static final int PAINT = 800;

    public static final int UPDATE = 801;

    private Rectangle updateRect;

    public PaintEvent(Component source, int id, Rectangle updateRect) {
        super(source, id);

        this.updateRect = updateRect;
    }

    public Rectangle getUpdateRect() {
        return updateRect;
    }

    public void setUpdateRect(Rectangle updateRect) {
        this.updateRect = updateRect;
    }

    public String paramString() {
        /* The format is based on 1.5 release behavior 
         * which can be revealed by the following code:
         * 
         * PaintEvent e = new PaintEvent(new Component(){}, 
         *          PaintEvent.PAINT, new Rectangle(0, 0, 10, 20)); 
         * System.out.println(e);
         */

        String typeString = null;

        switch (id) {
        case PAINT:
            typeString = "PAINT";
            break;
        case UPDATE:
            typeString = "UPDATE";
            break;
        default:
            typeString = "unknown type";
        }

        return typeString + ",updateRect=" + updateRect;
    }

}
