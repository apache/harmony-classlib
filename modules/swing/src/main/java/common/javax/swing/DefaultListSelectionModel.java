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
 * @author Anton Avtamonov
 * @version $Revision$
 */
package javax.swing;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.EventListener;

import javax.swing.event.EventListenerList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.harmony.awt.gl.MultiRectArea;


public class DefaultListSelectionModel implements ListSelectionModel, Cloneable, Serializable {
    private static final int NOT_SET = -1;

    private static class Segment extends Rectangle {
        public Segment(final int begin, final int end) {
            super(Math.min(begin, end), 0, Math.abs(end - begin) + 1, 1);
        }

        public void add(final Segment s) {
            if (s == null || s.isEmpty()) {
                return;
            } else if (isEmpty()) {
                setBounds(s);
            } else {
                setBounds(this.union(s));
            }
        }

        public int getBeginIndex() {
            return x;
        }

        public int getEndIndex() {
            return x + width - 1;
        }

        public int getLength() {
            return width;
        }
    }

    private static class Selection extends MultiRectArea implements Cloneable {
        private static final Rectangle EMPTY_RECTANGLE = new Rectangle();

        public Selection() {
        }

        private Selection(final Selection s) {
            super(s);
        }

        public void clear() {
            intersect(EMPTY_RECTANGLE);
        }

        public boolean contains(final int index) {
            return contains(index, 0);
        }

        public void insertIndices(final int index, final int length, final boolean multiSelectionAllowed) {
            MultiRectArea modified = new MultiRectArea();
            Rectangle[] rects = getRectangles();
            for (int i = 0; i < rects.length; i++) {
                Rectangle rect = (Rectangle)rects[i].clone();
                if (index < rect.x) {
                    rect.x += length;
                } else if (rect.x <= index && index < rect.x + rect.width) {
                    if (multiSelectionAllowed) {
                        rect.width += length;
                    } else {
                        rect.x += length;
                    }
                }
                modified.add(rect);
            }
            clear();
            add(modified);
        }

        public void removeIndices(final int index, final int length) {
            MultiRectArea modified = new MultiRectArea();
            Rectangle[] rects = getRectangles();
            for (int i = 0; i < rects.length; i++) {
                Rectangle rect = rects[i];
                int rectEnd = rect.x + rect.width - length - 1;
                if (index <= rect.x) {
                    if (rectEnd >= index) {
                        int rectBegin = rect.x - length < index ? index : rect.x - length;
                        modified.add(new Segment(rectBegin, rectEnd));
                    }
                } else if (rect.x < index && index < rect.x + rect.width) {
                    if (rectEnd < index - 1) {
                        rectEnd = index - 1;
                    }
                    modified.add(new Segment(rect.x, rectEnd));
                } else {
                    modified.add((Rectangle)rect.clone());
                }
            }
            clear();
            add(modified);
        }

        public Segment getDifferenceBounds(final Selection anotherSelection) {
            MultiRectArea thisFromAnother = MultiRectArea.subtract(this, anotherSelection);
            MultiRectArea anotherFromThis = MultiRectArea.subtract(anotherSelection, this);
            MultiRectArea diff = MultiRectArea.union(thisFromAnother, anotherFromThis);
            if (diff.isEmpty()) {
                return null;
            }
            Rectangle diffBounds = diff.getBounds();
            return new Segment(diffBounds.x, diffBounds.x + diffBounds.width - 1);
        }

        public int getBeginIndex() {
            return getBounds().x;
        }

        public int getEndIndex() {
            Rectangle bounds = getBounds();
            return bounds.x + bounds.width - 1;
        }


        public Object clone() {
            return new Selection(this);
        }
    }

    protected boolean leadAnchorNotificationEnabled = true;
    protected EventListenerList listenerList = new EventListenerList();

    private Selection selection = new Selection();
    private int anchorSelectionIndex = NOT_SET;
    private int leadSelectionIndex = NOT_SET;
    private Segment adjustingInterval;
    private int selectionMode = MULTIPLE_INTERVAL_SELECTION;
    private boolean valueIsAdjusting;

