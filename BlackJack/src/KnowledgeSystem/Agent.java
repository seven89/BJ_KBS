/**
 * 
 * @author Fabian Schäfer
 * @brief This class implements the algorithm of the agent
 *
 */
package KnowledgeSystem;

public class Agent extends Player{

	private double probability;
	
	public Agent (int credit)
	{
		super(credit);
	}

	//TODO: Wahrscheinlichkeit berechnen
	//TODO: Karten zählen
	//TODO: Entscheidung anhand von Kriterien treffen
	
	
	//getters & setters
	public double getProbability() {
		return probability;
	}

	public void setProbability(int [][] openCards, int length ) {
		
		//TODO: Herausfinden wie viele Karten mit value = 1 bzw. 10 etc
		//4x 1 - 9; 16x 10; 1x 11
		for(int i = 0; i < length;i++)
		{
			switch(openCards[i][3])
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
		
		
		
		//this.probability = probability;
	}
	
}
