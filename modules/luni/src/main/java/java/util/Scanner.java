/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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
package java.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.harmony.luni.util.NotYetImplementedException;

/**
 * A parser that parses a text string to primitive types with the help of
 * regular expression. It supports localized number and various radixes.
 * 
 * The input is broken into tokens by the delimiter pattern, which is whitespace
 * by default. The primitive types can be got via corresponding next methods. If
 * the token is not in valid format, an InputMissmatchException is thrown.
 * 
 * For example: Scanner s = new Scanner("1A true");
 * System.out.println(s.nextInt(16)); System.out.println(s.nextBoolean()); The
 * result: 26 true
 * 
 * A scanner can find or skip specific pattern with no regard to the delimiter.
 * All these methods and the various next and hasNext methods may block.
 * 
 * Scanner is not thread-safe without external synchronization
 */
public final class Scanner implements Iterator<String> {

    //  Default delimiting pattern
    private static final Pattern DEFAULT_DELIMITER = Pattern
            .compile("\\p{javaWhitespace}+"); //$NON-NLS-1$
    
    //The boolean's pattern
    private static final Pattern BOOLEAN_PATTERN = Pattern.compile(
            "true|false", Pattern.CASE_INSENSITIVE); //$NON-NLS-1$
    
    // The pattern matching anything
    private static final Pattern ANY_PATTERN = Pattern.compile("(?s).*"); //$NON-NLS-1$

    private static final int DIPLOID = 2;

    // Default radix
    private static final int DEFAULT_RADIX = 10;

    private static final int DEFAULT_TRUNK_SIZE = 1024;

    // The input source of scanner
    private Readable input;

    private CharBuffer buffer;

    private Pattern delimiter = DEFAULT_DELIMITER;

    private Matcher matcher;

    private int integerRadix = DEFAULT_RADIX;

    private Locale locale = Locale.getDefault();

    // The position where find begins
    private int findStartIndex = 0;

    // The last find start position
    private int preStartIndex = findStartIndex;

    // The length of the buffer
    private int bufferLength = 0;

    // Used by find and nextXXX operation
    private boolean closed = false;

    private IOException lastIOException;
    
    private boolean matchSuccessful = false;
    
    private DecimalFormat decimalFormat;
    
    private enum DataType{
        /*
         * Stands for Integer
         */
        INT,
        /*
         * Stands for Float
         */
        FLOAT;
    }

    /**
     * Constructs a scanner that uses File as its input. The default charset is
     * applied when reading the file.
     * 
     * @param src
     *            the file to be scanned
     * @throws FileNotFoundException
     *             if the specified file is not found
     */
    public Scanner(File src) throws FileNotFoundException {
        this(src, Charset.defaultCharset().name());
    }

    /**
     * Constructs a scanner that uses File as its input. The specified charset
     * is applied when reading the file.
     * 
     * @param src
     *            the file to be scanned
     * @param charsetName
     *            the name of the encoding type of the file
     * @throws FileNotFoundException
     *             if the specified file is not found
     * @throws IllegalArgumentException
     *            if the specified coding does not exist
     */
    public Scanner(File src, String charsetName) throws FileNotFoundException {
        if (null == src) {
            throw new NullPointerException(org.apache.harmony.luni.util.Msg
                    .getString("KA00a")); //$NON-NLS-1$
        }
        FileInputStream fis = new FileInputStream(src);
        if (null == charsetName) {
            throw new IllegalArgumentException(org.apache.harmony.luni.util.Msg
                    .getString("KA009")); //$NON-NLS-1$
        }
        try {
            input = new InputStreamReader(fis, charsetName);
        } catch (UnsupportedEncodingException e) {
            try {
                fis.close();
            } catch (IOException ioException) {
                // ignore
            }
            throw new IllegalArgumentException(e.getMessage());
        }
        initialization();
    }

    /**
     * Constructs a scanner that uses String as its input.
     * 
     * @param src
     *            the string to be scanned
     */
    public Scanner(String src) {
        input = new StringReader(src);
        initialization();
    }

    /**
     * Constructs a scanner that uses InputStream as its input. The default
     * charset is applied when decoding the input.
     * 
     * @param src
     *            the input stream to be scanned
     */
    public Scanner(InputStream src) {
        this(src, Charset.defaultCharset().name());
    }

