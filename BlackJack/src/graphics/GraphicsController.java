package graphics;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GraphicsController extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
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
	public JLabel[] labels;
	public JLabel[] stats;
	public JLabel decision;
	private int playercards=0;
	private int playercardsright=0;
	private int bankcards=0;
	private int cards=0;
	public boolean pause=false;
	//rdy grphc
	private BackgroundPanel backgroundPanel;
	private CardPanel cardPanel;
	private int positionx = 0;
	private int positiony = 0;
	   
public GraphicsController(){
	 
	 labels=new JLabel[10];
	 for(int i=0;i<labels.length;i++){
		 labels[i]=null;
	 }
	 
	 stats=new JLabel[7];
	 for(int i=0;i<stats.length;i++){
		 stats[i]=null;
	 }
	 
	 graphicObjects=new Figure[20];
	 for(int i=0;i<graphicObjects.length;i++){
		 graphicObjects[i]=new Figure();
	 }
	 
	 
	showpercentage();
	setSize(1024,768);
//	initGraphics();
	centerOnScreen(this);
	
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
			if(GraphicsController.this.pause){
				GraphicsController.this.pause=false;
				printDecision("Unpaused");
			}
			else{
				GraphicsController.this.pause=true;
				printDecision("paused");
				}
		}
	});
	
	backgroundPanel.add(closeButton);
	backgroundPanel.add(pauseButton);
	getContentPane().add(backgroundPanel);
	
	}
	 boolean first=true;  
	public void paint(Graphics g) {
		super.paint(g);
			
				cardPanel=new CardPanel(this, graphicObjects);
				getContentPane().add(cardPanel);

		if(first){
		decision.setLocation(460, 300);
		//labels
		 for(JLabel l : stats){
			    positiony+=30;
			    l.setLocation(positionx, positiony);
		    }
		 
		    positiony=0;
		    for(JLabel l : labels){
		    	positiony+=30;
			    l.setLocation(positionx+800, positiony);
		    }
		    positiony=0;
		    positionx=0;
			first=false;
			}
			this.setVisible(true);
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
			try {
				image=ImageIO.read( new File( "images/textureb.png" ) );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			if(f.value==0 && f.color==0){ 
				try {
					g.drawImage(ImageIO.read(new File( "images/texture1a.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //Ass
			}
			else if(f.value==0 && f.color==1){
				try {
					g.drawImage(ImageIO.read(new File( "images/texture1b.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //Ass
			}
			else if(f.value==0 && f.color==2)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture1c.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==0 && f.color==3)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture1d.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==1 && f.color==0)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture2a.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==1 && f.color==1)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture2b.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==1 && f.color==2)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture2c.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==1 && f.color==3)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture2d.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==1 && f.color==0)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture3a.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==2 && f.color==1)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture3b.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==2 && f.color==2)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture3c.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==2 && f.color==3)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture3d.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==3 && f.color==0)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture4a.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==3 && f.color==1)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture4b.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==3 && f.color==2)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture4c.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==3 && f.color==3)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture4d.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==4 && f.color==0)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture5a.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==4 && f.color==1)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture5b.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==4 && f.color==2)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture5c.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==4 && f.color==3)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture5d.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==5 && f.color==0)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture6a.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else if(f.value==5 && f.color==1)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture6b.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==5 && f.color==2)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture6c.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==5 && f.color==3)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture6d.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==6 && f.color==0)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture7a.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==6 && f.color==1)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture7b.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==6 && f.color==2)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture7c.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e7) {
					// TODO Auto-generated catch block
					e7.printStackTrace();
				}
			else if(f.value==6 && f.color==3)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture7d.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==7 && f.color==0)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture8a.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==7 && f.color==1)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture8b.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==7 && f.color==2)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture8c.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==7 && f.color==3)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture8d.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==8 && f.color==0)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture9a.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==8 && f.color==1)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture9b.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==8 && f.color==2)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture9c.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==8 && f.color==3)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture9d.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==9 && f.color==0)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture10a.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==9 && f.color==1)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture10b.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==9 && f.color==2)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture10c.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==9 && f.color==3)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture10d.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==10 && f.color==0)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture11a.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==10 && f.color==1)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture11b.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==10 && f.color==2)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture11c.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==10 && f.color==3)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture11d.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==11 && f.color==0)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture12a.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
			else if(f.value==11 && f.color==1)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture12b.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				}
			else if(f.value==11 && f.color==2)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture12c.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
			else if(f.value==11 && f.color==3)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture12d.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			else if(f.value==12 && f.color==0)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture13a.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			else if(f.value==12 && f.color==1)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture13b.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			else if(f.value==12 && f.color==2)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture13c.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			else if(f.value==12 && f.color==3)
				try {
					g.drawImage(ImageIO.read(new File( "images/texture13d.png" )).getScaledInstance(a, b, c),f.x,f.y,this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //Koenig
		}
		}
	 }
	
	}
	
	    JLabel label1;

		private void showpercentage(){
			JLabel label = new JLabel("Wahrscheinlichkeiten (in Punkten):");
			label.setFont(new Font("Serif", Font.PLAIN, 20));
		    label.setForeground(Color.WHITE);
		    this.add(label);
		    this.setSize(300, positiony);
		    this.setVisible(true);
		    
		    decision = new JLabel("Entscheidung");
		    decision.setFont(new Font("Serif", Font.PLAIN, 20));
		    decision.setForeground(Color.WHITE);
		    this.add(decision);
		    this.setSize(230, 230);
		    this.setVisible(true);

			stats[0] = new JLabel("Aktuelles Spiel: ");
			stats[1] = new JLabel("Guthaben: ");
			stats[2] = new JLabel("Einsatz: ");
			stats[3] = new JLabel("Ergebnis: ");
			stats[4] = new JLabel("Spiele gewonnen: ");
			stats[5] = new JLabel("Spiele verloren: ");
			stats[6] = new JLabel("Unentschieden: ");
			
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
		    
			//labels
			 for(JLabel l : stats){
			    	l.setFont(new Font("Serif", Font.PLAIN, 20));
				    l.setForeground(Color.WHITE);
				    this.add(l);
				    this.setSize(400, 230);
				    this.setVisible(true);
			    }
			 

			    for(JLabel l : labels){
			    	l.setFont(new Font("Serif", Font.PLAIN, 20));
				    l.setForeground(Color.WHITE);
				    this.add(l);
				    this.setSize(230, 230);
				    this.setVisible(true);
			    }
		}
	
		private void changepercentage(float[] probabilities){
			for(int i=0; i<labels.length; i++){
				labels[i].setText((i+1) +" ist: "+ probabilities[i] + "%");
			}
		}
		
		
		public void  printStatistic(String sGame,String sKonto,String sBet,String sBetResult,String sWon,String sLost,String sDraw){
			 stats[0].setText(sGame);
			 stats[1].setText(sKonto);
			 stats[2].setText(sBet);
			 stats[3].setText(sBetResult);
			 stats[4].setText(sWon);
			 stats[5].setText(sLost);
			 stats[6].setText(sDraw);
		}
		
		public void printDecision(String text){
			decision.setText(text);
		}
	
	public void newPlayerCard(int color, int value, float[] probability, int side){
		 graphicObjects[cards].type=2;
		 graphicObjects[cards].color=color;
		 graphicObjects[cards].value=value;
		 if(side==0){
			 graphicObjects[cards].x=300+playercards*40;
			 playercards++;
		 }
		 else{
			 graphicObjects[cards].x=600+playercardsright*40;
			 playercardsright++;
		 }
		 graphicObjects[cards].y=490;
		 cards++;
		 changepercentage(probability);
		 //printDecision("Neue Karte: " + playercards);
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
		playercards=0;
		playercardsright=0;
		bankcards=0;
		cards=0;
	}
	
}
