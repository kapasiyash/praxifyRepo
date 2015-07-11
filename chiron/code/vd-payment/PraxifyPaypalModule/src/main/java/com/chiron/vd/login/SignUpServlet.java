package com.chiron.vd.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.chiron.vd.entity.User;
import com.chiron.vd.utility.HibernateUtil;

/**
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			System.out.println("In Sign up FOrm");
			
			String host = request.getHeader("Host");
			
			User userEntity = new User();
			
			userEntity.setFirstName(request.getParameter("fname"));
			userEntity.setLastName(request.getParameter("lname"));
			userEntity.setAddress(request.getParameter("address"));
			userEntity.setAge(Long.parseLong(request.getParameter("age")));
			userEntity.setCellPhone(request.getParameter("phone"));	
			userEntity.setEmailAddress(request.getParameter("emailid"));
			userEntity.setUserName(request.getParameter("username"));
			userEntity.setPassword(request.getParameter("password"));
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        	Transaction tx = session.beginTransaction();
        	
        	List<User> users = session.createQuery("SELECT a FROM User a where a.emailAddress='"+userEntity.getEmailAddress()+"'").list();
        	
        	boolean isPresent = false;
        	
        	if(users!=null && !users.isEmpty()) {
        		isPresent=true;
        	}
        	
        	
        	if(!isPresent) {
        		session.save(userEntity);
        	}
			
			System.out.println(userEntity);
			
			session.getTransaction().commit();
			System.out.println("In Sign up FOrm Exit");
			
			response.sendRedirect("http://"+host+"/PraxifyPaypalModule/login.jsp");
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
