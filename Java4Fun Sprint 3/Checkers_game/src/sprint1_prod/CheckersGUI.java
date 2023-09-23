package sprint1_prod;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class CheckersGUI extends Application {

	private Board board; 
	
	@FXML
	private GridPane CheckersBoard;
	
	@Override
	public void start(Stage primaryStage) {
		try {

			CheckersBoard = (GridPane) FXMLLoader
					.load(getClass().getResource("/Sprint_prod/CheckersBoard.fxml"));

			Scene scene = new Scene(CheckersBoard, 600, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Checkers");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}


}
