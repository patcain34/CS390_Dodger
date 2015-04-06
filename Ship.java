//Ship class

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;



public class Ship extends JComponent{
	private static final int HEIGHT = 50;
	private static final int WIDTH = 50;
	private int xCoord = 720;
	private int yCoord = 1000;
	private static final Rectangle2D DaShip = new Rectangle2D.Double(720,369, HEIGHT, WIDTH);
	private Timer shipTimer;
	private int milisec = 5;
	public Ship(){
		shipTimer = new Timer(milisec, new TimerEventAction());
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
		 
		shipTimer.start();
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.GREEN);
		g2.fill(DaShip);
		g2.draw(DaShip);
	}
	
	 private class MouseHandler extends MouseAdapter
	   {
	     
		 	public void mouseClicked(MouseEvent event) {
	         
//		 	if(SwingUtilities.isLeftMouseButton(event)){	
//		 		if(yCoord > 15){
//		 			yCoord = yCoord - 15;
//		 			DaShip.setFrame(xCoord, yCoord, HEIGHT, WIDTH);
//		 			repaint();
//	         }
//		 	}
//		 	if(SwingUtilities.isRightMouseButton(event)){
//		 		//if(yCoord < 1000){
//		 			yCoord = yCoord + 15;
//		 			DaShip.setFrame(xCoord, yCoord, HEIGHT, WIDTH);
//		 			repaint();
		 		//}
//		 	}
	         
		 	}
	   }
	 private class MouseMotionHandler implements MouseMotionListener{
	      
		 public void mouseMoved(MouseEvent event){
	    	  xCoord = event.getX();
	    	  yCoord = event.getY();
	    	  DaShip.setFrame(xCoord, yCoord, HEIGHT, WIDTH);
	    	  repaint();
	      }
	 
	      public void mouseDragged(MouseEvent event){
	    	  
	      }
	   
}
	    public class TimerEventAction extends AbstractAction		       
	    {
		
		public void actionPerformed(ActionEvent event)
		{
		    
		//	if(yCoord > 370){
				
		//	yCoord = yCoord -25;
		//	DaShip.setFrame(xCoord, yCoord, HEIGHT, WIDTH);
		//    repaint();

		    
	//	}
	    } }
}