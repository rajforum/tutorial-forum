package com.sof.dao;

import com.sof.mysqlPOJO.Question;
import com.sof.persistence.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class QuestionDAO {
   public void addQuestion(Question bean){
       Session session = SessionUtil.getSession();
       Transaction tx = session.beginTransaction();
       
       Question ques = new Question();
       
       ques.setTitle(bean.getTitle());
       ques.setBodyText(bean.getBodyText());
       ques.setAuthorId(bean.getAuthorId());
       ques.setVote(bean.getVote());
       
       session.save(ques);
       tx.commit();
       session.close();
   }


   public int updateQuestion(int id, Question question){
       if(id <= 0) return 0;

       Session session = SessionUtil.getSession();
       Transaction tx =session.beginTransaction();

       String hql = "update question set " +
               "title=: title," +
               "bodyText=: bodyText," +
               "where id=: id";

       Query query = session.createQuery(hql);

       query.setParameter("id",id);
       query.setParameter("title",question.getTitle());
       query.setParameter("bodyText",question.getBodyText());

       int rowCount =query.executeUpdate();

       System.out.println("Rows affected "+rowCount);

       tx.commit();
       session.close();
       
       return rowCount;
   }

    public int deleteQuestion(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        Question question = new Question();

        String hql = "delete from question where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);

        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);

        session.save(question);
        tx.commit();
        session.close();
        return rowCount;
    }

    public List<Question> getQuestionList(){
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Question");
        List<Question> questions =  query.list();
        session.close();

        return questions;

    }
}
