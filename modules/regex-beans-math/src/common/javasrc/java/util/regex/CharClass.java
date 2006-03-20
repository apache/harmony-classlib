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
 * @version $Revision: 1.11.2.2 $
 */
package java.util.regex;

import java.util.BitSet;

/**
 * User defined character classes ([abef]). See AbstractCharClass
 * documentation for more details.
 * 
 * @author Nikolay A. Kuznetsov
 * @version $Revision: 1.11.2.2 $
 */
class CharClass extends AbstractCharClass {

    // Flag indicates if we add supplement upper/lower case
    boolean ci = false;

    boolean uci = false;

    // Flag indicates if there are unicode supplements
    boolean hasUCI = false;

    boolean inverted = false;

    boolean hideBits = false;

    BitSet bits = new BitSet();

    AbstractCharClass nonBitSet = null;

    public CharClass() {
    }

    public CharClass(boolean ci, boolean uci) {
        this.ci = ci;
        this.uci = uci;
    }

    public CharClass(boolean negative, boolean ci, boolean uci) {
        this(ci, uci);
        setNegative(negative);
    }

    public CharClass add(int ch) {
        if (ci) {
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                if (!inverted) {
                    bits.set(Pattern.getSupplement((char) ch));
                } else {
                    bits.clear(Pattern.getSupplement((char) ch));
                }
            } else if (uci && ch > 128) {
                hasUCI = true;
                ch = Character.toLowerCase(Character.toUpperCase((char) ch));
                // return this;
            }
        }
        if (!inverted) {
            bits.set(ch);
        } else
            bits.clear();

        return this;
    }

    public CharClass add(final AbstractCharClass cc) {
        if (cc.getBits() != null) {
            if (!inverted) {
                if (cc.isNegative()) {
                    bits.xor(cc.getBits());
                    bits.and(cc.getBits());
                    alt = !alt;
                    inverted = true;
                } else {
                    bits.or(cc.getBits());
                }
            } else {
                if (cc.isNegative()) {
                    bits.and(cc.getBits());
                } else {
                    bits.andNot(cc.getBits());
                }
            }
        } else {
            if (nonBitSet == null) {
                // hide bits true at the moment
                nonBitSet = new AbstractCharClass() {
                    public boolean contains(int ch) {
                        return cc.contains(ch) || bits.get(ch);
                    }
                };
                hideBits = true;
            } else {
                final AbstractCharClass nb = nonBitSet;
                nonBitSet = new AbstractCharClass() {
                    public boolean contains(int ch) {
                        return nb.contains(ch) || cc.contains(ch);
                    }
                };
            }
        }

        return this;
    }

    public CharClass add(int st, int end) {
        if (st > end)
            throw new IllegalArgumentException();
        if (!ci) {
            if (!inverted) {
                bits.set(st, end + 1);
            } else {
                bits.clear(st, end + 1);
            }
        } else {
            for (int i = st; i < end + 1; i++) {
                add(i);
            }
        }
        return this;
    }

    // OR operation
    public void union(final AbstractCharClass clazz) {
        if (clazz.hasUCI())
            this.hasUCI = true;
        if (!hideBits && clazz.getBits() != null) {
            if (alt ^ clazz.isNegative()) {
                if (alt) {
                    bits.andNot(clazz.getBits());
                } else {
                    bits.xor(clazz.getBits());
                    bits.and(clazz.getBits());
                }
                alt = true;
            } else {
                if (alt) {
                    bits.and(clazz.getBits());
                } else {
                    bits.or(clazz.getBits());
                }
            }
        } else {
            if (nonBitSet == null) {
                nonBitSet = new AbstractCharClass() {
                    public boolean contains(int ch) {
                        return clazz.contains(ch) || bits.get(ch);
                    }
                };
                hideBits = true;
            } else {
                final AbstractCharClass nb = nonBitSet;
                nonBitSet = new AbstractCharClass() {
                    public boolean contains(int ch) {
                        return nb.contains(ch) || clazz.contains(ch);
                    }
                };
            }
        }
    }

    // AND operation
    public void intersection(final AbstractCharClass clazz) {
        if (clazz.hasUCI())
            this.hasUCI = true;
        if (!hideBits && clazz.getBits() != null) {
            if (alt ^ clazz.isNegative()) {
                if (alt) {
                    bits.xor(clazz.getBits());
                    bits.and(clazz.getBits());
                    setNegative(false);
                } else {
                    bits.andNot(clazz.getBits());
                }
            } else {
                if (alt) {
                    bits.or(clazz.getBits());
                } else {
                    bits.and(clazz.getBits());
                }
            }
        } else {
            if (nonBitSet == null) {
                nonBitSet = new AbstractCharClass() {
                    public boolean contains(int ch) {
                        return bits.get(ch) && clazz.contains(ch);
                    }
                };
                hideBits = true;
            } else {
                final AbstractCharClass nb = nonBitSet;
                nonBitSet = new AbstractCharClass() {
                    public boolean contains(int ch) {
                        return nb.contains(ch) && clazz.contains(ch);
                    }
                };
            }
        }
    }

    /**
     * Returns <code>true</code> if character class contains symbol specified,
     * <code>false</code> otherwise. Note: #setNegative() method changes the
     * meaning of contains method;
     * 
     * @param ch
     * @return <code>true</code> if character class contains symbol specified;
     * 
     * TODO: currently <code>character class</code> implementation based on
     * BitSet, but this implementation possibly will be turned to combined
     * BitSet(for first 256 symbols) and Black/Red tree for the rest of UTF.
     */
    public boolean contains(int ch) {
        if (nonBitSet == null) {
            return this.alt ^ bits.get(ch);
        } else {
            return alt ^ nonBitSet.contains(ch);
        }
    }

    protected BitSet getBits() {
        if (hideBits)
            return null;
        return bits;
    }

    public AbstractCharClass getInstance() {
        if (nonBitSet == null) {
            final BitSet bs = getBits();
            AbstractCharClass res = new AbstractCharClass() {
                public boolean contains(int ch) {
                    return this.alt ^ bs.get(ch);
                }

                public String toString() {
                    StringBuffer temp = new StringBuffer();
                    for (int i = bs.nextSetBit(0); i >= 0; i = bs
                            .nextSetBit(i + 1)) {
                        temp.append((char) i);
                        temp.append('|');
                    }

                    if (temp.length() > 0)
                        temp.deleteCharAt(temp.length() - 1);

                    return temp.toString();
                }

            };
            return res.setNegative(isNegative());
        } else {
            return this;
        }
    }

    public String toString() {
        StringBuffer temp = new StringBuffer();
        for (int i = bits.nextSetBit(0); i >= 0; i = bits.nextSetBit(i + 1)) {
            temp.append((char) i);
            temp.append('|');
        }

        if (temp.length() > 0)
            temp.deleteCharAt(temp.length() - 1);

        return temp.toString();
    }

    public boolean hasUCI() {
        return hasUCI;
    }
}
