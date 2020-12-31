
package sample;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.shape.Arc;

import java.io.Serializable;

public abstract class Interactable{

    protected float[] coords;
    protected boolean paused = false;
    Timeline timeline;
    Group root;

    public void set_paused(boolean b) {
        paused=b;
    }

    public boolean get_paused() {
        return paused;
    }


    public Group getGrp()
    {
        return root;
    }




    abstract public boolean isTouching(Arc gp);
    abstract public void action(Game g);



}

