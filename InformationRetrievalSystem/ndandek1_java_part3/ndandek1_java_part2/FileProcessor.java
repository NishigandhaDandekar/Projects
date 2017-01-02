/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nishigandha
 */
class FileProcessor {

    private Scanner sc;
    public Scanner getScanner(InputStream input) {
        sc = new Scanner(input);
        return sc;
    }
}
