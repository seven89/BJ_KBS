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

	//TODO: Wahrscheinlichkeit berechnen
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
	
	private void initializeProbability()
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
	}
	
	public void setProbability()
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
				probability[i] = probCounter[i]/52*100;
			}
		}
	}
	
	//getters & setters
	public float[] getProbability() {
		return probability;
	}
}
	

