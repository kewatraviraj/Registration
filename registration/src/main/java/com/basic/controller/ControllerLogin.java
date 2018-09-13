package com.basic.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basic.dao.UserService;
import com.basic.daoImpl.UserServiceImpl;
import com.basic.database.Database;
import com.basic.pojo.Address;
import com.basic.pojo.Files;
import com.basic.pojo.User;

/**
 * Servlet implementation class ControllerLogin
 */
public class ControllerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
		User userpojo;
		Address addresspojo;
		Files filepojo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerLogin() {
    	userpojo = new User();
    	addresspojo = new Address();
    	filepojo = new Files();
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
		
		UserService valid = new UserServiceImpl();
		
		try {
			if(valid.isValid(userpojo)){
				HttpSession session = request.getSession();
				session.setAttribute("user", valid.getUser(userpojo));
				
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			}else {
				Properties prop=new Properties();
			    InputStream input = Database.class.getClassLoader().getResourceAsStream("messages.properties");
			    prop.load(input);
			    input.close();
			    
				request.setAttribute("message", prop.getProperty("logincheck"));
				request.getRequestDispatcher("index.jsp").forward(request, response);	
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("'"+ e + "'");
		}
	}

}
