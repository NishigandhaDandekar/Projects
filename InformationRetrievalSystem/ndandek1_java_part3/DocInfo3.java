
public class DocInfo3 {

    public DocInfo3(String heading, String snippet, String path, int length) {
        this.heading = heading;
        this.snippet = snippet;
        this.path = path;
        this.length = length;
    }
//    int docId;
    String heading;
    String snippet;
    String path;
    int length;
    
    public void addHeading(String heading){
        this.heading = heading;
    }
    
    public void addSnippet(String snippet){
        this.snippet = snippet;
    }
    
    public void addPath(String path){
        this.path = path;
    }
}
