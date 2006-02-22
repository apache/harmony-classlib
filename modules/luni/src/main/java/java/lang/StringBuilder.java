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

package java.lang;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.harmony.luni.util.NotYetImplementedException;

/**
 * TODO javadoc
 */
public final class StringBuilder implements CharSequence, Serializable {

	// TODO add 'implements Appendable' (see HARMONY-103)

	private static final long serialVersionUID = 4383685877147921099L;

	private static final String EMPTY_STRING = ""; //$NON-NLS-1$

	private static final String NULL_STRING = "null"; //$NON-NLS-1$

	private static final char[] NULL_CHAR_ARRAY = NULL_STRING.toCharArray();

	private static final int INITIAL_CAPACITY = 16;

	private transient char[] buffer;

	private transient int length;

	/**
	 * TODO javadoc
	 */
	public StringBuilder() {
		super();
		this.buffer = new char[INITIAL_CAPACITY];
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder(int capacity) {
		super();
		if (capacity < 0)
			throw new NegativeArraySizeException();
		this.buffer = new char[capacity];
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder(CharSequence seq) {
		super();
		if (seq == null)
			throw new NullPointerException();
		this.length = seq.length();
		this.buffer = new char[length + 16];
		for (int i = 0; i < length; i++) {
			this.buffer[i] = seq.charAt(i);
		}
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder(String str) {
		this((CharSequence) str);
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder append(boolean b) {
		return append(String.valueOf(b));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Appendable#append(char)
	 */
	public StringBuilder append(char c) {
		return append(String.valueOf(c));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder append(char[] str) {
		return append(String.valueOf(str));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder append(char[] str, int offset, int len) {
		return append(String.valueOf(str, offset, len));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Appendable#append(java.lang.CharSequence)
	 */
	public StringBuilder append(CharSequence csq) {
		return append((String) (csq == null ? null : csq.toString()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Appendable#append(java.lang.CharSequence, int, int)
	 */
	public StringBuilder append(CharSequence csq, int start, int end) {
		if (csq == null)
			csq = NULL_STRING;
		return append(csq.subSequence(start, end));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder append(double d) {
		return append(String.valueOf(d));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder append(float f) {
		return append(String.valueOf(f));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder append(int i) {
		return append(String.valueOf(i));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder append(long lng) {
		return append(String.valueOf(lng));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder append(Object obj) {
		return append(String.valueOf(obj));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder append(String str) {
		// if null or interned "null" string append "null"
		if (str == null || str == NULL_STRING) {
			ensureCapacity(length + 4);
			System.arraycopy(NULL_CHAR_ARRAY, 0, buffer, length, 4);
			length += 4;
		} else {
			int len = str.length();
			ensureCapacity(length + len);
			for (int i = 0; i < len; i++) {
				buffer[length++] = str.charAt(i);
			}
		}
		return this;
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder append(StringBuffer sb) {
		return append((CharSequence) sb);
	}

	/**
	 * <p>
	 * <b>NOTE - This method is currently not implemented and always throws a
	 * {@link NotYetImplementedException}.</b>
	 * </p>
	 * TODO javadoc
	 */
	public StringBuilder appendCodePoint(int codePoint) {
		// TODO Implement Java 5 code point functionality.
		throw new NotYetImplementedException();
	}

	/**
	 * TODO javadoc
	 */
	public int capacity() {
		return buffer.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.CharSequence#charAt(int)
	 */
	public char charAt(int index) {
		if (index < 0 || index >= length)
			throw new IndexOutOfBoundsException();
		return buffer[index];
	}

	/**
	 * <p>
	 * <b>NOTE - This method is currently NOT completely implemented and just
	 * delegates to the {@link #charAt(int)} method.</b>
	 * </p>
	 * TODO javadoc
	 */
	public int codePointAt(int index) {
		// TODO Implement Java 5 code point functionality.
		return charAt(index);
	}

	/**
	 * <p>
	 * <b>NOTE - This method is currently NOT completely implemented and just
	 * delegates to the {@link #charAt(int)} method by retrieving the character
	 * at the preceding index.</b>
	 * </p>
	 * TODO javadoc
	 */
	public int codePointBefore(int index) {
		// TODO Implement Java 5 code point functionality.
		return codePointAt(index - 1);
	}

	/**
	 * <p>
	 * <b>NOTE - This method is currently NOT completely implemented and just
	 * return the difference between the index parameters.</b>
	 * </p>
	 * TODO javadoc
	 */
	public int codePointCount(int beginIndex, int endIndex) {
		// TODO Implement Java 5 code point functionality.
		if (beginIndex < 0 || endIndex > length || beginIndex > endIndex)
			throw new IndexOutOfBoundsException();
		return endIndex - beginIndex;
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder delete(int start, int end) {
		if (start < 0 || start > length || start > end)
			throw new StringIndexOutOfBoundsException();
		if (start != end) {
			// massage end if it's too long
			if (end > length)
				end = length;
			// shift chars left
			System.arraycopy(buffer, end, buffer, start, length - end);
			// adjust length
			length -= (end - start);
		}
		return this;
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder deleteCharAt(int index) {
		// check for index values past length, as 'delete' will massage them out
		if (index >= length)
			throw new StringIndexOutOfBoundsException();
		return delete(index, index + 1);
	}

	/**
	 * TODO javadoc
	 */
	public void ensureCapacity(int minimumCapacity) {
		if (minimumCapacity > buffer.length) {
			/*
			 * Create a new buffer that's the greater of the requested capacity
			 * or the current capacity multiplied by 2 plus 2.
			 */
			char[] newbuffer = new char[Math.max(minimumCapacity,
					(buffer.length * 2) + 2)];
			// copy the original buffer's contents
			System.arraycopy(buffer, 0, newbuffer, 0, length);
			// assign new buffer to instance field
			buffer = newbuffer;
		}
	}

	/**
	 * TODO javadoc
	 */
	public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
		if (dst == null)
			throw new NullPointerException();
		if (srcBegin < 0 || dstBegin < 0 || srcBegin > srcEnd
				|| srcEnd > length
				|| (dstBegin + srcEnd - srcBegin) > dst.length)
			throw new IndexOutOfBoundsException();

		System.arraycopy(buffer, srcBegin, dst, dstBegin, srcEnd - srcBegin);
	}

	/**
	 * TODO javadoc
	 */
	public int indexOf(String str) {
		if (str == null)
			throw new NullPointerException();
		// TODO optimize
		return this.toString().indexOf(str);
	}

	/**
	 * TODO javadoc
	 */
	public int indexOf(String str, int fromIndex) {
		if (str == null)
			throw new NullPointerException();
		// TODO optimize
		return this.toString().indexOf(str, fromIndex);
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder insert(int offset, boolean b) {
		if (offset < 0 || offset > length)
			throw new StringIndexOutOfBoundsException();

		return insert(offset, String.valueOf(b));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder insert(int offset, char c) {
		if (offset < 0 || offset > length)
			throw new StringIndexOutOfBoundsException();

		return insert(offset, String.valueOf(c));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder insert(int offset, char[] str) {
		if (offset < 0 || offset > length)
			throw new StringIndexOutOfBoundsException();

		return insert(offset, String.valueOf(str));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder insert(int index, char[] str, int offset, int len) {
		if (index < 0 || index > length)
			throw new StringIndexOutOfBoundsException();

		return insert(index, String.valueOf(str, offset, len));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder insert(int offset, CharSequence s) {
		if (offset < 0 || offset > length)
			throw new StringIndexOutOfBoundsException();

		return insert(offset, (s == null ? (String) null : s.toString()));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder insert(int offset, CharSequence s, int start, int end) {
		if (offset < 0 || offset > length)
			throw new IndexOutOfBoundsException();

		if (s == null)
			s = NULL_STRING;

		return insert(offset, s.subSequence(start, end));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder insert(int offset, double d) {
		if (offset < 0 || offset > length)
			throw new StringIndexOutOfBoundsException();

		return insert(offset, String.valueOf(d));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder insert(int offset, float f) {
		if (offset < 0 || offset > length)
			throw new StringIndexOutOfBoundsException();

		return insert(offset, String.valueOf(f));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder insert(int offset, int i) {
		if (offset < 0 || offset > length)
			throw new StringIndexOutOfBoundsException();

		return insert(offset, String.valueOf(i));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder insert(int offset, long l) {
		if (offset < 0 || offset > length)
			throw new StringIndexOutOfBoundsException();

		return insert(offset, String.valueOf(l));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder insert(int offset, Object obj) {
		if (offset < 0 || offset > length)
			throw new StringIndexOutOfBoundsException();

		return insert(offset, String.valueOf(obj));
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder insert(int offset, String str) {
		if (offset < 0 || offset > length)
			throw new StringIndexOutOfBoundsException();

		if (str == null)
			str = NULL_STRING;

		int len = str.length();
		ensureCapacity(length + len);
		// shift chars right
		System.arraycopy(buffer, offset, buffer, offset + len, length - offset);
		// adjust length
		length += len;
		// copy in new chars
		for (int i = 0, j = offset; i < len; i++, j++)
			buffer[j] = str.charAt(i);
		return this;
	}

	/**
	 * TODO javadoc
	 */
	public int lastIndexOf(String str) {
		if (str == null)
			throw new NullPointerException();
		// TODO optimize
		return this.toString().lastIndexOf(str);
	}

	/**
	 * TODO javadoc
	 */
	public int lastIndexOf(String str, int fromIndex) {
		if (str == null)
			throw new NullPointerException();
		// TODO optimize
		return this.toString().lastIndexOf(str, fromIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.CharSequence#length()
	 */
	public int length() {
		return length;
	}

	/**
	 * TODO javadoc
	 */
	public int offsetByCodePoints(int index, int codePointOffset) {
		// TODO Implement Java 5 code point functionality.
		throw new NotYetImplementedException();
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder replace(int start, int end, String str) {
		if (start < 0 || start > length || start > end)
			throw new StringIndexOutOfBoundsException();

		if (str == null) // TODO verify this undocumented NPE
			throw new NullPointerException();

		// if the start is just past last char, then treat it like an append
		if (start == length) {
			return append(str);
		}

		int sbLen = end - start;
		int strLen = str.length();
		if (strLen > sbLen) {
			// shift chars with an insert of the difference
			// this will handle capacity and length management
			insert(start, new char[strLen - sbLen]);
		}
		// copy in new chars
		for (int i = 0, j = start; i < strLen; i++, j++)
			buffer[j] = str.charAt(i);
		return this;
	}

	/**
	 * TODO javadoc
	 */
	public StringBuilder reverse() {
		if (length > 1) { // only reverse if necessary
			for (int i = 0, j = length - 1; i <= j - 1; i++, j--) {
				char c = buffer[j];
				buffer[j] = buffer[i];
				buffer[i] = c;
			}
		}
		return this;
	}

	/**
	 * TODO javadoc
	 */
	public void setCharAt(int index, char ch) {
		if (index < 0 || index >= length)
			throw new IndexOutOfBoundsException();
		buffer[index] = ch;
	}

	/**
	 * TODO javadoc
	 */
	public void setLength(int newLength) {
		if (newLength < 0)
			throw new IndexOutOfBoundsException();
		if (newLength > length) {
			// expand if necessary
			ensureCapacity(newLength);
			// null out any new chars at the sequence end
			for (int i = length; i < newLength; i++)
				buffer[i] = 0;
		}
		length = newLength;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.CharSequence#subSequence(int, int)
	 */
	public CharSequence subSequence(int start, int end) {
		return substring(start, end);
	}

	/**
	 * TODO javadoc
	 */
	public String substring(int start) {
		if (start < 0 || start > length)
			throw new StringIndexOutOfBoundsException();

		int ssCharCnt = length - start;
		if (ssCharCnt == 0)
			return EMPTY_STRING;
		return new String(buffer, start, ssCharCnt);
	}

	/**
	 * TODO javadoc
	 */
	public String substring(int start, int end) {
		if (start < 0 || end > length || start > end)
			throw new StringIndexOutOfBoundsException();

		int ssCharCnt = end - start;
		if (ssCharCnt == 0)
			return EMPTY_STRING;
		return new String(buffer, start, end - start);
	}

	/**
	 * TODO javadoc
	 */
	public String toString() {
		if (length == 0)
			return EMPTY_STRING;
		return new String(buffer, 0, length);
	}

	/**
	 * TODO javadoc
	 */
	public void trimToSize() {
		if (length < buffer.length) {
			char[] newbuffer = new char[length];
			System.arraycopy(buffer, 0, newbuffer, 0, length);
			buffer = newbuffer;
		}
	}

	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		length = in.readInt();
		buffer = (char[]) in.readObject();
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeInt(length);
		out.writeObject(buffer);
	}
}
