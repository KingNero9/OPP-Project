package view;

import javafx.stage.Stage;
import javafx.scene.control.TextField;


public class TennisScoreWindow extends ScoreWindow  {

	public TennisScoreWindow(Stage stage, ChampoinshipWindow champ) {
		super(stage,champ);
		title.setText("Tennis Match");
		for (int i = 0; i < scores.length; i++) {
			scores[i] = new TextField();
			scores[i].setMaxWidth(40);
			if(i<=4) {
				scores[i].setLayoutX(100+(i*60));
				scores[i].setLayoutY(125);
			}
			else {
				scores[i].setLayoutX(100+(i-5)*60);
				scores[i].setLayoutY(190);
			}
			pane.getChildren().add(scores[i]);
		}


	}

	public int checkWinner() {
		int player1=0, player2=0;
		for(int i = 0; i < scores.length/2; i++) {
			if(Integer.parseInt(scores[i].getText()) != 6 && Integer.parseInt(scores[i+5].getText()) != 6) {
				return 2;
			}
			if(Integer.parseInt(scores[i].getText()) > Integer.parseInt(scores[i+5].getText())) {
				player1++;
				if(player1>=3) { //player1 win 
					return 0;
				}
			}
			else if(Integer.parseInt(scores[i].getText()) < Integer.parseInt(scores[i+5].getText())){
				player2++;
				if(player2>=3){ // player 2 win 
					return 1;
				}
			}
		}
		return 2; // A tie
	}

	public void tieError() {
		error.setText("You can win a max of 6 games in a set\nThere must be a winner, you win by wining 3 sets");
	}

}


