
public class DocIdTf3 {
    
    int docID;
    int tf;
   
    public DocIdTf3(int docID, int tf){
        this.docID=docID;
        this.tf=tf;
    }

    @Override
    public String toString() {
        return "docID=" + docID + ", tf=" + tf;
    }

   
    
    
}
