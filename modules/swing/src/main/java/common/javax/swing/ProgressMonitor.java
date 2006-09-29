/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Dennis Ushakov
 * @version $Revision$
 */
package javax.swing;

import java.awt.Component;
import java.awt.IllegalComponentStateException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleComponent;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleText;
import javax.accessibility.AccessibleValue;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;

public class ProgressMonitor implements Accessible {
    protected class AccessibleProgressMonitor extends AccessibleContext implements AccessibleText, ChangeListener, PropertyChangeListener {
        protected AccessibleProgressMonitor() {
        }
        public void stateChanged(final ChangeEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public void propertyChange(final PropertyChangeEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public String getAccessibleName() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public String getAccessibleDescription() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public AccessibleRole getAccessibleRole() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public AccessibleStateSet getAccessibleStateSet() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public Accessible getAccessibleParent() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getAccessibleIndexInParent() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getAccessibleChildrenCount() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public Accessible getAccessibleChild(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public Locale getLocale() throws IllegalComponentStateException {
            throw new UnsupportedOperationException("Not implemented");
        }
        public AccessibleComponent getAccessibleComponent() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public AccessibleValue getAccessibleValue() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public AccessibleText getAccessibleText() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getIndexAtPoint(final Point p) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public Rectangle getCharacterBounds(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getCharCount() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getCaretPosition() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public String getAtIndex(final int part, final int index) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public String getAfterIndex(final int part, final int index) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public String getBeforeIndex(final int part, final int index) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public AttributeSet getCharacterAttribute(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getSelectionStart() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public int getSelectionEnd() {
            throw new UnsupportedOperationException("Not implemented");
        }
        public String getSelectedText() {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    private Action cancelAction = new AbstractAction() {
        public void actionPerformed(final ActionEvent e) {
            close();
            isCancelled = true;
        }
    };

    protected AccessibleContext accessibleContext;

    private static final int DEFAULT_MILLIS_TO_DECIDE = 500;
    private static final int DEFAULT_MILLIS_TO_POPUP = 2000;

    private int millisToDecideToPopup = DEFAULT_MILLIS_TO_DECIDE;
    private int millisToPopup = DEFAULT_MILLIS_TO_POPUP;
    private Timer shouldShowTimer;

    private int max;
    private int min;
    private int progress;

    private JProgressBar progressBar;
    private JDialog progressDialog;
    private Component parentComponent;

    private JLabel noteLabel;
    private Object message;

    private boolean shouldShow;
    private boolean isCancelled;

    public ProgressMonitor(final Component parentComponent, final Object message,
                           final String note, final int min, final int max) {
        this.parentComponent = parentComponent;
        this.message = message;
        noteLabel = new JLabel(note);
        this.min = min;
        progress = min;
        this.max = max;

        startShouldShowTimer();
    }

    public void setProgress(final int progress) {
        int oldProgress = this.progress;

        if (progress >= max) {
            close();
        } else if (progress > this.progress) {
            this.progress = progress;

            if (shouldShow && !isCancelled && progressVisible(oldProgress, progress)) {
                if (progressDialog == null) {
                    showDialog();
                }
                progressBar.setValue(progress);
            }
        }
    }

    public void close() {
        if (progressDialog != null) {
            progressDialog.dispose();
            progressDialog = null;
        }
    }

    public int getMinimum() {
        return min;
    }

    public void setMinimum(final int min) {
        this.min = min;
        if (progressBar != null) {
            progressBar.setMinimum(min);
        }
    }

    public int getMaximum() {
        return max;
    }

    public void setMaximum(final int max) {
        this.max = max;
        if (progressBar != null) {
            progressBar.setMaximum(max);
        }
    }

    public boolean isCanceled() {
        return isCancelled;
    }

    public void setMillisToDecideToPopup(final int millisToDecideToPopup) {
        this.millisToDecideToPopup = millisToDecideToPopup;
        if (shouldShowTimer != null) {
            shouldShowTimer.setDelay(millisToDecideToPopup);
        }
    }

    public int getMillisToDecideToPopup() {
        return millisToDecideToPopup;
    }

    public void setMillisToPopup(final int millisToPopup) {
        this.millisToPopup = millisToPopup;
    }

    public int getMillisToPopup() {
        return millisToPopup;
    }

    public void setNote(final String note) {
        noteLabel.setText(note);
    }

    public String getNote() {
        return noteLabel.getText();
    }

    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AccessibleProgressMonitor();
        }
        return accessibleContext;
    }


    private void startShouldShowTimer() {
        shouldShow = false;
        if (shouldShowTimer == null) {
            shouldShowTimer = new Timer(millisToDecideToPopup, new AbstractAction() {
                public void actionPerformed(final ActionEvent e) {
                    shouldShow = (max - min) * millisToDecideToPopup > millisToPopup * (progress - min);
                }
            });
        }
        shouldShowTimer.setRepeats(false);
        shouldShowTimer.restart();
    }

    private void showDialog() {
        progressBar = new JProgressBar(min, max);

        JButton cancelButton = new JButton();
        cancelButton.setAction(cancelAction);
        cancelButton.setText(UIManager.getString("OptionPane.cancelButtonText"));
        cancelButton.setMnemonic(UIManager.getInt("OptionPane.cancelButtonMnemonic"));

        Object[] topPanel = {message, noteLabel, progressBar};
        Object[] bottomPanel = {cancelButton};
        JOptionPane dialogCreator = new JOptionPane(topPanel, JOptionPane.INFORMATION_MESSAGE,
                                                    JOptionPane.DEFAULT_OPTION, null, bottomPanel);

        progressDialog = dialogCreator.createDialog(parentComponent, UIManager.getString("ProgressMonitor.progressText"));
        progressDialog.setModal(false);

        progressDialog.addWindowListener(new WindowAdapter(){
            public void windowClosing(final WindowEvent e) {
                cancelAction.actionPerformed(null);
            }
        });
        progressDialog.setVisible(true);
    }

    private boolean progressVisible(final int oldProgress, final int newProgress) {
        return (newProgress - oldProgress) * 100 >= (max - min);
    }
}
