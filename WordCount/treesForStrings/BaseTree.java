/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WordCount.treesForStrings;
import WordCount.util.FileProcessor;
import WordCount.visitors.TreeProcessingVisitorI;
import java.io.PrintStream;
/**
 *
 * @author Nishigandha
 */
public class BaseTree {
        public Node root = null;
	
	public FileProcessor fp=null;

	public BaseTree(FileProcessor fp) {
		super();
		this.fp =fp ;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	public void accept(TreeProcessingVisitorI tree)
	{
		tree.visit(this);
	}
}
