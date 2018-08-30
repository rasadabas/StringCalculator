package com.string.calc;

import static org.junit.Assert.assertEquals;
import com.string.calc.service.StringCalculatorService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test for simple StringCalculatorService.
 */
public class TestStringCalculator {

	private StringCalculatorService strCalc;

	@Before
	public void setUp() throws Exception {
		strCalc = new StringCalculatorService();
	}

	@Test
	public void emptyStringReturn0() {

		assertEquals(0, strCalc.calculator(""));
	}

	@Test
	public void singleNumberReturnItself() {
		assertEquals(23, strCalc.calculator("23"));
	}

	@Test
	public void sumOfTwoNumbers() {
		assertEquals(47, strCalc.calculator("23,24"));
	}

	@Test
	public void sumOfMultipleNumbers() {
		assertEquals(98, strCalc.calculator("23,24,25,26"));
	}

	@Test
	public void newLineAsDelimiter() {
		assertEquals(47, strCalc.calculator("23\n24"));
	}

	@Test
	public void userDefinedDelimiter() {
		assertEquals(47, strCalc.calculator("//,\n23,24"));
	}

	@Test
	public void userDefinedWithRegexKeywordDelimiter() {
		assertEquals(47, strCalc.calculator("//?\n23?24"));
	}

	@Rule
	public ExpectedException expException = ExpectedException.none();

		@Test
	public void exceptionOnNegitiveNumbers() {
		expException.expect(RuntimeException.class);
		expException.expectMessage("Addition of Nagative Numbers not Allowed -23");
		strCalc.calculator("-23");
	}

	@Test
	public void exceptionOnMultipleNegitiveNumbers() {
		expException.expect(RuntimeException.class);
		expException.expectMessage("Addition of Nagative Numbers not Allowed -23 -26");
		strCalc.calculator("-23,24,-26");
	}

	@Test
	public void maxRangeOfNumbers() {
		assertEquals(23, strCalc.calculator("23,1023"));
	}

	@Test
	public void customizedMulticharacterDelimiter() {
		assertEquals(47, strCalc.calculator("//[;;;]\n23;;;24"));
	}

	@Test
	public void customizedMultipleSinglecharacterDelimiter() {
		assertEquals(72, strCalc.calculator("//[;][?]\n23;24?25"));
	}

	@Test
	public void customizedMultipleMulticharacterDelimiter() {
		assertEquals(72, strCalc.calculator("//[;;;][???]\n23;;;24???25"));
	}
}
