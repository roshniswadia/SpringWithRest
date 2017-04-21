package org.springrestful.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Repository;
import org.springrestful.bean.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	private static final AtomicInteger counter = new AtomicInteger();
    static List<User> users = new ArrayList<User>(
            Arrays.asList(
                    new User(counter.incrementAndGet(), "Daenerys Targaryen"),
                    new User(counter.incrementAndGet(), "John Snow"),
                    new User(counter.incrementAndGet(), "Arya Stark"),
                    new User(counter.incrementAndGet(), "Cersei Baratheon")));

	public List<User> getAllUsers() {
		return users;
	}

	public User findById(final int userId) {
		
		for (User user : users){
            if (user.getId() == userId){
                return user;
            }
        }
        return null;
	}

	public void addUser(final User user) {
		user.setId(counter.incrementAndGet());
        users.add(user);
		
	}

	public void deleteUser(final int id) {
		 User user = findById(id);
	        users.remove(user);
	}
	
	

}
