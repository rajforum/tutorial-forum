package com.sof.dao;

import com.sof.mysqlPOJO.UserCredential;
import com.sof.persistence.SessionUtil;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;

public class UserCredentialDAO {

	private static Logger LOGGER = Logger.getLogger("dblogger");
	


    public static Integer addUserCredential(UserCredential bean){
        UserCredential credential = new UserCredential();

        credential.setEmail(bean.getEmail());
        credential.setPassword(bean.getPassword());
        credential.setContact_no(bean.getContact_no());
        credential.setRole(bean.getRole());

        
        Session session1 = SessionUtil.getSession();
        Transaction tx1 = session1.beginTransaction();
        String hql = "from UserCredential where contact_no=:cno";
        Query query = session1.createQuery(hql); 
        query.setParameter("cno", credential.getContact_no());
        List<UserCredential> list = query.list();
        tx1.commit();
        session1.close();
        
        if ( list.size() == 1 ) {
        	System.out.println("User created" + credential );
        	UserCredential uc = list.get(0);
        	System.out.println("DBG1 : " + uc );
        	System.out.println("DBG2 : " + uc.getUserId() );
        	return uc.getUserId();
        }
        
        LOGGER.log(Level.INFO, "Failed to create new User" + credential );
        return -1;
    }

    public static int updateUserCredential(int id, UserCredential credential){
        if(id <= 0) return 0;

        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        String hql = "update userCredential set " +
                "contact_no=: contact_no," +
                "email=: email," +
                "password=: password," +
                "role=: role"+
                "where userId=: id";

        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        query.setParameter("email",credential.getEmail());
        query.setParameter("contact_No",credential.getContact_no());
        query.setParameter("password",credential.getPassword());
        query.setParameter("role",credential.getRole());

        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: "+rowCount);

        tx.commit();
        session.close();
        return rowCount;
    }


    public static int deleteUserCredential(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        UserCredential credential = new UserCredential();

        String hql = "delete from userCredential where userId = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);

        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);

        session.save(credential);
        tx.commit();
        session.close();
        return rowCount;
    }

    public static List<UserCredential> getUserCredentialList(){
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from UserCredential");
        List<UserCredential> userCredentials =  query.list();
        session.close();

        return userCredentials;

    }
    
    public static List<UserCredential> getUserCredential(String email, String contactNo, String password){
        Session session = SessionUtil.getSession();
        Criteria cr = session.createCriteria(UserCredential.class); 
        cr.add(Restrictions.eq("email", email));
        cr.add(Restrictions.eq("password", password));
        //cr.add(Restrictions.eq("contact_no", contactNo));
        
        List<UserCredential> userCredentials =  cr.list();
        session.close();

        return userCredentials;

    }
}
