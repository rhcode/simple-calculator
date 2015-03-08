package com.coverity.taqaedition.homework.tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coverity.taqaedition.homework.Main;


/**
 * Unit tests for Main class.
 */
public class MainTest {
	private final ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
	private String[] array = null;
	
	@Before
	public void setData() {
		array = new String[1];
		System.setOut(new PrintStream(consoleOutput));
	}
	
	@After
	public void cleanUp() {
		System.setOut(null);
	}
	
	@Test
	public void shouldCheckSpecialCharactersInInput() {
		array[0] = "add($,2)";
		Main.main(array);
		String str = "Check the operands specified for each of your operations\n"+
				"Please check log for further details";

		Assert.assertEquals(str, 
				consoleOutput.toString().trim());
	}
	
	@Test
	public void shouldCheckOrderOfBraces() {
		array[0] = "add)2,2(";
		Main.main(array);
		String str = "No input or wrongly formatted input";
		
		Assert.assertEquals(str, consoleOutput.toString().trim());
	}
	
	@Test
	public void shouldCheckNumberOfBraces() {
		array[0] = "add(2,2))";
		Main.main(array);
		String str = "No input or wrongly formatted input";
		Assert.assertEquals(str, consoleOutput.toString().trim());
	}
	
	@Test
	public void shouldCheckForMismatchingBraces() {
		array[0] = "add(2,2}";
		Main.main(array);
		String str = "No input or wrongly formatted input";
		Assert.assertEquals(str, consoleOutput.toString().trim());
	}
}
