package org.apache.harmony.archive.internal.pack200.bytecode;


public abstract class CPConstant extends ConstantPoolEntry {

	private Object value;

	public CPConstant(byte tag, Object value) {
		super(tag);
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final CPConstant other = (CPConstant) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	protected Object getValue() {
		return value;
	}



}
