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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Currency;
import java.util.Locale;

/**
 * DecimalFormat is used to format and parse numbers, both integers and
 * fractions, based on a pattern. The pattern characters used can be either
 * localized or non-localized.
 */
public class DecimalFormat extends NumberFormat {

    private static final long serialVersionUID = 864413376551465018L;

    private transient boolean parseBigDecimal = false;

    private transient DecimalFormatSymbols symbols;

    private transient com.ibm.icu.text.DecimalFormat dform;

    private transient com.ibm.icu.text.DecimalFormatSymbols icuSymbols;

    private static final int CURRENT_SERIAL_VERTION = 3;

    private transient int serialVersionOnStream = 3;
    
    // set default RoundingMode to RoundingMode.HALF_EVEN
    private transient RoundingMode roundingMode = RoundingMode.HALF_EVEN;

    /**
     * Constructs a new DecimalFormat for formatting and parsing numbers for the
     * default Locale.
     */
    public DecimalFormat() {
        Locale locale = Locale.getDefault();
        icuSymbols = new com.ibm.icu.text.DecimalFormatSymbols(locale);
        symbols = new DecimalFormatSymbols(locale);
        dform = new com.ibm.icu.text.DecimalFormat();

        super.setMaximumFractionDigits(dform.getMaximumFractionDigits());
        super.setMaximumIntegerDigits(dform.getMaximumIntegerDigits());
        super.setMinimumFractionDigits(dform.getMinimumFractionDigits());
        super.setMinimumIntegerDigits(dform.getMinimumIntegerDigits());
    }

    /**
     * Constructs a new DecimalFormat using the specified non-localized pattern
     * and the DecimalFormatSymbols for the default Locale.
     * 
     * @param pattern
     *            the non-localized pattern
     * 
     * @exception IllegalArgumentException
     *                when the pattern cannot be parsed
     */
    public DecimalFormat(String pattern) {
        Locale locale = Locale.getDefault();
        icuSymbols = new com.ibm.icu.text.DecimalFormatSymbols(locale);
        symbols = new DecimalFormatSymbols(locale);
        dform = new com.ibm.icu.text.DecimalFormat(pattern, icuSymbols);

        super.setMaximumFractionDigits(dform.getMaximumFractionDigits());
        super.setMaximumIntegerDigits(dform.getMaximumIntegerDigits());
        super.setMinimumFractionDigits(dform.getMinimumFractionDigits());
        super.setMinimumIntegerDigits(dform.getMinimumIntegerDigits());
    }

    /**
     * Constructs a new DecimalFormat using the specified non-localized pattern
     * and DecimalFormatSymbols.
     * 
     * @param pattern
     *            the non-localized pattern
     * @param value
     *            the DecimalFormatSymbols
     * 
     * @exception IllegalArgumentException
     *                when the pattern cannot be parsed
     */
    public DecimalFormat(String pattern, DecimalFormatSymbols value) {
        symbols = (DecimalFormatSymbols) value.clone();
        Locale locale = symbols.getLocale(); //$NON-NLS-1$
        icuSymbols = new com.ibm.icu.text.DecimalFormatSymbols(locale);
        copySymbols(icuSymbols, symbols);

        dform = new com.ibm.icu.text.DecimalFormat(pattern, icuSymbols);

        super.setMaximumFractionDigits(dform.getMaximumFractionDigits());
        super.setMaximumIntegerDigits(dform.getMaximumIntegerDigits());
        super.setMinimumFractionDigits(dform.getMinimumFractionDigits());
        super.setMinimumIntegerDigits(dform.getMinimumIntegerDigits());
    }

    /**
     * Changes the pattern of this DecimalFormat to the specified pattern which
     * uses localized pattern characters.
     * 
     * @param pattern
     *            the localized pattern
     * 
     * @exception IllegalArgumentException
     *                when the pattern cannot be parsed
     */
    public void applyLocalizedPattern(String pattern) {
        dform.applyLocalizedPattern(pattern);
    }

