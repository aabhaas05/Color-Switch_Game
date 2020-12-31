package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;

class Save_choice extends Application implements Page{

    private final Pause_menu pm;
    private int choice;


    Save_choice(Pause_menu _pm)
    {
       this.pm=_pm;
    }
    public int get_choice()
    {
        return choice;
    }
    public void set_choice(int choice, Stage _stage)
    {
        this.choice=choice;
        pm.save_game(_stage);
    }
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

        _stage.setScene(Pause_menu.scene_p);

    }

    @Override
    public void start(Stage stage) throws Exception {

        Image select_image = new Image(new FileInputStream("./img/select.png"));
        ImageView select_imageview = new ImageView(select_image);
        select_imageview.setX(50);
        select_imageview.setY(5);
        select_imageview.setFitHeight(200);
        select_imageview.setFitWidth(200);
        select_imageview.setPreserveRatio(true);

        Image game_1 = new Image(new FileInputStream("./img/game1.png"));
        Button game1_b = new Button();

        game1_b.setGraphic(new ImageView(game_1));
        //game1_b.setContentDisplay(ContentDisplay.LEFT);
        game1_b.setTranslateX(20);
        game1_b.setTranslateY(100);
        game1_b.setOnAction(e -> this.set_choice(0, stage));


        Image game_2 = new Image(new FileInputStream("./img/game2.png"));
        Button game2_b = new Button();
        game2_b.setGraphic(new ImageView(game_2));
        game2_b.setTranslateX(20);
        game2_b.setTranslateY(180);
        game2_b.setOnAction(e -> this.set_choice(1, stage));
        //game2_b.setOnAction(e -> System.out.println("Game 2 clicked"));


        Image game_3 = new Image(new FileInputStream("./img/game3.png"));
        Button game3_b = new Button();
        game3_b.setGraphic(new ImageView(game_3));
        game3_b.setTranslateX(20);
        game3_b.setTranslateY(260);
        game3_b.setOnAction(e -> this.set_choice(2, stage));


        Image game_4 = new Image(new FileInputStream("./img/game4.png"));
        Button game4_b = new Button();
        game4_b.setGraphic(new ImageView(game_4));
        game4_b.setTranslateX(20);
        game4_b.setTranslateY(340);
        game4_b.setOnAction(e -> this.set_choice(3, stage));


        Group game_root = new Group(select_imageview, game1_b, game2_b, game3_b, game4_b);
        Scene select_scene = new Scene(game_root, 300, 500, Color.BLACK);
        stage.setScene(select_scene);
        //System.out.println("yes");
        stage.show();
        //System.out.println("yes1");
    }
}

