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
 * @author Michael Danilov
 * @version $Revision$
 */
package java.awt.dnd;

import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.peer.DragSourceContextPeer;
import java.io.Serializable;
import java.util.TooManyListenersException;

public class DragSourceContext implements DragSourceListener,
        DragSourceMotionListener, Serializable
{

    private static final long serialVersionUID = -115407898692194719L;

    protected static final int DEFAULT = 0;

    protected static final int ENTER = 1;

    protected static final int OVER = 2;

    protected static final int CHANGED = 3;

    private static final int EXIT = DEFAULT;

    private final DragSource dragSource;
    private final DragGestureEvent trigger;
    private final Transferable transferable;
    private final Component component;
    private final DragSourceContextPeer peer;

    private int sourceAction;
    private DragSourceListener listener;
    private Cursor cursor;
    private boolean defaultCursor;
    private int lastTargetAction;
    private int lastStatus;

    public DragSourceContext(DragSourceContextPeer dscp, DragGestureEvent trigger,
            Cursor dragCursor, Image dragImage, Point offset,
            Transferable t, DragSourceListener dsl)
    {
        if (dscp == null) {
            throw new NullPointerException("Context peer is null.");
        }
        if (trigger == null) {
            throw new NullPointerException("Trigger event is null.");
        }
        if (trigger.getDragAction() == DnDConstants.ACTION_NONE) {
            throw new RuntimeException("Can't init ACTION_NONE drag.");
        }
        if ((dragImage != null) && (offset == null)) {
            throw new NullPointerException("Image offset is null.");
        }
        if (t == null) {
            throw new NullPointerException("Transferable is null.");
        }
        if (trigger.getComponent() == null) {
            throw new IllegalArgumentException(
                    "Component associated with the trigger event is null.");
        }
        if (trigger.getDragSource() == null) {
            throw new IllegalArgumentException("DragSource for the trigger event is null.");
        }
        if (trigger.getSourceAsDragGestureRecognizer().getSourceActions()
                == DnDConstants.ACTION_NONE)
        {
            throw new IllegalArgumentException("Source actions for the DragGestureRecognizer associated with the trigger event are equal to DnDConstants.ACTION_NONE.");
        }

        this.trigger = trigger;
        transferable = t;
        dragSource = trigger.getDragSource();
        sourceAction = trigger.getDragAction();
        component = trigger.getComponent();
        peer = dscp;

        try {
            addDragSourceListener(dsl);
        } catch (TooManyListenersException e) {
        }
        lastTargetAction = DnDConstants.ACTION_NONE;
        lastStatus = DEFAULT;
        setCursor(dragCursor);
    }

    public DragGestureEvent getTrigger() {
        return trigger;
    }

    public Transferable getTransferable() {
        return transferable;
    }

    public DragSource getDragSource() {
        return dragSource;
    }

    public int getSourceActions() {
        return sourceAction;
    }

    public Component getComponent() {
        return component;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public synchronized void setCursor(Cursor c) {
        cursor = c;

        defaultCursor = (cursor == null);
        if (defaultCursor) {
            updateCurrentCursor(sourceAction, lastTargetAction, lastStatus);
        } else {
            peer.setCursor(cursor);
        }
    }

    public synchronized void addDragSourceListener(DragSourceListener dsl) throws TooManyListenersException {
        if (dsl == null) {
            return;
        }
        if (dsl == this) {
            throw new IllegalArgumentException("Attempt to register context as its listener.");
        }
        if (listener != null) {
            throw new TooManyListenersException("One listener is already exist.");
        }

        listener = dsl;
    }

    public synchronized void removeDragSourceListener(DragSourceListener dsl) {
        if (listener != dsl) {
            throw new IllegalArgumentException("dsl is not current listener.");
        }

        listener = null;
    }

    protected synchronized void updateCurrentCursor(int dropOp, int targetAct, int status) {
        if (!defaultCursor) {
            return;
        }
        if ((status < DEFAULT) || (status > CHANGED)) {
            throw new RuntimeException("Invalid status.");
        }

        int possibleOps = dropOp & ((status == DEFAULT) ? DnDConstants.ACTION_NONE : targetAct);
        int theOperation;
        boolean opEnabled;

        if (possibleOps == DnDConstants.ACTION_NONE) {
            theOperation = findBestAction(dropOp);
            opEnabled = false;
        } else {
            theOperation = findBestAction(possibleOps);
            opEnabled = true;
        }

        peer.setCursor(findCursor(theOperation, opEnabled));
    }

    private void updateCursor(int dropOp, int targetAct, int status) {
        lastTargetAction = targetAct;
        lastStatus = status;

        updateCurrentCursor(dropOp, targetAct, status);
    }

    private int findBestAction(int actions) {
        if ((actions & DnDConstants.ACTION_MOVE) != 0) {
            return DnDConstants.ACTION_MOVE;
        } else if ((actions & DnDConstants.ACTION_COPY) != 0) {
            return DnDConstants.ACTION_COPY;
        } else  if ((actions & DnDConstants.ACTION_LINK) != 0) {
            return DnDConstants.ACTION_LINK;
        } else {
            return DnDConstants.ACTION_MOVE;
        }
    }

    private Cursor findCursor(int action, boolean enabled) {
        switch (action) {
        case DnDConstants.ACTION_MOVE:
            return (enabled ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
        case DnDConstants.ACTION_COPY:
            return (enabled ? DragSource.DefaultCopyDrop : DragSource.DefaultCopyNoDrop);
        case DnDConstants.ACTION_LINK:
            return (enabled ? DragSource.DefaultLinkDrop : DragSource.DefaultLinkNoDrop);
        default:
            throw new RuntimeException("Invalid action.");
        }
    }

    public void transferablesFlavorsChanged() {
        peer.transferablesFlavorsChanged();
    }

    public void dragEnter(DragSourceDragEvent dsde) {
        if (listener != null) {
            listener.dragEnter(dsde);
        }
        updateCursor(sourceAction, dsde.getTargetActions(), ENTER);
    }

    public void dragOver(DragSourceDragEvent dsde) {
        if (listener != null) {
            listener.dragOver(dsde);
        }
        updateCursor(sourceAction, dsde.getTargetActions(), OVER);
    }

    public void dropActionChanged(DragSourceDragEvent dsde) {
        sourceAction = dsde.getDropAction();
        if (listener != null) {
            listener.dropActionChanged(dsde);
        }
        updateCursor(sourceAction, dsde.getTargetActions(), CHANGED);
    }

    public void dragExit(DragSourceEvent dse) {
        if (listener != null) {
            listener.dragExit(dse);
        }
        updateCursor(sourceAction, DnDConstants.ACTION_NONE, EXIT);
    }

    public void dragDropEnd(DragSourceDropEvent dsde) {
        if (listener != null) {
            listener.dragDropEnd(dsde);
        }
    }

    public void dragMouseMoved(DragSourceDragEvent dsde) {
        DragSourceMotionListener[] listeners = dragSource.getDragSourceMotionListeners();

        for (DragSourceMotionListener element : listeners) {
            element.dragMouseMoved(dsde);
        }
    }

}
