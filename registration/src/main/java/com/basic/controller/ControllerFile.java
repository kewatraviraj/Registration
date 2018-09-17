package com.basic.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.basic.dao.DaoGetAll;
import com.basic.dao.DaoSave;
import com.basic.dao.DaoUpdate;
import com.basic.daoImpl.DaoGetImpl;
import com.basic.daoImpl.DaoSaveImpl;
import com.basic.daoImpl.DaoUpdateImpl;
import com.basic.pojo.Filemap;
import com.basic.pojo.Files;
import com.basic.pojo.User;

/**
 * Servlet implementation class ControllerFile
 */
public class ControllerFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Files filepojo;
	Filemap filemappojo;
	DaoSave servicesave;
	DaoGetAll serviceget;
	DaoUpdate serviceupdate;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerFile() {
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
			if(request.getParameter("action").equals("get")) {
				request.setAttribute("files", serviceget.getfile(Integer.parseInt(request.getParameter("userid"))));
				request.getRequestDispatcher("files.jsp").forward(request, response);
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
		
		filepojo.setFile_type("image");
		filepojo.setFile(request.getPart("file").getInputStream());
		
		try {	
			if(request.getParameter("action").equals("save")) {
				int file_id = servicesave.savefile(filepojo);
				
				filemappojo.setUser_id(Integer.parseInt(request.getParameter("userid")));
				filemappojo.setFile_id(file_id);
				servicesave.savefilemap(filemappojo);
			}else {
				filepojo.setFile_id(Integer.parseInt(request.getParameter("fileid")));
				filepojo.setUpdate_by(((User)session.getAttribute("user")).getUser_id());
				serviceupdate.updateFile(filepojo);
			}	
			
			request.getRequestDispatcher("display.jsp").forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
