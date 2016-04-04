package com.digitalriver.algorithms;

import org.junit.Assert;
import org.junit.Test;

public class PrimeUtilsTest {

	@Test
	public void testIsPrime() {
		PrimeUtils primeUtils = PrimeUtils.getPrimeUtilSingletonInstance();
		
		Assert.assertFalse(primeUtils.isPrime(108));
		Assert.assertTrue(primeUtils.isPrime(1049));
		Assert.assertTrue(primeUtils.isPrime(3559));
		Assert.assertFalse(primeUtils.isPrime(0));
		Assert.assertFalse(primeUtils.isPrime(1));
		Assert.assertTrue(primeUtils.isPrime(103357));
	}

}
