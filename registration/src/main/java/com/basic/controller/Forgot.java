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
 * Servlet implementation class Forgot
 */
public class Forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final UserService userService = new UserServiceImpl();
    static final User userpojo = new User();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forgot() {
        // TODO Auto-generated constructor stub  	
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
	       
		userpojo.setEmail(request.getParameter("email"));
		String pass = userService.getPass(userpojo);			//get password from DB for specified Email
			
		Properties prop=new Properties();
		InputStream input = Database.class.getClassLoader().getResourceAsStream("messages.properties");
		prop.load(input);
		input.close();
																//set message for responce 
		request.setAttribute("message", !("".equals(pass))? prop.getProperty("getforgotpass")+pass : prop.getProperty("unknownforgotpass"));
		request.getRequestDispatcher("forgot.jsp").forward(request, response);
	}

}
