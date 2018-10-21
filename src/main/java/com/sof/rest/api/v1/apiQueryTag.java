package com.sof.rest.api.v1;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sof.mysqlPOJO.Answer;

@Path("/query")
public class apiQueryTag {
	
	@GET
    @Path("/list")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Answer> getAnswerDetail(){
		return null;
	}


}
