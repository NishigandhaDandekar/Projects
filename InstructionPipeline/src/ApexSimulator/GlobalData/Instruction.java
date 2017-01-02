package ApexSimulator.GlobalData;
import ApexSimulator.Enums.InstructionTypeEnum;

/**
 * Instruction class This contains method which acts on instruction when it is
 * in respective stage
 */
public class Instruction {
	public GlobalDataObject globalDataObject_instance = GlobalDataObject.getInstance();
	public boolean isBranchInstruction;
	public boolean thisJUMPIsReturn;
	public String actualInstruction;

	// public boolean HALTInstructionIn;
	/**
	 * constructor
	 * 
	 * @param id
	 * @param address
	 * @param s
	 */
	public Instruction(int id, int address, String s) {
		instructionId = "I" + id;
		actualInstruction = s;
		instructionAddress = address;
	}

	// instruction Id
	public String instructionId;

	public int instructionAddress;

	// when Instruction is fetch following will be set
	public String byte1;
	public String byte2;
	public String byte3;
	public String byte4;

	/**
	 * updateFetchMethod
	 */
	public void updateFetchMethod() {
		String[] s = actualInstruction.split(" ");
		byte1 = s[0];
		if (s.length > 1)
			byte2 = s[1];
		if (s.length > 2)
			byte3 = s[2];
		if (s.length > 3)
			byte4 = s[3];
	}

	// when instruction is decoded following will be updated
	public InstructionTypeEnum instructionType;
	public boolean isBranchRequired;
	public String src1;
	public String src2;
	public Register src1_register;
	public Register src2_register;
	public Register dest_register;
	public int result;
	public int src2_value;
	public String dest;
	public int literal_value;
	public int target_adderess;
	public boolean flag_branchTaken;
	public boolean StallInPipeline;

	/**
	 * update Delay Unit Method 
	 */
	public void updateDelayUnitMethod() {
		// No Processing here
	}

	/**
	 * update BranchFU Method
	 */
	public void updateBranchFUMethod() {
		switch (byte1) {
		case "BZ":
			if (globalDataObject_instance.ZeroFlagRegister.zeroFlag) {
				// If zero flag is set branch is taken
				target_adderess = instructionAddress + literal_value;
				flag_branchTaken = true;
			} else {
				flag_branchTaken = false;
			}
			break;
		case "BNZ":
			if (!globalDataObject_instance.ZeroFlagRegister.zeroFlag) {
				// If zero flag is not set branch is taken
				target_adderess = instructionAddress + literal_value;
				flag_branchTaken = true;
			} else {
				flag_branchTaken = false;
			}
			break;
		case "JUMP":
			// calculate the value of target address
			if (thisJUMPIsReturn) {
				System.out.println("X is ==>" + globalDataObject_instance.specialX.data);
				target_adderess = globalDataObject_instance.specialX.data;
				thisJUMPIsReturn = false;
			} else {
				// target_adderess = src1_register.data + literal_value / 4;
				target_adderess = globalDataObject_instance.AvailabeRegisterFile.get(src1_register.registerName)
						+ literal_value;

			}
			flag_branchTaken = true;
			break;
		case "BAL":
			// target_adderess = src1_register.data + literal_value;
			target_adderess = globalDataObject_instance.AvailabeRegisterFile.get(src1_register.registerName)
					+ literal_value;
			// set special X Register
			System.out.println("instructionAddress=>" + instructionAddress);
			// globalDataObject_instance.specialX.isBusy = true;
			// globalDataObject_instance.specialX.data = instructionAddress + 1;
			result = instructionAddress + 4;
			globalDataObject_instance.AvailabeRegisterFile.put(globalDataObject_instance.specialX.registerName, result);
			// globalDataObject_instance.AvailabeRegisterFile.get(globalDataObject_instance.specialX.registerName)
			// = true;
			// globalDataObject_instance.specialX.isforwarded = true;

			flag_branchTaken = true;
			break;
		}
	}

