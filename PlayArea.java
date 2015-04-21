//PlayArea class 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;






public class PlayArea extends JFrame{ 
	private JPanel buttonPanel;
    private Rectangle2D r = new Rectangle2D.Double(20,20, 75, 75);
    //ints
    public static int areaWidth = 1600;
    public static int areaHeight =720;
    public int shipLives = 2;
    public int score = 0;
    //booleans
    public boolean mouse_moved;
    public boolean mouse_dragged;
    public boolean shipDead = false;
    public boolean shipPoweredUp = false;
   
    
    public PlayArea(){
      buttonPanel = new JPanel();
	  DrawArea myDrawArea = new DrawArea();
      add(myDrawArea);
    }
   
    
    //DrawArea is where all the graphics stuff takes place!
   public class DrawArea extends JComponent{
	  
	   //Arraylists
	   public ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	   public ArrayList<PowerUp> powerUpList = new ArrayList<PowerUp>();
	   //ints 
	   int milisec = 1;
	   int width = PlayArea.areaWidth;
	   int height = PlayArea.areaHeight;
	   //Timers
	   private Timer popEnemiesTimer;
	   private Timer popPowersTimer;
	   public Timer restartTimer  = new Timer(3000, new RestartTimerEventAction());
	   public Timer gameTimer = new Timer(milisec, new TimerEventAction());
	   public Timer movementTimer = new Timer(100, new MovementTimerEventAction());
	   public Timer scoreTimer = new Timer(2000, new ScoreTimerEventAction());
	   //objects
	   private Enemy curEnemy;
	   private PowerUp curPower;
	   private Ship DaShip = new Ship();
	   BufferedImage tieFighter;
	   //Constructor
	   public DrawArea(){
		   addMouseListener(new MouseHandler());
		   addMouseMotionListener(new MouseMotionHandler()); 
		   
		   //New stuff
		   popEnemiesTimer = new Timer(100, new PopulateEnemies());
		   popPowersTimer = new Timer(7000, new PopulatePowerUps());
	   
		   Action populateEnemy = new PopulateEnemies();
		   Action populatePowerUp = new PopulatePowerUps();
	   
		   tieFighter = null;
		   try {
			   tieFighter = ImageIO.read(new File("c:/DODGERGAMEIMAGES/Tie-Fighter-03-icon.png"));
		   } catch (IOException e){
			   
		   }
		   //Start the timers to repaint constantly
		   gameTimer.start();
		   popEnemiesTimer.start();
		   popPowersTimer.start();
		   movementTimer.start();
		   scoreTimer.start();

	   }
	   
	   public void paint(Graphics g){
		   Graphics2D g2 = (Graphics2D) g;
		   g2.setColor(Color.BLACK);
		   g2.fillRect(0, 0, getWidth(), getHeight());
		   g2.setColor(Color.GREEN);
		   g2.drawString("Lives: "+ shipLives, 1500, 650);
		   g2.drawString("Score: "+ score, 20, 650);
		   int moveImage = 1;
		   moveImage = moveImage+10;
		 //  g2.drawImage(tieFighter, 800, moveImage, this);
		   if(shipDead == true){
			  popEnemiesTimer.stop();
			  popPowersTimer.stop();
			  scoreTimer.stop(); 
			  //empty arraylists
			  enemyList.clear();
		      powerUpList.clear();
			  
			  if(shipLives > 0){ 
				 g2.setColor(Color.RED);
				 g2.drawString("You Died", 1600/2, 720/2);   
				 restartTimer.start();
			  }
			  else{
				 g2.setColor(Color.RED);
				 g2.drawString("GAME OVER!!", 1600/2, 720/2);
			 }
		   }
		   else{
		   
		   
			 
			   g2.setPaint(Color.GREEN);
			   g2.fill(DaShip);
			   g2.draw(DaShip);
			   g2.setColor(Color.RED);
	
			   for(Enemy e: enemyList){
				   e.setCoords(e.getX(),e.getY()+1);
				   g2.fill(e);
				   g2.draw(e);
			   }
			   for(PowerUp p: powerUpList){
				   p.setCoords(p.getX(), p.getY()+1);
				   g2.setColor(Color.CYAN);
				   g2.fill(p);
				   g2.draw(p);
			   }
		   }
		   
	   }
		   
   
     
