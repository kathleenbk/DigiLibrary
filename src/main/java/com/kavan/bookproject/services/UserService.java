package com.kavan.bookproject.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.kavan.bookproject.models.LoginUser;
import com.kavan.bookproject.models.User;
import com.kavan.bookproject.repositories.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public User register(User newUser, BindingResult result) {
		Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
		Optional<User> optionalUser2 = userRepo.findByUsername(newUser.getUsername());


		if (optionalUser.isPresent()) {
			result.rejectValue("email", "taken", "That email is already taken");
		}
		
		if (optionalUser2.isPresent()) {
			result.rejectValue("username", "taken", "That username is already taken");
		}

		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Passwords must match");
		}

		if (result.hasErrors())
			return null;

		String hashedPass = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashedPass);
		return userRepo.save(newUser);
	}

	public User login(LoginUser newLoginObject, BindingResult result) {

		Optional<User> optionalUser = userRepo.findByUsername(newLoginObject.getUsername());


		if (!optionalUser.isPresent()) {
			result.rejectValue("username", "Matches", "Can not find a user with that username.");
			return null;
		}


		User user = optionalUser.get();

		if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Incorrect password!");
		}

		// Return null if result has errors
		if (result.hasErrors())
			return null;
		// Otherwise, return the user object
		return user;
	}

	public User findUser(Long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}
		return null;
	}
	
	public User findByEmail(String email) {
		Optional<User> optionalUser = userRepo.findByEmail(email);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}
		return null;
	}
	
	public User findByUsername(String username) {
		Optional<User> optionalUser = userRepo.findByUsername(username);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}
		return null;
	}
	
	
	public User update(User user) {
		return userRepo.save(user);
	}
	
	public List<User> allUsers(){
		return userRepo.findAll();
	}
}

