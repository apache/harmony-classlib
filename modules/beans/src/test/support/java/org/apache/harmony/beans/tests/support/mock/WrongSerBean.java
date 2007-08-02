package org.apache.harmony.beans.tests.support.mock;

import java.io.Serializable;

/**
 * A bean class which .ser file is wrong.
 * Used for BeansTest.test_instantiate_withWrongSer()
 */
public class WrongSerBean implements Serializable{
	private int intField;
	private char charField;
	private String str;
	
	public WrongSerBean(){
		super();
	}
	
	public WrongSerBean(int intField, char charField){
		this.intField = intField;
		this.charField = charField;
		this.str = "test";
	}

	public char getCharField() {
		return charField;
	}

	public void setCharField(char charField) {
		this.charField = charField;
	}

	public int getIntField() {
		return intField;
	}

	public void setIntField(int intField) {
		this.intField = intField;
	}
	
	
}
