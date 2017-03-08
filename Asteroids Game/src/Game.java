/*
CLASS: Game
DESCRIPTION: A painted canvas in its own window, updated every tenth second.
USAGE: Extended by Asteroids.
NOTE: You don't need to understand the details here, no fiddling neccessary.
*/
import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

abstract class Game extends Canvas {
  protected boolean on = true;
  protected int width, height;
  protected Image buffer;
  Frame frame;
  
	public Game(String name, int inWidth, int inHeight) {
	  width = inWidth;
	  height = inHeight;
	  
	  // Frame can be read as 'window' here.
    frame = new Frame(name);
    frame.add(this);
    frame.setSize(width,height);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.addWindowListener(new WindowAdapter() { 
      public void windowClosing(WindowEvent e) {System.exit(0);} 
    });
    
    buffer = createImage(width, height);
	}
  
  // 'paint' will be called every tenth of a second that the game is on.
	abstract public void paint(Graphics brush);
  
  // 'update' paints to a buffer then to the screen, then waits a tenth of
  // a second before repeating itself, assuming the game is on. This is done
  // to avoid a choppy painting experience if repainted in pieces.
  public void update(Graphics brush) {
    paint(buffer.getGraphics());
		brush.drawImage(buffer,0,0,this);
    if (on) {sleep(10); repaint();}
  }
  
  // 'sleep' is a simple helper function used in 'update'.
  protected void sleep(int time) {
    try {Thread.sleep(time);} catch(Exception exc){};
  }
  
  public void close()
  {
	  frame.dispose();
  }
  
  public void showDialog()
  {
	    JDialog dialog = new JDialog(this.frame, "Restart");
		dialog.setModal(false);
		dialog.setLocation(297, 275);
		dialog.setSize(215, 110);
		dialog.setLayout(new FlowLayout());
		JTextArea text = new JTextArea("Would you like to start a new game?\n");
		text.setOpaque(false);
		text.setEditable(false);
		text.setSize(400, 15);
		dialog.add(text);
		
		JButton yes = new JButton("Yes");
		yes.setSize(25, 25);
		yes.addActionListener(
				new ActionListener() {
					
					public void actionPerformed(ActionEvent e){
						
						close();
						new Asteroids();
					}
				});
		
		JButton no = new JButton("No");
		no.setSize(25, 25);
		no.addActionListener(
				new ActionListener() {
					
					public void actionPerformed(ActionEvent e){
						
						System.exit(1);
					}
				});
		
		dialog.add(yes);
		dialog.add(no);
		dialog.show();
  }
}