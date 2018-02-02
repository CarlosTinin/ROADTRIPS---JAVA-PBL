package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Admin;
import model.User;

public class AdminUsr extends AdminMain {

	public AdminUsr() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);
	}
	
	@Override
	public void content(BorderPane mainPane, Stage primaryStage) {
		super.content(mainPane, primaryStage);
		GridPane grid = this.usersTable(mainPane, primaryStage);
		mainPane.setCenter(grid);
	}
	
	public GridPane usersTable(BorderPane mainPane, Stage primaryStage) {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25)); // define os pixels nos quatro lados
		
		// header
		Text text = new Text("USUÁRIOS - ");
		text.setFont(Font.font("Tahoma", FontWeight.NORMAL, 17));
    	grid.add(text, 0, 0, 3, 1);
    	grid.setStyle(".text{-fx-font-family:\"Segoe UI\", Helvetica, Arial, sans-serif; -fx-font-size: 15px; -fx-font-weight: bold;}");
    	
    	// Botão cadastrar novo usuário
    	Button newUser = new Button("CADASTRAR NOVO USUÁRIO");
    	newUser.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
    	grid.add(newUser, 2, 0, 4, 1);
    	
    	// handle do botão
    	newUser.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent arg0) {
    			AdminRegister register = new AdminRegister();
				try {
					register.start(primaryStage);
				} catch (Exception e) {
					System.out.println("Exception no botão!!");
				}
    		}    		
    	});
    	// fill the table with users informations    	
    	Iterator<User> usrIterator = AdminMain.adminFacade.listUsers();
    	
    	grid.add(new Text("Nome"), 0, 1);
    	grid.add(new Text("Apelido"), 1, 1);
    	grid.add(new Text("Email"), 2, 1);
    	grid.add(new Text("Tipo"), 3, 1);
    	grid.add(new Text("Ações"), 4, 1);
    	
    	int i = 2;
    	while (usrIterator.hasNext()) {
    		int j = 0;
    		User user = (User) usrIterator.next();
    		Text usrName = new Text(user.getName());
        	Text usrNick = new Text(user.getNickname());
        	Text usrEmail = new Text(user.getEmail());
        	
        	grid.add(usrName, j++, i);
        	grid.add(usrNick, j++, i);
        	grid.add(usrEmail, j++, i);
        	
        	Text usrType;
        	if (user instanceof Admin) {
        		usrType = new Text("Admin");
        		grid.add(usrType, j++, i);
        	} else {
        		usrType = new Text("User");
        		grid.add(usrType, j++, i);
        	}
        	
        	Button btnUpdate = new Button("Alterar");
        	Button btnDelete = new Button("Excluir");
        	
        	grid.add(btnUpdate, j++, i);
        	grid.add(btnDelete, j, i);
        	
        	usrName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        	usrNick.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        	usrEmail.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        	usrType.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        	btnUpdate.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        	btnDelete.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        	
        	i += 1;
        	j = 0;
        	
        	btnDelete.setOnAction(new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(ActionEvent event) {
        			try {
        				popupExcluir(user, primaryStage);        				
        			} catch(Exception e) {
        				e.printStackTrace();
        			}
        		}
        	});
        	        	
        	btnUpdate.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					AdminUsrUpdate adminusrup = new AdminUsrUpdate(user);
					primaryStage.close();
					try {
						adminusrup.start(primaryStage);
					} catch (Exception e) {
						System.out.println("Exception admin update");
						e.printStackTrace();
					}		
				}
        		
        	});
    	}
    	return grid;
	}
	
	private void popupExcluir(User user, Stage stage) {
		Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
        dialogoAviso.setTitle("Diálogo de Aviso");
        dialogoAviso.setHeaderText("Essa operação não pode ser desfeita!");
        dialogoAviso.setContentText("DESEJA EXCLUIR "+user.getName()+"?");
        dialogoAviso.getButtonTypes().clear();
        
        List<ButtonType> buttons = new ArrayList<>();
        buttons.add(ButtonType.YES);
        buttons.add(ButtonType.NO);
        
        dialogoAviso.getButtonTypes().addAll(buttons);
        
        dialogoAviso.showAndWait().ifPresent(b -> {
            if (b == ButtonType.YES) {
            	try {
                    adminFacade.excluirUsuario(user.getEmail(), getUserFacade().auth().getEmail());
                    AdminUsr adminusr = new AdminUsr();
    				stage.close();
    				try {
    					adminusr.start(stage);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}		
            	} catch(IllegalArgumentException e) {
            		e.printStackTrace();
            	}
            } 
        });
	}
}
