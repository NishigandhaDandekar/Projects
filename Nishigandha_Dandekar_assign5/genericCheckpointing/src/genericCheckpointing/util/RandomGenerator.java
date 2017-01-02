/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.util;

import java.util.Random;

/**
 *
 * @author Nishigandha
 * This class generates random string and character values
 */
public class RandomGenerator {
    String generatestring="abcdefghijklmnopqrstuvwxyz";
    private Random r=new Random();
    public String generateString()
    {
   
        String result="";
        int randomlength=1+r.nextInt(6);
        for(int i=0;i<randomlength;i++)
        {
            result=result+generatestring.charAt(r.nextInt(26));
        }
        return result;
    }
        public char generateChar()
    {
        return generatestring.charAt(r.nextInt(26));
    }
    
}
