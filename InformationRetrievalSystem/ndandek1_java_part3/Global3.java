

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class Global3 {

    public double w = 0.1;

    public Set<String> uniqueWordCollection = new TreeSet();
    public Hashtable<Integer, DocInfo3> docInfo = new Hashtable<Integer, DocInfo3>();
    public Hashtable<String, CfDfOffset3> cfDfOffset = new Hashtable<String, CfDfOffset3>(); //key-> term, value-> cf, df, offset
    public Hashtable<String, List<DocIdTf3>> docIdTf = new Hashtable<String, List<DocIdTf3>>();

    Hashtable<Integer,DocIdTf3> postings = new Hashtable<Integer,DocIdTf3>();

    int count = 0;

    public void print() throws FileNotFoundException {
//         List<Integer> docIdTempCollection = new ArrayList<Integer>(docInfo.keySet());
//         Collections.sort(docIdTempCollection);
//         PrintStream output;

//         output = new PrintStream(new FileOutputStream("C:\\Users\\Girish\\IRProject3\\src\\ndandek1_java_part2\\docsTable.csv"));
//         output.println("docNumber, headline, docLength, snippet, docPath");
//         for (Integer docIDtemp : docIdTempCollection) {
//             DocInfo3 temp = docInfo.get(docIDtemp);
//             output.println(docIDtemp + ","
//                     + temp.heading + ","
//                     + temp.length + ","
//                     + temp.snippet + ","
//                     + temp.path);
//         }

//         output = null;
//         output = new PrintStream(new FileOutputStream("C:\\Users\\Girish\\IRProject3\\src\\ndandek1_java_part2\\dictionary.csv"));
//         output.println("term, cf, df, offset");
//         List<String> termList = new ArrayList<String>(cfDfOffset.keySet());
//         Collections.sort(termList);
//         for (String termTemp : termList) {
//             CfDfOffset3 temp = cfDfOffset.get(termTemp);
//             output.println(termTemp + ", "
//                     + temp.cf + ", "
//                     + temp.df + ", "
//                     + temp.offset);
//         }

// //        output = null;
// //        output = new PrintStream(new FileOutputStream("C:\\Users\\Girish\\IRProject3\\src\\ndandek1_java_part2\\postings.csv"));
// //        output.println("docId, tf");
// //        for (DocIdTf3 temp : postings) {
// //            output.println(temp.docID + ", "
// //                    + temp.tf);
// //        }

//         output = null;
//         output = new PrintStream(new FileOutputStream("C:\\Users\\Girish\\IRProject3\\src\\ndandek1_java_part2\\total.txt"));
//         output.println("total");
//         output.println(count);
    }

    public void printDocIdProb(HashMap<Integer, Double> docIdProb, PrintStream output) throws FileNotFoundException {

        List<Map.Entry<Integer, Double>> result = new ArrayList(docIdProb.entrySet());
        Collections.sort(result, new Comparator<Map.Entry<Integer, Double>>() {
            public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
                return -(o1.getValue().compareTo(o2.getValue()));
            }
        });
        int count = 1;
        for (Map.Entry<Integer, Double> temp : result) {
            if (temp.getValue() != Math.log(0.0)) {
                if (count <= 5) {
                    output.println(docInfo.get(temp.getKey()).heading);
                    output.println(docInfo.get(temp.getKey()).path);
                    output.println("Computed Probabolity:- " + temp.getValue());
                    output.println(docInfo.get(temp.getKey()).snippet);
                    output.println();
                    count++;
                } else {
                    break;
                }
            }

        }

    }
}
