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
package org.apache.harmony.pack200;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.GZIPInputStream;

import org.apache.harmony.pack200.bytecode.Attribute;
import org.apache.harmony.pack200.bytecode.CPClass;
import org.apache.harmony.pack200.bytecode.CPField;
import org.apache.harmony.pack200.bytecode.CPMethod;
import org.apache.harmony.pack200.bytecode.CPUTF8;
import org.apache.harmony.pack200.bytecode.ClassConstantPool;
import org.apache.harmony.pack200.bytecode.ClassFile;
import org.apache.harmony.pack200.bytecode.ClassFileEntry;
import org.apache.harmony.pack200.bytecode.InnerClassesAttribute;
import org.apache.harmony.pack200.bytecode.SourceFileAttribute;

/**
 * A Pack200 archive consists of one or more segments. Each segment is
 * stand-alone, in the sense that every segment has the magic number header;
 * thus, every segment is also a valid archive. However, it is possible to
 * combine (non-GZipped) archives into a single large archive by concatenation
 * alone. Thus all the hard work in unpacking an archive falls to understanding
 * a segment.
 *
 * The first component of a segment is the header; this contains (amongst other
 * things) the expected counts of constant pool entries, which in turn defines
 * how many values need to be read from the stream. Because values are variable
 * width (see {@link Codec}), it is not possible to calculate the start of the
 * next segment, although one of the header values does hint at the size of the
 * segment if non-zero, which can be used for buffering purposes.
 *
 * Note that this does not perform any buffering of the input stream; each value
 * will be read on a byte-by-byte basis. It does not perform GZip decompression
 * automatically; both of these are expected to be done by the caller if the
 * stream has the magic header for GZip streams ({@link GZIPInputStream#GZIP_MAGIC}).
 * In any case, if GZip decompression is being performed the input stream will
 * be buffered at a higher level, and thus this can read on a byte-oriented
 * basis.
 */
public class Segment {

    public static final int LOG_LEVEL_VERBOSE = 2;

    public static final int LOG_LEVEL_STANDARD = 1;

    public static final int LOG_LEVEL_QUIET = 0;

    private SegmentHeader header;

    private CpBands cpBands;

    private AttrDefinitionBands attrDefinitionBands;

    private IcBands icBands;

    private ClassBands classBands;

    private BcBands bcBands;

    private FileBands fileBands;

    private boolean overrideDeflateHint;

    private boolean deflateHint;

	private int logLevel;

	private PrintWriter logStream;

