package com.user.management.service;

import java.util.ArrayList;
import java.util.List;

import com.user.management.dao.UserDao;
import com.user.management.model.binding.UserBindingModel;
import com.user.management.model.entity.User;
import com.user.management.model.service.UserServiceModel;
import com.user.management.util.mapper.Mapper;

public class UserServiceImpl implements UserService{
	private final UserDao userDao;
	private final Mapper mapper;

	public UserServiceImpl(UserDao userDao, Mapper mapper) {
		this.userDao = userDao;
		this.mapper = mapper;
	}

	@Override
	public void insert(UserServiceModel userServiceModel) {
		this.userDao.insertUser(this.mapper.map(userServiceModel, User.class));
	}

	@Override
	public UserBindingModel select(long userId) {
		User user = this.userDao.selectUser(userId);
		UserBindingModel userBindingModel = this.mapper.map(user, UserBindingModel.class);
		return userBindingModel;
	}

	@Override
	public List<UserBindingModel> find(String name) {
		List<User> usersWithFirstName = this.userDao.findUser(name);
		List<UserBindingModel> users = new ArrayList<>();
		for(User user : usersWithFirstName) {
			users.add(this.mapper.map(user, UserBindingModel.class));
		}
		return users;
	}

	@Override
	public List<UserBindingModel> getAll() {
		List<User> usersDb = this.userDao.getAll();
		List<UserBindingModel> users = new ArrayList<>();
		for(User user : usersDb) {
			users.add(this.mapper.map(user, UserBindingModel.class));
		}
		return users;
	}

	@Override
	public boolean delete(long id) {
		return this.userDao.deleteUser(id);
	}

	@Override
	public void update(UserServiceModel user) {
		this.userDao.updateUser(this.mapper.map(user, User.class));
	}
	
}
