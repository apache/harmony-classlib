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

public final class GlyphJustificationInfo {

    public static final int PRIORITY_KASHIDA = 0;

    public static final int PRIORITY_WHITESPACE = 1;

    public static final int PRIORITY_INTERCHAR = 2;

    public static final int PRIORITY_NONE = 3;

    public final boolean growAbsorb;

    public final float growLeftLimit;

    public final float growRightLimit;

    public final int growPriority;

    public final boolean shrinkAbsorb;

    public final float shrinkLeftLimit;

    public final float shrinkRightLimit;

    public final int shrinkPriority;

    public final float weight;

    public GlyphJustificationInfo(float weight, boolean growAbsorb, int growPriority,
            float growLeftLimit, float growRightLimit, boolean shrinkAbsorb,
            int shrinkPriority, float shrinkLeftLimit, float shrinkRightLimit) {

        if (weight < 0) {
            throw new IllegalArgumentException("weight must be a " + "positive number");
        }
        this.weight = weight;

        if (growLeftLimit < 0) {
            throw new IllegalArgumentException("growLeftLimit must be a " + "positive number");
        }
        this.growLeftLimit = growLeftLimit;

        if (growRightLimit < 0) {
            throw new IllegalArgumentException("growRightLimit must be a " + "positive number");
        }
        this.growRightLimit = growRightLimit;

        if ((shrinkPriority < 0) || (shrinkPriority > PRIORITY_NONE)) {
            throw new IllegalArgumentException("incorrect value for "
                    + "shrinkPriority, more than PRIORITY_NONE or less than "
                    + "PRIORITY_KASHIDA value");
        }
        this.shrinkPriority = shrinkPriority;

        if ((growPriority < 0) || (growPriority > PRIORITY_NONE)) {
            throw new IllegalArgumentException("incorrect value for "
                    + "growPriority, more than PRIORITY_NONE or less than "
                    + "PRIORITY_KASHIDA value");
        }
        this.growPriority = growPriority;

        if (shrinkLeftLimit < 0) {
            throw new IllegalArgumentException("shrinkLeftLimit must be a " + "positive number");
        }
        this.shrinkLeftLimit = shrinkLeftLimit;

        if (shrinkRightLimit < 0) {
            throw new IllegalArgumentException("shrinkRightLimit must be a "
                    + "positive number");
        }
        this.shrinkRightLimit = shrinkRightLimit;

        this.shrinkAbsorb = shrinkAbsorb;
        this.growAbsorb = growAbsorb;
    }
}
