package com.sof.dao;


import java.util.List;

import com.sof.mysqlPOJO.UserCredential;
import com.sof.mysqlPOJO.UserProfile;
import com.sof.persistence.SessionUtil;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserProfileDAO {



    public static Integer addUserProfile(UserProfile userProfile){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        
        session.save(userProfile);
        tx.commit();
        session.close();
        
        session = SessionUtil.getSession();
        tx = session.beginTransaction();
        
        String hql = "from UserProfile where userId=:userid";
        Query query = session.createQuery(hql); 
        query.setParameter("userid", userProfile.getUserId());
        List<UserProfile> list = query.list();
        
        tx.commit();
        session.close();
        
        System.out.println("DBGDBG#####" + list.get(0).getStackId());
        
        if ( list.size() == 1) {
        	return list.get(0).getStackId();
        }
        
        return -1;
    }


    public static int updateUserProfile(int id, UserProfile profile){
        if(id <=0)
            return 0;
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        String hql = "update userProfile set name =:name, image=:image, country=:country, sex=:sex, dob=:dob, creationTime=:creationTime where stackId = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        query.setParameter("name",profile.getName());
        query.setParameter("image",profile.getImage());

        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);


        tx.commit();
        session.close();
        return rowCount;

    }


    public static int deleteUserProfile(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        UserProfile profile = new UserProfile();
        String hql = "delete from userProfile where stackId = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);

        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);

        session.save(profile);
        tx.commit();
        session.close();
        return rowCount;
    }


    public static List<UserProfile> getUserProfileListByUserId(Integer userId){
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from UserProfile where userId=:userid");
        query.setParameter("userid", userId);
        List<UserProfile> userProfiles =  query.list();
        session.close();

        return userProfiles;

    }
    
    
    public static List<UserProfile> getUserProfileListByStackId(Integer stackId){
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from UserProfile where stackId=:stackid");
        query.setParameter("stackid", stackId);
        List<UserProfile> userProfiles =  query.list();
        session.close();

        return userProfiles;

    }



}
