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

public class Ball {


	private double t=0,a=3,v=-120*Math.sqrt(a);
	

	private Arc arc=new Arc();
	private Group root2 = new Group();
	public Ball(){

		arc.setCenterX(300.0f);
		arc.setCenterY(0f);
		arc.setRadiusX(15.0f);
		arc.setRadiusY(15.0f);
		arc.setStartAngle(0.0f);
		arc.setLength(360.0f);
		arc.setType(ArcType.ROUND);
		arc.setFill(Color.CYAN);

		root2.getChildren().add(arc);
		this.setupTimeline();
	}

	public void setColor(int ind) {
		switch(ind) {
			case 0:
				arc.setFill(Color.YELLOW);
				break;
			case 1:
				arc.setFill(Color.CYAN);
				break;
			case 2:
				arc.setFill(Color.DEEPPINK);
				break;
			case 3:
				arc.setFill(Color.PURPLE);
				break;
			default:
				break;
		}
	}
	public Group getGrp() {
		return root2;
	}
	
	public void impulse() {
		v=-120*Math.sqrt(a);
		t=0;
	}
	public void setupTimeline(){


			KeyFrame kf = new KeyFrame(Duration.millis(10), new TimeHandler());
			Timeline timeline = new Timeline(kf);
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
	}
	private class TimeHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event){

			v+=a*(t);
			//System.out.println(((root2.getTranslateY()+(v*t+a*t*t/2.0)/60)));
			if(((root2.getTranslateY()+(v*t+a*t*t/2.0)/60))>280)
			{
				return;
			}
		    root2.setTranslateY(root2.getTranslateY()+(v*t+a*t*t/2.0)/60);
		    t+=2/100.0;
		}
	}
}