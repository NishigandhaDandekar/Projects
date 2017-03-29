import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
/**
 * returns a line that is read from the input file 
 * @author Nishigandha 
 */
class FileProcessor 
{
	BufferedReader br;
/**
 * Constructor initializes instance variable of the class 
 * @param fr1 initializes instance variable with the parameter  
 */

	FileProcessor(FileReader fr1)
	{
			br=new BufferedReader(fr1);
	}	
/**
 * 
 * @return returns the string read from the file  
 */
	String readLineFromFile()
	{
		try
		{
			return br.readLine();
		}
		catch(IOException e)
		{
			return null; 
		}
	}
/**
 * 
 * @return returns a string 
 */
	public String toString() 
	{
        	return "FileProcessor{" + "br=" + br + '}';
    	}
/**
 * 
 * @return 
 */
    	public BufferedReader getBr() 
	{
        	return br;
    	}
/**
 * 
 * @param br initializes instance variable of this class 
 */
    	public void setBr(BufferedReader br) 
	{
        	this.br = br;
    	}	
	
}
		
