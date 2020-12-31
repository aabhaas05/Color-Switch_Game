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

public class Tri_loop {

	private final Arc[] arcs=new Arc[15];

	private final Group root = new Group();
	private final Group root1 = new Group();
	private final Group root2 = new Group();

	public Tri_loop(){


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
		for(int i=0;i<5;i++) {
			root.getChildren().add(arcs[i]);  
		}

		
		for(int i=5;i<9;i++) {
			arcs[i] = new Arc();
			arcs[i].setCenterX(300.0f);
			arcs[i].setCenterY(150.0f);
			arcs[i].setRadiusX(60.0f+15.0f);
			arcs[i].setRadiusY(60.0f+15.0f);
			arcs[i].setStartAngle(0.0f+90.0f*i);
			arcs[i].setLength(90.0f);
			arcs[i].setType(ArcType.ROUND);
		}
		arcs[5].setFill(Color.YELLOW);
		arcs[6].setFill(Color.PURPLE);
		arcs[7].setFill(Color.DEEPPINK);
		arcs[8].setFill(Color.CYAN);
		arcs[9]=new Arc();
		arcs[9].setCenterX(300.0f);
		arcs[9].setCenterY(150.0f);
		arcs[9].setRadiusX(45.0f+15.0f);
		arcs[9].setRadiusY(45.0f+15.0f);
		arcs[9].setStartAngle(0.0f);
		arcs[9].setLength(360.0f);
		arcs[9].setType(ArcType.ROUND);
		arcs[9].setFill(Color.BLACK);
		for(int i=5;i<10;i++) {
			root1.getChildren().add(arcs[i]);  
		}
		
		
		for(int i=0;i<4;i++) {
			arcs[10+i] = new Arc();
			arcs[10+i].setCenterX(300.0f);
			arcs[10+i].setCenterY(150.0f);
			arcs[10+i].setRadiusX(120.0f+15.0f);
			arcs[10+i].setRadiusY(120.0f+15.0f);
			arcs[10+i].setStartAngle(0.0f+90.0f*i);
			arcs[10+i].setLength(90.0f);
			arcs[10+i].setType(ArcType.ROUND);
		}
		arcs[10].setFill(Color.CYAN);
		arcs[11].setFill(Color.YELLOW);
		arcs[12].setFill(Color.PURPLE);
		arcs[13].setFill(Color.DEEPPINK);
		arcs[14]=new Arc();
		arcs[14].setCenterX(300.0f);
		arcs[14].setCenterY(150.0f);
		arcs[14].setRadiusX(105.0f+15.0f);
		arcs[14].setRadiusY(105.0f+15.0f);
		arcs[14].setStartAngle(0.0f);
		arcs[14].setLength(360.0f);
		arcs[14].setType(ArcType.ROUND);
		arcs[14].setFill(Color.BLACK);
		for(int i=10;i<15;i++) {
			root2.getChildren().add(arcs[i]);  
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

			root2.setRotate(root2.getRotate()+1);
		    root.setRotate(root.getRotate()-2);


		}
	}
}