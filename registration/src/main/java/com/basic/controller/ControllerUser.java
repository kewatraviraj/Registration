package com.basic.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basic.dao.DaoGetAll;
import com.basic.dao.DaoSave;
import com.basic.dao.DaoUpdate;
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
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerUser() {
        userpojo = new User();
        serviceget = new DaoGetImpl();
        serviceupdate = new DaoUpdateImpl();
        servicesave = new DaoSaveImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			if(request.getParameter("action").equals("get")) {
				request.setAttribute("user", serviceget.getUser(Integer.parseInt(request.getParameter("userid"))));
				
				request.getRequestDispatcher("view.jsp").forward(request, response);
				
			}else if(request.getParameter("action").equals("delete")) {
				request.setAttribute("user", servicesave.del(Integer.parseInt(request.getParameter("userid"))));	
				request.setAttribute("users", serviceget.getAllUser());
				request.getRequestDispatcher("details.jsp").forward(request, response);
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
