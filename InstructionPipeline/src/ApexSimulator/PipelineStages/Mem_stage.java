package ApexSimulator.PipelineStages;

import ApexSimulator.GlobalData.GlobalDataObject;
import ApexSimulator.GlobalData.Instruction;

public class Mem_stage {
	private volatile static Mem_stage Mem_stage_instance;

	/**
	 * Private constructor for Singleton class
	 */

	private Mem_stage() {
	}

	/**
	 * Get instance method
	 * 
	 * @return Mem_stage
	 */
	public static Mem_stage getInstance() {
		if (Mem_stage_instance == null) {
			synchronized (Branch_FU_stage.class) {
				if (Mem_stage_instance == null) {
					Mem_stage_instance = new Mem_stage();
				}
			}
		}
		return Mem_stage_instance;
	}

	/**
	 * Process method - this method calls updateMemoryMethod from instruction
	 * class
	 * 
	 * @return Instruction
	 */
	public synchronized Instruction processMemoryStage() {
		GlobalDataObject globalDataObject_instance = GlobalDataObject.getInstance();
		Instruction ins = globalDataObject_instance.input_latch_memory;
		if (ins == null) {
			System.out.println(String.format("%-8s  %20s", "MEM","--"));
			return null;
		} else {
			ins.updateMemoryMethod();
			System.out.println(
					String.format("%-8s  %8s %s %-20s", "MEM", ins.instructionId, " - ", ins.actualInstruction));
		}
		return ins;
	}
}
