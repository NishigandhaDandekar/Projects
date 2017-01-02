/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.util;

/**
 *
 * @author Girish
 */
public class MyAllTypesSecond extends SerializableObject {
    private double myDoubleT;
    private float myFloatT;
    private short myShortT;
    private char myCharT;

    public MyAllTypesSecond(double myDoubleT, float myFloatT, short myShortT, char myCharT) {
        this.myDoubleT = myDoubleT;
        this.myFloatT = myFloatT;
        this.myShortT = myShortT;
        this.myCharT = myCharT;
    }
    public double getMyDoubleT() {
        return myDoubleT;
    }
    public void setMyDoubleT(double myDoubleT) {
        this.myDoubleT = myDoubleT;
    }
    public float getMyFloatT() {
        return myFloatT;
    }

    public MyAllTypesSecond() {
    }

    public void setMyFloatT(float myFloatT) {
        this.myFloatT = myFloatT;
    }

    public short getMyShortT() {
        return myShortT;
    }

    public void setMyShortT(short myShortT) {
        this.myShortT = myShortT;
    }

    public char getMyCharT() {
        return myCharT;
    }

    public void setMyCharT(char myCharT) {
        this.myCharT = myCharT;
    }
    /**
     * Method to return the hash value of a object of the class 
     * @return 
     */
    @Override
    public int hashCode()
    {
        int hash=10;
        hash=(hash*5)+(int)this.myDoubleT;
        hash=(hash*5)+(int)this.myFloatT;
        hash=(hash*5)+(int)this.myShortT;
        hash=(hash*5)+(int)this.myCharT;
        return hash;        
    }
    /**
     * Method to compare two objects
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof MyAllTypesSecond))
        {
            return false;
        }
        MyAllTypesSecond f=(MyAllTypesSecond)o;
        if(f.hashCode()!=this.hashCode())
            return false;
        else 
            return true;
    }
    /**
     * Method to print an overriden version of toString()
     * @return 
     */
     public String toString()
    {
        String s="MyAllTypesSecond "+"myDoubleT:"+this.myDoubleT+" myFloatT:"+this.myFloatT+" myShort:"+this.myShortT+" myCharT:"+this.myCharT;
        return s;
    }
}
