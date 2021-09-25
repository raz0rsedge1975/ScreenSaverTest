package javaFX.domain;

import javafx.scene.canvas.GraphicsContext;

import static javafx.scene.paint.Color.rgb;

public class Attractor {

    private final Vector pos;

    public Attractor(double x, double y) {
        pos = new Vector(x, y);
    }

    public Vector getPos() {
        return pos;
    }

    public void setX(double x) {
        this.pos.setX((int) x);
    }

    public void setY(double y) {
        this.pos.setY(y);
    }

    public void draw(GraphicsContext gtx) {
        //Font Color
        gtx.setFill(rgb(20,20,30));
        gtx.fillOval(pos.getX(), pos.getY(), 10, 10); //Größe Pointer
    }
}