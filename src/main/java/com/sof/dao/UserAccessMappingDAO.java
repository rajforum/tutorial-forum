package com.sof.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.sof.mysqlPOJO.UserAccessMapping;
import com.sof.persistence.SessionUtil;

public class UserAccessMappingDAO {
	
	public static void createTicketOnLogin(Integer stackId, String authToken, Date date) { 
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		UserAccessMapping userAccess = new UserAccessMapping();
		userAccess.setProfile_stackId(stackId);
		userAccess.setAuthToken(authToken);
		
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DAY_OF_MONTH, 7);  
	    Date d = cal.getTime();
		
		userAccess.setAuthToken_creation((new Timestamp(d.getTime())).toString());
		
		session.save(userAccess);
		tx.commit();
		session.close();
	}
	
	public void remvoeTicketOnLogoutOrExpire() {
		
	}
	
	public static boolean validateTicket(String sofuid, String authTicket) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		//String sql ="from UserAccesMapping where authToken=:authticket";
		String sql = "from UserAccessMapping where authToken =: autht";
		Query query = session.createQuery(sql); 
        query.setParameter("autht", authTicket);
        List<UserAccessMapping> list = query.list(); 
        
        tx.commit();
        session.close(); 
        
        
        if(list.size()==1) {
        	String stackid = ""+list.get(0).getProfile_stackId();
        	//String creationTime = list.get(0).getAuthToken_creation();
        	//Timestamp t = new Timestamp(Long.parseLong(creationTime));
        	
        	//Date aTokenDate = t.getd;
        	if(stackid.equals(sofuid) /*&& aTokenDate.after(new Date())*/) {
        		return true;
        	}
        }

        return false;
	}
}
