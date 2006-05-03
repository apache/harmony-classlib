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

package java.lang;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <p>
 * A modifiable {@link CharSequence sequence of characters} for use in creating
 * and modifying Strings. This class is intended as a direct replacement of
 * {@link java.lang.StringBuffer} for non-concurrent use; unlike
 * <code>StringBuffer</code> this class is not synchronized for thread safety.
 * </p>
 * 
 * <p>
 * The majority of the modification methods on this class return
 * <code>StringBuilder</code>, so that, like <code>StringBuffer</code>s,
 * they can be used in chaining method calls together. For example,
 * <code>new StringBuilder("One should ").append("always strive ").append("to achieve Harmony")</code>.
 * </p>
 * 
 * @see java.lang.CharSequence
 * @see java.lang.Appendable
 * @see java.lang.StringBuffer
 * @see java.lang.String
 * 
 * @since 1.5
 * 
 * @author Nathan Beyer (Harmony)
 */
public final class StringBuilder implements Appendable, CharSequence, Serializable {

    private static final long serialVersionUID = 4383685877147921099L;

    private static final String EMPTY_STRING = ""; //$NON-NLS-1$

    private static final String NULL_STRING = "null"; //$NON-NLS-1$

    private static final char[] NULL_CHAR_ARRAY = NULL_STRING.toCharArray();

    private static final int INITIAL_CAPACITY = 16;

    private transient char[] buffer;

    private transient int length;

    /**
     * <p>
     * Constructs an instance with an initial capacity of <code>16</code>.
     * </p>
     * 
     * @see #capacity()
     */
    public StringBuilder() {
        super();
        this.buffer = new char[INITIAL_CAPACITY];
    }

    /**
     * <p>
     * Constructs an instance with a specified capacity.
     * </p>
     * 
     * @param capacity The initial capacity to use.
     * 
     * @throws NegativeArraySizeException if the <code>capacity</code>
     *         parameter is <code>null</code>.
     * 
     * @see #capacity()
     */
    public StringBuilder(int capacity) {
        super();
        if (capacity < 0)
            throw new NegativeArraySizeException();
        this.buffer = new char[capacity];
    }

    /**
     * <p>
     * Constructs an instance that's populated by a {@link CharSequence}. The
     * capacity of the new builder will bee the length of the
     * <code>CharSequence</code> plus 16.
     * </p>
     * 
     * @param seq The <code>CharSequence</code> to copy into the builder.
     * @throws NullPointerException if the <code>seq</code> parameter is
     *         <code>null</code>.
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
     * <p>
     * Constructs an instance that's populated by a {@link String}. The
     * capacity of the new builder will bee the length of the
     * <code>String</code> plus 16.
     * </p>
     * 
     * @param str The <code>String</code> to copy into the builder.
     * @throws NullPointerException if the <code>str</code> parameter is
     *         <code>null</code>.
     */
    public StringBuilder(String str) {
        this((CharSequence) str);
    }

    /**
     * <p>
     * Appends the String representation of the <code>boolean</code> value
     * passed. The <code>boolean</code> value is converted to a String
     * according to the rule defined by {@link String#valueOf(boolean)}.
     * </p>
     * 
     * @param b The <code>boolean</code> value to append to this object.
     * @return A reference to this object.
     * 
     * @see String#valueOf(boolean)
     */
    public StringBuilder append(boolean b) {
        return append(String.valueOf(b));
    }

    /**
     * <p>
     * Appends the String representation of the <code>char</code> value
     * passed. The <code>char</code> value is converted to a String according
     * to the rule defined by {@link String#valueOf(char)}.
     * </p>
     * 
     * @param c The <code>char</code> value to append to this object.
     * @return A reference to this object.
     * 
     * @see String#valueOf(char)
     */
    public StringBuilder append(char c) {
        return append(String.valueOf(c));
    }

    /**
     * <p>
     * Appends the String representation of the <code>char[]</code> value
     * passed. The <code>char[]</code> value is converted to a String
     * according to the rule defined by {@link String#valueOf(char[])}.
     * </p>
     * 
     * @param str The <code>char[]</code> value to append to this object.
     * @return A reference to this object.
     * 
     * @see String#valueOf(char[])
     */
    public StringBuilder append(char[] str) {
        return append(String.valueOf(str));
    }