	private ClassFile buildClassFile(int classNum) throws Pack200Exception {
		ClassFile classFile = new ClassFile();
		classFile.major = header.getDefaultClassMajorVersion(); // TODO If
		// classVersionMajor[] use
		// that instead
		classFile.minor = header.getDefaultClassMinorVersion(); // TODO if
		// classVersionMinor[] use
		// that instead
		// build constant pool
		ClassConstantPool cp = classFile.pool;
		String fullName = classBands.getClassThis()[classNum];
		// SourceFile attribute
		int i = fullName.lastIndexOf("/") + 1; // if lastIndexOf==-1, then
		// -1+1=0, so str.substring(0)
		// == str

		// Get the source file attribute
        ArrayList classAttributes = classBands.getClassAttributes()[classNum];
        SourceFileAttribute sourceFileAttribute = null;
        for(int index=0; index < classAttributes.size(); index++) {
            if(((Attribute)classAttributes.get(index)).isSourceFileAttribute()) {
                sourceFileAttribute = ((SourceFileAttribute)classAttributes.get(index));
            }
        }

        if(sourceFileAttribute == null) {
            // If we don't have a source file attribute yet, we need
            // to infer it from the class.
            AttributeLayout SOURCE_FILE = attrDefinitionBands.getAttributeDefinitionMap()
    				.getAttributeLayout(AttributeLayout.ATTRIBUTE_SOURCE_FILE,
    						AttributeLayout.CONTEXT_CLASS);
    		if (SOURCE_FILE.matches(classBands.getRawClassFlags()[classNum])) {
    		    int firstDollar = SegmentUtils.indexOfFirstDollar(fullName);
    		    String fileName = null;

    		    if(firstDollar > -1 && (i <= firstDollar)) {
    		        fileName = fullName.substring(i, firstDollar) + ".java";
    		    } else {
    		        fileName = fullName.substring(i) + ".java";
    		    }
    		    sourceFileAttribute = new SourceFileAttribute(cpBands.cpUTF8Value(fileName, ClassConstantPool.DOMAIN_ATTRIBUTEASCIIZ));
    			classFile.attributes = new Attribute[] { (Attribute) cp
    					.add(sourceFileAttribute) };
    		} else {
                classFile.attributes = new Attribute[] {};
            }
        } else {
            classFile.attributes = new Attribute[] { (Attribute) cp.add(sourceFileAttribute)};
        }

		// If we see any class attributes, add them to the class's attributes that will
		// be written out. Keep SourceFileAttributes out since we just
		// did them above.
	    ArrayList classAttributesWithoutSourceFileAttribute = new ArrayList();
	    for(int index=0; index < classAttributes.size(); index++) {
	        Attribute attrib = (Attribute)classAttributes.get(index);
	        if(!attrib.isSourceFileAttribute()) {
	            classAttributesWithoutSourceFileAttribute.add(attrib);
	        }
	    }
        Attribute[] originalAttributes = classFile.attributes;
        classFile.attributes = new Attribute[originalAttributes.length + classAttributesWithoutSourceFileAttribute.size()];
        System.arraycopy(originalAttributes, 0, classFile.attributes, 0, originalAttributes.length);
	    for(int index=0; index < classAttributesWithoutSourceFileAttribute.size(); index++) {
	        Attribute attrib = ((Attribute)classAttributesWithoutSourceFileAttribute.get(index));
	        cp.add(attrib);
	        classFile.attributes[originalAttributes.length + index] = attrib;
	    }

		// this/superclass
		ClassFileEntry cfThis = cp.add(cpBands.cpClassValue(fullName));
		ClassFileEntry cfSuper = cp.add(cpBands.cpClassValue(classBands.getClassSuper()[classNum]));
		// add interfaces
		ClassFileEntry cfInterfaces[] = new ClassFileEntry[classBands.getClassInterfaces()[classNum].length];
		for (i = 0; i < cfInterfaces.length; i++) {
			cfInterfaces[i] = cp.add(cpBands.cpClassValue(classBands.getClassInterfaces()[classNum][i]));
		}
		// add fields
		ClassFileEntry cfFields[] = new ClassFileEntry[classBands.getClassFieldCount()[classNum]];
		// fieldDescr and fieldFlags used to create this
		for (i = 0; i < cfFields.length; i++) {
            String descriptorStr = classBands.getFieldDescr()[classNum][i];
            int colon = descriptorStr.indexOf(':');
            CPUTF8 name = cpBands.cpUTF8Value(descriptorStr.substring(0,colon), ClassConstantPool.DOMAIN_NORMALASCIIZ);
            CPUTF8 descriptor = cpBands.cpUTF8Value(descriptorStr.substring(colon+1), ClassConstantPool.DOMAIN_SIGNATUREASCIIZ);
            cfFields[i] = cp.add(new CPField(name, descriptor,
                    classBands.getFieldFlags()[classNum][i], classBands.getFieldAttributes()[classNum][i]));
		}
		// add methods
		ClassFileEntry cfMethods[] = new ClassFileEntry[classBands.getClassMethodCount()[classNum]];
		// methodDescr and methodFlags used to create this
		for (i = 0; i < cfMethods.length; i++) {
            String descriptorStr = classBands.getMethodDescr()[classNum][i];
            int colon = descriptorStr.indexOf(':');
            CPUTF8 name = cpBands.cpUTF8Value(descriptorStr.substring(0,colon), ClassConstantPool.DOMAIN_NORMALASCIIZ);
            CPUTF8 descriptor = cpBands.cpUTF8Value(descriptorStr.substring(colon+1), ClassConstantPool.DOMAIN_SIGNATUREASCIIZ);
            cfMethods[i] = cp.add(new CPMethod(name, descriptor, classBands
                    .getMethodFlags()[classNum][i], classBands
                    .getMethodAttributes()[classNum][i]));
		}

		// add inner class attribute (if required)
		boolean addInnerClassesAttr = false;
        IcTuple[] ic_local = getClassBands().getIcLocal()[classNum];
		boolean ic_local_sent = false;
		if(ic_local != null) {
		    ic_local_sent = true;
		}
		InnerClassesAttribute innerClassesAttribute = new InnerClassesAttribute("InnerClasses");
		IcTuple[] ic_relevant = getIcBands().getRelevantIcTuples(fullName, cp);
		IcTuple[] ic_stored = computeIcStored(ic_local, ic_relevant);
		for(int index = 0; index < ic_stored.length; index++) {
		    String innerClassString = ic_stored[index].thisClassString();
		    String outerClassString = ic_stored[index].outerClassString();
		    String simpleClassName = ic_stored[index].simpleClassName();

		    CPClass innerClass = null;
		    CPUTF8 innerName = null;
		    CPClass outerClass = null;

		    if(ic_stored[index].isAnonymous()) {
		        innerClass = cpBands.cpClassValue(innerClassString);
		    } else {
	            innerClass = cpBands.cpClassValue(innerClassString);
	            innerName = cpBands.cpUTF8Value(simpleClassName, ClassConstantPool.DOMAIN_ATTRIBUTEASCIIZ);
		    }

		    if(ic_stored[index].isMember()) {
		        outerClass = cpBands.cpClassValue(outerClassString);
		    }

	        int flags = ic_stored[index].F;
	        innerClassesAttribute.addInnerClassesEntry(innerClass, outerClass, innerName, flags);
	        addInnerClassesAttr = true;
		}
		// If ic_local is sent and it's empty, don't add
		// the inner classes attribute.
		if(ic_local_sent && (ic_local.length == 0)) {
		    addInnerClassesAttr = false;
		}

		// If ic_local is not sent and ic_relevant is empty,
		// don't add the inner class attribute.
		if(!ic_local_sent && (ic_relevant.length == 0)) {
		    addInnerClassesAttr = false;
		}

		if(addInnerClassesAttr) {
		    // Need to add the InnerClasses attribute to the
		    // existing classFile attributes.
		    Attribute[] originalAttrs = classFile.attributes;
		    Attribute[] newAttrs = new Attribute[originalAttrs.length + 1];
		    for(int index=0; index < originalAttrs.length; index++) {
		        newAttrs[index] = originalAttrs[index];
		    }
		    newAttrs[newAttrs.length - 1] = innerClassesAttribute;
		    classFile.attributes = newAttrs;
		    cp.add(innerClassesAttribute);
		}
		// sort CP according to cp_All
		cp.resolve(this);
		// NOTE the indexOf is only valid after the cp.resolve()
		// build up remainder of file
		classFile.accessFlags = (int) classBands.getClassFlags()[classNum];
		classFile.thisClass = cp.indexOf(cfThis);
		classFile.superClass = cp.indexOf(cfSuper);
		// TODO placate format of file for writing purposes
		classFile.interfaces = new int[cfInterfaces.length];
		for (i = 0; i < cfInterfaces.length; i++) {
			classFile.interfaces[i] = cp.indexOf(cfInterfaces[i]);
		}
		classFile.fields = cfFields;
		classFile.methods = cfMethods;
		return classFile;
	}


