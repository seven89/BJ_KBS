package data;

import java.util.Random;

public class CardSet {
	
	public int[][] CardDeck;
	private int[] CurrentCard;
	private int lengthSym;
	private int lengthVal;
	

	/**
	 * constructor
	 * @brief create new CardDeck
	 * 
	 * CardDeck[i][j]: 
	 * i:
	 * 	  1: HEART
	 * 	  2: DIAMOND
	 *    3: CLUB
	 *    4: SPADE
	 * j:
	 * 	  0: ASS
	 *    1: TWO
	 *    ...
	 *    12: KING
	 *    
	 * @param refresh: set if cards should refresh (false) (needed for new Game)
	 */
	public CardSet(boolean refresh) {
		
		int i=0, j=0;
		setLengthSym(3);
		setLengthVal(12);
		
		if (refresh==false) {
		setCardDeck(new int[4][14]);
		CurrentCard= new int[3];
		}
		
		// new Card Deck (52 Cards)
		
		for  (i=0; i<4; i++) {
			CardDeck[i][j] = i;
			for (j=0; j<13; j++) {
				CardDeck[i][j] = 1;			// 1=card in deck, 0= card not in deck
			}
		}	
	}
	
	/**
	 * getCard
	 * @brief get a Card from the deck
	 */
	public int[] getRandCard() {

		Random random = new Random();
		int l, r;
		
		// get random card and check if this card is in the deck (loop)
		do {
			l = random.nextInt(4);
			r = random.nextInt(13);
		} while (CardDeck[l][r]==0);

			CurrentCard[0]=l;
			CurrentCard[1]=r;
			CurrentCard[2]= getCardScore(r);
			CardDeck[l][r]= 0;				// set 0, (card not more in the deck) 

		return CurrentCard;
	}
	
	/**
	 * getCardScore
	 * @brief give the score of the card 
	 */
	private int getCardScore(int score) {
        switch (score) {
        	case 0: score=11; break;
        		//if(highAss) { score=11; }
   		 			//else{ score=1;} break;
            case 1:  score=2; break;
            case 2:  score=3; break;
            case 3:  score=4; break;
            case 4:  score=5; break;
            case 5:  score=6; break;
            case 6:  score=7; break;
            case 7:  score=8; break;
            case 8:  score=9; break;
            case 9: score=10; break;
            case 10: score=10; break;
            case 11: score=10; break;
            case 12: score=10; break;
            
        }     
		return score;
	}
	
	// getter & setter
	public int[][] getCardDeck() {
		return CardDeck;
	}
	
	public void setCardDeck(int[][] cardDeck) {
		CardDeck = cardDeck;
	}
	public int getLengthSym() {
		return lengthSym;
	}

	public void setLengthSym(int lengthSym) {
		this.lengthSym = lengthSym;
	}

	public int getLengthVal() {
		return lengthVal;
	}

	public void setLengthVal(int lengthVal) {
		this.lengthVal = lengthVal;
	}
}