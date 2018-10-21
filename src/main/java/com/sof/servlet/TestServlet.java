package com.sof.servlet;

import com.sof.mysqlPOJO.UserCredential;
import com.sof.persistence.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
/*

    // to input data in UserProfile
        UserProfile user = new UserProfile();
        user.setName("ManiKandan");
        user.setCountry("INDIA");
        user.setDob(new Date(1999,12,5));
        user.setSex(UserProfile.Sex.MALE);
*/




    // to input data in userCredential
        UserCredential user = new UserCredential();
        user.setContact_No("1234543212");
        user.setEmail("Rajku789@gmail.com");
        user.setPassword("Raja789@12");
        user.setRole(UserCredential.Role.user);


/*
    //to add Tag in QueryTag
        QueryTag user = new QueryTag();
        user.setTagName("Java");
*/
         
     
/*
    // to input questions in Question
        Question user = new Question();
        user.setId(1);
        user.setBodyText("Explain JVM, JDK, JRE");
        user.setTitle("JVM, JDK, JRE in java");
        user.setVote(1);
        user.setAuthorId(1);
*/


/*
    // to answer in Answer
        Answer user = new Answer();
        user.setQueryId(5);
        user.setUserId(1);
        user.setBodyText("JDk is java Development Key. \n JVM is Java Virtual Machine. \n JRE is Java Runtime Environment.");
        user.setVote(2);
*/


        session.save(user);
        session.getTransaction().commit();
		System.out.println("##################DONw");
		return;
	}
}