    public void setSelectionInterval(final int intervalEnd1, final int intervalEnd2) {
        if (!isValidInterval(intervalEnd1, intervalEnd2)) {
            return;
        }

        Selection oldSelection = (Selection)selection.clone();
        selection.clear();

        setSelectionAndUpdateLeadAnchor(intervalEnd1, intervalEnd2, oldSelection);
    }

    public void addSelectionInterval(final int intervalEnd1, final int intervalEnd2) {
        if (!isValidInterval(intervalEnd1, intervalEnd2)) {
            return;
        }

        Selection oldSelection = (Selection)selection.clone();
        if (selectionMode == SINGLE_SELECTION || selectionMode == SINGLE_INTERVAL_SELECTION) {
            selection.clear();
        }

        setSelectionAndUpdateLeadAnchor(intervalEnd1, intervalEnd2, oldSelection);
    }

    public void removeSelectionInterval(final int intervalEnd1, final int intervalEnd2) {
        if (!isValidInterval(intervalEnd1, intervalEnd2)) {
            return;
        }

        Segment interval = new Segment(intervalEnd1, intervalEnd2);
        Selection oldSelection = (Selection)selection.clone();

        selection.substract(interval);

        int oldAnchor = anchorSelectionIndex;
        int oldLead = leadSelectionIndex;

        anchorSelectionIndex = intervalEnd1;
        leadSelectionIndex = intervalEnd2;

        doNotification(selection.getDifferenceBounds(oldSelection), oldAnchor, oldLead);
    }

    public void clearSelection() {
        Selection oldSelection = (Selection)selection.clone();
        selection.clear();

        doNotification(selection.getDifferenceBounds(oldSelection), anchorSelectionIndex, leadSelectionIndex);
    }

    public boolean isSelectedIndex(final int index) {
        return selection.contains(index);
    }

    public boolean isSelectionEmpty() {
        return selection.isEmpty();
    }

    public int getMaxSelectionIndex() {
        return isSelectionEmpty() ? NOT_SET : selection.getEndIndex();
    }

    public int getMinSelectionIndex() {
        return isSelectionEmpty() ? NOT_SET : selection.getBeginIndex();
    }


    public void insertIndexInterval(final int index, final int length, final boolean before) {
        if (!isValidInterval(index, length)) {
            return;
        }

        Selection oldSelection = (Selection)selection.clone();
        int insertionIndex = before ? index : index + 1;
        selection.insertIndices(index, length, selectionMode != SINGLE_SELECTION);

        int oldAnchor = anchorSelectionIndex;
        int oldLead = leadSelectionIndex;

        if (anchorSelectionIndex >= insertionIndex) {
            anchorSelectionIndex += length;
        }
        if (leadSelectionIndex >= insertionIndex) {
            leadSelectionIndex += length;
        }

        doNotification(selection.getDifferenceBounds(oldSelection), oldAnchor, oldLead);
    }

    public void removeIndexInterval(final int intervalEnd1, final int intervalEnd2) {
        if (!isValidInterval(intervalEnd1, intervalEnd2)) {
            return;
        }

        Selection oldSelection = (Selection)selection.clone();

        Segment removalInterval = new Segment(intervalEnd1, intervalEnd2);
        selection.removeIndices(removalInterval.getBeginIndex(), removalInterval.getLength());

        int oldAnchor = anchorSelectionIndex;
        int oldLead = leadSelectionIndex;

        anchorSelectionIndex = adjustLeadAnchorIndexForIndicesRemoval(anchorSelectionIndex, removalInterval);
        leadSelectionIndex = adjustLeadAnchorIndexForIndicesRemoval(leadSelectionIndex, removalInterval);

        doNotification(selection.getDifferenceBounds(oldSelection), oldAnchor, oldLead);
    }