	/**
	 * update DecodeRF Method
	 */
	public void updateDecodeRFMethod() {
		switch (byte1) {
		case "ADD": {
			instructionType = InstructionTypeEnum.ADD;
			dest = byte2;
			src1 = byte3;
			src2 = byte4;
			src1_register = globalDataObject_instance.RegisterFile.get(src1);
			src2_register = globalDataObject_instance.RegisterFile.get(src2);
			dest_register = globalDataObject_instance.RegisterFile.get(dest);
			break;
		}
		case "SUB":
			instructionType = InstructionTypeEnum.SUB;
			dest = byte2;
			src1 = byte3;
			src2 = byte4;
			src1_register = globalDataObject_instance.RegisterFile.get(src1);
			src2_register = globalDataObject_instance.RegisterFile.get(src2);
			dest_register = globalDataObject_instance.RegisterFile.get(dest);
			break;
		case "MUL":
			instructionType = InstructionTypeEnum.MUL;
			dest = byte2;
			src1 = byte3;
			src2 = byte4;
			src1_register = globalDataObject_instance.RegisterFile.get(src1);
			src2_register = globalDataObject_instance.RegisterFile.get(src2);
			dest_register = globalDataObject_instance.RegisterFile.get(dest);
			break;
		case "AND": {
			instructionType = InstructionTypeEnum.AND;
			dest = byte2;
			src1 = byte3;
			src2 = byte4;
			src1_register = globalDataObject_instance.RegisterFile.get(src1);
			src2_register = globalDataObject_instance.RegisterFile.get(src2);
			dest_register = globalDataObject_instance.RegisterFile.get(dest);
			break;
		}
		case "OR":
			instructionType = InstructionTypeEnum.OR;
			dest = byte2;
			src1 = byte3;
			src2 = byte4;
			src1_register = globalDataObject_instance.RegisterFile.get(src1);
			src2_register = globalDataObject_instance.RegisterFile.get(src2);
			dest_register = globalDataObject_instance.RegisterFile.get(dest);
			break;
		case "EXOR":
			instructionType = InstructionTypeEnum.EXOR;
			dest = byte2;
			src1 = byte3;
			src2 = byte4;
			src1_register = globalDataObject_instance.RegisterFile.get(src1);
			src2_register = globalDataObject_instance.RegisterFile.get(src2);
			dest_register = globalDataObject_instance.RegisterFile.get(dest);
			break;
		case "MOVC":
			instructionType = InstructionTypeEnum.MOVC;
			dest = byte2;
			literal_value = Integer.parseInt(byte3.replace('#', ' ').trim());
			dest_register = globalDataObject_instance.RegisterFile.get(dest);
			break;

		case "LOAD":
			instructionType = InstructionTypeEnum.LOAD;
			dest = byte2;
			src1 = byte3;
			literal_value = Integer.parseInt(byte4.replace('#', ' ').trim());
			src1_register = globalDataObject_instance.RegisterFile.get(src1);
			dest_register = globalDataObject_instance.RegisterFile.get(dest);
			break;
		case "STORE":
			instructionType = InstructionTypeEnum.STORE;
			src1 = byte2;
			src2 = byte3;
			literal_value = Integer.parseInt(byte4.replace('#', ' ').trim());
			src1_register = globalDataObject_instance.RegisterFile.get(src1);
			src2_register = globalDataObject_instance.RegisterFile.get(src2);
			break;
		case "BZ":
			instructionType = InstructionTypeEnum.BZ;
			literal_value = Integer.parseInt(byte2.replace('#', ' ').trim());
			isBranchInstruction = true;
			break;
		case "BNZ":
			instructionType = InstructionTypeEnum.BNZ;
			literal_value = Integer.parseInt(byte2.replace('#', ' ').trim());
			isBranchInstruction = true;
			break;
		case "BAL":
			instructionType = InstructionTypeEnum.BAL;
			src1 = byte2;
			literal_value = Integer.parseInt(byte3.replace('#', ' ').trim());
			src1_register = globalDataObject_instance.RegisterFile.get(src1);
			if (src1_register.isBusy) {
				if (src1_register.isforwarded)
					StallInPipeline = false;
				else
					StallInPipeline = true;
			} else {
				StallInPipeline = false;
			}
			isBranchInstruction = true;
			break;
		case "JUMP": // assuming JUMP is same as BAL
			instructionType = InstructionTypeEnum.JUMP;
			src1 = byte2;
			literal_value = Integer.parseInt(byte3.replace('#', ' ').trim());
			if (byte2.equals("X")) {
				src1_register = globalDataObject_instance.specialX;
				thisJUMPIsReturn = true;
			} else {
				src1_register = globalDataObject_instance.RegisterFile.get(src1);
				if (src1_register.isBusy) {
					if (src1_register.isforwarded)
						StallInPipeline = false;
					else
						StallInPipeline = true;
				} else {
					StallInPipeline = false;
				}
			}
			isBranchInstruction = true;
			break;
		case "HALT":
			instructionType = InstructionTypeEnum.HALT;
			// globalDataObject_instance.HALTInstructionIn = true;
			// HALTInstructionIn= true;
			break;

		}

	}

