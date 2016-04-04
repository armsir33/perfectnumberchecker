package com.digitalriver.algorithms;

import org.junit.Test;
import junit.framework.Assert;

public class PerfectNumberUtilsTest {

	@Test
	public void testCheck() {
		PerfectNumberUtils perfectNumberUtils = PerfectNumberUtils.getSingletonInstance();
		
		Assert.assertFalse(perfectNumberUtils.check(1));
		Assert.assertTrue(perfectNumberUtils.check(28));
		Assert.assertFalse(perfectNumberUtils.check(56));
		Assert.assertFalse(perfectNumberUtils.check(2828));
		Assert.assertFalse(perfectNumberUtils.check(12749));
		Assert.assertTrue(perfectNumberUtils.check(8589869056L));
	}

}
