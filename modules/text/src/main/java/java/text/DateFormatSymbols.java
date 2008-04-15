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

package java.text;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;

import org.apache.harmony.luni.util.NotImplementedException;

/**
 * DateFormatSymbols holds the Strings used in the formating and parsing of
 * dates and times.
 */
public class DateFormatSymbols implements Serializable, Cloneable {

    private static final long serialVersionUID = -5987973545549424702L;

    private String localPatternChars;

    String[] ampms, eras, months, shortMonths, shortWeekdays, weekdays;

    String[][] zoneStrings;

    /**
     * Constructs a new DateFormatSymbols containing the symbols for the default
     * Locale.
     */
    public DateFormatSymbols() {
        this(Locale.getDefault());
    }

    /**
     * Constructs a new DateFormatSymbols containing the symbols for the
     * specified Locale.
     * 
     * @param locale
     *            the Locale
     */
    public DateFormatSymbols(Locale locale) {
        com.ibm.icu.text.DateFormatSymbols icuSymbols = new com.ibm.icu.text.DateFormatSymbols(
                locale);

        localPatternChars = icuSymbols.getLocalPatternChars();
        ampms = icuSymbols.getAmPmStrings();
        eras = icuSymbols.getEras();
        months = icuSymbols.getMonths();
        shortMonths = icuSymbols.getShortMonths();
        shortWeekdays = icuSymbols.getShortWeekdays();
        weekdays = icuSymbols.getWeekdays();
        zoneStrings = icuSymbols.getZoneStrings();
    }

    /**
     * Get all locales which <code>getInstance(Locale)</code> method support
     * to return localize instance. The returned array locales include Java
     * runtime and installed service provider supported locales. And it must
     * contain <code>Locale</code> instance equals to <code>Locale.US</code>.
     * 
     * @return array of locales
     */
    public static Locale[] getAvailableLocales() {
        throw new NotImplementedException();
    }

    /**
     * Return the DateFormatSymbols instance for the default locale.
     * 
     * @return an instance of DateFormatSymbols
     * 
     * @since 1.6
     */
    public static final DateFormatSymbols getInstance() {
        throw new NotImplementedException();
    }

    /**
     * Return the DateFormatSymbols for the specified locale. This method
     * return DateFormatSymbols instances for locales supported by the Java
     * runtime and installed DateFormatSymbols implementations.
     * 
     * @param locale
     *            locale for the returned DateFormatSymbols instance
     * 
     * @return an instance of DateFormatSymbols
     * 
     * @exception NullPointerException
     *                if locale is null
     * 
     * @since 1.6
     */
    public static final DateFormatSymbols getInstance(Locale locale) {
        throw new NotImplementedException();
    }

