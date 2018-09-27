package com.basic.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.basic.dao.UserService;
import com.basic.daoImpl.UserServiceImpl;
import com.basic.database.Database;
import com.basic.pojo.User;

/**
 * Servlet implementation class ControllerLogin
 */
public class ControllerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final User userpojo = new User();
	static final UserService valid = new UserServiceImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerLogin() {    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		userpojo.setEmail(request.getParameter("userName"));
		userpojo.setPassword(request.getParameter("passWord"));

		if(valid.isValid(userpojo)){						//check username and password is correct or not
			User user = valid.getUser(userpojo);
			//request.getSession().setAttribute("user", user);		//set logged in user details in session
			request.getSession().setAttribute("user", "Logged In");
			request.getSession().setAttribute("firstname", user.getFirstname());
			request.getSession().setAttribute("user_id", user.getUser_id());
			request.getSession().setAttribute("role_id", user.getRole_id());
			response.sendRedirect("dashboard.jsp");
		}else {
			Properties prop=new Properties();
		    InputStream input = Database.class.getClassLoader().getResourceAsStream("messages.properties");
		    prop.load(input);
		    input.close();
		    
			request.setAttribute("message", prop.getProperty("logincheck"));
			request.getRequestDispatcher("index.jsp").forward(request, response);	
		}
	}

}
