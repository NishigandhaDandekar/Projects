package primeThreads.threadMgmt;
import primeThreads.util.Logger;
import primeThreads.util.FileProcessor;
import primeThreads.util.IsPrime;
import primeThreads.store.StdoutDisplayInterface;
import primeThreads.store.StoreData;
/**
 * The class implements the runnable interface 
 * @author Nishigandha
 */
public class WorkerThread implements Runnable
{
      FileProcessor fp;
      IsPrime isprime;
      StdoutDisplayInterface result;
      StoreData resultdata;
      int flag;
      IsPrime p=new IsPrime();
	/**
         * Constructor of class WorkerThread initializes the its 
         * data members with values of the following parameters
         * @param fp
         * @param isprime
         * @param result
         * @throws NumberFormatException 
         */
        public WorkerThread(FileProcessor fp,IsPrime isprime,StdoutDisplayInterface result) throws NumberFormatException
        {
                resultdata=(StoreData) result;
                this.fp=fp;
                this.isprime=isprime;
                this.result=result;
        }
   	   
         @Override
        /**
         * This class overrides the run method. 
         * This is from where a thread begins its execution
         */
	public void run()
	{
           	String str;
	 	while ((str = fp.readLineFromFile()) != null)
                {        
		    if(!str.equals(""))
		    {
			/**
                          * Method checks if the input number 
                          * is prime or not.If it is prime then
                          * it returns the number else it returns -1
                          */
                     	flag=IsPrime.checkPrime(Integer.parseInt(str));
                    } 
		     if(flag!=-1)
                     {
			/**
                          * This method stores the prime number 
                          * into the data structure
                          */  
                    		resultdata.store(flag);
                     }
               }
        }


}

