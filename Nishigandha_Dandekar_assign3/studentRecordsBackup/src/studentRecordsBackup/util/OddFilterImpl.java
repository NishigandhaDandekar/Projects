/*
 * This class is used to detect an even number
 * This class implements the OddEvenFilterI
 */
package studentRecordsBackup.util;
import studentRecordsBackup.bst.Data;

/**
 *
 * @author Nishigandha
 */
public class OddFilterImpl implements OddEvenFilterI{
    /**
     * This method checks if the entered value is odd
     * If the value is odd then it returns true else 
     * it returns false
     * @param value
     * @return 
     */
    public boolean check(Data value)
    {
        if(value.value % 2!=0)
        {
            return true;
        }
        else 
            return false;
    }
}
