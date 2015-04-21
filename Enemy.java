//Patrick Cain and Adam Turner
import java.awt.geom.Rectangle2D;

public class Enemy extends Rectangle2D.Double {
	private static final double height = 70;
	private static final double width = 70;
    private boolean isFinished = false;
	public Enemy(double x, double y) {
		super(x, y, width, height);
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
	



}