    /**
     * Changes the pattern of this SimpleDateFormat to the specified pattern
     * which uses non-localized pattern characters.
     * 
     * @param pattern
     *            the non-localized pattern
     * 
     * @exception IllegalArgumentException
     *                when the pattern cannot be parsed
     */
    public void applyPattern(String pattern) {

        dform.applyPattern(pattern);
    }

    /**
     * Answers a new instance of DecimalFormat with the same pattern and
     * properties as this DecimalFormat.
     * 
     * @return a shallow copy of this DecimalFormat
     * 
     * @see java.lang.Cloneable
     */
    @Override
    public Object clone() {
        DecimalFormat clone = (DecimalFormat) super.clone();
        clone.dform = (com.ibm.icu.text.DecimalFormat) dform.clone();
        clone.symbols = (DecimalFormatSymbols) symbols.clone();
        return clone;
    }

    /**
     * Compares the specified object to this DecimalFormat and answer if they
     * are equal. The object must be an instance of DecimalFormat with the same
     * pattern and properties.
     * 
     * @param object
     *            the object to compare with this object
     * @return true if the specified object is equal to this DecimalFormat,
     *         false otherwise
     * 
     * @see #hashCode
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DecimalFormat)) {
            return false;
        }
        DecimalFormat format = (DecimalFormat) object;
        return (this.dform == null ? format.dform == null : this.dform
                .equals(format.dform));
    }

    /**
     * Formats the specified object using the rules of this DecimalNumberFormat
     * and returns an AttributedCharacterIterator with the formatted number and
     * attributes.
     * 
     * @param object
     *            the object to format
     * @return an AttributedCharacterIterator with the formatted number and
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
        return dform.formatToCharacterIterator(object);
    }

    /**
     * Formats the double value into the specified StringBuffer using the
     * pattern of this DecimalFormat. If the field specified by the
     * FieldPosition is formatted, set the begin and end index of the formatted
     * field in the FieldPosition.
     * 
     * @param value
     *            the double to format
     * @param buffer
     *            the StringBuffer
     * @param position
     *            the FieldPosition
     * @return the StringBuffer parameter <code>buffer</code>
     */
    @Override
    public StringBuffer format(double value, StringBuffer buffer,
            FieldPosition position) {
        return dform.format(value, buffer, position);
    }

    /**
     * Formats the long value into the specified StringBuffer using the pattern
     * of this DecimalFormat. If the field specified by the FieldPosition is
     * formatted, set the begin and end index of the formatted field in the
     * FieldPosition.
     * 
     * @param value
     *            the long to format
     * @param buffer
     *            the StringBuffer
     * @param position
     *            the FieldPosition
     * @return the StringBuffer parameter <code>buffer</code>
     */
    @Override
    public StringBuffer format(long value, StringBuffer buffer,
            FieldPosition position) {
        return dform.format(value, buffer, position);
    }

    /**
     * Formats the number into the specified StringBuffer using the pattern of
     * this DecimalFormat. If the field specified by the FieldPosition is
     * formatted, set the begin and end index of the formatted field in the
     * FieldPosition.
     * 
     * @param number
     *            the object to format
     * @param toAppendTo
     *            the StringBuffer
     * @param pos
     *            the FieldPosition
     * @return the StringBuffer parameter <code>buffer</code>
     * @throws IllegalArgumentException
     *             if the given number is not instance of <code>Number</code>
     */
    @Override
    public final StringBuffer format(Object number, StringBuffer toAppendTo,
            FieldPosition pos) {
        if (!(number instanceof Number)) {
            throw new IllegalArgumentException();
        }
        if (toAppendTo == null || pos == null) {
            throw new NullPointerException();
        }
        if (number instanceof BigInteger || number instanceof BigDecimal) {
            return dform.format(number, toAppendTo, pos);
        }
        return super.format(number, toAppendTo, pos);
    }

