package ApexSimulator.PipelineStages;

import ApexSimulator.GlobalData.GlobalDataObject;
import ApexSimulator.GlobalData.Instruction;

/**
 * Fetch_stage class
 */
public class Fetch_stage {
	private volatile static Fetch_stage Fetch_stage_instance;

	/**
	 * Private constructor for Singleton class
	 */

	private Fetch_stage() {
	}

	/**
	 * Get instance method
	 * 
	 * @return Fetch_stage
	 */

	public static Fetch_stage getInstance() {
		if (Fetch_stage_instance == null) {
			synchronized (Branch_FU_stage.class) {
				if (Fetch_stage_instance == null) {
					Fetch_stage_instance = new Fetch_stage();
				}
			}
		}
		return Fetch_stage_instance;
	}

	/**
	 * Process method - this method calls updateFetchMethod from instruction
	 * class
	 * 
	 * @return Instruction
	 */
	public synchronized Instruction ProcessFetchStage(int pc_value) {
		GlobalDataObject globalDataObject = GlobalDataObject.getInstance();
		Instruction ins = globalDataObject.InstructionStorage.get(pc_value);
		if (ins != null) {
			ins.updateFetchMethod();
			System.out.println(
					String.format("%-8s  %8s %s %-20s", "FETCH", ins.instructionId, " - ", ins.actualInstruction));
		}
		
		return ins;
	}
}
