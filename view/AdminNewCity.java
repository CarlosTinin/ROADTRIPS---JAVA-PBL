package view;

import exception.DuplicateKeyException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminNewCity extends AdminMain {
	public static void main(String args[]) {
		launch(args);
	}
	@Override
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
    	primaryStage.setTitle("Cadastro de cidades");
    	
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
    	Label name = new Label("* Nome da cidade: ");
    	grid.add(name, 0, 1);
    	TextField citName = new TextField();
    	grid.add(citName, 1, 1);
    	
    	// Input area
    	Label area = new Label("* Area(m²): ");
    	grid.add(area, 0, 2);
    	TextField citArea = new TextField();
    	// force the field to be numeric only
    	citArea.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\W\\d")) {
    	        	citArea.setText(newValue.replaceAll("[^\\W\\d]", ""));	
    	        }
    	    }
    	});
    	grid.add(citArea, 1, 2);
    	
    	// Input população
    	Label populacao = new Label("* População: ");
    	grid.add(populacao, 0, 3);
    	TextField citPopulacao = new TextField();
    	citPopulacao.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\W\\d")) {
    	        	citPopulacao.setText(newValue.replaceAll("[^\\W\\d]", ""));	
    	        }
    	    }
    	});
    	grid.add(citPopulacao, 1, 3);
    	
    	// Input description
    	Label descricao = new Label("Descrição da cidade: ");
    	grid.add(descricao, 0, 4);
    	TextArea citDescr = new TextArea();
    	grid.add(citDescr, 1, 4);
    	
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
            	if (!citName.getText().isEmpty() && !citArea.getText().isEmpty() && !citPopulacao.getText().isEmpty()) {
        			try {
        				adminFacade.registerNewCity(citName.getText(), Double.parseDouble(citArea.getText()), Double.parseDouble(citPopulacao.getText()), citDescr.getText()); 
        				actiontarget.setFill(Color.DARKGREEN);
                    	actiontarget.setText("Cadastrado com sucesso!");
                    	citName.clear();
                    	citArea.clear();
                    	citPopulacao.clear();
                    	citDescr.clear();
            		} catch (DuplicateKeyException exception) {
                    	actiontarget.setFill(Color.FIREBRICK);
                    	actiontarget.setText("Cidade já existe!!");
            		}            	
	        	} else {
	        		actiontarget.setFill(Color.DARKGOLDENROD);
	            	actiontarget.setText("Preecha os campos obrigatórios! (*)");
	        	}
            }
        });
        return grid;
	}
}
