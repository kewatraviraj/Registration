package com.basic.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.basic.pojo.Address;
import com.basic.pojo.Files;
import com.basic.pojo.User;

public interface DaoGetAll {
	
	public List<User> getAllUser() throws ClassNotFoundException, SQLException, IOException;
	public List<Address> getAllAddress() throws ClassNotFoundException, SQLException, IOException;
	public List<Files> getAllFiles() throws ClassNotFoundException, SQLException, IOException;
	
	public User getUser(int user_id)  throws ClassNotFoundException, SQLException, IOException;
	public List<Address> getAddress(int user_id) throws ClassNotFoundException, SQLException, IOException;
	public List<Files> getfile(int user_id) throws ClassNotFoundException, SQLException, IOException;
	
	/*	public FilesPojo getFiles() throws ClassNotFoundException, SQLException, IOException;
*/}
