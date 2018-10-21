package com.sof.rest.api.v1;

import com.sof.mysqlPOJO.UserCredential;
import com.sof.mysqlPOJO.UserCredential.Role;
import com.sof.persistence.HibernateUtil;
import org.hibernate.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/user/new")
public class SetUserCredential {


    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.TEXT_HTML})
    public String setUserCredential(
            @FormParam("email") String email,
            @FormParam("mobileno") String mobileno,
            @FormParam("password") String password,
            @FormParam("role") String role
    ) {
        try {

            Role rObj = (role.equalsIgnoreCase("Admin")) ? Role.admin : Role.user;

            System.out.println("New user:" + email + ":" + mobileno + ":" + role+" | "+rObj.toString());

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            UserCredential user = new UserCredential();
            user.setContact_no(mobileno);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(rObj);
            System.out.println("DONE");
            session.save(user);
            session.getTransaction().commit();

            return "User added....";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "User cannot be added!";
    }
/*


    @GET
    @Path("get")
    @Produces ({MediaType.APPLICATION_JSON})
    public UserCredential editUserCredential(){
        return new UserCredential();
    }
*/
}



