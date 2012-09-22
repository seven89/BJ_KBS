package data;

/**
 * 
 * @author Fabian Schäfer
 * @brief This Class contains the BlackJack policy
 * @param players stores the amount of players for the BlackJack game
 * @param time stores the waiting time for an player interaction in each game cycle
 */

public class Rules {
	
	private int players;
	private double time;

	public Rules(int players, double time)
	{
		this.setPlayers(players);
		this.setTime(time);
	}

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
}
