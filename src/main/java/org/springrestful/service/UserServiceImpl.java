package org.springrestful.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springrestful.bean.User;
import org.springrestful.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	public List<User> getAllUsers() {
		
		return userDao.getAllUsers();
	}

	public User findById(final int userId) {
		return userDao.findById(userId);
	}

	public void addUser(final User user) {
		userDao.addUser(user);
	}

	public void deleteUser(final int id) {
		userDao.deleteUser(id);
	}

}