    /**
     * <p>
     * Appends the String representation of the subset of the
     * <code>char[]</code> value passed. The <code>char[]</code> value is
     * converted to a String according to the rule defined by
     * {@link String#valueOf(char[],int,int)}.
     * </p>
     * 
     * @param str The <code>char[]</code> value to append to this object.
     * @param offset The inclusive offset index to begin copying from the
     *        <code>str</code> parameter.
     * @param len The number of character to copy from the <code>str</code>
     *        parameter.
     * @return A reference to this object.
     * 
     * @see String#valueOf(char[],int,int)
     */
    public StringBuilder append(char[] str, int offset, int len) {
        return append(String.valueOf(str, offset, len));
    }

    /**
     * <p>
     * Appends the String representation of the <code>CharSequence</code>
     * value passed. If the <code>CharSequence</code> is <code>null</code>,
     * then the String <code>"null"</code> is appended.
     * </p>
     * 
     * @param csq The <code>CharSequence</code> value to append to this
     *        object.
     * @return A reference to this object.
     */
    public StringBuilder append(CharSequence csq) {
        return append((String) (csq == null ? null : csq.toString()));
    }

    /**
     * <p>
     * Appends the String representation of the subsequence of the
     * <code>CharSequence</code> value passed. If the
     * <code>CharSequence</code> is <code>null</code>, then the String
     * <code>"null"</code> is used to extract the subsequence from.
     * </p>
     * 
     * @param csq The <code>CharSequence</code> value to append to this
     *        object.
     * @param start The beginning index of the subsequence.
     * @param end The ending index of the subsequence.
     * @return A reference to this object.
     */
    public StringBuilder append(CharSequence csq, int start, int end) {
        if (csq == null)
            csq = NULL_STRING;
        return append(csq.subSequence(start, end));
    }

    /**
     * <p>
     * Appends the String representation of the <code>double</code> value
     * passed. The <code>double</code> value is converted to a String
     * according to the rule defined by {@link String#valueOf(double)}.
     * </p>
     * 
     * @param d The <code>double</code> value to append to this object.
     * @return A reference to this object.
     * 
     * @see String#valueOf(double)
     */
    public StringBuilder append(double d) {
        return append(String.valueOf(d));
    }

    /**
     * <p>
     * Appends the String representation of the <code>float</code> value
     * passed. The <code>float</code> value is converted to a String according
     * to the rule defined by {@link String#valueOf(float)}.
     * </p>
     * 
     * @param f The <code>float</code> value to append to this object.
     * @return A reference to this object.
     * 
     * @see String#valueOf(float)
     */
    public StringBuilder append(float f) {
        return append(String.valueOf(f));
    }

    /**
     * <p>
     * Appends the String representation of the <code>int</code> value passed.
     * The <code>int</code> value is converted to a String according to the
     * rule defined by {@link String#valueOf(int)}.
     * </p>
     * 
     * @param i The <code>int</code> value to append to this object.
     * @return A reference to this object.
     * 
     * @see String#valueOf(int)
     */
    public StringBuilder append(int i) {
        return append(String.valueOf(i));
    }

    /**
     * <p>
     * Appends the String representation of the <code>long</code> value
     * passed. The <code>long</code> value is converted to a String according
     * to the rule defined by {@link String#valueOf(long)}.
     * </p>
     * 
     * @param lng The <code>long</code> value to append to this object.
     * @return A reference to this object.
     * 
     * @see String#valueOf(long)
     */
    public StringBuilder append(long lng) {
        return append(String.valueOf(lng));
    }

