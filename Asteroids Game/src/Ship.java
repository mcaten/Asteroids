import java.awt.Color;
import java.awt.Graphics;
class Ship extends Polygon
{
	private Point[] inShape;
	private Point inPosition;
	private double inRotation;
	
	public Ship(Point[] inShape, Point inPosition, double inRotation) 
	{
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
		
		brush.setColor(new Color(154, 155, 226));
		brush.fillPolygon(x, y, inShape.length);
		
	}
	
	public void move()
	{
		if(this.position.x > 800)
		{
			this.position.x = 0;
		}
		
		else if(this.position.x < 0)
		{
			this.position.x = 800;
		}
		
		if(this.position.y > 600)
		{
			this.position.y = 0;
		}
		
		else if(this.position.y < 0)
		{
			this.position.y = 600;
		}
		
		if(this.rotation == 0)
		{
			this.position.y -= 5;
		}
		
		else if(this.rotation == 180)
		{
			this.position.y += 5;
		}
		
		else if(this.rotation > 0 && this.rotation <= 45)
		{
			this.position.x += 5;
			this.position.y -= 5;
		}
		
		else if(this.rotation > 45 && this.rotation < 90)
		{
			this.position.x += 5;
			this.position.y -= 2;
		}
		
		else if(this.rotation == 90)
		{
			this.position.x += 5;
		}
		
		else if(this.rotation > 90 && this.rotation <= 135)
		{
			this.position.x += 3;
			this.position.y += 3;
		}
		
		else if(this.rotation > 135 && this.rotation <= 180)
		{
			this.position.x += 5;
			this.position.y += 5;
		}
		
		else if(this.rotation > 180 && this.rotation <= 225)
		{
			this.position.x -= 5;
			this.position.y += 5;
		}
		
		else if(this.rotation > 225 && this.rotation < 270)
		{
			this.position.x -= 5;
			this.position.y += 2;
		}
		
		else if(this.rotation == 270)
		{
			this.position.x -=5;
		}
		
		else if(this.rotation > 270 && this.rotation < 315)
		{
			this.position.x -= 5;
			this.position.y -= 5;
		}
		
		else if(this.rotation >= 315)
		{
			this.position.x -= 5;
			this.position.y -= 7;
		}
	
	}
	
	public void accelerate (double acceleration) {
	   
		if(rotation >= 180)
		{
			this.position.x += acceleration * Math.cos(rotation * Math.PI / 180);
			this.position.y += acceleration * Math.sin(rotation * Math.PI / 180);
		}
		else
		{
			this.position.x -= acceleration * Math.cos(rotation * Math.PI / 180);
			this.position.y -= acceleration * Math.sin(rotation * Math.PI / 180);
		}

	}
	
	public Point getTip()
	{
		return this.getPoints()[0];
	}
}