    public void setAnchorSelectionIndex(final int anchorIndex) {
        int oldAnchor = anchorSelectionIndex;

        anchorSelectionIndex = anchorIndex;

        doNotification(null, oldAnchor, leadSelectionIndex);
    }

    public int getAnchorSelectionIndex() {
        return anchorSelectionIndex;
    }

    public void setLeadSelectionIndex(final int leadIndex) {
        if (leadIndex < 0 && anchorSelectionIndex < 0) {
            leadSelectionIndex = leadIndex;
        }
        if (leadIndex < 0 || anchorSelectionIndex < 0) {
            return;
        }

        Selection oldSelection = (Selection)selection.clone();
        int oldLead = leadSelectionIndex;
        leadSelectionIndex = leadIndex;

        Segment oldSegment = new Segment(anchorSelectionIndex, oldLead);
        Segment newSegment = new Segment(anchorSelectionIndex, leadSelectionIndex);
        if (selection.contains(anchorSelectionIndex)) {
            selection.substract(oldSegment);
            selection.add(newSegment);
        } else {
            selection.add(oldSegment);
            selection.substract(newSegment);
        }

        doNotification(selection.getDifferenceBounds(oldSelection), anchorSelectionIndex, oldLead);
    }

    public void moveLeadSelectionIndex(final int leadIndex) {
        if (leadIndex < 0 || leadSelectionIndex == leadIndex) {
            return;
        }

        int oldIndex = leadSelectionIndex;
        leadSelectionIndex = leadIndex;
        doNotification(null, anchorSelectionIndex, oldIndex);
    }

    public int getLeadSelectionIndex() {
        return leadSelectionIndex;
    }

    public void setLeadAnchorNotificationEnabled(final boolean enabled) {
        leadAnchorNotificationEnabled = enabled;
    }

    public boolean isLeadAnchorNotificationEnabled() {
        return leadAnchorNotificationEnabled;
    }

    public int getSelectionMode() {
        return selectionMode;
    }

    public void setSelectionMode(final int selectionMode) {
        if (selectionMode != SINGLE_SELECTION
            && selectionMode != SINGLE_INTERVAL_SELECTION
            && selectionMode != MULTIPLE_INTERVAL_SELECTION) {

            throw new IllegalArgumentException("Incorrect selection mode is specified");
        }

        this.selectionMode = selectionMode;
    }

    public void setValueIsAdjusting(final boolean isAdjusting) {
        valueIsAdjusting = isAdjusting;
        if (!isAdjusting) {
            fireValueChanged(isAdjusting);
        }
    }

    public boolean getValueIsAdjusting() {
        return valueIsAdjusting;
    }

    public void addListSelectionListener(final ListSelectionListener l) {
        listenerList.add(ListSelectionListener.class, l);
    }

    public void removeListSelectionListener(final ListSelectionListener l) {
        listenerList.remove(ListSelectionListener.class, l);
    }

    public ListSelectionListener[] getListSelectionListeners() {
        return (ListSelectionListener[])getListeners(ListSelectionListener.class);
    }

    public EventListener[] getListeners(final Class listenerType) {
        return listenerList.getListeners(listenerType);
    }

    public Object clone() throws CloneNotSupportedException {
        DefaultListSelectionModel result = new DefaultListSelectionModel();

        result.anchorSelectionIndex = anchorSelectionIndex;
        result.leadSelectionIndex = leadSelectionIndex;
        result.leadAnchorNotificationEnabled = leadAnchorNotificationEnabled;
        result.valueIsAdjusting = valueIsAdjusting;
        result.selectionMode = selectionMode;

        result.selection = (Selection)selection.clone();

        return result;
    }

    public String toString() {
        return getClass().toString() + ": leadIndex=" + leadSelectionIndex
                                     + ", anchorIndex=" + anchorSelectionIndex
                                     + ", isEmpty=" + isSelectionEmpty();
    }

