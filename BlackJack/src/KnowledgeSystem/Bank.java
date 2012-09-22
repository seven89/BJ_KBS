package KnowledgeSystem;

import data.CardSet;

/**
 * 
 * @author Boris Beck & Fabian Schäfer
 * @brief the bank (dealer) gives the cards to the player and to itself; 
 * it manages wins and loses for the players; 
 * it plays also in order to get BlackJack.
 * @param price stores the to be given price to a winning player
 * @param valueBorder marks the border of taking cards (the bank only 
 * take a new card form deck iff the current value of cards is under 17)
 *
 */


public class Bank extends Participant{
	
	private int price;
	private int valueBorder;
	
	public Bank ()
	{
		super();
		this.setValueBorder(17);
	}
	
	public int[] giveCard(CardSet cardDeck)
	{
		/**
		 * the Bank distributes the cards from the cardset to all participants
		 */
		return cardDeck.getRandCard();
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

	public int getValueBorder() {
		return valueBorder;
	}

	public void setValueBorder(int valueBorder) {
		this.valueBorder = valueBorder;
	}
	
}