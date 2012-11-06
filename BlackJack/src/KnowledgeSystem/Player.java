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
	
	public void splitt() {
		Bet = Bet*2;
	}
	
	public void insure () {
		Bet = Bet + Bet/2;
	}
	
	public void doubling() {
		Bet = Bet*2;
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
		System.out.println("new Game");
		Bet = 0;
		cardScore[0] = 0;
		cardScore[1] = 0;
		inGame = false;
		resetCountCards();
//		for (int i=0; i<2;i++) {
//			card[i]=0;
//		}
	}
}