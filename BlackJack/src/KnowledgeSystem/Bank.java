package KnowledgeSystem;

import data.CardSet;



public class Bank extends Participant{
	
	private int price;
	
	public Bank ()
	{
		super();
	}
	
	public int[] giveCard(CardSet cardDeck)
	{
		/**
		 * the Bank distributes the cards from the cardset to all participants
		 */
		return cardDeck.getCard();
	}
	
	public int getPrice()
	{
		//cash out the price for the winning player
		return price;
	}
	public void takeMoney()
	{
		//TODO: nimmt das Geld, was von einem Spieler gesetzt wurde
	}
	
}