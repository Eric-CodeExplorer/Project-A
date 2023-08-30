package application;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class ScreenPanel extends Parent implements Model{
	
	private final double WIDTH = 348;
	private final double HEIGHT = 200;
	private Label aLabel;
	private String expression;
	private Evaluator evaluator;
	
	
	public ScreenPanel() {
		evaluator = new Evaluator();
		expression = "";
		aLabel = new Label();
		nodeConfig();
		aLabel.setText(expression);
		getChildren().add(aLabel);
	}
	
	private void nodeConfig() {
		aLabel.setMaxHeight(HEIGHT);
		aLabel.setMaxWidth(WIDTH);
		aLabel.setMinHeight(HEIGHT);
		aLabel.setMinWidth(WIDTH);
		aLabel.setTextAlignment(TextAlignment.RIGHT);
		aLabel.setAlignment(Pos.CENTER_RIGHT);
		aLabel.setWrapText(true);
	}
	
	public String getExpression() {
		return expression;
	}

	@Override
	public void addExpression(String s) {
		expression = expression.concat(s);
		aLabel.setText(expression);
	}

	@Override
	public void clear() {
		expression = "";
		aLabel.setText(expression);
		
	}

	@Override
	public void evaluate() {
		try {
			expression = evaluator.evaluate(expression);
		}catch (Throwable e) {
			e.printStackTrace();
			expression = "Error";
		}
		aLabel.setText(expression);
		
	}
	@Override
	public void back() {
		if (expression != "") {
			StringBuffer sb= new StringBuffer(expression); 
			sb.deleteCharAt(sb.length()-1);
			expression = sb.toString();
			aLabel.setText(expression);
		}
		
	}

	@Override
	public void set_DEG() { evaluator.is_DEG = true; }

	@Override
	public void set_RAD() { evaluator.is_DEG = false; }
}
