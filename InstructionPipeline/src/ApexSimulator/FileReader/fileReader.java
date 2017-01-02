package ApexSimulator.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import ApexSimulator.GlobalData.GlobalDataObject;
import ApexSimulator.GlobalData.Instruction;

public class fileReader {
	  private Scanner sc;

	public void ReadInstructionFromInputFile(GlobalDataObject globalDataObject,String filename) throws FileNotFoundException{
		  FileReader fin = new FileReader(new File(filename));
			sc = new Scanner(fin);
			int counter = 4000;
			int i = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				globalDataObject.InstructionStorage.put(counter, new Instruction(i, counter, line.replace(",", "")));
				counter+=4;
				i++;
			}

	  }
	    
}