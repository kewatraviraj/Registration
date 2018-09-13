package com.basic.daoImpl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.basic.dao.UserService;
import com.basic.database.Database;
import com.basic.pojo.User;

public class UserServiceImpl implements UserService {

	@Override
	public boolean isValid(User value) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		Statement stat = Database.getConnection().createStatement();
		String query = "select password from userdata where email='"+ value.getEmail() +"'";
		ResultSet result = stat.executeQuery(query);
		
		String password = " ";
		while(result.next()){
			password = result.getString(1);
		}
		result.close();
		Database.getConnection().close();
		return ( password.equals(value.getPassword()) ? true : false );				
	}

	@Override
	public User getUser(User user) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		Statement stmt = Database.getConnection().createStatement();
		ResultSet result = stmt.executeQuery("select * from userdata where email='"+ user.getEmail()+"'");

		User loggedInUser = null;
		if(result.next()){
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
		return loggedInUser;
	}

	@Override
	public String getPass(User user) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Statement stmt = Database.getConnection().createStatement();
		ResultSet result = stmt.executeQuery("select password from userdata where email='"+ user.getEmail()+"' and mobile_no="+user.getMobile_no());
		String password = null;
		if(result.next()){
			password = result.getString(1);
		}
		return password;
	}

}
