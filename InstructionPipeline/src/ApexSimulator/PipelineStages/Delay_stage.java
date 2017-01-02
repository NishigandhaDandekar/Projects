package ApexSimulator.PipelineStages;

import ApexSimulator.GlobalData.GlobalDataObject;
import ApexSimulator.GlobalData.Instruction;

/**
 * Delay_stage
 */
public class Delay_stage {
	private volatile static Delay_stage Delay_stage_instance;

	/**
	 * Private constructor for Singleton class
	 */
	private Delay_stage() {
	}

	

	/**
	 * Get instance method
	 * 
	 * @return Delay_stage
	 */
	public static Delay_stage getInstance() {
		if (Delay_stage_instance == null) {
			synchronized (Branch_FU_stage.class) {
				if (Delay_stage_instance == null) {
					Delay_stage_instance = new Delay_stage();
				}
			}
		}
		return Delay_stage_instance;
	}

	/**
	 * Process method - this method calls updateDelayUnitMethod from instruction
	 * class
	 *  
	 * @return Instruction
	 */
	public Instruction ProcessDelayStage() {
		GlobalDataObject globalDataObject_instance = GlobalDataObject.getInstance();
		Instruction ins = globalDataObject_instance.input_latch_Delay;
		if (ins == null) {
			System.out.println(String.format("%-8s  %20s", "DELAY","--"));
			return null;
		} else {
			ins.updateDelayUnitMethod();
			System.out.println(
					String.format("%-8s  %8s %s %-20s", "DELAY", ins.instructionId, " - ", ins.actualInstruction));
		}
		return ins;
	}
}
