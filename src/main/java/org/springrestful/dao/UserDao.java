package org.springrestful.dao;

import java.util.List;

import org.springrestful.bean.User;

public interface UserDao {

	public List<User> getAllUsers();
	public User findById(int userId);
	public void addUser(User user);
	public void deleteUser(int id);

}
