/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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
* @author Stepan M. Mishura
* @version $Revision$
*/

package com.openintel.drl.security.asn1;

import java.io.IOException;
import java.util.Date;

/**
 * Abstract class to represent ASN.1 time types
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public abstract class ASN1Time extends ASN1StringType {

    // The number of days in monthes
    protected static final byte[] DAYS = new byte[] { 31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31 };

    /**
     * TODO Put ctor description here
     * 
     * @param tagNumber
     */
    public ASN1Time(int tagNumber) {
        super(tagNumber);
    }

    public Object getDecodedObject(BerInputStream in) throws IOException {
        long milliseconds = ASN1Time.getMilliseconds(in.times);
        return new Date(milliseconds);

    }

    public static int getTimeValue(String time, int offset, int length)
            throws IOException {

        int end = offset + length;
        if (time.length() < end) {
            throw new ASN1Exception("Wrong param"); //FIXME message & type
        }

        int c;
        int result = 0;
        for (int i = offset; i < end; i++) {
            c = time.charAt(i) - 48;
            if (c < 0 || c > 9) {
                throw new ASN1Exception("Wrong param"); //FIXME message & type
            }
            result = result * 10 + c;
        }
        return result;
    }

    public static long getMilliseconds(int[] times) {
        // count the number of milliseconds since Jan 1, 1970, 00:00:00 GMT
        long res = times[6]; //milliseconds
        res += times[5] * 1000; //second
        res += (long) times[4] * 60000; //minute
        res += (long) times[3] * 3600000; //hour
        res += (long) (times[2]-1) * 86400000; //day
        for (int i = 1; i < times[1]; i++) { //month
            res += (long) DAYS[i - 1] * 86400000;
        }
        // the number of passed leap years without this year
        int leap_years_num =
            (times[0] > 1970) ? (times[0] - 1969)/4 : (times[0] - 1971)/4;
        res += (long) (times[0] - 1970) * 31536000000l 
                            + (long) leap_years_num * 86400000;
        // if this year is a leap year and this is time after the february
        if ((times[0] % 4 == 0) && (times[1] > 2)) {
            res += (times[0] > 1970) ? 86400000 : -86400000; 
        }
//        // count the number of milliseconds since Jan 1, 1970, 00:00:00 GMT
//        long res = times[6]; //milliseconds
//        res += times[5] * 1000; //second
//        res += (long) times[4] * 60000; //minute
//        res += (long) times[3] * 3600000; //hour
//        res += (long) (times[2] - 1) * 86400000; //day
//        for (int i = 1; i < times[1]; i++) { //month
//            res += (long) DAYS[i - 1] * 86400000;
//        }
//        if ((times[0] % 4 == 0) && (times[1] > 2)) {
//            res += (long) 86400000; // this year is a leap year
//        }
//        if (times[0] > 1970) {
//            res += ((long) (times[0] - 1970) * 31536000000l + (long) ((times[0] - 1969) / 4) * 86400000);
//        }
        return res;
    }
}
