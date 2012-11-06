package controller;

import graphics.GraphicsController;
import data.CardSet;
import data.Rules;
import KnowledgeSystem.Agent;
import KnowledgeSystem.Bank;
import KnowledgeSystem.Player;

public class BlackJack {

	static int Players, Game, bet, debug;
	int startMoney;
	protected static GraphicsController gc;
	protected static CardSet CardDeck;
	protected static Agent agent, agentSplit;
	protected static Player p;
	protected static Rules rules;
	protected static Bank bank;
	static boolean agentHelp, agentSplitHelp, agentSplitCardHelp, modus;
	
	public BlackJack() {
		debug =1; // 1 bedeutet, dass alle syso ausgegeben werden
		//startMoney=100;
		gc = new GraphicsController(); //create gui
		gc.setVisible(false);
		agentHelp=true;
		agentSplitHelp=false;
		agentSplitCardHelp=true;
		Game=1;
		
		//bet=10;
		modus = false;
		
		CardDeck = new CardSet(false);	// create card deck
		rules = new Rules(7, 5);	
		agent = new Agent (100);
		bet = (int) agent.calcBetValue(rules);
		
		//p = new Player(50);		// create player(s)
		//setPlayers(1);			// count player
		//agent.setCredit(200);				// give player 100 money
		//p.setBet(bet);					// player set 10 money
		
		// Bank (first card)
		bank = new Bank();				
		
	}
	

	public static void main(String [ ] args)
	{
		BlackJack table = new BlackJack();		//create table
		nextStep();		
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
			agentHelp=true;
			agentSplitHelp=false;
			agentSplitCardHelp=true;
			agent.resetHelpAss();
			bank.resetHelpAss();
			if (agentSplit!=null) {
				agentSplit.resetHelpAss();
			}
			//p.newGame();
			agent.newGame();
			bank.newGame();
			bet = (int) agent.calcBetValue(rules);
			agent.setBet(bet);
			agent.initializeProbability();
//			System.out.println("------------New Game-----------");
	  }
	  
