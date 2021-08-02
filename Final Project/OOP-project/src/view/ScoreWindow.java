package view;

import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;
import javafx.scene.paint.Color;


public abstract class ScoreWindow {
	protected Pane pane;
	protected DropShadow ds;
	protected Text title;
	protected int width, height;
	protected Button done;
	protected Text name1, name2 , error;
	protected TextField [] scores;
	private ChampoinshipWindow champ;

	public ScoreWindow(Stage stage, ChampoinshipWindow champ) {
		this.champ = champ;
		scores = new TextField [10];
		error = new Text();
		error.setFill(Color.RED);
		error.setLayoutX(280);
		error.setLayoutY(330);
		title = new Text();
		title.setFont(Font.font("Verdana", 30));
		ds = new DropShadow();
		ds.setRadius(15);
		ds.setColor(Color.DARKBLUE);
		title.setEffect(ds);
		title.setLayoutX(220);
		title.setLayoutY(45);
		pane = new Pane();
		name1= new Text();
		name1.setLayoutY(140);
		name1.setLayoutX(20);
		name2= new Text();
		name2.setLayoutY(210);
		name2.setLayoutX(20);
		width = 625;
		height = 350;
		done = new Button("Done");
		done.setLayoutX(302);
		done.setLayoutY(280);
		pane.getChildren().addAll(title,name1,name2,done, error);
		Scene scene = new Scene(pane,width,height);
		stage.setScene(scene);
		stage.show();
	}

	
	
	public abstract int checkWinner();
	
	
	public abstract void tieError();
	
	public void setNames(Model theModel) {
		switch(theModel.getGameNumber()) {
		case 0:
			name1.setText(theModel.getName(0));
			name2.setText(theModel.getName(1));
			break;
		case 1:
			name1.setText(theModel.getName(2));
			name2.setText(theModel.getName(3));
			break;
		case 2:
			name1.setText(theModel.getName(4));
			name2.setText(theModel.getName(5));
			break;
		case 3:
			name1.setText(theModel.getName(6));
			name2.setText(theModel.getName(7));
			break;
		case 4:
			name1.setText(theModel.getSemiFinalistByIndex(0));
			name2.setText(theModel.getSemiFinalistByIndex(1));
			break;
		case 5:
			name1.setText(theModel.getSemiFinalistByIndex(2));
			name2.setText(theModel.getSemiFinalistByIndex(3));
			break;
		case 6:
			name1.setText(theModel.getFinalRoundByIndex(0));
			name2.setText(theModel.getFinalRoundByIndex(1));
			break;
		}
	}

	public void addEventHandlerToDone(EventHandler<ActionEvent> event) {
		done.setOnAction(event);
	}	
	
	public boolean isNumbers() {
		for (int i = 0; i < scores.length; i++) {
			try {
				Integer.parseInt(scores[i].getText());
			}
			catch(NumberFormatException e) {
				error.setText("Make sure you only entered numbers");
				return false;
			}
			catch(NullPointerException e) {
				scores[i] = new TextField();
				scores[i].setText("0");
			}
		}
		return true;
	}	
	
	public void closeScoreWindow(Stage stage, Model theModel) {
		champ.scoreIsClosed(theModel);
		stage.close();
	}
	
		

	
}


