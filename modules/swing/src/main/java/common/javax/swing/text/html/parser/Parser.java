/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Evgeniya G. Maenkova
 * @version $Revision$
 */
package javax.swing.text.html.parser;

import java.io.IOException;
import java.io.Reader;
import javax.swing.text.ChangedCharSetException;
import javax.swing.text.SimpleAttributeSet;

public class Parser implements DTDConstants {


    protected DTD dtd;

    protected boolean strict;


    public Parser(final DTD a0) {
        throw new UnsupportedOperationException("Not implemented");
    }


    protected int getCurrentPos() {
        throw new UnsupportedOperationException("Not implemented");
    }


    public synchronized void parse(final Reader a0) throws IOException {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected boolean parseMarkupDeclarations(final StringBuffer a0) throws IOException {
        throw new UnsupportedOperationException("Not implemented");

    }


    public String parseDTDMarkup() throws IOException {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void markFirstTime(final Element a0) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void endTag(final boolean a0) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void startTag(final TagElement a0) throws ChangedCharSetException {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void error(final String a0) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void error(final String a0, final String a1) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void error(final String a0, final String a1, final String a2) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void error(final String a0, final String a1, final String a2, final String a3) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void handleError(final int a0, final String a1) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void handleEndTag(final TagElement a0) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void handleStartTag(final TagElement a0) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void handleEmptyTag(final TagElement a0) throws ChangedCharSetException {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void handleEOFInComment() {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void handleComment(final char[] a0) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void handleTitle(final char[] a0) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void handleText(final char[] a0) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void flushAttributes() {
        throw new UnsupportedOperationException("Not implemented");

    }

    protected SimpleAttributeSet getAttributes() {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected TagElement makeTag(final Element a0) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected TagElement makeTag(final Element a0, final boolean a1) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected int getCurrentLine() {
        throw new UnsupportedOperationException("Not implemented");

    }

}

