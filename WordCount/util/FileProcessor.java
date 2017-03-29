/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCount.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Girish
 */
public class FileProcessor
{
 Scanner sc=null;  
 public PrintStream out=null;
 
     String word=null;
     public void input(String filename)
     {
                        File file=new File(filename);
			try {
				sc=new Scanner(new FileReader(file));
			} catch (FileNotFoundException e) {
				System.err.println("File Not Found");
				System.exit(1);
			}
     }
public String readWord()
 {
                        while(sc.hasNext()){
                        word=sc.next();
                        return word;	
                        }
                        return null;
}
public void output(String fileout)
{
     try {
         out = new PrintStream(new FileOutputStream(fileout));
     } catch (FileNotFoundException ex) {
                                System.err.println("File Not Found");
				System.exit(1);
     }

}
}
