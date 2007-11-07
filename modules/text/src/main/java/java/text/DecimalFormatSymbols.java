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
import java.io.Serializable;
import java.util.Currency;
import java.util.Locale;

/**
 * DecimalFormatSymbols holds the symbols used in the formating and parsing of
 * numbers.
 */
public final class DecimalFormatSymbols implements Cloneable, Serializable {

    private static final long serialVersionUID = 5772796243397350300L;

    private char zeroDigit;
    private char groupingSeparator;
    private char decimalSeparator;
    private char perMill;
    private char percent;
    private char digit;
    private char patternSeparator;
    private String infinity;
    private String NaN;
    private char minusSign;
    private String currencySymbol;
    private String intlCurrencySymbol;
    private char monetarySeparator;
    private char exponential;
    private Locale locale;
    // 3 indicates version 5 and later
    private int serialVersionOnStream = 3;
    private transient Currency currency;

    private transient com.ibm.icu.text.DecimalFormatSymbols icuSymbols;

    /**
     * Constructs a new DecimalFormatSymbols containing the symbols for the
     * default Locale.
     */
    public DecimalFormatSymbols() {
        this(Locale.getDefault());
    }

    /**
     * Constructs a new DecimalFormatSymbols containing the symbols for the
     * specified Locale.
     * 
     * @param locale
     *            the Locale
     */
    public DecimalFormatSymbols(Locale locale) {
        icuSymbols = new com.ibm.icu.text.DecimalFormatSymbols(locale);
        infinity = icuSymbols.getInfinity();
        NaN = icuSymbols.getNaN();
        this.locale = locale;
        currencySymbol = icuSymbols.getCurrencySymbol();
        intlCurrencySymbol = icuSymbols.getInternationalCurrencySymbol();
        if (locale.getCountry().length() == 0) {
            currency = Currency.getInstance("XXX");
        } else {
            currency = Currency.getInstance(locale);
        }
        zeroDigit = icuSymbols.getZeroDigit();
        digit = icuSymbols.getDigit();
        decimalSeparator = icuSymbols.getDecimalSeparator();
        groupingSeparator = icuSymbols.getGroupingSeparator();
        patternSeparator = icuSymbols.getPatternSeparator();
        percent = icuSymbols.getPercent();
        perMill = icuSymbols.getPerMill();
        exponential = 'E';
        monetarySeparator = icuSymbols.getMonetaryDecimalSeparator();
        minusSign = icuSymbols.getMinusSign();
    }

