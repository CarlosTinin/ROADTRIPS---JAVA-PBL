package model;

public class Session {

	private static Session instance = null;
	private User user;
	
	public Session() {
	
	}
	
	/**
	 * Loga um usuario sessão.
	 * 
	 * @param User user
	 * @return void
	 * */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Usuario que está logado na sessão.
	 * 
	 * @param 
	 * @return Usuário logado
	 * */
	public User getUser() {
		return this.user;
	}
	
	/**
	 * Inicia uma sessão, se já não exitir outra.
	 * 
	 * @param User user
	 * @return void
	 * */
	public static Session getInstance() {
		if (Session.instance == null) {
			instance = new Session();
		}
		return Session.instance;
	}
	
	/**
	 * Finaliza a sessão ativa.
	 * 
	 * @param User user
	 * @return void
	 * */
	public void finalize() {
		if (Session.instance != null) {
			this.setUser(null);
			Session.instance = null;
		}
	}
}
