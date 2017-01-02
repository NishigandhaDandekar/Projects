package genericCheckpointing.util;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 *
 * @author Nishigandha
 * This class creates a proxy object
 */
public class ProxyCreator {
    public StoreRestoreI createProxy(Class<?>[] interfaceArray,InvocationHandler handler){
		StoreRestoreI storeRestoreRef =
             (StoreRestoreI)
            Proxy.newProxyInstance(
                                   getClass().getClassLoader(),
                                   interfaceArray,
                                   handler
                                   );
            //    System.out.println("The class is "+getClass());

		return storeRestoreRef;
	}
}
