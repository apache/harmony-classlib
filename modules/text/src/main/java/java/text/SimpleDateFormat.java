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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;

import org.apache.harmony.text.internal.nls.Messages;

/**
 * SimpleDateFormat is used to format and parse Gregorian calendar dates and
 * times based on a pattern of date and time fields. Each date and time field is
 * specified in the pattern by a specific character. The characters used can be
 * either localized or non-localized. For some fields, which have both numeric
 * and text representations or abbreviated as well as full names, the number of
 * grouped characters specifies how the field is formatted or parsed.
 */
public class SimpleDateFormat extends DateFormat {

    private static final long serialVersionUID = 4774881970558875024L;

    private static final String patternChars = "GyMdkHmsSEDFwWahKzYeugAZvcLQqV"; //$NON-NLS-1$

    private String pattern;

    private DateFormatSymbols formatData;

    transient private int creationYear;

    private Date defaultCenturyStart;

    private transient com.ibm.icu.text.SimpleDateFormat icuFormat;

    /**
     * Constructs a new SimpleDateFormat for formatting and parsing dates and
     * times in the SHORT style for the default Locale.
     */
    public SimpleDateFormat() {
        this(Locale.getDefault());
        icuFormat = new com.ibm.icu.text.SimpleDateFormat();
        pattern = (String)getInternalField("pattern",icuFormat);
        formatData = new DateFormatSymbols(Locale.getDefault());
    }

    /**
     * Constructs a new SimpleDateFormat using the specified non-localized
     * pattern and the DateFormatSymbols and Calendar for the default Locale.
     * 
     * @param pattern
     *            the pattern
     * 
     * @exception NullPointerException
     *                if a <code>null</code> value of <code>pattern</code>
     *                is supplied.
     * @exception IllegalArgumentException
     *                if <code>pattern</code> is not considered to be useable
     *                by this formatter.
     */
    public SimpleDateFormat(String pattern) {
        this(pattern, Locale.getDefault());
    }
    
    /**
     * Validate the format character.
     * 
     * @param format
     *            the format character
     * 
     * @throws IllegalArgumentException
     *             when the format character is invalid
     */
    private void validateFormat(char format) {
        int index = patternChars.indexOf(format);
        if (index == -1) {
            // text.03=Unknown pattern character - '{0}'
            throw new IllegalArgumentException(Messages.getString(
                    "text.03", format)); //$NON-NLS-1$
        }
    }

    /**
     * Validate the pattern.
     * 
     * @param template
     *            the pattern to validate.
     * 
     * @throws NullPointerException
     *             if the pattern is null
     * @throws IllegalArgumentException
     *             if the pattern is invalid
     */
    private void validatePattern(String template) {
        boolean quote = false;
        int next, last = -1, count = 0;

        final int patternLength = template.length();
        for (int i = 0; i < patternLength; i++) {
            next = (template.charAt(i));
            if (next == '\'') {
                if (count > 0) {
                    validateFormat((char) last);
                    count = 0;
                }
                if (last == next) {
                    last = -1;
                } else {
                    last = next;
                }
                quote = !quote;
                continue;
            }
            if (!quote
                    && (last == next || (next >= 'a' && next <= 'z') || (next >= 'A' && next <= 'Z'))) {
                if (last == next) {
                    count++;
                } else {
                    if (count > 0) {
                        validateFormat((char) last);
                    }
                    last = next;
                    count = 1;
                }
            } else {
                if (count > 0) {
                    validateFormat((char) last);
                    count = 0;
                }
                last = -1;
            }
        }
        if (count > 0) {
            validateFormat((char) last);
        }

        if (quote) {
            // text.04=Unterminated quote {0}
            throw new IllegalArgumentException(Messages.getString("text.04")); //$NON-NLS-1$
        }

    }
    
