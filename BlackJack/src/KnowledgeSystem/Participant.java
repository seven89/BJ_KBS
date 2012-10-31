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
	protected int[] card;
	protected int countCards;
	protected int[] cardScore;
	protected boolean inGame;
	
	
	public Participant ()
	{
		cardScore = new int[2];
		cardScore[0] = 0;	//Low Stack (Ass=1)
		cardScore[1] = 0;	//High Stack (Ass=11)
		countCards=0;
		inGame=true;
	}
	
	
	public void holdValue()
	{
		/**
		 * Participant stay on current card-value
		 */
	}
	
	public void setCard(int[] pC) {
		/**
		 * Participant pulls another card
		 */
		
		// if Ass (eleven or one score)
		if (pC[2]==0) {
			cardScore[0] += 1;
			cardScore[1] += 11;
		}
		else {
			cardScore[0] += pC[2];
			cardScore[1] += pC[2];
		}
		card = pC;
		countCards++;
	}
	
	public int[] getCardScore() {
		return cardScore;
	}
	
	public int getCards() {
		return countCards;
	}
	
	
	//getters & setters
	public boolean getInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}
	

}
