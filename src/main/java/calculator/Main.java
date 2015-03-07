package calculator;

import org.apache.log4j.Logger;

/**
 * This the entry-point class for the calculator. 
 * Orchestrates calls to the Solver class to solve 
 * the input string
 */
public class Main 
{
	final static Logger logger = Logger.getLogger(Main.class);
	
    public static void main(String[] args) {
        logger.info("Starting calculator");
        logger.debug("The input received is : " + args[0]);
        boolean isInputCorrect = sanityCheck(args[0]);
        if (isInputCorrect == false)
        	return;
        
        
    }
    
    private static boolean sanityCheck(String input) {
    	if (input == null || input.trim().length() == 0) {
        	logger.error("No input provided");
        	return false;
        }
    	
    	char curChar, prevChar = 'x';
    	int bracesCount = 0;
    	for (int i = 0; i < input.length(); i++) {
    		curChar = input.charAt(i);
    		switch(curChar) {
    			case '(' : bracesCount++;
    					   break;
    					   
    			case ')' : bracesCount--;
    					   if (bracesCount < 0) {
    						   logger.error("Input is not well formatted. Check braces");
    						   return false;
    					   }
    					   break;
    		
    			case ',' : if (i > 0 && prevChar == ',') {
    							logger.error("Extra commas present in input");
    							return false;
    					   }
    			
    			default : break;
    		}
    		prevChar = curChar;
    	}
    	return true;
    }
}
