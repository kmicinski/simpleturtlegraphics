package msu.cse.turtlegraphics;

import java.lang.InterruptedException;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Color;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

import java.lang.Math;

import javax.swing.SwingUtilities;

/**
 * Class representing a configuration and set of commands which should
 * be executed to render a turtle graphics drawing.
 *
 * The Turtle class controls the main conceptual elements that take care 
 * of drawing Turtle graphics on a screen. 
 * 
 * @author Kristopher Micinski
 */
public class Turtle implements CommandListObserver
{
    // Is the pen set as up (not drawing) or down (drawing)
    private boolean isPenDown;

    // List of turtle commands
    private ObservableCommandList<TurtleCommand> commandList;

    // List of currently rendered shapes to be drawn on the screen
    private List<RenderableShape> currentlyDisplayedShapes;
    
    // Current X position
    private int currentX;
    private int currentY;
    
    // The current time unit, in milliseconds
    private int currentTimeUnit;
    
    // Are we currently using radians in our inputs from the user.
    private boolean usingRadians;
    
    // The current orientation of the turtle, in radians.
    private int currentOrientation;
    
    // The TurtleDisplayCanvas to which we are currently attached.
    private TurtleDisplayCanvas currentDisplayCanvas;
    
    // The currently running animation thread (if any)
    private AnimationThread currentAnimationThread;
    
    // Is the turtle hidden?
    private boolean turtleVisible;
    
    // Currently filling polygon
    private Polygon currentlyFillingPolygon; 
    
    // Should the screen be re-animated each time a command
    // is added to the command list?
    private boolean reanimateImmediate;
    
    /**
     * Create a new Turtle object.
     */
    public Turtle()
    {
	// Create the command 
	commandList = 
	    new ObservableCommandList<TurtleCommand>();
	
	commandList.addObserver(this);
	
	// Create a new synchronized list to store the list of
	// currently rendered shapes.
	currentlyDisplayedShapes =
	    Collections.synchronizedList(new LinkedList<RenderableShape>());
	
	// Turtle graphics conventions dictate that we start at due north as our 
	// zero degree mark...
	currentOrientation = 90;
	
	currentDisplayCanvas = null;
	
	currentAnimationThread = new AnimationThread(this);
	
	setUsingRadians(false);
	
	reanimateImmediate = true;
	
	isPenDown = true;
	
	// Set the current time unit to 33 milliseconds
	currentTimeUnit = 33;
	return;
    }
    
    public int getCurrentTimeUnit()
    {
	return currentTimeUnit;
    }
    
    public Polygon getCurrentlyFillingPolygon()
    {
	return currentlyFillingPolygon;
    }
    
    public void setCurrentlyFillingPolygon(Polygon p)
    {
	currentlyFillingPolygon = p;
    }
    
    /**
     * @return The current Point position of the Turtle
     */
    public Point getCurrentPoint() { return new Point(currentX, currentY); }
    
    public List<RenderableShape> getCurrentlyDisplayedShapes() { return currentlyDisplayedShapes; }
    
    //private double turtleRadsToRegular
    
    //public 
    /**
     * Get the state of the pen (up or down) for the current Turtle.
     */
    public boolean getPenDown() { return isPenDown; }

    /**
     * Set the state of the pen (up or down) for the current Turtle.
     *
     * @param penUp
     */
    public void setPenDown(boolean penDown) { isPenDown = penDown; }

    
    /**
     * Set the state of the Turtle's visibility.
     * 
     * @param turtleVisible true if the turtle should be displayed, false if the 
     * turtle should be hidden.
     * 
     */
    public void setTurtleVisible(boolean turtleVisible)
    {
	this.turtleVisible = turtleVisible;
    }
    
    /**
     * @returns true if the turtle is currently visible, false otherwise
     */
    public boolean getTurtleVisible()
    {
	return turtleVisible;
    }
    
    /**
     * Tell the turtle to use radians (as opposed to degrees).
     *
     * Drawing within the turtle system defaults.
     */
    public void setUsingRadians(boolean useRadians) 
    {
	usingRadians = useRadians;
    }

