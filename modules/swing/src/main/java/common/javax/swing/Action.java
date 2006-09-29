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

/**
 * @author Sergey Burlak
 * @version $Revision$
 */

package javax.swing;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public interface Action extends ActionListener {

    public static final String DEFAULT = "Default";
    public static final String NAME = "Name";
    public static final String SHORT_DESCRIPTION = "ShortDescription";
    public static final String LONG_DESCRIPTION = "LongDescription";
    public static final String SMALL_ICON = "SmallIcon";
    public static final String ACTION_COMMAND_KEY = "ActionCommandKey";
    public static final String ACCELERATOR_KEY = "AcceleratorKey";
    public static final String MNEMONIC_KEY = "MnemonicKey";

    public void putValue(final String key, final Object value);

    public Object getValue(final String key);

    public void removePropertyChangeListener(final PropertyChangeListener l);

    public void addPropertyChangeListener(final PropertyChangeListener l);

    public void setEnabled(final boolean b);

    public boolean isEnabled();

}


