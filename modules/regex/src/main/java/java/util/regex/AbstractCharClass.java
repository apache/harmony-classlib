/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.7.2.2 $
 */
package java.util.regex;

import java.util.BitSet;
import java.util.ListResourceBundle;

/**
 * This class represents character classes, i.e. 
 * sets of character either predefined or user defined.
 * 
 * Note, this class represent token, not node, so being 
 * constructed by lexer.
 * 
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.7.2.2 $
 */
abstract class AbstractCharClass extends SpecialToken {
    protected boolean alt;

    static PredefinedCharacterClasses charClasses = new PredefinedCharacterClasses();

    /**
     * Returns true if this char class contains character specified;
     * 
     * @param ch
     *            character to check;
     */
    abstract public boolean contains(int ch);

    /**
     * Returns BitSet representing this character class or <code>null</code>
     * if this character class does not have character representation;
     * 
     * @return bitset
     */
    protected BitSet getBits() {
        return null;
    }

    public int getType() {
        return SpecialToken.TOK_CHARCLASS;
    }

    public AbstractCharClass getInstance() {
        return this;
    }

    public boolean hasUCI() {
        return false;
    }

    /**
     * Sets this CharClass to negative form, i.e. if they will add some
     * characters and after that set this class to negative it will accept all
     * the characters except previously set ones.
     * 
     * Although this method will not alternate all the already set characters,
     * just overall meaning of the class.
     * 
     * @see #contains(int)
     * @see #intersect(CharClass)
     * @see #union(CharClass)
     */
    public AbstractCharClass setNegative(boolean value) {
        if (alt ^ value)
            alt = !alt;
        return this;
    }

    public boolean isNegative() {
        return alt;
    }

    // -----------------------------------------------------------------
    // Static methods and predefined classes
    // -----------------------------------------------------------------
    
    public static boolean intersects(char ch1, char ch2) {
        return ch1 == ch2;
    }

    public static boolean intersects(AbstractCharClass cc, char ch) {
        return cc.contains(ch);
    }

    public static boolean intersects(AbstractCharClass cc1,
            AbstractCharClass cc2) {
        if (cc1.getBits() == null || cc2.getBits() == null)
            return true;
        return cc1.getBits().intersects(cc2.getBits());
    }

    public static AbstractCharClass getPredefinedClass(String name,
            boolean negative) {
        return ((LazyCharClass) charClasses.getObject(name)).getValue(negative);
    }

    abstract static class LazyCharClass {
        AbstractCharClass posValue = null;

        AbstractCharClass negValue = null;

        public AbstractCharClass getValue(boolean negative) {
            if (!negative && posValue == null) {
                posValue = computeValue();
            } else if (negative && negValue == null) {
                negValue = computeValue().setNegative(true);
            }
            if (!negative)
                return posValue;
            return negValue;
        }

        protected abstract AbstractCharClass computeValue();
    }

