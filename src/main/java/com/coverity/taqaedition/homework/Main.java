package com.coverity.taqaedition.homework;

import java.util.EmptyStackException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.coverity.taqaedition.homework.exceptions.UnknownOperationException;

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
        boolean isInputCorrect = sanityCheck(args);
        if (isInputCorrect == false) {
        	String message = "No input or wrongly formatted input";
        	System.out.println(message);
        	return;
        }
        String input = args[0];
        logger.debug("The input received is : " + input);
        callSolver(input);
        logger.info("Stopping calculator");
    }    
    
    /**
     * All the sanity checks on the user input go here
     * @param input
     * @return
     */
    private static boolean sanityCheck(String[] args) {
    	logger.info("Performing input sanity check");
    	if (args == null || args.length == 0 || args[0].trim().length() == 0) {
        	logger.error("No input provided");
        	return false;
        }
    	String input = args[0];
    	//Temporarily assign a random character value to prevChar. This gets changed 
    	//after the first character is parsed
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
    	if (bracesCount == 0)
    		return true;
    	else
    		return false;
    }
    
    /**
     * Creates a new Solver class instance and calls it to perform 
     * calculation on the provided input. 
     * @param input
     */
    private static void callSolver(String input) {
    	logger.info("Input seems okay. Performing calculation of input");
    	Solver solver = new Solver();
        String errorMsg = "\nPlease check log for further details";
        try {
        	int result = solver.solve(input);
        	System.out.println(result);
        }
        catch (UnknownOperationException exception) {
        	System.out.println("Unknown operation provided" + errorMsg);
        	logger.log(Level.ERROR, exception.getMessage());
        }
        catch (EmptyStackException exception) {
        	String message = "Check the operands specified "
        			+ "for each of your operations";
        	System.out.println(message + errorMsg);
        	logger.log(Level.ERROR, message, exception);
        }
        catch (ArithmeticException exception) {
        	String message = "An error ocurred during calculation";
        	System.out.println(message + errorMsg);
        	logger.log(Level.ERROR, exception);
        }
        catch (NumberFormatException exception) {
        	String message = "Floating point or non-Integer operands provided";
        	System.out.println(message + errorMsg);
        	logger.log(Level.ERROR, message, exception);
        }
        catch (Exception exception) {
        	String message = "Unexpected exception has occurred";
        	System.out.println(message + errorMsg);
        	logger.log(Level.ERROR, message, exception);
        }
    }
}
