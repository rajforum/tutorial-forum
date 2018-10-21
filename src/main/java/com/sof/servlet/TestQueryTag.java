package com.sof.servlet;

import com.sof.mysqlPOJO.QueryTag;
import com.sof.persistence.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestQueryTag extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Maven + Hibernate + MySQL");
		
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        QueryTag tag = new QueryTag();
        tag.setTagName("Mavel");
        
        session.save(tag);
        session.getTransaction().commit();
	}
}