    /**
     * Answers the DecimalFormatSymbols used by this DecimalFormat.
     * 
     * @return a DecimalFormatSymbols
     */
    public DecimalFormatSymbols getDecimalFormatSymbols() {
        return (DecimalFormatSymbols) symbols.clone();
    }

    /**
     * Answers the currency used by this decimal format.
     * 
     * @return currency of DecimalFormatSymbols used by this decimal format
     * @see DecimalFormatSymbols#getCurrency()
     */
    @Override
    public Currency getCurrency() {
        final com.ibm.icu.util.Currency cur = dform.getCurrency();
        final String code = (cur == null) ? "XXX" : cur.getCurrencyCode(); //$NON-NLS-1$

        return Currency.getInstance(code);
    }

    /**
     * Answers the number of digits grouped together by the grouping separator.
     * 
     * @return the number of digits grouped together
     */
    public int getGroupingSize() {
        return dform.getGroupingSize();
    }

    /**
     * Answers the multiplier which is applied to the number before formatting
     * or after parsing.
     * 
     * @return the multiplier
     */
    public int getMultiplier() {
        return dform.getMultiplier();
    }

    /**
     * Answers the prefix which is formatted or parsed before a negative number.
     * 
     * @return the negative prefix
     */
    public String getNegativePrefix() {
        return dform.getNegativePrefix();
    }

    /**
     * Answers the suffix which is formatted or parsed after a negative number.
     * 
     * @return the negative suffix
     */
    public String getNegativeSuffix() {
        return dform.getNegativeSuffix();
    }

    /**
     * Answers the prefix which is formatted or parsed before a positive number.
     * 
     * @return the positive prefix
     */
    public String getPositivePrefix() {
        return dform.getPositivePrefix();
    }

    /**
     * Answers the suffix which is formatted or parsed after a positive number.
     * 
     * @return the positive suffix
     */
    public String getPositiveSuffix() {
        return dform.getPositiveSuffix();
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
        return dform.hashCode();
    }

    /**
     * Answers whether the decimal separator is shown when there are no
     * fractional digits.
     * 
     * @return true if the decimal separator should always be formatted, false
     *         otherwise
     */
    public boolean isDecimalSeparatorAlwaysShown() {
        return dform.isDecimalSeparatorAlwaysShown();
    }

    /**
     * This value indicates whether the return object of the parse operation
     * will be of type BigDecimal. This value will default to false.
     * 
     * @return true and parse will always return BigDecimals, false and the type
     *         of the result will be Long or Double.
     */
    public boolean isParseBigDecimal() {
        return this.parseBigDecimal;
    }

    /**
     * When DecimalFormat is used to parsing, and this value is set to true,
     * then all the resulting number will be of type
     * <code>java.lang.Integer</code>. Except that, NaN, positive and
     * negative infinity are still returned as <code>java.lang.Double</code>
     * 
     * In this implementation, com.ibm.icu.text.DecimalFormat is wrapped to
     * fulfill most of the format and parse feature. And this method is
     * delegated to the wrapped instance of com.ibm.icu.text.DecimalFormat.
     * 
     * @param value
     *            If set to true, all the resulting number will be of type
     *            java.lang.Integer except some special cases.
     */
    @Override
    public void setParseIntegerOnly(boolean value) {
        dform.setParseIntegerOnly(value);
    }

    /**
     * Returns true if this <code>DecimalFormat</code>'s all resulting number
     * will be of type <code>java.lang.Integer</code>
     * 
     * @return true if this <code>DecimalFormat</code>'s all resulting number
     *         will be of type <code>java.lang.Integer</code>
     */
    @Override
    public boolean isParseIntegerOnly() {
        return dform.isParseIntegerOnly();
    }

    private static final Double NEGATIVE_ZERO_DOUBLE = new Double(-0.0);

