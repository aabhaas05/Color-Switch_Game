package sample;

import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;

public abstract class Item extends Interactable {

    protected float radius;
    public void disappear(Item item)
    {
        item=null;
    }
    public boolean isTouching(Arc b)
    {
        Shape gp = null;
        Shape intersect = Shape.intersect(gp, b);
        return (intersect.getBoundsInParent().getHeight()!=-1);
    }
}
