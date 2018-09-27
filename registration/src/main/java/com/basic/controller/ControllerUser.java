package com.basic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basic.dao.DaoDelete;
import com.basic.dao.DaoGetAll;
import com.basic.dao.DaoSave;
import com.basic.dao.DaoUpdate;
import com.basic.daoImpl.DaoDeleteImpl;
import com.basic.daoImpl.DaoGetImpl;
import com.basic.daoImpl.DaoSaveImpl;
import com.basic.daoImpl.DaoUpdateImpl;
import com.basic.pojo.User;

/**
 * Servlet implementation class ControllerUser
 */
public class ControllerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final User userpojo = new User();
	
	static final DaoSave servicesave = new DaoSaveImpl(); 
	static final DaoGetAll serviceget  = new DaoGetImpl();
	static final DaoUpdate serviceupdate = new DaoUpdateImpl();
	static final DaoDelete serviceremove = new DaoDeleteImpl();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerUser() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.setContentType("text/plain");
		try {
			if(request.getParameter("action").equals("get")) {
				User user = serviceget.getUser(Integer.parseInt(request.getParameter("userid")));		//get user details
				request.setAttribute("user", user);												
				request.setAttribute("addresses", serviceget.getAddress( user.getUser_id()));			//get address details
				request.setAttribute("files", serviceget.getfile(user.getUser_id()));					//get file details
				
				request.getRequestDispatcher("register.jsp").forward(request, response);
				
			}else if(request.getParameter("action").equals("delete")) {
				if(serviceremove.deleteUser(Integer.parseInt(request.getParameter("userid")))) {		//delete user all details
					response.getWriter().write(request.getParameter("userid"));
				}else {
					response.getWriter().write("fail");
				}		
			}	
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		userpojo.setRole_id(2);
		userpojo.setFirstname(request.getParameter("firstName"));
		userpojo.setLastname(request.getParameter("lastName"));
		userpojo.setEmail(request.getParameter("email"));
		userpojo.setMobile_no(Long.parseLong(request.getParameter("mobileNo")));
		userpojo.setGender(request.getParameter("gender"));
		userpojo.setDate_of_birth(request.getParameter("dateofBirth"));
		
		if(request.getParameter("action").equals("save")) {
			servicesave.saveuser(userpojo);
				
		}else {
			userpojo.setUser_id(Integer.parseInt(request.getParameter("userid")));
			userpojo.setUpdate_by((int)session.getAttribute("user_id"));
			serviceupdate.updateUser(userpojo);	
		}
		
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}
}
