package com.basic.dao;

import java.io.IOException;
import java.sql.SQLException;

public interface DaoDelete {
	
	public boolean deleteUser(int id) throws ClassNotFoundException, SQLException, IOException;
	public boolean deleteAddress(String id) throws ClassNotFoundException, SQLException, IOException;
}
