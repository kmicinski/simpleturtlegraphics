package msu.cse.turtlegraphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

/**
 * A Canvas ({@link Canvas}) on which to display a {@link Turtle} instance. 
 *
 * This class allows configuration of more specific properties relating
 * to displaying the turtle, and also holds the actual shapes to be rendered.
 * The {@link Turtle} class is responsible for doing the conceptual drawing 
 * work, but this class actually does the drawing and rendering on the scren.
 * 
 * This class does *not* do animation, that is handled in the Turtle itself. 
 * Instead, this class holds only a number of "primitive" 
 *
 * @author Kristopher Micinski
 *
 */
public class TurtleDisplayCanvas extends JPanel 
implements MouseMotionListener, MouseListener, KeyListener
{
	// The currently displayed turtle
	private Turtle currentTurtle;

	private int preferedWidth = 800;
	private int preferedHeight = 600;

	private double zoomCoeff = 1.0;

	private JMenuBar menuBar;
	private JMenu helpMenu;
	private JMenu displayMenu;
	private JMenuItem menuItem;

	// Is the mouse currently clicked
	private boolean isClicked;
	private Point clickedPoint;

	/**
	 * The currentXOffset and currentYOffset variables specify the
	 * current transformation performed on the displayed Turtle
	 * component. Changing these variables allows one to "scroll"
	 * across the screen, or display a certain part of it as they
	 * wish.
	 */
	private int currentXOffset;
	private int currentYOffset;

	// utility variables to help mouse dragging across the screen.
	private int baseXOffset;
	private int baseYOffset;

	private Image turtleImage;

	private int dragCount;

	/**
	 * Create a new TurtleDisplayCanvsas
	 * 
	 */
	public TurtleDisplayCanvas()
	{
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);

		currentlyDisplayedShapes = null;

		setSize(getPreferedSize());

		int width = this.getWidth();
		int height = this.getHeight();

		currentXOffset = width / 2;
		currentYOffset = height / 2;

		dragCount = 0;

		addKeyListener(this);

		return;
	}

	//
	// KeyListener methods
	//
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyChar() == '+') {
			zoomCoeff *= 2.0;
		} else if (e.getKeyChar() == '-') {
			zoomCoeff /= 2.0;
		}
		repaint();
		return;
	}

	public void keyReleased(KeyEvent e)
	{
		return;
	}

	public void keyTyped(KeyEvent e)
	{
		return;
	}

	/**
	 * @return The prefered height of the Canvas
	 */
	public Dimension getPreferedSize()
	{
		return new Dimension(preferedWidth, preferedHeight);
	}

	/**
	 * @return The current X offset of the screen on the Turtle.
	 */ 
	public int getCurrentXOffset()
	{
		return currentXOffset;
	}

	/**
	 * @return the current Y offset of the screen on the Turtle.
	 */ 
	public int getCurrentYOffset()
	{
		return currentYOffset;
	}

	public void setCurrentXOffset(int x)
	{
		currentXOffset = x;
		invalidate();
		revalidate();
	}

	public void setCurrentYOffset(int y)
	{
		currentYOffset = y;
		invalidate();
		revalidate();
	}

	private List<RenderableShape> currentlyDisplayedShapes;

	public List<RenderableShape> getCurrentlyDisplayedShapes()
	{ 
		return currentlyDisplayedShapes; 
	}

	public void setCurrentlyDisplayedShapes(List<RenderableShape> shapes)
	{ 
		currentlyDisplayedShapes = shapes;
	}

	public void mouseMoved(MouseEvent mouseEvent)
	{	
		return;
	}

	/**
	 * Handle the user dragging the image of the rendered Turtle scnee.
	 *
	 * When the user clicks 
	 */
	public void mouseDragged(MouseEvent mouseEvent) 
	{
		if (clickedPoint != null) {
			currentXOffset = baseXOffset + (int)(mouseEvent.getX()
					- clickedPoint.getX());
			currentYOffset = baseYOffset + (int)(mouseEvent.getY()
					- clickedPoint.getY());
		}

		if (dragCount++ > 1) {
			repaint();
			dragCount = 0;
		}

		return;
	}

	public void mousePressed(MouseEvent e)
	{
		clickedPoint = new Point(e.getX(),e.getY());
		baseXOffset = currentXOffset;
		baseYOffset = currentYOffset;

		isClicked = true;
	}

	public void mouseReleased(MouseEvent e)
	{
		currentXOffset = baseXOffset + (int)(e.getX()-clickedPoint.getX());
		currentYOffset = baseYOffset + (int)(e.getY()-clickedPoint.getY());

		clickedPoint = null;
		isClicked = false;

		repaint();
	}

	public void mouseClicked(MouseEvent e)
	{
		return;
	}

	public void mouseEntered(MouseEvent e)
	{
		return;
	}

	public void mouseExited(MouseEvent e)
	{
		return;
	}

	/**
	 * Repaint the entire evironment. 
	 *
	 * Simply go through the list of renderable 
	 * shapes and display them on the currently graphics context.
	 * 
	 * @param graphics The graphics context on which to draw the shapes
	 */
	public void paint(Graphics graphicsold)
	{
		//super.paintComponents(graphicsold);

		Graphics2D graphics = (Graphics2D)graphicsold;

		// Clear the background first
		graphics.setBackground(Color.WHITE);
		graphics.clearRect(0,0,this.getWidth(),this.getHeight());

		graphics.translate(currentXOffset, currentYOffset);
		graphics.scale(zoomCoeff, zoomCoeff);

		// Simply draw all of the primitive rendered shapes.
		if (currentlyDisplayedShapes != null) {
			synchronized (currentlyDisplayedShapes) {
				for (RenderableShape i : currentlyDisplayedShapes) {
					i.renderShape((Graphics2D)graphics);
				}
			}
		}

		/*
	// Now draw the cute turtle
	if (turtleImage != null && currentTurtle != null) {
	    graphics.drawImage(turtleImage,currentTurtle.getCurrentX(),
			       currentTurtle.getCurrentY(),null);
			       }*/
	}

	public void setCurrentTurtle(Turtle turtle)
	{
		currentTurtle = turtle;
	}
}