    public boolean getUsingRadians()
    {
	return usingRadians;
    }
    
    /**
     * Set the pen as down.
     */
    public void setPenDown() 
    {
	setPenDown(true);
    }
 
    //public double getCurrentOrientation
	
    /**
     * Get the current X position of the turtle.
     */
    public int getCurrentX() 
    {
	return currentX;
    }

    /**
     * Set the current X position of the turtle.
     *
     * @param xPosition The X position to which the turtle should be moved.
     */
    public void setCurrentX(int xPosition)
    {
	currentX = xPosition;
    }
    
    /**
     * Get the current Y position of the cursor.
     */
    public int getCurrentY()
    {
	return currentY;
    }

    /**
     * Set the current Y position of the turtle.
     *
     * @param yPosition The Y position to which the turtle should be moved.
     */
    public void setCurrentY(int yPosition)
    {
	currentY = yPosition;
    }
    
    /**
     * A command has been added to the command list.  We add this hook so that
     * whenever the user adds a command to the Turtle's command list, we can 
     * start redrawing the screen for them.  Otherwise they would have to call 
     * clearAndRenderScene by themselvs.
     */
    public void handleCommandListChanged(ObservableCommandList commands)
    {
	if (reanimateImmediate) {
	    // Should we immediately start reanimating the scene?
	    ReanimationThread t = new ReanimationThread(this);
	    SwingUtilities.invokeLater(t);
	} else {
	    ; // Do nothing
	}
    }
    
    public class ReanimationThread implements Runnable
    {
	private Turtle turtle;
	
	public ReanimationThread(Turtle t)
	{
	    turtle = t;
	}
	
	public void run()
	{
	    turtle.clearAndRenderScene(true);
	}
    }
    
    /**
     * Insert a command in the turtle command list.
     */
    public void addCommand(TurtleCommand command)
    {
	commandList.add(command);
    }
    
    /**
     * Set the current {@link TurtleDisplayCanvas}.
     * 
     * Once a Turtle is created, it must be attached to a TurtleDisplayCanvas, 
     * which presents the generated graphics on the screen. The
     * TurtleDisplayCanvas does the actual drawing, presentation, and input 
     * handling, while the Turtle actually does the conceptual work of creating 
     * lines, circles, etc...
     */
    public void setCurrentTurtleDisplayCanvas(TurtleDisplayCanvas frame)
    {
	frame.setCurrentTurtle(this);
	frame.setCurrentlyDisplayedShapes(currentlyDisplayedShapes);
	currentDisplayCanvas = frame;
	
	return;
    }

    public TurtleDisplayCanvas getCurrentTurtleDisplayCanvas()
    {
	return currentDisplayCanvas;
    }
    
    /**
     * Redraw the entire scene each time a command is added to the command list.
     * 
     * @param reanimate if true, reanimate the scene each time a
     * {@link TurtleCommand} is added to the command list.
     */
    public void setReanimateImmediate(boolean reanimate)
    {
	reanimateImmediate = reanimate;
    }
    
    public boolean getReanimateImmediate()
    {
	return reanimateImmediate;
    }
    
    /**
     * Clear the current scene and start rendering the entire scene from 
     * scratch.
     *
     * @param wait If true, wait for a current animation thread to finish 
     * its work.
     */
    public void clearAndRenderScene(boolean wait)
    {
	if (currentAnimationThread.isAlive()) {
	    if (wait) {
		try {
		    currentAnimationThread.join();
		} catch (InterruptedException e) {
		    ; // Handle...
		}
	    } else {
		return;
	    }
	}
	
	// Set up a new animation thread to render the scene.
	currentAnimationThread = new AnimationThread(this);
	currentAnimationThread.start();
	
	return;
    }
    
    /**
     * Move the Turtle's orientation over right some number of radians or 
     * degrees.
     * 
     * @param delta The number of radians (or degrees) which the Turtle should 
     * move to the right.
     */
    public void moveCurrentOrientationRight(double delta)
    {
	currentOrientation -= convertToDegrees(delta);
	fixupOrientation();
	return;
    }
    
