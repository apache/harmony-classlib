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
 * @author Alexander T. Simbirtsev
 * @version $Revision$
 */
package javax.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.TimerTask;

import javax.swing.event.EventListenerList;

public class Timer implements Serializable {

    private class FiringEventRunnable implements Runnable {
        private ActionEvent event;
        private int queuedCounter;

        public void preQueueInit(final long scheduledTime) {
            queuedCounter++;
            event = new ActionEvent(Timer.this, 0, null, scheduledTime, 0);
        }

        public boolean isQueued() {
            return queuedCounter > 0;
        }
        
        public void run() {
            if (getLogTimers()) {
                System.out.println("Timer ringing: " + Timer.this);
            }
            fireActionPerformed(event);
            if (isRepeats) {
                queuedCounter--;
            } else {
                stopImpl();
            }
        }
    }

    protected EventListenerList listenerList = new EventListenerList();

    /**
     * following fields purposes fully corresponds to their names
     */
    private int delay;
    private int initialDelay;
    private boolean isRepeats = true;
    private boolean isCoalesce = true;
    private TimerTask timerTask;

    /**
     * this thread is used to execute all timers
     */
    private static java.util.Timer timerThread;

    /**
     * this value indicates whether we need to make output about every ringing
     * timer
     */
    private static boolean isLogTimers;

    public Timer(final int delay, final ActionListener listener) {
        this.delay = delay;
        initialDelay = delay;
        addActionListener(listener);
    }

    public void setRepeats(final boolean isRepeats) {
        this.isRepeats = isRepeats;
    }

    public boolean isRepeats() {
        return isRepeats;
    }

    public void setCoalesce(final boolean isCoalesce) {
        this.isCoalesce = isCoalesce;
    }

    public boolean isCoalesce() {
        return isCoalesce;
    }

    public void setInitialDelay(final int initialDelay) {
        if (initialDelay < 0) {
            throw new IllegalArgumentException("Invalid initial delay: " + initialDelay);
        }
        this.initialDelay = initialDelay;
    }

    public int getInitialDelay() {
        return initialDelay;
    }

    public void setDelay(final int delay) {
        if (delay < 0) {
            throw new IllegalArgumentException("Invalid delay: " + delay);
        }
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public void start() {
        if (isRunning()) {
            return;
        }

        if (timerThread == null) {
            timerThread = new java.util.Timer(true);
        }
        timerTask = createTimerTask();
        timerThread.scheduleAtFixedRate(timerTask, getPositiveDelay(initialDelay), getPositiveDelay(delay));
    }

    public void stop() {
        stopImpl();
    }

    public void restart() {
        stop();
        start();
    }

    public boolean isRunning() {
        return (timerTask != null);
    }

    public <T extends java.util.EventListener> T[] getListeners(final Class<T> listenersClass) {
        return listenerList.getListeners(listenersClass);
    }

    public void removeActionListener(final ActionListener listener) {
        listenerList.remove(ActionListener.class, listener);
    }

    public void addActionListener(final ActionListener listener) {
        listenerList.add(ActionListener.class, listener);
    }

    public ActionListener[] getActionListeners() {
        return listenerList.getListeners(ActionListener.class);
    }

    protected void fireActionPerformed(final ActionEvent event) {
        final ActionListener[] listeners = getActionListeners();
        for (int i = 0; i < listeners.length; i++) {
            listeners[i].actionPerformed(event);
        }
    }

    public static void setLogTimers(final boolean isLogTimers) {
        Timer.isLogTimers = isLogTimers;
    }

    public static boolean getLogTimers() {
        return isLogTimers;
    }

    private void stopImpl() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    private TimerTask createTimerTask() {
        return new TimerTask() {
            private final FiringEventRunnable fireEvent = new FiringEventRunnable();
            
            public void run() {
                if (!isCoalesce || !fireEvent.isQueued()) {
                    fireEvent.preQueueInit(scheduledExecutionTime());
                    SwingUtilities.invokeLater(fireEvent);
                }
            }
        };
    }

    private static int getPositiveDelay(final int d) {
        return (d > 0) ? d : 1;
    }
}