package model;


import javafx.scene.text.Text;

public class Player {
	private Text name;
	private static int index;
	private int myIndex;
	
	public Player(String name) {
		this.name = new Text(name);
		myIndex=index++;
	}
	
	public String toString() {
		return name.getText();
	}
	
	public int getMyIndex() {
		return myIndex;
	}
	
	public static int getIndex() {
		return index;
	}
	
	public static void resetIndex() {
		index = 0;
	}

}
