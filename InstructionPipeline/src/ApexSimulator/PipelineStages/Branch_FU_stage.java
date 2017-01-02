package ApexSimulator.PipelineStages;

import ApexSimulator.GlobalData.GlobalDataObject;
import ApexSimulator.GlobalData.Instruction;

/**
 * Branch_FU_stage Class
 *
 */
public class Branch_FU_stage {

	private volatile static Branch_FU_stage Branch_FU_stage_instance;

	/**
	 * Private constructor for Singleton class
	 */
	private Branch_FU_stage() {
	}

	/**
	 * Get instance method
	 * 
	 * @return Branch_FU_stage object
	 */
	public static Branch_FU_stage getInstance() {
		if (Branch_FU_stage_instance == null) {
			synchronized (Branch_FU_stage.class) {
				if (Branch_FU_stage_instance == null) {
					Branch_FU_stage_instance = new Branch_FU_stage();
				}
			}
		}
		return Branch_FU_stage_instance;
	}

	/**
	 * Process method - this method calls updateBranchFUMethod from instruction
	 * class
	 * 
	 * @return Instruction Object
	 */
	public Instruction processBranchFUStage() {
		GlobalDataObject globalDataObject_instance = GlobalDataObject.getInstance();
		Instruction ins = globalDataObject_instance.input_latch_branch;
		if (ins == null) {
			System.out.println(String.format("%-8s %20s", "BRANCH","--"));
			return null;
		} else {
			ins.updateBranchFUMethod();
			System.out.println(
					String.format("%-8s  %8s %s %-20s", "BRANCH", ins.instructionId, " - ", ins.actualInstruction));
		}
		return ins;
	}
}
