package msu.cse.turtlegraphics;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 * A Line that can be displayed on the screen. 
 *
 * @author Kristopher Micinski
 */

public class LineRenderableShape extends RenderableShape
{
    private Point beginPoint;
    private Point endPoint;
    
    /**
     * Create a new line shape from point begin to point end.
     * 
     * @param begin The start of the line segment
     * @param end The end of the line segment
     */
    public LineRenderableShape(Point begin, Point end)
    {
	beginPoint = begin;
	endPoint = end;
    }
    
    /**
     * Draw the line on the graphics context.
     */
    public void renderShape(Graphics2D g)
    {
	g.drawLine((int)beginPoint.getX(), (int)beginPoint.getY(),
		   (int)endPoint.getX(), (int)endPoint.getY());
	return;
    }
}