    /**
     * Constructs a new SimpleDateFormat using the specified non-localized
     * pattern and DateFormatSymbols and the Calendar for the default Locale.
     * 
     * @param template
     *            the pattern
     * @param value
     *            the DateFormatSymbols
     * 
     * @exception NullPointerException
     *                if the pattern is null
     * @exception IllegalArgumentException
     *                if the pattern is invalid
     */
    public SimpleDateFormat(String template, DateFormatSymbols value) {
        this(Locale.getDefault());
        validatePattern(template);
        icuFormat = new com.ibm.icu.text.SimpleDateFormat(template, Locale.getDefault());
        pattern = template;
        formatData = (DateFormatSymbols) value.clone();
    }

    private void copySymbols(DateFormatSymbols value, com.ibm.icu.text.DateFormatSymbols icuSymbols) {
        icuSymbols.setAmPmStrings(value.getAmPmStrings());
        icuSymbols.setEras(value.getEras());
        icuSymbols.setLocalPatternChars(value.getLocalPatternChars());
        icuSymbols.setMonths(value.getMonths());
        icuSymbols.setShortMonths(value.getShortMonths());
        icuSymbols.setShortWeekdays(value.getShortWeekdays());
        icuSymbols.setWeekdays(value.getWeekdays());
        icuSymbols.setZoneStrings(value.getZoneStrings());
    }
    
    /**
     * Constructs a new SimpleDateFormat using the specified non-localized
     * pattern and the DateFormatSymbols and Calendar for the specified Locale.
     * 
     * @param template
     *            the pattern
     * @param locale
     *            the Locale
     * 
     * @exception NullPointerException
     *                if the pattern is null
     * @exception IllegalArgumentException
     *                if the pattern is invalid
     */
    public SimpleDateFormat(String template, Locale locale) {
        this(locale);
        validatePattern(template);
        icuFormat = new com.ibm.icu.text.SimpleDateFormat(template, locale);
        pattern = template;
        formatData = new DateFormatSymbols(locale);
    }

    SimpleDateFormat(Locale locale, com.ibm.icu.text.SimpleDateFormat icuFormat){
        this(locale);
        this.icuFormat = icuFormat;
        pattern = (String)Format.getInternalField("pattern", icuFormat);
        formatData = new DateFormatSymbols(locale);
    }
    
    private SimpleDateFormat(Locale locale) {
        numberFormat = NumberFormat.getInstance(locale);
        numberFormat.setParseIntegerOnly(true);
        numberFormat.setGroupingUsed(false);
        calendar = new GregorianCalendar(locale);
        calendar.add(Calendar.YEAR, -80);
        creationYear = calendar.get(Calendar.YEAR);
        defaultCenturyStart = calendar.getTime();
    }

    /**
     * Changes the pattern of this SimpleDateFormat to the specified pattern
     * which uses localized pattern characters.
     * 
     * @param template
     *            the localized pattern
     */
    public void applyLocalizedPattern(String template) {
        icuFormat.applyLocalizedPattern(template);
        pattern = icuFormat.toPattern();
    }

    /**
     * Changes the pattern of this SimpleDateFormat to the specified pattern
     * which uses non-localized pattern characters.
     * 
     * @param template
     *            the non-localized pattern
     * 
     * @exception NullPointerException
     *                if the pattern is null
     * @exception IllegalArgumentException
     *                if the pattern is invalid
     */
    public void applyPattern(String template) {
        validatePattern(template);
        icuFormat.applyPattern(template);
        pattern = template;
    }

    /**
     * Answers a new SimpleDateFormat with the same pattern and properties as
     * this SimpleDateFormat.
     * 
     * @return a shallow copy of this SimpleDateFormat
     * 
     * @see java.lang.Cloneable
     */
    @Override
    public Object clone() {
        SimpleDateFormat clone = (SimpleDateFormat) super.clone();
        clone.formatData = (DateFormatSymbols) formatData.clone();
        clone.defaultCenturyStart = new Date(defaultCenturyStart.getTime());
        return clone;
    }

