package b1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;


public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("TEST TEST TEST");
        primaryStage.show();
    }

    public void draw(FXGraphics2D graphics) {
        graphics.translate(500, 500);
        graphics.scale(1,-1);

        //frame
        graphics.drawLine(0,0,100,0);
        graphics.drawLine(0,0,0,80);
        graphics.drawLine(100,0,100,80);
        graphics.drawLine(0,80,50,150);
        graphics.drawLine(50,150,100,80);

        //door
        graphics.drawLine(10,0,10,50);
        graphics.drawLine(40,0,40,50);
        graphics.drawLine(10,50,40,50);

        //window
        graphics.drawLine(50,20,90,20);
        graphics.drawLine(50,60,90,60);
        graphics.drawLine(50,20,50,60);
        graphics.drawLine(90,20,90,60);
    }
}