    /**
     * Move the Turtle's orientation over left some number of radians or 
     * degrees.
     * 
     * @param delta The number of radians (or degrees) which the Turtle should 
     * move to the right.
     */
    public void moveCurrentOrientationLeft(double delta)
    {
	currentOrientation += convertToDegrees(delta);
	fixupOrientation();
	return;
    }

    /**
     * Set the current orientation of the turtle.
     * 
     * @param orientation The new orientation of the turtle.
     */
    public void setOrientation(int orientation)
    {
	currentOrientation = orientation;
	fixupOrientation();
	return;
    }
    
    /**
     * Get the current orientation in radians.
     */
    public double currentOrientationRadians()
    {
	return Math.toRadians(currentOrientation);
    }
    
    /**
     * Convert the user's input to radians.
     * 
     * @param value The value the user wants to enter
     */
    private double convertToDegrees(double value)
    {
	if (usingRadians) {
	    return Math.toDegrees(value);
	} else {
	    return value;
	}
    }
    
    /**
     * Fix up the orientation so that it falls in the range [0,2*pi]
     */
    private void fixupOrientation()
    {
	while (currentOrientation < 0 && currentOrientation > 360) {
	    if (currentOrientation < 0) {
		currentOrientation += 360;
	    } else if (currentOrientation > 360) {
		currentOrientation -= 360;
	    }
	}
    }
    
    /**
     * We 
     * @param
     */
    public int calculateTimeStepsFromPathLength(int pathLength)
    {
	return 0;
    }
    
    //
    // BEGIN TURTLE COMMANDS
    // 
    // A few convience functions for users so that they do not have to
    // hand create commands. Instead, they can simply use these
    // functions to add points, draw, etc...
    //
    
    /**
     * Move the turtle forward some number of units.
     * 
     * @param units The number of units to move the Turtle forward
     */
    public void turtleForward(int units)
    {
	commandList.add(new TurtleCommandForward(units));
	return;
    }
    
    /**
     * Move the turtle backwards some number of units.
     * 
     * @param units The number of units to move the turtle backward
     */
    public void turtleBackward(int units)
    {
	commandList.add(new TurtleCommandBack(units));
	return;
    }
    
    /**
     * 
     */
    public void turtleLeft(int units)
    {
	commandList.add(new TurtleCommandRight(-1 * units));
	return;
    }
    
    public void turtleRight(int units)
    {
	commandList.add(new TurtleCommandRight(units));
	return;
    }
    
    public void turtlePenDown()
    {
	commandList.add(new TurtleCommandPenDown());
	return;
    }
    
    public void turtlePenUp()
    {
	commandList.add(new TurtleCommandPenUp());
	return;
    }
    
    /**
     * 
     */
    public void turtleGoto(Point destination)
    {
	commandList.add(new TurtleCommandGoto(destination));
	return;
    }
    
    public void turtleSetRadians()
    {
	commandList.add(new TurtleCommandSetRadians());
	return;
    }
    
    public void turtleSetDegrees()
    {
	commandList.add(new TurtleCommandSetDegrees());
	return;
    }
    
    public void turtleSetHeading(double heading)
    {
	commandList.add(new TurtleCommandSetHeading(heading));
	return;
    }
    
    public void turtleCircle(int radius, int angle)
    {
	commandList.add(new TurtleCommandCircle(radius, angle));
    }
    
    public void turtleDot(int radius, int angle)
    {
	commandList.add(new TurtleCommandDot(radius, angle));
    }
    
    public void turtleSetColor(int r, int g, int b)
    {
	commandList.add(new TurtleCommandSetPenColor(new Color(r,g,b)));
    }
    
    /**
     * Undo the last command.
     */
    public void undo()
    {
	return;
    }
    
    public void home()
    {
	currentX = 0;
	currentY = 0;
	currentOrientation = 90;
    }
    
    public void turtleBeginFillPolygon()
    {
	commandList.add(new TurtleCommandBeginPolyFill());
    }
    
