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
 * Represents a wildcard type, such as <code>?</code> or
 * <code>? extends Comparable</code>.
 * </p>
 * 
 * @since 1.5
 */
public interface WildcardType extends Type {
    /**
     * <p>
     * Gets the array of types that represent the upper bounds of this type. The
     * default upper bound is {@link Object}.
     * </p>
     * 
     * @return An array of {@link Type} instances.
     * @throws TypeNotPresentException if the component type points to a missing
     *         type.
     * @throws MalformedParameterizedTypeException if the component type points
     *         to a type that can't be instantiated for some reason.
     */
    Type[] getUpperBounds();

    /**
     * <p>
     * Gets the array of types that represent the lower bounds of this type. The
     * default lower bound is <code>null</code>, in which case a empty array
     * is returned.
     * </p>
     * 
     * @return An array of {@link Type} instances.
     * @throws TypeNotPresentException if the component type points to a missing
     *         type.
     * @throws MalformedParameterizedTypeException if the component type points
     *         to a type that can't be instantiated for some reason.
     */
    Type[] getLowerBounds();
}
