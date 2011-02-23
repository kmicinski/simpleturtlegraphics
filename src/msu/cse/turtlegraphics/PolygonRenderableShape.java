package msu.cse.turtlegraphics;

import java.awt.Polygon;
import java.awt.Graphics2D;

/**
 * A renderable 'polygon' containing an arbitrary number of endpoints. 
 *
 * @author Kristopher Micinski
 */
public class PolygonRenderableShape extends RenderableShape
{
    protected Polygon drawingPolygon;
    
    /**
     * Create a new 'draw polygon' command.
     */
    public PolygonRenderableShape(Polygon polygon)
    {
	drawingPolygon = polygon;
    }
    
    public Polygon getDrawingPolygon() { return drawingPolygon; }
    public void setDrawingPolygon(Polygon p) { drawingPolygon = p; }
    
    /**
     * Draw the polygon on the surface
     */
    public void renderShape(Graphics2D g)
    {
	g.fill(drawingPolygon);
    }
}