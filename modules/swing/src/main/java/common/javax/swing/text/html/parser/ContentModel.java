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
 * @author Evgeniya G. Maenkova
 * @version $Revision$
 */
package javax.swing.text.html.parser;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * Element's content representation. That's unary or binary expression.
 * Operands can be null (matched with null object), instance of Element,
 * instance of ContentType.
 * Valid operations can be unary operations (types):
 * 1)'+' - (e+) - e must occur one or more times;
 * 2)'*' - (e*) - e must occur zero or more times;
 * 3)'?' - (e?) - e must occur zero or one time;
 * 4)(0) - (e) - e must occur one time only
 * and binary operations (types):
 * 1)'|' - (e1|e2) means either e1 or e2 must occur, but not both;
 * 2)',' - (e1,e2) means both A and B must occur, in that order;
 * 3)'&' - (e1 & e2) means both e1 and e2 must occur, in any order *
 * (Operation interpretation corresponds to HTML 4.01 Specification (3.3.3))
 * As content model is using for dtd presentation here is some ambiguity
 * what null object can be matched with. So null shouldn't be passed to
 * constructor.
 *    No resursion is allowed.
 *    Content, next, type fields has the following limitation:
 *    1) if type is one from {'+', '*', '?'} content hasn't to be null;
 *    2) if type is one from {'|', ',', '&'} content and next have not to be
 *       null;
 *    3) content can be null if and only if type is 0;
 *    4) content can be null, instance of Element or instance of ContentModel;
 *    5) type can be one from the following '*', '+', '?', '|', '&', ','.
 */

public final class ContentModel implements Serializable {
    public int type;

    public Object content;

    public ContentModel next;

    private static final char DEFAULT_TYPE = 0;
    private static final char PLUS_TYPE = '+';
    private static final char STAR_TYPE = '*';
    private static final char QUESTION_TYPE = '?';
    private static final char LINE_TYPE = '|';
    private static final char COMMA_TYPE = ',';
    private static final char AMPER_TYPE = '&';

    /**
     * @throws IllegalArgumentException in the following cases:
     * 2) content model isn't instance of Element or Content Model
     *    (in particular, content equals to null);
     * 3) types isn't binary type mentioned above;
     * 4) next equals to null.
     */
    public ContentModel(final int type,
                        final Object content,
                        final ContentModel next) {
         checkBinaryType(type);
         checkObjectParameter(content);
         checkObjectParameter(next);
         this.type = type;
         this.content = content;
         this.next = next;
    }

    /**
     * @throws IllegalArgumentException in the following cases:
     * 1) type isn't unary type;
     * 2) next equals to null
     */

    public ContentModel(final int type,
                        final ContentModel content) {
        checkObjectParameter(content);
        checkUnaryType(type);
        this.type = type;
        this.content = content;
    }

    /**
     * That content model will be mathed with exactly one element.
     * Type will be 0.
     * Element can be equals to null. in such case contentModel will be matched
     * with an empty input stream.
     */
    public ContentModel(final Element element) {
        content = element;
    }

    public ContentModel() {
    }

    public String toString() {
        if (type == DEFAULT_TYPE) {
            return content == null ? "null" : ((Element)content).getName();
        } else if (isUnaryType(type)) {
            ContentModel content = (ContentModel)this.content;
            boolean need = !(content.content instanceof Element
               && isExtUnaryType(content.type));
            return getBracket("(", need) + content.toString()
                + getBracket(")", need) + (char)type;
        } else {
           String contentToString = content instanceof Element
               ? ((Element)content).getName() : content.toString();
           boolean needLeft = (content instanceof ContentModel)
              && ((ContentModel)content).type != type
              && isBinaryType(((ContentModel)content).type);
           boolean needRight = next != null && next.type != type
               && isBinaryType(next.type);
           return getBracket("(", needLeft) + contentToString
               + getBracket(")", needLeft)
               + (char) type
               + getBracket("(", needRight) + next + getBracket(")", needRight);
        }
    }

    private String getBracket(final String s, final boolean need) {
        return need ? s : "";
    }
    
    /**
     * Gets the <code>Element</code> that must be the first one to occur
     * in the <code>ContentModel</code>.
     * @return The first element that must appear in the
     * <code>ContentModel</code>. Null if zero or more than one
     * <code>Element</code> occurrence is possible.
     */
    public Element first() {
        Element element = null;
        if (type == DEFAULT_TYPE) {
            element = (Element)content;
        } else if (type == PLUS_TYPE || type == COMMA_TYPE) {
            element = ((ContentModel)content).first();
        }
        return element;
    }
    

    /**
     * @return
     * 1) Returns true if token is null or equals to content or this content
     *    model equals to token;
     * 2) if type equals to 0 return true if token equals to content and false
     *    otherwise;
     * 3) If type is one from the unary types returns false if token isn't
     *    istance of Element or ContentModel. Otherwise, returns true if and
     *    only if one of the following conditions is true:
     *    a) content is instance of Element, token is instance of Element and
     *       token equals to content
     *    b) content is instance of ContentModel, token is instance of Element
     *       and content.first(token) returns true;
     *    c) content is instance of Element and token is instance of
     *       ContentModel, token.type equals to 0 and content equals to
     *       token.content;
     *    d) content is instance of ContentModel, token is instance of
     *       ContentModel and content.first(token) returns true;
     * 4) If type is one from binary types then:
     *    a) if content instance of Element and content equals to token returns
     *       true;
     *    b) if content instance of ContentModel and content.first(token)
     *       equals to true, then returns true
     *    c) if content.first(token) equals to true returns true;
     *    d) if type equals to ',' returns true if and only if content is
     *       instance of ContentModel and content.empty() && next.first(token)
     *       equals to true.
     *    e) if type equals to '| or '&' returns next.first(token).
     *
     */

