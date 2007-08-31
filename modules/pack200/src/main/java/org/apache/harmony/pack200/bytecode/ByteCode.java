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
package org.apache.harmony.pack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ByteCode extends ClassFileEntry {
	private static final Map byteCodes = new HashMap();
	static {
		ByteCode[] byteCodeArray = new ByteCode[256];
		byteCodeArray[0] = new ByteCode(0, "nop");
		byteCodeArray[1] = new ByteCode(1, "aconst_null");
		byteCodeArray[2] = new ByteCode(2, "iconst_m1");
		byteCodeArray[3] = new ByteCode(3, "iconst_0");
		byteCodeArray[4] = new ByteCode(4, "iconst_1");
		byteCodeArray[5] = new ByteCode(5, "iconst_2");
		byteCodeArray[6] = new ByteCode(6, "iconst_3");
		byteCodeArray[7] = new ByteCode(7, "iconst_4");
		byteCodeArray[8] = new ByteCode(8, "iconst_5");
		byteCodeArray[9] = new ByteCode(9, "lconst_0");
		byteCodeArray[10] = new ByteCode(10, "lconst_1");
		byteCodeArray[11] = new ByteCode(11, "fconst_0");
		byteCodeArray[12] = new ByteCode(12, "fconst_1");
		byteCodeArray[13] = new ByteCode(13, "fconst_2");
		byteCodeArray[14] = new ByteCode(14, "dconst_0");
		byteCodeArray[15] = new ByteCode(15, "dconst_1");
		byteCodeArray[16] = new ByteCode(16, "bipush");
		byteCodeArray[17] = new ByteCode(17, "sipush");
		byteCodeArray[18] = new ByteCode(18, "ldc", new int[] { 18, 0 });
		byteCodeArray[19] = new ByteCode(19, "ldc_w");
		byteCodeArray[20] = new ByteCode(20, "ldc2_w");
		byteCodeArray[21] = new ByteCode(21, "iload");
		byteCodeArray[22] = new ByteCode(22, "lload");
		byteCodeArray[23] = new ByteCode(23, "fload");
		byteCodeArray[24] = new ByteCode(24, "dload");
		byteCodeArray[25] = new ByteCode(25, "aload");
		byteCodeArray[26] = new ByteCode(26, "iload_0");
		byteCodeArray[27] = new ByteCode(27, "iload_1");
		byteCodeArray[28] = new ByteCode(28, "iload_2");
		byteCodeArray[29] = new ByteCode(29, "iload_3");
		byteCodeArray[30] = new ByteCode(30, "lload_0");
		byteCodeArray[31] = new ByteCode(31, "lload_1");
		byteCodeArray[32] = new ByteCode(32, "lload_2");
		byteCodeArray[33] = new ByteCode(33, "lload_3");
		byteCodeArray[34] = new ByteCode(34, "fload_0");
		byteCodeArray[35] = new ByteCode(35, "fload_1");
		byteCodeArray[36] = new ByteCode(36, "fload_2");
		byteCodeArray[37] = new ByteCode(37, "fload_3");
		byteCodeArray[38] = new ByteCode(38, "dload_0");
		byteCodeArray[39] = new ByteCode(39, "dload_1");
		byteCodeArray[40] = new ByteCode(40, "dload_2");
		byteCodeArray[41] = new ByteCode(41, "dload_3");
		byteCodeArray[42] = new ByteCode(42, "aload_0");
		byteCodeArray[43] = new ByteCode(43, "aload_1");
		byteCodeArray[44] = new ByteCode(44, "aload_2");
		byteCodeArray[45] = new ByteCode(45, "aload_3");
		byteCodeArray[46] = new ByteCode(46, "iaload");
		byteCodeArray[47] = new ByteCode(47, "laload");
		byteCodeArray[48] = new ByteCode(48, "faload");
		byteCodeArray[49] = new ByteCode(49, "daload");
		byteCodeArray[50] = new ByteCode(50, "aaload");
		byteCodeArray[51] = new ByteCode(51, "baload");
		byteCodeArray[52] = new ByteCode(52, "caload");
		byteCodeArray[53] = new ByteCode(53, "saload");
		byteCodeArray[54] = new ByteCode(54, "istore");
		byteCodeArray[55] = new ByteCode(55, "lstore");
		byteCodeArray[56] = new ByteCode(56, "fstore");
		byteCodeArray[57] = new ByteCode(57, "dstore");
		byteCodeArray[58] = new ByteCode(58, "astore");
		byteCodeArray[59] = new ByteCode(59, "istore_0");
		byteCodeArray[60] = new ByteCode(60, "istore_1");
		byteCodeArray[61] = new ByteCode(61, "istore_2");
		byteCodeArray[62] = new ByteCode(62, "istore_3");
		byteCodeArray[63] = new ByteCode(63, "lstore_0");
		byteCodeArray[64] = new ByteCode(64, "lstore_1");
		byteCodeArray[65] = new ByteCode(65, "lstore_2");
		byteCodeArray[66] = new ByteCode(66, "lstore_3");
		byteCodeArray[67] = new ByteCode(67, "fstore_0");
		byteCodeArray[68] = new ByteCode(68, "fstore_1");
		byteCodeArray[69] = new ByteCode(69, "fstore_2");
		byteCodeArray[70] = new ByteCode(70, "fstore_3");
		byteCodeArray[71] = new ByteCode(71, "dstore_0");
		byteCodeArray[72] = new ByteCode(72, "dstore_1");
		byteCodeArray[73] = new ByteCode(73, "dstore_2");
		byteCodeArray[74] = new ByteCode(74, "dstore_3");
		byteCodeArray[75] = new ByteCode(75, "astore_0");
		byteCodeArray[76] = new ByteCode(76, "astore_1");
		byteCodeArray[77] = new ByteCode(77, "astore_2");
		byteCodeArray[78] = new ByteCode(78, "astore_3");
		byteCodeArray[79] = new ByteCode(79, "iastore");
		byteCodeArray[80] = new ByteCode(80, "lastore");
		byteCodeArray[81] = new ByteCode(81, "fastore");
		byteCodeArray[82] = new ByteCode(82, "dastore");
		byteCodeArray[83] = new ByteCode(83, "aastore");
		byteCodeArray[84] = new ByteCode(84, "bastore");
		byteCodeArray[85] = new ByteCode(85, "castore");
		byteCodeArray[86] = new ByteCode(86, "sastore");
		byteCodeArray[87] = new ByteCode(87, "pop");
		byteCodeArray[88] = new ByteCode(88, "pop2");
		byteCodeArray[89] = new ByteCode(89, "dup");
		byteCodeArray[90] = new ByteCode(90, "dup_x1");
		byteCodeArray[91] = new ByteCode(91, "dup_x2");
		byteCodeArray[92] = new ByteCode(92, "dup2");
		byteCodeArray[93] = new ByteCode(93, "dup2_x1");
		byteCodeArray[94] = new ByteCode(94, "dup2_x2");
		byteCodeArray[95] = new ByteCode(95, "swap");
		byteCodeArray[96] = new ByteCode(96, "iadd");
		byteCodeArray[97] = new ByteCode(97, "ladd");
		byteCodeArray[98] = new ByteCode(98, "fadd");
		byteCodeArray[99] = new ByteCode(99, "dadd");
		byteCodeArray[100] = new ByteCode(100, "isub");
		byteCodeArray[101] = new ByteCode(101, "lsub");
		byteCodeArray[102] = new ByteCode(102, "fsub");
		byteCodeArray[103] = new ByteCode(103, "dsub");
		byteCodeArray[104] = new ByteCode(104, "imul");
		byteCodeArray[105] = new ByteCode(105, "lmul");
		byteCodeArray[106] = new ByteCode(106, "fmul");
		byteCodeArray[107] = new ByteCode(107, "dmul");
		byteCodeArray[108] = new ByteCode(108, "idiv");
		byteCodeArray[109] = new ByteCode(109, "ldiv");
		byteCodeArray[110] = new ByteCode(110, "fdiv");
		byteCodeArray[111] = new ByteCode(111, "ddiv");
		byteCodeArray[112] = new ByteCode(112, "irem");
		byteCodeArray[113] = new ByteCode(113, "lrem");
		byteCodeArray[114] = new ByteCode(114, "frem");
		byteCodeArray[115] = new ByteCode(115, "drem");
		byteCodeArray[116] = new ByteCode(116, "");
		byteCodeArray[117] = new ByteCode(117, "lneg");
		byteCodeArray[118] = new ByteCode(118, "fneg");
		byteCodeArray[119] = new ByteCode(119, "dneg");
		byteCodeArray[120] = new ByteCode(120, "ishl");
		byteCodeArray[121] = new ByteCode(121, "lshl");
		byteCodeArray[122] = new ByteCode(122, "ishr");
		byteCodeArray[123] = new ByteCode(123, "lshr");
		byteCodeArray[124] = new ByteCode(124, "iushr");
		byteCodeArray[125] = new ByteCode(125, "lushr");
		byteCodeArray[126] = new ByteCode(126, "iand");
		byteCodeArray[127] = new ByteCode(127, "land");
		byteCodeArray[128] = new ByteCode(128, "ior");
		byteCodeArray[129] = new ByteCode(129, "lor");
		byteCodeArray[130] = new ByteCode(130, "ixor");
		byteCodeArray[131] = new ByteCode(131, "lxor");
		byteCodeArray[132] = new ByteCode(132, "iinc");
		byteCodeArray[133] = new ByteCode(133, "i2l");
		byteCodeArray[134] = new ByteCode(134, "i2f");
		byteCodeArray[135] = new ByteCode(135, "i2d");
		byteCodeArray[136] = new ByteCode(136, "l2i");
		byteCodeArray[137] = new ByteCode(137, "l2f");
		byteCodeArray[138] = new ByteCode(138, "l2d");
		byteCodeArray[139] = new ByteCode(139, "f2i");
		byteCodeArray[140] = new ByteCode(140, "f2l");
		byteCodeArray[141] = new ByteCode(141, "f2d");
		byteCodeArray[142] = new ByteCode(142, "d2i");
		byteCodeArray[143] = new ByteCode(143, "d2l");
		byteCodeArray[144] = new ByteCode(144, "d2f");
		byteCodeArray[145] = new ByteCode(145, "i2b");
		byteCodeArray[146] = new ByteCode(146, "i2c");
		byteCodeArray[147] = new ByteCode(147, "i2s");
		byteCodeArray[148] = new ByteCode(148, "lcmp");
		byteCodeArray[149] = new ByteCode(149, "fcmpl");
		byteCodeArray[150] = new ByteCode(150, "fcmpg");
		byteCodeArray[151] = new ByteCode(151, "dcmpl");
		byteCodeArray[152] = new ByteCode(152, "dcmpg");
		byteCodeArray[153] = new ByteCode(153, "ifeq");
		byteCodeArray[154] = new ByteCode(154, "ifne");
		byteCodeArray[155] = new ByteCode(155, "iflt");
		byteCodeArray[156] = new ByteCode(156, "ifge");
		byteCodeArray[157] = new ByteCode(157, "ifgt");
		byteCodeArray[158] = new ByteCode(158, "ifle");
		byteCodeArray[159] = new ByteCode(159, "if_icmpeq");
		byteCodeArray[160] = new ByteCode(160, "if_icmpne");
		byteCodeArray[161] = new ByteCode(161, "if_icmplt");
		byteCodeArray[162] = new ByteCode(162, "if_icmpge");
		byteCodeArray[163] = new ByteCode(163, "if_icmpgt");
		byteCodeArray[164] = new ByteCode(164, "if_icmple");
		byteCodeArray[165] = new ByteCode(165, "if_acmpeq");
		byteCodeArray[166] = new ByteCode(166, "if_acmpne");
		byteCodeArray[167] = new ByteCode(167, "goto");
		byteCodeArray[168] = new ByteCode(168, "jsr");
		byteCodeArray[169] = new ByteCode(169, "ret");
		byteCodeArray[170] = new ByteCode(170, "tableswitch");
		byteCodeArray[171] = new ByteCode(171, "lookupswitch");
		byteCodeArray[172] = new ByteCode(172, "ireturn");
		byteCodeArray[173] = new ByteCode(173, "lreturn");
		byteCodeArray[174] = new ByteCode(174, "freturn");
		byteCodeArray[175] = new ByteCode(175, "dreturn");
		byteCodeArray[176] = new ByteCode(176, "areturn");
		byteCodeArray[177] = new ByteCode(177, "return");
		byteCodeArray[178] = new ByteCode(178, "getstatic", new int[] { 178, 0,
				0 });
		byteCodeArray[179] = new ByteCode(179, "putstatic", new int[] { 179, 0,
				0 });
		byteCodeArray[180] = new ByteCode(180, "getfield", new int[] { 180, 0,
				0 });
		byteCodeArray[181] = new ByteCode(181, "putfield", new int[] { 181, 0,
				0 });
		byteCodeArray[182] = new ByteCode(182, "invokevirtual", new int[] {
				182, 0, 0 });
		byteCodeArray[183] = new ByteCode(183, "invokespecial", new int[] {
				183, 0, 0 });
		byteCodeArray[184] = new ByteCode(184, "invokestatic", new int[] { 184,
				0, 0 });
		byteCodeArray[185] = new ByteCode(185, "invokeinterface");
		byteCodeArray[186] = new ByteCode(186, "xxxunusedxxx");
		byteCodeArray[187] = new ByteCode(187, "new");
		byteCodeArray[188] = new ByteCode(188, "newarray");
		byteCodeArray[189] = new ByteCode(189, "anewarray");
		byteCodeArray[190] = new ByteCode(190, "arraylength");
		byteCodeArray[191] = new ByteCode(191, "athrow");
		byteCodeArray[192] = new ByteCode(192, "checkcast");
		byteCodeArray[193] = new ByteCode(193, "instanceof");
		byteCodeArray[194] = new ByteCode(194, "monitorenter");
		byteCodeArray[195] = new ByteCode(195, "monitorexit");
		byteCodeArray[196] = new ByteCode(196, "wide");
		byteCodeArray[197] = new ByteCode(197, "multianewarray");
		byteCodeArray[198] = new ByteCode(198, "ifnull");
		byteCodeArray[199] = new ByteCode(199, "ifnonnull");
		byteCodeArray[200] = new ByteCode(200, "goto_w");
		byteCodeArray[201] = new ByteCode(201, "jsr_w");
		// Extra ones defined by pack200
		byteCodeArray[202] = new ByteCode(202, "getstatic_this", new int[] {
				178, 0, 0 });
		byteCodeArray[203] = new ByteCode(203, "putstatic_this", new int[] {
				179, 0, 0 });
		byteCodeArray[204] = new ByteCode(204, "getfield_this", new int[] {
				180, 0, 0 });
		byteCodeArray[205] = new ByteCode(205, "putfield_this", new int[] {
				181, 0, 0 });
		byteCodeArray[231] = new ByteCode(231, "invokespecial_super_init",
				new int[] { 183, 0, 0 });
		// Reserved bytecodes
		byteCodeArray[254] = new ByteCode(254, "impdep1");
		byteCodeArray[255] = new ByteCode(255, "impdep2");
		for (int i = 0; i < byteCodeArray.length; i++) {
			ByteCode byteCode = byteCodeArray[i];
			if (byteCode != null) {
				byteCodes.put(new Integer(i), byteCode);
				byteCodes.put(byteCode.name, byteCode);
			}
		}
	}

	public static ByteCode getByteCode(int opcode) {
		ByteCode byteCode = (ByteCode) byteCodes
				.get(new Integer(0xff & opcode));
		if (byteCode == null)
			throw new Error("Unknown bytecode");
		return byteCode;
	}

	private final String name;
	private final ClassFileEntry[] nested;
	private final int opcode;
	private final int[] rewrite;

	protected ByteCode(int opcode, String name) {
		this(opcode, name, new int[] { opcode });
	}

	protected ByteCode(int opcode, String name, int[] rewrite) {
		this(opcode, name, rewrite, ClassFileEntry.NONE);
	}

	protected ByteCode(int opcode, String name, int[] rewrite,
			ClassFileEntry[] nested) {
		this.opcode = opcode;
		this.name = name;
		this.rewrite = rewrite;
		this.nested = nested;
	}

	protected void doWrite(DataOutputStream dos) throws IOException {
		// TODO operands?
		for (int i = 0; i < rewrite.length; i++) {
			dos.writeByte(rewrite[i]);
		}
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ByteCode other = (ByteCode) obj;
		if (opcode != other.opcode)
			return false;
		return true;
	}

	public int getLength() {
		return rewrite.length;
	}

	public String getName() {
		return name;
	}

	protected ClassFileEntry[] getNestedClassFileEntries() {
		return nested;
	}

	public int getOpcode() {
		return opcode;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + opcode;
		return result;
	}

	protected void resolve(ClassConstantPool pool) {
		// TODO Auto-generated method stub
		super.resolve(pool);
	}

	public String toString() {
		return name;
	}
}
