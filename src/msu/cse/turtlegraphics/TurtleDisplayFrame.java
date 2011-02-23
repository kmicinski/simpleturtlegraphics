package msu.cse.turtlegraphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

/**
 * A JFrame implementation which is capable of holding 
 * {@link TurtleDispayCanvas} objects.
 * 
 * @author Kristopher Micinski
 *
 */
public class TurtleDisplayFrame extends JFrame
{
    private int defaultWidth = 800;
    private int defaultHeight = 600;
    
    private JMenuBar menuBar;
    private JMenu helpMenu;
    private JMenu displayMenu;
    private JMenuItem menuItem;
    private TurtleDisplayCanvas currentCanvas;
        
    /**
     * Create a new TurtleDisplayFrame
     * 
     */
    public TurtleDisplayFrame()
    {
	super("Turtle graphics display engine (v0.0)");
	currentCanvas = new TurtleDisplayCanvas();
	
	//setSize(defaultWidth, defaultHeight);

	menuBar = new JMenuBar();
	helpMenu = new JMenu("Help");
	menuBar.add(helpMenu);

	displayMenu = new JMenu("Display");
	
	menuBar.add(displayMenu);
	menuItem = new JMenuItem("About...");
	helpMenu.add(menuItem);

	setJMenuBar(menuBar);
	
	setContentPane(currentCanvas);

	resize(currentCanvas.getPreferedSize());
	
	return;
    }
    
    /**
     * Get this Frame's currently displayed turtle canvas.
     * 
     */
    public TurtleDisplayCanvas getCurrentCanvas()
    {
	return currentCanvas;
    }
}
