package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import exception.DuplicateUserException;
import exception.UserNotFoundException;
import model.Session;

abstract public class UserController {
	
	private Session session;

	public UserController() {
		this.session = Session.getInstance();
	}
	
	abstract public void validateCredentials(String name, String password) throws UserNotFoundException;
	abstract public boolean registerUser(String name, String nickname, String email, String password) throws DuplicateUserException;

	public Session getSession() {
		return this.session;
	}
	
	abstract public void save() throws IOException;
	abstract public void recuperarSave() throws FileNotFoundException, IOException, ClassNotFoundException;
}
