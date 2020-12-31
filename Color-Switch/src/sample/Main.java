package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.text.Font;
import  javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.event.ActionEvent;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;

//import static sample.Load_page.*;
public class Main extends Application{


    @Override
    public void start(Stage stage) {

        MainPage mainPage = new MainPage();
        mainPage.display(stage);

        String curr_Dir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + curr_Dir);
    }
    public static void main(String[] args) {
        launch(args);
    }
}

interface Page
{
    void display(Stage _stage);
    void do_exit(Stage _stage);
}


class MainPage extends Application implements Page{

    static Scene scene1;

    public void display(Stage _stage)
    {
        try {
            this.start(_stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void start_new(Stage stage)
    {
       /* Only for checking
        Death_menu death_menu = new Death_menu();
        death_menu.display(stage);*/

        Game game = new Game();
        game.play(stage);

    }
    private void resume_game(Stage stage)
    {

        Load_page load_page = new Load_page();
        load_page.display(stage);


    }
    @Override
    public void do_exit(Stage _stage)
    {
        Platform.exit();
    }




    @Override
    public void start(Stage stage) throws Exception {
        Image image = new Image(new FileInputStream("./img/title.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(50);
        imageView.setY(5);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        Text t = new Text();
        t.setText("0");
        t.setX(25);
        t.setY(60);
        t.setFont(Font.font(20));
        t.setFill(Color.WHITE);

        Image _image = new Image(new FileInputStream("./img/stars.png"));
        ImageView _imageView = new ImageView(_image);
        _imageView.setX(5);
        _imageView.setY(5);
        _imageView.setFitHeight(50);
        _imageView.setFitWidth(50);
        _imageView.setPreserveRatio(true);

        Image image1 = new Image(new FileInputStream("./img/new1.png"));
        Button button = new Button();
        button.setGraphic(new ImageView(image1));
        button.setTranslateX(60);
        button.setTranslateY(150);
        button.setOnAction(e -> this.start_new(stage));

        Image image2 = new Image(new FileInputStream("./img/save1.png"));
        Button button1 = new Button();
        button1.setGraphic(new ImageView(image2));
        button1.setTranslateX(60);
        button1.setTranslateY(250);
        button1.setOnAction(e -> this.resume_game(stage));

        Image image3 = new Image(new FileInputStream("./img/exit1.png"));
        Button button2 = new Button();
        button2.setGraphic(new ImageView(image3));
        button2.setTranslateX(60);
        button2.setTranslateY(350);
        button2.setOnAction(e -> this.do_exit(stage));

        Group root = new Group(_imageView,imageView, button, button1, button2,t);

        stage.setTitle("Color Switch");
        scene1 = new Scene(root, 300, 500, Color.BLACK);
        stage.setScene(scene1);
        stage.show();


    }


}

class Load_page extends Application implements Page{

    Button button7;
    public void display(Stage _stage)
    {
        try {
            this.start(_stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void do_exit(Stage _stage) {

        _stage.setScene(MainPage.scene1);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Image image4 = new Image(new FileInputStream("./img/select.png"));
        ImageView imageView1 = new ImageView(image4);
        imageView1.setX(50);
        imageView1.setY(5);
        imageView1.setFitHeight(200);
        imageView1.setFitWidth(200);
        imageView1.setPreserveRatio(true);

        Image image5 = new Image(new FileInputStream("./img/game1.png"));
        Button button3 = new Button();

        button3.setGraphic(new ImageView(image5));
        //button3.setContentDisplay(ContentDisplay.LEFT);
        button3.setTranslateX(20);
        button3.setTranslateY(100);
        button3.setOnAction(e -> System.out.println("Game 1 clicked"));

        Image image6 = new Image(new FileInputStream("./img/game2.png"));
        Button button4 = new Button();
        button4.setGraphic(new ImageView(image6));
        button4.setTranslateX(20);
        button4.setTranslateY(180);
        button4.setOnAction(e -> System.out.println("Game 2 clicked"));

        Image image7 = new Image(new FileInputStream("./img/game3.png"));
        Button button5 = new Button();
        button5.setGraphic(new ImageView(image7));
        button5.setTranslateX(20);
        button5.setTranslateY(260);
        button5.setOnAction(e -> System.out.println("Game 3 clicked"));

        Image image8 = new Image(new FileInputStream("./img/game4.png"));
        Button button6 = new Button();
        button6.setGraphic(new ImageView(image8));
        button6.setTranslateX(20);
        button6.setTranslateY(340);
        button6.setOnAction(e -> System.out.println("Game 4 clicked"));

        Image image9 = new Image(new FileInputStream("./img/home1.png"));
        button7 = new Button();
        button7.setGraphic(new ImageView(image9));
        button7.setTranslateX(65);
        button7.setTranslateY(420);
        button7.setOnAction(e-> this.do_exit(stage));
        Group root1 = new Group(imageView1, button3, button4, button5, button6, button7);
        Scene scene2 = new Scene(root1, 300, 500, Color.BLACK);
        stage.setScene(scene2);
        stage.show();
    }
}

class Pause_menu extends Application
{
    Game curr_game;

    Pause_menu(Game _game)
    {
        curr_game=_game;
    }
    public void display(Stage _stage)
    {
        try {
            this.start(_stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void do_exit(Stage _stage)
    {
        _stage.setScene(MainPage.scene1);
    }
    public void resume(Stage _stage)
    {
        _stage.setScene(curr_game.getGame_scene());
    }
    @Override
    public void start(Stage stage) throws Exception {

        Image image1 = new Image(new FileInputStream("./img/paused.png"));
        ImageView imageView1 = new ImageView(image1);
        imageView1.setX(50);
        imageView1.setY(50);
        imageView1.setFitHeight(200);
        imageView1.setFitWidth(200);
        imageView1.setPreserveRatio(true);


        Image image2 = new Image(new FileInputStream("./img/resume.png"));
        Button button1 = new Button();
        button1.setGraphic(new ImageView(image2));
        button1.setTranslateX(65);
        button1.setTranslateY(180);
        button1.setOnAction(e -> this.resume(stage));


        Image image3 = new Image(new FileInputStream("./img/save2.png"));
        Button button2 = new Button();
        button2.setGraphic(new ImageView(image3));
        button2.setTranslateX(65);
        button2.setTranslateY(280);
        button2.setOnAction(e -> System.out.println("Save game button clicked"));


        Image image4 = new Image(new FileInputStream("./img/quit.png"));
        Button button3 = new Button();
        button3.setGraphic(new ImageView(image4));
        button3.setTranslateX(65);
        button3.setTranslateY(380);
        button3.setOnAction(e -> this.do_exit(stage));


        Group root = new Group(imageView1, button1, button2, button3);
        Scene scene_p = new Scene(root, 300, 500, Color.BLACK);
        stage.setScene(scene_p);
        stage.show();


    }
}

class Death_menu extends Application{

    Game curr_game;
    Death_menu(Game _game)
    {
        curr_game=_game;
    }
    public void display(Stage _stage)
    {
        try {
            this.start(_stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void do_exit(Stage _stage)
    {
        _stage.setScene(MainPage.scene1);
    }
    public void restart(Stage _stage)
    {
        Game new_game = new Game();
        new_game.play(_stage);
    }
    public void revive(Stage _stage)
    {
        //Ball ball = new Ball();
        _stage.setScene(curr_game.getGame_scene());
    }

    @Override
    public void start(Stage stage) throws Exception {

        Image image1 = new Image(new FileInputStream("./img/died.png"));
        ImageView imageView1 = new ImageView(image1);
        imageView1.setX(50);
        imageView1.setY(50);
        imageView1.setFitHeight(200);
        imageView1.setFitWidth(200);
        imageView1.setPreserveRatio(true);

        Text g_score = new Text();
        g_score.setText("Score: 0");
        g_score.setX(105);
        g_score.setY(140);
        g_score.setFont(Font.font(25));
        g_score.setFill(Color.WHITE);



        Image image2 = new Image(new FileInputStream("./img/revive.png"));
        Button button1 = new Button();
        button1.setGraphic(new ImageView(image2));
        button1.setTranslateX(65);
        button1.setTranslateY(170);
        button1.setOnAction(e -> revive(stage));


        Image image3 = new Image(new FileInputStream("./img/new2.png"));
        Button button2 = new Button();
        button2.setGraphic(new ImageView(image3));
        button2.setTranslateX(65);
        button2.setTranslateY(270);
        button2.setOnAction(e -> this.restart(stage));


        Image image4 = new Image(new FileInputStream("./img/exit3.png"));
        Button button3 = new Button();
        button3.setGraphic(new ImageView(image4));
        button3.setTranslateX(65);
        button3.setTranslateY(400);
        button3.setOnAction(e -> this.do_exit(stage));

        Group root = new Group(imageView1, g_score, button1, button2, button3);
        Scene scene_p = new Scene(root, 300, 500, Color.BLACK);
        stage.setScene(scene_p);
        stage.show();

    }
}


class Game extends Application
{
    Scene game_scene;
    Pause_menu pause_menu;
    Death_menu death_menu;
    StackPane pane;
    Loop loop;
    Ball ball;
    Color_switcher color_switcher;
    public void play(Stage _stage)
    {
        pause_menu = new Pause_menu(this);
        death_menu = new Death_menu(this);
        try {
            this.start(_stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected Scene getGame_scene()
    {
        return game_scene;
    }
    public void setupTimeline(){
        KeyFrame kf = new KeyFrame(Duration.millis(10), new TimeHandler());
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    private class TimeHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event){

            if(ball!=null && color_switcher!=null && color_switcher.isTouching((Arc) ball.getGrp().getChildren().get(0))) {
                ball.setColor(color_switcher.action());
                pane.getChildren().remove(color_switcher.getGrp());
                color_switcher=null;
            }
            if(ball!=null && loop!=null && loop.isTouching((Arc) ball.getGrp().getChildren().get(0))) {

                pane.getChildren().remove(ball.getGrp());
                ball=null;
            }

        }
    }

    @Override
    public void start(Stage stage) throws Exception {



        pane=new StackPane();

        Image s_image = new Image(new FileInputStream("./img/stars.png"));
        ImageView s_imageView = new ImageView(s_image);
        s_imageView.setX(5);
        s_imageView.setY(5);
        s_imageView.setFitHeight(50);
        s_imageView.setFitWidth(50);
        s_imageView.setPreserveRatio(true);

        Text score = new Text();
        score.setText("0");
        score.setX(25);
        score.setY(60);
        score.setFont(Font.font(20));
        score.setFill(Color.WHITE);

        Group s_group = new Group(s_imageView, score);
        s_group.setTranslateX(-120);
        s_group.setTranslateY(-220);

        Button p_button = new Button();
        Image p_image = new Image(new FileInputStream("./img/pause.png"));
        p_button.setGraphic(new ImageView(p_image));
        p_button.setTranslateX(400);
        p_button.setTranslateY(20);
        p_button.setOnAction(e -> pause_menu.display(stage));

        Text t = new Text();
        t.setText("Press 'A' to make the ball jump");
        t.setFont(Font.font(20));
        t.setFill(Color.WHITE);
        Group t_group = new Group(t);
        t_group.setTranslateX(1);
        t_group.setTranslateY(230);

        Group b_group = new Group(p_button);
        b_group.setTranslateX(115);
        b_group.setTranslateY(-220);

        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        loop = new Loop();
        Rhombus rhombus  = new Rhombus();
        Tri_loop tri_loop = new Tri_loop();
        Twin_loop_h twin_loop_h = new Twin_loop_h();
        Twin_loop_v twin_loop_v = new Twin_loop_v();
        Fan fan = new Fan();
        color_switcher = new Color_switcher();


        rhombus.getGrp().setTranslateY(-100);
        loop.getGrp().setTranslateY(-100);
        twin_loop_h.getGrp().setTranslateY(-100);
        twin_loop_v.getGrp().setTranslateY(-100);
        fan.getGrp().setTranslateY(-100);
        tri_loop.getGrp().setTranslateY(-100);
        color_switcher.getGrp().setTranslateY(100);

        ball = new Ball();
        ball.getGrp().setTranslateY(280);
        pane.getChildren().addAll(b_group, s_group,t_group);
        pane.getChildren().addAll(ball.getGrp());
        pane.getChildren().add(0, loop.getGrp());                  //inserted at 0 to keep ball on the foreground
        pane.getChildren().add(0, color_switcher.getGrp());



        //pane.getChildren().addAll(rhombus.getGrp());
        //pane.getChildren().addAll(fan.getGrp());
        //pane.getChildren().addAll(tri_loop.getGrp());
        //pane.getChildren().addAll(twin_loop_h.getGrp());
        //pane.getChildren().addAll(twin_loop_v.getGrp());

        //pane.getChildren().addAll();


        game_scene = new Scene(pane, 600, 600, Color.BLACK);
        game_scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A && ball!=null) {
                //System.out.println("A key was pressed");
                ball.impulse();
            }
            if (e.getCode() == KeyCode.A && ball==null) {
                System.out.println("Ball dead");

                death_menu.display(stage);
                //ball.impulse();
            }
        });
        stage.setScene(game_scene);
        stage.show();
        this.setupTimeline();


    }
}