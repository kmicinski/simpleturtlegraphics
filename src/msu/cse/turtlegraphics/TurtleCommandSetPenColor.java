package msu.cse.turtlegraphics;

import java.awt.Point;
import java.awt.Color;
import java.util.List;

/**
 * A command to set the pen color.
 * 
 * @author Kristopher Micinski
 */

public class TurtleCommandSetPenColor extends TurtleCommand {
    protected Color penColor;
    
    /**
     * Construct a new 'set pen color' command
     * 
     * @param color The color to which the pen should be changed.
     */
    public TurtleCommandSetPenColor(Color color) 
    {
	penColor = color;
    }
    
    /**
     * Executes the command.
     */
    public void executeCommand(Turtle turtle)
    {
	List<RenderableShape> currentlyDisplayedShapes = 
	    turtle.getCurrentlyDisplayedShapes();
	
	PenColorRenderableShape shape = 
	    new PenColorRenderableShape(penColor);
	
	// Add the command to the drawing queue 
	currentlyDisplayedShapes.add(shape);
	
	return;
    }
}