    /**
     * Parse a Long or Double from the specified String starting at the index
     * specified by the ParsePosition. If the string is successfully parsed, the
     * index of the ParsePosition is updated to the index following the parsed
     * text.
     * 
     * @param string
     *            the String to parse
     * @param position
     *            the ParsePosition, updated on return with the index following
     *            the parsed text, or on error the index is unchanged and the
     *            error index is set to the index where the error occurred
     * @return a Long or Double resulting from the parse, or null if there is an
     *         error. The result will be a Long if the parsed number is an
     *         integer in the range of a long, otherwise the result is a Double.
     */
    @Override
    public Number parse(String string, ParsePosition position) {
        Number number = dform.parse(string, position);
        if (null == number) {
            return null;
        }
        if (this.isParseBigDecimal()) {
            if (number instanceof Long) {
                return new BigDecimal(number.longValue());
            }
            if ((number instanceof Double) && !((Double) number).isInfinite()
                    && !((Double) number).isNaN()) {

                return new BigDecimal(number.doubleValue());
            }
            if (number instanceof BigInteger) {
                return new BigDecimal(number.doubleValue());
            }
            if (number instanceof com.ibm.icu.math.BigDecimal) {
                return new BigDecimal(number.toString());
            }
            return number;
        }
        if ((number instanceof com.ibm.icu.math.BigDecimal)
                || (number instanceof BigInteger)) {
            return new Double(number.doubleValue());
        }

        if (this.isParseIntegerOnly() && number.equals(NEGATIVE_ZERO_DOUBLE)) {
            return new Long(0);
        }
        return number;

    }

    /**
     * Sets the DecimalFormatSymbols used by this DecimalFormat.
     * 
     * @param value
     *            the DecimalFormatSymbols
     */
    public void setDecimalFormatSymbols(DecimalFormatSymbols value) {
        if (value != null) {
            symbols = (DecimalFormatSymbols) value.clone();
            icuSymbols = dform.getDecimalFormatSymbols();
            copySymbols(icuSymbols, symbols);
            dform.setDecimalFormatSymbols(icuSymbols);
        }
    }

    /**
     * Sets the currency used by this decimal format. The min and max fraction
     * digits remain the same.
     * 
     * @param currency
     * @see DecimalFormatSymbols#setCurrency(Currency)
     */
    @Override
    public void setCurrency(Currency currency) {
        dform.setCurrency(com.ibm.icu.util.Currency.getInstance(currency
                .getCurrencyCode()));
        symbols.setCurrency(currency);
    }

    /**
     * Sets whether the decimal separator is shown when there are no fractional
     * digits.
     * 
     * @param value
     *            true if the decimal separator should always be formatted,
     *            false otherwise
     */
    public void setDecimalSeparatorAlwaysShown(boolean value) {
        dform.setDecimalSeparatorAlwaysShown(value);
    }

    /**
     * Sets the number of digits grouped together by the grouping separator.
     * 
     * @param value
     *            the number of digits grouped together
     */
    public void setGroupingSize(int value) {
        dform.setGroupingSize(value);
    }

    /**
     * Sets whether or not grouping will be used in this format. Grouping
     * affects both parsing and formatting.
     * 
     * @param value
     *            true if uses grouping,false otherwise.
     * 
     */
    @Override
    public void setGroupingUsed(boolean value) {
        dform.setGroupingUsed(value);
    }

    /**
     * This value indicates whether grouping will be used in this format.
     * 
     * @return true if grouping is used,false otherwise.
     */
    @Override
    public boolean isGroupingUsed() {
        return dform.isGroupingUsed();
    }

    /**
     * Sets the maximum number of fraction digits that are printed when
     * formatting. If the maximum is less than the number of fraction digits,
     * the least significant digits are truncated. Limit the maximum to
     * DOUBLE_FRACTION_DIGITS.
     * 
     * @param value
     *            the maximum number of fraction digits
     */
    @Override
    public void setMaximumFractionDigits(int value) {
        super.setMaximumFractionDigits(value);
        dform.setMaximumFractionDigits(value);
        
        // update the RoundingIncrement
		setRoundingIncrement(value);
    }

