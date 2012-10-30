/**
 * 
 * @author Fabian Schäfer
 * @brief This class implements the algorithm of the agent
 * @param probability: the addresses 0 till 10 are reserved for the each distinct card 
 * value. The last address is reserved for the total number of all cards.
 */
package KnowledgeSystem;

import data.Rules;

public class Agent extends Player{

	private float [] probability;
	private int [] probCounter;
	
	float[] tempProb;
	int[] tempProbCounter;
	float resultProb;
	
	private int pullBorder;
	private boolean checkState;
	
	public Agent (int credit)
	{
		super(credit);
		probability = new float [11];
		probCounter = new int [11];
		tempProb = new float [11];
		tempProbCounter = new int [11];
		resultProb = 0;
		pullBorder = 16;
		initializeProbability();
	}

	//TODO: Strategie --> Entscheidung anhand von Kriterien treffen
	
	public double calcBetValue(Rules rules)
	{
		/**
		 * This method calculates the bet-value of an agent
		 */
		double betValue = 0;
		if(credit/50 < rules.getBoxLimit()) betValue =credit/50;
		else betValue = rules.getBoxLimit();
		
		return betValue;
	}
	
	public void initializeProbability()
	{
		/**
		 * At the beginning each card value from 1 to 9 and 11 is contained in the
		 * card deck four times. - the card value 10 sixteen times.
		 */
		for(int i = 0; i < probCounter.length; i++)
		{
			if(i == 9)
			{
				//ten, jack, queen, king
				probCounter[i] = 16;
			}
			else if(i==10)
			{
				//total count
				probCounter[i] = 52;
			}
			else 
			{
				//1-9, probability of 1 equals 11
				probCounter[i]= 4;
			}
		}
		this.setProbability();

	}

	public void updateProbability(int [] card) {
		
		/**
		 * Updates the probability if a new card is pulled by a participant
		 */
		switch(card[2])
		{
			case 1: probCounter[0]--;
					break;
			case 2: probCounter[1]--;
					break;
			case 3: probCounter[2]--;
					break;
			case 4: probCounter[3]--;
					break;
			case 5: probCounter[4]--;
					break;
			case 6: probCounter[5]--;
					break;
			case 7: probCounter[6]--;
					break;
			case 8: probCounter[7]--;
					break;
			case 9: probCounter[8]--;
					break;
			case 10: probCounter[9]--;
					break;
			case 11: probCounter[0]--;
					break;
		}
		probCounter[10]--;
		this.setProbability();
	}
	
	private void setProbability()
	{
		for(int i = 0; i < probability.length; i++)
		{
			if(i == 10)
			{
				//in case of ace
				probability[i] = probability[0];
			}
			else 
			{
				probability[i] = (float) (Math.round((float)probCounter[i]/probCounter[10]*100*100)/100.0);
				System.out.println(i+1 + " = " + probability[i]);
			}
		}
		System.out.println("---------");
	}
	
	public void calcProb(int card)
	{
		if(checkState)
		for(int i = 0; i < probability.length; i++){
			tempProb[i]=probability[i];
			tempProbCounter[i]=probCounter[i];
		}
		checkState = false;
		//TODO: Calc future Probability
		
		switch(card)
		{
			case 1: tempProbCounter[0]--;
					break;	
			case 2: tempProbCounter[1]--;
					break;
			case 3: tempProbCounter[2]--;
					break;
			case 4: tempProbCounter[3]--;
					break;
			case 5: tempProbCounter[4]--;
					break;
			case 6: tempProbCounter[5]--;
					break;
			case 7: tempProbCounter[6]--;
					break;
			case 8: tempProbCounter[7]--;
					break;
			case 9: tempProbCounter[8]--;
					break;
			case 10: tempProbCounter[9]--;
					break;
			case 11: tempProbCounter[0]--;
					break;
		}
		for(int i = 0; i < probability.length; i++){
			if(i == 10){
				//in case of ace
				tempProb[i] = tempProb[0];
			}
			else {
				tempProb[i] = (float) (Math.round((float)tempProbCounter[i]/tempProbCounter[10]*100*100)/100.0);
			}
		}		
	}
	