	/**
	 * Given an ic_local and an ic_relevant, use them to
	 * calculate what should be added as ic_stored.
	 * @param ic_local IcTuple[] array of local transmitted tuples
	 * @param ic_relevant IcTuple[] array of relevant tuples
	 * @return IcTuple[] array of tuples to be stored. If ic_local
	 *     is null or empty, the values returned may not be correct.
	 *     The caller will have to determine if this is the case.
	 */
	private IcTuple[] computeIcStored(IcTuple[] ic_local, IcTuple[] ic_relevant) {
	    List result = new ArrayList();
	    List resultCopy = new ArrayList();
	    List localList = new ArrayList();
	    List relevantList = new ArrayList();
	    if(ic_local != null) {
	        // If ic_local is null, this code doesn't get
	        // executed - which means the list ends up being
	        // ic_relevant.
	        for(int index=0; index < ic_local.length; index++) {
	            result.add(ic_local[index]);
	            resultCopy.add(ic_local[index]);
	            localList.add(ic_local[index]);
	        }
	    }
	    for(int index=0; index < ic_relevant.length; index++) {
	        result.add(ic_relevant[index]);
	        resultCopy.add(ic_relevant[index]);
	        relevantList.add(ic_relevant[index]);
	    }

	    // Since we're removing while iterating, iterate over
	    // a copy.
	    Iterator it = resultCopy.iterator();

	    while(it.hasNext()) {
	        IcTuple tuple = (IcTuple)it.next();
	        if(localList.contains(tuple) && relevantList.contains(tuple)) {
	            while(result.remove(tuple)) {};
	        }
	    }
	    IcTuple[] resultArray = new IcTuple[result.size()];
	    for(int index=0; index < resultArray.length; index++) {
	        resultArray[index] = (IcTuple)result.get(index);
	    }
	    return resultArray;
    }


