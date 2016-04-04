package com.digitalriver.algorithms;

/*
 * The rear number of even perfect numbers must be 6 or 8
 */
public class EvenNumberWith6Or8AsRearStrategy implements PerfectNumberStrategy {

	@Override
	public boolean checkIfPerfectNumber(long val) {
		long lastDigit = val % 10;
		if (lastDigit == 6 || lastDigit == 8)
			return true;
		else
			return false;
	}

}
