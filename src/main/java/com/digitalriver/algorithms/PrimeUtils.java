package com.digitalriver.algorithms;

import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class PrimeUtils {
	
	// Cache which avoids to repeat calculating and checking if prime
	private boolean[] numbers = new boolean[50000];

	public static PrimeUtils singletonInstance = null;
	
	private PrimeUtils() {}
	
	public static PrimeUtils getPrimeUtilSingletonInstance() {
		if(singletonInstance == null) {
			singletonInstance = new PrimeUtils();
			singletonInstance.initAndCalculate();
		}
		return singletonInstance;
	}
	
	/*
	 * Find out all the primes from the fixed length array. 
	 * 1. Initiate arrays by assigning all the elements as TRUE 
	 * 2. Assign 0, 1 as FALSE 
	 * 3. Eliminate elements which are products of other elements by assigning FALSE 
	 * 4. Thus, if the value of elements in the array is equal to TRUE, they are prime.
	 */
	private void initAndCalculate() {
		// Initiate array
		Arrays.fill(numbers, true);
		// 0, 1 must not be prime
		numbers[0] = numbers[1] = false;
		for (int i = 2; i < numbers.length; i++) {
			if (numbers[i]) {
				// Eliminate elements with multiplier
				for (int j = 2; i * j < numbers.length; j++) {
					numbers[i * j] = false;
				}
			}
		}
	}

	/*
	 * Predicate about whether given value is prime or not
	 */
	public boolean isPrime(long n) {
		if (n < numbers.length)
			return numbers[(int)n];
		else {
			// Check if even number
			if (n % 2 == 0)
				return false;

			// Check all the elements of less than sqrt of n
			for (int i = 3; i * i <= n; i += 2) {
				if (n % i == 0)
					return false;
			}
			
			return true;
		}

	}
}
