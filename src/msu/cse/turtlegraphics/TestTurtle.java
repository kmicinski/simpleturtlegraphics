package msu.cse.turtlegraphics;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics2D;

public class TestTurtle 
{
    public static void main(String[] progArgs) 
    {
	System.out.println("Welcome to ``dumb logo,'' by Kristopher Micinski.");
	System.out.println("Challenge problem: is dumb logo turing complete? " 
			   + "What might you add to make it so?\n");
	System.out.println("f (units), b (units), l (units), r (units), pendown, penup, beginfill, endfill");
	System.out.println("pendown, goto (x) (y), setheading (heading), circle (radius) (angle),");
	System.out.println("dot (radius) (angle), setcolor (r) (g) (b), q to quit...");
	
	BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	
	Turtle turtle = new Turtle();
	TurtleDisplayFrame frame = new TurtleDisplayFrame();
	frame.setVisible(true);
	
	turtle.setCurrentTurtleDisplayCanvas(frame.getCurrentCanvas());
	
	// prompt user for filename:
	while(true) {
	    String input = "";
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    System.out.print(">> ");
	    try {
		input = inputReader.readLine();
	    } catch (Exception e) {
		System.out.println("error!?");
	    }
	    
	    String[] parts = input.split(" ");
	    
	    if (parts.length < 1)
		continue;
	    
	    int[] args = new int[parts.length - 1];
	    int numArgs = args.length;
	    
	    for (int i = 1; i < parts.length; i++) {
		args[i-1] = Integer.parseInt(parts[i]);
	    }
	    
	    String cmd = parts[0];
	    
	    if (cmd.equals("f")) {
		turtle.turtleForward(args[0]);
	    } else if (cmd.equals("b")) {
		turtle.turtleBackward(args[0]);
	    } else if (cmd.equals("l")) {
		turtle.turtleLeft(args[0]);
	    } else if (cmd.equals("r")) {
		turtle.turtleRight(args[0]);
	    } else if (cmd.equals("pendown")) {
		turtle.turtlePenDown();
	    } else if (cmd.equals("penup")) {
		turtle.turtlePenUp();
	    } else if (cmd.equals("goto")) {
		turtle.turtleGoto(new Point(args[0],args[1]));
	    } else if (cmd.equals("setheading")) {
		turtle.turtleSetHeading(args[0]);
	    } else if (cmd.equals("circle")) {
		turtle.turtleCircle(args[0],args[1]);
	    } else if (cmd.equals("dot")) {
		turtle.turtleCircle(args[0],args[1]);
	    } else if (cmd.equals("color")) {
		turtle.turtleSetColor(args[0],args[1],args[2]);
	    } else if (cmd.equals("beginfill")) {
		turtle.turtleBeginFillPolygon();
	    } else if (cmd.equals("endfill")) {
		turtle.turtleEndFillPolygon();
	    } else if (cmd.equals("q")) {
		System.out.println("Thanks for using dumb logo...");
		frame.dispose();
		frame = null;
		System.exit(0);
		return;
	    }
	}
	/*
	SwingUtilities.invokeLater(new Runnable() {
		public void run() 
		{
		    Turtle turtle = new Turtle();
		    TurtleDisplayFrame frame = new TurtleDisplayFrame();
		    frame.setVisible(true);

		    turtle.setCurrentTurtleDisplayCanvas(frame.getCurrentCanvas());
		    turtle.turtlePenDown();
		    turtle.turtleBeginFillPolygon();
		    turtle.turtleSetDegrees();
		    turtle.turtleBackward(200);

		    turtle.turtleLeft(90);
		    turtle.turtleForward(200);
		    turtle.turtleEndFillPolygon();
		    turtle.turtlePenUp();
		    turtle.turtleGoto(new Point(300,400));
		    turtle.turtlePenDown();
		    turtle.turtleLeft(90);
		    turtle.turtleSetColor(255,0,0);
		    turtle.turtleCircle(50,0);
		    turtle.turtleSetColor(0,0,0);
		    turtle.turtleForward(200);

		    //turtle.clearAndRenderScene(true);
		    return;
		    }});
	*/
    }
}
