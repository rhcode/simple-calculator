package com.coverity.taqaedition.homework.tests;

import java.util.EmptyStackException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coverity.taqaedition.homework.Solver;
import com.coverity.taqaedition.homework.exceptions.UnknownOperationException;


public class SolverTest {
	private Solver solver;
	
	@Before
	public void initializeSolver() {
		solver = new Solver();
	}
	
	@Test
	public void shouldWorkWithExpectedCommands() 
			throws UnknownOperationException{
		Assert.assertEquals(12, solver.solve("mult(add(2,2),div(9,3))"));
	}
	
	@Test (expected = UnknownOperationException.class)
	public void shouldRecognizeCaseOfUnknownOperations() 
			throws UnknownOperationException {
		solver.solve("let(a,5,add(a,a))");
	}
	
	@Test (expected = EmptyStackException.class)
	public void shouldCheckForUnbalancedBraces() 
		throws UnknownOperationException {
		solver.solve("add(");
	}
	
	@Test (expected = EmptyStackException.class)
	public void shouldCheckWrongNumberOfOperands() 
		throws UnknownOperationException {
		solver.solve("add(2)");
	}
	
	@Test (expected = ArithmeticException.class)
	public void shouldCheckDivideByZero() 
		throws UnknownOperationException {
		solver.solve("add(2, div(3,0)");
	}
	
	@Test (expected = ArithmeticException.class)
	public void shouldCheckExtraOperandsForAnOperation() 
		throws UnknownOperationException {
		solver.solve("add(2,2,2)");
	}
	
	@Test
	public void shouldWorkWhenSpacesPresentInInput() 
		throws UnknownOperationException {
		Assert.assertEquals(4, solver.solve("add(2, 2)"));
	}
	
	@Test (expected = NumberFormatException.class)
	public void shouldNotWorkForNonIntegers() 
		throws UnknownOperationException {
		solver.solve("add(21474836473434,2)");
	}
	
	@Test (expected = NumberFormatException.class)
	public void shouldNotWorkForMixedInput() 
		throws UnknownOperationException {
		solver.solve("add(234w03i4r349093845093,2)");
	}
	
	@Test (expected = EmptyStackException.class)
	public void shouldNotWorkForSpecialCharacters() 
		throws UnknownOperationException {
		solver.solve("add($,2)");
	}
}