    private class MouseHandler extends MouseAdapter{
		
	     
		 public void mousePressed(MouseEvent event) {
		    
		 	
		 	if(SwingUtilities.isLeftMouseButton(event)){		
		 		if(DaShip.get_yCoord() > 15){
		 				DaShip.set_yCoord(DaShip.get_yCoord()-20);
		 				DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(), DaShip.get_HEIGHT(), DaShip.get_WIDTH());
//		 				repaint();
		 		}
	         
		 	}
		 	if(SwingUtilities.isRightMouseButton(event)){
		 		if(DaShip.get_yCoord() < 1000){
	 				DaShip.set_yCoord(DaShip.get_yCoord()+20);
	 				DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(), DaShip.get_WIDTH(), DaShip.get_HEIGHT());
//	 				repaint();
	 		}
		 	}    
		 }
		 public void mouseReleased(MouseEvent event){
			 
		 }
	   }
	 private class MouseMotionHandler implements MouseMotionListener{
	      
		 public void mouseMoved(MouseEvent event){
			 if(DaShip.get_xCoord() > 15 || DaShip.get_xCoord() < 1550){
		    	  DaShip.set_xCoord(event.getX());
		    	  DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(), DaShip.get_WIDTH(), DaShip.get_HEIGHT());
//		    	  repaint();
		    	  }
	      }
	 
	      public void mouseDragged(MouseEvent event){
	    	  
	    	  if(SwingUtilities.isLeftMouseButton(event)){
	    		  if(DaShip.get_yCoord() > 15){
			 			DaShip.set_yCoord(DaShip.get_yCoord()-5);
			 			DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(), DaShip.get_WIDTH(), DaShip.get_HEIGHT());
//			 			repaint();
	    		  }
	    	  }
	    	  if(SwingUtilities.isRightMouseButton(event)){
	    		  if(DaShip.get_yCoord() <600){
	    			  DaShip.set_yCoord(DaShip.get_yCoord()+5);
	    			  DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(), DaShip.get_WIDTH(), DaShip.get_HEIGHT());
//	    			  repaint();
	    		  }
	    	  }
	    	  if(DaShip.get_xCoord() > 15 ||DaShip.get_xCoord() < 1600){
	    	  DaShip.set_xCoord(event.getX());
	    	  DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(), DaShip.get_WIDTH(), DaShip.get_HEIGHT());
//	    	  repaint();
	    	  }
	      }
	   
	 }
 
	 //new stuff
/////////////////////////////////////////////////////////////////////////////////////

	 public class RestartTimerEventAction extends AbstractAction{
		 public void actionPerformed(ActionEvent event){
			 //change state
			 shipDead = false;
			 shipLives--;
			 //change timers
			 restartTimer.stop();
			 popEnemiesTimer.start();
			 popPowersTimer.start();
			 scoreTimer.start();
		 }
	 }
	 
	 public class ScoreTimerEventAction extends AbstractAction{
		 public void actionPerformed(ActionEvent event){
			 score = score + 10;
		 }
	 }
	 
	
	 
	 
	 
	 
	 
	 public class MovementTimerEventAction extends AbstractAction {

			public void actionPerformed(ActionEvent event) {
				// removes enemies that were flagged for reaching end of screen
				for (int i = 0; i < enemyList.size(); i++) {
					if (enemyList.get(i).getBool() == true) {
						removeEnemy(i);
					}
				}

				// flags enemy if at end of screen if not, increment enemies
				// coordinates
				for (int i = 0; i < enemyList.size(); i++) {
					System.out.println("Move enemy");
					if (enemyList.get(i).getY() == 900)
						enemyList.get(i).setBool(true);
					else
						enemyList.get(i).incCoords(0, 10);
				}
				// removes powerups that were flagged for reaching end of screen
				for (int j = 0; j < powerUpList.size(); j++) {
					if (powerUpList.get(j).getBool() == true) {
						removePowerUp(j);
					}
				}
				// flags enemy if at end of screen if not, increment enemies
				// coordinates
				for (int j = 0; j < powerUpList.size(); j++) {
					System.out.println("Move powerup");
					if (powerUpList.get(j).getY() == 900)
						powerUpList.get(j).setBool(true);
					else
						powerUpList.get(j).incCoords(0, 10);
				}

	//			repaint();
			}
		}
    
    
   
	// continually populates the array list of enemies based upon a timer
		public class PopulateEnemies extends AbstractAction {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Populate enemy");
				double randomX = (Math.random() * 1550);
				double ycoord = -10;
				curEnemy = new Enemy(randomX, ycoord);
				//Make sure enemies don't spawn inside/on each other
				for(Enemy enemy : enemyList){
					while(curEnemy.intersects(enemy)){
						randomX = (Math.random()*1600);
						curEnemy.setCoords(randomX, ycoord);
					}
				}
				
				
				enemyList.add(curEnemy);
//				repaint();
			}
		}

		// continually populates the array list of powerups based upon a timer
		public class PopulatePowerUps extends AbstractAction {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Populate powerup");
				double randomX = (Math.random() * 1600);
				double ycoord = -5;
				curPower = new PowerUp(randomX, ycoord, 1);
				//make sure power up doesnt spawn inside an enemy
				for(Enemy enemy : enemyList){
					while(curPower.intersects(enemy)){
						randomX = (Math.random()*1600);
						curPower.setCoords(randomX, ycoord);
					}
				}
				powerUpList.add(curPower);
//				repaint();
			}
		}
	 
		public void removeEnemy(int i) {
			enemyList.remove(i);
//			repaint();

		}

		public void removePowerUp(int i) {
			powerUpList.remove(i);
//			repaint();

		}
	 
	 
     //repaints the frame every milisecond
    public class TimerEventAction extends AbstractAction{
	
	public void actionPerformed(ActionEvent event){
		//Enemy hit detection
		for(Enemy enemy : enemyList){
			if(enemy.intersects(DaShip)){
				shipDead = true;
			}
		}
		//PowerUp hit detection
		for(PowerUp powerup : powerUpList){
			if(powerup.intersects(DaShip))
				shipPoweredUp = true;
		}
		repaint();

	    }
    }
  }
}
