package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import exception.DuplicateKeyException;
import exception.DuplicateUserException;
import exception.UserNotFoundException;
import model.AdjMapGraph;
import model.Admin;
import model.Cidade;
import model.Ponto;
import model.User;

public class AdminController extends UserController {
	
	private static ArrayList<User> usersList = new ArrayList<>();
	private static AdjMapGraph<Ponto> mapa = new AdjMapGraph<>();
	private int cityId = 0;
	
	public AdminController() {}
	
	public Iterator<User> listUsers() {
		return AdminController.usersList.iterator();
	}
	
	/**
	 * Registra novo usuário de nível administrador.
	 * 
	 * @param String name, String nickname, String email, String password
	 * @return boolean, DuplicateUserException
	 * */
	public boolean registerAdmin(String name, String nickname, String email, String password) throws DuplicateUserException {
		if (this.getUserByEmail(email) == null) {
			AdminController.usersList.add(new Admin(name, nickname, email, password));
			return true;
		}
		throw new DuplicateUserException();
	}
	
	/**
	 * Atualiza as informações de um usuário.
	 * 
	 * @param String name, String nickname, String olderEmail, String newEmail
	 * @return void, DuplicateUserException
	 * */
	public void updateUser(String name, String nickname, String email) throws DuplicateUserException {
		User user = this.getUserByEmail(email);		
		user.setName(name);
		user.setNickname(nickname);
		return;
	}
	
	public User deleteUser() {
		// TODO
		return null;
	}
	
	/**
	 * Valida as credenciais de um usuário.
	 * 
	 * @param String email, String password
	 * @return true, ou lanca a exceção {@link UserNotFoundException}
	 * */
	@Override
	public void validateCredentials(String email, String password) throws UserNotFoundException {
		User user = this.getUserByEmail(email);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				this.getSession().setUser(user);
				return;
			}
		}			
		throw new UserNotFoundException();
	}
	
	/**
	 * Retorna o usuário que tem o email passado por parâmetro, se existir.
	 * 
	 * @param String email
	 * @return boolean
	 * */
	private User getUserByEmail(String email) {
		Iterator<User> userIterator = usersList.iterator();
		while (userIterator.hasNext()) {
			User user = userIterator.next();
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Registra novo usuário.
	 * 
	 * @param String name, String nickname, String email, String password
	 * @return boolean, DuplicateUserException
	 * */
	@Override
	public boolean registerUser(String name, String nickname, String email, String password) throws DuplicateUserException {
		if (this.getUserByEmail(email) == null) {
			AdminController.usersList.add(new User(name, nickname, email, password));
			return true;
		}
		throw new DuplicateUserException();
	}
	
	/**
	 * Recupera as informações do sistema
	 * 
	 * @param
	 * @return FileNotFoundException, IOException, ClassNotFoundException
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public void recuperarSave() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("files/system_files/usuarios.data");
		ObjectInputStream fis;		
		fis = new ObjectInputStream(new FileInputStream(file));
		AdminController.usersList = (ArrayList<User>) fis.readObject();
		fis.close();
		
	}
	
	/**
	 * Salva as atuais informções do sistema.
	 * 
	 *@param 
	 *@return IOException
	 * */
	@Override
	public void save() throws IOException {
		File file = new File("files/system_files/usuarios.data");
		ObjectOutputStream fos;
		fos = new ObjectOutputStream(new FileOutputStream(file));
		fos.writeObject(AdminController.usersList);
		fos.close();
	}
	
	/**
	 * Registra nova cidade.
	 * 
	 * @param String name, double area, double populacao, String descricao
	 * @return void, DuplicateKeyException
	 * */
	public void registerCity(String name, double area, double populacao, String descricao) throws DuplicateKeyException {
		AdminController.mapa.addVertice(new Cidade(this.cityId++, name, area, populacao, descricao));
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<Cidade> listCities() {
		return mapa.vertices();
	}

	public void excluirUsuario(String email, String admin) throws IllegalArgumentException {
		User user = this.getUserByEmail(email);
		User adm = this.getUserByEmail(admin);
		if (user.equals(adm)) {
			throw new IllegalArgumentException();
		}
		AdminController.usersList.remove(user);
	}
}
