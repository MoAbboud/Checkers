module Checkers {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires junit;
	requires org.junit.jupiter.api;
	
	opens application_Sprint1_prod to javafx.graphics, javafx.fxml;
}
