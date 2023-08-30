package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	public static final int WIDTH = 350;
	public static final int HEIGHT = 600;
	public static final double MARGIN = 5;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = creatPane();
			Scene scene = new Scene(root,WIDTH,HEIGHT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private VBox creatPane() {
		VBox root = new VBox(MARGIN);
		Model model = new ScreenPanel();
		root.getChildren().add((Node) model);
		root.getChildren().add(new NumberPanel(model));
		return root;
	}
}

/*	Problems:
 * 	Constant 
 * 	Trigo UI
 *  DEG / RAD
 *  Error handle
 */
