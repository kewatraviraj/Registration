package com.basic.daoImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import com.basic.dao.DaoDelete;
import com.basic.database.Database;

public class DaoDeleteImpl implements DaoDelete {

	@Override
	public boolean deleteUser(int id) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Statement stat = Database.getConnection().createStatement();
		int result = stat.executeUpdate("delete from userdata where user_id="+id);
		
		stat.close();
		Database.getConnection().close();
		return (result == 1 ? true : false);
	}


	@Override
	public boolean deleteAddress(String id) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Statement stat = Database.getConnection().createStatement();
		int result = stat.executeUpdate("delete from address where address_id in("+ id +")");
		
		stat.close();
		Database.getConnection().close();
		return (result == 1 ? true : false);
	}

}
