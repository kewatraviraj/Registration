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

import com.basic.dao.DaoDelete;
import com.basic.dao.DaoGetAll;
import com.basic.dao.DaoSave;
import com.basic.dao.DaoUpdate;
import com.basic.daoImpl.DaoDeleteImpl;
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
		DaoDelete serviceremove;
		int user_id, file_id, updatebyuser_id=0;
		boolean result = false;
		String[] address1, address2, city, state, country, pincode, operationAddress, addressIds;
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
        serviceremove = new DaoDeleteImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			try {
				if("users".equals(request.getParameter("action"))) {
					request.setAttribute("users", serviceget.getAllUser());
					request.getRequestDispatcher("/details.jsp").forward(request, response);
					
				}else if("addresses".equals(request.getParameter("action"))) {
					request.setAttribute("addresses", serviceget.getAllAddress());
					request.getRequestDispatcher("/addresses.jsp").forward(request, response);
					
				}else if("files".equals(request.getParameter("action"))) {
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
			
			 address1 = request.getParameterValues("address_line1");
			 address2 = request.getParameterValues("address_line2");
			 city = request.getParameterValues("city");
			 state = request.getParameterValues("state");
			 country =request.getParameterValues("country");
			 pincode = request.getParameterValues("pincode");
			 
			 operationAddress = request.getParameterValues("operationAddress");     //operation value add or update for address
			 
			if("insert".equals(request.getParameter("operation"))) {			//check that request is for insert or update user detail
				
				userpojo.setPassword(request.getParameter("passWord"));
				user_id = servicesave.saveuser(userpojo);
				if(user_id != 0) {
					
					result = addUpdateAddress(user_id,updatebyuser_id);				//method for add or update addresses
					
					filepojo.setFile_type("image");
					filepojo.setFile(request.getPart("file").getInputStream());
					file_id = servicesave.savefile(filepojo);
					
					filemappojo.setUser_id(user_id);
					filemappojo.setFile_id(file_id);
					result = servicesave.savefilemap(filemappojo);		
				} 
				request.setAttribute("message", result  ? prop.getProperty("registerSuccessmessage") 
														: prop.getProperty("registerunSuccessmessage"));
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}else {
				user_id = Integer.parseInt(request.getParameter("user_id"));
				updatebyuser_id = ((User) request.getSession().getAttribute("user")).getUser_id();
				
				userpojo.setUser_id(user_id);
				userpojo.setUpdate_by(updatebyuser_id);
				addressIds = request.getParameterValues("address_id");						//address_id for update address details 
				
				if(serviceupdate.updateUser(userpojo)) {
					if("yes".equals(request.getParameter("deleteFlag"))) {
						serviceremove.deleteAddress(request.getParameter("deleteaddressIds"));		//remove addressses
					}
				
					result = addUpdateAddress(user_id,updatebyuser_id);
				}	
				request.setAttribute("message", result  ? prop.getProperty("updateSuccessmessage") 
														: prop.getProperty("updateunSuccessmessage"));
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean addUpdateAddress(int user_id,int updatebyuser_id) throws ClassNotFoundException, SQLException, IOException {
		for(int index=0; index<address1.length; index++ ) {
			addresspojo.setAddress_line1(address1[index]);
			addresspojo.setAddress_line2(address2[index]);
			addresspojo.setCity(city[index]);
			addresspojo.setState(state[index]);
			addresspojo.setCountry(country[index]);
			addresspojo.setPincode(Integer.parseInt(pincode[index]));	
			
			if("updateAddress".equals(operationAddress[index])) {
				addresspojo.setAddress_id(Integer.parseInt(addressIds[index]));
				addresspojo.setUpdate_by(updatebyuser_id);
				result = serviceupdate.updateAddress(addresspojo);
			}else {
				addresspojo.setUser_id(user_id);
				result = servicesave.saveaddress(addresspojo);	
			}	
		}
		return (result ? true : false);
	}

}
