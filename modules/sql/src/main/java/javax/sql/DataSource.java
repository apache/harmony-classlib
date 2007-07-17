/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Wrapper;

/**
 * An interface for the creation of Connection objects which represent a
 * connection to a database. This interface is an alternative to the
 * <code>java.sql.DriverManager</code>.
 * <p>
 * A class which implements the DataSource interface is typically registered
 * with a JNDI naming service directory and is retrieved from there by name.
 * <p>
 * The DataSource interface is typically implemented by the writer of a JDBC
 * driver. There are three variants of the DataSource interface, which produce
 * Connections with differing characteristics:
 * <ol>
 * <li>Standard DataSource, which produces standard Connection objects with no
 * special features.</li>
 * <li>Connection Pool DataSource, which produces PooledConnection objects
 * which are able to participate in connection pooling, typically involving a
 * connection pooling manager as an intermediary between applications and the
 * database.</li>
 * <li>Distributed transaction DataSource ("XADataSource"), which produces
 * XAConnection objects which can be used to handle distributed transactions and
 * which typically involve a transaction manager component in the system.
 * XAConnection objects also typically provide connection pooling capabilities
 * as well as distributed transaction capabilities. </li>
 * </ol>
 * <p>
 * Note that a JDBC driver which is accessed via the DataSource interface is
 * loaded via a JNDI lookup process. A driver loaded in this way does not
 * register itself with the <code>DriverManager</code>.
 */
public interface DataSource extends CommonDataSource, Wrapper {

    /**
     * Creates a connection to the database represented by this DataSource.
     * 
     * @return a Connection object which is a connection to the database.
     * @throws SQLException
     *             if there is a problem accessing the database.
     */
    public Connection getConnection() throws SQLException;

    /**
     * Creates a connection to the database represented by this DataSource,
     * using a supplied Username and Password,.
     * 
     * @param theUsername
     *            a String containing a User Name for the database
     * @param thePassword
     *            a String containing the Password for the user identified by
     *            <code>theUsername</code>
     * @return a Connection object which is a connection to the database.
     * @throws SQLException
     *             if there is a problem accessing the database.
     */
    public Connection getConnection(String theUsername, String thePassword)
            throws SQLException;
}
