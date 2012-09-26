package graphics;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;

import KnowledgeSystem.Agent;
import KnowledgeSystem.Player;

import controller.BlackJack;
import data.CardSet;
import data.Rules;


public class GraphicsController extends JWindow {

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

	
	class Figure {
		int type=0;
		int value=0;
		int color=0;
		int visible=0;
		int x=0;
		int y=0;
	}
	// Array for Objects that are to be painted (z index is rising)
	public Figure[] graphicObjects;
	private int playercards=0;
	private int bankcards=0;
	private int cards=0;
	   
public GraphicsController(){
	 createAndShowWindow(); 
	 
	 
	 graphicObjects=new Figure[52];
	 for(int i=0;i<graphicObjects.length;i++){
		 graphicObjects[i]=new Figure();
	 }
	 newBankCard(2, 10);
	 newBankCard(2, 10);
	 newPlayerCard(4, 8);
	 newPlayerCard(3, 2);
	 newPlayerCard(2, 1);
	 newPlayerCard(1, 13);
	setSize(1024,768);
	initGraphics();
	centerOnScreen(this);
	
	BackgroundPanel backgroundPanel = new BackgroundPanel(this);
	JButton closeButton = new JButton("Close");
	
	closeButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			GraphicsController.this.setVisible(false);
			GraphicsController.this.dispose();
			System.exit(0);
		}
	});
	backgroundPanel.add(closeButton);
	getContentPane().add(backgroundPanel);
}
	   
	public void paint(Graphics g) {
		
	super.paint(g);

	for (Figure f : graphicObjects) {
		if(f.type==1){}
		if(f.type==2){
			if(f.value==1 && f.color==1) g.drawImage(texture1a, f.x,f.y, this); //Ass
			if(f.value==1 && f.color==2) g.drawImage(texture1b, f.x,f.y, this); //Ass
			if(f.value==1 && f.color==3) g.drawImage(texture1c, f.x,f.y, this); //Ass
			if(f.value==1 && f.color==4) g.drawImage(texture1d, f.x,f.y, this); //Ass
			if(f.value==2 && f.color==1) g.drawImage(texture2a, f.x,f.y, this);//Zwei
			if(f.value==2 && f.color==2) g.drawImage(texture2b, f.x,f.y, this); //Zwei
			if(f.value==2 && f.color==3) g.drawImage(texture2c, f.x,f.y, this); //Zwei
			if(f.value==2 && f.color==4) g.drawImage(texture2d, f.x,f.y, this); //Zwei
			if(f.value==3 && f.color==1) g.drawImage(texture3a, f.x,f.y, this); //Drei
			if(f.value==3 && f.color==2) g.drawImage(texture3b, f.x,f.y, this); //Drei
			if(f.value==3 && f.color==3) g.drawImage(texture3c, f.x,f.y, this); //Drei
			if(f.value==3 && f.color==4) g.drawImage(texture3d, f.x,f.y, this); //Drei
			if(f.value==4 && f.color==1) g.drawImage(texture4a, f.x,f.y, this); //Vier
			if(f.value==4 && f.color==2) g.drawImage(texture4b, f.x,f.y, this); //Vier
			if(f.value==4 && f.color==3) g.drawImage(texture4c, f.x,f.y, this); //Vier
			if(f.value==4 && f.color==4) g.drawImage(texture4d, f.x,f.y, this); //Vier
			if(f.value==5 && f.color==1) g.drawImage(texture5a, f.x,f.y, this); //Fuenf
			if(f.value==5 && f.color==2) g.drawImage(texture5b, f.x,f.y, this); //Fuenf
			if(f.value==5 && f.color==3) g.drawImage(texture5c, f.x,f.y, this); //Fuenf
			if(f.value==5 && f.color==4) g.drawImage(texture5d, f.x,f.y, this); //Fuenf
			if(f.value==6 && f.color==1) g.drawImage(texture6a, f.x,f.y, this); //Sechs
			if(f.value==6 && f.color==2) g.drawImage(texture6b, f.x,f.y, this); //Sechs
			if(f.value==6 && f.color==3) g.drawImage(texture6c, f.x,f.y, this); //Sechs
			if(f.value==6 && f.color==4) g.drawImage(texture6d, f.x,f.y, this); //Sechs
			if(f.value==7 && f.color==1) g.drawImage(texture7a, f.x,f.y, this); //Sieben
			if(f.value==7 && f.color==2) g.drawImage(texture7b, f.x,f.y, this); //Sieben
			if(f.value==7 && f.color==3) g.drawImage(texture7c, f.x,f.y, this); //Sieben
			if(f.value==7 && f.color==4) g.drawImage(texture7d, f.x,f.y, this); //Sieben
			if(f.value==8 && f.color==1) g.drawImage(texture8a, f.x,f.y, this); //Acht
			if(f.value==8 && f.color==2) g.drawImage(texture8b, f.x,f.y, this); //Acht
			if(f.value==8 && f.color==3) g.drawImage(texture8c, f.x,f.y, this); //Acht
			if(f.value==8 && f.color==4) g.drawImage(texture8d, f.x,f.y, this); //Acht
			if(f.value==9 && f.color==1) g.drawImage(texture9a, f.x,f.y, this); //Neun
			if(f.value==9 && f.color==2) g.drawImage(texture9b, f.x,f.y, this); //Neun
			if(f.value==9 && f.color==3) g.drawImage(texture9c, f.x,f.y, this); //Neun
			if(f.value==9 && f.color==4) g.drawImage(texture9d, f.x,f.y, this); //Neun
			if(f.value==10 && f.color==1) g.drawImage(texture10a, f.x,f.y, this); //Zehn
			if(f.value==10 && f.color==2) g.drawImage(texture10b, f.x,f.y, this); //Zehn
			if(f.value==10 && f.color==3) g.drawImage(texture10c, f.x,f.y, this); //Zehn
			if(f.value==10 && f.color==4) g.drawImage(texture10d, f.x,f.y, this); //Zehn
			if(f.value==11 && f.color==1) g.drawImage(texture11a, f.x,f.y, this); //Bube
			if(f.value==11 && f.color==2) g.drawImage(texture11b, f.x,f.y, this); //Bube
			if(f.value==11 && f.color==3) g.drawImage(texture11c, f.x,f.y, this); //Bube
			if(f.value==11 && f.color==4) g.drawImage(texture11d, f.x,f.y, this); //Bube
			if(f.value==12 && f.color==1) g.drawImage(texture12a, f.x,f.y, this); //Dame
			if(f.value==12 && f.color==2) g.drawImage(texture12b, f.x,f.y, this); //Dame
			if(f.value==12 && f.color==3) g.drawImage(texture12c, f.x,f.y, this); //Dame
			if(f.value==12 && f.color==4) g.drawImage(texture12d, f.x,f.y, this); //Dame
			if(f.value==13 && f.color==1) g.drawImage(texture13a, f.x,f.y, this); //Koenig
			if(f.value==13 && f.color==2) g.drawImage(texture13b, f.x,f.y, this); //Koenig
			if(f.value==13 && f.color==3) g.drawImage(texture13c, f.x,f.y, this); //Koenig
			if(f.value==13 && f.color==4) g.drawImage(texture13d, f.x,f.y, this); //Koenig
		}
	 }
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
		BufferedImage image = null;

		BackgroundPanel(JWindow window) {
			try {
				image = ImageIO.read( new File( "images/textureb.png" ) );
			} catch (IOException e) {
				System.out.println("Hintergrund konnte nciht geladen werden da file nicht vorhanden!");
				e.printStackTrace();
			}
		}

		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, this);
		}
	}
	
	    private JFrame frame;
	   
	    private void createAndShowWindow() {
	        JTextField text = new JTextField("Whatewer");
	        JWindow win = new JWindow(frame);
	        win.setLayout(new GridLayout(0, 1));
	        this.add(text);
	        win.pack();
	        win.setLocation(0, 0);
	        win.setVisible(true);
	    }


	
	
	public void newPlayerCard(int color, int value){
		 graphicObjects[cards].type=2;
		 graphicObjects[cards].color=color;
		 graphicObjects[cards].value=value;
		 graphicObjects[cards].x=300+playercards*40;
		 graphicObjects[cards].y=410;
		 playercards++;
		 cards++;
	}
	
	public void newBankCard(int color, int value){
		 graphicObjects[cards].type=2;
		 graphicObjects[cards].color=color;
		 graphicObjects[cards].value=value;
		 graphicObjects[cards].x=600-bankcards*40;
		 graphicObjects[cards].y=100;
		 bankcards++;
		 cards++;
	}

	public void clearArray() {
		for(int i=0;i<graphicObjects.length;i++){
			 graphicObjects[i]=new Figure();
		 }
	}
	
}
