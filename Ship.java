//Ship class

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;



public class Ship extends Rectangle2D.Double{
	private static final int HEIGHT = 45;
	private static final int WIDTH = 45;
	private double xCoord = 720;
	private double yCoord = 600;
	public Ship(){
		super(720, 300, WIDTH, HEIGHT);
	}
	
	public void set_xCoord(double newX){
		xCoord = newX;
	}
	public void set_yCoord(double newY){
		yCoord = newY;
	}
	
	public double get_xCoord(){
		return xCoord;
	}
	public double get_yCoord(){
		return yCoord;
	}
	public int get_HEIGHT(){
		return HEIGHT;
	}
	public int get_WIDTH(){
		return WIDTH;
	}
	
}