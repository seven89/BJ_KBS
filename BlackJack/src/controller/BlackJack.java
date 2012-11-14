package controller;

import graphics.GraphicsController;
import data.CardSet;
import KnowledgeSystem.Agent;
import KnowledgeSystem.Bank;
import KnowledgeSystem.Player;

public class BlackJack {

	static int Players, Game, bet, debug, creditTmp, betResult;
	int startMoney;
	protected static GraphicsController gc;
	protected static CardSet CardDeck;
	protected static Agent agent, agentSplit;
	protected static Player p;
	protected static Bank bank;
	static boolean agentHelp, agentSplitHelp, agentSplitCardHelp, modus, insurance;
	private static int games=100;
	private static int speed=100;
	
	public BlackJack(int games, int speed) {
		debug =1; // 1 bedeutet, dass alle syso ausgegeben werden
		//startMoney=100;
		gc = new GraphicsController(); //create gui
		gc.setVisible(true);
		agentHelp=true;
		agentSplitHelp=false;
		agentSplitCardHelp=true;   
		Game=1;
		
		//bet=10;
		modus = false;
		insurance=false;
		
		CardDeck = new CardSet(false);	// create card deck	
		agent = new Agent (500);
//		bet = (int) agent.calcBetValue(50);
		
		//p = new Player(50);		// create player(s)
		//setPlayers(1);			// count player
		//agent.setCredit(200);				// give player 100 money
		//p.setBet(bet);					// player set 10 money
		
		// Bank (first card)
		bank = new Bank();
		this.speed=speed;
		this.games=games;
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
			insurance=false;
			agent.resetHelpAss();
			bank.resetHelpAss();
			if (agentSplit!=null) {
				agentSplit.resetHelpAss();
			}
			
			//p.newGame();
			agent.newGame();
			bank.newGame();
			bet = (int) agent.calcBetValue(50);
			agent.setBet(bet);
			agent.initializeProbability();
			Game++;
			agent.gamesPlayed++;
//			System.out.println("------------New Game-----------");
	  }
	  
	  public static void nextStep(){
		  //int Game=0;
		  int step=1;
		  boolean newgame=true;
		  //Render loop
			//System.out.println("GAME " + Game);
			while(agent.getCredit()>0 && Game<=games && agent.getCredit()>10){
			
				//refresh time
				wait(speed);	// waits for 1000 ms
				if(!gc.pause){
					
				if(newgame){
					int[] initialcard = CardDeck.getRandCard();
					agent.updateProbability(initialcard);
					bank.setCard(initialcard[0], initialcard[1], initialcard[2]);
					gc.newBankCard(initialcard[0], initialcard[1]);
					gc.printBank("Bank: "+bank.getHighCardScore());
					newgame=false;
				}
				
				//reset turn + player handling
				if(step==2) {
					step=0;
					//players turn?
					if ((agentHelp && agent.getHighCardScore()<22) || (!(agentSplitCardHelp) && agentSplit.getHighCardScore()<22)) {
						if(agent.pullCard(bank.getCardScore())){
							int[] card = CardDeck.getRandCard();
							//p.setCard(card);
							
							// Insurance (versichern, spielmodus)
							if (bank.getCountCards()==1 && bank.getCardScore()[1]==11) {
								System.out.println("oooooooooooooooooooooooooooooooooooo Versichern? (Spielmodus)");
								
								if (agent.insureBoolean(card[2])) {
									agent.insure();
									insurance=true;
								}
							}
							
							// splitting (teilen, spielmodus)
//							System.out.println("Card Step B           "+card[1]+" "+agent.getCurrentCard());
							if (agent.getCountCards()==1 && agent.getCurrentCard()==card[1] && agentSplit==null && modus==false) {
//								System.out.println("Teilen? (spielmodus)");
								agentSplit=agent;
								agentSplit.setCard(card[0], card[1], card[2]);
								agentSplitHelp=true;
								modus=true;
							}
							else {
								agent.setCard(card[0], card[1], card[2]);
							}
							
							// doubling down (doppeln, spielmodus)
							if (agent.getCountCards()==2 && (agent.getHighCardScore()>8 && agent.getHighCardScore()<11) && modus==false) {
								System.out.println("oooooooooooooooooooooooooooooooooooo Doppeln? (Spielmodus)");		
								
								if (agent.doublingBoolean(agent.getHighCardScore(),bank.getHighCardScore())) {
									agent.doubling();
									card = CardDeck.getRandCard();
									agent.setCard(card[0], card[1], card[2]);
									modus=true;
								}
							}
							
//							System.out.println("Card Step A           "+card[1]+" "+agent.getCurrentCard());

							
							gc.newPlayerCard(card[0], card[1], agent.probability, 0);
							agent.updateProbability(card);
//							System.out.println("Farbe " + (card[0]+1) + "Typ " + (card[1]+1) + "Score " + card[2]);
							gc.printDecision("Draw card!" );
							gc.printPlayer("Score: " +agent.getHighCardScore());
						} 
						else {
							agentHelp=false;
						}
						// for AgentSplit
						if (agentSplit!=null) {
							if(agentSplit.pullCard(bank.getCardScore())) {  
								int[] card = CardDeck.getRandCard();
								// TODO Marvin AgentSplitt zeichnen
								gc.newPlayerCard(card[0], card[1], agent.probability, 1);
								agentSplit.updateProbability(card);
								
								if (!(agentSplit.getHighCardScore()<21)) {
									agentSplitCardHelp=false;
								}
								agentSplitHelp=true;
							}
							else {
								agentSplitHelp=false;
							}
						}
					}
					//now banks turn
					else {
						if(bank.getHighCardScore()<17 && (agent.getHighCardScore()<21) && agentSplitCardHelp){
							int[] card = CardDeck.getRandCard();
							bank.setCard(card[0], card[1], card[2]);
							gc.newBankCard(card[0], card[1]);
							agent.updateProbability(card);
							gc.printDecision("Bank draws card!" );
							gc.printBank("Bank: "+bank.getHighCardScore());
						}
						//new game
						else{
							creditTmp=agent.getCredit();
							printResult();
							betResult=agent.getCredit()-creditTmp;
//							printStatDecision(); // erstmal weglasen
//							printDecision();
							
							// 0 =Links, 1= rechts
							
							
							String sGame = ("Aktuelles Spiel: "+Game);
							String sKonto = ("Kontostand: "+agent.getCredit());
							String sBet = ("Einsatz: "+agent.getBet());
							String sBetResult = ("Bet Result: "+betResult);
							String sWon = ("Wins: "+ agent.gamesWon +"/"+ (agent.gamesPlayed+1) + " " + Math.round( (((float)agent.gamesWon/(float)(agent.gamesPlayed+1))*100.0)) + "%");
							String sLost = ("Lost: "+ agent.gamesLost +"/"+ (agent.gamesPlayed+1) + " " + Math.round( (((float)agent.gamesLost/(float)(agent.gamesPlayed+1))*100.0)) + "%");
							String sDrawn = ("Draw: "+ agent.gamesDraw +"/"+ (agent.gamesPlayed+1) + " " + Math.round((((float)agent.gamesDraw/(float)(agent.gamesPlayed+1))*100.0)) + "%");
							
							gc.printStatistic(sGame, sKonto, sBet, sBetResult, sWon, sLost, sDrawn);
							
							newGame();
							newgame=true;
							//System.out.println("GAME " + Game);
						}
					}
					
					}
				gc.repaint();
				step++;
				}
			}
			System.out.println("You won "+ agent.gamesWon +"/"+ agent.gamesPlayed + " " + (((float)agent.gamesWon/(float)agent.gamesPlayed)*100.0) + "% of games!");
			System.out.println("You lost "+ agent.gamesLost +"/"+ agent.gamesPlayed + " " + (((float)agent.gamesLost/(float)agent.gamesPlayed)*100.0) + "% of games!");
			System.out.println("You had a draw in "+ agent.gamesDraw +"/"+ agent.gamesPlayed + " " + (((float)agent.gamesDraw/(float)agent.gamesPlayed)*100.0) + "% of games!");
			//gc.setVisible(false);
			//gc.dispose();
			//System.exit(0);
	  }
	  
