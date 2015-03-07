package calculator;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.apache.log4j.Logger;

/*
 * This class solves the given input string and obtains
 * the required result
 */
public class Solver {
	final static Logger logger = Logger.getLogger(Solver.class);
	
    //Tracks the index in the input from which processing still needs to be done 
	private int startIndex;
	
	//Set of all valid operations that can be performed TODO : let command
	private Set<String> operations = null;
	
	//Stack to save all the operations and operands
	private Stack<String> tokenStack = null;
	
	//A temporary stack needed to solve operations which further have other operations as operands 
	private Stack<Integer> tempStack = null;
	
	private int bracesCount = 0;
	
	public Solver() {
		startIndex = 0;
		
		operations = new HashSet<String>();
		operations.add("add");
		operations.add("sub");
		operations.add("mult");
		operations.add("div");
		
		tokenStack = new Stack<String>();
		tempStack = new Stack<Integer>();
	}
	
	//TODO : Need to complete
	public int solve (String input) {
		for (int i = 0; i < input.length(); i++) {
			char curChar = input.charAt(i);
			if (Character.isLetterOrDigit(curChar)) {
				String command = input.substring(startIndex, i+1);
				if (operations.contains(command)) {
				
				}
			
			}
		}
		return 1;
	}
}
