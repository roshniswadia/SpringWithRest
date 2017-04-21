package org.springrestful.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springrestful.bean.User;
import org.springrestful.dao.UserDao;

public interface UserService {
	
	public List<User> getAllUsers();
	public User findById(int userId);
	public void addUser(User user);
	public void deleteUser(int id);
	

}
