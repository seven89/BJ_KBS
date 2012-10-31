package controller;

import graphics.GraphicsController;
import data.CardSet;
import data.Rules;
import KnowledgeSystem.Agent;
import KnowledgeSystem.Bank;
import KnowledgeSystem.Player;

public class BlackJack {

	int Players;
	int startMoney;
	protected static GraphicsController gc;
	protected static CardSet CardDeck;
	protected static Agent agent;
	protected static Player p;
	protected static Bank bank;
	static int bet;
	
	public BlackJack() {
		startMoney=100;
		gc = new GraphicsController(); //create gui
		gc.setVisible(true);
		
		bet=10;
		
		CardDeck = new CardSet(false);	// create card deck
		Rules rules = new Rules(7, 5);	
		agent = new Agent (5000);
		agent.calcBetValue(rules);
		
		p = new Player(50);		// create player(s)
		setPlayers(1);			// count player
		p.setCredit(5000);				// give player 100 money
		p.setBet(bet);					// player set 10 money
		
		// Bank (first card)
		bank = new Bank();
				
		
	}
	

	public static void main(String [ ] args)
	{
		BlackJack table = new BlackJack();		//create table
		nextStep();
		
		/*if (bank.getCardScore()==10) {
			System.out.println("Versichern? (Spielmodus)");
		}*/
	}
	
	  public static void wait (int timeToWait){
	        long t0,t1;
	        t0 = System.currentTimeMillis();
	        do{
	            t1 = System.currentTimeMillis();
	        }
	        while(t1 - t0 < timeToWait);
	}
	  
	public int[][] getCardDeck() {
		return CardDeck.CardDeck;
	}
	  
	  private static void newGame(){

			gc.clearArray();
			CardDeck=new CardSet(false);
			//p.newGame();
			agent.newGame();
			bank.newGame();
			agent.initializeProbability();
			System.out.println("------------New Game-----------");
	  }
	  
	  private static void nextStep(){
		  int Game=0;
		  int step=1;
		  boolean newgame=true;
		  //Render loop
			//System.out.println("GAME " + Game);
			while(p.getCredit()>0){
				//refresh time
				wait(1000);	// waits for 1000 ms
				if(!gc.pause){
					
				if(newgame){
					int[] initialcard = CardDeck.getRandCard();
					agent.updateProbability(initialcard);
					bank.setCard(initialcard);
					gc.newBankCard(initialcard[0], initialcard[1]);
					newgame=false;
				}
				
				//reset turn + player handling
				if(step==2) {
					step=0;
						//players turn?
						if(agent.pullCard(bank.getCardScore())){  
							int[] card = CardDeck.getRandCard();
							//p.setCard(card);
							agent.setCard(card);
							
							
							// Insurance (versichern, spielmodus)
							if (bank.getCards()==1 && bank.getCardScore()[1]==11) {
								System.out.println("Versichern? (Spielmodus)");
							}
							
							// doubling down (doppeln, spielmodus)
							if (agent.getCards()==2 && (agent.getCardScore()[0]>=9 && agent.getCardScore()[0]<=11) || (agent.getCardScore()[1]>=9 && agent.getCardScore()[1]<=11)) {
								System.out.println("Doppeln? (Spielmodus)");
							}
							
							// splitting (teilen, spielmodus)
							//if (agent.getCards()==2 && )
							
							gc.newPlayerCard(card[0], card[1]);
							agent.updateProbability(card);
							//System.out.println("Farbe " + (card[0]+1) + "Typ " + (card[1]+1) + "Score " + card[2]);
						}
						//now banks turn
						else{
							if(bank.getCardScore()[0]<17 && bank.getCardScore()[1]<17 && (p.getCardScore()[0]<21) && (p.getCardScore()[1]<21)){
								int[] card = CardDeck.getRandCard();
								bank.setCard(card);
								gc.newBankCard(card[0], card[1]);
								agent.updateProbability(card);
							}
							//new game
							else{
								printResult();
								newGame();
								newgame=true;
								p.gamesPlayed++;
								Game++;
								//System.out.println("GAME " + Game);
							}
							
						}
						gc.repaint();
				}
				step++;
				
			}
			}
			System.out.println("You have lost! ;( ");
			System.out.println("You won "+ p.gamesWon +"/"+ p.gamesPlayed + " " + (((float)p.gamesWon/(float)p.gamesPlayed)*100.0) + "% of games!");
			System.out.println("You lost "+ p.gamesLost +"/"+ p.gamesPlayed + " " + (((float)p.gamesLost/(float)p.gamesPlayed)*100.0) + "% of games!");
			System.out.println("You had a draw in "+ p.gamesDraw +"/"+ p.gamesPlayed + " " + (((float)p.gamesDraw/(float)p.gamesPlayed)*100.0) + "% of games!");
			gc.setVisible(false);
			gc.dispose();
			System.exit(0);
	  }
	  
	  
	  private static void printResult(){
//		  System.out.println("-----------------------------");
			
			if(p.getCardScore()[0]<21 || p.getCardScore()[1]<21) {
				//System.out.println("Player under 21");
			}
			else if (p.getCardScore()[0]==21 || p.getCardScore()[1]==21) {
				//System.out.println("BlackJack! (player)");
				p.setCredit(bet+bet*1.5);
			}
			
			
			if(p.getCardScore()[0]>21 || p.getCardScore()[1]>21){
				//System.out.println("Player lose!");
				p.setInGame(false);
				p.setCredit(-bet);
				p.gamesLost++;
			}
			else if (bank.getCardScore()[0]>21 || bank.getCardScore()[1]>21) {
					//System.out.println("Player win!");
					p.setCredit(bet*2);
					p.gamesWon++;
			}
			else if (bank.getCardScore()[0] > p.getCardScore()[0] || bank.getCardScore()[1] > p.getCardScore()[1]) {
						//System.out.println("Bank win!");
						p.setCredit(-bet);
						p.gamesLost++;
			}
			else if ((bank.getCardScore()[0] == p.getCardScore()[0] || bank.getCardScore()[0] == p.getCardScore()[0]) && (p.getCardScore()[0]!=21 || p.getCardScore()[0]!=21)) {
//						System.out.println("drawn");
						p.setCredit(0);
						p.gamesDraw++;
			}
			else{
				p.setCredit(bet);
				p.gamesWon++;
			}
//				System.out.println("Player Score: "+p.getCardScore());
//				System.out.println("Bank Score: "+bank.getCardScore());
//				System.out.println("Money: "+p.getCredit());
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
		
}