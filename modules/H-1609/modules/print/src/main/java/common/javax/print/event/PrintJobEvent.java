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
 * @author Aleksei V. Ivaschenko 
 * @version $Revision: 1.3 $ 
 */ 

package javax.print.event;

import javax.print.DocPrintJob;

public class PrintJobEvent extends PrintEvent {

    public static final int DATA_TRANSFER_COMPLETE = 106;

    public static final int JOB_CANCELED = 101;

    public static final int JOB_COMPLETE = 102;

    public static final int JOB_FAILED = 103;

    public static final int NO_MORE_EVENTS = 105;

    public static final int REQUIRES_ATTENTION = 104;

    private int reason;

    public PrintJobEvent(DocPrintJob source, int reason) {
        super(source);
        this.reason = reason;
    }

    public int getPrintEventType() {
        return reason;
    }

    public DocPrintJob getPrintJob() {
        return (DocPrintJob)getSource();
    }
}
