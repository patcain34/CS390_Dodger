//Ship class

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ship extends Rectangle2D.Double {
	private static int HEIGHT = 45;
	private static int WIDTH = 45;
	private double xCoord = 720;
	private double yCoord = 600;
	private Image shipImage;
	public Ship() {
		super(720, 300, WIDTH, HEIGHT);
		shipImage = null;
		try {
			shipImage = ImageIO.read(new File(
					"c:/DODGERGAMEIMAGES/falcon2.png"));
		} catch (IOException e) {

		}
	
	}

	public void set_xCoord(double newX) {
		xCoord = newX;
	}

	public void set_yCoord(double newY) {
		yCoord = newY;
	}

	public double get_xCoord() {
		return xCoord;
	}

	public double get_yCoord() {
		return yCoord;
	}

	public int get_HEIGHT() {
		return HEIGHT;
	}

	public int get_WIDTH() {
		return WIDTH;
	}

	// Addition for PowerUp Actions
	public void normalSize() {
		HEIGHT = 45;
		WIDTH = 45;
	}

	// Addition for PowerUp Actions
	public void shrinkShip() {
		HEIGHT = 25;
		WIDTH = 25;
	}
	public Image getImage(){
		return shipImage;
	}

}