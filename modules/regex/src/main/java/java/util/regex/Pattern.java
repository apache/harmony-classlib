/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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

import org.apache.harmony.regex.internal.nls.Messages;


/**
 * Pattern implements a compiler for regular expressions as defined by the J2SE
 * specification. The regular expression syntax is largely similar to the syntax
 * defined by Perl 5 but has both omissions and extensions. A formal and
 * complete definition of the regular expression syntax is not provided by the
 * J2SE speTBD (TODO)
 * 
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
    
    static final int BACK_REF_NUMBER = 10;
    
    /**
     * Bit mask that includes all defined match flags
     */
    static final int flagsBitMask = Pattern.UNIX_LINES | 
                                    Pattern.CASE_INSENSITIVE | 
                                    Pattern.COMMENTS | 
                                    Pattern.MULTILINE |  
                                    Pattern.DOTALL | 
                                    Pattern.UNICODE_CASE | 
                                    Pattern.CANON_EQ;

    /**
     * Current <code>pattern</code> to be compiled;
     */
    private transient Lexer lexemes = null;

    /**
     * Pattern compile flags;
     */
    private int flags = 0;

    private String pattern = null;
    
    /*
     * All backreferences that may be used in pattern.
     */
    transient private FSet backRefs [] = new FSet [BACK_REF_NUMBER];
    
    /*
     * Is true if backreferenced sets replacement is needed
     */
    transient private boolean needsBackRefReplacement = false;

    transient private int groupIndex = -1;

    transient private int globalGroupIndex = -1;

    transient private int compCount = -1;

    transient private int consCount = -1;

    transient AbstractSet start = null;

	/**
	 * Create a matcher for this pattern and a given input character sequence
	 * 
	 * @param cs
	 *            The input character sequence
	 * @return A new matcher
	 */
    public Matcher matcher(CharSequence cs) {
        return new Matcher(this, cs);
    }

	/**
	 * Split an input string using the pattern as a token separator.
	 * 
	 * @param input
	 *            Input sequence to tokenize
	 * @param limit
	 *            If positive, the maximum number of tokens to return. If
	 *            negative, an indefinite number of tokens are returned. If
	 *            zero, an indefinite number of tokens are returned but trailing
	 *            empty tokens are excluded.
	 * @return A sequence of tokens split out of the input string.
	 */
    public String[] split(CharSequence input, int limit) {
        ArrayList res = new ArrayList();
        Matcher mat = matcher(input);
        int index = 0;
        int curPos = 0;       
        
        if (input.length() == 0) {
            return new String [] {""}; //$NON-NLS-1$
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
	 * Returns the pattern string passed to the compile method
	 * 
	 * @return A string representation of the pattern
	 */
    public String pattern() {
        return lexemes.toString();
    }

	/**
	 * Return a textual representation of the pattern.
	 * 
	 * @return The regular expression string
	 */
    public String toString() {
        return this.pattern();
    }

	/**
	 * Return the mask of flags used to compile the pattern
	 * 
	 * @return A mask of flags used to compile the pattern.
	 */
    public int flags() {
        return this.flags;
    }

	/**
     * Return a compiled pattern corresponding to the input regular expression
     * string.
     * 
     * The input <code>flags</code> is a mask of the following flags:
     * <dl>
     * <dt><code>UNIX_LINES</code> (0x0001)
     * <dd>Enables UNIX lines mode where only \n is recognized as a line
     * terminator. The default setting of this flag is <em>off</em> indicating
     * that all of the following character sequences are recognized as line
     * terminators: \n, \r, \r\n, NEL (&#92;u0085), &#92;u2028 and &#92;u2029.
     * <dt><code>CASE_INSENSITIVE</code> (0x0002)
     * <dd>Directs matching to be done in a way that ignores differences in
     * case. If input character sequences are encoded in character sets other
     * than ASCII, then the UNICODE_CASE must also be set to enable Unicode case
     * detection.
     * <dt><code>UNICODE_CASE</code> (0x0040)
     * <dd>Enables Unicode case folding if used in conjunction with the
     * <code>CASE_INSENSITIVE</code> flag. If <code>CASE_INSENSITIVE</code>
     * is not set, then this flag has no effect.
     * <dt><code>COMMENTS</code> (0x0004)
     * <dd>Directs the pattern compiler to ignore whitespace and comments in
     * the pattern. Whitespace consists of sequences including only these
     * characters: SP (&#92;u0020), HT (\t or &#92;u0009), LF (\n or ), VT
     * (&#92;u000b), FF (\f or &#92;u000c), and CR (\r or ). A comment is any
     * sequence of characters beginning with the "#" (&#92;u0023) character and
     * ending in a LF character.
     * <dt><code>MULTILINE</code> (0x0008)
     * <dd>Turns on multiple line mode for matching of character sequences. By
     * default, this mode is off so that the character "^" (&#92;u005e) matches
     * the beginning of the entire input sequence and the character "$"
     * (&#92;u0024) matches the end of the input character sequence. In multiple
     * line mode, the character "^" matches any character in the input sequence
     * which immediately follows a line terminator and the character "$" matches
     * any character in the input sequence which immediately precedes a line
     * terminator.
     * <dt><code>DOTALL</code> (0x0020)
     * <dd>Enables the DOT (".") character in regular expressions to match line
     * terminators. By default, line terminators are not matched by DOT.
     * <dt><code>CANON_EQ</code> (0x0080)
     * <dd>Enables matching of character sequences which are canonically
     * equivalent according to the Unicode standard. Canonical equivalence is
     * described here: http://www.unicode.org/reports/tr15/. By default,
     * canonical equivalence is not detected while matching.
     * </dl>
     * 
     * @param regex
     *            A regular expression string.
     * @param flags
     *            A set of flags to control the compilation of the pattern.
     * @return A compiled pattern
     * @throws PatternSyntaxException
     *             If the input regular expression does not match the required
     *             grammar.
     */
    public static Pattern compile(String regex, int flags)
            throws PatternSyntaxException {
    	
    	if ((flags != 0) &&
    	   	((flags | flagsBitMask) != flagsBitMask)) {
    	        	
    	    throw new IllegalArgumentException(Messages.getString("regex.1C"));
    	}
    	
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
        this.pattern = regex;

        start = processExpression(-1, this.flags, null);
        if (!lexemes.isEmpty()) {
            throw new PatternSyntaxException(
                    Messages.getString("regex.08"), lexemes.toString(), //$NON-NLS-1$
                    lexemes.getIndex());
        }
        finalizeCompile();
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
        AbstractSet rangeSet = processRangeSet(auxRange);
        rangeSet.setNext(last);
        
        return rangeSet;
    }

    /**
     * E->AE; E->S|E; E->S; A->(a|)+ E->S(|S)*
     */
    private AbstractSet processExpression(int ch, int newFlags,
            AbstractSet last) {
        ArrayList children = new ArrayList();
        AbstractSet child;
        int saveFlags = flags;
        FSet fSet;
        boolean saveChangedFlags = false;

        if (newFlags != flags) {
            flags = newFlags;
        }

        switch (ch) {
        case Lexer.CHAR_NONCAP_GROUP:
            fSet = new NonCapFSet(++consCount);
            break;
        
        case Lexer.CHAR_POS_LOOKAHEAD:
        	/* falls through */
        	
        case Lexer.CHAR_NEG_LOOKAHEAD:
            fSet = new AheadFSet();
            break;
        
        case Lexer.CHAR_POS_LOOKBEHIND:
        	/* falls through */
        	
        case Lexer.CHAR_NEG_LOOKBEHIND: 
            fSet = new BehindFSet(++consCount);
            break;
        
        case Lexer.CHAR_ATOMIC_GROUP: 
            fSet = new AtomicFSet(++consCount);
            break;
        
        default: 
            globalGroupIndex++;
            if (last == null) {
                
            	// expr = new StartSet();
            	fSet = new FinalSet();
            	saveChangedFlags = true;
            } else {
                
            	// expr = new JointSet(globalGroupIndex);
            	fSet = new FSet(globalGroupIndex);         
            }
            if (globalGroupIndex > -1 && globalGroupIndex < 10) {
            	backRefs[globalGroupIndex] = fSet;
            }
            break;
        }

        do {
            if (lexemes.isLetter()
                    && lexemes.lookAhead() == Lexer.CHAR_VERTICAL_BAR) {
                child = processAlternations(fSet);
            } else if (lexemes.peek() == Lexer.CHAR_VERTICAL_BAR){
                child = new EmptySet(fSet);
                lexemes.next();
            } else {
                child = processSubExpression(fSet);
                if (lexemes.peek() == Lexer.CHAR_VERTICAL_BAR) {
                    lexemes.next();
                }
            }
            if (child != null) {
                
                //expr.addChild(child);
            	children.add(child);                
            }
        } while (!(lexemes.isEmpty() 
        		   || (lexemes.peek() == Lexer.CHAR_RIGHT_PARENTHESIS)));
        
        if (lexemes.back() == Lexer.CHAR_VERTICAL_BAR) {
        	children.add(new EmptySet(fSet));
        }
        
        if (flags != saveFlags && !saveChangedFlags) {
            flags = saveFlags;
            lexemes.restoreFlags(flags);
        }

        switch (ch) {
        case Lexer.CHAR_NONCAP_GROUP:
            return new NonCapJointSet(children, fSet);
        
        case Lexer.CHAR_POS_LOOKAHEAD:
            return new PositiveLookAhead(children, fSet);           

        case Lexer.CHAR_NEG_LOOKAHEAD: 
            return new NegativeLookAhead(children, fSet);
        
        case Lexer.CHAR_POS_LOOKBEHIND:
            return new PositiveLookBehind(children, fSet);
        
        case Lexer.CHAR_NEG_LOOKBEHIND:
            return new NegativeLookBehind(children, fSet);
        
        case Lexer.CHAR_ATOMIC_GROUP:
            return new AtomicJointSet(children, fSet);
            
        default: 
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


    /**
     * T->a+
     */
    private AbstractSet processSequence(AbstractSet last) {
        StringBuffer substring = new StringBuffer();
        
        while (!lexemes.isEmpty()
                && lexemes.isLetter()
                && !lexemes.isHighSurrogate()
                && !lexemes.isLowSurrogate()
                && ((!lexemes.isNextSpecial() && lexemes.lookAhead() == 0) // end
                        // of
                        // pattern
                        || (!lexemes.isNextSpecial() && Lexer.isLetter(lexemes
                                .lookAhead()))
                        || lexemes.lookAhead() == Lexer.CHAR_RIGHT_PARENTHESIS
                        || (lexemes.lookAhead() & 0x8000ffff) == Lexer.CHAR_LEFT_PARENTHESIS
                        || lexemes.lookAhead() == Lexer.CHAR_VERTICAL_BAR || lexemes
                        .lookAhead() == Lexer.CHAR_DOLLAR)) {
            int ch = lexemes.next();
            
            if (Character.isSupplementaryCodePoint(ch)) {
                substring.append(Character.toChars(ch));
            } else {
                substring.append((char) ch);
            }
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
     * D->a
     */
    private AbstractSet processDecomposedChar(AbstractSet last) {
        int [] codePoints = new int [Lexer.MAX_DECOMPOSITION_LENGTH];                        
        char [] codePointsHangul;
        int readCodePoints = 0;
        int curSymb = -1;
        int curSymbIndex = -1;
        
        if (!lexemes.isEmpty() && lexemes.isLetter()) {
            curSymb = lexemes.next();
            codePoints [readCodePoints] = curSymb;            
            curSymbIndex = curSymb - Lexer.LBase;
        }
        
        /*
         * We process decomposed Hangul syllable LV or LVT or process jamo L.
         * See http://www.unicode.org/versions/Unicode4.0.0/ch03.pdf
         * "3.12 Conjoining Jamo Behavior"
         */
        if ((curSymbIndex >= 0) && (curSymbIndex < Lexer.LCount)) {
            codePointsHangul = new char [Lexer
                                         .MAX_HANGUL_DECOMPOSITION_LENGTH];
            codePointsHangul[readCodePoints++] = (char) curSymb;
            
            curSymb = lexemes.peek();
            curSymbIndex = curSymb - Lexer.VBase;
            if ((curSymbIndex >= 0) && (curSymbIndex < Lexer.VCount)) {
                codePointsHangul [readCodePoints++] = (char) curSymb;
                lexemes.next();
                curSymb = lexemes.peek();
                curSymbIndex = curSymb - Lexer.TBase;
                if ((curSymbIndex >= 0) && (curSymbIndex < Lexer.TCount)) {
                    codePointsHangul [readCodePoints++] = (char) curSymb;
                    lexemes.next();
                    
                    //LVT syllable
                    return new HangulDecomposedCharSet(codePointsHangul, 3);
                } else {
                    
                    //LV syllable
                    return new HangulDecomposedCharSet(codePointsHangul, 2);
                }
            } else {
                   
                   //L jamo
                   if (!hasFlag(Pattern.CASE_INSENSITIVE)) {
                       return new CharSet(codePointsHangul[0]);
                   } else if (!hasFlag(Pattern.UNICODE_CASE)) {
                       return new CICharSet(codePointsHangul[0]);
                   } else {
                       return new UCICharSet(codePointsHangul[0]);
                   }
            }
        
        /*
         * We process single codepoint or decomposed codepoint.
         * We collect decomposed codepoint and obtain 
         * one DecomposedCharSet.
         */
        } else {
            readCodePoints++;
            
            while((readCodePoints < Lexer.MAX_DECOMPOSITION_LENGTH) 
                    && !lexemes.isEmpty() && lexemes.isLetter() 
                    && !Lexer.isDecomposedCharBoundary(lexemes.peek())) {
                  codePoints [readCodePoints++] = lexemes.next();
            }
  
            /*
             * We have read an ordinary symbol.
             */
            if (readCodePoints == 1     
                && !Lexer.hasSingleCodepointDecomposition(codePoints[0])) {
                return processCharSet(codePoints[0]);
            } else {
                if (!hasFlag(Pattern.CASE_INSENSITIVE)) {
                    return new DecomposedCharSet(codePoints, readCodePoints);
                } else if (!hasFlag(Pattern.UNICODE_CASE)) {
                    return new CIDecomposedCharSet(codePoints, readCodePoints);
                } else {
                    return new UCIDecomposedCharSet(codePoints, readCodePoints);
                }
            }
        }
    }

    /**
     * S->BS; S->QS; S->Q; B->a+
     */
    private AbstractSet processSubExpression(AbstractSet last) {
        AbstractSet cur;
        if (lexemes.isLetter() && !lexemes.isNextSpecial()
                && Lexer.isLetter(lexemes.lookAhead())) {
            if (hasFlag(Pattern.CANON_EQ)) {
                cur = processDecomposedChar(last);
                if (!lexemes.isEmpty()        
                        
                        /* && !pattern.isQuantifier() */
                        && (lexemes.peek() != Lexer.CHAR_RIGHT_PARENTHESIS 
                                || last instanceof FinalSet)
                        && lexemes.peek() != Lexer.CHAR_VERTICAL_BAR 
                        && !lexemes.isLetter()) {
                    cur = processQuantifier(last, cur);
                }
            } else if (lexemes.isHighSurrogate() || lexemes.isLowSurrogate()) {
                AbstractSet term = processTerminal(last);
                cur = processQuantifier(last, term);
            } else {
                cur = processSequence(last);
            }
        } else if (lexemes.peek() == Lexer.CHAR_RIGHT_PARENTHESIS) {
        	if (last instanceof FinalSet) {
        	    throw new PatternSyntaxException(
        	            Messages.getString("regex.09"), lexemes.toString(),  //$NON-NLS-1$
        	         lexemes.getIndex());
        	} else {
        	      cur = new EmptySet(last);
        	}
        } else {
            AbstractSet term = processTerminal(last);
            cur = processQuantifier(last, term);
        }

        if (!lexemes.isEmpty()
        // && !pattern.isQuantifier()
                && (lexemes.peek() != Lexer.CHAR_RIGHT_PARENTHESIS 
                		|| last instanceof FinalSet)
                && lexemes.peek() != Lexer.CHAR_VERTICAL_BAR) {
            AbstractSet next = processSubExpression(last);
            if (cur instanceof LeafQuantifierSet
            // TODO create personal UnifiedQuantifierSet for composite
                    // quantifiers
                    // to take into account Quantifier counters
                    // ////
                    && !(cur instanceof CompositeQuantifierSet)
                    && !(cur instanceof GroupQuantifierSet)
                    && !(cur instanceof AltQuantifierSet)
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
    private AbstractSet processQuantifier(AbstractSet last, AbstractSet term) {               
        int quant = lexemes.peek();

        if (term != null && !(term instanceof LeafSet)) {
            switch (quant) {
            case Lexer.QUANT_STAR:
            case Lexer.QUANT_PLUS: {
                QuantifierSet q;
                
                lexemes.next();
                if (term.getType() == AbstractSet.TYPE_DOTSET) {
                    if (!hasFlag(Pattern.DOTALL)) {
                        q = new DotQuantifierSet(term, last, quant,
                                AbstractLineTerminator.getInstance(flags));
                    } else {
                        q = new DotAllQuantifierSet(term, last, quant);
                    }
                } else {
                    q = new GroupQuantifierSet(term, last, quant);
                }
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
                LeafQuantifierSet q = new LeafQuantifierSet(leaf,
                        last, quant);
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
            	 int newFlags;             	
             	 lexemes.next();
                 newFlags = (ch & 0x00ff0000) >> 16;
                 ch = ch & 0xff00ffff;
                 if (ch == Lexer.CHAR_FLAGS) {
                     flags = newFlags;
                 } else {
                     newFlags = (ch == Lexer.CHAR_NONCAP_GROUP) 
                                 ? newFlags
                                 : flags;
                     term = processExpression(ch, newFlags, last);
                     if (lexemes.peek() != Lexer.CHAR_RIGHT_PARENTHESIS) {
                         throw new PatternSyntaxException(
                                 Messages.getString("regex.0A"), lexemes.toString(), //$NON-NLS-1$
                                 lexemes.getIndex());
                     }
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
                        throw new PatternSyntaxException(
                                Messages.getString("regex.0B"), lexemes.toString(), //$NON-NLS-1$
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
                        (backRefs [number]).isBackReferenced = true;
                        needsBackRefReplacement = true;
                        break;
                    } else {
                        throw new PatternSyntaxException(
                                Messages.getString("regex.0C") //$NON-NLS-1$
                                        , lexemes.toString(), lexemes.getIndex());
                    }
                }

                case 0: {
                    AbstractCharClass cc = null;
                    if ((cc = (AbstractCharClass) lexemes.peekSpecial()) != null) {
                        term = processRangeSet(cc);
                    } else if (!lexemes.isEmpty()) {
                        
                        //ch == 0
                        term = new CharSet((char) ch);
                    } else {
                    	term = new EmptySet(last);
                        break;
                    }
                    lexemes.next();
                    break;
                }

                default: {
                    if (ch >= 0 && !lexemes.isSpecial()) {
                        term = processCharSet(ch);                        
                        lexemes.next();
                    } else if (ch == Lexer.CHAR_VERTICAL_BAR) {
                    	term = new EmptySet(last);
                    } else if (ch == Lexer.CHAR_RIGHT_PARENTHESIS) {
                        if (last instanceof FinalSet) {
                        	throw new PatternSyntaxException(
                    				Messages.getString("regex.09"), lexemes.toString(),  //$NON-NLS-1$
                    		    	lexemes.getIndex());
                        } else {
                    	    term = new EmptySet(last);
                        }
                    } else {
                        throw new PatternSyntaxException(
                                Messages.getString("regex.0D", //$NON-NLS-1$
                                 (lexemes.isSpecial() ? lexemes.peekSpecial()
                                        .toString() : Character
                                        .toString((char) ch))), lexemes
                                .toString(), lexemes.getIndex());
                    }
                }
                }
        } while (ch == Lexer.CHAR_FLAGS);
        return term;
    }

    private AbstractSet processRange(boolean negative, AbstractSet last) {
        AbstractCharClass res = processRangeExpression(negative);
        AbstractSet rangeSet = processRangeSet(res);
        rangeSet.setNext(last);
   
        return rangeSet;
    }

    /**
     * process [...] ranges
     */
    private CharClass processRangeExpression(boolean alt) {
        CharClass res = new CharClass(alt, hasFlag(Pattern.CASE_INSENSITIVE),
                hasFlag(Pattern.UNICODE_CASE));
        int buffer = -1;
        boolean intersection = false;
        boolean notClosed = false;
        boolean firstInClass = true;

        while (!lexemes.isEmpty()
                && (notClosed = (lexemes.peek()) != Lexer.CHAR_RIGHT_SQUARE_BRACKET
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
                if (buffer >= 0) {
                    res.add(buffer);
                    buffer = -1;
                }
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
                
                /*
                 * if there is a start for subrange we will do an intersection
                 * otherwise treat '&' as a normal character
                 */
                if (lexemes.peek() == Lexer.CHAR_AMPERSAND) {
                    if (lexemes.lookAhead() 
                            == Lexer.CHAR_LEFT_SQUARE_BRACKET) {
                        lexemes.next();
                        intersection = true;
                        buffer = -1;
                    } else {
                        lexemes.next();
                        if (firstInClass) {
                            
                            //skip "&&" at "[&&...]" or "[^&&...]"
                            res = processRangeExpression(false);
                        } else {
                            
                            //ignore "&&" at "[X&&]" ending where X != empty string
                            if (!(lexemes.peek() 
                                    == Lexer.CHAR_RIGHT_SQUARE_BRACKET)) {    
                                res.intersection(processRangeExpression(false));
                            }
                        }
                        
                    }
                } else {
                    
                    //treat '&' as a normal character
                    buffer = '&';
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
                            if (!Lexer.isLetter(cur)) {
                                cur = cur & 0xFFFF;
                            }
                            res.add(buffer, cur);
                        } catch (Exception e) {
                            throw new PatternSyntaxException(
                                    Messages.getString("regex.0E"), //$NON-NLS-1$
                                    pattern(), lexemes.getIndex());
                        }
                        lexemes.next();
                        buffer = -1;
                    } else {
                        throw new PatternSyntaxException(
                                Messages.getString("regex.0E"), //$NON-NLS-1$
                                pattern(), lexemes.getIndex());
                    }
                }

                break;
            }

            case Lexer.CHAR_CARET: {
                if (buffer >= 0)
                    res.add(buffer);
                buffer = '^';
                lexemes.next();
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
            throw new PatternSyntaxException(Messages.getString("regex.0F"), //$NON-NLS-1$
                    pattern(), lexemes.getIndex() - 1);
        }
        if (buffer >= 0)
            res.add(buffer);
        return res;
    }

    private AbstractSet processCharSet(int ch) { 
        boolean isSupplCodePoint = Character
                .isSupplementaryCodePoint(ch);
        
        if (hasFlag(Pattern.CASE_INSENSITIVE)) {
            
            if ((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')) {
                return new CICharSet((char) ch);
            } else if (hasFlag(Pattern.UNICODE_CASE)
                    && ch > 128) {
                if (isSupplCodePoint) {                                
                    return new UCISupplCharSet(ch);
                } else if (Lexer.isLowSurrogate(ch)) {
                    
                    //we need no UCILowSurrogateCharSet
                    return new LowSurrogateCharSet((char) ch);
                } else if (Lexer.isHighSurrogate(ch)) {

                    //we need no UCIHighSurrogateCharSet
                    return new HighSurrogateCharSet((char) ch);                                    
                } else {
                    return new UCICharSet((char) ch);                                                                    
                }
            }                          
        }                      
            
        if (isSupplCodePoint) {                                
            return new SupplCharSet(ch);
        } else if (Lexer.isLowSurrogate(ch)) {
            return new LowSurrogateCharSet((char) ch);
        } else if (Lexer.isHighSurrogate(ch)) {
            return new HighSurrogateCharSet((char) ch);                                    
        } else {
            return new CharSet((char) ch);                                                                    
        }                        
    }
    
    private AbstractSet processRangeSet(AbstractCharClass charClass) {
        if (charClass.hasLowHighSurrogates()) {
            AbstractCharClass surrogates = charClass.getSurrogates();            
            LowHighSurrogateRangeSet lowHighSurrRangeSet 
                    = new LowHighSurrogateRangeSet(surrogates); 
            
            if (charClass.mayContainSupplCodepoints()) {
                if (!charClass.hasUCI()) {
                    return new CompositeRangeSet(
                            new SupplRangeSet(charClass.getWithoutSurrogates()),
                            lowHighSurrRangeSet);                    
                } else {
                    return new CompositeRangeSet(
                            new UCISupplRangeSet(charClass.getWithoutSurrogates()),
                            lowHighSurrRangeSet);                    
                }
            }
            
            if (!charClass.hasUCI()) {
                return new CompositeRangeSet(
                        new RangeSet(charClass.getWithoutSurrogates()),
                        lowHighSurrRangeSet);                    
            } else {
                return new CompositeRangeSet(
                        new UCIRangeSet(charClass.getWithoutSurrogates()),
                        lowHighSurrRangeSet);                    
            }
        }
        
        if (charClass.mayContainSupplCodepoints()) {
            if (!charClass.hasUCI()) {
                return new SupplRangeSet(charClass);
            } else {
                return new UCISupplRangeSet(charClass);
            }
        }
        
        if (!charClass.hasUCI()) {
            return new RangeSet(charClass);
        } else {
            return new UCIRangeSet(charClass);
        }
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static Pattern compile(String pattern) {
        return compile(pattern, 0);
    }

    /*
     * This method do traverses of
     * automata to finish compilation.
     */
    private void finalizeCompile() {
    	
    	/*
    	 * Processing second pass
    	 */    	
    	if (needsBackRefReplacement) { //|| needsReason1 || needsReason2) {
    		start.processSecondPass();
    	}
    	
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static boolean matches(String regex, CharSequence input) {
        return Pattern.compile(regex).matcher(input).matches();
    }

    public static String quote(String s) {
        StringBuffer sb = new StringBuffer().append("\\Q"); //$NON-NLS-1$
        int apos = 0;
        int k;
        while ((k = s.indexOf("\\E", apos)) >= 0) { //$NON-NLS-1$
            sb.append(s.substring(apos, k + 2)).append("\\\\E\\Q"); //$NON-NLS-1$
            apos = k + 2;
        }

        return sb.append(s.substring(apos)).append("\\E").toString(); //$NON-NLS-1$
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
     * Dismiss public constructor.
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
        backRefs = new FSet [BACK_REF_NUMBER];

        compileImpl(pattern, flags);

    }
}
