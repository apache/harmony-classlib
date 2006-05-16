/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package ar.org.fitc.rmi.dgc;

import java.util.Timer;
import java.util.TimerTask;

import ar.org.fitc.rmi.utils.PropertiesReader;

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * This class schedules calls to the <code>java.lang.System.gc()</code>
 * method.
 * 
 * @author Gustavo Petri
 */
public class DGCScheduledGC {

    /**
     * Timer to schedule the calls
     */
    private Timer timer;

    /**
     * The interval used by the Timer
     */
    private static long gcInterval;

    /**
     * Flag to indicate the termination of the Thread
     */
    private static boolean running = false;

    static {
        gcInterval = PropertiesReader.readLong(
                "ar.org.fitc.rmi.dgc.gcInterval", 60000);
    }

    /**
     * Schedules the calls to <code>java.lang.System.gc()</code>
     * 
     * @see <a
     *      href="http://archives.java.sun.com/cgi-bin/wa?A2=ind9911&L=rmi-users&F=&S=&P=20099">
     *      Reference </a>
     */

    private DGCScheduledGC() {
        timer = new Timer(true);
        try {
            timer.schedule(new RunGCTask(), gcInterval, gcInterval);
        } catch (Exception e) {
            // There is no chance that this try will fail unless the runGC
            // method be errased.
            throw new AssertionError();
        }
        running = true;
    }

    /**
     * Schedules and starts the calls to <code>java.lang.System.gc()</code>
     * 
     * @see <a
     *      href="http://archives.java.sun.com/cgi-bin/wa?A2=ind9911&L=rmi-users&F=&S=&P=20099">
     *      Reference </a>
     */
    public static synchronized final void startGC() {
        if (!running) {
            new DGCScheduledGC();
        }
    }

    /**
     * Runs the <code>java.lang.System.gc()</code> method.
     */
    private class RunGCTask extends TimerTask {

        @Override
        public void run() {
            System.gc();
        }
    }
   
}
