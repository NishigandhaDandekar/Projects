

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Nishigandha
 */
public class StringProcessor {

    HashSet punctuation = new HashSet();
            Set stopwords = new HashSet();
    public StringProcessor() {
        punctuation.add(',');
        punctuation.add('.');
        punctuation.add('!');
        punctuation.add('?');
        punctuation.add(';');
        punctuation.add(':');
        
        stopwords.add("and");
        stopwords.add("an");
        stopwords.add("by");
        stopwords.add("from");
        stopwords.add("of");
        stopwords.add("the");
        stopwords.add("with");
        stopwords.add("a");
        stopwords.add("in");
        stopwords.add("for");
        stopwords.add("hence");
        stopwords.add("within");
        stopwords.add("who");
        stopwords.add("when");
        stopwords.add("where");
        stopwords.add("why");
        stopwords.add("how");
        stopwords.add("whom");
        stopwords.add("have");
        stopwords.add("had");
        stopwords.add("has");
        stopwords.add("not");
        stopwords.add("but");
        stopwords.add("do");
        stopwords.add("does");
        stopwords.add("done");    
    }
            
    
    public boolean processStopWordAndTags(String word){
     //   Set stopwords;
        if(stopwords.contains(word))
            return false;
        if(word.startsWith("<")&&word.endsWith(">")){
            return false; 
        }    
        return true;
    }
    public boolean isTag(String word){
        if(word.startsWith("<")&&word.endsWith(">"))
            return true;
        return false;
    }
    
    public String stem(String input) {
            if(input.endsWith("'s")) {
                return input.substring(0,input.length()-2)+"s";
            }
            if(input.endsWith("s'")) {
                return input.substring(0,input.length()-2)+"s";
            }
            if((!input.endsWith("aies") && !input.endsWith("eies"))) {
                if(input.endsWith("ies"))
                    return input.substring(0,input.length()-3)+"y";
            }
            if((!input.endsWith("us") && !input.endsWith("ss"))) {
                if(input.endsWith("s"))
                    return input.substring(0,input.length()-1);
            }
                return input;
    }
    public String display(List display) {
       return "Ecount = "+display.get(0)+" Gcount = "+display.get(1)+" Bcount = "+display.get(2);
    }
    
     public boolean isStopWord(String input){
        Set stopwords = new HashSet();
        stopwords.add("and");
        stopwords.add("an");
        stopwords.add("by");
        stopwords.add("from");
        stopwords.add("of");
        stopwords.add("the");
        stopwords.add("with");
        stopwords.add("a");
        stopwords.add("in");
        stopwords.add("for");
        stopwords.add("hence");
        stopwords.add("within");
        stopwords.add("who");
        stopwords.add("when");
        stopwords.add("where");
        stopwords.add("why");
        stopwords.add("how");
        stopwords.add("whom");
        stopwords.add("have");
        stopwords.add("had");
        stopwords.add("has");
        stopwords.add("not");
        stopwords.add("but");
        stopwords.add("do");
        stopwords.add("does");
        stopwords.add("done");
        return stopwords.contains(input);
    }
    public String removeFirstCharacter(String input){
        if((input.charAt(0)=='\"')||(input.charAt(0)=='\'')||(input.charAt(0)=='[')||(input.charAt(0)=='('))
            return input.substring(1);
        else 
            return input;
    }
     public String removeLastCharacter(String input){
        if((input.charAt(input.length()-1)=='\"')
                ||(input.charAt(input.length()-1)=='\'')
                ||(input.charAt(input.length()-1)==']')
                ||(input.charAt(input.length()-1)==')')
                ||(input.charAt(input.length()-1)==',')
                ||(input.charAt(input.length()-1)=='.')
                ||(input.charAt(input.length()-1)=='?')
                ||(input.charAt(input.length()-1)==':')
                ||(input.charAt(input.length()-1)==';')
                ||(input.charAt(input.length()-1)=='!'))
            return input.substring(0,input.length()-1);
        else 
            return input;
    }

     public String hasSpecialCharacters(String input){
       // if(input.matches("[a-zA-Z0-9]*"))
            return input.replaceAll("[^a-zA-Z0-9]*", "");
     
     //   return false;
     }
    public String process(String input) {
        if(input.length()<2){
              try{
                  Integer.parseInt(input);
             }
             catch(NumberFormatException e){
                 return null;
             }
        }
              // return null;
        if(isStopWord(input))
               return null;
        String temp1 = removeFirstCharacter(input);
        String temp2 = removeLastCharacter(temp1);
        String temp3 = hasSpecialCharacters(temp2);
       
        String temp4 = stem(temp3);
       // String temp4 = removeSpecialCharacters(temp3);
    
        if(temp4.length()<2){
             try{
                  Integer.parseInt(temp4);
             }
             catch(NumberFormatException e){
                 return null;
             }  
        }               
        if(isStopWord(temp4))
               return null;
        return temp4.toLowerCase();    }
}
