package sprint2_prod;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/sprint2_prod/CheckersBoard.fxml"));
				
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Team project - Checkers game");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
