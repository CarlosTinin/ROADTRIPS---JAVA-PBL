package facade;

import controller.AdminController;
import controller.UserController;
import exception.DuplicateUserException;
import exception.UserNotFoundException;

public class UserFacade {
	private UserController userController = new AdminController(); 
	
	public UserFacade() {
		// TODO Auto-generated constructor stub
	}

    public boolean validateCredentials(String email, String password) throws UserNotFoundException {
    	return userController.validateCredentials(email, password);
    }
    
    public boolean registerUser(String name, String nickname, String email, String password) throws DuplicateUserException {
    	return userController.registerUser(name, nickname, email, password);
    }
}
