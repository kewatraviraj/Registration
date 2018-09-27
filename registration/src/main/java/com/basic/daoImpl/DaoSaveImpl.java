/**
 * 
 */
package com.basic.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.basic.database.Database;
import com.basic.controller.ControllerRegister;
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
	static Logger log = Logger.getLogger(ControllerRegister.class.getName());

	PreparedStatement pstatement;
	int result;
	
	@Override
	public int saveuser(User Value1) {
		// TODO Auto-generated method stub
		int user_id = 0;
		
		try {
			pstatement = Database.getConnection().prepareStatement(
					"insert into userdata(role_id,firstname,lastname,email,mobile_no,password,gender,date_of_birth,created_time) values(?,?,?,?,?,?,?,?,Now())",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstatement.setInt(1, Value1.getRole_id());
			pstatement.setString(2, Value1.getFirstname());
			pstatement.setString(3, Value1.getLastname());
			pstatement.setString(4, Value1.getEmail());
			pstatement.setLong(5, Value1.getMobile_no());
			pstatement.setString(6, Value1.getPassword());
			pstatement.setString(7, Value1.getGender());
			pstatement.setString(8, Value1.getDate_of_birth());

			if(pstatement.executeUpdate() == 1) {
				log.info("Successfully saved User Details");
				ResultSet result = pstatement.getGeneratedKeys();
				user_id = result.next() ? result.getInt(1) : 0;	
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstatement.close();
				Database.getConnection().close();
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user_id;
	}

	public boolean saveaddress(Address Value2) {
		try {
			pstatement = Database.getConnection().prepareStatement(
					"insert into address(user_id,address_line1,address_line2,city,state,country,pincode,created_time)"
							+ "values(?,?,?,?,?,?,?,Now())");
			pstatement.setInt(1, Value2.getUser_id());
			pstatement.setString(2, Value2.getAddress_line1());
			pstatement.setString(3, Value2.getAddress_line2());
			pstatement.setString(4, Value2.getCity());
			pstatement.setString(5, Value2.getState());
			pstatement.setString(6, Value2.getCountry());
			pstatement.setInt(7, Value2.getPincode());

			result = pstatement.executeUpdate();
			

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstatement.close();
				Database.getConnection().close();
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result == 1 ? true : false;
	}

	public int savefile(Files fileValue) {
		int file_id = 0;
		try {
			pstatement = Database.getConnection().prepareStatement(
					"insert into files(file_type,file,created_time) values(?,?,Now())",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstatement.setString(1, fileValue.getFile_type());
			pstatement.setBlob(2, fileValue.getFile());

			if(pstatement.executeUpdate()==1) {
				ResultSet result = pstatement.getGeneratedKeys();
				file_id = result.next() ? result.getInt(1) : 0;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstatement.close();
				Database.getConnection().close();
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file_id;
	}

	@Override
	public boolean savefilemap(Filemap filemap) {
		// TODO Auto-generated method stub
		try {
			pstatement = Database.getConnection().prepareStatement("insert into filemap(user_id,file_id) values(?,?)");
			pstatement.setInt(1, filemap.getUser_id());
			pstatement.setInt(2, filemap.getFile_id());
			result = pstatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstatement.close();
				Database.getConnection().close();
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result == 1 ? true : false;
	}

}
