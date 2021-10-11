module Checkers {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	
	opens application_Sprint1_prod to javafx.graphics, javafx.fxml;
}