	public float calcBankWinProb (int currentVal, float curProb)
	{
		int diffVal = 21-currentVal;
		resultProb += curProb;
		if(diffVal > 4)//unter 17
		{
			switch(diffVal)
			{
				/**
				 * Minimum was die Bank haben kann ist ein Ass (als erste karte), deshalb fangen wir hier mit 2 an,
				 * da die Wahrscheinlichkeiten für folgende karten berechnet wird
				 */						
				case 5: 
					/**
					 * 5
					 * 4,1
					 * 3,2
					 * 3,1,1
					 * 2,3
					 * 2,2,1
					 * 2,1,2
					 * 2,1,1,1
					 * 1,2,2
					 * 1,3,1
					 * 1,4
					 * 1,1,1,2
					 * 1,2,1,1
					 * 1,1,2,1
					 */
						
				case 6: calcProb(6);
						calcBankWinProb(currentVal+6,tempProb[5]);
				case 7: calcProb(7);
						calcBankWinProb(currentVal+7,tempProb[6]);
				case 8: calcProb(8);
						calcBankWinProb(currentVal+8,tempProb[7]);
				case 9: calcProb(9);
						calcBankWinProb(currentVal+9,tempProb[8]);
				case 10: calcProb(10);
						calcBankWinProb(currentVal+10,tempProb[9]);
				case 11:
				case 12:
				case 13:
				case 14:
				case 15:
				case 16:
				case 17:
				case 18:
				case 19:
				case 20:
					
			}
		}
		return resultProb;
	}
	
	public boolean pullCard(int [] bankScore)
	{
		//-->used in funtion calcProb
		checkState = true;
		
		int diffLoStack = 21 - super.cardScore[0];
		int diffHiStack = 21 - super.cardScore[1];
		/**
		 * bank muss bis einschließlich 16 immer ziehen
		 * --> ab 17 ist ende
		 */
		if(diffHiStack < 11)
		{
			return true;
		}
		else if(diffLoStack <= 21-pullBorder)
		{
			//AgentScore >= 16
			return false;
		}
		else
		{
			int bankDiffLoStack = 21 - bankScore[0];
			int bankDiffHiStack = 21 - bankScore[1];
			int bankDiff, agentDiff;
			if(bankDiffLoStack != bankDiffHiStack){
				//in case of ace
				bankDiff = 0;
			}
			else {
				//else no difference between low and high stack
				bankDiff = bankDiffHiStack;
			}	
			float tempProb = 0, tempProb1 = 0;
			switch(bankDiff) {
				//Ace
				case 0:		
					//case Ace = 1 --> nur wenn 21 überschritten wird
					for(int i = 0; i < 6;i++){
						/**
						 * Wie hoch ist die Wahrscheinlichkeit, dass die Bank eine dritte Karte zieht (nach einem Ass)
						 */
						tempProb += probability[i];
					}
					for(int i = 0; i <= 9; i++){
						/**
						 * Wie hoch ist die Wahrscheinlichkeit, dass die Bank keine dritte Karte zieht
						 */
						if(i == 0 || i > 4){
							tempProb1 += probability[i];
						}
					}
					if(tempProb > tempProb1)
					{
						//bankscore >= 2 && <= 16
						//TODO: Entscheidung anhand von Wahrscheinlichkeiten ermitteln
						return false;
						/**
						 * eigene Wahrscheinlichkeit gegen Bank
						 * Spieler Score >= 11
						 */
					}
					else
					{
						//TODO: Entscheidung anhand von Wahrscheinlichkeit ermitteln
						//bankScore = 17 - 21
						return true;
					}
				//2
				case 19:
						return false;
				//3
				case 18:
						return false;
				//4
			    case 17:
						return true;
				//5
			    case 16:
			    		return true;
				//6
			    case 15:
			    		return true;
				//7
			    case 14:
			    		return true;
				//8
			    case 13:
			    		return true;
				//9
			    case 12:
			    		return true;
				//10
			    case 11:
			    		return true;
				
			}
		}
		return false;
	}
	
	//getters & setters
	public float[] getProbability() {
		return probability;
	}
}
	

