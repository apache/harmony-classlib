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
 * @author Sergey Burlak
 * @version $Revision$
 */
package javax.swing;

import java.awt.ItemSelectable;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeListener;

public interface ButtonModel extends ItemSelectable {

    public void addItemListener(final ItemListener listener);
    public void removeItemListener(final ItemListener listener);

    public void addActionListener(final ActionListener listener);
    public void removeActionListener(final ActionListener listener);

    public void addChangeListener(final ChangeListener listener);
    public void removeChangeListener(final ChangeListener listener);

    public void setSelected(final boolean selected);
    public boolean isSelected();

    public void setRollover(final boolean rollover);
    public boolean isRollover();

    public void setPressed(final boolean pressed);
    public boolean isPressed();

    public void setEnabled(final boolean enabled);
    public boolean isEnabled();

    public void setArmed(final boolean armed);
    public boolean isArmed();

    public void setMnemonic(final int mnemonic);
    public int getMnemonic();

    public void setGroup(final ButtonGroup group);

    public void setActionCommand(final String command);
    public String getActionCommand();
}

