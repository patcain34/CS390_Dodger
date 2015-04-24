//Patrick Cain and Adam Turner
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PowerUp extends Rectangle2D.Double {
	private static final double height = 25;
	private static final double width = 25;
	private double type;
    private boolean isFinished = false;
    private Image powerImage;
	public PowerUp(double x, double y, double t) {
		super(x, y, width, height);
		type = t;
		if(type  < .33){
			powerImage = null;
			try {
				powerImage = ImageIO.read(new File(
						"c:/DODGERGAMEIMAGES/jedi-emblem.png"));
			} catch (IOException e) {

			}
		}
		if(type >= .33 && type < .66){
			powerImage = null;
			try {
				powerImage = ImageIO.read(new File(
						"c:/DODGERGAMEIMAGES/rebel-emblem.png"));
			} catch (IOException e) {

			}
		}
		if(type >= .66){
			powerImage = null;
			try {
				powerImage = ImageIO.read(new File(
						"c:/DODGERGAMEIMAGES/empire-emblem.png"));
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
	public double getType(){
		return type;
	}
	public Image getImage(){
		return powerImage;
	}
	
}
