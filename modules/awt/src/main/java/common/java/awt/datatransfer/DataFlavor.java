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
 * @author Michael Danilov
 * @version $Revision$
 */
package java.awt.datatransfer;

import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.util.*;

import org.apache.harmony.awt.datatransfer.*;


public class DataFlavor implements Externalizable, Cloneable {

    private static final long serialVersionUID = 8367026044764648243L;

    /**
     * @deprecated
     */
    public static final DataFlavor plainTextFlavor =
            new DataFlavor("text/plain; charset=unicode; class=java.io.InputStream",
                    "Plain Text");

    public static final DataFlavor stringFlavor =
            new DataFlavor("application/x-java-serialized-object; class=java.lang.String",
                    "Unicode String");

    public static final DataFlavor imageFlavor =
            new DataFlavor("image/x-java-image; class=java.awt.Image",
                    "Image");

    public static final DataFlavor javaFileListFlavor =
            new DataFlavor("application/x-java-file-list; class=java.util.List",
                    "application/x-java-file-list");

    public static final String javaJVMLocalObjectMimeType =
            "application/x-java-jvm-local-objectref";

    public static final String javaRemoteObjectMimeType =
            "application/x-java-remote-object";

    public static final String javaSerializedObjectMimeType =
            "application/x-java-serialized-object";

    private static final String sortedTextFlavors[] = {
            "text/sgml",
            "text/xml",
            "text/html",
            "text/rtf",
            "text/enriched",
            "text/richtext",
            "text/uri-list",
            "text/tab-separated-values",
            "text/t140" ,
            "text/rfc822-headers",
            "text/parityfec",
            "text/directory",
            "text/css",
            "text/calendar",
            "application/x-java-serialized-object",
            "text/plain"
    };

    private static DataFlavor plainUnicodeFlavor = null;

    private String humanPresentableName;
    private Class representationClass;
    private MimeTypeProcessor.MimeType mimeInfo;

    public static final DataFlavor getTextPlainUnicodeFlavor() {
        if (plainUnicodeFlavor == null) {
            plainUnicodeFlavor = new DataFlavor("text/plain"
                    + "; charset=" + DTK.getDTK().getDefaultCharset()
                    + "; class=java.io.InputStream",
                    "Plain Text");
        }

        return plainUnicodeFlavor;
    }

