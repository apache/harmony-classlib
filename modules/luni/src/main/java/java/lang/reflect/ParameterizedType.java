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

package java.lang.reflect;

/**
 * <p>
 * Represents a parameterized type.
 * </p>
 * 
 * @since 1.5
 */
public interface ParameterizedType extends Type {
    /**
     * <p>
     * Gets the type arguments for this type.
     * </p>
     * 
     * @return An array of {@link Type}, which may be empty.
     * @throws TypeNotPresentException if one of the type arguments can't be
     *         found.
     * @throws MalformedParameterizedTypeException if one of the type arguments
     *         can't be instantiated for some reason.
     */
    Type[] getActualTypeArguments();

    /**
     * <p>
     * Gets the parent/owner type, if this type is an inner type, otherwise
     * <code>null</code> is returned if this is a top-level type.
     * </p>
     * 
     * @return An instance of {@link Type} or <code>null</code>.
     * @throws TypeNotPresentException if one of the type arguments can't be
     *         found.
     * @throws MalformedParameterizedTypeException if one of the type arguments
     *         can't be instantiated for some reason.
     */
    Type getOwnerType();

    /**
     * <p>
     * Gets the raw type of this type.
     * </p>
     * 
     * @return An instance of {@link Type}.
     */
    Type getRawType();
}