    static class LazyDigit extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new CharClass().add('0', '9');
        }
    }

    static class LazyNonDigit extends LazyDigit {
        protected AbstractCharClass computeValue() {
            return super.computeValue().setNegative(true);
        }
    }

    static class LazySpace extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            /* 9-13 - \t\n\x0B\f\r; 32 - ' ' */
            return new CharClass().add(9, 13).add(32);
        }
    }

    static class LazyNonSpace extends LazySpace {
        protected AbstractCharClass computeValue() {
            return super.computeValue().setNegative(true);
        }
    }

    static class LazyWord extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new CharClass().add('a', 'z').add('A', 'Z').add('0', '9')
                    .add('_');
        }
    }

    static class LazyNonWord extends LazyWord {
        protected AbstractCharClass computeValue() {
            return super.computeValue().setNegative(true);
        }
    }

    static class LazyLower extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new CharClass().add('a', 'z');
        }
    }

    static class LazyUpper extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new CharClass().add('A', 'Z');
        }
    }

    static class LazyASCII extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new CharClass().add(0x00, 0x7F);
        }
    }

    static class LazyAlpha extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new CharClass().add('a', 'z').add('A', 'Z');
        }
    }

    static class LazyAlnum extends LazyAlpha {
        protected AbstractCharClass computeValue() {
            return ((CharClass) super.computeValue()).add('0', '9');
        }
    }

    static class LazyPunct extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            /* Punctuation !"#$%&'()*+,-./:;<=>?@ [\]^_` {|}~ */
            return new CharClass().add(0x21, 0x40).add(0x5B, 0x60).add(0x7B,
                    0x7E);
        }
    }

    static class LazyGraph extends LazyAlnum {
        protected AbstractCharClass computeValue() {
            /* plus punctiation */
            return ((CharClass) super.computeValue()).add(0x21, 0x40).add(0x5B,
                    0x60).add(0x7B, 0x7E);
        }
    }

    static class LazyPrint extends LazyGraph {
        protected AbstractCharClass computeValue() {
            return ((CharClass) super.computeValue()).add(0x20);
        }
    }

    static class LazyBlank extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new CharClass().add(' ').add('\t');
        }
    }

    static class LazyCntrl extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new CharClass().add(0x00, 0x1F).add(0x7F);
        }
    }

    static class LazyXDigit extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new CharClass().add('0', '9').add('a', 'f').add('A', 'F');
        }
    }

    static class LazyRange extends LazyCharClass {
        int start, end;

        public LazyRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public AbstractCharClass computeValue() {
            return new CharClass().add(start, end);
        }
    }

    static class LazySpecialsBlock extends LazyCharClass {
        public AbstractCharClass computeValue() {
            return new CharClass().add(0xFEFF, 0xFEFF).add(0xFFF0, 0xFFFD);
        }
    }

    static class LazyCategoryScope extends LazyCharClass {
        int category;

        public LazyCategoryScope(int cat) {
            this.category = cat;
        }

        protected AbstractCharClass computeValue() {
            return new UnicodeCategoryScope(category);
        }
    }

    static class LazyCategory extends LazyCharClass {
        int category;

        public LazyCategory(int cat) {
            this.category = cat;
        }

        protected AbstractCharClass computeValue() {
            return new UnicodeCategory(category);
        }
    }

    static class LazyJavaLowerCase extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isLowerCase((char) ch);
                }
            };
        }
    }

    static class LazyJavaUpperCase extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isUpperCase((char) ch);
                }
            };
        }
    }

    static class LazyJavaWhitespace extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isWhitespace((char) ch);
                }
            };
        }
    }

    static class LazyJavaMirrored extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isMirrored((char) ch);
                }
            };
        }
    }
    
    static class LazyJavaDefined extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isDefined((char) ch);
                }
            };
        }
    }

    static class LazyJavaDigit extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isDigit((char) ch);
                }
            };
        }
    }

    static class LazyJavaIdentifierIgnorable extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isIdentifierIgnorable((char) ch);
                }
            };
        }
    }

    static class LazyJavaISOControl extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isISOControl((char) ch);
                }
            };
        }
    }

    static class LazyJavaJavaIdentifierPart extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isJavaIdentifierPart((char) ch);
                }
            };
        }
    }

    static class LazyJavaJavaIdentifierStart extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isJavaIdentifierStart((char) ch);
                }
            };
        }
    }

    static class LazyJavaLetter extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isLetter((char) ch);
                }
            };
        }
    }

    static class LazyJavaLetterOrDigit extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isLetterOrDigit((char) ch);
                }
            };
        }
    }

    static class LazyJavaSpaceChar extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isSpaceChar((char) ch);
                }
            };
        }
    }

    static class LazyJavaTitleCase extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isTitleCase((char) ch);
                }
            };
        }
    }

    static class LazyJavaUnicodeIdentifierPart extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isUnicodeIdentifierPart((char) ch);
                }
            };
        }
    }

    static class LazyJavaUnicodeIdentifierStart extends LazyCharClass {
        protected AbstractCharClass computeValue() {
            return new AbstractCharClass() {
                public boolean contains(int ch) {
                    return Character.isUnicodeIdentifierStart((char) ch);
                }
            };
        }
    }

    /**
     * character classes generated from 
     * http://www.unicode.org/reports/tr18/
     * http://www.unicode.org/Public/4.1.0/ucd/Blocks.txt
     */
    static final class PredefinedCharacterClasses extends ListResourceBundle {
        static LazyCharClass space = new LazySpace();

        static LazyCharClass digit = new LazyDigit();

        static final Object[][] contents = {
                { "Lower", new LazyLower() },
                { "Upper", new LazyUpper() },
                { "ASCII", new LazyASCII() },
                { "Alpha", new LazyAlpha() },
                { "Digit", digit },
                { "Alnum", new LazyAlnum() },
                { "Punct", new LazyPunct() },
                { "Graph", new LazyGraph() },
                { "Print", new LazyPrint() },
                { "Blank", new LazyBlank() },
                { "Cntrl", new LazyCntrl() },
                { "XDigit", new LazyXDigit() },
                { "javaLowerCase", new LazyJavaLowerCase() },
                { "javaUpperCase", new LazyJavaUpperCase() },
                { "javaWhitespace", new LazyJavaWhitespace() },
                { "javaMirrored", new LazyJavaMirrored() },
                { "javaDefined", new LazyJavaDefined() },
                { "javaDigit", new LazyJavaDigit() },
                { "javaIdentifierIgnorable", new LazyJavaIdentifierIgnorable() },
                { "javaISOControl", new LazyJavaISOControl() },
                { "javaJavaIdentifierPart", new LazyJavaJavaIdentifierPart() },
                { "javaJavaIdentifierStart", new LazyJavaJavaIdentifierStart() },
                { "javaLetter", new LazyJavaLetter() },
                { "javaLetterOrDigit", new LazyJavaLetterOrDigit() },
                { "javaSpaceChar", new LazyJavaSpaceChar() },
                { "javaTitleCase", new LazyJavaTitleCase() },
                { "javaUnicodeIdentifierPart", new LazyJavaUnicodeIdentifierPart() },
                { "javaUnicodeIdentifierStart", new LazyJavaUnicodeIdentifierStart() },
                { "Space", space },
                { "w", new LazyWord() },
                { "W", new LazyNonWord() },
                { "s", space },
                { "S", new LazyNonSpace() },
                { "d", digit },
                { "D", new LazyNonDigit() },
                { "BasicLatin", new LazyRange(0x0000, 0x007F) },
                { "Latin-1Supplement", new LazyRange(0x0080, 0x00FF) },
                { "LatinExtended-A", new LazyRange(0x0100, 0x017F) },
                { "LatinExtended-B", new LazyRange(0x0180, 0x024F) },
                { "IPAExtensions", new LazyRange(0x0250, 0x02AF) },
                { "SpacingModifierLetters", new LazyRange(0x02B0, 0x02FF) },
                { "CombiningDiacriticalMarks", new LazyRange(0x0300, 0x036F) },
                { "Greek", new LazyRange(0x0370, 0x03FF) },
                { "Cyrillic", new LazyRange(0x0400, 0x04FF) },
                { "CyrillicSupplement", new LazyRange(0x0500, 0x052F) },
                { "Armenian", new LazyRange(0x0530, 0x058F) },
                { "Hebrew", new LazyRange(0x0590, 0x05FF) },
                { "Arabic", new LazyRange(0x0600, 0x06FF) },
                { "Syriac", new LazyRange(0x0700, 0x074F) },
                { "ArabicSupplement", new LazyRange(0x0750, 0x077F) },
                { "Thaana", new LazyRange(0x0780, 0x07BF) },
                { "Devanagari", new LazyRange(0x0900, 0x097F) },
                { "Bengali", new LazyRange(0x0980, 0x09FF) },
                { "Gurmukhi", new LazyRange(0x0A00, 0x0A7F) },
                { "Gujarati", new LazyRange(0x0A80, 0x0AFF) },
                { "Oriya", new LazyRange(0x0B00, 0x0B7F) },
                { "Tamil", new LazyRange(0x0B80, 0x0BFF) },
                { "Telugu", new LazyRange(0x0C00, 0x0C7F) },
                { "Kannada", new LazyRange(0x0C80, 0x0CFF) },
                { "Malayalam", new LazyRange(0x0D00, 0x0D7F) },
                { "Sinhala", new LazyRange(0x0D80, 0x0DFF) },
                { "Thai", new LazyRange(0x0E00, 0x0E7F) },
                { "Lao", new LazyRange(0x0E80, 0x0EFF) },
                { "Tibetan", new LazyRange(0x0F00, 0x0FFF) },
                { "Myanmar", new LazyRange(0x1000, 0x109F) },
                { "Georgian", new LazyRange(0x10A0, 0x10FF) },
                { "HangulJamo", new LazyRange(0x1100, 0x11FF) },
                { "Ethiopic", new LazyRange(0x1200, 0x137F) },
                { "EthiopicSupplement", new LazyRange(0x1380, 0x139F) },
                { "Cherokee", new LazyRange(0x13A0, 0x13FF) },
                { "UnifiedCanadianAboriginalSyllabics",
                        new LazyRange(0x1400, 0x167F) },
                { "Ogham", new LazyRange(0x1680, 0x169F) },
                { "Runic", new LazyRange(0x16A0, 0x16FF) },
                { "Tagalog", new LazyRange(0x1700, 0x171F) },
                { "Hanunoo", new LazyRange(0x1720, 0x173F) },
                { "Buhid", new LazyRange(0x1740, 0x175F) },
                { "Tagbanwa", new LazyRange(0x1760, 0x177F) },
                { "Khmer", new LazyRange(0x1780, 0x17FF) },
                { "Mongolian", new LazyRange(0x1800, 0x18AF) },
                { "Limbu", new LazyRange(0x1900, 0x194F) },
                { "TaiLe", new LazyRange(0x1950, 0x197F) },
                { "NewTaiLue", new LazyRange(0x1980, 0x19DF) },
                { "KhmerSymbols", new LazyRange(0x19E0, 0x19FF) },
                { "Buginese", new LazyRange(0x1A00, 0x1A1F) },
                { "PhoneticExtensions", new LazyRange(0x1D00, 0x1D7F) },
                { "PhoneticExtensionsSupplement", new LazyRange(0x1D80, 0x1DBF) },
                { "CombiningDiacriticalMarksSupplement",
                        new LazyRange(0x1DC0, 0x1DFF) },
                { "LatinExtendedAdditional", new LazyRange(0x1E00, 0x1EFF) },
                { "GreekExtended", new LazyRange(0x1F00, 0x1FFF) },
                { "GeneralPunctuation", new LazyRange(0x2000, 0x206F) },
                { "SuperscriptsandSubscripts", new LazyRange(0x2070, 0x209F) },
                { "CurrencySymbols", new LazyRange(0x20A0, 0x20CF) },
                { "CombiningMarksforSymbols", new LazyRange(0x20D0, 0x20FF) },
                { "LetterlikeSymbols", new LazyRange(0x2100, 0x214F) },
                { "NumberForms", new LazyRange(0x2150, 0x218F) },
                { "Arrows", new LazyRange(0x2190, 0x21FF) },
                { "MathematicalOperators", new LazyRange(0x2200, 0x22FF) },
                { "MiscellaneousTechnical", new LazyRange(0x2300, 0x23FF) },
                { "ControlPictures", new LazyRange(0x2400, 0x243F) },
                { "OpticalCharacterRecognition", new LazyRange(0x2440, 0x245F) },
                { "EnclosedAlphanumerics", new LazyRange(0x2460, 0x24FF) },
                { "BoxDrawing", new LazyRange(0x2500, 0x257F) },
                { "BlockElements", new LazyRange(0x2580, 0x259F) },
                { "GeometricShapes", new LazyRange(0x25A0, 0x25FF) },
                { "MiscellaneousSymbols", new LazyRange(0x2600, 0x26FF) },
                { "Dingbats", new LazyRange(0x2700, 0x27BF) },
                { "MiscellaneousMathematicalSymbols-A",
                        new LazyRange(0x27C0, 0x27EF) },
                { "SupplementalArrows-A", new LazyRange(0x27F0, 0x27FF) },
                { "BraillePatterns", new LazyRange(0x2800, 0x28FF) },
                { "SupplementalArrows-B", new LazyRange(0x2900, 0x297F) },
                { "MiscellaneousMathematicalSymbols-B",
                        new LazyRange(0x2980, 0x29FF) },
                { "SupplementalMathematicalOperators",
                        new LazyRange(0x2A00, 0x2AFF) },
                { "MiscellaneousSymbolsandArrows",
                        new LazyRange(0x2B00, 0x2BFF) },
                { "Glagolitic", new LazyRange(0x2C00, 0x2C5F) },
                { "Coptic", new LazyRange(0x2C80, 0x2CFF) },
                { "GeorgianSupplement", new LazyRange(0x2D00, 0x2D2F) },
                { "Tifinagh", new LazyRange(0x2D30, 0x2D7F) },
                { "EthiopicExtended", new LazyRange(0x2D80, 0x2DDF) },
                { "SupplementalPunctuation", new LazyRange(0x2E00, 0x2E7F) },
                { "CJKRadicalsSupplement", new LazyRange(0x2E80, 0x2EFF) },
                { "KangxiRadicals", new LazyRange(0x2F00, 0x2FDF) },
                { "IdeographicDescriptionCharacters",
                        new LazyRange(0x2FF0, 0x2FFF) },
                { "CJKSymbolsandPunctuation", new LazyRange(0x3000, 0x303F) },
                { "Hiragana", new LazyRange(0x3040, 0x309F) },
                { "Katakana", new LazyRange(0x30A0, 0x30FF) },
                { "Bopomofo", new LazyRange(0x3100, 0x312F) },
                { "HangulCompatibilityJamo", new LazyRange(0x3130, 0x318F) },
                { "Kanbun", new LazyRange(0x3190, 0x319F) },
                { "BopomofoExtended", new LazyRange(0x31A0, 0x31BF) },
                { "CJKStrokes", new LazyRange(0x31C0, 0x31EF) },
                { "KatakanaPhoneticExtensions", new LazyRange(0x31F0, 0x31FF) },
                { "EnclosedCJKLettersandMonths", new LazyRange(0x3200, 0x32FF) },
                { "CJKCompatibility", new LazyRange(0x3300, 0x33FF) },
                { "CJKUnifiedIdeographsExtensionA",
                        new LazyRange(0x3400, 0x4DB5) },
                { "YijingHexagramSymbols", new LazyRange(0x4DC0, 0x4DFF) },
                { "CJKUnifiedIdeographs", new LazyRange(0x4E00, 0x9FFF) },
                { "YiSyllables", new LazyRange(0xA000, 0xA48F) },
                { "YiRadicals", new LazyRange(0xA490, 0xA4CF) },
                { "ModifierToneLetters", new LazyRange(0xA700, 0xA71F) },
                { "SylotiNagri", new LazyRange(0xA800, 0xA82F) },
                { "HangulSyllables", new LazyRange(0xAC00, 0xD7A3) },
                { "HighSurrogates", new LazyRange(0xD800, 0xDB7F) },
                { "HighPrivateUseSurrogates", new LazyRange(0xDB80, 0xDBFF) },
                { "LowSurrogates", new LazyRange(0xDC00, 0xDFFF) },
                { "PrivateUseArea", new LazyRange(0xE000, 0xF8FF) },
                { "CJKCompatibilityIdeographs", new LazyRange(0xF900, 0xFAFF) },
                { "AlphabeticPresentationForms", new LazyRange(0xFB00, 0xFB4F) },
                { "ArabicPresentationForms-A", new LazyRange(0xFB50, 0xFDFF) },
                { "VariationSelectors", new LazyRange(0xFE00, 0xFE0F) },
                { "VerticalForms", new LazyRange(0xFE10, 0xFE1F) },
                { "CombiningHalfMarks", new LazyRange(0xFE20, 0xFE2F) },
                { "CJKCompatibilityForms", new LazyRange(0xFE30, 0xFE4F) },
                { "SmallFormVariants", new LazyRange(0xFE50, 0xFE6F) },
                { "ArabicPresentationForms-B", new LazyRange(0xFE70, 0xFEFF) },
                { "HalfwidthandFullwidthForms", new LazyRange(0xFF00, 0xFFEF) },
                { "Specials", new LazySpecialsBlock() },
                { "Cn", new LazyCategory(Character.UNASSIGNED) },
                { "IsL", new LazyCategoryScope(0x3E) },
                { "Lu", new LazyCategory(Character.UPPERCASE_LETTER) },
                { "Ll", new LazyCategory(Character.LOWERCASE_LETTER) },
                { "Lt", new LazyCategory(Character.TITLECASE_LETTER) },
                { "Lm", new LazyCategory(Character.MODIFIER_LETTER) },
                { "Lo", new LazyCategory(Character.OTHER_LETTER) },
                { "IsM", new LazyCategoryScope(0x1C0) },
                { "Mn", new LazyCategory(Character.NON_SPACING_MARK) },
                { "Me", new LazyCategory(Character.ENCLOSING_MARK) },
                { "Mc", new LazyCategory(Character.COMBINING_SPACING_MARK) },
                { "N", new LazyCategoryScope(0xE00) },
                { "Nd", new LazyCategory(Character.DECIMAL_DIGIT_NUMBER) },
                { "Nl", new LazyCategory(Character.LETTER_NUMBER) },
                { "No", new LazyCategory(Character.OTHER_NUMBER) },
                { "IsZ", new LazyCategoryScope(0x7000) },
                { "Zs", new LazyCategory(Character.SPACE_SEPARATOR) },
                { "Zl", new LazyCategory(Character.LINE_SEPARATOR) },
                { "Zp", new LazyCategory(Character.PARAGRAPH_SEPARATOR) },
                { "IsC", new LazyCategoryScope(0xF0000) },
                { "Cc", new LazyCategory(Character.CONTROL) },
                { "Cf", new LazyCategory(Character.FORMAT) },
                { "Co", new LazyCategory(Character.PRIVATE_USE) },
                { "Cs", new LazyCategory(Character.SURROGATE) },
                { "IsP", new LazyCategoryScope(0xF8000) },
                { "Pd", new LazyCategory(Character.DASH_PUNCTUATION) },
                { "Ps", new LazyCategory(Character.START_PUNCTUATION) },
                { "Pe", new LazyCategory(Character.END_PUNCTUATION) },
                { "Pc", new LazyCategory(Character.CONNECTOR_PUNCTUATION) },
                { "Po", new LazyCategory(Character.OTHER_PUNCTUATION) },
                { "IsS", new LazyCategoryScope(0x7E000000) },
                { "Sm", new LazyCategory(Character.MATH_SYMBOL) },
                { "Sc", new LazyCategory(Character.CURRENCY_SYMBOL) },
                { "Sk", new LazyCategory(Character.MODIFIER_SYMBOL) },
                { "So", new LazyCategory(Character.OTHER_SYMBOL) },
                { "Pi", new LazyCategory(Character.INITIAL_QUOTE_PUNCTUATION) },
                { "Pf", new LazyCategory(Character.FINAL_QUOTE_PUNCTUATION) } };

        public Object[][] getContents() {
            return contents;
        }
    }
}
