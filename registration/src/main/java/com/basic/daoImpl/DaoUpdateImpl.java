package com.basic.daoImpl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.basic.dao.DaoUpdate;
import com.basic.database.Database;
import com.basic.pojo.Address;
import com.basic.pojo.Files;
import com.basic.pojo.User;

public class DaoUpdateImpl implements DaoUpdate {
	PreparedStatement pstatement;
	
	@Override
	public boolean updateUser(User userValue)
			throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		pstatement = Database.getConnection().prepareStatement("update userdata set firstname = ?, lastname = ?, email = ?, mobile_no = ?, "
				+ "gender = ?, date_of_birth= ?, update_by = ?, update_time = Now() Where user_id = " + userValue.getUser_id());
		pstatement.setString(1, userValue.getFirstname());
		pstatement.setString(2, userValue.getLastname());
		pstatement.setString(3, userValue.getEmail());
		pstatement.setLong(4, userValue.getMobile_no());
		pstatement.setString(5, userValue.getGender());
		pstatement.setString(6, userValue.getDate_of_birth());
		pstatement.setInt(7, userValue.getUpdate_by());
		
		int result = pstatement.executeUpdate();
		return ( result == 1 ? true : false);
	}
	
	@Override
	public boolean updateAddress(Address Value2)
			throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		pstatement = Database.getConnection().prepareStatement("update address set address_line1 = ?, address_line2 = ?,"
				     + " city = ?, state = ?, country = ?, pincode = ?, update_by = ?, update_time = Now() where address_id =" +Value2.getAddress_id());

		pstatement.setString(1, Value2.getAddress_line1());
		pstatement.setString(2, Value2.getAddress_line2());
		pstatement.setString(3, Value2.getCity());
		pstatement.setString(4, Value2.getState());
		pstatement.setString(5, Value2.getCountry());
		pstatement.setInt(6, Value2.getPincode());
		pstatement.setInt(7, Value2.getUpdate_by());
		
		int result = pstatement.executeUpdate();
		pstatement.close();
		return (result == 1 ? true : false);
	}
	
	@Override
	public boolean updateFile(Files fileValue) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		pstatement = Database.getConnection().prepareStatement("update files file_type = ?, file = ?, update_by = ?, update_time = Now() where file_id ="+fileValue.getFile_id());
		pstatement.setString(1, fileValue.getFile_type());
		pstatement.setBlob(2, fileValue.getFile());
		pstatement.setInt(3, fileValue.getUpdate_by());
		
		int result = pstatement.executeUpdate(); 
		return (result == 1 ? true : false);
	}


}
