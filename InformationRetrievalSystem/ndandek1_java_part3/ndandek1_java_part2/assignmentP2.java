/*
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Nishigandha
 */
public class assignmentP2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
    //   File file = null;
        Global data = new Global();
     //   file = new File("C:\\Users\\Girish\\IRProject2\\src\\irproject2\\docs");
        FileProcessor fp = new FileProcessor();
        List<String> paths = new ArrayList<String>();
        File folders = new File(args[0]); 
      //  System.out.println("Folders"+folders.toString());
        File[] files = folders.listFiles();
	for (File file : files) {
         //   File[] temp = file.listFiles();
          //  if(temp!=null)
          //  for(File tempFile : temp){
            //    if(!tempFile.isDirectory())
               try {
                    paths.add(file.getCanonicalPath());
           } catch (IOException ex) {
               Logger.getLogger(assignmentP2.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
        
        for(String path : paths){
     //       System.out.println("File Path"+path); 
        }
       
//        while (folders.hasMoreElements()) {
//            Object foldertemp = folders.nextElement();
//            paths.add(foldertemp.toString());
//            //System.out.println(foldertemp.toString());
//        }
        int docID = 0;
        StringProcessor sp = new StringProcessor();
       // total number of words in the collection
        for (String path : paths) {
            InputStream input = null;
            File f = new File(path);
            if (!f.isDirectory()) {
                try {
                    input = new FileInputStream(f);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(assignmentP2.class.getName()).log(Level.SEVERE, null, ex);
                }
                docID++; 
            }

            if (input != null) //check if it is a file 
            {
                //         System.out.println("FILE :" + zipfile.getEntry(path));
                Scanner sc = fp.getScanner(input);
                String heading = "";
                String snippet = "";
                while (sc.hasNext()) {
                    String next = sc.next().toLowerCase();
                    if (next.equalsIgnoreCase("<HEADLINE>")) {
                        next = sc.next().toLowerCase();
                        while (!next.equalsIgnoreCase("</HEADLINE>")) {
                            if (!sp.isTag(next)) {
                              
                                heading = heading + " " + next.replaceAll(",", "");
                            }
                            next = sc.next().toLowerCase();
                        }
                    }
                }
                sc = null;
                sc = fp.getScanner(new FileInputStream(f));
                while (sc.hasNext()) {
                    String next = sc.next().toLowerCase();
                    if (next.equalsIgnoreCase("<TEXT>")) {
                        next = sc.next().toLowerCase();
                        //           System.out.println("Here in text");
                        int countTemp = 0;
                        while (!next.equalsIgnoreCase("</TEXT>")) {
                            if (countTemp == 40) {
                                break;
                            }
                            if (!sp.isTag(next)) {
                                next.replaceAll(",", "");
                                snippet = snippet + " " + next.replaceAll(",", "");
                                countTemp++;
                            }
                            next = sc.next().toLowerCase();        
                        }
                    }
                }
                sc = null;
                sc = fp.getScanner(new FileInputStream(f));

                //Doc Table
                DocInfo d = new DocInfo();
                d.addPath(path);
                d.addHeading(heading);
                d.addSnippet(snippet);
                data.docInfo.put(docID, d);
                //Actual Processing for index terms starts here 
                while (sc.hasNext()) {
                    String temp = sc.next();
//                    if(sp.processStopWordAndTags(temp))
//                        data.count++;
           //         System.out.println(temp);
                    if (!temp.contains("<") && !temp.contains(">")) {
                        String temp1 = sp.process(temp.toLowerCase());
                        if (temp1 != null) {
                       //     System.out.println("after processing "+temp1);
                            data.uniqueWordCollection.add(temp1);
                            if (data.docInfo.containsKey(docID)) {
                                DocInfo d1 = data.docInfo.get(docID);
                                d1.incrementLength();
                                //    System.out.println("DocId: "+ docID + "Length "+d1.length);
                                data.docInfo.put(docID, d1);
                                data.count++; //stands for the number of terms in the collection
                            }
                                if (data.docIdTf.containsKey(temp1)) {
                                        List<DocIdTf> tempList = data.docIdTf.get(temp1);
                                        boolean containsDocId = false;
                                        for (DocIdTf tempTerm : tempList) {
                                            if (tempTerm.docID == docID) {
                                                tempTerm.tf++;
                                                containsDocId = true;
                                            }
                                        }
                                        if (!containsDocId) {
                                            DocIdTf term = new DocIdTf(docID, 1);
                                            tempList.add(term);
                                        }
                                    } else {
                                        DocIdTf term = new DocIdTf(docID, 1);
                                        List<DocIdTf> tempList = new ArrayList();
                                        tempList.add(term);
                                        data.docIdTf.put(temp1, tempList);
                                    }
                        }
                    
                    } 

                }
            }
        }
    //    System.out.println("Total number of words in collection " + count);
        Iterator<String> iterator = data.uniqueWordCollection.iterator();
        int offsetCount = 0;
        while (iterator.hasNext()) {
            String currentTerm = iterator.next();
            if (data.docIdTf.containsKey(currentTerm)) {
              //  System.out.println("Here");
                List<DocIdTf> tempList = data.docIdTf.get(currentTerm);
                CfDfOffset tempTerm = new CfDfOffset();
                tempTerm.df = tempList.size();
                if (data.cfDfOffset.isEmpty()) {
                    tempTerm.offset = offsetCount;
                    offsetCount=offsetCount+tempTerm.df;
                  //  offsetCount++;
                } else {
                    tempTerm.offset = offsetCount;
                    offsetCount = offsetCount + tempTerm.df;
                }
                int cf = 0;
                for (DocIdTf temp : tempList) {
                    cf = cf + temp.tf;
                }
                tempTerm.cf = cf;
                data.cfDfOffset.put(currentTerm, tempTerm);
            }
        }
        List<String> termList = new ArrayList<String>(data.cfDfOffset.keySet());
        Collections.sort(termList);
        int tempOffset =0;
       
        for (String tempTerm : termList) {
               if(tempOffset ==0){
                   List<DocIdTf> listTemp= data.docIdTf.get(tempTerm);
                   int countTemp =0;
                   while(countTemp<listTemp.size()){
                        data.postings.add(tempOffset, listTemp.get(countTemp));
                        tempOffset++;
                        countTemp++;
                   }
                  
               }
               else {
                   List<DocIdTf> listTemp= data.docIdTf.get(tempTerm);
                   int countTemp =0;
                   while(countTemp<listTemp.size()){
                        data.postings.add(tempOffset, listTemp.get(countTemp));
                        tempOffset++;
                        countTemp++;
                   }
               }
        }
        data.print();
    }
}
