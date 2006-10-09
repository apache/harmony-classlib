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
 * @author Elena V. Sayapina 
 * @version $Revision: 1.6 $ 
 */ 

package javax.print.attribute.standard;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.print.attribute.PrintJobAttribute;

public final class JobStateReasons extends HashSet implements PrintJobAttribute {
//1.5 support requires the following changes 
//public final class JobStateReasons extends HashSet<JobStateReason> 
//implements PrintJobAttribute {

    public JobStateReasons() {
        super();
    }

    public JobStateReasons(Collection collection) {
    //1.5 support requires the following changes 
    //public JobStateReasons(Collection<JobStateReason> collection) {

        this();
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
    }

    public JobStateReasons(int initialCapacity) {
        super(initialCapacity);

    }

    public JobStateReasons(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }


    public boolean add(Object reason) {
    //1.5 support requires the following changes 
    //public boolean add(JobStateReason reason) {

        if (reason == null) {
            throw new NullPointerException("Null JobStateReason");
        }
        return super.add( (JobStateReason) reason);
    }

    public final Class getCategory() {
    //1.5 support requires the following changes
    //Class<? extends Attribute> getCategory() {
        return JobStateReasons.class;
    }

    public final String getName() {
        return "job-state-reasons";
    }


}
