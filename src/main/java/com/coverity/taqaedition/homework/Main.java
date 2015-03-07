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
        String input = args[0];
        logger.debug("The input received is : " + input);
        boolean isInputCorrect = sanityCheck(input);
        if (isInputCorrect == false) {
        	String message = "Wrongly formatted input";
        	System.out.println(message);
        	return;
        }
        Solver solver = new Solver();
        String errorMsg = "\nPlease check log for further details";
        try {
        	solver.populateOperandAndOperatorStack(input);
        	int result = solver.performCalculation();
        	System.out.println(result);
        }
        catch (UnknownOperationException exception) {
        	System.out.println("Unknown operation provided" + errorMsg);
        	logger.log(Level.ERROR, exception.getMessage());
        }
        catch (EmptyStackException exception) {
        	String message = "Check the number of operands specified "
        			+ "for each of your operations";
        	System.out.println(message + errorMsg);
        	logger.log(Level.ERROR, message, exception);
        }
        catch (ArithmeticException exception) {
        	String message = "An error ocurred during calcuation";
        	System.out.println(message + errorMsg);
        	logger.log(Level.ERROR, exception);
        }
        catch (NumberFormatException exception) {
        	String message = "Floating point operands may have been provided";
        	System.out.println(message + errorMsg);
        	logger.log(Level.ERROR, message, exception);
        }
        catch (Exception exception) {
        	String message = "Unexpected exception has occurred";
        	System.out.println(message + errorMsg);
        	logger.log(Level.ERROR, message, exception);
        }
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
    	if (bracesCount == 0)
    		return true;
    	else
    		return false;
    }
}
