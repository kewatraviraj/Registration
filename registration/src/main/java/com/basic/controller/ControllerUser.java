package com.basic.controller;

import java.io.IOException;
import java.sql.SQLException;

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
	User userpojo;
	DaoGetAll serviceget;
	DaoUpdate serviceupdate;
	DaoSave servicesave;
	DaoDelete serviceremove;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerUser() {
        userpojo = new User();
        serviceget = new DaoGetImpl();
        serviceupdate = new DaoUpdateImpl();
        servicesave = new DaoSaveImpl();
        serviceremove = new DaoDeleteImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.setContentType("text/plain");
		try {
			if(request.getParameter("action").equals("get")) {
				User user = serviceget.getUser(Integer.parseInt(request.getParameter("userid")));
				request.setAttribute("user", user);
				request.setAttribute("addresses", serviceget.getAddress( user.getUser_id()));
				request.setAttribute("files", serviceget.getfile(user.getUser_id()));
				
				request.getRequestDispatcher("register.jsp").forward(request, response);
				
			}else if(request.getParameter("action").equals("delete")) {
				if(serviceremove.deleteUser(Integer.parseInt(request.getParameter("userid")))) {
					response.getWriter().write(request.getParameter("userid"));
				}else {
					response.getWriter().write("fail");
				}		
			}	
			
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
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
		
		try {
			if(request.getParameter("action").equals("save")) {
				servicesave.saveuser(userpojo);
					
			}else {
				userpojo.setUser_id(Integer.parseInt(request.getParameter("userid")));
				userpojo.setUpdate_by(((User)session.getAttribute("user")).getUser_id());
				serviceupdate.updateUser(userpojo);	
			}
			
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
