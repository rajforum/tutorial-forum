package com.sof.rest.api.v1;

import com.sof.mysqlPOJO.Question;
import com.sof.dao.QuestionDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("question")
public class apiQuestion {

    @GET
    @Path("/list")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Question> getQuestionDetail() {
        QuestionDAO dao = new QuestionDAO();
        List questions = dao.getQuestionList();
        return questions;
    }


    @POST
    @Path("/create")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response addQuestion(Question question){

        QuestionDAO dao = new QuestionDAO();
        dao.addQuestion(question);

        return Response.ok().build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response updateQuestion(@PathParam("id") int id, Question question){
        QuestionDAO dao = new QuestionDAO();
        int count = dao.updateQuestion(id, question);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response deleteQuestion(@PathParam("id") int id){
        QuestionDAO dao = new QuestionDAO();
        int count = dao.deleteQuestion(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}