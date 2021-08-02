package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.*;
import program.Program;
import view.BasketballScoreWindow;
import view.ChampoinshipWindow;
import view.ScoreWindow;
import view.SoccerScoreWindow;
import view.TennisScoreWindow;
import view.View;

public class Controller {
	Model theModel;
	View theView;
	ChampoinshipWindow champ;
	ScoreWindow score;
	EventHandler<ActionEvent> rightPlay;
	Stage stage1;
	public Controller(Model m, View v) {
		theModel = m;
		theView = v;



		Stage stage = new Stage();
		stage.setResizable(false);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				if(stage1.isShowing())
					event.consume();
			}
		});


		EventHandler<ActionEvent> AddParicipant = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(theModel.getSize()!=8){
					String name = theView.addGotName();
					if(!name.isEmpty()) {
						theModel.updatePlayers(name);
						theView.update(theModel.getSize());
					}
					else {
						theView.addGotNameError();
					}	
				}
				else {
					theView.addGotNameFullError();
				}
			}
		};
		theView.addEventHandlerToAddParticipant(AddParicipant);

		EventHandler<ActionEvent> simulat = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				theView.randomUpdate();
				theModel.setSizeToZero();
				for (int i = 0; i <= 7; i++) {
					String name = theView.getRandomNames(i);
					theModel.updatePlayers(name);
				}
			}
		};
		theView.addEventHandlerToSimulat(simulat); 

		EventHandler<ActionEvent> startChampoinship = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(theModel.getSize()==8){
					theModel.setSport(theView.getSport());
					theView.close();
					ChampoinshipWindow champ = new ChampoinshipWindow(stage, theModel.getSport());
					champ.launch(theModel);
					stage1 = new Stage();
					stage1.setAlwaysOnTop(true);
					stage1.setResizable(false);
					stage1.setOnCloseRequest(new EventHandler<WindowEvent>() {
						@Override
						public void handle(WindowEvent event) {
							event.consume();
						}
					});
					EventHandler<ActionEvent> back = new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							champ.close();
							Program.startOver(stage);
						}
					};champ.addEventHandlerToBack(back);

					EventHandler<ActionEvent> wrongPlay = new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							champ.wrongPlayButtonError();
						}
					};
					champ.addEventHandlerToWrongPlay(wrongPlay, theModel.getGameNumber());

					rightPlay = new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							switch(theModel.getSport()) {
							case 1:
								score = new TennisScoreWindow(stage1,champ);
								break;
							case 2:
								score = new BasketballScoreWindow(stage1, champ);
								break;
							case 3:
								score = new SoccerScoreWindow(stage1, champ);
								break;
							}
							champ.scoreIsOpen();
							score.setNames(theModel);
							EventHandler<ActionEvent> done = new EventHandler<ActionEvent>() {
								public void handle(ActionEvent event) {
									if(score.isNumbers()) {
										if(theModel.updateWinner(score.checkWinner())) {
											score.closeScoreWindow(stage1, theModel);
											champ.addEventHandlerToWrongPlay(wrongPlay, theModel.getGameNumber());
											champ.addEventHandlerToRightPlay(rightPlay, theModel.getGameNumber());
											if(theModel.getGameNumber() == 7) {
												champ.weGotAWinner();
											}
										}
										else {
											score.tieError();
										}
									}
								}
							};
							score.addEventHandlerToDone(done);

						}
					};
					champ.addEventHandlerToRightPlay(rightPlay, theModel.getGameNumber());

				}
				else {
					theView.champError();
				}

			}
		};
		theView.addEventHandlerToStart(startChampoinship); 




	}
}