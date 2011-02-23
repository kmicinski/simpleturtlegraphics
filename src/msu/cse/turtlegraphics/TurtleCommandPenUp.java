package msu.cse.turtlegraphics;

import java.awt.Point;

import java.util.List;

/**
 * A command which takes up the pen.
 * 
 * @author Kristopher Micinski
 */

public class TurtleCommandPenUp extends TurtleCommand 
{
    public void executeCommand(Turtle turtle)
    {
	turtle.setPenDown(false);
    }
}

