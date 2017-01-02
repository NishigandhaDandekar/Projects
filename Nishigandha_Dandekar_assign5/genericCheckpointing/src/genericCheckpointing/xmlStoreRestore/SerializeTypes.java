/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.FileProcessor;
/**
 *
 * @author Nishigandha
 * This class returns the pseudo XML string for each type like int,double and so on
 */

public class SerializeTypes {

     public  FileProcessor fp1;
    SerializeTypes(FileProcessor fp) {
        fp1=fp;
    }
   
    String serializeInt(int value, String tagName)
    {
        String s="<"+tagName+" xsi:type=\"xsd:int\">"+value+"</"+tagName+">";
        return s;
    }
     String serializeDouble(double value, String tagName)
    {
        String s="<"+tagName+" xsi:type=\"xsd:double\">"+value+"</"+tagName+">";
        return s;
    }
      String serializeLong(long value, String tagName)
    {
        String s="<"+tagName+" xsi:type=\"xsd:long\">"+value+"</"+tagName+">";
        return s;
    }
        String serializeString(String value, String tagName)
    {
        String s="<"+tagName+" xsi:type=\"xsd:string\">"+value+"</"+tagName+">";
        return s;
    }
           String serializeBool(boolean value, String tagName)
    {
        String s="<"+tagName+" xsi:type=\"xsd:boolean\">"+value+"</"+tagName+">";
        return s;
    }
    String serializeShort(short value, String tagName)
    {
        String s="<"+tagName+" xsi:type=\"xsd:short\">"+value+"</"+tagName+">";
        return s;
    }
    String serializeChar(char value, String tagName)
    {
        String s="<"+tagName+" xsi:type=\"xsd:char\">"+value+"</"+tagName+">";
        return s;
    }
        String serializeFloat(float value, String tagName)
    {
        String s="<"+tagName+" xsi:type=\"xsd:float\">"+value+"</"+tagName+">";
        return s;
    }
    public void displayHeader(String name)
    {
        fp1.out.println("<DPSerialization>"); 
        fp1.out.println("<complexType xsi:type=\""+name+"\">");
    }
     public void displayFooter()
    {
        fp1.out.println("</complexType>");
        fp1.out.println("</DPSerialization>");
   
    }
}
