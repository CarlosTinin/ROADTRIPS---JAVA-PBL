package view;

import exception.DuplicateUserException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminRegister extends AdminMain {
	
	public static void main(String args[]) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);
	}
	
	public void content(BorderPane mainPane, Stage primaryStage) {
		super.content(mainPane, primaryStage);
		GridPane grid = this.registerAdmin(primaryStage);
		mainPane.setCenter(grid);
	}

	private GridPane registerAdmin(Stage primaryStage) {
		final Text actiontarget = new Text();
    	primaryStage.setTitle("Cadastro de usuário");
    	
    	GridPane grid = new GridPane(); // Inicia uma instacia do gridpane, que gerencia a interface através de linhas e colunas	
    	grid.setAlignment(Pos.CENTER); // Indica que a posição do grid é no centro da janela
    	grid.setHgap(10); // Gap é meio que uma separação, HGap indica o tamanho da separação horizontal
    	grid.setVgap(10); // VGap separação vertical
    	grid.setPadding(new Insets(25, 25, 25, 25)); // define os pixels nos quatro lados
    	
    	// Header
    	Text scenetitle = new Text("ROADTRIPS");
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
    	
    	// Select user type
    	Label access = new Label("Tipo de acesso: ");
    	grid.add(access, 0, 4);
    	ChoiceBox<Object> userType = new ChoiceBox<>();
    	userType.setItems(FXCollections.observableArrayList("Administrador", new Separator(), "Usuário Comum"));
    	userType.setTooltip(new Tooltip("Selecione o tipo de usuário"));
    	grid.add(userType, 1, 4);
    	
    	// Submit button
    	Button btn = new Button("Cadastrar");
    	HBox hbBtn = new HBox(10);
    	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	hbBtn.getChildren().add(btn);
    	grid.add(hbBtn, 1, 6);
    	
    	// Mensagem de situation
        grid.add(actiontarget, 1, 7);
        
        // Handler do botão de submit
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            	if (!usrName.getText().isEmpty() && !usrNickname.getText().isEmpty() && !usrEmail.getText().isEmpty()) {
            			try {
        					if (userType.getValue().equals("Administrador")) {
            					adminFacade.registerAdmin(usrName.getText(), usrNickname.getText(), usrEmail.getText(), "12345");
                				actiontarget.setFill(Color.DARKGREEN);
                            	actiontarget.setText("Cadastrado com sucesso!");
                            	usrName.clear();
                            	usrNickname.clear();
                            	usrEmail.clear();
            				} else if (userType.getValue().equals("Usuário Comum")) {
    							getUserFacade().registerUser(usrName.getText(), usrNickname.getText(), usrEmail.getText(), "12345");    						
                				actiontarget.setFill(Color.DARKGREEN);
                            	actiontarget.setText("Cadastrado com sucesso!");
                            	usrName.clear();
                            	usrNickname.clear();
                            	usrEmail.clear();
            				}          				         			
                		} catch (DuplicateUserException exception) {
                        	actiontarget.setFill(Color.FIREBRICK);
                        	actiontarget.setText("Usuário já existe!!");
                        	usrEmail.clear();
                		} catch (NullPointerException exc) {
                			try {
								getUserFacade().registerUser(usrName.getText(), usrNickname.getText(), usrEmail.getText(), "12345");
							} catch (DuplicateUserException e1) {
								actiontarget.setFill(Color.FIREBRICK);
	                        	actiontarget.setText("Usuário já existe!!");
	                        	usrEmail.clear();
							}
            				actiontarget.setFill(Color.DARKGREEN);
                        	actiontarget.setText("Cadastrado com sucesso!");
                        	usrName.clear();
                        	usrNickname.clear();
                        	usrEmail.clear();
                		}            	
            	} else {
            		actiontarget.setFill(Color.DARKGOLDENROD);
                	actiontarget.setText("Preecha todos os campos!");
            	}
            }
        });
        return grid;
	}
}
