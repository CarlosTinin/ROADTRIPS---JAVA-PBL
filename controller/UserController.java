package controller;

import exception.DuplicateUserException;
import exception.UserNotFoundException;

abstract public class UserController {

	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	abstract public boolean validateCredentials(String name, String password) throws UserNotFoundException;
	abstract public boolean registerUser(String name, String nickname, String email, String password) throws DuplicateUserException;
}
