package com.basic.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
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
 * Servlet implementation class Forgot
 */
public class Forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UserService userService;
       User userpojo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forgot() {
        // TODO Auto-generated constructor stub
    	userService = new UserServiceImpl();
    	userpojo = new User();
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
		try {
			userpojo.setEmail(request.getParameter("email"));
			userpojo.setMobile_no(Long.parseLong(request.getParameter("mobileNo")));
			
			String pass = userService.getPass(userpojo);
			if(pass != null) {
				request.setAttribute("password", pass);
			}
			Properties prop=new Properties();
		    InputStream input = Database.class.getClassLoader().getResourceAsStream("messages.properties");
		    prop.load(input);
		    input.close();
		    
			request.setAttribute("message", pass != null? prop.getProperty("getforgotpass") : prop.getProperty("unknownforgotpass"));
			
			request.getRequestDispatcher("forgot.jsp").forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
