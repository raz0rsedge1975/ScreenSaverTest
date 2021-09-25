package javaFX.domain;
import javaFX.staticMethods.Vec;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import java.util.Random;

public class Particle {

    private final Attractor attractor;
    private final Vector pos;
    private Vector vel;


    private Vector acc = new Vector(0, 0);

    public Particle(double x, double y, Attractor attractor) {
        pos = new Vector(x, y);
        this.attractor = attractor;
        detVel();
    }

    private void detVel() {
        double vel_strength = 10;
        this.vel = new Vector(Math.random() * vel_strength * 2 - vel_strength, Math.random() * vel_strength * 2 - vel_strength);
    }


    public Vector getPos() {
        return pos;
    }

    public void setX(double x) {
        this.pos.setX(x);
    }


    public void setY(double y) {
        this.pos.setY(y);
    }

    public void attracted() {
        javaFX.domain.Vector dir = Vec.sub(attractor.getPos(), pos);
        double dsquared = dir.magSq();
        dsquared = constrain(dsquared);
        double g = 250;
        double strength = g / dsquared;
        dir.setMag(strength);
        this.acc = dir;
    }

    private double constrain(double getal) {
        if (getal > (double) 400) {
            return 400;
        } else if (getal < (double) 100) {
            return 100;
        } else {
            return getal;
        }
    }

    public void update() {
        pos.add(vel);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();


        if (pos.getX() <= 20) {
            //pos.setX(20);
            vel.touchObject();
        }

        if (pos.getX() >= primaryScreenBounds.getWidth() - 10) {
            //pos.setX(primaryScreenBounds.getWidth() - 20);
            vel.touchObject();
        }

        if (pos.getY() <= 20) {
            //pos.setY(20);
            vel.touchObject();
        }

        if (pos.getY() >= primaryScreenBounds.getHeight() - 10) {
            //pos.setY(primaryScreenBounds.getHeight() - 50);
            vel.touchObject();
        }

        vel.add(acc);
        attracted();
    }


    public void draw(GraphicsContext gtx) {
        Random rd = new Random();
                gtx.fillOval(pos.getX(), pos.getY(), 4, 4);             //Größe der Pixel
                gtx.setFill(Color.rgb(rd.nextInt(55), rd.nextInt(15) , rd.nextInt(256), 1));      //Farbe der Pixel
            }


//    public void draw(GraphicsContext gtx) {
////        gtx.setFill(Color.rgb(100, 105, 255, 1));
//     gtx.fillOval(pos.getX(), pos.getY(), 2, 2);
//                gtx.setFill(Color.rgb(25, 5, 222, 1));
//    }

}