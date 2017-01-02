package studentRecordsBackup.bst;
import studentRecordsBackup.util.OddEvenFilterI;
import studentRecordsBackup.util.EvenFilterImpl;
import studentRecordsBackup.util.OddFilterImpl;
/**
 * This class is used to encapsulate the ObserverI and 
 * the OddEvenFilterI into a single class BackupInput 
 * so that it can be easily stored in the ArrayList
 *
 * @author Nishigandha
 */

public class BackupInput {
    ObserverI o;
    OddEvenFilterI f;
}
