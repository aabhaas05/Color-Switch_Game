package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Rhombus {

	private final Polygon[] polys=new Polygon[5];
	private final Group root = new Group();
	public Rhombus(){

		double[] coordsX= {350.0,300.0,250.0,300.0};
		double[] coordsY= {150.0,250.0,150.0,50.0};
		for(int i=0;i<4;i++)
		{
			polys[i] = new Polygon();        
			polys[i].getPoints().addAll(300.0, 150.0, coordsX[i], coordsY[i], coordsX[(i+1)%4], coordsY[(i+1)%4]);
		}
		polys[0].setFill(Color.YELLOW);
		polys[1].setFill(Color.CYAN);
		polys[2].setFill(Color.DEEPPINK);
		polys[3].setFill(Color.PURPLE);
		polys[4]=new Polygon();
		polys[4].getPoints().addAll(coordsX[0]-15, coordsY[0], coordsX[1], coordsY[1]-15, coordsX[2]+15, coordsY[2], coordsX[3], coordsY[3]+15);
		polys[4].setFill(Color.BLACK);
		for(int i=0;i<5;i++)
		{
			root.getChildren().add(polys[i]);  
		}
		this.setupTimeline();
	}
	
	public Group getGrp() {
		return root;
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