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
 * @author Michael Danilov, Pavel Dolgov
 * @version $Revision$
 */
package java.awt;

import java.awt.event.*;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;
import java.lang.reflect.InvocationTargetException;

public class EventQueue {

    private final Toolkit toolkit;

    private final boolean systemEventQueue;

    private LinkedList events;

    private LinkedList queues;
    private EventQueue curQueue;

    public static boolean isDispatchThread() {
        return Thread.currentThread() instanceof EventDispatchThread;
    }

    public static void invokeLater(Runnable runnable) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        toolkit.systemEventQueue.postEvent(new InvocationEvent(toolkit, runnable));
    }

    public static void invokeAndWait(Runnable runnable)
            throws InterruptedException, InvocationTargetException
    {
        if (isDispatchThread()) {
            throw new Error();
        }

        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Object notifier = new Object();
        InvocationEvent event = new InvocationEvent(toolkit, runnable, notifier, true);

        synchronized (notifier) {
            toolkit.systemEventQueue.postEvent(event);
            notifier.wait();
        }

        Exception exception = event.getException();

        if (exception != null) {
            throw new InvocationTargetException(exception);
        }
    }

    public static long getMostRecentEventTime() {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        toolkit.lockAWT();
        try {
            return (isDispatchThread() ?
                    toolkit.eventQueueLastEvent.mostRecentEventTime :
                    System.currentTimeMillis());
        } finally {
            toolkit.unlockAWT();
        }
    }

    public static AWTEvent getCurrentEvent() {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        toolkit.lockAWT();
        try {
            return (isDispatchThread() ?
                    toolkit.eventQueueLastEvent.curEvent : null);
        } finally {
            toolkit.unlockAWT();
        }
    }

    public EventQueue() {
        this(false, Toolkit.getDefaultToolkit());
    }

    EventQueue(boolean sysEventQueue, Toolkit toolkit) {
        synchronized (this) {
            this.toolkit = toolkit;
            systemEventQueue = sysEventQueue;
            events = new LinkedList();
            queues =  new LinkedList();
            curQueue = null;
        }
    }

    public void postEvent(AWTEvent event) {
        synchronized (this) {
            if (curQueue == null) {
                AWTEvent eventToAdd = event;
                Object src = event.getSource();

                if (src instanceof Component) {
                    Component comp = (Component) src;

                    if (comp.isCoalescer()) {
                        AWTEvent relative = comp.getRelativeEvent(event.getID());

                        if (relative != null) {
                            AWTEvent coalisced = comp.coalesceEvents(relative, event);

                            if (coalisced != null) {
                                events.remove(relative);
                                comp.removeRelativeEvent();
                                eventToAdd = coalisced;
                            }
                        }
                        comp.addNewEvent(eventToAdd);
                    }
                }

                events.addLast(eventToAdd);
                notifyAll();
            } else {
                curQueue.postEvent(event);
            }
        }

        if (systemEventQueue) {
            if (!isDispatchThread()) {
                toolkit.getNativeEventQueue().awake();
            } else {
                toolkit.validateShutdownThread();
            }
        }
    }

    public AWTEvent getNextEvent() throws InterruptedException {
        synchronized (this) {
            if (curQueue == null) {
                while (events.isEmpty()) {
                    wait();
                }

                AWTEvent event = (AWTEvent) events.removeFirst();
                Object src = event.getSource();

                if (src instanceof Component) {
                    Component comp = (Component) src;

                    if (comp.isCoalescer()) {
                        comp.removeNextEvent(event.getID());
                    }
                }

                return event;
            } else {
                return curQueue.getNextEvent();
            }
        }
    }

    public AWTEvent peekEvent() {
        synchronized (this) {
            if (curQueue == null) {
                return events.isEmpty() ? null : (AWTEvent) events.getFirst();
            } else {
                return curQueue.peekEvent();
            }
        }
    }

    public AWTEvent peekEvent(int id) {
        synchronized (this) {
            if (curQueue == null) {
                for (Iterator i = events.iterator(); i.hasNext(); ) {
                    AWTEvent event = (AWTEvent) i.next();

                    if (event.getID() == id) {
                        return event;
                    }
                }

                return null;
            } else {
                return curQueue.peekEvent(id);
            }
        }
    }

    public void push(EventQueue newEventQueue) {
        synchronized (this) {
            while (peekEvent() != null) {
                try {
                    newEventQueue.postEvent(getNextEvent());
                } catch (InterruptedException e) {
                }
            }
            queues.addLast(newEventQueue);
            curQueue = newEventQueue;
        }
    }

    protected void pop() throws EmptyStackException {
        synchronized (this) {
            if (curQueue == null) {
                throw new EmptyStackException();
            }

            queues.removeLast();
            EventQueue destQueue = queues.isEmpty() ? this : (EventQueue) queues.getLast();

            while (curQueue.peekEvent() != null) {
                try {
                    destQueue.postEvent(curQueue.getNextEvent());
                } catch (InterruptedException e) {
                }
            }
            curQueue = queues.isEmpty() ? null : destQueue;
        }
    }

    protected void dispatchEvent(AWTEvent event) {
        EventQueue subsequentQueue = null;
        synchronized (this) {
            long when = 0l;

            if (event instanceof ActionEvent) {
                when = ((ActionEvent) event).getWhen();
            } else if (event instanceof InputEvent) {
                when = ((InputEvent) event).getWhen();
            } else if (event instanceof InputMethodEvent) {
                when = ((InputMethodEvent) event).getWhen();
            } else if (event instanceof InvocationEvent) {
                when = ((InvocationEvent) event).getWhen();
            }
            if (when != 0l) {
                toolkit.eventQueueLastEvent.mostRecentEventTime = when;
            }
            toolkit.eventQueueLastEvent.curEvent = event;

            subsequentQueue = curQueue;
        }

        if (subsequentQueue != null) {
            subsequentQueue.dispatchEvent(event);
            return;
        }

        if (event instanceof ActiveEvent) {
            toolkit.dispatchAWTEvent(event);
            ((ActiveEvent) event).dispatch();
            return;
        }

        Object src = event.getSource();

        if (src instanceof Component) {
            if (preprocessComponentEvent(event)) {
                ((Component) src).dispatchEvent(event);
            }
        } else {
            toolkit.dispatchAWTEvent(event);
            if (src instanceof MenuComponent) {
                ((MenuComponent) src).dispatchEvent(event);
            }
        }
    }

    private final boolean preprocessComponentEvent(AWTEvent event) {
        if (event instanceof MouseEvent) {
            return preprocessMouseEvent((MouseEvent)event);
        }
        return true;
    }

    private final boolean preprocessMouseEvent(MouseEvent event) {
        if (toolkit.mouseEventPreprocessor != null) {
            toolkit.lockAWT();
            try {
                return toolkit.mouseEventPreprocessor.preprocess(event);
            } finally {
                toolkit.unlockAWT();
            }
        }
        return true;
    }

    boolean isEmpty() {
        synchronized (this) {
            if ((curQueue == null)) {
                return events.isEmpty();
            } else {
                return (curQueue.peekEvent() == null);
            }
        }
    }

    static final class LastEvent {
        long mostRecentEventTime = System.currentTimeMillis();
        AWTEvent curEvent = null;
    }

}
