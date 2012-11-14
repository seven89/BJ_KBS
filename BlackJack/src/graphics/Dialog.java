package graphics;

import javax.swing.JOptionPane;

import controller.BlackJack;

public class Dialog {

   public static void main(String[] args) {
       int games = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of Games"));
       int speed = Integer.parseInt(JOptionPane.showInputDialog("please enter the speed"));
       
   	BlackJack table = new BlackJack(games, speed);		//create table
	table.nextStep();		
   }
}