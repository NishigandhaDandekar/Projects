/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;
import java.lang.reflect.Method;

/**
 *
 * @author Girish
 */
public interface SerStrategy {
    void processInput(SerializableObject os,Method m,SerializeTypes s,FileProcessor fp);
}
