package com.coverity.taqaedition.homework;

import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.coverity.taqaedition.homework.exceptions.UnknownOperationException;

/*
 * This class solves the given input string and obtains
 * the required result
 */
public class Solver {
	final static Logger logger = Logger.getLogger(Solver.class);
	
	//Set of all valid operations that can be performed TODO : let command
	private Set<String> operations = null;
	
	//Stack to save all the operations and operands
	private Stack<String> tokenStack = null;
	
	//A temporary stack needed to solve operations which further have other operations as operands 
	private Stack<Integer> tempStack = null;
	
	public Solver() {
		operations = new HashSet<String>();
		operations.add("add");
		operations.add("sub");
		operations.add("mult");
		operations.add("div");
		
		tokenStack = new Stack<String>();
		tempStack = new Stack<Integer>();
	}
	
	public void populateOperandAndOperatorStack (String input) 
		throws UnknownOperationException {
		for (int i = 0; i < input.length();) {
			char curChar = input.charAt(i);
			if (Character.isAlphabetic(curChar)) {
				String command = getCommand(input.substring(i));
				if (operations.contains(command) == false) {
					throw new UnknownOperationException(command);
				}
				i += command.length();
				tokenStack.add(command);
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
	
	public int performCalculation() throws 
		EmptyStackException, ArithmeticException, NumberFormatException {
		while (tokenStack.isEmpty() == false) {
			String token = tokenStack.pop();			
			if (operations.contains(token)) {
				int operand1 = tempStack.pop();
				int operand2 = tempStack.pop();

				if (token.equals("mult")) {
					tempStack.push(operand1 * operand2);
				}
				else if (token.equals("div")) {
					tempStack.push(operand1 / operand2);
				}
				else if (token.equals("add")) {
					tempStack.push(operand1 + operand2);
				}
				else {
					tempStack.push(operand1 - operand2);
				}
			}
			else {
				int operand = Integer.parseInt(token);
				tempStack.push(operand);
			}
		}
		return tempStack.pop();
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
