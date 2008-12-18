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

package java.util;

import org.apache.harmony.luni.util.Msg;

/**
 * Timers are used to schedule jobs for execution in a background process. A
 * single thread is used for the scheduling and this thread has the option of
 * being a daemon thread. By calling <code>cancel</code> you can terminate a
 * timer and it's associated thread. All tasks which are scheduled to run after
 * this point are cancelled. Tasks are executed sequentially but are subject to
 * the delays from other tasks run methods. If a specific task takes an
 * excessive amount of time to run it may impact the time at which subsequent
 * tasks may run.
 * <p>
 *
 * The Timer task does not offer any guarantees about the real-time nature of
 * scheduling tasks as it's underlying implementation relies on the
 * <code>Object.wait(long)</code> method.
 * <p>
 *
 * Multiple threads can share a single Timer without the need for their own
 * synchronization.
 *
 * @see TimerTask
 * @see java.lang.Object#wait(long)
 */
public class Timer {

    private static final class TimerImpl extends Thread {

        private static final class TimerHeap {
            private int DEFAULT_HEAP_SIZE = 256;

            private TimerTask[] timers = new TimerTask[DEFAULT_HEAP_SIZE];

            private int size = 0;

            private int deletedCancelledNumber = 0;

            public TimerTask minimum() {
                return timers[0];
            }

            public boolean isEmpty() {
                return size == 0;
            }

            public void insert(TimerTask task) {
                if (timers.length == size) {
                    TimerTask[] appendedTimers = new TimerTask[size * 2];
                    System.arraycopy(timers, 0, appendedTimers, 0, size);
                    timers = appendedTimers;
                }
                timers[size++] = task;
                upHeap();
            }

            public void delete(int pos) {
                // posible to delete any position of the heap
                if (pos >= 0 && pos < size) {
                    timers[pos] = timers[--size];
                    timers[size] = null;
                    downHeap(pos);
                }
            }

            private void upHeap() {
                int current = size - 1;
                int parent = (current - 1) / 2;

                while (timers[current].when < timers[parent].when) {
                    // swap the two
                    TimerTask tmp = timers[current];
                    timers[current] = timers[parent];
                    timers[parent] = tmp;

                    // update pos and current
                    current = parent;
                    parent = (current - 1) / 2;
                }
            }

            private void downHeap(int pos) {
                int current = pos;
                int child = 2 * current + 1;

                while (child < size && size > 0) {
                    // compare the children if they exist
                    if (child + 1 < size
                            && timers[child + 1].when < timers[child].when) {
                        child++;
                    }

                    // compare selected child with parent
                    if (timers[current].when < timers[child].when) {
                        break;
                    }

                    // swap the two
                    TimerTask tmp = timers[current];
                    timers[current] = timers[child];
                    timers[child] = tmp;

                    // update pos and current
                    current = child;
                    child = 2 * current + 1;
                }
            }

            public void reset() {
                timers = new TimerTask[DEFAULT_HEAP_SIZE];
                size = 0;
            }

            public void adjustMinimum() {
                downHeap(0);
            }

            public void deleteIfCancelled() {
                for (int i = 0; i < size; i++) {
                    if (timers[i].cancelled) {
                        deletedCancelledNumber++;
                        delete(i);
                        // re-try this point
                        i--;
                    }
                }
            }

            private int getTask(TimerTask task) {
                for (int i = 0; i < timers.length; i++) {
                    if (timers[i] == task) {
                        return i;
                    }
                }
                return -1;
            }

        }

        /**
         * True if the method cancel() of the Timer was called or the !!!stop()
         * method was invoked
         */
        private boolean cancelled;

        /**
         * True if the Timer has become garbage
         */
        private boolean finished;

        /**
         * Vector consists of scheduled events, sorted according to
         * <code>when</code> field of TaskScheduled object.
         */
        private TimerHeap tasks = new TimerHeap();

        /**
         * Starts a new timer.
         *
         * @param isDaemon
         */
        TimerImpl(boolean isDaemon) {
            this.setDaemon(isDaemon);
            this.start();
        }

        TimerImpl(String name, boolean isDaemon) {
            this.setName(name);
            this.setDaemon(isDaemon);
            this.start();
        }

