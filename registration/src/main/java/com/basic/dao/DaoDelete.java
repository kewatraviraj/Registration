package com.basic.dao;

import java.io.IOException;
import java.sql.SQLException;

public interface DaoDelete {
	
	/**
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	boolean deleteUser(int id);
	/**
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	boolean deleteAddress(String id);
}
