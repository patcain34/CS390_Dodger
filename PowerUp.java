//Patrick Cain and Adam Turner
import java.awt.geom.Rectangle2D;

public class PowerUp extends Rectangle2D.Double {
	private static final double height = 25;
	private static final double width = 25;
	private int type;
    private boolean isFinished = false;
    
	public PowerUp(double x, double y, int t) {
		super(x, y, width, height);
		type = t;
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
