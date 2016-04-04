package com.digitalriver.algorithms;

/*
 * Check given value by summation of sequential number of exponent 3
 * 28=1^3+3^3
 * 496=1^3+3^3+5^3+7^3
 * 8128=1^3+3^3+5^3+...+15^3
 */
public class EvenNumberWithOddSequentialNumberExponent3Strategy implements PerfectNumberStrategy {

	@Override
	public boolean checkIfPerfectNumber(long val) {
		// Exclude exceptions
		if(val <= 1)
			return false;
		
		long summation = 0;
		for(long i=1;;i=i+2) {
			summation += Math.pow(i, 3);
			if(summation == val)
				return true;
			if(summation >= val)
				return false;
		}
	}

}
