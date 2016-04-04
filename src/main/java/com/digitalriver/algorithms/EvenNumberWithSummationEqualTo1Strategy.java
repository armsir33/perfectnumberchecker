package com.digitalriver.algorithms;

/*
 * Except 6, the summation of all even perfect number until single digit must be 1.
 * 28 -> 2+8=10 -> 1+0=1
 * 496 -> 4+9+6=19 -> 1+9=10 -> 1+0=1
 */
public class EvenNumberWithSummationEqualTo1Strategy implements PerfectNumberStrategy {

	@Override
	public boolean checkIfPerfectNumber(long val) {
		// Check exceptions
		if(val == 6)
			return true;
		if(val==1)
			return false;
		
		long ret = val;
		do {
			ret = calculateOneRoundSummation(ret);
		} while (ret >= 10);

		return ret==1;
	}

	private long calculateOneRoundSummation(long val) {
		long summation = 0;

		while (true) {
			if (val >= 10) {
				summation += val % 10;
				val = val / 10;
			} else {
				summation += val;
				break;
			}
		}
		return summation;
	}
}
