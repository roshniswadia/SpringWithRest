package org.springrestful.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springrestful.bean.User;
import org.springrestful.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private static Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		System.out.println("Int Get All Users");
		final List<User> users = userService.getAllUsers();
		if (users == null || users.isEmpty()) {
			LOG.info("Users Not Found");
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<User>>(users,HttpStatus.OK);

	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id")final Integer id) {
		LOG.info("getting user with id: {}", id);
		final User user = userService.findById(id);

		if (user == null) {
			LOG.info("user with id {} not found", id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody final User user, final UriComponentsBuilder ucBuilder) {
		System.out.println("In add user");
		LOG.info("creating new user: {}", user);

		userService.addUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("id")final Integer id) {
		
		if (id == null) {
			LOG.info("User Id is null", id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		LOG.info("deleting user with id: {}", id);
		final User user = userService.findById(id);

		if (user == null) {
			LOG.info("Unable to delete. User with id {} not found", id);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