    public boolean first(final Object token) {
        if (token == null || token.equals(content) || equals(token)) {
            return true;
        }
        if (type == DEFAULT_TYPE) {
            return token.equals(content);
        } else if (isUnaryType(type)) {
            return canBeFirstInContent(token);
        } else if (isBinaryType(type)) {
            boolean contentIsModel = content instanceof ContentModel;
            boolean contentIsElement = content instanceof Element;
            if ((contentIsModel && ((ContentModel)content).first(token))
                || (contentIsElement && content.equals(token))) {
                return true;
            }
            switch (type) {
            case COMMA_TYPE:
                return  (contentIsModel && ((ContentModel)content).empty())
                    && next.first(token);
            case LINE_TYPE:
            case AMPER_TYPE:
                return next.first(token);
            default:
                return false;
            }
        } else  {
            return false;
        }
    }

    private boolean canBeFirstInContent(final Object token) {
        boolean contentIsElement = contentIsElement();
        boolean tokenIsElement = token instanceof Element;
        boolean tokenIsModel = token instanceof ContentModel;
        if (!tokenIsElement && !tokenIsModel) {
            return false;
        }

        boolean contentIsModel = !contentIsElement;
        return (contentIsElement && tokenIsElement && token.equals(content))
           || (contentIsModel && tokenIsElement
               && ((ContentModel)content).first(token))
           || (contentIsElement && tokenIsModel
               && ((ContentModel)token).type == 0
               && content.equals(((ContentModel)token).content))
           || (contentIsModel && tokenIsModel
               && ((ContentModel)content).first(token));

    }


    /**
     * Adds all elements of this contentModel to elemVec ignoring operations
     * between elements. For instance, for ((a+)| ((b*),(c?))) elements a,b,c
     * will be added to the elemVec.
     * It supposes that elemVec isn't null.
     * If content is null, nothing will be added to elemVec.
     */

    public void getElements(final Vector<Element> elemVec) {
        if (content instanceof Element) {
            elemVec.add((Element)content);
        } else if (content != null) {
            ((ContentModel)content).getElements(elemVec);
        }
        if (next != null) {
            next.getElements(elemVec);
        }
    }

    /**
     * @return: true if and only if some of the conditions is true:
     * 1)type equals to 0 and content equals to null;
     * 2)type equals to '*' or '?';
     * 3)if type equals to '|' one of the following conditions is true:
     *    a) content is instance of ContentModel and could match an empty input
     *       stream;
     *    b) next could match an empty input stream;
     * 4)if type equals to ',' or '&' both conditions are true:
     *    a) content is instance of ContentModel and could match an empty input
     *       stream;
     *    b) next could match an empty input stream;
     */
    public boolean empty() {
        switch (type) {
        case DEFAULT_TYPE:
            return content == null;
        case STAR_TYPE:
        case QUESTION_TYPE:
            return true;
        case PLUS_TYPE:
            return false;
        case LINE_TYPE:
            return (!contentIsElement() && ((ContentModel)content).empty())
            || next.empty();
        case AMPER_TYPE:
        case COMMA_TYPE:
            return !contentIsElement() && ((ContentModel)content).empty()
            && next.empty();
        default:
            return false;
        }
    }

    private boolean isUnaryType(final int type) {
        return type == STAR_TYPE || type == PLUS_TYPE || type == QUESTION_TYPE;
    }

    private boolean isExtUnaryType(final int type) {
        return isUnaryType(type) || type == DEFAULT_TYPE;
    }

    private boolean isBinaryType(final int type) {
        return type == LINE_TYPE || type == COMMA_TYPE || type == AMPER_TYPE;
    }

    private void checkBinaryType(final int type) {
        if (!isBinaryType(type)) {
            throw new IllegalArgumentException("Illegal type, must be "
                                               + COMMA_TYPE + ", "
                                               + LINE_TYPE + ", "
                                               + AMPER_TYPE + "");
        }
    }

    private void checkUnaryType(final int type) {
        if (!isUnaryType(type)) {
            throw new IllegalArgumentException("Illegal type, must be "
                                               + STAR_TYPE + ", "
                                               + PLUS_TYPE + ", "
                                               + QUESTION_TYPE + "");
        }
    }

    private void checkObjectParameter(final Object object) {
        if (!(object instanceof Element || object instanceof ContentModel)) {
            throw new IllegalArgumentException("Object have to be instance "
                    + "of Element or ContentModel and not null");
        }
    }

    private boolean contentIsElement() {
        return isElement(content);
    }

    private boolean isElement(final Object obj) {
        return obj instanceof Element;
    }
}
