package view;


import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BasketballScoreWindow extends ScoreWindow {
	public BasketballScoreWindow(Stage stage, ChampoinshipWindow champ) {
		super(stage, champ);
		title.setText("BasketBall Match");
		title.setLayoutX(200);
		for (int i = 0; i < 8; i++) {
			scores[i] = new TextField();
			scores[i].setMaxWidth(60);
			if(i<4) {
				scores[i].setLayoutX(100+(i*80)); // player1 score box 
				scores[i].setLayoutY(125);
			}
			else {
				scores[i].setLayoutX(100+(i-4)*80); // player2 score box
				scores[i].setLayoutY(190);
			}
			pane.getChildren().add(scores[i]);
		}

	}

	@Override
	public int checkWinner() {
		int player1=0, player2=0;
		for(int i = 0; i < 4; i++) {
			player1 += Integer.parseInt(scores[i].getText());
		}
		for (int i = 4; i < 8; i++) {
			player2 += Integer.parseInt(scores[i].getText());
		}
		if(player1>player2 || Integer.parseInt(scores[8].getText()) >  Integer.parseInt(scores[9].getText())) // left is normal score right is overtime
			return 0;
		if(player2>player1 || Integer.parseInt(scores[8].getText()) < Integer.parseInt(scores[9].getText()))
			return 1;
		return 2;
	}

	@Override
	public void tieError() {
		scores[8] = new TextField(); // player1 score box
		scores[8].setMaxWidth(60);
		scores[8].setLayoutX(100+(4*80));
		scores[8].setLayoutY(125);
		scores[9] = new TextField(); // player2 score box
		scores[9].setMaxWidth(60);
		scores[9].setLayoutX(100+(4*80)); 
		scores[9].setLayoutY(190);
		pane.getChildren().addAll(scores[8], scores[9]);
		error.setText("Overtime, enter each player/ team score");

	}


}
