package org.apache.harmony.beans.tests.support.mock;

import java.beans.PropertyVetoException;

public class FakeFox0411 extends FakeFox041 {

    public int[] getThreeProp() {
        return null;
    }

    // throwing PropertyVetoException makes this property constrained.
    public void setThreeProp(int[] i) {
    }

}
