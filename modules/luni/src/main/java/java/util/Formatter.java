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
import java.nio.charset.Charset;

import org.apache.harmony.luni.util.NotYetImplementedException;

/**
 * Formatter provides the method to give out formatted string just like the
 * printf-style. Layout,alignment and other format flags are provided to format
 * numeric,string and datetime as well as locale-specified formats applied.
 * Besides primitive types, formatter also support some java object types such
 * as BigInteger,BigDecimal and Calendar. Customized formatting is provided
 * through the Formattable interface.
 * 
 * The class is not multi-threaded safe. The responsibility to maintain thread
 * safety is the user's job.
 * 
 */
public final class Formatter implements Closeable, Flushable {

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
	 * 
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
	 * @param The
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
	public String toString() {
		checkClosed();
		return out.toString();
	}

	/**
	 * Flushes the formatter. If the output destination is flushable, then the
	 * method flush() will be called on that destinaiton.
	 * 
	 * @throws FormatterClosedException
	 *             If the formatter has been closed.
	 */
	public void flush() throws IOException {
		checkClosed();
		if (out instanceof Flushable) {
			try {
				((Flushable) out).flush();
			} catch (IOException e) {
				lastIOException = e;
			}
			;
		}

	}

	/**
	 * Closes the formatter. If the output destination is Closeable, then the
	 * method close() will be called on that destination.
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
	 *             the format string or any other illegal situcation.
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
	 *            localization will be applied. This param does not influence
	 *            the locale specified during construction.
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
	 *             the format string or any other illegal situcation.
	 * @throws FormatterClosedException
	 *             If the formatter has been closed.
	 */
	public Formatter format(Locale l, String format, Object... args) {
		throw new NotYetImplementedException();
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

}
