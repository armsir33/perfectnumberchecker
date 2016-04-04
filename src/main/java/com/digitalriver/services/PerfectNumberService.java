package com.digitalriver.services;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalriver.algorithms.PerfectNumberUtils;
import com.digitalriver.models.PerfectNumberReply;

@Service
public class PerfectNumberService {
	public PerfectNumberReply getPerfectNumberFeedback(String requestNo) {
		PerfectNumberUtils perfectNumberUtils = PerfectNumberUtils.getSingletonInstance();
		boolean isPerfect = perfectNumberUtils.check(Long.parseLong(requestNo));
		PerfectNumberReply perfectNumberReply = new PerfectNumberReply();
		perfectNumberReply.setRequestNo(requestNo);
		perfectNumberReply.setResult(isPerfect);
		return perfectNumberReply;
	}

	public Collection<Long> getRangeOfPerfectNumbers(String start, String end) {
		PerfectNumberUtils perfectNumberUtils = PerfectNumberUtils.getSingletonInstance();
		List<Long> list = perfectNumberUtils.findRangePerfectNumbers(Long.parseLong(start), Long.parseLong(end));
		return list;
	}

}
