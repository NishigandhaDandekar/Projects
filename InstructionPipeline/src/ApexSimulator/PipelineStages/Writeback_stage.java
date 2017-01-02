package ApexSimulator.PipelineStages;

import ApexSimulator.GlobalData.GlobalDataObject;
import ApexSimulator.GlobalData.Instruction;

public class Writeback_stage {
	private volatile static Writeback_stage Writeback_stage_instance;

	/**
	 * Private constructor for Singleton class
	 */

	private Writeback_stage() {
	}

	/**
	 * Get instance method
	 * 
	 * @return Writeback_stage
	 */

	public static Writeback_stage getInstance() {
		if (Writeback_stage_instance == null) {
			synchronized (Branch_FU_stage.class) {
				if (Writeback_stage_instance == null) {
					Writeback_stage_instance = new Writeback_stage();
				}
			}
		}
		return Writeback_stage_instance;
	}

	/**
	 * Process method - this method calls updateBranchFUMethod from instruction
	 * class
	 * 
	 * @return Instruction
	 */
	public synchronized Instruction processWriteBackStage() {

		GlobalDataObject globalDataObject_instance = GlobalDataObject.getInstance();
		Instruction ins = globalDataObject_instance.input_latch_writeback;
		if (ins == null) {
			System.out.println(String.format("%-8s  %20s", "WB","--"));
			return null;
		} else {
			ins.updateWriteBackMethod();
			System.out.println(
					String.format("%-8s  %8s %s %-20s", "WB", ins.instructionId, " - ", ins.actualInstruction));
		}
		return ins;
	}
}
