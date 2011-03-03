package msu.cse.turtlegraphics;

import java.awt.Point;

/**
 * An extension of an {@link ArcRenderableShape} which allows drawing of 
 * circles, either shaded or not.
 *
 * @author Kristopher Micinski
 */

public class CircleRenderableShape extends ArcRenderableShape
{
    /**
     * Create a new CircleRenderableShape.
     * 
     * @param centerX The x coordinate of the center of the circle
     * @param centerY The y coordinate of the center of the circle
     * @param radius The radious of the circle
     */
    CircleRenderableShape(int centerX, int centerY, int radius, int angle,
			  boolean filled)
    {
	setFilled(filled);
	setTopLeftX(centerX - radius);
	setTopLeftY(centerY - radius);
	
	setStartAngle(0);
	setArcAngle(angle);
	setHeight(2 * radius);
	setArcWidth(2 * radius);
    }
}