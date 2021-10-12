package application_Sprint1_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import application_Sprint1_prod.Board;
import application_Sprint1_prod.CheckersGUI;
import javafx.stage.Stage;

class CheckersBoardTest {

	Stage stage = new Stage();
	Board board;
	
	@Before
	public void setUp() throws Exception {

		board = new Board();
	}
	
	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testEmptyBoardWithPieces() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
