/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
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
 * @author Alexey A. Ivanov
 * @version $Revision$
 */
package javax.swing.text;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.DocumentEvent.EventType;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoableEdit;

public class DefaultStyledDocument extends AbstractDocument
    implements StyledDocument {

    public static class AttributeUndoableEdit extends AbstractUndoableEdit {
        protected AttributeSet copy;
        protected Element element;
        protected boolean isReplacing;
        protected AttributeSet newAttributes;

        public AttributeUndoableEdit(final Element element,
                                     final AttributeSet newAttributes,
                                     final boolean isReplacing) {
            this.element = element;
            this.newAttributes = newAttributes;
            this.isReplacing = isReplacing;
            this.copy = element.getAttributes().copyAttributes();
        }

        public void redo() {
            final AbstractElement elem = (AbstractElement)element;
            if (isReplacing) {
                elem.removeAttributes(elem);
            }
            elem.addAttributes(newAttributes);
        }

        public void undo() {
            final AbstractElement elem = (AbstractElement)element;
            elem.removeAttributes(newAttributes);
            elem.addAttributes(copy);
        }
    }

    public class ElementBuffer implements Serializable {
        private final Element root;
        private transient DefaultDocumentEvent event;
        private transient int offset;
        private transient int length;
        private transient ChangeDesc current;

        private transient Stack changeStack;
        private transient List changes;
        private transient boolean create;

        public ElementBuffer(final Element root) {
            this.root = root;
            initChangeLists();
        }

        public Element getRootElement() {
            return root;
        }

        public void insert(final int offset, final int length,
                           final ElementSpec[] spec,
                           final DefaultDocumentEvent event) {
            prepare(offset, length, event);

            insertUpdate(spec);

            collectEdits();
        }

        public void remove(final int offset, final int length,
                           final DefaultDocumentEvent event) {
            prepare(offset, length, event);

            removeUpdate();

            applyEdits();
            collectEdits();
        }

        public void change(final int offset, final int length,
                           final DefaultDocumentEvent event) {
            prepare(offset, length, event);

            changeUpdate();

            applyEdits();
            collectEdits();
        }

        public Element clone(final Element parent, final Element clonee) {
            if (clonee.isLeaf()) {
                return createLeafElement(parent,
                                         clonee.getAttributes(),
                                         clonee.getStartOffset(),
                                         clonee.getEndOffset());
            }
            BranchElement result =
                (BranchElement)createBranchElement(parent,
                                                   clonee.getAttributes());
            final int count = clonee.getElementCount();
            if (count > 0) {
                Element[] children = new Element[count];
                for (int i = 0; i < count; i++) {
                    children[i] = clone(result, clonee.getElement(i));
                }
                result.replace(0, 0, children);
            }
            return result;
        }

        protected void insertUpdate(final ElementSpec[] spec) {
            // Find the deepest branch
            BranchElement branch = (BranchElement)root;
            Element child;
            do {
                changeStack.push(new ChangeDesc(branch));
                child = branch.getElement(branch.getElementIndex(offset));
                if (!child.isLeaf()) {
                    branch = (BranchElement)child;
                }
            } while (!child.isLeaf());

            current = (ChangeDesc)changeStack.peek();
            //Element leaf = getCharacterElement(offset);
            //current = (BranchElement)leaf.getParentElement();

            // Apply each spec to the tree
            for (int i = 0; i < spec.length; i++) {
                switch (spec[i].getType()) {
                case ElementSpec.ContentType:
                    insertContent(spec[i]);
                    break;

                case ElementSpec.EndTagType:
                    insertEndTag();
                    break;

                case ElementSpec.StartTagType:
                    insertStartTag(spec[i]);
                    break;

                default:
                    throw new Error("Unknown type in the spec");
                }
            }
            leaveParagraph();
        }

        protected void removeUpdate() {
            final int endOffset = offset + length;
            final Element startLeaf = getCharacterElement(offset);
            final Element endLeaf = endOffset == startLeaf.getEndOffset()
                                    && endOffset < startLeaf.getParentElement()
                                                   .getEndOffset()
                                    ? startLeaf
                                    : getCharacterElement(endOffset);

            if (startLeaf == endLeaf) {
                if (startLeaf.getStartOffset() == offset
                    && endOffset == startLeaf.getEndOffset()) {

                    current = new ChangeDesc(startLeaf.getParentElement());
                    current.setIndex(current.element.getElementIndex(offset));
                    current.removed.add(startLeaf);
                    changes.add(current);
                }
                return;
            }

            final Element startBranch = startLeaf.getParentElement();
            final Element endBranch = endLeaf.getParentElement();

            if (startBranch == endBranch) {
                final int index = startBranch.getElementIndex(offset);
                current = new ChangeDesc(startBranch);
                for (int i = index; i < startBranch.getElementCount(); i++) {
                    final Element child = startBranch.getElement(i);
                    if (offset <= child.getStartOffset()
                        && child.getEndOffset() <= endOffset) {

                        if (current.getIndex() < 0) {
                            current.setIndex(i);
                        }
                        current.removed.add(child);
                    }
                    if (endOffset < child.getEndOffset()) {
                        break;
                    }
                }

                changes.add(current);
            } else {
                final BranchElement parent =
                    (BranchElement)startBranch.getParentElement();
                current = new ChangeDesc(parent);
                current.setIndex(parent.getElementIndex(offset));

                final BranchElement branch =
                    (BranchElement)createBranchElement(parent,
                                                       startBranch
                                                       .getAttributes());

                List children = new LinkedList();

                // Copy elements from startBranch
                int index = startBranch.getElementIndex(offset);
                if (startBranch.getElement(index).getStartOffset() < offset) {
                    ++index;
                }
                for (int i = 0; i < index; i++) {
                    children.add(clone(branch, startBranch.getElement(i)));
                }

                // Copy elements from endBranch
                index = endBranch.getElementIndex(endOffset);
                for (int i = index; i < endBranch.getElementCount(); i++) {
                    children.add(clone(branch, endBranch.getElement(i)));
                }

                index = parent.getElementIndex(endOffset);
                for (int i = current.getIndex(); i <= index; i++) {
                    current.removed.add(parent.getElement(i));
                }
                current.added.add(branch);

                branch.replace(0, 0, listToElementArray(children));

                changes.add(current);
            }
        }

        protected void changeUpdate() {
            final int endOffset = offset + length;
            final Element startLeaf = getCharacterElement(offset);
            final Element endLeaf = getCharacterElement(endOffset);

            if (startLeaf.getStartOffset() == offset
                && endOffset == startLeaf.getEndOffset()) {
                return;
            }

            if (startLeaf == endLeaf) {
                final int start = startLeaf.getStartOffset();
                final int end = startLeaf.getEndOffset();
                if (start == offset && endOffset == end) {
                    return;
                }
                final AttributeSet attrs = startLeaf.getAttributes();

                current = new ChangeDesc(startLeaf.getParentElement());
                current.setIndex(current.element.getElementIndex(offset));
                current.removed.add(startLeaf);

                if (startLeaf.getStartOffset() < offset) {
                    current.added.add(createLeafElement(current.element, attrs,
                                                        start, offset));
                }
                current.added.add(createLeafElement(current.element, attrs,
                                                    offset, endOffset));
                if (endOffset < end) {
                    current.added.add(createLeafElement(current.element, attrs,
                                                        endOffset, end));
                }

                changes.add(current);
            } else {
                // Break the startLeaf
                int start = startLeaf.getStartOffset();
                int end = startLeaf.getEndOffset();
                AttributeSet attrs = startLeaf.getAttributes();

                if (start < offset) {
                    current = new ChangeDesc(startLeaf.getParentElement());
                    current.setIndex(current.element.getElementIndex(offset));
                    current.removed.add(startLeaf);

                    current.added.add(createLeafElement(current.element, attrs,
                                                        start, offset));
                    current.added.add(createLeafElement(current.element, attrs,
                                                        offset, end));
                    changes.add(current);
                }

                // Break the endLeaf
                start = endLeaf.getStartOffset();
                end = endLeaf.getEndOffset();
                attrs = endLeaf.getAttributes();

                if (start < endOffset && endOffset < end) {
                    final boolean sameParents = current != null
                        && current.element == endLeaf.getParentElement();
                    if (!sameParents) {
                        current = new ChangeDesc(endLeaf.getParentElement());
                        current.setIndex(current.element
                                         .getElementIndex(endOffset));
                    } else {
                        final int endIndex = current.element
                                             .getElementIndex(endOffset);
                        for (int i = current.getIndex() + 1;
                             i < endIndex; i++) {

                            final Element child = current.element.getElement(i);
                            current.removed.add(child);
                            current.added.add(child);
                        }
                    }

                    current.removed.add(endLeaf);

                    current.added.add(createLeafElement(current.element, attrs,
                                                        start, endOffset));
                    current.added.add(createLeafElement(current.element, attrs,
                                                        endOffset, end));

                    if (!sameParents) {
                        changes.add(current);
                    }
                }
            }
        }

        final void create(final ElementSpec[] specs,
                          final DefaultDocumentEvent event) {
            prepare(event.getOffset(), event.getLength(), event);
            create = true;

            // Remove all elements from the only paragraph
            current = new ChangeDesc(getParagraphElement(0));
            current.setIndex(0);
            current.added.add(createLeafElement(current.element,
                                                current.element
                                                .getElement(0).getAttributes(),
                                                length, length + 1));
            for (int i = 0; i < current.element.getElementCount(); i++) {
                current.removed.add(current.element.getElement(i));
            }
            current.apply();
            changes.add(current);
            current = null;

            // Apply each spec to the tree
            for (int i = 0; i < specs.length; i++) {
                switch (specs[i].getType()) {
                case ElementSpec.ContentType:
                    insertContent(specs[i]);
                    break;

                case ElementSpec.EndTagType:
                    insertEndTag();
                    break;

                case ElementSpec.StartTagType:
                    insertStartTag(specs[i]);
                    break;

                default:
                    throw new Error("Unknown type in the spec");
                }
            }
            leaveParagraph();

            collectEdits();
        }

        private void applyEdits() {
            for (int i = 0; i < changes.size(); i++) {
                final ChangeDesc desc = (ChangeDesc)changes.get(i);
                desc.apply();
            }
        }

        private void collectEdits() {
            while (!changeStack.empty()) {
                ChangeDesc desc = (ChangeDesc)changeStack.pop();
                if (!desc.isEmpty()) {
                    changes.add(desc);
                }
            }

            for (int i = 0; i < changes.size(); i++) {
                final ChangeDesc desc = (ChangeDesc)changes.get(i);
                if (!desc.isEmpty()) {
                    event.addEdit(desc.toElementEdit());
                }
            }
            changes.clear();
        }

        private void insertContent(final ElementSpec spec) {
            switch (spec.getDirection()) {
            case ElementSpec.OriginateDirection:
                insertContentOriginate(spec);
                break;

            case ElementSpec.JoinNextDirection:
                insertContentJoinNext(spec);
                break;

            case ElementSpec.JoinPreviousDirection:
                break;

            case ElementSpec.JoinFractureDirection:
                insertContentOriginate(spec);
                break;
            }
            offset += spec.getLength();
            length -= spec.getLength();
        }

        private void insertContentOriginate(final ElementSpec spec) {
            // First we should remove the element that was at the place
            if (current.element.getElementCount() == 0) {
                if (current.getIndex() < 0) {
                    current.setIndex(0);
                }
                current.added.add(createLeafElement(current.element,
                                                    spec.getAttributes(),
                                                    offset,
                                                    offset + spec.getLength()));
                return;
            }

            if (current.getIndex() < 0) {
                current.setIndex(current.element.getElementIndex(offset));
            }
            final Element leafToRemove = current.element
                                         .getElement(current.getIndex());

            if (offset == 0) {
                current.removed.add(leafToRemove);
            } else if (offset == event.getOffset()
                       && leafToRemove.getStartOffset() < offset
                       && offset < leafToRemove.getEndOffset()) {
                current.removed.add(leafToRemove);
                current.added.add(createLeafElement(current.element,
                                            leafToRemove.getAttributes(),
                                            leafToRemove.getStartOffset(),
                                            offset));
            }
            current.added.add(createLeafElement(current.element,
                                                spec.getAttributes(),
                                                offset, offset + spec.length));
        }

        private void insertContentJoinNext(final ElementSpec spec) {
            if (current.getIndex() < 0) {
                current.setIndex(current.element.getElementIndex(offset));
            }
            final Element leaf = current.element.getElement(current.getIndex());
            if (leaf.getStartOffset() >= offset) {
                current.removed.add(leaf);
                current.added.add(createLeafElement(current.element,
                                                    leaf.getAttributes(),
                                                    offset,
                                                    leaf.getEndOffset()));
            } else {
                final Element next =
                    current.element.getElement(current.getIndex() + 1);
                current.removed.add(leaf);
                current.removed.add(next);
                current.added.add(createLeafElement(current.element,
                                                    leaf.getAttributes(),
                                                    leaf.getStartOffset(),
                                                    offset));
                current.added.add(createLeafElement(current.element,
                                                    next.getAttributes(),
                                                    offset,
                                                    next.getEndOffset()));
            }
        }

        private void insertStartTag(final ElementSpec spec) {
            switch (spec.getDirection()) {
            case ElementSpec.OriginateDirection:
                insertStartOriginate(spec);
                break;

            case ElementSpec.JoinNextDirection:
                insertStartJoinNext(spec);
                break;

            case ElementSpec.JoinPreviousDirection:
                insertStartJoinPrevious();
                break;

            case ElementSpec.JoinFractureDirection:
                insertStartFracture(spec);
                break;

            default:
                throw new Error("Unknown direction in the ElementSpec");
            }
        }

        private void insertStartFracture(final ElementSpec spec) {
            final ChangeDesc lastChange = (ChangeDesc)changes.get(changes.size()
                                                                  - 1);
            final BranchElement lastBranch = lastChange.element;
            final AttributeSet attrs =
                spec.getDirection() == ElementSpec.OriginateDirection
                ? spec.getAttributes()
                : findLastExistedBranch().getAttributes();
            final BranchElement branch =
                (BranchElement)createBranchElement(current.element,
                                                   attrs);
            final ChangeDesc change = new ChangeDesc(branch, true);
            int index = lastBranch.getElementIndex(offset);
            if (lastBranch.getElement(index).getEndOffset() <= offset) {
                ++index;
            }
            // Now copy all elements from lastBranch to the new one
            final List children = new ArrayList();
            final int count = lastBranch.getElementCount();
            for (int i = index; i < count; i++) {
                children.add(clone(branch, lastBranch.getElement(i)));
            }
            // Now we need to remove all previously added elements which were
            // copied from added list
            for (int i = index - lastChange.index, j = index; j < count; j++) {

                final Object addedElement =
                    i > 0 && i < lastChange.added.size()
                    ? lastChange.added.get(i)
                    : null;
                final Object existingElement = lastBranch.getElement(j);
                if (addedElement == existingElement) {
                    lastChange.added.remove(addedElement);
                } else if (!lastChange.justCreated) {
                    lastChange.removed.add(existingElement);
                }
            }
            // Complete the removal of elements from last branch
            if (count - index > 0) {
                lastBranch.replace(index, count - index, new Element[0]);
            }

            // Place copied children into the new element
            branch.replace(0, 0,
                (Element[])children.toArray(new Element[children.size()]));
            //change.setIndex(0);
            //change.added.addAll(children);
            //change.apply();

            current.added.add(branch);
            if (current.index == -1) {
                current.index = current.element.getElementIndex(offset);
                if (branch.getElementCount() > 0
                    && branch.getEndOffset()
                       > current.element.getElement(current.index)
                         .getStartOffset()) {
                    ++current.index;
                }
            }
            if (!current.isApplied()) {
                current.apply();
            } else {
                int replaceIndex = current.element.getElementIndex(offset);
                if (branch.getElementCount() > 0
                    && branch.getEndOffset()
                       > current.element
                         .getElement(replaceIndex).getStartOffset()) {
                    ++replaceIndex;
                }
                current.element.replace(replaceIndex, 0,
                                        new Element[] {branch});
            }

            current = change;
            changeStack.push(current);
        }

        private void insertStartOriginate(final ElementSpec spec) {
            if (!create) {
                insertStartFracture(spec);
            } else if (current == null) {
                insertStartJoinPrevious();
            } else {
                Element branch = createBranchElement(current.element,
                                                     spec.getAttributes());
                if (current.getIndex() < 0) {
                    current.setIndex(current.element.getElementIndex(offset));
                }
                current.added.add(branch);
                current = new ChangeDesc(branch, true);
                changeStack.push(current);
            }
        }

        private void insertStartJoinNext(final ElementSpec spec) {
            final int index = current.element.getElementIndex(offset);
            current = new ChangeDesc(current.element.getElement(index));
            changeStack.push(current);
        }

        private void insertStartJoinPrevious() {
            if (current == null) {
                current = new ChangeDesc(getRootElement());
                changeStack.push(current);
            } else {
                final int index = current.element.getElementIndex(offset);
                current = new ChangeDesc(current.element.getElement(index));
                changeStack.push(current);
            }
        }

        private void insertEndTag() {
            if (current.removed.size() == 0 && current.added.size() == 0) {
                current.setIndex(current.element.getElementIndex(offset));
                Element leaf = current.element.getElement(current.getIndex());
                final int start = leaf.getStartOffset();
                final int end = leaf.getEndOffset();
                if (start < offset && offset < end
                    || start < offset + length && offset + length < end) {

                    current.removed.add(leaf);

                    if (leaf.getStartOffset() < offset) {
                        current.added.add(
                            createLeafElement(current.element,
                                              leaf.getAttributes(),
                                              leaf.getStartOffset(),
                                              offset));
                    }
                    if (offset + length < leaf.getEndOffset()) {
                        current.added.add(
                            createLeafElement(current.element,
                                              leaf.getAttributes(),
                                              offset + length,
                                              leaf.getEndOffset()));
                    }
                }
            }
            leaveParagraph();
            changes.add(current);
            changeStack.pop();
            current = (ChangeDesc)changeStack.peek();
        }

        private BranchElement findLastExistedBranch() {
            int i = changes.size() - 1;
            ChangeDesc desc = null;
            while (i >= 0 && (desc = (ChangeDesc)changes.get(i)).justCreated) {
                i--;
            }
            return i >= 0 ? desc.element : null;
        }

        private void leaveParagraph() {
            if ((current.removed.size() == 0 && current.added.size() == 0)) {
                return;
            }

            if (current.removed.size() > 0
                && offset < ((Element)current.removed.get(current.removed.size()
                                                  - 1)).getEndOffset()
                && !(current.added.size() > 0
                     && offset < ((Element)current.added
                                           .get(current.added.size() - 1))
                                           .getEndOffset())) {
                final Element leaf = current.element
                                     .getElement(current.getIndex());
                if (event.getOffset() + event.getLength()
                    < leaf.getEndOffset()) {

                    current.added.add(createLeafElement(current.element,
                                                        leaf.getAttributes(),
                                                        event.getOffset()
                                                        + event.getLength(),
                                                        leaf.getEndOffset()));
                }
            }
            current.apply();
        }

        private Element[] listToElementArray(final List list) {
            Element[] result = new Element[list.size()];
            return (Element[])list.toArray(result);
        }

        private void initChangeLists() {
            changeStack = new Stack();
            changes = new ArrayList();
        }

        private void prepare(final int offset, final int length,
                          final DefaultDocumentEvent event) {
            this.offset = offset;
            this.length = length;
            this.event  = event;

            this.changes.clear();
            this.changeStack.clear();

            this.create = false;
        }

        private void readObject(final ObjectInputStream ois)
            throws IOException, ClassNotFoundException {

            ois.defaultReadObject();
            initChangeLists();
        }

        private void writeObject(final ObjectOutputStream oos)
            throws IOException {

            oos.defaultWriteObject();
        }
    }

    public static class ElementSpec {
        public static final short ContentType = 3;
        public static final short EndTagType = 2;
        public static final short StartTagType = 1;

        public static final short JoinFractureDirection = 7;
        public static final short JoinNextDirection = 5;
        public static final short JoinPreviousDirection = 4;
        public static final short OriginateDirection = 6;

        private AttributeSet attrs;
        private short type;
        private char[] text;
        private int offset;
        private int length;
        private short direction;

        public ElementSpec(final AttributeSet attrs, final short type) {
            this(attrs, type, null, 0, 0);
        }

        public ElementSpec(final AttributeSet attrs,
                           final short type,
                           final char[] text,
                           final int offset,
                           final int length) {
            this.attrs  = attrs;
            this.type   = type;
            this.text   = text;
            this.offset = offset;
            this.length = length;

            this.direction = OriginateDirection;
        }

        public ElementSpec(final AttributeSet attrs,
                           final short type,
                           final int length) {
            this(attrs, type, null, 0, length);
        }


        public char[] getArray() {
            return text;
        }

        public AttributeSet getAttributes() {
            return attrs;
        }

        public short getDirection() {
            return direction;
        }

        public int getLength() {
            return length;
        }

        public int getOffset() {
            return offset;
        }

        public short getType() {
            return type;
        }

        public void setDirection(final short direction) {
            this.direction = direction;
        }

        public void setType(final short type) {
            this.type = type;
        }

        /*
         * The format of the string is based on 1.5 release behavior
         * which can be revealed using the following code:
         *
         *     Object obj = new DefaultStyledDocument.ElementSpec(null,
         *         DefaultStyledDocument.ElementSpec.ContentType);
         *     System.out.println(obj.toString());
         */
        public String toString() {
            String result;
            switch (type) {
            case StartTagType:
                result = "StartTag:";
                break;
            case ContentType:
                result = "Content:";
                break;
            case EndTagType:
                result = "EndTag:";
                break;
            default:
                result = "??:";
            }

            switch (direction) {
            case OriginateDirection:
                result += "Originate:";
                break;
            case JoinFractureDirection:
                result += "Fracture:";
                break;
            case JoinNextDirection:
                result += "JoinNext:";
                break;
            case JoinPreviousDirection:
                result += "JoinPrevious:";
                break;
            default:
                result += "??:";
            }

            result += length;

            return result;
        }

    }

    protected class SectionElement extends BranchElement {
        public SectionElement() {
            super(null, null);
        }

        public String getName() {
            return AbstractDocument.SectionElementName;
        }
    }

    private static final class ChangeDesc {
        public final BranchElement element;
        private int index = -1;
        public final List added = new ArrayList();
        public final List removed = new ArrayList();
        public final boolean justCreated;
        private boolean applied;

        public ChangeDesc(final Element element) {
            this(element, false);
        }

        public ChangeDesc(final Element element,
                          final boolean justCreated) {
            this.element = (BranchElement)element;
            this.justCreated = justCreated;
        }

        public void setIndex(final int index) {
            if (this.index != -1) {
                throw new Error("Index has already been set, "
                                + "and cannot be changed.");
            }
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public Element[] getChildrenAdded() {
            final Element[] result = new Element[added.size()];
            return (Element[])added.toArray(result);
        }

        public Element[] getChildrenRemoved() {
            final Element[] result = new Element[removed.size()];
            return (Element[])removed.toArray(result);
        }

        public ElementEdit toElementEdit() {
            return new ElementEdit(element, index,
                                   getChildrenRemoved(),
                                   getChildrenAdded());
        }

        public void apply() {
            if (applied || isEmpty()) {
                return;
            }
            if (index == -1) {
                throw new Error("Index is not initialized");
            }

            applied = true;
            element.replace(index, removed.size(), getChildrenAdded());
        }

        public boolean isEmpty() {
            return removed.size() == 0 && added.size() == 0;
        }

        public boolean isApplied() {
            return applied;
        }
    }

    public static final int BUFFER_SIZE_DEFAULT = 4096;
    private transient AttributeSet defaultLogicalStyle;

    protected ElementBuffer buffer;

    private ChangeListener styleContextChangeListener;
    private ChangeListener styleChangeListener;

    public DefaultStyledDocument() {
        this(new GapContent(BUFFER_SIZE_DEFAULT), new StyleContext());
    }

    public DefaultStyledDocument(final Content content,
                                 final StyleContext styles) {
        super(content, styles);
        createDefaultLogicalStyle();
        buffer = new ElementBuffer(createDefaultRoot());
    }

    public DefaultStyledDocument(final StyleContext styles) {
        this(new GapContent(BUFFER_SIZE_DEFAULT), styles);
    }

    public Style addStyle(final String name,
                          final Style parent) {
        return getStyleContext().addStyle(name, parent);
    }

    public void removeStyle(final String name) {
        getStyleContext().removeStyle(name);
    }

    public Style getStyle(final String name) {
        return getStyleContext().getStyle(name);
    }

    public Enumeration getStyleNames() {
        return getStyleContext().getStyleNames();
    }

    public Color getForeground(final AttributeSet attrs) {
        return getStyleContext().getForeground(attrs);
    }

    public Color getBackground(final AttributeSet attrs) {
        return getStyleContext().getBackground(attrs);
    }

    public Font getFont(final AttributeSet attrs) {
        return getStyleContext().getFont(attrs);
    }

    public Element getDefaultRootElement() {
        return buffer.getRootElement();
    }

    public Element getCharacterElement(final int offset) {
        final Element paragraph = getParagraphElement(offset);
        return paragraph.getElement(paragraph.getElementIndex(offset));
    }

    public Element getParagraphElement(final int offset) {
        Element root = getDefaultRootElement();
        return root.getElement(root.getElementIndex(offset));
    }

    public void setCharacterAttributes(final int offset,
                                       final int length,
                                       final AttributeSet attrs,
                                       final boolean replace) {
        if (checkInvalid(offset, length)) {
            return;
        }

        writeLock();
        try {
            final DefaultDocumentEvent event =
                new DefaultDocumentEvent(offset, length, EventType.CHANGE);

            buffer.change(offset, length, event);

            AbstractElement element;
            int currentOffset = offset;
            final int limit = offset + length;
            while (currentOffset < limit) {
                element = (AbstractElement)getCharacterElement(currentOffset);
                event.addEdit(new AttributeUndoableEdit(element,
                                                        attrs, replace));
                if (replace) {
                    element.removeAttributes(element.getAttributeNames());
                }
                element.addAttributes(attrs);
                currentOffset = element.getEndOffset();
            }

            event.end();
            fireChangedUpdate(event);
            fireUndoableEditUpdate(new UndoableEditEvent(this, event));
        } finally {
            writeUnlock();
        }
    }

    public void setParagraphAttributes(final int offset,
                                       final int length,
                                       final AttributeSet attrs,
                                       final boolean replace) {
        if (checkInvalid(offset, length)) {
            return;
        }

        writeLock();
        try {
            final DefaultDocumentEvent event =
                new DefaultDocumentEvent(offset, length, EventType.CHANGE);

            AbstractElement element;
            int currentOffset = offset;
            final int limit = offset + length;
            while (currentOffset < limit) {
                element = (AbstractElement)getParagraphElement(currentOffset);
                event.addEdit(new AttributeUndoableEdit(element,
                                                        attrs, replace));
                if (replace) {
                    element.removeAttributes(element.getAttributeNames());
                }
                element.addAttributes(attrs);
                currentOffset = element.getEndOffset();
            }

            event.end();
            fireChangedUpdate(event);
            fireUndoableEditUpdate(new UndoableEditEvent(this, event));
        } finally {
            writeUnlock();
        }
    }

    public void setLogicalStyle(final int offset,
                                final Style style) {
        final AbstractElement branch =
            (AbstractElement)getParagraphElement(offset);
        writeLock();
        try {
            branch.setResolveParent(style);
        } finally {
            writeUnlock();
        }
    }

    public Style getLogicalStyle(final int offset) {
        final Element element = getParagraphElement(offset);
        Object resolver = element.getAttributes().getResolveParent();
        return resolver instanceof Style ? (Style)resolver : null;
    }

    public void addDocumentListener(final DocumentListener listener) {
        super.addDocumentListener(listener);
        getStyleContext().addChangeListener(getStyleContextListener());
        addListenerToStyles();
    }

    public void removeDocumentListener(final DocumentListener listener) {
        super.removeDocumentListener(listener);
        if (getDocumentListeners().length == 0) {
            getStyleContext().removeChangeListener(getStyleContextListener());
            removeListenerFromStyles();
        }
    }

    protected AbstractElement createDefaultRoot() {
        final BranchElement result = new SectionElement();
        writeLock();
        try {
            final BranchElement paragraph =
                (BranchElement)createBranchElement(result, null);
            paragraph.setResolveParent(getStyle(StyleContext.DEFAULT_STYLE));
            final Element content =
                createLeafElement(paragraph, null,
                                  getStartPosition().getOffset(),
                                  getEndPosition().getOffset());
            paragraph.replace(0, 0, new Element[] {content});
            result.replace(0, 0, new Element[] {paragraph});
        } finally {
            writeUnlock();
        }
        return result;
    }

    protected void create(final ElementSpec[] specs) {
        final StringBuffer text = new StringBuffer();
        for (int i = 0; i < specs.length; i++) {
            if (specs[i].getLength() > 0) {
                text.append(specs[i].getArray(), specs[i].getOffset(),
                            specs[i].getLength());
            }
        }

        writeLock();
        try {
            if (getLength() > 0) {
                try {
                    remove(0, getLength());
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }

            final int offset = 0;
            UndoableEdit contentInsert = null;
                try {
                    contentInsert =
                        getContent().insertString(offset, text.toString());
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }

            DefaultDocumentEvent event =
                new DefaultDocumentEvent(offset, text.length(),
                                         EventType.INSERT);
            if (contentInsert != null) {
                event.addEdit(contentInsert);
            }
            event.addEdit(
                new AttributeUndoableEdit(buffer.getRootElement(),
                                          getStyleContext().getEmptySet(),
                                          true));
            ((AbstractElement)buffer.getRootElement())
                              .removeAttributes(buffer.getRootElement()
                                                .getAttributes());

            buffer.create(specs, event);

            event.end();
            fireInsertUpdate(event);
            if (contentInsert != null) {
                fireUndoableEditUpdate(new UndoableEditEvent(this, event));
            }
        } finally {
            writeUnlock();
        }
    }

    protected void insert(final int offset, final ElementSpec[] specs)
        throws BadLocationException {

        final StringBuffer text = new StringBuffer();
        for (int i = 0; i < specs.length; i++) {
            if (specs[i].getLength() > 0) {
                text.append(specs[i].getArray(), specs[i].getOffset(),
                            specs[i].getLength());
            }
        }
        writeLock();
        try {
            UndoableEdit contentInsert =
                getContent().insertString(offset, text.toString());

            DefaultDocumentEvent event =
                new DefaultDocumentEvent(offset, text.length(),
                                         EventType.INSERT);
            if (contentInsert != null) {
                event.addEdit(contentInsert);
            }

            buffer.insert(offset, text.length(), specs, event);

            event.end();
            fireInsertUpdate(event);
            if (contentInsert != null) {
                fireUndoableEditUpdate(new UndoableEditEvent(this, event));
            }
        } finally {
            writeUnlock();
        }
    }

    protected void insertUpdate(final DefaultDocumentEvent event,
                                final AttributeSet attrs) {
        final AttributeSet attributes = attrs == null
                                        ? getStyleContext().getEmptySet()
                                        : attrs;

        final List specs = new LinkedList();

        String text = null;
        final int offset = event.getOffset();
        final int length = event.getLength();

        try {
            text = getText(offset, length);
        } catch (final BadLocationException e) { }

        boolean splitPrevParagraph = false;
        try {
            splitPrevParagraph = offset > 0
                                 && getText(offset - 1, 1).charAt(0) == '\n';
        } catch (final BadLocationException e) { }

        final int firstBreak = text.indexOf('\n');
        final int lastBreak = text.lastIndexOf('\n');
        final boolean hasLineBreak = firstBreak != -1;

        Element charElem = getCharacterElement(offset);
        ElementSpec spec;
        if (!hasLineBreak) {
            if (splitPrevParagraph) {
                specs.add(new ElementSpec(null, ElementSpec.EndTagType));

                spec = new ElementSpec(defaultLogicalStyle,
                                       ElementSpec.StartTagType);
                spec.setDirection(ElementSpec.JoinNextDirection);
                specs.add(spec);
            }
            spec = new ElementSpec(attributes, ElementSpec.ContentType, length);
            if (charElem.getAttributes().isEqual(attributes)) {
                spec.setDirection(splitPrevParagraph
                                  ? ElementSpec.JoinNextDirection
                                  : ElementSpec.JoinPreviousDirection);
            }
            specs.add(spec);
        } else {
            int currentOffset = offset;
            int currentIndex = firstBreak;
            int processedLength = 0;

            if (splitPrevParagraph) {
                specs.add(new ElementSpec(null, ElementSpec.EndTagType));

                specs.add(new ElementSpec(defaultLogicalStyle,
                                          ElementSpec.StartTagType));
            }

            while (currentOffset < offset + length) {
                if (!(currentIndex < 0)) {
                    spec = new ElementSpec(attributes, ElementSpec.ContentType,
                                           currentIndex + 1 - processedLength);
                    currentOffset += spec.getLength();
                    processedLength += spec.getLength();
                    if (specs.size() == 0
                        && charElem.getAttributes().isEqual(attributes)) {

                        spec.setDirection(ElementSpec.JoinPreviousDirection);
                    }
                    specs.add(spec);

                    specs.add(new ElementSpec(null, ElementSpec.EndTagType));

                    spec = new ElementSpec(defaultLogicalStyle,
                                           ElementSpec.StartTagType);
                    if (currentIndex == lastBreak) {
                        spec.setDirection(splitPrevParagraph
                                          ? ElementSpec.JoinNextDirection
                                          : ElementSpec.JoinFractureDirection);
                    }
                    specs.add(spec);

                    currentIndex = text.indexOf('\n', currentIndex + 1);
                } else {
                    spec = new ElementSpec(attributes, ElementSpec.ContentType,
                                           length - processedLength);
                    currentOffset += spec.getLength();
                    processedLength += spec.getLength();
                    if (getCharacterElement(currentOffset)
                        .getAttributes().isEqual(attributes)) {

                        spec.setDirection(ElementSpec.JoinNextDirection);
                    }
                    specs.add(spec);
                }
            }

        }

        final Object[] specArray = specs.toArray(new ElementSpec[specs.size()]);
        buffer.insert(offset, length, (ElementSpec[])specArray, event);

        super.insertUpdate(event, attrs);
    }

    protected void removeUpdate(final DefaultDocumentEvent event) {
        buffer.remove(event.getOffset(), event.getLength(), event);
    }

    protected void styleChanged(final Style style) {
    }

    private void addListenerToStyles() {
        final Enumeration names = getStyleNames();
        while (names.hasMoreElements()) {
            String name = (String)names.nextElement();
            getStyle(name).addChangeListener(getStyleChangeListener());
        }
    }

    private void removeListenerFromStyles() {
        final Enumeration names = getStyleNames();
        while (names.hasMoreElements()) {
            String name = (String)names.nextElement();
            getStyle(name).removeChangeListener(getStyleChangeListener());
        }
    }

    private boolean checkInvalid(final int offset, final int length) {
        return offset < 0 || length <= 0 || offset + length > getLength() + 1;
    }

    private void createDefaultLogicalStyle() {
        final StyleContext styles = getStyleContext();
        defaultLogicalStyle =
            styles.addAttribute(styles.getEmptySet(),
                                AttributeSet.ResolveAttribute,
                                styles.getStyle(StyleContext.DEFAULT_STYLE));
    }

    private ChangeListener getStyleChangeListener() {
        if (styleChangeListener == null) {
            styleChangeListener = new ChangeListener() {
                public void stateChanged(final ChangeEvent e) {
                    styleChanged((Style)e.getSource());
                }
            };
        }
        return styleChangeListener;
    }

    private ChangeListener getStyleContextListener() {
        if (styleContextChangeListener == null) {
            styleContextChangeListener = new ChangeListener() {
                public void stateChanged(final ChangeEvent e) {
                    removeListenerFromStyles();
                    addListenerToStyles();
                }
            };
        }
        return styleContextChangeListener;
    }

    private StyleContext getStyleContext() {
        return (StyleContext)getAttributeContext();
    }

    private void readObject(final ObjectInputStream ois)
        throws IOException, ClassNotFoundException {

        ois.defaultReadObject();
        createDefaultLogicalStyle();
    }

    private void writeObject(final ObjectOutputStream oos)
        throws IOException {

        oos.defaultWriteObject();
    }
}

