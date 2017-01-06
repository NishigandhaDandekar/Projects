package primeThreads.driver;
import primeThreads.util.FileProcessor;
import primeThreads.util.Logger;
import primeThreads.util.IsPrime;
import primeThreads.store.StdoutDisplayInterface;
import primeThreads.store.StoreData;
import primeThreads.threadMgmt.CreateWorkers;
import primeThreads.threadMgmt.WorkerThread;
import primeThreads.store.Results;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
/**
 * This class validates the command line arguments 
 * and passes the necessary arguments to the
 * CreateWorkers class
 * It also prints the final sum of all 
 * input prime numbers read from the file
 * @author Nishigandha
 */
public class Driver
{
	public static void main(String args[])throws IOException,NumberFormatException,InterruptedException
	{
		String str;
		boolean flag=false;
		if(args.length!=3)
		{
			System.out.println("Invalid number of arguements");
			System.exit(1);
		}
		try{
		if (Integer.parseInt(args[1].trim())<0||Integer.parseInt(args[1].trim())>5)
		{
			System.out.println("Number of threads must be between 1 and 5");
			flag=true;
		}
		if (Integer.parseInt(args[2].trim())<0||Integer.parseInt(args[2].trim())>4)
                {
                        System.out.println("Debug value must be between 0  and 4");
			flag=true;
		}
		}
		 catch(NumberFormatException e)
                {
                        System.out.println("Please enter only numeric values");
                        e.printStackTrace();
                        System.exit(1);
                }

		if(flag==true)
		{
			System.exit(1);
		}
		try
		{
			Logger.setDebugValue(Integer.parseInt(args[2]));
			FileReader fr=new FileReader(args[0]);
			Logger.writeMessage("File Reader Constructor ", Logger.DebugLevel.CONSTRUCTOR); 
			FileProcessor fp=new FileProcessor(fr);
		        Logger.writeMessage("File Processor Constructor ", Logger.DebugLevel.CONSTRUCTOR);
			/**
                          * creates an object of the isPrime 
                          * class which is used find the 
                          * prime numbers in the input file
                          */
			IsPrime isprime=new IsPrime();
			Logger.writeMessage("isPrime Constructor ", Logger.DebugLevel.CONSTRUCTOR);
			/**
                         * The Results class prints the sum of all prime 
                         * numbers in the input file and it implements 
                         * the StdoutDisplayInterface
                         */
			StdoutDisplayInterface result=new Results();
			Logger.writeMessage("Results Constructor ", Logger.DebugLevel.CONSTRUCTOR);
			/**
                         * This class is responsible for creating 
                         * the number of threads as specified by 
                         * the user and it invokes the start()
                         */
			CreateWorkers cw=new CreateWorkers(fp,isprime,result);
			Logger.writeMessage("CreateWorkers(fp,isprime,result) Constructor ", Logger.DebugLevel.CONSTRUCTOR);
			cw.startWorkers(Integer.parseInt(args[1]));
			/**
                         * This method belonging to the Results class 
                         * is used to display the final sum of the 
                         * numbers in the input file  
                         */
			result.writeSumToScreen();
		}
		catch(FileNotFoundException filename)
                {
                        System.out.println("The file entered is not present");
                        filename.printStackTrace();
                        System.exit(1);
                }
		catch(NumberFormatException e)
		{
			System.out.println("Please enter only numeric values");
                        e.printStackTrace();
                        System.exit(1);
		}
	}
}