    /**
     * Answers a new DateFormatSymbols with the same symbols as this
     * DateFormatSymbols.
     * 
     * @return a shallow copy of this DateFormatSymbols
     * 
     * @see java.lang.Cloneable
     */
    @Override
    public Object clone() {
        try {
            DateFormatSymbols symbols = (DateFormatSymbols) super.clone();
            symbols.ampms = ampms.clone();
            symbols.eras = eras.clone();
            symbols.months = months.clone();
            symbols.shortMonths = shortMonths.clone();
            symbols.shortWeekdays = shortWeekdays.clone();
            symbols.weekdays = weekdays.clone();
            symbols.zoneStrings = new String[zoneStrings.length][];
            for (int i = 0; i < zoneStrings.length; i++) {
                symbols.zoneStrings[i] = zoneStrings[i].clone();
            }
            return symbols;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Compares the specified object to this DateFormatSymbols and answer if
     * they are equal. The object must be an instance of DateFormatSymbols with
     * the same symbols.
     * 
     * @param object
     *            the object to compare with this object
     * @return true if the specified object is equal to this DateFormatSymbols,
     *         false otherwise
     * 
     * @see #hashCode
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DateFormatSymbols)) {
            return false;
        }
        DateFormatSymbols obj = (DateFormatSymbols) object;
        if (!localPatternChars.equals(obj.localPatternChars)) {
            return false;
        }
        if (!Arrays.equals(ampms, obj.ampms)) {
            return false;
        }
        if (!Arrays.equals(eras, obj.eras)) {
            return false;
        }
        if (!Arrays.equals(months, obj.months)) {
            return false;
        }
        if (!Arrays.equals(shortMonths, obj.shortMonths)) {
            return false;
        }
        if (!Arrays.equals(shortWeekdays, obj.shortWeekdays)) {
            return false;
        }
        if (!Arrays.equals(weekdays, obj.weekdays)) {
            return false;
        }
        if (zoneStrings.length != obj.zoneStrings.length) {
            return false;
        }
        for (String[] element : zoneStrings) {
            if (element.length != element.length) {
                return false;
            }
            for (int j = 0; j < element.length; j++) {
                if (element[j] != element[j]
                        && !(element[j].equals(element[j]))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Answers the array of Strings which represent AM and PM. Use the Calendar
     * constants Calendar.AM and Calendar.PM to index into the array.
     * 
     * @return an array of String
     */
    public String[] getAmPmStrings() {
        return ampms.clone();
    }

    /**
     * Answers the array of Strings which represent BC and AD. Use the Calendar
     * constants GregorianCalendar.BC and GregorianCalendar.AD to index into the
     * array.
     * 
     * @return an array of String
     */
    public String[] getEras() {
        return eras.clone();
    }

    /**
     * Answers the pattern characters used by SimpleDateFormat to specify date
     * and time fields.
     * 
     * @return a String containing the pattern characters
     */
    public String getLocalPatternChars() {
        return localPatternChars;
    }

    /**
     * Answers the array of Strings containing the full names of the months. Use
     * the Calendar constants Calendar.JANUARY, etc. to index into the array.
     * 
     * @return an array of String
     */
    public String[] getMonths() {
        return months.clone();
    }

    /**
     * Answers the array of Strings containing the abbreviated names of the
     * months. Use the Calendar constants Calendar.JANUARY, etc. to index into
     * the array.
     * 
     * @return an array of String
     */
    public String[] getShortMonths() {
        return shortMonths.clone();
    }

    /**
     * Answers the array of Strings containing the abbreviated names of the days
     * of the week. Use the Calendar constants Calendar.SUNDAY, etc. to index
     * into the array.
     * 
     * @return an array of String
     */
    public String[] getShortWeekdays() {
        return shortWeekdays.clone();
    }

    /**
     * Answers the array of Strings containing the full names of the days of the
     * week. Use the Calendar constants Calendar.SUNDAY, etc. to index into the
     * array.
     * 
     * @return an array of String
     */
    public String[] getWeekdays() {
        return weekdays.clone();
    }

    /**
     * Answers the two-dimensional array of Strings containing the names of the
     * timezones. Each element in the array is an array of five Strings, the
     * first is a TimeZone ID, and second and third are the full and abbreviated
     * timezone names for standard time, and the fourth and fifth are the full
     * and abbreviated names for daylight time.
     * 
     * @return a two-dimensional array of String
     */
    public String[][] getZoneStrings() {
        String[][] clone = new String[zoneStrings.length][];
        for (int i = zoneStrings.length; --i >= 0;) {
            clone[i] = zoneStrings[i].clone();
        }
        return clone;
    }

    /**
     * Answers an integer hash code for the receiver. Objects which are equal
     * answer the same value for this method.
     * 
     * @return the receiver's hash
     * 
     * @see #equals
     */
    @Override
    public int hashCode() {
        int hashCode;
        hashCode = localPatternChars.hashCode();
        for (String element : ampms) {
            hashCode += element.hashCode();
        }
        for (String element : eras) {
            hashCode += element.hashCode();
        }
        for (String element : months) {
            hashCode += element.hashCode();
        }
        for (String element : shortMonths) {
            hashCode += element.hashCode();
        }
        for (String element : shortWeekdays) {
            hashCode += element.hashCode();
        }
        for (String element : weekdays) {
            hashCode += element.hashCode();
        }
        for (String[] element : zoneStrings) {
            for (int j = 0; j < element.length; j++) {
                if (element[j] != null) {
                    hashCode += element[j].hashCode();
                }
            }
        }
        return hashCode;
    }

    /**
     * Sets the array of Strings which represent AM and PM. Use the Calendar
     * constants Calendar.AM and Calendar.PM to index into the array.
     * 
     * @param data
     *            the array of Strings
     */
    public void setAmPmStrings(String[] data) {
        ampms = data.clone();
    }

    /**
     * Sets the array of Strings which represent BC and AD. Use the Calendar
     * constants GregorianCalendar.BC and GregorianCalendar.AD to index into the
     * array.
     * 
     * @param data
     *            the array of Strings
     */
    public void setEras(String[] data) {
        eras = data.clone();
    }

    /**
     * Sets the pattern characters used by SimpleDateFormat to specify date and
     * time fields.
     * 
     * @param data
     *            the String containing the pattern characters
     * 
     * @exception NullPointerException
     *                when the data is null
     */
    public void setLocalPatternChars(String data) {
        if (data == null) {
            throw new NullPointerException();
        }
        localPatternChars = data;
    }

    /**
     * Sets the array of Strings containing the full names of the months. Use
     * the Calendar constants Calendar.JANUARY, etc. to index into the array.
     * 
     * @param data
     *            the array of Strings
     */
    public void setMonths(String[] data) {
        months = data.clone();
    }

    /**
     * Sets the array of Strings containing the abbreviated names of the months.
     * Use the Calendar constants Calendar.JANUARY, etc. to index into the
     * array.
     * 
     * @param data
     *            the array of Strings
     */
    public void setShortMonths(String[] data) {
        shortMonths = data.clone();
    }

    /**
     * Sets the array of Strings containing the abbreviated names of the days of
     * the week. Use the Calendar constants Calendar.SUNDAY, etc. to index into
     * the array.
     * 
     * @param data
     *            the array of Strings
     */
    public void setShortWeekdays(String[] data) {
        shortWeekdays = data.clone();
    }

    /**
     * Sets the array of Strings containing the full names of the days of the
     * week. Use the Calendar constants Calendar.SUNDAY, etc. to index into the
     * array.
     * 
     * @param data
     *            the array of Strings
     */
    public void setWeekdays(String[] data) {
        weekdays = data.clone();
    }

    /**
     * Sets the two-dimensional array of Strings containing the names of the
     * timezones. Each element in the array is an array of five Strings, the
     * first is a TimeZone ID, and second and third are the full and abbreviated
     * timezone names for standard time, and the fourth and fifth are the full
     * and abbreviated names for daylight time.
     * 
     * @param data
     *            the two-dimensional array of Strings
     */
    public void setZoneStrings(String[][] data) {
        zoneStrings = data.clone();
    }
}
