package com.sof.dao;

import com.sof.mysqlPOJO.Answer;
import com.sof.persistence.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AnswerDAO {
    public void addAnswer(Answer bean){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        
        Answer ans = new Answer();
        ans.setUserId(bean.getUserId());
        ans.setQueryId(bean.getQueryId());
        ans.setBodyText(bean.getBodyText());
        ans.setVote(bean.getVote());
        
        session.save(ans);
        tx.commit();
        
        session.close();
    }


    public int updateAnswer(int id, Answer answer){
        if(id <= 0) return 0;

        Session session = SessionUtil.getSession();
        Transaction tx =session.beginTransaction();

        String hql = "update answer set " +
                "userId=: userId," +
                "queryId=: queryId," +
                "bodyText=: bodyText," +
                "vote=: vote" +
                "where answerId=: id";

        Query query = session.createQuery(hql);

        query.setParameter("id",id);
        query.setParameter("userId",answer.getUserId());
        query.setParameter("bodyText",answer.getBodyText());
        query.setParameter("queryId",answer.getQueryId());
        query.setParameter("vote",answer.getVote());

        int rowCount =query.executeUpdate();

        System.out.println("Rows affected "+rowCount);

        tx.commit();
        session.close();
        return rowCount;
    }

    public int deleteAnswer(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        Answer answer = new Answer();

        String hql = "delete from answer where answerId = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);

        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);

        session.save(answer);
        tx.commit();
        session.close();
        return rowCount;
    }

    public List<Answer> getAnswerList(){
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Answer");
        List<Answer> answers =  query.list();
        session.close();

        return answers;

    }
 
}

