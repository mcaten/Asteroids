/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
*/
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

class Asteroids extends Game {
	
	private Point[] points = {new Point(10, 0), new Point(0, 20), new Point(10, 15), new Point(20, 20)};
	private Point[] apoints1 = {new Point(10, 0), new Point(20, 10), new Point(40, 0), new Point(50, 20), new Point(40, 40), new Point(10, 40), new Point(0, 10)};
	private Point[] apoints2 = {new Point(0, 0), new Point(50, 10), new Point(65, 25), new Point(50, 45), new Point(37, 48), new Point(30, 40), new Point(20, 43)};
	private Point[] apoints3 = {new Point(0, 30), new Point(15, 0), new Point(40, 15), new Point(42, 45), new Point(25, 55), new Point(10, 40)};
	private Point[][] asteroidPoints = {apoints1, apoints2, apoints3};
	private Ship ship;
	private Asteroid[] asteroids = new Asteroid[10];
	private Star[] stars = new Star[125];
	
	private boolean gameOver = false;
	private Bullet b;
	private int score = 0;
	private int times = 0;
	private Clip audioClip;
	
  public Asteroids() {
    super("Asteroids!",800,600);
    setFocusable(true);
    requestFocus();
    ship = new Ship(points, new Point(400, 300), 0.0);
  
    try 
    {
    	File audioFile = new File("src\\POl-star-commando-short.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
		
		AudioFormat format = audioStream.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		
		try 
		{
			audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);
			audioClip.start();
			audioClip.loop(Clip.LOOP_CONTINUOUSLY);
			
		} 
		
		catch (LineUnavailableException e1) 
		{
			e1.printStackTrace();
		}	
	} 
    
    catch (UnsupportedAudioFileException e1)
    {
		e1.printStackTrace();
	} 
    
    catch (IOException e1) 
    {
		e1.printStackTrace();
	}
    
    KeyListener l = new KeyListener()
    {

    	@Override
        public void keyPressed(KeyEvent e) {
        	   int key = e.getKeyCode();
        	   			
        	   if(key == KeyEvent.VK_UP && !gameOver)
        	   {
        		    ship.move();
        	   }
        	   
        	   else if(key == KeyEvent.VK_LEFT && !gameOver)
        	   {
        		   ship.rotate(-5);
        	   }
        	   
        	   else if(key == KeyEvent.VK_RIGHT && !gameOver)
        	   {
        		   ship.rotate(5);
        	   }
        	   
        	   else if(key == KeyEvent.VK_SPACE && !gameOver)
        	   {
        		   Point p = new Point(ship.getTip().x, ship.getTip().y);
        		   b = new Bullet(p, 5);      		   
        	   }
        	   			
        }

        @Override
        public void keyReleased(KeyEvent e) {
        	   		
        }

        @Override
        public void keyTyped(KeyEvent e) {
        	   			
        }

     };
        	   	
     addKeyListener(l);
     
     for(int count = 0; count < asteroids.length; count++)
     {
    	 asteroids[count] = new Asteroid(asteroidPoints[(int) (Math.random() * 3)], new Point((int)(Math.random() * this.width), (int)(Math.random() * this.height)), 0.0);
     }
     
     for(int i = 0; i < stars.length; i++)
     {
     	Point p = new Point((int) (Math.random() * this.width) + 1, (int) (Math.random() * this.height) + 1);
     	stars[i] = new Star(p, 3);
     }
  }
  
	public void paint(Graphics brush) 
	{
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);
    
		ship.draw(brush);
		
		for(Asteroid a : asteroids)
		{
			if(a != null)
			{
				a.draw(brush);
    	
				if(!gameOver)
				{
					a.move();
				}
    	
				if(a.intersect(ship))
				{	
					brush.setColor(Color.YELLOW);
					brush.drawString("Score: " + score, 375, 75);
    		    	
					brush.setColor(Color.GREEN);
					brush.setFont(new Font("SansSerif", Font.PLAIN, 50));
					brush.drawString("Game Over", 275, 50);
					gameOver = true;
				}
			}
		}
    
		for(Star s : stars)
		{
			s.draw(brush);
		}
    
		if(b != null)
		{
			b.draw(brush);
			b.move(ship.rotation);
			int i = 0;
    	
    	
			for(Asteroid a : asteroids)
			{
				if(a != null && a.contains(b.getPosition()))
				{
					score += 100;
					asteroids[i] = null;
					asteroids[i] = new Asteroid(asteroidPoints[(int) (Math.random() * 3)], new Point((int)(Math.random() * this.width), (int)(Math.random() * this.height)), (int)(Math.random() * 360));
					break;
				}
    		
				i++;
			}
		}
    
		if(!gameOver)
		{
			brush.setColor(Color.GRAY);
			brush.drawString("Score: " + score, 10, 550);
		}
    
    
		if(times == 0 && gameOver)
		{
			times++;
			this.showDialog();
			
			audioClip.stop();
		}
    
    	repaint();
	}
	
	public static void main (String[] args) {
		EventQueue.invokeLater(new Runnable() {

		    @Override
		    public void run() 
		    {
				new Asteroids();
						
		    }
		 });
		}
}


