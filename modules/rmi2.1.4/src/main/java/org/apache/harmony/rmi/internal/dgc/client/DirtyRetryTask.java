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
package org.apache.harmony.rmi.internal.dgc.client;

import java.rmi.server.ObjID;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.harmony.rmi.internal.transport.Endpoint;
import org.apache.harmony.rmi.internal.utils.Pair;

/**
 * When scheduled dirty calls fail, the dirty retry process uses instances of
 * this class to attempt to get a lease from the server. A <code>Timer</code>
 * should run this task in order to send the dirties for renewing the leases
 * periodically.
 * 
 * @author Gonzalo Ortega
 * 
 */
final class DirtyRetryTask extends TimerTask {

	private DGCClient dgc;

	private long originalPeriod;

	private Timer taskScheduler;

	private Endpoint ep;

	private ObjID[] objIDs;

	private int retryNumber;

	private static final int retriesAllowed = 4;

	/**
	 * Creates a new instance of <code>DirtyRetryTask</code>.
	 * 
	 * @param dgc
	 *            The client DGC.
	 * @param period
	 *            The original period in milliseconds of the lease granted to
	 *            the references grouped in the <code>DirtyTask</code> wich
	 *            has isntantiated this task.
	 * @param taskScheduler
	 *            The <code>Timer</code> object which will run this scheduled
	 *            <code>DirtyTask</code>.
	 * @param ep
	 *            The <code>Endpoint</code> of the references grouped in the
	 *            failed dirty call.
	 * @param objIDs
	 *            The <code>ObjID</code> of the references drouped in the
	 *            failed dirty call.
	 * @param retryNumber
	 *            The number of times the dirty call has been retried.
	 */
	public DirtyRetryTask(DGCClient dgc, long period, Timer taskScheduler,
			Endpoint ep, ObjID[] objIDs, int retryNumber) {
		this.dgc = dgc;
		this.originalPeriod = period;
		this.taskScheduler = taskScheduler;
		this.ep = ep;
		this.objIDs = objIDs;
		this.retryNumber = ++retryNumber;
	}

	/**
	 * This <code>DirtyRetryTask</code> will try to re-send the dirty call to
	 * the detination <code>Endpoint</code>. If this dirty call success, the
	 * references will be re-scheduled for normal lease renewal. If the dirty
	 * call fails, a new <code>DirtyRetryTask</code> will be scheduled with an
	 * exponential delay time for a new retry. If four retry attempts have been
	 * made and the retry call still hasn't succeeded, no more dirties for those
	 * references will be sent. The references will be removed from the DGC
	 * "live" references table and a clean "strong" call will be sent to the
	 * remote server's DGC.
	 */
	@Override
	public final void run() {
		if (dgc.sendDirty(ep, objIDs) != null) {
			for (ObjID objID : objIDs) {
				dgc.enqueueDirtyCall(new Pair<Endpoint, ObjID>(ep, objID),
						originalPeriod);
			}
		} else {
			if (retryNumber < retriesAllowed) {
				DirtyRetryTask retryTask = new DirtyRetryTask(dgc,
						originalPeriod, taskScheduler, ep, objIDs, retryNumber);
				taskScheduler.schedule(retryTask, Double.valueOf(
						(Math.pow(2, retryNumber))).longValue() * 1000);
			} else {
				// Giving up on dirties. Sending clean strong call for the
				// references.
				for (ObjID objID : objIDs) {
					dgc.removeFromLiveReferences(new Pair<Endpoint, ObjID>(ep,
							objID));
				}
				dgc.sendClean(ep, objIDs, true);
			}
		}
	}
}
