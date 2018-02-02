package view;

import exception.DuplicateUserException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;

public class AdminUsrUpdate extends AdminMain{
	
	private User user;
	
	public AdminUsrUpdate(User user) {
		this.user = user;		
	}
	
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);
	}
	
	public void content(BorderPane mainPane, Stage primaryStage) {
		super.content(mainPane, primaryStage);
		GridPane grid = this.updateUser(mainPane, primaryStage, this.user);
		mainPane.setCenter(grid);
	}

	private GridPane updateUser(BorderPane mainPane, Stage primaryStage, User user) {
		final Text actiontarget = new Text();
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25)); // define os pixels nos quatro lados
		
		// header 
		Text sceneTitle = new Text("ATUALIZAR USUÁRIO - " + user.getName());
    	grid.setStyle(".text{-fx-font-family:\"Segoe UI\", Helvetica, Arial, sans-serif;" + 
				"-fx-font-size: 15px; -fx-font-weight: bold;}");
		grid.add(sceneTitle, 0, 0, 2, 1);	
		
		// Inputs with user informations to be updated
		
		// Input name
    	Label name = new Label("Nome:");
    	grid.add(name, 0, 1);
    	TextField userTextField = new TextField();
    	userTextField.setText(user.getName());
    	grid.add(userTextField, 1, 1, 2, 1);
    	
    	// Input nickname
    	Label nick = new Label("Apelido:");
    	grid.add(nick, 0, 2);
    	TextField usernick = new TextField();
    	usernick.setText(user.getNickname());
    	grid.add(usernick, 1, 2, 2, 1);
    	// Tipo de acesso
    	// TODO
    	
    	// button
    	Button update = new Button("ATUALIZAR");
    	grid.add(update, 0, 5);
    	
    	//actiontarget
    	grid.add(actiontarget, 2, 6);
    	
    	// handle dos botões
    	update.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					adminFacade.updateUser(userTextField.getText(), usernick.getText(), user.getEmail());
					actiontarget.setFill(Color.DARKGREEN);
                	actiontarget.setText("Atualizado com sucesso!");
				} catch(DuplicateUserException e) {
					actiontarget.setFill(Color.FIREBRICK);
                	actiontarget.setText("Email duplicado!");
				}
			}
    	});
    	
    	return grid;
	}
}