    /**
     * Sets the maximum number of integer digits that are printed when
     * formatting. If the maximum is less than the number of integer digits, the
     * most significant digits are truncated. Limit the maximum to
     * DOUBLE_INTEGER_DIGITS.
     * 
     * @param value
     *            the maximum number of integer digits
     */
    @Override
    public void setMaximumIntegerDigits(int value) {
        super.setMaximumIntegerDigits(value);
        dform.setMaximumIntegerDigits(value);
    }

    /**
     * Sets the minimum number of fraction digits that are printed when
     * formatting. Limit the minimum to DOUBLE_FRACTION_DIGITS.
     * 
     * @param value
     *            the minimum number of fraction digits
     */
    @Override
    public void setMinimumFractionDigits(int value) {
        super.setMinimumFractionDigits(value);
        dform.setMinimumFractionDigits(value);
    }

    /**
     * Sets the minimum number of integer digits that are printed when
     * formatting. Limit the minimum to DOUBLE_INTEGER_DIGITS.
     * 
     * @param value
     *            the minimum number of integer digits
     */
    @Override
    public void setMinimumIntegerDigits(int value) {
        super.setMinimumIntegerDigits(value);
        dform.setMinimumIntegerDigits(value);
    }

    /**
     * Sets the multiplier which is applied to the number before formatting or
     * after parsing.
     * 
     * @param value
     *            the multiplier
     */
    public void setMultiplier(int value) {
        dform.setMultiplier(value);
    }

    /**
     * Sets the prefix which is formatted or parsed before a negative number.
     * 
     * @param value
     *            the negative prefix
     */
    public void setNegativePrefix(String value) {
        dform.setNegativePrefix(value);
    }

    /**
     * Sets the suffix which is formatted or parsed after a negative number.
     * 
     * @param value
     *            the negative suffix
     */
    public void setNegativeSuffix(String value) {
        dform.setNegativeSuffix(value);
    }

    /**
     * Sets the prefix which is formatted or parsed before a positive number.
     * 
     * @param value
     *            the positive prefix
     */
    public void setPositivePrefix(String value) {
        dform.setPositivePrefix(value);
    }

    /**
     * Sets the suffix which is formatted or parsed after a positive number.
     * 
     * @param value
     *            the positive suffix
     */
    public void setPositiveSuffix(String value) {
        dform.setPositiveSuffix(value);
    }

    /**
     * Let users change the behavior of a DecimalFormat, If set to true all the
     * returned objects will be of type BigDecimal
     * 
     * @param newValue
     *            true if all the returned objects should be type of BigDecimal
     */
    public void setParseBigDecimal(boolean newValue) {
        this.parseBigDecimal = newValue;
    }

    /**
     * Answers the pattern of this DecimalFormat using localized pattern
     * characters.
     * 
     * @return the localized pattern
     */
    public String toLocalizedPattern() {
        return dform.toLocalizedPattern();
    }

    /**
     * Answers the pattern of this DecimalFormat using non-localized pattern
     * characters.
     * 
     * @return the non-localized pattern
     */
    public String toPattern() {
        return dform.toPattern();
    }

