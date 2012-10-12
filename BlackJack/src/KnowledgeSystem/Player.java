package KnowledgeSystem;

/**
 * 
 * @author Boris Beck & Fabian Schäfer
 * @brief a player can be a human or an agent
 * @param credit stores the specific money of each participant
 */
public class Player extends Participant{
	
	protected int credit;
	private double Bet;
	public int gamesPlayed;
	public int gamesWon;
	public int gamesLost;
	public int gamesDraw;
	
	public Player(int credit) 
	{
		super();
		gamesPlayed=0;
		gamesWon=0;
		gamesLost=0;
		gamesDraw=0;
	}
	
	public void splitt()
	{
		//TODO: implement function
	}
	
	public void insure ()
	{
		//TODO: implement function
	}
	
	public void setCredit(double d) {
		credit += d;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public double getBet() {
		return Bet;
	}
	
	public void setBet(double d) {
		Bet = d;
		
		if (inGame = false) {
			inGame = true;
		}
	}
	
	public void newGame() {
		Bet = 0;
		cardScore[0] = 0;
		cardScore[1] = 0;
		inGame = false;
	}
}