package view;

import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Cidade;

public class AdminMainCities extends AdminMain {
	
	public static void main(String args[]) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);
	}
	
	@Override
	public void content(BorderPane mainPane, Stage primaryStage) {
		super.content(mainPane, primaryStage);
		GridPane grid = this.citiesTable(mainPane, primaryStage);
		mainPane.setCenter(grid);
	}
	
	public GridPane citiesTable(BorderPane mainPane, Stage primaryStage) {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25)); // define os pixels nos quatro lados
		
		// header
		Text text = new Text("CIDADES - ");
		text.setFont(Font.font("Tahoma", FontWeight.NORMAL, 17));
    	grid.add(text, 0, 0, 2, 1);
    	grid.setStyle(".text{-fx-font-family:\"Segoe UI\", Helvetica, Arial, sans-serif; -fx-font-size: 15px; -fx-font-weight: bold;}");
    	
    	// Botão cadastrar nova cidade
    	Button newCity = new Button("CADASTRAR NOVA CIDADE");
    	newCity.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
    	grid.add(newCity, 2, 0, 3, 1);
    	
    	// handle do botão
    	newCity.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				AdminNewCity adminNewCity = new AdminNewCity();
				primaryStage.close();
				try {
					adminNewCity.start(primaryStage);
				} catch (Exception e) {
					System.out.println("Exception admin update");
					e.printStackTrace();
				}	
			}
    		
    	});
    	
    	// fill the table with cities informations    	
    	Iterator<Cidade> citIterator = AdminMain.adminFacade.listCities();
    	if (citIterator != null) {
    		grid.add(new Text("Nome"), 0, 1);
        	grid.add(new Text("Area"), 1, 1);
        	grid.add(new Text("População"), 2, 1);
        	grid.add(new Text("Ações"), 3, 1);
        	
        	int i = 2;
        	while (citIterator.hasNext()) {
        		int j = 0;
        		Cidade cit = (Cidade) citIterator.next();
        		Text citName = new Text(cit.getName());
            	Text citArea = new Text(Double.toString(cit.getArea()));
            	Text citPop = new Text(Double.toString(cit.getPopulacao()));
            	
            	grid.add(citName, j++, i);
            	grid.add(citArea, j++, i);
            	grid.add(citPop, j++, i);
            	
            	Button btnDetConfig = new Button("Detalhes/Configs");
            	
            	grid.add(btnDetConfig, j, i);
            	
            	citName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
            	citArea.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
            	citPop.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
            	btnDetConfig.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
            	
            	i += 1;
            	j = 0;
            	
            	btnDetConfig.setOnAction(new EventHandler<ActionEvent>() {

    				@Override
    				public void handle(ActionEvent event) {
    							
    				}
            		
            	});
        	
        	}
    	}
    	return grid;
	}
}
