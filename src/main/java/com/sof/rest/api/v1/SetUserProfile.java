package com.sof.rest.api.v1;

import com.sof.mysqlPOJO.UserProfile;
import com.sof.persistence.HibernateUtil;
import org.hibernate.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("set/profile")
public class SetUserProfile {


    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.TEXT_HTML})
    public String setUserCredential(
            @FormParam("name") String name,
            @FormParam("country") String country,
            @FormParam("DOB") Date date,
            @FormParam("sex") UserProfile.Sex sex,
            @FormParam("image") String image,
            @FormParam("creationTime") Date creationTime)
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            UserProfile profile = new UserProfile();
            profile.setName(name);
            profile.setCountry(country);
            profile.setDob(date);
            profile.setSex(sex);
           // profile.setImage(image);

            session.save(profile);
            session.getTransaction().commit();

            return "Profile added....";
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "Error: Profile doesnot added !!!";
    }
}