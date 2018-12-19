package com.sof.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;
import com.sof.authentication.SHA256Encryption;
import com.sof.dao.UserAccessMappingDAO;
import com.sof.dao.UserCredentialDAO;
import com.sof.dao.UserProfileDAO;
import com.sof.mysqlPOJO.UserCredential;
import com.sof.mysqlPOJO.UserProfile;

public class Login extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String mobileNo = req.getParameter("mobile_no");
		String password = req.getParameter("password");
//		String password = req.getParameter("password")+""+SHA256Encryption.getSalt();
		
		System.out.println(email+" : "+mobileNo+" : "+password);
		
		List<UserCredential> list = UserCredentialDAO.getUserCredential(email, password);
        System.out.println("List::::" + list);
        JsonObject json = new JsonObject();
        if ( list.size() == 1) {
        	String authToken;
		
			authToken = SHA256Encryption.generateHash(list.get(0).getEmail() + "-" + list.get(0).getContact_no() + "-" + System.currentTimeMillis());
        	json.addProperty("status", "verified");
        	json.addProperty("authtoken", authToken);
        	resp.addCookie(new Cookie("ticket",authToken));
        	
        	List<UserProfile> userProfileList = UserProfileDAO.getUserProfileListByUserId(list.get(0).getUserId());
        	Integer stackId = -1;
        	if ( list.size() == 1 ) {
        		stackId = userProfileList.get(0).getStackId();
        		resp.addCookie(new Cookie("sofuid",""+stackId));
        	}
        	
       
        } else {
        	resp.setStatus(401);
        	return;
        }
        resp.setStatus(200);
        resp.getWriter().write(json.toString());
        return;
	}
/*
	@POST
    @Path("/signin")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_JSON})
    public Response checkUser(@FormParam("username") String email,@FormParam("password") String password, @Context HttpServletResponse response) {
		System.out.println("inside signin");
		UserCredentialDAO dao = new UserCredentialDAO();
		
		List<UserCredential> list = dao.getUserCredential(email, password+""+SHA256Encryption.getSalt());
		System.out.println(email+" : "+password+""+SHA256Encryption.getSalt());
        System.out.println("Hi I am chekUser List::::" + list);
        JsonObject json = new JsonObject();
        if ( list.size() == 1) {
        	String authToken;
			
				authToken = SHA256Encryption.generateHash(list.get(0).getEmail() + "-" + list.get(0).getContact_no() + "-" + System.currentTimeMillis());
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
    }*/
	
}
