/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
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
 * @author Vadim L. Bogdanov
 * @version $Revision$
 */

package javax.swing.text.html;

import java.net.URL;

import javax.swing.text.Element;

public class FormSubmitEvent extends HTMLFrameHyperlinkEvent {

    public static class MethodType {
        // TODO: Uncomment along with transition to 1.5
        // public static enum MethodType extends Enum<MethodType> {

        public static final MethodType GET = new MethodType();
        public static final MethodType POST = new MethodType();

        private MethodType() {
        }

        public static MethodType valueOf(final String name) {
            if ("GET".equals(name)) {
                return GET;
            } else if ("POST".equals(name)) {
                return POST;
            }
            throw new IllegalArgumentException("parameter has to be GET or POST");
        }

        public static final MethodType[] values() {
            MethodType[] result = new MethodType[2];
            result[0] = GET;
            result[1] = POST;
            return result;
        }
    }

    private String data;
    private MethodType method;

    FormSubmitEvent(final Object source, final EventType type,
                    final URL targetURL, final String desc,
                    final Element sourceElement,
                    final String targetFrame,
                    final MethodType method,
                    final String data) {

        super(source, type, targetURL, targetFrame);

        this.data = data;
        this.method = method;
    }

    public String getData() {
        return data;
    }

    public MethodType getMethod() {
        return method;
    }
}
