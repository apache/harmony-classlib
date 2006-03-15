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

package tests.api.java.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class Impl_DatabaseMetaData implements DatabaseMetaData {

	public Impl_DatabaseMetaData() {
		super();
	} // end constructor

	public boolean allProceduresAreCallable() {

		return true;
	} // end method allProceduresAreCallable

	public boolean allTablesAreSelectable() {

		return true;
	} // end method allTablesAreSelectable

	public String getURL() {

		return null;
	} // end method getURL

	public String getUserName() {

		return null;
	} // end method getUserName

	public boolean isReadOnly() {

		return true;
	} // end method isReadOnly

	public boolean nullsAreSortedHigh() {

		return false;
	} // end method nullsAreSortedHigh

	public boolean nullsAreSortedLow() {

		return false;
	} // end method nullsAreSortedLow

	public boolean nullsAreSortedAtStart() {

		return true;
	} // end method nullsAreSortedAtStart

	public boolean nullsAreSortedAtEnd() {

		return true;
	} // end method nullsAreSortedAtEnd

	public String getDatabaseProductName() {

		return null;
	} // end method getDatabaseProductName

	public String getDatabaseProductVersion() {

		return null;
	} // end method getDatabaseProductVersion

	public String getDriverName() {

		return null;
	} // end method getDriverName

	public String getDriverVersion() {

		return null;
	} // end method getDriverVersion

	public int getDriverMajorVersion() {

		return 61603206;
	} // end method getDriverMajorVersion

	public int getDriverMinorVersion() {

		return 833369254;
	} // end method getDriverMinorVersion

	public boolean usesLocalFiles() {

		return false;
	} // end method usesLocalFiles

	public boolean usesLocalFilePerTable() {

		return false;
	} // end method usesLocalFilePerTable

	public boolean supportsMixedCaseIdentifiers() {

		return false;
	} // end method supportsMixedCaseIdentifiers

	public boolean storesUpperCaseIdentifiers() {

		return false;
	} // end method storesUpperCaseIdentifiers

	public boolean storesLowerCaseIdentifiers() {

		return true;
	} // end method storesLowerCaseIdentifiers

	public boolean storesMixedCaseIdentifiers() {

		return true;
	} // end method storesMixedCaseIdentifiers

	public boolean supportsMixedCaseQuotedIdentifiers() {

		return true;
	} // end method supportsMixedCaseQuotedIdentifiers

	public boolean storesUpperCaseQuotedIdentifiers() {

		return true;
	} // end method storesUpperCaseQuotedIdentifiers

	public boolean storesLowerCaseQuotedIdentifiers() {

		return true;
	} // end method storesLowerCaseQuotedIdentifiers

	public boolean storesMixedCaseQuotedIdentifiers() {

		return false;
	} // end method storesMixedCaseQuotedIdentifiers

	public String getIdentifierQuoteString() {

		return null;
	} // end method getIdentifierQuoteString

	public String getSQLKeywords() {

		return null;
	} // end method getSQLKeywords

	public String getNumericFunctions() {

		return null;
	} // end method getNumericFunctions

	public String getStringFunctions() {

		return null;
	} // end method getStringFunctions

	public String getSystemFunctions() {

		return null;
	} // end method getSystemFunctions

	public String getTimeDateFunctions() {

		return null;
	} // end method getTimeDateFunctions

	public String getSearchStringEscape() {

		return null;
	} // end method getSearchStringEscape

	public String getExtraNameCharacters() {

		return null;
	} // end method getExtraNameCharacters

	public boolean supportsAlterTableWithAddColumn() {

		return false;
	} // end method supportsAlterTableWithAddColumn

	public boolean supportsAlterTableWithDropColumn() {

		return false;
	} // end method supportsAlterTableWithDropColumn

	public boolean supportsColumnAliasing() {

		return true;
	} // end method supportsColumnAliasing

	public boolean nullPlusNonNullIsNull() {

		return false;
	} // end method nullPlusNonNullIsNull

	public boolean supportsConvert() {

		return false;
	} // end method supportsConvert

	public boolean supportsConvert(int parm1, int parm2) {

		return false;
	} // end method supportsConvert

	public boolean supportsTableCorrelationNames() {

		return true;
	} // end method supportsTableCorrelationNames

	public boolean supportsDifferentTableCorrelationNames() {

		return false;
	} // end method supportsDifferentTableCorrelationNames

	public boolean supportsExpressionsInOrderBy() {

		return true;
	} // end method supportsExpressionsInOrderBy

	public boolean supportsOrderByUnrelated() {

		return true;
	} // end method supportsOrderByUnrelated

	public boolean supportsGroupBy() {

		return true;
	} // end method supportsGroupBy

	public boolean supportsGroupByUnrelated() {

		return true;
	} // end method supportsGroupByUnrelated

	public boolean supportsGroupByBeyondSelect() {

		return false;
	} // end method supportsGroupByBeyondSelect

	public boolean supportsLikeEscapeClause() {

		return false;
	} // end method supportsLikeEscapeClause

	public boolean supportsMultipleResultSets() {

		return false;
	} // end method supportsMultipleResultSets

	public boolean supportsMultipleTransactions() {

		return false;
	} // end method supportsMultipleTransactions

	public boolean supportsNonNullableColumns() {

		return false;
	} // end method supportsNonNullableColumns

	public boolean supportsMinimumSQLGrammar() {

		return true;
	} // end method supportsMinimumSQLGrammar

	public boolean supportsCoreSQLGrammar() {

		return true;
	} // end method supportsCoreSQLGrammar

	public boolean supportsExtendedSQLGrammar() {

		return false;
	} // end method supportsExtendedSQLGrammar

	public boolean supportsANSI92EntryLevelSQL() {

		return true;
	} // end method supportsANSI92EntryLevelSQL

	public boolean supportsANSI92IntermediateSQL() {

		return true;
	} // end method supportsANSI92IntermediateSQL

	public boolean supportsANSI92FullSQL() {

		return true;
	} // end method supportsANSI92FullSQL

	public boolean supportsIntegrityEnhancementFacility() {

		return false;
	} // end method supportsIntegrityEnhancementFacility

	public boolean supportsOuterJoins() {

		return false;
	} // end method supportsOuterJoins

	public boolean supportsFullOuterJoins() {

		return true;
	} // end method supportsFullOuterJoins

	public boolean supportsLimitedOuterJoins() {

		return false;
	} // end method supportsLimitedOuterJoins

	public String getSchemaTerm() {

		return null;
	} // end method getSchemaTerm

	public String getProcedureTerm() {

		return null;
	} // end method getProcedureTerm

	public String getCatalogTerm() {

		return null;
	} // end method getCatalogTerm

	public boolean isCatalogAtStart() {

		return true;
	} // end method isCatalogAtStart

	public String getCatalogSeparator() {

		return null;
	} // end method getCatalogSeparator

	public boolean supportsSchemasInDataManipulation() {

		return false;
	} // end method supportsSchemasInDataManipulation

	public boolean supportsSchemasInProcedureCalls() {

		return false;
	} // end method supportsSchemasInProcedureCalls

	public boolean supportsSchemasInTableDefinitions() {

		return true;
	} // end method supportsSchemasInTableDefinitions

	public boolean supportsSchemasInIndexDefinitions() {

		return true;
	} // end method supportsSchemasInIndexDefinitions

	public boolean supportsSchemasInPrivilegeDefinitions() {

		return false;
	} // end method supportsSchemasInPrivilegeDefinitions

	public boolean supportsCatalogsInDataManipulation() {

		return false;
	} // end method supportsCatalogsInDataManipulation

	public boolean supportsCatalogsInProcedureCalls() {

		return false;
	} // end method supportsCatalogsInProcedureCalls

	public boolean supportsCatalogsInTableDefinitions() {

		return false;
	} // end method supportsCatalogsInTableDefinitions

	public boolean supportsCatalogsInIndexDefinitions() {

		return true;
	} // end method supportsCatalogsInIndexDefinitions

	public boolean supportsCatalogsInPrivilegeDefinitions() {

		return false;
	} // end method supportsCatalogsInPrivilegeDefinitions

	public boolean supportsPositionedDelete() {

		return true;
	} // end method supportsPositionedDelete

	public boolean supportsPositionedUpdate() {

		return true;
	} // end method supportsPositionedUpdate

	public boolean supportsSelectForUpdate() {

		return true;
	} // end method supportsSelectForUpdate

	public boolean supportsStoredProcedures() {

		return true;
	} // end method supportsStoredProcedures

	public boolean supportsSubqueriesInComparisons() {

		return true;
	} // end method supportsSubqueriesInComparisons

	public boolean supportsSubqueriesInExists() {

		return false;
	} // end method supportsSubqueriesInExists

	public boolean supportsSubqueriesInIns() {

		return false;
	} // end method supportsSubqueriesInIns

	public boolean supportsSubqueriesInQuantifieds() {

		return false;
	} // end method supportsSubqueriesInQuantifieds

	public boolean supportsCorrelatedSubqueries() {

		return true;
	} // end method supportsCorrelatedSubqueries

	public boolean supportsUnion() {

		return true;
	} // end method supportsUnion

	public boolean supportsUnionAll() {

		return false;
	} // end method supportsUnionAll

	public boolean supportsOpenCursorsAcrossCommit() {

		return true;
	} // end method supportsOpenCursorsAcrossCommit

	public boolean supportsOpenCursorsAcrossRollback() {

		return true;
	} // end method supportsOpenCursorsAcrossRollback

	public boolean supportsOpenStatementsAcrossCommit() {

		return true;
	} // end method supportsOpenStatementsAcrossCommit

	public boolean supportsOpenStatementsAcrossRollback() {

		return false;
	} // end method supportsOpenStatementsAcrossRollback

	public int getMaxBinaryLiteralLength() {

		return 362957471;
	} // end method getMaxBinaryLiteralLength

	public int getMaxCharLiteralLength() {

		return 1550865190;
	} // end method getMaxCharLiteralLength

	public int getMaxColumnNameLength() {

		return 876239751;
	} // end method getMaxColumnNameLength

	public int getMaxColumnsInGroupBy() {

		return 1862881253;
	} // end method getMaxColumnsInGroupBy

	public int getMaxColumnsInIndex() {

		return 830898910;
	} // end method getMaxColumnsInIndex

	public int getMaxColumnsInOrderBy() {

		return 1749757802;
	} // end method getMaxColumnsInOrderBy

	public int getMaxColumnsInSelect() {

		return 26691343;
	} // end method getMaxColumnsInSelect

	public int getMaxColumnsInTable() {

		return 2040676013;
	} // end method getMaxColumnsInTable

	public int getMaxConnections() {

		return 424071034;
	} // end method getMaxConnections

	public int getMaxCursorNameLength() {

		return -1731767936;
	} // end method getMaxCursorNameLength

	public int getMaxIndexLength() {

		return -1478012889;
	} // end method getMaxIndexLength

	public int getMaxSchemaNameLength() {

		return 1031741715;
	} // end method getMaxSchemaNameLength

	public int getMaxProcedureNameLength() {

		return -1317963516;
	} // end method getMaxProcedureNameLength

	public int getMaxCatalogNameLength() {

		return 66984415;
	} // end method getMaxCatalogNameLength

	public int getMaxRowSize() {

		return 746561690;
	} // end method getMaxRowSize

	public boolean doesMaxRowSizeIncludeBlobs() {

		return false;
	} // end method doesMaxRowSizeIncludeBlobs

	public int getMaxStatementLength() {

		return -1774364912;
	} // end method getMaxStatementLength

	public int getMaxStatements() {

		return -150904006;
	} // end method getMaxStatements

	public int getMaxTableNameLength() {

		return -1193255272;
	} // end method getMaxTableNameLength

	public int getMaxTablesInSelect() {

		return -835633948;
	} // end method getMaxTablesInSelect

	public int getMaxUserNameLength() {

		return 1633873125;
	} // end method getMaxUserNameLength

	public int getDefaultTransactionIsolation() {

		return 1017227733;
	} // end method getDefaultTransactionIsolation

	public boolean supportsTransactions() {

		return false;
	} // end method supportsTransactions

	public boolean supportsTransactionIsolationLevel(int parm1) {

		return false;
	} // end method supportsTransactionIsolationLevel

	public boolean supportsDataDefinitionAndDataManipulationTransactions() {

		return true;
	} // end method supportsDataDefinitionAndDataManipulationTransactions

	public boolean supportsDataManipulationTransactionsOnly() {

		return false;
	} // end method supportsDataManipulationTransactionsOnly

	public boolean dataDefinitionCausesTransactionCommit() {

		return false;
	} // end method dataDefinitionCausesTransactionCommit

	public boolean dataDefinitionIgnoredInTransactions() {

		return false;
	} // end method dataDefinitionIgnoredInTransactions

	public ResultSet getProcedures(String parm1, String parm2, String parm3) {

		return null;
	} // end method getProcedures

	public ResultSet getProcedureColumns(String parm1, String parm2,
			String parm3, String parm4) {

		return null;
	} // end method getProcedureColumns

	public ResultSet getTables(String parm1, String parm2, String parm3,
			String[] parm4) {

		return null;
	} // end method getTables

	public ResultSet getSchemas() {

		return null;
	} // end method getSchemas

	public ResultSet getCatalogs() {

		return null;
	} // end method getCatalogs

	public ResultSet getTableTypes() {

		return null;
	} // end method getTableTypes

	public ResultSet getColumns(String parm1, String parm2, String parm3,
			String parm4) {

		return null;
	} // end method getColumns

	public ResultSet getColumnPrivileges(String parm1, String parm2,
			String parm3, String parm4) {

		return null;
	} // end method getColumnPrivileges

	public ResultSet getTablePrivileges(String parm1, String parm2, String parm3) {

		return null;
	} // end method getTablePrivileges

	public ResultSet getBestRowIdentifier(String parm1, String parm2,
			String parm3, int parm4, boolean parm5) {

		return null;
	} // end method getBestRowIdentifier

	public ResultSet getVersionColumns(String parm1, String parm2, String parm3) {

		return null;
	} // end method getVersionColumns

	public ResultSet getPrimaryKeys(String parm1, String parm2, String parm3) {

		return null;
	} // end method getPrimaryKeys

	public ResultSet getImportedKeys(String parm1, String parm2, String parm3) {

		return null;
	} // end method getImportedKeys

	public ResultSet getExportedKeys(String parm1, String parm2, String parm3) {

		return null;
	} // end method getExportedKeys

	public ResultSet getCrossReference(String parm1, String parm2,
			String parm3, String parm4, String parm5, String parm6) {

		return null;
	} // end method getCrossReference

	public ResultSet getTypeInfo() {

		return null;
	} // end method getTypeInfo

	public ResultSet getIndexInfo(String parm1, String parm2, String parm3,
			boolean parm4, boolean parm5) {

		return null;
	} // end method getIndexInfo

	public boolean supportsResultSetType(int parm1) {

		return true;
	} // end method supportsResultSetType

	public boolean supportsResultSetConcurrency(int parm1, int parm2) {

		return true;
	} // end method supportsResultSetConcurrency

	public boolean ownUpdatesAreVisible(int parm1) {

		return false;
	} // end method ownUpdatesAreVisible

	public boolean ownDeletesAreVisible(int parm1) {

		return false;
	} // end method ownDeletesAreVisible

	public boolean ownInsertsAreVisible(int parm1) {

		return true;
	} // end method ownInsertsAreVisible

	public boolean othersUpdatesAreVisible(int parm1) {

		return true;
	} // end method othersUpdatesAreVisible

	public boolean othersDeletesAreVisible(int parm1) {

		return true;
	} // end method othersDeletesAreVisible

	public boolean othersInsertsAreVisible(int parm1) {

		return true;
	} // end method othersInsertsAreVisible

	public boolean updatesAreDetected(int parm1) {

		return false;
	} // end method updatesAreDetected

	public boolean deletesAreDetected(int parm1) {

		return true;
	} // end method deletesAreDetected

	public boolean insertsAreDetected(int parm1) {

		return true;
	} // end method insertsAreDetected

	public boolean supportsBatchUpdates() {

		return false;
	} // end method supportsBatchUpdates

	public ResultSet getUDTs(String parm1, String parm2, String parm3,
			int[] parm4) {

		return null;
	} // end method getUDTs

	public Connection getConnection() {

		return null;
	} // end method getConnection

	public boolean supportsSavepoints() {

		return false;
	} // end method supportsSavepoints

	public boolean supportsNamedParameters() {

		return false;
	} // end method supportsNamedParameters

	public boolean supportsMultipleOpenResults() {

		return false;
	} // end method supportsMultipleOpenResults

	public boolean supportsGetGeneratedKeys() {

		return false;
	} // end method supportsGetGeneratedKeys

	public ResultSet getSuperTypes(String parm1, String parm2, String parm3) {

		return null;
	} // end method getSuperTypes

	public ResultSet getSuperTables(String parm1, String parm2, String parm3) {

		return null;
	} // end method getSuperTables

	public ResultSet getAttributes(String parm1, String parm2, String parm3,
			String parm4) {

		return null;
	} // end method getAttributes

	public boolean supportsResultSetHoldability(int parm1) {

		return true;
	} // end method supportsResultSetHoldability

	public int getResultSetHoldability() {

		return 2084363747;
	} // end method getResultSetHoldability

	public int getDatabaseMajorVersion() {

		return -984289201;
	} // end method getDatabaseMajorVersion

	public int getDatabaseMinorVersion() {

		return -19204050;
	} // end method getDatabaseMinorVersion

	public int getJDBCMajorVersion() {

		return -935937342;
	} // end method getJDBCMajorVersion

	public int getJDBCMinorVersion() {

		return 1874384348;
	} // end method getJDBCMinorVersion

	public int getSQLStateType() {

		return 802510040;
	} // end method getSQLStateType

	public boolean locatorsUpdateCopy() {

		return true;
	} // end method locatorsUpdateCopy

	public boolean supportsStatementPooling() {

		return false;
	} // end method supportsStatementPooling

} // end class DatabaseMetaDataTest
