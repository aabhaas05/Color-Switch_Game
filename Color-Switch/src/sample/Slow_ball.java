package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Slow_ball extends Item {

    private final Group root = new Group();
    private Circle slow_ball = new Circle();

    Slow_ball() throws FileNotFoundException {

        slow_ball.setRadius(35);
        Image st_image = new Image(new FileInputStream("./img/speed_down.png"));
        slow_ball.setFill(new ImagePattern(st_image));
        root.getChildren().add(slow_ball);
    }

    public Group getGrp() {
        return root;
    }

    @Override
    public boolean isTouching(Arc gp) {
        Shape intersect = Shape.intersect(gp, slow_ball);
        return (intersect.getBoundsInParent().getHeight()!=-1);
    }
    @Override
    public void action(Game g) {


        if(g.getBall()!=null && this!=null && this.isTouching((Arc) g.getBall().getGrp().getChildren().get(0))) {

            Obstacle.set_rot(Math.max(0.5f,Obstacle.get_rot()-0.5f));
            g.getPane().getChildren().remove(this.getGrp());
            //g.interactables.remove(this);
            g.interactables.set(g.interactables.indexOf(this), null);
            return;

        }
    }
}
