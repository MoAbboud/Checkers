package sprint1_prod;

public class Board {

	private static int[][] board;
	private String turn = "Black";

	public Board() {
		board = new int[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == 0) {
					if (j % 2 == 1) {
						board[i][j] = 1;
					}
				} else if (i == 1) {
					if (j % 2 == 0) {
						board[i][j] = 1;
					}
				} else if (i == 2) {
					if (j % 2 == 1) {
						board[i][j] = 1;
					}
				} else if (i == 5) {
					if (j % 2 == 0) {
						board[i][j] = 1;
					}
				} else if (i == 6) {
					if (j % 2 == 1) {
						board[i][j] = 1;
					}
				} else if (i == 7) {
					if (j % 2 == 0) {
						board[i][j] = 1;
					}
				}
			}
		}
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
