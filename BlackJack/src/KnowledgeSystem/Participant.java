package KnowledgeSystem;

/**
 * 
 * @author Fabian Schäfer & Boris Beck
 * @param CardScore stores the sum of current card values
 * @param inGame marks all active players: active player = 
 * has a chance to win (has not exceeded the 21 and a guarded value is not surpassed)
 * @param Card stores the received cards for each participant
 */

public class Participant {
	
	//private CardSet [] cards;
	protected int[] Card;
	protected int CardScore;
	protected boolean inGame;
	
	public Participant ()
	{
		CardScore = 0;
		inGame=true;
	}
	
	public void takeCard()
	{
		/**
		 * Participant pulls another card
		 */
	}
	
	public void holdValue()
	{
		/**
		 * Participant stay on current card-value
		 */
	}
	
	public void setCard(int[] pC) {
		Card = pC;
		CardScore += pC[2];
	}
	
	public int getCardScore() {
		return CardScore;
	}
	
	
	
	//getters & setters

	public boolean getInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}
	

}
