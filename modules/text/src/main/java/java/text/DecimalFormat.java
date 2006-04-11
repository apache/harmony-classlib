/* Copyright 1998, 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import java.util.Locale;

import org.apache.harmony.luni.util.NotYetImplementedException;

/**
 * DecimalFormat is used to format and parse numbers, both integers and
 * fractions, based on a pattern. The pattern characters used can be either
 * localized or non-localized.
 */
public class DecimalFormat extends NumberFormat {

    static final long serialVersionUID = 864413376551465018L;

    private boolean parseBigDecimal = false;

    private DecimalFormatSymbols symbols;

    private transient com.ibm.icu.text.DecimalFormat dform;

    private transient com.ibm.icu.text.DecimalFormatSymbols icuSymbols;

    static final int currentSerialVersion = 3;

    /**
     * Constructs a new DecimalFormat for formatting and parsing numbers for the
     * default Locale.
     */
    public DecimalFormat() {
        this(getPattern(Locale.getDefault(), "Number"));
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
        this(pattern, new DecimalFormatSymbols());
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
        symbols = value;
        icuSymbols = adapt2ICU(symbols);
        dform = new com.ibm.icu.text.DecimalFormat(pattern, icuSymbols);
        setMaximumFractionDigits(dform.getMaximumFractionDigits());
        setMinimumFractionDigits(dform.getMinimumFractionDigits());
        setMaximumIntegerDigits(dform.getMaximumIntegerDigits());
        setMinimumIntegerDigits(dform.getMinimumIntegerDigits());

    }

    private com.ibm.icu.text.DecimalFormatSymbols adapt2ICU(
            DecimalFormatSymbols symbols) {
        com.ibm.icu.text.DecimalFormatSymbols icuSymbols = new com.ibm.icu.text.DecimalFormatSymbols();
        String currencyCode = symbols.getCurrency().getCurrencyCode();
        icuSymbols.setCurrency(com.ibm.icu.util.Currency
                .getInstance(currencyCode));
        icuSymbols.setCurrencySymbol(symbols.getCurrencySymbol());
        icuSymbols.setDecimalSeparator(symbols.getDecimalSeparator());
        icuSymbols.setDigit(symbols.getDigit());
        icuSymbols.setGroupingSeparator(symbols.getGroupingSeparator());
        icuSymbols.setInfinity(symbols.getInfinity());
        icuSymbols.setInternationalCurrencySymbol(symbols
                .getInternationalCurrencySymbol());
        icuSymbols.setMinusSign(symbols.getMinusSign());
        icuSymbols.setMonetaryDecimalSeparator(symbols
                .getMonetaryDecimalSeparator());
        icuSymbols.setNaN(symbols.getNaN());
        icuSymbols.setPatternSeparator(symbols.getPatternSeparator());
        icuSymbols.setPercent(symbols.getPercent());
        icuSymbols.setPerMill(symbols.getPerMill());
        icuSymbols.setZeroDigit(symbols.getZeroDigit());

        return icuSymbols;
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
     * @exception IllegalArgumentException
     *                when the object cannot be formatted by this Format
     */
    public AttributedCharacterIterator formatToCharacterIterator(Object object) {
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
    public StringBuffer format(long value, StringBuffer buffer,
            FieldPosition position) {
        return dform.format(value, buffer, position);
    }

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
        } else {
            return super.format(number, toAppendTo, pos);
        }
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
    public Currency getCurrency() {
        return Currency.getInstance(dform.getCurrency().getCurrencyCode());
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

    public void setParseIntegerOnly(boolean value) {
        dform.setParseIntegerOnly(value);
    }

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
        } else {
            if ((number instanceof com.ibm.icu.math.BigDecimal)
                    || (number instanceof BigInteger)) {
                return new Double(number.doubleValue());
            }

            if (this.isParseIntegerOnly()
                    && number.equals(NEGATIVE_ZERO_DOUBLE)) {
                return new Long(0);
            }
            return number;

        }

    }

    /**
     * Sets the DecimalFormatSymbols used by this DecimalFormat.
     * 
     * @param value
     *            the DecimalFormatSymbols
     */
    public void setDecimalFormatSymbols(DecimalFormatSymbols value) {
        symbols = (DecimalFormatSymbols) value.clone();
        icuSymbols = adapt2ICU(symbols);
        dform.setDecimalFormatSymbols(icuSymbols);
    }

    /**
     * Sets the currency used by this decimal format. The min and max fraction
     * digits remain the same.
     * 
     * @param currency
     * @see DecimalFormatSymbols#setCurrency(Currency)
     */
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
    
    /*
     * (non-Javadoc)
     * @see java.text.NumberFormat#setGroupingUsed(boolean)
     */
    public void setGroupingUsed(boolean value) {
        dform.setGroupingUsed(value);
    }

    /*
     * (non-Javadoc)
     * @see java.text.NumberFormat#setGroupingUsed(boolean)
     */
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
    public void setMaximumFractionDigits(int value) {
        super.setMaximumFractionDigits(value);
        dform.setMaximumFractionDigits(value);
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

    private void writeObject(ObjectOutputStream stream) throws IOException,
            ClassNotFoundException {
        throw new NotYetImplementedException();
    }

    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        throw new NotYetImplementedException();
    }

}
