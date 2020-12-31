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
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Loop {

	private final Arc[] arcs=new Arc[6];
	private final Group root = new Group();
	public Loop(){

		for(int i=0;i<4;i++) {
			arcs[i] = new Arc();
			arcs[i].setCenterX(300.0f);
			arcs[i].setCenterY(0f);
			arcs[i].setRadiusX(130.0f);
			arcs[i].setRadiusY(130.0f);
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
		arcs[4].setCenterY(0f);
		arcs[4].setRadiusX(115.0f);
		arcs[4].setRadiusY(115.0f);
		arcs[4].setStartAngle(0.0f);
		arcs[4].setLength(360.0f);
		arcs[4].setType(ArcType.ROUND);
		arcs[4].setFill(Color.BLACK);
		arcs[5]=new Arc();
		arcs[5].setCenterX(300.0f);
		arcs[5].setCenterY(0.0f);
		arcs[5].setRadiusX(101.0f);
		arcs[5].setRadiusY(101.0f);
		arcs[5].setStartAngle(0.0f);
		arcs[5].setLength(360.0f);
		arcs[5].setType(ArcType.ROUND);
		arcs[5].setFill(Color.BLACK);
		for(int i=0;i<6;i++) {
			root.getChildren().add(arcs[i]);  
		}
		this.setupTimeline();
	}

	
	public Group getGrp() {
		return root;
	}
	public boolean isTouching(Arc gp) {
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

		return false;
	}
	public void setupTimeline(){
			KeyFrame kf = new KeyFrame(Duration.millis(10),
			new TimeHandler());
			Timeline timeline = new Timeline(kf);
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
	}
	private class TimeHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event){

			root.setRotate(root.getRotate()+1);

		}
	}
}