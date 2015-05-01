import java.awt.*;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;





public class Menu{
	
	public Rectangle play = new Rectangle(800-30, 150, 150, 50);
	public Rectangle quit = new Rectangle(800-30, 450, 150, 50);
	public Rectangle altShip = new Rectangle(800-30, 250, 150, 50);
	public Rectangle altShip2 = new Rectangle(800-30, 350, 150, 50);
	
	
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		Font title = new Font("helvetica", Font.BOLD, 100);
		g2.setFont(title);
		g2.setColor(Color.YELLOW);
		g2.drawString("STAR WARS", 800-250, 100);
		
		Font buttons = new Font("helvetica", Font.BOLD, 30);
		g2.setFont(buttons);
		g2.drawString("PLAY", play.x +34, play.y+35);
		g2.draw(play);
		g2.drawString("X-WING", altShip.x +34, altShip.y+35);
		g2.draw(altShip);
		g2.drawString("AOTC", altShip2.x +34, altShip2.y+35);
		g2.draw(altShip2);
		
	}




	
	


}


	