        /**
         * This method will be launched on separate thread for each Timer
         * object.
         */
        @Override
        public void run() {
            while (true) {
                TimerTask task;
                synchronized (this) {
                    // need to check cancelled inside the synchronized block
                    if (cancelled) {
                        return;
                    }
                    if (tasks.isEmpty()) {
                        if (finished) {
                            return;
                        }
                        // no tasks scheduled -- sleep until any task appear
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                        }
                        continue;
                    }

                    long currentTime = System.currentTimeMillis();

                    task = tasks.minimum();
                    long timeToSleep;

                    synchronized (task.lock) {
                        if (task.cancelled) {
                            tasks.delete(0);
                            continue;
                        }

                        // check the time to sleep for the first task scheduled
                        timeToSleep = task.when - currentTime;
                    }

                    if (timeToSleep > 0) {
                        // sleep!
                        try {
                            this.wait(timeToSleep);
                        } catch (InterruptedException e) {
                            // Ignored
                        }
                        continue;
                    }

                    // no sleep is necessary before launching the task

                    synchronized (task.lock) {
                        int pos = 0;
                        if (tasks.minimum().when != task.when) {
                            pos = tasks.getTask(task);
                        }
                        if (task.cancelled) {
                            tasks.delete(tasks.getTask(task));
                            continue;
                        }

                        // set time to schedule
                        task.setScheduledTime(task.when);

                        // remove task from queue
                        tasks.delete(pos);

                        // set when the next task should be launched
                        if (task.period >= 0) {
                            // this is a repeating task,
                            if (task.fixedRate) {
                                // task is scheduled at fixed rate
                                task.when = task.when + task.period;
                            } else {
                                // task is scheduled at fixed delay
                                task.when = System.currentTimeMillis()
                                        + task.period;
                            }

                            // insert this task into queue
                            insertTask(task);
                        } else {
                            task.when = 0;
                        }
                    }
                }

                // run the task
                try {
                    task.run();
                } catch (Exception e) {
                    // Ignored
                }
            }
        }

        private void insertTask(TimerTask newTask) {
            // callers are synchronized
            tasks.insert(newTask);
            this.notify();
        }

        /**
         * Cancels timer.
         */
        public synchronized void cancel() {
            cancelled = true;
            tasks.reset();
            this.notify();
        }

