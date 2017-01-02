package ApexSimulator.GlobalData;
/**
 * Register Class
 * Object of this class represents a Register
 */
public class Register {
		public String registerName;
		public boolean isBusy;
		public Integer data;
		public int instructionId;
		
		public boolean isforwarded;
		public boolean zeroFlag;
		public Register(String RegisterNameIn) {
			registerName = RegisterNameIn;
		}
}

