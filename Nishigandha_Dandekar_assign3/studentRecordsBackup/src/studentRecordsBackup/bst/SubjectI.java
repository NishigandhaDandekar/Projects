package studentRecordsBackup.bst;
import studentRecordsBackup.util.OddEvenFilterI;
import studentRecordsBackup.bst.Data;
/**
 * This interface defines the method signatures which 
 * are to be implemented by the subject
 *
 * @author Nishigandha
 */

public interface SubjectI {
    public void registerObserver(ObserverI o,OddEvenFilterI f);
    public void removeObserver(ObserverI o);
    public void notifyObservers();    
    public void updateValue(Data value,Data value1);
}