    // the fields list to be serialized
    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("positivePrefix", String.class), //$NON-NLS-1$
            new ObjectStreamField("positiveSuffix", String.class), //$NON-NLS-1$
            new ObjectStreamField("negativePrefix", String.class), //$NON-NLS-1$
            new ObjectStreamField("negativeSuffix", String.class), //$NON-NLS-1$
            new ObjectStreamField("posPrefixPattern", String.class), //$NON-NLS-1$
            new ObjectStreamField("posSuffixPattern", String.class), //$NON-NLS-1$
            new ObjectStreamField("negPrefixPattern", String.class), //$NON-NLS-1$
            new ObjectStreamField("negSuffixPattern", String.class), //$NON-NLS-1$
            new ObjectStreamField("multiplier", int.class), //$NON-NLS-1$
            new ObjectStreamField("groupingSize", byte.class), //$NON-NLS-1$
            new ObjectStreamField("decimalSeparatorAlwaysShown", boolean.class), //$NON-NLS-1$
            new ObjectStreamField("parseBigDecimal", boolean.class), //$NON-NLS-1$
            new ObjectStreamField("symbols", DecimalFormatSymbols.class), //$NON-NLS-1$
            new ObjectStreamField("useExponentialNotation", boolean.class), //$NON-NLS-1$
            new ObjectStreamField("minExponentDigits", byte.class), //$NON-NLS-1$
            new ObjectStreamField("maximumIntegerDigits", int.class), //$NON-NLS-1$
            new ObjectStreamField("minimumIntegerDigits", int.class), //$NON-NLS-1$
            new ObjectStreamField("maximumFractionDigits", int.class), //$NON-NLS-1$
            new ObjectStreamField("minimumFractionDigits", int.class), //$NON-NLS-1$
            new ObjectStreamField("serialVersionOnStream", int.class), }; //$NON-NLS-1$

    /**
     * Writes serialized fields following serialized forms specified by Java
     * specification.
     * 
     * @param stream
     *            the output stream to write serialized bytes
     * @throws IOException
     *             if some I/O error occurs
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("nls")
    private void writeObject(ObjectOutputStream stream) throws IOException,
            ClassNotFoundException {
        ObjectOutputStream.PutField fields = stream.putFields();
        fields.put("positivePrefix", dform.getPositivePrefix());
        fields.put("positiveSuffix", dform.getPositiveSuffix());
        fields.put("negativePrefix", dform.getNegativePrefix());
        fields.put("negativeSuffix", dform.getNegativeSuffix());
        String posPrefixPattern = (String) Format.getInternalField(
                "posPrefixPattern", dform);
        fields.put("posPrefixPattern", posPrefixPattern);
        String posSuffixPattern = (String) Format.getInternalField(
                "posSuffixPattern", dform);
        fields.put("posSuffixPattern", posSuffixPattern);
        String negPrefixPattern = (String) Format.getInternalField(
                "negPrefixPattern", dform);
        fields.put("negPrefixPattern", negPrefixPattern);
        String negSuffixPattern = (String) Format.getInternalField(
                "negSuffixPattern", dform);
        fields.put("negSuffixPattern", negSuffixPattern);
        fields.put("multiplier", dform.getMultiplier());
        fields.put("groupingSize", (byte) dform.getGroupingSize());
        fields.put("decimalSeparatorAlwaysShown", dform
                .isDecimalSeparatorAlwaysShown());
        fields.put("parseBigDecimal", parseBigDecimal);
        fields.put("symbols", symbols);
        boolean useExponentialNotation = ((Boolean) Format.getInternalField(
                "useExponentialNotation", dform)).booleanValue();
        fields.put("useExponentialNotation", useExponentialNotation);
        byte minExponentDigits = ((Byte) Format.getInternalField(
                "minExponentDigits", dform)).byteValue();
        fields.put("minExponentDigits", minExponentDigits);
        fields.put("maximumIntegerDigits", dform.getMaximumIntegerDigits());
        fields.put("minimumIntegerDigits", dform.getMinimumIntegerDigits());
        fields.put("maximumFractionDigits", dform.getMaximumFractionDigits());
        fields.put("minimumFractionDigits", dform.getMinimumFractionDigits());
        fields.put("serialVersionOnStream", CURRENT_SERIAL_VERTION);
        stream.writeFields();
    }

    /**
     * Reads serialized fields following serialized forms specified by Java
     * specification.
     * 
     * @param stream
     *            the input stream to read serialized bytes
     * @throws IOException
     *             if some I/O error occurs
     * @throws ClassNotFoundException
     *             if some class of serialized objects or fields cannot be found
     */
    @SuppressWarnings("nls")
    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {

        ObjectInputStream.GetField fields = stream.readFields();
        String positivePrefix = (String) fields.get("positivePrefix", "");
        String positiveSuffix = (String) fields.get("positiveSuffix", "");
        String negativePrefix = (String) fields.get("negativePrefix", "-");
        String negativeSuffix = (String) fields.get("negativeSuffix", "");

        String posPrefixPattern = (String) fields.get("posPrefixPattern", "");
        String posSuffixPattern = (String) fields.get("posSuffixPattern", "");
        String negPrefixPattern = (String) fields.get("negPrefixPattern", "-");
        String negSuffixPattern = (String) fields.get("negSuffixPattern", "");

        int multiplier = fields.get("multiplier", 1);
        byte groupingSize = fields.get("groupingSize", (byte) 3);
        boolean decimalSeparatorAlwaysShown = fields.get(
                "decimalSeparatorAlwaysShown", false);
        boolean parseBigDecimal = fields.get("parseBigDecimal", false);
        symbols = (DecimalFormatSymbols) fields.get("symbols", null);

        boolean useExponentialNotation = fields.get("useExponentialNotation",
                false);
        byte minExponentDigits = fields.get("minExponentDigits", (byte) 0);

        int maximumIntegerDigits = fields.get("maximumIntegerDigits", 309);
        int minimumIntegerDigits = fields.get("minimumIntegerDigits", 309);
        int maximumFractionDigits = fields.get("maximumFractionDigits", 340);
        int minimumFractionDigits = fields.get("minimumFractionDigits", 340);
        this.serialVersionOnStream = fields.get("serialVersionOnStream", 0);

        Locale locale = (Locale) Format.getInternalField("locale", symbols);
        dform = new com.ibm.icu.text.DecimalFormat("",
                new com.ibm.icu.text.DecimalFormatSymbols(locale));
        setInternalField("useExponentialNotation", dform, Boolean
                .valueOf(useExponentialNotation));
        setInternalField("minExponentDigits", dform,
                new Byte(minExponentDigits));
        dform.setPositivePrefix(positivePrefix);
        dform.setPositiveSuffix(positiveSuffix);
        dform.setNegativePrefix(negativePrefix);
        dform.setNegativeSuffix(negativeSuffix);
        setInternalField("posPrefixPattern", dform, posPrefixPattern);
        setInternalField("posSuffixPattern", dform, posSuffixPattern);
        setInternalField("negPrefixPattern", dform, negPrefixPattern);
        setInternalField("negSuffixPattern", dform, negSuffixPattern);
        dform.setMultiplier(multiplier);
        dform.setGroupingSize(groupingSize);
        dform.setDecimalSeparatorAlwaysShown(decimalSeparatorAlwaysShown);
        dform.setMinimumIntegerDigits(minimumIntegerDigits);
        dform.setMaximumIntegerDigits(maximumIntegerDigits);
        dform.setMinimumFractionDigits(minimumFractionDigits);
        dform.setMaximumFractionDigits(maximumFractionDigits);
        this.setParseBigDecimal(parseBigDecimal);

        if (serialVersionOnStream < 3) {
            setMaximumIntegerDigits(super.getMaximumIntegerDigits());
            setMinimumIntegerDigits(super.getMinimumIntegerDigits());
            setMaximumFractionDigits(super.getMaximumFractionDigits());
            setMinimumFractionDigits(super.getMinimumFractionDigits());
        }
        if (serialVersionOnStream < 1) {
            this.setInternalField("useExponentialNotation", dform,
                    Boolean.FALSE);
        }
        serialVersionOnStream = 3;
    }

    /*
     * Copies decimal format symbols from text object to ICU one.
     * 
     * @param icu the object which receives the new values. @param dfs the
     * object which contains the new values.
     */
    private void copySymbols(final com.ibm.icu.text.DecimalFormatSymbols icu,
            final DecimalFormatSymbols dfs) {
        Currency currency = dfs.getCurrency();
        if (currency == null) {
            icu.setCurrency(com.ibm.icu.util.Currency.getInstance("XXX"));
        } else {
            icu.setCurrency(com.ibm.icu.util.Currency.getInstance(dfs
                    .getCurrency().getCurrencyCode()));
        }
       
        icu.setCurrencySymbol(dfs.getCurrencySymbol());
        icu.setDecimalSeparator(dfs.getDecimalSeparator());
        icu.setDigit(dfs.getDigit());
        icu.setGroupingSeparator(dfs.getGroupingSeparator());
        icu.setInfinity(dfs.getInfinity());
        icu
                .setInternationalCurrencySymbol(dfs
                        .getInternationalCurrencySymbol());
        icu.setMinusSign(dfs.getMinusSign());
        icu.setMonetaryDecimalSeparator(dfs.getMonetaryDecimalSeparator());
        icu.setNaN(dfs.getNaN());
        icu.setPatternSeparator(dfs.getPatternSeparator());
        icu.setPercent(dfs.getPercent());
        icu.setPerMill(dfs.getPerMill());
        icu.setZeroDigit(dfs.getZeroDigit());
    }

    /*
     * Sets private field value by reflection.
     * 
     * @param fieldName the field name to be set @param target the object which
     * field to be set @param value the value to be set
     */
    private void setInternalField(final String fieldName, final Object target,
            final Object value) {
        AccessController
                .doPrivileged(new PrivilegedAction<java.lang.reflect.Field>() {
                    public java.lang.reflect.Field run() {
                        java.lang.reflect.Field field = null;
                        try {
                            field = target.getClass().getDeclaredField(
                                    fieldName);
                            field.setAccessible(true);
                            field.set(target, value);
                        } catch (Exception e) {
                            return null;
                        }
                        return field;
                    }
                });
    }
    
	/**
	 * Get the RoundingMode of this DecimalFormat
	 * 
	 * @return the RoundingMode
	 */
	public RoundingMode getRoundingMode() {
		// return the RoundingMode of this DecimalFormat
		return roundingMode;
	}

	/**
	 * Set the RoundingMode of this DecimalFormat
	 * 
	 * @param roundingMode
	 *            the given RoundingMode
	 */
	public void setRoundingMode(RoundingMode roundingMode) {
		if (roundingMode == null) {
			// when the given RoundingMode is null, throw NullPointerException
			throw new NullPointerException();
		}

		// update the RoundingMode with the given RoundingMode
		this.roundingMode = roundingMode;

		// update the RoundingMode of com.ibm.icu.text.DecimalFormat
		switch(roundingMode){
		case CEILING:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_CEILING);
			break;
		case DOWN:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_DOWN);
			break;
		case UP:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_UP);
			break;
		case FLOOR:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_FLOOR);
			break;
		case HALF_DOWN:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_HALF_DOWN);
			break;
		case HALF_UP:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_HALF_UP);
			break;
		case UNNECESSARY:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_UNNECESSARY);
			break;
		case HALF_EVEN:
			dform.setRoundingMode(com.ibm.icu.math.BigDecimal.ROUND_HALF_EVEN);
			break;
		default:
			break;
		}
		
		// update the RoundingIncrement
		setRoundingIncrement(getMaximumFractionDigits());
	}

	/*
	 * Update the RoundingIncrement according to the given MaxFractionDigits
	 */
	private void setRoundingIncrement(int value) {
		int maxFractionDigits = value;
		// when value < 0, set maximumFractionDigits to 0 and update the
		// RoundingIncreament
		if (maxFractionDigits < 0) {
			maxFractionDigits = 0;
		}
		double roundingIncrement = 1.0 / Math.pow(10, maxFractionDigits);
		dform.setRoundingIncrement(roundingIncrement);
	}
}
