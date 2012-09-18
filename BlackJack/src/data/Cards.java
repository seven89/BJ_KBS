package data;

public class Cards {
	
	public int[][] CardDeck;

	
	

	// Konstruktor
	public void Cards(){
		CardDeck= new int[12][3];
		int i = 0;
		int j = 0;
		
		for  (i=0; i<12; i++) {
			CardDeck[i][j] = CardDeck[i+1][j];
			for (j=0; j<3; j++) {
				CardDeck[i][j] = CardDeck[i][j+1];
			}
		}	
	}
	
	// CardTypes	
	public enum CardTypes {
		TWO("0"), THREE("1"), FOUR("2"), FIVE("3"), SIX("4"), SEVEN ("5"), EIGHT("6"), NINE("7"), TEN("8"), JACK("9"), QUEEN("10"), KING("11"), ACE("12");
		
		//public final int valor = ordinal() + 2;
		public final String symbol;
		
		CardTypes(String sym) {
			symbol = sym;
		}
	}
	
	// CardIcons
	public enum CardIcons {
		HEART('H'), DIAMONDS('D'), SPADE('S'), CLUBS('C');
		
		public final char symbol;
		
		CardIcons(char c) {
			symbol = c;		
		}
	}
	
	//CardDeck
	private CardTypes cardType;
	private CardIcons cardIcon;
	
	public void SetCards () {
		
		
		
		
	}
}
