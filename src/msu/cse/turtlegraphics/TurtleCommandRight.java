package msu.cse.turtlegraphics;

import java.awt.Point;
import java.util.List;

/**
 * A command which moves the Turtle's orientation to the right by some 
 * number of radians or degrees.
 * 
 * The command specifies the number of degrees / radians that are be to be used, 
 * but the {@link Turtle} tells us whether or not to use radians or degrees.
 *
 * @author Kristopher Micinski
 */

public class TurtleCommandRight extends TurtleCommand {
    // The number of radians right to move the Turtle
    protected double radiansRight;
    
    /**
     * Create a new 'right' command with a specified number of radians or 
     * degrees.
     * 
     * @param units The number of radians or degrees to move the Turtle 
     * right. The {@link Turtle#getUsingRadians} method is used to 
     * distinguish whether radians or degrees should be used.
     */
    TurtleCommandRight(double unitsRight)
    {
	radiansRight = unitsRight;
    }
    
    /**
     * Execute the command to move the Turtle.
     */
    public void executeCommand(Turtle turtle)
    {
	turtle.moveCurrentOrientationRight(radiansRight);
	return;
    }
}
