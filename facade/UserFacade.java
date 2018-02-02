package facade;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.AdminController;
import controller.UserController;
import exception.DuplicateUserException;
import exception.UserNotFoundException;
import model.User;

public class UserFacade {
	private UserController userController = new AdminController(); 
	
	public UserFacade() {
		
	}

    public void validateCredentials(String email, String password) throws UserNotFoundException {
    	userController.validateCredentials(email, password);
    }
    
    public boolean registerUser(String name, String nickname, String email, String password) throws DuplicateUserException {
    	return userController.registerUser(name, nickname, email, password);
    }
    
    public void initComponents() throws FileNotFoundException, ClassNotFoundException, IOException {
    	userController.recuperarSave();
    }
    
    public void save() throws IOException {
    	userController.save();
    }
    
    public User auth() {
    	return userController.getSession().getUser();
    }
    
    public void logout() {
    	userController.getSession().finalize();
    }
}
