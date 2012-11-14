package controller;

import javax.swing.JOptionPane;

import graphics.Dialog;
import graphics.GraphicsController;
import data.CardSet;
import KnowledgeSystem.Agent;
import KnowledgeSystem.Bank;
import KnowledgeSystem.Player;

public class BlackJack {

	//auswertung
	private static int insured=0;
	private static int insuredwin=0;
	private static int insuredlost=0;
	private static int insureddraw=0;
	private static int doubled=0;
	private static int doubledwin=0;
	private static int doubledlost=0;
	private static int doubleddraw=0;
	private static int averagewin=0;
	private static int averagelost=0;
	private static int averageinsured=0;
	private static int averagedoubled=0;
	private static int lostover=0;
	private static int wonover=0;
	private static int lostunder=0;
	private static int wonunder=0;
	private static int blackjack=0;
	private static int lostblackjack=0;
	
	private static double money=0;
	private static boolean splitted=false;
	private static boolean won=false;
	private static int wincounter=0;
	private static int lostcounter=0;
	private static int doubledcounter=0;
	private static int insuredcounter=0;

	
	
	
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
	private static Dialog d;
	
	public BlackJack(int games, int speed, Dialog d) {
	 this.d=d;
		debug =2; // 1 bedeutet, dass alle syso ausgegeben werden
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
		BlackJack.speed=speed;
		BlackJack.games=games;
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
								//System.out.println("oooooooooooooooooooooooooooooooooooo Versichern? (Spielmodus)");
								
								if (agent.insureBoolean(card[2])) {
									agent.insure();
									insurance=false;
								}
							}
							