	  private static void printResult(){
		  
		  if (debug==1) {
			  System.out.println("GAME " + Game);
			  System.out.println("Bet    "+bet);
			  System.out.println("Bank   Cards "+bank.getCountCards());
			  System.out.println("Agent  Cards "+agent.getCountCards());
			  if (agentSplit!=null) {
				  System.out.println("AgentS Cards "+agentSplit.getCountCards());
			  }
			  System.out.println("Bank    Score "+ bank.getHighCardScore() + "    Einzeln: " + bank.getCardScore()[0] +" "+ bank.getCardScore()[1]);
			  System.out.println("Player  Score "+ agent.getHighCardScore() + "    Einzeln: " + agent.getCardScore()[0] +" "+ agent.getCardScore()[1]);
			  if (agentSplit!=null) {
				  System.out.println("PlayerS Score "+ agentSplit.getHighCardScore() + "    Einzeln: " + agentSplit.getCardScore()[0] +" "+ agentSplit.getCardScore()[1]);
			  }
			  System.out.println("Money  Before : "+agent.getCredit());
		  }

		// TODO agentsplitt bei der auszahlung einbinden (zunaechst aber darf agentSplit nicht gleich agent sein)
			
			
		  if (insurance==true && bank.getCountCards()==2 && bank.getHighCardScore()==21) {
			  agent.setCredit(agent.getBet());
			  agent.gamesWon++;
			  gc.printDecision("Gewonnen, versichert!");
		  }
		  else if (agent.getHighCardScore()==21 && agent.getCountCards()==2) {
				System.out.println("> > > > > > > > > > BlackJack! < < < < < < < < < <");
				agent.setCredit(bet+bet*1.5);
				agent.gamesWon++;
				gc.printDecision("BlackJack!");
			}
			else if(agent.getHighCardScore()>21){
				agent.setInGame(false);
//				agent.setCredit(-bet);
				agent.gamesLost++;
				gc.printDecision("Verloren >21");
			}
			else if (bank.getHighCardScore()>21) {
					//System.out.println("Player win!");
					agent.setCredit(bet*2);
					agent.gamesWon++;
					gc.printDecision("Sieg bank>21");
			}
			else if (bank.getHighCardScore()>agent.getHighCardScore()) {
						//System.out.println("Bank win!");
//						agent.setCredit(-bet);
						agent.gamesLost++;
						gc.printDecision("L: Bank->" + bank.getHighCardScore() + "|"+ agent.getHighCardScore() + "<-You" );
			}
			else if (bank.getHighCardScore() == agent.getHighCardScore()) {
						System.out.println("drawn");
						agent.setCredit(bet);
						agent.gamesDraw++;
						gc.printDecision("Draw!");
			}
			// else: bank < player < 21
			else{
				agent.gamesWon++;
				agent.setCredit(bet);
				gc.printDecision("W: Bank->" + bank.getHighCardScore() + "|"+ agent.getHighCardScore() + "<-You" );
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
		 * only for test test
		 */
}