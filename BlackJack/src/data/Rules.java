package data;

import KnowledgeSystem.Bank;
import KnowledgeSystem.Player;

/**
 * 
 * @author Fabian Schäfer
 * @brief This Class contains the BlackJack policy
 * @param players stores the amount of players for the BlackJack game
 * @param time stores the waiting time for an player interaction in each game cycle
 * @param blackJack marks the BlackJack value
 * @param boxLimit limits the max. stake
 */

public class Rules {
	
	private int players;
	private double time;
	private int blackJack;
	private int boxLimit;

	public Rules(int players, double time)
	{
		this.setPlayers(players);
		this.setTime(time);
		this.setBlackJack(21);
		this.setBoxLimit(50);
	}
	
	public boolean checkTripleSeven(int[][] openCards)
	{
		/**
		 * iff a player has BlackJack, then this function checks for triple seven.
		 * In case of triple seven the player wins immediatly in relation of 3:2, 
		 * after the player is out of the current game
		 */
		int i = 0;
		while(i<3)
		{
			if(openCards[i][3] == 7) i++;
			else break;
		}
		if(i == 2) return true;
		else return false;
	}
	
	public boolean checkDraw (Bank bank, Player player, int[][] openCards)
	{
		/**
		 * This method checks whether player and bank have a BlackJack simultaneously
		 */
		if(bank.getCardScore() == 21 && player.getCardScore() == 21)
		{
			if(!checkTripleSeven(openCards)) return true;
		}
		return false;
	}

	public boolean insuranceEnabled(int[][] firstCard)
	{
		/**
		 * This Method gets the first open Card of the dealer (bank) and check
		 */
		if(firstCard[0][0] == 12) return true;
		return false;
	}
	//TODO: Bust, split und doppeln Bedingungen --> sollte parallel mit den entsprechenden player methoden implmentiert werden
	
	//getters & setters
	public int getPlayers() {
		return players;
	}

	public void setPlayers(int players) {
		this.players = players;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public int getBlackJack() {
		return blackJack;
	}

	public void setBlackJack(int blackJack) {
		this.blackJack = blackJack;
	}

	public int getBoxLimit() {
		return boxLimit;
	}

	public void setBoxLimit(int boxLimit) {
		this.boxLimit = boxLimit;
	}
}
