package sprint2_prod;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.plugins.tiff.ExifGPSTagSet;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class CheckersGUI {

	private Board board;

	@FXML
	private GridPane CheckersBoard;

	@FXML
	private Label lblPlayer1;

	@FXML
	private Label lblPlayer1Perc;

	@FXML
	private Label lblPlayer2;

	@FXML
	private Label lblPlayer2Perc;

	@FXML
	private Label lblStart;

	@FXML
	private Button btnStart;
	int row = 0;
	int column = 0;

	@FXML
	private void mousePressedOnNode() {

		btnStart.setDisable(true);
		board = new Board();
		for (Node piece : CheckersBoard.getChildren()) {
			if (piece instanceof Ellipse) {
				piece.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						
						if (piece.getBoundsInParent().contains(event.getSceneX(), event.getSceneY())) {

							if (GridPane.getRowIndex(piece) == null) {
								row = 0;
							} else {
								row = GridPane.getRowIndex(piece);
							}
							if (GridPane.getColumnIndex(piece) == null) {
								column = 0;
							} else {
								column = GridPane.getColumnIndex(piece);
							}
							System.out.println("Piece at " + row + "-" + column);
							System.out.println(board.getCell(row, column));
							
							if(board.getCell(row - 1, column + 1) == 0 && board.getCell(row - 1, column - 1) == 0) {
								System.out.println("You can move left and right");
							}
							
							if(board.getCell(row - 1, column + 1) == 0) {
								System.out.println("You can move right");
							}
							
							if(board.getCell(row - 1, column - 1) == 0) {
								System.out.println("You can move left");
							}
						}
					}
				});
			}
		}
	}
	
	

}
