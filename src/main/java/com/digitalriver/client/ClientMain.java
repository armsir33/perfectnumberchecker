package com.digitalriver.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import com.digitalriver.models.PerfectNumberReply;

public class ClientMain {

	public static void main(String[] args) {
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJaxbJsonProvider());

		WebClient client = WebClient.create("http://localhost:8080/rest/api", providers);
		
		PerfectNumberReply reply = invokePerfectNumberChecking(client, 28);
		System.out.println(reply);
		
		client.reset();
		
		Collection<Long> collection = invokeRangeNumberPerfectNumberChecking(client, 2, 10000);
		System.out.println(collection);
	}
	
	/*
	 * Browse URL: http://localhost:8080/rest/api/perfect/28
	 * 			   http://localhost:8080/rest/api/perfect/20160323
	 */
	public static PerfectNumberReply invokePerfectNumberChecking(WebClient client, long value) {
		client = client.accept("application/json").type("application/json").path("/perfect/" + value);

		PerfectNumberReply reply = client.get(PerfectNumberReply.class);
		return reply;
	}
	
	/*
	 * Browse URL: http://localhost:8080/rest/api/perfect/range?start=2&end=10000
	 * 			   http://localhost:8080/rest/api/perfect/range?start=128&end=12345
	 */
	public static Collection<Long> invokeRangeNumberPerfectNumberChecking(WebClient client, long start, long end) {
		client = client.accept("application/json").type("application/json").path("/perfect/range").query("start", start).query("end", end);

		@SuppressWarnings("unchecked")
		Collection<Long> numbers = client.get(Collection.class);
		return numbers;
	}
}
