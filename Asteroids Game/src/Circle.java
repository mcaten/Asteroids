import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
class Circle extends Shapes
{
	private Point inPosition;
	private double radius;
	
	public Circle(Point inPosition, double radius)
	{
		this.inPosition = inPosition;
		this.radius = radius;
	}
	
	public boolean contains(Point p)
	{
		Shape oval = new Ellipse2D.Double(inPosition.x, inPosition.y, radius, radius);
		
		return oval.contains(new Point2D.Double(p.x, p.y));
	}

	@Override
	public double getPerimeter() {
		return 0; //fix later
		
	}
}