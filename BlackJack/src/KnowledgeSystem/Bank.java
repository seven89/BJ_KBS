package KnowledgeSystem;

public class Bank {
	
	int CardScore=0;
	int[] Card;
	boolean inGame=true;
	
	public void setCard(int[] pC) {
		Card = pC;
		CardScore += pC[2];
	}
	
	public int getCardScore() {
		return CardScore;
	}
}