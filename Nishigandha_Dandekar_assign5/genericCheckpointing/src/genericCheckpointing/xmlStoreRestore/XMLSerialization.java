/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Girish
 */
public class XMLSerialization implements SerStrategy {

    @Override
    public void processInput(SerializableObject os,Method m,SerializeTypes s,FileProcessor fp) {
         Class paramList[] = m.getParameterTypes();

//            System.out.println("Parameter : " + paramList[0].getName() + " " + paramList[0].getTypeName());
            Field fieldlist[] = (paramList[0]).getDeclaredFields();
            s.displayHeader(os.getClass().getName());
            for (Field f : fieldlist) {
                String fieldname = "get" + ((f.getName()).substring(0, 1).toUpperCase()) + ((f.getName()).substring(1));
//                System.out.println(fieldname);
//                System.out.println("Fieldname :" + (f.getType().getSimpleName()));
                Method mg = null;
                try {
                    mg = paramList[0].getMethod(fieldname, null);
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                Object value = null;
                try {
                    value = mg.invoke(os);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                if ((f.getType().getSimpleName()).equals("int")) {
                    fp.out.println(s.serializeInt(Integer.parseInt(value.toString()), f.getName()));
                }
                if ((f.getType().getSimpleName()).equals("double")) {
                    fp.out.println(s.serializeDouble(Double.parseDouble(value.toString()), f.getName()));
                }
                if ((f.getType().getSimpleName()).equals("char")) {
                    fp.out.println(s.serializeChar(value.toString().charAt(0), f.getName()));
                }
                if ((f.getType().getSimpleName()).equals("float")) {
                    fp.out.println(s.serializeFloat(Float.parseFloat(value.toString()), f.getName()));
                }
                if ((f.getType().getSimpleName()).equals("short")) {
                    fp.out.println(s.serializeShort(Short.parseShort(value.toString()), f.getName()));
                }
                if ((f.getType().getSimpleName()).equals("long")) {
                    fp.out.println(s.serializeLong(Long.parseLong(value.toString()), f.getName()));
                }
                if ((f.getType().getSimpleName()).equals("boolean")) {
                    fp.out.println(s.serializeBool(Boolean.parseBoolean(value.toString()), f.getName()));
                }
                if ((f.getType().getSimpleName()).equals("String")) {
                    //  fp.out.println("Value here :"+value.toString());
                    fp.out.println(s.serializeString(value.toString(), f.getName()));
                }
            }
            s.displayFooter();
           

    }
    
}
