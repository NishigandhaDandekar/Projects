/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCount.visitors;

import WordCount.treesForStrings.BaseTree;
import WordCount.treesForStrings.Node;

/**
 *
 * @author Girish
 */
public class grepVisitor implements TreeProcessingVisitorI{
    	String searchStr=null;
	
	
public grepVisitor(String searchStr) {
		this.searchStr = searchStr;
	}
public void search(Node n,String data,BaseTree tree)
{
	 if(n.data.compareTo(data)>0)
     {
         if(n.left==null)
         {
             tree.fp.out.println("Search String could not be found");
         }
         else
             search(n.left,data,tree);
     }
    if(n.data.compareTo(data)<0)
     {
    	if(n.right==null)
        {
    		tree.fp.out.println("Search String could not be found"); 
        }
        else
            search(n.right,data,tree);  
		
     }
    if(n.data.compareTo(data)==0)
    {
    	tree.fp.out.println("The word <"+n.data +"> occurs the following times : "+n.storecount[0]);
    }
}
public void searchNode(Node root,String searchstring,BaseTree tree)
{
	if(root==null)
		tree.fp.out.println("Tree empty");
	else
	search(root,searchstring,tree);
}
@Override
public void visit(BaseTree tree) {
	// TODO Auto-generated method stub
	searchNode(tree.root,searchStr,tree);
	
}
}
