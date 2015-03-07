package calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.log4j.Logger;

/*
 * This class solves the given input string and obtains
 * the required result
 */
public class Solver {
	final static Logger logger = Logger.getLogger(Solver.class);
	
	//Map of all valid operations that can be performed TODO : let command
	private Map<String, String> operations = null;
	
	//Stack to save all the operations and operands
	private Stack<String> tokenStack = null;
	
	//A temporary stack needed to solve operations which further have other operations as operands 
	private Stack<Integer> tempStack = null;
	
	private int bracesCount = 0;
	
	public Solver() {
		operations = new HashMap<String, String>();
		operations.put("add","+");
		operations.put("sub","-");
		operations.put("mult","*");
		operations.put("div","/");
		
		tokenStack = new Stack<String>();
		tempStack = new Stack<Integer>();
	}
	
	
	public void populateOperandAndOperatorStack (String input) {
		for (int i = 0; i < input.length();) {
			char curChar = input.charAt(i);
			if (Character.isAlphabetic(curChar)) {
				String command = getCommand(input.substring(i));
				i += command.length();
				tokenStack.add(operations.get(command));
			}
			else if (Character.isDigit(curChar)) {
				String number = getNumber(input.substring(i));
				i += number.length();
				tokenStack.add(number);
			}
			else 
				i++;
		}
	}
	
	private String getCommand(String substr) {
		int indexOfOpeningBrace = substr.indexOf("(");
		return substr.substring(0, indexOfOpeningBrace);
	}
	
	private String getNumber(String substr) {
		int indexOfComma = substr.indexOf(",");
		int indexOfClosingBrace = substr.indexOf(")");
		int lesserValueIndex = 0;
		if (indexOfComma > 0)
			lesserValueIndex = Math.min(indexOfComma, indexOfClosingBrace);
		else
			lesserValueIndex = indexOfClosingBrace;
		return substr.substring(0, lesserValueIndex);
	}
}
