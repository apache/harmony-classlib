/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, 
 *  as applicable.
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
package org.apache.harmony.archive.internal.pack200;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

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
 * 
 * @author Alex Blewitt
 * @version $Revision: $
 */
public class Segment {
	/**
	 * The magic header for a Pack200 Segment is 0xCAFED00D. I wonder where they
	 * get their inspiration from ...
	 */
	private static final int[] magic = { 0xCA, 0xFE, 0xD0, 0x0D };

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
		segment.parseSegment(in);
		return segment;
	}

	/**
	 * Completely reads in a byte array, akin to the implementation in
	 * {@link java.lang.DataInputStream}. TODO Refactor out into a separate
	 * InputStream handling class
	 * 
	 * @param in
	 *            the input stream to read from
	 * @param data
	 *            the byte array to read into
	 * @throws IOException
	 *             if a problem occurs during reading from the underlying stream
	 * @throws Pack200Exception
	 *             if a problem occurs with an unexpected value or unsupported
	 *             codec
	 */
	private static void readFully(InputStream in, byte[] data)
			throws IOException, Pack200Exception {
		int total = in.read(data);
		if (total == -1)
			throw new EOFException("Failed to read any data from input stream");
		while (total < data.length) {
			int delta = in.read(data, total, data.length - total);
			if (delta == -1)
				throw new EOFException(
						"Failed to read some data from input stream");
			total += delta;
		}
	}

	private long archiveModtime;

	private long archiveSize;

	private int attributeDefinitionCount;

	private byte[] attributeDefinitionHeader;

	private String[] attributeDefinitionLayout;

	private String[] attributeDefinitionName;

	private byte[] bandHeadersData;

	private int bandHeadersSize;

	private int classCount;

	private String[] cpClass;

	private int cpClassCount;

	private String[] cpDescriptor;

	private int cpDescriptorCount;

	private double[] cpDouble;

	private int cpDoubleCount;

	private String[] cpFieldClass;

	private int cpFieldCount;

	private Object cpFieldDescriptor;

	private float[] cpFloat;

	private int cpFloatCount;

	private String[] cpIMethodClass;

	private int cpIMethodCount;

	private String[] cpIMethodDescriptor;

	private int[] cpInt;

	private int cpIntCount;

	private long[] cpLong;

	private int cpLongCount;

	private String[] cpMethodClass;

	private int cpMethodCount;

	private String[] cpMethodDescriptor;

	private String[] cpSignature;

	private int cpSignatureCount;

	private String[] cpString;

	private int cpStringCount;

	private String[] cpUTF8;

	private int cpUTF8Count;

	private int defaultClassMajorVersion;

	private int defaultClassMinorVersion;

	private int innerClassCount;

	private int major;

	private int minor;

	private int numberOfFiles;

	private SegmentOptions options;

	private int segmentsRemaining;

	public long getArchiveModtime() {
		return archiveModtime;
	}

	public long getArchiveSize() {
		return archiveSize;
	}

	public int getNumberOfFiles() {
		return numberOfFiles;
	}

	private SegmentOptions getOptions() {
		return options;
	}

	public int getSegmentsRemaining() {
		return segmentsRemaining;
	}

	private void parseArchiveFileCounts(InputStream in) throws IOException,
			Pack200Exception {
		if (getOptions().hasArchiveFileCounts()) {
			setArchiveSize(Codec.UNSIGNED5.decode(in) << 32
					| Codec.UNSIGNED5.decode(in));
			setSegmentsRemaining(Codec.UNSIGNED5.decode(in));
			setArchiveModtime(Codec.UNSIGNED5.decode(in));
			setNumberOfFiles(Codec.UNSIGNED5.decode(in));
		}
	}

	private void parseArchiveSpecialCounts(InputStream in) throws IOException,
			Pack200Exception {
		if (getOptions().hasSpecialFormats()) {
			setBandHeadersSize(Codec.UNSIGNED5.decode(in));
			setAttributeDefinitionCount(Codec.UNSIGNED5.decode(in));
		}
	}

	/**
	 * Reads {@link #attributeDefinitionCount} attribute definitions from the
	 * stream, into {@link #attributeDefinitionHeader},
	 * {@link #attributeDefinitionName} and {@link #attributeDefinitionLayout}.
	 * This affects the codecs that are used to parse non-standard bands. TODO
	 * Currently, these values if present cause a failure in the parsing.
	 * 
	 * @param in
	 *            the input stream to read from
	 * @throws IOException
	 *             if a problem occurs during reading from the underlying stream
	 * @throws Pack200Exception
	 *             if a problem occurs with an unexpected value or unsupported
	 *             codec
	 */
	private void parseAttributeDefinition(InputStream in) throws IOException,
			Pack200Exception {
		attributeDefinitionHeader = new byte[attributeDefinitionCount];
		for (int i = 0; i < attributeDefinitionCount; i++) {
			attributeDefinitionHeader[i] = (byte) Codec.BYTE1.decode(in);
		}
		attributeDefinitionName = parseReferences(in, Codec.UNSIGNED5,
				attributeDefinitionCount, cpUTF8);
		attributeDefinitionLayout = parseReferences(in, Codec.UNSIGNED5,
				attributeDefinitionCount, cpUTF8);
		if (attributeDefinitionCount > 0)
			throw new Error("No idea what the adc is for yet");
	}

	private void parseClassCounts(InputStream in) throws IOException,
			Pack200Exception {
		setInnerClassCount(Codec.UNSIGNED5.decode(in));
		setDefaultClassMinorVersion(Codec.UNSIGNED5.decode(in));
		setDefaultClassMajorVersion(Codec.UNSIGNED5.decode(in));
		setClassCount(Codec.UNSIGNED5.decode(in));
	}

	/**
	 * Parses the constant pool class names, using {@link #cpClassCount} to
	 * populate {@link #cpClass} from {@link #cpUTF8}.
	 * 
	 * @param in
	 *            the input stream to read from
	 * @throws IOException
	 *             if a problem occurs during reading from the underlying stream
	 * @throws Pack200Exception
	 *             if a problem occurs with an unexpected value or unsupported
	 *             codec
	 */
	private void parseCpClass(InputStream in) throws IOException,
			Pack200Exception {
		cpClass = parseReferences(in, Codec.UDELTA5, cpClassCount, cpUTF8);
	}

	private void parseCpCounts(InputStream in) throws IOException,
			Pack200Exception {
		setCPUtf8Count(Codec.UNSIGNED5.decode(in));
		if (getOptions().hasCPNumberCounts()) {
			setCPIntCount(Codec.UNSIGNED5.decode(in));
			setCPFloatCount(Codec.UNSIGNED5.decode(in));
			setCPLongCount(Codec.UNSIGNED5.decode(in));
			setCPDoubleCount(Codec.UNSIGNED5.decode(in));
		}
		setCPStringCount(Codec.UNSIGNED5.decode(in));
		setCPClassCount(Codec.UNSIGNED5.decode(in));
		setCPSignatureCount(Codec.UNSIGNED5.decode(in));
		setCPDescriptorCount(Codec.UNSIGNED5.decode(in));
		setCPFieldCount(Codec.UNSIGNED5.decode(in));
		setCPMethodCount(Codec.UNSIGNED5.decode(in));
		setCPIMethodCount(Codec.UNSIGNED5.decode(in));
	}

	/**
	 * Parses the constant pool descriptor definitions, using
	 * {@link #cpDescriptorCount} to populate {@link #cpDescriptor}. For ease
	 * of use, the cpDescriptor is stored as a string of the form <i>name:type</i>,
	 * largely to make it easier for representing field and method descriptors
	 * (e.g. <code>out:java.lang.PrintStream</code>) in a way that is
	 * compatible with passing String arrays.
	 * 
	 * @param in
	 *            the input stream to read from
	 * @throws IOException
	 *             if a problem occurs during reading from the underlying stream
	 * @throws Pack200Exception
	 *             if a problem occurs with an unexpected value or unsupported
	 *             codec
	 */
	private void parseCpDescriptor(InputStream in) throws IOException,
			Pack200Exception {
		String[] cpDescriptorNames = parseReferences(in, Codec.DELTA5,
				cpDescriptorCount, cpUTF8);
		String[] cpDescriptorTypes = parseReferences(in, Codec.UDELTA5,
				cpDescriptorCount, cpSignature);
		cpDescriptor = new String[cpDescriptorCount];
		for (int i = 0; i < cpDescriptorCount; i++) {
			cpDescriptor[i] = cpDescriptorNames[i] + ":" + cpDescriptorTypes[i];
		}
	}

	private void parseCpDouble(InputStream in) throws IOException,
			Pack200Exception {
		cpDouble = new double[cpDoubleCount];
		long[] lastBits = new long[cpDoubleCount];
		long last = 0;
		for (int i = 0; i < cpDoubleCount; i++) {
			last = Codec.UDELTA5.decode(in, last);
			lastBits[i] = last << 32;
		}
		for (int i = 0; i < cpDoubleCount; i++) {
			last = Codec.DELTA5.decode(in, last);
			lastBits[i] = lastBits[i] | last;
			cpDouble[i] = Double.longBitsToDouble(lastBits[i]);
		}
	}

	/**
	 * Parses the constant pool field definitions, using {@link #cpFieldCount}
	 * to populate {@link #cpFieldClass} and {@link #cpFieldDescriptor}.
	 * 
	 * @param in
	 *            the input stream to read from
	 * @throws IOException
	 *             if a problem occurs during reading from the underlying stream
	 * @throws Pack200Exception
	 *             if a problem occurs with an unexpected value or unsupported
	 *             codec
	 */
	private void parseCpField(InputStream in) throws IOException,
			Pack200Exception {
		cpFieldClass = parseReferences(in, Codec.DELTA5, cpFieldCount, cpClass);
		cpFieldDescriptor = parseReferences(in, Codec.UDELTA5, cpFieldCount,
				cpDescriptor);
	}

	private void parseCpFloat(InputStream in) throws IOException,
			Pack200Exception {
		cpFloat = new float[cpFloatCount];
		long last = 0;
		for (int i = 0; i < cpFloatCount; i++) {
			last = Codec.UDELTA5.decode(in, last);
			cpFloat[i] = Float.intBitsToFloat((int) last);
		}
	}

	/**
	 * Parses the constant pool interface method definitions, using
	 * {@link #cpIMethodCount} to populate {@link #cpIMethodClass} and
	 * {@link #cpIMethodDescriptor}.
	 * 
	 * @param in
	 *            the input stream to read from
	 * @throws IOException
	 *             if a problem occurs during reading from the underlying stream
	 * @throws Pack200Exception
	 *             if a problem occurs with an unexpected value or unsupported
	 *             codec
	 */
	private void parseCpIMethod(InputStream in) throws IOException,
			Pack200Exception {
		cpIMethodClass = parseReferences(in, Codec.DELTA5, cpIMethodCount,
				cpClass);
		cpIMethodDescriptor = parseReferences(in, Codec.UDELTA5,
				cpIMethodCount, cpDescriptor);
	}

	private void parseCpInt(InputStream in) throws IOException,
			Pack200Exception {
		cpInt = new int[cpIntCount];
		long last = 0;
		for (int i = 0; i < cpIntCount; i++) {
			last = Codec.UDELTA5.decode(in, last);
			cpInt[i] = (int) last;
		}
	}

	private void parseCpLong(InputStream in) throws IOException,
			Pack200Exception {
		cpLong = new long[cpLongCount];
		long last = 0;
		for (int i = 0; i < cpLongCount; i++) {
			last = Codec.UDELTA5.decode(in, last);
			cpLong[i] = last << 32;
		}
		for (int i = 0; i < cpLongCount; i++) {
			last = Codec.DELTA5.decode(in, last);
			cpLong[i] = cpLong[i] | last;
		}
	}

	/**
	 * Parses the constant pool method definitions, using {@link #cpMethodCount}
	 * to populate {@link #cpMethodClass} and {@link #cpMethodDescriptor}.
	 * 
	 * @param in
	 *            the input stream to read from
	 * @throws IOException
	 *             if a problem occurs during reading from the underlying stream
	 * @throws Pack200Exception
	 *             if a problem occurs with an unexpected value or unsupported
	 *             codec
	 */
	private void parseCpMethod(InputStream in) throws IOException,
			Pack200Exception {
		cpMethodClass = parseReferences(in, Codec.DELTA5, cpMethodCount,
				cpClass);
		cpMethodDescriptor = parseReferences(in, Codec.UDELTA5, cpMethodCount,
				cpDescriptor);
	}

	/**
	 * Parses the constant pool signature classes, using
	 * {@link #cpSignatureCount} to populate {@link #cpSignature}. A signature
	 * form is akin to the bytecode representation of a class; Z for boolean, I
	 * for int, [ for array etc. However, although classes are started with L,
	 * the classname does not follow the form; instead, there is a separate
	 * array of classes. So an array corresponding to
	 * <code>public static void main(String args[])</code> has a form of
	 * <code>[L(V)</code> and a classes array of
	 * <code>[java.lang.String]</code>. The {@link #cpSignature} is a string
	 * represenation identical to the bytecode equivalent
	 * <code>[Ljava/lang/String;(V)</code> TODO Check that the form is as
	 * above and update other types e.g. J
	 * 
	 * @param in
	 *            the input stream to read from
	 * @throws IOException
	 *             if a problem occurs during reading from the underlying stream
	 * @throws Pack200Exception
	 *             if a problem occurs with an unexpected value or unsupported
	 *             codec
	 */
	private void parseCpSignature(InputStream in) throws IOException,
			Pack200Exception {
		String[] cpSignatureForm = parseReferences(in, Codec.DELTA5,
				cpSignatureCount, cpUTF8);
		cpSignature = new String[cpSignatureCount];
		long last = 0;
		for (int i = 0; i < cpSignatureCount; i++) {
			String form = cpSignatureForm[i];
			int len = form.length();
			StringBuffer signature = new StringBuffer(64);
			ArrayList list = new ArrayList();
			for (int j = 0; j < len; j++) {
				char c = form.charAt(j);
				signature.append(c);
				if (c == 'L') {
					int index = (int) (last = Codec.UDELTA5.decode(in, last));
					String className = cpClass[index];
					list.add(className);
					signature.append(className);
				}
			}
			cpSignature[i] = signature.toString();
		}
	}

	/**
	 * Parses the constant pool strings, using {@link #cpStringCount} to
	 * populate {@link #cpString} from indexes into {@link #cpUTF8}.
	 * 
	 * @param in
	 *            the input stream to read from
	 * @throws IOException
	 *             if a problem occurs during reading from the underlying stream
	 * @throws Pack200Exception
	 *             if a problem occurs with an unexpected value or unsupported
	 *             codec
	 */
	private void parseCpString(InputStream in) throws IOException,
			Pack200Exception {
		cpString = new String[cpStringCount];
		long last = 0;
		for (int i = 0; i < cpStringCount; i++) {
			int index = (int) (last = Codec.UDELTA5.decode(in, last));
			cpString[i] = cpUTF8[index];
		}
	}

	private void parseCpUtf8(InputStream in) throws IOException,
			Pack200Exception {
		cpUTF8 = new String[(int) cpUTF8Count];
		cpUTF8[0] = "";
		int[] prefix = new int[cpUTF8Count];
		int[] suffix = new int[cpUTF8Count];
		if (cpUTF8Count > 0) {
			prefix[0] = 0;
			suffix[0] = 0;
			if (cpUTF8Count > 1)
				prefix[1] = 0;
		}
		long last = 0;
		for (int i = 2; i < cpUTF8Count; i++) {
			last = prefix[i] = (int) Codec.DELTA5.decode(in, last);
		}
		int chars = 0;
		int bigSuffix = 0;
		for (int i = 1; i < cpUTF8Count; i++) {
			last = suffix[i] = (int) Codec.UNSIGNED5.decode(in);
			if (last == 0) {
				bigSuffix++;
			} else {
				chars += last;
			}
		}
		char data[] = new char[chars];
		for (int i = 0; i < data.length; i++) {
			data[i] = (char) Codec.CHAR3.decode(in);
		}
		// read in the big suffix data
		char bigSuffixData[][] = new char[bigSuffix][];
		last = 0;
		for (int i = 0; i < bigSuffix; i++) {
			last = (int) Codec.DELTA5.decode(in, last);
			bigSuffixData[i] = new char[(int) last];
		}
		// initialise big suffix data
		for (int i = 0; i < bigSuffix; i++) {
			char[] singleBigSuffixData = bigSuffixData[i];
			last = 0;
			for (int j = 0; j < singleBigSuffixData.length; j++) {
				last = singleBigSuffixData[j] = (char) Codec.DELTA5.decode(in,
						last);
			}
		}
		// go through the strings
		chars = 0;
		bigSuffix = 0;
		for (int i = 1; i < cpUTF8Count; i++) {
			String lastString = cpUTF8[i - 1];
			if (suffix[i] == 0) {
				// The big suffix stuff hasn't been tested, and I'll be
				// surprised if it works first time w/o errors ...
				cpUTF8[i] = lastString.substring(0, prefix[i])
						+ new String(bigSuffixData[bigSuffix++]);
			} else {
				cpUTF8[i] = lastString.substring(0, prefix[i])
						+ new String(data, chars, suffix[i]);
				chars += suffix[i];
			}
		}
	}

	/**
	 * Helper method to parse <i>count</i> references from <code>in</code>,
	 * using <code>codec</code> to decode the values as indexes into
	 * <code>reference</code> (which is populated prior to this call). An
	 * exception is thrown if a decoded index falls outside the range
	 * [0..reference.length-1].
	 * 
	 * @param in
	 *            the input stream to read from
	 * @param codec
	 *            the codec to use for decoding
	 * @param count
	 *            the number of references to decode
	 * @param reference
	 *            the array of values to use for the indexes; often
	 *            {@link #cpUTF8}
	 * @throws IOException
	 *             if a problem occurs during reading from the underlying stream
	 * @throws Pack200Exception
	 *             if a problem occurs with an unexpected value or unsupported
	 *             codec
	 */
	private String[] parseReferences(InputStream in, Codec codec, int count,
			String[] reference) throws IOException, Pack200Exception {
		String[] result = new String[count];
		long last = 0;
		for (int i = 0; i < count; i++) {
			int index = (int) (last = codec.decode(in, last));
			if (index < 0 || index > reference.length)
				throw new Pack200Exception(
						"Something has gone wrong during parsing references");
			result[i] = reference[index];
		}
		return result;
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
		parseSegmentHeader(in);
		if (bandHeadersSize > 0) {
			byte[] bandHeaders = new byte[(int) bandHeadersSize];
			readFully(in, bandHeaders);
			setBandHeadersData(bandHeaders);
		}
		parseCpUtf8(in);
		parseCpInt(in);
		parseCpFloat(in);
		parseCpLong(in);
		parseCpDouble(in);
		parseCpString(in);
		parseCpClass(in);
		parseCpSignature(in);
		parseCpDescriptor(in);
		parseCpField(in);
		parseCpMethod(in);
		parseCpIMethod(in);
		parseAttributeDefinition(in);
	}

	private void parseSegmentHeader(InputStream in) throws IOException,
			Pack200Exception, Error, Pack200Exception {
		for (int m = 0; m < magic.length; m++)
			if (in.read() != magic[m])
				throw new Error("Bad header");
		setMinorVersion((int) Codec.UNSIGNED5.decode(in));
		setMajorVersion((int) Codec.UNSIGNED5.decode(in));
		setOptions(new SegmentOptions((int) Codec.UNSIGNED5.decode(in, 0)));
		parseArchiveFileCounts(in);
		parseArchiveSpecialCounts(in);
		parseCpCounts(in);
		parseClassCounts(in);
	}

	public void setArchiveModtime(long archiveModtime) {
		this.archiveModtime = archiveModtime;
	}

	public void setArchiveSize(long archiveSize) {
		this.archiveSize = archiveSize;
	}

	private void setAttributeDefinitionCount(long valuie) {
		this.attributeDefinitionCount = (int) valuie;
	}

	private void setBandHeadersData(byte[] bandHeaders) {
		this.bandHeadersData = bandHeaders;
	}

	private void setBandHeadersSize(long value) {
		this.bandHeadersSize = (int) value;
	}

	private void setClassCount(long value) {
		classCount = (int) value;
	}

	private void setCPClassCount(long value) {
		cpClassCount = (int) value;
	}

	private void setCPDescriptorCount(long value) {
		cpDescriptorCount = (int) value;
	}

	private void setCPDoubleCount(long value) {
		cpDoubleCount = (int) value;
	}

	private void setCPFieldCount(long value) {
		cpFieldCount = (int) value;
	}

	private void setCPFloatCount(long value) {
		cpFloatCount = (int) value;
	}

	private void setCPIMethodCount(long value) {
		cpIMethodCount = (int) value;
	}

	private void setCPIntCount(long value) {
		cpIntCount = (int) value;
	}

	private void setCPLongCount(long value) {
		cpLongCount = (int) value;
	}

	private void setCPMethodCount(long value) {
		cpMethodCount = (int) value;
	}

	private void setCPSignatureCount(long value) {
		cpSignatureCount = (int) value;
	}

	private void setCPStringCount(long value) {
		cpStringCount = (int) value;
	}

	private void setCPUtf8Count(long value) {
		cpUTF8Count = (int) value;
	}

	private void setDefaultClassMajorVersion(long value) {
		defaultClassMajorVersion = (int) value;
	}

	private void setDefaultClassMinorVersion(long value) {
		defaultClassMinorVersion = (int) value;
	}

	private void setInnerClassCount(long value) {
		innerClassCount = (int) value;
	}

	/**
	 * Sets the major version of this archive.
	 * 
	 * @param version
	 *            the minor version of the archive
	 * @throws Pack200Exception
	 *             if the major version is not 150
	 */
	private void setMajorVersion(int version) throws Pack200Exception {
		if (version != 150)
			throw new Pack200Exception("Invalid segment major version");
		major = version;
	}

	/**
	 * Sets the minor version of this archive
	 * 
	 * @param version
	 *            the minor version of the archive
	 * @throws Pack200Exception
	 *             if the minor version is not 7
	 */
	private void setMinorVersion(int version) throws Pack200Exception {
		if (version != 7)
			throw new Pack200Exception("Invalid segment minor version");
		minor = version;
	}

	public void setNumberOfFiles(long value) {
		numberOfFiles = (int) value;
	}

	private void setOptions(SegmentOptions options) {
		this.options = options;
	}

	public void setSegmentsRemaining(long value) {
		segmentsRemaining = (int) value;
	}
}