	/**
	 * Update Execute1 Method
	 */
	public void UpdateExecute1Method() {
		switch (instructionType) {
		case ADD:
			// result = src1_register.data + src2_register.data;
			result = globalDataObject_instance.AvailabeRegisterFile.get(src1_register.registerName)
					+ globalDataObject_instance.AvailabeRegisterFile.get(src2_register.registerName);
			// if (result == 0)
			// globalDataObject_instance.zeroFlag = true;
			break;
		case SUB:
			// result = src1_register.data - src2_register.data;
			result = globalDataObject_instance.AvailabeRegisterFile.get(src1_register.registerName)
					- globalDataObject_instance.AvailabeRegisterFile.get(src2_register.registerName);
			// globalDataObject_instance.zeroFlag = true;
			break;
		case MUL:
			// result = src1_register.data * src2_register.data;
			result = globalDataObject_instance.AvailabeRegisterFile.get(src1_register.registerName)
					* globalDataObject_instance.AvailabeRegisterFile.get(src2_register.registerName);
			// globalDataObject_instance.zeroFlag = true;
			break;
		case AND:
			// result = src1_register.data & src2_register.data;
			result = globalDataObject_instance.AvailabeRegisterFile.get(src1_register.registerName)
					& globalDataObject_instance.AvailabeRegisterFile.get(src2_register.registerName);
			break;
		case OR:
			// result = src1_register.data | src2_register.data;
			result = globalDataObject_instance.AvailabeRegisterFile.get(src1_register.registerName)
					| globalDataObject_instance.AvailabeRegisterFile.get(src2_register.registerName);
			break;
		case EXOR:
			// result = src1_register.data ^ src2_register.data;
			result = globalDataObject_instance.AvailabeRegisterFile.get(src1_register.registerName)
					^ globalDataObject_instance.AvailabeRegisterFile.get(src2_register.registerName);
			break;
		case MOVC:
			result = literal_value + 0;
			break;
		case LOAD:
			result = globalDataObject_instance.AvailabeRegisterFile.get(src1_register.registerName) + literal_value;
			break;
		case STORE:
			result = globalDataObject_instance.AvailabeRegisterFile.get(src2_register.registerName) + literal_value;
			break;
		case HALT:
			 globalDataObject_instance.haltBeyondALU1 = true;
			break;
		default:
			break;
		}
	}

