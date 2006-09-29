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
 * @author Ilya S. Okomin
 * @version $Revision$
 */
package java.awt.font;

public abstract class LineMetrics {

    public abstract float[] getBaselineOffsets();

    public abstract int getNumChars();

    public abstract int getBaselineIndex();

    public abstract float getUnderlineThickness();

    public abstract float getUnderlineOffset();

    public abstract float getStrikethroughThickness();

    public abstract float getStrikethroughOffset();

    public abstract float getLeading();

    public abstract float getHeight();

    public abstract float getDescent();

    public abstract float getAscent();

}

