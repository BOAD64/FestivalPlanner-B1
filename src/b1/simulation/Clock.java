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
    private SimpleDateFormat format;
    private Point2D location;
    private Font clockFont;


    public Clock(double speedMultiplier, LocalTime startTime, Point2D location){
        this.currentTime = startTime;
        this.speedMultiplier = speedMultiplier;
        this.format = new SimpleDateFormat("hh:mm");
        this.location = location;
        this.clockFont = new Font("Rockwell", Font.PLAIN, 70);
    }

    /**
     * gets the current time
     * @return
     */
    public LocalTime getCurrentTime(){
        return this.currentTime;
    }

    /**
     * gets the new Delta time
     * used to apply speed multiplier to delta time in simulation class
     * @param deltaTime
     * @return
     */
    public double getNewDeltaTime(double deltaTime){
        return deltaTime * speedMultiplier;
    }

    /**
     * sets the speed multiplier to the value
     * @param speedMultiplier
     */
    public void setSpeedMultiplier(double speedMultiplier){
        this.speedMultiplier = speedMultiplier;
    }

    /**
     * updates the current time to the given value
     * @param originalDeltaTime
     */
    public void update(double originalDeltaTime){
        this.currentTime = this.currentTime.plusNanos((long)((originalDeltaTime * Math.pow(10, 9)) * 60 * speedMultiplier));
    }

    /**
     * draws the clock with the current time
     * @param graphics
     * @param location
     */
    public void draw(FXGraphics2D graphics, Point2D location){
        this.location = location;
        graphics.setColor(Color.white);
        graphics.setFont(clockFont);
        String time = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        graphics.drawString(time, (float)location.getX(), (float)location.getY());
        Shape textOutline = clockFont.createGlyphVector(graphics.getFontRenderContext(), time).getOutline();
        graphics.setColor(Color.black);
        graphics.draw(AffineTransform.getTranslateInstance(location.getX(),location.getY()).createTransformedShape(textOutline));
    }

}