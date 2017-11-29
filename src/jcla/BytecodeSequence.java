package jcla;

import jcla.classfile.Opcode;
import jcla.classfile.attribute.Code;

import java.util.ArrayList;
import java.util.List;

import static jcla.classfile.Opcode.WIDE;

/**
 * @author link
 */
public final class BytecodeSequence {

	private final Instruction[] instructions;

	public BytecodeSequence(Code code) {
		instructions = getInstructions(code);
	}

	public BytecodeSequence(Instruction[] instructions) {
		this.instructions = instructions;
	}

	public Instruction getInstruction(int index) {
		return instructions[index];
	}

	public Instruction[] getInstructions() {
		return instructions;
	}

	public static final class Instruction {

		private final Opcode     opcode;
		private final short[] operands;

		public Instruction(Opcode opcode, short... operands) {
			this.opcode = opcode;
			this.operands = operands;
		}

		public Opcode getOpcode() {
			return opcode;
		}

		public short[] getOperands() {
			return operands;
		}
	}

	private static Instruction[] getInstructions(Code code) {
		short[]           bytecode = code.getBytecode();
		// at minimum three instructions for a single method
		List<Instruction> instructions = new ArrayList<>(3);

		for (int i = 0; i < bytecode.length; i++) {
			Opcode opcode = Opcode.get(bytecode[i]);
			if(opcode.getOperandCount() == 0) {
				instructions.add(new Instruction(opcode));
			} else {
				short[] operands = new short[opcode.getOperandCount() + (opcode == WIDE && bytecode[i + 1] == Opcode.IINC.ordinal() ? 2 : 0)];

				for (int j = 0; j < operands.length; j++) {
					operands[j] = bytecode[i + j + 1];
				}

				instructions.add(new Instruction(opcode, operands));
			}

		}

		return instructions.toArray(new Instruction[0]);
	}

}
