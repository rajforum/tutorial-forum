package com.sof.rest.api.v1;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sof.authentication.SHA256Encryption;
import com.sof.dao.UserAccessMappingDAO;
import com.sof.dao.UserCredentialDAO;
import com.sof.dao.UserProfileDAO;
import com.sof.mysqlPOJO.UserCredential;
import com.sof.mysqlPOJO.UserCredential.Role;
import com.sof.mysqlPOJO.UserProfile;

@Path("credential")
public class apiUserCredential {
	private static Logger LOGGER = Logger.getLogger(apiUserCredential.class.getName());

	
    @GET
    @Path("/list")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<UserCredential> getUserCredentialsDetail() {
        UserCredentialDAO dao = new UserCredentialDAO();
        List credentials = dao.getUserCredentialList();
        LOGGER.info("List");
        return credentials;
    }


    @PUT
    @Path("/update/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    //@Produces({ MediaType.APPLICATION_JSON })
    public Response updateUserCredential(@PathParam("id") int id, String jsongObj){
    	LOGGER.info("########################################\n");
    	LOGGER.info(jsongObj);
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