    /**
     * Compares the specified object to this SimpleDateFormat and answer if they
     * are equal. The object must be an instance of SimpleDateFormat and have
     * the same DateFormat properties, pattern, DateFormatSymbols, and creation
     * year.
     * 
     * @param object
     *            the object to compare with this object
     * @return true if the specified object is equal to this SimpleDateFormat,
     *         false otherwise
     * 
     * @see #hashCode
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof SimpleDateFormat)) {
            return false;
        }
        SimpleDateFormat simple = (SimpleDateFormat) object;
        return super.equals(object) && pattern.equals(simple.pattern)
                && formatData.equals(simple.formatData);
    }

    /**
     * Formats the specified object using the rules of this SimpleDateFormat and
     * returns an AttributedCharacterIterator with the formatted Date and
     * attributes.
     * 
     * @param object
     *            the object to format
     * @return an AttributedCharacterIterator with the formatted date and
     *         attributes
     * 
     * @exception NullPointerException
     *                when the object is null
     * @exception IllegalArgumentException
     *                when the object cannot be formatted by this Format
     */
    @Override
    public AttributedCharacterIterator formatToCharacterIterator(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
        if (object instanceof Date) {
            return formatToCharacterIteratorImpl((Date) object);
        }
        if (object instanceof Number) {
            return formatToCharacterIteratorImpl(new Date(((Number) object)
                    .longValue()));
        }
        throw new IllegalArgumentException();
        
    }
    
    private AttributedCharacterIterator formatToCharacterIteratorImpl(Date date) {
        StringBuffer buffer = new StringBuffer();
        Vector<FieldPosition> fields = new Vector<FieldPosition>();

        // format the date, and find fields
        formatImpl(date, buffer, null, fields);

        // create and AttributedString with the formatted buffer
        AttributedString as = new AttributedString(buffer.toString());

        // add DateFormat field attributes to the AttributedString
        for (int i = 0; i < fields.size(); i++) {
            FieldPosition pos = fields.elementAt(i);
            Format.Field attribute = pos.getFieldAttribute();
            as.addAttribute(attribute, attribute, pos.getBeginIndex(), pos
                    .getEndIndex());
        }

        // return the CharacterIterator from AttributedString
        return as.getIterator();
    }

    /**
     * Formats the date.
     * <p>
     * If the FieldPosition <code>field</code> is not null, and the field
     * specified by this FieldPosition is formatted, set the begin and end index
     * of the formatted field in the FieldPosition.
     * <p>
     * If the Vector <code>fields</code> is not null, find fields of this
     * date, set FieldPositions with these fields, and add them to the fields
     * vector.
     * 
     * @param date
     *            Date to Format
     * @param buffer
     *            StringBuffer to store the resulting formatted String
     * @param field
     *            FieldPosition to set begin and end index of the field
     *            specified, if it is part of the format for this date
     * @param fields
     *            Vector used to store the FieldPositions for each field in this
     *            date
     * 
     * @return the formatted Date
     * 
     * @exception IllegalArgumentException
     *                when the object cannot be formatted by this Format
     */
    private StringBuffer formatImpl(Date date, StringBuffer buffer,
            FieldPosition field, Vector<FieldPosition> fields) {

        boolean quote = false;
        int next, last = -1, count = 0;
        calendar.setTime(date);
        if (field != null) {
            field.clear();
        }

        final int patternLength = pattern.length();
        for (int i = 0; i < patternLength; i++) {
            next = (pattern.charAt(i));
            if (next == '\'') {
                if (count > 0) {
                    append(buffer, field, fields, (char) last, count);
                    count = 0;
                }
                if (last == next) {
                    buffer.append('\'');
                    last = -1;
                } else {
                    last = next;
                }
                quote = !quote;
                continue;
            }
            if (!quote
                    && (last == next || (next >= 'a' && next <= 'z') || (next >= 'A' && next <= 'Z'))) {
                if (last == next) {
                    count++;
                } else {
                    if (count > 0) {
                        append(buffer, field, fields, (char) last, count);
                    }
                    last = next;
                    count = 1;
                }
            } else {
                if (count > 0) {
                    append(buffer, field, fields, (char) last, count);
                    count = 0;
                }
                last = -1;
                buffer.append((char) next);
            }
        }
        if (count > 0) {
            append(buffer, field, fields, (char) last, count);
        }
        return buffer;
    }
    