    /**
     * Answers a new DecimalFormatSymbols with the same symbols as this
     * DecimalFormatSymbols.
     * 
     * @return a shallow copy of this DecimalFormatSymbols
     * 
     * @see java.lang.Cloneable
     */
    @Override
    public Object clone() {
        try {
            DecimalFormatSymbols symbols = (DecimalFormatSymbols) super.clone();
            return symbols;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Compares the specified object to this DecimalFormatSymbols and answer if
     * they are equal. The object must be an instance of DecimalFormatSymbols
     * with the same symbols.
     * 
     * @param object
     *            the object to compare with this object
     * @return true if the specified object is equal to this
     *         DecimalFormatSymbols, false otherwise
     * 
     * @see #hashCode
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DecimalFormatSymbols)) {
            return false;
        }
        DecimalFormatSymbols obj = (DecimalFormatSymbols) object;
        return zeroDigit == obj.zeroDigit && digit == obj.digit
                && decimalSeparator == obj.decimalSeparator
                && groupingSeparator == obj.groupingSeparator
                && patternSeparator == obj.patternSeparator
                && percent == obj.percent && perMill == obj.perMill
                && exponential == obj.exponential
                && monetarySeparator == obj.monetarySeparator
                && minusSign == obj.minusSign && infinity.equals(obj.infinity)
                && NaN.equals(obj.NaN)
                && currencySymbol.equals(obj.currencySymbol)
                && intlCurrencySymbol.equals(obj.intlCurrencySymbol);
    }

    /**
     * Answers the currency.
     * <p>
     * <code>null<code> is returned
     * if <code>setInternationalCurrencySymbol()</code> has been previously called
     * with a value that is not a valid ISO 4217 currency code.
     * <p>
     *
     * @return      the currency that was set in the constructor, <code>setCurrency()</code>,
     *              or <code>setInternationalCurrencySymbol()</code>, or </code>null</code>
     * 
     * @see #setCurrency(Currency)
     * @see #setInternationalCurrencySymbol(String)
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Answers the international currency symbol.
     * 
     * @return a String
     */
    public String getInternationalCurrencySymbol() {
        return intlCurrencySymbol;
    }

    /**
     * Answers the currency symbol.
     * 
     * @return a String
     */
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    /**
     * Answers the character which represents the decimal point in a number.
     * 
     * @return a char
     */
    public char getDecimalSeparator() {
        return decimalSeparator;
    }

    /**
     * Answers the character which represents a single digit in a format
     * pattern.
     * 
     * @return a char
     */
    public char getDigit() {
        return digit;
    }

    /**
     * Answers the character used as the thousands separator in a number.
     * 
     * @return a char
     */
    public char getGroupingSeparator() {
        return groupingSeparator;
    }

    /**
     * Answers the String which represents infinity.
     * 
     * @return a String
     */
    public String getInfinity() {
        return infinity;
    }

    String getLocalPatternChars() {
        // Don't include the MonetaryDecimalSeparator or the MinusSign
        return new String(new char[]{zeroDigit, digit, decimalSeparator, groupingSeparator,
                patternSeparator, percent, perMill, exponential});
    }

    /**
     * Answers the minus sign character.
     * 
     * @return a char
     */
    public char getMinusSign() {
        return minusSign;
    }

    /**
     * Answers the character which represents the decimal point in a monetary
     * value.
     * 
     * @return a char
     */
    public char getMonetaryDecimalSeparator() {
        return monetarySeparator;
    }

    /**
     * Answers the String which represents NaN.
     * 
     * @return a String
     */
    public String getNaN() {
        return NaN;
    }

    /**
     * Answers the character which separates the positive and negative patterns
     * in a format pattern.
     * 
     * @return a char
     */
    public char getPatternSeparator() {
        return patternSeparator;
    }

    /**
     * Answers the percent character.
     * 
     * @return a char
     */
    public char getPercent() {
        return percent;
    }

    /**
     * Answers the mille percent sign character.
     * 
     * @return a char
     */
    public char getPerMill() {
        return perMill;
    }

    /**
     * Answers the character which represents zero.
     * 
     * @return a char
     */
    public char getZeroDigit() {
        return zeroDigit;
    }

    char getExponential() {
        return exponential;
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
        return new String(new char[] { zeroDigit, digit, decimalSeparator,
                groupingSeparator, patternSeparator, percent, perMill,
                exponential, monetarySeparator, minusSign }).hashCode()
                + infinity.hashCode()
                + NaN.hashCode()
                + currencySymbol.hashCode() + intlCurrencySymbol.hashCode();
    }

    /**
     * Sets the currency.
     * <p>
     * The international currency symbol and currency symbol are updated, but
     * the min and max number of fraction digits stay the same.
     * <p>
     * 
     * @param currency
     *            the new currency
     * 
     * @throws java.lang.NullPointerException
     *             if currency is null
     */
    public void setCurrency(Currency currency) {
        if (currency == null) {
            throw new NullPointerException();
        }
        if (currency == this.currency) {
            return;
        }
        this.currency = currency;
        intlCurrencySymbol = currency.getCurrencyCode();
        currencySymbol = currency.getSymbol(locale);
    }

    /**
     * Sets the international currency symbol.
     * <p>
     * currency and currency symbol also are updated, if <code>value</code> is
     * a valid ISO4217 currency code.
     * <p>
     * The min and max number of fraction digits stay the same.
     * 
     * @param value
     *            currency code
     */
    public void setInternationalCurrencySymbol(String value) {
        if (value == null) {
            currency = null;
            intlCurrencySymbol = null;
            return;
        }

        if (value.equals(intlCurrencySymbol)) {
            return;
        }

        try {
            currency = Currency.getInstance(value);
            currencySymbol = currency.getSymbol(locale);
        } catch (IllegalArgumentException e) {
            currency = null;
        }
        intlCurrencySymbol = value;
    }

    /**
     * Sets the currency symbol.
     * 
     * @param value
     *            a String
     */
    public void setCurrencySymbol(String value) {
        currencySymbol = value;
    }

    /**
     * Sets the character which represents the decimal point in a number.
     * 
     * @param value
     *            the decimal separator character
     */
    public void setDecimalSeparator(char value) {
        decimalSeparator = value;
    }

    /**
     * Sets the character which represents a single digit in a format pattern.
     * 
     * @param value
     *            the digit character
     */
    public void setDigit(char value) {
        digit = value;
    }

    /**
     * Sets the character used as the thousands separator in a number.
     * 
     * @param value
     *            the grouping separator character
     */
    public void setGroupingSeparator(char value) {
        groupingSeparator = value;
    }

    /**
     * Sets the String which represents infinity.
     * 
     * @param value
     *            the String
     */
    public void setInfinity(String value) {
        infinity = value;
    }

    /**
     * Sets the minus sign character.
     * 
     * @param value
     *            the minus sign character
     */
    public void setMinusSign(char value) {
        minusSign = value;
    }

    /**
     * Sets the character which represents the decimal point in a monetary
     * value.
     * 
     * @param value
     *            the monetary decimal separator character
     */
    public void setMonetaryDecimalSeparator(char value) {
        monetarySeparator = value;
    }

    /**
     * Sets the String which represents NaN.
     * 
     * @param value
     *            the String
     */
    public void setNaN(String value) {
        NaN = value;
    }

    /**
     * Sets the character which separates the positive and negative patterns in
     * a format pattern.
     * 
     * @param value
     *            the pattern separator character
     */
    public void setPatternSeparator(char value) {
        patternSeparator = value;
    }

    /**
     * Sets the percent character.
     * 
     * @param value
     *            the percent character
     */
    public void setPercent(char value) {
        percent = value;
    }

    /**
     * Sets the mille percent sign character.
     * 
     * @param value
     *            the mille percent character
     */
    public void setPerMill(char value) {
        perMill = value;
    }

    /**
     * Sets the character which represents zero.
     * 
     * @param value
     *            the zero digit character
     */
    public void setZeroDigit(char value) {
        zeroDigit = value;
    }

    void setExponential(char value) {
        exponential = value;
    }

    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        ObjectInputStream.GetField fields = stream.readFields();
        currencySymbol = (String) fields.get("currencySymbol", ""); //$NON-NLS-1$ //$NON-NLS-2$
        setDecimalSeparator(fields.get("decimalSeparator", '.')); //$NON-NLS-1$
        setDigit(fields.get("digit", '#')); //$NON-NLS-1$
        setGroupingSeparator(fields.get("groupingSeparator", ',')); //$NON-NLS-1$
        infinity = (String) fields.get("infinity", ""); //$NON-NLS-1$ //$NON-NLS-2$
        intlCurrencySymbol = (String) fields.get("intlCurrencySymbol", ""); //$NON-NLS-1$ //$NON-NLS-2$
        setMinusSign(fields.get("minusSign", '-')); //$NON-NLS-1$
        NaN = (String) fields.get("NaN", ""); //$NON-NLS-1$ //$NON-NLS-2$
        setPatternSeparator(fields.get("patternSeparator", ';')); //$NON-NLS-1$
        setPercent(fields.get("percent", '%')); //$NON-NLS-1$
        setPerMill(fields.get("perMill", '\u2030')); //$NON-NLS-1$
        setZeroDigit(fields.get("zeroDigit", '0')); //$NON-NLS-1$
        locale = (Locale) fields.get("locale", null); //$NON-NLS-1$
        if (fields.get("serialVersionOnStream", 0) == 0) { //$NON-NLS-1$
            setMonetaryDecimalSeparator(getDecimalSeparator());
            setExponential('E');
        } else {
            setMonetaryDecimalSeparator(fields.get("monetarySeparator", '.')); //$NON-NLS-1$
            setExponential(fields.get("exponential", 'E')); //$NON-NLS-1$

        }
        try {
            currency = Currency.getInstance(intlCurrencySymbol);
        } catch (IllegalArgumentException e) {
            currency = null;
        }
    }
    
    Locale getLocale(){
        return locale;
    }
}
