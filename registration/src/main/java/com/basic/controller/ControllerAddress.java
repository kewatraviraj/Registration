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
import com.basic.pojo.Address;
import com.basic.pojo.User;

/**
 * Servlet implementation class ControllerAddress
 */
public class ControllerAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Address addresspojo;
	DaoSave servicesave;
	DaoUpdate serviceupdate;
	DaoGetAll serviceget;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAddress() {
    	addresspojo = new Address();
    	servicesave = new DaoSaveImpl();
    	serviceupdate = new DaoUpdateImpl();
    	serviceget = new DaoGetImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			request.setAttribute("addresses", serviceget.getAddress(Integer.parseInt(request.getParameter("userid"))));
			request.getRequestDispatcher("addresses.jsp").forward(request, response);
			
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
		
			addresspojo.setAddress_line1(request.getParameter("address_line1"));
			addresspojo.setAddress_line2(request.getParameter("address_line2"));
			addresspojo.setCity(request.getParameter("city"));
			addresspojo.setState(request.getParameter("state"));
			addresspojo.setCountry(request.getParameter("country"));
			addresspojo.setPincode(Integer.parseInt(request.getParameter("pincode")));
		
		try {
			if(request.getParameter("action").equals("save")) {
				addresspojo.setUser_id(Integer.parseInt(request.getParameter("userid")));
				servicesave.saveaddress(addresspojo);
			}else {
				addresspojo.setAddress_id(Integer.parseInt(request.getParameter("addressid")));
				addresspojo.setUpdate_by(((User)session.getAttribute("user")).getUser_id());
				serviceupdate.updateAddress(addresspojo);
			}		
			
			request.getRequestDispatcher("display.jsp").forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