    public void turtleEndFillPolygon()
    {
	commandList.add(new TurtleCommandEndPolyFill());
    }
    
    /**
     * Class to describe a vector, used for orienting the Turtle
     *
     * @author Kristopher Micinski
     */
    public class Vector
    {
	private double xComponent;
	private double yComponent;
	
	public Vector(double x, double y)
	{
	    xComponent = x;
	    yComponent = y;
	}
	
	public double getX() { return xComponent; }
	public double getY() { return yComponent; }
	
	public void setX(double x) { xComponent = x; }
	public void setY(double y) { yComponent = y; }
	
	/**
	 * Get the Euclidian length of the vector.
	 * 
	 * @returns the Euclidian length of the vector
	 */
	public double getLength()
	{ 
	    return Math.sqrt(
			     xComponent * xComponent
			     + yComponent * yComponent);
	}
	
	/**
	 * Scale the vector by some amount.
	 * 
	 * @param scale The amount to scale the vector
	 */
	public void scaleVector(double scale)
	{
	    xComponent *= scale;
	    yComponent *= scale;
	    return;
	}
    }
    
    /**
     * Class to describe a vector sitting someone where in space.
     * 
     * @author Kristopher Micinski
     */
    public class RootedVector extends Vector {
	private Point mStartPoint;
	
	private RootedVector(Point startPoint, double xComponent, double yComponent)
	{
	    super(xComponent, yComponent);
	    
	}
	
	public Point getEndpoint() { 
	    return new Point((int)(mStartPoint.getX()+getX()),
			     (int)(mStartPoint.getY()+getY()));
	}
	
	public Point getStartpoint() {
	    return mStartPoint;
	}	
    }
    
    /**
     * Get the current vector to describe the direction in which the 
     * turtle is pointing.
     *
     * @return A vector describing the current orientation of the turtle.
     */
    public Vector getTurtleDirection()
    {
	return new Vector(Math.cos(Math.toRadians(currentOrientation)),
			  Math.sin(Math.toRadians(currentOrientation)));
    }
    
    /**
     * Class to run the animation loop.
     * 
     * Whenever the Turtle is given commands, it stores them in a command list. 
     * However, the environment includes more than just static images, there is 
     * also animation. To handle animation, we have a separate thread which 
     * handles animation, looping, etc...
     * 
     * In short, all animation and drawing is done using this thread, which 
     * updates the currentlyDisplayedShapes list to render the contents on the 
     * screen via the {@link TurtleDisplayCanvas}.
     * 
     * @author Kristopher Micinski
     */
    private class AnimationThread extends Thread {
	Turtle turtle;
	
	AnimationThread(Turtle t) {
	    turtle = t;
	    return;
	}
	
	public void setTurtle(Turtle t) { turtle = t; }
	public Turtle getTurtle() { return turtle; }
	
	/**
	 * Actually run the thread to animate the scene.
	 */
	public void run() {
	    turtle.home();
	    
	    // Make sure we aren't displaying any shapes at first.
	    synchronized (currentlyDisplayedShapes) {
		currentlyDisplayedShapes.clear();
	    }
	    
	    // Synchronize on the command list
	    synchronized (commandList) {
		// Run all the commands in sequence
		for (TurtleCommand i : commandList) {
		    i.executeCommand(turtle);
		    
		    turtle.getCurrentTurtleDisplayCanvas().revalidate();
		    turtle.getCurrentTurtleDisplayCanvas().repaint();
		    
		    // After each command check to see if the current point should 
		    // be added to the current Turtle polygon fill.
		    // 
		    // One question I have, how should we do this in the presence of
		    // the pen being up? I'm just going to ignore points if the pen 
		    // is up, but this is probablyi not the ``best idea''
		    //    -- Kris, Feb 6th, 2010, 3:07 AM
		    if (turtle.getPenDown()
			&& turtle.getCurrentlyFillingPolygon() != null) {
			Polygon p = turtle.getCurrentlyFillingPolygon();
			p.addPoint(turtle.getCurrentX(), turtle.getCurrentY());
		    }
		}
	    }
	}
    }
}
