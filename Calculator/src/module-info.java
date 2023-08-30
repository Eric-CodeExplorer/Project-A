module Calculator {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires ch.obermuhlner.math.big;
	
	opens application to javafx.graphics, javafx.fxml;
}
