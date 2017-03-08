import java.awt.Color;
import java.awt.Graphics;

class Star extends Circle
{
	private Point inPosition;
	private double radius;
	
	public Star(Point inPosition, double radius) 
	{
		super(inPosition, radius);
		this.inPosition = inPosition;
		this.radius = radius;
	}
	
	public void draw(Graphics brush)
	{
		brush.setColor(Color.WHITE);
		brush.fillOval((int)this.inPosition.x, (int)this.inPosition.y, (int)this.radius, (int)this.radius);
	}
	
	
	
}