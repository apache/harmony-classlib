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
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.GZIPInputStream;

import org.apache.harmony.pack200.bytecode.Attribute;
import org.apache.harmony.pack200.bytecode.CPClass;
import org.apache.harmony.pack200.bytecode.CPField;
import org.apache.harmony.pack200.bytecode.CPMethod;
import org.apache.harmony.pack200.bytecode.ClassConstantPool;
import org.apache.harmony.pack200.bytecode.ClassFile;
import org.apache.harmony.pack200.bytecode.ClassFileEntry;
import org.apache.harmony.pack200.bytecode.SourceFileAttribute;

/**
 * A Pack200 archive consists of one (or more) segments. Each segment is
 * standalone, in the sense that every segment has the magic number header;
 * thus, every segment is also a valid archive. However, it is possible to
 * combine (non-GZipped) archives into a single large archive by concatenation
 * alone. Thus all the hard work in unpacking an archive falls to understanding
 * a segment.
 * 
 * This class implements the Pack200 specification by an entry point ({@link #parse(InputStream)})
 * which in turn delegates to a variety of other parse methods. Each parse
 * method corresponds (roughly) to the name of the bands in the Pack200
 * specification.
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


	/**
	 * Decode a segment from the given input stream. This does not attempt to
	 * re-assemble or export any class files, but it contains enough information
	 * to be able to re-assemble class files by external callers.
	 * 
	 * @param in
	 *            the input stream to read from TODO At this point, this must be
	 *            a non-GZipped input stream, but this decoding could be done in
	 *            this method in the future (but perhaps more likely on an
	 *            archive as a whole)
	 * @return a segment parsed from the input stream
	 * @throws IOException
	 *             if a problem occurs during reading from the underlying stream
	 * @throws Pack200Exception
	 *             if a problem occurs with an unexpected value or unsupported
	 *             codec
	 */
	public static Segment parse(InputStream in) throws IOException,
			Pack200Exception {
		Segment segment = new Segment();
		// See if file is GZip compressed
		if (!in.markSupported()) {
			in = new BufferedInputStream(in);
			if (!in.markSupported())
				throw new IllegalStateException();
		}
		in.mark(2);
		if (((in.read() & 0xFF) | (in.read() & 0xFF) << 8) == GZIPInputStream.GZIP_MAGIC) {
			in.reset();
			in = new BufferedInputStream(new GZIPInputStream(in));
		} else {
			in.reset();
		}
		segment.parseSegment(in);
		return segment;
	}


    private SegmentHeader header;

    private CpBands cpBands;
    
    private AttrDefinitionBands attrDefinitionBands;

    private IcBands icBands;

    private ClassBands classBands;

    private BcBands bcBands;

    private FileBands fileBands;

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
		AttributeLayout SOURCE_FILE = attrDefinitionBands.getAttributeDefinitionMap()
				.getAttributeLayout(AttributeLayout.ATTRIBUTE_SOURCE_FILE,
						AttributeLayout.CONTEXT_CLASS);
		if (SOURCE_FILE.matches(classBands.getClassFlags()[classNum])) {
			String fileName = fullName.substring(i) + ".java";
			classFile.attributes = new Attribute[] { (Attribute) cp
					.add(new SourceFileAttribute(fileName)) };
		} else {
			classFile.attributes = new Attribute[] {};
		}
		// this/superclass
		ClassFileEntry cfThis = cp.add(new CPClass(fullName));
		ClassFileEntry cfSuper = cp.add(new CPClass(classBands.getClassSuper()[classNum]));
		// add interfaces
		ClassFileEntry cfInterfaces[] = new ClassFileEntry[classBands.getClassInterfaces()[classNum].length];
		for (i = 0; i < cfInterfaces.length; i++) {
			cfInterfaces[i] = cp.add(new CPClass(classBands.getClassInterfaces()[classNum][i]));
		}
		// add fields
		ClassFileEntry cfFields[] = new ClassFileEntry[classBands.getClassFieldCount()[classNum]];
		// fieldDescr and fieldFlags used to create this
		for (i = 0; i < cfFields.length; i++) {
			cfFields[i] = cp.add(new CPField(classBands.getFieldDescr()[classNum][i],
                    classBands.getFieldFlags()[classNum][i], classBands.getFieldAttributes()[classNum][i]));
		}
		// add methods
		ClassFileEntry cfMethods[] = new ClassFileEntry[classBands.getClassMethodCount()[classNum]];
		// fieldDescr and fieldFlags used to create this
		for (i = 0; i < cfMethods.length; i++) {
			cfMethods[i] = cp.add(new CPMethod(classBands.getMethodDescr()[classNum][i],
                    classBands.getMethodFlags()[classNum][i], classBands.getMethodAttributes()[classNum][i]));
		}
		// sort CP according to cp_All
		cp.resolve(this);
		// print out entries
		debug("Constant pool looks like:");
		for (i = 1; i <= cp.size(); i++) {
			debug(String.valueOf(i) + ":" + String.valueOf(cp.get(i)));
		}
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
		debug("-------");
        header = new SegmentHeader();
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
        // TODO Can handle multiple concatenated streams, so should deal with
        // that possibility
        parse(in).unpack(in, out);
    }
    
    /**
     * This is a local debugging message to aid the developer in writing this
     * class. It will be removed before going into production. If the property
     * 'debug.pack200' is set, this will generate messages to stderr; otherwise,
     * it will be silent.
     * 
     * @param message
     * @deprecated this should be removed from production code
     */
    protected void debug(String message) {
        if (System.getProperty("debug.pack200") != null) {
            System.err.println(message);
        }
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
	 * @param in
	 *            the same InputStream that was used to parse the segment
	 * @throws IOException
	 *             if an error occurs whilst reading or writing to the streams
	 * @throws Pack200Exception
	 *             if an error occurs whilst unpacking data
	 */
	public void writeJar(JarOutputStream out, InputStream in)
			throws IOException, Pack200Exception {
		fileBands.processFileBits(in);
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
		out.finish();
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

}