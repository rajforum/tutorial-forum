package com.sof.rest.api.v1;

import com.sof.mysqlPOJO.UserCredential;
import com.google.gson.Gson;
import com.sof.dao.UserCredentialDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("credential")
public class apiUserCredential {

    @GET
    @Path("/list")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<UserCredential> getUserCredentialsDetail() {
        UserCredentialDAO dao = new UserCredentialDAO();
        List credentials = dao.getUserCredentialList();
        System.out.println("##### LIST");
        return credentials;
    }


    @POST
    @Path("/create")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response addUserCredential(UserCredential credential){

        UserCredentialDAO dao = new UserCredentialDAO();
        dao.addUserCredential(credential);

        return Response.ok().build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    //@Produces({ MediaType.APPLICATION_JSON })
    public Response updateUserCredential(@PathParam("id") int id, String jsongObj){
    	System.out.println("########################################\n");
    	System.out.println(jsongObj);
    	UserCredential credential = (new Gson()).fromJson(jsongObj, UserCredential.class);
        UserCredentialDAO dao = new UserCredentialDAO();
        int count = dao.updateUserCredential(id, credential);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response deleteUserCredential(@PathParam("id") int id){
        UserCredentialDAO dao = new UserCredentialDAO();
        int count = dao.deleteUserCredential(id);
        if(count==0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}
