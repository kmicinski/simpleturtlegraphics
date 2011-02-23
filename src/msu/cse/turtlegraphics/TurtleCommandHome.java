package msu.cse.turtlegraphics;

import java.awt.Point;
import java.util.List;

/**
 * Move the Turtle to the 'home' position (0,0).
 * 
 * @author Kristopher Micinski
 */

public class TurtleCommandHome extends TurtleCommand {
    public void executeCommand(Turtle turtle)
    {
	turtle.setCurrentX(0);
	turtle.setCurrentY(0);
	turtle.setOrientation(0);
	
	return;
    }
}

