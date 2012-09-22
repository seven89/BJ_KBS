package controller;

import data.CardSet;
import KnowledgeSystem.Bank;
import KnowledgeSystem.Player;

public class BlackJack {

	int Players;
	int startMoney;
	
	public BlackJack() {
		startMoney=100;
	}
	
	// getter & setter
	public int getPlayers() {
		return Players;
	}
	
	public void setPlayers(int p) {
		Players = p;
	}
	/**
	 * only for test
	 */
	public static void main(String [ ] args)
	{
		int i=0, j=0;
		int bet=10;
		
		BlackJack table = new BlackJack();		//create table
		CardSet CardDeck = new CardSet();	// create card deck
		Player p = new Player(50);		// create player(s)
		table.setPlayers(1);			// count player
		p.setCredit(100);				// give player 100 money
		p.setBet(bet);					// player set 10 money
		
		// Bank (first card)
		Bank bank = new Bank();
		bank.setCard(CardDeck.getRandCard());
		
		if (bank.getCardScore()==10) {
			System.out.println("Versichern? (Spielmodus)");
		}
		
		// give player a card
		p.setCard(CardDeck.getRandCard());
		p.getCardScore();
		System.out.println("Score (one card): "+p.getCardScore());
		
		// give player another card
		p.setCard(CardDeck.getRandCard());
		p.getCardScore();
		System.out.println("Score (two cards): "+p.getCardScore());
		
		p.setCard(CardDeck.getRandCard());
		p.getCardScore();
		System.out.println("Score (three cards): "+p.getCardScore());
		System.out.println("-----------------------------");
		
		if(p.getCardScore()<21) {
			System.out.println("Player: under 21");
		}
		else if (p.getCardScore()==21) {
			System.out.println("Player: BlackJack!");
			p.setBet(bet*2.5);
		}
		else {
			System.out.println("Player: you Lose!");
			p.setInGame(false);
		}
		
		if (p.getInGame()==false) {
			System.out.println("Bank win");
		}
		else {
			bank.setCard(CardDeck.getRandCard());
			
			while(bank.getCardScore()<17) {
				bank.setCard(CardDeck.getRandCard());
			}
			
			if (bank.getCardScore()>21) {
				System.out.println("You win!");
				p.setCredit(bet*2);
				
			}
			else {
				if (bank.getCardScore() > p.getCardScore()) {
					System.out.println("Bank win!");
				}
				else if (bank.getCardScore() == p.getCardScore() && p.getCardScore()!=21) {
					System.out.println("drawn");
					p.setCredit(bet);
				}
				else {
					System.out.println("You win!");
					p.setCredit(bet*2);
				}
			}
			System.out.println("Bank Score: "+bank.getCardScore());
		}
		System.out.println("Money: "+p.getCredit());
		
		
		
		
		
		
//		// get card
//		for (int c=0; c<52;c++) {
//			CardTest.getCard();
//		}
		
		// show Carddeck
		System.out.println("----------CardDeck ----------");
		for  (i=0; i<4; i++) {
			for (j=0; j<13; j++) {
				System.out.print(CardDeck.getCardDeck()[i][j]+" ");
			}
		}
	}
}