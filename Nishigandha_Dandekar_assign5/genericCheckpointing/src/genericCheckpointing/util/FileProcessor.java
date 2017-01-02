package genericCheckpointing.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Nishigandha
 * FileProcessor class with methods to read data
 * from input file and write to output file
 */
public class FileProcessor {
     Scanner sc = null;
    public PrintStream out = null;
    public FileInputStream input;
    String word = null;
    BufferedReader r;
    public void input(String filename) {

            File file = new File(filename);
            try {
                sc = new Scanner(new FileReader(file));
                if(!sc.hasNext())
                {
                    System.out.println("The input file is empty");
                    System.exit(1);
                }
            } catch (FileNotFoundException e) {
                System.err.println("File Not Found");
                System.exit(1);
            } 
            
        } 
    public String readWord() {
        while (sc.hasNextLine()) {
            word = sc.nextLine();
            return word;
        }
        return null;
    }

    public void output(String fileout) {
        try {
            out = new PrintStream(new FileOutputStream(fileout));
        } catch (FileNotFoundException ex) {
            System.err.println("File Not Found");
            System.exit(1);
        }

    }
}
