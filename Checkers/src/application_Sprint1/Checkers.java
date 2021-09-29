package application_Sprint1;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Checkers extends Application {

	public static final int CELL_SIZE = 75;
	public static final int GRID_WIDTH = 8;
	public static final int GRID_HEIGHT = 8;

	private GridPane Board = new GridPane();

	@Override
	public void start(Stage primaryStage) {
		try {
			
			configureBoardLayout(Board);
			addSquaresToBoard(Board);

			BorderPane root = new BorderPane(Board);
			Scene scene = new Scene(root, 600 + 200, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Checkers");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addSquaresToBoard(GridPane board) {
		Color[] squareColors = new Color[] { Color.WHITE, Color.GRAY };
		for (int row = 0; row < GRID_WIDTH; row++) {
			for (int col = 0; col < GRID_HEIGHT; col++) {
				Board.add(new Rectangle(CELL_SIZE, CELL_SIZE, squareColors[(row + col) % 2]), col, row);
			}
		}
	}

	private void configureBoardLayout(GridPane board) {
		for (int i = 0; i < 8; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setMinHeight(CELL_SIZE);
			rowConstraints.setPrefHeight(CELL_SIZE);
			rowConstraints.setMaxHeight(CELL_SIZE);
			rowConstraints.setValignment(VPos.CENTER);
			board.getRowConstraints().add(rowConstraints);

			ColumnConstraints colConstraints = new ColumnConstraints();
			colConstraints.setMinWidth(CELL_SIZE);
			colConstraints.setMaxWidth(CELL_SIZE);
			colConstraints.setPrefWidth(CELL_SIZE);
			colConstraints.setHalignment(HPos.CENTER);
			board.getColumnConstraints().add(colConstraints);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void createBoard() {

	}
}
