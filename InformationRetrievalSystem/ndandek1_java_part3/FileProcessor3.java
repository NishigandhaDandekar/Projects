import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


class FileProcessor3 {

    private Scanner sc;
    public Scanner getScanner(InputStream input) {
        sc = new Scanner(input);
        return sc;
    }
}
