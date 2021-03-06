package com.string.calc.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.string.calc.constants.CalculatorConstants;
import com.string.calc.util.CalculatorUtil;

public class StringCalculatorService {
	// A utility class to perform util functions 
	CalculatorUtil calculatorUtil = new CalculatorUtil();
	// Main calculator Method
	public int calculator(String inputString) {

		Matcher match = Pattern.compile(CalculatorConstants.USER_DEFINED_DELIMITERS).matcher(inputString);
		String delimiters = getDelimitersFromInputString(match);
		String inputStringNumbers = getNumbersFromInputString(inputString, match.reset());
		return getSumOfNumbers(getNumbersToSum(inputStringNumbers, delimiters));
	}
	// Method to get Delimiters from the Input String
	private String getDelimitersFromInputString(Matcher match) {

		if (match.find()) {
			return userDefinedDilimiter(match);

		} else {
			return CalculatorConstants.DEFAULT_DELIMITERS;
		}
	}
	// Method to get Numbers to be added from the Input String 
	private String getNumbersFromInputString(String inputString, Matcher match) {
		if (match.find()) {
			return match.group(CalculatorConstants.NUMBER_STRING_GROUP);
		} else {
			return inputString;
		}
	}
	// Method returns String array of the numbers needs to be added
	private String[] getNumbersToSum(String inputStringNumbers, String delimiters) {

		if (inputStringNumbers.isEmpty()) {
			return new String[0];
		} else
			return inputStringNumbers.split(delimiters);
	}
	//Method to get the actual sum of the numbers 
	private int getSumOfNumbers(String[] numbers) {
		int sum = 0;
		StringBuffer nagativeNumbers = new StringBuffer();
		for (String num : numbers) {
			sum = sum + calculatorUtil.refineNumbersToBeAdded(num, nagativeNumbers);
		}
		calculatorUtil.nagativeNumbersCase(nagativeNumbers);
		return sum;
	}
	//Method to find User defined or customized Inputs 
	private String userDefinedDilimiter(Matcher match) {
		if (match.group(CalculatorConstants.SINGLE_CHARACTER_DELIMITER_STRING_GROUP) != null) {
			return getCustomizedSingleDelimiter(match);
		} else {
			return getCustomizedMultipleDelimiters(match);
		}

	}

	private String getCustomizedMultipleDelimiters(Matcher match) {
		String delimiters = match.group(CalculatorConstants.CUSTOMIZED_ALL_DELIMITER_STRING_GROUP);
		Matcher matchDelimiter = Pattern.compile(CalculatorConstants.CUSTOMIZED_DELIMITERS_PATTERN).matcher(delimiters);
		StringBuffer allDelimiters = new StringBuffer();

		while (matchDelimiter.find()) {
			addCustomDelimiter(allDelimiters, matchDelimiter.group(CalculatorConstants.CUSTOMIZED_ONE_DELIMITER_STRING_GROUP));
		}

		return allDelimiters.toString();
	}

	private String getCustomizedSingleDelimiter(Matcher match) {
		return calculatorUtil.stringQuote(match.group(CalculatorConstants.SINGLE_CHARACTER_DELIMITER_STRING_GROUP));
	}

	private void addCustomDelimiter(StringBuffer allDelimiters, String delimiter) {
		if (allDelimiters.length() == 0) {
			allDelimiters.append(calculatorUtil.stringQuote(delimiter));
		} else {
			allDelimiters.append("|" + calculatorUtil.stringQuote(delimiter));
		}
	}

}
