import java.awt.Color;
import java.awt.Graphics;

class Bullet extends Circle
{
	private Point inPosition;
	private double radius;
	
	public Bullet(Point inPosition, double radius) {
		super(inPosition, radius);
		
		this.inPosition = inPosition;
		this.radius = radius;
	}
	
	public void draw(Graphics brush)
	{
		brush.setColor(Color.RED);
		brush.fillOval((int)this.inPosition.x, (int)this.inPosition.y, (int)this.radius, (int)this.radius);
	}
	
	public void move(double rotation)
	{
		if(rotation == 0)
		{
			inPosition.y -= 5;
		}
		
		else if(rotation == 180)
		{
			inPosition.y += 5;
		}
		
		else if(rotation > 0 && rotation <= 45)
		{
			inPosition.x += 5;
			inPosition.y -= 5;
		}
		
		else if(rotation > 45 && rotation < 90)
		{
			inPosition.x += 5;
			inPosition.y -= 2;
		}
		
		else if(rotation == 90)
		{
			inPosition.x += 5;
		}
		
		else if(rotation > 90 && rotation <= 135)
		{
			inPosition.x += 5;
			inPosition.y += 10;
		}
		
		else if(rotation > 135 && rotation <= 180)
		{
			inPosition.x += 5;
			inPosition.y += 5;
		}
		
		else if(rotation > 180 && rotation <= 225)
		{
			inPosition.x -= 5;
			inPosition.y += 5;
		}
		
		else if(rotation > 225 && rotation < 270)
		{
			inPosition.x -= 5;
			inPosition.y += 2;
		}
		
		else if(rotation == 270)
		{
			inPosition.x -=5;
		}
		
		else if(rotation > 270 && rotation < 315)
		{
			inPosition.x -= 5;
			inPosition.y -= 5;
		}
		
		else if(rotation >= 315)
		{
			inPosition.x -= 5;
			inPosition.y -= 7;
		}
	}
	
	public Point getPosition()
	{
		return new Point(this.inPosition.x, this.inPosition.y);
	}
	
}