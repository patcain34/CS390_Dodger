import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JComponent;

//Adam Turner


public class Dodger{
	
   
	
	public static void main(String[] args){
    
	      EventQueue.invokeLater(new Runnable()
		         {
		            public void run()
		            {
		               JFrame frame = new PlayArea();
		               frame.setTitle("Dodger!");
		               frame.setSize(1600, 720);
		               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		               frame.setVisible(true);
		            }
		         });
		   }
}