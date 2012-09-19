package data;

public class Cards {
	
	public int[][] CardDeck;
	
	//constructor
	public Cards() {
		CardDeck= new int[5][16];
	}
	
	public void newCardDeck() {									// 52 Cards
		for  (int i=1; i<4; i++) {
			for (int j=2; j<15; j++) {
				CardDeck[i][j] = CardDeck[i][1];				// 1=Karte im Stapel, 0= Karte nicht mehr im Stapel
				System.out.println("CARD: " + CardDeck[i][3]);
			}
		}	
	}
	
	//getCard
	public int[][] getCard() {
		//Random random = new Random();
		return CardDeck;
		
	}
	
	// shuffle CardDeck
	public int[][]shuffleCardDeck() {
		return CardDeck;
		
	}
	
	
	// only for test
	public static void main(String [ ] args)
	{
		Cards CardTest = new Cards();
		CardTest.newCardDeck();
	}
}
