package com.digitalriver.algorithms;

import org.junit.Test;
import junit.framework.Assert;

public class EvenNumberWithOddSequentialNumberExponent3StrategyTest {

	@Test
	public void testCheckIfPerfectNumber() {
		EvenNumberWithOddSequentialNumberExponent3Strategy strategy = new EvenNumberWithOddSequentialNumberExponent3Strategy();
		
		Assert.assertFalse(strategy.checkIfPerfectNumber(1));
		Assert.assertTrue(strategy.checkIfPerfectNumber(28));
		Assert.assertFalse(strategy.checkIfPerfectNumber(56));
		Assert.assertFalse(strategy.checkIfPerfectNumber(2828));
		Assert.assertFalse(strategy.checkIfPerfectNumber(12749));
		Assert.assertTrue(strategy.checkIfPerfectNumber(8589869056L));
	}

}
