package com.tweetapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tweetapp.model.ChangePasswordTo;
import com.tweetapp.model.CreateUserTo;
import com.tweetapp.model.BaseUserTo;
import com.tweetapp.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/v1")
@CrossOrigin
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public ResponseEntity<BaseUserTo> signUp(@RequestBody CreateUserTo createUserTo) {
		String response = userService.signUp(createUserTo);
		if(response.equals(userService.USER_ADDED)) {
			final BaseUserTo baseUserTo = new BaseUserTo();
			baseUserTo.setName(createUserTo.getName());
			baseUserTo.setPassword(createUserTo.getPassword());
			baseUserTo.setEmail(createUserTo.getEmail());
			baseUserTo.setGender(createUserTo.getGender());
			return new ResponseEntity<>(baseUserTo, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/login")
	public ResponseEntity<BaseUserTo> login(@RequestBody BaseUserTo userTo) {
		System.out.println(userTo);
		String response = userService.login(userTo);
		System.out.println(response);
		if(response.equals(userService.SUCCESS))
			return new ResponseEntity<>(userTo, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping("/change-password")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordTo changePasswordTo) {
		String response = userService.changePassword(changePasswordTo);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/get-user")
	public ResponseEntity<BaseUserTo> getUser(@RequestBody BaseUserTo baseUser, @RequestBody String username) {
		BaseUserTo baseUserTo = UserService.convertUserJpaToBaseUser(userService.findByUsername(username));
		return new ResponseEntity<>(baseUserTo, HttpStatus.OK);
	}

	@PostMapping("/get-all-users")
	public ResponseEntity<List<BaseUserTo>> getAllUsers(@RequestBody BaseUserTo baseUser) {
		if(baseUser == null) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		List<BaseUserTo> baseUserToList = userService.getAllUsers().stream().map(userJpa ->  {
			BaseUserTo baseUserTo = new BaseUserTo();
			baseUserTo.setId(userJpa.getId());
			baseUserTo.setName(userJpa.getName());
			baseUserTo.setPassword(userJpa.getPassword());
			baseUserTo.setEmail(userJpa.getEmail());
			baseUserTo.setGender(userJpa.getGender());
			return baseUserTo;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(baseUserToList, HttpStatus.OK);
	}

}