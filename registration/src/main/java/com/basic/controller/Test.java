package com.basic.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basic.dao.DaoSave;
import com.basic.dao.DaoUpdate;
import com.basic.daoImpl.DaoSaveImpl;
import com.basic.daoImpl.DaoUpdateImpl;
import com.basic.pojo.Address;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Address addresspojo;
	DaoSave servicesave;
	DaoUpdate serviceupdate;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
    	addresspojo = new Address();
    	servicesave = new DaoSaveImpl();
    	serviceupdate = new DaoUpdateImpl();
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
			/*Map<String, String[]> parameterMap = request.getParameterMap();
	        for (String parameterName : parameterMap.keySet()) {
	        	System.out.println("Begin");
	        	if (parameterName.endsWith("-address_line1")
	        			|| parameterName.endsWith("-address_line2")) {
		            String[] value = parameterMap.get(parameterName);
		            Byte markValue = Byte.valueOf(value[0]);
		            
		            String[] keyAndValue = parameterName.split("-");
			          
			        	//addresspojo.setAddress_id( Integer.valueOf(keyAndValue[0]));
			        	System.out.println(keyAndValue[0]);
			        	System.out.println(keyAndValue[1]);
			       System.out.println("in middle");
	        }
	        	
        }*/
		
		/* List<Address> addresses = new LinkedList<Address>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            System.out.println(json = br.readLine());
            
        }*/
        
		String deleteaddressIds = request.getParameter("deleteaddressIds");
		
		System.out.println("delete Ids :" +deleteaddressIds);
			System.out.println("begin");
 
		//	Address address = mapper.readValue(json, Address.class);
			
		//	addresses.add(address);
		//	for(int i =0; i< addresses.size(); i++) {
		//		System.out.println(addresses.toArray()[i]);
		//	}
		
			/*for (String string : myJsonData.toString()) {
				myJsonData.address_line1;
			}*/
		/*	for(int i = 0; i < myJsonData.length; i++) {
				System.out.println(myJsonData[i]);
			}
		*/	
	        System.out.println("Stop");  
	        request.getRequestDispatcher("register.jsp").forward(request, response);
	}

}