    protected static final Class tryToLoadClass(String className, ClassLoader fallback)
            throws ClassNotFoundException
    {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e0) {
            try {
                return ClassLoader.getSystemClassLoader().loadClass(className);
            } catch (ClassNotFoundException e1) {
                ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();

                if (contextLoader != null) {
                    try {
                        return contextLoader.loadClass(className);
                    } catch (ClassNotFoundException e2) {
                    }
                }

                return fallback.loadClass(className);
            }
        }
    }

    private static boolean isCharsetSupported(String charset) {
        try {
            return Charset.isSupported(charset);
        } catch (IllegalCharsetNameException e) {
            return false;
        }
    }

    public DataFlavor() {
        mimeInfo = null;
        humanPresentableName = null;
        representationClass = null;
    }

    public DataFlavor(Class representationClass, String humanPresentableName) {
        mimeInfo = new MimeTypeProcessor.MimeType("application", "x-java-serialized-object");

        if (humanPresentableName != null) {
            this.humanPresentableName = humanPresentableName;
        } else {
            this.humanPresentableName = "application/x-java-serialized-object";
        }

        mimeInfo.addParameter("class", representationClass.getName());
        this.representationClass = representationClass;
    }

    public DataFlavor(String mimeType, String humanPresentableName) {
        try {
            init(mimeType, humanPresentableName, null);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Can't load class: " + mimeInfo.getParameter("class"));
        }
    }

    public DataFlavor(String mimeType) throws ClassNotFoundException {
        init(mimeType, null, null);
    }

    public DataFlavor(String mimeType, String humanPresentableName, ClassLoader classLoader)
            throws ClassNotFoundException
    {
        init(mimeType, humanPresentableName, classLoader);
    }

    private void init(String mimeType, String humanPresentableName, ClassLoader classLoader)
            throws ClassNotFoundException
    {
        String className;

        try {
            mimeInfo = MimeTypeProcessor.parse(mimeType);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Can't parse MIME type: " + mimeType);
        }

        if (humanPresentableName != null) {
            this.humanPresentableName = humanPresentableName;
        } else {
            this.humanPresentableName = mimeInfo.getPrimaryType() + '/' + mimeInfo.getSubType();
        }

        className = mimeInfo.getParameter("class");
        if (className == null) {
            className = "java.io.InputStream";
            mimeInfo.addParameter("class", className);
        }
        representationClass = (classLoader == null) ?
                Class.forName(className) :
                classLoader.loadClass(className);
    }

    private String getCharset() {
        if (isCharsetRedundant()) {
            return "";
        } else {
            String charset = mimeInfo.getParameter("charset");

            if (isCharsetRequired() && ((charset == null) || (charset.length() == 0))) {
                return DTK.getDTK().getDefaultCharset();
            } else {
                if (charset == null) {
                    return "";
                }

                return charset;
            }
        }
    }

    private boolean isCharsetRequired() {
        String type = mimeInfo.getFullType();

        return (type.equals("text/sgml") ||
                type.equals("text/xml") ||
                type.equals("text/html") ||
                type.equals("text/enriched") ||
                type.equals("text/richtext") ||
                type.equals("text/uri-list") ||
                type.equals("text/directory") ||
                type.equals("text/css") ||
                type.equals("text/calendar") ||
                type.equals("application/x-java-serialized-object") ||
                type.equals("text/plain"));
    }

    private boolean isCharsetRedundant() {
        String type = mimeInfo.getFullType();

        return (type.equals("text/rtf") ||
                type.equals("text/tab-separated-values") ||
                type.equals("text/t140") ||
                type.equals("text/rfc822-headers") ||
                type.equals("text/parityfec"));
    }

    MimeTypeProcessor.MimeType getMimeInfo() {
        return mimeInfo;
    }

    public String getPrimaryType() {
        return (mimeInfo != null) ? mimeInfo.getPrimaryType() : null;
    }

    public String getSubType() {
        return (mimeInfo != null) ? mimeInfo.getSubType() : null;
    }

    public String getMimeType() {
        return (mimeInfo != null) ? MimeTypeProcessor.assemble(mimeInfo) : null;
    }

    public String getParameter(String paramName) {
        String lowerName = paramName.toLowerCase();

        if (lowerName.equals("humanpresentablename")) {
            return humanPresentableName;
        } else {
            return mimeInfo.getParameter(lowerName);
        }
    }

    public String getHumanPresentableName() {
        return humanPresentableName;
    }

    public void setHumanPresentableName(String humanPresentableName) {
        this.humanPresentableName = humanPresentableName;
    }

    public Class getRepresentationClass() {
        return representationClass;
    }

    public final Class getDefaultRepresentationClass() {
        return InputStream.class;
    }

    public final String getDefaultRepresentationClassAsString() {
        return getDefaultRepresentationClass().getName();
    }

    public boolean isRepresentationClassSerializable() {
        return Serializable.class.isAssignableFrom(representationClass);
    }

    public boolean isRepresentationClassRemote() {
        // Code should be enabled when RMI is supported
        // return java.rmi.Remote.class.isAssignableFrom(representationClass);
        return false;
    }

    public boolean isRepresentationClassReader() {
        return Reader.class.isAssignableFrom(representationClass);
    }

    public boolean isRepresentationClassInputStream() {
        return InputStream.class.isAssignableFrom(representationClass);
    }

    public boolean isRepresentationClassCharBuffer() {
        return CharBuffer.class.isAssignableFrom(representationClass);
    }

    public boolean isRepresentationClassByteBuffer() {
        return ByteBuffer.class.isAssignableFrom(representationClass);
    }

    /**
     * @deprecated
     */
    protected String normalizeMimeTypeParameter(String parameterName, String parameterValue) {
        return parameterValue;
    }

    /**
     * @deprecated
     */
    protected String normalizeMimeType(String mimeType) {
        return mimeType;
    }

    public final boolean isMimeTypeEqual(DataFlavor dataFlavor) {
        return mimeInfo.equals(dataFlavor.mimeInfo);
    }

    public boolean isMimeTypeEqual(String mimeType) {
        try {
            return mimeInfo.equals(MimeTypeProcessor.parse(mimeType));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Can't parse MIME type: " + mimeType);
        }
    }

    public synchronized void writeExternal(ObjectOutput os) throws IOException {
        os.writeObject(humanPresentableName);
        os.writeObject(mimeInfo);
    }

    public synchronized void readExternal(ObjectInput is)
            throws IOException, ClassNotFoundException
    {
        humanPresentableName = (String) is.readObject();
        mimeInfo = (MimeTypeProcessor.MimeType) is.readObject();

        representationClass = (mimeInfo != null) ?
                Class.forName(mimeInfo.getParameter("class")) : null;
    }

    public Object clone() throws CloneNotSupportedException {
        DataFlavor clone = new DataFlavor();

        clone.humanPresentableName = humanPresentableName;
        clone.representationClass = representationClass;
        clone.mimeInfo = (MimeTypeProcessor.MimeType) mimeInfo.clone();

        return clone;
    }

    public String toString() {
        /* The format is based on 1.5 release behavior 
         * which can be revealed by the following code:
         * 
         * System.out.println(DataFlavor.imageFlavor.toString());
         */

        return (getClass().getName()
                + "[MimeType=(" + getMimeType()
                + ");humanPresentableName=" + humanPresentableName + "]");
    }

    public boolean isMimeTypeSerializedObject() {
        return isMimeTypeEqual(javaSerializedObjectMimeType);
    }

    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof DataFlavor)) {
            return false;
        } else {
            return equals((DataFlavor) o);
        }
    }

    public boolean equals(DataFlavor that) {
        if (that == this) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (mimeInfo == null) {
            return (that.mimeInfo == null);
        }
        if (!(mimeInfo.equals(that.mimeInfo) &&
                representationClass.equals(that.representationClass)))
        {
            return false;
        }
        if (!mimeInfo.getPrimaryType().equals("text") || isUnicodeFlavor()) {
            return true;
        }

        String charset1 = getCharset();
        String charset2 = that.getCharset();

        if (!isCharsetSupported(charset1) || !isCharsetSupported(charset2)) {
            return charset1.equalsIgnoreCase(charset2);
        } else {
            return (Charset.forName(charset1).equals(Charset.forName(charset2)));
        }

    }

    public boolean equals(String s) {
      if (s == null) {
          return false;
      }

      return isMimeTypeEqual(s);
    }

    public boolean match(DataFlavor that) {
        return equals(that);
    }

    public int hashCode() {
        return getKeyInfo().hashCode();
    }

    private String getKeyInfo() {
        String key = mimeInfo.getFullType() + ";class=" + representationClass.getName();

        if (!mimeInfo.getPrimaryType().equals("text") || isUnicodeFlavor()) {
            return key;
        }

        return key + ";charset=" + getCharset().toLowerCase();
    }

    public boolean isFlavorSerializedObjectType() {
        return (isMimeTypeSerializedObject()
                && isRepresentationClassSerializable());
    }

    public boolean isFlavorRemoteObjectType() {
        return (isMimeTypeEqual(javaRemoteObjectMimeType)
                && isRepresentationClassRemote());
    }

    public boolean isFlavorJavaFileListType() {
        return (java.util.List.class.isAssignableFrom(representationClass) &&
                isMimeTypeEqual(javaFileListFlavor));
    }

    public boolean isFlavorTextType() {
        if (equals(stringFlavor) || equals(plainTextFlavor)) {
            return true;
        }
        if (!mimeInfo.getPrimaryType().equals("text")) {
            return false;
        }

        String charset = getCharset();

        if (isByteCodeFlavor()) {
            if (charset.length() != 0) {
                return isCharsetSupported(charset);
            }

            return true;
        }

        return isUnicodeFlavor();
    }

    public Reader getReaderForText(Transferable transferable)
            throws UnsupportedFlavorException, IOException
    {
        Object data = transferable.getTransferData(this);

        if (data == null) {
            throw new IllegalArgumentException("Transferable has null data");
        }

        if (data instanceof Reader) {
            Reader reader = (Reader) data;
            reader.reset();
            return reader;
        } else if (data instanceof String) {
            return new StringReader((String) data);
        } else if (data instanceof CharBuffer) {
            return new CharArrayReader(((CharBuffer) data).array());
        } else if (data instanceof char[]) {
            return new CharArrayReader((char[]) data);
        } else {
            String charset = getCharset();
            InputStream stream;

            if (data instanceof InputStream) {
                stream = (InputStream) data;
                stream.reset();
            } else if (data instanceof ByteBuffer) {
                stream = new ByteArrayInputStream((((ByteBuffer) data).array()));
            } else if (data instanceof byte[]) {
                stream = new ByteArrayInputStream((byte[]) data);
            } else {
                throw new IllegalArgumentException(
                        "Can't create reader for this representation class");
            }

            if (charset.length() == 0) {
                return new InputStreamReader(stream);
            } else {
                return new InputStreamReader(stream, charset);
            }
        }
    }

    public static final DataFlavor selectBestTextFlavor(DataFlavor[] availableFlavors) {
        if (availableFlavors == null) {
            return null;
        }

        List sorted = sortTextFlavorsByType(new LinkedList(Arrays.asList(availableFlavors)));

        if (sorted.isEmpty()) {
            return null;
        }

        List bestSorted = (List) sorted.get(0);

        if (bestSorted.size() == 1) {
            return (DataFlavor) bestSorted.get(0);
        }

        if (((DataFlavor) bestSorted.get(0)).getCharset().length() == 0) {
            return selectBestFlavorWOCharset(bestSorted);
        } else {
            return selectBestFlavorWCharset(bestSorted);
        }
    }

    private static DataFlavor selectBestFlavorWCharset(List list) {
        List best;

        best = getFlavors(list, Reader.class);
        if (best != null) {
            return (DataFlavor) best.get(0);
        }
        best = getFlavors(list, String.class);
        if (best != null) {
            return (DataFlavor) best.get(0);
        }
        best = getFlavors(list, CharBuffer.class);
        if (best != null) {
            return (DataFlavor) best.get(0);
        }
        best = getFlavors(list, char[].class);
        if (best != null) {
            return (DataFlavor) best.get(0);
        }

        return selectBestByCharset(list);
    }

    private static DataFlavor selectBestByCharset(List list) {
        List best;

        best = getFlavors(list, new String[] {"UTF-16", "UTF-8", "UTF-16BE", "UTF-16LE"});
        if (best == null) {
            best = getFlavors(list, new String[] {DTK.getDTK().getDefaultCharset()});
            if (best == null) {
                best = getFlavors(list, new String[] {"US-ASCII"});
                if (best == null) {
                    best = selectBestByAlphabet(list);
                }
            }
        }

        if (best != null) {
            if (best.size() == 1) {
                return (DataFlavor) best.get(0);
            } else {
                return selectBestFlavorWOCharset(best);
            }
        }

        return null;
    }

    private static List selectBestByAlphabet(List list) {
        String charsets[] = new String[list.size()];
        LinkedList best = new LinkedList();

        for (int i = 0; i < charsets.length; i++) {
            charsets[i] = ((DataFlavor) list.get(i)).getCharset();
        }
        Arrays.sort(charsets, String.CASE_INSENSITIVE_ORDER);

        for (Iterator i = list.iterator(); i.hasNext();) {
            DataFlavor flavor = (DataFlavor) i.next();

            if (charsets[0].equalsIgnoreCase(flavor.getCharset())) {
                best.add(flavor);
            }
        }

        return best.isEmpty() ? null : best;
    }

    private static List getFlavors(List list, String[] charset) {
        LinkedList sublist = new LinkedList();

        for (Iterator i = list.iterator(); i.hasNext();) {
            DataFlavor flavor = (DataFlavor) i.next();

            if (isCharsetSupported(flavor.getCharset())) {
                for (int j = 0; j < charset.length; j++) {
                    if (Charset.forName(charset[j]).equals(Charset.forName(flavor.getCharset()))) {
                        sublist.add(flavor);
                    }
                }
            } else {
                i.remove();
            }
        }

        return sublist.isEmpty() ? null : list;
    }

    private static DataFlavor selectBestFlavorWOCharset(List list) {
        List best;

        best = getFlavors(list, InputStream.class);
        if (best != null) {
            return (DataFlavor) best.get(0);
        }
        best = getFlavors(list, ByteBuffer.class);
        if (best != null) {
            return (DataFlavor) best.get(0);
        }
        best = getFlavors(list, byte[].class);
        if (best != null) {
            return (DataFlavor) best.get(0);
        }

        return (DataFlavor) list.get(0);
    }

    private static List getFlavors(List list, Class klass) {
        LinkedList sublist = new LinkedList();

        for (Iterator i = list.iterator(); i.hasNext();) {
            DataFlavor flavor = (DataFlavor) i.next();

            if (flavor.representationClass.equals(klass)) {
                sublist.add(flavor);
            }
        }

        return sublist.isEmpty() ? null : list;
    }

    private static List sortTextFlavorsByType(List availableFlavors) {
        LinkedList list = new LinkedList();

        for (int i = 0; i < sortedTextFlavors.length; i++) {
            List subList = fetchTextFlavors(availableFlavors, sortedTextFlavors[i]);

            if (subList != null) {
                list.addLast(subList);
            }
        }
        if (!availableFlavors.isEmpty()) {
            list.addLast(availableFlavors);
        }

        return list;
    }

    private static List fetchTextFlavors(List availableFlavors, String mimeType) {
        LinkedList list = new LinkedList();

        for (Iterator i = availableFlavors.iterator(); i.hasNext();) {
            DataFlavor flavor = (DataFlavor) i.next();

            if (flavor.isFlavorTextType()) {
                if (flavor.mimeInfo.getFullType().equals(mimeType)) {
                    if (!list.contains(flavor)) {
                        list.add(flavor);
                    }
                    i.remove();
                }
            } else {
                i.remove();
            }
        }

        return list.isEmpty() ? null : list;
    }

    private boolean isUnicodeFlavor() {
        return (representationClass.equals(Reader.class) ||
                representationClass.equals(String.class) ||
                representationClass.equals(CharBuffer.class) ||
                representationClass.equals(char[].class));
    }

    private boolean isByteCodeFlavor() {
        return (representationClass.equals(InputStream.class)
                || representationClass.equals(ByteBuffer.class)
                || representationClass.equals(byte[].class));
    }

}
