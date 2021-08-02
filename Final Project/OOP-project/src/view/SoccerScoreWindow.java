package view;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
public class SoccerScoreWindow extends ScoreWindow {
	private CheckBox[] penaltyKicks;
	public SoccerScoreWindow(Stage stage, ChampoinshipWindow champ) {
		super(stage, champ);
		title.setText("Soccer Match");
		for (int i = 0; i < 4; i++) {
			scores[i] = new TextField();
			scores[i].setMaxWidth(60);
			if(i<2) {
				scores[i].setLayoutX(140+(i*90)); // player1 score box 
				scores[i].setLayoutY(125);
			}
			else {
				scores[i].setLayoutX(140+(i-2)*90); // player2 score box
				scores[i].setLayoutY(190);
			}
			pane.getChildren().add(scores[i]);
		}
	}


	@Override
	public int checkWinner() {
		int player1=0, player2=0;
		if(error.getText().isEmpty()) { // checks its not penalty kicks
			player1 = Integer.parseInt(scores[0].getText()) + Integer.parseInt(scores[1].getText()); 
			player2 = Integer.parseInt(scores[2].getText()) + Integer.parseInt(scores[3].getText());

		}
		else { //penalty kicks
			for (int i = 0; i < penaltyKicks.length; i++) {
				if(penaltyKicks[i].isSelected() ) {
					if(i<=4)
						player1++;
					else
						player2++;
				}
			}
		}
		if(player1 > player2) 
			return 0;
		if(player2 > player1)
			return 1;
		return 2;
	}

	@Override
	public void tieError() {
		for (int i = 0; i < 4; i++) {
			pane.getChildren().remove(scores[i]);
		}
		penaltyKicks = new CheckBox[10];
		for (int i = 0; i < penaltyKicks.length; i++) {
			if(i<=4) {
				penaltyKicks[i] = new CheckBox("kick " + (i+1) );
				penaltyKicks[i].setLayoutX(100+(i*80));
				penaltyKicks[i].setLayoutY(125);
			}
			else {
				penaltyKicks[i] = new CheckBox("kick " + (i-4));
				penaltyKicks[i].setLayoutX(100+(i-5)*80);
				penaltyKicks[i].setLayoutY(190);
			}
			pane.getChildren().add(penaltyKicks[i]);
		}

		error.setText("Penalty kicks, Enter the results");

	}

}
