package com.user.management.service;

import java.util.List;

import com.user.management.model.binding.UserBindingModel;
import com.user.management.model.service.UserServiceModel;

public interface UserService {
	
	void insert(UserServiceModel user);

	UserBindingModel select(long userId);
	
	List<UserBindingModel> find(String name);
	
	List<UserBindingModel> getAll();
	
	boolean delete(long id);
	
	void update(UserServiceModel user);

}
