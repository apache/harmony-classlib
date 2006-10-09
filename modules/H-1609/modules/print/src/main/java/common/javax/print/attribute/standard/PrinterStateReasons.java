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

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.print.attribute.PrintServiceAttribute;

/*
 * Table values are obtained from RFC2911: Internet Printing Protocol/1.1: 
 * Model and Semantics, section 4.4.11, http://ietf.org/rfc/rfc2911.txt?number=2911
 */

public final class PrinterStateReasons extends HashMap
        implements PrintServiceAttribute {
//public final class PrinterStateReasons extends 
//HashMap<PrinterStateReason, Severity> implements PrintServiceAttribute {
    
    
    public PrinterStateReasons() {
        super();
    }

    public PrinterStateReasons(int initialCapacity) {
        super(initialCapacity);
    }

    public PrinterStateReasons(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public PrinterStateReasons(Map map) {
    //1.5 support requires the following changes 
    //public PrinterStateReasons(Map<PrinterStateReason, Severity> map) {
        this();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            put(mapEntry.getKey(), mapEntry.getValue());
        }
    }


    public final Class getCategory() {
    //1.5 support requires the following changes
    //Class<? extends Attribute> getCategory() {
        return PrinterStateReasons.class;
    }

    public final String getName() {
        return "printer-state-reasons";
    }

    public Object put(Object reason, Object severity) {
    //1.5 support requires the following changes 
    //public Severity put(PrinterStateReason reason, Severity severity) {

        if (reason == null) {
            throw new NullPointerException("Reason is null");
        }
        if (severity == null) {
            throw new NullPointerException("Severity is null");
        }
        return super.put((PrinterStateReason) reason,
                                            (Severity) severity);
    }

    public Set printerStateReasonSet(Severity severity) {
    //1.5 support requires the following changes 
    //public Set<PrinterStateReason> printerStateReasonSet(Severity severity) {

        if (severity == null) {
            throw new NullPointerException("Severity is null");
        }
        Map.Entry mapEntry;
        HashSet set = new HashSet();
        Iterator iterator = entrySet().iterator();
        while (iterator.hasNext()) {
            mapEntry = (Map.Entry) iterator.next();
            if ((Severity) mapEntry.getValue() == severity) {
                set.add((PrinterStateReason) mapEntry.getKey());
            }
        }
        return Collections.unmodifiableSet(set);
    }

}
