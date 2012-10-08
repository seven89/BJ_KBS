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
	
	public Agent (int credit)
	{
		super(credit);
		probability = new float [11];
		probCounter = new int [11];
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
	
	public boolean evalStrategy()
	{
		int diffLoStack = 21 - super.cardScore[0];
		int diffHiStack = 21 - super.cardScore[1];
		//cardScore[0] = stack with ace value equals 1
		if(diffLoStack > 10 && diffHiStack == 10)
		{
			//In this case always a new card is pulled.
			return true;
		}
		else
		{
			if(diffHiStack > 21)
			{
				//grenze zwischen High and low stack bestimmen
				//wo ist die höhere wahrscheinlichkeit zu gewinnen? - Noch eine karte nehmen oder den stapel mit high ass nehmen
			}
			//needed value until 21 is reached
			float tempProbability;
			//TODO: decision between both stacks
			//strategy with stack zero
			switch(diffLoStack)
			{
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
				case 11:
			}
		
			//strategy with stack one
		
		
			//summary strategy (both stacks)
		}
		
		return false;
	}
	
	//getters & setters
	public float[] getProbability() {
		return probability;
	}
}
	

