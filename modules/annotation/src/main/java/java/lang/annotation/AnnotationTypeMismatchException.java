/*
 * Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package java.lang.annotation;

import java.lang.reflect.Method;

/**
 * <p>
 * Indicates that an annotation type has changed since it was compiled or
 * serialized.
 * </p>
 * 
 * @since 1.5
 */
public class AnnotationTypeMismatchException extends RuntimeException {

    private static final long serialVersionUID = 8125925355765570191L;

    private Method element;

    private String foundType;

    /**
     * <p>
     * Constructs an instance for the given type element and the type found.
     * </p>
     * 
     * @param element The annotation type element.
     * @param foundType The invalid type that was found.
     */
    public AnnotationTypeMismatchException(Method element, String foundType) {
        super("The annotation element, " + element
                + ", doesn't match the type " + foundType + ".");
        this.element = element;
        this.foundType = foundType;
    }

    /**
     * <p>
     * The method object for the invalid type.
     * </p>
     * 
     * @return A {@link Method} instance.
     */
    public Method element() {
        return element;
    }

    /**
     * <p>
     * The invalid type.
     * </p>
     * 
     * @return A String describing the invalid data.
     */
    public String foundType() {
        return foundType;
    }
}
