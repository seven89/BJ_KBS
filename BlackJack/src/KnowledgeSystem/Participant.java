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
	protected int cardtmp;
	protected int countCards;
	protected int[] cardScore;
	protected boolean inGame;
	protected int helpAss;
	boolean secondCardScore;
	
	
	public Participant ()
	{
		cardScore = new int[2];
		cardScore[0] = 0;	//Low Stack (Ass=1)
		cardScore[1] = 0;	//High Stack (Ass=11)
		countCards=0;
		inGame=true;
		helpAss=0;
		secondCardScore=false;
	}
	
	
	public void holdValue()
	{
		/**
		 * Participant stay on current card-value
		 */
	}
	
	public void setCard(int c0, int c1, int c2) {
		/**
		 * Participant pulls another card
		 */
		
		// if Ass (eleven or one score)
		if (c2==11 && helpAss==0) {
			cardScore[0] += 1;
			cardScore[1] += 11;
			helpAss++;
			secondCardScore=true;
		}
		else if (c2==11 && helpAss==1) {
			cardScore[0] += 11;
			cardScore[1] += 1;
			helpAss++;
		}
		else if (c2==11 && helpAss>1) {
			cardScore[0] += 1;
			cardScore[1] += 1;
			helpAss++;
		}
		else {
			cardScore[0] += c2;
			cardScore[1] += c2;
		}
		cardtmp = c1;
		countCards++;

		
		// only for test
		if (helpAss>3) {
			//System.out.println("ERROR: IM DECK SIND MEHR ALS 4 ASSE");
		}
	}
	
	// return all scores
	public int[] getCardScore() {
		return cardScore;
	}
	
	public void resetHelpAss() {
		helpAss=0;
	}
	
	// return one high score (higher)
	public int getHighCardScore() {
		if (cardScore[0]>=cardScore[1]) {
			if (cardScore[0]<=21) { 
				return cardScore[0]; }
			else return cardScore[1];
		}		
		else 
			if (cardScore[1]>21)
				return cardScore[0];
			else return cardScore[1];
	}
	
	public int getCountCards() {
		return countCards;
	}
	
	public void setSecondCardScore(boolean score) {
		secondCardScore=score;
	}
	
	public boolean getSecondCardScore() {
		return secondCardScore;
	}

	public int getCurrentCard() {
		return cardtmp;
	}
	
	//getters & setters
	public boolean getInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}
	
	public void resetCountCards() {
		countCards=0;
		cardtmp=0;	
	}
}
