

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
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


public class assignmentP3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        //   File file = null;
        Global3 data = new Global3();
        StringProcessor3 sp = new StringProcessor3();
        //   file = new File("C:\\Users\\Girish\\IRProject2\\src\\irproject2\\docs");

//         data.print();
        int docID =0;
        PrintStream output = new PrintStream(new FileOutputStream("result.txt"));
        
        Scanner sc1 = new Scanner(new FileInputStream(new File("ndandek1_java_part2/dictionary.csv")));
        Scanner sc2 = new Scanner(new FileInputStream(new File("ndandek1_java_part2/postings.csv")));
        Scanner sc3 = new Scanner(new FileInputStream(new File("ndandek1_java_part2/docsTable.csv")));
        Scanner sc4 = new Scanner(new FileInputStream(new File("ndandek1_java_part2/total.txt")));
        //initialize docID
        double collectionSize = 0;
        if(sc4.hasNext())
            collectionSize = sc4.nextInt();
        //initalize docInfo
        while(sc3.hasNext()){
            String input = sc3.nextLine();
             StringTokenizer tokenizer = new StringTokenizer(input, ",");
                int temp1 = Integer.parseInt(tokenizer.nextToken().trim());
                String temp2 = tokenizer.nextToken().trim();
                int temp3 = Integer.parseInt(tokenizer.nextToken().trim());
                String temp4 = tokenizer.nextToken().trim();
                String temp5 = tokenizer.nextToken().trim();
     //             System.out.println("sc3: temp1: "+temp1+" temp2: "+temp2+" temp3: "+temp3+" temp4: "+temp4+" temp5: "+temp5);
                data.docInfo.put(temp1, new DocInfo3(temp2, temp4, temp5, temp3));
                if(!sc3.hasNext())
                    docID = temp1;
            }
        
        //initialize cfdfoffset
        while(sc1.hasNext()){
            String input = sc1.nextLine();
             StringTokenizer tokenizer = new StringTokenizer(input, ",");
                String temp1 = tokenizer.nextToken().trim();
                int temp2 = Integer.parseInt(tokenizer.nextToken().trim());
                int temp3 = Integer.parseInt(tokenizer.nextToken().trim());
                int temp4 = Integer.parseInt(tokenizer.nextToken().trim());
      //          System.out.println("sc1: temp1: "+temp1+" temp2 "+temp2+" temp3: "+temp3+" temp4: "+temp4);
                data.cfDfOffset.put(temp1, new CfDfOffset3(temp2, temp3, temp4));
        }
        //initialize postings 
        int tempOffset =0;
        while(sc2.hasNext()){
           String input = sc2.nextLine();
           StringTokenizer tokenizer = new StringTokenizer(input, ",");
                
                int temp1 = Integer.parseInt(tokenizer.nextToken().trim());
                int temp2 = Integer.parseInt(tokenizer.nextToken().trim());
                data.postings.put(tempOffset, new DocIdTf3(temp1, temp2));
                tempOffset++;
        }
        //initialize docIdTd
       
        List<String> tempList1 = new ArrayList(data.cfDfOffset.keySet());
        Collections.sort(tempList1);
        for(String term : tempList1 ){
    //System.out.println();
            CfDfOffset3 tempCfDfOffset = data.cfDfOffset.get(term);
             int tempOffset1 = tempCfDfOffset.offset;
            List<DocIdTf3> tempList = new ArrayList();
            int temp4 = tempOffset1;
                while(tempOffset1<(tempCfDfOffset.df+temp4)){
                  //  System.out.println(tempOffset1);
                  tempList.add(data.postings.get(tempOffset1));
    //            System.out.println("Term "+term+" temp1: "+data.postings.get(tempOffset1).docID+" temp2: "+data.postings.get(tempOffset1).tf);
                tempOffset1++;
                }   
                data.docIdTf.put(term,tempList);
            }
        
        int docIdTemp = docID;
        
        data.print();
        System.out.println("Enter Query");
        Scanner input = new Scanner(System.in);
        String inputQuery = null;
        inputQuery = input.nextLine();
        while (!inputQuery.equalsIgnoreCase("exit")) {
             HashMap<Integer, Double> docIDProb = new HashMap<Integer, Double>();
            Hashtable<String, Integer> queryTermIndex = new Hashtable<String, Integer>();

            StringTokenizer tokenizer = new StringTokenizer(inputQuery, " ");
                    while (tokenizer.hasMoreElements()) {
                        String temp1 = sp.process(tokenizer.nextToken().toLowerCase());
                        if (temp1 != null) {
                            if (queryTermIndex.containsKey(temp1)) {
                                queryTermIndex.put(temp1.toLowerCase(), queryTermIndex.get(temp1.toLowerCase()) + 1);
                            } else {
                                queryTermIndex.put(temp1.toLowerCase(), 1);
                            }
                        }
                    }
                      for(int i =1;i<=docID;i++){
                          double temp=0;
                          for(String term : queryTermIndex.keySet()){
                              double cf=0;
                              if(data.cfDfOffset.containsKey(term)){
                                  cf=data.cfDfOffset.get(term).cf;
                              }
                              double df=0;
                              List<DocIdTf3> tempList = data.docIdTf.get(term);
                              if(tempList!=null)
                              for(DocIdTf3 t1: tempList){
                                  if(t1.docID==i){
                                      df = t1.tf;
                                      break;
                                  }
                              }
                              double docLength = data.docInfo.get(i).length;
                       //       double collectionSize = data.count;
                              if(((double)Math.log(((1-data.w)*(df/docLength))+(data.w*(cf/collectionSize)))/(double)Math.log(2))!=Math.log(0.0))
                 //          System.out.println("docID: "+i+" "+term+" df "+df+" cf "+cf);
                           temp = temp +((double)Math.log(((1-data.w)*(df/docLength))+(data.w*(cf/collectionSize)))/(double)Math.log(2));
                 //             System.out.println(temp);
                          }
                    //          System.out.println(i+" "+temp);
                              docIDProb.put(i, temp);
                    }
                    for(Integer temp1: docIDProb.keySet()){
                 //       System.out.println(temp1 + " "+ docIDProb.get(temp1));
                    }
                    boolean flag = false;
                    for(int i=1;i<=docIdTemp;i++){
                        if(docIDProb.get(i)!= 0.0)
                            flag = true;
                            break;
                    }
               //     output.println(inputQuery);
                    if(flag==false)
                        output.println("NO RESULTs\n");
                    else
                        data.printDocIdProb(docIDProb, output);

                    System.out.println("Enter Query");
                    inputQuery = input.nextLine();
           // data.print();
        }
        output.close();
    }
}