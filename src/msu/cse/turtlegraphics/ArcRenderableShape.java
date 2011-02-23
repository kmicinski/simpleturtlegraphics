package msu.cse.turtlegraphics;

import java.awt.Graphics2D;

/**
 * A shape which represents some arc to be drawn on the space.
 *
 * To draw an arc (note that circles, ovals, etc... can be drawn as arcs)
 * this class is used. This class allows an area to be shaded, or unshaed.
 */

public class ArcRenderableShape extends RenderableShape
{
    protected int topLeftX;
    protected int topLeftY;
    
    protected int arcWidth;
    
    protected int arcHeight;
    
    protected int startAngle;
    protected int arcAngle;
    
    protected boolean filled;
    
    public int getTopLeftX() { return topLeftX; }
    public int getTopLeftY() { return topLeftY; }
    
    public void setTopLeftX(int x) { topLeftX = x; }
    public void setTopLeftY(int y) { topLeftY = y; }
    
    public void setArcWidth(int width) { arcWidth = width; }
    public int getArcWidth() { return arcWidth; }
    
    public int getHeight() { return arcHeight; }
    public void setHeight(int h) { arcHeight = h; }
    
    public int getStartAngle() { return startAngle; }
    public void setStartAngle(int angle) { startAngle = angle; }
    
    public void setArcAngle(int angle) { arcAngle = angle; }
    public int getArcAngle() { return arcAngle; }
    
    public boolean getFilled() { return filled; }
    public void setFilled(boolean isfilled) { filled = isfilled; }
    
    public void renderShape(Graphics2D g)
    {
	if (filled) {
	    g.fillArc(topLeftX,
		      topLeftY,
		      arcWidth,
		      arcHeight,
		      startAngle,
		      arcAngle);
	} else {
	    g.drawArc(topLeftX,
		      topLeftY,
		      arcWidth,
		      arcHeight,
		      startAngle,
		      arcAngle);
	}
	return;
    }
}