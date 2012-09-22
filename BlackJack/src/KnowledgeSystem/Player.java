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
	
	public void setCredit(int m) {
		credit += m;
	}
	
	public void setCard(int[] pC) {		
		if(countCards==1 && pC[2]<11) {
			System.out.println("Verdoppeln? (Spielmodus)");
		}
		if (countCards==1 && Card==pC) {
			System.out.println("Splitten? (Spielmodus)");
		}
		Card = pC;
		CardScore += pC[2];
	}
	
	public int getCredit() {
		return credit;
	}
	
	public double getBet() {
		return Bet;
	}
	
	public void setBet(double d) {
		Bet = d;
		credit -= d;
	}
}