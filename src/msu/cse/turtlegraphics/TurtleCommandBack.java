package msu.cse.turtlegraphics;

import java.awt.Point;

import java.util.List;

/**
 * A command which moves the turtle back some number of units.
 * 
 * @author Kristopher Micinski
 */

public class TurtleCommandBack extends TurtleCommandForward {
    // The number of units we should move back
    private int unitsBack;
    
    private Point beginPoint;
    private Point endPoint;
    
    /**
     * Create a new 'move back' command with a specified number of units.
     * 
     * @param units The number of units to move back.
     */
    TurtleCommandBack(int units) 
    {
	super(-units);
	unitsBack = units;
	return;
    }
}

