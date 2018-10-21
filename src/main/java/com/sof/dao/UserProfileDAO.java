package com.sof.dao;


import java.util.Date;
import java.util.List;

import com.sof.mysqlPOJO.UserProfile;
import com.sof.persistence.SessionUtil;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserProfileDAO {



    public void addUserProfile(UserProfile bean){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        addUserProfile(session,bean);

        tx.commit();
        session.close();

    }

    private void addUserProfile(Session session, UserProfile bean){
        UserProfile profile = new UserProfile();

        profile.setName(bean.getName());
        profile.setImage(bean.getImage());
        profile.setDob(bean.getDob());
        profile.setSex(bean.getSex());
        profile.setCountry(bean.getCountry());
        

        session.save(profile);

    }


    public int updateUserProfile(int id, UserProfile profile){
        if(id <=0)
            return 0;
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        String hql = "update userProfile set name =:name, image=: image, country=: country, sex=: sex, dob=: dob, creationTime=: creationTime where stackId = :id";
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


    public int deleteUserProfile(int id) {
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


    public List<UserProfile> getUserProfileList(){
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from UserProfile");
        List<UserProfile> userProfiles =  query.list();
        session.close();

        return userProfiles;

    }



}
