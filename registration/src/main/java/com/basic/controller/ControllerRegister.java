package com.basic.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.basic.dao.DaoGetAll;
import com.basic.dao.DaoSave;
import com.basic.dao.DaoUpdate;
import com.basic.daoImpl.DaoGetImpl;
import com.basic.daoImpl.DaoSaveImpl;
import com.basic.daoImpl.DaoUpdateImpl;
import com.basic.database.Database;
import com.basic.pojo.Address;
import com.basic.pojo.Filemap;
import com.basic.pojo.Files;
import com.basic.pojo.User;

/**
 * Servlet implementation class ControllerRegister
 */
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class ControllerRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
		User userpojo;
		Address addresspojo;
		Files filepojo;
		Filemap filemappojo;
		DaoSave servicesave;
		DaoGetAll serviceget;
		DaoUpdate serviceupdate;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerRegister() {
        // TODO Auto-generated constructor stub
    	userpojo = new User();
    	addresspojo = new Address();
    	filepojo = new Files();
    	filemappojo = new Filemap();
        servicesave = new DaoSaveImpl();
        serviceget = new DaoGetImpl();
        serviceupdate = new DaoUpdateImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			try {
				if(request.getParameter("action").equals("users")) {
					request.setAttribute("users", serviceget.getAllUser());
					request.getRequestDispatcher("/details.jsp").forward(request, response);
					
				}else if(request.getParameter("action").equals("addresses")) {
					request.setAttribute("addresses", serviceget.getAllAddress());
					request.getRequestDispatcher("/addresses.jsp").forward(request, response);
					
				}else if(request.getParameter("action").equals("files")) {
					request.setAttribute("files", serviceget.getAllFiles());
					request.getRequestDispatcher("/files.jsp").forward(request, response);	
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int user_id;
		int file_id;
		boolean result = false;
		
		Properties prop=new Properties();
	    InputStream input = Database.class.getClassLoader().getResourceAsStream("messages.properties");
	    prop.load(input);
	    input.close();
		
	    try {
			userpojo.setRole_id(2);
			userpojo.setFirstname(request.getParameter("firstName"));
			userpojo.setLastname(request.getParameter("lastName"));
			userpojo.setEmail(request.getParameter("email"));
			userpojo.setMobile_no(Long.parseLong(request.getParameter("mobileNo")));
			userpojo.setGender(request.getParameter("gender"));
			userpojo.setDate_of_birth(request.getParameter("dateofBirth"));
			
		if("insert".equals(request.getParameter("operation"))) {
			userpojo.setPassword(request.getParameter("passWord"));
			user_id = servicesave.saveuser(userpojo);
			
			if(user_id != 0) {
				String address1[] = request.getParameterValues("address_line1");
				String address2[] = request.getParameterValues("address_line2");
				String city[] = request.getParameterValues("city");
				String state[] = request.getParameterValues("state");
				String country[] =request.getParameterValues("country");
				String pincode[] = request.getParameterValues("pincode");
				
				for(int index=0; index<address1.length; index++ ) {
					addresspojo.setUser_id(user_id);
					addresspojo.setAddress_line1(address1[index]);
					addresspojo.setAddress_line2(address2[index]);
					addresspojo.setCity(city[index]);
					addresspojo.setState(state[index]);
					addresspojo.setCountry(country[index]);
					addresspojo.setPincode(Integer.parseInt(pincode[index]));	
					servicesave.saveaddress(addresspojo);	
				}	
				
				filepojo.setFile_type("image");
				filepojo.setFile(request.getPart("file").getInputStream());
				file_id = servicesave.savefile(filepojo);
				
				filemappojo.setUser_id(user_id);
				filemappojo.setFile_id(file_id);
				result = servicesave.savefilemap(filemappojo);		
			} 
			request.setAttribute("message", result? prop.getProperty("registerSuccessmessage") : prop.getProperty("registerunSuccessmessage"));
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			userpojo.setUser_id(Integer.parseInt(request.getParameter("user_id")));
			
			if(serviceupdate.updateUser(userpojo)) {
				
				if("yes".equals(request.getParameter("deleteFlag"))) {
					servicesave.deleteAddress(request.getParameter("deleteaddressIds"));
				}
				
				String operationAddress[] = request.getParameterValues("operationAddress");
				
				String addressId[] = request.getParameterValues("address_id");
				String address1[] = request.getParameterValues("address_line1");
				String address2[] = request.getParameterValues("address_line2");
				String city[] = request.getParameterValues("city");
				String state[] = request.getParameterValues("state");
				String country[] =request.getParameterValues("country");
				String pincode[] = request.getParameterValues("pincode");
				
				for(int index=0; index<address1.length; index++ ) {
					
					addresspojo.setAddress_line1(address1[index]);
					addresspojo.setAddress_line2(address2[index]);
					addresspojo.setCity(city[index]);
					addresspojo.setState(state[index]);
					addresspojo.setCountry(country[index]);
					addresspojo.setPincode(Integer.parseInt(pincode[index]));	
					
					if("updateAddress".equals(operationAddress[index])) {
						addresspojo.setAddress_id(Integer.parseInt(addressId[index]));
						addresspojo.setUpdate_by(((User) request.getSession().getAttribute("user")).getUser_id());
						
					result = serviceupdate.updateAddress(addresspojo);
					}else {
						addresspojo.setUser_id(userpojo.getUser_id());
					result = servicesave.saveaddress(addresspojo);	
					}	
				}	
			}	
			request.setAttribute("message", result? prop.getProperty("updateSuccessmessage") : prop.getProperty("updateunSuccessmessage"));
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
