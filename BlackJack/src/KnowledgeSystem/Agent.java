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
	float[] resultProb;
	
	private int pullBorder;
	private boolean checkState;
	private boolean isDoubled;
	
	public Agent (int credit)
	{
		super(credit);
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
		int y,y1,x,x1;
		float tmpProb = 0;
		x = 0; y = 10;
    	x1 = 10; y1 = 0;
    	for(int i = 0; i < probability.length-1; i++)
    	{
    		if((y-i)+(x+i+1) > border)
    		{
    			//forwards
    			tmpProb = tempProb[y-1-i];
    			calcProb(y-i);
    			tmpProb += tempProb[x+i];
    			checkState = true;
    		}
    		
    		if((y1+i+1)+(x1-i) > border)
    		{
    			//backwards
    			if(!(y-i == x+i))
    			{
    				tmpProb = tempProb[y1+i];
    				calcProb(y1+i+1);
    				tmpProb += tempProb[x1-1-i];
    				checkState = true;
    			}
    		}
    	}	
    	return tmpProb;
	}
	
	public boolean pullCard(int [] bankScore)
	{
		/**
		 * @brief This method decides when the agent should pull a new Card or not
		 */
		//-->used in funtion calcProb
		checkState = true;
		@SuppressWarnings("unused")
		float bankBj = 0;
		
		
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
						 * Wie hoch ist die Wahrscheinlichkeit, dass die Bank eine dritte Karte zieht (nach einem Ass)
						 */
						tmpProb += probability[i];
					}
					for(int i = 0; i <= 9; i++){
						/**
						 * Wie hoch ist die Wahrscheinlichkeit, dass die Bank keine dritte Karte zieht
						 */
						if(i == 0 || i > 5){
							tempProb1 += probability[i];
						}
					}
					if(tmpProb > tempProb1)
					{
						//bankscore >= 2 && <= 16
						//TODO: Entscheidung anhand von Wahrscheinlichkeiten ermitteln
						/**
						 * eigene Wahrscheinlichkeit gegen Bank
						 * Spieler Score >= 11
						 */
						//wie hoch die Wahrscheinlichkeit, dass Bank über 21
						//wie hoch, dass Wahrscheinlichkeit unter 21 
						//TODO: bankOverTop ermitteln --> man 12 erreicht --> danach erste wahrscheinlichkeit drüber
						bankOverTop = tmpProb;
						{//Ass+10
							tmpProb = tempProb[0];
							calcProb(1); 
							tmpProb += tempProb[10];
							calcProb(10);
							checkState = true;
						}
						{//2+9
							tmpProb = tempProb[1];
							calcProb(2); 
							tmpProb += tempProb[8];
							calcProb(9);
							checkState = true;
						}
						{//3+8
							tmpProb = tempProb[2];
							calcProb(3); 
							tmpProb += tempProb[7];
							calcProb(8);
							checkState = true;
						}
						{//4+7
							tmpProb = tempProb[3];
							calcProb(4); 
							tmpProb += tempProb[6];
							calcProb(7);
							checkState = true;
						}
						{//5+6
							tmpProb = tempProb[4];
							calcProb(5); 
							tmpProb += tempProb[5];
							calcProb(6);
							checkState = true;
						}
						{//6+5
							tmpProb = tempProb[5];
							calcProb(6); 
							tmpProb += tempProb[4];
							calcProb(5);
							checkState = true;
						}
						{//7+4
							tmpProb = tempProb[6];
							calcProb(7); 
							tmpProb += tempProb[3];
							calcProb(4);
							checkState = true;
						}
						{//8+3
							tmpProb = tempProb[7];
							calcProb(8); 
							tmpProb += tempProb[2];
							calcProb(3);
							checkState = true;
						}
						{//9+2
							tmpProb = tempProb[8];
							calcProb(9); 
							tmpProb += tempProb[1];
							calcProb(2);
							checkState = true;
						}
						{//10+1
							tmpProb = tempProb[9];
							calcProb(10); 
							tmpProb += tempProb[0];
							calcProb(1);
							checkState = true;
						}
						bankOverTop += tmpProb;
					}
					else
					{
						//bankScore = 17 - 21 --> Probability
						bankHiScore = tempProb1 + probability[5] + probability[6]
								+ probability[7] + probability[8] + probability[9];
					}
					tempProb1 = 0;
					tmpProb = 0;
				//2
				case 19:
				{//2+10+10
						tmpProb = tempProb[9];
						calcProb(10);
						tmpProb += tempProb[9];
						checkState = true;
						bankOverTop = tmpProb;
						bankHiScore = 100-tmpProb;
						tmpProb = 0;
						
				}
				//3
				case 18:
				{//3+9+10| 3+10+9
					tmpProb = tempProb[9];
					calcProb(10);
					tmpProb += tempProb[9];
					checkState = true;
					tmpProb = tempProb[9];
					calcProb(10);
					tmpProb += tempProb[8];
					checkState = true;
					tmpProb += tempProb[8];
					calcProb(10);
					tmpProb += tempProb[9];
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
					checkState = true;
				}
				//4
			    case 17:
			    {//4+10+8| 4+8+10 | 4+9+9
			    	tmpProb = calcOverrun (17);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
					
				}
				//5
			    case 16:
			    {//8+9|9+8|10+7|7+10
			    	tmpProb = calcOverrun (16);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;				
				}
				//6
			    case 15:
			    {//8+8|9+7|7+9|10+6|6+10
			    	tmpProb = calcOverrun (15);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
				}
				//7
			    case 14:
			    {//5+10|10+5|7+8|8+7|6+9|9+6
			    	tmpProb = calcOverrun (14);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
				}	 	
				//8
			    case 13:
			    	tmpProb = calcOverrun (13);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
				//9
			    case 12:
			    	tmpProb = calcOverrun (12);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
				//10
			    case 11:
			    	tmpProb = calcOverrun (11);
					bankOverTop = tmpProb;
					bankHiScore = 100-tmpProb;
					tmpProb = 0;
					bankBj = probability[9];
			}
			if(bankHiScore > 50 && bankOverTop <= 50 && bankHiScore >= bankOverTop)
			{
				return false;
			}
			else if(bankOverTop > 50 && bankOverTop > bankHiScore)
			{
				return true;
			}
		}
		return false;
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
	

