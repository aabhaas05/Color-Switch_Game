package sample;

import javafx.animation.RotateTransition; 
import javafx.application.Application; 
import static javafx.application.Application.launch;

import java.util.Date;

import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.paint.Color; 
import javafx.scene.shape.*;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import javafx.event.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class Fan {
	private final BorderPane pane;
	private final Polygon[] polys=new Polygon[5];
	private final Group root = new Group();
	private final Group root2 = new Group();
	public Fan(){
		pane =new BorderPane();
		polys[0] = new Polygon();
		polys[0].getPoints().addAll(307.5, 157.5,
				307.5, 142.5,
				292.5, 142.5,
				292.5, 157.5);
		polys[1] = new Polygon();
		polys[1].getPoints().addAll(307.5, 157.5,
				307.5, 142.5,
				307.5+90.0, 142.5,
				307.5+90.0, 157.5);
		polys[2] = new Polygon();
		polys[2].getPoints().addAll(307.5, 157.5,
				307.5, 157.5+90.0,
				292.5, 157.5+90.0,
				292.5, 157.5);
		polys[3] = new Polygon();
		polys[3].getPoints().addAll(292.5, 142.5,
				292.5, 157.5,
				292.5-90.0, 157.5,
				292.5-90.0, 142.5);
		polys[4] = new Polygon();
		polys[4].getPoints().addAll(307.5, 142.5-90.0,
				307.5, 142.5,
				292.5, 142.5,
				292.5, 142.5-90.0);
		polys[0].setFill(Color.BLACK);
		polys[1].setFill(Color.YELLOW);
		polys[2].setFill(Color.CYAN);
		polys[3].setFill(Color.DEEPPINK);
		polys[4].setFill(Color.PURPLE);
		for(int i=0;i<5;i++) {
			root.getChildren().add(polys[i]);
		}
		root2.getChildren().add(root);
		pane.setCenter(root2);
		this.setupTimeline();
	}
	public BorderPane getRoot() {
		return pane;
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
		    root.setRotate(root.getRotate()-1.5);
//		    root.setTranslateX(100);
//		    Translate t=new Translate();
//		    t.setX(100);
//		    t.setY(0);
//		    t.setZ(0);
//		    t.
//		    root.getTransforms().addAll(t);
		}
	}
}