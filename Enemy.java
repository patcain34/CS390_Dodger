//Patrick Cain and Adam Turner
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Rectangle2D.Double {
	private static final double height = 50;
	private static final double width = 50;
    private boolean isFinished = false;
	private Image enemyImage;
	private double type = Math.random();
	
    public Enemy(double x, double y) {
		super(x, y, width, height);
		if(type  < .33){
			enemyImage = null;
			try {
				enemyImage = ImageIO.read(new File(
						"c:/DODGERGAMEIMAGES/TieFighter-icon.png"));
			} catch (IOException e) {

			}
		}
		if(type >= .33 && type < .66){
			enemyImage = null;
			try {
				enemyImage = ImageIO.read(new File(
						"c:/DODGERGAMEIMAGES/vader-ship2.png"));
			} catch (IOException e) {

			}
		}
		if(type >= .66){
			enemyImage = null;
			try {
				enemyImage = ImageIO.read(new File(
						"c:/DODGERGAMEIMAGES/asteroid-icon2.png"));
			} catch (IOException e) {

			}
		}
	
	
	}

	public void incCoords(double incX, double incY) {
		x = x + incX;
		y = y + incY;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setCoords(double xCoord, double yCoord) {
		x = xCoord;
		y = yCoord;
	}

	public boolean getBool(){
		return isFinished;
	}
	
	public void setBool(boolean bool){
		isFinished = bool;
	}
	
	public Image getImage(){
		return enemyImage;
	}
	public double getType(){
		return type;
	}



}