    private void append(StringBuffer buffer, FieldPosition position,
            Vector<FieldPosition> fields, char format, int count) {
        int field = -1;
        int index = patternChars.indexOf(format);
        if (index == -1) {
            // text.03=Unknown pattern character - '{0}'
            throw new IllegalArgumentException(Messages.getString(
                    "text.03", format)); //$NON-NLS-1$
        }

        int beginPosition = buffer.length();
        Field dateFormatField = null;
        switch (index) {
            case ERA_FIELD:
                dateFormatField = Field.ERA;
                buffer.append(formatData.eras[calendar.get(Calendar.ERA)]);
                break;
            case YEAR_FIELD:
                dateFormatField = Field.YEAR;
                int year = calendar.get(Calendar.YEAR);
                if (count < 4) {
                    appendNumber(buffer, 2, year %= 100);
                } else {
                    appendNumber(buffer, count, year);
                }
                break;
            case MONTH_FIELD:
                dateFormatField = Field.MONTH;
                int month = calendar.get(Calendar.MONTH);
                if (count <= 2) {
                    appendNumber(buffer, count, month + 1);
                } else if (count == 3) {
                    buffer.append(formatData.shortMonths[month]);
                } else {
                    buffer.append(formatData.months[month]);
                }
                break;
            case DATE_FIELD:
                dateFormatField = Field.DAY_OF_MONTH;
                field = Calendar.DATE;
                break;
            case HOUR_OF_DAY1_FIELD: // k
                dateFormatField = Field.HOUR_OF_DAY1;
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                appendNumber(buffer, count, hour == 0 ? 24 : hour);
                break;
            case HOUR_OF_DAY0_FIELD: // H
                dateFormatField = Field.HOUR_OF_DAY0;
                field = Calendar.HOUR_OF_DAY;
                break;
            case MINUTE_FIELD:
                dateFormatField = Field.MINUTE;
                field = Calendar.MINUTE;
                break;
            case SECOND_FIELD:
                dateFormatField = Field.SECOND;
                field = Calendar.SECOND;
                break;
            case MILLISECOND_FIELD:
                dateFormatField = Field.MILLISECOND;
                int value = calendar.get(Calendar.MILLISECOND);
                appendNumber(buffer, count, value);
                break;
            case DAY_OF_WEEK_FIELD:
                dateFormatField = Field.DAY_OF_WEEK;
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                if (count < 4) {
                    buffer.append(formatData.shortWeekdays[day]);
                } else {
                    buffer.append(formatData.weekdays[day]);
                }
                break;
            case DAY_OF_YEAR_FIELD:
                dateFormatField = Field.DAY_OF_YEAR;
                field = Calendar.DAY_OF_YEAR;
                break;
            case DAY_OF_WEEK_IN_MONTH_FIELD:
                dateFormatField = Field.DAY_OF_WEEK_IN_MONTH;
                field = Calendar.DAY_OF_WEEK_IN_MONTH;
                break;
            case WEEK_OF_YEAR_FIELD:
                dateFormatField = Field.WEEK_OF_YEAR;
                field = Calendar.WEEK_OF_YEAR;
                break;
            case WEEK_OF_MONTH_FIELD:
                dateFormatField = Field.WEEK_OF_MONTH;
                field = Calendar.WEEK_OF_MONTH;
                break;
            case AM_PM_FIELD:
                dateFormatField = Field.AM_PM;
                buffer.append(formatData.ampms[calendar.get(Calendar.AM_PM)]);
                break;
            case HOUR1_FIELD: // h
                dateFormatField = Field.HOUR1;
                hour = calendar.get(Calendar.HOUR);
                appendNumber(buffer, count, hour == 0 ? 12 : hour);
                break;
            case HOUR0_FIELD: // K
                dateFormatField = Field.HOUR0;
                field = Calendar.HOUR;
                break;
            case TIMEZONE_FIELD: // z
                dateFormatField = Field.TIME_ZONE;
                appendTimeZone(buffer, count, true);
                break;
            case com.ibm.icu.text.DateFormat.TIMEZONE_RFC_FIELD: // Z
                dateFormatField = Field.TIME_ZONE;
                appendTimeZone(buffer, count, false);
                break;
        }
        if (field != -1) {
            appendNumber(buffer, count, calendar.get(field));
        }

        if (fields != null) {
            position = new FieldPosition(dateFormatField);
            position.setBeginIndex(beginPosition);
            position.setEndIndex(buffer.length());
            fields.add(position);
        } else {
            // Set to the first occurrence
            if ((position.getFieldAttribute() == dateFormatField || (position
                    .getFieldAttribute() == null && position.getField() == index))
                    && position.getEndIndex() == 0) {
                position.setBeginIndex(beginPosition);
                position.setEndIndex(buffer.length());
            }
        }
    }
    
