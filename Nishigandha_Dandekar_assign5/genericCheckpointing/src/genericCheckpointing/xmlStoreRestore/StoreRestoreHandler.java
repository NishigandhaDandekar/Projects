package genericCheckpointing.xmlStoreRestore;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.SerializableObject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nishigandha
 * This class is used to serialize and deserialize the objects
 */
public class StoreRestoreHandler implements InvocationHandler {

   
    public SerializeTypes s;
    public FileProcessor fp;
    int objectcount = -1;

    public StoreRestoreHandler(FileProcessor fp) {

        this.fp = fp;
        s = new SerializeTypes(fp);
    }

    /**
     *
     * @param proxy
     * @param method
     * @param os
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method m, Object[] os) {
//        Class cls = null;
//        Object returnobject = null;
//        System.out.println("Class name :" + os[0].getClass().getSimpleName());

        String methodName = m.getName();
        Class className = m.getDeclaringClass();
        Method[] m1 = new Method[4];

//        System.out.println("Class full name " + os[0].getClass().getName());
        if (methodName.equals("writeObj")) {
           if(os[1].toString().equals("XML"))
           {
               serializeData((SerializableObject)os[0], new XMLSerialization(),m,fp,s);
           }
           return null;
        }
//        System.out.println("Mthod name : " + methodName);
        if (methodName.equals("readObj")) {
               if(os[0].toString().equals("XML"))
                 {
                    return deserializeData(new XMLDeserialization(),m,fp,s);           
                }
           }  
               return null;
        }
    public void serializeData(SerializableObject sObject, SerStrategy sStrategy,Method m,FileProcessor fp,SerializeTypes s) {
              sStrategy.processInput(sObject,m,s,fp);
    }
     public Object deserializeData(DserStrategy sStrategy,Method m,FileProcessor fp,SerializeTypes s) {
             return  sStrategy.processInput(m,s,fp);
    }
}
