package sample;

import javafx.scene.shape.Arc;

public abstract class Obstacle extends Interactable {

    protected static float rot_speed =1;
    protected static float rot_dirn =1;
    public void action(Game g) {
        if(g.getBall()!=null && isTouching((Arc) g.getBall().getGrp().getChildren().get(0))) {

            g.getPane().getChildren().remove(g.getBall().getGrp());
            g.setBall_null();
        }
    }
    public static float get_rot()
    {
        return rot_speed;
    }
    public static void set_rot(float rot)
    {
        rot_speed=rot;
    }
    public static float get_rot_d()
    {
        return rot_dirn;
    }
    public static void set_rot_d(float rot)
    {
        rot_dirn=rot;
    }
    public abstract void toggle_pause();
    public abstract void toggle_play();
    public abstract boolean isTouching(Arc gp);


}
