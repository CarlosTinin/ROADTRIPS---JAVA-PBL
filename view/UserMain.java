package view;

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

public class UserMain extends LayoutUser{
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);
	}
	
	@Override
	public void content(BorderPane mainPane, Stage primaryStage) {
		this.left(mainPane);
	}
	
	public void left(BorderPane mainPane) {
		HBox lbox = new HBox();
		lbox.setPadding(new Insets(15, 12, 15, 12));
		lbox.setSpacing(10);
		lbox.setMaxWidth(0.5);
		lbox.setStyle("-fx-background-color: #1A83FF;"); 
		
		FlowPane flow = new FlowPane();
		flow.setVgap(10);
		flow.setAlignment(Pos.TOP_CENTER);     // Right-justify nodes in flow
		
		Text text1 = new Text("DASHBOARD - user");
		text1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		Button newTripBtn = new Button("NOVA VIAGEM");
		Button locaisBtn = new Button("LOCAIS");
		
		flow.getChildren().addAll(text1, newTripBtn, locaisBtn);
		lbox.getChildren().add(flow);
		HBox.setHgrow(flow, Priority.ALWAYS); 
		
		mainPane.setLeft(lbox);
	}
}
