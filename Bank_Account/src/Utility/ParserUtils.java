package Utility;

import java.util.Scanner;

public class ParserUtils {
	private Scanner in;
	public ParserUtils() { this.in = new Scanner(System.in); }
	
	public int getValidInt() {
		int selection;
		while (true) {
			String input = in.next();
			try {
				selection = Integer.parseInt(input);
				return selection;
			} catch (NumberFormatException ne) {
				System.out.println("Invalid input. Please enter a number:");
			}
		}
		

	}
	
	public double getValidDouble() {
		double selection;
		while (true) {
			String input = in.next();
			try {
				selection = Double.parseDouble(input);
				return selection;
			} catch (NumberFormatException ne) {
				System.out.println("Invalid input. Please enter a number:");
			}
		}
	}
	
	public String getValidSSN() {
		System.out.println("Enter your social security number:");
		int ssn = this.getValidInt();
		while (Integer.toString(ssn).length() != 9) {
			System.out.println("Must be nine digits:");
			ssn = this.getValidInt();
		}
		return Integer.toString(ssn);
	}
	
}