    /**
	 * This performs the actual work of parsing against a non-static instance of
	 * Segment.
	 *
	 * @param in
	 *            the input stream to read from
	 * @throws IOException
	 *             if a problem occurs during reading from the underlying stream
	 * @throws Pack200Exception
	 *             if a problem occurs with an unexpected value or unsupported
	 *             codec
	 */
	private void parseSegment(InputStream in) throws IOException,
			Pack200Exception {
		log(LOG_LEVEL_VERBOSE, "-------");
        header = new SegmentHeader(this);
        header.unpack(in);
        cpBands = new CpBands(this);
        cpBands.unpack(in);
        attrDefinitionBands = new AttrDefinitionBands(this);
        attrDefinitionBands.unpack(in);
        icBands = new IcBands(this);
        icBands.unpack(in);
        classBands = new ClassBands(this);
        classBands.unpack(in);
        bcBands = new BcBands(this);
        bcBands.unpack(in);
        fileBands = new FileBands(this);
        fileBands.unpack(in);
	}

    /**
     * Unpacks a packed stream (either .pack. or .pack.gz) into a corresponding
     * JarOuputStream.
     *
     * @throws Pack200Exception
     *             if there is a problem unpacking
     * @throws IOException
     *             if there is a problem with I/O during unpacking
     */
    public void unpack(InputStream in, JarOutputStream out) throws IOException,
            Pack200Exception {
        if (!in.markSupported())
            in = new BufferedInputStream(in);
        parseSegment(in);
        writeJar(out);
    }

	/**
	 * Writes the segment to an output stream. The output stream should be
	 * pre-buffered for efficiency. Also takes the same input stream for
	 * reading, since the file bits may not be loaded and thus just copied from
	 * one stream to another. Doesn't close the output stream when finished, in
	 * case there are more entries (e.g. further segments) to be written.
	 *
	 * @param out
	 *            the JarOutputStream to write data to
	 * @throws IOException
	 *             if an error occurs while reading or writing to the streams
	 * @throws Pack200Exception
	 *             if an error occurs while processing data
	 */
	public void writeJar(JarOutputStream out)
			throws IOException, Pack200Exception {
		fileBands.processFileBits();
		DataOutputStream dos = new DataOutputStream(out);
        String[] fileName = fileBands.getFileName();
        long[] fileModtime = fileBands.getFileModtime();
        long[] fileOptions = fileBands.getFileOptions();
        long[] fileSize = fileBands.getFileSize();
        byte[][] fileBits = fileBands.getFileBits();

		// out.setLevel(JarEntry.DEFLATED)
		// now write the files out
		int classNum = 0;
        int numberOfFiles = header.getNumberOfFiles();
        long archiveModtime = header.getArchiveModtime();
        SegmentOptions options = header.getOptions();
		for (int i = 0; i < numberOfFiles; i++) {
			String name = fileName[i];
			long modtime = archiveModtime + fileModtime[i];
			boolean deflate = (fileOptions[i] & 1) == 1
					|| options.shouldDeflate();
            if (overrideDeflateHint) { // Overridden by a command line argument
                deflate = deflateHint;
            }
			boolean isClass = (fileOptions[i] & 2) == 2 || name == null
					|| name.equals("");
			if (isClass) {
				// pull from headers
				if (name == null || name.equals(""))
					name = classBands.getClassThis()[classNum] + ".class";
			}
			JarEntry entry = new JarEntry(name);
			if (deflate)
				entry.setMethod(JarEntry.DEFLATED);
			entry.setTime(modtime);
			out.putNextEntry(entry);

			if (isClass) {
				// write to dos
				ClassFile classFile = buildClassFile(classNum);
				classFile.write(dos);
				dos.flush();
				classNum++;
			} else {
				long size = fileSize[i];
				entry.setSize(size);
				// TODO pull from in
				byte[] data = fileBits[i];
				out.write(data);
			}
		}
		dos.flush();
		out.flush();
	}

    public SegmentConstantPool getConstantPool() {
        return cpBands.getConstantPool();
    }


    public SegmentHeader getSegmentHeader() {
        return header;
    }


    protected AttrDefinitionBands getAttrDefinitionBands() {
        return attrDefinitionBands;
    }


    protected BcBands getBcBands() {
        return bcBands;
    }


    protected ClassBands getClassBands() {
        return classBands;
    }


    protected CpBands getCpBands() {
        return cpBands;
    }

    protected FileBands getFileBands() {
        return fileBands;
    }

    protected IcBands getIcBands() {
        return icBands;
    }


    public void setLogLevel(int logLevel) {
    	this.logLevel = logLevel;
    }

    public void setLogStream(OutputStream logStream) {
    	this.logStream = new PrintWriter(logStream);
    }

    public void log(int logLevel, String message) {
    	if (this.logLevel >= logLevel) {
    		logStream.println(message);
    	}
    }

    /**
     * Override the archive's deflate hint with the given boolean
     * @param deflateHint - the deflate hint to use
     */
    public void overrideDeflateHint(boolean deflateHint) {
        this.overrideDeflateHint = true;
        this.deflateHint = deflateHint;
    }

}