package com.digitalriver.algorithms;

/*
 * Check if given number can be formated as below or not
 * 6=1+2+3 
 * 28=1+2+3+4+5+6+7 
 * 496=1+2+3+…+30+31
 * 
 * Note: it takes quite long to calculate for larger numbers. Discard!
 */
public class EvenNumberWithContinuousSummationStrategy implements PerfectNumberStrategy {

	@Override
	public boolean checkIfPerfectNumber(long val) {
		long summation = 0;
		for (long i = 1; i <= val / 2; i++) {
			if (val % i == 0) {
				summation += i;
			}
		}

		if (summation == val)
			return true;
		else
			return false;
	}
	
}
