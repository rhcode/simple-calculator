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
        if (args[0] == null || args[0].trim().length() == 0) {
        	logger.error("No input provided");
        	return;
        }
        
        logger.debug("The input received is : " + args[0]);
        
    }
}
