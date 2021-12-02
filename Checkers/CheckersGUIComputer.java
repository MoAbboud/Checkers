package sprint1_prod;

import java.util.ArrayList;
import java.util.Random;

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
import sprint1_prod.Board.GameState;

public class CheckersGUIComputer {

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
	private Label lblState;

	@FXML
	private Button btnStart;

	@FXML
	private Ellipse black;

	@FXML
	private Ellipse white;

	int[] possibleMoves;
	int[] jumpMoves;
	ArrayList<Piece> computerMoves;
	boolean jumpMovesFlag = false;
	boolean overtakeFlag = false;
	boolean kingPieceTrigger = false;
	Boolean changedTurns = false;

	@FXML
	private void InitializeBoard() {

		btnStart.setDisable(true);
		board = new Board();
		UpdateGamePlayer();

	}

	private void GameOver() {
		btnStart.setDisable(false);
	}

	private void UpdateGamePlayer() {

		CheckGameOver(false);
		for (Node piece : CheckersBoard.getChildren()) {
			if (piece instanceof Ellipse) {

				if (piece.getId().contains("black")) {

					piece.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {

							if (piece.getBoundsInParent().contains(event.getSceneX(), event.getSceneY())) {

								int fromRow = CheckRowIndex(piece);
								int fromCol = CheckColIndex(piece);

								System.out.println("Piece at " + fromRow + "-" + fromCol);
								System.out.println(board.getCell(fromRow, fromCol));
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
								// GameState();

								for (Node Tile : CheckersBoard.getChildren()) {
									if (Tile instanceof Rectangle) {

										Tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
											@Override
											public void handle(MouseEvent event) {

												if (Tile.getBoundsInParent().contains(event.getSceneX(),
														event.getSceneY())) {

													int toRow = CheckRowIndex(Tile);
													int toCol = CheckColIndex(Tile);

													if (row1 == toRow && col1 == toCol || row2 == toRow && col2 == toCol
															|| row3 == toRow && col3 == toCol
															|| row4 == toRow && col4 == toCol) {

														System.out.println("Tile at " + toRow + "-" + toCol);
														System.out.println(board.getCell(toRow, toCol));
														// Make Move
														DoMove(fromRow, fromCol, toRow, toCol, piece);
														if (overtakeFlag) {
															overtakeFlag = false;
															PerfomOvertake(fromRow, fromCol, toRow, toCol, piece);

														}
														CheckJumpMove(toRow, toCol, board.getTurn(), piece.getId());
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
															System.out.println(board.getTurn());
															board.CheckGameState();
															board.ChangeTurn();
															System.out.println(board.getGameState().toString());
															if (board.getGameState() == GameState.PLAYING) {
																lblState.setText("Game is underway!");
																changedTurns = false;
																UpdateGameComputer();
															} else {
																if (board.getGameState() == GameState.BLACK_WON) {
																	lblState.setText("Black Wins!");
																} else if (board
																		.getGameState() == GameState.WHITE_WON) {
																	lblState.setText("White Wins!");
																}
																RemoveListeners('B');
																GameOver();
															}
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

	private void UpdateGameComputer() {

		CheckGameOver(true);
		ArrayList<Piece> overtakeMoves = new ArrayList<Piece>();
		for (int i = 0; i < computerMoves.size(); i++) {
			// Checking if there is a possible capture move, if not move any piece.
			int[] computerPossibleMoves = computerMoves.get(i).getPossibleMoves();
			Node pieceNode = computerMoves.get(i).getNode();

			int firstMoveCheck = 0;
			int secondMoveCheck = 0;
			int thirdMoveCheck = 0;
			int fourthMoveCheck = 0;
			if (computerPossibleMoves[0] != -1) {
				firstMoveCheck = Math.abs(computerPossibleMoves[0] - computerMoves.get(i).getRow());
			}
			if (computerPossibleMoves[2] != -1) {
				secondMoveCheck = Math.abs(computerPossibleMoves[2] - computerMoves.get(i).getRow());
			}
			if (pieceNode.getId().contains("King")) {
				if (computerPossibleMoves[4] != -1) {
					thirdMoveCheck = Math.abs(computerPossibleMoves[4] - computerMoves.get(i).getRow());
				}
				if (computerPossibleMoves[6] != -1) {
					fourthMoveCheck = Math.abs(computerPossibleMoves[6] - computerMoves.get(i).getRow());
				}
			}
			if (firstMoveCheck == 2 || secondMoveCheck == 2 || thirdMoveCheck == 2 || fourthMoveCheck == 2) {
				overtakeMoves.add(computerMoves.get(i));
			}
		}

		if (overtakeMoves.size() > 0) {
			int toRow = 0;
			int toCol = 0;
			Node piece = overtakeMoves.get(0).getNode();
			int fromRow = overtakeMoves.get(0).getRow();
			int fromCol = overtakeMoves.get(0).getCol();
			int[] computerPossibleMoves = overtakeMoves.get(0).getPossibleMoves();

			for (int i = 0; i < computerPossibleMoves.length; i += 2) {
				if (computerPossibleMoves[i] != -1 && computerPossibleMoves[i + 1] != -1) {
					if (Math.abs(fromRow - computerPossibleMoves[i]) == 2) {
						toRow = computerPossibleMoves[i];
						toCol = computerPossibleMoves[i + 1];
					}
				}
			}

			GridPane.setRowIndex(piece, toRow);
			GridPane.setColumnIndex(piece, toCol);
			DoMove(fromRow, fromCol, toRow, toCol, piece);
			if (overtakeFlag) {
				overtakeFlag = false;
				PerfomOvertake(fromRow, fromCol, toRow, toCol, piece);
			}
			CheckJumpMove(toRow, toCol, board.getTurn(), piece.getId());
			if (jumpMovesFlag) {
				System.out.println("You have to keep jumping");
				board.printBoard();
				UpdateGameComputer();
			}
			CheckComputerPieceOutcome(piece);

		} else if (computerMoves.size() > 0) {
			Random r = new Random();
			int randomInt = r.nextInt(computerMoves.size() - 0) + 0;

			Node piece = computerMoves.get(randomInt).getNode();
			int fromRow = computerMoves.get(randomInt).getRow();
			int fromCol = computerMoves.get(randomInt).getCol();
			int[] computerPossibleMoves = computerMoves.get(randomInt).getPossibleMoves();

			if (computerPossibleMoves[0] == -1 && computerPossibleMoves[1] == -1) {
				GridPane.setRowIndex(piece, computerPossibleMoves[2]);
				GridPane.setColumnIndex(piece, computerPossibleMoves[3]);
				DoMove(fromRow, fromCol, computerPossibleMoves[2], computerPossibleMoves[3], piece);
			} else {
				GridPane.setRowIndex(piece, computerPossibleMoves[0]);
				GridPane.setColumnIndex(piece, computerPossibleMoves[1]);
				DoMove(fromRow, fromCol, computerPossibleMoves[0], computerPossibleMoves[1], piece);
			}
			CheckComputerPieceOutcome(piece);
		}
	}

	public void CheckComputerPieceOutcome(Node piece) {
		if (kingPieceTrigger) {
			((Ellipse) piece).setStroke(Color.RED);
			((Ellipse) piece).setStrokeType(StrokeType.INSIDE);
			((Ellipse) piece).setStrokeWidth(5);
			piece.setId(board.getTurn().toLowerCase() + "King");
			kingPieceTrigger = false;
		}
		board.CheckGameState();
		if (!changedTurns) {
			board.ChangeTurn();
			changedTurns = true;
		}
		board.printBoard();

		if (board.getGameState() == GameState.PLAYING) {
			lblState.setText("Game is underway!");
			UpdateGamePlayer();
		} else {
			if (board.getGameState() == GameState.BLACK_WON) {
				lblState.setText("Black Wins!");
			} else if (board.getGameState() == GameState.WHITE_WON) {
				lblState.setText("White Wins!");
			}
			GameOver();
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

	public void PerfomOvertake(int fromRow, int fromCol, int toRow, int toCol, Node piece) {

		System.out.println("from Row: " + fromRow + " From col: " + fromCol);
		System.out.println("to Row: " + toRow + " to col: " + toCol);
		int piecetoRemoverow = 0;
		int piecetoRemoveCol = 0;

		if (!piece.getId().contains("King")) {
			if (board.getTurn().equals("Black")) {
				piecetoRemoverow = fromRow - 1;
			} else if (board.getTurn().equals("White")) {
				piecetoRemoverow = fromRow + 1;
			}
		} else {
			if (fromRow < toRow) {
				piecetoRemoverow = fromRow + 1;
			} else {
				piecetoRemoverow = fromRow - 1;
			}
		}
		piecetoRemoveCol = 0;
		if (fromCol > toCol) {
			piecetoRemoveCol = fromCol - 1;
		} else {
			piecetoRemoveCol = fromCol + 1;
		}
		System.out.println("piecetoRemoverow: " + piecetoRemoverow + " piecetoRemoveCol: " + piecetoRemoveCol);

		for (Node piecetoRemove : CheckersBoard.getChildren()) {
			if (piecetoRemove instanceof Ellipse) {
				int rowForRemoval = 0;
				int colForRemoval = 0;
				if (GridPane.getRowIndex(piecetoRemove) == null) {
					rowForRemoval = 0;
				} else {
					rowForRemoval = GridPane.getRowIndex(piecetoRemove);
				}
				if (GridPane.getColumnIndex(piecetoRemove) == null) {
					colForRemoval = 0;
				} else {
					colForRemoval = GridPane.getColumnIndex(piecetoRemove);
				}

				if (rowForRemoval == piecetoRemoverow && colForRemoval == piecetoRemoveCol) {

					System.out.println("rowForRemoval: " + rowForRemoval + " colForRemoval: " + colForRemoval);
					CheckersBoard.getChildren().remove(piecetoRemove);
					if (board.getTurn().equals("Black")) {
						board.whitePieces--;
						System.out.println("Black pieces left: " + board.blackPieces);
						System.out.println("White pieces left: " + board.whitePieces);

					} else {
						board.blackPieces--;
						System.out.println("Black pieces left: " + board.blackPieces);
						System.out.println("White pieces left: " + board.whitePieces);
					}
					board.setCellAfterOvertake(rowForRemoval, colForRemoval);
					break;
				}
			}
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

			if (board.getCell(fromRow - 1, fromCol + 1) == 0) { // Check Right
				System.out.println("You can move top right");
				possibleMovesMethod[0] = fromRow - 1;
				possibleMovesMethod[1] = fromCol + 1;

			} else if (board.getCell(fromRow - 1, fromCol + 1) == pieceNumber
					&& board.getCell(fromRow - 2, fromCol + 2) == 0) {
				System.out.println("You can overtake top right");
				possibleMovesMethod[0] = fromRow - 2;
				possibleMovesMethod[1] = fromCol + 2;

			} else {
				possibleMovesMethod[0] = -1;
				possibleMovesMethod[1] = -1;
			}

			if (board.getCell(fromRow - 1, fromCol - 1) == 0) { // Check Left
				System.out.println("You can move top left");
				possibleMovesMethod[2] = fromRow - 1;
				possibleMovesMethod[3] = fromCol - 1;

			} else if (board.getCell(fromRow - 1, fromCol - 1) == pieceNumber
					&& board.getCell(fromRow - 2, fromCol - 2) == 0) {
				System.out.println("You can overtake top left");
				possibleMovesMethod[2] = fromRow - 2;
				possibleMovesMethod[3] = fromCol - 2;

			} else {
				possibleMovesMethod[2] = -1;
				possibleMovesMethod[3] = -1;
			}

			if (board.getCell(fromRow + 1, fromCol + 1) == 0) { // Check Right
				System.out.println("You can move bottom right");
				possibleMovesMethod[4] = fromRow + 1;
				possibleMovesMethod[5] = fromCol + 1;

			} else if (board.getCell(fromRow + 1, fromCol + 1) == pieceNumber
					&& board.getCell(fromRow + 2, fromCol + 2) == 0) {
				System.out.println("You can overtake bottom right");
				possibleMovesMethod[4] = fromRow + 2;
				possibleMovesMethod[5] = fromCol + 2;

			} else {
				possibleMovesMethod[4] = -1;
				possibleMovesMethod[5] = -1;
			}

			if (board.getCell(fromRow + 1, fromCol - 1) == 0) { // Check Left
				System.out.println("You can move bottom left");

				possibleMovesMethod[6] = fromRow + 1;
				possibleMovesMethod[7] = fromCol - 1;

			} else if (board.getCell(fromRow + 1, fromCol - 1) == pieceNumber
					&& board.getCell(fromRow + 2, fromCol - 2) == 0) {
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

				if (board.getCell(fromRow - 1, fromCol + 1) == 0) { // Check Right
					System.out.println("You can move right");
					possibleMovesMethod[0] = fromRow - 1;
					possibleMovesMethod[1] = fromCol + 1;

				} else if (board.getCell(fromRow - 1, fromCol + 1) == 2
						&& board.getCell(fromRow - 2, fromCol + 2) == 0) {
					System.out.println("You can overtake right");
					possibleMovesMethod[0] = fromRow - 2;
					possibleMovesMethod[1] = fromCol + 2;

				} else {
					possibleMovesMethod[0] = -1;
					possibleMovesMethod[1] = -1;
				}

				if (board.getCell(fromRow - 1, fromCol - 1) == 0) { // Check Left
					System.out.println("You can move left");
					possibleMovesMethod[2] = fromRow - 1;
					possibleMovesMethod[3] = fromCol - 1;

				} else if (board.getCell(fromRow - 1, fromCol - 1) == 2
						&& board.getCell(fromRow - 2, fromCol - 2) == 0) {
					System.out.println("You can overtake left");
					possibleMovesMethod[2] = fromRow - 2;
					possibleMovesMethod[3] = fromCol - 2;

				} else {
					possibleMovesMethod[2] = -1;
					possibleMovesMethod[3] = -1;
				}

			} else if (turn.equals("White")) {

				if (board.getCell(fromRow + 1, fromCol + 1) == 0) { // Check Right
					System.out.println("You can move right");
					possibleMovesMethod[0] = fromRow + 1;
					possibleMovesMethod[1] = fromCol + 1;

				} else if (board.getCell(fromRow + 1, fromCol + 1) == 1
						&& board.getCell(fromRow + 2, fromCol + 2) == 0) {
					System.out.println("You can overtake right");
					possibleMovesMethod[0] = fromRow + 2;
					possibleMovesMethod[1] = fromCol + 2;

				} else {
					possibleMovesMethod[0] = -1;
					possibleMovesMethod[1] = -1;
				}

				if (board.getCell(fromRow + 1, fromCol - 1) == 0) { // Check Left
					System.out.println("You can move left");

					possibleMovesMethod[2] = fromRow + 1;
					possibleMovesMethod[3] = fromCol - 1;

				} else if (board.getCell(fromRow + 1, fromCol - 1) == 1
						&& board.getCell(fromRow + 2, fromCol - 2) == 0) {
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
						&& isKing) {
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

	private void GameState(boolean isComputer) {
		board.CheckGameState();
		boolean isDraw = true;
		computerMoves = new ArrayList<Piece>();
		if (board.getGameState() == GameState.PLAYING) {
			for (Node piece : CheckersBoard.getChildren()) {
				if (piece instanceof Ellipse) {
					if (piece.getId().contains(board.getTurn().toLowerCase())) {

						int fromRow = CheckRowIndex(piece);
						int fromCol = CheckColIndex(piece);

						int[] possibleMoves = DisplayPossibleMoves(fromRow, fromCol, board.getTurn(), piece.getId());
						boolean addtoList = true;
						for (int i = 0; i < possibleMoves.length; i++) {
							if (possibleMoves[i] != -1) {
								isDraw = false;
								if (isComputer && addtoList) {
									addtoList = false;
									computerMoves.add(new Piece(piece, fromRow, fromCol, possibleMoves));
								}
							}
						}
					}
				}
			}

			if (isDraw) {
				if (board.getTurn().equals("Black")) {
					board.setCurrentGameState(GameState.WHITE_WON);
				} else {
					board.setCurrentGameState(GameState.BLACK_WON);
				}
			}
		}
	}

	public void CheckGameOver(boolean isComputer) {
		GameState(isComputer);
		if (board.getGameState() == GameState.BLACK_WON) {
			lblState.setText("Black Wins!");
			RemoveListeners('B');
			GameOver();
		} else if (board.getGameState() == GameState.WHITE_WON) {
			lblState.setText("White Wins!");
			RemoveListeners('B');
			GameOver();
		}

	}

	public int CheckRowIndex(Node piece) {
		if (GridPane.getRowIndex(piece) == null) {
			return 0;
		} else {
			return GridPane.getRowIndex(piece);
		}
	}

	public int CheckColIndex(Node piece) {
		if (GridPane.getColumnIndex(piece) == null) {
			return 0;
		} else {
			return GridPane.getColumnIndex(piece);
		}
	}

}
