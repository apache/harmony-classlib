package org.apache.harmony.archive.internal.pack200.bytecode;


public abstract class CPConstantNumber extends CPConstant {

	public CPConstantNumber(byte tag, Object value) {
		super(tag, value);
	}

	protected Number getNumber() {
		return (Number)getValue();
	}


}
