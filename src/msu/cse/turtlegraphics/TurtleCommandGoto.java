package msu.cse.turtlegraphics;

import java.awt.Point;
import java.util.List;

/**
 * Move the turtle to some position on the screen. 
 * 
 * If the pen is down, then draw a line between the previous point and
 * the new point, otherwise simply move the turtle to the point. 
 * 
 * @author Kristopher Micinski
 */

public class TurtleCommandGoto extends TurtleCommand {
    protected Point destination;
    
    /**
     * Construct a new 'goto' command
     * 
     * @param destination The destination of the goto command.
     */
    public TurtleCommandGoto(Point dest) 
    {
	destination = dest;
    }
    
    /**
     * Set the destination of the move for the turtle.
     */
    public void setDestination(Point dest) { destination = dest; }
    
    /**
     * Get the destination of the move for the turtle.
     */
    public Point getDestination() { return destination; }
    
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
	
	Point endPoint = destination;

	LineRenderableShape shape = 
	    new LineRenderableShape(beginPoint, endPoint);
	
	if (turtle.getPenDown()) {
	    // Draw the shape on the graphics context.
	    currentlyDisplayedShapes.add(shape);
	}
		
	turtle.setCurrentX((int)endPoint.getX());
	turtle.setCurrentY((int)endPoint.getY());
	
	return;
    }
}

