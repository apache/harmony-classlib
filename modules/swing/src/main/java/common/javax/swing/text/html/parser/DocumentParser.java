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

import java.io.IOException;
import java.io.Reader;
import javax.swing.text.ChangedCharSetException;
import javax.swing.text.html.HTMLEditorKit;

public class DocumentParser extends Parser {

    public DocumentParser(final DTD a0) {
        super(a0);
        throw new UnsupportedOperationException("Not implemented");
    }


    protected void handleError(final int a0, final String a1) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void handleText(final char[] a0) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void handleEndTag(final TagElement a0) {
        throw new UnsupportedOperationException("Not implemented");
    }


    protected void handleEmptyTag(final TagElement a0) throws ChangedCharSetException {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void handleComment(final char[] a0) {
        throw new UnsupportedOperationException("Not implemented");

    }


    protected void handleStartTag(final TagElement a0) {
        throw new UnsupportedOperationException("Not implemented");

    }


    public void parse(final Reader a0, final HTMLEditorKit.ParserCallback a1, final boolean a2) throws IOException {
        throw new UnsupportedOperationException("Not implemented");

    }

}

