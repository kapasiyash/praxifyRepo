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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String host = request.getHeader("Host");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
    	
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		List<User> users = session.createQuery("SELECT a FROM User a where a.userName='"+username+"' and a.password='"+password+"'").list();
		
		if(users!=null && !users.isEmpty()) {
			
			User user = users.get(0);
			HttpSession sessionReq = request.getSession(true);
			sessionReq.setAttribute("userBean", user);
			
			response.sendRedirect("http://"+host+"/PraxifyPaypalModule/index.jsp");
			
		}
		
		session.getTransaction().commit();
		
	}

}