							// splitting (teilen, spielmodus)
//							System.out.println("Card Step B           "+card[1]+" "+agent.getCurrentCard());
							if (agent.getCountCards()==1 && agent.getCurrentCard()==card[1] && agentSplit==null && modus==false) {
//								System.out.println("Teilen? (spielmodus)");
								doubled++;
								splitted=true;
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
								//System.out.println("oooooooooooooooooooooooooooooooooooo Doppeln? (Spielmodus)");		
								
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
			
			if(debug==2){
				
				int wert1=0;
				int wert2=0;
				int wert3=0;
				int wert4=0;
				if(insuredcounter!=0) wert1= (averageinsured/insuredcounter);
				if(doubledcounter!=0)wert2=(averagedoubled/doubledcounter);
				if(wincounter!=0) wert3=(averagewin/wincounter);
				if(lostcounter!=0) wert4=(averagelost/lostcounter);
				System.out.println("You used insurance " + insured + " times");
				System.out.println("You won " + insuredwin + " insured games");
				System.out.println("You lost " + insuredlost + " insured games");
				System.out.println("You gained " + wert1 + " in average when insured");
				System.out.println("You used double " + doubled + " times");
				System.out.println("You won " + doubledwin + " doubled games");
				System.out.println("You lost " + doubledlost + " doubled games");
				System.out.println("You gained " + wert2 + " in average when doubled");
				System.out.println("You won " + wert3 + " in average");
				System.out.println("You lost " + wert4 + " in average");
				System.out.println("You have lost because you had >21 points " + lostover);
				System.out.println("You have won because bank had >21 points " + wonover);
				System.out.println("You have lost because you had less points " + lostunder);
				System.out.println("You have won because bank had less points " + wonunder);
				System.out.println("You had " + blackjack + " Blackjacks");
				System.out.println("Bank had " + lostblackjack + " Blackjacks");
				System.out.println("Verbleibendes Guthaben "+ agent.getCredit());
				
				float[] values =new float[21];
				values[0]=(float) (((float)agent.gamesWon/(float)agent.gamesPlayed)*100.0);
				values[1]=(float) (((float)agent.gamesLost/(float)agent.gamesPlayed)*100.0);
				values[2]=(float) (((float)agent.gamesDraw/(float)agent.gamesPlayed)*100.0);
				values[3]=insured;
				values[4]=insuredwin;
				values[5]=insuredlost;
				values[6]=wert1;
				values[7]=doubled;
				values[8]=doubledwin;
				values[9]=doubledlost;
				values[10]=wert2;
				values[11]=wert3;
				values[12]=wert4;
				values[13]= lostover;
				values[14]=wonover;
				values[15]=lostunder;
				values[16]=wonunder;
				values[17]=blackjack;
				values[18]=lostblackjack;
				values[19]=agent.getCredit();
				values[20]=agent.gamesPlayed;
				d.addnewone(values);
			}
			
			//gc.setVisible(false);
			//gc.dispose();
			//System.exit(0);
			
			  int n = JOptionPane.showConfirmDialog(
			            null,
			            "Would you like to play again?",
			            "-",
			            JOptionPane.YES_NO_OPTION);

			        if(n==0){
			        	gc.setVisible(false);
			        	gc=null;
			            d.dialog();
			            System.exit(0);
			           
			        }
			        else {
			            JOptionPane.showMessageDialog(null, "Thanks for playing!");
			            System.exit(0);
			        }

			        
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
			
		  if (bank.getCountCards()==2 && bank.getHighCardScore()==21) {
			  if(insurance==true){
			  money=agent.getBet();
			  agent.setCredit(agent.getBet());
			  agent.gamesWon++;
			  gc.printDecision("Gewonnen, versichert!");
			  lostblackjack++;
			  insuredwin++;
			  insured++;
			  won=true;
			  }
			  else{
				  lostblackjack++;
				  money=agent.getBet();
				  insuredlost++;
				  insured++;
				  won=false;
			  }
		  }
		  else if(insurance==true){
			  insuredlost++;
			  insured++;
			  money=bet;
			  won=false;
		  }
		  else if (agent.getHighCardScore()==21 && agent.getCountCards()==2) {
				//System.out.println("> > > > > > > > > > BlackJack! < < < < < < < < < <");
				money=bet+bet*1.5;
			  	agent.setCredit(((bet+bet)*1.5));
				agent.gamesWon++;
				gc.printDecision("BlackJack!");
				blackjack++;
				won=true;
			}
			else if(agent.getHighCardScore()>21){
				money=agent.getBet();
				agent.setInGame(false);
//				agent.setCredit(-bet);
				agent.gamesLost++;
				gc.printDecision("Verloren >21");
				lostover++;
				won=false;
			}
			else if (bank.getHighCardScore()>21) {
					//System.out.println("Player win!");
				money=bet*2;	
				agent.setCredit((bet*2));
					agent.gamesWon++;
					gc.printDecision("Sieg bank>21");
					wonover++;
					won=true;
			}
			else if (bank.getHighCardScore()>agent.getHighCardScore()) {
						//System.out.println("Bank win!");
//						agent.setCredit(-bet);
						money=agent.getBet();
						agent.gamesLost++;
						gc.printDecision("L: Bank->" + bank.getHighCardScore() + "|"+ agent.getHighCardScore() + "<-You" );
						lostunder++;
						won=false;
			}
			else if (bank.getHighCardScore() == agent.getHighCardScore()) {
						
						money=agent.getBet();
						//System.out.println("drawn");
						agent.setCredit(bet*1.2);
						agent.gamesDraw++;
						gc.printDecision("Draw!");
			}
			// else: bank < player < 21
			else{
				money=bet;
				agent.gamesWon++;
				agent.setCredit(bet*2);
				gc.printDecision("W: Bank->" + bank.getHighCardScore() + "|"+ agent.getHighCardScore() + "<-You" );
				wonunder++;
				won=true;
			}
			
		  //averagemoney
		  	if(insurance==true){
		  		if(won){
		  			averageinsured +=money;
		  		}
		  		else{
		  			averageinsured -=money;
		  		}
		  		insuredcounter++;
		  	}
		  	else if(splitted){
		  		if(won){doubledwin++;averagedoubled+=money;}
		  		else{
		  			doubledlost++;
		  			averagedoubled-=money;
		  		}
		  		doubledcounter++;
		  		splitted=false;
		  	}
		  	else {
		  			if(won){averagewin+=money;wincounter++;}
		  			else{
		  				averagelost-=money;
		  				lostcounter++;
		  			}
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