	/**
	 * Update Execute2 Method
	 */
	public void UpdateExecute2Method() {
		switch (instructionType) {
		case ADD:
			if (result == 0)
				globalDataObject_instance.ZeroFlagRegister.zeroFlag = true;
			else {
				globalDataObject_instance.ZeroFlagRegister.zeroFlag = false;
			}
			// if (from_Int_ALU_2 != null && from_Int_ALU_2.dest_register !=
			// null){
			globalDataObject_instance.AvailabeRegisterFile.put(dest_register.registerName, result);
			// globalDataObject_instance.RegisterFile.get(dest_register.registerName).isforwarded
			// = true;
			// }
			// result = result;
			// check if any forwarding required
			break;
		case SUB:
			if (result == 0)
				globalDataObject_instance.ZeroFlagRegister.zeroFlag = true;
			else {
				globalDataObject_instance.ZeroFlagRegister.zeroFlag = false;
			}
			globalDataObject_instance.AvailabeRegisterFile.put(dest_register.registerName, result);
			// globalDataObject_instance.RegisterFile.get(dest_register.registerName).isforwarded
			// = true;

			// result = result;
			// check if any forwarding required
			break;
		case MUL:
			if (result == 0)
				globalDataObject_instance.ZeroFlagRegister.zeroFlag = true;
			else {
				globalDataObject_instance.ZeroFlagRegister.zeroFlag = false;
			}
			globalDataObject_instance.AvailabeRegisterFile.put(dest_register.registerName, result);
			// globalDataObject_instance.RegisterFile.get(dest_register.registerName).isforwarded
			// = true;

			// result = result;
			// check if any forwarding required
			break;
		case AND:
			if (result == 0)
				globalDataObject_instance.ZeroFlagRegister.zeroFlag = true;
			else {
				globalDataObject_instance.ZeroFlagRegister.zeroFlag = false;
			}
			// result = result;
			// check if any forwarding required
			globalDataObject_instance.AvailabeRegisterFile.put(dest_register.registerName, result);
			// globalDataObject_instance.RegisterFile.get(dest_register.registerName).isforwarded
			// = true;

			break;
		case OR:
			if (result == 0)
				globalDataObject_instance.ZeroFlagRegister.zeroFlag = true;
			else {
				globalDataObject_instance.ZeroFlagRegister.zeroFlag = false;
			}
			// result = result;
			// check if any forwarding required
			globalDataObject_instance.AvailabeRegisterFile.put(dest_register.registerName, result);
			// globalDataObject_instance.RegisterFile.get(dest_register.registerName).isforwarded
			// = true;

			break;
		case EXOR:
			if (result == 0)
				globalDataObject_instance.ZeroFlagRegister.zeroFlag = true;
			else {
				globalDataObject_instance.ZeroFlagRegister.zeroFlag = false;
			}
			// result = result;
			// check if any forwarding required
			globalDataObject_instance.AvailabeRegisterFile.put(dest_register.registerName, result);
			// globalDataObject_instance.RegisterFile.get(dest_register.registerName).isforwarded
			// = true;

			break;
		case MOVC:
			// result = result;
			globalDataObject_instance.AvailabeRegisterFile.put(dest_register.registerName, result);
			// globalDataObject_instance.RegisterFile.get(dest_register.registerName).isforwarded
			// = true;

			break;
		case LOAD:
			// result = result;
			break;
		case STORE:
			// result = result;
			break;
		case HALT:
			break;
		default:
			break;
		}
	}

	
	/**
	 * update Memory Method
	 */
	public void updateMemoryMethod() {
		switch (instructionType) {
		case ADD:
		case SUB:
		case MUL:
		case AND:
		case OR:
		case EXOR:
		case MOVC:
			// No Processing in MEM for MOVC
			break;
		/*
		 * case ADD: // check if any forwarding required break; case SUB: //
		 * check if any forwarding required break; case MUL: // check if any
		 * forwarding required break; case AND: // check if any forwarding
		 * required break; case OR: // check if any forwarding required break;
		 * case EXOR: // check if any forwarding required break; case MOVC: //
		 * No Processing in MEM for MOVC break;
		 */case LOAD:
			result = globalDataObject_instance.MemoryStream.get(result).data;
			// System.out.println("result==> " + this.result);
			// System.out.println("Destination
			// Register"+dest_register.registerName);
			globalDataObject_instance.AvailabeRegisterFile.put(dest_register.registerName, result);
			// globalDataObject_instance.RegisterFile.get(dest_register.registerName).isforwarded
			// = true;
			// result = result;
			// check if any forwarding required
			break;
		case STORE:
			// System.out.println("result==> " + this.result);
			// globalDataObject_instance.MemoryStream.get(result).data =
			// src1_register.data;
			//globalDataObject_instance.MemoryStream.get(result).data = globalDataObject_instance.AvailabeRegisterFile
				//	.get(src1_register.registerName);
			globalDataObject_instance.MemoryStream.get(result).data = new Integer(globalDataObject_instance.AvailabeRegisterFile
			.get(src1_register.registerName));
			// result = result;
			break;
		case JUMP:
		case BZ:
		case BNZ:
		case HALT:
		case BAL:
			// No Proceesing in Mem for JUMP
			break;
		/*
		 * case BZ: // No Proceesing in Mem for BZ break; case BNZ: // No
		 * Proceesing in Mem for BNZ break; case BAL: // No Proceesing in Mem
		 * for BAL break; case HALT: // No Proceesing in Mem for HALT break;
		 */ default:
			break;
		}
	}

	
	/**
	 * update WriteBack Method
	 */
	public void updateWriteBackMethod() {
		switch (instructionType) {
		case ADD:
		case SUB:
		case MUL:
		case AND:
		case OR:
		case EXOR:
		case LOAD:
		case MOVC:
			//dest_register.data = result;
			dest_register.data = result;
			/*
			 * case ADD: dest_register.data = result; // dest_register.isBusy =
			 * false; break; case SUB: dest_register.data = result; //
			 * dest_register.isBusy = false; break; case MUL: dest_register.data
			 * = result; // dest_register.isBusy = false; break; case AND:
			 * dest_register.data = result; // dest_register.isBusy = false;
			 * break; case OR: dest_register.data = result; //
			 * dest_register.isBusy = false; break; case EXOR:
			 * dest_register.data = result; // dest_register.isBusy = false;
			 * break; case MOVC: dest_register.data = result; //
			 * dest_register.isBusy = false; break; case LOAD:
			 * dest_register.data = result; // dest_register.isBusy = false;
			 * break;
			 */
		case STORE:
			// No Processing in WB for STORE
			break;
		case BAL:
			// Special Register X gets updated in WB stage
			globalDataObject_instance.specialX.data = instructionAddress + 4;
			break;
		case JUMP:
		case BZ:
		case BNZ:
			// No Processing in WB for JUMP BZ and BNZ
			break;
		/*
		 * case JUMP: // No Processing in WB for JUMP break; case BZ: // No
		 * Processing in WB for BZ break; case BNZ: // No Processing in WB for
		 * BNZ break;
		 */
		case HALT:
			globalDataObject_instance.stopProcessingAsHALT = true;
			break;
		default:
			break;
		}
	}

}
