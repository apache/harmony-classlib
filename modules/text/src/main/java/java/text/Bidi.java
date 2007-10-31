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

import org.apache.harmony.text.internal.nls.Messages;

/**
 * Bidi is the class providing the bidirectional algorithm. The algorithm is
 * defined in the Unicode Standard Annex #9, version 13, also described in The
 * Unicode Standard, Version 4.0 .
 * 
 * Use a Bidi object to get the information on the position reordering of a
 * bidirectional text, such as Arabic or Hebrew. The natural display ordering of
 * horizontal text in these languages is from right to left, while they order
 * numbers from left to right.
 * 
 * If the text contains multiple runs, the information of each run can be
 * obtained from the run index. The level of any particular run indicates the
 * direction of the text as well as the nesting level. Left-to-right runs have
 * even levels while right-to-left runs have odd levels.
 * 
 */
public final class Bidi {
    /**
     * Constant that indicates the default base level. If there is no strong
     * character, then set the paragraph level to left-to-right.
     */
    public static final int DIRECTION_DEFAULT_LEFT_TO_RIGHT = com.ibm.icu.text.Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT;

    /**
     * Constant that indicates the default base level. If there is no strong
     * character, then set the paragraph level to right-to-left.
     */
    public static final int DIRECTION_DEFAULT_RIGHT_TO_LEFT = com.ibm.icu.text.Bidi.DIRECTION_DEFAULT_RIGHT_TO_LEFT;

    /**
     * Constant that specifies the default base level as 
     * left-to-right. 
     */
    public static final int DIRECTION_LEFT_TO_RIGHT = com.ibm.icu.text.Bidi.DIRECTION_LEFT_TO_RIGHT;

    /**
     * Constant that specifies the default base level right-to-left.
     */  
    public static final int DIRECTION_RIGHT_TO_LEFT = com.ibm.icu.text.Bidi.DIRECTION_RIGHT_TO_LEFT;

    /* 
     * Use an embedded ICU4J Bidi object to do all the work
     */
    private com.ibm.icu.text.Bidi icuBidi;

    /**
     * Create a Bidi object from the AttributedCharacterIterator of a paragraph
     * text.
     * 
     * The RUN_DIRECTION attribute determines the base direction of the
     * bidirectional text. If it's not specified explicitly, the algorithm uses
     * DIRECTION_DEFAULT_LEFT_TO_RIGHT by default.
     * 
     * The BIDI_EMBEDDING attribute specifies the level of embedding for each
     * character. Values between -1 and -62 denote overrides at the level's
     * absolute value, values from 1 to 62 indicate embeddings, and the 0 value
     * indicates the level is calculated by the algorithm automatically. For the
     * character with no BIDI_EMBEDDING attribute or with a improper attribute
     * value, such as a null value, the algorithm treats its embedding level as
     * 0.
     * 
     * The NUMERIC_SHAPING attribute specifies the instance of NumericShaper
     * used to convert European digits to other decimal digits before performing
     * the bidi algorithm.
     * 
     * @param paragraph
     * 
     * @see TextAttribute.BIDI_EMBEDDING
     * @see TextAttribute.NUMERIC_SHAPING
     * @see TextAttribute.RUN_DIRECTION
     */
    public Bidi(AttributedCharacterIterator paragraph) {
        if (paragraph == null) {
            // text.14=paragraph is null
            throw new IllegalArgumentException(Messages.getString("text.14")); //$NON-NLS-1$
        }

        icuBidi = new com.ibm.icu.text.Bidi(paragraph);
    }

