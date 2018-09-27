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

import org.apache.log4j.Logger;

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
	static Logger log = Logger.getLogger(ControllerRegister.class.getName());

	private static final long serialVersionUID = 1L;
	static final User userpojo = new User();
	static final Address addresspojo = new Address();
	static final Files filepojo = new Files();
	static final Filemap filemappojo = new Filemap();
	
	static final DaoSave servicesave = new DaoSaveImpl();
	static final DaoGetAll serviceget = new DaoGetImpl();
	static final DaoUpdate serviceupdate = new DaoUpdateImpl(); 
	static final DaoDelete serviceremove = new DaoDeleteImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerRegister() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if("users".equals(request.getParameter("action"))) {
				request.setAttribute("users", serviceget.getAllUser());					//method for get all user details
				request.getRequestDispatcher("/details.jsp").forward(request, response);
				
			}else if("addresses".equals(request.getParameter("action"))) {
				request.setAttribute("addresses", serviceget.getAllAddress());			//method for get all addresses
				request.getRequestDispatcher("/addresses.jsp").forward(request, response);
				
			}else if("files".equals(request.getParameter("action"))) {
				request.setAttribute("files", serviceget.getAllFiles());				//method for get all files
				request.getRequestDispatcher("/files.jsp").forward(request, response);	
			}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 log.info("-----ControllerRegister post Logged-----");	    
		int user_id, file_id, updatebyuser_id=0;
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
			 
			if("insert".equals(request.getParameter("operation"))) {			//check that request is for insert or update user detail
				
				userpojo.setPassword(request.getParameter("passWord"));
				user_id = servicesave.saveuser(userpojo);
				if(user_id != 0) {
					
					result = addUpdateAddress(request, user_id,updatebyuser_id);				//method for add and update addresses
					
					filepojo.setFile_type("image");
					filepojo.setFile(request.getPart("file").getInputStream());
					file_id = servicesave.savefile(filepojo);						//call a method for save file to DataBase
					
					filemappojo.setUser_id(user_id);
					filemappojo.setFile_id(file_id);
					result = servicesave.savefilemap(filemappojo);
				} 																	/*set the message for responses*/
				request.setAttribute("message", result  ? prop.getProperty("registerSuccessmessage") 
														: prop.getProperty("registerunSuccessmessage"));
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}else {
				user_id = Integer.parseInt(request.getParameter("user_id"));
				updatebyuser_id = (int) request.getSession().getAttribute("user_id");
				
				userpojo.setUser_id(user_id);
				userpojo.setUpdate_by(updatebyuser_id);
				
				if(serviceupdate.updateUser(userpojo)) {
					if("yes".equals(request.getParameter("deleteFlag"))) {
						serviceremove.deleteAddress(request.getParameter("deleteaddressIds"));		//remove addressses
					}
				
					result = addUpdateAddress(request, user_id,updatebyuser_id);					//method for add and update address
				}													/*set the message for responses*/				
				request.setAttribute("message", result  ? prop.getProperty("updateSuccessmessage") 		
														: prop.getProperty("updateunSuccessmessage"));
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			log.error("Error Message Logged !!!" + e );
			
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
	
	
	/**
	 * @param user_id
	 * @param updatebyuser_id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	private boolean addUpdateAddress(HttpServletRequest request,int user_id,int updatebyuser_id)throws ServletException, IOException{
		String[] address1 = null, address2 = null, city = null, state = null, country = null, pincode = null, operationAddress = null, addressIds = null;
		boolean result = false;
		
        address1 = request.getParameterValues("address_line1");
		 address2 = request.getParameterValues("address_line2");
		 city = request.getParameterValues("city");
		 state = request.getParameterValues("state");
		 country =request.getParameterValues("country");
		 pincode = request.getParameterValues("pincode");
		 
		 operationAddress = request.getParameterValues("operationAddress");     //operation value add or update for address
		 addressIds = request.getParameterValues("address_id");						//address_id for update address details 
			
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
				result = serviceupdate.updateAddress(addresspojo);					//call a method for update addresses
			}else {
				addresspojo.setUser_id(user_id);
				result = servicesave.saveaddress(addresspojo);						//call a method for insert addresses
			}	
		}
		return result ? true : false;
	}

}
