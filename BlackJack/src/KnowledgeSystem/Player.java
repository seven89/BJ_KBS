package KnowledgeSystem;

public class Player extends Bank{
	
	double Money, countCards=0;
	double Bet;
	
	public void setMoney(int m) {
		Money += m;
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
	
	public double getMoney() {
		return Money;
	}
	
	public double getBet() {
		return Bet;
	}
	
	public void setBet(double d) {
		Bet = d;
		Money -= d;
	}
	
	public void setInGame(boolean iG) {
		inGame = iG;
	}
	
	public boolean getInGame() {
		return inGame;
	}
}