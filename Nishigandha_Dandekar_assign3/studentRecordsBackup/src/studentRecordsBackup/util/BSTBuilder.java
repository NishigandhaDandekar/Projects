package studentRecordsBackup.util;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import studentRecordsBackup.bst.BST;
import studentRecordsBackup.bst.Data;
/**
 * To read the input file and insert the nodes
 * into the Binary Search Tree 
 * @author Nishigandha
 */


public class BSTBuilder {
    FileReader fr;
    public BSTBuilder(FileReader fr1)
    {
        fr=fr1;
    }
     public BSTBuilder()
    {
    }

    public BST original,backup1,backup2;
    /**
     * This method does the following 
       * creates three instances of class BST
       * It reads input from file and calls insert method to 
       *  insert the input value into the original and backup 
       *  binary search trees
     * @throws FileNotFoundException 
     */
    public void insertInBst() throws FileNotFoundException,NumberFormatException,IOException
    {   
     
            FileProcessor fp=new FileProcessor(fr);
            original=new BST();
            backup1=new BST();
            backup2=new BST();
            String str;
            try
            {
                 while((str=fp.readLineFromFile())!=null)
                {
                     if(!str.equals(""))
                    {
                        int n=Integer.parseInt(str);
                        Data d=new Data();
                        d.value=n;
                        original.insert(d,backup1,backup2);
                    }
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid Entry");
                e.printStackTrace();
                System.exit(1);
            }
       
      
    }
    /**
     * This method returns the original BST
     * @return 
     */
    public BST getOriginal() {
        return original;
    }
    /**
     * This method returns the first backup of BST
     * @return 
     */
    public BST getBackup1() {
        return backup1;
    }

    /**
     *  This method returns the second backup of BST
     * @return 
     */
    public BST getBackup2() {
        return backup2;
    }
    /**
     * This method prints the nodes of Original, Backup1 
     * and Backup2 BSTs in inorder manner 
     */
    public void printInorder()
    {
        System.out.println("Original Tree");
        original.inordertraversal();
        System.out.println("Backup 1");
        backup1.inordertraversal();
        System.out.println("Backup 2");
        backup2.inordertraversal();
    }
    /**
     * This method is used to print the sum 
     * of all the nodes in the BST
     * It prints the sum of all nodes in the original tree,
     * backup1 BST and backup2 BST
     * 
     */
    public void printBSum()
    {
        original.calculateSum();
        System.out.println("Sum of all nodes in the original BST is "+original.sum1.value);
        backup1.calculateSum();
        System.out.println("Sum of all nodes in Backup1 BST is "+backup1.sum1.value);
        backup2.calculateSum();
        System.out.println("Sum of all nodes in the Backup2 BST is "+backup2.sum1.value);
    }
}
