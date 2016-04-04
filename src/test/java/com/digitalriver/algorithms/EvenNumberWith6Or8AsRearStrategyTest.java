package com.digitalriver.algorithms;

import org.junit.Test;
import junit.framework.Assert;

public class EvenNumberWith6Or8AsRearStrategyTest {

	@Test
	public void testCheckIfPerfectNumber() {
		EvenNumberWith6Or8AsRearStrategy strategy = new EvenNumberWith6Or8AsRearStrategy();

		Assert.assertFalse(strategy.checkIfPerfectNumber(0));
		Assert.assertTrue(strategy.checkIfPerfectNumber(8));
		Assert.assertTrue(strategy.checkIfPerfectNumber(28));
		Assert.assertTrue(strategy.checkIfPerfectNumber(498));
		Assert.assertTrue(strategy.checkIfPerfectNumber(18128));
		Assert.assertFalse(strategy.checkIfPerfectNumber(8383));
		Assert.assertTrue(strategy.checkIfPerfectNumber(33550336));
		Assert.assertFalse(strategy.checkIfPerfectNumber(858986905L));
		Assert.assertTrue(strategy.checkIfPerfectNumber(148389056L));
	}

}
