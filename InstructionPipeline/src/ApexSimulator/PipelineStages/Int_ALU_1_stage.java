package ApexSimulator.PipelineStages;

import ApexSimulator.GlobalData.GlobalDataObject;
import ApexSimulator.GlobalData.Instruction;

public class Int_ALU_1_stage {
	private volatile static Int_ALU_1_stage Int_ALU_1_stage_instance;

	/**
	 * Private constructor for Singleton class
	 */
	private Int_ALU_1_stage() {
	}

	/**
	 * Get instance method
	 * 
	 * @return Int_ALU_1_stage
	 */
	public static Int_ALU_1_stage getInstance() {
		if (Int_ALU_1_stage_instance == null) {
			synchronized (Branch_FU_stage.class) {
				if (Int_ALU_1_stage_instance == null) {
					Int_ALU_1_stage_instance = new Int_ALU_1_stage();
				}
			}
		}
		return Int_ALU_1_stage_instance;
	}

	/**
	 * Process method - this method calls UpdateExecute1Method from instruction
	 * class
	 * 
	 * @return Instruction
	 */
	public Instruction processINT_ALU_1_stage() {
		GlobalDataObject globalDataObject_instance = GlobalDataObject.getInstance();
		Instruction ins = globalDataObject_instance.input_latch_execute1;
		if (ins == null) {
			System.out.println(String.format("%-8s  %20s", "ALU_1","--"));
			return null;
		} else {
			ins.UpdateExecute1Method();
			System.out.println(
					String.format("%-8s  %8s %s %-20s", "ALU_1", ins.instructionId, " - ", ins.actualInstruction));
		}
		return ins;
	}

}
