module Checkers {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	
	opens application_Sprint1 to javafx.graphics, javafx.fxml;
}
