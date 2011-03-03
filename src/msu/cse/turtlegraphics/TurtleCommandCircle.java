package msu.cse.turtlegraphics;

import java.awt.Point;
import java.util.List;

/**
 * Draw a circle with the given radius.
 * 
 * The given radius is radius units to the left of the Turtle. Angle
 * can be given to specify what portion of the circle is drawn, if not
 * specified (0) then the entire circle is drawn.
 * 
 * @author Kristopher Micinski
 */

public class TurtleCommandCircle extends TurtleCommand {
    protected int radius;
    protected int angle;
    protected boolean filled;
    
    /**
     * Create a new 'draw circle' command
     * 
     * @param radius The radius of the circle to be drawn, 
     * to the left of the turtle.
     * 
     * @param angle The extent of the circle to be drawn, in degrees.
     */
    public TurtleCommandCircle(int radius, int angle) 
    {
	this.radius = radius;
	this.angle = angle;
	filled = false;
    }
    
    public int getRadius() { return radius; }
    public void setRadius(int r) { radius = r; }
    
    public int getAngle() { return angle; }
    public void setAngle(int a) { angle = a; }
    
    /**
     * Execute the command, starts at the current turtle position,
     * eventually ends up at the turtle position specified by the 
     * destination field.
     */
    public void executeCommand(Turtle turtle)
    {
	List<RenderableShape> currentlyDisplayedShapes = 
	    turtle.getCurrentlyDisplayedShapes();
	
	Point beginPoint = turtle.getCurrentPoint();
	
	CircleRenderableShape shape = 
	    new CircleRenderableShape(((int)beginPoint.getX()) - radius,
				      ((int)beginPoint.getY()) - radius,
				      radius,
				      angle,
				      filled);
	
	if (turtle.getPenDown ()) {
	    // Draw the shape on the graphics context.
	    currentlyDisplayedShapes.add(shape);
	}
	
	/*
	  
	turtle.setCurrentX((int)endPoint.getX());
	turtle.setCurrentY((int)endPoint.getY());

	*/
	
	return;
    }
}
