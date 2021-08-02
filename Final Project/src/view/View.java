package view;

import javafx.scene.effect.DropShadow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class View {
	private BorderPane bp;
	private Button add, start, simulat; 
	private TextField addName;
	private TextField[] players;
	private ToggleGroup tg;
	private RadioButton tennis, basket, soccer;
	private VBox vbNames, vbCenter, vbButtons;
	private HBox hbButton, hbTitle, hbRadioButton, hbNames, hbCenter;
	private Pane titlePane,namesPane, namesPane2, buttonPane;
	private Label addNameLabel;
	private Text title, addNameError, addGotNameFullError, champError;
	private Stage stage;

	public View(Stage stage1){
		stage = stage1;
		stage.setResizable(false);
		//errors 
		addNameError = new Text("Please enter a valid name");
		addNameError.setFill(Color.RED);
		addGotNameFullError = new Text("The tournament is full");
		addGotNameFullError.setFill(Color.RED);
		champError = new Text("Please enter 8 participants");
		champError.setFill(Color.RED);

		//Title
		DropShadow ds = new DropShadow();
		ds.setRadius(10);
		ds.setColor(Color.DARKBLUE);
		title = new Text("Championship");
		title.setFont(Font.font("Verdana", 30));
		title.setEffect(ds);
		titlePane = new Pane();
		titlePane.setMinWidth(520);
		titlePane.setMinHeight(100);
		hbTitle = new HBox();
		hbTitle.getChildren().addAll(titlePane, title);

		//Left 
		players = new TextField[8];
		Font font = Font.font("Times New Roman",FontWeight.BOLD, FontPosture.ITALIC, 13);
		namesPane2 = new Pane();
		namesPane2.setMinHeight(100);
		vbNames = new VBox();
		vbNames.getChildren().addAll(namesPane2);
		vbNames.setSpacing(30);
		for (int i = 0; i < players.length; i++) {
			players[i] = new TextField();
			players[i].setFont(font);
			players[i].setEffect(ds);
			players[i].setEditable(false);
			vbNames.getChildren().add(players[i]);
		}
		namesPane = new Pane();
		namesPane.setMinSize(320, 250);
		hbNames = new HBox();
		hbNames.getChildren().addAll(vbNames, namesPane);

		//Center 
		addNameLabel = new Label("Participant name:     ");
		addName = new TextField();
		hbCenter = new HBox();
		hbCenter.getChildren().addAll(addNameLabel, addName);
		hbCenter.setSpacing(15);
		add = new Button("Add Participant");
		start = new Button("Start Championship");
		simulat = new Button("simulat");
		hbButton = new HBox();
		hbButton.getChildren().addAll(add,start);
		hbButton.setSpacing(30);
		vbCenter = new VBox();
		vbCenter.getChildren().addAll(hbCenter, hbButton, simulat);
		vbCenter.setSpacing(40);
		vbCenter.setMaxHeight(250);

		//Right Buttons
		tennis = new RadioButton("Tennis");
		basket = new RadioButton("Basketball");
		soccer = new RadioButton("Soccer");
		tg = new ToggleGroup();
		tennis.setSelected(true);
		tennis.setToggleGroup(tg);
		basket.setToggleGroup(tg);
		soccer.setToggleGroup(tg);
		vbButtons = new VBox();
		vbButtons.setSpacing(15);
		vbButtons.setMinWidth(250);
		buttonPane = new Pane();
		buttonPane.setMinHeight(200);
		vbButtons.getChildren().addAll(buttonPane, tennis, basket, soccer);
		hbRadioButton = new HBox();
		hbRadioButton.getChildren().addAll(vbButtons);


		//connection everything
		bp = new BorderPane();
		bp.setTop(hbTitle);
		bp.setLeft(hbNames);
		bp.setCenter(vbCenter);
		bp.setRight(hbRadioButton);
		bp.setMargin(bp.getLeft(), new Insets(0, 0,0,40));
		int width = 1250, height = 832;
		Scene scene = new Scene(bp,width,height);
		stage.setScene(scene);
		stage.show();

	}

	public void addEventHandlerToAddParticipant(EventHandler<ActionEvent> event) { //Add button
		add.setOnAction(event);
	}

	public String addGotName() {
		return addName.getText();
	}

	public void addGotNameError() { // error for no Text
		vbCenter.getChildren().remove(addNameError);
		vbCenter.getChildren().add(addNameError);	
	}

	public void addGotNameFullError() { // error for max Participants
		addName.clear();
		vbCenter.getChildren().remove(addGotNameFullError);
		vbCenter.getChildren().add(addGotNameFullError);
	}

	public void update(int index) { // update the window
		String name = addName.getText();
		addName.clear();
		players[index-1].setText(name);
		vbCenter.getChildren().remove(addNameError);
		vbCenter.getChildren().remove(champError);


	}

	public void addEventHandlerToStart(EventHandler<ActionEvent> event) { //Start button
		start.setOnAction(event);
	}

	public void champError() {
		vbCenter.getChildren().remove(champError);
		vbCenter.getChildren().add(champError);
	}

	public void close() {
		stage.close();
	}

	public void addEventHandlerToSimulat(EventHandler<ActionEvent> event) { //Random button
		simulat.setOnAction(event);
	}

	public void randomUpdate() {
		players[0].setText("Ron");
		players[1].setText("Tali");
		players[2].setText("Almog");
		players[3].setText("Yuval");
		players[4].setText("Amir");
		players[5].setText("Lior");
		players[6].setText("Shlomi");
		players[7].setText("Ana");
	}

	public String getRandomNames(int index) {
		return players[index].getText();
	}

	public String getSport() {
		if(tennis.isSelected()) {
			return tennis.getText();
		}
		if(basket.isSelected()) {
			return basket.getText();
		}
		return soccer.getText();

	}

}
