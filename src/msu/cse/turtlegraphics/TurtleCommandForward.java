package msu.cse.turtlegraphics;

import java.awt.Point;
import java.util.List;

/**
 * A command which moves the turtle forward some number of units.
 * 
 * @author Kristopher Micinski
 */

public class TurtleCommandForward extends TurtleCommand {
    // The number of units we should move back
    protected int unitsForward;
    
    private Point beginPoint;
    private Point endPoint;
    
    /**
     * Create a new 'move back' command with a specified number of units.
     * 
     * @param units The number of units to move back.
     */
    TurtleCommandForward(int units)
    {
	unitsForward = units;
	return;
    }
    
    /**
     * Execute the command itself.
     */
    public void executeCommand(Turtle turtle)
    {
	List<RenderableShape> currentlyDisplayedShapes = 
	    turtle.getCurrentlyDisplayedShapes();
 	
	// Where is the point we are currently drawing.
	Turtle.RootedVector currentEndPoint;
	
	Turtle.Vector direction = turtle.getTurtleDirection();
	
	beginPoint = turtle.getCurrentPoint();
	
	endPoint= new Point((int)Math.round(beginPoint.getX()+direction.getX()*unitsForward),
			    (int)Math.round(beginPoint.getY()-direction.getY()*unitsForward));
	
	LineRenderableShape shape = new LineRenderableShape(beginPoint, 
							    endPoint);
	
	turtle.setCurrentX((int)endPoint.getX());
	turtle.setCurrentY((int)endPoint.getY());
	
	if (turtle.getPenDown()) {
	    currentlyDisplayedShapes.add(shape);
	}
	
	//currentEndPoint = new 
	// Now do the animation loop to draw the line
	//for (int i = 0; 
	
 	return;
    }
}
 