    /**
     * <p>
     * Appends the String representation of the <code>Object</code> value
     * passed. The <code>Object</code> value is converted to a String
     * according to the rule defined by {@link String#valueOf(Object)}.
     * </p>
     * 
     * @param obj The <code>Object</code> value to append to this object.
     * @return A reference to this object.
     * 
     * @see String#valueOf(Object)
     */
    public StringBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }

    /**
     * <p>
     * Appends the contents of the String. If the String passed is
     * <code>null</code>, then the String <code>"null"</code> is appended.
     * </p>
     * 
     * @param str The String to append to this object.
     * @return A reference to this object.
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
     * <p>
     * Appends the contents of the StringBuffer. If the StringBuffer passed is
     * <code>null</code>, then the StringBuffer <code>"null"</code> is
     * appended.
     * </p>
     * 
     * @param sb The StringBuffer to append to this object.
     * @return A reference to this object.
     */
    public StringBuilder append(StringBuffer sb) {
        return append((CharSequence) sb);
    }

    /**
     * <p>
     * Appends the encoded Unicode code point to this object. The code point is
     * converted to a <code>char[]</code> as defined by
     * {@link Character#toChars(int)}.
     * </p>
     * 
     * @param codePoint The Unicode code point to encode and append.
     * @return A reference to this object.
     * @see Character#toChars(int)
     */
    public StringBuilder appendCodePoint(int codePoint) {
        return append(Character.toChars(codePoint));
    }

    /**
     * <p>
     * The current capacity of this object.
     * </p>
     * 
     * @return An int value representing the capacity.
     */
    public int capacity() {
        return buffer.length;
    }

    /**
     * <p>
     * Retrieves the character at the <code>index</code>.
     * </p>
     * 
     * @param The index of character in this object to retrieve.
     * @return The char value.
     * @throws IndexOutOfBoundsException if <code>index</code> is negative or
     *         greater than or equal to the current {@link #length()}.
     */
    public char charAt(int index) {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException();
        return buffer[index];
    }

    /**
     * <p>
     * Retrieves the Unicode code point value at the <code>index</code>.
     * </p>
     * 
     * @param index The index to the <code>char</code> code unit within this
     *        object.
     * @return The Unicode code point value.
     * @throws IndexOutOfBoundsException if <code>index</code> is negative or
     *         greater than or equal to {@link #length()}.
     * @see Character
     * @see Character#codePointAt(char[], int, int)
     */
    public int codePointAt(int index) {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException();
        return Character.codePointAt(buffer, index, length);
    }

    /**
     * <p>
     * Retrieves the Unicode code point value that precedes the
     * <code>index</code>.
     * </p>
     * 
     * @param index The index to the <code>char</code> code unit within this
     *        object.
     * @return The Unicode code point value.
     * @throws IndexOutOfBoundsException if <code>index</code> is less than 1
     *         or greater than {@link #length()}.
     * @see Character
     * @see Character#codePointBefore(char[], int, int)
     */
    public int codePointBefore(int index) {
        if (index < 1 || index > length)
            throw new IndexOutOfBoundsException();
        return Character.codePointBefore(buffer, index);
    }

    /**
     * <p>
     * Calculates the number of Unicode code points between
     * <code>beginIndex</code> and <code>endIndex</code>.
     * </p>
     * 
     * @param beginIndex The inclusive beginning index of the subsequence.
     * @param endIndex The exclusive end index of the subsequence.
     * @return The number of Unicode code points in the subsequence.
     * @throws IndexOutOfBoundsException if <code>beginIndex</code> is
     *         negative or greater than <code>endIndex</code> or
     *         <code>endIndex</code> is greater than {@link #length()}.
     */
    public int codePointCount(int beginIndex, int endIndex) {
        if (beginIndex < 0 || endIndex > length || beginIndex > endIndex)
            throw new IndexOutOfBoundsException();
        return Character.codePointCount(buffer, beginIndex, endIndex
                - beginIndex);
    }

    /**
     * <p>
     * Deletes a sequence of characters within this object, shifts any remaining
     * characters to the left and adjusts the {@link #length()} of this object.
     * </p>
     * 
     * @param start The inclusive start index to begin deletion.
     * @param end The exclusive end index to stop deletion.
     * @return A reference to this object.
     * @throws StringIndexOutOfBoundsException if <code>start</code> is less
     *         than zero, greater than the current length or greater than
     *         <code>end</code>.
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
     * <p>
     * Deletes a single character within this object, shifts any remaining
     * characters to the left and adjusts the {@link #length()} of this object.
     * </p>
     * 
     * @param index The index of the character to delete.
     * @return A reference to this object.
     * @throws StringIndexOutOfBoundsException if <code>index</code> is less
     *         than zero or is greater than or equal to the current length.
     */
    public StringBuilder deleteCharAt(int index) {
        // check for index values past length, as 'delete' will massage them out
        if (index >= length)
            throw new StringIndexOutOfBoundsException();
        return delete(index, index + 1);
    }

    /**
     * <p>
     * Ensures that this object has a minimum capacity available before
     * requiring the internal buffer to be enlarged. The general policy of this
     * method is that if the <code>minimumCapacity</code> is larger than the
     * current {@link #capacity()}, then the capacity will be increased to the
     * largest value of either the <code>minimumCapacity</code> or the current
     * capacity multiplied by two plus two. Although this is the general policy,
     * there is no guarantee that the capacity will change.
     * </p>
     * 
     * @param minimumCapacity The new minimum capacity to set.
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
     * <p>
     * Copies the requested sequence of characters to be copied to the
     * <code>char[]</code> passed.
     * </p>
     * 
     * @param srcBegin The inclusive start index of the characters to copy from
     *        this object.
     * @param srcEnd The exclusive end index of the characters to copy from this
     *        object.
     * @param dst The <code>char[]</code> to copy the characters to.
     * @param dstBegin The inclusive start index of the <code>dst</code>
     *        parameter to begin copying to.
     * @throws IndexOutOfBoundsException if the <code>srcBegin</code> is
     *         negative, the <code>dstBegin</code> is negative, the
     *         <code>srcBegin</code> is greater than <code>srcEnd</code>,
     *         the <code>srcEnd</code> is greater than the current
     *         {@link #length()} or <code>dstBegin + srcEnd - srcBegin</code>
     *         is greater than <code>dst.lenbth</code>.
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
     * <p>
     * Searches this object for a match to the String passed and returns the
     * index of the first character that matches or <code>-1</code> if a match
     * wasn't found. This method follows the same rules as the
     * {@link String#indexOf(java.lang.String)}.
     * </p>
     * 
     * @param str The pattern to search for.
     * @return The beginning index of the matching sequence or <code>-1</code>
     *         if no match was found.
     * 
     * @throws NullPointerException if the <code>str</code> parameter is
     *         <code>null</code>.
     * 
     * @see String#indexOf(java.lang.String)
     */
    public int indexOf(String str) {
        if (str == null)
            throw new NullPointerException();
        return indexOf(str, 0);
    }

    /**
     * <p>
     * Searches this object, starting at the <code>fromIndex</code>, for a
     * match to the String passed and returns the index of the first character
     * that matches or <code>-1</code> if a match wasn't found. This method
     * follows the same rules as the
     * {@link String#indexOf(java.lang.String,int)}.
     * </p>
     * 
     * @param str The pattern to search for.
     * @param fromIndex The index of this object to begin searching at.
     * @return The beginning index of the matching sequence or <code>-1</code>
     *         if no match was found.
     * 
     * @throws NullPointerException if the <code>str</code> parameter is
     *         <code>null</code>.
     * 
     * @see String#indexOf(java.lang.String,int)
     */
    public int indexOf(String str, int fromIndex) {
        if (str == null)
            throw new NullPointerException();
        //TODO optimize
        return this.toString().indexOf(str, fromIndex);
    }

    /**
     * <p>
     * Inserts the String representation of the <code>boolean</code> value
     * passed into this object at the <code>offset</code> passed. The
     * <code>boolean</code> value is converted to a String according to the
     * rule defined by {@link String#valueOf(boolean)}.
     * </p>
     * 
     * @param offset The index of this object to insert the value.
     * @param b The <code>boolean</code> value to insert into this object.
     * @return A reference to this object.
     * 
     * @throws StringIndexOutOfBoundsException if <code>offset</code> is
     *         negative or greater than the current {@link #length()}.
     * 
     * @see String#valueOf(boolean)
     */
    public StringBuilder insert(int offset, boolean b) {
        if (offset < 0 || offset > length)
            throw new StringIndexOutOfBoundsException();

        return insert(offset, String.valueOf(b));
    }

    /**
     * <p>
     * Inserts the String representation of the <code>char</code> value passed
     * into this object at the <code>offset</code> passed. The
     * <code>char</code> value is converted to a String according to the rule
     * defined by {@link String#valueOf(char)}.
     * </p>
     * 
     * @param offset The index of this object to insert the value.
     * @param c The <code>char</code> value to insert into this object.
     * @return A reference to this object.
     * 
     * @throws StringIndexOutOfBoundsException if <code>offset</code> is
     *         negative or greater than the current {@link #length()}.
     * 
     * @see String#valueOf(char)
     */
    public StringBuilder insert(int offset, char c) {
        if (offset < 0 || offset > length)
            throw new StringIndexOutOfBoundsException();

        return insert(offset, String.valueOf(c));
    }

    /**
     * <p>
     * Inserts the String representation of the <code>char[]</code> value
     * passed into this object at the <code>offset</code> passed. The
     * <code>char[]</code> value is converted to a String according to the
     * rule defined by {@link String#valueOf(char[])}.
     * </p>
     * 
     * @param offset The index of this object to insert the value.
     * @param str The <code>char[]</code> value to insert into this object.
     * @return A reference to this object.
     * 
     * @throws StringIndexOutOfBoundsException if <code>offset</code> is
     *         negative or greater than the current {@link #length()}.
     * 
     * @see String#valueOf(char[])
     */
    public StringBuilder insert(int offset, char[] str) {
        if (offset < 0 || offset > length)
            throw new StringIndexOutOfBoundsException();

        return insert(offset, String.valueOf(str));
    }

    /**
     * <p>
     * Inserts the String representation of the subsequence of the
     * <code>char[]</code> value passed into this object at the
     * <code>offset</code> passed. The <code>char[]</code> value is
     * converted to a String according to the rule defined by
     * {@link String#valueOf(char[],int,int)}.
     * </p>
     * 
     * @param offset The index of this object to insert the value.
     * @param str The <code>char[]</code> value to insert into this object.
     * @param strOffset The inclusive index of the <code>str</code> parameter
     *        to start copying from.
     * @param strLen The number of characters to copy from the <code>str</code>
     *        parameter.
     * @return A reference to this object.
     * 
     * @throws StringIndexOutOfBoundsException if <code>offset</code> is
     *         negative or greater than the current {@link #length()}.
     * 
     * @see String#valueOf(char[],int,int)
     */
    public StringBuilder insert(int offset, char[] str, int strOffset,
            int strLen) {
        if (offset < 0 || offset > length)
            throw new StringIndexOutOfBoundsException();

        return insert(offset, String.valueOf(str, strOffset, strLen));
    }

    /**
     * <p>
     * Inserts the String representation of the <code>CharSequence</code>
     * value passed into this object at the <code>offset</code> passed. The
     * <code>CharSequence</code> value is converted to a String as defined by
     * {@link CharSequence#toString()}. If the <code>CharSequence</code> is
     * <code>null</code>, then the String <code>"null"</code> is inserted.
     * </p>
     * 
     * @param offset The index of this object to insert the value.
     * @param s The <code>CharSequence</code> value to insert into this
     *        object.
     * @return A reference to this object.
     * 
     * @throws IndexOutOfBoundsException if <code>offset</code> is negative or
     *         greater than the current {@link #length()}.
     * 
     * @see CharSequence#toString()
     */
    public StringBuilder insert(int offset, CharSequence s) {
        if (offset < 0 || offset > length)
            throw new IndexOutOfBoundsException();

        return insert(offset, (s == null ? (String) null : s.toString()));
    }

    /**
     * <p>
     * Inserts the String representation of the subsequence of the
     * <code>CharSequence</code> value passed into this object at the
     * <code>offset</code> passed. The <code>CharSequence</code> value is
     * converted to a String as defined by
     * {@link CharSequence#subSequence(int, int)}. If the
     * <code>CharSequence</code> is <code>null</code>, then the String
     * <code>"null"</code> is used to determine the subsequence.
     * </p>
     * 
     * @param offset The index of this object to insert the value.
     * @param s The <code>CharSequence</code> value to insert into this
     *        object.
     * @param start The start of the subsequence of the <code>s</code>
     *        parameter.
     * @param end The end of the subsequence of the <code>s</code> parameter.
     * @return A reference to this object.
     * 
     * @throws IndexOutOfBoundsException if <code>offset</code> is negative or
     *         greater than the current {@link #length()}.
     * 
     * @see CharSequence#subSequence(int, int)
     */
    public StringBuilder insert(int offset, CharSequence s, int start, int end) {
        if (offset < 0 || offset > length)
            throw new IndexOutOfBoundsException();

        if (s == null)
            s = NULL_STRING;

        return insert(offset, s.subSequence(start, end));
    }

    /**
     * <p>
     * Inserts the String representation of the <code>double</code> value
     * passed into this object at the <code>offset</code> passed. The
     * <code>double</code> value is converted to a String according to the
     * rule defined by {@link String#valueOf(double)}.
     * </p>
     * 
     * @param offset The index of this object to insert the value.
     * @param d The <code>double</code> value to insert into this object.
     * @return A reference to this object.
     * 
     * @throws StringIndexOutOfBoundsException if <code>offset</code> is
     *         negative or greater than the current {@link #length()}.
     * 
     * @see String#valueOf(double)
     */
    public StringBuilder insert(int offset, double d) {
        if (offset < 0 || offset > length)
            throw new StringIndexOutOfBoundsException();

        return insert(offset, String.valueOf(d));
    }

    /**
     * <p>
     * Inserts the String representation of the <code>float</code> value
     * passed into this object at the <code>offset</code> passed. The
     * <code>float</code> value is converted to a String according to the rule
     * defined by {@link String#valueOf(float)}.
     * </p>
     * 
     * @param offset The index of this object to insert the value.
     * @param b The <code>float</code> value to insert into this object.
     * @return A reference to this object.
     * 
     * @throws StringIndexOutOfBoundsException if <code>offset</code> is
     *         negative or greater than the current {@link #length()}.
     * 
     * @see String#valueOf(float)
     */
    public StringBuilder insert(int offset, float f) {
        if (offset < 0 || offset > length)
            throw new StringIndexOutOfBoundsException();

        return insert(offset, String.valueOf(f));
    }

    /**
     * <p>
     * Inserts the String representation of the <code>int</code> value passed
     * into this object at the <code>offset</code> passed. The
     * <code>int</code> value is converted to a String according to the rule
     * defined by {@link String#valueOf(int)}.
     * </p>
     * 
     * @param offset The index of this object to insert the value.
     * @param b The <code>int</code> value to insert into this object.
     * @return A reference to this object.
     * 
     * @throws StringIndexOutOfBoundsException if <code>offset</code> is
     *         negative or greater than the current {@link #length()}.
     * 
     * @see String#valueOf(int)
     */
    public StringBuilder insert(int offset, int i) {
        if (offset < 0 || offset > length)
            throw new StringIndexOutOfBoundsException();

        return insert(offset, String.valueOf(i));
    }

    /**
     * <p>
     * Inserts the String representation of the <code>long</code> value passed
     * into this object at the <code>offset</code> passed. The
     * <code>long</code> value is converted to a String according to the rule
     * defined by {@link String#valueOf(long)}.
     * </p>
     * 
     * @param offset The index of this object to insert the value.
     * @param b The <code>long</code> value to insert into this object.
     * @return A reference to this object.
     * 
     * @throws StringIndexOutOfBoundsException if <code>offset</code> is
     *         negative or greater than the current {@link #length()}.
     * 
     * @see String#valueOf(long)
     */
    public StringBuilder insert(int offset, long l) {
        if (offset < 0 || offset > length)
            throw new StringIndexOutOfBoundsException();

        return insert(offset, String.valueOf(l));
    }

    /**
     * <p>
     * Inserts the String representation of the <code>Object</code> value
     * passed into this object at the <code>offset</code> passed. The
     * <code>Object</code> value is converted to a String according to the
     * rule defined by {@link String#valueOf(Object)}.
     * </p>
     * 
     * @param offset The index of this object to insert the value.
     * @param b The <code>Object</code> value to insert into this object.
     * @return A reference to this object.
     * 
     * @throws StringIndexOutOfBoundsException if <code>offset</code> is
     *         negative or greater than the current {@link #length()}.
     * 
     * @see String#valueOf(Object)
     */
    public StringBuilder insert(int offset, Object obj) {
        if (offset < 0 || offset > length)
            throw new StringIndexOutOfBoundsException();

        return insert(offset, String.valueOf(obj));
    }

    /**
     * <p>
     * Inserts the String value passed into this object at the
     * <code>offset</code> passed. If the String parameter is null, then the
     * String <code>"null"</code> is inserted.
     * </p>
     * 
     * @param offset The index of this object to insert the value.
     * @param str The String to insert into this object.
     * @return A reference to this object.
     * 
     * @throws StringIndexOutOfBoundsException if <code>offset</code> is
     *         negative or greater than the current {@link #length()}.
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
     * <p>
     * Searches this object for the last match to the String passed and returns
     * the index of the first character that matches or <code>-1</code> if a
     * match wasn't found. This method follows the same rules as the
     * {@link String#lastIndexOf(java.lang.String)}.
     * </p>
     * 
     * @param str The pattern to search for.
     * @return The beginning index of the matching sequence or <code>-1</code>
     *         if no match was found.
     * 
     * @throws NullPointerException if the <code>str</code> parameter is
     *         <code>null</code>.
     * 
     * @see String#lastIndexOf(java.lang.String)
     */
    public int lastIndexOf(String str) {
        if (str == null)
            throw new NullPointerException();
        return lastIndexOf(str, length);
    }

    /**
     * <p>
     * Searches this object, starting at the <code>fromIndex</code>, for the
     * last match to the String passed and returns the index of the first
     * character that matches or <code>-1</code> if a match wasn't found. This
     * method follows the same rules as the
     * {@link String#lastIndexOf(java.lang.String,int)}.
     * </p>
     * 
     * @param str The pattern to search for.
     * @param fromIndex The index of this object to begin searching at.
     * @return The beginning index of the matching sequence or <code>-1</code>
     *         if no match was found.
     * 
     * @throws NullPointerException if the <code>str</code> parameter is
     *         <code>null</code>.
     * 
     * @see String#lastIndexOf(java.lang.String,int)
     */
    public int lastIndexOf(String str, int fromIndex) {
        if (str == null)
            throw new NullPointerException();
        // TODO optimize
        return this.toString().lastIndexOf(str, fromIndex);
    }

    /**
     * <p>
     * The current length of this object.
     * </p>
     * 
     * @return The current length.
     */
    public int length() {
        return length;
    }

    /**
     * <p>
     * Returns the index within this object that is offset from
     * <code>index</code> by <code>codePointOffset</code> code points.
     * </p>
     * 
     * @param index The index within this object to calculate the offset from.
     * @param codePointOffset The number of code points to count.
     * @return The index within this object that is the offset.
     * @throws IndexOutOfBoundsException if <code>index</code> is negative or
     *         greater than {@link #length()} or if there aren't enough code
     *         points before or after <code>index</code> to match
     *         <code>codePointOffset</code>.
     */
    public int offsetByCodePoints(int index, int codePointOffset) {
        return Character.offsetByCodePoints(buffer, 0, length, index,
                codePointOffset);
    }

    /**
     * <p>
     * Replaces the indicated subsequence of this object with the String passed.
     * If the String passed is longer or shorter than the subsequence, then this
     * object will be adjusted appropriately.
     * </p>
     * 
     * @param start The inclusive start index of the sequence to replace in this
     *        object.
     * @param end The exclusive end index of the sequence to replace in this
     *        object.
     * @param str The String to replace the subsequence.
     * @return A reference to this object.
     * @throws StringIndexOutOfBoundsException if <code>start</code> is
     *         negative, greater than the current {@link #length()} or greater
     *         than <code>end</code>.
     * @throws NullPointerException if the <code>str</code> parameter is
     *         <code>null</code>.
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
        if(end > length) {
            end = length;
        }
        int sbLen = end - start;
        int strLen = str.length();
        if (strLen > sbLen) {
            // shift chars with an insert of the difference
            // this will handle capacity and length management
            insert(start, new char[strLen - sbLen]);
        }
        if(strLen < sbLen) {
            delete(start, start + sbLen-strLen);
        }
        // copy in new chars
        for (int i = 0, j = start; i < strLen; i++, j++)
            buffer[j] = str.charAt(i);
        return this;
    }

    /**
     * <p>
     * Reverses the contents of this object.
     * </p>
     * 
     * @return A reference to this object.
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
     * <p>
     * Sets the character at the <code>index</code> in this object.
     * </p>
     * 
     * @param index The index of the character to replace.
     * @param ch The character to set.
     * @throws IndexOutOfBoundsException if <code>index</code> is negative or
     *         greater than or equal to the current {@link #length()}.
     */
    public void setCharAt(int index, char ch) {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException();
        buffer[index] = ch;
    }

    /**
     * <p>
     * Sets the current length to a new value. If the new length is larger than
     * the current length, then the new characters at the end of this object
     * will contain the <code>char</code> value of <code>\u0000</code>.
     * </p>
     * 
     * @param newLength The new length to set this object to.
     * @throws IndexOutOfBoundsException if the <code>newLength</code>
     *         parameter is negative.
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

    /**
     * <p>
     * Returns a <code>CharSequence</code> of the subsequence of this object
     * from the <code>start</code> index to the <code>start</code> index.
     * </p>
     * 
     * @param start The inclusive start index to begin the subsequence.
     * @param end The exclusive end index to end the subsequence.
     * @return A CharSequence containing the subsequence.
     * @throws IndexOutOfBoundsException if <code>start</code> is negative,
     *         greater than the current {@link #length()} or greater than
     *         <code>end</code>.
     */
    public CharSequence subSequence(int start, int end) {
        return substring(start, end);
    }

    /**
     * <p>
     * Returns the String value of the subsequence of this object from the
     * <code>start</code> index to the current end.
     * </p>
     * 
     * @param start The inclusive start index to begin the subsequence.
     * @return A String containing the subsequence.
     * @throws StringIndexOutOfBoundsException if <code>start</code> is
     *         negative or greater than the current {@link #length()}.
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
     * <p>
     * Returns the String value of the subsequence of this object from the
     * <code>start</code> index to the <code>start</code> index.
     * </p>
     * 
     * @param start The inclusive start index to begin the subsequence.
     * @param end The exclusive end index to end the subsequence.
     * @return A String containing the subsequence.
     * @throws StringIndexOutOfBoundsException if <code>start</code> is
     *         negative, greater than the current {@link #length()} or greater
     *         than <code>end</code>.
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
     * <p>
     * Returns the current String representation of this object.
     * </p>
     * 
     * @return The current String representation of this object.
     */
    public String toString() {
        if (length == 0)
            return EMPTY_STRING;
        return new String(buffer, 0, length);
    }

    /**
     * <p>
     * Trims off any extra capacity beyond the current length. Note, this method
     * is NOT guaranteed to change the capacity of this object.
     * </p>
     */
    public void trimToSize() {
        if (length < buffer.length) {
            char[] newbuffer = new char[length];
            System.arraycopy(buffer, 0, newbuffer, 0, length);
            buffer = newbuffer;
        }
    }

    /**
     * <p>
     * Reads the state of a <code>StringBuilder</code> from the passed stream
     * and restores it to this instance.
     * </p>
     * 
     * @param in The stream to read the state from.
     * @throws IOException if the stream throws it during the read.
     * @throws ClassNotFoundException if the stream throws it during the read.
     */
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        length = in.readInt();
        buffer = (char[]) in.readObject();
    }

    /**
     * <p>
     * Writes the state of this object to the stream passed.
     * </p>
     * 
     * @param out The stream to write the state to.
     * @throws IOException if the stream throws it during the write.
     * @serialData <code>int</code> - The length of this object.
     *             <code>char[]</code> - The buffer from this object, which
     *             may be larger than the length field.
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(length);
        out.writeObject(buffer);
    }
}
