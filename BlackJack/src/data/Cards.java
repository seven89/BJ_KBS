package data;

import java.util.Random;

public class Cards {
	
	private int[][] CardDeck;
	private int[] CurrentCard;
	

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
	 * 	  0: TWO
	 *    1: THREE
	 *    ...
	 *    12: ACE
	 */
	private Cards() {
		CardDeck= new int[4][14];
		CurrentCard= new int[3];
		int i=0, j=0;	
		
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
	public int[] getCard() {

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
          case 0: score=2;
          case 1: score=3;
          case 2: score=4;
          case 3: score=5;
          case 4: score=6;
          case 5: score=7;
          case 6: score=8;
          case 7: score=9;
          case 12: score=11;
          default: score=10;
        }	
		return score;
	}	
	
	/**
	 * only for test
	 */
	public static void main(String [ ] args)
	{
		int i=0, j=0;
		Cards CardTest = new Cards();
		
		for (int c=0; c<52;c++) {
			CardTest.getCard();
		}
		
		for  (i=0; i<4; i++) {
			for (j=0; j<13; j++) {
				System.out.print(CardTest.CardDeck[i][j]+" ");
			}
		}	
	}
}