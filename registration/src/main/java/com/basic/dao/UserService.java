package com.basic.dao;

import com.basic.pojo.User;

public interface UserService {

	boolean isValid(User value);
	User getUser(User user);
	String getPass(User user);
}
