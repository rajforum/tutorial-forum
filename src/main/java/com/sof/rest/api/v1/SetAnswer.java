package com.sof.rest.api.v1;

import com.sof.mysqlPOJO.Answer;
import com.sof.persistence.HibernateUtil;
import org.hibernate.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/set/answer")
class SetAnswer {


    @POST
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.TEXT_HTML })
    public String setAnswer(@FormParam("vote") Integer vote, @FormParam("bodyText") String bodyText) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Answer ans = new Answer();
            ans.setQueryId(1); //??
            ans.setUserId(1); //??
            ans.setVote(1); //??
            ans.setBodyText(bodyText);

            session.save(ans);
            session.getTransaction().commit();

            return "Answer added....";

        }catch (Exception e){e.printStackTrace();}
        return "Error: Answer not added!!!";
    }
}
