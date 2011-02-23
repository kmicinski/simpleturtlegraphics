package msu.cse.turtlegraphics;

import java.awt.Point;
import java.util.List;

/**
 * Set the heading of the turtle.
 * 
 * @author Kristopher Micinski
 */

public class TurtleCommandSetHeading extends TurtleCommand {
    protected double heading;
    
    public TurtleCommandSetHeading(double heading)
    {
	this.heading = heading;
    }
    
    /**
     * Set the destination of the move for the turtle.
     */
    public void setHeading(double heading) { this.heading = heading; }
    
    /**
     * Get the destination of the move for the turtle.
     */
    public double getHeading() { return heading; }
    
    /**
     * Execute the command, starts at the current turtle position,
     * eventually ends up at the turtle position specified by the 
     * destination field.
     */
    public void executeCommand(Turtle turtle)
    {
	turtle.setOrientation((int)heading);
	
	return;
    }
}

