package msu.cse.turtlegraphics;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Change the pen color.
 * 
 * This is a hack. We should really be setting the Renderable shape 
 * color. But because each of the renderable shapes are drawn sequentially 
 * it doesn't really matter.
 */

public class PenColorRenderableShape extends RenderableShape
{
    // The color to set the pen
    private Color specifiedColor;
    
    public PenColorRenderableShape(Color color) { specifiedColor = color; }
    
    public Color getPenColor() { return specifiedColor; }
    public void setPenColor(Color c) { specifiedColor = c; }
    
    /**
     * Perform the action to change the color in the specified graphics 
     * context.
     */
    public void renderShape(Graphics2D g)
    {
	g.setColor(specifiedColor);
    }
}
