package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Speed_ball extends Item {

    private final Group root = new Group();
    private Circle speed_ball = new Circle();

    Speed_ball() throws FileNotFoundException {
        //Circle speed_ball = new Circle();
        speed_ball.setRadius(35);
        Image st_image = new Image(new FileInputStream("./img/speed_up.png"));
        speed_ball.setFill(new ImagePattern(st_image));
        root.getChildren().add(speed_ball);
    }

    public Group getGrp() {
        return root;
    }

    @Override
    public boolean isTouching(Arc gp) {
        Shape intersect = Shape.intersect(gp, speed_ball);
        //System.out.println(intersect.getBoundsInParent().getWidth()+" "+intersect.getBoundsInParent().getHeight()+" ");
        return (intersect.getBoundsInParent().getHeight()!=-1);
    }
    @Override
    public void action(Game g) {

        if(g.getBall()!=null && this!=null && this.isTouching((Arc) g.getBall().getGrp().getChildren().get(0))) {

            Obstacle.set_rot(Math.min(1.2f,Obstacle.get_rot()+0.5f));
            g.getPane().getChildren().remove(this.getGrp());
            //g.interactables.remove(this);
            g.interactables.set(g.interactables.indexOf(this), null);
            return;

        }


    }
}
