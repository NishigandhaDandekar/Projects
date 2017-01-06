package primeThreads.util;
/**
 * This class is used to find if the number is prime or not 
 * @author Nishigandha
 */
public class IsPrime
{
	public IsPrime()
	{}
 /**
   * This method checks if the parameter number 
   * is prime or not ,
   * If it is prime then it returns the
   * number else it returns -1
   * @param number
   * @return 
   */
	public static int checkPrime(int number)
	{
		if(number%2==0&&number!=1)
			return -1;
		else 
			return number;
	}
}
	

