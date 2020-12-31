package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class SuperStar extends Item {

    private final Group root = new Group();
    private final Circle star_circle = new Circle();

    SuperStar() throws FileNotFoundException {
        //Circle star_circle = new Circle();
        star_circle.setRadius(35);
        Image st_image = new Image(new FileInputStream("./img/big_star.png"));
        star_circle.setFill(new ImagePattern(st_image));
        root.getChildren().add(star_circle);


    }
    public Group getGrp() {
        return root;
    }

    @Override
    public boolean isTouching(Arc gp) {
        Shape intersect = Shape.intersect(gp, star_circle);
        return (intersect.getBoundsInParent().getHeight()!=-1);
    }

    @Override
    public void action(Game g) {

        if(g.getBall()!=null && isTouching((Arc) g.getBall().getGrp().getChildren().get(0))) {

            g.getPane().getChildren().remove(this.getGrp());
            disappear(this);
            //g.interactables.remove(this);
            g.interactables.set(g.interactables.indexOf(this), null);
            Random rand=new Random();
            for(int i=0;i<3+rand.nextInt(5);i++) g.inc_score();
            g.set_score();
            return;

        }
    }



}
