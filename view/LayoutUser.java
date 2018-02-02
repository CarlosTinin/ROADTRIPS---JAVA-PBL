
package view;

import java.io.IOException;

import facade.UserFacade;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LayoutUser extends Application {

	private static UserFacade userFacade = new UserFacade();
	
	public static void main(String args[]) {
		try {
			userFacade.initComponents();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("failed components initialization");
		}
		System.out.println("success components initialization");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// begin close configs
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                t.consume();

                System.out.println("Closing...");
                try {
					userFacade.save();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                primaryStage.close();
                Platform.exit();
                System.exit(0);
            }
        });
		
		// end close configs
		primaryStage.setResizable(false); // Define que a tela não pode ser redimensionada pelo usuário
		primaryStage.setTitle("RoadTrips"); // Título da janela
		
		BorderPane mainPane = new BorderPane(); // inicia o gerenciador de layout BorderPane
		
		Scene scene = new Scene(mainPane, 900, 600); // O JFx Aplicattion sepera a aplicação entre Stage e Scene, aqui inicia uma nova scene
    	primaryStage.setScene(scene);
    	
    	header(mainPane, primaryStage);
    	content(mainPane, primaryStage);
    	footer(mainPane);
    	
    	primaryStage.show();
	}
	
	public UserFacade getUserFacade() {
		return LayoutUser.userFacade;
	}
	
	private void header(BorderPane mainPane, Stage primaryStage) {
		/*------   Begin header  ------*/
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;"); 
	    
	    Text scenetitle = new Text("RoadTRIPS");
    	scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

    	FlowPane flow = new FlowPane(); // New Flow pane
    	flow.setHgap(10);
    	flow.setAlignment(Pos.CENTER_RIGHT);     // Right-justify nodes in flow
    	
    	if (LayoutUser.userFacade.auth() == null) {
    		Button loginBtn = new Button("LOGIN"); // login button
        	Button registerBtn = new Button("REGISTRE-SE"); // register button
        	
	    	flow.getChildren().addAll(loginBtn, registerBtn); // add the login and register buttons to the hbox
	    	
	    	// Handle dos botões
		    registerBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					Register register = new Register();
					try {
						register.start(primaryStage);
					} catch (Exception e) {
						System.out.println("Exception no botão!!");
					}
				}
	        	
	        });
		    
		    loginBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Login login = new Login();
					try {
						login.start(primaryStage);
					} catch (Exception e) {
						System.out.println("Exception no botão!!");
					}
				}
	        	
	        });
	    	
    	} else {
    		Button profileBtn = new Button(LayoutUser.userFacade.auth().getNickname() + " - PERFIL"); // profile button
        	Button tripsBtn = new Button("VIAGENS"); // trips button
        	Button logoutBtn = new Button("SAIR"); // logout button
        	
        	flow.getChildren().addAll(profileBtn, tripsBtn, logoutBtn); // add the login and register buttons to the hbox
	    	logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					LayoutUser.userFacade.logout();
					Login login = new Login();
					try {
						login.start(primaryStage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
    	}
    	
    	hbox.getChildren().addAll(scenetitle, flow);
    	HBox.setHgrow(flow, Priority.ALWAYS);    // Give flow any extra space
        
	    mainPane.setTop(hbox);
	    /*------   End header  ------*/
	}
	
	private void footer(BorderPane mainPane) {
		/*------   Begin footer  ------*/
	    HBox fbox = new HBox();
	    fbox.setPadding(new Insets(15, 12, 15, 12));
	    fbox.setSpacing(10);
	    fbox.setStyle("-fx-background-color: #336699;");    
	    mainPane.setBottom(fbox);
	    
	    Text pwBy = new Text("Powered by ACKTININ");
	    pwBy.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
	    //pwBy.setTextAlignment(TextAlignment.CENTER);
	    
	    fbox.getChildren().add(pwBy);
	    /*------   End footer  ------*/
	}
	
	protected void content(BorderPane mainPane, Stage primaryStage) {}
}
