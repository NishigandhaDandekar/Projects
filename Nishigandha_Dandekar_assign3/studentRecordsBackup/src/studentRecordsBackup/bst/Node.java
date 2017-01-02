package studentRecordsBackup.bst;
import java.util.ArrayList;
import java.util.List;
import studentRecordsBackup.util.OddEvenFilterI;
import studentRecordsBackup.util.EvenFilterImpl;
import studentRecordsBackup.util.OddFilterImpl;
/*
 *This class implements the SubjectI and the 
 *ListenerI interface hence it acts as both
 *Subject as well as listener
 *
 * @author Nishigandha
 */
public class Node implements SubjectI, ObserverI, DisplayElementI {
   List<BackupInput> observers = null; 
   Data value;
   Data value1;
   Data bnumber;
   boolean maxflag=false;
   Node left=null;
   Node right=null;
   Node parent=null;
 //  private SubjectI n;
   public Node()
    {
       observers = new ArrayList<>();
    }
    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public Data getBnumber() {
        return bnumber;
    }
    public void setBnumber(Data bnumber) {
        this.bnumber = bnumber;
    }
   /**
    * This method is overridden from the Subject interface 
    * and it is used to register an observer with a subject
    * It takes the observer instance along with the filter 
    * that has to be associated with the observer
    * @param o
    * @param f 
    */
    @Override
    public void registerObserver(ObserverI o,OddEvenFilterI f) {
        BackupInput bi=new BackupInput();
        bi.o=o;
        bi.f=f;
        try
        {
            observers.add(bi);
        }
        catch(StackOverflowError e)
        {
            System.out.println("Input size if very large");
            e.printStackTrace();
             System.exit(1);
        }
    }    
        
    /**
     * This method can be used to deregister the observer 
     * @param o 
     */
    @Override
    public void removeObserver(ObserverI o) {
       
    }
    /**
     * This method is called to notify the observers of the 
     * change in the value of the subject
     * The notification is sent to the observer based 
     * on the filter associated with the observer
     */

    @Override
    public void notifyObservers() {
          for(BackupInput observer: observers){
          if(observer.f.check(value1)==true&&(observer.f instanceof EvenFilterImpl))
          { observer.o.update(value); display();}
          else if(observer.f.check(value1)==true&&(observer.f instanceof OddFilterImpl))
          {observer.o.update(value); display();}
       }            
    }
    /**
     * This method is used to update the value of the Subject
     * value1 - it is the UPDATE_VALUE 
     * @param value
     * @param value1 
     */
    public void updateValue(Data value,Data value1)
    {
        this.value=value;
        this.value1=value1;
        notifyObservers();
    }
    /**
     * This method is invoked by listener registered as
     * observers for a particular subject, in the 
     * notifyObservers() method
     * @param number 
     */
    @Override
    public void update(Data number) {
        setBnumber(number);
    }
    /**
     * This method is used by notifyObservers() to 
     * display the updated values of the observers 
     */
    @Override
    public void display() {
        System.out.println("Bnumber in listener "+bnumber.value);
    }
   
}
