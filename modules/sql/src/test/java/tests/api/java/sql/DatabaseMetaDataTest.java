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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import junit.framework.TestCase;

public class DatabaseMetaDataTest extends TestCase {

	/*
	 * Public statics test
	 */
	public void testPublicStatics() {

		HashMap thePublicStatics = new HashMap();
		thePublicStatics.put("sqlStateSQL99", new Integer(2));
		thePublicStatics.put("sqlStateXOpen", new Integer(1));
		thePublicStatics.put("attributeNullableUnknown", new Short((short) 2));
		thePublicStatics.put("attributeNullable", new Short((short) 1));
		thePublicStatics.put("attributeNoNulls", new Short((short) 0));
		thePublicStatics.put("tableIndexOther", new Short((short) 3));
		thePublicStatics.put("tableIndexHashed", new Short((short) 2));
		thePublicStatics.put("tableIndexClustered", new Short((short) 1));
		thePublicStatics.put("tableIndexStatistic", new Short((short) 0));
		thePublicStatics.put("typeSearchable", new Integer(3));
		thePublicStatics.put("typePredBasic", new Integer(2));
		thePublicStatics.put("typePredChar", new Integer(1));
		thePublicStatics.put("typePredNone", new Integer(0));
		thePublicStatics.put("typeNullableUnknown", new Integer(2));
		thePublicStatics.put("typeNullable", new Integer(1));
		thePublicStatics.put("typeNoNulls", new Integer(0));
		thePublicStatics.put("importedKeyNotDeferrable", new Integer(7));
		thePublicStatics.put("importedKeyInitiallyImmediate", new Integer(6));
		thePublicStatics.put("importedKeyInitiallyDeferred", new Integer(5));
		thePublicStatics.put("importedKeySetDefault", new Integer(4));
		thePublicStatics.put("importedKeyNoAction", new Integer(3));
		thePublicStatics.put("importedKeySetNull", new Integer(2));
		thePublicStatics.put("importedKeyRestrict", new Integer(1));
		thePublicStatics.put("importedKeyCascade", new Integer(0));
		thePublicStatics.put("versionColumnPseudo", new Integer(2));
		thePublicStatics.put("versionColumnNotPseudo", new Integer(1));
		thePublicStatics.put("versionColumnUnknown", new Integer(0));
		thePublicStatics.put("bestRowPseudo", new Integer(2));
		thePublicStatics.put("bestRowNotPseudo", new Integer(1));
		thePublicStatics.put("bestRowUnknown", new Integer(0));
		thePublicStatics.put("bestRowSession", new Integer(2));
		thePublicStatics.put("bestRowTransaction", new Integer(1));
		thePublicStatics.put("bestRowTemporary", new Integer(0));
		thePublicStatics.put("columnNullableUnknown", new Integer(2));
		thePublicStatics.put("columnNullable", new Integer(1));
		thePublicStatics.put("columnNoNulls", new Integer(0));
		thePublicStatics.put("procedureNullableUnknown", new Integer(2));
		thePublicStatics.put("procedureNullable", new Integer(1));
		thePublicStatics.put("procedureNoNulls", new Integer(0));
		thePublicStatics.put("procedureColumnResult", new Integer(3));
		thePublicStatics.put("procedureColumnReturn", new Integer(5));
		thePublicStatics.put("procedureColumnOut", new Integer(4));
		thePublicStatics.put("procedureColumnInOut", new Integer(2));
		thePublicStatics.put("procedureColumnIn", new Integer(1));
		thePublicStatics.put("procedureColumnUnknown", new Integer(0));
		thePublicStatics.put("procedureReturnsResult", new Integer(2));
		thePublicStatics.put("procedureNoResult", new Integer(1));
		thePublicStatics.put("procedureResultUnknown", new Integer(0));

		/*
		 * System.out.println( "sqlStateSQL99: " +
		 * DatabaseMetaData.sqlStateSQL99 ); System.out.println( "sqlStateXOpen: " +
		 * DatabaseMetaData.sqlStateXOpen ); System.out.println(
		 * "attributeNullableUnknown: " +
		 * DatabaseMetaData.attributeNullableUnknown ); System.out.println(
		 * "attributeNullable: " + DatabaseMetaData.attributeNullable );
		 * System.out.println( "attributeNoNulls: " +
		 * DatabaseMetaData.attributeNoNulls ); System.out.println(
		 * "tableIndexOther: " + DatabaseMetaData.tableIndexOther );
		 * System.out.println( "tableIndexHashed: " +
		 * DatabaseMetaData.tableIndexHashed ); System.out.println(
		 * "tableIndexClustered: " + DatabaseMetaData.tableIndexClustered );
		 * System.out.println( "tableIndexStatistic: " +
		 * DatabaseMetaData.tableIndexStatistic ); System.out.println(
		 * "typeSearchable: " + DatabaseMetaData.typeSearchable );
		 * System.out.println( "typePredBasic: " +
		 * DatabaseMetaData.typePredBasic ); System.out.println( "typePredChar: " +
		 * DatabaseMetaData.typePredChar ); System.out.println( "typePredNone: " +
		 * DatabaseMetaData.typePredNone ); System.out.println(
		 * "typeNullableUnknown: " + DatabaseMetaData.typeNullableUnknown );
		 * System.out.println( "typeNullable: " + DatabaseMetaData.typeNullable );
		 * System.out.println( "typeNoNulls: " + DatabaseMetaData.typeNoNulls );
		 * System.out.println( "importedKeyNotDeferrable: " +
		 * DatabaseMetaData.importedKeyNotDeferrable ); System.out.println(
		 * "importedKeyInitiallyImmediate: " +
		 * DatabaseMetaData.importedKeyInitiallyImmediate ); System.out.println(
		 * "importedKeyInitiallyDeferred: " +
		 * DatabaseMetaData.importedKeyInitiallyDeferred ); System.out.println(
		 * "importedKeySetDefault: " + DatabaseMetaData.importedKeySetDefault );
		 * System.out.println( "importedKeyNoAction: " +
		 * DatabaseMetaData.importedKeyNoAction ); System.out.println(
		 * "importedKeySetNull: " + DatabaseMetaData.importedKeySetNull );
		 * System.out.println( "importedKeyRestrict: " +
		 * DatabaseMetaData.importedKeyRestrict ); System.out.println(
		 * "importedKeyCascade: " + DatabaseMetaData.importedKeyCascade );
		 * System.out.println( "versionColumnPseudo: " +
		 * DatabaseMetaData.versionColumnPseudo ); System.out.println(
		 * "versionColumnNotPseudo: " + DatabaseMetaData.versionColumnNotPseudo );
		 * System.out.println( "versionColumnUnknown: " +
		 * DatabaseMetaData.versionColumnUnknown ); System.out.println(
		 * "bestRowPseudo: " + DatabaseMetaData.bestRowPseudo );
		 * System.out.println( "bestRowNotPseudo: " +
		 * DatabaseMetaData.bestRowNotPseudo ); System.out.println(
		 * "bestRowUnknown: " + DatabaseMetaData.bestRowUnknown );
		 * System.out.println( "bestRowSession: " +
		 * DatabaseMetaData.bestRowSession ); System.out.println(
		 * "bestRowTransaction: " + DatabaseMetaData.bestRowTransaction );
		 * System.out.println( "bestRowTemporary: " +
		 * DatabaseMetaData.bestRowTemporary ); System.out.println(
		 * "columnNullableUnknown: " + DatabaseMetaData.columnNullableUnknown );
		 * System.out.println( "columnNullable: " +
		 * DatabaseMetaData.columnNullable ); System.out.println(
		 * "columnNoNulls: " + DatabaseMetaData.columnNoNulls );
		 * System.out.println( "procedureNullableUnknown: " +
		 * DatabaseMetaData.procedureNullableUnknown ); System.out.println(
		 * "procedureNullable: " + DatabaseMetaData.procedureNullable );
		 * System.out.println( "procedureNoNulls: " +
		 * DatabaseMetaData.procedureNoNulls ); System.out.println(
		 * "procedureColumnResult: " + DatabaseMetaData.procedureColumnResult );
		 * System.out.println( "procedureColumnReturn: " +
		 * DatabaseMetaData.procedureColumnReturn ); System.out.println(
		 * "procedureColumnOut: " + DatabaseMetaData.procedureColumnOut );
		 * System.out.println( "procedureColumnInOut: " +
		 * DatabaseMetaData.procedureColumnInOut ); System.out.println(
		 * "procedureColumnIn: " + DatabaseMetaData.procedureColumnIn );
		 * System.out.println( "procedureColumnUnknown: " +
		 * DatabaseMetaData.procedureColumnUnknown ); System.out.println(
		 * "procedureReturnsResult: " + DatabaseMetaData.procedureReturnsResult );
		 * System.out.println( "procedureNoResult: " +
		 * DatabaseMetaData.procedureNoResult ); System.out.println(
		 * "procedureResultUnknown: " + DatabaseMetaData.procedureResultUnknown );
		 */

		Class databaseMetaDataClass;
		try {
			databaseMetaDataClass = Class.forName("java.sql.DatabaseMetaData");
		} catch (ClassNotFoundException e) {
			fail("java.sql.DatabaseMetaData class not found!");
			return;
		} // end try

		Field[] theFields = databaseMetaDataClass.getDeclaredFields();
		int requiredModifier = Modifier.PUBLIC + Modifier.STATIC
				+ Modifier.FINAL;

		int countPublicStatics = 0;
		for (int i = 0; i < theFields.length; i++) {
			String fieldName = theFields[i].getName();
			int theMods = theFields[i].getModifiers();
			if (Modifier.isPublic(theMods) && Modifier.isStatic(theMods)) {
				try {
					Object fieldValue = theFields[i].get(null);
					Object expectedValue = thePublicStatics.get(fieldName);
					if (expectedValue == null) {
						fail("Field " + fieldName + " missing!");
					} // end
					assertEquals("Field " + fieldName + " value mismatch: ",
							expectedValue, fieldValue);
					assertEquals("Field " + fieldName + " modifier mismatch: ",
							requiredModifier, theMods);
					countPublicStatics++;
				} catch (IllegalAccessException e) {
					fail("Illegal access to Field " + fieldName);
				} // end try
			} // end if
		} // end for

	} // end method testPublicStatics

} // end class DatabaseMetaDataTest
