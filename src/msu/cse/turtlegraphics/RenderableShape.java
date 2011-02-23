package msu.cse.turtlegraphics;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Color;

/**
 * An abstract description of a shape which can be drawn on a
 * {@link}TurtleDisplayFrame.  
 *
 * To render individual shapes on the drawing surface, the TurtleDisplayFrame 
 * maintains a list of RenderableShapes which are drawn to display the actual 
 * surface. When the {@link}Turtle creates new objects (lines, text, circles, 
 * etc...) to be drawn on the surface, it creates new RenderableShapes and 
 * adds them to the list of shapes currently being displayed by the 
 * TurtleDisplayFrame. 
 * 
 */
public abstract class RenderableShape // implements Shape
{
    // The color with which to draw the shape
    private Color drawingColor;
    
    /**
     * Get the {@link}Color with which this shape will be drawn.
     *
     * @return The Color that will be used to draw the shape.
     */
    public Color getDrawingColor() { return drawingColor; }
    
    /**
     * Set the {@link}Color with which this shape will be drawn.
     * 
     * @param color The shape with which to draw the shape.
     */
    public void setDrawingColor(Color color) { drawingColor = color; }
    
    /**
     * Render the shape.
     */
    public abstract void renderShape(Graphics2D g);
}