        public int purge() {
            if (tasks.isEmpty()) {
                return 0;
            }
            // callers are synchronized
            tasks.deletedCancelledNumber = 0;
            tasks.deleteIfCancelled();
            return tasks.deletedCancelledNumber;
        }

    }

    /* This object will be used in synchronization purposes */
    private TimerImpl impl;

    // Used to finalize thread
    @SuppressWarnings("unused")
    private Object finalizer = new Object() { // $NON-LOCK-1$
        @Override
        protected void finalize() {
            synchronized (impl) {
                impl.finished = true;
                impl.notify();
            }
        }
    };

    /**
     * Creates a new Timer which may be specified to be run as a Daemon Thread.
     *
     * @param isDaemon
     *            true if Timers thread should be a daemon thread.
     */
    public Timer(boolean isDaemon) {
        impl = new TimerImpl(isDaemon);
    }

    /**
     * Creates a new non-daemon Timer.
     */
    public Timer() {
        impl = new TimerImpl(false);
    }

    /**
     * Create a new timer with the given name and daemon status.
     *
     * The name is given the timer's background thread and if the flag is true
     * the thread is run as a daemon.
     *
     * @param name
     *            a name to associate with the timer thread.
     * @param isDaemon
     *            true if the timer thread should be a daemon, or false if it is
     *            a regular thread that prevents the application terminating.
     */
    public Timer(String name, boolean isDaemon) {
        impl = new TimerImpl(name, isDaemon);
    }

    /**
     * Create a new timer whose thread has the given name.
     *
     * The name is given the timer's background thread, that is not run as a
     * daemon.
     *
     * @param name
     *            a name to associate with the timer thread.
     */
    public Timer(String name) {
        impl = new TimerImpl(name, false);
    }

    /**
     * Cancels the Timer and removed any scheduled tasks. If there is a
     * currently running task it is not effected. No more tasks may be scheduled
     * on this Timer. Subsequent calls do nothing.
     */
    public void cancel() {
        impl.cancel();
    }

    /**
     * Purging the timer eagerly removes cancelled tasks.
     *
     * When a large number of tasks have been cancelled it may be helpful to
     * explicitly purge them from the timer rather than let them be removed
     * during normal expiry processing. This is a housekeeping task that does
     * not affect the timer's schedule tasks.
     *
     * @return the number of tasks that were purged.
     */
    public int purge() {
        synchronized (impl) {
            return impl.purge();
        }
    }

    /**
     * Schedule a task for single execution. If when is less than the current
     * time, it will be scheduled to executed as soon as possible.
     *
     * @param task
     *            The task to schedule
     * @param when
     *            Time of execution
     *
     * @exception IllegalArgumentException
     *                if when.getTime() < 0
     * @exception IllegalStateException
     *                if the timer has been cancelled, the task has been
     *                scheduled or cancelled.
     */
    public void schedule(TimerTask task, Date when) {
        if (when.getTime() < 0) {
            throw new IllegalArgumentException();
        }
        long delay = when.getTime() - System.currentTimeMillis();
        scheduleImpl(task, delay < 0 ? 0 : delay, -1, false);
    }

    /**
     * Schedule a task for single execution after a specific delay.
     *
     * @param task
     *            The task to schedule
     * @param delay
     *            Amount of time before execution
     *
     * @exception IllegalArgumentException
     *                if delay < 0
     * @exception IllegalStateException
     *                if the timer has been cancelled, the task has been
     *                scheduled or cancelled.
     */
    public void schedule(TimerTask task, long delay) {
        if (delay < 0) {
            throw new IllegalArgumentException();
        }
        scheduleImpl(task, delay, -1, false);
    }

    /**
     * Schedule a task for repeated fix-delay execution after a specific delay.
     *
     * @param task
     *            The task to schedule
     * @param delay
     *            Amount of time before first execution
     * @param period
     *            Amount of time between subsequent executions
     *
     * @exception IllegalArgumentException
     *                if delay < 0 or period < 0
     * @exception IllegalStateException
     *                if the timer has been cancelled, the task has been
     *                scheduled or cancelled.
     */
    public void schedule(TimerTask task, long delay, long period) {
        if (delay < 0 || period <= 0) {
            throw new IllegalArgumentException();
        }
        scheduleImpl(task, delay, period, false);
    }

    /**
     * Schedule a task for repeated fix-delay execution after a specific time
     * has been reached.
     *
     * @param task
     *            The task to schedule
     * @param when
     *            Time of first execution
     * @param period
     *            Amount of time between subsequent executions
     *
     * @exception IllegalArgumentException
     *                if when.getTime() < 0 or period < 0
     * @exception IllegalStateException
     *                if the timer has been cancelled, the task has been
     *                scheduled or cancelled.
     */
    public void schedule(TimerTask task, Date when, long period) {
        if (period <= 0 || when.getTime() < 0) {
            throw new IllegalArgumentException();
        }
        long delay = when.getTime() - System.currentTimeMillis();
        scheduleImpl(task, delay < 0 ? 0 : delay, period, false);
    }

    /**
     * Schedule a task for repeated fixed-rate execution after a specific delay
     * has been happened. The difference of fixed-rate is that it may bunch up
     * subsequent task runs to try to get the task repeating at it's desired
     * time.
     *
     * @param task
     *            The task to schedule
     * @param delay
     *            Amount of time before first execution
     * @param period
     *            Amount of time between subsequent executions
     *
     * @exception IllegalArgumentException
     *                if delay < 0 or period < 0
     * @exception IllegalStateException
     *                if the timer has been cancelled, the task has been
     *                scheduled or cancelled.
     */
    public void scheduleAtFixedRate(TimerTask task, long delay, long period) {
        if (delay < 0 || period <= 0) {
            throw new IllegalArgumentException();
        }
        scheduleImpl(task, delay, period, true);
    }

    /**
     * Schedule a task for repeated fixed-rate execution after a specific time
     * has been reached. The difference of fixed-rate is that it may bunch up
     * subsequent task runs to try to get the task repeating at it's desired
     * time.
     *
     * @param task
     *            The task to schedule
     * @param when
     *            Time of first execution
     * @param period
     *            Amount of time between subsequent executions
     *
     * @exception IllegalArgumentException
     *                if when.getTime() < 0 or period < 0
     * @exception IllegalStateException
     *                if the timer has been cancelled, the task has been
     *                scheduled or cancelled.
     */
    public void scheduleAtFixedRate(TimerTask task, Date when, long period) {
        if (period <= 0 || when.getTime() < 0) {
            throw new IllegalArgumentException();
        }
        long delay = when.getTime() - System.currentTimeMillis();
        scheduleImpl(task, delay < 0 ? 0 : delay, period, true);
    }

    /*
     * Schedule a task.
     */
    private void scheduleImpl(TimerTask task, long delay, long period,
            boolean fixed) {
        synchronized (impl) {
            if (impl.cancelled) {
                throw new IllegalStateException(Msg.getString("K00f3")); //$NON-NLS-1$
            }

            long when = delay + System.currentTimeMillis();

            if (when < 0) {
                throw new IllegalArgumentException(Msg.getString("K00f5")); //$NON-NLS-1$
            }

            synchronized (task.lock) {
                if (task.isScheduled()) {
                    throw new IllegalStateException(Msg.getString("K00f6")); //$NON-NLS-1$
                }

                if (task.cancelled) {
                    throw new IllegalStateException(Msg.getString("K00f7")); //$NON-NLS-1$
                }

                task.when = when;
                task.period = period;
                task.fixedRate = fixed;
            }

            // insert the newTask into queue
            impl.insertTask(task);
        }
    }
}
