package org.apache.harmony.beans.tests.support.mock;

import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;

public class FakeFox041 extends FakeFox04 {

    public int[] getTwoProp() {
        return null;
    }

    // throwing PropertyVetoException makes this property constrained.
    public void setTwoProp(int[] i) throws PropertyVetoException {
    }

    // being able to add/remove listeners makes this classes properties bound.
    // but it does not bind properties in any superclasses.
    // both add and remove methods are required.
    public void addPropertyChangeListener(PropertyChangeListener l) {
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
    }

}
