package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Reverse_ball extends Item {

    private final Group root = new Group();
    private Circle reverse_ball = new Circle();

    Reverse_ball() throws FileNotFoundException {
        //Circle Reverse_ball = new Circle();
        reverse_ball.setRadius(35);
        Image st_image = new Image(new FileInputStream("./img/reverse.png"));
        reverse_ball.setFill(new ImagePattern(st_image));
        root.getChildren().add(reverse_ball);
    }

    public Group getGrp() {
        return root;
    }

    @Override
    public boolean isTouching(Arc gp) {
        Shape intersect = Shape.intersect(gp, reverse_ball);
        //System.out.println(intersect.getBoundsInParent().getWidth()+" "+intersect.getBoundsInParent().getHeight()+" ");
        return (intersect.getBoundsInParent().getHeight()!=-1);
    }
    @Override
    public void action(Game g) {

        //Obstacle.set_rot(-Obstacle.get_rot());
        if(g.getBall()!=null && this!=null && this.isTouching((Arc) g.getBall().getGrp().getChildren().get(0))) {

            Obstacle.set_rot_d(-Obstacle.get_rot_d());
            g.getPane().getChildren().remove(this.getGrp());
            //g.interactables.remove(this);
            g.interactables.set(g.interactables.indexOf(this), null);
            return;

        }
    }
}
