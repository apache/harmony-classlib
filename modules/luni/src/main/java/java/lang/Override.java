/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.lang;

/**
 * <p>
 * An annotation to indicate that a method is intended to override a superclass
 * method. This provides a compile-time assertion that a method actually
 * overrides the superclass method.
 * </p>
 * 
 * @since 1.5
 */
// TODO Enable annotations when Java 5 class files are available.
// @Target(ElementType.METHOD)
// @Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
