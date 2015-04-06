import java.awt.EventQueue;

import javax.swing.JFrame;

//Adam Turner


public class ShipTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               JFrame frame = new ShipFrame();
               frame.setTitle("ShipTest");
               frame.setSize(1920, 1080);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}