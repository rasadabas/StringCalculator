package com.string.calc;

import java.util.Scanner;

import com.string.calc.service.StringCalculatorService;

public class StringCalculatorApplication {

	// Object of scanner class to scan Inputs
	private final static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		String playAgain;
		do {
			System.out.println("Starting String Calculator ");
			System.out.println("Please provide the Numbers in the string format to add");
			StringCalculatorService stringCalculatorService = new StringCalculatorService();
			String inputString = scanner.nextLine();
			int sum = stringCalculatorService.calculator(inputString);
			System.out.println("The sum of the numbers are " + sum);
			// option to play again after one complete game
			System.out.print("Calculate again [y/N]? ");
			playAgain = scanner.nextLine();
		} while (playAgain.equalsIgnoreCase("y") || playAgain.equalsIgnoreCase("yes"));
	}
}
