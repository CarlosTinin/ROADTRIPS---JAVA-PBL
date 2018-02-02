package facade;

import java.util.Iterator;

import controller.AdminController;
import exception.DuplicateKeyException;
import exception.DuplicateUserException;
import model.Cidade;
import model.User;

public class AdminFacade extends UserFacade{

	private AdminController controller = new AdminController();
	
	public Iterator<User> listUsers() {
		return controller.listUsers();
	}
	
	public Iterator<Cidade> listCities() {
		return controller.listCities();
	}
	
	public boolean registerAdmin(String name, String nickname, String email, String password) throws DuplicateUserException {
		return controller.registerAdmin(name, nickname, email, password);		
	}
	
	public void updateUser(String name, String nickname, String email) throws DuplicateUserException {
		controller.updateUser(name, nickname, email);
	}
	
	public void registerNewCity(String name, double area, double populacao, String descricao) throws DuplicateKeyException {
		controller.registerCity(name, area, populacao, descricao);
	}

	public void excluirUsuario(String email, String admin) throws IllegalArgumentException{
		controller.excluirUsuario(email, admin);
	}
}
