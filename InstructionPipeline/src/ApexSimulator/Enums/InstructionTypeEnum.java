package ApexSimulator.Enums;

public enum InstructionTypeEnum {
			//Reg to Reg Instruction
			ADD,SUB,MOVC,MUL, 					//4
			OR,AND,	EXOR,     					//3
			//Memory Instruction
			LOAD,STORE,							//2
			//Control Flow Instruction
			BZ, BNZ, JUMP, BAL, HALT			//5
												//total - 14
			
}
