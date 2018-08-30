package com.string.calc.util;

import java.util.regex.Pattern;

import com.string.calc.constants.CalculatorConstants;

public class CalculatorUtil {

	public String stringQuote(String delimiter) {
		return Pattern.quote(delimiter);
	}
	
	public int refineNumbersToBeAdded(String inputStringNumbers, StringBuffer nagativeNumbers) {
		int integerNumber = Integer.parseInt(inputStringNumbers);
		if (integerNumber < 0) {
			nagativeNumbers.append(" " + integerNumber);
		}
		if (integerNumber > CalculatorConstants.MAX_RANGE) {
			integerNumber = 0;
		}
		return integerNumber;
	}
	
	public void nagativeNumbersCase(StringBuffer nagativeNumbers) {
		if (nagativeNumbers.length() > 0) {
			throw new RuntimeException(CalculatorConstants.NEGATIVE_NUM_EXP + nagativeNumbers);
		}
	}
}
