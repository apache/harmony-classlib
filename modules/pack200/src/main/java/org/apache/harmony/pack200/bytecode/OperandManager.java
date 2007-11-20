package org.apache.harmony.pack200.bytecode;

import org.apache.harmony.pack200.Segment;
import org.apache.harmony.pack200.SegmentConstantPool;

/**
 * This class keeps track of operands used. It provides
 * API to let other classes get next elements, and also
 * knows about which classes have been used recently
 * in super, this and new references.
 */
public class OperandManager {
	
    int[] bcByte;
    int[] bcShort;
    int[] bcLocal;
    int[] bcLabel;
    int[] bcIntRef;
    int[] bcFloatRef;
    int[] bcLongRef;
    int[] bcDoubleRef;
    int[] bcStringRef;
    int[] bcClassRef;
    int[] bcFieldRef;
    int[] bcMethodRef;
    int[] bcIMethodRef;
	int[] bcThisField;
	int[] bcSuperField;
	int[] bcThisMethod;
	int[] bcSuperMethod;
	int[] bcInitRef;
    
	int bcByteIndex = 0;
	int bcShortIndex = 0;
	int bcLocalIndex = 0;
	int bcLabelIndex = 0;
	int bcIntRefIndex = 0;
	int bcFloatRefIndex = 0;
	int bcLongRefIndex = 0;
	int bcDoubleRefIndex = 0;
	int bcStringRefIndex = 0;
	int bcClassRefIndex = 0;
	int bcFieldRefIndex = 0;
	int bcMethodRefIndex = 0;
	int bcIMethodRefIndex = 0;
	int bcThisFieldIndex = 0;
	int bcSuperFieldIndex = 0;
	int bcThisMethodIndex = 0;
	int bcSuperMethodIndex = 0;
	int bcInitRefIndex = 0;
    
	Segment segment = null;
	
	String currentClass = null;
	String superClass = null;
	String newClass = null;
	
	public OperandManager(int[] bcByte, int[]  bcShort, int[]  bcLocal, int[]  bcLabel, int[]  bcIntRef, int[]  bcFloatRef, int[]  bcLongRef, int[]  bcDoubleRef, int[]  bcStringRef, int[]  bcClassRef, int[]  bcFieldRef, int[]  bcMethodRef, int[] bcIMethodRef, int[] bcThisField, int[] bcSuperField, int[] bcThisMethod, int[] bcSuperMethod, int[] bcInitRef) {
	    this.bcByte = bcByte;
	    this.bcShort = bcShort;
	    this.bcLocal = bcLocal;
	    this.bcLabel = bcLabel;
	    this.bcIntRef = bcIntRef;
	    this.bcFloatRef = bcFloatRef;
	    this.bcLongRef = bcLongRef;
	    this.bcDoubleRef = bcDoubleRef;
	    this.bcStringRef = bcStringRef;
	    this.bcClassRef = bcClassRef;
	    this.bcFieldRef = bcFieldRef;
	    this.bcMethodRef = bcMethodRef;
	    this.bcIMethodRef = bcIMethodRef;

	    this.bcThisField = bcThisField;
	    this.bcSuperField = bcSuperField;
	    this.bcThisMethod = bcThisMethod;
	    this.bcSuperMethod = bcSuperMethod;
	    this.bcInitRef = bcInitRef;
	}
    
	public int nextByte() {
		return bcByte[bcByteIndex++];		
	}
	
	public int nextShort() {
		return bcShort[bcShortIndex++];		
	}
	
	public int nextLocal() {
		return bcLocal[bcLocalIndex++];		
	}
	
	public int nextLabel() {
		return bcLabel[bcLabelIndex++];		
	}
	
	public int nextIntRef() {
		return bcIntRef[bcIntRefIndex++];		
	}
	
	public int nextFloatRef() {
		return bcFloatRef[bcFloatRefIndex++];		
	}
	
	public int nextLongRef() {
		return bcLongRef[bcLongRefIndex++];		
	}
	
	public int nextDoubleRef() {
		return bcDoubleRef[bcDoubleRefIndex++];		
	}
	
	public int nextStringRef() {
		return bcStringRef[bcStringRefIndex++];		
	}
	
	public int nextClassRef() {
		return bcClassRef[bcClassRefIndex++];		
	}
	
	public int nextFieldRef() {
		return bcFieldRef[bcFieldRefIndex++];		
	}
	
	public int nextMethodRef() {
		return bcMethodRef[bcMethodRefIndex++];		
	}
	
	public int nextIMethodRef() {
		return bcIMethodRef[bcIMethodRefIndex++];		
	}
	
	public int nextThisFieldRef() {
		return bcThisField[bcThisFieldIndex++];
	}

	public int nextSuperFieldRef() {
		return bcSuperField[bcSuperFieldIndex++];
	}

	public int nextThisMethodRef() {
		return bcThisMethod[bcThisMethodIndex++];
	}

	public int nextSuperMethodRef() {
		return bcSuperMethod[bcSuperMethodIndex++];
	}

	public int nextInitRef() {
		return bcInitRef[bcInitRefIndex++];
	}

	public static void main(String args[]) {
		int foo[] = {1, 172, 3, 4};
		OperandManager op = new OperandManager(foo, foo, foo, foo, foo, foo, foo, foo, foo, foo, foo, foo, foo, foo, foo, foo, foo, foo);
		for(int index=0; index < 4; index++) {
			System.out.println(op.nextByte());
		}
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}
	public Segment getSegment() {
		return segment;
	}
	
	public SegmentConstantPool globalConstantPool() {
		return segment.getConstantPool();
	}
	
	public void setCurrentClass(String string) {
		currentClass = string;
	}
	public void setSuperClass(String string) {
		superClass = string;
	}
	public void setNewClass(String string) {
		newClass = string;
	}
	
	public String getCurrentClass() {
		if(null==currentClass) {
			throw new Error("Current class not set yet");
		} else {
			return currentClass;
		}
	}
	public String getSuperClass() {
		if(null==superClass) {
			throw new Error("SuperClass not set yet");
		} else {
			return superClass;
		}
	}
	public String getNewClass() {
		if(null==newClass) {
			throw new Error("New class not set yet");
		} else {
			return newClass;
		}
	}
}
