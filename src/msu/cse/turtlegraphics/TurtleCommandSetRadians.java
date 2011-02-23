package msu.cse.turtlegraphics;

/**
 * A command which switches the Turtle to radians mode.
 * 
 * @author Kristopher Micinski
 */

public class TurtleCommandSetRadians extends TurtleCommand {
    public void executeCommand(Turtle turtle)
    {
	turtle.setUsingRadians(true);
    }
}
