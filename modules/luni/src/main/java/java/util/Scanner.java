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
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.regex.MatchResult;
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

    // Default delimiting pattern
    private final static Pattern DEFAULT_DELIMITER = Pattern
            .compile("\\p{javaWhitespace}+"); //$NON-NLS-1$

    //Default radix
    private final static int DEFAULT_RADIX = 10;

    //The input source of scanner
    private Readable input;

    private Pattern delimiter = DEFAULT_DELIMITER;

    private int radix = DEFAULT_RADIX;

    private Locale locale = Locale.getDefault();

    private boolean closed = false; // used by find and nextXXX operation

    private IOException lastIOException;

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
                    .getString("KA00a"));
        }
        FileInputStream fis = new FileInputStream(src);
        if (null == charsetName) {
            throw new IllegalArgumentException(org.apache.harmony.luni.util.Msg
                    .getString("KA009"));
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
    }

    /**
     * Constructs a scanner that uses String as its input.
     * 
     * @param src
     *            the string to be scanned
     */
    public Scanner(String src) {
        input = new StringReader(src);
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
                    .getString("KA00b"));
        }
        try {
            input = new InputStreamReader(src, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Constructs a scanner that uses Readable as its input.
     * 
     * @param src
     *            the Readable to be scanned
     */
    public Scanner(Readable src) {
        if (null == src) {
            throw new NullPointerException(org.apache.harmony.luni.util.Msg
                    .getString("KA00c"));
        }
        input = src;
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
                    .getString("KA00d"));
        }
        if (null == charsetName) {
            throw new IllegalArgumentException(org.apache.harmony.luni.util.Msg
                    .getString("KA009"));
        }
        try {
            input = new InputStreamReader(Channels.newInputStream(src),
                    charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
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

    //TODO: To implement this feature
    public String findWithinHorizon(Pattern pattern, int horizon) {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public String findWithinHorizon(String pattern, int horizon) {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public boolean hasNext() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public boolean hasNext(Pattern pattern) {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public boolean hasNext(String pattern) {
        throw new NotYetImplementedException();
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

    //TODO: To implement this feature
    public boolean hasNextBoolean() {
        throw new NotYetImplementedException();
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

    //TODO: To implement this feature
    public boolean hasNextFloat() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public boolean hasNextInt() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public boolean hasNextInt(int radix) {
        throw new NotYetImplementedException();
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

    //TODO: To implement this feature
    public MatchResult match() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public String next() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public String next(Pattern pattern) {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public String next(String pattern) {
        throw new NotYetImplementedException();
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

    //TODO: To implement this feature
    public boolean nextBoolean() {
        throw new NotYetImplementedException();
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

    //TODO: To implement this feature
    public float nextFloat() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public int nextInt() {
        throw new NotYetImplementedException();
    }

    //TODO: To implement this feature
    public int nextInt(int radix) {
        throw new NotYetImplementedException();
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
        return radix;
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
        if( null == locale )
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
                    .getString("KA008", radix));
        }
        this.radix = radix;
        return this;
    }

    //TODO: To implement this feature
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
