/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Nishigandha
 */
public class DocInfo {
    int docId;
    String heading;
    String snippet;
    String path;
    int length=0;
    
    public void addHeading(String heading){
        this.heading = heading;
    }
    
    public void addSnippet(String snippet){
        this.snippet = snippet;
    }
    
    public void addPath(String path){
        this.path = path;
    }
    
    public void incrementLength(){
        this.length = this.length + 1;
    }
}
