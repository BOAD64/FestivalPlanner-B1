package b1.simulation;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;

public class Clock {

    private LocalTime currentTime;
    private double speedMultiplier;
    private double previousSpeedMultiplier;
    private SimpleDateFormat format;
    private Point2D location;
    private Font clockFont;
    private boolean paused;
    private Color fontColor;


    public Clock(double speedMultiplier, LocalTime startTime, Point2D location){
        this.currentTime = startTime;
        this.speedMultiplier = speedMultiplier;
        this.format = new SimpleDateFormat("hh:mm");
        this.location = location;
        this.clockFont = new Font("Rockwell", Font.PLAIN, 70);
        this.paused = false;
        this.fontColor = Color.white;
        this.previousSpeedMultiplier = speedMultiplier;
    }

    /**
     * Gets the current time
     * @return current time
     */
    public LocalTime getCurrentTime(){
        return this.currentTime;
    }

    /**
     * gets the new Delta time
     * used to apply speed multiplier to delta time in simulation class
     * @param deltaTime deltaTime provided by animation timer
     * @return altered deltaTime value to be used by all update methods, excluding that of the clock itself.
     */
    public double getNewDeltaTime(double deltaTime){
        return deltaTime * this.speedMultiplier;
    }

    /**
     * sets the speed multiplier to the value
     * @param speedMultiplier value speedMultiplier is to be set to.
     */
    public void setSpeedMultiplier(double speedMultiplier){
        this.speedMultiplier = speedMultiplier;
    }

    /**
     * updates the current time to the given value
     * @param originalDeltaTime deltaTime provided by animation timer, not passed through getNewDeltaTime().
     */
    public void update(double originalDeltaTime){
        this.currentTime = this.currentTime.plusNanos((long)((originalDeltaTime * Math.pow(10, 9)) * 10 * this.speedMultiplier));
    }

    /**
     * Called when pause button is presses, checks if the simulation is paused. If not, the simulation enters
     * the paused state. If yes, the simulation is unpaused.
     * Pausing is done by setting the speedMultiplier to zero, so updating this variable should be prevented while paused.
     */
    public void pause(){
        if (!this.paused){
            this.paused = true;
            this.fontColor = Color.red;
            this.previousSpeedMultiplier = this.speedMultiplier;
            this.speedMultiplier = 0;

        }else {
            this.paused = false;
            this.fontColor = Color.white;
            this.speedMultiplier = this.previousSpeedMultiplier;
        }
    }

    /**
     * Returns whether or not simulation is paused. Can be used for preventing updating of speedMultiplier in paused state.
     * @return Boolean value; true if paused, false if not paused
     */
    public boolean isPaused(){
        return this.paused;
    }

    /**
     * draws the clock with the current time
     * @param graphics used for drawing the clock to the screen
     * @param location the location where the clock needs to be drawn
     */
    public void draw(FXGraphics2D graphics, Point2D location){
        this.location = location;
        graphics.setColor(this.fontColor);
        graphics.setFont(this.clockFont);
        String time = this.currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        graphics.drawString(time, (float)location.getX(), (float)location.getY());
        Shape textOutline = this.clockFont.createGlyphVector(graphics.getFontRenderContext(), time).getOutline();
        graphics.setColor(Color.black);
        graphics.draw(AffineTransform.getTranslateInstance(location.getX(),location.getY()).createTransformedShape(textOutline));
    }

}