    /**
     * Create a Bidi object.
     * 
     * @param text
     *            the char array of the paragraph text.
     * @param textStart
     *            the start offset of the text array to perform the algorithm.
     * @param embeddings
     *            the embedding level array of the paragraph text, specifying
     *            the embedding level information for each character. Values
     *            between -1 and -62 denote overrides at the level's absolute
     *            value, values from 1 to 62 indicate embeddings, and the 0
     *            value indicates the level is calculated by the algorithm
     *            automatically.
     * @param embStart
     *            the start offset of the embeddings array to perform the
     *            algorithm.
     * @param paragraphLength
     *            the length of the text to perform the algorithm. It must be
     *            text.length >= textStart + paragraphLength, and
     *            embeddings.length >= embStart + paragraphLength.
     * @param flags
     *            indicates the base direction of the bidirectional text. It is
     *            expected that this will be one of the direction constant
     *            values defined in this class. An unknown value is treated as
     *            DIRECTION_DEFAULT_LEFT_TO_RIGHT.
     * 
     * @see #DIRECTION_LEFT_TO_RIGHT
     * @see #DIRECTION_RIGHT_TO_LEFT
     * @see #DIRECTION_DEFAULT_RIGHT_TO_LEFT
     * @see #DIRECTION_DEFAULT_LEFT_TO_RIGHT
     */
    public Bidi(char[] text, int textStart, byte[] embeddings, int embStart,
            int paragraphLength, int flags) {

        if (text == null || text.length - textStart < paragraphLength) {
            throw new IllegalArgumentException();
        }

        if (embeddings != null) {
            if (embeddings.length - embStart < paragraphLength) {
                throw new IllegalArgumentException();
            }
        }

        if (textStart < 0) {
            // text.0D=Negative textStart value {0}
            throw new IllegalArgumentException(Messages.getString(
                    "text.0D", textStart)); //$NON-NLS-1$
        }
        if (embStart < 0) {
            // text.10=Negative embStart value {0}
            throw new IllegalArgumentException(Messages.getString(
                    "text.10", embStart)); //$NON-NLS-1$
        }
        if (paragraphLength < 0) {
            // text.11=Negative paragraph length {0}
            throw new IllegalArgumentException(Messages.getString(
                    "text.11", paragraphLength)); //$NON-NLS-1$
        }

        icuBidi = new com.ibm.icu.text.Bidi(text, textStart, embeddings, embStart, paragraphLength, flags);
    }

    /**
     * Create a Bidi object.
     * 
     * @param paragraph
     *            the String containing the paragraph text to perform the
     *            algorithm.
     * @param flags
     *            indicates the base direction of the bidirectional text. It is
     *            expected that this will be one of the direction constant
     *            values defined in this class. An unknown value is treated as
     *            DIRECTION_DEFAULT_LEFT_TO_RIGHT.
     * 
     * @see #DIRECTION_LEFT_TO_RIGHT
     * @see #DIRECTION_RIGHT_TO_LEFT
     * @see #DIRECTION_DEFAULT_RIGHT_TO_LEFT
     * @see #DIRECTION_DEFAULT_LEFT_TO_RIGHT
     */
    public Bidi(String paragraph, int flags) {
        this((paragraph == null ? null : paragraph.toCharArray()), 0, null, 0,
                (paragraph == null ? 0 : paragraph.length()), flags);
    }

    /* private constructor used by createLineBidi() */
    private Bidi(com.ibm.icu.text.Bidi bidi) {
        this.icuBidi = bidi;
    }

    /**
     * Return whether the base level is from left to right.
     * 
     * @return true if the base level is from left to right.
     */
    public boolean baseIsLeftToRight() {
        return icuBidi.baseIsLeftToRight();
    }

    /**
     * Create a new Bidi object containing the information of one line from this
     * object.
     * 
     * @param lineStart
     *            the start offset of the line.
     * @param lineLimit
     *            the limit of the line.
     * @return the new line Bidi object. In this new object, the indices will
     *         range from 0 to (limit - start - 1).
     */
    public Bidi createLineBidi(int lineStart, int lineLimit) {
        int length = icuBidi.getLength();
        if (lineStart < 0 || lineLimit < 0 || lineLimit > length
                || lineStart > lineLimit) {
            // text.12=Invalid ranges (start={0}, limit={1}, length={2})
            throw new IllegalArgumentException(Messages.getString(
                    "text.12", new Object[] { lineStart, lineLimit, length })); //$NON-NLS-1$
        }

        com.ibm.icu.text.Bidi lineBidi = icuBidi.createLineBidi(lineStart, lineLimit);
        Bidi bidi = new Bidi(lineBidi);
        return bidi;
    }

    /**
     * Return the base level.
     * 
     * @return the int value of the base level.
     */
    public int getBaseLevel() {
        return icuBidi.getBaseLevel();
    }

    /**
     * Return the length of the text in the Bidi object.
     * 
     * @return the int value of the length.
     */
    public int getLength() {
        return icuBidi.getLength();
    }

