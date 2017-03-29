import java.lang.String;
import java.util.LinkedList;
/**
 * This class contains methods to :
   * extract element names from the document
   * store the element and their corresponding frequency of
     occurence in the linked list
   * display the element name which has the maximum frequency
     of occurence
   * display all the contents of the linked list
 * @author Nishigandha
 */
class StringOperations
{
	LinkedList<ElementFrequency> list1=new LinkedList<ElementFrequency>();
/**
 * 
 * @return returns a string
 * 
 */
@Override
	public String toString() 
    	{
        	return "StringOperations{" + "list1=" + list1 + '}';
    	}
/**
 * 
 * @param list1 initializes instance variable list1 
 */
	public void setList1(LinkedList<ElementFrequency> list1) 
	{
     		this.list1 = list1;
    	}
/**
 * 
 * @return returns the linked list
 */
    	public LinkedList<ElementFrequency> getList1() 
	{
        	return list1;
    	}
/**
 * 
 * @param str input string from which element name is to be extracted 
 * @return  returns the extracted element name from the input parameter 
   str
 */
	String  extractName(String str)
	{
		try
		{
			if((str.indexOf("xs:element"))== -1)
				return null;
			else
			{
				String substring1="";
				int i=str.indexOf("name=");	
				if(i== -1)
					return null;
				else 
				{
					while(str.charAt(i)!=' '&&str.charAt(i)!='>')
					{
						substring1=substring1+str.charAt(i);
						i++;
					}
					int first=(substring1.indexOf("\"")+1);
					int last=substring1.lastIndexOf("\"");
					String sub;
					sub=substring1.substring(first,last);
					return sub;	
				}
			}
		
		}
		catch(StringIndexOutOfBoundsException e)
		{
			return null;
		}
	}

/**
 * method stores the extracted element name and its count in the linked list
 * @param ef   
 */
	void add(ElementFrequency ef)
	{
		ElementFrequency ef2;
		boolean flag=false;
		if(list1.size()>0)
		{	
			for(int j=0;j<list1.size();j++)
			{
				ef2=list1.get(j);
				if((ef2.elementname).equals(ef.elementname))
				{
					ef2.count=ef2.count+1;
					list1.set(0,ef2);
					flag=true;
					break;
				}
			}
			if(flag==false)
				list1.add(ef);
		}
		else 
		{
			list1.add(ef);
		}	
	}
/**
 *  displays the element name which has the
    maximum frequency of occurence
 * @return void 
 */
	void displayMax()
	{
		ElementFrequency max;
		ElementFrequency ef1;
		 max=list1.get(0);
                for(int i=0;i<list1.size();i++)
                {
                        ef1=list1.get(i);
			if(max.count<ef1.count)
				max=ef1;
                }
	
		 System.out.println("The most frequently occuring element name in this document is :\nElement name "+max.elementname+" Max Count  "+max.count);  
	}

/**
 *  display all the contents of the linked list
 * @return void 
 */
	void display()
	{
		ElementFrequency ef1;
		for(int i=0;i<list1.size();i++)
		{
			ef1=list1.get(i);
			System.out.println(ef1.elementname+" "+ef1.count);
		}
	}
}
