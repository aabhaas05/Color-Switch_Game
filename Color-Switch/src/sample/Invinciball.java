package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Invinciball extends Item {

    private final Group root = new Group();
    private final Circle ball_circle = new Circle();

    Invinciball() throws FileNotFoundException {
        //Circle star_circle = new Circle();
        ball_circle.setRadius(25);
        Image st_image = new Image(new FileInputStream("./img/big_ball.png"));
        ball_circle.setFill(new ImagePattern(st_image));
        root.getChildren().add(ball_circle);


    }
    public Group getGrp() {
        return root;
    }

    @Override
    public boolean isTouching(Arc gp) {
        Shape intersect = Shape.intersect(gp, ball_circle);
        return (intersect.getBoundsInParent().getHeight()!=-1);
    }

    @Override
    public void action(Game g) {

        if(g.getBall()!=null && this.isTouching((Arc) g.getBall().getGrp().getChildren().get(0))) {

            g.getPane().getChildren().remove(this.getGrp());
            disappear(this);
            //g.interactables.remove(this);
            g.interactables.set(g.interactables.indexOf(this), null);
            g.getBall().setColor(-1);

        }

    }



}
