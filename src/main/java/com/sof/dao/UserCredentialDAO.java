package com.sof.dao;

import com.sof.mysqlPOJO.UserCredential;
import com.sof.persistence.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserCredentialDAO {

    public void addUserCredential(UserCredential bean){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        addUserCredential(session,bean);
        tx.commit();
        session.close();
    }

    private void addUserCredential(Session session, UserCredential bean){
        UserCredential credential = new UserCredential();

        credential.setEmail(bean.getEmail());
        credential.setPassword(bean.getPassword());
        credential.setContact_No(bean.getContact_No());
        credential.setRole(bean.getRole());
        
        session.save(credential);
    }

    public int updateUserCredential(int id, UserCredential credential){
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
        query.setParameter("contact_No",credential.getContact_No());
        query.setParameter("password",credential.getPassword());
        query.setParameter("role",credential.getRole());

        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: "+rowCount);

        tx.commit();
        session.close();
        return rowCount;
    }


    public int deleteUserCredential(int id) {
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

    public List<UserCredential> getUserCredentialList(){
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from UserCredential");
        List<UserCredential> userCredentials =  query.list();
        session.close();

        return userCredentials;

    }
}
