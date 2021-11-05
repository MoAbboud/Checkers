package sprint1_prod;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

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

	@FXML
	private Ellipse black;

	@FXML
	private Ellipse white;

	int row = 0;
	int column = 0;
	int[] possibleMoves;
	int[] jumpMoves;
	boolean jumpMovesFlag = false;
	boolean overtakeFlag = false;
	boolean kingPieceTrigger = false;

	@FXML
	private void InitializeBoard() {

		btnStart.setDisable(true);
		board = new Board();
		UpdateGame();

	}

	private void UpdateGame() {

		for (Node piece : CheckersBoard.getChildren()) {
			if (piece instanceof Ellipse) {

				if (piece.getId().contains(board.getTurn().toLowerCase())) {

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
								int fromRow = row;
								int fromCol = column;
								System.out.println("Piece at " + row + "-" + column);
								System.out.println(board.getCell(row, column));
								int[] possibleMoves = DisplayPossibleMoves(fromRow, fromCol, board.getTurn(),
										piece.getId());
								int row1, col1, row2, col2, row3, col3, row4, col4;
								if (possibleMoves.length == 4) {
									row1 = possibleMoves[0];
									col1 = possibleMoves[1];

									row2 = possibleMoves[2];
									col2 = possibleMoves[3];

									row3 = -1;
									col3 = -1;
									row4 = -1;
									col4 = -1;

									System.out.println("Possible move: " + row1 + "-" + col1);
									System.out.println("Possible move: " + row2 + "-" + col2);
								} else {
									row1 = possibleMoves[0];
									col1 = possibleMoves[1];
									row2 = possibleMoves[2];
									col2 = possibleMoves[3];
									row3 = possibleMoves[4];
									col3 = possibleMoves[5];
									row4 = possibleMoves[6];
									col4 = possibleMoves[7];

									System.out.println("Possible move: " + row1 + "-" + col1);
									System.out.println("Possible move: " + row2 + "-" + col2);
									System.out.println("Possible move: " + row3 + "-" + col3);
									System.out.println("Possible move: " + row4 + "-" + col4);
								}

								for (Node Tile : CheckersBoard.getChildren()) {
									if (Tile instanceof Rectangle) {

										Tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
											@Override
											public void handle(MouseEvent event) {

												if (Tile.getBoundsInParent().contains(event.getSceneX(),
														event.getSceneY())) {

													if (GridPane.getRowIndex(Tile) == null) {
														row = 0;
													} else {
														row = GridPane.getRowIndex(Tile);
													}
													if (GridPane.getColumnIndex(Tile) == null) {
														column = 0;
													} else {
														column = GridPane.getColumnIndex(Tile);
													}
													int toRow = row;
													int toCol = column;

													if (row1 == toRow && col1 == toCol || row2 == toRow && col2 == toCol
															|| row3 == toRow && col3 == toCol
															|| row4 == toRow && col4 == toCol) {

														System.out.println("Tile at " + row + "-" + column);
														System.out.println(board.getCell(row, column));
														// Make Move
														DoMove(fromRow, fromCol, toRow, toCol, piece);
														if (overtakeFlag) {
															overtakeFlag = false;

															int piecetoRemoverow = 0;
															int piecetoRemoveCol = 0;

															if (board.getTurn().equals("Black")) {
																piecetoRemoverow = fromRow - 1;
															} else {
																piecetoRemoverow = fromRow + 1;
															}
															piecetoRemoveCol = 0;
															if (fromCol < toCol) {
																piecetoRemoveCol = fromCol - 1;
															} else {
																piecetoRemoveCol = fromCol + 1;
															}
															
														
														    

															// REMOVE PIECE
															// CheckersBoard.getChildren().remove;
														}
														CheckJumpMove(row, column, board.getTurn(), piece.getId());
														board.printBoard();
														GridPane.setRowIndex(piece, toRow);
														GridPane.setColumnIndex(piece, toCol);

														if (jumpMovesFlag) {
															RemoveListeners('R');
															System.out.println("You have to keep jumping");
														} else {
															if (kingPieceTrigger) {
																((Ellipse) piece).setStroke(Color.RED);
																((Ellipse) piece).setStrokeType(StrokeType.INSIDE);
																((Ellipse) piece).setStrokeWidth(5);
																piece.setId(board.getTurn().toLowerCase() + "King");
																kingPieceTrigger = false;
															}
															RemoveListeners('B');
															board.ChangeTurn();
															System.out.println(board.getTurn());
															UpdateGame();
														}
													}
												}
											}
										});
									}
								}
							}
						}
					});
				}
			}
		}
	}

	public void DoMove(int fromRow, int fromCol, int toRow, int toCol, Node piece) {

		board.makeMove(fromRow, fromCol, toRow, toCol, board.getTurn());
		int rowDif = fromRow - toRow;
		rowDif = Math.abs(rowDif);
		System.out.println("Row Diff: " + rowDif);
		if (rowDif == 2) {
			jumpMovesFlag = true;
			overtakeFlag = true;
		}

		if (toRow == 0 && board.getTurn().equals("Black") || toRow == 7 && board.getTurn().equals("White")) {
			kingPieceTrigger = true;
		}
	}

	public int[] DisplayPossibleMoves(int fromRow, int fromCol, String turn, String id) {

		int[] possibleMovesMethod = null;

		if (id.contains("King")) {
			int pieceNumber = 0;
			if (turn.equals("Black"))
				pieceNumber = 2;
			else
				pieceNumber = 1;

			possibleMovesMethod = new int[8];

			if (board.getCell(row - 1, column + 1) == 0) { // Check Right
				System.out.println("You can move top right");
				possibleMovesMethod[0] = fromRow - 1;
				possibleMovesMethod[1] = fromCol + 1;

			} else if (board.getCell(row - 1, column + 1) == pieceNumber && board.getCell(row - 2, column + 2) == 0) {
				System.out.println("You can overtake top right");
				possibleMovesMethod[0] = fromRow - 2;
				possibleMovesMethod[1] = fromCol + 2;

			} else {
				possibleMovesMethod[0] = -1;
				possibleMovesMethod[1] = -1;
			}

			if (board.getCell(row - 1, column - 1) == 0) { // Check Left
				System.out.println("You can move top left");
				possibleMovesMethod[2] = fromRow - 1;
				possibleMovesMethod[3] = fromCol - 1;

			} else if (board.getCell(row - 1, column - 1) == pieceNumber && board.getCell(row - 2, column - 2) == 0) {
				System.out.println("You can overtake top left");
				possibleMovesMethod[2] = fromRow - 2;
				possibleMovesMethod[3] = fromCol - 2;

			} else {
				possibleMovesMethod[2] = -1;
				possibleMovesMethod[3] = -1;
			}

			if (board.getCell(row + 1, column + 1) == 0) { // Check Right
				System.out.println("You can move bottom right");
				possibleMovesMethod[4] = fromRow + 1;
				possibleMovesMethod[5] = fromCol + 1;

			} else if (board.getCell(row + 1, column + 1) == pieceNumber && board.getCell(row + 2, column + 2) == 0) {
				System.out.println("You can overtake bottom right");
				possibleMovesMethod[4] = fromRow + 2;
				possibleMovesMethod[5] = fromCol + 2;

			} else {
				possibleMovesMethod[4] = -1;
				possibleMovesMethod[5] = -1;
			}

			if (board.getCell(row + 1, column - 1) == 0) { // Check Left
				System.out.println("You can move bottom left");

				possibleMovesMethod[6] = fromRow + 1;
				possibleMovesMethod[7] = fromCol - 1;

			} else if (board.getCell(row + 1, column - 1) == pieceNumber && board.getCell(row + 2, column - 2) == 0) {
				System.out.println("You can overtake bottom left");
				possibleMovesMethod[6] = fromRow + 2;
				possibleMovesMethod[7] = fromCol - 2;

			} else {
				possibleMovesMethod[6] = -1;
				possibleMovesMethod[7] = -1;
			}

		} else {
			possibleMovesMethod = new int[4];
			if (turn.equals("Black")) {

				if (board.getCell(row - 1, column + 1) == 0) { // Check Right
					System.out.println("You can move right");
					possibleMovesMethod[0] = fromRow - 1;
					possibleMovesMethod[1] = fromCol + 1;

				} else if (board.getCell(row - 1, column + 1) == 2 && board.getCell(row - 2, column + 2) == 0) {
					System.out.println("You can overtake right");
					possibleMovesMethod[0] = fromRow - 2;
					possibleMovesMethod[1] = fromCol + 2;

				} else {
					possibleMovesMethod[0] = -1;
					possibleMovesMethod[1] = -1;
				}

				if (board.getCell(row - 1, column - 1) == 0) { // Check Left
					System.out.println("You can move left");
					possibleMovesMethod[2] = fromRow - 1;
					possibleMovesMethod[3] = fromCol - 1;

				} else if (board.getCell(row - 1, column - 1) == 2 && board.getCell(row - 2, column - 2) == 0) {
					System.out.println("You can overtake left");
					possibleMovesMethod[2] = fromRow - 2;
					possibleMovesMethod[3] = fromCol - 2;

				} else {
					possibleMovesMethod[2] = -1;
					possibleMovesMethod[3] = -1;
				}

			} else if (turn.equals("White")) {

				if (board.getCell(row + 1, column + 1) == 0) { // Check Right
					System.out.println("You can move right");
					possibleMovesMethod[0] = fromRow + 1;
					possibleMovesMethod[1] = fromCol + 1;

				} else if (board.getCell(row + 1, column + 1) == 1 && board.getCell(row + 2, column + 2) == 0) {
					System.out.println("You can overtake right");
					possibleMovesMethod[0] = fromRow + 2;
					possibleMovesMethod[1] = fromCol + 2;

				} else {
					possibleMovesMethod[0] = -1;
					possibleMovesMethod[1] = -1;
				}

				if (board.getCell(row + 1, column - 1) == 0) { // Check Left
					System.out.println("You can move left");

					possibleMovesMethod[2] = fromRow + 1;
					possibleMovesMethod[3] = fromCol - 1;

				} else if (board.getCell(row + 1, column - 1) == 1 && board.getCell(row + 2, column - 2) == 0) {
					System.out.println("You can overtake left");
					possibleMovesMethod[2] = fromRow + 2;
					possibleMovesMethod[3] = fromCol - 2;

				} else {
					possibleMovesMethod[2] = -1;
					possibleMovesMethod[3] = -1;
				}
			}
		}
		return possibleMovesMethod;
	}

	private void CheckJumpMove(int row, int column, String turn, String id) {

		// jumpMoves = DisplayPossibleMoves(row, column, turn, id);
		jumpMoves = new int[8];
		boolean isKing = false;
		boolean blackPiece = false;
		boolean whitePiece = false;

		int pieceNumber = 0;

		if (turn.equals("Black")) {
			blackPiece = true;
			pieceNumber = 2;
		} else {
			whitePiece = true;
			pieceNumber = 1;
		}

		if (id.contains("King")) {
			isKing = true;
		}

		if (board.getCell(row - 1, column + 1) == pieceNumber && board.getCell(row - 2, column + 2) == 0 && blackPiece
				|| board.getCell(row - 1, column + 1) == pieceNumber && board.getCell(row - 2, column + 2) == 0
						&& isKing) {
			jumpMoves[0] = row - 2;
			jumpMoves[1] = column + 2;
		} else {
			jumpMoves[0] = -1;
			jumpMoves[1] = -1;
		}

		if (board.getCell(row - 1, column - 1) == pieceNumber && board.getCell(row - 2, column - 2) == 0 && blackPiece
				|| board.getCell(row - 1, column - 1) == pieceNumber && board.getCell(row - 2, column - 2) == 0
						&& isKing) {
			jumpMoves[2] = row - 2;
			jumpMoves[3] = column - 2;
		} else {
			jumpMoves[2] = -1;
			jumpMoves[3] = -1;
		}

		if (board.getCell(row + 1, column + 1) == pieceNumber && board.getCell(row + 2, column + 2) == 0 && whitePiece
				|| board.getCell(row + 1, column + 1) == pieceNumber && board.getCell(row + 2, column + 2) == 0
						&& whitePiece && isKing) {
			jumpMoves[4] = row + 2;
			jumpMoves[5] = column + 2;
		} else {
			jumpMoves[4] = -1;
			jumpMoves[5] = -1;
		}

		if (board.getCell(row + 1, column - 1) == pieceNumber && board.getCell(row + 2, column - 2) == 0 && whitePiece
				|| board.getCell(row + 1, column - 1) == pieceNumber && board.getCell(row + 2, column - 2) == 0
						&& isKing) {
			jumpMoves[6] = row + 2;
			jumpMoves[7] = column - 2;
		} else {
			jumpMoves[6] = -1;
			jumpMoves[7] = -1;
		}

		if (jumpMoves[0] == -1 && jumpMoves[1] == -1 && jumpMoves[2] == -1 && jumpMoves[3] == -1 && jumpMoves[4] == -1
				&& jumpMoves[5] == -1 && jumpMoves[6] == -1 && jumpMoves[7] == -1 || jumpMoves == null) {
			jumpMovesFlag = false;
		}

		if (jumpMovesFlag) {
			System.out.println("Possible Jump move: " + jumpMoves[0] + "-" + jumpMoves[1]);
			System.out.println("Possible Jump move: " + jumpMoves[2] + "-" + jumpMoves[3]);
			System.out.println("Possible Jump move: " + jumpMoves[4] + "-" + jumpMoves[5]);
			System.out.println("Possible Jump move: " + jumpMoves[6] + "-" + jumpMoves[7]);
		}
		System.out.println(jumpMovesFlag);
	}

	private void RemoveListeners(char choice) {

		if (choice == 'R') {
			for (Node piece : CheckersBoard.getChildren()) {
				if (piece instanceof Rectangle) {
					piece.setOnMouseClicked(null);
				}
			}
		} else {
			for (Node piece : CheckersBoard.getChildren()) {
				if (piece instanceof Ellipse || piece instanceof Rectangle) {
					piece.setOnMouseClicked(null);
				}
			}
		}
	}

}
