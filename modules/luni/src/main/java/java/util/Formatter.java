/* Licensed to the Apache Software Foundation (ASF) under one or more
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
package java.util;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

/**
 * Formatter provides the method to give out formatted string just like the
 * printf-style. Layout,alignment and other format flags are provided to format
 * numeric,string and date/time as well as locale-specified formats applied.
 * Besides primitive types, formatter also support some java object types such
 * as BigInteger,BigDecimal and Calendar. Customized formatting is provided
 * through the Formattable interface.
 * 
 * The class is not multi-threaded safe. The responsibility to maintain thread
 * safety is the user's job.
 * 
 * @since 1.5
 */
public final class Formatter implements Closeable, Flushable {

    public enum BigDecimalLayoutForm {
        SCIENTIFIC, DECIMAL_FLOAT
    }

    private Appendable out;

    private Locale locale;

    private boolean closed = false;

    private IOException lastIOException;

    /**
     * Constructs a formatter.
     * 
     * The output is a StringBuilder which can be achieved by invoking the out
     * method and whose contents can be attained by calling the toString method.
     * 
     * The locale for the formatter is the default locale of the JVM.
     */
    public Formatter() {
        this(new StringBuilder(), Locale.getDefault());
    }

    /**
     * Constructs a formatter of which the output is denoted.
     * 
     * The locale for the formatter is the default locale of the JVM.
     * 
     * @param a
     *            The output of the formatter. If a is null, then a
     *            StringBuilder will be used.
     */
    public Formatter(Appendable a) {
        this(a, Locale.getDefault());
    }

    /**
     * Constructs a formatter of which the locale is denoted.
     * 
     * The output destination is a StringBuilder which can be achieved by
     * invoking the out method and whose contents can be attained by calling the
     * toString method.
     * 
     * @param l
     *            The locale of the formatter. If l is null, then no
     *            localization will be used.
     */
    public Formatter(Locale l) {
        this(new StringBuilder(), l);
    }

    /**
     * Constructs a formatter of which the output and locale is denoted.
     * 
     * @param a
     *            The output of the formatter. If a is null, then a
     *            StringBuilder will be used.
     * @param l
     *            The locale of the formatter. If l is null, then no
     *            localization will be used.
     */
    public Formatter(Appendable a, Locale l) {
        if (null == a) {
            out = new StringBuilder();
        } else {
            out = a;
        }
        locale = l;
    }

    /**
     * Constructs a formatter of which the filename is denoted.
     * 
     * The charset of the formatter is the default charset of JVM.
     * 
     * The locale for the formatter is the default locale of the JVM.
     * 
     * @param fileName
     *            The filename of the file that is used as the output
     *            destination for the formatter. The file will be truncated to
     *            zero size if the file exists, or else a new file will be
     *            created. The output of the formatter is buffered.
     * 
     * @throws FileNotFoundException
     *             If the filename does not denote a normal and writable file,
     *             or a new file cannot be created or any error rises when
     *             opening or creating the file.
     * @throws SecurityException
     *             If there is a security manager and it denies writing to the
     *             file in checkWrite(file.getPath()).
     */
    public Formatter(String fileName) throws FileNotFoundException {
        this(new File(fileName));

    }

    /**
     * Constructs a formatter of which the filename and charset is denoted.
     * 
     * The locale for the formatter is the default locale of the JVM.
     * 
     * @param fileName
     *            The filename of the file that is used as the output
     *            destination for the formatter. The file will be truncated to
     *            zero size if the file exists, or else a new file will be
     *            created. The output of the formatter is buffered.
     * @param csn
     *            The name of the charset for the formatter.
     * 
     * @throws FileNotFoundException
     *             If the filename does not denote a normal and writable file,
     *             or a new file cannot be created or any error rises when
     *             opening or creating the file.
     * @throws SecurityException
     *             If there is a security manager and it denies writing to the
     *             file in checkWrite(file.getPath()).
     * @throws UnsupportedEncodingException
     *             If the charset with the specified name is not supported.
     */
    public Formatter(String fileName, String csn) throws FileNotFoundException,
            UnsupportedEncodingException {
        this(new File(fileName), csn);
    }

    /**
     * Constructs a formatter of which the filename, charset and locale is
     * denoted.
     * 
     * @param fileName
     *            The filename of the file that is used as the output
     *            destination for the formatter. The file will be truncated to
     *            zero size if the file exists, or else a new file will be
     *            created. The output of the formatter is buffered.
     * @param csn
     *            The name of the charset for the formatter.
     * @param l
     *            The locale of the formatter. If l is null, then no
     *            localization will be used.
     * 
     * @throws FileNotFoundException
     *             If the filename does not denote a normal and writable file,
     *             or a new file cannot be created or any error rises when
     *             opening or creating the file.
     * @throws SecurityException
     *             If there is a security manager and it denies writing to the
     *             file in checkWrite(file.getPath()).
     * @throws UnsupportedEncodingException
     *             If the charset with the specified name is not supported.
     * 
     */
    public Formatter(String fileName, String csn, Locale l)
            throws FileNotFoundException, UnsupportedEncodingException {

        this(new File(fileName), csn, l);
    }

    /**
     * Constructs a formatter of which the file is denoted.
     * 
     * The charset of the formatter is the default charset of JVM.
     * 
     * The locale for the formatter is the default locale of the JVM.
     * 
     * @param file
     *            The file that is used as the output destination for the
     *            formatter. The file will be truncated to zero size if the file
     *            exists, or else a new file will be created. The output of the
     *            formatter is buffered.
     * 
     * @throws FileNotFoundException
     *             If the file does not denote a normal and writable file, or a
     *             new file cannot be created or any error rises when opening or
     *             creating the file.
     * @throws SecurityException
     *             If there is a security manager and it denies writing to the
     *             file in checkWrite(file.getPath()).
     */
    public Formatter(File file) throws FileNotFoundException {
        this(new FileOutputStream(file));
    }

    /**
     * Constructs a formatter of which the file and charset is denoted.
     * 
     * The locale for the formatter is the default locale of the JVM.
     * 
     * @param file
     *            The file of the file that is used as the output destination
     *            for the formatter. The file will be truncated to zero size if
     *            the file exists, or else a new file will be created. The
     *            output of the formatter is buffered.
     * @param csn
     *            The name of the charset for the formatter.
     * @throws FileNotFoundException
     *             If the file does not denote a normal and writable file, or a
     *             new file cannot be created or any error rises when opening or
     *             creating the file.
     * @throws SecurityException
     *             If there is a security manager and it denies writing to the
     *             file in checkWrite(file.getPath()).
     * @throws UnsupportedEncodingException
     *             If the charset with the specified name is not supported.
     */
    public Formatter(File file, String csn) throws FileNotFoundException,
            UnsupportedEncodingException {
        this(file, csn, Locale.getDefault());
    }

    /**
     * Constructs a formatter of which the file, charset and locale is denoted.
     * 
     * @param file
     *            file that is used as the output destination for the formatter.
     *            The file will be truncated to zero size if the file exists, or
     *            else a new file will be created. The output of the formatter
     *            is buffered.
     * @param csn
     *            The name of the charset for the formatter.
     * @param l
     *            The locale of the formatter. If l is null, then no
     *            localization will be used.
     * @throws FileNotFoundException
     *             If the file does not denote a normal and writable file, or a
     *             new file cannot be created or any error rises when opening or
     *             creating the file.
     * @throws SecurityException
     *             If there is a security manager and it denies writing to the
     *             file in checkWrite(file.getPath()).
     * @throws UnsupportedEncodingException
     *             If the charset with the specified name is not supported.
     */
    public Formatter(File file, String csn, Locale l)
            throws FileNotFoundException, UnsupportedEncodingException {
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(fout, csn);
            out = new BufferedWriter(writer);
        } catch (RuntimeException e) {
            closeOutputStream(fout);
            throw e;
        } catch (UnsupportedEncodingException e) {
            closeOutputStream(fout);
            throw e;
        }

