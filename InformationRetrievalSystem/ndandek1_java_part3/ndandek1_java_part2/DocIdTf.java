/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Nishigandha
 */
public class DocIdTf {
    int docID;
    int tf;
   
    public DocIdTf(int docID, int tf){
        this.docID=docID;
        this.tf=tf;
    }

    @Override
    public String toString() {
        return "docID=" + docID + ", tf=" + tf;
    }

   
    
    
}
