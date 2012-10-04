package KnowledgeSystem;

/**
 * 
 * @author Boris Beck & Fabian Schäfer
 * @brief a player can be a human or an agent
 * @param credit stores the specific money of each participant
 */
public class Player extends Participant{
	
	protected int credit;
	private double countCards;
	private double Bet;
	public int gamesPlayed=0;
	public int gamesWon=0;
	public int gamesLost=0;
	public int gamesDraw=0;
	
	public Player(int credit) 
	{
		super();
		countCards=0;
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
		countCards = 0;
		Bet = 0;
		cardScore[0] = 0;
		cardScore[1] = 0;
		inGame = false;
	}
}