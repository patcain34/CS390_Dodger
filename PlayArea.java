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

import javax.swing.*;




/**
 * A frame containing a panel for testing mouse operations
 */
public class PlayArea extends JFrame{
	private Timer gameTimer;
	private int milisec = 1;
    private JPanel buttonPanel;
    private Rectangle2D r = new Rectangle2D.Double(20,20, 75, 75);
    
    
    public PlayArea(){
      buttonPanel = new JPanel();
	  DrawArea myDrawArea = new DrawArea();    
      add(myDrawArea);
    }
    
   public class DrawArea extends JComponent{
	   Ship DaShip = new Ship();
//	   public ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
//	   public ArrayList<PowerUp> powerUpList = new ArrayList<PowerUp>();
	   public DrawArea(){
		   addMouseListener(new MouseHandler());
		   addMouseMotionListener(new MouseMotionHandler()); 
	   
	   
	   //Fill the ArrayList of enemies and power ups
	   
	   
	   
	   
	   
	   
	   }
	   
	   public void paint(Graphics g){
		   Graphics2D g2 = (Graphics2D) g;
		   g2.setColor(Color.BLACK);
		   g2.fillRect(0, 0, getWidth(), getHeight());
		   
		   g2.setPaint(Color.GREEN);
		   g2.draw(DaShip);
	   }
   
     
    private class MouseHandler extends MouseAdapter{
		 private boolean mouse_pressed;
	     
		 public void mousePressed(MouseEvent event) {
		    
		 	mouse_pressed = true;
		 	if(SwingUtilities.isLeftMouseButton(event)){		
		 		if(DaShip.get_yCoord() > 15){
		 				DaShip.set_yCoord(DaShip.get_yCoord()+10);
		 				DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(), DaShip.get_HEIGHT(), DaShip.get_WIDTH());
		 				repaint();
		 		}
	         
		 	}
		 	if(SwingUtilities.isRightMouseButton(event)){
		 		if(DaShip.get_yCoord() < 1000){
	 				DaShip.set_yCoord(DaShip.get_yCoord()-10);
	 				DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(), DaShip.get_WIDTH(), DaShip.get_HEIGHT());
	 				repaint();
	 		}
		 	}    
		 }
		 public void mouseReleased(MouseEvent event){
			 mouse_pressed = false;
		 }
	   }
	 private class MouseMotionHandler implements MouseMotionListener{
	      
		 public void mouseMoved(MouseEvent event){
			 if(DaShip.get_xCoord() > 15 || DaShip.get_xCoord() < 1550){
		    	  DaShip.set_xCoord(event.getX());
		    	  DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(), DaShip.get_WIDTH(), DaShip.get_HEIGHT());
		    	  repaint();
		    	  }
	      }
	 
	      public void mouseDragged(MouseEvent event){
	    	  
	    	  if(SwingUtilities.isLeftMouseButton(event)){
	    		  if(DaShip.get_yCoord() > 15){
			 			DaShip.set_yCoord(DaShip.get_yCoord()-5);
			 			DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(), DaShip.get_WIDTH(), DaShip.get_HEIGHT());
			 			repaint();
	    		  }
	    	  }
	    	  if(SwingUtilities.isRightMouseButton(event)){
	    		  if(DaShip.get_yCoord() <600){
	    			  DaShip.set_yCoord(DaShip.get_yCoord()+5);
	    			  DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(), DaShip.get_WIDTH(), DaShip.get_HEIGHT());
	    			  repaint();
	    		  }
	    	  }
	    	  if(DaShip.get_xCoord() > 15 ||DaShip.get_xCoord() < 1600){
	    	  DaShip.set_xCoord(event.getX());
	    	  DaShip.setFrame(DaShip.get_xCoord(), DaShip.get_yCoord(), DaShip.get_WIDTH(), DaShip.get_HEIGHT());
	    	  repaint();
	    	  }
	      }
	   
}
    
    
    
    
    
    
    
    
    
    
    
  
    
    
    
    public class TimerEventAction extends AbstractAction		       
    {
	
	public void actionPerformed(ActionEvent event)
	{
	

	    
	}
    }

}}

