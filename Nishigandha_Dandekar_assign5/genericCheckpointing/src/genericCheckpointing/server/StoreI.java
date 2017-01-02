/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.server;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

/**
 *
 * @author Girish
 */
public interface StoreI extends StoreRestoreI {
 
      void writeObj(MyAllTypesFirst aRecord, String wireFormat);
      void writeObj(MyAllTypesSecond aRecord, String wireFormat);
}




