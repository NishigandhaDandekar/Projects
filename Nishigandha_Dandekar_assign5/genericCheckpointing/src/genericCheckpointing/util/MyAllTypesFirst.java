package genericCheckpointing.util;

/**
 *
 * @author Nishigandha
 * This class contains methods to  create objects of complex
 * type MyAllTypesFirst 
 */
public class MyAllTypesFirst extends SerializableObject{
    private int myInt;
    private long myLong;
    private String myString;
    private boolean myBool;
    
     public String getMyString() {
        return myString;
    }

    public MyAllTypesFirst() {
    }

    public MyAllTypesFirst(int myInt, long myLong, String myString, boolean myBool) {
        this.myInt = myInt;
        this.myLong = myLong;
        this.myString = myString;
        this.myBool = myBool;
    }
/**
 * Method to set the myString member of the class
 * @param myString 
 */
    public void setMyString(String myString) {
        this.myString = myString;
    }
    /**
     * Method to get the myInt member of the class
     * @return 
     */
    public int getMyInt() {
        return myInt;
    }
    /**
     * Method to set the myInt member of the class
     * @param myInt 
     */
    public void setMyInt(int myInt) {
        this.myInt = myInt;
    }
/**
 * Method to get the myLong member of the class
 * @return 
 */
    public long getMyLong() {
        return myLong;
    }
/**
 * Method to set the myLong member of the class
 * @param myLong 
 */
    public void setMyLong(long myLong) {
        this.myLong = myLong;
    }
/**
 * Method to get the myBool member of the class
 * @return 
 */
    public boolean getMyBool() {
        return myBool;
    }
/**
 * Method to set the myBool member of the class
 * @param myBool 
 */
    public void setMyBool(boolean myBool) {
        this.myBool = myBool;
    }
    /**
     * Method to return the hash value of a object of the class 
     * @return 
     */
    @Override
    public int hashCode()
    {
        int hash=10;
        hash=(hash*5)+this.myInt;
        hash=(hash*5)+(int)this.myLong;
        hash=(hash*5)+getBool(this.myBool);
        hash=(hash*5)+this.myString.length();
        return hash;        
    }
    /**
     * Method to assign an integer value to the boolean types
     * @param b
     * @return 
     */
    public int getBool(boolean b)
    {
        if(b==true)
            return 1;
        else 
            return 2;
    }  
    /**
     * Method to compare two objects
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof MyAllTypesFirst))
        {
            return false;
        }
        MyAllTypesFirst f=(MyAllTypesFirst)o;
        if(f.hashCode()!=this.hashCode())
            return false;
        else 
            return true;
    }
    /**
     * Method to print an overriden version of toString()
     * @return 
     */
    @Override
    public String toString()
    {
        String s="MyAllTypesFirst "+"myInt:"+this.myInt+" myLong:"+this.myLong+" myString:"+this.myString+" myBool:"+this.myBool;
        return s;
    }
    }

  

    
    

