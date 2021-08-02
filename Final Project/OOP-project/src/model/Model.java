package model;
import java.util.ArrayList;

public class Model {
	private ArrayList<Player> allPlayers;
	private Player[]  semiFinal, finalRound;
	private Player theWinner;
	private int sport;
	private int gameNumber;

	public Model() {
		allPlayers = new ArrayList<>();
		semiFinal = new Player[4];
		finalRound = new Player[2];
		sport = 0;
		gameNumber= 0;
	}

	public void updatePlayers(String name) {
		Player player = new Player(name);
		allPlayers.add(player);
	}

	public int getSize() {
		return allPlayers.size();
	}

	public void setSizeToZero() {
		allPlayers.clear();
		Player.resetIndex();
	}

	public String getName(int index) {
		return allPlayers.get(index).toString();
	}
	
	public String getSemiFinalist () {
		return semiFinal[gameNumber].toString();
	}

	public String getSemiFinalistByIndex (int index) {
		return semiFinal[index].toString();
	}
	
	public String getFinalRoundByIndex(int index) {
		return finalRound[index].toString();
	}
	public int setSport(String game) {
		switch(game) {
		case"Tennis":
			sport = 1;
			return 1;
		case"Basketball":
			sport = 2;
			return 2;
		case"Soccer":
			sport =3;
			return 3;
		}
		return 0;
	}

	public int getSport() {
		return sport;
	}

	public int getGameNumber() {
		return gameNumber;
	}
	
	public void nextGame() {
		gameNumber++;
	}
	
	public String getWinenr() {
		return theWinner.toString();
	}

	public boolean updateWinner(int winner) {
		if(winner ==1 || winner ==0) {
			switch(gameNumber) {
			case 0:
				semiFinal[gameNumber] = allPlayers.get(winner);
				return true;
			case 1:
				semiFinal[gameNumber] = allPlayers.get(winner+2);
				return true;
			case 2:
				semiFinal[gameNumber] = allPlayers.get(winner+4);
				return true;
			case 3:
				semiFinal[gameNumber] = allPlayers.get(winner+6);
				return true;
			case 4:
				finalRound[0] = semiFinal[winner];
				return true;
			case 5:
				finalRound[1] = semiFinal[winner+2];
				return true;
			case 6:
				theWinner = finalRound[winner];
				return true;
			}
		}
		return false;
	}
	
}


