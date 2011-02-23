package msu.cse.turtlegraphics;

import java.awt.Point;
import java.awt.Polygon;

/**
 * Set the heading of the turtle.
 * 
 * @author Kristopher Micinski
 */

public class TurtleCommandBeginPolyFill extends TurtleCommand {
    /**
     * Execute the command.
     *
     * Begin a newly filled polygon. The programmer uses this command
     * to signal when we should start shading in a new polygon. As the
     * turtle moves around, each point in its path is recorded, until
     * the {@link TurtleCommandEndPolyFill} command is given. If there
     * is currently a polygon being filled, this command does nothing.
     * (i.e., we don't handle a stack of polygon fills).  However, 
     * if there is no polygon that is currently being filled, this 
     * command creates a new polygon and sets it to be filled.
     * 
     */
    public void executeCommand(Turtle turtle)
    {
	if (turtle.getCurrentlyFillingPolygon() == null) {
	    Polygon p = new Polygon();
	    turtle.setCurrentlyFillingPolygon(p);
	    
	    // After this point we will start adding our current 
	    // point to the polygon after each command is given. 
	    // Because of this, we don't need to add the current 
	    // point of the Turtle at this time (since it will be 
	    // added immediately after this command finishes 
	    // its execution).
	}
	
	return;
    }
}

