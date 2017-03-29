/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCount.visitors;

import WordCount.treesForStrings.BaseTree;
import WordCount.treesForStrings.Node;
import WordCount.util.FileProcessor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Girish
 */
public class PopulateTreeVisitor implements TreeProcessingVisitorI{
    Node root=null;
	Scanner sc=null;
	String word=null;
	public void visit(BaseTree tree)
	{
	
   
			while((word=tree.fp.readWord())!=null)
			{
                                // System.out.println("word "+word);
				this.insertIntree(word,tree);
				word="";
			}
	}
	public void insertInNode(Node newnode,String data)
	{
		 if(newnode.data.compareTo(data)>0)
	     {
	         if(newnode.left==null)
	         {
	             Node n=new Node(data);
	             newnode.left=n;
	             n.parent=newnode;
	             n.storecount[0]++;
	             n.storecount[1]=data.length();
	         }
	         else
	             insertInNode(newnode.left,data);
	     }
	    if(newnode.data.compareTo(data)<0)
	     {
	    	if(newnode.right==null)
	        {
	            Node n=new Node(data);
	            newnode.right=n;
	            n.parent=newnode;
	            n.storecount[0]++;
	            n.storecount[1]=data.length();
	        }
	        else
	            insertInNode(newnode.right,data);  
			
	     }
	    if(newnode.data.compareTo(data)==0)
	    {
	    	newnode.storecount[0]++;
	    }
	}
	public void insertIntree(String value,BaseTree tree)
		{
		if(this.root==null)
		{
			//this.root=tree.root;
			this.root=new Node(value);
			tree.root=this.root;
			this.root.storecount[0]++;
			this.root.storecount[1]=value.length();
		}
		else
			insertInNode(this.root,value);
		}
}
