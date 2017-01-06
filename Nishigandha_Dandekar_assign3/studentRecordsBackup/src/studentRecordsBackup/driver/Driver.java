package studentRecordsBackup.driver;
import java.io.FileNotFoundException;
import java.io.FileReader;
/**
 * This class is used to accept the input file and the update value 
 * from the command line 
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import studentRecordsBackup.util.BSTBuilder;
import studentRecordsBackup.bst.Data;
import studentRecordsBackup.bst.BST;
/**
 * 
* @author Nishigandha
 */
public class Driver {

    public static void main(String[] args) throws IOException {
        if(args.length!=2)
        {
            System.out.println("Invalid number of arguements");
            System.exit(1);
        }
        FileReader fr=new FileReader(args[0]);
        BSTBuilder createbsts=new BSTBuilder(fr);
        try {
            createbsts.insertInBst();
        } catch (FileNotFoundException ex) {
            System.out.println("Input file is not found");
            ex.printStackTrace();
        }
       int value=0;
        try
        {
            value=Integer.parseInt(args[1]);
             
        }
        catch(NumberFormatException e)
        {
            System.out.println("Invalid Arguement");
            e.printStackTrace();
            System.exit(1);
        }
        Data d=new Data();
        d.value=value;
        createbsts.printInorder();
        BST original,backup1,backup2;
        original=createbsts.getOriginal();
        backup1=createbsts.getBackup1();
        backup2=createbsts.getBackup2();
        original.updateInorder(original.root,backup1.root,backup2.root,d);
        createbsts.printInorder();
        createbsts.printBSum();
        
    }    
}
