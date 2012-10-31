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
	protected static boolean modus;
	protected static Agent agentSplit;
	
	public BlackJack() {
		startMoney=100;
		gc = new GraphicsController(); //create gui
		gc.setVisible(false);
		
		bet=10;
		modus = false;
		
		CardDeck = new CardSet(false);	// create card deck
		Rules rules = new Rules(7, 5);	
		agent = new Agent (200);
		agent.calcBetValue(rules);
		
		p = new Player(50);		// create player(s)
		setPlayers(1);			// count player
		agent.setCredit(200);				// give player 100 money
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
			agentSplit=null;
			modus=false;
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
			System.out.println("GAME " + Game);
			while(agent.getCredit()>0){
				//refresh time
				wait(1);	// waits for 1000 ms
				if(!gc.pause){
					
				if(newgame){
					int[] initialcard = CardDeck.getRandCard();
					agent.updateProbability(initialcard);
					bank.setCard(initialcard);
					gc.newBankCard(initialcard[0], initialcard[1]);
					newgame=false;
					
					// Insurance (versichern, spielmodus)
					if (bank.getCards()==1 && bank.getCardScore()[1]==11) {
						System.out.println("Versichern? (Spielmodus)");
						agent.insure();
					}
				}
				
				//reset turn + player handling
				if(step==2) {
					step=0;
						//players turn?
						if(agent.pullCard(bank.getCardScore())){  
							int[] card = CardDeck.getRandCard();
							//p.setCard(card);

//							// splitting (teilen, spielmodus)
//							if (agent.getCards()==1 && agent.getCurrentCard()==card[2] && agentSplit==null && modus==false) {
//								System.out.println("Teilen? (spielmodus)");
//								agentSplit = agent;
//								agentSplit.setCard(card);
//								modus=true;
//							}
//							else {
//								agent.setCard(card);
//							}
							agent.setCard(card);
						
							// doubling down (doppeln, spielmodus)
							if (agent.getCards()==2 && (agent.getCardScore()[0]>=9 && agent.getCardScore()[0]<=11) || (agent.getCardScore()[1]>=9 && agent.getCardScore()[1]<=11) && modus==false) {
								System.out.println("Doppeln? (Spielmodus)");
								agent.doubling();
								card = CardDeck.getRandCard();
								agent.setCard(card);
								agent.setDoubled(true);
								modus=true;
							}
							
							gc.newPlayerCard(card[0], card[1]);
							agent.updateProbability(card);
							//System.out.println("Farbe " + (card[0]+1) + "Typ " + (card[1]+1) + "Score " + card[2]);
						}
						
						// for AgentSplit
//						else if (agentSplit!=null) {
//							if(agentSplit.pullCard(bank.getCardScore())) {  
//								int[] card = CardDeck.getRandCard();
//								// TODO Marvin AgenSplitt zeichnen (Splitt=1))
//								gc.newPlayerCard(card[0], card[1]);
//								agentSplit.updateProbability(card);
//							}
//						}
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
								agent.gamesPlayed++;
								Game++;
								System.out.println("GAME " + Game);
							}
							
						}
						gc.repaint();
					}
				step++;
				}
			}
			
			System.out.println("You have lost! ;( ");
			System.out.println("You won "+ agent.gamesWon +"/"+ agent.gamesPlayed + " " + (((float)agent.gamesWon/(float)agent.gamesPlayed)*100.0) + "% of games!");
			System.out.println("You lost "+ agent.gamesLost +"/"+ agent.gamesPlayed + " " + (((float)agent.gamesLost/(float)agent.gamesPlayed)*100.0) + "% of games!");
			System.out.println("You had a draw in "+ p.gamesDraw +"/"+ agent.gamesPlayed + " " + (((float)agent.gamesDraw/(float)agent.gamesPlayed)*100.0) + "% of games!");
			gc.setVisible(false);
			gc.dispose();
			System.exit(0);
	  }
	  
	  
	  private static void printResult(){
		  System.out.println("-----------------------------");
			
			if(agent.getCardScore()[0]<21 || agent.getCardScore()[1]<21) {
				//System.out.println("Player under 21");
			}
			else if (agent.getCardScore()[0]==21 || agent.getCardScore()[1]==21) {
				//System.out.println("BlackJack! (player)");
				agent.setCredit(bet+bet*1.5);
			}
			
			
			if(agent.getCardScore()[0]>21 || agent.getCardScore()[1]>21){
				//System.out.println("Player lose!");
				agent.setInGame(false);
				agent.setCredit(-bet);
				agent.gamesLost++;
			}
			else if (bank.getCardScore()[0]>21 || bank.getCardScore()[1]>21) {
					//System.out.println("Player win!");
					agent.setCredit(bet*2);
					agent.gamesWon++;
			}
			else if (bank.getCardScore()[0] > agent.getCardScore()[0] || bank.getCardScore()[1] > p.getCardScore()[1]) {
						//System.out.println("Bank win!");
						agent.setCredit(-bet);
						agent.gamesLost++;
			}
			else if ((bank.getCardScore()[0] == agent.getCardScore()[0] || bank.getCardScore()[0] == agent.getCardScore()[0]) && (agent.getCardScore()[0]!=21 || agent.getCardScore()[0]!=21)) {
//						System.out.println("drawn");
						agent.setCredit(0);
						agent.gamesDraw++;
			}
			else{
				agent.setCredit(bet);
				agent.gamesWon++;
			}
				System.out.println("Money: "+agent.getCredit());
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