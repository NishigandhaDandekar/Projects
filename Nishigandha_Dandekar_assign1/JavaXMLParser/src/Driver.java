import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class performs the following functionality:
  * checks the command line argument entered
  * contains the main() method
  * makes function calls to methods belonging to the FileProcessor and
    StringOperations class
 * @author Nishigandha
 */
class Driver
{
 
 /**
  * 
  * @param args command line arguments
  * @throws IOException 
  */

   public static void main(String[] args) throws IOException
   {
        if(args.length>1)
        {
                System.out.println("Only one command line arguement is permitted !");
                System.exit(1);
        }
	else if(args.length==0)
	{
		System.out.println("A command line arguement should be entered");
                System.exit(1);
	}
        else
	{    		
		try
                {
			StringOperations stringop=new StringOperations();
			FileReader fr= new FileReader(args[0]);
			FileProcessor fp=new FileProcessor(fr);
			String str;
	                while ((str = fp.readLineFromFile()) != null)
                        {
				String str1;
				str1=stringop.extractName(str);
				if(str1!=null)
					stringop.add(new ElementFrequency(str1));			       
                        }
			stringop.displayMax();
                }
                catch(FileNotFoundException filename)
                {
			System.out.println("The file entered is not present");
                        filename.printStackTrace();
                        System.exit(1);
                }
        }
   }
/**
 * 
 * @return returns a string
 */

   public String toString()
   {
        return "Driver{" + '}';
   }
}

/**
 * The ElementFrequency  class defines a user defined type 
   which holds the element name and count, which stores 
   the frequency of occurence of the element.
 * @author Nishigandha
 */
class ElementFrequency
{
	String elementname;
	int count;
/**
 * 
 * @param elementname sets the element name to the instance variable of the class 
 */	
	 public String getElementname() 
	{
        	return elementname;
    	}
/**
 * 
 * @param elementname sets the element name to the instance variable of the class 
 */
    	public void setElementname(String elementname) 
	{
        	this.elementname = elementname;
    	}
/**
 * 
 * @return returns the value of count  
 */
   	public int getCount() 
	{
        	return count;
    	}

/**
 * 
 * @param count sets the value of count to the instance variable of the class
 */
    	public void setCount(int count) 
	{
        	this.count = count;
    	}	


/**
 * 
 * @return returns a string  
 */
	public String toString() 
    	{
        	return "ElementFrequency{" + "elementname=" + elementname + ", count=" + count + '}';
    	}	
/**
 * Constructor initializes the elementname instance variable 
   with the string str and sets the value of count to 1.
 * @param str initializes the elementname instance variable
   with the string str 
 */

	ElementFrequency(String str)
	{
		elementname=str;
		count=1;
	}
}

