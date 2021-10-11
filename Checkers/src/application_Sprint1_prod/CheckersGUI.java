package application_Sprint1_prod;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class CheckersGUI extends Application{

	public static final int cellSize = 75;
	private static int Board_Size = 8;
	public static int Board_Width = 8;
	public static int Board_Hieght = 8;
	private static int SQUARE_SIZE = 50;
	private static int NumberOfPieces = 12;
	
	private static Rectangle[][] Recboard;

	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			GridPane CheckersBoard = (GridPane)FXMLLoader.load(getClass().getResource("/application_Sprint1_prod/CheckersBoard.fxml"));
	       
			CreateBoard(CheckersBoard);

			Circle[] playerOnePieces = new Circle[NumberOfPieces];
			Circle[] playerTwoPieces = new Circle[NumberOfPieces];

			addPiecesToBoard(CheckersBoard, playerOnePieces, playerTwoPieces);

			BorderPane root = new BorderPane(CheckersBoard);
			Scene scene = new Scene(root, 600, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Checkers");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void CreateBoard(GridPane board) {
		
		Recboard = new Rectangle[Board_Width][Board_Hieght];
		Color[] squareColors = new Color[] { Color.WHITE, Color.GRAY };
		for(int x=0; x < Board_Width; x++){
	        for(int y=0; y < Board_Hieght; y++){
	        	Recboard[x][y] = new Rectangle();
	        	Recboard[x][y].setWidth(cellSize);
	        	Recboard[x][y].setHeight(cellSize);
	        	Recboard[x][y].setX(x * 50);
	        	Recboard[x][y].setY(y * 50);
	        	Recboard[x][y].setStroke(Color.TRANSPARENT);
	        	Recboard[x][y].setStrokeType(StrokeType.INSIDE);
	        	Recboard[x][y].setStrokeWidth(1);
	        	Recboard[x][y].setFill(squareColors[(x + y) % 2]);
	        	board.add(Recboard[x][y], x, y);
	        }
	    }
		
	}

	private void addPiecesToBoard(GridPane checkerBoard, Circle[] blackPieces, Circle[] whitePieces) {
		for (int i = 0; i < NumberOfPieces; i++) {
			whitePieces[i] = new Circle(cellSize / 2 - 6, Color.WHITE);
			whitePieces[i].setStroke(Color.BLACK);
			checkerBoard.add(whitePieces[i],
					i % (Board_Size / 2) * 2 + (2 * i / Board_Size) % 2,
					Board_Size - 1 - (i * 2) / Board_Size);

			blackPieces[i] = new Circle(cellSize / 2 - 6, Color.BLACK);
			blackPieces[i].setStroke(Color.WHITE);
			checkerBoard.add(blackPieces[i],
					i % (Board_Size / 2) * 2 + (1 + 2 * i / Board_Size) % 2,
					(i * 2) / Board_Size);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
