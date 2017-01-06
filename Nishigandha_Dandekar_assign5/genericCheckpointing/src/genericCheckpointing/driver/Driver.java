package genericCheckpointing.driver;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.RandomGenerator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
import java.lang.reflect.InvocationHandler;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Nishigandha
 * This the driver class which is used to  call the 
 * required methods to serialize and deserialize the 
 * randomly generated objects
 */
public class Driver {

    /**
   * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ProxyCreator pc1 = new ProxyCreator();
        FileProcessor fp = new FileProcessor();
        Random r = new Random();
        InvocationHandler h = new StoreRestoreHandler(fp);
      if(args.length!=3)
        {
            System.out.println("Invalid number of arguements");
            System.exit(1);
        }
        int numberofobjects=0;
        try
        {
        	numberofobjects = Integer.parseInt(args[1]);
    	}
    	 catch(NumberFormatException e)
        {
            System.out.println("Invalid Arguement");
            e.printStackTrace();
            System.exit(1);
        }
        StoreRestoreI cpointRef = (StoreRestoreI) pc1.createProxy(new Class[]{StoreI.class, RestoreI.class}, h);
        Vector<SerializableObject> v = new Vector<>();
        Vector<SerializableObject> vo = new Vector<>();
        String filename=args[2];
       
        String serializationmode = args[0];
        if (serializationmode.equals("ser")||serializationmode.equals("serdser")) {
        	 fp.output(filename);
            MyAllTypesFirst a;
            MyAllTypesSecond b;
            RandomGenerator rg = new RandomGenerator();
            for (int i = 0; i < numberofobjects; i++) {
     
                a = new MyAllTypesFirst(r.nextInt(26), r.nextLong(), rg.generateString(), r.nextBoolean());
                v.add(a);
   
                b = new MyAllTypesSecond(r.nextDouble(), r.nextFloat(), (short) r.nextInt(26), rg.generateChar());
                v.add(b);
 
            }
            for (int i = 0; i < v.size(); i++) {

                if ((v.get(i).getClass().getSimpleName()).equals("MyAllTypesFirst")) {
                    ((StoreI) cpointRef).writeObj((MyAllTypesFirst) v.get(i), "XML");
                }
                if ((v.get(i).getClass().getSimpleName()).equals("MyAllTypesSecond")) {
                    ((StoreI) cpointRef).writeObj((MyAllTypesSecond) v.get(i), "XML");
                }
            }
        }  if (serializationmode.equals("serdser")) {
            fp.input(filename);
            int mismatch=0;
            for (int i = 0; i < v.size(); i++) {
                vo.add((SerializableObject) ((RestoreI) cpointRef).readObj("XML"));
            }
            for (int i = 0; i < v.size(); i++) {
                if(!(v.get(i).equals(vo.get(i))))
                {
                    mismatch++;
                }
            }
            System.out.println(""+mismatch+" objects mismatched");    
        }   if (serializationmode.equals("dser")) {
            fp.input(filename);
            SerializableObject o;
            for (int i = 0; i < numberofobjects; i++) {
                o=(SerializableObject) ((RestoreI) cpointRef).readObj("XML");
                if(o!=null)
                vo.add(o);
            }
            for (int i = 0; i < vo.size(); i++) {
               System.out.println(vo.get(i).toString());
            }
        }
    }

}
