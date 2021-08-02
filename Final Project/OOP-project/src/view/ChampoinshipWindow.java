package view;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Line;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;
import javafx.scene.paint.Color;
import javafx.scene.Group;

public class ChampoinshipWindow {
	private Pane pane;
	private Button [] plays;
	private Button back;
	private TextField [] players;
	private VBox vbRound1, vbRound2, vbRound3;
	private Text title, wrongButtonError;
	private Line line1, line2,eLine1; // Straight lines Round 1
	private Line vLine1,vLine2; //Vertical Lines Round 1cccc
	private Group [] rounds;
	private Stage stage;

	public ChampoinshipWindow(Stage stage, int game) {
		this.stage = stage;
		pane = new Pane();
		back = new Button("Back to the Main screen");
		back.setLayoutX(580);
		back.setLayoutY(750);
		wrongButtonError = new Text("You cannot start this match because you need to complete prior matches");
		wrongButtonError.setFill(Color.RED);
		wrongButtonError.setLayoutX(550);
		wrongButtonError.setLayoutY(600);
		Font font = Font.font("Times New Roman",FontWeight.BOLD, FontPosture.ITALIC, 13);
		DropShadow ds = new DropShadow();
		ds.setRadius(15);
		ds.setColor(Color.DARKBLUE);
		players = new TextField[15];
		for (int i = 0; i < players.length; i++) {
			players[i] = new TextField();
			players[i].setFont(font);
			players[i].setEffect(ds);
			players[i].setEditable(false);
			if(i ==14) {
				DropShadow ds1 = new DropShadow();
				ds1.setColor(Color.RED);
				players[i].setEffect(ds1);
				players[i].setLayoutX(1000);
				players[i].setLayoutY(385);
			}

		}
		vbRound1 = new VBox(); //First Round
		vbRound1.setSpacing(30);
		vbRound1.setLayoutX(40);
		vbRound1.setLayoutY(200);
		plays = new Button[7];
		rounds = new Group[15];
		int count =0;
		for (int i = 0; i < 8; i++) { // FIRST ROUND
			rounds[i] = new Group();
			vbRound1.getChildren().add(players[i]);
			line1 = new Line(205, 215+(54*i), 250 ,215+(54*i));
			if(i %2 ==0) {
				vLine1 = new Line(250,215+(54*i),250,215+(54*i)+15);
				eLine1 = new Line(315,215+(54*i)+27, 360, 215+(54*i)+27);
				plays[count] = new Button("Play a game");
				plays[count].setLayoutX(235);
				plays[count].setLayoutY(230+(54)*i);
				rounds[i].getChildren().add(plays[count]);
				count++;
			}
			else {
				vLine1 = new Line(250,215+(54*i),250,215+(54*i)-(13));
			}
			rounds[i].getChildren().addAll(line1,vLine1,eLine1);
			pane.getChildren().add(rounds[i]);
		}


		vbRound2 = new VBox(); //Round2
		vbRound2.setSpacing(82);
		vbRound2.setLayoutY(230);
		vbRound2.setLayoutX(380);
		for (int i = 0; i < 4; i++) {
			vbRound2.getChildren().add(players[i+8]);	
			line1 = new Line(545,242 + (108*i), 590, 242+(108*i));
			if(i%2 ==0) {
				vLine1 = new Line(590,242 + (108*i),590,242+(108*i)+38);
				eLine1 = new Line(655,242 + (108*i)+50, 690, 242 + (108*i)+50);
				plays[count] = new Button("Play a game");
				plays[count].setLayoutX(575);
				plays[count].setLayoutY(280+(108)*i);
				rounds[i].getChildren().add(plays[count]);
				count++;
			}
			else {
				vLine1 = new Line(590,242+(108*i),590,242+(108*i)-(43));
			}
			rounds[i].getChildren().addAll(line1,vLine1,eLine1);
		}

		vbRound3 = new VBox(); //Round3
		vbRound3.setSpacing(188);
		vbRound3.setLayoutY(280);
		vbRound3.setLayoutX(720);
		vbRound3.getChildren().add(players[12]);
		vbRound3.getChildren().add(players[13]);
		line1 = new Line(885,294, 925, 294);
		line2 = new Line(885,506, 925, 506);
		vLine1 = new Line(925,294,925,294+90);
		vLine2 = new Line(925,506,925,506-97);
		plays[count] = new Button("Play a game");
		plays[count].setLayoutX(890);
		plays[count].setLayoutY(385);
		eLine1 = new Line(970,400, 985, 400);

		rounds[3].getChildren().addAll(line1, line2,vLine1, vLine2,eLine1,plays[count]);

		//title
		switch(game) {
		case 1:
			title = new Text("Tennis Championship");
			break;
		case 2: 
			title = new Text("BasketBall Championship");
			break;
		case 3:
			title = new Text("Soccer Championship");
			break;
		}
		title.setFont(Font.font("Verdana", 30));
		title.setEffect(ds);
		title.setLayoutX(480);
		title.setLayoutY(45);
		pane.getChildren().addAll(title,vbRound1,vbRound2,vbRound3,players[14]);

		int width = 1250, height = 832;
		Scene scene = new Scene(pane,width,height);
		stage.setScene(scene);
		stage.show();
	}

	public void launch(Model theModel) {
		for (int i = 0; i < theModel.getSize(); i++) {
			players[i].setText(theModel.getName(i));
		}
	}

	public void addEventHandlerToRightPlay(EventHandler<ActionEvent> event, int j) { 
		if(plays.length > j) {
			plays[j].setOnAction(event);
		}
	}
	
	public void addEventHandlerToWrongPlay(EventHandler<ActionEvent> event, int j) {
		for (int i = 0; i < plays.length; i++) {
			if(j!=i) {
				plays[i].setOnAction(event);
			}
		}
	}
	
	public void addEventHandlerToBack(EventHandler<ActionEvent> event) { 
		back.setOnAction(event);
	}

	public void wrongPlayButtonError() {
		pane.getChildren().remove(wrongButtonError);
		pane.getChildren().add(wrongButtonError);
	}

	public void scoreIsOpen() {
		pane.getChildren().remove(wrongButtonError);
		for (int i = 0; i < plays.length; i++) {
			plays[i].setDisable(true);
		}
	}

	public void scoreIsClosed(Model theModel) {
		for (int i = theModel.getGameNumber()+1; i < plays.length; i++) {
			plays[i].setDisable(false);
		}
		if(theModel.getGameNumber() <= 3) {
			players[theModel.getGameNumber()+8].setText(theModel.getSemiFinalist());
		}
		else if(theModel.getGameNumber() <= 5){
			players[theModel.getGameNumber()+8].setText(theModel.getFinalRoundByIndex(theModel.getGameNumber()-4));

		}
		else {
			players[theModel.getGameNumber()+8].setText(theModel.getWinenr());
		}
		theModel.nextGame();
	}
	
	public void weGotAWinner() {
		pane.getChildren().add(back);
	}
	
	public void close() {
		stage.close();
	}
}
