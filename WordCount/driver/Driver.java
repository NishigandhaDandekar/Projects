/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCount.driver;

import WordCount.treesForStrings.BaseTree;
import WordCount.util.FileProcessor;
import WordCount.visitors.PopulateTreeVisitor;
import WordCount.visitors.TreeProcessingVisitorI;
import WordCount.visitors.WordCountVisitor;
import WordCount.visitors.grepVisitor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author Nishigandha
 */
public class Driver {
    public static void main(String[] args) {
        FileProcessor fp1=new FileProcessor();
        fp1.output("C:\\Users\\Girish\\Documents\\NetBeansProjects\\Visitor\\src\\WordCount\\util\\OutFile.txt");
        fp1.input("C:\\Users\\Girish\\Documents\\NetBeansProjects\\Visitor\\src\\WordCount\\util\\input.txt");
        long startTime = System.currentTimeMillis();
        BaseTree t=new BaseTree(fp1);
        for(int i=0;i<5;i++)
        {
            TreeProcessingVisitorI v=new PopulateTreeVisitor();
            TreeProcessingVisitorI gv=new grepVisitor("is");
            TreeProcessingVisitorI vw=new WordCountVisitor();
            
            t.accept(v);
            t.accept(vw);
            t.accept(gv);
            fp1.out.println();
        }
        long finishTime = System.currentTimeMillis();
        long total_time= (finishTime-startTime)/5;
        System.out.println("The time for each iteration is: "+total_time+" ms");
        fp1.out.close();
		   
	}
}