    private void appendTimeZone(StringBuffer buffer, int count,
            boolean generalTimezone) {
        // cannot call TimeZone.getDisplayName() because it would not use
        // the DateFormatSymbols of this SimpleDateFormat

        if (generalTimezone) {
            String id = calendar.getTimeZone().getID();
            String[][] zones = formatData.zoneStrings;
            String[] zone = null;
            for (String[] element : zones) {
                if (id.equals(element[0])) {
                    zone = element;
                    break;
                }
            }
            if (zone == null) {
                int offset = calendar.get(Calendar.ZONE_OFFSET)
                        + calendar.get(Calendar.DST_OFFSET);
                char sign = '+';
                if (offset < 0) {
                    sign = '-';
                    offset = -offset;
                }
                buffer.append("GMT"); //$NON-NLS-1$
                buffer.append(sign);
                appendNumber(buffer, 2, offset / 3600000);
                buffer.append(':');
                appendNumber(buffer, 2, (offset % 3600000) / 60000);
            } else {
                int daylight = calendar.get(Calendar.DST_OFFSET) == 0 ? 0 : 2;
                if (count < 4) {
                    buffer.append(zone[2 + daylight]);
                } else {
                    buffer.append(zone[1 + daylight]);
                }
            }
        } else {
            int offset = calendar.get(Calendar.ZONE_OFFSET)
                    + calendar.get(Calendar.DST_OFFSET);
            char sign = '+';
            if (offset < 0) {
                sign = '-';
                offset = -offset;
            }
            buffer.append(sign);
            appendNumber(buffer, 2, offset / 3600000);
            appendNumber(buffer, 2, (offset % 3600000) / 60000);
        }
    }

    private void appendNumber(StringBuffer buffer, int count, int value) {
        int minimumIntegerDigits = numberFormat.getMinimumIntegerDigits();
        numberFormat.setMinimumIntegerDigits(count);
        numberFormat.format(new Integer(value), buffer, new FieldPosition(0));
        numberFormat.setMinimumIntegerDigits(minimumIntegerDigits);
    }
    

    /**
     * Formats the specified Date into the specified StringBuffer using the
     * pattern of this SimpleDateFormat. If the field specified by the
     * FieldPosition is formatted, set the begin and end index of the formatted
     * field in the FieldPosition.
     * 
     * @param date
     *            the Date to format
     * @param buffer
     *            the StringBuffer
     * @param field
     *            the FieldPosition
     * @return the StringBuffer parameter <code>buffer</code>
     * 
     * @exception IllegalArgumentException
     *                when there are invalid characters in the pattern
     */
    @Override
    public StringBuffer format(Date date, StringBuffer buffer,
            FieldPosition field) {
        icuFormat.setTimeZone(com.ibm.icu.util.TimeZone.getTimeZone(calendar
                .getTimeZone().getID()));
        return icuFormat.format(date, buffer, field);
    }


    /**
     * Answers the Date which is the start of the one hundred year period for
     * two digits year values.
     * 
     * @return a Date
     */
    public Date get2DigitYearStart() {
        return defaultCenturyStart;
    }

