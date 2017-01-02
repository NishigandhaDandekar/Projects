/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Nishigandha
 */
public class Global {

    public Set<String> uniqueWordCollection = new TreeSet();
    //    Hashtable<Integer, List<TermAndTF>> docTermAndTF = new Hashtable<>();
    public Hashtable<Integer, DocInfo> docInfo = new Hashtable<Integer, DocInfo>();
    public Hashtable<String, CfDfOffset> cfDfOffset = new Hashtable<String, CfDfOffset>(); //key-> term, value-> cf, df, offset
    public Hashtable<String, List<DocIdTf>> docIdTf = new Hashtable<String, List<DocIdTf>>();
    ArrayList<DocIdTf> postings = new ArrayList<DocIdTf>();
    int count = 0; 
//  Hashtable<String, Integer> TermAndCF = new Hashtable<>(); // key - term and value - CF

    public void print() throws FileNotFoundException {
        //   System.out.println();
        //    System.out.println("docInfo");
        List<Integer> docIdTempCollection = new ArrayList<Integer>(docInfo.keySet());
        Collections.sort(docIdTempCollection);
        //TO BE UNCOMMENTED
        PrintStream output;
     
            output = new PrintStream(new FileOutputStream("docsTable.csv"));
    //        output.println("docNumber, headline, docLength, snippet, docPath");
            for (Integer docIDtemp : docIdTempCollection) {
                DocInfo temp = docInfo.get(docIDtemp);
                output.println(docIDtemp + ","
                        + temp.heading + ","
                        + temp.length + ","
                        + temp.snippet + ","
                        + temp.path);
            }
         

        output = null;
        output = new PrintStream(new FileOutputStream("dictionary.csv"));
 //       output.println("term, cf, df, offset");
        List<String> termList = new ArrayList<String>(cfDfOffset.keySet());
        Collections.sort(termList);
     //   System.out.println("term, cf, df, offset");
        for (String termTemp : termList) {
            CfDfOffset temp = cfDfOffset.get(termTemp);
            output.println(termTemp + ", "
                    + temp.cf + ", "
                    + temp.df + ", "
                    + temp.offset);
        }

        output = null;
        output = new PrintStream(new FileOutputStream("postings.csv"));
    //    output.println("docId, tf");
        for (DocIdTf temp : postings) {
            output.println(temp.docID + ", "
                    + temp.tf + ", ");
        }
        
        output = null;
        output = new PrintStream(new FileOutputStream("total.txt"));
    //    output.println("total");
        output.println(count);
        
        
        

        //    List<String> termList = new ArrayList<String>(cfDfOffset.keySet());
        //    Collections.sort(termList);
        //To be uncommented******
//             for(String termTemp : termList) {    
//                CfDfOffset temp = cfDfOffset.get(termTemp);
//                System.out.println("Dictionary:");
//                System.out.println("Term: "+ termTemp +
//                        " CF: "+ temp.cf +
//                        " DF: " + temp.df +
//                        " offset: " + temp.offset);
//                System.out.println("Postings List:");
//                int stopAt = temp.offset+temp.df;
//                int counter = temp.offset;
//                while(counter<stopAt){
//                    System.out.println(postings.get(counter).toString());
//                    counter++;
//                }    
//            }
    }
}
