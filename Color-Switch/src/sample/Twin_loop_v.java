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
import javafx.util.Duration;

public class Twin_loop_v {

	private final Arc[] arcs=new Arc[10];
	private final Group root = new Group();
	private final Group root1 = new Group();
	private final Group root2 = new Group();

	public Twin_loop_v(){

		for(int i=0;i<4;i++) {
			arcs[i] = new Arc();
			arcs[i].setCenterX(300.0f);
			arcs[i].setCenterY(150.0f);
			arcs[i].setRadiusX(90.0f+15.0f);
			arcs[i].setRadiusY(90.0f+15.0f);
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
		arcs[4].setRadiusX(75.0f+15.0f);
		arcs[4].setRadiusY(75.0f+15.0f);
		arcs[4].setStartAngle(0.0f);
		arcs[4].setLength(360.0f);
		arcs[4].setType(ArcType.ROUND);
		arcs[4].setFill(Color.BLACK);
		for(int i=0;i<5;i++)
		{
			root.getChildren().add(arcs[i]);  
		}

		
		for(int i=5;i<9;i++)
		{
			arcs[i] = new Arc();
			arcs[i].setCenterX(arcs[0].getCenterX());
			arcs[i].setCenterY(arcs[0].getCenterY()+2*arcs[0].getRadiusY());
			arcs[i].setRadiusX(arcs[0].getRadiusX());
			arcs[i].setRadiusY(arcs[0].getRadiusY());
			arcs[i].setStartAngle(0.0f+90.0f*i);
			arcs[i].setLength(90.0f);
			arcs[i].setType(ArcType.ROUND);
		}
		arcs[5].setFill(Color.DEEPPINK);
		arcs[6].setFill(Color.CYAN);
		arcs[7].setFill(Color.YELLOW);
		arcs[8].setFill(Color.PURPLE);
		arcs[9]=new Arc();
		arcs[9].setCenterX(arcs[5].getCenterX());
		arcs[9].setCenterY(arcs[5].getCenterY());
		arcs[9].setRadiusX(arcs[4].getRadiusX());
		arcs[9].setRadiusY(arcs[4].getRadiusY());
		arcs[9].setStartAngle(0.0f);
		arcs[9].setLength(360.0f);
		arcs[9].setType(ArcType.ROUND);
		arcs[9].setFill(Color.BLACK);
		for(int i=5;i<10;i++) {
			root1.getChildren().add(arcs[i]);  
		}
		root2.getChildren().add(root);
		root2.getChildren().add(root1);

		this.setupTimeline();
	}

	
	public Group getGrp() {
		return root2;
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

			root1.setRotate(root1.getRotate()+1);
		    root.setRotate(root.getRotate()-1);

		}
	}
}