    protected void fireValueChanged(final boolean isAdjusting) {
        if (adjustingInterval != null) {
            fireValueChanged(adjustingInterval.getBeginIndex(), adjustingInterval.getEndIndex(), isAdjusting);
            adjustingInterval = null;
        }
    }

    protected void fireValueChanged(final int firstIndex, final int lastIndex) {
        fireValueChanged(firstIndex, lastIndex, getValueIsAdjusting());
    }

    protected void fireValueChanged(final int firstIndex, final int lastIndex, final boolean isAdjusting) {
        fireListSelectionEvent(new ListSelectionEvent(this, firstIndex, lastIndex, isAdjusting));
    }


    private void fireListSelectionEvent(final ListSelectionEvent event) {
        ListSelectionListener[] listeners = getListSelectionListeners();
        for (int i = 0; i < listeners.length; i++) {
            listeners[i].valueChanged(event);
        }
    }

    private void doNotification(final Segment changedInterval, final int oldAnchorIndex, final int oldLeadIndex) {
        Segment fireInterval = changedInterval;

        if (leadAnchorNotificationEnabled) {
            Segment anchorLeadInterval = getLeadAnchorInterval(oldAnchorIndex, oldLeadIndex);
            fireInterval = mergeIntervals(fireInterval, anchorLeadInterval);
        }

        if (fireInterval == null) {
            return;
        }

        if (valueIsAdjusting) {
            adjustingInterval = mergeIntervals(adjustingInterval, fireInterval);
        }
        fireValueChanged(fireInterval.getBeginIndex(), fireInterval.getEndIndex());
    }

    private Segment mergeIntervals(final Segment interval1, final Segment interval2) {
        Segment result = interval1;

        if (result != null) {
            result.add(interval2);
        } else {
            result = interval2;
        }

        return result;
    }

    private Segment getLeadAnchorInterval(final int oldAnchorIndex, final int oldLeadIndex) {
        Segment anchorInterval = createInterval(oldAnchorIndex, anchorSelectionIndex);
        Segment leadInterval = createInterval(oldLeadIndex, leadSelectionIndex);

        return mergeIntervals(anchorInterval, leadInterval);
    }

    private Segment createInterval(final int oldLeadAnchorIndex, final int newLeadAnchorIndex) {
        if (oldLeadAnchorIndex == newLeadAnchorIndex) {
            return null;
        }
        if (oldLeadAnchorIndex == NOT_SET) {
            return new Segment(newLeadAnchorIndex, newLeadAnchorIndex);
        } else {
            if (newLeadAnchorIndex == NOT_SET) {
                return new Segment(oldLeadAnchorIndex, oldLeadAnchorIndex);
            } else {
                return new Segment(oldLeadAnchorIndex, newLeadAnchorIndex);
            }
        }
    }

    private int adjustLeadAnchorIndexForIndicesRemoval(final int leadAnchorIndex, final Segment removalInterval) {
        int result = leadAnchorIndex;
        if (result >= removalInterval.getBeginIndex()) {
            if (result < removalInterval.getEndIndex()) {
                result = removalInterval.getBeginIndex() - 1;
            } else {
                result -= removalInterval.getLength();
            }
        }

        return result;
    }

    private void setSelectionAndUpdateLeadAnchor(final int intervalEnd1, final int intervalEnd2,
                                                 final Selection oldSelection) {
        int oldAnchor = anchorSelectionIndex;
        int oldLead = leadSelectionIndex;

        if (selectionMode == SINGLE_SELECTION) {
            anchorSelectionIndex = intervalEnd2;
        } else {
            anchorSelectionIndex = intervalEnd1;
        }
        leadSelectionIndex = intervalEnd2;
        selection.add(new Segment(anchorSelectionIndex, leadSelectionIndex));

        doNotification(selection.getDifferenceBounds(oldSelection), oldAnchor, oldLead);
    }

    private boolean isValidInterval(final int n1, final int n2) {
        return n1 >= 0 && n2 >= 0;
    }
}
