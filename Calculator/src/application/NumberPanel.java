package application;


import java.util.ArrayList;
import java.util.List;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class NumberPanel extends GridPane{
	
	private Model aModel;
	private Stage primaryStage;
	private final int Spacing = 2;
	private final double MINHEIGHT = 400;
	private final double MINWIDTH = 200;
//	private final double initX = 5;
//	private final double initY = 5;
	private final double ButtonHeight = 50;
	private final double ButtonWidth = 60;
	private final String[] Symbols = {"Tri","π","e","CE","<-",
									"RAD","|x|","x^2","^","mod",
									"DEG","(",")","n!","/",
									"√","7","8","9","*",
									"*10^x","4","5","6","-",
									"log","1","2","3","+",
									"ln","+/-","0",".","="};
	
	
	public NumberPanel(Model pModel, Stage stage) {
		super();
		aModel = pModel;
		this.primaryStage = stage;
		setLayout();
		setButtons();
		
	}
	private void setLayout() {
		this.setMinSize(MINHEIGHT,MINWIDTH);
		this.setVgap(Spacing);
		this.setHgap(Spacing);
		
//		this.setTranslateX(initX);
//		this.setTranslateY(initY);
	}
	
	private void setButtons() {
		
		for (int i = 0;i<Symbols.length;i++) {
			Button button;
			
			switch(Symbols[i]) {
				case "Tri":
					button = new Button("Tri");
					EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
						
					    public void handle(ActionEvent e)
					    {
					    	Popup trigo_popup = new Popup();
							Label label = new Label("This is a Popup");
							StackPane popupLayout = new StackPane();
							popupLayout.setStyle("-fx-background-color: lightgray;");
					        popupLayout.getChildren().addAll(label);
					        trigo_popup.getContent().add(popupLayout);
					        trigo_popup.setAutoHide(true);
					        double sceneX = button.localToScene(button.getBoundsInLocal()).getWidth();
				            double sceneY = button.localToScene(button.getBoundsInLocal()).getHeight();
				            System.out.println(sceneX);
				            System.out.println(sceneY);
					        trigo_popup.show(NumberPanel.this,sceneX,sceneY);
							
					    }
					};
					button.setOnAction(event);
					break;
				case "RAD":
					button = new Button("RAD");
					button.setOnAction(e->aModel.set_RAD());
					break;
				case "DEG":
					button = new Button("DEG");
					button.setOnAction(e->aModel.set_DEG());
					break;
				case "|x|":
					button = new Button("|x|");
					button.setOnAction(e->aModel.addExpression("abs("));
					break;
				case "x^2":
					button = new Button("x^2");
					button.setOnAction(e->aModel.addExpression("^2"));
					break;
				case "√":
					button = new Button("√");
					button.setOnAction(e->aModel.addExpression("sqrt("));
					break;
				case "*10^x":
					button = new Button("*10^x");
					button.setOnAction(e->aModel.addExpression("*10^"));
					break;
				case "=":
					button = new Button("=");
					button.setOnAction(e->aModel.evaluate());
					break;
				case "+/-":
					button = new Button("+/-");
					button.setOnAction(e->aModel.addExpression("Neg("));
					break;
				case "mod":
					button = new Button("mod");
					button.setOnAction(e->aModel.addExpression("%"));
					break;
				case "n!":
					button = new Button("n!");
					button.setOnAction(e->aModel.addExpression("!"));
					break;
				case "log":
					button = new Button("log");
					button.setOnAction(e->aModel.addExpression("log("));
					break;
				case "tan":
					button = new Button("tan");
					button.setOnAction(e->aModel.addExpression("tan("));
					break;
				case "sin":
					button = new Button("sin");
					button.setOnAction(e->aModel.addExpression("sin("));
					break;
				case "cos":
					button = new Button("cos");
					button.setOnAction(e->aModel.addExpression("cos("));
					break;
				case "CE":
					button = new Button("CE");
					button.setOnAction(e->aModel.clear());
					break;
				case "<-":
					button = new Button("<-");
					button.setOnAction(e->aModel.back());
					break;
				default:
					button = new Button(Symbols[i]);
					button.setOnAction(e->aModel.addExpression(button.getText()));
					break;
			}
			
			button.setMinWidth(ButtonWidth);
			button.setMinHeight(ButtonHeight);
			this.add(button, i%5, i/5);	
		}
	}
}
