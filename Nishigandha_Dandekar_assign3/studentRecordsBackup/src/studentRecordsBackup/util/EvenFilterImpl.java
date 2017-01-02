/**
 * This class is used to detect an even number
 * This class implements the OddEvenFilterI
 */
package studentRecordsBackup.util;
import studentRecordsBackup.bst.Data;

/**
 * @author Nishigandha
 */
public class EvenFilterImpl implements OddEvenFilterI {
    /**
     * This method checks if the entered value is even
     * If the value is even then it returns true else it returns false
     * @param value
     * @return 
     */
    public boolean check(Data value)
    {
        if(value.value % 2==0)
        {
            return true;
        }
        else 
            return false;
    }
}
