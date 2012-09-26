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
	protected Agent a;
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
		a = new Agent (500);
		a.calcBetValue(rules);
		
		p = new Player(50);		// create player(s)
		setPlayers(1);			// count player
		p.setCredit(100);				// give player 100 money
		p.setBet(bet);					// player set 10 money
		
		// Bank (first card)
		bank = new Bank();
				
		
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
	  
	  private static void newGame(){

			gc.clearArray();
			CardDeck=new CardSet(false);
			p.newGame();
			bank.newGame();
	  }
	  
	  private static void nextStep(){
		  int Game=0;
		  int step=1;
		  boolean newgame=true;
		  //Render loop
			System.out.println("GAME " + Game);
			while(p.getCredit()>0){
				//refresh time
				wait(1);	// waits for 1000 ms
				
				if(newgame){
					int[] initialcard = CardDeck.getRandCard();
					bank.setCard(initialcard);
					gc.newBankCard(initialcard[0]+1, initialcard[1]+1);
					newgame=false;
				}
				
				//reset turn + player handling
				if(step==2) {
					step=0;
						//players turn?
						if(pullCard(p.getCardScore())){  //TODO pullCard (implemented below to Agent a
							int[] card = CardDeck.getRandCard();
							p.setCard(card);
							gc.newPlayerCard(card[0]+1, card[1]+1);
							//System.out.println("Farbe " + (card[0]+1) + "Typ " + (card[1]+1) + "Score " + card[2]);
						}
						//now banks turn
						else{
							if(bank.getCardScore()<17 || (bank.getCardScore()<p.getCardScore() && p.getCardScore()<22)){
								int[] card = CardDeck.getRandCard();
								bank.setCard(card);
								gc.newBankCard(card[0]+1, card[1]+1);
							}
							//new game
							else{
								printResult();
								newGame();
								newgame=true;
								p.gamesPlayed++;
								Game++;
								System.out.println("GAME " + Game);
							}
							
						}
					
				}
				step++;
				gc.repaint();
			}  
			System.out.println("You have lost! ;( ");
			System.out.println("You won "+ p.gamesWon +"/"+ p.gamesPlayed + " " + (((float)p.gamesWon/(float)p.gamesPlayed)*100.0) + "% of games!");
			System.out.println("You lost "+ p.gamesLost +"/"+ p.gamesPlayed + " " + (((float)p.gamesLost/(float)p.gamesPlayed)*100.0) + "% of games!");
			System.out.println("You had a draw in "+ p.gamesDraw +"/"+ p.gamesPlayed + " " + (((float)p.gamesDraw/(float)p.gamesPlayed)*100.0) + "% of games!");
			gc.setVisible(false);
			gc.dispose();
			System.exit(0);
	  }
	  
	  public static Boolean pullCard(int currentPlayerValue){
		  if(currentPlayerValue<16) return true;
		  return false;
	  }
	  
	  private static void printResult(){
		  System.out.println("-----------------------------");
			
			if(p.getCardScore()<21) {
				System.out.println("Player under 21");
			}
			else if (p.getCardScore()==21) {
				System.out.println("BlackJack! (player)");
				p.setBet(bet+bet*1.5);
			}
			
			
			if(p.getCardScore()>21){
				System.out.println("Player lose!");
				p.setInGame(false);
				p.setCredit(-bet);
				p.gamesLost++;
			}
			else if (bank.getCardScore()>21) {
					System.out.println("Player win!");
					p.setCredit(bet);
					p.gamesWon++;
			}
			else if (bank.getCardScore() > p.getCardScore()) {
						System.out.println("Bank win!");
						p.setCredit(-bet);
						p.gamesLost++;
			}
			else if (bank.getCardScore() == p.getCardScore() && p.getCardScore()!=21) {
						System.out.println("drawn");
						p.setCredit(0);
						p.gamesDraw++;
			}
				System.out.println("Player Score: "+p.getCardScore());
				System.out.println("Bank Score: "+bank.getCardScore());
				System.out.println("Money: "+p.getCredit());
	  }
		
}