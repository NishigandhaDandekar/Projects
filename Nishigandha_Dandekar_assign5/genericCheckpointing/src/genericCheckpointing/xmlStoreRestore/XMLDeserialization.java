/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Girish
 */
public class XMLDeserialization implements DserStrategy {

    @Override
    public Object processInput(Method m, SerializeTypes s, FileProcessor fp) {
         Vector<SerializableObject> v = new Vector<>();
            Class cls = null;
        Object returnobject = null;
            Parameter p[] = m.getParameters();

//            System.out.println("File name" + os[0]);
            String word;
            while ((word = fp.readWord()) != null) {
                if (word.equals("<DPSerialization>")) {
                    word = fp.readWord();
                    while (!word.equals("</DPSerialization>")) {
                        if (word.contains("xsi:type")) {
//                            System.out.println(word);
                            int index = word.indexOf(">");
//                            System.out.println(word);
                            String str = word.substring(0, index + 1);
                            if (str.contains(".") && str.contains("\"")) {
                                int end = str.lastIndexOf("\"");
                                int start = str.indexOf("\"");
                                String extract = str.substring(start + 1, end);
                                //     System.out.println(extract);
                                cls = null;
                                try {
                                    cls = Class.forName(extract);
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                returnobject = null;
                                try {
                                    returnobject = cls.newInstance();
                                } catch (InstantiationException ex) {
                                    Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IllegalAccessException ex) {
                                    Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else if (str.contains(":") && str.contains("\"")) {
                                int end = str.lastIndexOf("\"");
                                int start = str.lastIndexOf(":");
                                String extract = str.substring(start + 1, end);
                     //           System.out.println(extract);
                                int value;
                                int start1 = word.indexOf(">");
                                int end1 = word.lastIndexOf("<");
                                int start2=word.indexOf("<");
                                int end2=word.indexOf("xsi");
                                String setextract=word.substring(start2, (end2-1));
                                String fieldname = "set" + (setextract.substring(1, 2).toUpperCase()) + (setextract.substring(2));
                                String fieldname1 = "get" + (setextract.substring(1, 2).toUpperCase()) + (setextract.substring(2));
                                //     System.out.println(fieldname);
//                                System.out.println(word.substring(start1 + 1, end1));
                                if (extract.equals("int")) {
                                    value = Integer.parseInt(word.substring(start1 + 1, end1));
                                    Class[] signature = new Class[1];
                                    signature[0] = int.class;
                                    Method mr = null;
                                    try {
                                        mr = cls.getMethod(fieldname, signature);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    try {
                                        mr.invoke(returnobject, value);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    Method mg1 = null;
                                    try {
                                        mg1 = cls.getMethod(fieldname1, null);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    int value1 = 0;
                                    try {
                                        value1 = (int) mg1.invoke(returnobject);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                    //                System.out.println("Value set " + value1);
                                }
                                if (extract.equals("long")) {
                                    Long vl = Long.parseLong(word.substring(start1 + 1, end1));
                                    Class[] signature = new Class[1];
                                    signature[0] = long.class;
                                    Method mr = null;
                                    try {
                                        mr = cls.getMethod(fieldname, signature);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    try {
                                        mr.invoke(returnobject, vl);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    Method mg1 = null;
                                    try {
                                        mg1 = cls.getMethod(fieldname1, null);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    long value1 = 0;
                                    try {
                                        value1 = (long) mg1.invoke(returnobject);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                     //               System.out.println("Value set " + value1);
                                }
                                if (extract.equals("boolean")) {
                                    boolean vb = Boolean.parseBoolean(word.substring(start1 + 1, end1));
                                    Class[] signature = new Class[1];
                                    signature[0] = boolean.class;
                                    Method mr = null;
                                    try {
                                        mr = cls.getMethod(fieldname, signature);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    try {
                                        mr.invoke(returnobject, vb);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    Method mg1 = null;
                                    try {
                                        mg1 = cls.getMethod(fieldname1, null);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    boolean value1 = false;
                                    try {
                                        value1 = (boolean) mg1.invoke(returnobject);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                //                    System.out.println("Value set " + value1);
                                }
                                if (extract.equals("string")) {
                                    String vs = word.substring(start1 + 1, end1);
                                    Class[] signature = new Class[1];
                                    signature[0] = String.class;
                                    Method mr = null;
                                    try {
                                        mr = cls.getMethod(fieldname, signature);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    try {
                                        mr.invoke(returnobject, vs);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    Method mg1 = null;
                                    try {
                                        mg1 = cls.getMethod(fieldname1, null);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    String value1 = "";
                                    try {
                                        value1 = (String) mg1.invoke(returnobject);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                   //                 System.out.println("Value set " + value1);
                                }
                                if (extract.equals("double")) {
                                    try {
                                        double vs = Double.parseDouble(word.substring(start1 + 1, end1));
                                        Class[] signature = new Class[1];
                                        signature[0] = double.class;
                                        Method mr = null;
                                        try {
                                            mr = cls.getMethod(fieldname, signature);
                                        } catch (NoSuchMethodException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (SecurityException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        try {
                                            mr.invoke(returnobject, vs);
                                        } catch (IllegalAccessException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IllegalArgumentException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (InvocationTargetException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        Method mg1 = cls.getMethod(fieldname1, null);
                                        double value1 = 0;
                                        try {
                                            value1 = (double) mg1.invoke(returnobject);
                                        } catch (IllegalAccessException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IllegalArgumentException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (InvocationTargetException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                    //                    System.out.println("Value set " + value1);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                if (extract.equals("short")) {
                                    short vs = Short.parseShort(word.substring(start1 + 1, end1));
                                    Class[] signature = new Class[1];
                                    signature[0] = short.class;
                                    Method mr = null;
                                    try {
                                        mr = cls.getMethod(fieldname, signature);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    try {
                                        mr.invoke(returnobject, vs);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    Method mg1 = null;
                                    try {
                                        mg1 = cls.getMethod(fieldname1, null);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    short value1 = 0;
                                    try {
                                        value1 = (short) mg1.invoke(returnobject);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                  //                 System.out.println("Value set " + value1);
                                }
                                if (extract.equals("float")) {
                                    float vs = Float.parseFloat(word.substring(start1 + 1, end1));
                                    Class[] signature = new Class[1];
                                    signature[0] = float.class;
                                    Method mr = null;
                                    try {
                                        mr = cls.getMethod(fieldname, signature);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    try {
                                        mr.invoke(returnobject, vs);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    Method mg1 = null;
                                    try {
                                        mg1 = cls.getMethod(fieldname1, null);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    float value1 = 0;
                                    try {
                                        value1 = (float) mg1.invoke(returnobject);
                                    } catch (IllegalAccessException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IllegalArgumentException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (InvocationTargetException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
         //                           System.out.println("Value set " + value1);
                                }
                                if (extract.equals("char")) {
                                    try {
                                        char vs = (word.substring(start1 + 1, end1)).charAt(0);
                                        Class[] signature = new Class[1];
                                        signature[0] = char.class;
                                        Method mr = cls.getMethod(fieldname, signature);
                                        try {
                                            mr.invoke(returnobject, vs);
                                        } catch (IllegalAccessException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IllegalArgumentException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (InvocationTargetException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        Method mg1 = cls.getMethod(fieldname1, null);
                                        char value1 = '0';
                                        try {
                                            value1 = (char) mg1.invoke(returnobject);
                                        } catch (IllegalAccessException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IllegalArgumentException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (InvocationTargetException ex) {
                                            Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                  //                      System.out.println("Value set " + value1);
                                    } catch (NoSuchMethodException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SecurityException ex) {
                                        Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                        word = fp.readWord();
                    }
                   
                    return returnobject;
                    
                }
            }
 return null;
}
    
}
