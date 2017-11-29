package jcla.classfile;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO document all instructions
 * <p>
 * An Opcode is an object representing a Java&trade; Virtual Machine instruction. Each Opcode is defined in order, so to
 * get the actual opcode of an instruction, call {@link #ordinal()}.
 * </p>
 *
 * @author link
 */
public enum Opcode {
	NOP("nop", 0),
	ACONST_NULL("aconst_null", 0),
	ICONST_M1("iconst_m1", 0),
	ICONST_0("iconst_0", 0),
	ICONST_1("iconst_1", 0),
	ICONST_2("iconst_2", 0),
	ICONST_3("iconst_3", 0),
	ICONST_4("iconst_4", 0),
	ICONST_5("iconst_5", 0),
	LCONST_0("lconst_0", 0),
	LCONST_1("lconst_1", 0),
	FCONST_0("fconst_0", 0),
	FCONST_1("fconst_1", 0),
	FCONST_2("fconst_2", 0),
	DCONST_0("dconst_0", 0),
	DCONST_1("dconst_1", 0),
	BIPUSH("bipush", 1),
	SIPUSH("sipush", 1),
	LDC("ldc", 1),
	LDC_W("ldc_w", 2),
	LDC2_W("ldc2_w", 2),
	ILOAD("iload", 1),
	LLOAD("lload", 1),
	FLOAD("fload", 1),
	DLOAD("dload", 1),
	ALOAD("aload", 1),
	ILOAD_0("iload_0", 0),
	ILOAD_1("iload_1", 0),
	ILOAD_2("iload_2", 0),
	ILOAD_3("iload_3", 0),
	LLOAD_0("lload_0", 0),
	LLOAD_1("lload_1", 0),
	LLOAD_2("lload_2", 0),
	LLOAD_3("lload_3", 0),
	FLOAD_0("fload_0", 0),
	FLOAD_1("fload_1", 0),
	FLOAD_2("fload_2", 0),
	FLOAD_3("fload_3", 0),
	DLOAD_0("dload_0", 0),
	DLOAD_1("dload_1", 0),
	DLOAD_2("dload_2", 0),
	DLOAD_3("dload_3", 0),
	ALOAD_0("aload_0", 0),
	ALOAD_1("aload_1", 0),
	ALOAD_2("aload_2", 0),
	ALOAD_3("aload_3", 0),
	IALOAD("iaload", 0),
	LALOAD("laload", 0),
	FALOAD("faload", 0),
	DALOAD("daload", 0),
	AALOAD("aaload", 0),
	BALOAD("baload", 0),
	CALOAD("caload", 0),
	SALOAD("saload", 0),
	ISTORE("istore", 1),
	LSTORE("lstore", 1),
	FSTORE("fstore", 1),
	DSTORE("dstore", 1),
	ASTORE("astore", 1),
	ISTORE_0("istore_0", 0),
	ISTORE_1("istore_1", 0),
	ISTORE_2("istore_2", 0),
	ISTORE_3("istore_3", 0),
	LSTORE_0("lstore_0", 0),
	LSTORE_1("lstore_1", 0),
	LSTORE_2("lstore_2", 0),
	LSTORE_3("lstore_3", 0),
	FSTORE_0("fstore_0", 0),
	FSTORE_1("fstore_1", 0),
	FSTORE_2("fstore_2", 0),
	FSTORE_3("fstore_3", 0),
	DSTORE_0("dstore_0", 0),
	DSTORE_1("dstore_1", 0),
	DSTORE_2("dstore_2", 0),
	DSTORE_3("dstore_3", 0),
	ASTORE_0("astore_0", 0),
	ASTORE_1("astore_1", 0),
	ASTORE_2("astore_2", 0),
	ASTORE_3("astore_3", 0),
	IASTORE("iastore", 0),
	LASTORE("lastore", 0),
	FASTORE("fastore", 0),
	DASTORE("dastore", 0),
	AASTORE("aastore", 0),
	BASTORE("bastore", 0),
	CASTORE("castore", 0),
	SASTORE("sastore", 0),
	POP("pop", 0),
	POP2("pop2", 0),
	DUP("dup", 0),
	DUP_X1("dup_x1", 0),
	DUP_X2("dup_x2", 0),
	DUP2("dup2", 0),
	DUP2_X1("dup2_x1", 0),
	DUP2_X2("dup2_x2", 0),
	SWAP("swap", 0),
	IADD("iadd", 0),
	LADD("ladd", 0),
	FADD("fadd", 0),
	DADD("dadd", 0),
	ISUB("isub", 0),
	LSUB("lsub", 0),
	FSUB("fsub", 0),
	DSUB("dsub", 0),
	IMUL("imul", 0),
	LMUL("lmul", 0),
	FMUL("fmul", 0),
	DMUL("dmul", 0),
	IDIV("idiv", 0),
	LDIV("ldiv", 0),
	FDIV("fdiv", 0),
	DDIV("ddiv", 0),
	IREM("irem", 0),
	LREM("lrem", 0),
	FREM("frem", 0),
	DREM("drem", 0),
	INEG("ineg", 0),
	LNEG("lneg", 0),
	FNEG("fneg", 0),
	DNEG("dneg", 0),
	ISHL("ishl", 0),
	LSHL("lshl", 0),
	ISHR("ishr", 0),
	LSHR("lshr", 0),
	IUSHR("iushr", 0),
	LUSHR("lushr", 0),
	IAND("iand", 0),
	LAND("land", 0),
	IOR("ior", 0),
	LOR("lor", 0),
	IXOR("ixor", 0),
	LXOR("lxor", 0),
	IINC("iinc", 0),
	I2L("i2l", 0),
	I2F("i2f", 0),
	I2D("i2d", 0),
	L2I("l2i", 0),
	L2F("l2f", 0),
	L2D("l2d", 0),
	F2I("f2i", 0),
	F2L("f2l", 0),
	F2D("f2d", 0),
	D2I("d2i", 0),
	D2L("d2l", 0),
	D2F("d2f", 0),
	I2B("i2b", 0),
	I2C("i2c", 0),
	I2S("i2s", 0),
	LCMP("lcmp", 0),
	FCMPL("fcmpl", 0),
	FCMPG("fcmpg", 0),
	DCMPL("dcmpl", 0),
	DCMPG("dcmpg", 0),
	IFEQ("ifeq", 2),
	IFNE("ifne", 2),
	IFLT("iflt", 2),
	IFGE("ifge", 2),
	IFGT("ifgt", 2),
	IFLE("ifle", 2),
	IF_ICMPEQ("if_icmpeq", 2),
	IF_ICMPNE("if_icmpne", 2),
	IF_ICMPLT("if_icmplt", 2),
	IF_ICMPGE("if_icmpge", 2),
	IF_ICMPGT("if_icmpgt", 2),
	IF_ICMPLE("if_icmple", 2),
	IF_ACMPEQ("if_acmpeq", 2),
	IF_ACMPNE("if_acmpne", 2),
	GOTO("goto", 2),
	JSR("jsr", 2),
	RET("ret", 1),
	TABLESWITCH("tableswitch", 12),
	LOOKUPSWITCH("lookupswitch", 8),
	IRETURN("ireturn", 0),
	LRETURN("lreturn", 0),
	FRETURN("freturn", 0),
	DRETURN("dreturn", 0),
	ARETURN("areturn", 0),
	RETURN("return", 0),
	GETSTATIC("getstatic", 2),
	PUTSTATIC("putstatic", 2),
	GETFIELD("getfield", 2),
	PUTFIELD("putfield", 2),
	INVOKEVIRTUAL("invokevirtual", 2),
	INVOKESPECIAL("invokespecial", 2),
	INVOKESTATIC("invokestatic", 2),
	INVOKEINTERFACE("invokeinterface", 4),
	INVOKEDYNAMIC("invokedynamic", 4),
	NEW("new", 2),
	NEWARRAY("newarray", 2),
	ANEWARRAY("anewarray", 2),
	ARRAYLENGTH("arraylength", 0),
	ATHROW("athrow", 0),
	CHECKCAST("checkcast", 2),
	INSTANCEOF("instanceof", 2),
	MONITORENTER("monitorenter", 0),
	MONITOREXIT("monitorexit", 0),
	WIDE("wide", 3),
	MULTIANEWARRAY("multianewarray", 3),
	IFNULL("ifnull", 2),
	IFNONNULL("ifnonnull", 2),
	GOTO_W("goto_w", 4),
	JSR_W("jsr_w", 4);

	private static final Opcode[] INDEX = {
			NOP,
			ACONST_NULL,
			ICONST_M1,
			ICONST_0,
			ICONST_1,
			ICONST_2,
			ICONST_3,
			ICONST_4,
			ICONST_5,
			LCONST_0,
			LCONST_1,
			FCONST_0,
			FCONST_1,
			FCONST_2,
			DCONST_0,
			DCONST_1,
			BIPUSH,
			SIPUSH,
			LDC,
			LDC_W,
			LDC2_W,
			ILOAD,
			LLOAD,
			FLOAD,
			DLOAD,
			ALOAD,
			ILOAD_0,
			ILOAD_1,
			ILOAD_2,
			ILOAD_3,
			LLOAD_0,
			LLOAD_1,
			LLOAD_2,
			LLOAD_3,
			FLOAD_0,
			FLOAD_1,
			FLOAD_2,
			FLOAD_3,
			DLOAD_0,
			DLOAD_1,
			DLOAD_2,
			DLOAD_3,
			ALOAD_0,
			ALOAD_1,
			ALOAD_2,
			ALOAD_3,
			IALOAD,
			LALOAD,
			FALOAD,
			DALOAD,
			AALOAD,
			BALOAD,
			CALOAD,
			SALOAD,
			ISTORE,
			LSTORE,
			FSTORE,
			DSTORE,
			ASTORE,
			ISTORE_0,
			ISTORE_1,
			ISTORE_2,
			ISTORE_3,
			LSTORE_0,
			LSTORE_1,
			LSTORE_2,
			LSTORE_3,
			FSTORE_0,
			FSTORE_1,
			FSTORE_2,
			FSTORE_3,
			DSTORE_0,
			DSTORE_1,
			DSTORE_2,
			DSTORE_3,
			ASTORE_0,
			ASTORE_1,
			ASTORE_2,
			ASTORE_3,
			IASTORE,
			LASTORE,
			FASTORE,
			DASTORE,
			AASTORE,
			BASTORE,
			CASTORE,
			SASTORE,
			POP,
			POP2,
			DUP,
			DUP_X1,
			DUP_X2,
			DUP2,
			DUP2_X1,
			DUP2_X2,
			SWAP,
			IADD,
			LADD,
			FADD,
			DADD,
			ISUB,
			LSUB,
			FSUB,
			DSUB,
			IMUL,
			LMUL,
			FMUL,
			DMUL,
			IDIV,
			LDIV,
			FDIV,
			DDIV,
			IREM,
			LREM,
			FREM,
			DREM,
			INEG,
			LNEG,
			FNEG,
			DNEG,
			ISHL,
			LSHL,
			ISHR,
			LSHR,
			IUSHR,
			LUSHR,
			IAND,
			LAND,
			IOR,
			LOR,
			IXOR,
			LXOR,
			IINC,
			I2L,
			I2F,
			I2D,
			L2I,
			L2F,
			L2D,
			F2I,
			F2L,
			F2D,
			D2I,
			D2L,
			D2F,
			I2B,
			I2C,
			I2S,
			LCMP,
			FCMPL,
			FCMPG,
			DCMPL,
			DCMPG,
			IFEQ,
			IFNE,
			IFLT,
			IFGE,
			IFGT,
			IFLE,
			IF_ICMPEQ,
			IF_ICMPNE,
			IF_ICMPLT,
			IF_ICMPGE,
			IF_ICMPGT,
			IF_ICMPLE,
			IF_ACMPEQ,
			IF_ACMPNE,
			GOTO,
			JSR,
			RET,
			TABLESWITCH,
			LOOKUPSWITCH,
			IRETURN,
			LRETURN,
			FRETURN,
			DRETURN,
			ARETURN,
			RETURN,
			GETSTATIC,
			PUTSTATIC,
			GETFIELD,
			PUTFIELD,
			INVOKEVIRTUAL,
			INVOKESPECIAL,
			INVOKESTATIC,
			INVOKEINTERFACE,
			INVOKEDYNAMIC,
			NEW,
			NEWARRAY,
			ANEWARRAY,
			ARRAYLENGTH,
			ATHROW,
			CHECKCAST,
			INSTANCEOF,
			MONITORENTER,
			MONITOREXIT,
			WIDE,
			MULTIANEWARRAY,
			IFNULL,
			IFNONNULL,
			GOTO_W,
			JSR_W
	};

	private static final Map<String, Opcode> NAMED_INDEX = new HashMap<>(202);

	static {
		NAMED_INDEX.put("nop", NOP);
		NAMED_INDEX.put("aconst_null", ACONST_NULL);
		NAMED_INDEX.put("iconst_m1", ICONST_M1);
		NAMED_INDEX.put("iconst_0", ICONST_0);
		NAMED_INDEX.put("iconst_1", ICONST_1);
		NAMED_INDEX.put("iconst_2", ICONST_2);
		NAMED_INDEX.put("iconst_3", ICONST_3);
		NAMED_INDEX.put("iconst_4", ICONST_4);
		NAMED_INDEX.put("iconst_5", ICONST_5);
		NAMED_INDEX.put("lconst_0", LCONST_0);
		NAMED_INDEX.put("lconst_1", LCONST_1);
		NAMED_INDEX.put("fconst_0", FCONST_0);
		NAMED_INDEX.put("fconst_1", FCONST_1);
		NAMED_INDEX.put("dconst_0", DCONST_0);
		NAMED_INDEX.put("dconst_1", DCONST_1);
		NAMED_INDEX.put("bipush", BIPUSH);
		NAMED_INDEX.put("sipush", SIPUSH);
		NAMED_INDEX.put("ldc", LDC);
		NAMED_INDEX.put("ldc_w", LDC_W);
		NAMED_INDEX.put("ldc2_w", LDC2_W);
		NAMED_INDEX.put("iload", ILOAD);
		NAMED_INDEX.put("lload", LLOAD);
		NAMED_INDEX.put("fload", FLOAD);
		NAMED_INDEX.put("dload", DLOAD);
		NAMED_INDEX.put("aload", ALOAD);
		NAMED_INDEX.put("iload_0", ILOAD_0);
		NAMED_INDEX.put("iload_1", ILOAD_1);
		NAMED_INDEX.put("iload_2", ILOAD_2);
		NAMED_INDEX.put("iload_3", ILOAD_3);
		NAMED_INDEX.put("lload_0", LLOAD_0);
		NAMED_INDEX.put("lload_1", LLOAD_1);
		NAMED_INDEX.put("lload_2", LLOAD_2);
		NAMED_INDEX.put("lload_3", LLOAD_3);
		NAMED_INDEX.put("fload_0", FLOAD_0);
		NAMED_INDEX.put("fload_1", FLOAD_1);
		NAMED_INDEX.put("fload_2", FLOAD_2);
		NAMED_INDEX.put("fload_3", FLOAD_3);
		NAMED_INDEX.put("dload_0", DLOAD_0);
		NAMED_INDEX.put("dload_1", DLOAD_1);
		NAMED_INDEX.put("dload_2", DLOAD_2);
		NAMED_INDEX.put("dload_3", DLOAD_3);
		NAMED_INDEX.put("aload_0", ALOAD_0);
		NAMED_INDEX.put("aload_1", ALOAD_1);
		NAMED_INDEX.put("aload_2", ALOAD_2);
		NAMED_INDEX.put("aload_3", ALOAD_3);
		NAMED_INDEX.put("iaload", IALOAD);
		NAMED_INDEX.put("laload", LALOAD);
		NAMED_INDEX.put("faload", FALOAD);
		NAMED_INDEX.put("daload", DALOAD);
		NAMED_INDEX.put("aaload", AALOAD);
		NAMED_INDEX.put("baload", BALOAD);
		NAMED_INDEX.put("caload", CALOAD);
		NAMED_INDEX.put("saload", SALOAD);
		NAMED_INDEX.put("istore", ISTORE);
		NAMED_INDEX.put("lstore", LSTORE);
		NAMED_INDEX.put("fstore", FSTORE);
		NAMED_INDEX.put("dstore", DSTORE);
		NAMED_INDEX.put("astore", ASTORE);
		NAMED_INDEX.put("istore_0", ISTORE_0);
		NAMED_INDEX.put("istore_1", ISTORE_1);
		NAMED_INDEX.put("istore_2", ISTORE_2);
		NAMED_INDEX.put("istore_3", ISTORE_3);
		NAMED_INDEX.put("lstore_0", LSTORE_0);
		NAMED_INDEX.put("lstore_1", LSTORE_1);
		NAMED_INDEX.put("lstore_2", LSTORE_2);
		NAMED_INDEX.put("lstore_3", LSTORE_3);
		NAMED_INDEX.put("fstore_0", FSTORE_0);
		NAMED_INDEX.put("fstore_1", FSTORE_1);
		NAMED_INDEX.put("fstore_2", FSTORE_2);
		NAMED_INDEX.put("fstore_3", FSTORE_3);
		NAMED_INDEX.put("dstore_0", DSTORE_0);
		NAMED_INDEX.put("dstore_1", DSTORE_1);
		NAMED_INDEX.put("dstore_2", DSTORE_2);
		NAMED_INDEX.put("dstore_3", DSTORE_3);
		NAMED_INDEX.put("astore_0", ASTORE_0);
		NAMED_INDEX.put("astore_1", ASTORE_1);
		NAMED_INDEX.put("astore_2", ASTORE_2);
		NAMED_INDEX.put("astore_3", ASTORE_3);
		NAMED_INDEX.put("iastore", IASTORE);
		NAMED_INDEX.put("lastore", LASTORE);
		NAMED_INDEX.put("fastore", FASTORE);
		NAMED_INDEX.put("dastore", DASTORE);
		NAMED_INDEX.put("aastore", AASTORE);
		NAMED_INDEX.put("bastore", BASTORE);
		NAMED_INDEX.put("castore", CASTORE);
		NAMED_INDEX.put("sastore", SASTORE);
		NAMED_INDEX.put("pop", POP);
		NAMED_INDEX.put("pop2", POP2);
		NAMED_INDEX.put("dup", DUP);
		NAMED_INDEX.put("dup_x1", DUP_X1);
		NAMED_INDEX.put("dup_x2", DUP_X2);
		NAMED_INDEX.put("dup2", DUP2);
		NAMED_INDEX.put("dup2_x1", DUP2_X1);
		NAMED_INDEX.put("dup2_x2", DUP2_X2);
		NAMED_INDEX.put("swap", SWAP);
		NAMED_INDEX.put("iadd", IADD);
		NAMED_INDEX.put("ladd", LADD);
		NAMED_INDEX.put("fadd", FADD);
		NAMED_INDEX.put("dadd", DADD);
		NAMED_INDEX.put("isub", ISUB);
		NAMED_INDEX.put("lsub", LSUB);
		NAMED_INDEX.put("fsub", FSUB);
		NAMED_INDEX.put("dsub", DSUB);
		NAMED_INDEX.put("imul", IMUL);
		NAMED_INDEX.put("lmul", LMUL);
		NAMED_INDEX.put("fmul", FMUL);
		NAMED_INDEX.put("dmul", DMUL);
		NAMED_INDEX.put("idiv", IDIV);
		NAMED_INDEX.put("ldiv", LDIV);
		NAMED_INDEX.put("fdiv", FDIV);
		NAMED_INDEX.put("ddiv", DDIV);
		NAMED_INDEX.put("irem", IREM);
		NAMED_INDEX.put("lrem", LREM);
		NAMED_INDEX.put("frem", FREM);
		NAMED_INDEX.put("drem", DREM);
		NAMED_INDEX.put("ineg", INEG);
		NAMED_INDEX.put("lneg", LNEG);
		NAMED_INDEX.put("fneg", FNEG);
		NAMED_INDEX.put("dneg", DNEG);
		NAMED_INDEX.put("ishl", ISHL);
		NAMED_INDEX.put("lshl", LSHL);
		NAMED_INDEX.put("ishr", ISHR);
		NAMED_INDEX.put("lshr", LSHR);
		NAMED_INDEX.put("iand", IAND);
		NAMED_INDEX.put("land", LAND);
		NAMED_INDEX.put("ior", IOR);
		NAMED_INDEX.put("lor", LOR);
		NAMED_INDEX.put("ixor", IXOR);
		NAMED_INDEX.put("lxor", LXOR);
		NAMED_INDEX.put("iinc", IINC);
		NAMED_INDEX.put("i2l", I2L);
		NAMED_INDEX.put("i2f", I2F);
		NAMED_INDEX.put("i2d", I2D);
		NAMED_INDEX.put("l2i", L2I);
		NAMED_INDEX.put("l2f", L2F);
		NAMED_INDEX.put("l2d", L2D);
		NAMED_INDEX.put("f2i", F2I);
		NAMED_INDEX.put("f2l", F2L);
		NAMED_INDEX.put("f2d", F2D);
		NAMED_INDEX.put("d2i", D2I);
		NAMED_INDEX.put("d2l", D2L);
		NAMED_INDEX.put("d2f", D2F);
		NAMED_INDEX.put("i2b", I2B);
		NAMED_INDEX.put("i2c", I2C);
		NAMED_INDEX.put("i2s", I2S);
		NAMED_INDEX.put("lcmp", LCMP);
		NAMED_INDEX.put("fcmpl", FCMPL);
		NAMED_INDEX.put("fcmpg", FCMPG);
		NAMED_INDEX.put("dcmpl", DCMPL);
		NAMED_INDEX.put("dcmpg", DCMPG);
		NAMED_INDEX.put("ifeq", IFEQ);
		NAMED_INDEX.put("ifne", IFNE);
		NAMED_INDEX.put("iflt", IFLT);
		NAMED_INDEX.put("ifge", IFGE);
		NAMED_INDEX.put("ifgt", IFGT);
		NAMED_INDEX.put("ifle", IFLE);
		NAMED_INDEX.put("if_icmpeq", IF_ICMPEQ);
		NAMED_INDEX.put("if_icmpne", IF_ICMPNE);
		NAMED_INDEX.put("if_icmplt", IF_ICMPLT);
		NAMED_INDEX.put("if_icmpge", IF_ICMPGE);
		NAMED_INDEX.put("if_icmpgt", IF_ICMPGT);
		NAMED_INDEX.put("if_icmple", IF_ICMPLE);
		NAMED_INDEX.put("if_acmpeq", IF_ACMPEQ);
		NAMED_INDEX.put("if_acmpne", IF_ACMPNE);
		NAMED_INDEX.put("goto", GOTO);
		NAMED_INDEX.put("jsr", JSR);
		NAMED_INDEX.put("ret", RET);
		NAMED_INDEX.put("tableswitch", TABLESWITCH);
		NAMED_INDEX.put("lookupswitch", LOOKUPSWITCH);
		NAMED_INDEX.put("ireturn", IRETURN);
		NAMED_INDEX.put("lreturn", LRETURN);
		NAMED_INDEX.put("freturn", FRETURN);
		NAMED_INDEX.put("dreturn", DRETURN);
		NAMED_INDEX.put("areturn", ARETURN);
		NAMED_INDEX.put("return", RETURN);
		NAMED_INDEX.put("getstatic", GETSTATIC);
		NAMED_INDEX.put("putstatic", PUTSTATIC);
		NAMED_INDEX.put("getfield", GETFIELD);
		NAMED_INDEX.put("putfield", PUTFIELD);
		NAMED_INDEX.put("invokevirtual", INVOKEVIRTUAL);
		NAMED_INDEX.put("invokespecial", INVOKESPECIAL);
		NAMED_INDEX.put("invokestatic", INVOKESTATIC);
		NAMED_INDEX.put("invokeinterface", INVOKEINTERFACE);
		NAMED_INDEX.put("invokedynamic", INVOKEDYNAMIC);
		NAMED_INDEX.put("new", NEW);
		NAMED_INDEX.put("newarray", NEWARRAY);
		NAMED_INDEX.put("anewarray", ANEWARRAY);
		NAMED_INDEX.put("arraylength", ARRAYLENGTH);
		NAMED_INDEX.put("athrow", ATHROW);
		NAMED_INDEX.put("checkcast", CHECKCAST);
		NAMED_INDEX.put("instanceof", INSTANCEOF);
		NAMED_INDEX.put("monitorenter", MONITORENTER);
		NAMED_INDEX.put("monitorexit", MONITOREXIT);
		NAMED_INDEX.put("wide", WIDE);
		NAMED_INDEX.put("multianewarray", MULTIANEWARRAY);
		NAMED_INDEX.put("ifnull", IFNULL);
		NAMED_INDEX.put("ifnonnull", IFNONNULL);
		NAMED_INDEX.put("goto_w", GOTO_W);
		NAMED_INDEX.put("jsr_w", JSR_W);
	}

	public static Opcode get(int opcode) {
		return INDEX[opcode];
	}

	public static Opcode get(String opcode) {
		return NAMED_INDEX.get(opcode);
	}

	/**
	 * The name of this Opcode
	 */
	private final String name;
	private final byte   operands;

	Opcode(String name, int operands) {
		this.name = name;
		this.operands = (byte) operands;
	}

	public String getName() {
		return name;
	}

	public byte getOperandCount() {
		return operands;
	}

	@Override
	public String toString() {
		return name;
	}
}
