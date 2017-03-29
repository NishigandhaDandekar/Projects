/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCount.visitors;
import WordCount.treesForStrings.BaseTree;
import WordCount.treesForStrings.Node;
import java.io.FileOutputStream;
import java.io.PrintStream;
/**
 *
 * @author Girish
 */
public class WordCountVisitor implements TreeProcessingVisitorI{
    int sum=0;
	int sumc=0;
	int max=0;
	
	public void inordertraversal(Node root)
	{
	    if(root!=null)
	    {
	        max=root.storecount[0];
	        inorder(root);
	    }
	}
	public void inorder(Node n)
	{
	    if(n!=null)
	    {
	       inorder(n.left);
	       sum=sum+n.storecount[0];
	       sumc=sumc+(n.storecount[1]*n.storecount[0]);
	       if(n.storecount[0]>max)
	    	   max=n.storecount[0];
	     //  System.out.println("Content:"+n.data+" Count:"+n.storecount[0]+ " Len:"+n.storecount[1]);
	        inorder(n.right);
	    }
	}
	public void inorderMax(Node n,BaseTree tree)
	{
		if(n!=null)
	    {
	       inorderMax(n.left,tree);
	       if(n.storecount[0]==max)
               {
                   tree.fp.out.println("The most frequently used word is : "+n.data);
                   tree.fp.out.println("The frequency of the most frequently used word is: "+n.storecount[0]);
               }
                   inorderMax(n.right,tree);
	    }	
	}
	
	
	public void visit(BaseTree tree)
	{
		//System.out.println("The word count "+tree.root.storecount[1]);
	//	PrintStream out=new PrintStream(new FileOutputStream(
		//          "OutFile.txt"));
		inordertraversal(tree.root);
		tree.fp.out.println("The total number of words is:"+sum);
		inorderMax(tree.root,tree);
		tree.fp.out.println("The number of characters (without whitespaces) is:"+sumc);
	
	}
}
