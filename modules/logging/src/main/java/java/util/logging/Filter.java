/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
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


package java.util.logging;

/**
 * <code>Filter</code> objects are used to filter log records that are not
 * desired. <code>Handler</code> or <code>Logger</code> objects can be
 * attached with a filter to get finer grain control over what should be logged.
 * 
 */
public interface Filter {

    /*
     * -------------------------------------------------------------------
     * Methods
     * -------------------------------------------------------------------
     */

    /**
     * Checks whether the supplied log record needs to be logged.
     * 
     * @param record
     *            the log record to be checked
     * @return <code>true</code> if the supplied log record needs to be
     *         logged, otherwise <code>false</code>
     */
    boolean isLoggable(LogRecord record);
}

