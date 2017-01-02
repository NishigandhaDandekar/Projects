/**
 *This class is used to print statements based on 
 * the Debug value specified during execution
 * @author Nishigandha
 */
package primeThreads.util;
public class Logger{


    public static enum DebugLevel {NO_OUTPUT,DS_CONTENT,DATA_ENTRY,RUN_METHOD,CONSTRUCTOR };

    private static DebugLevel debugLevel;

/**
 * This method takes an integer and sets its 
 * corresponding debugLevel
 * @param levelIn 
 */
    public static void setDebugValue (int levelIn) {
        switch (levelIn) {
          case 0: debugLevel = DebugLevel.NO_OUTPUT; break;  
          case 1: debugLevel = DebugLevel.DS_CONTENT; break;  
          case 2: debugLevel = DebugLevel.DATA_ENTRY; break;  
          case 3: debugLevel = DebugLevel.RUN_METHOD; break;  
          case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
        }
    }
/**
 * This method  sets the  debugLevel
 * @param levelIn 
 */
    public static void setDebugValue (DebugLevel levelIn) {
        debugLevel = levelIn;
    }
/**
 * Method takes as input the message to be printed and the debugLevel
 * if the debugLevel is same as the debugLevel set during
 * execution then the message is displayed
 * @param message
 * @param levelIn 
 */
 // @return None
	 public static void writeMessage (String message  ,DebugLevel levelIn ) {
        if (levelIn == debugLevel)
            System.out.println(message);     
    }
/**
 * Returns a string specifying the set debugLevel
 * @return 
 */
    public String toString() {
        return "Debug Level is " + debugLevel;
    }
}