        locale = l;
    }

    /**
     * Constructs a formatter of which the output destination is specified.
     * 
     * The charset of the formatter is the default charset of JVM.
     * 
     * The locale for the formatter is the default locale of the JVM.
     * 
     * @param os
     *            The stream used as the destination of the formatter.
     */
    public Formatter(OutputStream os) {
        OutputStreamWriter writer = new OutputStreamWriter(os, Charset
                .defaultCharset());
        out = new BufferedWriter(writer);
        locale = Locale.getDefault();
    }

    /**
     * Constructs a formatter of which the output destination and the charset is
     * specified.
     * 
     * The locale for the formatter is the default locale of the JVM.
     * 
     * @param os
     *            The stream used as the destination of the formatter.
     * @param csn
     *            The name of the charset for the formatter.
     * @throws UnsupportedEncodingException
     *             If the charset with the specified name is not supported.
     */
    public Formatter(OutputStream os, String csn)
            throws UnsupportedEncodingException {

        this(os, csn, Locale.getDefault());
    }

    /**
     * Constructs a formatter of which the output destination, the charset and
     * the locale is specified.
     * 
     * @param os
     *            The stream used as the destination of the formatter.
     * @param csn
     *            The name of the charset for the formatter.
     * @param l
     *            The locale of the formatter. If l is null, then no
     *            localization will be used.
     * @throws UnsupportedEncodingException
     *             If the charset with the specified name is not supported.
     */
    public Formatter(OutputStream os, String csn, Locale l)
            throws UnsupportedEncodingException {

        OutputStreamWriter writer = new OutputStreamWriter(os, csn);
        out = new BufferedWriter(writer);

        locale = l;
    }

    /**
     * Constructs a formatter of which the output destination is specified.
     * 
     * The charset of the formatter is the default charset of JVM.
     * 
     * The locale for the formatter is the default locale of the JVM.
     * 
     * @param ps
     *            The print stream used as destination of the formatter. If ps
     *            is null, then NullPointerExcepiton will be thrown out.
     */
    public Formatter(PrintStream ps) {
        if (null == ps) {
            throw new NullPointerException();
        }
        out = ps;
        locale = Locale.getDefault();
    }

    private void checkClosed() {
        if (closed) {
            throw new FormatterClosedException();
        }
    }

    /**
     * Returns the locale of the formatter.
     * 
     * @return The locale for the formatter and null for no locale.
     * @throws FormatterClosedException
     *             If the formatter has been closed.
     */
    public Locale locale() {
        checkClosed();
        return locale;
    }

    /**
     * Returns the output destination of the formatter.
     * 
     * @return The output destination of the formatter.
     * @throws FormatterClosedException
     *             If the formatter has been closed.
     */
    public Appendable out() {
        checkClosed();
        return out;
    }

    /**
     * Returns the content by calling the toString() method of the output
     * destination.
     * 
     * @return The content by calling the toString() method of the output
     *         destination.
     * @throws FormatterClosedException
     *             If the formatter has been closed.
     */
    @Override
    public String toString() {
        checkClosed();
        return out.toString();
    }

    /**
     * Flushes the formatter. If the output destination is {@link Flushable},
     * then the method flush() will be called on that destination.
     * 
     * @throws FormatterClosedException
     *             If the formatter has been closed.
     */
    public void flush() {
        checkClosed();
        if (out instanceof Flushable) {
            try {
                ((Flushable) out).flush();
            } catch (IOException e) {
                lastIOException = e;
            }
        }
    }

    /**
     * Closes the formatter. If the output destination is {@link Closeable},
     * then the method close() will be called on that destination.
     * 
     * If the formatter has been closed, then calling the close will have no
     * effect.
     * 
     * Any method but the ioException() that is called after the formatter has
     * been closed will raise a FormatterClosedException.
     */
    public void close() {
        closed = true;
        try {
            if (out instanceof Closeable) {
                ((Closeable) out).close();
            }
        } catch (IOException e) {

            lastIOException = e;
        }
    }

    /**
     * Returns the last IOException thrown out by the formatter's output
     * destination. If the append() method of the destination will not throw
     * IOException, the ioException() method will always return null.
     * 
     * @return The last IOException thrown out by the formatter's output
     *         destination.
     */
    public IOException ioException() {
        return lastIOException;
    }

    /**
     * Writes a formatted string to the output destination of the formatter.
     * 
     * @param format
     *            A format string.
     * @param args
     *            The arguments list used in the format() method. If there are
     *            more arguments than those specified by the format string, then
     *            the additional arguments are ignored.
     * @return This formatter.
     * @throws IllegalFormatException
     *             If the format string is illegal or incompatible with the
     *             arguments or the arguments are less than those required by
     *             the format string or any other illegal situation.
     * @throws FormatterClosedException
     *             If the formatter has been closed.
     */
    public Formatter format(String format, Object... args) {
        return format(locale, format, args);
    }

    /**
     * Writes a formatted string to the output destination of the formatter.
     * 
     * @param l
     *            The locale used in the method. If locale is null, then no
     *            localization will be applied. This parameter does not
     *            influence the locale specified during construction.
     * @param format
     *            A format string.
     * @param args
     *            The arguments list used in the format() method. If there are
     *            more arguments than those specified by the format string, then
     *            the additional arguments are ignored.
     * @return This formatter.
     * @throws IllegalFormatException
     *             If the format string is illegal or incompatible with the
     *             arguments or the arguments are less than those required by
     *             the format string or any other illegal situation.
     * @throws FormatterClosedException
     *             If the formatter has been closed.
     */
    public Formatter format(Locale l, String format, Object... args) {
        checkClosed();
        CharBuffer formatBuffer = CharBuffer.wrap(format);
        ParserStateMachine parser = new ParserStateMachine(formatBuffer);
        Transformer transformer = new Transformer(this, l);

        int currentObjectIndex = 0;
        Object lastArgument = null;
        boolean hasLastArgumentSet = false;
        while (formatBuffer.hasRemaining()) {
            parser.reset();
            FormatToken token = parser.getNextFormatToken();
            String result;
            String plainText = token.getPlainText();
            if (token.getConversionType() == (char) FormatToken.UNSET) {
                result = plainText;
            } else {
                plainText = plainText.substring(0, plainText.indexOf('%'));
                Object argument = null;
                if (token.requireArgument()) {
                    int index = token.getArgIndex() == FormatToken.UNSET ? currentObjectIndex++
                            : token.getArgIndex();
                    argument = getArgument(args, index, token, lastArgument,
                            hasLastArgumentSet);
                    lastArgument = argument;
                    hasLastArgumentSet = true;
                }
                result = transformer.transform(token, argument);
                result = (null == result ? plainText : plainText + result);
            }
            // if output is made by formattable callback
            if (null != result) {
                try {
                    out.append(result);
                } catch (IOException e) {
                    lastIOException = e;
                }
            }
        }
        return this;
    }

    private Object getArgument(Object[] args, int index, FormatToken token,
            Object lastArgument, boolean hasLastArgumentSet) {
        if (index == FormatToken.LAST_ARGUMENT_INDEX && !hasLastArgumentSet) {
            throw new MissingFormatArgumentException("<"); //$NON-NLS-1$
        }

        if (null == args) {
            return null;
        }

        if (index >= args.length) {
            throw new MissingFormatArgumentException(token.getPlainText());
        }

        if (index == FormatToken.LAST_ARGUMENT_INDEX) {
            return lastArgument;
        }

        return args[index];
    }

    private static void closeOutputStream(OutputStream os) {
        if (null == os) {
            return;
        }
        try {
            os.close();

        } catch (IOException e) {
            // silently
        }
    }

    /*
     * Information about the format string of a specified argument, which
     * includes the conversion type, flags, width, precision and the argument
     * index as well as the plainText that contains the whole format string used
     * as the result for output if necessary. Besides, the string for flags is
     * recorded to construct corresponding FormatExceptions if necessary.
     */
    private static class FormatToken {

        static final int LAST_ARGUMENT_INDEX = -2;

        static final int UNSET = -1;

        static final int FLAGS_UNSET = 0;

        static final int DEFAULT_PRECISION = 6;

        static final int FLAG_MINUS = 1;

        static final int FLAG_SHARP = 1 << 1;

        static final int FLAG_ADD = 1 << 2;

        static final int FLAG_SPACE = 1 << 3;

        static final int FLAG_ZERO = 1 << 4;

        static final int FLAG_COMMA = 1 << 5;

        static final int FLAG_PARENTHESIS = 1 << 6;

        private static final int FLAGT_TYPE_COUNT = 6;

        private int formatStringStartIndex;

        private String plainText;

        private int argIndex = UNSET;

        private int flags = 0;

        private int width = UNSET;

        private int precision = UNSET;

        private StringBuilder strFlags = new StringBuilder(FLAGT_TYPE_COUNT);

        private char dateSuffix;// will be used in new feature.

        private char conversionType = (char) UNSET;

        boolean isPrecisionSet() {
            return precision != UNSET;
        }

        boolean isWidthSet() {
            return width != UNSET;
        }

        boolean isFlagSet(int flag) {
            return 0 != (flags & flag);
        }

        int getArgIndex() {
            return argIndex;
        }

        void setArgIndex(int index) {
            argIndex = index;
        }

        String getPlainText() {
            return plainText;
        }

        void setPlainText(String plainText) {
            this.plainText = plainText;
        }

        int getWidth() {
            return width;
        }

        void setWidth(int width) {
            this.width = width;
        }

        int getPrecision() {
            return precision;
        }

        void setPrecision(int precise) {
            this.precision = precise;
        }

        String getStrFlags() {
            return strFlags.toString();
        }

        int getFlags() {
            return flags;
        }

        void setFlags(int flags) {
            this.flags = flags;
        }

        /*
         * Sets qualified char as one of the flags. If the char is qualified,
         * sets it as a flag and returns true. Or else returns false.
         */
        boolean setFlag(char c) {
            int newFlag;
            switch (c) {
                case '-': {
                    newFlag = FLAG_MINUS;
                    break;
                }
                case '#': {
                    newFlag = FLAG_SHARP;
                    break;
                }
                case '+': {
                    newFlag = FLAG_ADD;
                    break;
                }
                case ' ': {
                    newFlag = FLAG_SPACE;
                    break;
                }
                case '0': {
                    newFlag = FLAG_ZERO;
                    break;
                }
                case ',': {
                    newFlag = FLAG_COMMA;
                    break;
                }
                case '(': {
                    newFlag = FLAG_PARENTHESIS;
                    break;
                }
                default:
                    return false;
            }
            if (0 != (flags & newFlag)) {
                throw new DuplicateFormatFlagsException(String.valueOf(c));
            }
            flags = (flags | newFlag);
            strFlags.append(c);
            return true;

        }

        int getFormatStringStartIndex() {
            return formatStringStartIndex;
        }

        void setFormatStringStartIndex(int index) {
            formatStringStartIndex = index;
        }

        char getConversionType() {
            return conversionType;
        }

        void setConversionType(char c) {
            conversionType = c;
        }

        char getDateSuffix() {
            return dateSuffix;
        }

        void setDateSuffix(char c) {
            dateSuffix = c;
        }

        boolean requireArgument() {
            return conversionType != '%' && conversionType != 'n';
        }
    }

    /*
     * Transforms the argument to the formatted string according to the format
     * information contained in the format token.
     */
    private static class Transformer {

        private Formatter formatter;

        private FormatToken formatToken;

        private Object arg;

        private Locale locale;

        private static String lineSeparator;

        private NumberFormat numberFormat;

        private DecimalFormatSymbols decimalFormatSymbols;

        private DateTimeUtil dateTimeUtil;

        Transformer(Formatter formatter, Locale locale) {
            this.formatter = formatter;
            this.locale = (null == locale ? Locale.US : locale);
        }

        private NumberFormat getNumberFormat() {
            if (null == numberFormat) {
                numberFormat = NumberFormat.getInstance(locale);
            }
            return numberFormat;
        }

        private DecimalFormatSymbols getDecimalFormatSymbols() {
            if (null == decimalFormatSymbols) {
                decimalFormatSymbols = new DecimalFormatSymbols(locale);
            }
            return decimalFormatSymbols;
        }

        /*
         * Gets the formatted string according to the format token and the
         * argument.
         */
        String transform(FormatToken token, Object argument) {

            /* init data member to print */
            this.formatToken = token;
            this.arg = argument;

            String result;
            switch (token.getConversionType()) {
                case 'B':
                case 'b': {
                    result = transformFromBoolean();
                    break;
                }
                case 'H':
                case 'h': {
                    result = transformFromHashCode();
                    break;
                }
                case 'S':
                case 's': {
                    result = transformFromString();
                    break;
                }
                case 'C':
                case 'c': {
                    result = transformFromCharacter();
                    break;
                }
                case 'd':
                case 'o':
                case 'x':
                case 'X': {
                    if (null == arg || arg instanceof BigInteger) {
                        result = transformFromBigInteger();
                    } else {
                        result = transformFromInteger();
                    }
                    break;
                }
                case 'e':
                case 'E':
                case 'g':
                case 'G':
                case 'f':
                case 'a':
                case 'A': {
                    result = transformFromFloat();
                    break;
                }
                case '%': {
                    result = transformFromPercent();
                    break;
                }
                case 'n': {
                    result = transfromFromLineSeparator();
                    break;
                }
                case 't':
                case 'T': {
                    result = transformFromDateTime();
                    break;
                }
                default: {
                    throw new UnknownFormatConversionException(String
                            .valueOf(token.getConversionType()));
                }
            }

            if (Character.isUpperCase(token.getConversionType())) {
                if (null != result) {
                    result = result.toUpperCase(Locale.US);
                }
            }
            return result;
        }

        /*
         * Transforms the Boolean argument to a formatted string.
         */
        private String transformFromBoolean() {
            StringBuilder result = new StringBuilder();
            int startIndex = 0;
            int flags = formatToken.getFlags();

            if (formatToken.isFlagSet(FormatToken.FLAG_MINUS)
                    && !formatToken.isWidthSet()) {
                throw new MissingFormatWidthException("-" //$NON-NLS-1$
                        + formatToken.getConversionType());
            }

            // only '-' is valid for flags
            if (FormatToken.FLAGS_UNSET != flags
                    && FormatToken.FLAG_MINUS != flags) {
                throw new FormatFlagsConversionMismatchException(formatToken
                        .getStrFlags(), formatToken.getConversionType());
            }

            if (null == arg) {
                result.append("false"); //$NON-NLS-1$
            } else if (arg instanceof Boolean) {
                result.append(arg);
            } else {
                result.append("true"); //$NON-NLS-1$
            }
            return padding(result, startIndex);
        }

        /*
         * Transforms the hashcode of the argument to a formatted string.
         */
        private String transformFromHashCode() {
            StringBuilder result = new StringBuilder();

            int startIndex = 0;
            int flags = formatToken.getFlags();

            if (formatToken.isFlagSet(FormatToken.FLAG_MINUS)
                    && !formatToken.isWidthSet()) {
                throw new MissingFormatWidthException("-" //$NON-NLS-1$
                        + formatToken.getConversionType());
            }

            // only '-' is valid for flags
            if (FormatToken.FLAGS_UNSET != flags
                    && FormatToken.FLAG_MINUS != flags) {
                throw new FormatFlagsConversionMismatchException(formatToken
                        .getStrFlags(), formatToken.getConversionType());
            }

            if (null == arg) {
                result.append("null"); //$NON-NLS-1$
            } else {
                result.append(Integer.toHexString(arg.hashCode()));
            }
            return padding(result, startIndex);
        }

        /*
         * Transforms the String to a formatted string.
         */
        private String transformFromString() {
            StringBuilder result = new StringBuilder();
            int startIndex = 0;
            int flags = formatToken.getFlags();

            if (formatToken.isFlagSet(FormatToken.FLAG_MINUS)
                    && !formatToken.isWidthSet()) {
                throw new MissingFormatWidthException("-" //$NON-NLS-1$
                        + formatToken.getConversionType());
            }

            if (arg instanceof Formattable) {
                int flag = 0;
                // only minus and sharp flag is valid
                if (FormatToken.FLAGS_UNSET != (flags & ~FormatToken.FLAG_MINUS & ~FormatToken.FLAG_SHARP)) {
                    throw new IllegalFormatFlagsException(formatToken
                            .getStrFlags());
                }
                if (formatToken.isFlagSet(FormatToken.FLAG_MINUS)) {
                    flag |= FormattableFlags.LEFT_JUSTIFY;
                }
                if (formatToken.isFlagSet(FormatToken.FLAG_SHARP)) {
                    flag |= FormattableFlags.ALTERNATE;
                }
                if (Character.isUpperCase(formatToken.getConversionType())) {
                    flag |= FormattableFlags.UPPERCASE;
                }
                ((Formattable) arg).formatTo(formatter, flag, formatToken
                        .getWidth(), formatToken.getPrecision());
                // all actions have been taken out in the
                // Formattable.formatTo, thus there is nothing to do, just
                // returns null, which tells the Parser to add nothing to the
                // output.
                return null;
            }
            // only '-' is valid for flags if the argument is not an
            // instance of Formattable
            if (FormatToken.FLAGS_UNSET != flags
                    && FormatToken.FLAG_MINUS != flags) {
                throw new FormatFlagsConversionMismatchException(formatToken
                        .getStrFlags(), formatToken.getConversionType());
            }

            result.append(arg);
            return padding(result, startIndex);
        }

        /*
         * Transforms the Character to a formatted string.
         */
        private String transformFromCharacter() {
            StringBuilder result = new StringBuilder();

            int startIndex = 0;
            int flags = formatToken.getFlags();

            if (formatToken.isFlagSet(FormatToken.FLAG_MINUS)
                    && !formatToken.isWidthSet()) {
                throw new MissingFormatWidthException("-" //$NON-NLS-1$
                        + formatToken.getConversionType());
            }

            // only '-' is valid for flags
            if (FormatToken.FLAGS_UNSET != flags
                    && FormatToken.FLAG_MINUS != flags) {
                throw new FormatFlagsConversionMismatchException(formatToken
                        .getStrFlags(), formatToken.getConversionType());
            }

            if (formatToken.isPrecisionSet()) {
                throw new IllegalFormatPrecisionException(formatToken
                        .getPrecision());
            }

            if (null == arg) {
                result.append("null"); //$NON-NLS-1$
            } else {
                if (arg instanceof Character) {
                    result.append(arg);
                } else if (arg instanceof Byte) {
                    byte b = ((Byte) arg).byteValue();
                    if (!Character.isValidCodePoint(b)) {
                        throw new IllegalFormatCodePointException(b);
                    }
                    result.append((char) b);
                } else if (arg instanceof Short) {
                    short s = ((Short) arg).shortValue();
                    if (!Character.isValidCodePoint(s)) {
                        throw new IllegalFormatCodePointException(s);
                    }
                    result.append((char) s);
                } else if (arg instanceof Integer) {
                    int codePoint = ((Integer) arg).intValue();
                    if (!Character.isValidCodePoint(codePoint)) {
                        throw new IllegalFormatCodePointException(codePoint);
                    }
                    result.append(String.valueOf(Character.toChars(codePoint)));
                } else {
                    // argument of other class is not acceptable.
                    throw new IllegalFormatConversionException(formatToken
                            .getConversionType(), arg.getClass());
                }
            }
            return padding(result, startIndex);
        }

        /*
         * Transforms percent to a formatted string. Only '-' is legal flag.
         * Precision is illegal.
         */
        private String transformFromPercent() {
            StringBuilder result = new StringBuilder("%"); //$NON-NLS-1$

            int startIndex = 0;
            int flags = formatToken.getFlags();

            if (formatToken.isFlagSet(FormatToken.FLAG_MINUS)
                    && !formatToken.isWidthSet()) {
                throw new MissingFormatWidthException("-" //$NON-NLS-1$
                        + formatToken.getConversionType());
            }

            if (FormatToken.FLAGS_UNSET != flags
                    && FormatToken.FLAG_MINUS != flags) {
                throw new FormatFlagsConversionMismatchException(formatToken
                        .getStrFlags(), formatToken.getConversionType());
            }
            if (formatToken.isPrecisionSet()) {
                throw new IllegalFormatPrecisionException(formatToken
                        .getPrecision());
            }
            return padding(result, startIndex);
        }

        /*
         * Transforms line separator to a formatted string. Any flag, the width
         * or the precision is illegal.
         */
        private String transfromFromLineSeparator() {
            if (formatToken.isPrecisionSet()) {
                throw new IllegalFormatPrecisionException(formatToken
                        .getPrecision());
            }

            if (formatToken.isWidthSet()) {
                throw new IllegalFormatWidthException(formatToken.getWidth());
            }

            int flags = formatToken.getFlags();
            if (FormatToken.FLAGS_UNSET != flags) {
                throw new IllegalFormatFlagsException(formatToken.getStrFlags());
            }

            if (null == lineSeparator) {
                lineSeparator = AccessController
                        .doPrivileged(new PrivilegedAction<String>() {

                            public String run() {
                                return System.getProperty("line.separator"); //$NON-NLS-1$
                            }
                        });
            }
            return lineSeparator;
        }

        /*
         * Pads characters to the formatted string.
         */
        private String padding(StringBuilder source, int startIndex) {
            int start = startIndex;
            boolean paddingRight = formatToken
                    .isFlagSet(FormatToken.FLAG_MINUS);
            char paddingChar = '\u0020';// space as padding char.
            if (formatToken.isFlagSet(FormatToken.FLAG_ZERO)) {
                if ('d' == formatToken.getConversionType()) {
                    paddingChar = getDecimalFormatSymbols().getZeroDigit();
                } else {
                    paddingChar = '0';
                }
            } else {
                // if padding char is space, always padding from the head
                // location.
                start = 0;
            }
            int width = formatToken.getWidth();
            int precision = formatToken.getPrecision();

            int length = source.length();
            if (precision >= 0) {
                length = Math.min(length, precision);
                source.delete(length, source.length());
            }
            if (width > 0) {
                width = Math.max(source.length(), width);
            }
            if (length >= width) {
                return source.toString();
            }

            char[] paddings = new char[width - length];
            Arrays.fill(paddings, paddingChar);
            String insertString = new String(paddings);

            if (paddingRight) {
                source.append(insertString);
            } else {
                source.insert(start, insertString);
            }
            return source.toString();
        }

        /*
         * Transforms the Integer to a formatted string.
         */
        private String transformFromInteger() {
            int startIndex = 0;
            boolean isNegative = false;
            StringBuilder result = new StringBuilder();
            char currentConversionType = formatToken.getConversionType();
            long value;

            if (formatToken.isFlagSet(FormatToken.FLAG_MINUS)
                    || formatToken.isFlagSet(FormatToken.FLAG_ZERO)) {
                if (!formatToken.isWidthSet()) {
                    throw new MissingFormatWidthException(formatToken
                            .getStrFlags());
                }
            }
            // Combination of '+' & ' ' is illegal.
            if (formatToken.isFlagSet(FormatToken.FLAG_ADD)
                    && formatToken.isFlagSet(FormatToken.FLAG_SPACE)) {
                throw new IllegalFormatFlagsException(formatToken.getStrFlags());
            }
            if (formatToken.isPrecisionSet()) {
                throw new IllegalFormatPrecisionException(formatToken
                        .getPrecision());
            }
            if (arg instanceof Long) {
                value = ((Long) arg).longValue();
            } else if (arg instanceof Integer) {
                value = ((Integer) arg).longValue();
            } else if (arg instanceof Short) {
                value = ((Short) arg).longValue();
            } else if (arg instanceof Byte) {
                value = ((Byte) arg).longValue();
            } else {
                throw new IllegalFormatConversionException(formatToken
                        .getConversionType(), arg.getClass());
            }
            if ('d' != currentConversionType) {
                if (formatToken.isFlagSet(FormatToken.FLAG_ADD)
                        || formatToken.isFlagSet(FormatToken.FLAG_SPACE)
                        || formatToken.isFlagSet(FormatToken.FLAG_COMMA)
                        || formatToken.isFlagSet(FormatToken.FLAG_PARENTHESIS)) {
                    throw new FormatFlagsConversionMismatchException(
                            formatToken.getStrFlags(), formatToken
                                    .getConversionType());
                }
            }

            if (formatToken.isFlagSet(FormatToken.FLAG_SHARP)) {
                if ('d' == currentConversionType) {
                    throw new FormatFlagsConversionMismatchException(
                            formatToken.getStrFlags(), formatToken
                                    .getConversionType());
                } else if ('o' == currentConversionType) {
                    result.append("0"); //$NON-NLS-1$
                    startIndex += 1;
                } else {
                    result.append("0x"); //$NON-NLS-1$
                    startIndex += 2;
                }
            }

            if (formatToken.isFlagSet(FormatToken.FLAG_MINUS)
                    && formatToken.isFlagSet(FormatToken.FLAG_ZERO)) {
                throw new IllegalFormatFlagsException(formatToken.getStrFlags());
            }

            if (value < 0) {
                isNegative = true;
            }

            if ('d' == currentConversionType) {
                NumberFormat numberFormat = getNumberFormat();
                if (formatToken.isFlagSet(FormatToken.FLAG_COMMA)) {
                    numberFormat.setGroupingUsed(true);
                } else {
                    numberFormat.setGroupingUsed(false);
                }
                result.append(numberFormat.format(arg));
            } else {
                long BYTE_MASK = 0x00000000000000FFL;
                long SHORT_MASK = 0x000000000000FFFFL;
                long INT_MASK = 0x00000000FFFFFFFFL;
                if (isNegative) {
                    if (arg instanceof Byte) {
                        value &= BYTE_MASK;
                    } else if (arg instanceof Short) {
                        value &= SHORT_MASK;
                    } else if (arg instanceof Integer) {
                        value &= INT_MASK;
                    }
                }
                if ('o' == currentConversionType) {
                    result.append(Long.toOctalString(value));
                } else {
                    result.append(Long.toHexString(value));
                }
                isNegative = false;
            }

            if (!isNegative) {
                if (formatToken.isFlagSet(FormatToken.FLAG_ADD)) {
                    result.insert(0, '+');
                    startIndex += 1;
                }
                if (formatToken.isFlagSet(FormatToken.FLAG_SPACE)) {
                    result.insert(0, ' ');
                    startIndex += 1;
                }
            }

            /* pad paddingChar to the output */
            if (isNegative
                    && formatToken.isFlagSet(FormatToken.FLAG_PARENTHESIS)) {
                result = wrapParentheses(result);
                return result.toString();

            }
            if (isNegative && formatToken.isFlagSet(FormatToken.FLAG_ZERO)) {
                startIndex++;
            }
            return padding(result, startIndex);
        }

        /*
         * add () to the output,if the value is negative and
         * formatToken.FLAG_PARENTHESIS is set. 'result' is used as an in-out
         * parameter.
         */
        private StringBuilder wrapParentheses(StringBuilder result) {
            // delete the '-'
            result.deleteCharAt(0);
            result.insert(0, '(');
            if (formatToken.isFlagSet(FormatToken.FLAG_ZERO)) {
                formatToken.setWidth(formatToken.getWidth() - 1);
                padding(result, 1);
                result.append(')');
            } else {
                result.append(')');
                padding(result, 0);
            }
            return result;
        }

        private String transformFromSpecialNumber() {
            String source = null;

            if (!(arg instanceof Number) || arg instanceof BigDecimal) {
                return null;
            }

            Number number = (Number) arg;
            double d = number.doubleValue();
            if (Double.isNaN(d)) {
                source = "NaN"; //$NON-NLS-1$
            } else if (Double.isInfinite(d)) {
                if (d >= 0) {
                    if (formatToken.isFlagSet(FormatToken.FLAG_ADD)) {
                        source = "+Infinity"; //$NON-NLS-1$
                    } else if (formatToken.isFlagSet(FormatToken.FLAG_SPACE)) {
                        source = " Infinity"; //$NON-NLS-1$
                    } else {
                        source = "Infinity"; //$NON-NLS-1$
                    }
                } else {
                    if (formatToken.isFlagSet(FormatToken.FLAG_PARENTHESIS)) {
                        source = "(Infinity)"; //$NON-NLS-1$
                    } else {
                        source = "-Infinity"; //$NON-NLS-1$
                    }
                }
            }

            if (null != source) {
                formatToken.setPrecision(FormatToken.UNSET);
                formatToken.setFlags(formatToken.getFlags()
                        & (~FormatToken.FLAG_ZERO));
                source = padding(new StringBuilder(source), 0);
            }
            return source;
        }

        private String transformFromNull() {
            formatToken.setFlags(formatToken.getFlags()
                    & (~FormatToken.FLAG_ZERO));
            return padding(new StringBuilder("null"), 0); //$NON-NLS-1$
        }

        /*
         * Transforms a BigInteger to a formatted string.
         */
        private String transformFromBigInteger() {
            int startIndex = 0;
            boolean isNegative = false;
            StringBuilder result = new StringBuilder();
            BigInteger bigInt = (BigInteger) arg;
            char currentConversionType = formatToken.getConversionType();

            if (formatToken.isFlagSet(FormatToken.FLAG_MINUS)
                    || formatToken.isFlagSet(FormatToken.FLAG_ZERO)) {
                if (!formatToken.isWidthSet()) {
                    throw new MissingFormatWidthException(formatToken
                            .getStrFlags());
                }
            }

            // Combination of '+' & ' ' is illegal.
            if (formatToken.isFlagSet(FormatToken.FLAG_ADD)
                    && formatToken.isFlagSet(FormatToken.FLAG_SPACE)) {
                throw new IllegalFormatFlagsException(formatToken.getStrFlags());
            }

            // Combination of '-' & '0' is illegal.
            if (formatToken.isFlagSet(FormatToken.FLAG_ZERO)
                    && formatToken.isFlagSet(FormatToken.FLAG_MINUS)) {
                throw new IllegalFormatFlagsException(formatToken.getStrFlags());
            }

            if (formatToken.isPrecisionSet()) {
                throw new IllegalFormatPrecisionException(formatToken
                        .getPrecision());
            }

            if ('d' != currentConversionType
                    && formatToken.isFlagSet(FormatToken.FLAG_COMMA)) {
                throw new FormatFlagsConversionMismatchException(formatToken
                        .getStrFlags(), currentConversionType);
            }

            if (formatToken.isFlagSet(FormatToken.FLAG_SHARP)
                    && 'd' == currentConversionType) {
                throw new FormatFlagsConversionMismatchException(formatToken
                        .getStrFlags(), currentConversionType);
            }

            if (null == bigInt) {
                return transformFromNull();
            }

            isNegative = (bigInt.compareTo(BigInteger.ZERO) < 0);

            if ('d' == currentConversionType) {
                NumberFormat numberFormat = getNumberFormat();
                boolean readableName = formatToken
                        .isFlagSet(FormatToken.FLAG_COMMA);
                numberFormat.setGroupingUsed(readableName);
                result.append(numberFormat.format(bigInt));
            } else if ('o' == currentConversionType) {
                // convert BigInteger to a string presentation using radix 8
                result.append(bigInt.toString(8));
            } else {
                // convert BigInteger to a string presentation using radix 16
                result.append(bigInt.toString(16));
            }
            if (formatToken.isFlagSet(FormatToken.FLAG_SHARP)) {
                startIndex = isNegative ? 1 : 0;
                if ('o' == currentConversionType) {
                    result.insert(startIndex, "0"); //$NON-NLS-1$
                    startIndex += 1;
                } else if ('x' == currentConversionType
                        || 'X' == currentConversionType) {
                    result.insert(startIndex, "0x"); //$NON-NLS-1$
                    startIndex += 2;
                }
            }

            if (!isNegative) {
                if (formatToken.isFlagSet(FormatToken.FLAG_ADD)) {
                    result.insert(0, '+');
                    startIndex += 1;
                }
                if (formatToken.isFlagSet(FormatToken.FLAG_SPACE)) {
                    result.insert(0, ' ');
                    startIndex += 1;
                }
            }

            /* pad paddingChar to the output */
            if (isNegative
                    && formatToken.isFlagSet(FormatToken.FLAG_PARENTHESIS)) {
                result = wrapParentheses(result);
                return result.toString();

            }
            if (isNegative && formatToken.isFlagSet(FormatToken.FLAG_ZERO)) {
                startIndex++;
            }
            return padding(result, startIndex);
        }

        /*
         * Transforms a Float,Double or BigDecimal to a formatted string.
         */
        private String transformFromFloat() {
            StringBuilder result = new StringBuilder();
            int startIndex = 0;
            char currentConversionType = formatToken.getConversionType();

            if (formatToken.isFlagSet(FormatToken.FLAG_MINUS
                    | FormatToken.FLAG_ZERO)) {
                if (!formatToken.isWidthSet()) {
                    throw new MissingFormatWidthException(formatToken
                            .getStrFlags());
                }
            }

            if (formatToken.isFlagSet(FormatToken.FLAG_ADD)
                    && formatToken.isFlagSet(FormatToken.FLAG_SPACE)) {
                throw new IllegalFormatFlagsException(formatToken.getStrFlags());
            }

            if (formatToken.isFlagSet(FormatToken.FLAG_MINUS)
                    && formatToken.isFlagSet(FormatToken.FLAG_ZERO)) {
                throw new IllegalFormatFlagsException(formatToken.getStrFlags());
            }

            if ('e' == Character.toLowerCase(currentConversionType)) {
                if (formatToken.isFlagSet(FormatToken.FLAG_COMMA)) {
                    throw new FormatFlagsConversionMismatchException(
                            formatToken.getStrFlags(), currentConversionType);
                }
            }

            if ('g' == Character.toLowerCase(currentConversionType)) {
                if (formatToken.isFlagSet(FormatToken.FLAG_SHARP)) {
                    throw new FormatFlagsConversionMismatchException(
                            formatToken.getStrFlags(), currentConversionType);
                }
            }

            if ('a' == Character.toLowerCase(currentConversionType)) {
                if (formatToken.isFlagSet(FormatToken.FLAG_COMMA)
                        || formatToken.isFlagSet(FormatToken.FLAG_PARENTHESIS)) {
                    throw new FormatFlagsConversionMismatchException(
                            formatToken.getStrFlags(), currentConversionType);
                }
            }

            if (null == arg) {
                return transformFromNull();
            }

            if (!(arg instanceof Float || arg instanceof Double || arg instanceof BigDecimal)) {
                throw new IllegalFormatConversionException(
                        currentConversionType, arg.getClass());
            }

            String specialNumberResult = transformFromSpecialNumber();
            if (null != specialNumberResult) {
                return specialNumberResult;
            }

            if ('a' != Character.toLowerCase(currentConversionType)) {
                formatToken
                        .setPrecision(formatToken.isPrecisionSet() ? formatToken
                                .getPrecision()
                                : FormatToken.DEFAULT_PRECISION);
            }
            // output result
            FloatUtil floatUtil = new FloatUtil(result, formatToken,
                    (DecimalFormat) NumberFormat.getInstance(locale), arg);
            floatUtil.transform(formatToken, result);

            formatToken.setPrecision(FormatToken.UNSET);

            if (getDecimalFormatSymbols().getMinusSign() == result.charAt(0)) {
                if (formatToken.isFlagSet(FormatToken.FLAG_PARENTHESIS)) {
                    result = wrapParentheses(result);
                    return result.toString();
                }
            } else {
                if (formatToken.isFlagSet(FormatToken.FLAG_SPACE)) {
                    result.insert(0, ' ');
                    startIndex++;
                }
                if (formatToken.isFlagSet(FormatToken.FLAG_ADD)) {
                    result.insert(0, floatUtil.getAddSign());
                    startIndex++;
                }
            }

            char firstChar = result.charAt(0);
            if (formatToken.isFlagSet(FormatToken.FLAG_ZERO)
                    && (firstChar == floatUtil.getAddSign() || firstChar == floatUtil
                            .getMinusSign())) {
                startIndex = 1;
            }

            if ('a' == Character.toLowerCase(currentConversionType)) {
                startIndex += 2;
            }
            return padding(result, startIndex);
        }

        /*
         * Transforms a Date to a formatted string.
         */
        private String transformFromDateTime() {
            int startIndex = 0;
            char currentConversionType = formatToken.getConversionType();

            if (formatToken.isPrecisionSet()) {
                throw new IllegalFormatPrecisionException(formatToken
                        .getPrecision());
            }

            if (formatToken.isFlagSet(FormatToken.FLAG_SHARP)) {
                throw new FormatFlagsConversionMismatchException(formatToken
                        .getStrFlags(), currentConversionType);
            }

            if (formatToken.isFlagSet(FormatToken.FLAG_MINUS)
                    && FormatToken.UNSET == formatToken.getWidth()) {
                throw new MissingFormatWidthException("-" //$NON-NLS-1$
                        + currentConversionType);
            }

            if (null == arg) {
                return transformFromNull();
            }

            Calendar calendar;
            if (arg instanceof Calendar) {
                calendar = (Calendar) arg;
            } else {
                Date date = null;
                if (arg instanceof Long) {
                    date = new Date(((Long) arg).longValue());
                } else if (arg instanceof Date) {
                    date = (Date) arg;
                } else {
                    throw new IllegalFormatConversionException(
                            currentConversionType, arg.getClass());
                }
                calendar = Calendar.getInstance(locale);
                calendar.setTime(date);
            }

            if (null == dateTimeUtil) {
                dateTimeUtil = new DateTimeUtil(locale);
            }
            StringBuilder result = new StringBuilder();
            // output result
            dateTimeUtil.transform(formatToken, calendar, result);
            return padding(result, startIndex);
        }
    }

    private static class FloatUtil {
        private StringBuilder result;

        private DecimalFormat decimalFormat;

        private FormatToken formatToken;

        private Object argument;

        private char minusSign;

        FloatUtil(StringBuilder result, FormatToken formatToken,
                DecimalFormat decimalFormat, Object argument) {
            this.result = result;
            this.formatToken = formatToken;
            this.decimalFormat = decimalFormat;
            this.argument = argument;
            this.minusSign = decimalFormat.getDecimalFormatSymbols()
                    .getMinusSign();
        }

        void transform(FormatToken aFormatToken, StringBuilder aResult) {
            this.result = aResult;
            this.formatToken = aFormatToken;
            switch (formatToken.getConversionType()) {
                case 'e':
                case 'E': {
                    transform_e();
                    break;
                }
                case 'f': {
                    transform_f();
                    break;
                }
                case 'g':
                case 'G': {
                    transform_g();
                    break;
                }
                case 'a':
                case 'A': {
                    transform_a();
                    break;
                }
                default: {
                    throw new UnknownFormatConversionException(String
                            .valueOf(formatToken.getConversionType()));
                }
            }
        }

        char getMinusSign() {
            return minusSign;
        }

        char getAddSign() {
            return '+';
        }

        void transform_e() {
            StringBuilder pattern = new StringBuilder();
            pattern.append('0');
            if (formatToken.getPrecision() > 0) {
                pattern.append('.');
                char[] zeros = new char[formatToken.getPrecision()];
                Arrays.fill(zeros, '0');
                pattern.append(zeros);
            }
            pattern.append('E');
            pattern.append("+00"); //$NON-NLS-1$
            decimalFormat.applyPattern(pattern.toString());
            String formattedString = decimalFormat.format(argument);
            result.append(formattedString.replace('E', 'e'));

            // if the flag is sharp and decimal seperator is always given
            // out.
            if (formatToken.isFlagSet(FormatToken.FLAG_SHARP)
                    && 0 == formatToken.getPrecision()) {
                int indexOfE = result.indexOf("e"); //$NON-NLS-1$
                char dot = decimalFormat.getDecimalFormatSymbols()
                        .getDecimalSeparator();
                result.insert(indexOfE, dot);
            }
        }

        void transform_g() {
            int precision = formatToken.getPrecision();
            precision = (0 == precision ? 1 : precision);
            formatToken.setPrecision(precision);

            if (0.0 == ((Number) argument).doubleValue()) {
                precision--;
                formatToken.setPrecision(precision);
                transform_f();
                return;
            }

            boolean requireScientificRepresentation = true;
            double d = ((Number) argument).doubleValue();
            d = Math.abs(d);
            long l = Math.round(d);

            if (l >= 1) {
                if (l < Math.pow(10, precision)) {
                    requireScientificRepresentation = false;
                    precision -= String.valueOf(l).length();
                    precision = precision < 0 ? 0 : precision;
                    l = Math.round(d * Math.pow(10, precision + 1));
                    if (String.valueOf(l).length() <= formatToken
                            .getPrecision()) {
                        precision++;
                    }
                    formatToken.setPrecision(precision);
                }

            } else {
                l = Math.round(d * Math.pow(10, 4));
                if (l >= 1) {
                    requireScientificRepresentation = false;
                    precision += 4 - String.valueOf(l).length();
                    l = Math.round(d * Math.pow(10, precision + 1));
                    if (String.valueOf(l).length() <= formatToken
                            .getPrecision()) {
                        precision++;
                    }
                    l = Math.round(d * Math.pow(10, precision));
                    if (l < Math.pow(10, precision - 4)) {
                        requireScientificRepresentation = true;
                    } else {
                        formatToken.setPrecision(precision);
                    }
                }
            }
            if (requireScientificRepresentation) {
                precision = formatToken.getPrecision();
                precision--;
                formatToken.setPrecision(precision);
                transform_e();
            } else {
                transform_f();
            }

        }

        void transform_f() {
            StringBuilder pattern = new StringBuilder();
            if (formatToken.isFlagSet(FormatToken.FLAG_COMMA)) {
                pattern.append(',');
                int groupingSize = decimalFormat.getGroupingSize();
                if (groupingSize > 1) {
                    char[] sharps = new char[groupingSize - 1];
                    Arrays.fill(sharps, '#');
                    pattern.append(sharps);
                }
            }

            pattern.append(0);

            if (formatToken.getPrecision() > 0) {
                pattern.append('.');
                char[] zeros = new char[formatToken.getPrecision()];
                Arrays.fill(zeros, '0');
                pattern.append(zeros);
            }
            decimalFormat.applyPattern(pattern.toString());
            result.append(decimalFormat.format(argument));
            // if the flag is sharp and decimal seperator is always given
            // out.
            if (formatToken.isFlagSet(FormatToken.FLAG_SHARP)
                    && 0 == formatToken.getPrecision()) {
                char dot = decimalFormat.getDecimalFormatSymbols()
                        .getDecimalSeparator();
                result.append(dot);
            }

        }

        void transform_a() {
            char currentConversionType = formatToken.getConversionType();

            if (argument instanceof Float) {
                Float F = (Float) argument;
                result.append(Float.toHexString(F.floatValue()));

            } else if (argument instanceof Double) {
                Double D = (Double) argument;
                result.append(Double.toHexString(D.doubleValue()));
            } else {
                // BigInteger is not supported.
                throw new IllegalFormatConversionException(
                        currentConversionType, argument.getClass());
            }

            if (!formatToken.isPrecisionSet()) {
                return;
            }

            int precision = formatToken.getPrecision();
            precision = (0 == precision ? 1 : precision);
            int indexOfFirstFracitoanlDigit = result.indexOf(".") + 1; //$NON-NLS-1$
            int indexOfP = result.indexOf("p"); //$NON-NLS-1$
            int fractionalLength = indexOfP - indexOfFirstFracitoanlDigit;

            if (fractionalLength == precision) {
                return;
            }

            if (fractionalLength < precision) {
                char zeros[] = new char[precision - fractionalLength];
                Arrays.fill(zeros, '0');
                result.insert(indexOfP, zeros);
                return;
            }
            result.delete(indexOfFirstFracitoanlDigit + precision, indexOfP);
        }
    }

    private static class DateTimeUtil {
        private Calendar calendar;

        private Locale locale;

        private StringBuilder result;

        private DateFormatSymbols dateFormatSymbols;

        DateTimeUtil(Locale locale) {
            this.locale = locale;
        }

        void transform(FormatToken formatToken, Calendar aCalendar,
                StringBuilder aResult) {
            this.result = aResult;
            this.calendar = aCalendar;
            char suffix = formatToken.getDateSuffix();

            switch (suffix) {
                case 'H': {
                    transform_H();
                    break;
                }
                case 'I': {
                    transform_I();
                    break;
                }
                case 'M': {
                    transform_M();
                    break;
                }
                case 'S': {
                    transform_S();
                    break;
                }
                case 'L': {
                    transform_L();
                    break;
                }
                case 'N': {
                    transform_N();
                    break;
                }
                case 'k': {
                    transform_k();
                    break;
                }
                case 'l': {
                    transform_l();
                    break;
                }
                case 'p': {
                    transform_p(true);
                    break;
                }
                case 's': {
                    transform_s();
                    break;
                }
                case 'z': {
                    transform_z();
                    break;
                }
                case 'Z': {
                    transform_Z();
                    break;
                }
                case 'Q': {
                    transform_Q();
                    break;
                }
                case 'B': {
                    transform_B();
                    break;
                }
                case 'b':
                case 'h': {
                    transform_b();
                    break;
                }
                case 'A': {
                    transform_A();
                    break;
                }
                case 'a': {
                    transform_a();
                    break;
                }
                case 'C': {
                    transform_C();
                    break;
                }
                case 'Y': {
                    transform_Y();
                    break;
                }
                case 'y': {
                    transform_y();
                    break;
                }
                case 'j': {
                    transform_j();
                    break;
                }
                case 'm': {
                    transform_m();
                    break;
                }
                case 'd': {
                    transform_d();
                    break;
                }
                case 'e': {
                    transform_e();
                    break;
                }
                case 'R': {
                    transform_R();
                    break;
                }

                case 'T': {
                    transform_T();
                    break;
                }
                case 'r': {
                    transform_r();
                    break;
                }
                case 'D': {
                    transform_D();
                    break;
                }
                case 'F': {
                    transform_F();
                    break;
                }
                case 'c': {
                    transform_c();
                    break;
                }
                default: {
                    throw new UnknownFormatConversionException(String
                            .valueOf(formatToken.getConversionType())
                            + formatToken.getDateSuffix());
                }
            }
        }

        private void transform_e() {
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            result.append(day);
        }

        private void transform_d() {
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            result.append(paddingZeros(day, 2));
        }

        private void transform_m() {
            int month = calendar.get(Calendar.MONTH);
            // The returned month starts from zero, which needs to be
            // incremented by 1.
            month++;
            result.append(paddingZeros(month, 2));
        }

        private void transform_j() {
            int day = calendar.get(Calendar.DAY_OF_YEAR);
            result.append(paddingZeros(day, 3));
        }

        private void transform_y() {
            int year = calendar.get(Calendar.YEAR);
            year %= 100;
            result.append(paddingZeros(year, 2));
        }

        private void transform_Y() {
            int year = calendar.get(Calendar.YEAR);
            result.append(paddingZeros(year, 4));
        }

        private void transform_C() {
            int year = calendar.get(Calendar.YEAR);
            year /= 100;
            result.append(paddingZeros(year, 2));
        }

        private void transform_a() {
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            result.append(getDateFormatSymbols().getShortWeekdays()[day]);
        }

        private void transform_A() {
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            result.append(getDateFormatSymbols().getWeekdays()[day]);
        }

        private void transform_b() {
            int month = calendar.get(Calendar.MONTH);
            result.append(getDateFormatSymbols().getShortMonths()[month]);
        }

        private void transform_B() {
            int month = calendar.get(Calendar.MONTH);
            result.append(getDateFormatSymbols().getMonths()[month]);
        }

        private void transform_Q() {
            long milliSeconds = calendar.getTimeInMillis();
            result.append(milliSeconds);
        }

        private void transform_s() {
            long milliSeconds = calendar.getTimeInMillis();
            milliSeconds /= 1000;
            result.append(milliSeconds);
        }

        private void transform_Z() {
            TimeZone timeZone = calendar.getTimeZone();
            result
                    .append(timeZone.getDisplayName(true, TimeZone.SHORT,
                            locale));
        }

        private void transform_z() {
            int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
            zoneOffset /= 3600000;
            zoneOffset *= 100;
            if (zoneOffset >= 0) {
                result.append('+');
            }
            result.append(paddingZeros(zoneOffset, 4));
        }

        private void transform_p(boolean isLowerCase) {
            int i = calendar.get(Calendar.AM_PM);
            String s = getDateFormatSymbols().getAmPmStrings()[i];
            if (isLowerCase) {
                s = s.toLowerCase(locale);
            }
            result.append(s);
        }

        private void transform_N() {
            // TODO System.nanoTime();
            long nanosecond = calendar.get(Calendar.MILLISECOND) * 1000000L;
            result.append(paddingZeros(nanosecond, 9));
        }

        private void transform_L() {
            int millisecond = calendar.get(Calendar.MILLISECOND);
            result.append(paddingZeros(millisecond, 3));
        }

        private void transform_S() {
            int second = calendar.get(Calendar.SECOND);
            result.append(paddingZeros(second, 2));
        }

        private void transform_M() {
            int minute = calendar.get(Calendar.MINUTE);
            result.append(paddingZeros(minute, 2));
        }

        private void transform_l() {
            int hour = calendar.get(Calendar.HOUR);
            if (0 == hour) {
                hour = 12;
            }
            result.append(hour);
        }

        private void transform_k() {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            result.append(hour);
        }

        private void transform_I() {
            int hour = calendar.get(Calendar.HOUR);
            if (0 == hour) {
                hour = 12;
            }
            result.append(paddingZeros(hour, 2));
        }

        private void transform_H() {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            result.append(paddingZeros(hour, 2));
        }

        private void transform_R() {
            transform_H();
            result.append(':');
            transform_M();
        }

        private void transform_T() {
            transform_H();
            result.append(':');
            transform_M();
            result.append(':');
            transform_S();
        }

        private void transform_r() {
            transform_I();
            result.append(':');
            transform_M();
            result.append(':');
            transform_S();
            result.append(' ');
            transform_p(false);
        }

        private void transform_D() {
            transform_m();
            result.append('/');
            transform_d();
            result.append('/');
            transform_y();
        }

        private void transform_F() {
            transform_Y();
            result.append('-');
            transform_m();
            result.append('-');
            transform_d();
        }

        private void transform_c() {
            transform_a();
            result.append(' ');
            transform_b();
            result.append(' ');
            transform_d();
            result.append(' ');
            transform_T();
            result.append(' ');
            transform_Z();
            result.append(' ');
            transform_Y();
        }

        private static String paddingZeros(long number, int length) {
            int len = length;
            StringBuilder result = new StringBuilder();
            result.append(number);
            int startIndex = 0;
            if (number < 0) {
                len++;
                startIndex = 1;
            }
            len -= result.length();
            if (len > 0) {
                char[] zeros = new char[len];
                Arrays.fill(zeros, '0');
                result.insert(startIndex, zeros);
            }
            return result.toString();
        }

        private DateFormatSymbols getDateFormatSymbols() {
            if (null == dateFormatSymbols) {
                dateFormatSymbols = new DateFormatSymbols(locale);
            }
            return dateFormatSymbols;
        }
    }

    private static class ParserStateMachine {

        private static final char EOS = (char) -1;

        private static final int EXIT_STATE = 0;

        private static final int ENTRY_STATE = 1;

        private static final int START_CONVERSION_STATE = 2;

        private static final int FLAGS_STATE = 3;

        private static final int WIDTH_STATE = 4;

        private static final int PRECISION_STATE = 5;

        private static final int CONVERSION_TYPE_STATE = 6;

        private static final int SUFFIX_STATE = 7;

        private FormatToken token;

        private int state = ENTRY_STATE;

        private char currentChar = 0;

        private CharBuffer format = null;

        ParserStateMachine(CharBuffer format) {
            this.format = format;
        }

        void reset() {
            this.currentChar = (char) FormatToken.UNSET;
            this.state = ENTRY_STATE;
            this.token = null;
        }

        /*
         * Gets the information about the current format token. Information is
         * recorded in the FormatToken returned and the position of the stream
         * for the format string will be advanced till the next format token.
         */
        FormatToken getNextFormatToken() {
            token = new FormatToken();
            token.setFormatStringStartIndex(format.position());

            // FINITE AUTOMATIC MACHINE
            while (true) {

                if (ParserStateMachine.EXIT_STATE != state) {
                    // exit state does not need to get next char
                    currentChar = getNextFormatChar();
                    if (EOS == currentChar
                            && ParserStateMachine.ENTRY_STATE != state) {
                        throw new UnknownFormatConversionException(
                                getFormatString());
                    }
                }

                switch (state) {
                    // exit state
                    case ParserStateMachine.EXIT_STATE: {
                        process_EXIT_STATE();
                        return token;
                    }
                        // plain text state, not yet applied converter
                    case ParserStateMachine.ENTRY_STATE: {
                        process_ENTRY_STATE();
                        break;
                    }
                        // begins converted string
                    case ParserStateMachine.START_CONVERSION_STATE: {
                        process_START_CONVERSION_STATE();
                        break;
                    }
                    case ParserStateMachine.FLAGS_STATE: {
                        process_FlAGS_STATE();
                        break;
                    }
                    case ParserStateMachine.WIDTH_STATE: {
                        process_WIDTH_STATE();
                        break;
                    }
                    case ParserStateMachine.PRECISION_STATE: {
                        process_PRECISION_STATE();
                        break;
                    }
                    case ParserStateMachine.CONVERSION_TYPE_STATE: {
                        process_CONVERSION_TYPE_STATE();
                        break;
                    }
                    case ParserStateMachine.SUFFIX_STATE: {
                        process_SUFFIX_STATE();
                        break;
                    }
                }
            }
        }

        /*
         * Gets next char from the format string.
         */
        private char getNextFormatChar() {
            if (format.hasRemaining()) {
                return format.get();
            }
            return EOS;
        }

        private String getFormatString() {
            int end = format.position();
            format.rewind();
            String formatString = format.subSequence(
                    token.getFormatStringStartIndex(), end).toString();
            format.position(end);
            return formatString;
        }

        private void process_ENTRY_STATE() {
            if (EOS == currentChar) {
                state = ParserStateMachine.EXIT_STATE;
            } else if ('%' == currentChar) {
                // change to conversion type state
                state = START_CONVERSION_STATE;
            }
            // else remains in ENTRY_STATE
        }

        private void process_START_CONVERSION_STATE() {
            if (Character.isDigit(currentChar)) {
                int position = format.position() - 1;
                int number = parseInt(format);
                char nextChar = 0;
                if (format.hasRemaining()) {
                    nextChar = format.get();
                }
                if ('$' == nextChar) {
                    // the digital sequence stands for the argument
                    // index.
                    int argIndex = number;
                    // k$ stands for the argument whose index is k-1 except that
                    // 0$ and 1$ both stands for the first element.
                    if (argIndex > 0) {
                        token.setArgIndex(argIndex - 1);
                    } else if (argIndex == FormatToken.UNSET) {
                        throw new MissingFormatArgumentException(
                                getFormatString());
                    }
                    state = FLAGS_STATE;
                } else {
                    // the digital zero stands for one format flag.
                    if ('0' == currentChar) {
                        state = FLAGS_STATE;
                        format.position(position);
                    } else {
                        // the digital sequence stands for the width.
                        state = WIDTH_STATE;
                        // do not get the next char.
                        format.position(format.position() - 1);
                        token.setWidth(number);
                    }
                }
                currentChar = nextChar;
            } else if ('<' == currentChar) {
                state = FLAGS_STATE;
                token.setArgIndex(FormatToken.LAST_ARGUMENT_INDEX);
            } else {
                state = FLAGS_STATE;
                // do not get the next char.
                format.position(format.position() - 1);
            }

        }

        private void process_FlAGS_STATE() {
            if (token.setFlag(currentChar)) {
                // remains in FLAGS_STATE
            } else if (Character.isDigit(currentChar)) {
                token.setWidth(parseInt(format));
                state = WIDTH_STATE;
            } else if ('.' == currentChar) {
                state = PRECISION_STATE;
            } else {
                state = CONVERSION_TYPE_STATE;
                // do not get the next char.
                format.position(format.position() - 1);
            }
        }

        private void process_WIDTH_STATE() {
            if ('.' == currentChar) {
                state = PRECISION_STATE;
            } else {
                state = CONVERSION_TYPE_STATE;
                // do not get the next char.
                format.position(format.position() - 1);
            }
        }

        private void process_PRECISION_STATE() {
            if (Character.isDigit(currentChar)) {
                token.setPrecision(parseInt(format));
            } else {
                // the precision is required but not given by the
                // format string.
                throw new UnknownFormatConversionException(getFormatString());
            }
            state = CONVERSION_TYPE_STATE;
        }

        private void process_CONVERSION_TYPE_STATE() {
            token.setConversionType(currentChar);
            if ('t' == currentChar || 'T' == currentChar) {
                state = SUFFIX_STATE;
            } else {
                state = EXIT_STATE;
            }

        }

        private void process_SUFFIX_STATE() {
            token.setDateSuffix(currentChar);
            state = EXIT_STATE;
        }

        private void process_EXIT_STATE() {
            token.setPlainText(getFormatString());
        }

        /*
         * Parses integer value from the given buffer
         */
        private int parseInt(CharBuffer buffer) {
            int start = buffer.position() - 1;
            int end = buffer.limit();
            while (buffer.hasRemaining()) {
                if (!Character.isDigit(buffer.get())) {
                    end = buffer.position() - 1;
                    break;
                }
            }
            buffer.position(0);
            String intStr = buffer.subSequence(start, end).toString();
            buffer.position(end);
            try {
                return Integer.parseInt(intStr);
            } catch (NumberFormatException e) {
                return FormatToken.UNSET;
            }
        }
    }
}
