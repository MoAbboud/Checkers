package application_Sprint1_prod;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Board extends Pane {

	private int[][] board;
	private String turn = "Black";

	public Board() {
		board = new int[8][8];
	}
	
	public int getCell(int row, int column) {		
		if (row >= 0 && row < 8 && column >= 0 && column < 8)
			return board[row][column];
		else 
			return -1;

	}

	public String getTurn() {
		return turn;
	}
	
}
