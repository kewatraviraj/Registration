package com.basic.dao;

import java.util.List;
import com.basic.pojo.Address;
import com.basic.pojo.Files;
import com.basic.pojo.User;

public interface DaoGetAll {
	
	List<User> getAllUser();
	List<Address> getAllAddress();
	List<Files> getAllFiles();
	
	User getUser(int user_id);
	List<Address> getAddress(int user_id);
	List<Files> getfile(int user_id);
	
	/*	public FilesPojo getFiles() throws ClassNotFoundException, SQLException, IOException;
*/}
