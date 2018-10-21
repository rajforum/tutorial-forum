package com.sof.rest.api.v1;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.sof.mysqlPOJO.Question;
import com.sof.persistence.HibernateUtil;
import org.hibernate.Session;

@Path("/set/question")
public class SetQuestion  {



    @POST
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.TEXT_HTML })
    public String setQuestion(@FormParam("title") String title, @FormParam("vote") Integer vote, @FormParam("bodyText") String bodyText) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Question ques = new Question();
            ques.setAuthorId(1);//?? take input via api
            ques.setTitle(title);
            ques.setVote(vote);
            ques.setBodyText(bodyText);

            session.save(ques);
            session.getTransaction().commit();

            return "Question added";


        }catch (Exception e){e.printStackTrace();}
        return "Error: Question not added!!!";
    }
}
