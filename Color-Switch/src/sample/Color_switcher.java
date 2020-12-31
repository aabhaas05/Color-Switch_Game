package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;
import java.util.Random;

public class Color_switcher {

	private Arc[] arcs=new Arc[4];
	private Arc arc=new Arc();
	private Group root = new Group();
	public Color_switcher(){

		for(int i=0;i<4;i++) {
			arcs[i] = new Arc();
			arcs[i].setCenterX(300.0f);
			arcs[i].setCenterY(0f);
			arcs[i].setRadiusX(15.0f);
			arcs[i].setRadiusY(15.0f);
			arcs[i].setStartAngle(0.0f+90.0f*i);
			arcs[i].setLength(90.0f);
			arcs[i].setType(ArcType.ROUND);
		}
		arc.setCenterX(300.0f);
		arc.setCenterY(0f);
		arc.setRadiusX(15.0f);
		arc.setRadiusY(15.0f);
		arc.setStartAngle(0.0f);
		arc.setLength(360.0f);
		arc.setType(ArcType.ROUND);
		arc.setFill(Color.WHITE);
		arcs[0].setFill(Color.YELLOW);
		arcs[1].setFill(Color.CYAN);
		arcs[2].setFill(Color.DEEPPINK);
		arcs[3].setFill(Color.PURPLE);
		for(int i=0;i<4;i++) {
			root.getChildren().add(arcs[i]);  
		}

	}
	public boolean isTouching(Arc gp) {
		Shape intersect = Shape.intersect(gp, arcs[0]);
		//System.out.println(intersect.getBoundsInParent().getWidth()+" "+intersect.getBoundsInParent().getHeight()+" ");
		return (intersect.getBoundsInParent().getHeight()!=-1);
	}
	public int action()
	{
		Random rand = new Random();
		int r_choice = rand.nextInt(4);
		if(r_choice!=1)
		{
			return r_choice;
		}
		else
		{
			while(r_choice==1)
			{
				r_choice = rand.nextInt(4);
			}
		}
		return r_choice;
	}

	
	public Group getGrp() {
		return root;
	}
	

}