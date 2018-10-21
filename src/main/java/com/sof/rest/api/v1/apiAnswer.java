package com.sof.rest.api.v1;

import com.sof.mysqlPOJO.Answer;
import com.sof.dao.AnswerDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/answer")
public class apiAnswer {


    @GET
    @Path("/list")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Answer> getAnswerDetail() {
        AnswerDAO dao = new AnswerDAO();
        List answers = dao.getAnswerList();
        return answers;
    }


    @POST
    @Path("/create")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response addAnswer(Answer answer){

        AnswerDAO dao = new AnswerDAO();
        dao.addAnswer(answer);

        return Response.ok().build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response updateAnswer(@PathParam("id") int id, Answer answer){
        AnswerDAO dao = new AnswerDAO();
        int count = dao.updateAnswer(id, answer);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response deleteAnswer(@PathParam("id") int id){
        AnswerDAO dao = new AnswerDAO();
        int count = dao.deleteAnswer(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}
