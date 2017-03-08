import java.awt.Color;
import java.awt.Graphics;

public class Asteroid extends Polygon
{
	private Point[] inShape;
	private Point inPosition;
	private double inRotation;
	boolean moving = true;
	boolean movingDown = true;
	
	public Asteroid(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		this.inShape = inShape;
		this.inPosition = inPosition;
		this.inRotation = inRotation;
	}
	
	public void draw(Graphics brush)
	{
		int[] x = new int[inShape.length];
		int[] y = new int[inShape.length];
		int xIndx = 0;
		int yIndx = 0;
		
		for(Point p : this.getPoints())
		{
			x[xIndx] = (int) p.x;
			y[yIndx] = (int) p.y;
			
			xIndx++;
			yIndx++;
		}
	
		brush.setColor(Color.WHITE);
		brush.drawPolygon(x, y, inShape.length);
	}
	
	public void move()
	{
		if(this.position.x < 784 && moving)
		{
			this.position.x += 1;
		}
		
		else
		{
			moving = false;
			moveBack();
		}
		
		if(!moving && this.position.x <= 0)
		{
			moving = true;
		}
		
		if(this.position.y < 562 && movingDown)
		{
			this.position.y += 1;
		}
		
		else
		{
			movingDown = false;
			moveUp();
			
			if(this.position.y <= 0)
			{
				movingDown = true;
			}
		}
	}
	
	private void moveBack()
	{
		this.position.x -= 1;
	}
	
	private void moveUp()
	{
		this.position.y -= 1;
	}
}