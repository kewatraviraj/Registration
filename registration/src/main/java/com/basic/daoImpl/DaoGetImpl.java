package com.basic.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import com.basic.dao.DaoGetAll;
import com.basic.database.Database;
import com.basic.pojo.Address;
import com.basic.pojo.Files;
import com.basic.pojo.User;

public class DaoGetImpl implements DaoGetAll {
	PreparedStatement stat;
	ResultSet result;

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		try {
			stat = Database.getConnection().prepareStatement("select * from userdata");
			result = stat.executeQuery();
			User user;
			while (result.next()) {
				user = new User();
				user.setUser_id(result.getInt(1));
				user.setRole_id(result.getInt(2));
				user.setFirstname(result.getString(3));
				user.setLastname(result.getString(4));
				user.setEmail(result.getString(5));
				user.setMobile_no(result.getLong(6));
				user.setPassword(result.getString(7));
				user.setGender(result.getString(8));
				user.setDate_of_birth(result.getString(9));
				user.setCreated_time(result.getString(10));
				user.setUpdate_by(result.getInt(11));
				user.setUpdate_time(result.getString(12));

				users.add(user);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				result.close();
				stat.close();
				Database.getConnection().close();
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return users;
	}

	/**
	 */
	public List<Address> getAllAddress() {
		List<Address> allAddress = new ArrayList<Address>();

		try {
			stat = Database.getConnection().prepareStatement("select * from address");
			result = stat.executeQuery();
			
			allAddress = fetchAddressDetails(result);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				result.close();
				stat.close();
				Database.getConnection().close();
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return allAddress;
	}

	@Override
	public List<Files> getAllFiles() {
		// TODO Auto-generated method stub
		List<Files> allfiles = new ArrayList<Files>();

		try {
			stat = Database.getConnection().prepareStatement("select * from files");
			result = stat.executeQuery();

			Files file;
			while (result.next()) {
				file = new Files();
				file.setFile_id(result.getInt(1));
				file.setFile_type(result.getString(2));
				file.setCreated_time(result.getString(4));
				file.setUpdate_by(result.getInt(5));
				file.setUpdate_time(result.getString(6));

				file.setFilestring(Base64.getEncoder().encodeToString(result.getBytes(3)));

				allfiles.add(file);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				result.close();
				stat.close();
				Database.getConnection().close();
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return allfiles;
	}

	@Override
	public User getUser(int user_id) {
		// TODO Auto-generated method stub
			User user = null;
		try {
			stat = Database.getConnection().prepareStatement("select * from userdata where user_id= ?");
			stat.setInt(1, user_id);
			result = stat.executeQuery();
			
			while (result.next()) {
				user = new User();
				user.setUser_id(result.getInt(1));
				user.setRole_id(result.getInt(2));
				user.setFirstname(result.getString(3));
				user.setLastname(result.getString(4));
				user.setEmail(result.getString(5));
				user.setMobile_no(result.getLong(6));
				user.setGender(result.getString(8));
				user.setDate_of_birth(result.getString(9));
				user.setCreated_time(result.getString(10));
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
		return user;
	}

	@Override
	public List<Address> getAddress(int user_id) {
		// TODO Auto-generated method stub
		List<Address> allAddress = new ArrayList<Address>();
		try {
			stat = Database.getConnection().prepareStatement("select * from address where user_id= ?");
			stat.setInt(1, user_id);
			result = stat.executeQuery();
			
			allAddress = fetchAddressDetails(result);
			
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
		return allAddress;
	}

	@Override
	public List<Files> getfile(int user_id) {
		// TODO Auto-generated method stub
		List<Files> fileList = new ArrayList<Files>();
		try {
				stat = Database.getConnection().prepareStatement("select * from filemap where user_id= ?");
				stat.setInt(1, user_id);
				result = stat.executeQuery();
			
				List<Integer> fileIds = new ArrayList<Integer>();
				while (result.next()) {
					fileIds.add(result.getInt(3));
				}
				result.close();
				stat.close();
			
				String file_id = "";
				if (fileIds.size() > 0) {
					file_id = getIdString(fileIds);
				} else {
					return fileList;
				}
			
				stat = Database.getConnection().prepareStatement("select * from files where file_id in(?)");
				stat.setString(1, file_id);
				result = stat.executeQuery();
			
				Files file;
				while (result.next()) {
					file = new Files();
					file.setFile_id(result.getInt(1));
					file.setFile_type(result.getString(2));
					file.setFile(result.getBinaryStream(3));
					file.setCreated_time(result.getString(4));
			
					file.setFilestring(Base64.getEncoder().encodeToString(result.getBytes(3)));
			
					fileList.add(file);
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
		return fileList;
	}
	
	/**
	 * @param result2 
	 * @return 
	 * @throws SQLException 
	 * 
	 */
	private List<Address> fetchAddressDetails(ResultSet result2) throws SQLException {
		// TODO Auto-generated method stub
		List<Address> allAddress = new ArrayList<Address>();
		
		Address address;
		while (result2.next()) {
			address = new Address();
			address.setAddress_id(result2.getInt(1));
			address.setUser_id(result2.getInt(2));
			address.setAddress_line1(result2.getString(3));
			address.setAddress_line2(result2.getString(4));
			address.setCity(result2.getString(5));
			address.setState(result2.getString(6));
			address.setCountry(result2.getString(7));
			address.setPincode(result2.getInt(8));
			address.setCreated_time(result2.getString(9));
			address.setUpdate_by(result2.getInt(10));
			address.setUpdate_time(result2.getString(11));
			
			allAddress.add(address);
		}
		return allAddress;
	}
	
	/**
	 * @param Ids
	 * @return
	 */
	private String getIdString(List<Integer> Ids) {
		String[] ids = new String[Ids.size()];
		for (int index = 0; index < Ids.size(); index++) {
			ids[index] = Ids.get(index).toString();
		}
		return Arrays.toString(ids).replaceAll("\\[|\\]", "").replaceAll(",", ",");
	}
}
