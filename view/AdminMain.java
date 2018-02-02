package view;

import facade.AdminFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminMain extends LayoutUser {

	protected static AdminFacade adminFacade = new AdminFacade();
	
	public static void main(String args[]) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);
	}
	
	@Override
	public void content(BorderPane mainPane, Stage primaryStage) {
		this.left(mainPane, primaryStage);
	}
	
	public void left(BorderPane mainPane, Stage primaryStage) {
		HBox lbox = new HBox();
		lbox.setPadding(new Insets(15, 12, 15, 12));
		lbox.setSpacing(10);
		lbox.setMaxWidth(0.4);
		lbox.setStyle("-fx-background-color: #1A83FF;"); 
		
		FlowPane flow = new FlowPane();
		flow.setHgap(10);
		flow.setVgap(20);
		flow.setAlignment(Pos.TOP_CENTER);     // Right-justify nodes in flow
		
		Text text1 = new Text("DASHBOARD - admin");
		text1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		Button newTripBtn = new Button("NOVA VIAGEM");
		Text text = new Text("GERENCIAR");
		text.setFont(Font.font("Tahoma", FontWeight.NORMAL, 13));
		Button usrBtn = new Button("USUÁRIOS");
		Button locaisBtn = new Button("CIDADES");
		Button intersecoesBtn = new Button("INTERSEÇÕES");
		
		flow.getChildren().addAll(text1, newTripBtn, text, usrBtn, locaisBtn, intersecoesBtn);
		lbox.getChildren().add(flow);
		HBox.setHgrow(flow, Priority.ALWAYS); 
		
		// handles
		locaisBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				AdminMainCities admincit = new AdminMainCities();
				primaryStage.close();
				try {
					admincit.start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
		});
		
		usrBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				AdminUsr adminusr = new AdminUsr();
				primaryStage.close();
				try {
					adminusr.start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
		});
		
		mainPane.setLeft(lbox);
	}
}
