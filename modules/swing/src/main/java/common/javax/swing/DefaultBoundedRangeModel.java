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
 * @author Evgeniya G. Maenkova
 * @version $Revision$
 */
package javax.swing;

import java.io.Serializable;
import java.util.EventListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class DefaultBoundedRangeModel extends Object implements
        BoundedRangeModel, Serializable {

    protected ChangeEvent       changeEvent;

    protected EventListenerList listenerList;

    int max;

    int min;

    int extent;

    int  value;

    boolean valueIsAdjusting;

    private static final int DEFAULT_MAX = 100;

    public DefaultBoundedRangeModel() {
        this(0, 0, 0, DEFAULT_MAX);
    }

    public DefaultBoundedRangeModel(final int value, final int extent,
                                    final int min, final int max) {

        if (extent < 0 || min > value || value + extent > max) {
            throw new IllegalArgumentException("invalid range properties");
        }
        this.min = min;
        this.max = max;
        this.extent = extent;
        this.value = value;
        changeEvent = new ChangeEvent(this);
        valueIsAdjusting = false;
        listenerList = new EventListenerList();
    }

    public void addChangeListener(final ChangeListener listener) {
        listenerList.add(ChangeListener.class, listener);
    }

    protected void fireStateChanged() {
        ChangeListener[] listeners = getChangeListeners();
        for (int i = 0; i < listeners.length; i++) {
            listeners[i].stateChanged(changeEvent);
        }

    }

    public ChangeListener[] getChangeListeners() {
        return (ChangeListener[])listenerList
                .getListeners(ChangeListener.class);
    }

    public int getExtent() {
        return extent;
    }

    public EventListener[] getListeners(final Class c) {
        return listenerList.getListeners(c);
    }

    public int getMaximum() {
        return max;
    }

    public int getMinimum() {
        return min;
    }

    public int getValue() {
        return value;
    }

    public boolean getValueIsAdjusting() {
        return valueIsAdjusting;
    }

    public void removeChangeListener(final ChangeListener listener) {
        listenerList.remove(ChangeListener.class, listener);
    }

    public void setExtent(final int n) {
        int newExtent = Math.min(Math.max(n, 0), max - value);
        if (newExtent != extent) {
            extent = newExtent;
            fireStateChanged();
        }

    }

    public void setMaximum(final int n) {
        if (max == n) {
            return;
        }
        max = n;
        min = Math.min(n, min);
        extent = Math.min(max - min, extent);
        value = Math.min(value, max - extent);

        fireStateChanged();
    }

    public void setMinimum(final int n) {
        if (n == min) {
            return;
        }
        min = n;
        max = Math.max(n, max);
        extent = Math.min(max - min, extent);
        value = Math.max(value, min);

        fireStateChanged();
    }

    public void setRangeProperties(final int newValue, final int newExtent,
                                   final int newMin, final int newMax,
                                   final boolean adjusting) {
        if (newValue == value && newExtent == extent && newMin == min
            && newMax == max && adjusting == valueIsAdjusting) {
            return;
        }
        value = newValue;
        min = Math.min(newMin, value);
        max = Math.max(newMax, value);
        extent = Math.min(newExtent, max - value);
        valueIsAdjusting = adjusting;
        fireStateChanged();
    }

    public void setValue(final int n) {
        int newValue = Math.min(Math.max(n, min), max - extent);
        if (newValue != value) {
            value = newValue;
            fireStateChanged();
        }

    }

    public void setValueIsAdjusting(final boolean b) {
        if (b != valueIsAdjusting) {
            valueIsAdjusting = b;
            fireStateChanged();
        }
    }

    /*
     * The format of the string is based on 1.5 release behavior
     * which can be revealed using the following code:
     *
     *     Object obj = new DefaultBoundedRangeModel();
     *     System.out.println(obj.toString());
     */
    public String toString() {

        return getClass().getName() + "[" + "value=" + value + ", " + "extent="
               + extent + ", " + "min=" + min + ", " + "max=" + max + ", "
               + "adj=" + valueIsAdjusting + "]";
    }
}