package application_Sprint1;

public class Board {

	private int[][] grid;
	private String turn = "Red";

	public Board() {
		grid = new int[8][8];
	}

	public int getCell(int row, int column) {
		return grid[row][column];
	}

	public String getTurn() {
		return turn;
	}
}
