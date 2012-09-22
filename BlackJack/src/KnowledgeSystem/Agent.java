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
		setProbability(0);
	}

	//TODO: Wahrscheinlichkeit berechnen
	//TODO: Karten zählen
	//TODO: Entscheidung anhand von Kriterien treffen
	
	
	//getters & setters
	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}
	
}
