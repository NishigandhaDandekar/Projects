/**
 * This class inserts nodes into the binary search tree.
 * It is has a method for doing inorder traversal through the tree.
 * It also calculates the sum of all the nodes in the BST
 */
package studentRecordsBackup.bst;
import studentRecordsBackup.util.EvenFilterImpl;
import studentRecordsBackup.util.OddFilterImpl;
import studentRecordsBackup.util.OddEvenFilterI;

/**
 *
 * @author Nishigandha
 */
public class BST {
    public Node root;
    public Data sum1=new Data();
   
    OddEvenFilterI f1=new EvenFilterImpl();
    OddEvenFilterI f2=new OddFilterImpl();
    /**
     * This function is used to insert nodes into the BST
     * @param data
     * @param backup1
     * @param backup2 
     */
    public void insert(Data data,BST backup1,BST backup2) 
    {
         if(root==null)
        {
            root=new Node();
            backup1.root=new Node();
            backup2.root=new Node();
            ObserverI o1=backup1.root;
            try
            {
                root.registerObserver(o1,f1);
            }
            catch(StackOverflowError e)
            {
                System.out.println("Input file very large");
                e.printStackTrace();
                System.exit(1);
            }
            ObserverI o2=backup2.root;
             try
            {
                root.registerObserver(o2,f2);
            }
            catch(StackOverflowError e)
            {
                System.out.println("Input file very large");     
                e.printStackTrace();
                System.exit(1);
            }
            root.bnumber=new Data();
            backup1.root.bnumber=new Data();
            backup2.root.bnumber=new Data();
            root.bnumber.value=data.value;  
            backup1.root.bnumber.value=data.value;
            backup2.root.bnumber.value=data.value;
            
        }    
         else 
         {
              try
            {
                insertnode(root,backup1.root,backup2.root,data);
            }
            catch(StackOverflowError e)
            {
                System.out.println("Input file very large");
                e.printStackTrace();
                System.exit(1);
            }
         }
            
        
    }    
    /**
     * This method is used by insert() to insert nodes into the BST
     * @param newnode
     * @param backup1
     * @param backup2
     * @param data 
     */
    public void insertnode(Node newnode,Node backup1,Node backup2,Data data)
    {
            if(newnode.bnumber.value>data.value)
            {
                if(newnode.left==null)
                {
                    Node n=new Node();
                    newnode.left=n;
                    n.bnumber=new Data();
                    n.bnumber.value=data.value;
                    newnode.maxflag=true;
                    n.parent=newnode; 
                    Node n1=new Node();
                    Node n2=new Node();
                     try
                    {
                        n.registerObserver(n1,f1);
                    }
                    catch(StackOverflowError e)
                    {
                        System.out.println("Input file very large");
                        e.printStackTrace();
                         System.exit(1);
                    }
                     try
                    {
                        n.registerObserver(n2,f2);
                    }
                    catch(StackOverflowError e)
                    {
                        System.out.println("Input file very large");
                        e.printStackTrace();
                        System.exit(1);
                    }
                    backup1.left=n1;
                    n1.bnumber=new Data();
                    n1.bnumber.value=data.value;
                    n1.parent=backup1; 
                    backup2.left=n2;
                    n2.bnumber=new Data();
                    n2.bnumber.value=data.value;
                    n2.parent=backup2; 
                }
                else
                {
                     try
                     {
                       insertnode(newnode.left,backup1.left,backup2.left,data);
                     }
                     catch(StackOverflowError e)
                    {
                        System.out.println("Input file very large");
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
                    
            }       
           if(newnode.bnumber.value<data.value)
            {
                if(newnode.right==null)
                {
                    Node n=new Node();
                    newnode.right=n;
                    n.bnumber=new Data();
                    n.bnumber.value=data.value;
                    newnode.maxflag=false;
                    n.parent=newnode; 
                    Node n1=new Node();
                    Node n2=new Node();
                    try
                    {
                        n.registerObserver(n1,f1);
                    }
                    catch(StackOverflowError e)
                    {
                        System.out.println("Input file very large");
                        e.printStackTrace();
                    }
                     try
                    {
                        n.registerObserver(n2,f2);
                    }
                    catch(StackOverflowError e)
                    {
                        System.out.println("Input file very large");
                        e.printStackTrace();
                        System.exit(1);
                    }

                    backup1.right=n1;
                    n1.bnumber=new Data();
                    n1.bnumber.value=data.value;
           /*         if(n1.bnumber.value>backup1.bnumber.value)
                    {
                        n1.maxflag=true;
                        backup1.maxflag=false;
                    }*/
                    n1.parent=backup1; 
                    backup2.right=n2;
                    n2.bnumber=new Data();
                    n2.bnumber.value=data.value;
                   
                    n2.parent=backup2; 
                }
                else
                {
                     try
                     {
                       insertnode(newnode.right,backup1.right,backup2.right,data);
                     }
                     catch(StackOverflowError e)
                    {
                        System.out.println("Input file very large");
                        e.printStackTrace();
                        System.exit(1);
                    }
                    
                }
            }     
    }
    /**
     * This method is used inorder traversal through the BST 
     */
    public void inordertraversal()
    {
        if(root!=null)
        {
            inorder(root);
        }
    }
    /**
     * This method is used by the inordertraversal()
     * to traverse the through the tree
     * @param n 
     */
    public void inorder(Node n)
    {
        if(n!=null)
        {
            inorder(n.left);
            System.out.println(n.bnumber.value);
     //       sum=sum+n.bnumber;
            inorder(n.right);
        }
    }
    /**
     *This method is used to calculate the sum of all 
     * nodes in the BST
     */
      public void calculateSum()
    {
        if(root!=null)
        {
            sum(root);
        }
    }
    /**
     * Used by the calculateSum() method to calculate the sum 
     * of all nodes 
     * @param n 
     */  
    public void sum(Node n)
    {
        if(n!=null)
        {
            sum(n.left);
            sum1.value=sum1.value+n.bnumber.value;
            sum(n.right);
        }
    }
    /**
     * This method is used to update the values of the 
     * original BST by the update value
     * @param noriginal
     * @param backup1
     * @param backup2
     * @param value 
     */
    public void updateInorder(Node noriginal,Node backup1,Node backup2,Data value)
    {
         if(noriginal!=null)
        {
            updateInorder(noriginal.left,backup1.left,backup2.left,value);
            if(noriginal.maxflag==false)
            {
                noriginal.bnumber.value=noriginal.bnumber.value+value.value;
            }
            else 
            {
                noriginal.bnumber.value=noriginal.bnumber.value+(value.value+value.value);
            }
            noriginal.updateValue(noriginal.bnumber,value);
            updateInorder(noriginal.right,backup1.right,backup2.right,value);
        }
    }
}


