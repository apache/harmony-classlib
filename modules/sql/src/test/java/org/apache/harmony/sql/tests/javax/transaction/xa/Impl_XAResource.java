/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.sql.tests.javax.transaction.xa;

import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

public class Impl_XAResource implements XAResource {

	public Impl_XAResource() {
		super();
	} // end constructor

	public void commit(Xid parm1, boolean parm2) {

	} // end method commit

	public void end(Xid parm1, int parm2) {

	} // end method end

	public void forget(Xid parm1) {

	} // end method forget

	public int getTransactionTimeout() {

		return 14369347;
	} // end method getTransactionTimeout

	public boolean isSameRM(XAResource parm1) {

		return true;
	} // end method isSameRM

	public int prepare(Xid parm1) {

		return -1212576306;
	} // end method prepare

	public Xid[] recover(int parm1) {

		return null;
	} // end method recover

	public void rollback(Xid parm1) {

	} // end method rollback

	public boolean setTransactionTimeout(int parm1) {

		return true;
	} // end method setTransactionTimeout

	public void start(Xid parm1, int parm2) {

	} // end method start

} // end class XAResourceTest
