package com.user.management.dao;

import java.util.List;

import com.user.management.model.entity.User;

public interface UserDao {
	void insertUser(User user);
	
	User selectUser(long userId);
	
	List<User> findUser(String name);
	
	List<User> getAll();
	
	boolean deleteUser(long id);
	
	void updateUser(User user);
}
