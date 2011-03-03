package msu.cse.turtlegraphics;

import java.awt.Point;
import java.util.List;

/**
 * Draw a dot with the given radius.
 * 
 * @see {@link TurtleCommandCircle}
 * @author Kristopher Micinski
 */

public class TurtleCommandDot extends TurtleCommandCircle {

    /**
     * Create a new 'draw dot' command
     * 
     * @param radius The radius of the dot to be drawn,
     * to the left of the turtle.
     * 
     * @param angle The extent of the circle to be drawn, in 
     * degrees.
     */
    public TurtleCommandDot(int radius, int angle) 
    {
	super(radius, angle);
	filled = true;
    }
}

