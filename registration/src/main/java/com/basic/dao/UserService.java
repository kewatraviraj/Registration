package com.basic.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.basic.pojo.User;

public interface UserService {

	public boolean isValid(User value) throws ClassNotFoundException, SQLException, IOException;
	public User getUser(User user) throws ClassNotFoundException, SQLException, IOException;
	public String getPass(User user)throws ClassNotFoundException, SQLException, IOException;
}
