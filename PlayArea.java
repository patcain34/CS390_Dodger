//PlayArea class 

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class PlayArea extends JFrame {
	public Menu myMenu = new Menu();
	// ints
	public static int areaWidth = 1600;
	public static int areaHeight = 720;
	public int shipLives = 2;
	public int score = 0;
	// booleans
	public boolean mouse_moved;
	public boolean mouse_dragged;
	public boolean shipDead = false;
	public boolean shipPoweredUp = false;
	public boolean DrawGame = false;
	//sound
	public AudioClip song;
	public URL songPath;
	public AudioClip song2;
	public URL songPath2;
	public AudioClip song3;
	public URL songPath3;
	public AudioClip explosion;
	public URL explosionPath;
	public AudioClip explosion2;
	public URL explosionPath2;
	public AudioClip explosion3;
	public URL explosionPath3;
	public AudioClip power1;
	public URL powerPath1;
	public AudioClip power2;
	public URL powerPath2;
	public AudioClip power3;
	public URL powerPath3;
	public Image possibleShip1 = null;
	public Image possibleShip2 = null;
	public Image possibleShip3 = null;
	
	public enum STATE{
		MENU,
		GAME
	};
	
	public static STATE State = STATE.MENU;
	
	
	public PlayArea() {
		
		DrawArea myDrawArea = new DrawArea();
	
		//Load all sounds
		try{
			songPath = new URL("file:c:/DODGERGAMEIMAGES/Cantina1.mid");
			song = Applet.newAudioClip(songPath);
		}
		catch(Exception e){
		}
		try{
			songPath2 = new URL("file:c:/DODGERGAMEIMAGES/theme.mid");
			song2 = Applet.newAudioClip(songPath2);
		}
		catch(Exception e){
		}
		try{
			songPath3 = new URL("file:c:/DODGERGAMEIMAGES/dualfate.mid");
			song3 = Applet.newAudioClip(songPath3);
		}
		catch(Exception e){
		}
		try{
			explosionPath = new URL("file:c:/DODGERGAMEIMAGES/yodalaughing.wav");
			explosion = Applet.newAudioClip(explosionPath);
		}
		catch(Exception e){
		}
		try{
			explosionPath2 = new URL("file:c:/DODGERGAMEIMAGES/chewyroar.wav");
			explosion2 = Applet.newAudioClip(explosionPath2);
		}
		catch(Exception e){
		}
		try{
			explosionPath3 = new URL("file:c:/DODGERGAMEIMAGES/jabbalaughing.wav");
			explosion3 = Applet.newAudioClip(explosionPath3);
		}
		catch(Exception e){
		}
		try{
			powerPath1 = new URL("file:c:/DODGERGAMEIMAGES/R2D2_1.wav");
			power1 = Applet.newAudioClip(powerPath1);
		}
		catch(Exception e){
		}
		try{
			powerPath2 = new URL("file:c:/DODGERGAMEIMAGES/R2D2_2.wav");
			power2 = Applet.newAudioClip(powerPath2);
		}
		catch(Exception e){
		}
		try{
			powerPath3 = new URL("file:c:/DODGERGAMEIMAGES/R2D2_3.wav");
			power3 = Applet.newAudioClip(powerPath3);
		}
		catch(Exception e){
		}
		try {
			possibleShip1 = ImageIO.read(new File(
					"c:/DODGERGAMEIMAGES/x-wing.png"));
		} catch (IOException e) {

		}
		try {
			possibleShip2 = ImageIO.read(new File(
					"c:/DODGERGAMEIMAGES/AOTC fighter.png"));
		} catch (IOException e) {

		}
		try {
			possibleShip3 = ImageIO.read(new File(
					"c:/DODGERGAMEIMAGES/falcon2.png"));
		} catch (IOException e) {

		}
		
		int randomSong = randomize();
		if(randomSong == 1){
			song.loop();
		}else if(randomSong ==2){
			song2.loop();
		}else if(randomSong == 3 ){
			song3.loop();
		}

			add(myDrawArea);
		
	}
	
	public int randomize(){
		double randomVar = Math.random();
		
		if (randomVar < .33) {
			return 1;
			
		}
		else if (randomVar>= .33 && randomVar < .66) {
			return 2;
			
		}
		else if (randomVar >= .66) {
			return 3;
			
		}
		else{
			return 1;
		}
		
	}
	

	// DrawArea is where all the graphics stuff takes place!
	public class DrawArea extends JComponent {

		// Arraylists
		public ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
		public ArrayList<PowerUp> powerUpList = new ArrayList<PowerUp>();
		// ints
		public int milisec = 1;
		public int speed = 1;
		int width = PlayArea.areaWidth;
		int height = PlayArea.areaHeight;
		// Timers
		private Timer popEnemiesTimer;
		private Timer popPowersTimer;
		public Timer powerActionTimer;
		public Timer restartTimer = new Timer(3000, new RestartTimerEventAction());
		public Timer gameTimer = new Timer(milisec, new TimerEventAction());
		public Timer movementTimer = new Timer(100, new MovementTimerEventAction());
		public Timer scoreTimer = new Timer(2000, new ScoreTimerEventAction());
		// objects
		private Enemy curEnemy;
		private PowerUp curPower;
		private Ship DaShip = new Ship();
		private Image gameBackground;
		

		// Constructor
		public DrawArea() {
			addMouseListener(new MouseHandler());
			addMouseMotionListener(new MouseMotionHandler());

			// New stuff
			popEnemiesTimer = new Timer(100, new PopulateEnemies());
			popPowersTimer = new Timer(7000, new PopulatePowerUps());
			powerActionTimer = new Timer(5000, new PowerUpAction());

			Action populateEnemy = new PopulateEnemies();
			Action populatePowerUp = new PopulatePowerUps();
			
			//Set background
			gameBackground = null;
			try {
				gameBackground = ImageIO.read(new File(
						"c:/DODGERGAMEIMAGES/stars.jpg"));
			} catch (IOException e) {

			}
			
			// Start the timers to repaint constantly
	
		}

		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			
			g2.drawImage(gameBackground, 0, 0, 1600, 720, this);
			if(State == State.MENU){
				myMenu.paint(g2);
			}
			
			if(State == State.GAME){	

				
				
			g2.setColor(Color.GREEN);
			g2.setFont(new Font("Courier", Font.BOLD, 18));
			g2.drawString("Lives: " + shipLives, 1490, 650);
			g2.drawString("Score: " + score, 20, 650);
			if(score >= 100 && score < 250){
				speed = 2;
			}
			if(score >= 250 && score < 500){
				speed = 2;
				popEnemiesTimer.setDelay(75);
			}
			if(score >= 500 && score < 750){
				speed = 3;
				popEnemiesTimer.setDelay(68);
			}
			if(score >= 750 && score < 1000){
				speed = 4;
				popEnemiesTimer.setDelay(60);
			}
			if(score >= 1000 && score < 1300){
				speed = 6;
				popEnemiesTimer.setDelay(50);
			}
			if(score >= 1300){
				speed = 8;
				popEnemiesTimer.setDelay(35);
			}
			if (shipDead == true) {
				popEnemiesTimer.stop();
				popPowersTimer.stop();
				scoreTimer.stop();
				// empty arraylists
				enemyList.clear();
				powerUpList.clear();

				if (shipLives > 0) {
					g2.setColor(Color.RED);
					g2.setFont(new Font("Haettenschweiler", Font.BOLD, 75));
					g2.drawString("You Died", (1600 / 2) - 70, (720/2)-90);
					restartTimer.start();
				} else {
					g2.setColor(Color.RED);
					g2.setFont(new Font("Haettenschweiler", Font.BOLD, 75));
					g2.drawString("GAME OVER!!", (1600/2) - 80, (720/2)-90);
					restartTimer.start();
					speed = 1;
				}
			} else {

				g2.setPaint(Color.GREEN);
			
				int shipX = (int) DaShip.getX();
				int shipY = (int) DaShip.getY();
				
				g2.drawImage(DaShip.getImage(), shipX , shipY, DaShip.get_HEIGHT(), DaShip.get_WIDTH(), this);
				g2.setColor(Color.BLACK);

				for (Enemy e : enemyList) {
					e.setCoords(e.getX(), e.getY() + speed);
			
					int newX = (int) e.getX();
					int newY = (int) e.getY();
					if(e.getType() >= .66){
						g2.drawImage(e.getImage(), newX-19, newY-19, 95, 95, this);
					}
					else{
						g2.drawImage(e.getImage(), newX-14, newY+3, 75, 75, this);
					}
				}
				for (PowerUp p : powerUpList) {
					p.setCoords(p.getX(), p.getY() + speed);
					g2.setColor(Color.CYAN);
			
					int newX = (int) p.getX();
					int newY = (int) p.getY();
					
					g2.drawImage(p.getImage(), newX, newY, 25, 25, this);
				}
			}
			
			}
		}

		private class MouseHandler extends MouseAdapter {

			public void mousePressed(MouseEvent event) {
				if(State == STATE.GAME){
				if (SwingUtilities.isLeftMouseButton(event)) {
					if (DaShip.get_yCoord() > 15) {
						DaShip.set_yCoord(DaShip.get_yCoord() - 20);
						DaShip.setFrame(DaShip.get_xCoord(),
								DaShip.get_yCoord(), DaShip.get_HEIGHT(),
								DaShip.get_WIDTH());
						
					}

				}
				if (SwingUtilities.isRightMouseButton(event)) {
					if (DaShip.get_yCoord() < 1000) {
						DaShip.set_yCoord(DaShip.get_yCoord() + 20);
						DaShip.setFrame(DaShip.get_xCoord(),
								DaShip.get_yCoord(), DaShip.get_WIDTH(),
								DaShip.get_HEIGHT());
						
					}
				}
			  }
				if(State == STATE.MENU){
				  int mouseX = event.getX();
				  int mouseY = event.getY();
				
				  
				  if(mouseX >= 770 && mouseX <=770 + 150){
					  if(mouseY >= 150 && mouseY <= 200){
						  State = State.GAME;
						  gameTimer.start();

						  
						  popEnemiesTimer.start();
						  popPowersTimer.start();
						  movementTimer.start();
						  scoreTimer.start();
						  shipLives = 2;
						  score = 0;
					  }
				  }
				  if(mouseX >= 770 && mouseX <=770 + 150){
					  if(mouseY >= 250 && mouseY <= 300){
						  DaShip.setImage(possibleShip1);
					  }
				  }
				  if(mouseX >= 770 && mouseX <=770 + 150){
					  if(mouseY >= 350 && mouseY <= 400){
						  DaShip.setImage(possibleShip2);
					  }
				  }
				  if(mouseX >= 770 && mouseX <=770 + 150){
					  if(mouseY >= 450 && mouseY <= 500){
						  DaShip.setImage(possibleShip3);
					  }
				  }
			  }
				
			}

			public void mouseReleased(MouseEvent event) {

			}
		}

		private class MouseMotionHandler implements MouseMotionListener {

			public void mouseMoved(MouseEvent event) {
				if (DaShip.get_xCoord() > 15 || DaShip.get_xCoord() < 1550) {
					DaShip.set_xCoord(event.getX());
					DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(),
							DaShip.get_WIDTH(), DaShip.get_HEIGHT());
					
				}
			}

			public void mouseDragged(MouseEvent event) {

				if (SwingUtilities.isLeftMouseButton(event)) {
					if (DaShip.get_yCoord() > 15) {
						DaShip.set_yCoord(DaShip.get_yCoord() - 5);
						DaShip.setFrame(DaShip.get_xCoord(),
								DaShip.get_yCoord(), DaShip.get_WIDTH(),
								DaShip.get_HEIGHT());
						
					}
				}
				if (SwingUtilities.isRightMouseButton(event)) {
					if (DaShip.get_yCoord() < 600) {
						DaShip.set_yCoord(DaShip.get_yCoord() + 5);
						DaShip.setFrame(DaShip.get_xCoord(),
								DaShip.get_yCoord(), DaShip.get_WIDTH(),
								DaShip.get_HEIGHT());
						
					}
				}
				if (DaShip.get_xCoord() > 15 || DaShip.get_xCoord() < 1600) {
					DaShip.set_xCoord(event.getX());
					DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(),
							DaShip.get_WIDTH(), DaShip.get_HEIGHT());
					
				}
			}

		}

		// new stuff
		// ///////////////////////////////////////////////////////////////////////////////////		
		
		
		
		
		public class RestartTimerEventAction extends AbstractAction {
			public void actionPerformed(ActionEvent event) {
				
				if(shipLives > 0){
					// change state
					shipDead = false;
					shipLives--;
					// change timers
					restartTimer.stop();
					popEnemiesTimer.start();
					popPowersTimer.start();
					scoreTimer.start();
				}
				else{
					restartTimer.stop();
					enemyList.clear();
					powerUpList.clear();
					popEnemiesTimer.stop();
					popPowersTimer.stop();
					DaShip.set_xCoord(720);
					DaShip.set_yCoord(600);
					shipDead = false;
					State = State.MENU;
				}
			}
		}

		public class ScoreTimerEventAction extends AbstractAction {
			public void actionPerformed(ActionEvent event) {
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
					if (powerUpList.get(j).getY() == 900)
						powerUpList.get(j).setBool(true);
					else
						powerUpList.get(j).incCoords(0, 10);
				}

				
			}
		}

		// continually populates the array list of enemies based upon a timer
		public class PopulateEnemies extends AbstractAction {
			public void actionPerformed(ActionEvent event) {
				double randomX = (Math.random() * 1550);
				double ycoord = -25;
				curEnemy = new Enemy(randomX, ycoord);
				// Make sure enemies don't spawn inside/on each other
				for (Enemy enemy : enemyList) {
					while (curEnemy.intersects(enemy)) {
						randomX = (Math.random() * 1600);
						curEnemy.setCoords(randomX, ycoord);
					}
				}

				enemyList.add(curEnemy);
				
			}
		}

		// continually populates the array list of powerups based upon a timer
		public class PopulatePowerUps extends AbstractAction {
			public void actionPerformed(ActionEvent event) {
				double randomX = (Math.random() * 1600);
				double ycoord = -25;
				double randomType = (Math.random());
				curPower = new PowerUp(randomX, ycoord, randomType);
				// make sure power up doesnt spawn inside an enemy
				for (Enemy enemy : enemyList) {
					while (curPower.intersects(enemy)) {
						randomX = (Math.random() * 1600);
						curPower.setCoords(randomX, ycoord);
					}
				}
				powerUpList.add(curPower);
				
			}
		}

		public void removeEnemy(int i) {
			enemyList.remove(i);
			

		}

		public void removePowerUp(int i) {
			powerUpList.remove(i);
			

		}
		

		public class TimerEventAction extends AbstractAction {
			// repaints the frame every milisecond
			
			public void actionPerformed(ActionEvent event) {
				
				if(State == State.GAME){
				for (Enemy enemy : enemyList) {
					if (enemy.intersects(DaShip)) {
						shipDead = true;
						if(shipLives > 0){
							int random = randomize();
							
						
							if(random == 1){
								explosion.play();
							}else if(random == 2){
								explosion2.play();
							}else if(random == 3){
								explosion2.play();
							}
						}
						else{
							explosion3.play();
						}
	
					}
				}
				// PowerUp hit detection
				for (PowerUp powerUp : powerUpList) {
					if (powerUp.intersects(DaShip)) {
						if (powerUp.getType() < .33) {
							DaShip.shrinkShip();
							powerActionTimer.start();
							power1.play();
							
						}
						else if (powerUp.getType() >= .33 && powerUp.getType() < .66) {
							powerActionTimer.start();
							enemyList.clear();
							power2.play();
						}
						else if (powerUp.getType() >= .66) {
							score = score + 5;
							power3.play();
						}
					}
				}
				}
			repaint();
			}
		}

		public class PowerUpAction extends AbstractAction {

			public void actionPerformed(ActionEvent event) {
				DaShip.normalSize();
				powerActionTimer.stop();
			}

		}
	}
}