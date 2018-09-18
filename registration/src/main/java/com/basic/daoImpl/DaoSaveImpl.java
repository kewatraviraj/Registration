/**
 * 
 */
package com.basic.daoImpl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.basic.database.Database;
import com.basic.dao.DaoSave;
import com.basic.pojo.Address;
import com.basic.pojo.Filemap;
import com.basic.pojo.Files;
import com.basic.pojo.User;

/**
 * @author HP
 *
 */
public class DaoSaveImpl implements DaoSave {
	PreparedStatement pstatement;
	String query;
	@Override
	public int saveuser(User Value1) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		query ="insert into userdata(role_id,firstname,lastname,email,mobile_no,password,gender,date_of_birth,created_time)"
				+ "values(?,?,?,?,?,?,?,?,Now())";
		pstatement = Database.getConnection().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
		
		pstatement.setInt(1, Value1.getRole_id());
		pstatement.setString(2, Value1.getFirstname());
		pstatement.setString(3, Value1.getLastname());
		pstatement.setString(4, Value1.getEmail());
		pstatement.setLong(5, Value1.getMobile_no());
		pstatement.setString(6, Value1.getPassword());
		pstatement.setString(7, Value1.getGender());
		pstatement.setString(8, Value1.getDate_of_birth());
		
		pstatement.executeUpdate();
		ResultSet result = pstatement.getGeneratedKeys();
		int user_id = result.next() ? result.getInt(1): 0;
		
		pstatement.close();
		Database.getConnection().close();
		return (user_id);
	}

	
	public boolean saveaddress(Address Value2) throws ClassNotFoundException, SQLException, IOException {
		
		pstatement = Database.getConnection().prepareStatement("insert into address(user_id,address_line1,address_line2,city,state,country,pincode,created_time)"
				+ "values(?,?,?,?,?,?,?,Now())");
		
		pstatement.setInt(1,Value2.getUser_id());
		pstatement.setString(2, Value2.getAddress_line1());
		pstatement.setString(3, Value2.getAddress_line2());
		pstatement.setString(4, Value2.getCity());
		pstatement.setString(5, Value2.getState());
		pstatement.setString(6, Value2.getCountry());
		pstatement.setInt(7, Value2.getPincode());
		
		int result = pstatement.executeUpdate();
		pstatement.close();
		Database.getConnection().close();
		return (result == 1 ? true : false);
	}
	
	public int savefile(Files fileValue) throws ClassNotFoundException, SQLException, IOException {
		query ="insert into files(file_type,file,created_time) values(?,?,Now())";
		pstatement = Database.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		pstatement.setString(1, fileValue.getFile_type());
		pstatement.setBlob(2, fileValue.getFile());
		
		pstatement.executeUpdate();
		ResultSet result = pstatement.getGeneratedKeys();
		int file_id = result.next() ? result.getInt(1): 0;
		
		pstatement.close();
		Database.getConnection().close();
		return (file_id);
	}

	@Override
	public boolean savefilemap(Filemap filemap) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		pstatement = Database.getConnection().prepareStatement("insert into filemap(user_id,file_id) values(?,?)");
		pstatement.setInt(1,filemap.getUser_id());
		pstatement.setInt(2, filemap.getFile_id());
		int result = pstatement.executeUpdate();
		pstatement.close();
		Database.getConnection().close();
		return (result == 1 ? true : false);
	}
	
}
