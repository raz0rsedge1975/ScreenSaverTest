package javaFX.gui;
import java.util.*;
import javaFX.domain.Attractor;
import javaFX.domain.Particle;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class DrawScreen extends Canvas {

    private GraphicsContext gtx;

    private int amount_particles = 0;
    private final int particles_per_click = 1000;

    private final List<Particle> particles;
    private Attractor attractor;

    Timer t = new Timer();

    public DrawScreen() {
        particles = new ArrayList<>();
        init();
        build();
    }

    private void init() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        this.setWidth(screenBounds.getWidth());
        this.setHeight(screenBounds.getHeight());
        gtx = this.getGraphicsContext2D();
    }


    private void build() {
        attractor = new Attractor(this.getWidth() / 2, this.getHeight() / 2);

        this.setOnMouseDragged(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                attractor.setX(e.getX());
                attractor.setY(e.getY());
            }

        });

        gtx.setFill(Color.rgb(0, 0, 0, 1));
        gtx.setGlobalAlpha(1.);
        gtx.fillRect(0, 0, this.getWidth(), this.getHeight());


        this.setOnMouseClicked(e -> {
            //get type of mouse click
            MouseButton button = e.getButton();

            //if left mouse click, change pos of attractor to mouse pos
            if (button == MouseButton.PRIMARY) {
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int x = 1 + (int) (Math.random() * ((2560 - 1) + 10));
                        int y = 1 + (int) (Math.random() * ((1440 - 1) + 10));
                        attractor.setX(x);
                        attractor.setY(y);
                    }
                }, 0, 5000); //alle 5 sekunden...

//                attractor.setX(e.getX());
//                attractor.setY(e.getY());
            }

            //else if rightclick, create particles

            else if (button == MouseButton.SECONDARY) {
                if (amount_particles + particles_per_click <= 40000) {
                    amount_particles += particles_per_click;
                    for (int i = 0; i < particles_per_click; i++) {
                        createParticle(e.getX(), e.getY(), attractor);
                    }
                }

            }
        });
    }

    private void createParticle(double x, double y, Attractor a) {
        Particle p = new Particle(x, y, a);
        particles.add(p);
    }

    public void draw() {

        gtx.setFill(Color.rgb(0, 0, 0, 0.9));
        gtx.fillRect(0, 0, this.getWidth(), this.getHeight());
        particles.forEach(p -> {
            p.draw(gtx);
        });

        attractor.draw(gtx);
        gtx.fillText("Particles: " + amount_particles, 20, 20);
        //Particle on top covering underlaying particle
        gtx.setGlobalBlendMode(BlendMode.SRC_OVER);
    }

    public void update() {
        particles.forEach(Particle::update);
    }

}