    /**
     * Return the level of a specified character.
     * 
     * @param offset
     *            the offset of the character.
     * @return the int value of the level.
     */
    public int getLevelAt(int offset) {
        try {
            return icuBidi.getLevelAt(offset);
        } catch (RuntimeException e) {
            return icuBidi.getBaseLevel();
        }
    }

    /**
     * Return the number of runs in the bidirectional text.
     * 
     * @return the int value of runs, at least 1.
     */
    public int getRunCount() {
        int runCount = icuBidi.getRunCount();
        return (runCount < 1) ? 1 : runCount;
    }

    /**
     * Return the level of a specified run.
     * 
     * @param run
     *            the index of the run.
     * @return the level of the run.
     */
    public int getRunLevel(int run) {
        if (icuBidi.getRunCount() == 0) {
            return icuBidi.getBaseLevel();
        } else {
            return icuBidi.getRunLevel(run);
        }
    }

    /**
     * Return the limit offset of a specified run.
     * 
     * @param run
     *            the index of the run.
     * @return the limit offset of the run.
     */
    public int getRunLimit(int run) {
        if (icuBidi.getRunCount() == 0) {
            return icuBidi.getLength();
        } else {
            return icuBidi.getRunLimit(run);
        }
    }

    /**
     * Return the start offset of a specified run.
     * 
     * @param run
     *            the index of the run.
     * @return the start offset of the run.
     */
    public int getRunStart(int run) {
        if (icuBidi.getRunCount() == 0) {
            return 0;
        } else {
            return icuBidi.getRunStart(run);
        }
    }

    /**
     * Return whether the text is from left to right, that is, both the base
     * direction and the text direction is from left to right.
     * 
     * @return true if the text is from left to right.
     */
    public boolean isLeftToRight() {
        return icuBidi.isLeftToRight();
    }

    /**
     * Return whether the text direction is mixed.
     * 
     * @return true if the text direction is mixed.
     */
    public boolean isMixed() {
        return icuBidi.isMixed();
    }

    /**
     * Return whether the text is from right to left, that is, both the base
     * direction and the text direction is from right to left.
     * 
     * @return true if the text is from right to left.
     */
    public boolean isRightToLeft() {
        return icuBidi.isRightToLeft();
    }

    /**
     * Reorder a range of objects according to their specified levels. This is a
     * convenience function that does not use a Bidi object. The range of
     * objects at index from objectStart to objectStart + count will be
     * reordered according to the range of levels at index from levelStart to
     * levelStart + count.
     * 
     * @param levels
     *            the level array, which is already determined.
     * @param levelStart
     *            the start offset of the range of the levels.
     * @param objects
     *            the object array to reorder.
     * @param objectStart
     *            the start offset of the range of objects.
     * @param count
     *            the count of the range of objects to reorder.
     */
    public static void reorderVisually(byte[] levels, int levelStart,
            Object[] objects, int objectStart, int count) {
        if (count < 0 || levelStart < 0 || objectStart < 0
                || count > levels.length - levelStart
                || count > objects.length - objectStart) {
            // text.13=Invalid ranges (levels={0}, levelStart={1}, objects={2},
            // objectStart={3}, count={4})
            throw new IllegalArgumentException(Messages.getString("text.13", //$NON-NLS-1$
                    new Object[] { levels.length, levelStart, objects.length,
                            objectStart, count }));
        }

        com.ibm.icu.text.Bidi.reorderVisually(levels, levelStart, objects, objectStart, count);
    }

    /**
     * Return whether a range of characters of a text requires a Bidi object to
     * display properly.
     * 
     * @param text
     *            the char array of the text.
     * @param start
     *            the start offset of the range of characters.
     * @param limit
     *            the limit offset of the range of characters.
     * @return true if the range of characters requires a Bidi object.
     */
    public static boolean requiresBidi(char[] text, int start, int limit) {
        //int length = text.length;
        if (limit < 0 || start < 0 || start > limit || limit > text.length) {
            throw new IllegalArgumentException();
        }

        return com.ibm.icu.text.Bidi.requiresBidi(text, start, limit);
    }

    /**
     * Return the internal message of the Bidi object, used in debugging.
     * 
     * @return a string containing the internal message.
     */
    @Override
    public String toString() {
        return icuBidi.toString();
    }
}