    /**
     * Constructs a scanner that uses InputStream as its input. The specified
     * charset is applied when decoding the input.
     * 
     * @param src
     *            the input stream to be scanned
     * @param charsetName
     *            the encoding type of the input stream
     * @throws IllegalArgumentException
     *            if the specified character set is not found
     */
    public Scanner(InputStream src, String charsetName) {
        if (null == src) {
            throw new NullPointerException(org.apache.harmony.luni.util.Msg
                    .getString("KA00b")); //$NON-NLS-1$
        }
        try {
            input = new InputStreamReader(src, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        initialization();
    }

    /**
     * Constructs a scanner that uses Readable as its input.
     * 
     * @param src
     *            the Readable to be scanned
     */
    public Scanner(Readable src) {
        if (null == src) {
            throw new NullPointerException();
        }
        input = src;
        initialization();
    }

    /**
     * Constructs a scanner that uses ReadableByteChannel as its input. The
     * default charset is applied when decoding the input.
     * 
     * @param src
     *            the ReadableByteChannel to be scanned
     */
    public Scanner(ReadableByteChannel src) {
        this(src, Charset.defaultCharset().name());
    }

    /**
     * Constructs a scanner that uses ReadableByteChannel as its input. The
     * specified charset is applied when decoding the input.
     * 
     * @param src
     *            the ReadableByteChannel to be scanned
     * @param charsetName
     *            the encoding type of the content in the ReadableByteChannel
     * @throws IllegalArgumentException
     *            if the specified character set is not found           
     */
    public Scanner(ReadableByteChannel src, String charsetName) {
        if (null == src) {
            throw new NullPointerException(org.apache.harmony.luni.util.Msg
                    .getString("KA00d")); //$NON-NLS-1$
        }
        if (null == charsetName) {
            throw new IllegalArgumentException(org.apache.harmony.luni.util.Msg
                    .getString("KA009")); //$NON-NLS-1$
        }
        try {
            input = new InputStreamReader(Channels.newInputStream(src),
                    charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        initialization();
    }

    /**
     * Closes the underlying input if the input implements Closeable. If the
     * scanner has been closed, this method will take no effect. The scanning
     * operation after calling this method will throw IllegalStateException
     * 
     */
    public void close() {
        if (closed == true) {
            return;
        }
        if (input instanceof Closeable) {
            try {
                ((Closeable) input).close();
            } catch (IOException e) {
                lastIOException = e;
            }
        }
        closed = true;
    }

    /**
     * Returns the <code>Pattern</code> in use by this scanner.
     * 
     * @return the <code>Pattern</code> presently in use by this scanner
     */
    public Pattern delimiter() {
        return delimiter;
    }

    //TODO: To implement this feature
    public String findInLine(Pattern pattern) {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public String findInLine(String pattern) {
        throw new NotYetImplementedException();
    }

    /**
     * @param pattern
     * @param horizon
     * @return
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     */
    public String findWithinHorizon(Pattern pattern, int horizon) {
        checkClosed();
        if (null == pattern) {
            throw new NullPointerException();
        }
        if (horizon < 0) {
            throw new IllegalArgumentException(org.apache.harmony.luni.util.Msg
                    .getString("KA00e")); //$NON-NLS-1$
        }
        matcher.usePattern(pattern);

        boolean isInputExhausted = false;
        String result = null;
        int findEndIndex = 0;
        int horizonEndIndex = 0;
        if (horizon == 0) {
            horizonEndIndex = Integer.MAX_VALUE;
        } else {
            horizonEndIndex = findStartIndex + horizon;
        }
        while (true) {
            findEndIndex = bufferLength;

            // If horizon > 0, then search up to
            // min( bufferLength, findStartIndex + horizon).
            // Otherwise search until readable is exhausted.
            findEndIndex = Math.min(horizonEndIndex, bufferLength);
            // If horizon == 0, consider horizon as always outside buffer.
            boolean isHorizonInBuffer = (horizonEndIndex <= bufferLength);
            // First, try to find pattern within buffer. If pattern can not be
            // found in buffer, then expand the buffer and try again,
            // util horizonEndIndex is exceeded or no more input left.
            matcher.region(findStartIndex, findEndIndex);
            if (matcher.find()) {
                if (isHorizonInBuffer || isInputExhausted) {
                    result = matcher.group();
                    break;
                }
            } else {
                // Pattern is not found in buffer while horizonEndIndex is
                // within buffer, or input is exhausted. Under this situation,
                // it can be judged that find fails.
                if (isHorizonInBuffer || isInputExhausted) {
                    break;
                }
            }

            // Expand buffer and reset matcher if needed.
            if (readMore()) {
                matcher.reset(buffer);
            } else {
                isInputExhausted = true;
            }
        }
        if (null != result) {
            findStartIndex = matcher.end();
            matchSuccessful = true;
        } else {
            matchSuccessful = false;
        }
        return result;
    }

    //TODO: To implement this feature
    public String findWithinHorizon(String pattern, int horizon) {
        throw new NotYetImplementedException();
    }

    /**
     * Returns true if this scanner has next token. This method may be blocked
     * when it is waiting for input to scan. This scanner does not advance past
     * the input.
     * 
     * @return true 
     *             iff this scanner has next token
     * @throws IllegalStateException
     *             if the scanner has been closed
     */
    public boolean hasNext() {
        return hasNext(ANY_PATTERN);
    }

    /**
     * Returns true if this scanner's next token matches the specified pattern.
     * This method may be blocked when it is waiting for input to scan. This
     * scanner does not advance past the input that matched the pattern.
     * 
     * @param pattern
     *            the specified pattern to scan
     * @return 
     *            true iff this scanner's next token matches the specified pattern
     * @throws IllegalStateException
     *            if the scanner has been closed
     */
    public boolean hasNext(Pattern pattern) {
        checkClosed();
        if (isInputExhausted()) {
            return false;
        }
        saveCurrentStatus();
        //if the next token exists, set the match region, otherwise return false
        if (!setTokenRegion()) {
            recoverPreviousStatus();
            return false;
        }
        matcher.usePattern(pattern);
        boolean hasNext = false;
        //check whether next token matches the specified pattern
        if (matcher.matches()) {
            hasNext = true;
        }
        recoverPreviousStatus();
        return hasNext;
    }


    /**
     * Returns true if this scanner's next token matches the pattern constructed
     * from the specified string. This method may be blocked when it is waiting
     * for input to scan. This scanner does not advance past the input that
     * matched the pattern.
     * 
     * The invocation of this method in the form hasNext(pattern) behaves in the
     * same way as the invocaiton of hasNext(Pattern.compile(pattern)).
     * 
     * @param pattern
     *            the string specifying the pattern to scan for
     * @return true 
     *            iff this scanner's next token matches the specified pattern
     * @throws IllegalStateException
     *             if the scanner has been closed
     */
    public boolean hasNext(String pattern) {
        return hasNext(Pattern.compile(pattern));
    }

    //TODO: To implement this feature
    public boolean hasNextBigDecimal() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public boolean hasNextBigInteger() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public boolean hasNextBigInteger(int radix) {
        throw new NotYetImplementedException();
    }

    /**
     * Returns true if this scanner's next token can be translated into a valid
     * boolean value. The scanner does not advance past the input that matched.
     * 
     * @return true 
     *             iff the next token in this scanner's input can be translated
     *         into a valid boolean value
     * @throws IllegalStateException
     *             if the scanner has been closed
     */
    public boolean hasNextBoolean() {
        return hasNext(BOOLEAN_PATTERN);
    }

    //TODO: To implement this feature
    public boolean hasNextByte() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public boolean hasNextByte(int radix) {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public boolean hasNextDouble() {
        throw new NotYetImplementedException();
    }

    /**
     * Returns true if this scanner's next token can be translated into a valid
     * float value. The scanner does not advance past the input.
     * 
     * @return true iff the next token in this scanner's input can be translated
     *         into a valid float value
     * @throws IllegalStateException
     *             if the scanner has been closed
     */
    public boolean hasNextFloat() {
        Pattern floatPattern = getFloatPattern();
        boolean isFloatValue = false;
        if (hasNext(floatPattern)) {
            String floatString = matcher.group();
            floatString = removeLocaleInfoFromFloat(floatString);
            try {
                Float.parseFloat(floatString);
                isFloatValue = true;
            } catch (NumberFormatException e) {
                matchSuccessful = false;
            }
        }
        return isFloatValue;
    }

    /**
     * Returns true if this scanner's next token can be translated into a valid
     * int value in the default radix. The scanner does not advance past the
     * input.
     * 
     * @return true iff the next token in this scanner's input can be translated
     *         into a valid int value
     * @throws IllegalStateException
     *             if the scanner has been closed
     */
    public boolean hasNextInt() {
        return hasNextInt(integerRadix);
    }

    /**
     * Returns true if this scanner's next token can be translated into a valid
     * int value in the specified radix. The scanner does not advance past the
     * input.
     * 
     * @param radix
     *            the radix used to translate the token into an int value
     * @return true iff the next token in this scanner's input can be translated
     *         into a valid int value
     * @throws IllegalStateException
     *             if the scanner has been closed
     */
    public boolean hasNextInt(int radix) {
        Pattern integerPattern = getIntegerPattern(radix);
        boolean isIntValue = false;
        if (hasNext(integerPattern)) {
            String intString = matcher.group();
            intString = removeLocaleInfo(intString, DataType.INT);
            try {
                Integer.parseInt(intString, radix);
                isIntValue = true;
            } catch (NumberFormatException e) {
                matchSuccessful = false;
            }
        }
        return isIntValue;
    }
    
    //TODO: To implement this feature
    public boolean hasNextLine() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public boolean hasNextLong() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public boolean hasNextLong(int radix) {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public boolean hasNextShort() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public boolean hasNextShort(int radix) {
        throw new NotYetImplementedException();
    }

    /**
     * returns the last IOException thrown when reading the underlying input. If
     * no exception is thrown, return null.
     * 
     * @return the last IOException thrown
     */
    public IOException ioException() {
        return lastIOException;
    }

    /**
     * return the locale of this scanner.
     * 
     * @return 
     *             the locale of this scanner
     */
    public Locale locale() {
        return locale;
    }

    /**
     * Returns the match result of this scanner's last match operation.This
     * method throws IllegalStateException if no match operation has been
     * performed, or if the last match was unsuccessful.
     * 
     * The various nextXXX methods of Scanner provide a match result if they do
     * not complete with throwing an exception. For example, after an invocation
     * of the nextBoolean() method which returned a boolean value, this method
     * returns a match result for the search of the Boolean regular expression
     * defined above. In the same way,the findInLine(java.lang.String),
     * findWithinHorizon(java.lang.String, int), and
     * skip(java.util.regex.Pattern) methods will provide a match result if they
     * are successful.
     * 
     * @return the match result of the last match operation
     * @throws IllegalStateException
     *             if the match result is available
     */
    public MatchResult match() {
        if (!matchSuccessful) {
            throw new IllegalStateException();
        }
        return matcher.toMatchResult();
    }

    /**
     * Finds and Returns the next complete token which is prefixed and postfixed
     * by input that matches the delimiter pattern. This method may be blocked
     * when it is waiting for input to scan, even if a previous invocation of
     * hasNext() returned true. If this match successes, the scanner advances
     * past the next complete token.
     * 
     * @return 
     *             the next complete token
     * @throws IllegalStateException
     *             if this scanner has been closed
     * @throws NoSuchElementException
     *             if input has been exhausted
     */
    public String next() {
        return next(ANY_PATTERN);
    }

    /**
     * Returns the next token which is prefixed and postfixed by input that
     * matches the delimiter pattern if this token matches the specified
     * pattern. This method may be blocked when it is waiting for input to scan,
     * even if a previous invocation of hasNext(Pattern) returned true. If this
     * match successes, the scanner advances past the next token that matched
     * the pattern.
     * 
     * @param pattern
     *            the specified pattern to scan
     * @return 
     *             the next token
     * @throws IllegalStateException
     *             if this scanner has been closed
     * @throws NoSuchElementException
     *             if input has been exhausted
     */
    public String next(Pattern pattern) {
        checkClosed();
        if (null == pattern) {
            throw new NullPointerException();
        }
        matchSuccessful = false;
        if (isInputExhausted()) {
            throw new NoSuchElementException();
        }
        saveCurrentStatus();
        if (!setTokenRegion()) {
            recoverPreviousStatus();
            // if setting match region fails
            throw new NoSuchElementException();
        }
        matcher.usePattern(pattern);
        if (matchSuccessful = matcher.matches()) {
            return matcher.group(0);
        } else {
            recoverPreviousStatus();
            throw new InputMismatchException();
        }
    }

    /**
     * Returns the next token which is prefixed and postfixed by input that
     * matches the delimiter pattern if this token matches the pattern
     * constructed from the sepcified string. This method may be blocked when it
     * is waiting for input to scan. If this match successes, the scanner
     * advances past the next token that matched the pattern.
     * 
     * The invocation of this method in the form next(pattern) behaves in the
     * same way as the invocaiton of next(Pattern.compile(pattern)).
     * 
     * @param pattern
     *            the string specifying the pattern to scan for
     * @return 
     *             the next token
     * @throws IllegalStateException
     *             if this scanner has been closed
     * @throws NoSuchElementException
     *             if input has been exhausted
     */
    public String next(String pattern) {
        return next(Pattern.compile(pattern));
    }

    //TODO: To implement this feature
    public BigDecimal nextBigDecimal() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public BigInteger nextBigInteger() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public BigInteger nextBigInteger(int radix) {
        throw new NotYetImplementedException();
    }

    /**
     * Translates the next token in this scanner's input into a boolean value and
     * returns this value. This method will throw InputMismatchException if the
     * next token can not be interpreted as a boolean value with a case
     * insensitive pattern created from the string "true|false". If this match
     * succeeds, the scanner advances past the input that matched.
     * 
     * @return the boolean value scanned from the input
     * @throws IllegalStateException
     *             if this scanner has been closed
     * @throws NoSuchElementException
     *             if input has been exhausted
     * @throws InputMismatchException
     *             if the next token can not be translated into a valid boolean
     *             value
     */
    public boolean nextBoolean() {
        return Boolean.parseBoolean(next(BOOLEAN_PATTERN));
    }
    
    //TODO: To implement this feature
    public byte nextByte() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public byte nextByte(int radix) {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public double nextDouble() {
        throw new NotYetImplementedException();
    }

    /**
     * Translates the next token in this scanner's input into a float value and
     * returns this value. This method may be blocked when it is waiting for
     * input to scan, even if a previous invocation of hasNextFloat() returned
     * true. If this match succeeds, the scanner advances past the input that
     * matched.
     * 
     * If the next token matches the Float regular expression successfully, the
     * token is translated into a float value as following steps. At first all
     * locale specific prefixes ,group separators, and locale specific suffixes
     * are removed. Then non-ASCII digits are mapped into ASCII digits via
     * {@link Character#digit(char, int)}}, a negative sign (-) is added if the
     * locale specific negative prefixes and suffixes were present. At last the
     * resulting String is passed to {@link Float#parseFloat(String)}}.If the
     * token matches the localized NaN or infinity strings, it is also passed to
     * {@link Float#parseFloat(String)}}.
     * 
     * @return the float value scanned from the input
     * @throws IllegalStateException
     *             if this scanner has been closed
     * @throws NoSuchElementException
     *             if input has been exhausted
     * @throws InputMismatchException
     *             if the next token can not be translated into a valid float
     *             value
     */
    public float nextFloat() {
        Pattern floatPattern = getFloatPattern();
        String floatString = next(floatPattern);
        floatString = removeLocaleInfoFromFloat(floatString);
        float floatValue = 0;
        try {
            floatValue = Float.parseFloat(floatString);
        } catch (NumberFormatException e) {
            matchSuccessful = false;
            recoverPreviousStatus();
            throw new InputMismatchException();
        }
        return floatValue;
    }

    /**
     * Translates the next token in this scanner's input into an int value and
     * returns this value. This method may be blocked when it is waiting for
     * input to scan, even if a previous invocation of hasNextInt() returned
     * true. If this match succeeds, the scanner advances past the input that
     * matched.
     * 
     * The invocation of this method in the form nextInt() behaves in the same
     * way as the invocaiton of nextInt(radix), the radix is the default radix
     * of this scanner.
     * 
     * @return the int value scanned from the input
     * @throws IllegalStateException
     *             if this scanner has been closed
     * @throws NoSuchElementException
     *             if input has been exhausted
     * @throws InputMismatchException
     *             if the next token can not be translated into a valid int
     *             value
     */
    public int nextInt() {
        return nextInt(integerRadix);
    }

    /**
     * Translates the next token in this scanner's input into an int value and
     * returns this value. This method may be blocked when it is waiting for
     * input to scan, even if a previous invocation of hasNextInt(radix)
     * returned true. If this match succeeds, the scanner advances past the
     * input that matched.
     * 
     * If the next token matches the Integer regular expression successfully,
     * the token is translated into an int value as following steps. At first
     * all locale specific prefixes ,group separators, and locale specific
     * suffixes are removed. Then non-ASCII digits are mapped into ASCII digits
     * via Character.digit, a negative sign (-) is added if the locale specific
     * negative prefixes and suffixes were present. At last the resulting String
     * is passed to Integer.parseInt with the specified radix.
     * 
     * @param radix
     *            the radix used to translate the token into an int value
     * @return the int value scanned from the input
     * @throws IllegalStateException
     *             if this scanner has been closed
     * @throws NoSuchElementException
     *             if input has been exhausted
     * @throws InputMismatchException
     *             if the next token can not be translated into a valid int
     *             value
     */
    public int nextInt(int radix) {
        Pattern integerPattern = getIntegerPattern(radix);
        String intString=next(integerPattern);
        intString = removeLocaleInfo(intString, DataType.INT);
        int intValue = 0;
        try {
            intValue = Integer.parseInt(intString, radix);
        } catch (NumberFormatException e) {
            matchSuccessful = false;
            recoverPreviousStatus();
            throw new InputMismatchException();
        }
        return intValue;
    }

    //TODO: To implement this feature
    public String nextLine() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public long nextLong() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public long nextLong(int radix) {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public short nextShort() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public short nextShort(int radix) {
        throw new NotYetImplementedException();
    }

    /**
     * return the radix of this scanner.
     * 
     * @return
     *            the radix of this scanner
     */
    public int radix() {
        return integerRadix;
    }

    //TODO: To implement this feature
    public Scanner skip(Pattern pattern) {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public Scanner skip(String pattern) {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public String toString() {
        throw new NotYetImplementedException();
    }

    /**
     * Set the delimiting pattern of this scanner
     * 
     * @param pattern
     *            the delimiting pattern to use
     * @return this scanner
     */
    public Scanner useDelimiter(Pattern pattern) {
        delimiter = pattern;
        return this;
    }

    /**
     * Set the delimiting pattern of this scanner with a pattern compiled from
     * the supplied string value
     * 
     * @param pattern
     *            a string from which a <code>Pattern</code> can be compiled
     * @return this scanner
     */
    public Scanner useDelimiter(String pattern) {
        return useDelimiter(Pattern.compile(pattern));
    }

    /**
     * 
     * set the locale of this scanner to a specified locale. 
     *
     * @param locale
     *              the specified locale to use
     * @return
     *              this scanner
     */
    public Scanner useLocale(Locale locale) {
        if (null == locale)
            throw new NullPointerException();
        this.locale = locale;
        return this;
    }

    /**
     * 
     * set the radix of this scanner to a specified radix.
     * 
     * @param radix
     *             the specified radix to use
     * @return
     *             this scanner
     */
    public Scanner useRadix(int radix) {
        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX) {
            throw new IllegalArgumentException(org.apache.harmony.luni.util.Msg
                    .getString("KA008", radix)); //$NON-NLS-1$
        }
        this.integerRadix = radix;
        return this;
    }

    /**
     * 
     * The operation of remove is not supported by this implementation of
     * Iterator.
     * 
     * @return UnsupportedOperationException 
     *            if this method is invoked
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /*
     * Initial some components.
     */
    private void initialization() {
        buffer = CharBuffer.allocate(DEFAULT_TRUNK_SIZE);
        buffer.limit(0);
        matcher = delimiter.matcher(buffer);
    }
    
    /*
     * Check the scanner's state, if it is closed, IllegalStateException will be
     * thrown.
     */
    private void checkClosed() {
        if (closed) {
            throw new IllegalStateException();
        }
    }

    /*
     * Check input resource of this scanner, if it has been exhausted, return
     * true.
     */
    private boolean isInputExhausted() {
        if (findStartIndex == bufferLength) {
            if (readMore()) {
                resetMatcher();
            } else {
                return true;
            }
        }
        return false;
    }

    /*
     * Change the matcher's string after reading input
     */
    private void resetMatcher() {
        if (null == matcher) {
            matcher = delimiter.matcher(buffer);
        } else {
            matcher.reset(buffer);
        }
        matcher.region(findStartIndex, bufferLength);
    }

    /*
     * save the matcher's last find position
     */
    private void saveCurrentStatus() {
        preStartIndex = findStartIndex;
    }

    /*
     * Change the matcher's status to  last find position
     */
    private void recoverPreviousStatus() {
        findStartIndex = preStartIndex;
    }
    
    /*
     * Get integer's pattern
     */
    private Pattern getIntegerPattern(int radix) {
        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX) {
            throw new IllegalArgumentException(org.apache.harmony.luni.util.Msg
                    .getString("KA00e", radix)); //$NON-NLS-1$
        }
        decimalFormat = (DecimalFormat) NumberFormat.getInstance(locale);
        
        String allAvailableDigits="0123456789abcdefghijklmnopqrstuvwxyz"; //$NON-NLS-1$ 
        String ASCIIDigit=allAvailableDigits.substring(0, radix);
        String nonZeroASCIIDigit=allAvailableDigits.substring(1, radix);

        StringBuilder digit = new StringBuilder("((?i)[").append(ASCIIDigit) //$NON-NLS-1$ 
                .append("]|\\p{javaDigit})"); //$NON-NLS-1$
        StringBuilder nonZeroDigit = new StringBuilder("((?i)[").append( //$NON-NLS-1$
                nonZeroASCIIDigit).append("]|([\\p{javaDigit}&&[^0]]))"); //$NON-NLS-1$
        StringBuilder numeral = getNumeral(digit, nonZeroDigit);

        StringBuilder integer = new StringBuilder("(([-+]?(").append(numeral) //$NON-NLS-1$
                .append(")))|(").append(addPositiveSign(numeral)).append(")|(") //$NON-NLS-1$ //$NON-NLS-2$
                .append(addNegativeSign(numeral)).append(")"); //$NON-NLS-1$

        Pattern integerPattern = Pattern.compile(integer.toString());
        return integerPattern;
    }

    /*
     * Get pattern of float
     */
    private Pattern getFloatPattern() {
        decimalFormat = (DecimalFormat) NumberFormat.getInstance(locale);

        StringBuilder digit = new StringBuilder("([0-9]|(\\p{javaDigit}))"); //$NON-NLS-1$
        StringBuilder nonZeroDigit = new StringBuilder("[\\p{javaDigit}&&[^0]]"); //$NON-NLS-1$
        StringBuilder numeral = getNumeral(digit, nonZeroDigit);

        char decimalSeparator = decimalFormat.getDecimalFormatSymbols()
                .getDecimalSeparator();
        StringBuilder decimalNumeral = new StringBuilder("(").append(numeral) //$NON-NLS-1$
                .append("|").append(numeral).append("\\") //$NON-NLS-1$//$NON-NLS-2$
                .append(decimalSeparator).append(digit).append("*+|\\").append( //$NON-NLS-1$
                        decimalSeparator).append(digit).append("++)"); //$NON-NLS-1$
        StringBuilder exponent = new StringBuilder("([eE][+-]?").append(digit) //$NON-NLS-1$
                .append("+)?"); //$NON-NLS-1$

        StringBuilder decimal = new StringBuilder("(([-+]?").append( //$NON-NLS-1$
                decimalNumeral).append("(").append(exponent).append("?)") //$NON-NLS-1$ //$NON-NLS-2$
                .append(")|(").append(addPositiveSign(decimalNumeral)).append( //$NON-NLS-1$
                        "(").append(exponent).append("?)").append(")|(") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                .append(addNegativeSign(decimalNumeral)).append("(").append( //$NON-NLS-1$
                        exponent).append("?)").append("))"); //$NON-NLS-1$ //$NON-NLS-2$

        StringBuilder hexFloat = new StringBuilder("([-+]?0[xX][0-9a-fA-F]*\\") //$NON-NLS-1$
                .append(decimalSeparator).append(
                        "[0-9a-fA-F]+([pP][-+]?[0-9]++)?)"); //$NON-NLS-1$
        String localNaN = decimalFormat.getDecimalFormatSymbols().getNaN();
        String localeInfinity = decimalFormat.getDecimalFormatSymbols()
                .getInfinity();
        StringBuilder nonNumber = new StringBuilder("NaN|\\").append(localNaN) //$NON-NLS-1$
                .append("|Infinity|\\").append(localeInfinity).append(""); //$NON-NLS-1$ //$NON-NLS-2$
        StringBuilder singedNonNumber = new StringBuilder("((([-+]?(").append( //$NON-NLS-1$
                nonNumber).append(")))|(").append(addPositiveSign(nonNumber)) //$NON-NLS-1$
                .append(")|(").append(addNegativeSign(nonNumber)).append("))"); //$NON-NLS-1$ //$NON-NLS-2$

        StringBuilder floatString = new StringBuilder().append(decimal).append(
                "|").append(hexFloat).append("|").append(singedNonNumber); //$NON-NLS-1$ //$NON-NLS-2$
        Pattern floatPattern = Pattern.compile(floatString.toString());
        return floatPattern;
    }

    private StringBuilder getNumeral(StringBuilder digit,
            StringBuilder nonZeroDigit) {
        char groupSeparator = decimalFormat.getDecimalFormatSymbols()
                .getGroupingSeparator();
        StringBuilder groupedNumeral = new StringBuilder("(").append( //$NON-NLS-1$
                nonZeroDigit).append(digit).append("?").append(digit).append( //$NON-NLS-1$
                "?(\\").append(groupSeparator).append(digit).append(digit) //$NON-NLS-1$
                .append(digit).append(")+)"); //$NON-NLS-1$
        StringBuilder numeral = new StringBuilder("((").append(digit).append( //$NON-NLS-1$
                "++)|").append(groupedNumeral).append(")"); //$NON-NLS-1$ //$NON-NLS-2$
        return numeral;
    }

    /*
     * Add the locale specific positive prefixes and suffixes to the pattern
     */
    private StringBuilder addPositiveSign(StringBuilder unSignNumeral) {
        String positivePrefix = ""; //$NON-NLS-1$
        String positiveSuffix = ""; //$NON-NLS-1$
        if (!decimalFormat.getPositivePrefix().equals("")) { //$NON-NLS-1$
            positivePrefix = "\\" + decimalFormat.getPositivePrefix(); //$NON-NLS-1$
        }
        if (!decimalFormat.getPositiveSuffix().equals("")) { //$NON-NLS-1$
            positiveSuffix = "\\" + decimalFormat.getPositiveSuffix(); //$NON-NLS-1$
        }
        StringBuilder signedNumeral = new StringBuilder()
                .append(positivePrefix).append(unSignNumeral).append(
                        positiveSuffix);
        return signedNumeral;
    }

    /*
     * Add the locale specific negative prefixes and suffixes to the pattern
     */
    private StringBuilder addNegativeSign(StringBuilder unSignNumeral) {
        String negativePrefix = ""; //$NON-NLS-1$
        String negativeSuffix = ""; //$NON-NLS-1$
        if (!decimalFormat.getNegativePrefix().equals("")) { //$NON-NLS-1$
            negativePrefix = "\\" + decimalFormat.getNegativePrefix(); //$NON-NLS-1$
        }
        if (!decimalFormat.getNegativeSuffix().equals("")) { //$NON-NLS-1$
            negativeSuffix = "\\" + decimalFormat.getNegativeSuffix(); //$NON-NLS-1$
        }
        StringBuilder signedNumeral = new StringBuilder()
                .append(negativePrefix).append(unSignNumeral).append(
                        negativeSuffix);
        return signedNumeral;
    }

    /*
     * Remove locale related information from float String
     */
    private String removeLocaleInfoFromFloat(String floatString) {
        // If the token is HexFloat
        if (-1 != floatString.indexOf('x')
                || -1 != floatString.indexOf('X')) {
            return floatString;
        }
        
        int exponentIndex;
        String decimalNumeralString;
        String exponentString;
        // If the token is scientific notation
        if (-1 != (exponentIndex = floatString.indexOf('e'))
                || -1 != (exponentIndex = floatString.indexOf('E'))) {
            decimalNumeralString = floatString.substring(0, exponentIndex);
            exponentString = floatString.substring(exponentIndex + 1,
                    floatString.length());
            decimalNumeralString = removeLocaleInfo(decimalNumeralString,
                    DataType.FLOAT);
            return decimalNumeralString + "e" + exponentString; //$NON-NLS-1$ 
        }
        return removeLocaleInfo(floatString, DataType.FLOAT);
    }
    
    /*
     * Remove the locale specific prefixes, group separators, and locale
     * specific suffixes from input string
     */
    private String removeLocaleInfo(String token, DataType type) {
        StringBuilder tokenBuilder = new StringBuilder(token);
        boolean negative = removeLocaleSign(tokenBuilder);
        // Remove group separator
        String groupSeparator = String.valueOf(decimalFormat
                .getDecimalFormatSymbols().getGroupingSeparator());
        int separatorIndex = -1;
        while (-1 != (separatorIndex = tokenBuilder.indexOf(groupSeparator))) {
            tokenBuilder.delete(separatorIndex, separatorIndex + 1);
        }
        // Remove decimal separator
        String decimalSeparator = String.valueOf(decimalFormat
                .getDecimalFormatSymbols().getDecimalSeparator());
        separatorIndex = tokenBuilder.indexOf(decimalSeparator);
        StringBuilder result = new StringBuilder(""); //$NON-NLS-1$
        if (DataType.INT == type) {
            for (int i = 0; i < tokenBuilder.length(); i++) {
                if (-1 != Character.digit(tokenBuilder.charAt(i),
                        Character.MAX_RADIX)) {
                    result.append(tokenBuilder.charAt(i));
                }
            }
        }
        if (DataType.FLOAT == type) {
            for (int i = 0; i < tokenBuilder.length(); i++) {
                if (-1 != Character.digit(tokenBuilder.charAt(i), 10)) {
                    result.append(Character.digit(tokenBuilder.charAt(i), 10));
                }
            }
        }
        // Token is NaN or Infinity
        if (0 == result.length()) {
            result = tokenBuilder;
        }
        if (-1 != separatorIndex) {
            result.insert(separatorIndex, "."); //$NON-NLS-1$
        }
        // If input is negative
        if (negative) {
            result.insert(0, '-');
        }
        return result.toString();
    }
    /*
     * remove positive and negative sign from the parameter stringBuilder, and
     * return whether the input string is negative
     */
    private boolean removeLocaleSign(StringBuilder tokenBuilder) {
        String positivePrefix = decimalFormat.getPositivePrefix();
        String positiveSuffix = decimalFormat.getPositiveSuffix();
        String negativePrefix = decimalFormat.getNegativePrefix();
        String negativeSuffix = decimalFormat.getNegativeSuffix();

        if (0 == tokenBuilder.indexOf("+")) { //$NON-NLS-1$
            tokenBuilder.delete(0, 1);
        }
        if (!positivePrefix.equals("") //$NON-NLS-1$
                && 0 == tokenBuilder.indexOf(positivePrefix)) {
            tokenBuilder.delete(0, positivePrefix.length());
        }
        if (!positiveSuffix.equals("") //$NON-NLS-1$
                && -1 != tokenBuilder.indexOf(positiveSuffix)) {
            tokenBuilder.delete(
                    tokenBuilder.length() - positiveSuffix.length(),
                    tokenBuilder.length());
        }
        boolean negative = false;
        if (0 == tokenBuilder.indexOf("-")) { //$NON-NLS-1$
            tokenBuilder.delete(0, 1);
            negative = true;
        }
        if (!negativePrefix.equals("") //$NON-NLS-1$
                && 0 == tokenBuilder.indexOf(negativePrefix)) {
            tokenBuilder.delete(0, negativePrefix.length());
            negative = true;
        }
        if (!negativeSuffix.equals("") //$NON-NLS-1$
                && -1 != tokenBuilder.indexOf(negativeSuffix)) {
            tokenBuilder.delete(
                    tokenBuilder.length() - negativeSuffix.length(),
                    tokenBuilder.length());
            negative = true;
        }
        return negative;
    }

    /*
     * Find the prefixed delimiter and posefixed delimiter in the input resource
     * and set the start index and end index of Matcher region. If postfixed
     * delimiter does not exist, the end index is set to be end of input.
     */
    private boolean setTokenRegion() {
        // The position where token begins
        int tokenStartIndex = 0;
        // The position where token ends
        int tokenEndIndex = 0;
        // Use delimiter pattern
        matcher.usePattern(delimiter);
        matcher.region(findStartIndex, bufferLength);

        tokenStartIndex = findPreDelimiter();
        if (setHeadTokenRegion(tokenStartIndex)) {
            return true;
        }
        tokenEndIndex = findPostDelimiter();
        // If the second delimiter is not found
        if (-1 == tokenEndIndex) {
            // Just first Delimiter Exists
            if (findStartIndex == bufferLength) {
                return false;
            }
            tokenEndIndex = bufferLength;
            findStartIndex = bufferLength;
        }

        matcher.region(tokenStartIndex, tokenEndIndex);
        return true;
    }

    /*
     * Find prefixed delimiter
     */
    private int findPreDelimiter() {
        int tokenStartIndex;
        boolean findComplete = false;
        while (!findComplete) {
            if (findComplete = matcher.find()) {
                // If just delimiter remains
                if (matcher.start() == findStartIndex
                        && matcher.end() == bufferLength) {
                    // If more input resource exists
                    if (readMore()) {
                        resetMatcher();
                        findComplete = false;
                    }
                }
            } else {
                if (readMore()) {
                    resetMatcher();
                } else {
                    return -1;
                }
            }
        }
        tokenStartIndex = matcher.end();
        findStartIndex = matcher.end();
        return tokenStartIndex;
    }

    /*
     * Handle some special cases
     */
    private boolean setHeadTokenRegion(int findIndex) {
        int tokenStartIndex;
        int tokenEndIndex;
        boolean setSuccess = false;
        // If no delimiter exists, but something exites in this scanner
        if (-1 == findIndex && preStartIndex != bufferLength) {
            tokenStartIndex = preStartIndex;
            tokenEndIndex = bufferLength;
            findStartIndex = bufferLength;
            matcher.region(tokenStartIndex, tokenEndIndex);
            setSuccess = true;
        }
        // If the first delimiter of scanner is not at the find start position
        if (-1 != findIndex && preStartIndex != matcher.start()) {
            tokenStartIndex = preStartIndex;
            tokenEndIndex = matcher.start();
            findStartIndex = matcher.start();
            // set match region and return
            matcher.region(tokenStartIndex, tokenEndIndex);
            setSuccess = true;
        }
        return setSuccess;
    }

    /*
     * Find postfixed delimiter
     */
    private int findPostDelimiter() {
        int tokenEndIndex = 0;
        boolean findComplete = false;
        while (!findComplete) {
            if (findComplete = matcher.find()) {
                if (matcher.start() == findStartIndex
                        && matcher.start() == matcher.end()) {
                    findComplete = false;
                }
            } else {
                if (readMore()) {
                    resetMatcher();
                } else {
                    return -1;
                }
            }
        }
        tokenEndIndex = matcher.start();
        findStartIndex = matcher.start();
        return tokenEndIndex;
    }

    /*
     * Read more data from underlying Readable. Return false if nothing is
     * available or I/O operation fails.
     */
    private boolean readMore() {
        int oldPosition = buffer.position();
        int oldLimit = buffer.limit();
        // Increase capacity if empty space is not enough
        if (buffer.limit() >= buffer.capacity()) {
            expandBuffer();
        }

        // Read input resource
        int readCount = 0;
        try {
            buffer.limit(buffer.capacity());
            buffer.position(oldLimit);
            while ((readCount = input.read(buffer)) == 0) {
                // nothing to do here
            }
        } catch (IOException e) {
            bufferLength += (buffer.position() - oldLimit);
            readCount = -1;
            lastIOException = e;
        }

        // The return value of readable.read() method can be used to record the
        // actual characters read. Consider the scenario: readable puts 4 chars into
        // buffer and then an IOException is thrown out. In this case, buffer is
        // actually grown, but readable.read() will never return.

        buffer.flip();
        buffer.position(oldPosition);
        if (-1 != readCount)
            bufferLength = readCount + bufferLength;
        return readCount != -1;
    }

    // Expand the size of internal buffer.
    private void expandBuffer() {
        int oldPosition = buffer.position();
        int oldCapacity = buffer.capacity();
        int oldLimit = buffer.limit();
        int newCapacity = oldCapacity * DIPLOID;
        char[] newBuffer = new char[newCapacity];
        if (buffer != null) {
            System.arraycopy(buffer.array(), 0, newBuffer, 0, oldLimit);
        }
        buffer = CharBuffer.wrap(newBuffer, 0, newCapacity);
        buffer.position(oldPosition);
        buffer.limit(oldLimit);
    }
}
