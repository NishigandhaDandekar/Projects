package ApexSimulator.GlobalData;

import java.util.HashMap;

/**
 * GlobalDataObject Class This class acts as a global repositor for entire
 * pipeline
 *
 */
public class GlobalDataObject {
	// unique instance of GlobalDataObject
	private volatile static GlobalDataObject GlobalDataObject_instance;
	// Register file and Memory Stream nad initial storage for instruction
	public HashMap<Integer, Memory> MemoryStream = null;
	public HashMap<Integer, Instruction> InstructionStorage = null;
	public HashMap<String, Register> RegisterFile = null;
	// Registerfile which will act as forwarded registed
	public HashMap<String, Integer> AvailabeRegisterFile = new HashMap<>();

	// input latches
	public Instruction input_latch_Decode = null;
	public Instruction input_latch_execute1 = null;
	public Instruction input_latch_execute2 = null;
	public Instruction input_latch_branch = null;
	public Instruction input_latch_Delay = null;
	public Instruction input_latch_memory = null;
	public Instruction input_latch_writeback = null;
	// special X register
	public Register specialX;
	public Register ZeroFlagRegister;
	// public boolean zeroFlag;
	public boolean stopProcessingAsHALT;
	public boolean StallInPipelineGlobal;

	public boolean haltBeyondALU1;

	// public boolean flag_branchTaken;
	/**
	 * Private constructor Initiales hashmap's
	 */
	private GlobalDataObject() {
		MemoryStream = new HashMap<>();
		InstructionStorage = new HashMap<>();
		RegisterFile = new HashMap<>(16);
		initializeRegisterFile();
		initializeMemoryStream();
	}

	/**
	 * initialize Memory Stream
	 */
	private void initializeMemoryStream() {
		for (int i = 0; i < 4000; i++) {
			MemoryStream.put(i, new Memory());
		}
	}

	/**
	 * getInstance method
	 * 
	 * @return GlobalDataObject
	 */
	public static GlobalDataObject getInstance() {
		if (GlobalDataObject_instance == null) {
			synchronized (GlobalDataObject.class) {
				if (GlobalDataObject_instance == null) {
					GlobalDataObject_instance = new GlobalDataObject();
				}
			}
		}
		return GlobalDataObject_instance;
	}
/**
 * Set GlobalDataObject_instance to null
 */
	public static void setNull() {
		GlobalDataObject_instance = null;
	}

	/**
	 * initialize Register File
	 */
	private void initializeRegisterFile() {
		for (int i = 0; i < 16; i++) {
			RegisterFile.put("R" + i, new Register("R" + i));
			AvailabeRegisterFile.put("R" + i, null);
		}
		specialX = new Register("X");
		ZeroFlagRegister = new Register("Z");
	}

	/**
	 * add To Instruction Storage
	 * 
	 * @param index
	 * @param instructionIn
	 */
	public void addToInstructionStorage(int index, Instruction instructionIn) {
		if (!InstructionStorage.containsKey(index))
			InstructionStorage.put(index, instructionIn);
	}

	/**
	 * print Architectural Register
	 */
	public void print_ArchitecturalRegister() {
		System.out.println("\n**********************Content of Architectural Register**********************\n");
		for (int i = 0; i < 16; i++) {
			//if (RegisterFile.get("R" + i).data == null)
			//	continue;
			String s = "R" + i + " => " + RegisterFile.get("R" + i).data + " ";
			System.out.println(String.format("%-10s", s));
		}
		//if (specialX.data != null)
		System.out.print("X => " + specialX.data + " ");
		//System.out.println("\n**********************Content of Architectural Register**********************\n");
	}

	/**
	 * print Memory Stream
	 */
	public void print_MemoryStream() {
		System.out.println("\n*************************Content of Memory Locations**************************\n");
		for (int i = 0; i < 4000; i++) {
			if (MemoryStream.get(i).data == null)
				continue;
			String s = "Mem[" + i + "]" + " => " + MemoryStream.get(i).data;
			System.out.println(String.format("%-10s", s));
		}
		//System.out.println("\n*************************Content of Memory Locations*************************\n");
	}

	/**
	 * print All Instruction
	 */
	public void printAllInstruction() {
		for (int i = 0; i < InstructionStorage.size() * 4; i += 4) {
			System.out.println(i + 4000 + " - " + InstructionStorage.get(i + 4000).instructionId + "  "
					+ InstructionStorage.get(i + 4000).actualInstruction);
		}

	}
}
