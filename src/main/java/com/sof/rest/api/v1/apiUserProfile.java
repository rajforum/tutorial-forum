package com.sof.rest.api.v1;

import com.sof.mysqlPOJO.UserProfile;
import com.sof.dao.UserProfileDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/profile")
public class apiUserProfile {

    @GET
    @Path("/list")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public List<UserProfile> getUserProfiles() {
        UserProfileDAO dao = new UserProfileDAO();
        List profiles = dao.getUserProfileList();
        return profiles;
    }


    @POST
    @Path("/create")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response addUserProfile(UserProfile profile){

        UserProfileDAO dao = new UserProfileDAO();
        dao.addUserProfile(profile);

        return Response.ok().build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response updateUserProfile(@PathParam("id") int id, UserProfile profile){
        UserProfileDAO dao = new UserProfileDAO();
        int count = dao.updateUserProfile(id, profile);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response deleteUserProfile(@PathParam("id") int id){
        UserProfileDAO dao = new UserProfileDAO();
        int count = dao.deleteUserProfile(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}
