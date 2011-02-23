package msu.cse.turtlegraphics;

import java.awt.Point;
import java.util.List;

/**
 * Set the heading of the turtle.
 * 
 * @author Kristopher Micinski
 */

public class TurtleCommandEndPolyFill extends TurtleCommand {
    /**
     * Execute the command.
     *
     * Used in conjunction with {@link TurtleCommandBeginPolyFill} to 
     * color in a region. First the {@link TurtleCommandBeginPolyFill} 
     * command is used. When the polygon is currently being filled, 
     * after each command, the Turtle's current position is recorded.
     * When this command is executed the entire area in the Turtle's
     * path is connected (using straight lines). 
     * 
     * We don't currently implement circles, just straight lines.
     * (should we?)
     * 
     */
    public void executeCommand(Turtle turtle)
    {
	if (turtle.getCurrentlyFillingPolygon() != null) {
	    List<RenderableShape> currentlyDisplayedShapes = 
		turtle.getCurrentlyDisplayedShapes();
	    
	    PolygonRenderableShape renderableShape = new
		PolygonRenderableShape(turtle.getCurrentlyFillingPolygon());
	    turtle.setCurrentlyFillingPolygon(null);

	    currentlyDisplayedShapes.add(renderableShape);
	}
	
	return;
    }
}

