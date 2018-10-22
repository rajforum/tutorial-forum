package com.sof.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sof.dao.UserCredentialDAO;
import com.sof.dao.UserProfileDAO;
import com.sof.mysqlPOJO.UserCredential;
import com.sof.mysqlPOJO.UserProfile;
import com.sof.mysqlPOJO.UserCredential.Role;

public class Signup extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String mobileNo = req.getParameter("mobile_no");
		String name = req.getParameter("name");
		String country = req.getParameter("country");
		
		UserCredential credential = new UserCredential();
    	credential.setEmail(email);
    	credential.setPassword(password);
    	credential.setContact_No(mobileNo);
    	credential.setRole(Role.user);
    	System.out.println("DBG1#########UserCredential:" + credential.toString() + " Contact NO:" + credential.getContact_No());
        
    	Integer userId = -1, stackId = -1; 
        if ( (userId = UserCredentialDAO.addUserCredential(credential)) != -1 ) {
        	UserProfile uProfile = new UserProfile();
        	uProfile.setName(name);
        	uProfile.setCountry(country);
        	uProfile.setUserId(userId);
        	stackId = UserProfileDAO.addUserProfile(uProfile);
        	if (stackId != -1) {
        		resp.setStatus(200, "status:user created");
        		return;
            }
        } else {
        	//Error message
        }
        
        
        
//        JsonObject json = new JsonObject();
//        json.addProperty("status", "user created");
        resp.setStatus(500, "status:User not created");
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
    	
    	UserCredential credential = new UserCredential();
    	credential.setEmail(email);
    	credential.setPassword(password);
    	credential.setContact_No(mobileNo);
    	credential.setRole(Role.user);
    	System.out.println("DBG1#########UserCredential:" + credential.toString() + " Contact NO:" + credential.getContact_No());
        
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
        }
        
        
        
//        JsonObject json = new JsonObject();
//        json.addProperty("status", "user created");
        
        return Response.status(400).build();
    }
}
