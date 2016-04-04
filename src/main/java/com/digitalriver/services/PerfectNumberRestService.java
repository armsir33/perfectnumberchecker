package com.digitalriver.services;

import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import com.digitalriver.models.PerfectNumberReply;

@Path("/perfect")
public class PerfectNumberRestService {

	@Inject
	private PerfectNumberService perfectNumberService;
	
	@Produces({"application/json"})
	@GET
	@Path("{no}")
	public PerfectNumberReply checkIfPerfect(@PathParam("no") @DefaultValue("1") final String no) {
		return perfectNumberService.getPerfectNumberFeedback(no);
	}
	
	@Produces({"application/json"})
	@GET
	@Path("/range")
	public Collection<Long> getRangeOfPerfectNumbers(@QueryParam("start") @DefaultValue("0") final String start, @QueryParam("end") @DefaultValue("100000") final String end) {
		return perfectNumberService.getRangeOfPerfectNumbers(start, end);
	}
}
