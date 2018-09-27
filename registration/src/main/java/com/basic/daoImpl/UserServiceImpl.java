package com.basic.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.basic.dao.UserService;
import com.basic.database.Database;
import com.basic.pojo.User;

public class UserServiceImpl implements UserService {
	PreparedStatement stat;
	ResultSet result;
	@Override
	public boolean isValid(User value) {
		// TODO Auto-generated method stub
		String password = " ";
		try {
			stat = Database.getConnection().prepareStatement("select password from userdata where email= ?");
			stat.setString(1, value.getEmail());
			result = stat.executeQuery();
			
			while(result.next()){
				password = result.getString(1);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				result.close();
				stat.close();
				Database.getConnection().close();
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return value.getPassword().equals(password) ? true : false;				
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
			User loggedInUser = null;
		try {
			stat = Database.getConnection().prepareStatement("select * from userdata where email= ?");
			stat.setString(1, user.getEmail());
			result = stat.executeQuery();

			while(result.next()){
				loggedInUser = new User();
				loggedInUser.setUser_id(result.getInt(1));
				loggedInUser.setRole_id(result.getInt(2));
				loggedInUser.setFirstname(result.getString(3));
				loggedInUser.setLastname(result.getString(4));
				loggedInUser.setEmail(result.getString(5));
				loggedInUser.setMobile_no(result.getLong(6));
				loggedInUser.setGender(result.getString(8));
				loggedInUser.setDate_of_birth(result.getString(9));
				loggedInUser.setCreated_time(result.getString(10));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				result.close();
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return loggedInUser;
	}

	@Override
	public String getPass(User user) {
		// TODO Auto-generated method stub
		String password = "";
		try {
			stat = Database.getConnection().prepareStatement("select password from userdata where email=?");
			stat.setString(1,  user.getEmail());
			result = stat.executeQuery();
			
			while(result.next()){
				password = result.getString(1);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				result.close();
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return password;
	}

}