    /**
     * Answers the DateFormatSymbols used by this SimpleDateFormat.
     * 
     * @return a DateFormatSymbols
     */
    public DateFormatSymbols getDateFormatSymbols() {
        // Return a clone so the arrays in the ResourceBundle are not modified
        return (DateFormatSymbols) formatData.clone();
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
        return super.hashCode() + pattern.hashCode() + formatData.hashCode()
                + creationYear;
    }

    /**
     * Parse a Date from the specified String starting at the index specified by
     * the ParsePosition. If the string is successfully parsed, the index of the
     * ParsePosition is updated to the index following the parsed text.
     * 
     * @param string
     *            the String to parse according to the pattern of this
     *            SimpleDateFormat
     * @param position
     *            the ParsePosition, updated on return with the index following
     *            the parsed text, or on error the index is unchanged and the
     *            error index is set to the index where the error occurred
     * @return the Date resulting from the parse, or null if there is an error
     * 
     * @exception IllegalArgumentException
     *                when there are invalid characters in the pattern
     */
    @Override
    public Date parse(String string, ParsePosition position) {
        icuFormat.setTimeZone(com.ibm.icu.util.TimeZone.getTimeZone(calendar
                .getTimeZone().getID()));
        return icuFormat.parse(string,position);
    }

    /**
     * Sets the Date which is the start of the one hundred year period for two
     * digits year values.
     * 
     * @param date
     *            the Date
     */
    public void set2DigitYearStart(Date date) {
        icuFormat.set2DigitYearStart(date);
        defaultCenturyStart = date;
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        creationYear = cal.get(Calendar.YEAR);
    }

    /**
     * Sets the DateFormatSymbols used by this SimpleDateFormat.
     * 
     * @param value
     *            the DateFormatSymbols
     */
    public void setDateFormatSymbols(DateFormatSymbols value) {
        com.ibm.icu.text.DateFormatSymbols icuSymbols = new com.ibm.icu.text.DateFormatSymbols();
        copySymbols(value, icuSymbols);
        icuFormat.setDateFormatSymbols(icuSymbols);
        formatData = (DateFormatSymbols) value.clone();
    }

    /**
     * Answers the pattern of this SimpleDateFormat using localized pattern
     * characters.
     * 
     * @return the localized pattern
     */
    public String toLocalizedPattern() {
        return icuFormat.toLocalizedPattern();
    }

    /**
     * Answers the pattern of this SimpleDateFormat using non-localized pattern
     * characters.
     * 
     * @return the non-localized pattern
     */
    public String toPattern() {
        return pattern;
    }

    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("defaultCenturyStart", Date.class), //$NON-NLS-1$
            new ObjectStreamField("formatData", DateFormatSymbols.class), //$NON-NLS-1$
            new ObjectStreamField("pattern", String.class), //$NON-NLS-1$
            new ObjectStreamField("serialVersionOnStream", Integer.TYPE), }; //$NON-NLS-1$

    private void writeObject(ObjectOutputStream stream) throws IOException {
        ObjectOutputStream.PutField fields = stream.putFields();
        fields.put("defaultCenturyStart", defaultCenturyStart); //$NON-NLS-1$
        fields.put("formatData", formatData); //$NON-NLS-1$
        fields.put("pattern", pattern); //$NON-NLS-1$
        fields.put("serialVersionOnStream", 1); //$NON-NLS-1$
        stream.writeFields();
    }

    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        ObjectInputStream.GetField fields = stream.readFields();
        int version = fields.get("serialVersionOnStream", 0); //$NON-NLS-1$
        Date date;
        if (version > 0) {
            date = (Date) fields.get("defaultCenturyStart", new Date()); //$NON-NLS-1$
        } else {
            date = new Date();
        }
        set2DigitYearStart(date);
        formatData = (DateFormatSymbols) fields.get("formatData", null); //$NON-NLS-1$
        pattern = (String) fields.get("pattern", ""); //$NON-NLS-1$ //$NON-NLS-2$
    }
}
