package ApexSimulator.Driver;

import java.io.FileNotFoundException;
import java.util.Scanner;
import ApexSimulator.Enums.InstructionTypeEnum;
import ApexSimulator.FileReader.fileReader;
import ApexSimulator.GlobalData.GlobalDataObject;
import ApexSimulator.GlobalData.Instruction;
import ApexSimulator.PipelineStages.*;
/**
 * driver Class
 * 
 */
public class driver {
	public Fetch_stage fetch_stage_Object;
	public Decode_RF_stage decodeRF_stage_Object;
	public Int_ALU_1_stage IntALU1_stage_Object;
	public Int_ALU_2_stage IntALU2_stage_Object;
	public Branch_FU_stage branchFU_stage_Object;
	public Delay_stage delay_stage_Objct;
	public Mem_stage mem_stage_Object;
	public Writeback_stage writeBack_stage_Object;

	public Instruction from_writeback = null;
	public Instruction from_memory = null;
	public Instruction from_Int_ALU_2 = null;
	public Instruction from_Int_ALU_1 = null;
	public Instruction from_branchFU = null;
	public Instruction from_Delay = null;
	public Instruction from_decode = null;
	public Instruction from_fetch = null;
	public static GlobalDataObject globalDataObject = GlobalDataObject.getInstance();
	public static int PC_value;// = 4000;
	private static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		String filename = args[0];
		driver d = null;
		while (true) {
			System.out.println("\nEnter Command\n");
			String commandIn = scanner.nextLine();

			String[] cmd = commandIn.split("\\s+");
			switch (cmd[0].toLowerCase()) {
			case "initialize":
				d = new driver();
				GlobalDataObject.setNull();
				PC_value = 4000;
				globalDataObject = GlobalDataObject.getInstance();
				fileReader reader = new fileReader();
				try {
					reader.ReadInstructionFromInputFile(globalDataObject, filename);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.exit(0);
				}
				//globalDataObject.printAllInstruction();
				System.out.println("Instructions from "+filename);
				System.out.println("-----------Simulator Initialized---------------");
				break;
			case "simulate":
				d.createAllStageObjects();
				if(cmd.length < 2 || cmd.length > 2){
					System.err.println("Invalid simulate command\n");
					continue;
				}
				int number_of_cycles = Integer.parseInt(cmd[1]);
				for (int i = 1; i <= number_of_cycles; i++) {
					System.out.println(String.format("%-10s  %d", "Cycle:", i));
					// output latches
					d.setAllfromLatchesNULL();

					d.from_writeback = d.writeBack_stage_Object.processWriteBackStage();
					d.from_memory = d.mem_stage_Object.processMemoryStage();
					d.from_Int_ALU_2 = d.IntALU2_stage_Object.processINT_ALU_2_stage();
					d.from_Int_ALU_1 = d.IntALU1_stage_Object.processINT_ALU_1_stage();
					d.from_Delay = d.delay_stage_Objct.ProcessDelayStage();
					d.from_branchFU = d.branchFU_stage_Object.processBranchFUStage();
					d.from_decode = d.decodeRF_stage_Object.ProcessDecodeRF();
					if(!globalDataObject.haltBeyondALU1)
						d.from_fetch = d.fetch_stage_Object.ProcessFetchStage(PC_value);
					else{
						System.out.println(String.format("%-8s  %20s", "FETCH","--"));
					}

					if(globalDataObject.haltBeyondALU1){
						d.from_decode = null;
						d.from_fetch = null;
					}
					
					// update All Flags
					d.updateAllFlagsAtEndOfCycle();

					// Update All latches
					d.updateAllLatchesAtEndOfCycle();

					if (globalDataObject.stopProcessingAsHALT)
						break;

					System.out.println("----------------------End of Cycle--------------------");
					//globalDataObject.print_ArchitecturalRegister();
					//globalDataObject.print_MemoryStream();
				}
				
				break;
			case "display":
				globalDataObject.print_ArchitecturalRegister();
				globalDataObject.print_MemoryStream();
				break;
			case "exit":
				System.out.println("----------------------End of simulator--------------------");
				System.exit(0);
			default:
				break;
			}
		}

	}

	private void setAllfromLatchesNULL() {
		from_writeback = null;
		from_memory = null;
		from_Int_ALU_2 = null;
		from_Int_ALU_1 = null;
		from_branchFU = null;
		from_Delay = null;
		from_decode = null;
		from_fetch = null;
	}

	private void updateAllLatchesAtEndOfCycle() {
		// **********latch updates
		// check is global stall true
		if (from_decode != null && from_decode.StallInPipeline)
			globalDataObject.StallInPipelineGlobal = true;
		else
			globalDataObject.StallInPipelineGlobal = false;

		// no input latch for fetch

		if (from_branchFU != null && from_branchFU.flag_branchTaken) {
			// Flush
			PC_value = from_branchFU.target_adderess;
			if (from_decode != null) {
				if (from_decode.src1_register != null) {
					from_decode.src1_register.isBusy = false;
					from_decode.src1_register.isforwarded = false;
				}
				if (from_decode.src2_register != null) {
					from_decode.src2_register.isBusy = false;
					from_decode.src2_register.isforwarded = false;
				}
				if (from_decode.dest_register != null) {
					from_decode.dest_register.isBusy = false;
					from_decode.dest_register.isforwarded = false;
				}
			}
			from_decode = null;
			globalDataObject.input_latch_branch = null;
			globalDataObject.input_latch_execute1 = null;
			from_fetch = null;
		} else {
			if (!globalDataObject.StallInPipelineGlobal) {
				PC_value += 4;
			}
		}

		if (!globalDataObject.StallInPipelineGlobal)
			globalDataObject.input_latch_Decode = from_fetch;

		if (from_decode != null && from_decode.isBranchInstruction) {
			globalDataObject.input_latch_execute1 = null;
			if (!globalDataObject.StallInPipelineGlobal)
				globalDataObject.input_latch_branch = from_decode;
		} else {
			globalDataObject.input_latch_branch = null;
			if (!globalDataObject.StallInPipelineGlobal)
				globalDataObject.input_latch_execute1 = from_decode;
			else
				globalDataObject.input_latch_execute1 = null;
		}
		globalDataObject.input_latch_execute2 = from_Int_ALU_1;

		globalDataObject.input_latch_Delay = from_branchFU;

		if (from_Delay != null)
			globalDataObject.input_latch_memory = from_Delay;
		else
			globalDataObject.input_latch_memory = from_Int_ALU_2;
		globalDataObject.input_latch_writeback = from_memory;

	}

	private void updateAllFlagsAtEndOfCycle() {
		// All updates at the end of clock cycle
		if (from_writeback != null && from_writeback.dest_register != null)
			from_writeback.dest_register.isBusy = false;

		if (from_Int_ALU_2 != null && from_Int_ALU_2.dest_register != null
				&& from_Int_ALU_2.instructionType != InstructionTypeEnum.LOAD)
			globalDataObject.RegisterFile.get(from_Int_ALU_2.dest_register.registerName).isforwarded = true;

		// Destination of ALU1 is forwarded to false
		if (from_Int_ALU_1 != null && from_Int_ALU_1.dest_register != null) {
			from_Int_ALU_1.dest_register.isforwarded = false;
		}
		// destination of MOVC busy and forwarded
		if (from_decode != null && from_decode.dest_register != null
				&& from_decode.instructionType == InstructionTypeEnum.MOVC) {
			from_decode.dest_register.isBusy = true;
			from_decode.dest_register.isforwarded = false;
		}

		// Special X busy and forwarded
		if (from_branchFU != null && from_branchFU.instructionType == InstructionTypeEnum.BAL) {
			globalDataObject.specialX.isBusy = true;
			globalDataObject.specialX.isforwarded = true;
		}

		//// Stall when any of the source of the Arithmetic is not forwarded
		if (from_decode != null && (from_decode.instructionType == InstructionTypeEnum.ADD
				|| from_decode.instructionType == InstructionTypeEnum.SUB
				|| from_decode.instructionType == InstructionTypeEnum.MUL
				|| from_decode.instructionType == InstructionTypeEnum.OR
				|| from_decode.instructionType == InstructionTypeEnum.EXOR
				|| from_decode.instructionType == InstructionTypeEnum.AND))

		{
			if (from_decode.src1_register.isBusy || from_decode.src2_register.isBusy) {
				if (from_decode.src1_register.isforwarded && from_decode.src2_register.isforwarded) {
					{
						from_decode.StallInPipeline = false;
						from_decode.dest_register.isBusy = true;
					}
				} else
					from_decode.StallInPipeline = true;
			} else {
				from_decode.dest_register.isBusy = true;
				from_decode.dest_register.isforwarded = false;
				from_decode.StallInPipeline = false;
			}
		}

		// Stall when source of the LOAD is not forwarded
		if (from_decode != null && from_decode.dest_register != null
				&& from_decode.instructionType == InstructionTypeEnum.LOAD) {
			if ((from_decode.src1_register.isBusy)) {
				if (from_decode.src1_register.isforwarded) {
					from_decode.StallInPipeline = false;
					from_decode.dest_register.isBusy = true;
				} else
					from_decode.StallInPipeline = true;
			} else {
				from_decode.dest_register.isBusy = true;
				from_decode.dest_register.isforwarded = false;
				from_decode.StallInPipeline = false;
			}
		}

		if (from_memory != null && from_memory.dest_register != null
				&& from_memory.instructionType == InstructionTypeEnum.LOAD)
			globalDataObject.RegisterFile.get(from_memory.dest_register.registerName).isforwarded = true;

		// Stall when any of the STORE source is not forwarded
		if (from_decode != null && from_decode.instructionType == InstructionTypeEnum.STORE) {
			if (from_decode.src1_register.isBusy || from_decode.src2_register.isBusy) {
				if (from_decode.src1_register.isforwarded && from_decode.src2_register.isforwarded)
					from_decode.StallInPipeline = false;
				else
					from_decode.StallInPipeline = true;
			} else {
				from_decode.StallInPipeline = false;
			}
		}

		// Stall Branch instruction to D/RF if instruction in ALU1 can update Z
		// flag register.
		if (from_decode != null && from_decode.isBranchInstruction) {
			if (from_Int_ALU_1 != null && (from_Int_ALU_1.instructionType == InstructionTypeEnum.ADD
					|| from_Int_ALU_1.instructionType == InstructionTypeEnum.SUB
					|| from_Int_ALU_1.instructionType == InstructionTypeEnum.MUL
					|| from_Int_ALU_1.instructionType == InstructionTypeEnum.OR
					|| from_Int_ALU_1.instructionType == InstructionTypeEnum.EXOR
					|| from_Int_ALU_1.instructionType == InstructionTypeEnum.AND))
				from_decode.StallInPipeline = true;
			else {
				from_decode.StallInPipeline = false;
			}
		}

	}

	private void createAllStageObjects() {
		fetch_stage_Object = Fetch_stage.getInstance();
		decodeRF_stage_Object = Decode_RF_stage.getInstance();
		IntALU1_stage_Object = Int_ALU_1_stage.getInstance();
		IntALU2_stage_Object = Int_ALU_2_stage.getInstance();
		branchFU_stage_Object = Branch_FU_stage.getInstance();
		delay_stage_Objct = Delay_stage.getInstance();
		mem_stage_Object = Mem_stage.getInstance();
		writeBack_stage_Object = Writeback_stage.getInstance();

	}
}
