package primeThreads.threadMgmt;
import primeThreads.util.Logger;
import primeThreads.util.FileProcessor;
import primeThreads.util.IsPrime;
import primeThreads.store.StdoutDisplayInterface;
import primeThreads.store.StoreData;
/**
 * This class is used to create the number of threads as 
 * specified in the command line arguments and it 
 * also invokes the start() method of the threads
 * @author Nishigandha
 */
public class CreateWorkers
{
	public FileProcessor fp;
	public IsPrime isprime;
	public StdoutDisplayInterface result;
/**
 * Constructor initializes its instance variables with 
 * values as received by the constructor
 * @param fp
 * @param prime
 * @param result 
 */    

    public CreateWorkers(FileProcessor fp, IsPrime prime, StdoutDisplayInterface result) {
         	this.fp=fp;
		this.isprime=isprime;
		this.result=result;
    }
/**
     * This method takes the thread number as entered by the user as
     * input and creates the specified number of threads
     * @param threadnumber
     * @throws InterruptedException 
     */
	public void startWorkers(int threadnumber) throws InterruptedException
	{
                 
                    WorkerThread wt[] =new WorkerThread[threadnumber];
                    Thread t[]=new Thread[threadnumber];
                    for(int i=0;i<threadnumber;i++)
                    {   
                        Logger.writeMessage("WorkerThread(fp,isprime,result) Constructor ",Logger.DebugLevel.CONSTRUCTOR);
                        wt[i] =new WorkerThread(fp,isprime,result);
                        Logger.writeMessage("Thread(wt) Constructor ", Logger.DebugLevel.CONSTRUCTOR);
                        t[i]=new Thread(wt[i]);
                        Logger.writeMessage("Run method called ", Logger.DebugLevel.RUN_METHOD);
                        t[i].start();  
                        
                    }
                    for(int i=0;i<threadnumber;i++)
                    {     
                         t[i].join();
                    }
        }
}

