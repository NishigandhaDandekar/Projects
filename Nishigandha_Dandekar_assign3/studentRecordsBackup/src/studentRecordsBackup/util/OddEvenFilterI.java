/**
 * This interface contains the method signature check()
 * which is implemented by the EvenFilterImplementation 
 * and OddFilterImplementation class to check if 
 * the given value is even or odd
 *
 * @author Nishigandha
 */
package studentRecordsBackup.util;
import studentRecordsBackup.bst.Data;
public interface OddEvenFilterI {
    public boolean check(Data value);
}
