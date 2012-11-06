package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;

import KnowledgeSystem.Agent;
import KnowledgeSystem.Player;

import controller.BlackJack;
import data.CardSet;
import data.Rules;


public class GraphicsController extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	private static BufferedImage texture1a;
	private static BufferedImage texture1b;
	private static BufferedImage texture1c;
	private static BufferedImage texture1d;
	private static BufferedImage texture2a;
	private static BufferedImage texture2b;
	private static BufferedImage texture2c;
	private static BufferedImage texture2d;
	private static BufferedImage texture3a;
	private static BufferedImage texture3b;
	private static BufferedImage texture3c;
	private static BufferedImage texture3d;
	private static BufferedImage texture4a;
	private static BufferedImage texture4b;
	private static BufferedImage texture4c;
	private static BufferedImage texture4d;
	private static BufferedImage texture5a;
	private static BufferedImage texture5b;
	private static BufferedImage texture5c;
	private static BufferedImage texture5d;
	private static BufferedImage texture6a;
	private static BufferedImage texture6b;
	private static BufferedImage texture6c;
	private static BufferedImage texture6d;
	private static BufferedImage texture7a;
	private static BufferedImage texture7b;
	private static BufferedImage texture7c;
	private static BufferedImage texture7d;
	private static BufferedImage texture8a;
	private static BufferedImage texture8b;
	private static BufferedImage texture8c;
	private static BufferedImage texture8d;
	private static BufferedImage texture9a;
	private static BufferedImage texture9b;
	private static BufferedImage texture9c;
	private static BufferedImage texture9d;
	private static BufferedImage texture10a;
	private static BufferedImage texture10b;
	private static BufferedImage texture10c;
	private static BufferedImage texture10d;
	private static BufferedImage texture11a;
	private static BufferedImage texture11b;
	private static BufferedImage texture11c;
	private static BufferedImage texture11d;
	private static BufferedImage texture12a;
	private static BufferedImage texture12b;
	private static BufferedImage texture12c;
	private static BufferedImage texture12d;
	private static BufferedImage texture13a;
	private static BufferedImage texture13b;
	private static BufferedImage texture13c;
	private static BufferedImage texture13d;
	private static BufferedImage textureb;

	
	class Figure {
		int type=0;
		int value=0;
		int color=0;
		int visible=0;
		int x=0;
		int y=0;
		public int drawn=0;
	}
	// Array for Objects that are to be painted (z index is rising)
	public Figure[] graphicObjects;
	public CardPanel[] panels;
	public JLabel[] labels;
	public JLabel[] stats;
	public JLabel decision;
	private int playercards=0;
	private int bankcards=0;
	private int cards=0;
	public boolean pause=false;
	//rdy grphc
	private BackgroundPanel backgroundPanel;
	private CardPanel cardPanel;
	   
