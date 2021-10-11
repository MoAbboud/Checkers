package application_Sprint1_prod;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Board extends Pane {

	private Rectangle[][] board;
	private String turn = "Black";

	public Rectangle getCell(int row, int column) {
		return board[row][column];
	}

	public String getTurn() {
		return turn;
	}
	
}
