package KnowledgeSystem;

/**
 * 
 * @author Boris Beck & Fabian Schäfer
 * @brief a player can be a human or an agent
 * @param credit stores the specific money of each participant
 */
public class Player extends Participant{
	
	protected int credit;
	private int Bet;
	public int gamesPlayed;
	protected int countCards;
	public int gamesWon;
	public int gamesLost;
	public int gamesDraw;
	
	public Player(int c) 
	{
		super();
		gamesPlayed=0;
		gamesWon=0;
		gamesLost=0;
		gamesDraw=0;
		credit+=c;
	}
	
	// TODO Bug money
	
	public void splitt() {
		//Bet = Bet*2;
	}
	
	public void insure () {
//		Bet = Bet + Bet/2;
	}
	
	public void doubling() {
		Bet = Bet*2;
	}
	
	public void setCredit(double d) {
		credit =(int) (credit+d);
	}
	
	public int getCredit() {
		return credit;
	}
	
	public int getBet() {
		return Bet;
	}
	
	public void setBet(int d) {
		Bet = d;
		credit = credit - d;
		System.out.println("Bet " +Bet);
		
		if (inGame = false) {
			inGame = true;
		}
	}
	
	public int getBetResult() {
		return Bet;
	}
	
	public void newGame() {
		Bet = 0;
		cardScore[0] = 0;
		cardScore[1] = 0;
		inGame = false;
		resetCountCards();
	}
}