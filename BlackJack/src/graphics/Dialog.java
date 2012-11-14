package graphics;

import javax.swing.JOptionPane;

import controller.BlackJack;

public class Dialog {

   public static void main(String[] args) {
	   Dialog d = new Dialog();
   }
   
   public Dialog(){
	   initialise();
	   	dialog();
   }
   public void dialog(){
	   int games = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of Games"));
       int speed = Integer.parseInt(JOptionPane.showInputDialog("Please enter the speed"));
   	BlackJack table = new BlackJack(games, speed, this);		//create table
	BlackJack.nextStep();
   }
   
   private static void initialise() {
	// TODO Auto-generated method stub
	   for(int i=0; i<values.length;i++){
		   values[i]=0;
	   }
	   
	   for(int i=0; i<value.length;i++){
		   value[i]=0;
	   }
}

static float[] values =new float[21];
static float[] value =new float[21];
private int counter=0;
	
   public void addnewone(float[] newvalues){
	  
	   for(int i=0; i<values.length;i++){
		   values[i]+=newvalues[i];
	   }
	   counter++;
	   print();
   }

   public void update(){
	   for(int i=0; i<values.length;i++){
		   value[i]=values[i]/counter;
	   }
   }
   
   public void print(){
	   update();
	   System.out.println("____________________Accumulated_______________________");
	   System.out.println("______________________________________________________");
	   System.out.println("Spiele: " +value[20]);
	   System.out.println("Gewonnen: " +value[0]+ "%");
	   System.out.println("Verloren: " +value[1]+ "%");
	   System.out.println("Drawn: " +value[2]+ "%");
	   System.out.println("You used insurance " + value[3] + " times");
		System.out.println("You won " + value[4] + " insured games");
		System.out.println("You lost " + value[5] + " insured games");
		System.out.println("You gained " + value[6] + " in average when insured");
		System.out.println("You used double " + value[7] + " times");
		System.out.println("You won " + value[8] + " doubled games");
		System.out.println("You lost " + value[9] + " doubled games");
		System.out.println("You gained " + value[10] + " in average when doubled");
		System.out.println("You won " + value[11] + " in average");
		System.out.println("You lost " + value[12] + " in average");
		System.out.println("You have lost because you had >21 points " + value[13]);
		System.out.println("You have won because bank had >21 points " + value[14]);
		System.out.println("You have lost because you had less points " + value[15]);
		System.out.println("You have won because bank had less points " + value[16]);
		System.out.println("You had " + value[17] + " Blackjacks");
		System.out.println("Bank had " + value[18] + " Blackjacks");
		System.out.println("Verbleibendes Guthaben "+ value[19]);
		System.out.println("________________________________________________");
		System.out.println("___________________End Akkumulated______________");
   }
}
