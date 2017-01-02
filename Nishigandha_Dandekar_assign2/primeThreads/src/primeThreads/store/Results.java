package primeThreads.store;
import primeThreads.util.Logger;
import primeThreads.util.FileProcessor;
import primeThreads.util.IsPrime;
import primeThreads.store.StdoutDisplayInterface;
import primeThreads.store.StoreData;
import java.util.Vector;
/**
 * This class is used to store the prime numbers
 * read from the input file into the data structure 
 * and to print the sum of these numbers
 * @author Nishigandha
 */
public class Results implements StdoutDisplayInterface,StoreData
{
	public int sum=0;
	/**
         * Vector is used to store the numbers as it 
         * is a thread safe data structure and 
         * the size of a vector can grow dynamically
         */
	Vector<Integer> v1=new Vector<>();
        @Override
	/**
         * This method stores the prime numbers in the vector
         */
	public void store(int number)
	{
		v1.addElement(number);
		Logger.writeMessage(String.valueOf(number), Logger.DebugLevel.DATA_ENTRY);
                
	}	
        @Override
	/**
         * This method displays the final sum of all the prime numbers  
         */
	public void writeSumToScreen()
	{
		 for(int i=0;i<v1.size();i++)
		{
			Logger.writeMessage(String.valueOf(v1.get(i)), Logger.DebugLevel.DS_CONTENT);
		}
        for(int i=0;i<v1.size();i++)
		{
			sum=sum+v1.get(i);
		}
                
		Logger.writeMessage("The sum of all prime numbers is : "+String.valueOf(sum), Logger.DebugLevel.NO_OUTPUT);
	}
}


