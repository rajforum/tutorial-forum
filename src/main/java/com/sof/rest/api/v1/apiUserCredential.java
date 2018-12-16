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
    @Produces({ MediaType.APPLICATION_JSON })
    public List<UserCredential> getUserCredentialsDetail() {
        UserCredentialDAO dao = new UserCredentialDAO();
        List credentials = dao.getUserCredentialList();
        LOGGER.info("List");
        return credentials;
    }



    @POST
    @Path("/create")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response addUserCredential(UserCredential credential){

        
        UserCredentialDAO.addUserCredential(credential);

        return Response.ok().build();
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
    
    /**
     * Sign up new user.
     * @param email
     * @param password
     * @param mobileNo
     * @param name
     * @param country
     * @return
     */
    @POST
    @Path("/create")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.TEXT_HTML })
    public Response addUserCredential(@FormParam("email") String email, 
    		@FormParam("password") String password, 
    		@FormParam("mobile_no") String mobileNo,
    		@FormParam("name") String name,
    		@FormParam("country") String country){
    	
    	
    	//password = SHA256Encryption.generateHash(password+""+SHA256Encryption.getSalt());
    	
    	UserCredential credential = new UserCredential();
    	credential.setEmail(email);
    	credential.setPassword(password);
    	credential.setContact_no(mobileNo);
    	credential.setRole(Role.user);
    	System.out.println("credential DBG1#########UserCredential:" + credential.toString() + " Contact NO:" + credential.getContact_no());
        
    	Integer userId = -1, stackId = -1; 
        if ( (userId = UserCredentialDAO.addUserCredential(credential)) != -1 ) {
        	UserProfile uProfile = new UserProfile();
        	uProfile.setName(name);
        	uProfile.setCountry(country);
        	uProfile.setUserId(userId);
        	stackId = UserProfileDAO.addUserProfile(uProfile);
        	if (stackId != -1) {
        		return Response.ok("status:user created").build();
            }
        } else {
        	//Error message
        	System.out.println("Error in user creation");
        }
        
        
        
//        JsonObject json = new JsonObject();
//        json.addProperty("status", "user created");
        
        return Response.status(400).build();
    }

    

	@POST
    @Path("/signin")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON})
    public Response checkUser(@FormParam("email") String email,
    		@FormParam("mobile_no") String mobileNo,
    		@FormParam("password") String password, 
    		@Context HttpServletResponse response) {
		

		//password = SHA256Encryption.generateHash(password+""+SHA256Encryption.getSalt());
		
		
		UserCredentialDAO dao = new UserCredentialDAO();
		
		System.out.println(email+" : "+password);
		List<UserCredential> list = dao.getUserCredential(email, password);
        System.out.println("credential chekUser List::::" + list);
        JsonObject json = new JsonObject();
        if ( list.size() == 1) {
        	String authToken;
			
				authToken = SHA256Encryption.generateHash(list.get(0).getEmail() + "-" + System.currentTimeMillis());
	        	json.addProperty("status", "verified");
	        	json.addProperty("authtoken", authToken);
	        	response.addCookie(new Cookie("ticket",authToken));
	        	
	        	List<UserProfile> userProfileList = UserProfileDAO.getUserProfileListByUserId(list.get(0).getUserId());
	        	Integer stackId = -1;
	        	if ( list.size() == 1 ) {
	        		stackId = userProfileList.get(0).getStackId();
	        		response.addCookie(new Cookie("sofuid",""+stackId));
	        	}
	        	
	        	UserAccessMappingDAO.createTicketOnLogin(stackId, authToken, new Date());
			
        } else {
        	return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
        return Response.ok(json.toString()).build();
    }


}
