package msu.cse.turtlegraphics;

/**
 * An abstract description of a command which will perform some change 
 * to the environment.
 * 
 * The TurtleCommand class is the main endpoint to dispatch commands to the 
 * {@link Turtle}. Whenever the Turtle is to be moved, or perform some other 
 * action, the command is implemented as a TurtleCommand. To actually perform 
 * the actions, the Turtle uses an animation loop to display actions that are.  
 * 
 * The TurtleCommands do not actually *draw* objects on the screen. Instead, 
 * the TurtleCommands affect the {@link TurtleDisplayFrame} and add 
 * {@link RenderableShape} objects to be drawn. Then, every time the 
 * TurtleDisplayFrame redraws the screen it simply looks at those primitive 
 * objects and renders them on the screen. 
 * 
 * This methodology is used for a number of reasons:
 * <ul>
 *   <li> It allows the Turtle to perform all of the conceptual work for moving 
 * the Turle.
 *   <li> We can then store a simplified ``compiled'' version of the commands which 
 * must be redrawn each time the window is redrawn.
 *   <li> We can separate animation and command semantics themselvs.
 * </ul>
 *
 * All commands have durations, a set of time for which they last. These 
 * durations are to determine animation characteristics. For example, 
 * if we want to be able to animate a line over the course of five seconds, 
 * we can set its duration to 5. Durations are given with respect to the 
 * unit time (which is determined by the Turtle itsef, along with the unit 
 * length).
 * 
 * @author Kristopher Micinski
 */
public abstract class TurtleCommand {
    // The duration of the command
    private double duration;
    
    protected void setDuration(double d) { duration = d; }
    public double getDuration() { return duration; }
    
    /**
     * Actually execute the command. This may put shapes in the
     * {@link TurtleDisplayFrame}, or may simply wait a number of 
     * seconds.
     */ 
    public abstract void executeCommand(Turtle turtle);
}
