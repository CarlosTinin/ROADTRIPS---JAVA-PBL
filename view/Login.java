package view;

import exception.UserNotFoundException;
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
 
public class Login extends Application {
	
	private UserFacade userFacade = new UserFacade();
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	final Text actiontarget = new Text();
    	primaryStage.setTitle("Login de Usuário");
    	
    	GridPane grid = new GridPane(); // Inicia uma instacia do gridpane, que gerencia a interface através de linhas e colunas	
    	grid.setAlignment(Pos.CENTER); // Indica que a posição do grid é no centro da janela
    	grid.setHgap(10); // Gap é meio que uma separação, HGap indica o tamanho da separação horizontal
    	grid.setVgap(10); // VGap separação vertical
    	grid.setPadding(new Insets(25, 25, 25, 25)); // define os pixels nos quatro lados
    	
    	Scene scene = new Scene(grid, 300, 275); // O JFx Aplicattion sepera a aplicação entre Stage e Scene, aqui inicia uma nova scene
    	primaryStage.setScene(scene); 
    	
    	// Header
    	Text scenetitle = new Text("ROADTRIPS");
    	scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    	grid.add(scenetitle, 0, 0, 2, 1);
    	
    	// Input name
    	Label email = new Label("Email:");
    	grid.add(email, 0, 1);
    	TextField userTextField = new TextField();
    	grid.add(userTextField, 1, 1);
    	
    	// Input Password
    	Label pw = new Label("Senha:");
    	grid.add(pw, 0, 2);
    	PasswordField pwBox = new PasswordField();
    	grid.add(pwBox, 1, 2);
    	
    	// Submit button
    	Button btn = new Button("Entrar");
    	HBox hbBtn = new HBox(10);
    	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	hbBtn.getChildren().add(btn);
    	grid.add(hbBtn, 1, 4);
    	
    	// link de cadastre-se
    	Label register = new Label("ainda não tenho conta!");
    	register.setTextFill(Color.DARKBLUE);
    	grid.add(register, 0, 3, 2, 1);
    	
    	// Mensagem de situation
        grid.add(actiontarget, 1, 6);
        
        // Handler do botão de submit
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            	if (!userTextField.getText().isEmpty() && !pwBox.getText().isEmpty()) {
            		try {
            			if (userFacade.validateCredentials(userTextField.getText(), pwBox.getText())) {               				
            				actiontarget.setFill(Color.DARKGREEN);
                        	actiontarget.setText("Logou");
            			}
            		} catch(UserNotFoundException exception) {
                    	actiontarget.setFill(Color.FIREBRICK);
                    	actiontarget.setText("Credencias inválidas!");
            		}
            	} else {
            		actiontarget.setFill(Color.DARKGOLDENROD);
                	actiontarget.setText("Preecha todos os campos!");
            	}     
            }
        });
        
        register.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Register register = new Register();
				primaryStage.close();
				register.start(primaryStage);				
			}
        	
        });
        
        
    	primaryStage.show();
    }
}