public GraphicsController(){
	 
	 labels=new JLabel[10];
	 for(int i=0;i<labels.length;i++){
		 labels[i]=null;
	 }
	 
	 stats=new JLabel[7];
	 for(int i=0;i<stats.length;i++){
		 stats[i]=null;
	 }
	 
	 graphicObjects=new Figure[52];
	 for(int i=0;i<graphicObjects.length;i++){
		 graphicObjects[i]=new Figure();
	 }
	 
	 panels=new CardPanel[99];
	 for(int i=0;i<panels.length;i++){
		 panels[i]=null;
	 }
	 
	createAndShowWindow(); 
	showpercentage();
	
	
	 

	setSize(1024,768);
	initGraphics();
	centerOnScreen(this);
	
	float w1=(float) 1.02;
	JTextField text = new JTextField("Whatever");
    text.setText("Wahrscheinlichkeit für 1: "+ w1);
    getContentPane().add(text);
	
	backgroundPanel = new BackgroundPanel(this);
	JButton closeButton = new JButton("Close");
	JButton pauseButton = new JButton("Pause");
	
	closeButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			GraphicsController.this.setVisible(false);
			GraphicsController.this.dispose();
			System.exit(0);
		}
	});
	
	pauseButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(GraphicsController.this.pause)GraphicsController.this.pause=false;
			else{GraphicsController.this.pause=true;}
		}
	});
	
	backgroundPanel.add(closeButton);
	backgroundPanel.add(pauseButton);
	getContentPane().add(backgroundPanel);
	}
	   
	
	public void paint(Graphics g) {
		super.paint(g);
				cardPanel=new CardPanel(this, graphicObjects);
				getContentPane().add(cardPanel);

		this.setVisible(true);
	}
	
	public static void initGraphics(){
		try {
			texture1a = ImageIO.read( new File( "images/texture1a.png" ) );
			texture2a = ImageIO.read( new File( "images/texture2a.png" ) );
			texture3a = ImageIO.read( new File( "images/texture3a.png" ) );
			texture4a = ImageIO.read( new File( "images/texture4a.png" ) );
			texture5a = ImageIO.read( new File( "images/texture5a.png" ) );
			texture6a = ImageIO.read( new File( "images/texture6a.png" ) );
			texture7a = ImageIO.read( new File( "images/texture7a.png" ) );
			texture8a = ImageIO.read( new File( "images/texture8a.png" ) );
			texture9a = ImageIO.read( new File( "images/texture9a.png" ) );
			texture10a = ImageIO.read( new File( "images/texture10a.png" ) );
			texture11a = ImageIO.read( new File( "images/texture11a.png" ) );
			texture12a = ImageIO.read( new File( "images/texture12a.png" ) );
			texture13a = ImageIO.read( new File( "images/texture13a.png" ) );
			texture1b = ImageIO.read( new File( "images/texture1b.png" ) );
			texture2b = ImageIO.read( new File( "images/texture2b.png" ) );
			texture3b = ImageIO.read( new File( "images/texture3b.png" ) );
			texture4b = ImageIO.read( new File( "images/texture4b.png" ) );
			texture5b = ImageIO.read( new File( "images/texture5b.png" ) );
			texture6b = ImageIO.read( new File( "images/texture6b.png" ) );
			texture7b = ImageIO.read( new File( "images/texture7b.png" ) );
			texture8b = ImageIO.read( new File( "images/texture8b.png" ) );
			texture9b = ImageIO.read( new File( "images/texture9b.png" ) );
			texture10b = ImageIO.read( new File( "images/texture10b.png" ) );
			texture11b = ImageIO.read( new File( "images/texture11b.png" ) );
			texture12b = ImageIO.read( new File( "images/texture12b.png" ) );
			texture13b = ImageIO.read( new File( "images/texture13b.png" ) );
			texture1c = ImageIO.read( new File( "images/texture1c.png" ) );
			texture2c = ImageIO.read( new File( "images/texture2c.png" ) );
			texture3c = ImageIO.read( new File( "images/texture3c.png" ) );
			texture4c = ImageIO.read( new File( "images/texture4c.png" ) );
			texture5c = ImageIO.read( new File( "images/texture5c.png" ) );
			texture6c = ImageIO.read( new File( "images/texture6c.png" ) );
			texture7c = ImageIO.read( new File( "images/texture7c.png" ) );
			texture8c = ImageIO.read( new File( "images/texture8c.png" ) );
			texture9c = ImageIO.read( new File( "images/texture9c.png" ) );
			texture10c = ImageIO.read( new File( "images/texture10c.png" ) );
			texture11c = ImageIO.read( new File( "images/texture11c.png" ) );
			texture12c = ImageIO.read( new File( "images/texture12c.png" ) );
			texture13c = ImageIO.read( new File( "images/texture13c.png" ) );
			texture1d = ImageIO.read( new File( "images/texture1d.png" ) );
			texture2d = ImageIO.read( new File( "images/texture2d.png" ) );
			texture3d = ImageIO.read( new File( "images/texture3d.png" ) );
			texture4d = ImageIO.read( new File( "images/texture4d.png" ) );
			texture5d = ImageIO.read( new File( "images/texture5d.png" ) );
			texture6d = ImageIO.read( new File( "images/texture6d.png" ) );
			texture7d = ImageIO.read( new File( "images/texture7d.png" ) );
			texture8d = ImageIO.read( new File( "images/texture8d.png" ) );
			texture9d = ImageIO.read( new File( "images/texture9d.png" ) );
			texture10d = ImageIO.read( new File( "images/texture10d.png" ) );
			texture11d = ImageIO.read( new File( "images/texture11d.png" ) );
			texture12d = ImageIO.read( new File( "images/texture12d.png" ) );
			texture13d = ImageIO.read( new File( "images/texture13d.png" ) );
			textureb = ImageIO.read( new File( "images/textureb.png" ) );
			
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("FUCKING SHIT IT DOESNT WORK!(Picture missing)");
		}
	}

	private void centerOnScreen(Container c) {
		Dimension paneSize = c.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		c.setLocation((screenSize.width - paneSize.width) / 2,
				(screenSize.height - paneSize.height) / 2);
	}
	
	private class BackgroundPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		//BufferedImage image = null;
		Image image = null;
		
		BackgroundPanel(GraphicsController graphicsController) {	
			//image = ImageIO.read( new File( "images/textureb.png" ) );
			image=textureb;
		}

		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, this);
		}
	
	}
	
	private class CardPanel extends JPanel {


		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Figure[] graphicObjects;
		int  a=67;
		int b=98;
		int c=4;
		
		
		CardPanel(GraphicsController graphicsController,  Figure[] graphicObjects) {
			
			this.graphicObjects =graphicObjects;
					}

		protected void paintComponent(Graphics g) {
			for(Figure f: graphicObjects){
			if(f.type==2){
			if(f.value==0 && f.color==0) g.drawImage(texture1a.getScaledInstance(a, b, c),f.x,f.y,this); //Ass
			else if(f.value==0 && f.color==1) g.drawImage(texture1b.getScaledInstance(a, b, c),f.x,f.y,this); //Ass
			else if(f.value==0 && f.color==2) g.drawImage(texture1c.getScaledInstance(a, b, c),f.x,f.y,this); //Ass
			else if(f.value==0 && f.color==3) g.drawImage(texture1d.getScaledInstance(a, b, c),f.x,f.y,this); //Ass
			else if(f.value==1 && f.color==0) g.drawImage(texture2a.getScaledInstance(a, b, c),f.x,f.y,this);//Zwei
			else if(f.value==1 && f.color==1) g.drawImage(texture2b.getScaledInstance(a, b, c),f.x,f.y,this); //Zwei
			else if(f.value==1 && f.color==2) g.drawImage(texture2c.getScaledInstance(a, b, c),f.x,f.y,this); //Zwei
			else if(f.value==1 && f.color==3) g.drawImage(texture2d.getScaledInstance(a, b, c),f.x,f.y,this); //Zwei
			else if(f.value==1 && f.color==0) g.drawImage(texture3a.getScaledInstance(a, b, c),f.x,f.y,this); //Drei
			else if(f.value==2 && f.color==1) g.drawImage(texture3b.getScaledInstance(a, b, c),f.x,f.y,this); //Drei
			else if(f.value==2 && f.color==2) g.drawImage(texture3c.getScaledInstance(a, b, c),f.x,f.y,this); //Drei
			else if(f.value==2 && f.color==3) g.drawImage(texture3d.getScaledInstance(a, b, c),f.x,f.y,this); //Drei
			else if(f.value==3 && f.color==0) g.drawImage(texture4a.getScaledInstance(a, b, c),f.x,f.y,this); //Vier
			else if(f.value==3 && f.color==1) g.drawImage(texture4b.getScaledInstance(a, b, c),f.x,f.y,this); //Vier
			else if(f.value==3 && f.color==2) g.drawImage(texture4c.getScaledInstance(a, b, c),f.x,f.y,this); //Vier
			else if(f.value==3 && f.color==3) g.drawImage(texture4d.getScaledInstance(a, b, c),f.x,f.y,this); //Vier
			else if(f.value==4 && f.color==0) g.drawImage(texture5a.getScaledInstance(a, b, c),f.x,f.y,this); //Fuenf
			else if(f.value==4 && f.color==1) g.drawImage(texture5b.getScaledInstance(a, b, c),f.x,f.y,this); //Fuenf
			else if(f.value==4 && f.color==2) g.drawImage(texture5c.getScaledInstance(a, b, c),f.x,f.y,this); //Fuenf
			else if(f.value==4 && f.color==3) g.drawImage(texture5d.getScaledInstance(a, b, c),f.x,f.y,this); //Fuenf
			else if(f.value==5 && f.color==0) g.drawImage(texture6a.getScaledInstance(a, b, c),f.x,f.y,this); //Sechs
			else if(f.value==5 && f.color==1) g.drawImage(texture6b.getScaledInstance(a, b, c),f.x,f.y,this); //Sechs
			else if(f.value==5 && f.color==2) g.drawImage(texture6c.getScaledInstance(a, b, c),f.x,f.y,this); //Sechs
			else if(f.value==5 && f.color==3) g.drawImage(texture6d.getScaledInstance(a, b, c),f.x,f.y,this); //Sechs
			else if(f.value==6 && f.color==0) g.drawImage(texture7a.getScaledInstance(a, b, c),f.x,f.y,this); //Sieben
			else if(f.value==6 && f.color==1) g.drawImage(texture7b.getScaledInstance(a, b, c),f.x,f.y,this); //Sieben
			else if(f.value==6 && f.color==2) g.drawImage(texture7c.getScaledInstance(a, b, c),f.x,f.y,this); //Sieben
			else if(f.value==6 && f.color==3) g.drawImage(texture7d.getScaledInstance(a, b, c),f.x,f.y,this); //Sieben
			else if(f.value==7 && f.color==0) g.drawImage(texture8a.getScaledInstance(a, b, c),f.x,f.y,this); //Acht
			else if(f.value==7 && f.color==1) g.drawImage(texture8b.getScaledInstance(a, b, c),f.x,f.y,this); //Acht
			else if(f.value==7 && f.color==2) g.drawImage(texture8c.getScaledInstance(a, b, c),f.x,f.y,this); //Acht
			else if(f.value==7 && f.color==3) g.drawImage(texture8d.getScaledInstance(a, b, c),f.x,f.y,this); //Acht
			else if(f.value==8 && f.color==0) g.drawImage(texture9a.getScaledInstance(a, b, c),f.x,f.y,this); //Neun
			else if(f.value==8 && f.color==1) g.drawImage(texture9b.getScaledInstance(a, b, c),f.x,f.y,this); //Neun
			else if(f.value==8 && f.color==2) g.drawImage(texture9c.getScaledInstance(a, b, c),f.x,f.y,this); //Neun
			else if(f.value==8 && f.color==3) g.drawImage(texture9d.getScaledInstance(a, b, c),f.x,f.y,this); //Neun
			else if(f.value==9 && f.color==0) g.drawImage(texture10a.getScaledInstance(a, b, c),f.x,f.y,this); //Zehn
			else if(f.value==9 && f.color==1) g.drawImage(texture10b.getScaledInstance(a, b, c),f.x,f.y,this); //Zehn
			else if(f.value==9 && f.color==2) g.drawImage(texture10c.getScaledInstance(a, b, c),f.x,f.y,this); //Zehn
			else if(f.value==9 && f.color==3) g.drawImage(texture10d.getScaledInstance(a, b, c),f.x,f.y,this); //Zehn
			else if(f.value==10 && f.color==0) g.drawImage(texture11a.getScaledInstance(a, b, c),f.x,f.y,this); //Bube
			else if(f.value==10 && f.color==1) g.drawImage(texture11b.getScaledInstance(a, b, c),f.x,f.y,this); //Bube
			else if(f.value==10 && f.color==2) g.drawImage(texture11c.getScaledInstance(a, b, c),f.x,f.y,this); //Bube
			else if(f.value==10 && f.color==3) g.drawImage(texture11d.getScaledInstance(a, b, c),f.x,f.y,this); //Bube
			else if(f.value==11 && f.color==0) g.drawImage(texture12a.getScaledInstance(a, b, c),f.x,f.y,this); //Dame
			else if(f.value==11 && f.color==1) g.drawImage(texture12b.getScaledInstance(a, b, c),f.x,f.y,this); //Dame
			else if(f.value==11 && f.color==2) g.drawImage(texture12c.getScaledInstance(a, b, c),f.x,f.y,this); //Dame
			else if(f.value==11 && f.color==3) g.drawImage(texture12d.getScaledInstance(a, b, c),f.x,f.y,this); //Dame
			else if(f.value==12 && f.color==0) g.drawImage(texture13a.getScaledInstance(a, b, c),f.x,f.y,this); //Koenig
			else if(f.value==12 && f.color==1) g.drawImage(texture13b.getScaledInstance(a, b, c),f.x,f.y,this); //Koenig
			else if(f.value==12 && f.color==2) g.drawImage(texture13c.getScaledInstance(a, b, c),f.x,f.y,this); //Koenig
			else if(f.value==12 && f.color==3) g.drawImage(texture13d.getScaledInstance(a, b, c),f.x,f.y,this); //Koenig
		}
		}
	 }
	
	}
	
		private JFrame frame;
	   
	    private void createAndShowWindow() {
	        float w1=(float) 1.02;
	    	JTextField text = new JTextField("Whatever");
	        text.setText("Wahrscheinlichkeit für 1: "+ w1);
	        JWindow win = new JWindow(frame);
	        win.setLayout(new GridLayout(0, 1));
	        getContentPane().add(text);
	        text.setLocation(100, 100);
	        text.setVisible(true);
	        win.pack();
	        win.setLocation(0, 0);
	        win.setVisible(true);
	    }
	    
	    JLabel label1;

		private void showpercentage(){
			int positiony = 60;
			int positiony2 = 60;
			
			JLabel label = new JLabel("Wahrscheinlichkeiten (in Punkten):");
			label.setFont(new Font("Serif", Font.PLAIN, 20));
		    label.setForeground(Color.WHITE);
		    this.add(label);
		    this.setSize(300, positiony);
		    this.setVisible(true);
		    
		    decision = new JLabel("Wahrscheinlichkeiten (in Punkten):");
		    decision.setFont(new Font("Serif", Font.PLAIN, 20));
		    decision.setForeground(Color.WHITE);
		    decision.locate(200, 0);
		    this.add(decision);
		    this.setSize(300, 600);
		    this.setVisible(true);

			stats[0] = new JLabel("Aktuelles Spiel: ");
			stats[1] = new JLabel("Guthaben: ");
			stats[2] = new JLabel("Einsatz: ");
			stats[3] = new JLabel("Ergebnis: ");
			stats[4] = new JLabel("Spiele gewonnen: ");
			stats[5] = new JLabel("Spiele verloren: ");
			stats[6] = new JLabel("Unentschieden: ");
			
		    for(JLabel l : stats){
		    	l.setFont(new Font("Serif", Font.PLAIN, 20));
			    l.setForeground(Color.WHITE);
			    this.add(l);
			    positiony2+=30;
			    this.setSize(230, positiony2);
			    this.setVisible(true);
		    }
		    
			
		    labels[0] = new JLabel("1 ist : ");
		    labels[1] = new JLabel("2 ist : ");
		    labels[2] = new JLabel("3 ist : ");
		    labels[3] = new JLabel("4 ist : ");
		    labels[4] = new JLabel("5 ist : ");
		    labels[5] = new JLabel("6 ist : ");
		    labels[6] = new JLabel("7 ist : ");
		    labels[7] = new JLabel("8 ist : ");
		    labels[8] = new JLabel("9 ist : ");
		    labels[9] = new JLabel("10 ist : ");

		    
		    for(JLabel l : labels){
		    	l.setFont(new Font("Serif", Font.PLAIN, 20));
			    l.setForeground(Color.WHITE);
			    l.locate(200, 0);
			    this.add(l);
			    positiony+=30;
			    this.setSize(230, positiony);
			    this.setVisible(true);
		    }
		    
		}
	
		private void changepercentage(float[] probabilities){
			for(int i=0; i<labels.length; i++){
				labels[i].setText((i+1) +" ist: "+ probabilities[i] + "%");
			}
		}
		
		
		public void  printStatistic(String sGame,String sKonto,String sBet,String sBetResult,String sWon,String sLost,String sDraw){
			 labels[0].setText(sGame);
			 labels[1].setText(sKonto);
			 labels[2].setText(sBet);
			 labels[3].setText(sBetResult);
			 labels[4].setText(sWon);
			 labels[5].setText(sLost);
			 labels[6].setText(sDraw);
		}
		
		public void printDecision(String text){
			decision.setText(text);
		}
	
	public void newPlayerCard(int color, int value, float[] probability){
		 graphicObjects[cards].type=2;
		 graphicObjects[cards].color=color;
		 graphicObjects[cards].value=value;
		 graphicObjects[cards].x=300+playercards*40;
		 graphicObjects[cards].y=490;
		 playercards++;
		 cards++;
		 changepercentage(probability);
	}
	
	public void newBankCard(int color, int value){
		 graphicObjects[cards].type=2;
		 graphicObjects[cards].color=color;
		 graphicObjects[cards].value=value;
		 graphicObjects[cards].x=600-bankcards*40;
		 graphicObjects[cards].y=140;
		 bankcards++;
		 cards++;
	}

	public void clearArray() {
		for(int i=0;i<graphicObjects.length;i++){
			 graphicObjects[i]=new Figure();
			 graphicObjects[i].drawn=0;
		 }
		for(int i=0;i<panels.length;i++){
			 
			 if(panels[i]!=null)getContentPane().remove(panels[i]);
			 panels[i]=null;
		 }
		playercards=0;
		bankcards=0;
		cards=0;
	}
	
}
