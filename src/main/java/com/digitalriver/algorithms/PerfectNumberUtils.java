package com.digitalriver.algorithms;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerfectNumberUtils {

	private static final Logger logger = LoggerFactory.getLogger(PerfectNumberUtils.class);
	
	private final static int MAX_SIGNED_LONG_VAL_INDEX_IN_JAVA = 63;
	
	private final static long MIN_VAL_IN_CACHED_LIST = 0;
	private static long MAX_VAL_IN_CACHED_LIST = 0;
	
	private final static long INIT_MAX_VAL_IN_CACHED_LIST = 10000;

	private static long CURRENT_MAX_INDEX_IN_CACHED = 0;
	
	private static PerfectNumberUtils singletonInstance = null;
	
	private PerfectNumberStrategy strategy;

	// Cache which avoids to repeat checking if given numbers are perfect or not
	private static List<Long> cachedPerfectNumberList = new ArrayList<Long>();

	private PerfectNumberUtils() {}
	
	public static PerfectNumberUtils getSingletonInstance() {
		if(singletonInstance == null) {
			singletonInstance = new PerfectNumberUtils();
			cachedPerfectNumberList = singletonInstance.findRangePerfectNumbers(MIN_VAL_IN_CACHED_LIST, INIT_MAX_VAL_IN_CACHED_LIST);
		}
		return singletonInstance;
	}
	
	/* Entry point to check if given numbers are perfect or not
	 * Regarding to characters of perfect number, it filters out most of values.
	 */
	public boolean check(long val) {
		// Exception
		if(val < MIN_VAL_IN_CACHED_LIST)
			return false;
		
		// Search from cached perfect number list
		if(val >= MIN_VAL_IN_CACHED_LIST && val <= MAX_VAL_IN_CACHED_LIST) {
			return hasPerfectNumberInCachedList(val);
		}
		
		// Filtering by EvenNumberWith6Or8AsRearStrategy
		strategy = new EvenNumberWith6Or8AsRearStrategy();
		if(!strategy.checkIfPerfectNumber(val))
			return false;
		
		// Filtering by EvenNumberWithSummationEqualTo1Strateger
		strategy = new EvenNumberWithSummationEqualTo1Strategy();
		if(!strategy.checkIfPerfectNumber(val))
			return false;
		
		// Filtering by EvenNumberWithContinuousSummationStrategy. Note: discard due to slow performance!
		/*
		strategy = new EvenNumberWithContinuousSummationStrategy();
		if(!strategy.checkIfPerfectNumber(val))
			return false;
		*/
		
		// Filter by EvenNumberWithOddSequentialNumberExponent3Strategy
		strategy = new EvenNumberWithOddSequentialNumberExponent3Strategy();
		if(!strategy.checkIfPerfectNumber(val))
			return false;
		
		
		List<Long> givenRangeOfPerfectNumberList = findRangePerfectNumbers(MAX_VAL_IN_CACHED_LIST + 1, val);
		if(givenRangeOfPerfectNumberList == null)
			return false;
		else {
			// Extend the cached list of perfect numbers and will hit cache next time.
			MAX_VAL_IN_CACHED_LIST = val;
			cachedPerfectNumberList.addAll(givenRangeOfPerfectNumberList);
			return true;
		}
	}

	// Quickest check if given value is perfect. Search from cached list
	public boolean hasPerfectNumberInCachedList(long val) {
		return cachedPerfectNumberList.contains(val);
	}

	/*
	 * Find out a list of perfect numbers regarding to given range
	 */
	public List<Long> findRangePerfectNumbers(long start, long end) {
		// start must not be less than end and start must not be less than MIN_VAL
		if (start > end && start < MIN_VAL_IN_CACHED_LIST) {
			return null;
		}
		
		// Can find the range of perfect numbers in the cache list
		if(end <= MAX_VAL_IN_CACHED_LIST) {
			return getSubList(start, end);
		}

		PrimeUtils primeUtils = PrimeUtils.getPrimeUtilSingletonInstance();
		// Fetch partial list of perfect numbers from cache
		List<Long> list = getSubList(start, MAX_VAL_IN_CACHED_LIST);
		
		long tmp = 0;

		for (long i = CURRENT_MAX_INDEX_IN_CACHED+1;i<=MAX_SIGNED_LONG_VAL_INDEX_IN_JAVA; i++) {
			logger.debug("Checking 2^index " + i);
			// Check if Mersenne primes regarding to equation 2^i-1 prime or not
			tmp = (long) Math.pow(2, i) - 1;
			boolean isPrime = primeUtils.isPrime(tmp);
			
			tmp = (long) (Math.pow(2, i - 1) * tmp);
			
			// update max value and index for cached list of perfect numbers
			CURRENT_MAX_INDEX_IN_CACHED = i;
			MAX_VAL_IN_CACHED_LIST = tmp;
			
			// If Mersenne primes
			if (isPrime) {
				logger.debug("**** " + i + " is the index of perfect number");
				
				// Add perfect number to cached list
				cachedPerfectNumberList.add(tmp);
				
				// Value should be within the given range
				if (tmp >= start && tmp <= end)
					list.add(tmp);
				else if (tmp < start)
					continue;
				else {
					break;
				}
			}
		}
		return list;
	}
	
	private List<Long> getSubList(long start, long end) {
		List<Long> list = new ArrayList<Long>();
		
		if(start > MAX_VAL_IN_CACHED_LIST)
			return list;
					
		for(Long perfectNumber: cachedPerfectNumberList) {
			if(perfectNumber >= start && perfectNumber <= end) {
				list.add(perfectNumber);
			}
		}
		
		return list;
	}
}
