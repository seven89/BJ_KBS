package data;

public class Cards {
	
	// CardTypes	
	public enum CardTypes {
		TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN ("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("11"), QUEEN("12"), KING("13"), ACE("14");
		
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
	private String[][] CardDeck;
	
	public void SetCards () {
		
		
		
		
	}
}
