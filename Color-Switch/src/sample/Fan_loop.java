package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Fan_loop extends Obstacle {


	private final Polygon[] polys=new Polygon[6];
	private final Arc[] arcs=new Arc[6];
	private final Group root = new Group();
	private final Group root1 = new Group();
	private final Group root2 = new Group();
	public Fan_loop(){

		for(int i=0;i<4;i++)
		{
			arcs[i] = new Arc();
			arcs[i].setCenterX(300.0f);
			arcs[i].setCenterY(150.0f);
			arcs[i].setRadiusX(90.0f + 30.0f);
			arcs[i].setRadiusY(90.0f + 30.0f);
			arcs[i].setStartAngle(0.0f+90.0f*i);
			arcs[i].setLength(90.0f);
			arcs[i].setType(ArcType.ROUND);
		}
		arcs[0].setFill(Color.YELLOW);
		arcs[1].setFill(Color.CYAN);
		arcs[2].setFill(Color.DEEPPINK);
		arcs[3].setFill(Color.PURPLE);
		arcs[4]=new Arc();
		arcs[4].setCenterX(300.0f);
		arcs[4].setCenterY(150.0f);
		arcs[4].setRadiusX(75.0f + 30.0f);
		arcs[4].setRadiusY(75.0f + 30.0f);
		arcs[4].setStartAngle(0.0f);
		arcs[4].setLength(360.0f);
		arcs[4].setType(ArcType.ROUND);
		arcs[4].setFill(Color.BLACK);
		arcs[5]=new Arc();
		arcs[5].setCenterX(300.0f);
		arcs[5].setCenterY(150.0f);
		arcs[5].setRadiusX(75.0f + 30.0f - 14.0f);
		arcs[5].setRadiusY(75.0f + 30.0f - 14.0f);
		arcs[5].setStartAngle(0.0f);
		arcs[5].setLength(360.0f);
		arcs[5].setType(ArcType.ROUND);
		arcs[5].setFill(Color.BLACK);
		for(int i=0;i<6;i++) {
			root1.getChildren().add(arcs[i]);  
		}
		polys[0] = new Polygon();
		polys[0].getPoints().addAll(307.5+arcs[4].getRadiusX()/3.0, 157.5-15,
				307.5+arcs[4].getRadiusX()/3.0, 142.5-15,
				292.5+arcs[4].getRadiusX()/3.0, 142.5-15,
				292.5+arcs[4].getRadiusX()/3.0, 157.5-15);
		polys[1] = new Polygon();
		polys[1].getPoints().addAll(307.5+arcs[4].getRadiusX()/3.0, 157.5-15,
				307.5+arcs[4].getRadiusX()/3.0, 142.5-15,
				307.5+arcs[4].getRadiusX()/3.0+arcs[4].getRadiusX()*0.68, 142.5-15,
				307.5+arcs[4].getRadiusX()/3.0+arcs[4].getRadiusX()*0.68, 157.5-15);
		polys[2] = new Polygon();
		polys[2].getPoints().addAll(307.5+arcs[4].getRadiusX()/3.0, 157.5-15,
				307.5+arcs[4].getRadiusX()/3.0, 157.5-15+arcs[4].getRadiusX()*0.68,
				292.5+arcs[4].getRadiusX()/3.0, 157.5-15+arcs[4].getRadiusX()*0.68,
				292.5+arcs[4].getRadiusX()/3.0, 157.5-15);
		polys[3] = new Polygon();
		polys[3].getPoints().addAll(292.5+arcs[4].getRadiusX()/3.0, 142.5-15,
				292.5+arcs[4].getRadiusX()/3.0, 157.5-15,
				292.5+arcs[4].getRadiusX()/3.0-arcs[4].getRadiusX()*0.68, 157.5-15,
				292.5+arcs[4].getRadiusX()/3.0-arcs[4].getRadiusX()*0.68, 142.5-15);
		polys[4] = new Polygon();
		polys[4].getPoints().addAll(307.5+arcs[4].getRadiusX()/3.0, 142.5-15-arcs[4].getRadiusX()*0.68,
				307.5+arcs[4].getRadiusX()/3.0, 142.5-15,
				292.5+arcs[4].getRadiusX()/3.0, 142.5-15,
				292.5+arcs[4].getRadiusX()/3.0, 142.5-15-arcs[4].getRadiusX()*0.68);
		polys[5] = new Polygon();
		polys[5].getPoints().addAll(307.5+arcs[4].getRadiusX()/3.0, 157.5-15,
				307.5+arcs[4].getRadiusX()/3.0, 142.5-15,
				292.5+arcs[4].getRadiusX()/3.0, 142.5-15,
				292.5+arcs[4].getRadiusX()/3.0, 157.5-15);
		polys[0].setFill(Color.GRAY);
		polys[5].setFill(Color.GRAY);
		polys[2].setFill(Color.DEEPPINK);
		polys[3].setFill(Color.PURPLE);
		polys[4].setFill(Color.YELLOW);
		polys[1].setFill(Color.CYAN);
		for(int i=0;i<6;i++) {
			root.getChildren().add(polys[i]);
		}
		root2.getChildren().add(root1);
		root2.getChildren().add(root);

		this.setupTimeline();
	}

	public boolean isTouching(Arc gp) {

		if(gp.getFill().equals(Color.ANTIQUEWHITE)) {
//			System.out.println("white");
			return false;
		}
		Shape intersect;
		int i=0;
		do {
			intersect = Shape.intersect(gp, arcs[i]);
			if(intersect.getBoundsInParent().getHeight()!=-1) {
				intersect=Shape.intersect(gp, arcs[5]);
				if(intersect.getBoundsInParent().getHeight()==-1 && !gp.getFill().equals(arcs[i].getFill())) {
					return true;
				}
			}
			i++;
		}while(i<=3);
		i=1;
		do {
			intersect = Shape.intersect(gp, polys[i]);
			if(intersect.getBoundsInParent().getHeight()!=-1) {
				intersect=Shape.intersect(gp, polys[5]);
				if(intersect.getBoundsInParent().getHeight()==-1 && !gp.getFill().equals(polys[i].getFill())) {
					return true;
				}
			}
			i++;
		}while(i<=4);

		return false;
	}



	@Override
	public void toggle_pause() {
		timeline.pause();
	}

	@Override
	public void toggle_play() {
		timeline.play();
	}

	public Group getGrp() {
		return root2;
	}
	
	private void setupTimeline(){
			KeyFrame kf = new KeyFrame(Duration.millis(10),
			new TimeHandler());
			timeline = new Timeline(kf);
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
	}

	private class TimeHandler
	implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event){
		    root.setRotate(root.getRotate()+(0.5 * rot_speed*rot_dirn));
		    root1.setRotate(root1.getRotate()-(0.5*rot_speed * rot_dirn));
		}
	}
}