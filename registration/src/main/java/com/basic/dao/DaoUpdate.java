package com.basic.dao;

import com.basic.pojo.Address;
import com.basic.pojo.Files;
import com.basic.pojo.User;

public interface DaoUpdate {
	
	boolean updateUser(User userValue);
	boolean updateAddress(Address addressValue);
	boolean updateFile(Files fileValue);
	
}
