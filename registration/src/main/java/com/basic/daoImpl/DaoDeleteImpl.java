package com.basic.daoImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.basic.dao.DaoDelete;
import com.basic.database.Database;

public class DaoDeleteImpl implements DaoDelete {
		int result;
		PreparedStatement stat;
	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub	
		try {
			stat = Database.getConnection().prepareStatement("delete from userdata where user_id= ?");
			stat.setInt(1, id);
			result = stat.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stat.close();
				Database.getConnection().close();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result == 1 ? true : false;
	}

	@Override
	public boolean deleteAddress(String id) {
		// TODO Auto-generated method stub
		try {
			stat = Database.getConnection().prepareStatement("delete from address where address_id in(?)");
			stat.setString(1, id);
			result = stat.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stat.close();
				Database.getConnection().close();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result == 1 ? true : false;
	}

}
