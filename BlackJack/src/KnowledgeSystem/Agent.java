/**
 * 
 * @author Fabian Schäfer
 * @brief This class implements the algorithm of the agent
 *
 */
package KnowledgeSystem;

import data.CardSet;
import data.Rules;

public class Agent extends Player{

	private double [] probability;
	
	public Agent (int credit)
	{
		super(credit);
		probability = new double [11];
	}

	//TODO: Wahrscheinlichkeit berechnen
	//TODO: Strategie
	//TODO: Entscheidung anhand von Kriterien treffen
	
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
	
	public void initializeProbability(CardSet cardDeck)
	{
		//TODO probability berechnen
		for(int i = 0; i < cardDeck.getLengthSym();i++) {
			for(int j = 0; j < cardDeck.getLengthVal();j++){
//				if(cardDeck.[i][j] == 0){
//					
				}
		}
	}

	public void updateProbability(int [] card) {
		
		/**
		 * Updates the probabilty if a new card is pulled by a participant
		 */
		//4x 1 - 9; 16x 10; 1x 11
		switch(card[2])
		{
			case 1: break;
			case 2: break;
			case 3: break;
			case 4: break;
			case 5: break;
			case 6: break;
			case 7: break;
			case 8: break;
			case 9: break;
			case 10: break;
			case 11: break;
		}
	}
	
	//getters & setters
	public double[] getProbability() {
		return probability;
	}
}
	

