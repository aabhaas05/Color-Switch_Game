package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;



class Death_menu extends Application{

    private Game curr_game;
    private final MainPage mainPage;
    private final Tooltip tooltip1 = new Tooltip("Need 10 stars to revive");
    Death_menu(Game _game)
    {
        curr_game=_game;
        mainPage= _game.getMainPage();
        //mainPage = _game.mainPage;
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
        curr_game.getMainPage().add_stars(curr_game.get_score());
        //curr_game.mainPage.add_stars(curr_game.get_score());
        _stage.setScene(MainPage.getScene1());
    }
    private void restart(Stage _stage) throws FileNotFoundException {

        curr_game.getMainPage().add_stars(curr_game.get_score());
        //curr_game.mainPage.add_stars(curr_game.get_score());
        curr_game = new Game(mainPage);
        curr_game.play(_stage);
    }
    private void revive(Stage _stage)
    {
        //Ball ball = new Ball();
        if(mainPage.getTotal_stars()>=10)
        {
            mainPage.decTotal_stars();
            curr_game.re_ball();
            curr_game.getPane().getChildren().addAll(curr_game.getBall().getGrp());
            if(curr_game.getBall()==null)
            {
                System.out.println("null");
            }

            curr_game.getTimeline().play();
            //curr_game.timeline.play();
            for(int j=curr_game.interactables.size()-1;j>=0;j--) {

                if(!curr_game.getPane().getChildren().get(j).equals(curr_game.getBall()))
                {
                    curr_game.interactables.remove(j);
                    curr_game.getPane().getChildren().remove(j);
                }
            }
            _stage.setScene(curr_game.getGame_scene());

        }
        else
        {
            tooltip1.setText("Total stars are less than 10");
        }


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
        g_score.setText("Score: "+ curr_game.get_star());
        g_score.setX(105);
        g_score.setY(140);
        g_score.setFont(Font.font(25));
        g_score.setFill(Color.WHITE);

        tooltip1.setShowDelay(Duration.ZERO);
        Image image2 = new Image(new FileInputStream("./img/revive.png"));
        Button button1 = new Button();
        button1.setGraphic(new ImageView(image2));
        button1.setTranslateX(65);
        button1.setTranslateY(170);
        button1.setTooltip(tooltip1);
        button1.setOnAction(e -> revive(stage));


        Image image3 = new Image(new FileInputStream("./img/new2.png"));
        Button button2 = new Button();
        button2.setGraphic(new ImageView(image3));
        button2.setTranslateX(65);
        button2.setTranslateY(270);
        button2.setOnAction(e -> {
            try {
                this.restart(stage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });


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


