package com.basic.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.Part;

import com.basic.pojo.Address;
import com.basic.pojo.Files;
import com.basic.pojo.User;

public interface DaoUpdate {
	
	public boolean updateUser(User userValue) throws ClassNotFoundException, SQLException, IOException;
	public boolean updateAddress(Address addressValue) throws ClassNotFoundException, SQLException, IOException;
	public boolean updateFile(Files fileValue, Part file) throws ClassNotFoundException, SQLException, IOException;
	
}
