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
 * Indicates that an <code>enum</code> constant does not exist for a
 * particular name.
 * </p>
 * 
 * @since 1.5
 * @author Nathan Beyer (Harmony)
 */
public class EnumConstantNotPresentException extends RuntimeException {

    private static final long serialVersionUID = -6046998521960521108L;

    private final Class<? extends Enum> enumType;

    private final String constantName;

    /**
     * <p>
     * Constructs an instance for the passed enum and constant name.
     * </p>
     * 
     * @param enumType The enum type.
     * @param constantName The missing constant name.
     */
    public EnumConstantNotPresentException(Class<? extends Enum> enumType,
            String constantName) {
        super("The enum constant " + enumType.getName() + "." + constantName
                + " is missing.");
        this.enumType = enumType;
        this.constantName = constantName;
    }

    /**
     * <p>
     * The enum type from which the constant name is missing.
     * </p>
     * 
     * @return A <code>Class</code> instance.
     */
    public Class<? extends Enum> enumType() {
        return enumType;
    }

    /**
     * <p>
     * The name of the constant missing.
     * </p>
     * 
     * @return A <code>String</code> instance.
     */
    public String constantName() {
        return constantName;
    }
}
