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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;

class Pause_menu extends Application
{
    private Save_choice save_choice;
    private Game curr_game;
    static Scene scene_p;
    private String[] g_file = {"game_file1.ser", "game_file2.ser", "game_file3.ser", "game_file4.ser"};
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
        _stage.setScene(MainPage.getScene1());
    }
    private void resume(Stage _stage)
    {
        _stage.setScene(curr_game.getGame_scene());

        if(curr_game.getBall()!=null)
        {
            curr_game.getBall().toggle_play();
        }
        for(int i=0;i<curr_game.interactables.size();i++)
        {
            //System.out.println(interactables.get(i).paused);
            if(curr_game.interactables.get(i)!= null && curr_game.interactables.get(i).get_paused())
            {
                curr_game.interactables.get(i).set_paused(false);
                //System.out.println(curr_game.interactables.get(i).paused);
                if(curr_game.interactables.get(i) instanceof Obstacle)
                {
                    ((Obstacle) curr_game.interactables.get(i)).toggle_play();
                }
            }
        }
    }
    private void choice_for_save(Stage _stage)
    {
        save_choice = new Save_choice(this);
        save_choice.display(_stage);
    }
    public void save_game(Stage _stage)
    {
        /*for(int i=0; i<curr_game.int_check.size(); i++)
        {
            System.out.println(curr_game.int_check.get(i));
        }*/
        //System.out.println(curr_game.int_check.size());

        for(int i=0; i<curr_game.interactables.size(); i++)
        {
            curr_game.insert_y_coords(curr_game.get_interactable_y_coords(i));
        }
        curr_game.set_color_code(curr_game.getBall().getColor()) ;
        curr_game.set_ball_y_coords(curr_game.getBall().getGrp().getTranslateY());

        try
        {
            FileOutputStream file = new FileOutputStream(g_file[save_choice.get_choice()]);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(curr_game);
            out.close();
            file.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        this.do_exit(_stage);
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
        button2.setOnAction(e -> this.choice_for_save(stage));


        Image image4 = new Image(new FileInputStream("./img/quit.png"));
        Button button3 = new Button();
        button3.setGraphic(new ImageView(image4));
        button3.setTranslateX(65);
        button3.setTranslateY(380);
        button3.setOnAction(e -> this.do_exit(stage));


        Group root = new Group(imageView1, button1, button2, button3);
        scene_p = new Scene(root, 300, 500, Color.BLACK);
        stage.setScene(scene_p);
        stage.show();


    }
}