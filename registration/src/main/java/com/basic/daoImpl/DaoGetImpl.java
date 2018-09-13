package com.basic.daoImpl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import com.basic.dao.DaoGetAll;
import com.basic.database.Database;
import com.basic.pojo.Address;
import com.basic.pojo.Files;
import com.basic.pojo.User;

public class DaoGetImpl implements DaoGetAll{
	
	@Override
	public List<User> getAllUser() throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		
		Statement stat = Database.getConnection().createStatement();
		ResultSet result = stat.executeQuery("select * from userdata");
		
		User user;
		while(result.next()) {
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
		result.close();
		stat.close();
		Database.getConnection().close();
		return users;
	}
	
	public List<Address> getAllAddress() throws ClassNotFoundException, SQLException, IOException{
		List<Address> allAddress = new ArrayList<Address>();
		
		Statement stat = Database.getConnection().createStatement();
		ResultSet result = stat.executeQuery("select * from address");
		
		Address address;
		while(result.next()) {
			address = new Address();
			address.setAddress_id(result.getInt(1));
			address.setUser_id(result.getInt(2));
			address.setAddress_line1(result.getString(3));
			address.setAddress_line2(result.getString(4));
			address.setCity(result.getString(5));
			address.setState(result.getString(6));
			address.setCountry(result.getString(7));
			address.setPincode(result.getInt(8));
			address.setCreated_time(result.getString(9));
			address.setUpdate_by(result.getInt(10));
			address.setUpdate_time(result.getString(11));
		
			allAddress.add(address);
		}
		result.close();
		stat.close();
		Database.getConnection().close();
		return allAddress;	
	}
	
	@Override
	public List<Files> getAllFiles() throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		List<Files> allfiles = new ArrayList<Files>();
		
		Statement stat = Database.getConnection().createStatement();
		ResultSet result = stat.executeQuery("select * from files");
		
		Files file;
		while(result.next()) {
			file = new Files();
			file.setFile_id(result.getInt(1));
			file.setFile_type(result.getString(2));
			file.setFile(result.getBlob(3));
			file.setCreated_time(result.getString(4));
			file.setUpdate_by(result.getInt(5));
			file.setUpdate_time(result.getString(6));
			
			int blobLength = (int) result.getBlob(3).length();
			byte[] blobAsBytes = result.getBlob(3).getBytes(1, blobLength);
			file.setFilestring(Base64.getEncoder().encodeToString(blobAsBytes));
			
			allfiles.add(file);
		}
		result.close();
		stat.close();
		Database.getConnection().close();
		return allfiles;
	}
	
	@Override
	public User getUser(int user_id) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		Statement statement = Database.getConnection().createStatement();
		ResultSet result = statement.executeQuery("select * from userdata where user_id="+ user_id);
		
		User user = null;
		if(result.next()){
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
		result.close();
		statement.close();
		Database.getConnection().close();
		return user;
	}
	
	@Override
	public List<Address> getAddress(int user_id) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		List<Address> allAddress = new ArrayList<Address>();
		
		Statement statement = Database.getConnection().createStatement();
		ResultSet result = statement.executeQuery("select * from address where user_id="+user_id);

		Address address;
		while(result.next()) {
			address = new Address();
			address.setAddress_id(result.getInt(1));
			address.setUser_id(result.getInt(2));
			address.setAddress_line1(result.getString(3));
			address.setAddress_line2(result.getString(4));
			address.setCity(result.getString(5));
			address.setState(result.getString(6));
			address.setCountry(result.getString(7));
			address.setPincode(result.getInt(8));
			address.setCreated_time(result.getString(9));
			
		allAddress.add(address);
		}
		result.close();
		statement.close();
		Database.getConnection().close();
		return allAddress;
	}

	@Override
	public List<Files> getfile(int user_id) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		List<Files> fileList = new ArrayList<Files>();
		Statement stat  = Database.getConnection().createStatement();
		ResultSet result = stat.executeQuery("select * from filemap where user_id="+user_id);
		
		List<Integer> fileIds = new ArrayList<Integer>();
		while(result.next()){
			fileIds.add(result.getInt(3));
		}
		
		String file_id=null;
		if(fileIds!=null && fileIds.size()>0){
			file_id = getIdString(fileIds);
		}else{
			return fileList;	
		}
		result.close();
		stat.close();
		
		Statement statement = Database.getConnection().createStatement();
		ResultSet results = statement.executeQuery("select * from files where file_id in("+ file_id +")");
		
		Files file;
		while(results.next()) {
			file = new Files();
			file.setFile_id(results.getInt(1));
			file.setFile_type(results.getString(2));
			file.setFile(results.getBlob(3));
			file.setCreated_time(results.getString(4));
			
			int blobLength = (int) results.getBlob(3).length();
			byte[] blobAsBytes = results.getBlob(3).getBytes(1, blobLength);
			file.setFilestring(Base64.getEncoder().encodeToString(blobAsBytes));
			
			fileList.add(file);
		}
		return fileList;
	}
	
	private String getIdString(List<Integer> Ids) {
		String[] ids = new String[Ids.size()];
		for (int index = 0; index < Ids.size(); index++) {
				ids[index] = Ids.get(index).toString();
		}
			return Arrays.toString(ids).replaceAll("\\[|\\]", "").replaceAll(",", ",");
	}
}
