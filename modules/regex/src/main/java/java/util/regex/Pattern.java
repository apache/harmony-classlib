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
 * @version $Revision: 1.36.2.2 $
 */
package java.util.regex;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * @com.intel.drl.spec_ref
 * 
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.36.2.2 $
 */
public final class Pattern implements Serializable {
    
    private static final long serialVersionUID = 5073258162644648461L;
    
    static final boolean _DEBUG_ = false;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int UNIX_LINES = 1 << 0;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int CASE_INSENSITIVE = 1 << 1;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int COMMENTS = 1 << 2;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int MULTILINE = 1 << 3;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int LITERAL = 1 << 4;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int DOTALL = 1 << 5;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int UNICODE_CASE = 1 << 6;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int CANON_EQ = 1 << 7;

    /**
     * Current <code>pattern</code> to be compiled;
     */
    private Lexer lexemes = null;

    /**
     * Pattern compile flags;
     */
    private int flags = 0;

    private String pattern = null;

    transient private int groupIndex = -1;

    transient private int globalGroupIndex = -1;

    transient private int compCount = -1;

    transient private int consCount = -1;

    transient AbstractSet start = null;

    /**
     * @com.intel.drl.spec_ref
     */
    public Matcher matcher(CharSequence cs) {
        return new Matcher(this, cs);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String[] split(CharSequence input, int limit) {
        ArrayList res = new ArrayList();
        Matcher mat = matcher(input);
        int index = 0;
        int curPos = 0;       
        
        if (input.length() == 0) {
            return new String [] {""};
        } else {
            while (mat.find() && (index + 1 < limit || limit <= 0)) {
                  res.add(input.subSequence(curPos, mat.start()).toString());
                  curPos = mat.end();
                  index++;
            }
                
            res.add(input.subSequence(curPos, input.length()).toString());
            index++;
                             
            /*
             * discard trailing empty strings
             */
            if (limit == 0) {
                while (--index >= 0 && res.get(index).toString().length() == 0) {
                       res.remove(index);
                }
            }
        }
        return (String[]) res.toArray(new String[index >= 0 ? index : 0]);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String[] split(CharSequence input) {
        return split(input, 0);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String pattern() {
        return lexemes.toString();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        return this.pattern();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int flags() {
        return this.flags;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static Pattern compile(String regex, int flags)
            throws PatternSyntaxException {
        AbstractSet.counter = 1;

        return new Pattern().compileImpl(regex, flags);
    }

    /**
     * 
     * @param pattern -
     *            Regular expression to be compiled
     * @param flags -
     *            The bit mask including CASE_INSENSITIVE, MULTILINE, DOTALL,
     *            UNICODE_CASE, and CANON_EQ
     * 
     * @return Compiled pattern
     */
    private Pattern compileImpl(String regex, int flags)
            throws PatternSyntaxException {
        this.lexemes = new Lexer(regex, flags);
        this.flags = flags;

        start = processExpression(-1, flags, null);
        if (!lexemes.isEmpty()) {
            throw new PatternSyntaxException(I18n
                    .getMessage("Trailing characters"), lexemes.toString(),
                    lexemes.getIndex());
        }

        return this;
    }

    /**
     * A->(a|)+
     */
    private AbstractSet processAlternations(AbstractSet last) {
        CharClass auxRange = new CharClass(hasFlag(Pattern.CASE_INSENSITIVE),
                hasFlag(Pattern.UNICODE_CASE));
        while (!lexemes.isEmpty()
                && lexemes.isLetter()
                && (lexemes.lookAhead() == 0
                        || lexemes.lookAhead() == Lexer.CHAR_VERTICAL_BAR || lexemes
                        .lookAhead() == Lexer.CHAR_RIGHT_PARENTHESIS)) {
            auxRange.add(lexemes.next());
            if (lexemes.peek() == Lexer.CHAR_VERTICAL_BAR)
                lexemes.next();
        }

        if (!auxRange.hasUCI()) {
            return new RangeSet(auxRange, last);
        } else {
            return new UCIRangeSet(auxRange, last);
        }
    }

    /**
     * E->AE; E->S|E; E->S; A->(a|)+ E->S(|S)*
     */
    private AbstractSet processExpression(int ch, int new_flags,
            AbstractSet last) {
        ArrayList children = new ArrayList();
        AbstractSet child;
        int safe_flags = flags;
        FSet fSet;

        if (new_flags != flags) {
            flags = new_flags;
            lexemes.setFlags(flags);
        }

        switch (ch) {
        case Lexer.CHAR_NONCAP_GROUP: {
            fSet = new NonCapFSet(++consCount);
            break;
        }

        case Lexer.CHAR_POS_LOOKAHEAD:
        case Lexer.CHAR_NEG_LOOKAHEAD: {
            fSet = new AheadFSet();
            break;
        }
        case Lexer.CHAR_POS_LOOKBEHIND:
        case Lexer.CHAR_NEG_LOOKBEHIND: {
            fSet = new BehindFSet(++consCount);
            break;
        }

        case Lexer.CHAR_ATOMIC_GROUP: {
            fSet = new AtomicFSet(++consCount);
            break;
        }

        default: {
            globalGroupIndex++;
            if (last == null) {
                fSet = new FinalSet();
                // expr = new StartSet();
            } else {
                fSet = new FSet(globalGroupIndex);
                // expr = new JointSet(globalGroupIndex);
            }
        }
        }

        do {
            if (lexemes.isLetter()
                    && lexemes.lookAhead() == Lexer.CHAR_VERTICAL_BAR) {
                child = processAlternations(fSet);
            } else {
                child = processSubExpression(fSet);
                if (lexemes.peek() == Lexer.CHAR_VERTICAL_BAR)
                    lexemes.next();
            }
            if (child != null)
                children.add(child);
            // expr.addChild(child);
        } while (!(lexemes.isEmpty() || lexemes.peek() == Lexer.CHAR_RIGHT_PARENTHESIS));

        if (flags != safe_flags) {
            flags = safe_flags;
            lexemes.setFlags(flags);
        }

        switch (ch) {
        case Lexer.CHAR_NONCAP_GROUP: {
            return new NonCapJointSet(children, fSet);
        }
        case Lexer.CHAR_POS_LOOKAHEAD: {
            return new PositiveLookAhead(children, fSet);
        }

        case Lexer.CHAR_NEG_LOOKAHEAD: {
            return new NegativeLookAhead(children, fSet);
        }

        case Lexer.CHAR_POS_LOOKBEHIND: {
            return new PositiveLookBehind(children, fSet);
        }

        case Lexer.CHAR_NEG_LOOKBEHIND: {
            return new NegativeLookBehind(children, fSet);
        }

        case Lexer.CHAR_ATOMIC_GROUP: {
            return new AtomicJointSet(children, fSet);
        }
        default: {
            switch (children.size()) {
            case 0:
                return new EmptySet(fSet);
            case 1:
                return new SingleSet((AbstractSet) children.get(0), fSet);
            default:
                return new JointSet(children, fSet);
            }
        }
        }
    }

    /**
     * T->a+
     */
    private AbstractSet processSequence(AbstractSet last) {
        StringBuffer substring = new StringBuffer();
        while (!lexemes.isEmpty()
                && lexemes.isLetter()
                && ((!lexemes.isNextSpecial() && lexemes.lookAhead() == 0) // end
                        // of
                        // pattern
                        || (!lexemes.isNextSpecial() && Lexer.isLetter(lexemes
                                .lookAhead()))
                        || lexemes.lookAhead() == Lexer.CHAR_RIGHT_PARENTHESIS
                        || (lexemes.lookAhead() & 0x8000ffff) == Lexer.CHAR_LEFT_PARENTHESIS
                        || lexemes.lookAhead() == Lexer.CHAR_VERTICAL_BAR || lexemes
                        .lookAhead() == Lexer.CHAR_DOLLAR)) {
            substring.append((char) lexemes.next());
        }
        if (!hasFlag(Pattern.CASE_INSENSITIVE)) {
            return new SequenceSet(substring);
        } else if (!hasFlag(Pattern.UNICODE_CASE)) {
            return new CISequenceSet(substring);
        } else {
            return new UCISequenceSet(substring);
        }
    }

    /**
     * S->BS; S->QS; S->Q; B->a+
     */
    private AbstractSet processSubExpression(AbstractSet last) {
        AbstractSet cur;
        if (lexemes.isLetter() && !lexemes.isNextSpecial()
                && Lexer.isLetter(lexemes.lookAhead())) {
            cur = processSequence(last);
        } else {
            cur = processQuantifier(last);
        }

        if (!lexemes.isEmpty()
        // && !pattern.isQuantifier()
                && lexemes.peek() != Lexer.CHAR_RIGHT_PARENTHESIS
                && lexemes.peek() != Lexer.CHAR_VERTICAL_BAR) {
            AbstractSet next = processSubExpression(last);
            if (cur instanceof LeafQuantifierSet
            // TODO create personal UnifiedQuantifierSet for composite
                    // quantifiers
                    // to take into account Quantifier counters
                    // ////
                    && !(cur instanceof CompositeQuantifierSet)
                    && !(cur instanceof GroupQuantifierSet)
                    && !next.first(((LeafQuantifierSet) cur).getInnerSet())) {
                cur = new UnifiedQuantifierSet((LeafQuantifierSet) cur);
            }
            if (((char) next.getType()) == '+') {
                cur.setNext(((LeafQuantifierSet) next).getInnerSet());
            } else {
                cur.setNext(next);
            }
        } else if (cur != null) {
            cur.setNext(last);
        } else {
            return null;
        }

        if (((char) cur.getType()) == '+') {
            return ((QuantifierSet) cur).getInnerSet();
        } else {
            return cur;
        }
    }

    /**
     * Q->T(*|+|?...) also do some optimizations.
     * 
     */
    private AbstractSet processQuantifier(AbstractSet last) {
        AbstractSet term = processTerminal(last);
        SpecialToken quantifier = null;

        int quant = lexemes.peek();

        if (term != null && !(term instanceof LeafSet)) {
            switch (quant) {
            case Lexer.QUANT_STAR:
            case Lexer.QUANT_PLUS: {
                lexemes.next();
                GroupQuantifierSet q = new GroupQuantifierSet(term, last, quant);
                term.setNext(q);
                return q;
            }

            case Lexer.QUANT_STAR_R:
            case Lexer.QUANT_PLUS_R: {
                lexemes.next();
                GroupQuantifierSet q = new ReluctantGroupQuantifierSet(term,
                        last, quant);
                term.setNext(q);
                return q;
            }

            case Lexer.QUANT_PLUS_P: {
                lexemes.next();
                // possessive plus will be handled by unique class
                // and should not be postprocessed to point previous set
                // to the inner one.
                // //
                return new PosPlusGroupQuantifierSet(term, last,
                        Lexer.QUANT_STAR_P);
            }

            case Lexer.QUANT_STAR_P: {
                lexemes.next();
                return new PossessiveGroupQuantifierSet(term, last, quant);
            }

            case Lexer.QUANT_ALT: {
                lexemes.next();
                AltGroupQuantifierSet q = new AltGroupQuantifierSet(term, last,
                        Lexer.QUANT_ALT);
                term.setNext(last);
                return q;
            }

            case Lexer.QUANT_ALT_P: {
                lexemes.next();
                return new PosAltGroupQuantifierSet(term, last, Lexer.QUANT_ALT);
            }

            case Lexer.QUANT_ALT_R: {
                lexemes.next();
                RelAltGroupQuantifierSet q = new RelAltGroupQuantifierSet(term,
                        last, Lexer.QUANT_ALT);
                term.setNext(last);
                return q;
            }

            case Lexer.QUANT_COMP: {
                CompositeGroupQuantifierSet q = new CompositeGroupQuantifierSet(
                        (Quantifier) lexemes.nextSpecial(), term, last,
                        Lexer.QUANT_ALT, ++compCount);
                term.setNext(q);
                return q;
            }

            case Lexer.QUANT_COMP_P: {
                return new PosCompositeGroupQuantifierSet((Quantifier) lexemes
                        .nextSpecial(), term, last, Lexer.QUANT_ALT,
                        ++compCount);
            }

            case Lexer.QUANT_COMP_R: {
                RelCompositeGroupQuantifierSet q = new RelCompositeGroupQuantifierSet(
                        (Quantifier) lexemes.nextSpecial(), term, last,
                        Lexer.QUANT_ALT, ++compCount);
                term.setNext(q);
                return q;
            }

            default:
                return term;
            }
        } else {
            LeafSet leaf = null;
            if (term != null)
                leaf = (LeafSet) term;
            switch (quant) {
            case Lexer.QUANT_STAR:
            case Lexer.QUANT_PLUS: {
                lexemes.next();
                LeafQuantifierSet q;
                if (term.getType() == AbstractSet.TYPE_DOTSET) {
                    if (!hasFlag(Pattern.DOTALL)) {
                        q = new DotQuantifierSet(leaf, last, quant,
                                AbstractLineTerminator.getInstance(flags));
                    } else {
                        q = new DotAllQuantifierSet(leaf, last, quant);
                    }
                } else {
                    q = new LeafQuantifierSet(leaf, last, quant);
                }
                leaf.setNext(q);
                return q;
            }

            case Lexer.QUANT_STAR_R:
            case Lexer.QUANT_PLUS_R: {
                lexemes.next();
                ReluctantQuantifierSet q = new ReluctantQuantifierSet(leaf,
                        last, quant);
                leaf.setNext(q);
                return q;
            }

            case Lexer.QUANT_PLUS_P:
            case Lexer.QUANT_STAR_P: {
                lexemes.next();
                PossessiveQuantifierSet q = new PossessiveQuantifierSet(leaf,
                        last, quant);
                leaf.setNext(q);
                return q;
            }

            case Lexer.QUANT_ALT: {
                lexemes.next();
                return new AltQuantifierSet(leaf, last, Lexer.QUANT_ALT);
            }

            case Lexer.QUANT_ALT_P: {
                lexemes.next();
                return new PossessiveAltQuantifierSet(leaf, last,
                        Lexer.QUANT_ALT_P);
            }

            case Lexer.QUANT_ALT_R: {
                lexemes.next();
                return new ReluctantAltQuantifierSet(leaf, last,
                        Lexer.QUANT_ALT_R);
            }

            case Lexer.QUANT_COMP: {
                return new CompositeQuantifierSet((Quantifier) lexemes
                        .nextSpecial(), leaf, last, Lexer.QUANT_COMP);
            }

            case Lexer.QUANT_COMP_P: {
                return new PossessiveCompositeQuantifierSet(
                        (Quantifier) lexemes.nextSpecial(), leaf, last,
                        Lexer.QUANT_COMP_P);
            }
            case Lexer.QUANT_COMP_R: {
                return new ReluctantCompositeQuantifierSet((Quantifier) lexemes
                        .nextSpecial(), leaf, last, Lexer.QUANT_COMP_R);
            }

            default:
                return term;
            }
        }
    }

    /**
     * T-> letter|[range]|{char-class}|(E)
     */
    private AbstractSet processTerminal(AbstractSet last) {
        int ch;
        AbstractSet term = null;
        do {
            ch = lexemes.peek();
            if ((ch & 0x8000ffff) == Lexer.CHAR_LEFT_PARENTHESIS) {
                lexemes.next();
                int new_flags = (ch & 0x00ff0000) >> 16;
                ch = ch & 0xff00ffff;

                if (ch == Lexer.CHAR_FLAGS) {
                    flags = new_flags;
                    lexemes.setFlags(new_flags);
                } else {
                    new_flags = (ch == Lexer.CHAR_NONCAP_GROUP) ? new_flags
                            : flags;
                    term = processExpression(ch, new_flags, last);
                    if (lexemes.peek() != Lexer.CHAR_RIGHT_PARENTHESIS)
                        throw new PatternSyntaxException(I18n
                                .getMessage("unmatched ("), lexemes.toString(),
                                lexemes.getIndex()-1);
                    lexemes.next();
                }

            } else
                switch (ch) {
                case Lexer.CHAR_LEFT_SQUARE_BRACKET: {
                    lexemes.next();
                    boolean negative = false;
                    if (lexemes.peek() == Lexer.CHAR_CARET) {
                        negative = true;
                        lexemes.next();
                    }

                    term = processRange(negative, last);
                    if (lexemes.peek() != Lexer.CHAR_RIGHT_SQUARE_BRACKET)
                        throw new PatternSyntaxException(I18n
                                .getMessage("unmatched ["), lexemes.toString(),
                                lexemes.getIndex());
                    lexemes.setMode(Lexer.MODE_PATTERN);
                    lexemes.next();
                    break;
                }

                case Lexer.CHAR_DOT: {
                    lexemes.next();

                    if (!hasFlag(Pattern.DOTALL)) {
                        term = new DotSet(AbstractLineTerminator
                                .getInstance(flags));
                    } else {
                        term = new DotAllSet();
                    }

                    break;
                }

                case Lexer.CHAR_CARET: {
                    lexemes.next();
                    consCount++;
                    if (!hasFlag(Pattern.MULTILINE)) {
                        term = new SOLSet();
                    } else {
                        term = new MultiLineSOLSet(AbstractLineTerminator
                                .getInstance(flags));
                    }

                    break;
                }

                case Lexer.CHAR_DOLLAR: {
                    lexemes.next();
                    consCount++;
                    if (!hasFlag(Pattern.MULTILINE)) {
                        if (!hasFlag(Pattern.UNIX_LINES)) {
                            term = new EOLSet(consCount);
                        } else {
                            term = new UEOLSet(consCount);
                        }
                    } else {
                        if (!hasFlag(Pattern.UNIX_LINES)) {
                            term = new MultiLineEOLSet(consCount);
                        } else {
                            term = new UMultiLineEOLSet(consCount);
                        }
                    }

                    break;
                }

                case Lexer.CHAR_WORD_BOUND: {
                    lexemes.next();
                    term = new WordBoundary(true);
                    break;
                }

                case Lexer.CHAR_NONWORD_BOUND: {
                    lexemes.next();
                    term = new WordBoundary(false);
                    break;
                }

                case Lexer.CHAR_END_OF_INPUT: {
                    lexemes.next();
                    term = new EOISet();
                    break;
                }

                case Lexer.CHAR_END_OF_LINE: {
                    lexemes.next();
                    term = new EOLSet(++consCount);
                    break;
                }

                case Lexer.CHAR_START_OF_INPUT: {
                    lexemes.next();
                    term = new SOLSet();
                    break;
                }

                case Lexer.CHAR_PREVIOUS_MATCH: {
                    lexemes.next();
                    term = new PreviousMatch();
                    break;
                }

                case 0x80000000 | '1':
                case 0x80000000 | '2':
                case 0x80000000 | '3':
                case 0x80000000 | '4':
                case 0x80000000 | '5':
                case 0x80000000 | '6':
                case 0x80000000 | '7':
                case 0x80000000 | '8':
                case 0x80000000 | '9': {
                    int number = (ch & 0x7FFFFFFF) - '0';
                    if (globalGroupIndex >= number) {
                        lexemes.next();
                        consCount++;
                        if (!hasFlag(Pattern.CASE_INSENSITIVE)) {
                            term = new BackReferenceSet(number, consCount);
                        } else if (!hasFlag(Pattern.UNICODE_CASE)) {
                            term = new CIBackReferenceSet(number, consCount);
                        } else {
                            term = new UCIBackReferenceSet(number, consCount);
                        }
                        break;
                    } else {
                        throw new PatternSyntaxException(I18n
                                .getMessage("No such group yet exists at "
                                        + "this point in the pattern"), lexemes
                                .toString(), lexemes.getIndex());
                    }
                }

                case 0: {
                    AbstractCharClass cc = null;
                    if ((cc = (AbstractCharClass) lexemes.peekSpecial()) != null) {
                        term = new RangeSet(cc);
                    } else if (!lexemes.isEmpty()) {
                        term = new CharSet((char) ch);
                    }
                    lexemes.next();
                    break;
                }

                default: {
                    if (ch >= 0 && !lexemes.isSpecial()) {
                        if (hasFlag(Pattern.CASE_INSENSITIVE)) {
                            if ((ch >= 'a' && ch <= 'z')
                                    || (ch >= 'A' && ch <= 'Z')) {
                                term = new CICharSet((char) ch);
                            } else if (hasFlag(Pattern.UNICODE_CASE)
                                    && ch > 128) {
                                term = new UCICharSet((char) ch);
                            } else {
                                term = new CharSet((char) ch);
                            }
                        } else {
                            term = new CharSet((char) ch);
                        }
                        lexemes.next();
                    } else {
                        throw new PatternSyntaxException(I18n
                                .getMessage("Dangling meta construction")
                                + ": "
                                + (lexemes.isSpecial() ? lexemes.peekSpecial()
                                        .toString() : Character
                                        .toString((char) ch)), lexemes
                                .toString(), lexemes.getIndex());
                    }
                }
                }
        } while (ch == Lexer.CHAR_FLAGS);
        return term;
    }

    private AbstractSet processRange(boolean negative, AbstractSet last) {
        AbstractCharClass res = processRangeExpression(negative);
        if (!res.hasUCI()) {
            return new RangeSet(res, last);
        } else {
            return new UCIRangeSet(res, last);
        }
    }

    /**
     * proceess [...] ranges
     */
    private AbstractCharClass processRangeExpression(boolean alt) {
        CharClass res = new CharClass(alt, hasFlag(Pattern.CASE_INSENSITIVE),
                hasFlag(Pattern.UNICODE_CASE));
        int buffer = -1;
        // TODO remove this one, being used for debug only
        int ch = 0;
        boolean intersection = false;
        boolean notClosed = false;
        boolean firstInClass = true;

        while (!lexemes.isEmpty()
                && (notClosed = (ch = lexemes.peek()) != Lexer.CHAR_RIGHT_SQUARE_BRACKET
                        || firstInClass)) {
            switch (lexemes.peek()) {

            case Lexer.CHAR_RIGHT_SQUARE_BRACKET: {
                if (buffer >= 0)
                    res.add(buffer);
                buffer = ']';
                lexemes.next();
                break;
            }
            case Lexer.CHAR_LEFT_SQUARE_BRACKET: {
                lexemes.next();
                boolean negative = false;
                if (lexemes.peek() == Lexer.CHAR_CARET) {
                    lexemes.next();
                    negative = true;
                }

                if (intersection)
                    res.intersection(processRangeExpression(negative));
                else
                    res.union(processRangeExpression(negative));
                intersection = false;
                lexemes.next();
                break;
            }

            case Lexer.CHAR_AMPERSAND: {
                if (buffer >= 0)
                    res.add(buffer);
                buffer = lexemes.next();
                // if there is a start for subrange we will do an intersection
                // otherwise treat '&' as normal character
                if (lexemes.peek() == Lexer.CHAR_AMPERSAND
                        && lexemes.lookAhead() == Lexer.CHAR_LEFT_SQUARE_BRACKET) {
                    lexemes.next();
                    intersection = true;
                    buffer = -1;
                }

                break;
            }

            case Lexer.CHAR_HYPHEN: {
                if (firstInClass
                        || lexemes.lookAhead() == Lexer.CHAR_RIGHT_SQUARE_BRACKET
                        || lexemes.lookAhead() == Lexer.CHAR_LEFT_SQUARE_BRACKET
                        || buffer < 0) {
                    // treat hypen as normal character
                    if (buffer >= 0)
                        res.add(buffer);
                    buffer = '-';
                    lexemes.next();
                    // range
                } else {
                    lexemes.next();
                    int cur = lexemes.peek();

                    if (!lexemes.isSpecial()
                            && (cur >= 0
                                    || lexemes.lookAhead() == Lexer.CHAR_RIGHT_SQUARE_BRACKET
                                    || lexemes.lookAhead() == Lexer.CHAR_LEFT_SQUARE_BRACKET || buffer < 0)) {

                        try {
                            res.add(buffer, (char) lexemes.peek());
                        } catch (Exception e) {
                            throw new PatternSyntaxException(I18n
                                    .getMessage("Illegal character range"),
                                    pattern(), lexemes.getIndex());
                        }
                        lexemes.next();
                        buffer = -1;
                    } else {
                        throw new PatternSyntaxException(I18n
                                .getMessage("Illegal character range"),
                                pattern(), lexemes.getIndex());
                    }
                }

                break;
            }

            case 0: {
                if (buffer >= 0)
                    res.add(buffer);
                AbstractCharClass cs = (AbstractCharClass) lexemes
                        .peekSpecial();
                if (cs != null) {
                    res.add(cs);
                    buffer = -1;
                } else {
                    buffer = 0;
                }

                lexemes.next();
                break;
            }

            default: {
                if (buffer >= 0)
                    res.add(buffer);
                buffer = lexemes.next();
                break;
            }
            }

            firstInClass = false;
        }
        if (notClosed) {
            throw new PatternSyntaxException(I18n.getMessage("Missing ']'"),
                    pattern(), lexemes.getIndex() - 1);
        }
        if (buffer >= 0)
            res.add(buffer);
        return res;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static Pattern compile(String pattern) {
        return compile(pattern, 0);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static boolean matches(String regex, CharSequence input) {
        return Pattern.compile(regex).matcher(input).matches();
    }

    public static String quote(String s) {
        StringBuffer sb = new StringBuffer().append("\\Q");
        int apos = 0;
        int k;
        while ((k = s.indexOf("\\E", apos)) >= 0) {
            sb.append(s.substring(apos, k + 2)).append("\\\\E\\Q");
            apos = k + 2;
        }

        return sb.append(s.substring(apos)).append("\\E").toString();
    }

    /**
     * return number of groups found at compile time
     */
    int groupCount() {
        return globalGroupIndex;
    }

    int compCount() {
        return this.compCount + 1;
    }

    int consCount() {
        return this.consCount + 1;
    }

    /**
     * Returns supplementary character. At this time only for ASCII chars.
     */
    static char getSupplement(char ch) {
        char res = ch;
        if (ch >= 'a' && ch <= 'z') {
            res -= 32;
        } else if (ch >= 'A' && ch <= 'Z') {
            res += 32;
        }

        return res;
    }

    /**
     * @return true if pattern has specified flag
     */
    private boolean hasFlag(int flag) {
        return (flags & flag) == flag;
    }

    /**
     * Dismiss public costructor.
     * 
     */
    private Pattern() {
    }

    /**
     * Serialization support
     */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        AbstractSet.counter = 1;
        groupIndex = -1;
        globalGroupIndex = -1;
        compCount = -1;
        consCount = -1;

        compileImpl(pattern, flags);

    }
}
