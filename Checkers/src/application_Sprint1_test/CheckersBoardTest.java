package sprint1_test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import application_Sprint1_prod.Board;

class CheckersBoardTest {

	private Board board = new Board();
	int count = 0;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	// acceptance criterion 3.1
	@Test
	public void testBoardContainsPieces() {
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				if (row == 0 || row == 2) {
					if (column % 2 == 0) {
						assertEquals("", board.getCell(row, column), 0);
					} else {
						assertEquals("", board.getCell(row, column), 1);
					}
				} else if (row == 1) {
					if (column % 2 == 0) {
						assertEquals("", board.getCell(row, column), 1);
					} else {
						assertEquals("", board.getCell(row, column), 0);
					}
				} else if (row == 5 || row == 7) {
					if (column % 2 == 0) {
						assertEquals("", board.getCell(row, column), 1);
					} else {
						assertEquals("", board.getCell(row, column), 0);
					}
				} else if (row == 6) {
					if (column % 2 == 0) {
						assertEquals("", board.getCell(row, column), 0);
					} else {
						assertEquals("", board.getCell(row, column), 1);
					}
				}
			}
		}
		assertEquals("", board.getTurn(), "Black");
	}

	// acceptance criterion 3.2
	@Test
	public void testValidRow() {
		assertEquals("", board.getCell(7, 0), 1);
		assertEquals("", board.getCell(7, 1), 0);
	}

	// acceptance criterion 3.3
	@Test
	public void testValidColumn() {
		assertEquals("", board.getCell(0, 7), 1);
		assertEquals("", board.getCell(1, 7), 0);
	}

	// acceptance criterion 3.4
	@Test
	public void testInvalidRow() {
		assertEquals("", board.getCell(8, 0), -1);
	}

	// acceptance criterion 3.5
	@Test
	public void testInvalidColumn() {
		assertEquals("", board.getCell(0, 8), -1);
	}

}
