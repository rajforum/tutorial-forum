package com.sof.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sof.dao.UserAccessMappingDAO;

public class AuthenticationFilter implements Filter {

	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		String str = req.getRequestURI();
		System.out.println("DSFDSDFDFD########" + str);
		if ( str.contains("credential/create") || str.contains("credential/signin")) {
			System.out.println("DSFDSDFDFD11111########" + str);
			chain.doFilter(request, response);
			System.out.println("DSFDSDFDFD2222########" + str);
			return;
		}
		
		System.out.println("else doFilter:  " + str);
		
		Cookie[] cookies = req.getCookies();
		String authtoken="-1", sofuid="-1";
		if(cookies != null){
			System.out.println("cookies is present in browser");
			for(Cookie cookie : cookies){
				if ( "ticket".equals(cookie.getName() ) ) {
					authtoken = cookie.getValue();
				}
				if ( "sofuid".equals(cookie.getName() ) ) {
					sofuid = cookie.getValue();
				}
				System.out.println("cookies verified");
			}
			System.out.println("end of for loop");
		}else {
			System.out.println("NO cookies ");
		}
		 
		
		boolean authenticated = UserAccessMappingDAO.validateTicket(sofuid, authtoken);
		
		if (authenticated == true) {
			// pass the request along the filter chain
			System.out.println("authenticated...");
			chain.doFilter(request, response);
			
			System.out.println("do chain over...");
		} else {
			HttpServletResponse resp = (HttpServletResponse) response;
		    //resp.reset();
		    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		    System.out.println("NOT authenticated...");
		}
	}

	public void destroy() {
		//we can close resources here
	}
}
