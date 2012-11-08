/**
 * 
 * @author Fabian Schäfer
 * @brief This class implements the algorithm of the agent
 * @param probability: the addresses 0 till 10 are reserved for the each distinct card 
 * value. The last address is reserved for the total number of all cards.
 */
package KnowledgeSystem;



public class Agent extends Player{

	public float [] probability;
	private int [] probCounter;
	
	float[] tempProb;
	int[] tempProbCounter;
	int twoPartyCombis;
	float[] resultProb;
	
	private int pullBorder;
	private boolean checkState;
	private boolean isDoubled;
	
	public Agent (int credit)
	{
		super(credit);
		//130 = Count of possible two-party combinations [1,1][1,2][2,1][2,2]...
		twoPartyCombis = 130;
		
		probability = new float [11];
		probCounter = new int [11];
		tempProb = new float [11];
		tempProbCounter = new int [11];
		pullBorder = 21;
		setDoubled(false);
		initializeProbability();
	}
	
	public double calcBetValue(int boxLimit)
	{
		/**
		 * This method calculates the bet-value of an agent
		 */
		double betValue = 0;
		if(credit/10 <boxLimit) betValue =credit/10;
		else betValue = boxLimit;
		
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
//				System.out.println(i+1 + " = " + probability[i]);
			}
		}
//		System.out.println("---------");
	}
	
	public void calcProb(int card)
	{
		/**
		 * @brief This method is for temporary probability calculations
		 * @param checkState indicates, whether the real probabilities should 
		 * 		  be initialized again (true) or whether not (false)
		 */
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
	
	
	private float calcOverrun (int border)
	{
		/**
		 * @brief Calculates all combination which overrun the border and
		 * 		  returns its probability
		 */
		int y,x;
		float tmpProb = 0;
		x = 0; y = 10;
    	for(int i = 0; i < probability.length-1; i++)
    	{
    		for(int z = 0; z < probability.length-1; z++)
    		{
    			if((y-i)+(x+z+1) > border)
    			{
    				tmpProb += tempProb[y-1-i];
    				calcProb(y-i);
    				tmpProb += tempProb[x+z];
    				checkState = true;
    			}
    		}
    	}	
    	tmpProb = tmpProb/130;
    	return tmpProb;
	}
	
	public boolean pullCard(int [] bankScore)
	{
		/**
		 * @brief This method decides when the agent should pull a new Card or not
		 */
		//-->used in funtion calcProb
		checkState = true;
		
		//bank score is between 17 and 21
		float bankHiScore = 0;
		
		//Indicates whether 21 is exceeded (when 22 is reached)
		float bankOverTop = 0;
		
		int diffLoStack = 21 - super.cardScore[0];
		int diffHiStack = 21 - super.cardScore[1];
		/**
		 * bank have to pull until a score of 16 
		 * --> at score of 17 pulling is over
		 */
		/*
		 * The probability of pulling a 10 is the highest in game, therefore the agent
		 * should always pull until a difference of 10 until the score reaches 21.
		 */
		if(diffHiStack >= 10)
			return true;
		/*
		 * This else-path is used to prevent that the agent sells itself
		 * -->diffLoStack considers Ace with score of 1
		 */
		else if(diffLoStack <= 21-pullBorder)
			return false;
		/*
		 * This else-path decides on basis of bank probabilities
		 */
		else {
			boolean check = calcPullBorder (diffLoStack, diffHiStack);
			if(!check)
				return false;
			int bankDiffLoStack = 21 - bankScore[0];
			int bankDiffHiStack = 21 - bankScore[1];
			int bankDiff;
			if(bankDiffLoStack != bankDiffHiStack){
				//in case of ace
				bankDiff = 0;
			}
			else {
				//else no difference between low and high stack
				bankDiff = bankDiffHiStack;
			}	
			float tmpProb = 0, tempProb1 = 0;
			switch(bankDiff) {
				//Ace
				case 0:		
					//case Ace = 1 --> nur wenn 21 überschritten wird
					for(int i = 0; i < 6;i++){
						/**
						 * Probability, that bank pulls a third card (after ace)
						 */
						tmpProb += probability[i];
					}
					for(int i = 0; i <= 9; i++){
						/**
						 * Probability, that bank pulls no third card
						 */
						if(i == 0 || i > 5){
							tempProb1 += probability[i];
						}
					}
					if(tmpProb > tempProb1)
					{
						//case: bank pulls three cards, first ace score = 11
						//-->Question: When overrun Bank score the value 21
						bankOverTop = tmpProb;
						tmpProb = calcOverrun (10);
						bankOverTop += tmpProb/2;
					}
					else
					{
						//bankScore = 17 - 21 --> Probability
						bankHiScore = (tempProb1 + probability[5] + probability[6]
								+ probability[7] + probability[8] + probability[9])/2;
					}
					tempProb1 = 0;
					tmpProb = 0;
					break;
				//2
				case 19:
				{//2+10+10
					tmpProb = calcOverrun (19);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
					break;
				}
				//3
				case 18:
				{//3+9+10| 3+10+9
					tmpProb = calcOverrun (18);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
					break;
				}
				//4
			    case 17:
			    {//4+10+8| 4+8+10 | 4+9+9
			    	tmpProb = calcOverrun (17);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
					break;
				}
				//5
			    case 16:
			    {//8+9|9+8|10+7|7+10
			    	tmpProb = calcOverrun (16);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;			
					break;
				}
				//6
			    case 15:
			    {//8+8|9+7|7+9|10+6|6+10
			    	tmpProb = calcOverrun (15);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
					break;
				}
				//7
			    case 14:
			    {//5+10|10+5|7+8|8+7|6+9|9+6
			    	tmpProb = calcOverrun (14);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
					break;
				}	 	
				//8
			    case 13:
			    	tmpProb = calcOverrun (13);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
					break;
				//9
			    case 12:
			    	tmpProb = calcOverrun (12);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
					break;
				//10
			    case 11:
			    	tmpProb = calcOverrun (11);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
					break;
			}
			//spot check result = 92% Bank HighScore (17-21); Agent Score <= 13
			if(bankHiScore > 92 && bankOverTop <8 && bankHiScore >= bankOverTop)
			{
				return false;
			}
			else 
			{
				return true;
			}
		}
	}
	
	private boolean calcPullBorder (int lo, int hi)
	{
		float probHi = calcOverrun (hi);
		float probLo = calcOverrun (lo);
		float tmpProb;
		//Probability that agent overruns 21
		tmpProb = (probLo + probHi)/2;
		if(tmpProb > 40)
		{
			pullBorder = lo;
			return false;
		}
		return true;
	}
	
	public boolean insureBoolean(int agentScore) {
		boolean back=false;
		return back;
	}
	
	public boolean doublingBoolean(int aScore, int bScore) {
			
        switch (bScore) {
        	case 2: {
        		if (aScore==9) return false;
        		else return true;
        	}	
            case 3:  return true;
            case 4:  return true;
            case 5:  return true;
            case 6:  return true;
            case 7: {
        		if (aScore==9) return false;
        		else return true;
        	}	
            default: return false;
        }
	}
		
	
	public boolean splittingBoolean(int aCard, int bCard) {
		return true;
	}
	
	
	
	//getters & setters
	public float[] getProbability() {
		return probability;
	}

	public boolean isDoubled() {
		return isDoubled;
	}

	public void setDoubled(boolean isDoubled) {
		this.isDoubled = isDoubled;
	}
}
	