	  private static void nextStep(){
		  //int Game=0;
		  int step=1;
		  boolean newgame=true;
		  //Render loop
			//System.out.println("GAME " + Game);
			while(agent.getCredit()>0 && Game<2){
			
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
//					if (bank.getCards()==1 && bank.getCardScore()[1]==11) {
//						System.out.println("Versichern? (Spielmodus)");
//						agent.insure();
//					}
				}
				
				//reset turn + player handling
				if(step==2) {
					step=0;
					//players turn?
					if (agentHelp || agentSplitHelp) {
						if(agent.pullCard(bank.getCardScore())){
							int[] card = CardDeck.getRandCard();
							//p.setCard(card);

							// splitting (teilen, spielmodus)
							System.out.println("Card Step B           "+card[1]+" "+agent.getCurrentCard());
//							if (agent.getCards()==1 && agent.getCurrentCard()==card[1] && agentSplit==null && modus==false) {
//								System.out.println("Teilen? (spielmodus)");
//								agentSplit = agent;
//								agentSplit.setCard(card);
//								agentSplitHelp=true;
//								modus=true;
//							}
//							else {
//								agent.setCard(card);
//							}
							agent.setCard(card);
							System.out.println("Card Step A           "+card[1]+" "+agent.getCurrentCard());
						
//							// doubling down (doppeln, spielmodus)
//							if (agent.getCards()==2 && (agent.getCardScore()[0]>=9 && agent.getCardScore()[0]<=11) || (agent.getCardScore()[1]>=9 && agent.getCardScore()[1]<=11) && modus==false) {
//								System.out.println("Doppeln? (Spielmodus)");
//								agent.doubling();
//								card = CardDeck.getRandCard();
//								agent.setCard(card);
//								agent.setDoubled(true);
//								modus=true;
//							}
							
							gc.newPlayerCard(card[0], card[1]);
							agent.updateProbability(card);
//							System.out.println("Farbe " + (card[0]+1) + "Typ " + (card[1]+1) + "Score " + card[2]);
						} 
						else {
							agentHelp=false;
						}
						// for AgentSplit
//						if (agentSplit!=null) {
//							if(agentSplit.pullCard(bank.getCardScore())) {  
//								int[] card = CardDeck.getRandCard();
//								// TODO Marvin AgentSplitt zeichnen
//								gc.newPlayerCard(card[0], card[1]);
//								agentSplit.updateProbability(card);
//								
//								if (!(agentSplit.getHighCardScore()<21)) {
//									agentSplitCardHelp=false;
//								}
//								agentSplitHelp=true;
//							}
//							else {
//								agentSplitHelp=false;
//							}
//						}
					}
					//now banks turn
					else {
						if(bank.getCardScore()[0]<17 || bank.getCardScore()[1]<17 && (agent.getHighCardScore()<21) && agentSplitCardHelp){
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
							//System.out.println("GAME " + Game);
						}	
					}
					gc.repaint();
					}
				step++;
				}
			}
			System.out.println("You won "+ agent.gamesWon +"/"+ agent.gamesPlayed + " " + (((float)agent.gamesWon/(float)agent.gamesPlayed)*100.0) + "% of games!");
			System.out.println("You lost "+ agent.gamesLost +"/"+ agent.gamesPlayed + " " + (((float)agent.gamesLost/(float)agent.gamesPlayed)*100.0) + "% of games!");
			System.out.println("You had a draw in "+ agent.gamesDraw +"/"+ agent.gamesPlayed + " " + (((float)agent.gamesDraw/(float)agent.gamesPlayed)*100.0) + "% of games!");
			gc.setVisible(false);
			gc.dispose();
			System.exit(0);
	  }
	  
	  
	  private static void printResult(){
		  
		  if (debug==1) {
			  System.out.println("GAME " + Game);
			  System.out.println("Bet    "+bet);
			  System.out.println("Bank   Cards "+bank.getCards());
			  System.out.println("Agent  Cards "+agent.getCards());
			  if (agentSplit!=null) {
				  System.out.println("AgentS Cards "+agentSplit.getCards());
			  }
			  System.out.println("Bank    Score "+ bank.getHighCardScore() + "    Einzeln: " + bank.getCardScore()[0] +" "+ bank.getCardScore()[1]);
			  System.out.println("Player  Score "+ agent.getHighCardScore() + "    Einzeln: " + agent.getCardScore()[0] +" "+ agent.getCardScore()[1]);
			  if (agentSplit!=null) {
				  System.out.println("PlayerS Score "+ agentSplit.getHighCardScore() + "    Einzeln: " + agentSplit.getCardScore()[0] +" "+ agentSplit.getCardScore()[1]);
			  }
			  System.out.println("Money  Before : "+agent.getCredit());
		  }
		


		// TODO agentsplitt bei der auszahlung einbinden
			
			if (agent.getHighCardScore()==21 && agent.getCards()==2) {
				System.out.println("> > > > > > > > > > BlackJack! < < < < < < < < < <");
				agent.setCredit(bet+bet*1.5);
			}
			else if(agent.getHighCardScore()>21){
				agent.setInGame(false);
				agent.setCredit(-bet);
				agent.gamesLost++;
			}
			else if (bank.getHighCardScore()>21) {
					//System.out.println("Player win!");
					agent.setCredit(bet*2);
					agent.gamesWon++;
			}
			else if (bank.getHighCardScore()>agent.getHighCardScore()) {
						//System.out.println("Bank win!");
						agent.setCredit(-bet);
						agent.gamesLost++;
			}
			else if (bank.getHighCardScore() == agent.getHighCardScore()) {
						System.out.println("drawn");
						//agent.setCredit(0);
						agent.gamesDraw++;
			}
			// else: bank < player < 21
			else{
				agent.setCredit(bet);
				agent.gamesWon++;
			}
			
			if (debug==1) {
				System.out.println("Money  After  : "+agent.getCredit());
				System.out.println("---------------------------------");
			}				
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