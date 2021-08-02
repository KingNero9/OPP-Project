package program;

import controller.Controller;
import model.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;

public class Program extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Model theModel = new Model();
		View theView = new View(stage);
		Controller TheController = new Controller(theModel, theView);
	}
	
	public static void startOver(Stage stage) {
		Model theModel = new Model();
		View theView = new View(stage);
		Controller TheController = new Controller(theModel, theView);
	}

}


