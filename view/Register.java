package view;

import exception.DuplicateUserException;
import facade.UserFacade;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 

public class Register extends Application {

	private UserFacade userFacade = new UserFacade();
	
	public static void main(String[] args) {
        launch(args);
    }
	
	 @Override
    public void start(Stage primaryStage) {
    	final Text actiontarget = new Text();
    	primaryStage.setTitle("Cadastro de usuário");
    	
    	GridPane grid = new GridPane(); // Inicia uma instacia do gridpane, que gerencia a interface através de linhas e colunas	
    	grid.setAlignment(Pos.CENTER); // Indica que a posição do grid é no centro da janela
    	grid.setHgap(10); // Gap é meio que uma separação, HGap indica o tamanho da separação horizontal
    	grid.setVgap(10); // VGap separação vertical
    	grid.setPadding(new Insets(25, 25, 25, 25)); // define os pixels nos quatro lados
    	
    	Scene scene = new Scene(grid, 350, 325); // O JFx Aplicattion sepera a aplicação entre Stage e Scene, aqui inicia uma nova scene
    	primaryStage.setScene(scene); 
    	
    	// Header
    	Text scenetitle = new Text("BEM-VINDO AO ROADTRIPS");
    	scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    	grid.add(scenetitle, 0, 0, 2, 1);
    	
    	// Input name
    	Label name = new Label("Nome Completo: ");
    	grid.add(name, 0, 1);
    	TextField usrName = new TextField();
    	grid.add(usrName, 1, 1);
    	
    	// Input nickname
    	Label nickname = new Label("Apelido: ");
    	grid.add(nickname, 0, 2);
    	TextField usrNickname = new TextField();
    	grid.add(usrNickname, 1, 2);
    	
    	// Input email
    	Label email = new Label("Email: ");
    	grid.add(email, 0, 3);
    	TextField usrEmail = new TextField();
    	grid.add(usrEmail, 1, 3);
    	
    	// Input Password
    	Label senha = new Label("Senha: ");
    	grid.add(senha, 0, 4);
    	PasswordField usrSenha = new PasswordField();
    	grid.add(usrSenha, 1, 4);
    	
    	// Input Confirm Password
    	Label confSenha = new Label("Confirmar Senha: ");
    	grid.add(confSenha, 0, 5);
    	PasswordField usrConfSenha = new PasswordField();
    	grid.add(usrConfSenha, 1, 5);
    	
    	// Submit button
    	Button btn = new Button("Cadastrar");
    	HBox hbBtn = new HBox(10);
    	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	hbBtn.getChildren().add(btn);
    	grid.add(hbBtn, 1, 7);
    	
    	// link de login
    	Label login = new Label("já tenho uma conta!");
    	login.setTextFill(Color.DARKBLUE);
    	grid.add(login, 0, 6, 2, 1);
    	
    	// Mensagem de situation
        grid.add(actiontarget, 1, 8);
        
        // Handler do botão de submit
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            	if (!usrName.getText().isEmpty() && !usrNickname.getText().isEmpty() && !usrEmail.getText().isEmpty() && !usrSenha.getText().isEmpty() && !usrConfSenha.getText().isEmpty()) {
            		if (usrSenha.getText().equals(usrConfSenha.getText())) {
            			try {
                			if (userFacade.registerUser(usrName.getText(), usrNickname.getText(), usrEmail.getText(), usrSenha.getText())) {
                				actiontarget.setFill(Color.DARKGREEN);
                            	actiontarget.setText("Cadastrado com sucesso!");
                            	usrName.clear();
                            	usrNickname.clear();
                            	usrEmail.clear();
                            	usrSenha.clear();
                            	usrConfSenha.clear();
                    		}
                		} catch (DuplicateUserException exception) {
                        	actiontarget.setFill(Color.FIREBRICK);
                        	actiontarget.setText("Usuário já existe!!");
                        	usrEmail.clear();
                		}
            		} else {
            			actiontarget.setFill(Color.DARKGOLDENROD);
                    	actiontarget.setText("Senhas divergem!");
                    	usrSenha.clear();
                    	usrConfSenha.clear();
            		}
            	} else {
            		actiontarget.setFill(Color.DARKGOLDENROD);
                	actiontarget.setText("Preecha todos os campos!");
            	}
            }
        });
        
        //redirect to login page
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Login login = new Login();
				primaryStage.close();
				login.start(primaryStage);				
			}
        	
        });
    	
    	primaryStage.show();
    }

}
