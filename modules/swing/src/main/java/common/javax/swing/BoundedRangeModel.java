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
 * @author Evgeniya G. Maenkova
 * @version $Revision$
 */
package javax.swing;

import javax.swing.event.ChangeListener;

public interface BoundedRangeModel {

    void addChangeListener(final ChangeListener x);

    int getExtent();

    int getMaximum();

    int getMinimum();

    int getValue();

    boolean getValueIsAdjusting();

    void removeChangeListener(final ChangeListener x);

    void setExtent(final int newExtent);

    void setMaximum(final int newMaximum);

    void setMinimum(final int newMinimum);

    void setRangeProperties(final int value, final int extent, final int min,
                            final int max,
            final boolean adjusting);

    void setValue(final int newValue);

    void setValueIsAdjusting(final boolean b);

}