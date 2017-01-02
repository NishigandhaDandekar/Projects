package ApexSimulator.PipelineStages;

import ApexSimulator.GlobalData.GlobalDataObject;
import ApexSimulator.GlobalData.Instruction;

/**
 * Decode_RF_stage Class
 */
public class Decode_RF_stage {
	private volatile static Decode_RF_stage Decode_RF_stage_instance;

	public Object id;
	/**
	 * Private constructor for Singleton class
	 */
	private Decode_RF_stage() {
	}

	/**
	 * Get instance method
	 * 
	 * @return Decode_RF_stage
	 */
	public static Decode_RF_stage getInstance() {
		if (Decode_RF_stage_instance == null) {
			synchronized (Branch_FU_stage.class) {
				if (Decode_RF_stage_instance == null) {
					Decode_RF_stage_instance = new Decode_RF_stage();
				}
			}
		}
		return Decode_RF_stage_instance;
	}

	/**
	 * Process method - this method calls updateDecodeRFMethod from instruction
	 * class
	 * 
	 * @return Instruction
	 */ 
	public synchronized Instruction ProcessDecodeRF() {
		GlobalDataObject globalDataObject_instance = GlobalDataObject.getInstance();
		Instruction ins = globalDataObject_instance.input_latch_Decode;
		if (ins == null) {
			System.out.println(String.format("%-8s %20s", "D/RF","--"));
			return null;
		} else {
			ins.updateDecodeRFMethod();
			System.out.println(
					String.format("%-8s  %8s %s %-20s", "D/RF", ins.instructionId, " - ", ins.actualInstruction));
		}
		return ins;
	}
}